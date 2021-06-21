package pe.com.builderp.core.facturacion.ejb.dao.venta.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.venta.TipoDocSunatEntidadDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.TipoDocSunatEntidad;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;

/**
 * La Class TipoDocSunatEntidadDaoLocal.
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
public interface TipoDocSunatEntidadDaoLocal  extends GenericDAOLocal<String,TipoDocSunatEntidad> {

	String actualizarTipoDocSunat(Long idTipoDocSunat,String idEntidad, String nroDoc,String serie) throws Exception;
	//add serie para actualizar
		
	TipoDocSunatEntidad obtenberTipoDocSunatEntidad(Long idTipoDocSunat,String idEntidad,String serie) throws Exception;
		
	
	/**
	 * Listar tipo doc sunat entidad.
	 *
	 * @param tipoDocSunatEntidad el tipo doc sunat entidadDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<TipoDocSunatEntidad> listarTipoDocSunatEntidad(TipoDocSunatEntidadDTO tipoDocSunatEntidad) throws Exception;
	
	/**
	 * contar lista tipo doc sunat entidad.
	 *
	 * @param tipoDocSunatEntidad el tipo doc sunat entidad
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarTipoDocSunatEntidad(TipoDocSunatEntidadDTO tipoDocSunatEntidad);
	/**
	 * Generar id tipoDocSunatEntidad.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	String generarIdTipoDocSunatEntidad() throws Exception;
}