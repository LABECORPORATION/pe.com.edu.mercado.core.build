package pe.com.edu.siaa.core.ejb.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import pe.com.edu.siaa.core.ejb.service.jms.impl.JMSSender;
import pe.com.edu.siaa.core.ejb.service.local.ProcesarColaServiceLocal;
import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.model.vo.RegistroMensajeriaOutputVO;

/**
 * La Class ProcesarColaServiceLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal-
 * OSCE. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Tue Apr 29 19:20:13 COT 2017
 * @since SIAA-CORE 2.1
 */
 @Stateless(name = "ProcesarColaService", mappedName = "ejb/ProcesarColaService")//Servicios remotos
 @EJB(name = "java:app/ProcesarColaService", beanInterface = ProcesarColaServiceLocal.class)
 @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ProcesarColaServiceImpl implements ProcesarColaServiceLocal {
	 
    /** El log. */
	private static Logger log = Logger.getLogger(ProcesarColaServiceImpl.class);  
	
	/* (non-Javadoc)
	 * @see pe.gob.osce.bsp.nms.ejb.service.local.MensajeriaServiceLocal#enviarCorreo(java.util.List)
	 */
	@Override
	public RegistroMensajeriaOutputVO enviarCola(List<Object> listaObjectos) {
		final RegistroMensajeriaOutputVO resultado = new RegistroMensajeriaOutputVO();
		try {
			JMSSender.sendMessageList(listaObjectos);
		} catch (Exception e) {
			log.error("Error ProcesarColaServiceImpl.enviarReporte(final List<SolicitudReporteDTO> solicitudReporteDTOs) al enviar cola de reporte " + e.getMessage());
			resultado.setError(e.toString());
		}
				
		return resultado;
	}

}