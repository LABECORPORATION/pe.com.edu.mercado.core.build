package pe.com.edu.siaa.core.ejb.service.contabilidad.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.contabilidad.model.vo.RegistroAsientoFiltroVO;
import pe.com.edu.siaa.core.model.dto.contabilidad.AsientoContableDTO;
import pe.com.edu.siaa.core.model.dto.contabilidad.AsientoContableDetDTO;
import pe.com.edu.siaa.core.model.dto.contabilidad.ConfigDependenciaItemDTO;
import pe.com.edu.siaa.core.model.dto.contabilidad.ConfiguracionCuentaDTO;
import pe.com.edu.siaa.core.model.dto.contabilidad.PlanContableDTO;
import pe.com.edu.siaa.core.model.type.AccionType;

/**
 * La Class ContabilidadServiceLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Fri Sep 08 16:33:12 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface ContabilidadServiceLocal{
	
	String generarReporteAsientoContableDiario(String userName,RegistroAsientoFiltroVO registroAsientoFiltro) throws Exception;
	
	String generarReporteAsientoContableLibroMayor(String userName,RegistroAsientoFiltroVO registroAsientoFiltro) throws Exception;
	/*
	Map<String,Integer> obtenerCantidadEmpresaAsignadaMap(List<String> listaIdUsuario) throws Exception;
	*/
	/**
	 * Listar asiento contable det.
	 *
	 * @param asientoContableDet el asiento contable det
	 * @return the list
	 * @throws Exception the exception
	 */
	List<AsientoContableDetDTO> listarAsientoContableDet(AsientoContableDetDTO asientoContableDet) throws Exception;
	
	/**
	 * contar lista asiento contable det.
	 *
	 * @param asientoContableDet el asiento contable det
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarAsientoContableDet(AsientoContableDetDTO asientoContableDet);
	
	/**
	 * Controlador accion asiento contable.
	 *
	 * @param asientoContable el asiento contable
	 * @param accionType el accion type
	 * @return the asiento contable
	 * @throws Exception the exception
	 */
	AsientoContableDTO registrarAsientoContable(AsientoContableDTO asientoContable,String userName, AccionType accionType) throws Exception; 
	
	/**
	 * Listar asiento contable.
	 *
	 * @param asientoContable el asiento contable
	 * @return the list
	 * @throws Exception the exception
	 */
	List<AsientoContableDTO> listarAsientoContable(RegistroAsientoFiltroVO registroVentaFiltro) throws Exception;
	
	/**
	 * contar lista asiento contable.
	 *
	 * @param asientoContable el asiento contable
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarAsientoContable(RegistroAsientoFiltroVO registroVentaFiltro);
	
	
	
	//List<Long> obtenerIdByUser(AsociarEmpresaDTO asociarEmpresa) throws Exception;
	
	
	/**
	 * Controlador accion plan contable.
	 *
	 * @param planContable el plan contable
	 * @param accionType el accion type
	 * @return the plan contable
	 * @throws Exception the exception
	 */
	PlanContableDTO controladorAccionPlanContable(PlanContableDTO planContable,AccionType accionType) throws Exception; 
	
	/**
	 * Listar plan contable.
	 *
	 * @param planContable el plan contable
	 * @return the list
	 * @throws Exception the exception
	 */
	List<PlanContableDTO> listarPlanContable(PlanContableDTO planContable) throws Exception;
	
	/**
	 * contar lista plan contable.
	 *
	 * @param planContable el plan contable
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarPlanContable(PlanContableDTO planContable);
	
	
	/**
	 * Controlador accion config dependencia item.
	 *
	 * @param configDependenciaItem el config dependencia item
	 * @param accionType el accion type
	 * @return the config dependencia item
	 * @throws Exception the exception
	 */
	ConfigDependenciaItemDTO controladorAccionConfigDependenciaItem(ConfigDependenciaItemDTO configDependenciaItem,AccionType accionType) throws Exception; 
	
	/**
	 * Listar config dependencia item.
	 *
	 * @param configDependenciaItem el config dependencia item
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ConfigDependenciaItemDTO> listarConfigDependenciaItem(ConfigDependenciaItemDTO configDependenciaItem) throws Exception;
	
	/**
	 * contar lista config dependencia item.
	 *
	 * @param configDependenciaItem el config dependencia item
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarConfigDependenciaItem(ConfigDependenciaItemDTO configDependenciaItem);
	
	/**
	 * Controlador accion configuracion cuenta.
	 *
	 * @param configuracionCuenta el configuracion cuenta
	 * @param accionType el accion type
	 * @return the configuracion cuenta
	 * @throws Exception the exception
	 */
	ConfiguracionCuentaDTO controladorAccionConfiguracionCuenta(ConfiguracionCuentaDTO configuracionCuenta,AccionType accionType) throws Exception; 
	
	/**
	 * Listar configuracion cuenta.
	 *
	 * @param configuracionCuenta el configuracion cuenta
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ConfiguracionCuentaDTO> listarConfiguracionCuenta(ConfiguracionCuentaDTO configuracionCuenta) throws Exception;
	
	/**
	 * contar lista configuracion cuenta.
	 *
	 * @param configuracionCuenta el configuracion cuenta
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarConfiguracionCuenta(ConfiguracionCuentaDTO configuracionCuenta);
	
}