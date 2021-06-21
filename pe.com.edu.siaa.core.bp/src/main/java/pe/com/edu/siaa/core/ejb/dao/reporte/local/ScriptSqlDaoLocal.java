package pe.com.edu.siaa.core.ejb.dao.reporte.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.dto.reporte.ScriptSqlDTO;
import pe.com.edu.siaa.core.model.jpa.reporte.ScriptSql;

/**
 * La Class ScriptSqlDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 11:25:08 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface ScriptSqlDaoLocal  extends GenericDAOLocal<String,ScriptSql> {
	/**
	 * Listar script sql.
	 *
	 * @param scriptSql el script sqlDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ScriptSql> listarScriptSql(ScriptSqlDTO scriptSql) throws Exception;
	
	/**
	 * contar lista script sql.
	 *
	 * @param scriptSql el script sql
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarScriptSql(ScriptSqlDTO scriptSql);
	/**
	 * Generar id scriptSql.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdScriptSql() throws Exception;
}