package pe.com.edu.siaa.core.ejb.service.jms.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import pe.com.edu.siaa.core.ejb.util.jms.ConfiguracionJMSUtil;
import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.model.vo.CorreoVO;

/**
 * La Class JMSSender.
 * <ul>
 * <li>Copyright 2017 ndavilal- mapfre. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Tue Apr 29 19:20:13 COT 2017
 * @since SIAA-CORE 2.1
 */
public class JMSSender {

	/** La log. */
	private static Logger log = Logger.getLogger(JMSSender.class);

	// NOTA: Las dos lineas siguientes establecen el nombre de la fabrica de
	// conexiones de cola
	// Y la cola que queremos utilizar.
	public JMSSender() {
		super();
	}

	public static void sendMessage(Object obj) {
		try {
			List<Object> listaObject = new ArrayList<Object>();
			listaObject.add(obj);
			sendMessageList(listaObject);
		} catch (Exception e) {
			log.error("Error en JMSSender.sendMessage(listaObject) " + e.getMessage());
		}
	}
	
	public static void sendMessageTrama(Object obj) {
		try {
			List<Object> listaObject = new ArrayList<Object>();
			listaObject.add(obj);
			sendMessageTramaList(listaObject);
		} catch (Exception e) {
			log.error("Error en JMSSender.sendMessageTrama(listaObject) " + e.getMessage());
		}
	}
	
	public static void sendMessageCorreo(Object obj) {
		try {
			List<Object> listaObject = new ArrayList<Object>();
			listaObject.add(obj);
			sendMessageCorreoList(listaObject);
		} catch (Exception e) {
			log.error("Error en JMSSender.sendMessageCorreo(listaObject) " + e.getMessage());
		}
	}
	
	public static void sendMessageHeavy(Object obj) {
		try {
			List<Object> listaObject = new ArrayList<Object>();
			listaObject.add(obj);
			sendMessageHeavyList(listaObject);
		} catch (Exception e) {
			log.error("Error en JMSSender.sendMessageHeavy(listaObject) " + e.getMessage());
		}
	}

	public static void sendMessageList(List<Object> listaObject, String QCF_NAME, String QUEUE_NAME) throws NamingException, JMSException {
		try {
			InitialContext ctx = new InitialContext();
			ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup(QCF_NAME);
			Connection connection = connectionFactory.createConnection();
			Queue queue = (Queue) ctx.lookup(QUEUE_NAME);
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer messageProducer = session.createProducer(queue);
			for (Object obj : listaObject) {
				// ObjectMessage message = session.createObjectMessage(correo);
				ObjectMessage message = session.createObjectMessage();
				if (obj instanceof CorreoVO) {
					CorreoVO correoVO = (CorreoVO) obj;
					message.setObject(correoVO);
				}
				
				messageProducer.send(message);
			}
			messageProducer.close();
			session.close();
			connection.close();
			connectionFactory = null;
		} catch (Exception e) {
			log.error("Error en JMSSender.sendMessage(listaObject) " + e.getMessage());
		}
	}

	public static void sendMessageList(List<Object> listaObject) throws NamingException, JMSException {
		sendMessageList(listaObject, ConfiguracionJMSUtil.QCF_NAME, ConfiguracionJMSUtil.QUEUE_NAME);
	}
	
	public static void sendMessageHeavyList(List<Object> listaObject) throws NamingException, JMSException {
		sendMessageList(listaObject, ConfiguracionJMSUtil.QCF_REPORTE_HEAVY_NAME, ConfiguracionJMSUtil.QUEUE_REPORTE_HEAVY_NAME);
	}
	
	public static void sendMessageTramaList(List<Object> listaObject) throws NamingException, JMSException {
		sendMessageList(listaObject, ConfiguracionJMSUtil.QCF_TRAMA_CONTROL_NAME, ConfiguracionJMSUtil.QUEUE_TRAMA_CONTROL_NAME);
	}
	
	public static void sendMessageCorreoList(List<Object> listaObject) throws NamingException, JMSException {
		sendMessageList(listaObject, ConfiguracionJMSUtil.QCF_CORREO_NAME, ConfiguracionJMSUtil.QUEUE_CORREO_NAME);
	}
	
}