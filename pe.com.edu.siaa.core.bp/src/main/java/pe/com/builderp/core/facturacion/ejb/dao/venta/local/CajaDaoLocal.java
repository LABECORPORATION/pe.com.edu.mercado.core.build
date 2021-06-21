package pe.com.builderp.core.facturacion.ejb.dao.venta.local;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.venta.CajaDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.Caja; 
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
public interface CajaDaoLocal  extends GenericDAOLocal<String,Caja> {
	/**
	 * Listar proforma.
	 *
	 * @param proforma el proformaDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Caja> listarCaja(CajaDTO Caja) throws Exception;
	
	/**
	 * contar lista Caja.
	 *
	 * @param Caja el Caja
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarCaja(CajaDTO Caja);
	/**
	 * Generar id Caja.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdCaja() throws Exception;
	
	
	void updateCajaCierre(CajaDTO caja) throws Exception;
	
	Caja findByCaja(CajaDTO cajaTemp) throws Exception;
	
	 Caja findByCajaByIdUsuario(String idUsuario) throws Exception;
}