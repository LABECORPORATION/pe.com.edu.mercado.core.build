package pe.com.builderp.core.facturacion.ejb.dao.empresa.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.empresa.SectorDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.jpa.empresa.Sector;

/**
 * La Class SectorDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:21 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface SectorDaoLocal  extends GenericDAOLocal<String,Sector> {
	/**
	 * Listar Sector.
	 *
	 * @param Sector el SectorDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Sector> listarSector(SectorDTO Sector) throws Exception;
	/**
	 * contar lista Sector.
	 *
	 * @param Sector el Sector
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarSector(SectorDTO Sector);
	/**
	 * Generar id Sector.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	String generarIdSector() throws Exception;
	
	Sector findSectorByID(String idSector) throws Exception;
}