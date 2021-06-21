package pe.com.edu.siaa.core.ui.jms;

import java.io.Serializable;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import pe.com.edu.siaa.core.ejb.service.helper.Referencia;
import pe.com.edu.siaa.core.ejb.service.local.MensajeriaServiceLocal;
import pe.com.edu.siaa.core.ejb.util.jms.ConfiguracionJMSUtil;
import pe.com.edu.siaa.core.ejb.util.jms.ThreadColaUtil;
import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.model.vo.CorreoVO;



/**
 * La Class MDBJMSTableroControlReporteServiceImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * mapfre. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Tue Apr 29 19:20:13 COT 2017
 * @since PWR v1.0
 */
@MessageDriven(activationConfig = { 
@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
@ActivationConfigProperty(propertyName = "destination", propertyValue = ConfiguracionJMSUtil.QUEUE_CORREO_NAME),
@ActivationConfigProperty (propertyName = "transactionTimeout" , propertyValue = ConfiguracionJMSUtil.TRANSACCTION_TIMEOUT )
})
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.BEAN)
public class MDBJMSCorreoServiceImpl implements MessageListener {
	
	/** La log. */
	private static Logger log = Logger.getLogger(MDBJMSCorreoServiceImpl.class);
	
	/** La automatico dao. */
	private transient MensajeriaServiceLocal mensajeriaServiceImpl;
	
	/**
	 * Instancia un nuevo proceso seleccion ficha service impl.
	 */
	public MDBJMSCorreoServiceImpl() {	
	}
	
	private void cargarEjb() {
		//Carga de EJBs remotos, si es que se requieren
		if (mensajeriaServiceImpl == null) {
			mensajeriaServiceImpl = Referencia.getReference(MensajeriaServiceLocal.class);	
		}
	}


	/* (non-Javadoc)
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(Message aMessage) {
		try {
			cargarEjb();
			if (aMessage instanceof ObjectMessage) {
				ObjectMessage objMessage = (ObjectMessage) aMessage;
				Serializable serialObj = objMessage.getObject();
				// objetos de la cola
				if (serialObj instanceof CorreoVO) {
					CorreoVO correoVO = (CorreoVO) serialObj;
					ThreadColaUtil.getInstance().put("mytron-correo", correoVO.getUUID(), Thread.currentThread());
					mensajeriaServiceImpl.enviarCorreoElectronico(correoVO);
					ThreadColaUtil.getInstance().remove("mytron-correo" + correoVO.getUUID());
				}
			}
		} catch (JMSException e) {
			log.error("Error MDBJMSCorreoServiceImpl.onMessage(Message aMessage) error al consumir el mensaje a cola JMSException e : " + e.getMessage());
		} catch (Exception e) {
			log.error("Error MDBJMSCorreoServiceImpl.onMessage(Message aMessage) error al consumir el mensaje a cola Exception e : " + e.getMessage());
		}
	}
}