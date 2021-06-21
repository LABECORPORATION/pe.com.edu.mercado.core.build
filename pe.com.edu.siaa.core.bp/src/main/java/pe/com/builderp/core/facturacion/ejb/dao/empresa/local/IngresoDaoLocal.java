package pe.com.builderp.core.facturacion.ejb.dao.empresa.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.empresa.IngresoDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.jpa.empresa.Ingreso;

/**
 * La Class IngresoDaoLocal.
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
public interface IngresoDaoLocal  extends GenericDAOLocal<String,Ingreso> {
	/**
	 * Listar Ingreso.
	 *
	 * @param Ingreso el IngresoDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Ingreso> listarIngreso(IngresoDTO ingreso) throws Exception;
	
	/**
	 * contar lista Ingreso.
	 *
	 * @param Ingreso el Ingreso
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarIngreso(IngresoDTO ingreso);
	/**
	 * Generar id Ingreso.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	String generarIdIngreso() throws Exception;
}