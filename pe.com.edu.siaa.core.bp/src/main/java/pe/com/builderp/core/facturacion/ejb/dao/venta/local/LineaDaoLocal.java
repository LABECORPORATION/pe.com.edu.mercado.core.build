package pe.com.builderp.core.facturacion.ejb.dao.venta.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.venta.LineaDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.Linea;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;

/**
 * La Class LineaDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:23 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface LineaDaoLocal  extends GenericDAOLocal<Long,Linea> {
	/**
	 * Listar linea.
	 *
	 * @param linea el lineaDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Linea> listarLinea(LineaDTO linea) throws Exception;
	
	/**
	 * contar lista linea.
	 *
	 * @param linea el linea
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarLinea(LineaDTO linea);
	/**
	 * Generar id linea.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	Long generarIdLinea() throws Exception;
}