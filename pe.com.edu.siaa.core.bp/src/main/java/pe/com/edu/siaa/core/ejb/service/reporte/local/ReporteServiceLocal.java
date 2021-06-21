package pe.com.edu.siaa.core.ejb.service.reporte.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.edu.siaa.core.model.dto.reporte.ParametrosScriptSqlDTO;
import pe.com.edu.siaa.core.model.dto.reporte.ScriptSqlDTO;
import pe.com.edu.siaa.core.model.type.AccionType;

/**
 * La Class ReporteServiceLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 13:43:23 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface ReporteServiceLocal {
	
		
	
	String generarReporteScript(ScriptSqlDTO scriptSql,String userName) throws Exception; 
	
	/**
	 * Controlador accion script sql.
	 *
	 * @param scriptSql el script sql
	 * @param accionType el accion type
	 * @return the script sql
	 * @throws Exception the exception
	 */
	ScriptSqlDTO controladorAccionScriptSql(ScriptSqlDTO scriptSql,AccionType accionType) throws Exception; 
	
	/**
	 * Listar script sql.
	 *
	 * @param scriptSql el script sql
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ScriptSqlDTO> listarScriptSql(ScriptSqlDTO scriptSql) throws Exception;
	
	/**
	 * contar lista script sql.
	 *
	 * @param scriptSql el script sql
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarScriptSql(ScriptSqlDTO scriptSql);
	
	/**
	 * Controlador accion parametros script sql.
	 *
	 * @param parametrosScriptSql el parametros script sql
	 * @param accionType el accion type
	 * @return the parametros script sql
	 * @throws Exception the exception
	 */
	ParametrosScriptSqlDTO controladorAccionParametrosScriptSql(ParametrosScriptSqlDTO parametrosScriptSql,AccionType accionType) throws Exception; 
	
	/**
	 * Listar parametros script sql.
	 *
	 * @param parametrosScriptSql el parametros script sql
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ParametrosScriptSqlDTO> listarParametrosScriptSql(ParametrosScriptSqlDTO parametrosScriptSql) throws Exception;
	
	/**
	 * contar lista parametros script sql.
	 *
	 * @param parametrosScriptSql el parametros script sql
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarParametrosScriptSql(ParametrosScriptSqlDTO parametrosScriptSql);
	
}