package pe.com.builderp.core.facturacion.ejb.dao.venta.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.jpa.venta.GuiaRemision;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.builderp.core.facturacion.model.dto.venta.GuiaRemisionDTO;


/**
 * La Class GuiaRemisionDaoLocal.
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
public interface GuiaRemisionDaoLocal  extends GenericDAOLocal<String,GuiaRemision> {
	/**
	 * Listar guia remision.
	 *
	 * @param guiaRemision el guia remisionDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<GuiaRemision> listarGuiaRemision(GuiaRemisionDTO guiaRemision) throws Exception;
	
	/**
	 * contar lista guia remision.
	 *
	 * @param guiaRemision el guia remision
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarGuiaRemision(GuiaRemisionDTO guiaRemision);
	/**
	 * Generar id guiaRemision.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdGuiaRemision() throws Exception;
}