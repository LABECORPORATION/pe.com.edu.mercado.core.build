package pe.com.builderp.core.facturacion.ejb.service.empresa.local;
 
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.empresa.ActividadDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.AnhioDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.AsistenciaDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.AsociadoDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.AsociadoFamiliaDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.CategoriaByEmpresaDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.ConceptoPagoDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.ControlPagoDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.CuotaConceptoDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.DetControlPagoDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.DetPlanPagosDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.EgresoDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.IngresoDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.PersonalDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.PlanPagosDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.PuestoDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.RecordatorioDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.RubroDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.SectorDTO;
import pe.com.edu.siaa.core.model.estate.EstadoAnhoSemestreState;
import pe.com.edu.siaa.core.model.jpa.empresa.Asociado;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.model.vo.DetallePlanPagosFiltroVO;
import pe.com.edu.siaa.core.model.vo.PagoPersonalVO;
import pe.com.edu.siaa.core.model.vo.VentaFiltroVO;

/**
 * La Class CompraServiceLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:32:52 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface EmpresaServiceLocal{
 
	CategoriaByEmpresaDTO controladorAccionCategoria(CategoriaByEmpresaDTO Categoria,AccionType accionType) throws Exception; 
	
	/**
	 * Listar Categoria.
	 *
	 * @param Categoria el Categoria
	 * @return the list
	 * @throws Exception the exception
	 */
	List<CategoriaByEmpresaDTO> listarCategoria(CategoriaByEmpresaDTO Categoria) throws Exception;
	
	/**
	 * contar lista Categoria.
	 *
	 * @param Categoria el Categoria
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarCategoria(CategoriaByEmpresaDTO Categoria);
	
	//
	
	EgresoDTO controladorAccionEgreso(EgresoDTO Egreso,AccionType accionType) throws Exception; 
	
	/**
	 * Listar Egreso.
	 *
	 * @param Egreso el Egreso
	 * @return the list
	 * @throws Exception the exception
	 */
	List<EgresoDTO> listarEgreso(EgresoDTO Egreso) throws Exception;
	
	/**
	 * contar lista Egreso.
	 *
	 * @param Egreso el Egreso
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarEgreso(EgresoDTO Egreso);
	
	//
	
	IngresoDTO controladorAccionIngreso(IngresoDTO Ingreso,AccionType accionType) throws Exception; 
	
	/**
	 * Listar Ingreso.
	 *
	 * @param Ingreso el Ingreso
	 * @return the list
	 * @throws Exception the exception
	 */
	List<IngresoDTO> listarIngreso(IngresoDTO Ingreso) throws Exception;
	
	/**
	 * contar lista Ingreso.
	 *
	 * @param Ingreso el Ingreso
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarIngreso(IngresoDTO Ingreso);
	
	
	
	PersonalDTO controladorAccionPersonal(PersonalDTO Personal,AccionType accionType) throws Exception; 
	
	/**
	 * Listar Personal.
	 *
	 * @param Personal el Personal
	 * @return the list
	 * @throws Exception the exception
	 */
	List<PersonalDTO> listarPersonal(PersonalDTO Personal) throws Exception;
	
	/**
	 * contar lista Personal.
	 *
	 * @param Personal el Personal
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarPersonal(PersonalDTO Personal);
	
	
	RecordatorioDTO controladorAccionRecordatorio(RecordatorioDTO Recordatorio,AccionType accionType) throws Exception; 
	
	/**
	 * Listar Recordatorio.
	 *
	 * @param Recordatorio el Recordatorio
	 * @return the list
	 * @throws Exception the exception
	 */
	List<RecordatorioDTO> listarRecordatorio(RecordatorioDTO Recordatorio) throws Exception;
	
	/**
	 * contar lista Recordatorio.
	 *
	 * @param Recordatorio el Recordatorio
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarRecordatorio(RecordatorioDTO Recordatorio);
	
 
	//
	
	List<PagoPersonalVO> listarPersonalTemp(VentaFiltroVO filtro) throws Exception;
	
	CategoriaByEmpresaDTO obtenerCategoriaByParameter(CategoriaByEmpresaDTO categoriaByEmpresaDTO) throws Exception;
	
	void registrarPagoPersonal(List<PagoPersonalVO> listaPagoPersonalVO)  throws Exception;
	
	//
	
    AsociadoDTO controladorAccionAsociado(AsociadoDTO Asociado,AccionType accionType) throws Exception; 
	
	/**
	 * Listar Asociado.
	 *
	 * @param Asociado el Asociado
	 * @return the list
	 * @throws Exception the exception
	 */
	List<AsociadoDTO> listarAsociado(AsociadoDTO Asociado) throws Exception;
	
	/**
	 * contar lista Asociado.
	 *
	 * @param Asociado el Asociado
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarAsociado(AsociadoDTO Asociado);
	
	//
   AsociadoFamiliaDTO controladorAccionAsociadoFamilia(AsociadoFamiliaDTO AsociadoFamilia,Asociado asociado,AccionType accionType) throws Exception; 
	
	/**
	 * Listar AsociadoFamilia.
	 *
	 * @param AsociadoFamilia el AsociadoFamilia
	 * @return the list
	 * @throws Exception the exception
	 */
	List<AsociadoFamiliaDTO> listarAsociadoFamilia(AsociadoFamiliaDTO AsociadoFamilia) throws Exception;
	
	/**
	 * contar lista AsociadoFamilia.
	 *
	 * @param AsociadoFamilia el AsociadoFamilia
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarAsociadoFamilia(AsociadoFamiliaDTO AsociadoFamilia);
	
	//
	
    PuestoDTO controladorAccionPuesto(PuestoDTO Puesto,AccionType accionType) throws Exception; 
	
	/**
	 * Listar Puesto.
	 *
	 * @param Puesto el Puesto
	 * @return the list
	 * @throws Exception the exception
	 */
	List<PuestoDTO> listarPuesto(PuestoDTO Puesto) throws Exception;
	
	/**
	 * contar lista Puesto.
	 *
	 * @param Puesto el Puesto
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarPuesto(PuestoDTO Puesto);
	//
    SectorDTO controladorAccionSector(SectorDTO Sector,AccionType accionType) throws Exception; 
	
	/**
	 * Listar Sector.
	 *
	 * @param Sector el Sector
	 * @return the list
	 * @throws Exception the exception
	 */
	List<SectorDTO> listarSector(SectorDTO Sector) throws Exception;
	
	/**
	 * contar lista Sector.
	 *
	 * @param Sector el Sector
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarSector(SectorDTO Sector);
	
	//
	
	List<RubroDTO> listarRubroRequerido(String idSector) throws Exception;
	
	//
	
	/**
	 * Controlador accion plan pagos.
	 *
	 * @param planPagos el plan pagos
	 * @param accionType el accion type
	 * @return the plan pagos
	 * @throws Exception the exception
	 */
	PlanPagosDTO registrarPlanPagos(PlanPagosDTO planPagos) throws Exception; 
	
	
	/**
	 * Listar plan pagos.
	 *
	 * @param planPagos el plan pagos
	 * @return the list
	 * @throws Exception the exception
	 */
	List<PlanPagosDTO> listarPlanPagos(PlanPagosDTO planPagos) throws Exception;
	
	/**
	 * contar lista plan pagos.
	 *
	 * @param planPagos el plan pagos
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarPlanPagos(PlanPagosDTO planPagos);
	
	List<DetPlanPagosDTO> listarDetPlanPagos(DetPlanPagosDTO detPlanPagos) throws Exception;
	
	DetPlanPagosDTO eliminarDetPlanPagos(DetPlanPagosDTO detPlanPagos); 
	
	int contarListarDetPlanPagos(DetPlanPagosDTO detPlanPagos);
	
	List<DetPlanPagosDTO> listarConceptoPagoAsociado(String idAsociado, boolean flagFaltaMontoResta) throws Exception;
	
	List<DetPlanPagosDTO> listarConceptoPagoAsociadoAPP(String idAsociado, String idCuotaConcpeto) throws Exception;
	
	DetPlanPagosDTO optenerByDetPlanPagos(String idAlumno) throws Exception;
	/**
	 * Controlador accion cuota concepto.
	 *
	 * @param cuotaConcepto el cuota concepto
	 * @param accionType el accion type
	 * @return the cuota concepto
	 * @throws Exception the exception
	 */
	CuotaConceptoDTO controladorAccionCuotaConcepto(CuotaConceptoDTO cuotaConcepto,AccionType accionType) throws Exception; 
	
	/**
	 * Listar cuota concepto.
	 *
	 * @param cuotaConcepto el cuota concepto
	 * @return the list
	 * @throws Exception the exception
	 */
	List<CuotaConceptoDTO> listarCuotaConcepto(CuotaConceptoDTO cuotaConcepto) throws Exception;
	
	/**
	 * contar lista cuota concepto.
	 *
	 * @param cuotaConcepto el cuota concepto
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarCuotaConcepto(CuotaConceptoDTO cuotaConcepto);
	
	/**
	 * Controlador accion control pago.
	 *
	 * @param controlPago el control pago
	 * @param accionType el accion type
	 * @return the control pago
	 * @throws Exception the exception
	 */
	ControlPagoDTO controladorAccionControlPago(ControlPagoDTO controlPago,AccionType accionType) throws Exception; 
	
	ControlPagoDTO registrarPago(ControlPagoDTO controlPago) throws Exception;
	
	/**
	 * Listar control pago.
	 *
	 * @param controlPago el control pago
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ControlPagoDTO> listarControlPago(ControlPagoDTO controlPago) throws Exception;
	
	/**
	 * contar lista control pago.
	 *
	 * @param controlPago el control pago
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarControlPago(ControlPagoDTO controlPago);
	
	String generarReportePago(String idControlPago,String idAsociado,String usuario) throws Exception;
	
	String generarReportePagoBase64(String idControlPago, String idAsociado, String usuario) throws Exception;
	

	/**
	 * Controlador accion det control pago.
	 *
	 * @param detControlPago el det control pago
	 * @param accionType el accion type
	 * @return the det control pago
	 * @throws Exception the exception
	 */
	DetControlPagoDTO controladorAccionDetControlPago(DetControlPagoDTO detControlPago,AccionType accionType) throws Exception; 
	
	/**
	 * Listar det control pago.
	 *
	 * @param detControlPago el det control pago
	 * @return the list
	 * @throws Exception the exception
	 */
	List<DetControlPagoDTO> listarDetControlPago(DetControlPagoDTO detControlPago) throws Exception;
	
	/**
	 * contar lista det control pago.
	 *
	 * @param detControlPago el det control pago
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarDetControlPago(DetControlPagoDTO detControlPago);
	
	/**
	 * Ver detalle pagos realizados.
	 *
	 * @param detControlPago el det control pago
	 * @return the list
	 * @throws Exception the exception
	 */
	List<DetControlPagoDTO> verDetallePagosRealizados(String idControlPago) throws Exception;
	
	List<DetControlPagoDTO> obtenerDetControlPagoAsociado(String idAsociado, String idAnio)throws Exception;
	
	/**
	 * Controlador accion anhio.
	 *
	 * @param anhio el anhio
	 * @param accionType el accion type
	 * @return the anhio
	 * @throws Exception the exception
	 */
	AnhioDTO controladorAccionAnhio(AnhioDTO anhio,AccionType accionType); 
	
	/**
	 * Listar anhio.
	 *
	 * @param anhio el anhio
	 * @return the list
	 * @throws Exception the exception
	 */
	List<AnhioDTO> listarAnhio(AnhioDTO anhio);
	
	/**
	 * contar lista anhio.
	 *
	 * @param anhio el anhio
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarAnhio(AnhioDTO anhio);
	
	AnhioDTO obtenerAnhioByEstado(EstadoAnhoSemestreState estadoAnhioState) throws Exception;
	
	//
	void registrarAsistencia(List<AsistenciaDTO> listaAsistencia,String userName) throws Exception;
	
	/**
	 * Controlador accion asistencia.
	 *
	 * @param asistencia el asistencia
	 * @param accionType el accion type
	 * @return the asistencia
	 * @throws Exception the exception
	 */
	AsistenciaDTO controladorAccionAsistencia(AsistenciaDTO asistencia,AccionType accionType) throws Exception; 
	
	/**
	 * Listar asistencia.
	 *
	 * @param asistencia el asistencia
	 * @return the list
	 * @throws Exception the exception
	 */
	List<AsistenciaDTO> listarAsistencia(AsistenciaDTO asistencia) throws Exception;
	
	/**
	 * contar lista asistencia.
	 *
	 * @param asistencia el asistencia
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarAsistencia(AsistenciaDTO asistencia);
	
	List<AsistenciaDTO> obtenerAsistencia(boolean isConsulta,String idActividad,String idFiltro,Date fechaHorario,String userName) throws Exception;
	
	/**
	 * Controlador accion asistencia.
	 *
	 * @param asistencia el asistencia
	 * @param accionType el accion type
	 * @return the asistencia
	 * @throws Exception the exception
	 */
	ActividadDTO controladorAccionActividad(ActividadDTO Actividad,AccionType accionType) throws Exception; 
	
	/**
	 * Listar Actividad.
	 *
	 * @param Actividad el Actividad
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ActividadDTO> listarActividad(ActividadDTO Actividad) throws Exception;
	
	/**
	 * contar lista Actividad.
	 *
	 * @param Actividad el Actividad
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarActividad(ActividadDTO Actividad);
	
	 PlanPagosDTO findByPlanPagos(PlanPagosDTO planPagos) throws Exception;
	
	String generarPlanPagosMaisvo(String idCuotaConcepto,String userName) throws Exception;
	
	List<DetallePlanPagosFiltroVO> obtenerDetallePlanPagosFiltroVO(DetallePlanPagosFiltroVO filtro) throws Exception;
	
	PuestoDTO optenerByPuesto(String idPuesto) throws Exception;
	
	 List<ControlPagoDTO> obtenerListaCajaFiltroPago(VentaFiltroVO filtro) throws Exception;
	 
	 PersonalDTO findPersonalByNro(String nroDoc) throws Exception;
	 
	 AsociadoDTO findAsociadoByNro(String nroDoc) throws Exception;
}