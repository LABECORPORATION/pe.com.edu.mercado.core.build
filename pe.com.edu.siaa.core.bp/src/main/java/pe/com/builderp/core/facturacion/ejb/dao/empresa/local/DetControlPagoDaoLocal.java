package pe.com.builderp.core.facturacion.ejb.dao.empresa.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.empresa.DetControlPagoDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.jpa.empresa.DetControlPago; 

/**
 * La Class DetControlPagoDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 11:25:17 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface DetControlPagoDaoLocal  extends GenericDAOLocal<String,DetControlPago> {
	
	/**
	 * Listar detControlPago.
	 *
	 * @param detControlPago el detControlPago
	 * @return the list
	 * @throws Exception the exception
	 */
	List<DetControlPago> listarDetControlPago(String idControPago) throws Exception;
	
	/**
	 * Generar id detControlPago.
	 *
	 * @param detControlPago el cuota concepto
	 * @return the long
	 * @throws Exception the exception
	 */
	String generarIdDetControlPago(String idControlPago) throws Exception;
	/**
	 * Listar det control pago.
	 *
	 * @param detControlPago el det control pagoDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<DetControlPago> listarDetControlPago(DetControlPagoDTO detControlPago) throws Exception;
	
	/**
	 * contar lista det control pago.
	 *
	 * @param detControlPago el det control pago
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarDetControlPago(DetControlPagoDTO detControlPago);
	/**
	 * Generar id detControlPago.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdDetControlPago() throws Exception;
	
	//agregado
	List<DetControlPago> listarDetControlPagoDelectid(String idControlpago) throws Exception;
	
	void deleteDetControlpago(List<String> idetcontrolpago ) throws Exception;
	
	/**
	 * Listar DetControlPagoAlumno.
	 *
	 * @param DetControlPagoAlumno el Movimiento
	 * @return the list
	 * @throws Exception the exception
	 */
	List<DetControlPago> listarDetControlPagoAlumno(String idAlumno, String idAnhoSemestre) throws Exception;
	
	
	DetControlPago findDetcontrolpagoBy(String idControlPago) throws Exception; 
}