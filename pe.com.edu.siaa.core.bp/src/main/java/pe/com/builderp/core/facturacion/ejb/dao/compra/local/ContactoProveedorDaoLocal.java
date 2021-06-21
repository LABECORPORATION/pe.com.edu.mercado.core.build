package pe.com.builderp.core.facturacion.ejb.dao.compra.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.compra.ContactoProveedorDTO;
import pe.com.builderp.core.facturacion.model.jpa.compra.ContactoProveedor;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;

/**
 * La Class ContactoProveedorDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:17 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface ContactoProveedorDaoLocal  extends GenericDAOLocal<String,ContactoProveedor> {
	/**
	 * Listar contacto proveedor.
	 *
	 * @param contactoProveedor el contacto proveedorDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ContactoProveedor> listarContactoProveedor(ContactoProveedorDTO contactoProveedor) throws Exception;
	
	/**
	 * contar lista contacto proveedor.
	 *
	 * @param contactoProveedor el contacto proveedor
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarContactoProveedor(ContactoProveedorDTO contactoProveedor);
	/**
	 * Generar id contactoProveedor.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdContactoProveedor() throws Exception;
}