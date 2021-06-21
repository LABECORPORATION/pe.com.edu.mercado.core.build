package pe.com.edu.siaa.core.ejb.dao.common.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.dto.common.ListaValoresDTO;
import pe.com.edu.siaa.core.model.jpa.common.ListaValores;

/**
 * La Class ListaValoresDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 11:25:19 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface ListaValoresDaoLocal  extends GenericDAOLocal<Long,ListaValores> {
	/**
	 * Listar lista valores.
	 *
	 * @param listaValores el lista valoresDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ListaValores> listarListaValores(ListaValoresDTO listaValores) throws Exception;
	
	/**
	 * contar lista lista valores.
	 *
	 * @param listaValores el lista valores
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarListaValores(ListaValoresDTO listaValores);
	/**
	 * Generar id listaValores.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	Long generarIdListaValores() throws Exception;
}