package pe.com.builderp.core.facturacion.ejb.service.compra.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import pe.com.builderp.core.facturacion.ejb.dao.compra.local.CompraDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.compra.local.ContactoProveedorDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.compra.local.CuentaBancariaDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.compra.local.CuentaTipoDocumentoDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.compra.local.DetalleCompraDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.compra.local.DetalleOrdenCompraDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.compra.local.OrdenCompraDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.compra.local.ProveedorDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.DetallePedidoDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.PedidoDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.ProductoDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.TipoDocSunatEntidadDaoLocal;
import pe.com.builderp.core.facturacion.ejb.service.compra.local.CompraServiceLocal;
import pe.com.builderp.core.facturacion.model.dto.compra.CompraDTO;
import pe.com.builderp.core.facturacion.model.dto.compra.ContactoProveedorDTO;
import pe.com.builderp.core.facturacion.model.dto.compra.CuentaBancariaDTO;
import pe.com.builderp.core.facturacion.model.dto.compra.CuentaTipoDocumentoDTO;
import pe.com.builderp.core.facturacion.model.dto.compra.DetalleCompraDTO;
import pe.com.builderp.core.facturacion.model.dto.compra.DetalleOrdenCompraDTO;
import pe.com.builderp.core.facturacion.model.dto.compra.OrdenCompraDTO;
import pe.com.builderp.core.facturacion.model.dto.compra.ProveedorDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.PersonalDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.DetallePedidoDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.DetalleProductoDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.PedidoDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.ProductoDTO;
import pe.com.builderp.core.facturacion.model.jpa.compra.Compra;
import pe.com.builderp.core.facturacion.model.jpa.compra.ContactoProveedor;
import pe.com.builderp.core.facturacion.model.jpa.compra.CuentaBancaria;
import pe.com.builderp.core.facturacion.model.jpa.compra.CuentaTipoDocumento;
import pe.com.builderp.core.facturacion.model.jpa.compra.DetalleCompra;
import pe.com.builderp.core.facturacion.model.jpa.compra.DetalleOrdenCompra;
import pe.com.builderp.core.facturacion.model.jpa.compra.OrdenCompra;
import pe.com.builderp.core.facturacion.model.jpa.compra.Proveedor;
import pe.com.builderp.core.facturacion.model.jpa.venta.DetallePedido;
import pe.com.builderp.core.facturacion.model.jpa.venta.Pedido;
import pe.com.builderp.core.facturacion.model.jpa.venta.Producto;
import pe.com.builderp.core.facturacion.model.jpa.venta.Proforma;
import pe.com.builderp.core.facturacion.model.vo.compra.RegistroCompraVO;
import pe.com.edu.siaa.core.ejb.factory.CollectionUtil;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.contabilidad.local.ContabilidadServiceLocal;
import pe.com.edu.siaa.core.ejb.service.local.GenerarReporteServiceLocal;
import pe.com.edu.siaa.core.ejb.service.seguridad.local.SeguridadServiceLocal;
import pe.com.edu.siaa.core.ejb.service.util.FechaUtil;
import pe.com.edu.siaa.core.ejb.util.cache.AppAuthenticator;
import pe.com.edu.siaa.core.ejb.util.cache.ConstanteCommonUtil;
import pe.com.edu.siaa.core.ejb.util.cache.ContabilidadCacheUtil;
import pe.com.edu.siaa.core.ejb.util.cache.ParametroCacheUtil;
import pe.com.edu.siaa.core.ejb.util.cache.SelectItemServiceCacheUtil;
import pe.com.edu.siaa.core.ejb.util.excel.DataExportExcelPersonalizadoUtil;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.dto.contabilidad.AsientoContableDTO;
import pe.com.edu.siaa.core.model.dto.contabilidad.AsientoContableDetDTO;
import pe.com.edu.siaa.core.model.dto.contabilidad.ConfiguracionCuentaDTO;
import pe.com.edu.siaa.core.model.dto.contabilidad.PlanContableDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.EntidadDTO;
import pe.com.edu.siaa.core.model.estate.EstadoGeneralState;
import pe.com.edu.siaa.core.model.jpa.empresa.Personal;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.model.type.NombreReporteType;
import pe.com.edu.siaa.core.model.type.RespuestaNaturalType;
import pe.com.edu.siaa.core.model.type.TipoMovimientoType;
import pe.com.edu.siaa.core.model.type.TipoProductoType;
import pe.com.edu.siaa.core.model.type.TipoReporteGenerarType;
import pe.com.edu.siaa.core.model.util.ConstanteConfigUtil;
import pe.com.edu.siaa.core.model.util.ObjectUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;
import pe.com.edu.siaa.core.model.vo.ExcelHederDataVO;
import pe.com.edu.siaa.core.model.vo.ExcelHederTitleVO;
import pe.com.edu.siaa.core.model.vo.FileVO;
import pe.com.edu.siaa.core.model.vo.ParametroReporteVO;
import pe.com.edu.siaa.core.ui.paginator.IDataProvider;
import pe.com.edu.siaa.core.ui.paginator.LazyLoadingList;


/**
 * La Class CompraServiceImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:32:52 COT 2017
 * @since SIAA-CORE 2.1
 */
 @Stateless
public class CompraServiceImpl implements CompraServiceLocal{
	/** El servicio compra dao impl. */
	@EJB
	private CompraDaoLocal compraDaoImpl; 
	
	/** El servicio contacto proveedor dao impl. */
	@EJB
	private ContactoProveedorDaoLocal contactoProveedorDaoImpl; 
	
	/** El servicio cuenta bancaria dao impl. */
	@EJB
	private CuentaBancariaDaoLocal cuentaBancariaDaoImpl; 
	
	/** El servicio cuenta tipo documento dao impl. */
	@EJB
	private CuentaTipoDocumentoDaoLocal cuentaTipoDocumentoDaoImpl; 
	
	/** El servicio detalle compra dao impl. */
	@EJB
	private DetalleCompraDaoLocal detalleCompraDaoImpl; 
	
	/** El servicio detalle orden compra dao impl. */
	@EJB
	private DetalleOrdenCompraDaoLocal detalleOrdenCompraDaoImpl; 
	
	/** El servicio orden compra dao impl. */
	@EJB
	private OrdenCompraDaoLocal ordenCompraDaoImpl; 
	
	/** El servicio proveedor dao impl. */
	@EJB
	private ProveedorDaoLocal proveedorDaoImpl; 
	

