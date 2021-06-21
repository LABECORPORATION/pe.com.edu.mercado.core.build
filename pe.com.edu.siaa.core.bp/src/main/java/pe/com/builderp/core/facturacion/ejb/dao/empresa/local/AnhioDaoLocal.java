package pe.com.builderp.core.facturacion.ejb.dao.empresa.local;

import java.util.List;

import pe.com.builderp.core.facturacion.model.dto.empresa.AnhioDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.estate.EstadoAnhoSemestreState;
import pe.com.edu.siaa.core.model.jpa.empresa.Anhio;
 

/**
 * La Class AnhioDaoLocal.
 * <ul>
 * <li>Copyright 2020 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Wed Oct 21 20:17:19 COT 2020
 * @since BUILDERP-CORE 2.1
 */
public interface AnhioDaoLocal  extends GenericDAOLocal<Long,Anhio> {
	/**
	 * Listar anhio.
	 *
	 * @param anhio el anhioDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Anhio> listarAnhio(AnhioDTO anhio);
	
	/**
	 * contar lista anhio.
	 *
	 * @param anhio el anhio
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarAnhio(AnhioDTO anhio);
	/**
	 * Generar id anhio.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	Long generarIdAnhio();
	
	Anhio obtenerAnioByEstado(EstadoAnhoSemestreState estadoAnhioState) throws Exception;
}