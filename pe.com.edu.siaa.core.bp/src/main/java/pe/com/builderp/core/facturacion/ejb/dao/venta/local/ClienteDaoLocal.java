package pe.com.builderp.core.facturacion.ejb.dao.venta.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.venta.ClienteDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.Cliente;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;

/**
 * La Class ClienteDaoLocal.
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
public interface ClienteDaoLocal  extends GenericDAOLocal<String,Cliente> {
	/**
	 * Listar cliente.
	 *
	 * @param cliente el clienteDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Cliente> listarCliente(ClienteDTO cliente) throws Exception;
	
	/**
	 * contar lista cliente.
	 *
	 * @param cliente el cliente
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarCliente(ClienteDTO cliente);
	/**
	 * Generar id cliente.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdCliente() throws Exception;
	
	Cliente findAlumnoByCliente(String idCliente) throws Exception;	
	
	Cliente findAlumnoByClienteNro(String nroDoc) throws Exception;
}