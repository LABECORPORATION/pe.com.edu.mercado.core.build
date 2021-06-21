package pe.com.builderp.core.facturacion.ejb.dao.empresa.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.empresa.AsociadoDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.jpa.empresa.Asociado;

/**
 * La Class AsociadoDaoLocal.
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
public interface AsociadoDaoLocal  extends GenericDAOLocal<String,Asociado> {
	/**
	 * Listar Asociado.
	 *
	 * @param Asociado el AsociadoDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Asociado> listarAsociado(AsociadoDTO asociado) throws Exception;
	
	/**
	 * contar lista Asociado.
	 *
	 * @param Asociado el Asociado
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarAsociado(AsociadoDTO asociado);
	/**
	 * Generar id Asociado.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	String generarIdAsociado() throws Exception;
	
	Asociado findAsociadoByNro(String nroDoc) throws Exception;
}