package pe.com.builderp.core.facturacion.ejb.dao.venta.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.venta.ProformaDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.VentaDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.Proforma;
import pe.com.builderp.core.facturacion.model.jpa.venta.Venta;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;

/**
 * La Class ProformaDaoLocal.
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
public interface ProformaDaoLocal  extends GenericDAOLocal<String,Proforma> {
	/**
	 * Listar proforma.
	 *
	 * @param proforma el proformaDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Proforma> listarProforma(ProformaDTO proforma) throws Exception;
	
	/**
	 * contar lista proforma.
	 *
	 * @param proforma el proforma
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarProforma(ProformaDTO proforma);
	/**
	 * Generar id proforma.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdProforma() throws Exception;
	
	void updateEstadoProforma() throws Exception;
	
	 List<Proforma> listarProformaReporte(ProformaDTO proforma) throws Exception;
	
	
	
	
}