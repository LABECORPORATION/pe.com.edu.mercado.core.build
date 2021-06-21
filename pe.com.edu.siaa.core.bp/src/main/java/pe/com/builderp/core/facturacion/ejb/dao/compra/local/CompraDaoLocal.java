package pe.com.builderp.core.facturacion.ejb.dao.compra.local;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.compra.CompraDTO;
import pe.com.builderp.core.facturacion.model.jpa.compra.Compra;
import pe.com.builderp.core.facturacion.model.vo.compra.RegistroCompraVO;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;

/**
 * La Class CompraDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:16 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface CompraDaoLocal  extends GenericDAOLocal<String,Compra> {
	
    List<RegistroCompraVO> listarCompraReporte(Map<String,Object> parametroFiltroMap) throws Exception;
	
	int contarListarCompraReporte(Map<String,Object> parametroFiltroMap);
	
	/**
	 * Listar compra.
	 *
	 * @param compra el compraDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Compra> listarCompra(CompraDTO compra) throws Exception;
	
	/**
	 * contar lista compra.
	 *
	 * @param compra el compra
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarCompra(CompraDTO compra);
	/**
	 * Generar id compra.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdCompra() throws Exception;
	
	List<Compra> listarCompraTemp(String idOrdenCompra);
}
