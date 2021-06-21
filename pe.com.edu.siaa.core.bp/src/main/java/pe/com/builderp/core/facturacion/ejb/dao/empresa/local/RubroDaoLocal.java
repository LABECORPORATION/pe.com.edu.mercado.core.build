package pe.com.builderp.core.facturacion.ejb.dao.empresa.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.empresa.RubroDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.jpa.empresa.Rubro;

/**
 * La Class RubroDaoLocal.
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
public interface RubroDaoLocal  extends GenericDAOLocal<String,Rubro> {
	/**
	 * Listar Rubro.
	 *
	 * @param Rubro el RubroDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Rubro> listarRubro(String idSector) throws Exception;
	 
	/**
	 * Generar id Rubro.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	String generarIdRubro() throws Exception;
	
	boolean eliminarRubroRequerido(String idSector) throws Exception;
}