	/** El servicio producto dao impl. */
	@EJB
	private ProductoDaoLocal productoDaoImpl; 
	
	@EJB
	private GenerarReporteServiceLocal	 generarReporteServiceImpl;
		
	
	//Integrando con la contabilidad para generar asientos contables
	
	/** El servicio contabilidad service impl. */
	@EJB
	private ContabilidadServiceLocal contabilidadServiceImpl; 

	@EJB
	private SeguridadServiceLocal seguridadServiceLocal; 
	
	/** El servicio tipo doc sunat entidad dao impl. */
	@EJB
	private TipoDocSunatEntidadDaoLocal tipoDocSunatEntidadDaoImpl; 
	
	@Override
	public  List<DetalleCompraDTO> listarDetalleCompra(DetalleCompraDTO detalleCompra) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.detalleCompraDaoImpl.listarDetalleCompra(detalleCompra),DetalleCompraDTO.class,"producto");
	}
	
	
	@Override
	public String generarReporteRegistroCompra(String userName,String idEntidad,Long ejercicio,Long periodo, Date fechaAsientoDesde, Date fechaAsientoHasta) throws Exception {
		String fileName = UUIDUtil.generarElementUUID();
		EntidadDTO entidadFiltro = new EntidadDTO();
		entidadFiltro.setIdEntidad(idEntidad);
		EntidadDTO entidad = seguridadServiceLocal.controladorAccionEntidad(entidadFiltro, AccionType.FIND_BY_ID);
		List<RegistroCompraVO> listaCompraReporte = new ArrayList<RegistroCompraVO>();
		Map<String,Object> parametroFiltroMap = new HashMap<String,Object>();
		parametroFiltroMap.put("fechaInicio", fechaAsientoDesde);
		parametroFiltroMap.put("fechaFin", fechaAsientoHasta);
		parametroFiltroMap.put("idEntidad", idEntidad);
		listaCompraReporte = this.buscarPaginadoCompra(listaCompraReporte, 3000, parametroFiltroMap);
		String archivoName = fileName;
		
		if (listaCompraReporte != null) {
			Map<String, Object> propiedadesMap = new HashMap<String, Object>();
			propiedadesMap.put("calcularWitchDemanda", "true");
			propiedadesMap.put("exluirCabecera", "true");
			//propiedadesMap.put("anexarHojaExistente", "true");
			//propiedadesMap.put("nombreArchivo", "formato_registro_nota.xlsx");
			//propiedadesMap.put("anexarHojaPosition",1);
			//propiedadesMap.put("printTitleView", "true");
			String titulo = "Data";
			propiedadesMap.put("fechaEmisionFormat", FechaUtil.DATE_DMY);
			propiedadesMap.put("fechaVencimientoFormat", FechaUtil.DATE_DMY);
			
			List<ExcelHederDataVO> listaHeaderData = new ArrayList<ExcelHederDataVO>();
			listaHeaderData.add(new ExcelHederDataVO("codigoOperacion","codigoOperacion"));
			listaHeaderData.add(new ExcelHederDataVO("fechaEmision","fechaEmision"));
			listaHeaderData.add(new ExcelHederDataVO("fechaVencimiento","fechaVencimiento"));
			
			listaHeaderData.add(new ExcelHederDataVO("01","tipoDocumentoComprobante"));
			listaHeaderData.add(new ExcelHederDataVO("001","serieOCodigoDepAduanera"));
			listaHeaderData.add(new ExcelHederDataVO("2000","anhoEmisionDuaODSI"));
			
			listaHeaderData.add(new ExcelHederDataVO("nroDocumentoComprante","nroDocumentoComprante"));
			
			listaHeaderData.add(new ExcelHederDataVO("tipoDocIdentidad","tipoDocIdentidad"));
			listaHeaderData.add(new ExcelHederDataVO("nroDocIdentidad","nroDocIdentidad"));
			listaHeaderData.add(new ExcelHederDataVO("nombreORazonSocial","nombreORazonSocial"));
			
			listaHeaderData.add(new ExcelHederDataVO("baseImponible","baseImponibleOperacionGrabadaExportacion"));
			listaHeaderData.add(new ExcelHederDataVO("IGVOpeEx","IGVOperacionGrabadaExportacion"));
			
			listaHeaderData.add(new ExcelHederDataVO("baseImponible","baseImponibleOperacionExportacionNoGrabada"));
			listaHeaderData.add(new ExcelHederDataVO("IGVOpeExpNoGr","IGVOperacionExportacionNoGrabada"));
			
			listaHeaderData.add(new ExcelHederDataVO("baseImponi","baseImponibleOperacionNoGrabada"));
			listaHeaderData.add(new ExcelHederDataVO("IGVOpeNoGra","IGVOperacionNoGrabada"));
			
			
			listaHeaderData.add(new ExcelHederDataVO("valorAdquisicionNoGrabada","valorAdquisicionNoGrabada"));
			
			listaHeaderData.add(new ExcelHederDataVO("ISC","ISC"));
			listaHeaderData.add(new ExcelHederDataVO("otroTributoCargo","otroTributoCargo"));
			listaHeaderData.add(new ExcelHederDataVO("importeTotal","importeTotal"));
			listaHeaderData.add(new ExcelHederDataVO("nroComprobante","nroComprobanteEmitidoSujetoNoDomiciliado"));
			
			listaHeaderData.add(new ExcelHederDataVO("nroConstaciaD","nroConstaciaDepositoDetraccion"));
			listaHeaderData.add(new ExcelHederDataVO("fechaEmision","fechaEmisionConstaciaDepositoDetraccion"));
			
			listaHeaderData.add(new ExcelHederDataVO("tipoCambio","tipoCambio"));
			
			listaHeaderData.add(new ExcelHederDataVO("fehaRefCo","fehaRefComprobanteDocModifica"));
			listaHeaderData.add(new ExcelHederDataVO("tipoDocRefC","tipoDocRefComprobanteDocModifica"));
			listaHeaderData.add(new ExcelHederDataVO("serieDocRefCom","serieDocRefComprobanteDocModifica"));
			listaHeaderData.add(new ExcelHederDataVO("nroDocRefCompr","nroDocRefComprobanteDocModifica"));
			
			
			List<ExcelHederTitleVO> listaExcelHederTitle = new ArrayList<ExcelHederTitleVO>();
			listaExcelHederTitle.add(new ExcelHederTitleVO("FORMATO 8.1: REGISTRO DE COMPRAS", HorizontalAlignment.LEFT.getCode(),VerticalAlignment.JUSTIFY.getCode(), 1, 1, listaHeaderData.size(),0,(short)14,true));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("EJERCICIO:", HorizontalAlignment.LEFT.getCode(), VerticalAlignment.JUSTIFY.getCode(), 1, 3, 0,0,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("2017", HorizontalAlignment.LEFT.getCode(), VerticalAlignment.JUSTIFY.getCode(), 2, 3, 3,0,true));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("PERIODO:", HorizontalAlignment.LEFT.getCode(), VerticalAlignment.JUSTIFY.getCode(), 1, 4, 0,0,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("1", HorizontalAlignment.LEFT.getCode(), VerticalAlignment.JUSTIFY.getCode(), 2, 4, 3,0,true));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("MONEDA:", HorizontalAlignment.LEFT.getCode(), VerticalAlignment.JUSTIFY.getCode(), 1, 5, 0,0,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("SOLES", HorizontalAlignment.LEFT.getCode(), VerticalAlignment.JUSTIFY.getCode(), 2, 5, 3,0,true));			
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("EMPRESA:", HorizontalAlignment.LEFT.getCode(), VerticalAlignment.JUSTIFY.getCode(), 1, 6, 0,0,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO(entidad.getNombre(), HorizontalAlignment.LEFT.getCode(), VerticalAlignment.JUSTIFY.getCode(), 2, 6, 3,0,true));
			
			
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("CODIGO UNICO DE LA OPERACION", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 1,7, 0,3,0,17,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("FECHA EMISION", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 2,7, 0,3,1,15,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("FECHA DE VENCIMIENTO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 3,7, 0,3,2,15,true));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("COMPROBANTE DE PAGO O DOCUMENTO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 4,7, 3,0,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("TIPO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 4,8, 0,2,3,10,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("SERIE O CODIGO DE LA DEPENDENCIA ADUANERA", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 5,8, 0,2,4,25,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("AÑO DE EMISION DE LA DUA O DSI", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 6,8, 0,2,5,15,true));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("Nº DEL COMOPROBANTE DE PAGO DOCUMENTO U ORDEN DE FORMULARIO FISICO O VIRTUAL", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 7,7, 0,3,6,30,true));
			
			
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("INFORMACION DEL PROVEEDOR", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 8,7, 3,0,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("DOCUMENTO DE IDENTIDAD", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),8, 8, 2,0,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("TIPO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),8, 9, 0,0,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("NUMERO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),9, 9, 0,0,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("APELLIDOS Y NOMBRES, DENOMINACION O RAZON SOCIAL", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),10, 8, 0,2,9,70,true));
			
		
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("ADQUISICIONES GRAVADAS DESTINADAS A OPERACIONES GRAVADAS Y/O DE EXPORTACION", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 11,7, 2,2,10,31,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("BASE IMPONIBLE", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),11, 9, 0,0,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("IGV", HorizontalAlignment.LEFT.getCode(),VerticalAlignment.TOP.getCode(),12, 9, 0,0,true));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("ADQUISICIONES GRAVADAS DESTINADAS A OPERACIONES GRAVADAS Y/O DE EXPORTACION Y OPERACIONES NO GRAVADAS", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 13,7, 2,2,12,40,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("BASE IMPONIBLE", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),13, 9, 0,0,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("IGV", HorizontalAlignment.LEFT.getCode(),VerticalAlignment.TOP.getCode(),14, 9, 0,0,true));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("ADQUISICIONES GRAVADAS DESTINADAS A OPERACIONES NO GRAVADAS", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 15,7, 2,2,14,35,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("BASE IMPONIBLE", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),15, 9, 0,0,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("IGV", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),16, 9, 0,0,true));
			
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("VALOR DE LAS ADQUISICIONES NO GRAVADAS", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 17,7, 0,3,16,16,true));			
			listaExcelHederTitle.add(new ExcelHederTitleVO("ISC", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 18,7, 0,3,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("OTROS TRIBUTOS Y CARGOS", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 19,7, 0,3,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("IMPORTE TOTAL", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 20,7, 0,3,true));			
			listaExcelHederTitle.add(new ExcelHederTitleVO("Nº DE COMPROBANTE DE PAGO EMITIDO POR SUJETO NO DOMICILIADO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 21,7, 0,3,20,20,true));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("CONSTANCIA DE DEPOSITO DE DETRACCION", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 22,7, 2,2,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("NUMERO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),22, 9, 0,0,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("FECHA DE EMISION", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),23, 9, 0,0,true));
				
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("TIPO DE CAMBIO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 24,7, 0,3,true));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("REFERENCIA DE COMPROBANTE DE PAGO O DOCUMENTO ORIGINAL QUE SE MODIFICA", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 25,7, 4,2,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("FECHA", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 25,9, 0,0,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("TIPO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 26,9, 0,0,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("SERIE", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 27,9, 0,0,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("Nº DEL COMPROBANTE DE PAGO O DOCUMENTO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 28,9, 0,0,true));
			
			propiedadesMap.put("listaTituloFinal", listaExcelHederTitle);//para crear con esta lista
			
			DataExportExcelPersonalizadoUtil.generarExcelXLSX(listaHeaderData, listaCompraReporte, archivoName, titulo, propiedadesMap);
		}
		DataExportExcelPersonalizadoUtil.generarExcelXLSXViewMap(archivoName);
		return fileName;
	}
	
	private List<RegistroCompraVO> buscarPaginadoCompra(List<RegistroCompraVO> listaAsientoContableDet, int cantidadPagina, final Map<String,Object> parametroFiltroMap) {
		IDataProvider<RegistroCompraVO> dataProvider = new IDataProvider<RegistroCompraVO>() {
			private int total = 0;
			private int cuenta = 0;
			@Override
			public List<RegistroCompraVO> getBufferedData(int startRow, int offset) {
				List<RegistroCompraVO> lista = new ArrayList<RegistroCompraVO>();
				parametroFiltroMap.put("startRow",startRow);
				parametroFiltroMap.put("offset",startRow + offset);
				try {
					lista = listarCompraReporte(parametroFiltroMap);
				} catch (Exception e) {
					lista = new ArrayList<RegistroCompraVO>();
				}
				return lista;
			}
			@Override
			public int getTotalResultsNumber() {
				if (total == 0 && cuenta == 0) {
					try {
						total = contarListarCompraReporte(parametroFiltroMap);
					} catch (Exception e) {
						//e.printStackTrace();
					}					
					cuenta++;
				}
				return total;
			}
		};
		listaAsientoContableDet = new LazyLoadingList<RegistroCompraVO>(dataProvider, cantidadPagina);
		return listaAsientoContableDet;
	}
	private List<RegistroCompraVO> listarCompraReporte(Map<String,Object> parametroFiltroMap) throws Exception {
		return  this.compraDaoImpl.listarCompraReporte(parametroFiltroMap);
	}
	private int contarListarCompraReporte(Map<String,Object> parametroFiltroMap){
		return  this.compraDaoImpl.contarListarCompraReporte(parametroFiltroMap);
	}
	
	@Override
	public CompraDTO registrarCompra(CompraDTO compra) throws Exception {
		CompraDTO resultado = null;
		Compra resultadoEntity = null;
		String userName = AppAuthenticator.getInstance().getUserName(compra.getAuthToken());
		compra.setIdEntidadSelect(compra.getEntidad().getIdEntidad());
		
		if (!StringUtils.isNotNullOrBlank(compra.getIdCompra())) {
			compra.setFechaCreacion(FechaUtil.obtenerFecha());
			compra.setFechaCompra(FechaUtil.obtenerFecha());
			compra.setUsuarioCreacion(userName);
			compra.setEstado(EstadoGeneralState.ACTIVO.getKey());
			compra.setIdCompra(this.compraDaoImpl.generarIdCompra());
			resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(compra, Compra.class,"entidad@PK@","ordenCompra@PK@","proveedor@PK@","tipoDocSunat@PK@","itemByTipoMoneda@PK@");
			this.compraDaoImpl.save(resultadoEntity);
			//String nroCorrelativoOperacion = registrarAsientoContable(compra, userName);
			//resultadoEntity.setNroCorrelativoOperacion(nroCorrelativoOperacion);
			resultado = compra;
		} else {
			resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(compra, Compra.class,"entidad@PK@","ordenCompra@PK@","proveedor@PK@","tipoDocSunat@PK@","itemByTipoMoneda@PK@");
			this.compraDaoImpl.update(resultadoEntity);
			resultado = compra;	
			//resultadoEntity = this.ventaDaoImpl.find(Venta.class, venta.getIdVenta());
		}
		if (!CollectionUtil.isEmpty(compra.getCompraDetalleCompraList())) {
			for (DetalleCompraDTO detalleCompra : compra.getCompraDetalleCompraList()) {
				if (!detalleCompra.isEsEliminado()) {
					if (StringUtils.isNullOrEmpty(detalleCompra.getIdDetalleCompra())) {
						controladorAccionDetalleCompra(detalleCompra,resultadoEntity,AccionType.CREAR);
					} else {
						controladorAccionDetalleCompra(detalleCompra,resultadoEntity,AccionType.MODIFICAR);
					}
					
				} else {
					controladorAccionDetalleCompra(detalleCompra,resultadoEntity, AccionType.ELIMINAR);
				}
			}
		}
		
		return resultado;
	}
	private String registrarAsientoContable(CompraDTO compra,String userName) throws Exception {
		String resultado = "";
		AsientoContableDTO asientoContable = new AsientoContableDTO();
		asientoContable.setIdEntidadSelect(compra.getIdEntidadSelect());
		asientoContable.setEntidad(new EntidadDTO());
		asientoContable.getEntidad().setIdEntidad(compra.getIdEntidadSelect());
		//asientoContable.setIdAsientoContable();
		String keyLibro =  "49" + "" + "5";
		String keySubLibro =  "49" + "" + "8";
		asientoContable.setItemByLibro(SelectItemServiceCacheUtil.getInstance().obtenerItemByCodigo(keyLibro));
		asientoContable.setItemBySubLibro(SelectItemServiceCacheUtil.getInstance().obtenerItemByCodigo(keySubLibro));
		//asientoContable.setNroCorrelativoAsiento(0L);//Generar en el servicio
		asientoContable.setFechaOperacion(compra.getFechaCompra());//TODO:VER_NATAN_CONTABILIDAD
		String glosa = "Provision de la commpra {nuroDocumento}, {proveedor}";
		glosa = glosa.replace("{nuroDocumento}", compra.getNroDoc());
		glosa = glosa.replace("{proveedor}", (compra.getProveedor().getNombre() + " " + compra.getProveedor().getApellidoPaterno() + " " + compra.getProveedor().getApellidoPaterno()).trim());
		asientoContable.setGlosa(glosa);//TODO:VER_NATAN Pasar al parametro
		//asientoContable.setNroCorrelativoOperacion(controlPago.getNroCorrelativoOperacion());//Auto genere en base a libro y sub libro
		asientoContable.setNroDocumentoOperacion(compra.getNroDoc());
		asientoContable.setIdOperacion(compra.getIdCompra() + "");
		asientoContable.setFechaCreacion(FechaUtil.obtenerFecha());
		asientoContable.setUsuarioCreacion(compra.getUsuarioCreacion());
		asientoContable.setFechaModificacion(null);
		asientoContable.setUsuarioModificacion(null);
		asientoContable.setIpAcceso(compra.getIpAcceso());
		//detalle
		boolean isFlagAplicaIGV = false;
		for (DetalleCompraDTO detalleCompraDTO : compra.getCompraDetalleCompraList()) {//TODO:Mejorar esto
				if (RespuestaNaturalType.SI.getKey().toString().equals(detalleCompraDTO.getProducto().getEsAfectoIGV())) {
					isFlagAplicaIGV = true;
					break;
				}
		}
		//Cuenta libro venta 8 ==> cuenta 12
		String keyCuenta =  compra.getIdEntidadSelect() + "8";
		ConfiguracionCuentaDTO configuracionCuentaDTO = ContabilidadCacheUtil.getInstance().getConfiguracionCuentaMap().get(keyCuenta);
		AsientoContableDetDTO asientoContableDet = new AsientoContableDetDTO();
		asientoContableDet.setPlanContable(configuracionCuentaDTO.getPlanContable());
		asientoContableDet.setMonto(compra.getMontoTotal());
		asientoContableDet.setTipo(TipoMovimientoType.DEBE.getKey());
		asientoContable.getAsientoContableAsientoContableDetList().add(asientoContableDet);
        // cuenta 40
		String porcentajeIGV = "";
		if (isFlagAplicaIGV) {
			String procentajeIgvKey = compra.getIdEntidadSelect() +  ConstanteCommonUtil.PARAMETRO_IGV;
			porcentajeIGV = ParametroCacheUtil.getInstance().getParamtroMap().get(procentajeIgvKey) + "";			
		}
		//cuenta 70
		BigDecimal montoIGV = BigDecimal.ZERO;
		for (DetalleCompraDTO detalleCompraDTO : compra.getCompraDetalleCompraList()) {//TODO:Mejorar esto
			 String keyCuentaVenta =  detalleCompraDTO.getProducto().getPlanContableCompra().getIdPlanContable();
			 PlanContableDTO planContableDTO = ContabilidadCacheUtil.getInstance().getPlanContableMap().get(keyCuentaVenta);
			 asientoContableDet = new AsientoContableDetDTO();
			 asientoContableDet.setPlanContable(planContableDTO);
			 asientoContableDet.setMonto(detalleCompraDTO.getMontoTotal());
			 asientoContableDet.setTipo(TipoMovimientoType.HABER.getKey());
			 asientoContable.getAsientoContableAsientoContableDetList().add(asientoContableDet);
			 if (RespuestaNaturalType.SI.getKey().toString().equals(detalleCompraDTO.getProducto().getEsAfectoIGV())) {
				 montoIGV = montoIGV.add(detalleCompraDTO.getMontoTotal().multiply(ObjectUtil.objectToBigDecimal(porcentajeIGV)));
			 }
		}
		if (isFlagAplicaIGV) {			
			String igvCuentaAsignadaKey = compra.getIdEntidadSelect() +  ConstanteCommonUtil.PARAMETRO_IGV_CUENTA_ASIGANDA;
			String IGVCuentaAsignadaValue =  ParametroCacheUtil.getInstance().getParamtroMap().get(igvCuentaAsignadaKey) + "";
			PlanContableDTO planContableDTO = ContabilidadCacheUtil.getInstance().getPlanContableMap().get(IGVCuentaAsignadaValue);
			asientoContableDet = new AsientoContableDetDTO();
			asientoContableDet.setPlanContable(planContableDTO);
			asientoContableDet.setMonto(montoIGV);
			asientoContableDet.setTipo(TipoMovimientoType.HABER.getKey());
			asientoContable.getAsientoContableAsientoContableDetList().add(asientoContableDet);			
		}
	    AsientoContableDTO asientoContableDetPersit = contabilidadServiceImpl.registrarAsientoContable(asientoContable ,userName, AccionType.CREAR);
	    resultado = asientoContableDetPersit.getNroCorrelativoOperacion();
	    return resultado;
	}
	@Override
	public List<CompraDTO> listarCompra(CompraDTO compra) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.compraDaoImpl.listarCompra(compra),CompraDTO.class,"tipoDocSunat","itemByTipoMoneda","proveedor","ordenCompra:{tipoDocSunat}");
	}
	@Override
	public int contarListarCompra(CompraDTO compra){
		return  this.compraDaoImpl.contarListarCompra(compra);
	}
	@Override
	public ContactoProveedorDTO controladorAccionContactoProveedor(ContactoProveedorDTO contactoProveedor, AccionType accionType) throws Exception {
		ContactoProveedorDTO resultado = null;
		ContactoProveedor resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				contactoProveedor.setIdContactoProveedor(this.contactoProveedorDaoImpl.generarIdContactoProveedor());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(contactoProveedor, ContactoProveedor.class,"proveedor@PK@","itemByContacto@PK@");
				this.contactoProveedorDaoImpl.save(resultadoEntity);	
				resultado = contactoProveedor;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(contactoProveedor, ContactoProveedor.class,"proveedor@PK@","itemByContacto@PK@");
				this.contactoProveedorDaoImpl.update(resultadoEntity);
				resultado = contactoProveedor;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.contactoProveedorDaoImpl.find(ContactoProveedor.class, contactoProveedor.getIdContactoProveedor());
				this.contactoProveedorDaoImpl.delete(resultadoEntity);
				resultado = contactoProveedor;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.contactoProveedorDaoImpl.find(ContactoProveedor.class, contactoProveedor.getIdContactoProveedor());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,ContactoProveedorDTO.class,"proveedor@PK@","itemByContacto@PK@");
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.contactoProveedorDaoImpl.findByNombre(contactoProveedor),ContactoProveedorDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<ContactoProveedorDTO> listarContactoProveedor(ContactoProveedorDTO contactoProveedor) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.contactoProveedorDaoImpl.listarContactoProveedor(contactoProveedor),ContactoProveedorDTO.class,"proveedor","itemByContacto");
	}
	@Override
	public int contarListarContactoProveedor(ContactoProveedorDTO contactoProveedor){
		return  this.contactoProveedorDaoImpl.contarListarContactoProveedor(contactoProveedor);
	}
	@Override
	public CuentaBancariaDTO controladorAccionCuentaBancaria(CuentaBancariaDTO cuentaBancaria, AccionType accionType) throws Exception {
		CuentaBancariaDTO resultado = null;
		CuentaBancaria resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				cuentaBancaria.setIdCuentaBancaria(this.cuentaBancariaDaoImpl.generarIdCuentaBancaria());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(cuentaBancaria, CuentaBancaria.class,"itemByBanco@PK@","proveedor@PK@","itemByMoneda@PK@","itemByTipoCuenta@PK@");
				this.cuentaBancariaDaoImpl.save(resultadoEntity);	
				resultado = cuentaBancaria;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(cuentaBancaria, CuentaBancaria.class,"itemByBanco@PK@","proveedor@PK@","itemByMoneda@PK@","itemByTipoCuenta@PK@");
				this.cuentaBancariaDaoImpl.update(resultadoEntity);
				resultado = cuentaBancaria;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.cuentaBancariaDaoImpl.find(CuentaBancaria.class, cuentaBancaria.getIdCuentaBancaria());
				this.cuentaBancariaDaoImpl.delete(resultadoEntity);
				resultado = cuentaBancaria;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.cuentaBancariaDaoImpl.find(CuentaBancaria.class, cuentaBancaria.getIdCuentaBancaria());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,CuentaBancariaDTO.class,"itemByBanco@PK@","proveedor@PK@","itemByMoneda@PK@","itemByTipoCuenta@PK@");
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.cuentaBancariaDaoImpl.findByNombre(cuentaBancaria),CuentaBancariaDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<CuentaBancariaDTO> listarCuentaBancaria(CuentaBancariaDTO cuentaBancaria) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.cuentaBancariaDaoImpl.listarCuentaBancaria(cuentaBancaria),CuentaBancariaDTO.class,"itemByBanco","proveedor","itemByMoneda","itemByTipoCuenta");
	}
	@Override
	public int contarListarCuentaBancaria(CuentaBancariaDTO cuentaBancaria){
		return  this.cuentaBancariaDaoImpl.contarListarCuentaBancaria(cuentaBancaria);
	}
	@Override
	public CuentaTipoDocumentoDTO controladorAccionCuentaTipoDocumento(CuentaTipoDocumentoDTO cuentaTipoDocumento, AccionType accionType) throws Exception {
		CuentaTipoDocumentoDTO resultado = null;
		CuentaTipoDocumento resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				cuentaTipoDocumento.setIdCuentaTipoDocumento(this.cuentaTipoDocumentoDaoImpl.generarIdCuentaTipoDocumento());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(cuentaTipoDocumento, CuentaTipoDocumento.class,"proveedor@PK@","idPlanContable@PK@","itemByTipoDocumento@PK@","itemByMoneda@PK@");
				this.cuentaTipoDocumentoDaoImpl.save(resultadoEntity);	
				resultado = cuentaTipoDocumento;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(cuentaTipoDocumento, CuentaTipoDocumento.class,"proveedor@PK@","idPlanContable@PK@","itemByTipoDocumento@PK@","itemByMoneda@PK@");
				this.cuentaTipoDocumentoDaoImpl.update(resultadoEntity);
				resultado = cuentaTipoDocumento;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.cuentaTipoDocumentoDaoImpl.find(CuentaTipoDocumento.class, cuentaTipoDocumento.getIdCuentaTipoDocumento());
				this.cuentaTipoDocumentoDaoImpl.delete(resultadoEntity);
				resultado = cuentaTipoDocumento;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.cuentaTipoDocumentoDaoImpl.find(CuentaTipoDocumento.class, cuentaTipoDocumento.getIdCuentaTipoDocumento());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,CuentaTipoDocumentoDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.cuentaTipoDocumentoDaoImpl.findByNombre(cuentaTipoDocumento),CuentaTipoDocumentoDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<CuentaTipoDocumentoDTO> listarCuentaTipoDocumento(CuentaTipoDocumentoDTO cuentaTipoDocumento) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.cuentaTipoDocumentoDaoImpl.listarCuentaTipoDocumento(cuentaTipoDocumento),CuentaTipoDocumentoDTO.class);
	}
	@Override
	public int contarListarCuentaTipoDocumento(CuentaTipoDocumentoDTO cuentaTipoDocumento){
		return  this.cuentaTipoDocumentoDaoImpl.contarListarCuentaTipoDocumento(cuentaTipoDocumento);
	}
	private DetalleCompraDTO controladorAccionDetalleCompra(DetalleCompraDTO detalleCompra, Compra compra, AccionType accionType) throws Exception {
		DetalleCompraDTO resultado = null;
		DetalleCompra resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				detalleCompra.setIdDetalleCompra(this.detalleCompraDaoImpl.generarIdDetalleCompra());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(detalleCompra, DetalleCompra.class,"compra@PK@","producto@PK@");
				resultadoEntity.setCompra(compra);
				this.detalleCompraDaoImpl.save(resultadoEntity);
				this.actualizarStock(detalleCompra, true);
				resultado = detalleCompra;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(detalleCompra, DetalleCompra.class,"compra@PK@","producto@PK@");
			    resultadoEntity.setCompra(compra);
				this.detalleCompraDaoImpl.update(resultadoEntity);
				//this.actualizarStock(detalleCompra, true);
				resultado = detalleCompra;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.detalleCompraDaoImpl.find(DetalleCompra.class, detalleCompra.getIdDetalleCompra());
				this.detalleCompraDaoImpl.delete(resultadoEntity);
				this.actualizarStock(detalleCompra, false);
				resultado = detalleCompra;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.detalleCompraDaoImpl.find(DetalleCompra.class, detalleCompra.getIdDetalleCompra());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,DetalleCompraDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.detalleCompraDaoImpl.findByNombre(detalleCompra),DetalleCompraDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	
	private boolean actualizarStock(DetalleCompraDTO detalleCompra, boolean esSumar) {
		Producto producto = this.productoDaoImpl.find(Producto.class, detalleCompra.getProducto().getIdProducto());
		if (producto != null && TipoProductoType.BIEN.getKey().equals(producto.getTipo())) {
			if (!esSumar) { 
				producto.setStock(producto.getStock().subtract(detalleCompra.getCantidad()));
			} else {
				producto.setStock(producto.getStock().add(detalleCompra.getCantidad()));
			}
			this.productoDaoImpl.update(producto);
			return true;
		}
		return false;
	}

	
	@Override
	public List<DetalleOrdenCompraDTO> listarDetalleOrdenCompra(DetalleOrdenCompraDTO detalleOrdenCompra) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.detalleOrdenCompraDaoImpl.listarDetalleOrdenCompra(detalleOrdenCompra),DetalleOrdenCompraDTO.class,"producto:{itemByUnidadMedida}","ordenCompra");
	}
	@Override
	public int contarListarDetalleOrdenCompra(DetalleOrdenCompraDTO detalleOrdenCompra){
		return  this.detalleOrdenCompraDaoImpl.contarListarDetalleOrdenCompra(detalleOrdenCompra);
	}
	@Override
	public OrdenCompraDTO controladorAccionOrdenCompra(OrdenCompraDTO ordenCompra, AccionType accionType) throws Exception {
		OrdenCompraDTO resultado = null;
		OrdenCompra resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				ordenCompra.setIdOrdenCompra(this.ordenCompraDaoImpl.generarIdOrdenCompra());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(ordenCompra, OrdenCompra.class,"entidad@PK@","proveedor@PK@","tipoDocSunat@PK@","itemByTipoMoneda@PK@");
				this.ordenCompraDaoImpl.save(resultadoEntity);	
				resultado = ordenCompra;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(ordenCompra, OrdenCompra.class,"entidad@PK@","proveedor@PK@","tipoDocSunat@PK@","itemByTipoMoneda@PK@");
				this.ordenCompraDaoImpl.update(resultadoEntity);
				resultado = ordenCompra;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.ordenCompraDaoImpl.find(OrdenCompra.class, ordenCompra.getIdOrdenCompra());
				this.ordenCompraDaoImpl.delete(resultadoEntity);
				resultado = ordenCompra;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.ordenCompraDaoImpl.find(OrdenCompra.class, ordenCompra.getIdOrdenCompra());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,OrdenCompraDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.ordenCompraDaoImpl.findByNombre(ordenCompra),OrdenCompraDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<OrdenCompraDTO> listarOrdenCompra(OrdenCompraDTO ordenCompra) throws Exception {
		List<OrdenCompra> listaCompra = ordenCompraDaoImpl.listarOrdenCompra(ordenCompra);
		List<OrdenCompraDTO> listaComp = new ArrayList<OrdenCompraDTO>(); 
		for(OrdenCompra compra : listaCompra ) { 
			OrdenCompraDTO compraDTO = TransferDataObjectUtil.transferObjetoEntityDTO(compra,OrdenCompraDTO.class,"proveedor","tipoDocSunat","itemByTipoMoneda","entidad");
			DetalleOrdenCompraDTO detalleOrdenCompra = new DetalleOrdenCompraDTO();
			detalleOrdenCompra.setId(compra.getIdOrdenCompra());
			List<DetalleOrdenCompraDTO> ordenCompraDetalleOrdenCompraList = listarDetalleOrdenCompra(detalleOrdenCompra);
			compraDTO.setOrdenCompraDetalleOrdenCompraList(ordenCompraDetalleOrdenCompraList);
			listaComp.add(compraDTO);
		}
		listaCompra =  null;
		return listaComp;
	}
	@Override
	public int contarListarOrdenCompra(OrdenCompraDTO ordenCompra){
		return  this.ordenCompraDaoImpl.contarListarOrdenCompra(ordenCompra);
	}
	@Override
	public ProveedorDTO controladorAccionProveedor(ProveedorDTO proveedor, AccionType accionType) throws Exception {
		ProveedorDTO resultado = null;
		Proveedor resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				proveedor.setIdProveedor(this.proveedorDaoImpl.generarIdProveedor());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(proveedor, Proveedor.class,"entidad@PK@","itemByTipoDocumentoIdentidad@PK@");
				this.proveedorDaoImpl.save(resultadoEntity);	
				resultado = proveedor;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(proveedor, Proveedor.class,"entidad@PK@","itemByTipoDocumentoIdentidad@PK@");
				this.proveedorDaoImpl.update(resultadoEntity);
				resultado = proveedor;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.proveedorDaoImpl.find(Proveedor.class, proveedor.getIdProveedor());
				this.proveedorDaoImpl.delete(resultadoEntity);
				resultado = proveedor;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.proveedorDaoImpl.find(Proveedor.class, proveedor.getIdProveedor());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,ProveedorDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.proveedorDaoImpl.findByNombre(proveedor),ProveedorDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<ProveedorDTO> listarProveedor(ProveedorDTO proveedor) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.proveedorDaoImpl.listarProveedor(proveedor),ProveedorDTO.class,"itemByTipoDocumentoIdentidad");
	}
	@Override
	public int contarListarProveedor(ProveedorDTO proveedor){
		return  this.proveedorDaoImpl.contarListarProveedor(proveedor);
	}
	
	@Override
	public DetalleOrdenCompraDTO controladorAccionDetalleOrdenCompra(DetalleOrdenCompraDTO detalleOrdenVenta,OrdenCompra ordencompra, AccionType accionType) throws Exception {
		DetalleOrdenCompraDTO resultado = null;
		DetalleOrdenCompra resultadoEntity = null;
		switch (accionType) {
			case CREAR: 
				detalleOrdenVenta.setIdDetalleOrdenCompra(this.detalleOrdenCompraDaoImpl.generarIdDetalleOrdenCompra());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(detalleOrdenVenta, DetalleOrdenCompra.class,"producto@PK@","ordenCompra@PK@");
				resultadoEntity.setOrdenCompra(ordencompra);
				this.detalleOrdenCompraDaoImpl.save(resultadoEntity);	
				resultado = detalleOrdenVenta;
				//this.actualizarStock(detalleVenta,false);
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(detalleOrdenVenta, DetalleOrdenCompra.class,"producto@PK@","ordenCompra@PK@" );
				resultadoEntity.setOrdenCompra(ordencompra);
				this.detalleOrdenCompraDaoImpl.update(resultadoEntity);
				resultado = detalleOrdenVenta;	
				//this.actualizarStock(detalleVenta,false);
				break;
				
			case ELIMINAR:
				resultadoEntity = this.detalleOrdenCompraDaoImpl.find(DetalleOrdenCompra.class, detalleOrdenVenta.getIdDetalleOrdenCompra());
				this.detalleOrdenCompraDaoImpl.delete(resultadoEntity);
				//this.actualizarStock(detalleVenta,true);
				resultado = detalleOrdenVenta;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.detalleOrdenCompraDaoImpl.find(DetalleOrdenCompra.class, detalleOrdenVenta.getIdDetalleOrdenCompra());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,DetalleOrdenCompraDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.detalleVentaDaoImpl.findByNombre(detalleVenta),DetalleVentaDTO .class);
				break;*/
				
			default:
				break;
	  }
		return resultado;
	}
	
	@Override
	public OrdenCompraDTO registrarOrdenCompraDTO(OrdenCompraDTO ordenCompra) throws Exception {
		OrdenCompraDTO resultado = null;
		OrdenCompra resultadoEntity = null; 
		String userName = AppAuthenticator.getInstance().getUserName(ordenCompra.getAuthToken());
		ordenCompra.setIdEntidadSelect(ordenCompra.getEntidad().getIdEntidad());
		if (!StringUtils.isNotNullOrBlank(ordenCompra.getIdOrdenCompra())) {	
			if (StringUtils.isNotNullOrBlank(ordenCompra.getTipoDocSunat().getIdItem())) {
				String nroDocCalc = tipoDocSunatEntidadDaoImpl.actualizarTipoDocSunat(ordenCompra.getTipoDocSunat().getIdItem(),ordenCompra.getIdEntidadSelect(),ordenCompra.getNroDoc(),ordenCompra.getSerie());
				if (!StringUtils.isNotNullOrBlank(ordenCompra.getNroDoc())) {
					ordenCompra.setNroDoc(nroDocCalc);	
				}
			}
			ordenCompra.setIdOrdenCompra(this.ordenCompraDaoImpl.generarIdOrdenCompra());
			ordenCompra.setFechaCreacion(FechaUtil.obtenerFecha());
			ordenCompra.setFechaOrdenCompra(FechaUtil.obtenerFecha());
			ordenCompra.setUsuarioCreacion(userName);
			ordenCompra.setEstado(EstadoGeneralState.ACTIVO.getKey());
			resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(ordenCompra, OrdenCompra.class,"proveedor@PK@","tipoDocSunat@PK@","itemByTipoMoneda@PK@","entidad@PK@");
			this.ordenCompraDaoImpl.save(resultadoEntity); 
			resultado = ordenCompra;
		} else {
			ordenCompra.setFechaModificacion(FechaUtil.obtenerFecha()); 
			ordenCompra.setUsuarioModificacion(userName);
			resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(ordenCompra, OrdenCompra.class,"proveedor@PK@","tipoDocSunat@PK@","itemByTipoMoneda@PK@","entidad@PK@");
			this.ordenCompraDaoImpl.update(resultadoEntity);
			resultado = ordenCompra;	 
		}
		
		if (!CollectionUtil.isEmpty(ordenCompra.getOrdenCompraDetalleOrdenCompraList())) {
			for (DetalleOrdenCompraDTO detalleOrdenCompra : ordenCompra.getOrdenCompraDetalleOrdenCompraList()) { 
				if (!detalleOrdenCompra.isEsEliminado()) {
					if (StringUtils.isNullOrEmpty(detalleOrdenCompra.getIdDetalleOrdenCompra())) {
						controladorAccionDetalleOrdenCompra(detalleOrdenCompra,resultadoEntity,AccionType.CREAR);
					} else {
						controladorAccionDetalleOrdenCompra(detalleOrdenCompra,resultadoEntity,AccionType.MODIFICAR);
					} 
				} else {
					controladorAccionDetalleOrdenCompra(detalleOrdenCompra,resultadoEntity, AccionType.ELIMINAR);
				}
			}
		} 
		
	 
		
		return resultado;
	}

	
	@Override
	public String generarReporteOrdenCompra(String idOrdenCompra) throws Exception {
		//String fileName = UUIDUtil.generarElementUUID();
		//String codigoGeneradoReporte = fileName;
		
		OrdenCompra ordenCompra = this.ordenCompraDaoImpl .find(OrdenCompra.class, idOrdenCompra);
		String fileName = ordenCompra.getSerie() +"-"+ordenCompra.getNroDoc();
		String codigoGeneradoReporte = ordenCompra.getSerie() +"-"+ordenCompra.getNroDoc();
		 
		Map<String, Object> parametros = new HashMap<String, Object>();
		String[] subreportes;
		subreportes = new String[0];	
		parametros.put("Id_OrdenCompra", idOrdenCompra);
		parametros.put("ruta", ""); 
		
		NombreReporteType reporte = NombreReporteType.JR_REP_ORDEN_COMPRA;
		ParametroReporteVO parametroReporteVO = new ParametroReporteVO(parametros, null, reporte, subreportes, null, false, "", "");
		parametroReporteVO.setFormato(TipoReporteGenerarType.PDF.getKey());
		parametroReporteVO.setFileName(fileName);
		codigoGeneradoReporte = generarReporteServiceImpl.obtenerParametroReporteBigMemory(parametroReporteVO);
		return codigoGeneradoReporte;
	}
	
	
	@Override
	public void eliminarOrdenCompra(String idOrdenCompra,String userName) throws Exception {
		//Eliminado el detalle de matricula y nota 
		 eliminarOrdenCompraTemp(idOrdenCompra,true);
	}
	
	private void eliminarOrdenCompraTemp(String idOrdenCompra, boolean isAll) throws Exception {
		if (StringUtils.isNotNullOrBlank(idOrdenCompra)) { 
			List<DetalleOrdenCompra> listarDetalleOrdenCompra = detalleOrdenCompraDaoImpl.listarDetalleOrdenCompra(idOrdenCompra);
			for (DetalleOrdenCompra detalleOrdenCompraDelete : listarDetalleOrdenCompra) {
				 detalleOrdenCompraDaoImpl.delete(detalleOrdenCompraDelete);
			}
			//
			if (isAll) { 
				List<Compra> listarCompra = compraDaoImpl.listarCompraTemp(idOrdenCompra);
				if(listarCompra.size() > 0) {
					List<String> idCompra= new ArrayList<String>();
					for (Compra compraDelete : listarCompra) {
						idCompra.add(compraDelete.getIdCompra());
					}
					List<DetalleCompra> listarDetalleCompra = detalleCompraDaoImpl.listarDetalleCompraTemp(idCompra);
					for (DetalleCompra detalleCompraDelete : listarDetalleCompra) { 
						 detalleCompraDaoImpl.delete(detalleCompraDelete);
					}
					for (Compra compraDelete : listarCompra) {
						compraDaoImpl.delete(compraDelete);
					}		
				}

				OrdenCompra matriculaEliminar = ordenCompraDaoImpl.find(OrdenCompra.class, idOrdenCompra);
				ordenCompraDaoImpl.delete(matriculaEliminar);
			}
			
		}
	}
	
	
	@Override
	public void eliminarCompra(String idCompra,String userName) throws Exception {
		//Eliminado el detalle de matricula y nota 
		 eliminarCompraTemp(idCompra,true);
	}
	
	private void eliminarCompraTemp(String idCompra, boolean isAll) throws Exception {
		if (StringUtils.isNotNullOrBlank(idCompra)) { 
			List<String>idcompralist= new ArrayList<String>();
			idcompralist.add(idCompra);
			List<DetalleCompra> listarDetalleCompra = detalleCompraDaoImpl.listarDetalleCompraTemp(idcompralist);
			for (DetalleCompra detalleCompraDelete : listarDetalleCompra) { 
				 detalleCompraDaoImpl.delete(detalleCompraDelete);
			}
			//
			if (isAll) { 
				Compra compraEliminar = compraDaoImpl.find(Compra.class, idCompra);
				compraDaoImpl.delete(compraEliminar);
			}
			
		}
	}
	
	
	
	@Override
	public ProveedorDTO findProveedorByNro(String nroDoc) throws Exception {
		ProveedorDTO resultado = new ProveedorDTO();
		Proveedor proveedor = proveedorDaoImpl.findProveedorByNro(nroDoc);
		resultado = TransferDataObjectUtil.transferObjetoEntityDTO(proveedor,ProveedorDTO.class,"tipoDocumento","tipoPersonal");
		proveedor = null;
		return resultado;
	}
	
}