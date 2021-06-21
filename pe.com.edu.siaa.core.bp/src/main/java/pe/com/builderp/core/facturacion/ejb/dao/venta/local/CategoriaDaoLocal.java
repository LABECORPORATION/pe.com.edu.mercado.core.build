package pe.com.builderp.core.facturacion.ejb.dao.venta.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.venta.CategoriaDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.Categoria;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;

/**
 * La Class CategoriaDaoLocal.
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
public interface CategoriaDaoLocal  extends GenericDAOLocal<Long,Categoria> {
	/**
	 * Listar categoria.
	 *
	 * @param categoria el categoriaDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Categoria> listarCategoria(CategoriaDTO categoria) throws Exception;
	
	/**
	 * contar lista categoria.
	 *
	 * @param categoria el categoria
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarCategoria(CategoriaDTO categoria);
	/**
	 * Generar id categoria.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	Long generarIdCategoria() throws Exception;
}