package pe.com.builderp.core.facturacion.ejb.dao.venta.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.venta.ModeloDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.Modelo;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;

/**
 * La Class ModeloDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:23 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface ModeloDaoLocal  extends GenericDAOLocal<Long,Modelo> {
	/**
	 * Listar modelo.
	 *
	 * @param modelo el modeloDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Modelo> listarModelo(ModeloDTO modelo) throws Exception;
	
	/**
	 * contar lista modelo.
	 *
	 * @param modelo el modelo
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarModelo(ModeloDTO modelo);
	/**
	 * Generar id modelo.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	Long generarIdModelo() throws Exception;
}