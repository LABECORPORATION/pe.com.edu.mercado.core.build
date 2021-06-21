package pe.com.builderp.core.facturacion.ejb.dao.venta.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.jpa.venta.Vehiculo;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.builderp.core.facturacion.model.dto.venta.VehiculoDTO;


/**
 * La Class VehiculoDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu May 02 10:20:30 COT 2019
 * @since SIAA-CORE 2.1
 */
@Local
public interface VehiculoDaoLocal  extends GenericDAOLocal<String,Vehiculo> {
	/**
	 * Listar vehiculo.
	 *
	 * @param vehiculo el vehiculoDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Vehiculo> listarVehiculo(VehiculoDTO vehiculo) throws Exception;
	
	/**
	 * contar lista vehiculo.
	 *
	 * @param vehiculo el vehiculo
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarVehiculo(VehiculoDTO vehiculo);
	/**
	 * Generar id vehiculo.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdVehiculo() throws Exception;
}