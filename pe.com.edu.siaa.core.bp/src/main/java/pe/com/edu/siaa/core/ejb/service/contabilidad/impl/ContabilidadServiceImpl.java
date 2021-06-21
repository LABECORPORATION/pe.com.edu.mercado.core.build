package pe.com.edu.siaa.core.ejb.service.contabilidad.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import pe.com.builderp.core.contabilidad.model.vo.RegistroAsientoFiltroVO;
import pe.com.edu.siaa.core.ejb.dao.contabilidad.local.AsientoContableDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.contabilidad.local.AsientoContableDetDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.contabilidad.local.ConfigDependenciaItemDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.contabilidad.local.ConfiguracionCuentaDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.contabilidad.local.PlanContableDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.HibernateUtil;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.contabilidad.local.ContabilidadServiceLocal;
import pe.com.edu.siaa.core.ejb.service.seguridad.local.SeguridadServiceLocal;
import pe.com.edu.siaa.core.ejb.service.util.FechaUtil;
import pe.com.edu.siaa.core.ejb.util.excel.DataExportExcelPersonalizadoUtil;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.dto.contabilidad.AsientoContableDTO;
import pe.com.edu.siaa.core.model.dto.contabilidad.AsientoContableDetDTO;
import pe.com.edu.siaa.core.model.dto.contabilidad.ConfigDependenciaItemDTO;
import pe.com.edu.siaa.core.model.dto.contabilidad.ConfiguracionCuentaDTO;
import pe.com.edu.siaa.core.model.dto.contabilidad.PlanContableDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.EntidadDTO;
import pe.com.edu.siaa.core.model.jpa.contabilidad.AsientoContable;
import pe.com.edu.siaa.core.model.jpa.contabilidad.AsientoContableDet;
import pe.com.edu.siaa.core.model.jpa.contabilidad.ConfigDependenciaItem;
import pe.com.edu.siaa.core.model.jpa.contabilidad.ConfiguracionCuenta;
import pe.com.edu.siaa.core.model.jpa.contabilidad.PlanContable;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.model.type.TipoMovimientoType;
import pe.com.edu.siaa.core.model.util.StringUtils;
import pe.com.edu.siaa.core.model.vo.ExcelHederDataVO;
import pe.com.edu.siaa.core.model.vo.ExcelHederTitleVO;
import pe.com.edu.siaa.core.ui.paginator.IDataProvider;
import pe.com.edu.siaa.core.ui.paginator.LazyLoadingList;


