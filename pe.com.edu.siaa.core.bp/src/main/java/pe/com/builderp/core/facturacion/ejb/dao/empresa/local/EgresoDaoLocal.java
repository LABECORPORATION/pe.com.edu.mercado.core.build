package pe.com.builderp.core.facturacion.ejb.dao.empresa.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.empresa.EgresoDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.jpa.empresa.Egreso;

/**
 * La Class EgresoDaoLocal.
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
public interface EgresoDaoLocal  extends GenericDAOLocal<String,Egreso> {
	/**
	 * Listar Egreso.
	 *
	 * @param Egreso el EgresoDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Egreso> listarEgreso(EgresoDTO egreso) throws Exception;
	
	/**
	 * contar lista Egreso.
	 *
	 * @param Egreso el Egreso
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarEgreso(EgresoDTO egreso);
	/**
	 * Generar id Egreso.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	String generarIdEgreso() throws Exception;
}