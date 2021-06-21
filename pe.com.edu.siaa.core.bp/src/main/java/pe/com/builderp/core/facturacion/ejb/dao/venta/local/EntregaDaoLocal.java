package pe.com.builderp.core.facturacion.ejb.dao.venta.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.venta.EntregaDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.Entrega;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;

/**
 * La Class EntregaDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:24 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface EntregaDaoLocal  extends GenericDAOLocal<String,Entrega> {
	/**
	 * Listar entrega.
	 *
	 * @param entrega el entregaDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Entrega> listarEntrega(EntregaDTO entrega) throws Exception;
	
	/**
	 * contar lista entrega.
	 *
	 * @param entrega el entrega
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarEntrega(EntregaDTO entrega);
	/**
	 * Generar id entrega.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdEntrega() throws Exception;
}