/**
 * La Class ContabilidadServiceImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Fri Sep 08 19:44:49 COT 2017
 * @since SIAA-CORE 2.1
 */
 @Stateless
 @EJB(name = "java:app/ContabilidadService", beanInterface = ContabilidadServiceLocal.class)
 @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ContabilidadServiceImpl implements ContabilidadServiceLocal{
	/** El servicio asiento contable det dao impl. */
	@EJB
	private AsientoContableDetDaoLocal asientoContableDetDaoImpl; 
	
	/** El servicio asiento contable dao impl. */
	@EJB
	private AsientoContableDaoLocal asientoContableDaoImpl; 
	
	/** El servicio plan contable dao impl. */
	@EJB
	private PlanContableDaoLocal planContableDaoImpl; 
	
	/** El servicio config dependencia item dao impl. */
	@EJB
	private ConfigDependenciaItemDaoLocal configDependenciaItemDaoImpl; 
	
	/** El servicio configuracion cuenta dao impl. */
	@EJB
	private ConfiguracionCuentaDaoLocal configuracionCuentaDaoImpl; 
	
	@EJB
	private SeguridadServiceLocal seguridadServiceLocal; 
	
	@Override
	public String generarReporteAsientoContableDiario(String userName,RegistroAsientoFiltroVO registroAsientoFiltro) throws Exception {
		String fileName = UUIDUtil.generarElementUUID();
		AsientoContableDetDTO asientoContableDetFiltro = new AsientoContableDetDTO();
		EntidadDTO entidadFiltro = new EntidadDTO();
		entidadFiltro.setIdEntidad(registroAsientoFiltro.getIdEntidad());
		EntidadDTO entidad = seguridadServiceLocal.controladorAccionEntidad(entidadFiltro, AccionType.FIND_BY_ID);
		List<AsientoContableDetDTO> listaAsientoContableDet = new ArrayList<AsientoContableDetDTO>();
		listaAsientoContableDet = this.buscarPaginadoAsientoContableDet(listaAsientoContableDet, 3000, asientoContableDetFiltro);
		String archivoName = fileName;
		
		if (listaAsientoContableDet != null) {
			Map<String, Object> propiedadesMap = new HashMap<String, Object>();
			propiedadesMap.put("calcularWitchDemanda", "true");
			propiedadesMap.put("exluirCabecera", "true");
			//propiedadesMap.put("anexarHojaExistente", "true");
			//propiedadesMap.put("nombreArchivo", "formato_registro_nota.xlsx");
			//propiedadesMap.put("anexarHojaPosition",1);
			//propiedadesMap.put("printTitleView", "true");
			String titulo = "Data";
			
			List<ExcelHederDataVO> listaHeaderData = new ArrayList<ExcelHederDataVO>();
			listaHeaderData.add(new ExcelHederDataVO("nroCorrelativoAsiento","asientoContable.nroCorrelativoAsiento"));
			listaHeaderData.add(new ExcelHederDataVO("FECHA DE LA OPERACION","asientoContable.fechaOperacion"));
			listaHeaderData.add(new ExcelHederDataVO("GLOSA O DESCRIPCION DE LA OPERACION","asientoContable.glosa"));
			listaHeaderData.add(new ExcelHederDataVO("CODIGO DEL LIBRO O REGISTRO","asientoContable.itemBySubLibro.codigoExterno"));
			listaHeaderData.add(new ExcelHederDataVO("nroCorrelativoOperacion","asientoContable.nroCorrelativoOperacion"));
			listaHeaderData.add(new ExcelHederDataVO("nroDocumentoOperacion","asientoContable.nroDocumentoOperacion"));
			listaHeaderData.add(new ExcelHederDataVO("nroCuenta","nroCuenta"));
			listaHeaderData.add(new ExcelHederDataVO("descripcionCuenta","descripcionCuenta"));
			listaHeaderData.add(new ExcelHederDataVO("debe","debe"));
			listaHeaderData.add(new ExcelHederDataVO("haber","haber"));
			
			
			List<ExcelHederTitleVO> listaExcelHederTitle = new ArrayList<ExcelHederTitleVO>();
			listaExcelHederTitle.add(new ExcelHederTitleVO(entidad.getNombre(), HorizontalAlignment.LEFT.getCode(), VerticalAlignment.JUSTIFY.getCode(), 1, 1, listaHeaderData.size(),0,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO(entidad.getCodigo(), HorizontalAlignment.LEFT.getCode(),VerticalAlignment.JUSTIFY.getCode(), 1, 2, listaHeaderData.size(),0,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("LIBRO DIARIO", HorizontalAlignment.CENTER_SELECTION.getCode(),VerticalAlignment.JUSTIFY.getCode(), 1, 3, listaHeaderData.size(),0,(short)14,false));
			String dataReporte  = "DESDE : " + FechaUtil.obtenerFechaFormatoSimple(registroAsientoFiltro.getFechaAsientoDesde()) + "               HASTA : " + FechaUtil.obtenerFechaFormatoSimple(registroAsientoFiltro.getFechaAsientoDesde()) + "               RUC : " + entidad.getCodigo() +"               APELLIDOS Y NOMBRES, DENOMINACION O RAZON SOCIAL : " + entidad.getNombre() + "";
			listaExcelHederTitle.add(new ExcelHederTitleVO(dataReporte, HorizontalAlignment.LEFT.getCode(),(short)-1, 1, 4, 0,0,true));

			listaExcelHederTitle.add(new ExcelHederTitleVO("Nº CORRELATIVO DEL ASIENTO O CODIGO UNICO DE LA OPERACIÓN", HorizontalAlignment.LEFT.getCode(),VerticalAlignment.JUSTIFY.getCode(),1, 5, 0,3,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("FECHA DE LA OPERACION", HorizontalAlignment.LEFT.getCode(),VerticalAlignment.JUSTIFY.getCode(),2, 5, 0,3,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("GLOSA O DESCRIPCION DE LA OPERACION", HorizontalAlignment.LEFT.getCode(),VerticalAlignment.JUSTIFY.getCode(), 3, 5, 0,3,true));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("REFERENCIA DE LA OPERACION", HorizontalAlignment.CENTER_SELECTION.getCode(),VerticalAlignment.JUSTIFY.getCode(), 4, 5, 3,0,true));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("CODIGO DEL LIBRO O REGISTRO", HorizontalAlignment.LEFT.getCode(), VerticalAlignment.JUSTIFY.getCode(), 4, 6, 0,2,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("Nº CORRELATIVO", HorizontalAlignment.LEFT.getCode(),VerticalAlignment.JUSTIFY.getCode(), 5, 6, 0,2,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("Nº DEL DOCUMENTO SUSTENTATORIO", HorizontalAlignment.LEFT.getCode(),VerticalAlignment.JUSTIFY.getCode(), 6, 6, 0,2,true));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("CUENTA CONTABLE ASOCIADA A LA OPERACION", HorizontalAlignment.CENTER_SELECTION.getCode(),VerticalAlignment.JUSTIFY.getCode(), 7, 5, 2,0,true));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("CODIGO", HorizontalAlignment.LEFT.getCode(),VerticalAlignment.JUSTIFY.getCode(), 7, 6, 0,2,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("DENOMINACION", HorizontalAlignment.LEFT.getCode(),VerticalAlignment.JUSTIFY.getCode(), 8, 6, 0,2,true));
			
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("MOVIMIENTO", HorizontalAlignment.CENTER_SELECTION.getCode(),VerticalAlignment.JUSTIFY.getCode(), 9, 5, 2,0,true));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("DEBE", HorizontalAlignment.LEFT.getCode(), VerticalAlignment.JUSTIFY.getCode(), 9, 6, 0,2,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("HABER", HorizontalAlignment.LEFT.getCode(), VerticalAlignment.JUSTIFY.getCode(), 10, 6, 0,2,true));
		
			propiedadesMap.put("listaTituloFinal", listaExcelHederTitle);//para crear con esta lista
			
			DataExportExcelPersonalizadoUtil.generarExcelXLSX(listaHeaderData, listaAsientoContableDet, archivoName, titulo, propiedadesMap);
		}
		DataExportExcelPersonalizadoUtil.generarExcelXLSXViewMap(archivoName);
		return fileName;
	}
	
	@Override
	public String generarReporteAsientoContableLibroMayor(String userName,RegistroAsientoFiltroVO registroAsientoFiltro) throws Exception {
		String fileName = UUIDUtil.generarElementUUID();
		AsientoContableDetDTO asientoContableDetFiltro = new AsientoContableDetDTO();
		EntidadDTO entidadFiltro = new EntidadDTO();
		entidadFiltro.setIdEntidad(registroAsientoFiltro.getIdEntidad());
		EntidadDTO entidad = seguridadServiceLocal.controladorAccionEntidad(entidadFiltro, AccionType.FIND_BY_ID);
		List<AsientoContableDetDTO> listaAsientoContableDet = new ArrayList<AsientoContableDetDTO>();
		listaAsientoContableDet = this.buscarPaginadoAsientoContableDet(listaAsientoContableDet, 3000, asientoContableDetFiltro);
		String archivoName = fileName;
		
		if (listaAsientoContableDet != null) {
			Map<String, Object> propiedadesMap = new HashMap<String, Object>();
			propiedadesMap.put("calcularWitchDemanda", "true");
			propiedadesMap.put("exluirCabecera", "true");
			//propiedadesMap.put("anexarHojaExistente", "true");
			//propiedadesMap.put("nombreArchivo", "formato_registro_nota.xlsx");
			//propiedadesMap.put("anexarHojaPosition",1);
			//propiedadesMap.put("printTitleView", "true");
			String titulo = "Data";
			
			List<ExcelHederDataVO> listaHeaderData = new ArrayList<ExcelHederDataVO>();
			listaHeaderData.add(new ExcelHederDataVO("FECHA DE LA OPERACION","asientoContable.fechaOperacion"));
			listaHeaderData.add(new ExcelHederDataVO("nroCorrelativoAsiento","asientoContable.nroCorrelativoAsiento"));
			listaHeaderData.add(new ExcelHederDataVO("GLOSA O DESCRIPCION DE LA OPERACION","asientoContable.glosa"));
			
			listaHeaderData.add(new ExcelHederDataVO("nroCuenta","nroCuenta"));
			listaHeaderData.add(new ExcelHederDataVO("descripcionCuenta","descripcionCuenta"));
			
			listaHeaderData.add(new ExcelHederDataVO("DEUDOR s","debe"));
			listaHeaderData.add(new ExcelHederDataVO("ACREEDOR s","haber"));
			
			
			List<ExcelHederTitleVO> listaExcelHederTitle = new ArrayList<ExcelHederTitleVO>();
			listaExcelHederTitle.add(new ExcelHederTitleVO(entidad.getNombre(), HorizontalAlignment.LEFT.getCode(), VerticalAlignment.JUSTIFY.getCode(), 1, 1, listaHeaderData.size(),0,false));
			listaExcelHederTitle.add(new ExcelHederTitleVO("Fecha : " + FechaUtil.obtenerFechaFormato(FechaUtil.DATE_DMY_HORA) + "", HorizontalAlignment.LEFT.getCode(),VerticalAlignment.JUSTIFY.getCode(), 5, 2, 3,0,false));
			listaExcelHederTitle.add(new ExcelHederTitleVO("Usuario : " + userName + "", HorizontalAlignment.LEFT.getCode(),VerticalAlignment.JUSTIFY.getCode(), 5, 3, 3,0,false));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("FORMATO 6.1: LIBRO MAYOR", HorizontalAlignment.LEFT.getCode(),VerticalAlignment.JUSTIFY.getCode(), 1, 4, listaHeaderData.size(),0,(short)10,false));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("EJERCICIO:", HorizontalAlignment.LEFT.getCode(),(short)-1, 1,6, 0,0,false));
			listaExcelHederTitle.add(new ExcelHederTitleVO("2017 ", HorizontalAlignment.LEFT.getCode(),(short)-1, 2,6, 0,0,false));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("PERIODO INI.:", HorizontalAlignment.LEFT.getCode(),(short)-1, 1,7, 0,0,false));
			listaExcelHederTitle.add(new ExcelHederTitleVO("1 ", HorizontalAlignment.LEFT.getCode(),(short)-1, 2,7, 0,0,false));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("PERIODO HASTA.:", HorizontalAlignment.LEFT.getCode(),(short)-1, 1,8, 0,0,false));
			listaExcelHederTitle.add(new ExcelHederTitleVO("1 ", HorizontalAlignment.LEFT.getCode(),(short)-1, 2,8, 0,0,false));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("EMPRESA:", HorizontalAlignment.LEFT.getCode(),(short)-1, 1,9, 0,0,false));
			listaExcelHederTitle.add(new ExcelHederTitleVO(entidad.getNombre(), HorizontalAlignment.LEFT.getCode(),(short)-1, 2,9, 0,0,false));
			
			

			listaExcelHederTitle.add(new ExcelHederTitleVO("FECHA OPERACION", HorizontalAlignment.LEFT.getCode(),VerticalAlignment.JUSTIFY.getCode(),1, 12, 0,3,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("NÚMERO CORRELATIVO DEL LIBRO", HorizontalAlignment.LEFT.getCode(),VerticalAlignment.JUSTIFY.getCode(),2, 12, 0,3,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("DESCRIPCIÓN O GLOSA DE LA OPERACION", HorizontalAlignment.LEFT.getCode(),VerticalAlignment.JUSTIFY.getCode(), 3, 12, 0,3,true ));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("CODIGO Y/O DENOMINACIÓN DE LA CUENTA", HorizontalAlignment.CENTER_SELECTION.getCode(),VerticalAlignment.JUSTIFY.getCode(), 4, 12, 2,0,true));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("CODIGO", HorizontalAlignment.LEFT.getCode(), VerticalAlignment.JUSTIFY.getCode(), 4, 13, 0,2,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("DENOMINACIÓN", HorizontalAlignment.CENTER_SELECTION.getCode(),VerticalAlignment.JUSTIFY.getCode(), 5, 13, 0,2,true));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("SALDOS Y MOVIMIENTOS", HorizontalAlignment.CENTER_SELECTION.getCode(),VerticalAlignment.JUSTIFY.getCode(), 6, 12, 2,0,true));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("DEUDOR", HorizontalAlignment.LEFT.getCode(),VerticalAlignment.JUSTIFY.getCode(), 6, 13, 0,2,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("ACREEDOR", HorizontalAlignment.CENTER_SELECTION.getCode(),VerticalAlignment.JUSTIFY.getCode(), 7, 13, 0,2,true));
			
		
			propiedadesMap.put("listaTituloFinal", listaExcelHederTitle);//para crear con esta lista
			
			DataExportExcelPersonalizadoUtil.generarExcelXLSX(listaHeaderData, listaAsientoContableDet, archivoName, titulo, propiedadesMap);
		}
		DataExportExcelPersonalizadoUtil.generarExcelXLSXViewMap(archivoName);
		return fileName;
	}
	private List<AsientoContableDetDTO> buscarPaginadoAsientoContableDet(List<AsientoContableDetDTO> listaAsientoContableDet, int cantidadPagina, final AsientoContableDetDTO asientoContableDetFiltro) {
		IDataProvider<AsientoContableDetDTO> dataProvider = new IDataProvider<AsientoContableDetDTO>() {
			private int total = 0;
			private int cuenta = 0;
			@Override
			public List<AsientoContableDetDTO> getBufferedData(int startRow, int offset) {
				List<AsientoContableDetDTO> lista = new ArrayList<AsientoContableDetDTO>();
				asientoContableDetFiltro.setStartRow(startRow);
				asientoContableDetFiltro.setOffset(startRow + offset);
				try {
					lista = listarAsientoContableDetReporte(asientoContableDetFiltro);
					if (lista != null) {
						for (AsientoContableDetDTO objDataDet : lista) {
							if (TipoMovimientoType.DEBE.getKey().equals(objDataDet.getTipo())) {
								objDataDet.setDebe(objDataDet.getMonto());
							} else if (TipoMovimientoType.HABER.getKey().equals(objDataDet.getTipo())) {
								objDataDet.setHaber(objDataDet.getMonto());
							}
						}
					}
				} catch (Exception e) {
					lista = new ArrayList<AsientoContableDetDTO>();
				}
				return lista;
			}
			@Override
			public int getTotalResultsNumber() {
				if (total == 0 && cuenta == 0) {
					try {
						total = contarListarAsientoContableDetReporte(asientoContableDetFiltro);
					} catch (Exception e) {
						//e.printStackTrace();
					}					
					cuenta++;
				}
				return total;
			}
		};
		listaAsientoContableDet = new LazyLoadingList<AsientoContableDetDTO>(dataProvider, cantidadPagina);
		return listaAsientoContableDet;
	}
	
	/*@Override
	public  Map<String,Integer> obtenerCantidadEmpresaAsignadaMap(List<String> listaIdUsuario) throws Exception {
		return this.asociarEmpresaDaoImpl.obtenerCantidadEmpresaAsignadaMap(listaIdUsuario);
	}*/
	
	private AsientoContableDetDTO controladorAccionAsientoContableDet(AsientoContableDetDTO asientoContableDet, AsientoContable asientoContable , AccionType accionType) throws Exception {
		AsientoContableDetDTO resultado = null;
		AsientoContableDet resultadoEntity = null;
		asientoContableDet.setNroCuenta(asientoContableDet.getPlanContable().getCodigo());
		asientoContableDet.setDescripcionCuenta(asientoContableDet.getPlanContable().getNombre());
		switch (accionType) {
			case CREAR:
				asientoContableDet.setIdAsientoContableDet(this.asientoContableDetDaoImpl.generarIdAsientoContableDet());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(asientoContableDet, AsientoContableDet.class,"planContable@PK@");
				resultadoEntity.setAsientoContable(asientoContable);
				this.asientoContableDetDaoImpl.save(resultadoEntity);	
				resultado = asientoContableDet;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(asientoContableDet, AsientoContableDet.class,"planContable@PK@");
			    resultadoEntity.setAsientoContable(asientoContable);
				this.asientoContableDetDaoImpl.update(resultadoEntity);
				resultado = asientoContableDet;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.asientoContableDetDaoImpl.find(AsientoContableDet.class, asientoContableDet.getIdAsientoContableDet());
				this.asientoContableDetDaoImpl.delete(resultadoEntity);
				resultado = asientoContableDet;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.asientoContableDetDaoImpl.find(AsientoContableDet.class, asientoContableDet.getIdAsientoContableDet());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,AsientoContableDetDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.asientoContableDetDaoImpl.findByNombre(asientoContableDet),AsientoContableDetDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<AsientoContableDetDTO> listarAsientoContableDet(AsientoContableDetDTO asientoContableDet) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.asientoContableDetDaoImpl.listarAsientoContableDet(asientoContableDet),AsientoContableDetDTO.class,"planContable");
	}
	@Override
	public int contarListarAsientoContableDet(AsientoContableDetDTO asientoContableDet){
		return  this.asientoContableDetDaoImpl.contarListarAsientoContableDet(asientoContableDet);
	}
	
	private List<AsientoContableDetDTO> listarAsientoContableDetReporte(AsientoContableDetDTO asientoContableDet) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.asientoContableDetDaoImpl.listarAsientoContableDetReporte(asientoContableDet),AsientoContableDetDTO.class,"planContable","asientoContable:{itemByLibro;itemBySubLibro}");
	}
	private int contarListarAsientoContableDetReporte(AsientoContableDetDTO asientoContableDet){
		return  this.asientoContableDetDaoImpl.contarListarAsientoContableDetReporte(asientoContableDet);
	}
	
	@Override
	public AsientoContableDTO registrarAsientoContable(AsientoContableDTO asientoContable, String userName,AccionType accionType) throws Exception {
		AsientoContableDTO resultado = null;
		AsientoContable resultadoEntity = null;
		boolean crearDetalle = false;
		switch (accionType) {
			case CREAR:
				Integer anho = FechaUtil.anio(FechaUtil.obtenerFecha());
				asientoContable.setFechaCreacion(new Date());
				asientoContable.setUsuarioCreacion(userName);
				asientoContable.setIdAsientoContable(this.asientoContableDaoImpl.generarIdAsientoContable());
				asientoContable.setNroCorrelativoAsiento(this.asientoContableDaoImpl.generarNumeroAsiento(anho,asientoContable.getIdEntidadSelect()));
				if (!StringUtils.isNotNullOrBlank(asientoContable.getNroCorrelativoOperacion())) {
					//asientoContable.setNroCorrelativoOperacion(asientoContable.getNroCorrelativoAsiento());//TODO:VER AQUI YA QUE DEBERIA SER POR LIBRO Y SUB LIBRO
					asientoContable.setNroCorrelativoOperacion(this.asientoContableDaoImpl.generarNumeroOperacion(anho,asientoContable.getIdEntidadSelect(),asientoContable.getItemByLibro().getIdItem(),asientoContable.getItemBySubLibro().getIdItem()));
				}
				if (!StringUtils.isNotNullOrBlank(asientoContable.getIdOperacion())) {
					asientoContable.setIdOperacion(asientoContable.getIdAsientoContable());
				}
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(asientoContable, AsientoContable.class,"itemByLibro@PK@","itemBySubLibro@PK@","entidad@PK@");
				this.asientoContableDaoImpl.save(resultadoEntity);	
				resultado = asientoContable;
				crearDetalle = true;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(asientoContable, AsientoContable.class,"itemByLibro@PK@","itemBySubLibro@PK@","entidad@PK@");
				this.asientoContableDaoImpl.update(resultadoEntity);
				resultado = asientoContable;
				crearDetalle = true;
				break;
				
			case ELIMINAR:
				resultadoEntity = this.asientoContableDaoImpl.find(AsientoContable.class, asientoContable.getIdAsientoContable());
				this.asientoContableDaoImpl.delete(resultadoEntity);
				resultado = asientoContable;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.asientoContableDaoImpl.find(AsientoContable.class, asientoContable.getIdAsientoContable());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,AsientoContableDTO.class);
				break;
				
			default:
				break;
		}
		if (crearDetalle) {
			if (asientoContable.getAsientoContableAsientoContableDetList() != null) {
				for (AsientoContableDetDTO asientoContableDet : asientoContable.getAsientoContableAsientoContableDetList()) {
					if (!asientoContable.isEsEliminado()) {
						if (StringUtils.isNullOrEmpty(asientoContableDet.getIdAsientoContableDet())) {
							controladorAccionAsientoContableDet(asientoContableDet,resultadoEntity,AccionType.CREAR);
						} else {
							controladorAccionAsientoContableDet(asientoContableDet,resultadoEntity,AccionType.MODIFICAR);
						}
						
					} else {
						controladorAccionAsientoContableDet(asientoContableDet,resultadoEntity, AccionType.ELIMINAR);
					}
				}
			}
			HibernateUtil.setListaNull(asientoContable);
		}
		return resultado;
	}
	@Override
	public List<AsientoContableDTO> listarAsientoContable(RegistroAsientoFiltroVO registroVentaFiltro) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.asientoContableDaoImpl.listarAsientoContable(registroVentaFiltro),AsientoContableDTO.class,"itemByLibro","itemBySubLibro","entidad");
	}
	@Override
	public int contarListarAsientoContable(RegistroAsientoFiltroVO registroVentaFiltro){
		return  this.asientoContableDaoImpl.contarListarAsientoContable(registroVentaFiltro);
	}
	/*
	@Override
	public List<Long> obtenerIdByUser(AsociarEmpresaDTO asociarEmpresa) throws Exception {
		return this.asociarEmpresaDaoImpl.obtenerIdByUser(asociarEmpresa);
	}*/
	@Override
	public PlanContableDTO controladorAccionPlanContable(PlanContableDTO planContable, AccionType accionType) throws Exception {
		PlanContableDTO resultado = null;
		PlanContable resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				planContable.setIdPlanContable(UUIDUtil.generarElementUUID());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(planContable, PlanContable.class,"planContableDepedencia@PK@","planContableDebe@PK@","planContableHaber@PK@","cliente@PK@");
				this.planContableDaoImpl.save(resultadoEntity);	
				resultado = planContable;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(planContable, PlanContable.class,"planContableDepedencia@PK@","planContableDebe@PK@","planContableHaber@PK@","cliente@PK@");
				this.planContableDaoImpl.update(resultadoEntity);
				resultado = planContable;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.planContableDaoImpl.find(PlanContable.class, planContable.getIdPlanContable());
				this.planContableDaoImpl.delete(resultadoEntity);
				resultado = planContable;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.planContableDaoImpl.find(PlanContable.class, planContable.getIdPlanContable());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,PlanContableDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.planContableDaoImpl.findByNombre(planContable),PlanContableDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<PlanContableDTO> listarPlanContable(PlanContableDTO planContable) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.planContableDaoImpl.listarPlanContable(planContable),PlanContableDTO.class,"planContableDepedencia","entidad","planContableDebe","planContableHaber");
	}
	@Override
	public int contarListarPlanContable(PlanContableDTO planContable){
		return  this.planContableDaoImpl.contarListarPlanContable(planContable);
	}
	
	@Override
	public ConfigDependenciaItemDTO controladorAccionConfigDependenciaItem(ConfigDependenciaItemDTO configDependenciaItem, AccionType accionType) throws Exception {
		ConfigDependenciaItemDTO resultado = null;
		ConfigDependenciaItem resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				configDependenciaItem.setIdConfigDependenciaItem(this.configDependenciaItemDaoImpl.generarIdConfigDependenciaItem());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(configDependenciaItem, ConfigDependenciaItem.class,"item@PK@","itemHijo@PK@","entidad@PK@");
				this.configDependenciaItemDaoImpl.save(resultadoEntity);	
				resultado = configDependenciaItem;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(configDependenciaItem, ConfigDependenciaItem.class,"item@PK@","itemHijo@PK@","entidad@PK@");
				this.configDependenciaItemDaoImpl.update(resultadoEntity);
				resultado = configDependenciaItem;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.configDependenciaItemDaoImpl.find(ConfigDependenciaItem.class, configDependenciaItem.getIdConfigDependenciaItem());
				this.configDependenciaItemDaoImpl.delete(resultadoEntity);
				resultado = configDependenciaItem;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.configDependenciaItemDaoImpl.find(ConfigDependenciaItem.class, configDependenciaItem.getIdConfigDependenciaItem());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,ConfigDependenciaItemDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.configDependenciaItemDaoImpl.findByNombre(configDependenciaItem),ConfigDependenciaItemDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<ConfigDependenciaItemDTO> listarConfigDependenciaItem(ConfigDependenciaItemDTO configDependenciaItem) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.configDependenciaItemDaoImpl.listarConfigDependenciaItem(configDependenciaItem),ConfigDependenciaItemDTO.class,"item","itemHijo");
	}
	@Override
	public int contarListarConfigDependenciaItem(ConfigDependenciaItemDTO configDependenciaItem){
		return  this.configDependenciaItemDaoImpl.contarListarConfigDependenciaItem(configDependenciaItem);
	}

	@Override
	public ConfiguracionCuentaDTO controladorAccionConfiguracionCuenta(ConfiguracionCuentaDTO configuracionCuenta, AccionType accionType) throws Exception {
		ConfiguracionCuentaDTO resultado = null;
		ConfiguracionCuenta resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				configuracionCuenta.setIdConfiguracionCuenta(this.configuracionCuentaDaoImpl.generarIdConfiguracionCuenta());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(configuracionCuenta, ConfiguracionCuenta.class,"itemByLibro@PK@","planContable@PK@","entidad@PK@");
				this.configuracionCuentaDaoImpl.save(resultadoEntity);	
				resultado = configuracionCuenta;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(configuracionCuenta, ConfiguracionCuenta.class,"itemByLibro@PK@","planContable@PK@","entidad@PK@");
				this.configuracionCuentaDaoImpl.update(resultadoEntity);
				resultado = configuracionCuenta;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.configuracionCuentaDaoImpl.find(ConfiguracionCuenta.class, configuracionCuenta.getIdConfiguracionCuenta());
				this.configuracionCuentaDaoImpl.delete(resultadoEntity);
				resultado = configuracionCuenta;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.configuracionCuentaDaoImpl.find(ConfiguracionCuenta.class, configuracionCuenta.getIdConfiguracionCuenta());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,ConfiguracionCuentaDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.configuracionCuentaDaoImpl.findByNombre(configuracionCuenta),ConfiguracionCuentaDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<ConfiguracionCuentaDTO> listarConfiguracionCuenta(ConfiguracionCuentaDTO configuracionCuenta) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.configuracionCuentaDaoImpl.listarConfiguracionCuenta(configuracionCuenta),ConfiguracionCuentaDTO.class,"planContable","itemByLibro","entidad");
	}
	@Override
	public int contarListarConfiguracionCuenta(ConfiguracionCuentaDTO configuracionCuenta){
		return  this.configuracionCuentaDaoImpl.contarListarConfiguracionCuenta(configuracionCuenta);
	}
	
}