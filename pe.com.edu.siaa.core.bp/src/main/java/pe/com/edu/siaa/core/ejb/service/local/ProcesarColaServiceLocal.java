package pe.com.edu.siaa.core.ejb.service.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.edu.siaa.core.model.vo.RegistroMensajeriaOutputVO;


/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Interface ProcesarColaServiceLocal.
 *
 * @author ndavilal
 * @version 1.0 , 07/04/2015
 * @since SIAA-CORE 2.1
 */
@Local
public interface ProcesarColaServiceLocal {
	
	/**
	 * Enviar cola.
	 *
	 * @param listaObjectos el lista objectos
	 * @return the registro mensajeria output vo
	 */
	RegistroMensajeriaOutputVO enviarCola(List<Object> listaObjectos);
	
	
}