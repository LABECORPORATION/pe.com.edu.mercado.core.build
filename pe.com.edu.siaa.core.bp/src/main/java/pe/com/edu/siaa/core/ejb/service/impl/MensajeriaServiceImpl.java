package pe.com.edu.siaa.core.ejb.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import pe.com.edu.siaa.core.ejb.service.jms.impl.JMSSender;
import pe.com.edu.siaa.core.ejb.service.local.MensajeriaServiceLocal;
import pe.com.edu.siaa.core.ejb.util.jasper.ArchivoUtilidades;
import pe.com.edu.siaa.core.ejb.util.jms.MailMessages;
import pe.com.edu.siaa.core.ejb.util.jms.ProcesarPlantillaUtil;
import pe.com.edu.siaa.core.ejb.util.jms.SMTPAuthenticator;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.model.type.AsuntoDetalleCorreoType;
import pe.com.edu.siaa.core.model.type.TipoDetalleCorreoType;
import pe.com.edu.siaa.core.model.util.ConstanteConfigUtil;
import pe.com.edu.siaa.core.model.util.ConstanteTypeUtil;
import pe.com.edu.siaa.core.model.util.ResourceUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;
import pe.com.edu.siaa.core.model.vo.CorreoVO;
import pe.com.edu.siaa.core.model.vo.DetalleCorreoVO;
import pe.com.edu.siaa.core.model.vo.FileVO;
import pe.com.edu.siaa.core.model.vo.RegistroMensajeriaOutputVO;
import pe.com.siaa.core.integration.ws.util.ProxyWSUtil;

