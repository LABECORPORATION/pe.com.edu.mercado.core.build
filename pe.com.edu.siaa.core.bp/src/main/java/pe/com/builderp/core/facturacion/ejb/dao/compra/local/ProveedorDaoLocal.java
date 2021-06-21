package pe.com.builderp.core.facturacion.ejb.dao.compra.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.compra.ProveedorDTO;
import pe.com.builderp.core.facturacion.model.jpa.compra.Proveedor;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;

/**
 * La Class ProveedorDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:20 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface ProveedorDaoLocal  extends GenericDAOLocal<String,Proveedor> {
	/**
	 * Listar proveedor.
	 *
	 * @param proveedor el proveedorDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Proveedor> listarProveedor(ProveedorDTO proveedor) throws Exception;
	
	/**
	 * contar lista proveedor.
	 *
	 * @param proveedor el proveedor
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarProveedor(ProveedorDTO proveedor);
	/**
	 * Generar id proveedor.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdProveedor() throws Exception;
	
	
	Proveedor findProveedorByNro(String nroDoc) throws Exception;
}