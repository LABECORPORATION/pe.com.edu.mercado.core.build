package pe.com.builderp.core.facturacion.ejb.dao.compra.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.compra.CuentaBancariaDTO;
import pe.com.builderp.core.facturacion.model.jpa.compra.CuentaBancaria;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;

/**
 * La Class CuentaBancariaDaoLocal.
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
public interface CuentaBancariaDaoLocal  extends GenericDAOLocal<String,CuentaBancaria> {
	/**
	 * Listar cuenta bancaria.
	 *
	 * @param cuentaBancaria el cuenta bancariaDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<CuentaBancaria> listarCuentaBancaria(CuentaBancariaDTO cuentaBancaria) throws Exception;
	
	/**
	 * contar lista cuenta bancaria.
	 *
	 * @param cuentaBancaria el cuenta bancaria
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarCuentaBancaria(CuentaBancariaDTO cuentaBancaria);
	/**
	 * Generar id cuentaBancaria.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdCuentaBancaria() throws Exception;
}