/**
 * La Class MensajeriaServiceImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal-
 * OSCE. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Tue Apr 29 19:20:13 COT 2017
 * @since SIAA-CORE 2.1
 */
 @Stateless
 @EJB(name = "java:app/MensajeriaService", beanInterface = MensajeriaServiceLocal.class)//Servicios remotos
 @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class MensajeriaServiceImpl implements MensajeriaServiceLocal {
	 
    /** El log. */
	private static Logger log = Logger.getLogger(MensajeriaServiceImpl.class);  
		
	/* (non-Javadoc)
	 * @see pe.gob.osce.bsp.nms.ejb.service.local.MensajeriaServiceLocal#enviarCorreo(java.util.List)
	 */
	@Override
	public RegistroMensajeriaOutputVO enviarCorreo(final List<CorreoVO> correos) {
		final RegistroMensajeriaOutputVO resultado = new RegistroMensajeriaOutputVO();
		/*new Thread(new Runnable() {
			public void run() {*/
				try {
					for (CorreoVO correoVO : correos) {
						 correoVO.modificarUUID(UUIDUtil.generarUUID());
						//JMSSender.sendMessage(correoVO);
					}
					JMSSender.sendMessageCorreoList(new ArrayList<Object>(correos));
				} catch (Exception e) {
					log.error("Error MensajeriaServiceImpl.enviarCorreo(final List<CorreoVO> correos) al enviar cola de correo " + e.getMessage());
					resultado.setError(e.toString());
				}
				
			/*}
		}).start();*/
		
		return resultado;
	}
	
	
	
	/* (non-Javadoc)
	 * @see pe.com.edu.siaa.core.ejb.service.local.MensajeriaServiceLocal#enviarCorreoElectronico(pe.com.edu.siaa.core.model.vo.CorreoVO)
	 */
	@Override
	public void enviarCorreoElectronico(final CorreoVO correo) {
		ProxyWSUtil.proxy();
		// verificar que los datos sean correctos
		if (correo.getDetalleCorreo().size() > 0) {
			if (!correo.getAsunto().isEmpty()) {
				if (!correo.getContenido().isEmpty()) {
//					new Thread(new Runnable() {
//						public void run() {
							try {
								String siaaMail = null;
								if (!StringUtils.isNullOrEmpty(MailMessages.getParametro("mailserver.mail.smtp.user"))) {
									siaaMail = MailMessages.getParametro("mailserver.mail.smtp.user");
								}
								String siaaName = MailMessages.getParametro("mailserver.mail.name.from");
								String siaaPass = null;
								if (!StringUtils.isNullOrEmpty(MailMessages.getParametro("mailserver.mail.smtp.pass"))) {
									siaaPass = MailMessages.getParametro("mailserver.mail.smtp.pass");
								}
								Properties props = new Properties();
								if (!StringUtils.isNullOrEmpty(MailMessages.getParametro("mailserver.mail.smtp.host"))) {
									props.setProperty("mail.smtp.host",MailMessages.getParametro("mailserver.mail.smtp.host"));
								}
								if (!StringUtils.isNullOrEmpty(MailMessages.getParametro("mailserver.mail.smtp.port"))) {
									props.setProperty("mail.smtp.port",MailMessages.getParametro("mailserver.mail.smtp.port"));
								}
								if (!StringUtils.isNullOrEmpty(siaaMail)) {
 									props.setProperty("mail.smtp.user", siaaMail);
								}
								if (!StringUtils.isNullOrEmpty(MailMessages.getParametro("mailserver.mail.smtp.auth"))) {
									props.setProperty("mail.smtp.auth",MailMessages.getParametro("mailserver.mail.smtp.auth"));
								}
								if (!StringUtils.isNullOrEmpty(MailMessages.getParametro("mail.smtp.starttls.enable"))) {
									props.setProperty("mail.smtp.starttls.enable", MailMessages.getParametro("mail.smtp.starttls.enable"));
								}
								
								SMTPAuthenticator authenticator = null;
								if (!StringUtils.isNullOrEmpty(siaaMail) && !StringUtils.isNullOrEmpty(siaaPass)) {
									authenticator = new SMTPAuthenticator(siaaMail, siaaPass);
								}
								Session session = null;
								if (authenticator != null) {
									session =  Session.getInstance(props,authenticator);
								} else {
									session =  Session.getDefaultInstance(props,null);
								}
								MimeMessage messageToClient = new MimeMessage(session);
								String pwrMailForm = siaaMail;
								if (!StringUtils.isNullOrEmpty(MailMessages.getParametro("mailserver.mail.name.usar"))) {
									if (MailMessages.getParametro("mailserver.mail.name.usar").equalsIgnoreCase("true")) {
										if (!StringUtils.isNullOrEmpty(MailMessages.getParametro("mailserver.mail.name"))) {
											siaaName = siaaName + " " + MailMessages.getParametro("mailserver.mail.name");
										} else {
											siaaName = siaaName + " " + correo.getNombreCompleto();
										}
									} else {
										siaaName = siaaName + " " + correo.getNombreCompleto();
									}
								} else {
									siaaName = siaaName + " " + correo.getNombreCompleto();		
								}
							
								
								
								if (!StringUtils.isNullOrEmpty(pwrMailForm)) {
									messageToClient.setFrom(new InternetAddress(pwrMailForm, siaaName));
								} else {
									messageToClient.setFrom(new InternetAddress(MailMessages.getParametro("mailserver.mail.name.from.mail.defaulft"), siaaName));
								}
								
								Multipart multipart = new MimeMultipart();
								MimeBodyPart cuerpoCorreo = new MimeBodyPart();
								
								for (DetalleCorreoVO detCorreo : correo.getDetalleCorreo()) {
									String clientMail = detCorreo.getEmail();
									if (TipoDetalleCorreoType.DESTINATARIO.getKey().equals(detCorreo.getTipo())) {
										messageToClient.addRecipient(Message.RecipientType.TO, new InternetAddress(clientMail,clientMail));
									}
									if (TipoDetalleCorreoType.CON_COPIA.getKey().equals(detCorreo.getTipo())) {
										messageToClient.addRecipient(Message.RecipientType.BCC, new InternetAddress(clientMail,clientMail));
									}
									if (TipoDetalleCorreoType.CON_COPIA_OCULTA.getKey().equals(detCorreo.getTipo())) {
										messageToClient.addRecipient(Message.RecipientType.CC, new InternetAddress(clientMail,clientMail));
									}
								}
								messageToClient.setSubject(correo.getAsunto());
								//messageToClient.setContent(correo.getContenido(), "text/html");
								cuerpoCorreo.setContent(correo.getContenido(),"text/html");
								multipart.addBodyPart(cuerpoCorreo);
								
								//adjuntat archivo:
								File[] archivosAdjuntos = correo.getArchivosAdjuntos();

								if (archivosAdjuntos != null && archivosAdjuntos.length > 0) {
									for (File archivo : archivosAdjuntos) {
										MimeBodyPart adjuntoCorreo = new MimeBodyPart();
										adjuntoCorreo.attachFile(archivo);
										adjuntoCorreo.setFileName(archivo.getName());
										multipart.addBodyPart(adjuntoCorreo);
									}
								}
								messageToClient.setContent(multipart);
								messageToClient.setSentDate(new Date());
								// Enviamos los mensajes.
								if (authenticator != null) {
									Transport t = session.getTransport("smtp");
									t.connect(siaaMail, siaaPass);
									t.sendMessage(messageToClient,messageToClient.getAllRecipients());
									t.close();
								} else {
									// Enviamos los mensajes.
							          Transport.send(messageToClient);
								}
								//eliminando archivos temporales
								if (archivosAdjuntos != null && archivosAdjuntos.length > 0) {
									for (File archivo : archivosAdjuntos) {
										if (archivo.exists()) {
											archivo.delete();
										}
									}
								}
								log.info("Envio correo correctamente");
							} catch (MessagingException ex) {
								log.error("Error MensajeriaServiceImpl.enviarCorreoElectronico(final CorreoVO correo) no se pudo enviar el correo " + correo.getUUID() + " MessagingException ex : " + ex.getMessage());
							//	log.error(ex);
							} catch (Exception e) {
								log.error("Error MensajeriaServiceImpl.enviarCorreoElectronico(final CorreoVO correo) no se pudo enviar el correo " + correo.getUUID() + " Exception e : " + e.getMessage());
								//log.error(e);
							}
							//Thread.interrupted();
						//}

					//}).start();
				} else {
					//registrar fallo
					log.error("Error MensajeriaServiceImpl.enviarCorreoElectronico(final CorreoVO correo) no tiene contenido");
				}
			} else {
				//no tiene asunto
				log.error("Error MensajeriaServiceImpl.enviarCorreoElectronico(final CorreoVO correo) no tiene asunto");
			}
		} else {
			log.error("Error MensajeriaServiceImpl.enviarCorreoElectronico(final CorreoVO correo) no tiene destinatario");
		}

	}
	
	@Override
	public void enviarMailDataReporte(String pPara,String asunto,String mensaje,String usuario, FileVO fileVO) {
    	try {
    		List<String> destinatario = new ArrayList<String>();
    		//String asunto  = "";
    		String contenido = "";
    		String userName = "";
    		String nombreArchivo = "";
    		if (StringUtils.isNullOrEmpty(pPara)) {
    			return;
    		}
    		String[] paras = pPara.split(";");
    		if (paras != null) {
    			for (String para : paras) {
    				if (!StringUtils.isNullOrEmpty(para)) {
    					if (!destinatario.contains(para)) {
    						destinatario.add(para);
    					}
    				}
				}
    			
    		}
			//asunto = asunto;
			contenido = mensaje;
			userName = usuario;
			nombreArchivo = fileVO.getName();
			
        	if (destinatario.size() > 0) {
	        	List<DetalleCorreoVO> detalleCorreo = new ArrayList<DetalleCorreoVO>();	
	    		for (String correo : destinatario) {
	    			DetalleCorreoVO correoDestino = new DetalleCorreoVO(correo, TipoDetalleCorreoType.DESTINATARIO.getKey());		
	    			detalleCorreo.add(correoDestino);
	    		}		
	    		List<CorreoVO> correos = new ArrayList<CorreoVO>();
	    		CorreoVO correoVO = new CorreoVO(asunto,contenido,detalleCorreo);
	    		//correoVO.setUsuarioDTO(criterioEnvioCorreoVO.getUsuarioDTO());
	    		//correoVO.setConfiguracionReporteFromCorreoVO(IntegracionTron2000CacheUtil.getInstance().getConfiguracionReporteFromCorreoMap().get(criterioEnvioCorreoVO.getCodigoReporte()));
	    		if (fileVO.getName() != null) {
	    			File[] archivosAdjuntos = new File[1];
	    			String rutaArchivo =  ConstanteConfigUtil.generarRuta(ArchivoUtilidades.RUTA_RECURSOS , ArchivoUtilidades.RUTA_REPORTE_GENERADO ,  userName) +  nombreArchivo ;
	    			File objFile = new File(rutaArchivo);
	    			if (objFile.isFile()) {
	    				archivosAdjuntos[0] = objFile;
	    				correoVO.setArchivosAdjuntos(archivosAdjuntos);
	    			}
	    		}
	    		correos.add(correoVO);
	    		enviarCorreo(correos);
        	}
			
		} catch (Exception e) {
			log.error(e);
		}
    	

    }
	
	/**
	 * Obtener mensaje by tipo.
	 *
	 * @param asuntoDetalleCorreoType el asunto detalle correo type
	 * @param codigoSolicitud el codigo solicitud
	 * @return the string
	 */
	@Override
	public String obtenerMensajeByTipo(AsuntoDetalleCorreoType asuntoDetalleCorreoType,String codigoSolicitud,Map<String,Object> dataModel) {
		if (dataModel == null) {
			dataModel = new HashMap<String, Object>();
		}
		dataModel.put("codigoSolicitud",codigoSolicitud);
		StringBuilder resultado = new StringBuilder(ProcesarPlantillaUtil.procesarPlantillaByFreemarkerDo(dataModel, ConstanteConfigUtil.RUTA_GENERAL_TEMPLANTE, asuntoDetalleCorreoType.getTemplate()));
		return resultado.toString();
	}
	
	@Override
	public void envioCorreoByTipo(String correoCorporativo,String correoPersonal,String codigoSolicitud, AsuntoDetalleCorreoType asuntoDetalleCorreoType,Map<String,Object> dataModel) throws Exception {
		List<String> destinatario = new ArrayList<String>();
		if (!StringUtils.isNullOrEmpty(correoCorporativo)) {
			destinatario.add(correoCorporativo);
		}
		if (!StringUtils.isNullOrEmpty(correoPersonal)) {
			destinatario.add(correoPersonal);
		}
		envioCorreoByTipo(destinatario, codigoSolicitud, asuntoDetalleCorreoType,dataModel);
	}
	/**
	 * Envio correo by tipo.
	 *
	 * @param destinatario el destinatario
	 * @param codigoSolicitud el codigo solicitud
	 * @param asuntoDetalleCorreoType el asunto detalle correo type
	 * @throws Exception the exception
	 */
	@Override
	public void envioCorreoByTipo(List<String> destinatario,String codigoSolicitud, AsuntoDetalleCorreoType asuntoDetalleCorreoType,Map<String,Object> dataModel) throws Exception {
		List<DetalleCorreoVO> detalleCorreo = new ArrayList<DetalleCorreoVO>();	
		for (String correo : destinatario) {
			DetalleCorreoVO correoDestino = new DetalleCorreoVO(correo, TipoDetalleCorreoType.DESTINATARIO.getKey());		
			detalleCorreo.add(correoDestino);
		}		
		String asunto = getDescription(null,asuntoDetalleCorreoType.getValue()) + codigoSolicitud;
		List<CorreoVO> correos = new ArrayList<CorreoVO>();
		correos.add(new CorreoVO(asunto,obtenerMensajeByTipo(asuntoDetalleCorreoType,codigoSolicitud,dataModel),detalleCorreo));
		enviarCorreo(correos);
	}
	
	/**
	 * Obtiene description.
	 *
	 * @param locale el locale
	 * @param value el value
	 * @return description
	 */
	public String getDescription(Locale locale,String value) {
		return ResourceUtil.getString(locale, ConstanteTypeUtil.BUNDLE_NAME_TYPE, value);
	}	
}