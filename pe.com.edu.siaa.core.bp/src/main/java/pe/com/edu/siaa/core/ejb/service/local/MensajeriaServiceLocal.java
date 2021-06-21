package pe.com.edu.siaa.core.ejb.service.local;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import pe.com.edu.siaa.core.model.type.AsuntoDetalleCorreoType;
import pe.com.edu.siaa.core.model.vo.CorreoVO;
import pe.com.edu.siaa.core.model.vo.FileVO;
import pe.com.edu.siaa.core.model.vo.RegistroMensajeriaOutputVO;

/**
 * La Class MensajeriaServiceLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal-
 * OSCE. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Tue Apr 29 19:20:13 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface MensajeriaServiceLocal {
	
	/**
	 * Enviar correo.
	 *
	 * @param correos el correos
	 * @return the registro mensajeria output vo
	 */
	RegistroMensajeriaOutputVO enviarCorreo(List<CorreoVO> correos);
	
	
	/**
	 * Enviar correo electronico.
	 *
	 * @param correo the correo
	 */
	void enviarCorreoElectronico(final CorreoVO correo);
	
	/**
	 * Enviar mail data reporte.
	 *
	 * @param criterioEnvioCorreoVO el criterio envio correo vo
	 * @param usuario el usuario
	 * @param fileVO el file vo
	 */
	void enviarMailDataReporte(String pPara,String asunto,String mensaje,String usuario, FileVO fileVO) ;
	
	 String obtenerMensajeByTipo(AsuntoDetalleCorreoType asuntoDetalleCorreoType,String codigoSolicitud,Map<String,Object> dataModel);
	 
	 void envioCorreoByTipo(String correoCorporativo,String correoPersonal,String codigoSolicitud, AsuntoDetalleCorreoType asuntoDetalleCorreoType,Map<String,Object> dataModel) throws Exception;
	 void envioCorreoByTipo(List<String> destinatario,String codigoSolicitud, AsuntoDetalleCorreoType asuntoDetalleCorreoType,Map<String,Object> dataModel) throws Exception;
}