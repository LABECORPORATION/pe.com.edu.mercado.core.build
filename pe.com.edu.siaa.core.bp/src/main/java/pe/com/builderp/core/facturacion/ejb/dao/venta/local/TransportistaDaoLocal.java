package pe.com.builderp.core.facturacion.ejb.dao.venta.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.jpa.venta.Transportista;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.builderp.core.facturacion.model.dto.venta.TransportistaDTO;

/**
 * La Class TransportistaDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu May 02 10:20:31 COT 2019
 * @since SIAA-CORE 2.1
 */
@Local
public interface TransportistaDaoLocal  extends GenericDAOLocal<String,Transportista> {
	/**
	 * Listar transportista.
	 *
	 * @param transportista el transportistaDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Transportista> listarTransportista(TransportistaDTO transportista) throws Exception;
	
	/**
	 * contar lista transportista.
	 *
	 * @param transportista el transportista
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarTransportista(TransportistaDTO transportista);
	/**
	 * Generar id transportista.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdTransportista() throws Exception;
}