package pe.com.builderp.core.facturacion.ejb.dao.empresa.local;


import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.empresa.AsistenciaDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.jpa.empresa.Asistencia; 

/**
 * La Class AsistenciaDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 11:25:04 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface AsistenciaDaoLocal  extends GenericDAOLocal<String,Asistencia> {
	
	String generarCodigoAsistencia(Asistencia asistencia) throws Exception;
	
	List<Asistencia> listarAsistencia(boolean isConculta, String idActividad,String idAlumno,String estado, Date fechaHorario) throws Exception;
	/**
	 * Listar asistencia.
	 *
	 * @param asistencia el asistenciaDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Asistencia> listarAsistencia(AsistenciaDTO asistencia) throws Exception;
	
	/**
	 * contar lista asistencia.
	 *
	 * @param asistencia el asistencia
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarAsistencia(AsistenciaDTO asistencia);
	/**
	 * Generar id asistencia.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdAsistencia() throws Exception;
	
	//agregado
	List<String>  obtenerListadofechaBydetCraglectiva(String idActividad,String idAnhio) throws Exception;
	
	List<String>  obtenerListadofecha(String idActividad,String idAnhio,String fecha)throws Exception;
	
	List<String>  obtenerNombres(String idActividad,String idAnhio)throws Exception;
	
	List<String>  obtenerCodigoAlumno(String idActividad,String idAnhio)throws Exception;
	
	List<Date>  obtenerListadofechaByAsistencia(String idActividad)throws Exception;
}