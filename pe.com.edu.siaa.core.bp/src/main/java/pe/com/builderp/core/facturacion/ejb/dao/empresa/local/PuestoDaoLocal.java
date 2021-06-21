package pe.com.builderp.core.facturacion.ejb.dao.empresa.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.empresa.PuestoDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.jpa.empresa.Puesto;

/**
 * La Class PuestoDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:21 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface PuestoDaoLocal  extends GenericDAOLocal<String,Puesto> {
	/**
	 * Listar Puesto.
	 *
	 * @param Puesto el PuestoDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Puesto> listarPuesto(PuestoDTO Puesto) throws Exception;
	
	/**
	 * contar lista Puesto.
	 *
	 * @param Puesto el Puesto
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarPuesto(PuestoDTO Puesto);
	
	Puesto findByPuesto(String idPuesto) throws Exception;
	/**
	 * Generar id Puesto.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	String generarIdPuesto() throws Exception;
}