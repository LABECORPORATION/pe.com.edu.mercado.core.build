package pe.com.builderp.core.facturacion.ejb.dao.empresa.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.empresa.RecordatorioDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.jpa.empresa.Recordatorio;

/**
 * La Class RecordatorioDaoLocal.
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
public interface RecordatorioDaoLocal  extends GenericDAOLocal<String,Recordatorio> {
	/**
	 * Listar Recordatorio.
	 *
	 * @param Recordatorio el RecordatorioDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Recordatorio> listarRecordatorio(RecordatorioDTO recordatorio) throws Exception;
	
	/**
	 * contar lista Recordatorio.
	 *
	 * @param Recordatorio el Recordatorio
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarRecordatorio(RecordatorioDTO recordatorio);
	/**
	 * Generar id Recordatorio.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	String generarIdRecordatorio() throws Exception;
}