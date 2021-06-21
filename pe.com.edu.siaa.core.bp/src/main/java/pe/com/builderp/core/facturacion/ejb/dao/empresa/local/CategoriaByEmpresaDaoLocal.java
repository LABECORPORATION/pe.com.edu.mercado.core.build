package pe.com.builderp.core.facturacion.ejb.dao.empresa.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.empresa.CategoriaByEmpresaDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.jpa.empresa.CategoriaByEmpresa;

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
public interface CategoriaByEmpresaDaoLocal  extends GenericDAOLocal<String,CategoriaByEmpresa> {
	/**
	 * Listar categoria.
	 *
	 * @param categoria el categoriaDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<CategoriaByEmpresa> listarCategoria(CategoriaByEmpresaDTO categoria) throws Exception;
	
	/**
	 * contar lista categoria.
	 *
	 * @param categoria el categoria
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarCategoria(CategoriaByEmpresaDTO categoria);
	/**
	 * Generar id categoria.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	String generarIdCategoria() throws Exception;
	
	CategoriaByEmpresa obtenerCategoriaByParameter(CategoriaByEmpresaDTO categoriaByEmpresa) throws Exception;
}