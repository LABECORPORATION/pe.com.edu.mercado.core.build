package pe.com.builderp.core.facturacion.ejb.dao.venta.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.venta.MarcaDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.Marca;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;

/**
 * La Class MarcaDaoLocal.
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
public interface MarcaDaoLocal  extends GenericDAOLocal<Long,Marca> {
	/**
	 * Listar marca.
	 *
	 * @param marca el marcaDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Marca> listarMarca(MarcaDTO marca) throws Exception;
	
	/**
	 * contar lista marca.
	 *
	 * @param marca el marca
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarMarca(MarcaDTO marca);
	/**
	 * Generar id marca.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	Long generarIdMarca() throws Exception;
}