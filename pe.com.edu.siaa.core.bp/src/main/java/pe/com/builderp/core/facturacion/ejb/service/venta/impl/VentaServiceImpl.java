package pe.com.builderp.core.facturacion.ejb.service.venta.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.poi.hslf.record.Sound;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import pe.com.edu.siaa.core.model.vo.ExcelHederDataVO;
import pe.com.edu.siaa.core.model.vo.ExcelHederTitleVO;

import com.google.gson.JsonObject;

import pe.com.builderp.core.contabilidad.model.vo.RegistroAsientoFiltroVO;
import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.AsociadoDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.ControlPagoDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.PersonalDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.CajaDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.CategoriaDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.ClienteDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.ComboDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.ConfiguracionAtributoDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.ConfiguracionAtributoValueDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.DetalleEntregaDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.DetallePedidoDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.DetalleProductoDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.DetalleProformaDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.DetalleVentaDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.EntregaDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.GuiaRemisionDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.LineaDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.MarcaDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.ModeloDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.PedidoDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.ProductoDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.ProductoProveedorDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.ProformaDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.TipoDocSunatEntidadDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.TransportistaDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.VehiculoDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.VentaDaoLocal;
import pe.com.builderp.core.facturacion.ejb.service.empresa.local.EmpresaServiceLocal;
import pe.com.builderp.core.facturacion.ejb.service.venta.local.VentaServiceLocal;
import pe.com.builderp.core.facturacion.model.dto.compra.DetalleOrdenCompraDTO;
import pe.com.builderp.core.facturacion.model.dto.compra.OrdenCompraDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.AsociadoDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.ControlPagoDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.PersonalDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.CajaDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.CategoriaDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.ClienteDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.ComboDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.ConfiguracionAtributoDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.ConfiguracionAtributoValueDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.DetalleEntregaDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.DetallePedidoDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.DetalleProductoDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.DetalleProformaDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.DetalleVentaDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.EntregaDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.GuiaRemisionDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.LineaDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.MarcaDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.ModeloDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.PedidoDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.ProductoDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.ProductoProveedorDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.ProformaDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.TipoDocSunatEntidadDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.TransportistaDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.VehiculoDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.VentaDTO;
import pe.com.builderp.core.facturacion.model.jpa.compra.Compra;
import pe.com.builderp.core.facturacion.model.jpa.compra.DetalleCompra;
import pe.com.builderp.core.facturacion.model.jpa.compra.OrdenCompra;
import pe.com.builderp.core.facturacion.model.jpa.venta.Caja;
import pe.com.builderp.core.facturacion.model.jpa.venta.Categoria;
import pe.com.builderp.core.facturacion.model.jpa.venta.Cliente;
import pe.com.builderp.core.facturacion.model.jpa.venta.Combo;
import pe.com.builderp.core.facturacion.model.jpa.venta.ConfiguracionAtributo;
import pe.com.builderp.core.facturacion.model.jpa.venta.ConfiguracionAtributoValue;
import pe.com.builderp.core.facturacion.model.jpa.venta.DetalleEntrega;
import pe.com.builderp.core.facturacion.model.jpa.venta.DetallePedido;
import pe.com.builderp.core.facturacion.model.jpa.venta.DetalleProducto;
import pe.com.builderp.core.facturacion.model.jpa.venta.DetalleProforma;
import pe.com.builderp.core.facturacion.model.jpa.venta.DetalleVenta;
import pe.com.builderp.core.facturacion.model.jpa.venta.Entrega;
import pe.com.builderp.core.facturacion.model.jpa.venta.GuiaRemision;
import pe.com.builderp.core.facturacion.model.jpa.venta.Linea;
import pe.com.builderp.core.facturacion.model.jpa.venta.Marca;
import pe.com.builderp.core.facturacion.model.jpa.venta.Modelo;
import pe.com.builderp.core.facturacion.model.jpa.venta.Pedido;
import pe.com.builderp.core.facturacion.model.jpa.venta.Producto;
import pe.com.builderp.core.facturacion.model.jpa.venta.ProductoProveedor;
import pe.com.builderp.core.facturacion.model.jpa.venta.Proforma;
import pe.com.builderp.core.facturacion.model.jpa.venta.TipoDocSunatEntidad;
import pe.com.builderp.core.facturacion.model.jpa.venta.Transportista;
import pe.com.builderp.core.facturacion.model.jpa.venta.Vehiculo;
import pe.com.builderp.core.facturacion.model.jpa.venta.Venta;
import pe.com.builderp.core.facturacion.model.vo.venta.FormatDataGraficoGananciasVO;
import pe.com.builderp.core.facturacion.model.vo.venta.RegistroVentaVO;
import pe.com.builderp.core.facturacion.model.vo.venta.TendenciasVO;
import pe.com.builderp.core.facturacion.model.vo.venta.VentaAnhoVO;
import pe.com.builderp.core.facturacion.model.vo.venta.VentaGraficoVO;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.UsuarioDaoLocal;
import pe.com.edu.siaa.core.ejb.factory.CollectionUtil;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.common.local.CommonServiceLocal;
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
import pe.com.edu.siaa.core.ejb.util.jasper.ArchivoUtilidades;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.ejb.util.txt.TXTUtil;
import pe.com.edu.siaa.core.ejb.util.txt.Utilidades;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO;
import pe.com.edu.siaa.core.model.dto.common.UbigeoDTO;
import pe.com.edu.siaa.core.model.dto.contabilidad.AsientoContableDTO;
import pe.com.edu.siaa.core.model.dto.contabilidad.AsientoContableDetDTO;
import pe.com.edu.siaa.core.model.dto.contabilidad.ConfiguracionCuentaDTO;
import pe.com.edu.siaa.core.model.dto.contabilidad.PlanContableDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.EntidadDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.UsuarioDTO;
import pe.com.edu.siaa.core.model.estate.EstadoGeneralState;
import pe.com.edu.siaa.core.model.jpa.seguridad.Usuario;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.model.type.NombreReporteType;
import pe.com.edu.siaa.core.model.type.RespuestaNaturalType;
import pe.com.edu.siaa.core.model.type.TipoMovimientoType;
import pe.com.edu.siaa.core.model.type.TipoProductoType;
import pe.com.edu.siaa.core.model.type.TipoReporteGenerarType;
import pe.com.edu.siaa.core.model.type.TipoReportePagoType;
import pe.com.edu.siaa.core.model.util.ConstanteConfigUtil;
import pe.com.edu.siaa.core.model.util.NumerosUtil;
import pe.com.edu.siaa.core.model.util.ObjectUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;
import pe.com.edu.siaa.core.model.vo.DetalleVentaFiltroVO;
import pe.com.edu.siaa.core.model.vo.ExcelHederDataVO;
import pe.com.edu.siaa.core.model.vo.ExcelHederTitleVO;
import pe.com.edu.siaa.core.model.vo.FileVO;
import pe.com.edu.siaa.core.model.vo.ParametroReporteVO;
import pe.com.edu.siaa.core.model.vo.SunatDatosVO;
import pe.com.edu.siaa.core.model.vo.VentaFiltroVO;
import pe.com.edu.siaa.core.ui.paginator.IDataProvider;
import pe.com.edu.siaa.core.ui.paginator.LazyLoadingList;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class VentaServiceImpl.
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
public class VentaServiceImpl implements VentaServiceLocal {
	 
	private Logger log = Logger.getLogger(VentaServiceImpl.class);
	 
	/** El servicio categoria dao impl. */
	@EJB
	private CategoriaDaoLocal categoriaDaoImpl; 
	
	/** El servicio cliente dao impl. */
	@EJB
	private ClienteDaoLocal clienteDaoImpl; 
	
	/** El servicio configuracion atributo dao impl. */
	@EJB
	private ConfiguracionAtributoDaoLocal configuracionAtributoDaoImpl; 
	
	/** El servicio configuracion atributo value dao impl. */
	@EJB
	private ConfiguracionAtributoValueDaoLocal configuracionAtributoValueDaoImpl; 
	
	/** El servicio detalle pedido dao impl. */
	@EJB
	private DetallePedidoDaoLocal detallePedidoDaoImpl; 
	
	/** El servicio guia remision dao impl. */
	@EJB
	private GuiaRemisionDaoLocal guiaRemisionDaoImpl; 
	
	/** El servicio vehiculo dao impl. */
	@EJB
	private VehiculoDaoLocal vehiculoDaoImpl; 
	
	/** El servicio transportista dao impl. */
	@EJB
	private TransportistaDaoLocal transportistaDaoImpl; 
	
	/** El servicio detalle proforma dao impl. */
	@EJB
	private DetalleProformaDaoLocal detalleProformaDaoImpl; 
	
	/** El servicio detalle venta dao impl. */
	@EJB
	private DetalleVentaDaoLocal detalleVentaDaoImpl; 
	
	/** El servicio linea dao impl. */
	@EJB
	private LineaDaoLocal lineaDaoImpl; 
	
	/** El servicio marca dao impl. */
	@EJB
	private MarcaDaoLocal marcaDaoImpl; 
	
	/** El servicio modelo dao impl. */
	@EJB
	private ModeloDaoLocal modeloDaoImpl; 
	
	/** El servicio pedido dao impl. */
	@EJB
	private PedidoDaoLocal pedidoDaoImpl; 
	@EJB
	private transient CommonServiceLocal commonServiceLocal;
	/** El servicio producto dao impl. */
	@EJB
	private ProductoDaoLocal productoDaoImpl; 
	
	/** El servicio proforma dao impl. */
	@EJB
	private ProformaDaoLocal proformaDaoImpl; 
	
	/** El servicio tipo doc sunat entidad dao impl. */
	@EJB
	private TipoDocSunatEntidadDaoLocal tipoDocSunatEntidadDaoImpl; 
	
	/** El servicio venta dao impl. */
	@EJB
	private VentaDaoLocal ventaDaoImpl; 
	
	@EJB
	private SeguridadServiceLocal seguridadServiceLocal; 
	
	@EJB
	private DetalleProductoDaoLocal detalleProductoDaoImpl; 
	
	//Integrando con la contabilidad para generar asientos contables
	
	/** El servicio contabilidad service impl. */
	@EJB
	private ContabilidadServiceLocal contabilidadServiceImpl; 
	
	@EJB
	private GenerarReporteServiceLocal	 generarReporteServiceImpl;
	
	@EJB
	private UsuarioDaoLocal usuarioServiceImpl; 
	
	@EJB
	private ProductoProveedorDaoLocal productoProveedorDaoImpl; 
	
	@EJB
	private CajaDaoLocal cajaDaoImpl;
	
	@EJB
	private ComboDaoLocal comboDaoImpl;
	
	@EJB
	private EntregaDaoLocal entregaDaoImpl;
	
	@EJB
	private DetalleEntregaDaoLocal detalleEntregaDaoImpl;
	
	@EJB
	private transient EmpresaServiceLocal empresaServiceLocal;
	
	@EJB
	private ControlPagoDaoLocal controlPagoDaoImpl;
	
	@EJB
	private PersonalDaoLocal personalDaoImpl;
	
	@EJB
	private AsociadoDaoLocal asociadoDaoImpl;
	
	@Override
	public String generarReporteProductoCodigoBarra(ProductoDTO productoFiltro) throws Exception {
		String fileName = UUIDUtil.generarElementUUID();
		String codigoGeneradoReporte = fileName;
		try {
			Map<String, Object> parametros = new HashMap<String, Object>();
			String[] subreportes;
			subreportes = new String[0];	
			parametros.put("ruta", "");
			List<ProductoDTO> listaProducto = new ArrayList<ProductoDTO>();
			listaProducto = buscarPaginadoProducto(listaProducto, 2000, productoFiltro);
			NombreReporteType reporte = NombreReporteType.JR_REP_REPORTE_PRODUCTO_CODIGO_BARRA;
			ParametroReporteVO parametroReporteVO = new ParametroReporteVO(parametros, listaProducto, reporte, subreportes, null, true, "", "");
			parametroReporteVO.setFormato(TipoReporteGenerarType.PDF.getKey());
			//parametroReporteVO.setUserName(usuario);
			parametroReporteVO.setFileName(fileName);
			codigoGeneradoReporte = generarReporteServiceImpl.obtenerParametroReporteBigMemory(parametroReporteVO);
			return codigoGeneradoReporte;
		} catch (Exception e) {
			log.error(e);
		}
		return codigoGeneradoReporte;
	}
	
	private List<ProductoDTO> buscarPaginadoProducto(List<ProductoDTO> listaProducto, int cantidadPagina, final ProductoDTO productoFiltro) {
		IDataProvider<ProductoDTO> dataProvider = new IDataProvider<ProductoDTO>() {
			private int total = 0;
			private int cuenta = 0;
			@Override
			public List<ProductoDTO> getBufferedData(int startRow, int offset) {
				List<ProductoDTO> lista = new ArrayList<ProductoDTO>();
				productoFiltro.setStartRow(startRow);
				productoFiltro.setOffset(offset);
				try {
					lista = listarProducto(productoFiltro);
				} catch (Exception e) {
					lista = new ArrayList<ProductoDTO>();
				}
				return lista;
			}
			@Override
			public int getTotalResultsNumber() {
				if (total == 0 && cuenta == 0) {
					try {
						total = contarListarProducto(productoFiltro);
					} catch (Exception e) {
						//e.printStackTrace();
					}					
					cuenta++;
				}
				return total;
			}
		};
		listaProducto = new LazyLoadingList<ProductoDTO>(dataProvider, cantidadPagina);
		return listaProducto;
	}
	
	@Override
	public  List<DetalleVentaDTO> listarDetalleVenta(DetalleVentaDTO detalleVenta) throws Exception {		
		List<DetalleVenta> listaTemp = detalleVentaDaoImpl.listarDetalleVenta(detalleVenta);
		List<DetalleVentaDTO> listaDetV = new ArrayList<DetalleVentaDTO>(); 
		for (DetalleVenta det : listaTemp) {
			int cant =0;
			DetalleVentaDTO detalleVentaDTO = TransferDataObjectUtil.transferObjetoEntityDTO(det,DetalleVentaDTO.class,"detalleProducto:{producto;itemByUnidadMedida}","venta");
			detalleVentaDTO.setDetalleProducto(TransferDataObjectUtil.transferObjetoEntityDTO(det.getDetalleProducto(), DetalleProductoDTO.class, "producto","itemByUnidadMedida"));
			detalleVentaDTO.getDetalleProducto().setProducto(TransferDataObjectUtil.transferObjetoEntityDTO(det.getDetalleProducto().getProducto(),ProductoDTO.class,"categoria","itemByUnidadMedida","modelo","itemByColor","itemByTalla","planContableVenta","planContableCompra"));
			cant = detalleVentaDTO.getCantidad().intValue();
			detalleVentaDTO.setVarCantidad(cant); 
			//System.out.println("Cant:: " +detalleVentaDTO.getVarCantidad());
			/*FileVO fileVO = new FileVO();
			fileVO.setRuta(ConstanteConfigUtil.RUTA_RECURSOS_FOTO_ALUMN + ConstanteConfigUtil.SEPARADOR_FILE + "086" +  detalleVentaDTO.getDetalleProducto().getProducto().getImagen());
			detalleVentaDTO.getDetalleProducto().getProducto().setImagen(commonServiceLocal.obtenerImagenEncodeBase64(fileVO));*/
			listaDetV.add(detalleVentaDTO);
		}	
		listaTemp = null;
		return listaDetV;		
	}
	
	@Override
	public String generarReporteRegistroVentaTXT(RegistroAsientoFiltroVO registroAsientoFiltroVO) throws Exception {
		String resultado = UUIDUtil.generarElementUUID();
		EntidadDTO entidadFiltro = new EntidadDTO();
		entidadFiltro.setIdEntidad(registroAsientoFiltroVO.getIdEntidad());
		EntidadDTO entidad = seguridadServiceLocal.controladorAccionEntidad(entidadFiltro, AccionType.FIND_BY_ID);
		String archivoName = "LE" + entidad.getCodigoExterno() +"" + registroAsientoFiltroVO.getEjercicio() +"" + registroAsientoFiltroVO.getPeriodo() + "00140100001111";
		String userName = AppAuthenticator.getInstance().getUserName(registroAsientoFiltroVO.getAuthToken());
		List<Map<String,Object>> listaDetalleVenta = new ArrayList<Map<String,Object>>();
		listaDetalleVenta = this.buscarPaginadoDetalleVentaTXT(listaDetalleVenta, 3000, registroAsientoFiltroVO);
		TXTUtil.escribirArchivo(listaDetalleVenta, userName, resultado, "txt", "|");
		TXTUtil.generarTXTViewMap(userName,resultado,archivoName,TipoReporteGenerarType.TXT);
		return resultado;
	}
	private List<Map<String,Object>> buscarPaginadoDetalleVentaTXT(List<Map<String,Object>> listaAsientoContableDet, int cantidadPagina, final RegistroAsientoFiltroVO registroVentaFiltroVO) {
		IDataProvider<Map<String,Object>> dataProvider = new IDataProvider<Map<String,Object>>() {
			private int total = 0;
			private int cuenta = 0;
			@Override
			public List<Map<String,Object>> getBufferedData(int startRow, int offset) {
				List<Map<String,Object>> lista = new ArrayList<Map<String,Object>>();
				registroVentaFiltroVO.setStartRow(startRow);
				registroVentaFiltroVO.setOffset(startRow + offset);
				try {
					lista = listarVentaReporteTXT(registroVentaFiltroVO);
				} catch (Exception e) {
					lista = new ArrayList<Map<String,Object>>();
				}
				return lista;
			}
			@Override
			public int getTotalResultsNumber() {
				if (total == 0 && cuenta == 0) {
					try {
						total = contarListarVentaReporteTXT(registroVentaFiltroVO);
					} catch (Exception e) {
						//e.printStackTrace();
					}					
					cuenta++;
				}
				return total;
			}
		};
		listaAsientoContableDet = new LazyLoadingList<Map<String,Object>>(dataProvider, cantidadPagina);
		return listaAsientoContableDet;
	}
	private List<Map<String,Object>> listarVentaReporteTXT(RegistroAsientoFiltroVO registroVentaFiltroVO) throws Exception {
		return  this.ventaDaoImpl.listarVentaReporteTXT(registroVentaFiltroVO);
	}
	private int contarListarVentaReporteTXT(RegistroAsientoFiltroVO registroVentaFiltroVO){
		return  this.ventaDaoImpl.contarListarVentaReporteTXT(registroVentaFiltroVO);
	}
	@Override
	public String generarReporteRegistroVenta(RegistroAsientoFiltroVO registroVentaFiltroVO) throws Exception {
		String fileName = UUIDUtil.generarElementUUID();
		EntidadDTO entidadFiltro = new EntidadDTO();
		entidadFiltro.setIdEntidad(registroVentaFiltroVO.getIdEntidad());
		EntidadDTO entidad = seguridadServiceLocal.controladorAccionEntidad(entidadFiltro, AccionType.FIND_BY_ID);
		registroVentaFiltroVO.setCodigoEntidadUniversitaria(entidad.getCodigo());
		List<RegistroVentaVO> listaDetalleVenta = new ArrayList<RegistroVentaVO>();
		listaDetalleVenta = this.buscarPaginadoDetalleVenta(listaDetalleVenta, 3000, registroVentaFiltroVO);
		String archivoName = fileName;
		
		if (listaDetalleVenta != null) {
			Map<String, Object> propiedadesMap = new HashMap<String, Object>();
			propiedadesMap.put("calcularWitchDemanda", "true");
			propiedadesMap.put("exluirCabecera", "true");
			//propiedadesMap.put("anexarHojaExistente", "true");
			//propiedadesMap.put("nombreArchivo", "formato_registro_nota.xlsx");
			//propiedadesMap.put("anexarHojaPosition",1);
			//propiedadesMap.put("printTitleView", "true");
			String titulo = "Data";
			propiedadesMap.put("fechaEmisionFormat", FechaUtil.DATE_DMY);
			propiedadesMap.put("fechaVencimientoOPagoFormat", FechaUtil.DATE_DMY);
			List<ExcelHederDataVO> listaHeaderData = new ArrayList<ExcelHederDataVO>();
			listaHeaderData.add(new ExcelHederDataVO("codigoOperacion","codigoOperacion"));
			listaHeaderData.add(new ExcelHederDataVO("fechaEmision","fechaEmision"));
			listaHeaderData.add(new ExcelHederDataVO("fechaVencien","fechaVencimientoOPago"));
			
			listaHeaderData.add(new ExcelHederDataVO("01","tipoDocumentoPago"));
			listaHeaderData.add(new ExcelHederDataVO("00001","serieDocumentoPago"));
			listaHeaderData.add(new ExcelHederDataVO("nroDocumentoPago","nroDocumentoPago"));
			
			listaHeaderData.add(new ExcelHederDataVO("DNI01","tipoDocIdentidad"));
			listaHeaderData.add(new ExcelHederDataVO("nroDocIdentidad","nroDocIdentidad"));
			listaHeaderData.add(new ExcelHederDataVO("nombreORazonSocial","nombreORazonSocial"));
			
			listaHeaderData.add(new ExcelHederDataVO("valorFacturado","valorFacturadoExportacion"));
			listaHeaderData.add(new ExcelHederDataVO("baseImponibl","baseImponibleOperacionGrabada"));
			
			listaHeaderData.add(new ExcelHederDataVO("importeTotal","importeTotalOperacionExonerada"));
			listaHeaderData.add(new ExcelHederDataVO("importeTotal","importeTotalOperacionInafecto"));
			
			listaHeaderData.add(new ExcelHederDataVO("ISC","ISC"));
			listaHeaderData.add(new ExcelHederDataVO("IGVoIPM","IGVoIPM"));
			
			listaHeaderData.add(new ExcelHederDataVO("otroTribut","otroTributoBaseImponible"));
			listaHeaderData.add(new ExcelHederDataVO("importeTotal","importeTotalComprobantePago"));
			listaHeaderData.add(new ExcelHederDataVO("tipoCambio","tipoCambio"));
			
			listaHeaderData.add(new ExcelHederDataVO("fehaRefCo","fehaRefComprobanteDocModifica"));
			listaHeaderData.add(new ExcelHederDataVO("tipoDocRefC","tipoDocRefComprobanteDocModifica"));
			listaHeaderData.add(new ExcelHederDataVO("serieDocRefCom","serieDocRefComprobanteDocModifica"));
			listaHeaderData.add(new ExcelHederDataVO("nroDocRefCompr","nroDocRefComprobanteDocModifica"));
			
			
			List<ExcelHederTitleVO> listaExcelHederTitle = new ArrayList<ExcelHederTitleVO>();
			listaExcelHederTitle.add(new ExcelHederTitleVO(entidad.getNombre(), HorizontalAlignment.LEFT.getCode(), VerticalAlignment.JUSTIFY.getCode(), 1, 1, 6,0,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO(entidad.getCodigoExterno(), HorizontalAlignment.LEFT.getCode(), VerticalAlignment.JUSTIFY.getCode(), 1, 2, 0,0,true));
			
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("REGISTRO DE VENTAS - INGRESOS", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.JUSTIFY.getCode(), 1, 3, 7,0,(short)14,false));
			
			String dataReporte = "EJERCICIO : " + registroVentaFiltroVO.getEjercicio()  +"               PERIODO : " + registroVentaFiltroVO.getPeriodo() + "               RUC : " + entidad.getCodigoExterno() + "               APELLIDOS Y NOMBRES, DENOMINACION O RAZON SOCIAL : " + entidad.getNombre() + "";
			listaExcelHederTitle.add(new ExcelHederTitleVO(dataReporte, HorizontalAlignment.LEFT.getCode(),(short)-1, 1,4, 22,0,true));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("Nº CORRELATIVO DEL REGISTRO O CODIGO UNICO DE LA OPERACION ", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 1,5, 0,3,0,25,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("FECHA DE EMISION DEL COMPROBANTE DE PAGO O DOCUMENTO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 2,5, 0,3,1,22,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("FECHA DE VENCIMIENTO Y/O PAGO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 3,5, 0,3,2,22,true));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("COMPROBANTE DE PAGO O DOCUMENTO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 4,5, 3,0,3,22,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("TIPO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 4,6, 0,2,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("Nº SERIE", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 5,6, 0,2,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("NUMERO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 6,6, 0,2,true));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("INFORMACION DEL CLIENTE", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 7,5, 3,0,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("DOCUMENTO DE IDENTIDAD", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),7, 6, 2,0,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("TIPO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),7, 7, 0,0,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("NUMERO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),8, 7, 0,0,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("APELLIDOS Y NOMBRES, DENOMINACION O RAZON SOCIAL", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),9, 6, 0,2,true));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("VALOR FACTURADO DE LA EXPORTACION", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 10,5, 0,3,9,16,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("BASE IMPONIBLE DE LA OPERACION GRAVADA", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 11,5, 0,3,10,16,true));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("IMPORTE TOTAL DE LA OPERACION EXONERADA O INAFECTA", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 12,5, 2,2,11,20,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("EXONERADA", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),12, 7, 0,0,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("INAFECTA", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),13, 7, 0,0,true));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("ISC", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 14,5, 0,3,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("IGV Y/O IPM", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 15,5, 0,3,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("OTROS TRIBUTOS Y CARGOS QUE NO FORMAN PARTE DE LA BASE IMPONIBLE", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 16,5, 0,3,15,25,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("IMPORTE TOTAL DEL COMPROBANTE DE PAGO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 17,5, 0,3,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("TIPO DE CAMBIO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 18,5, 0,3,true));
			
			listaExcelHederTitle.add(new ExcelHederTitleVO("REFERENCIA DEL COMPROBANTE DE PAGO O DOCUMENTO ORIGINAL QUE SE MODIFICA", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 19,5, 4,2,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("FECHA", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 19,7, 0,0,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("TIPO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 20,7, 0,0,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("SERIE", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 21,7, 0,0,true));
			listaExcelHederTitle.add(new ExcelHederTitleVO("Nº DEL COMPROBANTE DE PAGO O DOCUMENTO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(), 22,7, 0,0,true));
			
			propiedadesMap.put("listaTituloFinal", listaExcelHederTitle);//para crear con esta lista
			
			DataExportExcelPersonalizadoUtil.generarExcelXLSX(listaHeaderData, listaDetalleVenta, archivoName, titulo, propiedadesMap);
		}
		DataExportExcelPersonalizadoUtil.generarExcelXLSXViewMap(archivoName);
		return fileName;
	}
	
	private List<RegistroVentaVO> buscarPaginadoDetalleVenta(List<RegistroVentaVO> listaAsientoContableDet, int cantidadPagina, final RegistroAsientoFiltroVO registroVentaFiltroVO) {
		IDataProvider<RegistroVentaVO> dataProvider = new IDataProvider<RegistroVentaVO>() {
			private int total = 0;
			private int cuenta = 0;
			@Override
			public List<RegistroVentaVO> getBufferedData(int startRow, int offset) {
				List<RegistroVentaVO> lista = new ArrayList<RegistroVentaVO>();
				registroVentaFiltroVO.setStartRow(startRow);
				registroVentaFiltroVO.setOffset(startRow + offset);
				try {
					lista = listarVentaReporte(registroVentaFiltroVO);
				} catch (Exception e) {
					lista = new ArrayList<RegistroVentaVO>();
				}
				return lista;
			}
			@Override
			public int getTotalResultsNumber() {
				if (total == 0 && cuenta == 0) {
					try {
						total = contarListarVentaReporte(registroVentaFiltroVO);
					} catch (Exception e) {
						//e.printStackTrace();
					}					
					cuenta++;
				}
				return total;
			}
		};
		listaAsientoContableDet = new LazyLoadingList<RegistroVentaVO>(dataProvider, cantidadPagina);
		return listaAsientoContableDet;
	}
	private List<RegistroVentaVO> listarVentaReporte(RegistroAsientoFiltroVO registroVentaFiltroVO) throws Exception {
		return  this.ventaDaoImpl.listarVentaReporte(registroVentaFiltroVO);
	}
	private int contarListarVentaReporte(RegistroAsientoFiltroVO registroVentaFiltroVO){
		return  this.ventaDaoImpl.contarListarVentaReporte(registroVentaFiltroVO);
	}
	@Override
	public CategoriaDTO controladorAccionCategoria(CategoriaDTO categoria, AccionType accionType) throws Exception {
		CategoriaDTO resultado = null;
		Categoria resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				categoria.setIdCategoria(this.categoriaDaoImpl.generarIdCategoria());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(categoria, Categoria.class,"linea@PK@","subCategoria@PK@","entidad@PK@");
				this.categoriaDaoImpl.save(resultadoEntity);	
				resultado = categoria;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(categoria, Categoria.class,"linea@PK@","subCategoria@PK@","entidad@PK@");
				this.categoriaDaoImpl.update(resultadoEntity);
				resultado = categoria;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.categoriaDaoImpl.find(Categoria.class, categoria.getIdCategoria());
				this.categoriaDaoImpl.delete(resultadoEntity);
				resultado = categoria;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.categoriaDaoImpl.find(Categoria.class, categoria.getIdCategoria());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,CategoriaDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.categoriaDaoImpl.findByNombre(categoria),CategoriaDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<CategoriaDTO> listarCategoria(CategoriaDTO categoria) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.categoriaDaoImpl.listarCategoria(categoria),CategoriaDTO.class,"linea","subCategoria","entidad");
	}
	@Override
	public int contarListarCategoria(CategoriaDTO categoria){
		return  this.categoriaDaoImpl.contarListarCategoria(categoria);
	}
	@Override
	public ClienteDTO controladorAccionCliente(ClienteDTO cliente, AccionType accionType) throws Exception {
		ClienteDTO resultado = null;
		Cliente resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				cliente.setIdCliente(this.clienteDaoImpl.generarIdCliente());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(cliente, Cliente.class,"entidad@PK@","itemByTipoDocumentoIdentidad@PK@","itemByCategoriaCliente@PK@");
				this.clienteDaoImpl.save(resultadoEntity);	
				resultado = cliente;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(cliente, Cliente.class,"entidad@PK@","itemByTipoDocumentoIdentidad@PK@","itemByCategoriaCliente@PK@");
				this.clienteDaoImpl.update(resultadoEntity);
				resultado = cliente;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.clienteDaoImpl.find(Cliente.class, cliente.getIdCliente());
				this.clienteDaoImpl.delete(resultadoEntity);
				resultado = cliente;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.clienteDaoImpl.find(Cliente.class, cliente.getIdCliente());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,ClienteDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.clienteDaoImpl.findByNombre(cliente),ClienteDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<ClienteDTO> listarCliente(ClienteDTO cliente) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.clienteDaoImpl.listarCliente(cliente),ClienteDTO.class,"itemByTipoDocumentoIdentidad","itemByCategoriaCliente");
	}
	@Override
	public int contarListarCliente(ClienteDTO cliente){
		return  this.clienteDaoImpl.contarListarCliente(cliente);
	}
	@Override
	public ConfiguracionAtributoDTO controladorAccionConfiguracionAtributo(ConfiguracionAtributoDTO configuracionAtributo, AccionType accionType) throws Exception {
		ConfiguracionAtributoDTO resultado = null;
		ConfiguracionAtributo resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				configuracionAtributo.setIdConfiguracionAtributo(this.configuracionAtributoDaoImpl.generarIdConfiguracionAtributo());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(configuracionAtributo, ConfiguracionAtributo.class,"itemByIdNombreEntidad@PK@","itemByIdComponte@PK@","listaItem@PK@");
				this.configuracionAtributoDaoImpl.save(resultadoEntity);	
				resultado = configuracionAtributo;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(configuracionAtributo, ConfiguracionAtributo.class,"itemByIdNombreEntidad@PK@","itemByIdComponte@PK@","listaItem@PK@");
				this.configuracionAtributoDaoImpl.update(resultadoEntity);
				resultado = configuracionAtributo;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.configuracionAtributoDaoImpl.find(ConfiguracionAtributo.class, configuracionAtributo.getIdConfiguracionAtributo());
				this.configuracionAtributoDaoImpl.delete(resultadoEntity);
				resultado = configuracionAtributo;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.configuracionAtributoDaoImpl.find(ConfiguracionAtributo.class, configuracionAtributo.getIdConfiguracionAtributo());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,ConfiguracionAtributoDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.configuracionAtributoDaoImpl.findByNombre(configuracionAtributo),ConfiguracionAtributoDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<ConfiguracionAtributoDTO> listarConfiguracionAtributo(ConfiguracionAtributoDTO configuracionAtributo) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.configuracionAtributoDaoImpl.listarConfiguracionAtributo(configuracionAtributo),ConfiguracionAtributoDTO.class);
	}
	@Override
	public int contarListarConfiguracionAtributo(ConfiguracionAtributoDTO configuracionAtributo){
		return  this.configuracionAtributoDaoImpl.contarListarConfiguracionAtributo(configuracionAtributo);
	}
	@Override
	public ConfiguracionAtributoValueDTO controladorAccionConfiguracionAtributoValue(ConfiguracionAtributoValueDTO configuracionAtributoValue, AccionType accionType) throws Exception {
		ConfiguracionAtributoValueDTO resultado = null;
		ConfiguracionAtributoValue resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				configuracionAtributoValue.setIdConfiguracionAtributoValue(this.configuracionAtributoValueDaoImpl.generarIdConfiguracionAtributoValue());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(configuracionAtributoValue, ConfiguracionAtributoValue.class,"configuracionAtributo@PK@");
				this.configuracionAtributoValueDaoImpl.save(resultadoEntity);	
				resultado = configuracionAtributoValue;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(configuracionAtributoValue, ConfiguracionAtributoValue.class,"configuracionAtributo@PK@");
				this.configuracionAtributoValueDaoImpl.update(resultadoEntity);
				resultado = configuracionAtributoValue;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.configuracionAtributoValueDaoImpl.find(ConfiguracionAtributoValue.class, configuracionAtributoValue.getIdConfiguracionAtributoValue());
				this.configuracionAtributoValueDaoImpl.delete(resultadoEntity);
				resultado = configuracionAtributoValue;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.configuracionAtributoValueDaoImpl.find(ConfiguracionAtributoValue.class, configuracionAtributoValue.getIdConfiguracionAtributoValue());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,ConfiguracionAtributoValueDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.configuracionAtributoValueDaoImpl.findByNombre(configuracionAtributoValue),ConfiguracionAtributoValueDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<ConfiguracionAtributoValueDTO> listarConfiguracionAtributoValue(ConfiguracionAtributoValueDTO configuracionAtributoValue) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.configuracionAtributoValueDaoImpl.listarConfiguracionAtributoValue(configuracionAtributoValue),ConfiguracionAtributoValueDTO.class);
	}
	@Override
	public int contarListarConfiguracionAtributoValue(ConfiguracionAtributoValueDTO configuracionAtributoValue){
		return  this.configuracionAtributoValueDaoImpl.contarListarConfiguracionAtributoValue(configuracionAtributoValue);
	}
	@Override
	public DetallePedidoDTO controladorAccionDetallePedido(DetallePedidoDTO detallePedidoDTO,Pedido pedido, AccionType accionType) throws Exception {
		DetallePedidoDTO resultado = null;
		DetallePedido resultadoEntity = null;
		switch (accionType) {
			case CREAR: 
				detallePedidoDTO.setIdDetallePedido(this.detallePedidoDaoImpl.generarIdDetallePedido());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(detallePedidoDTO, DetallePedido.class,"detalleProducto@PK@","pedido@PK@");
				resultadoEntity.setPedido(pedido);
				this.detallePedidoDaoImpl.save(resultadoEntity);	
				resultado = detallePedidoDTO;
				//this.actualizarStock(detalleVenta,false);
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(detallePedidoDTO, DetallePedido.class,"detalleProducto@PK@","pedido@PK@" );
				resultadoEntity.setPedido(pedido);
				this.detallePedidoDaoImpl.update(resultadoEntity);
				resultado = detallePedidoDTO;	
				//this.actualizarStock(detalleVenta,false);
				break;
				
			case ELIMINAR:
				resultadoEntity = this.detallePedidoDaoImpl.find(DetallePedido.class, detallePedidoDTO.getIdDetallePedido());
				this.detallePedidoDaoImpl.delete(resultadoEntity);
				//this.actualizarStock(detalleVenta,true);
				resultado = detallePedidoDTO;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.detallePedidoDaoImpl.find(DetallePedido.class, detallePedidoDTO.getIdDetallePedido());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,DetallePedidoDTO.class);
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
	public List<DetallePedidoDTO> listarDetallePedido(DetallePedidoDTO detallePedido) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.detallePedidoDaoImpl.listarDetallePedido(detallePedido),DetallePedidoDTO.class,"detalleProducto:{producto;itemByUnidadMedida}","pedido");
	}
	@Override
	public int contarListarDetallePedido(DetallePedidoDTO detallePedido){
		return  this.detallePedidoDaoImpl.contarListarDetallePedido(detallePedido);
	}

	public DetalleProformaDTO controladorAccionDetalleProforma(DetalleProformaDTO detalleProforma, Proforma proforma, AccionType accionType) throws Exception {
		DetalleProformaDTO resultado = null;
		DetalleProforma resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				detalleProforma.setIdDetalleProforma(this.detalleProformaDaoImpl.generarIdDetalleProforma());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(detalleProforma, DetalleProforma.class,"proforma@PK@","detalleProducto@PK@");
				resultadoEntity.setProforma(proforma);
				this.detalleProformaDaoImpl.save(resultadoEntity);	
				resultado = detalleProforma;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(detalleProforma, DetalleProforma.class,"proforma@PK@","detalleProducto@PK@");
				resultadoEntity.setProforma(proforma);
				this.detalleProformaDaoImpl.update(resultadoEntity);
				resultado = detalleProforma;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.detalleProformaDaoImpl.find(DetalleProforma.class, detalleProforma.getIdDetalleProforma());
				this.detalleProformaDaoImpl.delete(resultadoEntity);
				resultado = detalleProforma;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.detalleProformaDaoImpl.find(DetalleProforma.class, detalleProforma.getIdDetalleProforma());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,DetalleProformaDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.detalleProformaDaoImpl.findByNombre(detalleProforma),DetalleProformaDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<DetalleProformaDTO> listarDetalleProforma(DetalleProformaDTO detalleProforma) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.detalleProformaDaoImpl.listarDetalleProforma(detalleProforma),DetalleProformaDTO.class,"producto");
	}
	@Override
	public int contarListarDetalleProforma(DetalleProformaDTO detalleProforma){
		return  this.detalleProformaDaoImpl.contarListarDetalleProforma(detalleProforma);
	}
	
	@Override
	public DetalleVentaDTO controladorAccionDetalleVenta(DetalleVentaDTO detalleVenta,Venta venta, AccionType accionType) throws Exception {
		DetalleVentaDTO resultado = null;
		DetalleVenta resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				detalleVenta.setIdDetalleVenta(this.detalleVentaDaoImpl.generarIdDetalleVenta());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(detalleVenta, DetalleVenta.class,"venta@PK@","detalleProducto@PK@","itemByUnidadMedida@PK@");
				resultadoEntity.setVenta(venta);
				this.detalleVentaDaoImpl.save(resultadoEntity);	
				resultado = detalleVenta;
				this.actualizarStock(detalleVenta,false);
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(detalleVenta, DetalleVenta.class,"venta@PK@","detalleProducto@PK@","itemByUnidadMedida@PK@");
			    resultadoEntity.setVenta(venta);
				this.detalleVentaDaoImpl.update(resultadoEntity);
				resultado = detalleVenta;	
				this.actualizarStock(detalleVenta,false);
				break;
				
			case ELIMINAR:
				resultadoEntity = this.detalleVentaDaoImpl.find(DetalleVenta.class, detalleVenta.getIdDetalleVenta());
				//this.detalleVentaDaoImpl.update(resultadoEntity);
				this.actualizarStock(detalleVenta,true);
				resultado = detalleVenta;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.detalleVentaDaoImpl.find(DetalleVenta.class, detalleVenta.getIdDetalleVenta());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,DetalleVentaDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.detalleVentaDaoImpl.findByNombre(detalleVenta),DetalleVentaDTO .class);
				break;*/
				
			case ELIMINAR_MANUAL:
				resultadoEntity = this.detalleVentaDaoImpl.find(DetalleVenta.class, detalleVenta.getIdDetalleVenta());
				this.detalleVentaDaoImpl.delete(resultadoEntity);
				//this.actualizarStock(detalleVenta,true);
				resultado = detalleVenta;
				break;	
				
			default:
				break;
		}
		
		return resultado;
	}
	private boolean actualizarStock(DetalleVentaDTO detalleVenta, boolean esSumar) {
		Producto producto = this.productoDaoImpl.find(Producto.class, detalleVenta.getDetalleProducto().getProducto().getIdProducto());
		if (producto != null && TipoProductoType.BIEN.getKey().equals(producto.getTipo())) {
			if (!esSumar) { 
				producto.setStock(producto.getStock().subtract(detalleVenta.getCantidad()));
			} else {
				producto.setStock(producto.getStock().add(detalleVenta.getCantidad()));
			}
			this.productoDaoImpl.update(producto);
			return true;
		}
		return false;
	}
	@Override
	public LineaDTO controladorAccionLinea(LineaDTO linea, AccionType accionType) throws Exception {
		LineaDTO resultado = null;
		Linea resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				linea.setIdLinea(this.lineaDaoImpl.generarIdLinea());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(linea, Linea.class,"entidad@PK@");
				this.lineaDaoImpl.save(resultadoEntity);	
				resultado = linea;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(linea, Linea.class,"entidad@PK@");
				this.lineaDaoImpl.update(resultadoEntity);
				resultado = linea;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.lineaDaoImpl.find(Linea.class, linea.getIdLinea());
				this.lineaDaoImpl.delete(resultadoEntity);
				resultado = linea;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.lineaDaoImpl.find(Linea.class, linea.getIdLinea());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,LineaDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.lineaDaoImpl.findByNombre(linea),LineaDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<LineaDTO> listarLinea(LineaDTO linea) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.lineaDaoImpl.listarLinea(linea),LineaDTO.class,"entidad");
	}
	@Override
	public int contarListarLinea(LineaDTO linea){
		return  this.lineaDaoImpl.contarListarLinea(linea);
	}
	@Override
	public MarcaDTO controladorAccionMarca(MarcaDTO marca, AccionType accionType) throws Exception {
		MarcaDTO resultado = null;
		Marca resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				marca.setIdMarca(this.marcaDaoImpl.generarIdMarca());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(marca, Marca.class,"entidad@PK@");
				this.marcaDaoImpl.save(resultadoEntity);	
				resultado = marca;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(marca, Marca.class,"entidad@PK@");
				this.marcaDaoImpl.update(resultadoEntity);
				resultado = marca;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.marcaDaoImpl.find(Marca.class, marca.getIdMarca());
				this.marcaDaoImpl.delete(resultadoEntity);
				resultado = marca;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.marcaDaoImpl.find(Marca.class, marca.getIdMarca());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,MarcaDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.marcaDaoImpl.findByNombre(marca),MarcaDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<MarcaDTO> listarMarca(MarcaDTO marca) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.marcaDaoImpl.listarMarca(marca),MarcaDTO.class,"entidad");
	}
	@Override
	public int contarListarMarca(MarcaDTO marca){
		return  this.marcaDaoImpl.contarListarMarca(marca);
	}
	@Override
	public ModeloDTO controladorAccionModelo(ModeloDTO modelo, AccionType accionType) throws Exception {
		ModeloDTO resultado = null;
		Modelo resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				modelo.setIdModelo(this.modeloDaoImpl.generarIdModelo());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(modelo, Modelo.class,"marca@PK@","subModelo@PK@","entidad@PK@");
				this.modeloDaoImpl.save(resultadoEntity);	
				resultado = modelo;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(modelo, Modelo.class,"marca@PK@","subModelo@PK@","entidad@PK@");
				this.modeloDaoImpl.update(resultadoEntity);
				resultado = modelo;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.modeloDaoImpl.find(Modelo.class, modelo.getIdModelo());
				this.modeloDaoImpl.delete(resultadoEntity);
				resultado = modelo;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.modeloDaoImpl.find(Modelo.class, modelo.getIdModelo());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,ModeloDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.modeloDaoImpl.findByNombre(modelo),ModeloDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<ModeloDTO> listarModelo(ModeloDTO modelo) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.modeloDaoImpl.listarModelo(modelo),ModeloDTO.class,"marca","subModelo","entidad");
	}
	@Override
	public int contarListarModelo(ModeloDTO modelo){
		return  this.modeloDaoImpl.contarListarModelo(modelo);
	}
	@Override
	public PedidoDTO controladorAccionPedido(PedidoDTO pedido, AccionType accionType) throws Exception {
		PedidoDTO resultado = null;
		Pedido resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				pedido.setIdPedido(this.pedidoDaoImpl.generarIdPedido());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(pedido, Pedido.class,"entidad@PK@","cliente@PK@","tipoDocSunat@PK@","itemByTipoMoneda@PK@");
				this.pedidoDaoImpl.save(resultadoEntity);	
				resultado = pedido;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(pedido, Pedido.class,"entidad@PK@","cliente@PK@","tipoDocSunat@PK@","itemByTipoMoneda@PK@");
				this.pedidoDaoImpl.update(resultadoEntity);
				resultado = pedido;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.pedidoDaoImpl.find(Pedido.class, pedido.getIdPedido());
				this.pedidoDaoImpl.delete(resultadoEntity);
				resultado = pedido;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.pedidoDaoImpl.find(Pedido.class, pedido.getIdPedido());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,PedidoDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.pedidoDaoImpl.findByNombre(pedido),PedidoDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<PedidoDTO> listarPedido(PedidoDTO pedido) throws Exception {		
		List<Pedido> listaPedido = pedidoDaoImpl.listarPedido(pedido);
		List<PedidoDTO> listaComp = new ArrayList<PedidoDTO>(); 
		for(Pedido objPedido : listaPedido ) { 
			PedidoDTO pedidoDTO = TransferDataObjectUtil.transferObjetoEntityDTO(objPedido,PedidoDTO.class,"cliente","tipoDocSunat","itemByTipoMoneda","entidad");
			DetallePedidoDTO detallePedido = new DetallePedidoDTO();
			detallePedido.setId(objPedido.getIdPedido());
			List<DetallePedidoDTO> pedidoDetallePedidoList = listarDetallePedido(detallePedido);
			pedidoDTO.setPedidoDetallePedidoList(pedidoDetallePedidoList);
			listaComp.add(pedidoDTO);
		}
		listaPedido =  null;
		return listaComp;		
	}
	@Override
	public int contarListarPedido(PedidoDTO pedido){
		return  this.pedidoDaoImpl.contarListarPedido(pedido);
	}
	@Override
	public ProductoDTO controladorAccionProducto(ProductoDTO producto, AccionType accionType) throws Exception {
		ProductoDTO resultado = null;
		Producto resultadoEntity = null;		
		switch (accionType) {
			case CREAR:
				producto.setIdProducto(this.productoDaoImpl.generarIdProducto());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(producto, Producto.class,"categoria@PK@","itemByUnidadMedida@PK@","modelo@PK@","itemByColor@PK@","itemByTalla@PK@","planContableVenta@PK@","planContableCompra@PK@","entidad@PK@");
				this.productoDaoImpl.save(resultadoEntity);	
				resultado = producto;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(producto, Producto.class,"categoria@PK@","itemByUnidadMedida@PK@","modelo@PK@","itemByColor@PK@","itemByTalla@PK@","planContableVenta@PK@","planContableCompra@PK@","entidad@PK@");
				this.productoDaoImpl.update(resultadoEntity);
				resultado = producto;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.productoDaoImpl.find(Producto.class, producto.getIdProducto());	
				List<DetalleProductoDTO> listadoDetProducto = listarDetalleProductoBy(producto.getIdProducto());
				for (DetalleProductoDTO dProDTO : listadoDetProducto) {					
					controladorAccionDetalleProducto(dProDTO,resultadoEntity,AccionType.ELIMINAR);
				}								
				this.productoDaoImpl.delete(resultadoEntity);
				resultado = producto;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.productoDaoImpl.find(Producto.class, producto.getIdProducto());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,ProductoDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.productoDaoImpl.findByNombre(producto),ProductoDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<ProductoDTO> listarProducto(ProductoDTO producto) throws Exception {
		//return TransferDataObjectUtil.transferObjetoEntityDTOList(this.productoDaoImpl.listarProducto(producto),ProductoDTO.class,"categoria","itemByUnidadMedida","modelo","itemByColor","itemByTalla","planContableVenta","planContableCompra");
		List<Producto> listaProd = productoDaoImpl.listarProducto(producto);
		List<ProductoDTO> listaProducto = new ArrayList<ProductoDTO>();
		for(Producto productoObj : listaProd ) {
			ProductoDTO productoDTO = TransferDataObjectUtil.transferObjetoEntityDTO(productoObj,ProductoDTO.class,"categoria","itemByUnidadMedida","modelo:{marca}","itemByColor","itemByTalla","planContableVenta","planContableCompra");
			FileVO fileVO = new FileVO();
			fileVO.setRuta(ConstanteConfigUtil.RUTA_RECURSOS_FOTO_ALUMN + ConstanteConfigUtil.SEPARADOR_FILE + "P-" +  productoObj.getFoto());
			productoDTO.setFoto(commonServiceLocal.obtenerImagenEncodeBase64(fileVO));
			List<DetalleProductoDTO> listadoDetProducto = listarDetalleProductoBy(productoObj.getIdProducto());
			productoDTO.setDetalleProductoList(listadoDetProducto); 
			listaProducto.add(productoDTO);
		}
		listaProd =  null;
		return listaProducto;
	}
	@Override
	public int contarListarProducto(ProductoDTO producto){
		return  this.productoDaoImpl.contarListarProducto(producto);
	}
	/*@Override
	public ProformaDTO controladorAccionProforma(ProformaDTO proforma, AccionType accionType) throws Exception {
		ProformaDTO resultado = null;
		Proforma resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				proforma.setIdProforma(this.proformaDaoImpl.generarIdProforma());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(proforma, Proforma.class,"entidad@PK@","cliente@PK@","tipoDocSunat@PK@","itemByTipoMoneda@PK@");
				this.proformaDaoImpl.save(resultadoEntity);	
				resultado = proforma;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(proforma, Proforma.class,"entidad@PK@","cliente@PK@","tipoDocSunat@PK@","itemByTipoMoneda@PK@");
				this.proformaDaoImpl.update(resultadoEntity);
				resultado = proforma;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.proformaDaoImpl.find(Proforma.class, proforma.getIdProforma());
				this.proformaDaoImpl.delete(resultadoEntity);
				resultado = proforma;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.proformaDaoImpl.find(Proforma.class, proforma.getIdProforma());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,ProformaDTO.class);
				break;
				
			case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.proformaDaoImpl.findByNombre(proforma),ProformaDTO .class);
				break;
				
			default:
				break;
		}
		
		return resultado;
	}*/
	@Override
	public List<ProformaDTO> listarProforma(ProformaDTO proforma) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.proformaDaoImpl.listarProforma(proforma),ProformaDTO.class,"cliente","tipoDocSunat","itemByTipoMoneda");
	}
	@Override
	public int contarListarProforma(ProformaDTO proforma){
		return  this.proformaDaoImpl.contarListarProforma(proforma);
	}
	@Override
	public TipoDocSunatEntidadDTO controladorAccionTipoDocSunatEntidad(TipoDocSunatEntidadDTO tipoDocSunatEntidad, AccionType accionType) throws Exception {
		TipoDocSunatEntidadDTO resultado = null;
		TipoDocSunatEntidad resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				tipoDocSunatEntidad.setIdTipoDocSunatEntidad(this.tipoDocSunatEntidadDaoImpl.generarIdTipoDocSunatEntidad());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(tipoDocSunatEntidad, TipoDocSunatEntidad.class,"itemByTipoDocSunat@PK@","entidad@PK@");
				this.tipoDocSunatEntidadDaoImpl.save(resultadoEntity);	
				resultado = tipoDocSunatEntidad;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(tipoDocSunatEntidad, TipoDocSunatEntidad.class,"itemByTipoDocSunat@PK@","entidad@PK@");
				this.tipoDocSunatEntidadDaoImpl.update(resultadoEntity);
				resultado = tipoDocSunatEntidad;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.tipoDocSunatEntidadDaoImpl.find(TipoDocSunatEntidad.class, tipoDocSunatEntidad.getIdTipoDocSunatEntidad());
				this.tipoDocSunatEntidadDaoImpl.delete(resultadoEntity);
				resultado = tipoDocSunatEntidad;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.tipoDocSunatEntidadDaoImpl.find(TipoDocSunatEntidad.class, tipoDocSunatEntidad.getIdTipoDocSunatEntidad());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,TipoDocSunatEntidadDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.tipoDocSunatEntidadDaoImpl.findByNombre(tipoDocSunatEntidad),TipoDocSunatEntidadDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<TipoDocSunatEntidadDTO> listarTipoDocSunatEntidad(TipoDocSunatEntidadDTO tipoDocSunatEntidad) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.tipoDocSunatEntidadDaoImpl.listarTipoDocSunatEntidad(tipoDocSunatEntidad),TipoDocSunatEntidadDTO.class,"itemByTipoDocSunat","entidad");
	}
	@Override
	public int contarListarTipoDocSunatEntidad(TipoDocSunatEntidadDTO tipoDocSunatEntidad){
		return  this.tipoDocSunatEntidadDaoImpl.contarListarTipoDocSunatEntidad(tipoDocSunatEntidad);
	}
	@Override
	public VentaDTO registrarVenta(VentaDTO venta) throws Exception {
		VentaDTO resultado = null;
		Venta resultadoEntity = null;
		String userName = AppAuthenticator.getInstance().getUserName(venta.getAuthToken());
		venta.setIdEntidadSelect(venta.getEntidad().getIdEntidad());

		
		if (!StringUtils.isNotNullOrBlank(venta.getIdVenta())) {
			if (StringUtils.isNotNullOrBlank(venta.getTipoDocSunat().getIdItem())) {
				String nroDocCalc = tipoDocSunatEntidadDaoImpl.actualizarTipoDocSunat(venta.getTipoDocSunat().getIdItem(),venta.getIdEntidadSelect(),venta.getNroDoc(),venta.getSerie());
				if (!StringUtils.isNotNullOrBlank(venta.getNroDoc())) {
					//generar el nro doc
					venta.setNroDoc(nroDocCalc);
				}
			}
			venta.setFechaCreacion(FechaUtil.obtenerFecha());
			venta.setFechaVenta(FechaUtil.obtenerFecha());
			venta.setUsuarioCreacion(userName);
			if(!StringUtils.isNullOrEmpty(venta.getIdPadreView())) {
				venta.setEstado(EstadoGeneralState.CANCELADO.getKey());
				venta.setEstadoVenta("V");
			}else {
				venta.setEstado(EstadoGeneralState.PENDIENTE.getKey());
			}

			venta.setIdVenta(this.ventaDaoImpl.generarIdVenta());
			resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(venta, Venta.class,"entidad@PK@","pedido@PK@","cliente@PK@","tipoDocSunat@PK@","itemByTipoMoneda@PK@");
			this.ventaDaoImpl.save(resultadoEntity);
			//String nroCorrelativoOperacion = registrarAsientoContable(venta, userName);
			//resultadoEntity.setNroCorrelativoOperacion(nroCorrelativoOperacion);  PARA NO CREAR ASIENTOS CONABLES
			resultado = venta;
		} else {
			resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(venta, Venta.class,"entidad@PK@","pedido@PK@","cliente@PK@","tipoDocSunat@PK@","itemByTipoMoneda@PK@");
			resultadoEntity.setEstado(EstadoGeneralState.CANCELADO.getKey());
			resultadoEntity.setEstadoVenta(EstadoGeneralState.VENDIDO.getKey());
			this.ventaDaoImpl.update(resultadoEntity);
			resultado = venta;	
			//resultadoEntity = this.ventaDaoImpl.find(Venta.class, venta.getIdVenta());
		}
		
		if(StringUtils.isNotNullOrBlank(venta.getPedido().getIdPedido())) {	
			if(venta.getPedido().getEstado().equals(EstadoGeneralState.ACTIVO.getKey())) {
				venta.getPedido().setEstado(EstadoGeneralState.INACTIVO.getKey());
				controladorAccionPedido(venta.getPedido(),AccionType.MODIFICAR);
				EntregaDTO entregaDTO = new EntregaDTO();				
				entregaDTO.setPedido(venta.getPedido());
				entregaDTO.getPedido().setIdPedido(venta.getPedido().getIdPedido());
				entregaDTO.setEstadoEntrega("PE");
				controladorAccionEntrega(entregaDTO, AccionType.CREAR);
			}
			
		}
		
		if (!CollectionUtil.isEmpty(venta.getVentaDetalleVentaList())) {
			for (DetalleVentaDTO detalleVenta : venta.getVentaDetalleVentaList()) {
				if (!detalleVenta.isEsEliminado()) {
					if (StringUtils.isNullOrEmpty(detalleVenta.getIdDetalleVenta())) {
						controladorAccionDetalleVenta(detalleVenta,resultadoEntity,AccionType.CREAR);
						System.out.println("Crando Detallesde la Venta:::: ");
					} else {
						controladorAccionDetalleVenta(detalleVenta,resultadoEntity,AccionType.MODIFICAR);
						System.out.println("Modificando Detallesde la Venta:::: ");
					}
					
				} else {
					controladorAccionDetalleVenta(detalleVenta,resultadoEntity, AccionType.ELIMINAR);
				}
			}
		}
		
		return resultado;
	}
	
	
	@Override
	public VentaDTO registrarVentaManual(VentaDTO venta) throws Exception {
		VentaDTO resultado = null;
		Venta resultadoEntity = null;
		String userName = AppAuthenticator.getInstance().getUserName(venta.getAuthToken());
		venta.setIdEntidadSelect(venta.getEntidad().getIdEntidad());

		
		if (!StringUtils.isNotNullOrBlank(venta.getIdVenta())) {
			if (StringUtils.isNotNullOrBlank(venta.getTipoDocSunat().getIdItem())) {
				String nroDocCalc = venta.getNroDoc();
				if (!StringUtils.isNotNullOrBlank(venta.getNroDoc())) {
					//generar el nro doc
					venta.setNroDoc(nroDocCalc);
				}
			}
			venta.setFechaCreacion(FechaUtil.obtenerFecha());
			venta.setFechaVenta(FechaUtil.obtenerFecha());
			venta.setUsuarioCreacion(userName);
			venta.setEstado(EstadoGeneralState.PENDIENTE.getKey());
			venta.setIdVenta(this.ventaDaoImpl.generarIdVenta());
			resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(venta, Venta.class,"entidad@PK@","pedido@PK@","cliente@PK@","tipoDocSunat@PK@","itemByTipoMoneda@PK@");
			this.ventaDaoImpl.save(resultadoEntity);
			//String nroCorrelativoOperacion = registrarAsientoContable(venta, userName);
			//resultadoEntity.setNroCorrelativoOperacion(nroCorrelativoOperacion);  PARA NO CREAR ASIENTOS CONABLES
			resultado = venta;
		} else {
			resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(venta, Venta.class,"entidad@PK@","pedido@PK@","cliente@PK@","tipoDocSunat@PK@","itemByTipoMoneda@PK@");					
			this.ventaDaoImpl.update(resultadoEntity);
			resultado = venta;	
			//resultadoEntity = this.ventaDaoImpl.find(Venta.class, venta.getIdVenta());
		}
		
		if(StringUtils.isNotNullOrBlank(venta.getPedido().getIdPedido())) {	
			if(venta.getPedido().getEstado().equals(EstadoGeneralState.ACTIVO.getKey())) {
				venta.getPedido().setEstado(EstadoGeneralState.INACTIVO.getKey());
				controladorAccionPedido(venta.getPedido(),AccionType.MODIFICAR);
				EntregaDTO entregaDTO = new EntregaDTO();				
				entregaDTO.setPedido(venta.getPedido());
				entregaDTO.getPedido().setIdPedido(venta.getPedido().getIdPedido());
				controladorAccionEntrega(entregaDTO, AccionType.CREAR);
			}
			
		}
		
		if (!CollectionUtil.isEmpty(venta.getVentaDetalleVentaList())) {
			for (DetalleVentaDTO detalleVenta : venta.getVentaDetalleVentaList()) {
				if (!detalleVenta.isEsEliminado()) {
					if (StringUtils.isNullOrEmpty(detalleVenta.getIdDetalleVenta())) {
						controladorAccionDetalleVenta(detalleVenta,resultadoEntity,AccionType.CREAR);
						System.out.println("Crando Detallesde la Venta:::: ");
					} else {
						controladorAccionDetalleVenta(detalleVenta,resultadoEntity,AccionType.MODIFICAR);
						System.out.println("Modificando Detallesde la Venta:::: ");
					}
					
				} else {
					controladorAccionDetalleVenta(detalleVenta,resultadoEntity, AccionType.ELIMINAR);
				}
			}
		}
		
		return resultado;
	}
	
	
	
	
	@Override
	public ProformaDTO registrarProforma(ProformaDTO proforma) throws Exception {
		ProformaDTO resultado = null;
		Proforma resultadoEntity = null;
		String userName = AppAuthenticator.getInstance().getUserName(proforma.getAuthToken());
		proforma.setIdEntidadSelect(proforma.getEntidad().getIdEntidad());

		
		if (!StringUtils.isNotNullOrBlank(proforma.getIdProforma())) {
			if (StringUtils.isNotNullOrBlank(proforma.getTipoDocSunat().getIdItem())) {
				String nroDocCalc = tipoDocSunatEntidadDaoImpl.actualizarTipoDocSunat(proforma.getTipoDocSunat().getIdItem(),proforma.getIdEntidadSelect(),proforma.getNroDoc(),proforma.getSerie());
				if (!StringUtils.isNotNullOrBlank(proforma.getNroDoc())) {
					//generar el nro doc
					proforma.setNroDoc(nroDocCalc);
				}
			}
			proforma.setFechaCreacion(FechaUtil.obtenerFecha());
			proforma.setFechaProforma(FechaUtil.obtenerFecha());
			proforma.setUsuarioCreacion(userName);
			proforma.setEstado(EstadoGeneralState.ACTIVO.getKey());
			proforma.setIdProforma(this.proformaDaoImpl.generarIdProforma());
			resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(proforma, Proforma.class,"entidad@PK@","cliente@PK@","tipoDocSunat@PK@","itemByTipoMoneda@PK@");
			this.proformaDaoImpl.save(resultadoEntity);												
			resultado = proforma;
		} else {
			resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(proforma, Proforma.class,"entidad@PK@","cliente@PK@","tipoDocSunat@PK@","itemByTipoMoneda@PK@");
			resultadoEntity.setEstado(EstadoGeneralState.INACTIVO.getKey());
			this.proformaDaoImpl.update(resultadoEntity);
			resultado = proforma;	
			//resultadoEntity = this.ventaDaoImpl.find(Venta.class, venta.getIdVenta());
		}
		if (!CollectionUtil.isEmpty(proforma.getProformaDetalleProformaList())) {
			for (DetalleProformaDTO  detalleProforma : proforma.getProformaDetalleProformaList()) {
				if (!detalleProforma.isEsEliminado()) {
					if (StringUtils.isNullOrEmpty(detalleProforma.getIdDetalleProforma())) {
						controladorAccionDetalleProforma(detalleProforma,resultadoEntity,AccionType.CREAR);
						System.out.println("Crando Detallesde la Proforma:::: ");
					} else {
						controladorAccionDetalleProforma(detalleProforma,resultadoEntity,AccionType.MODIFICAR);
						System.out.println("Modificando Detallesde la Proforma:::: ");
					}
					
				} else {
					controladorAccionDetalleProforma(detalleProforma,resultadoEntity, AccionType.ELIMINAR);
				}
			}
		}
		
		return resultado;
	}
	
	
	
	
	
	
	private String registrarAsientoContable(VentaDTO venta,String userName) throws Exception {
		String resultado = "";
		AsientoContableDTO asientoContable = new AsientoContableDTO();
		asientoContable.setIdEntidadSelect(venta.getIdEntidadSelect());
		asientoContable.setEntidad(new EntidadDTO());
		asientoContable.getEntidad().setIdEntidad(venta.getIdEntidadSelect());
		//asientoContable.setIdAsientoContable();
		String keyLibro =  "49" + "" + "5";
		String keySubLibro =  "49" + "" + "14";
		asientoContable.setItemByLibro(SelectItemServiceCacheUtil.getInstance().obtenerItemByCodigo(keyLibro));
		asientoContable.setItemBySubLibro(SelectItemServiceCacheUtil.getInstance().obtenerItemByCodigo(keySubLibro));
		//asientoContable.setNroCorrelativoAsiento(0L);//Generar en el servicio
		asientoContable.setFechaOperacion(venta.getFechaVenta());//TODO:VER_NATAN_CONTABILIDAD
		String glosa = "Provision de la venta {nuroDocumento}, {cliente}";
		glosa = glosa.replace("{nuroDocumento}", venta.getNroDoc());
		glosa = glosa.replace("{cliente}", (venta.getCliente().getNombre() + " " + venta.getCliente().getApellidoPaterno() + " " + venta.getCliente().getApellidoPaterno()).trim());
		asientoContable.setGlosa(glosa);//TODO:VER_NATAN Pasar al parametro
		//asientoContable.setNroCorrelativoOperacion(controlPago.getNroCorrelativoOperacion());//Auto genere en base a libro y sub libro
		asientoContable.setNroDocumentoOperacion(venta.getNroDoc());
		asientoContable.setIdOperacion(venta.getIdVenta() + "");
		asientoContable.setFechaCreacion(FechaUtil.obtenerFecha());
		asientoContable.setUsuarioCreacion(venta.getUsuarioCreacion());
		asientoContable.setFechaModificacion(null);
		asientoContable.setUsuarioModificacion(null);
		asientoContable.setIpAcceso(venta.getIpAcceso());
		//detalle
		boolean isFlagAplicaIGV = false;
		for (DetalleVentaDTO detalleVentaDTO : venta.getVentaDetalleVentaList()) {//TODO:Mejorar esto
				if (RespuestaNaturalType.SI.getKey().toString().equals(detalleVentaDTO.getDetalleProducto().getProducto().getEsAfectoIGV())) {
					isFlagAplicaIGV = true;
					break;
				}
		}
		//Cuenta libro venta 14 ==> cuenta 12
		String keyCuenta =  venta.getIdEntidadSelect() + "14";
		ConfiguracionCuentaDTO configuracionCuentaDTO = ContabilidadCacheUtil.getInstance().getConfiguracionCuentaMap().get(keyCuenta);
		AsientoContableDetDTO asientoContableDet = new AsientoContableDetDTO();
		asientoContableDet.setPlanContable(configuracionCuentaDTO.getPlanContable());
		asientoContableDet.setMonto(venta.getMontoTotal());
		asientoContableDet.setTipo(TipoMovimientoType.DEBE.getKey());
		asientoContable.getAsientoContableAsientoContableDetList().add(asientoContableDet);
        // cuenta 40
		String porcentajeIGV = "";
		if (isFlagAplicaIGV) {
			String procentajeIgvKey = venta.getIdEntidadSelect() +  ConstanteCommonUtil.PARAMETRO_IGV;
			porcentajeIGV = ParametroCacheUtil.getInstance().getParamtroMap().get(procentajeIgvKey) + "";			
			
		}
		//cuenta 70
		BigDecimal montoIGV = BigDecimal.ZERO;
		for (DetalleVentaDTO detalleVentaDTO : venta.getVentaDetalleVentaList()) {//TODO:Mejorar esto
			 String keyCuentaVenta =  detalleVentaDTO.getDetalleProducto().getProducto().getPlanContableVenta().getIdPlanContable();
			 PlanContableDTO planContableDTO = ContabilidadCacheUtil.getInstance().getPlanContableMap().get(keyCuentaVenta);
			 asientoContableDet = new AsientoContableDetDTO();
			 asientoContableDet.setPlanContable(planContableDTO);
			 asientoContableDet.setMonto(detalleVentaDTO.getMontoTotal());
			 asientoContableDet.setTipo(TipoMovimientoType.HABER.getKey());
			 asientoContable.getAsientoContableAsientoContableDetList().add(asientoContableDet);
			 
			 if (RespuestaNaturalType.SI.getKey().toString().equals(detalleVentaDTO.getDetalleProducto().getProducto().getEsAfectoIGV())) {
				 montoIGV = montoIGV.add(detalleVentaDTO.getMontoTotal().multiply(ObjectUtil.objectToBigDecimal(porcentajeIGV)));
			 }
		}
		if (isFlagAplicaIGV) {			
			String igvCuentaAsignadaKey = venta.getIdEntidadSelect() +  ConstanteCommonUtil.PARAMETRO_IGV_CUENTA_ASIGANDA;
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
	public List<VentaDTO> listarVenta(VentaDTO venta) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.ventaDaoImpl.listarVenta(venta),VentaDTO.class,"tipoDocSunat","itemByTipoMoneda","cliente","pedido");
	}
	@Override
	public int contarListarVenta(VentaDTO venta){
		return  this.ventaDaoImpl.contarListarVenta(venta);
	}
	
	
	//add
		@Override
		public String generarReporteVenta(VentaDTO venta,String usuario) throws Exception {
			//String fileName = UUIDUtil.generarElementUUID();
			String fileName = venta.getSerie() +"-"+venta.getNroDoc();
			String codigoGeneradoReporte = venta.getSerie() +" - "+venta.getNroDoc();
			UsuarioDTO resultado = null;
			Usuario resultadoEntity = null;
			try {
				ClienteDTO cliente = new ClienteDTO();
				cliente.setIdCliente(venta.getCliente().getIdCliente());
				cliente = this.controladorAccionCliente(cliente, AccionType.FIND_BY_ID);
				Map<String, Object> parametros = new HashMap<String, Object>();
				String[] subreportes;
				subreportes = new String[0];	
				parametros.put("nombreCliente", cliente.getNombre() + " " +  cliente.getApellidoPaterno() + " " + cliente.getApellidoMaterno());
				parametros.put("ruta", "");
				parametros.put("nroDocCli", cliente.getNroDoc());
				parametros.put("direccion", cliente.getDireccion());
				
				List<VentaDTO> listaVentaGenerar = new ArrayList<VentaDTO>();
				VentaDTO ventaReporte = new VentaDTO();
				ventaReporte.setIdVenta(venta.getIdVenta()); 
				ventaReporte = this.listarVenta(ventaReporte).get(0);
				if (ventaReporte.getCliente() == null || ventaReporte.getCliente().getIdCliente() == null) {
					ventaReporte.setCliente(new ClienteDTO());
				}
				parametros.put("nroDoc", ventaReporte.getNroDoc());
				resultadoEntity = this.usuarioServiceImpl.findUsuario(ventaReporte.getUsuarioCreacion());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,UsuarioDTO.class);
				parametros.put("nombreCajero", resultado.getNombre()+ " " + resultado.getApellidoPaterno()+ " " +resultado.getApellidoMaterno());
				NumerosUtil numLetra= new NumerosUtil();
				parametros.put("montoLetra", numLetra.Convertir(ventaReporte.getMontoTotal().toString(), true));
				ventaReporte.setCliente(cliente);
				DetalleVentaDTO det = new DetalleVentaDTO();
				det.setId(ventaReporte.getIdVenta());
				List<DetalleVentaDTO> listaDetVenta = listarDetalleVenta(det);
				Map<BigDecimal, DetalleVentaDTO> montoContar1 = new HashMap<BigDecimal, DetalleVentaDTO>();
				BigDecimal montoContar =new BigDecimal("0");
				DetalleVentaDTO detVenta = new DetalleVentaDTO();
				List<DetalleVentaDTO> lisDet= new ArrayList<DetalleVentaDTO>();
				for(DetalleVentaDTO obj : listaDetVenta) { 
					if(obj.getEsCheck() == true) {
						montoContar = montoContar.add(obj.getMontoTotal());
					}else{
						montoContar1.putIfAbsent(obj.getMontoTotal(), obj);
					}
				}
				
				if(montoContar != new BigDecimal("0")) {
					for (Map.Entry<BigDecimal,DetalleVentaDTO> entryMap : montoContar1.entrySet()) {
						detVenta = entryMap.getValue();
						detVenta.setMontoTotal(detVenta.getMontoTotal().add(montoContar));
						detVenta.setPrecio(detVenta.getPrecio().add(montoContar));
					}
					lisDet.add(detVenta);
				}

				
				if (CollectionUtil.isEmpty(lisDet)) {
					ventaReporte.setVentaDetalleVentaList(lisDet);
				}else {
					ventaReporte.setVentaDetalleVentaList(listaDetVenta);
				}
			

				listaVentaGenerar.add(ventaReporte);
				NombreReporteType reporte = NombreReporteType.JR_REP_BOLETA_VENTA;
				ParametroReporteVO parametroReporteVO = new ParametroReporteVO(parametros, listaVentaGenerar, reporte, subreportes, null, true, "", "");
				parametroReporteVO.setFormato(TipoReporteGenerarType.PDF.getKey());
				parametroReporteVO.setUserName(usuario);
				parametroReporteVO.setFileName(fileName);
				codigoGeneradoReporte = generarReporteServiceImpl.obtenerParametroReporteBigMemory(parametroReporteVO);
				//System.out.println("PDF:: " +codigoGeneradoReporte);
				Utilidades.imprimirFactura(codigoGeneradoReporte,usuario,venta);
				return codigoGeneradoReporte;
			} catch (Exception e) {
				log.error(e);
			}
			return codigoGeneradoReporte;
		}
		
		
		@Override
		public String generarReporteVentaBase64(VentaDTO venta,String usuario) throws Exception {
			//String fileName = UUIDUtil.generarElementUUID();
			String fileName = venta.getSerie() +"-"+venta.getNroDoc();
			String codigoGeneradoReporte = venta.getSerie() +" - "+venta.getNroDoc();
			UsuarioDTO resultado = null;
			Usuario resultadoEntity = null;
			try {
				ClienteDTO cliente = new ClienteDTO();
				cliente.setIdCliente(venta.getCliente().getIdCliente());
				cliente = this.controladorAccionCliente(cliente, AccionType.FIND_BY_ID);
				Map<String, Object> parametros = new HashMap<String, Object>();
				String[] subreportes;
				subreportes = new String[0];	
				parametros.put("nombreCliente", cliente.getNombre() + " " +  cliente.getApellidoPaterno() + " " + cliente.getApellidoMaterno());
				parametros.put("ruta", "");
				parametros.put("nroDocCli", cliente.getNroDoc());
				parametros.put("direccion", cliente.getDireccion());
				
				List<VentaDTO> listaVentaGenerar = new ArrayList<VentaDTO>();
				VentaDTO ventaReporte = new VentaDTO();
				ventaReporte.setIdVenta(venta.getIdVenta()); 
				ventaReporte = this.listarVenta(ventaReporte).get(0);
				if (ventaReporte.getCliente() == null || ventaReporte.getCliente().getIdCliente() == null) {
					ventaReporte.setCliente(new ClienteDTO());
				}
				parametros.put("nroDoc", ventaReporte.getNroDoc());
				resultadoEntity = this.usuarioServiceImpl.findUsuario(ventaReporte.getUsuarioCreacion());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,UsuarioDTO.class);
				parametros.put("nombreCajero", resultado.getNombre()+ " " + resultado.getApellidoPaterno()+ " " +resultado.getApellidoMaterno());
				NumerosUtil numLetra= new NumerosUtil();
				parametros.put("montoLetra", numLetra.Convertir(ventaReporte.getMontoTotal().toString(), true));
				ventaReporte.setCliente(cliente);
				DetalleVentaDTO det = new DetalleVentaDTO();
				det.setId(ventaReporte.getIdVenta());
				List<DetalleVentaDTO> listaDetVenta = listarDetalleVenta(det);
				Map<BigDecimal, DetalleVentaDTO> montoContar1 = new HashMap<BigDecimal, DetalleVentaDTO>();
				BigDecimal montoContar =new BigDecimal("0");
				DetalleVentaDTO detVenta = new DetalleVentaDTO();
				List<DetalleVentaDTO> lisDet= new ArrayList<DetalleVentaDTO>();
				for(DetalleVentaDTO obj : listaDetVenta) { 
					if(obj.getEsCheck() == true) {
						montoContar = montoContar.add(obj.getMontoTotal());
					}else{
						montoContar1.putIfAbsent(obj.getMontoTotal(), obj);
					}
				}
				
				if(montoContar != new BigDecimal("0")) {
					for (Map.Entry<BigDecimal,DetalleVentaDTO> entryMap : montoContar1.entrySet()) {
						detVenta = entryMap.getValue();
						detVenta.setMontoTotal(detVenta.getMontoTotal().add(montoContar));
						detVenta.setPrecio(detVenta.getPrecio().add(montoContar));
					}
					lisDet.add(detVenta);
				}

				
				if (CollectionUtil.isEmpty(lisDet)) {
					ventaReporte.setVentaDetalleVentaList(lisDet);
				}else {
					ventaReporte.setVentaDetalleVentaList(listaDetVenta);
				}
			

				listaVentaGenerar.add(ventaReporte);
				NombreReporteType reporte = NombreReporteType.JR_REP_BOLETA_VENTA;
				ParametroReporteVO parametroReporteVO = new ParametroReporteVO(parametros, listaVentaGenerar, reporte, subreportes, null, true, "", "");
				parametroReporteVO.setFormato(TipoReporteGenerarType.PDF.getKey());
				parametroReporteVO.setUserName(usuario);
				parametroReporteVO.setFileName(fileName);
				codigoGeneradoReporte = generarReporteServiceImpl.obtenerParametroReporteBigMemory(parametroReporteVO);
				FileVO fileVO = new FileVO();
				fileVO.setRuta(ConstanteConfigUtil.RUTA_RECURSOS_BYTE_BUFFER + ConstanteConfigUtil.generarRuta(usuario) + codigoGeneradoReporte+ ".pdf");
				codigoGeneradoReporte = commonServiceLocal.obtenerImagenEncodeBase64(fileVO);
			} catch (Exception e) {
				log.error(e);
			}
			return codigoGeneradoReporte;
		}
		
		
		@Override
		public String generarReporteVentaA4(VentaDTO venta,String usuario) throws Exception {
			//String fileName = UUIDUtil.generarElementUUID();
			String fileName = venta.getSerie() +"-"+venta.getNroDoc();
			String codigoGeneradoReporte = venta.getSerie() +" - "+venta.getNroDoc();
			UsuarioDTO resultado = null;
			Usuario resultadoEntity = null;
			try {
				ClienteDTO cliente = new ClienteDTO();
				cliente.setIdCliente(venta.getCliente().getIdCliente());
				cliente = this.controladorAccionCliente(cliente, AccionType.FIND_BY_ID);
				Map<String, Object> parametros = new HashMap<String, Object>();
				String[] subreportes;
				subreportes = new String[0];	
				parametros.put("nombreCliente", cliente.getNombre() + " " +  cliente.getApellidoPaterno() + " " + cliente.getApellidoMaterno());
				parametros.put("ruta", "");
				parametros.put("nroDocCli", cliente.getNroDoc());
				parametros.put("direccion", cliente.getDireccion());
				
				List<VentaDTO> listaVentaGenerar = new ArrayList<VentaDTO>();
				VentaDTO ventaReporte = new VentaDTO();
				ventaReporte.setIdVenta(venta.getIdVenta()); 
				ventaReporte = this.listarVenta(ventaReporte).get(0);
				if (ventaReporte.getCliente() == null || ventaReporte.getCliente().getIdCliente() == null) {
					ventaReporte.setCliente(new ClienteDTO());
				}
				parametros.put("nroDoc", ventaReporte.getNroDoc());
				resultadoEntity = this.usuarioServiceImpl.findUsuario(ventaReporte.getUsuarioCreacion());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,UsuarioDTO.class);
				parametros.put("nombreCajero", resultado.getNombre()+ " " + resultado.getApellidoPaterno()+ " " +resultado.getApellidoMaterno());
				NumerosUtil numLetra= new NumerosUtil();
				parametros.put("montoLetra", numLetra.Convertir(ventaReporte.getMontoTotal().toString(), true));
				ventaReporte.setCliente(cliente);
				DetalleVentaDTO det = new DetalleVentaDTO();
				det.setId(ventaReporte.getIdVenta());
				List<DetalleVentaDTO> listaDetVenta = listarDetalleVenta(det);
				Map<BigDecimal, DetalleVentaDTO> montoContar1 = new HashMap<BigDecimal, DetalleVentaDTO>();
				BigDecimal montoContar =new BigDecimal("0");
				DetalleVentaDTO detVenta = new DetalleVentaDTO();
				List<DetalleVentaDTO> lisDet= new ArrayList<DetalleVentaDTO>();
				for(DetalleVentaDTO obj : listaDetVenta) { 
					if(obj.getEsCheck() == true) {
						montoContar = montoContar.add(obj.getMontoTotal());
					}else{
						montoContar1.putIfAbsent(obj.getMontoTotal(), obj);
					}
				}
				
				if(montoContar != new BigDecimal("0")) {
					for (Map.Entry<BigDecimal,DetalleVentaDTO> entryMap : montoContar1.entrySet()) {
						detVenta = entryMap.getValue();
						detVenta.setMontoTotal(detVenta.getMontoTotal().add(montoContar));
						detVenta.setPrecio(detVenta.getPrecio().add(montoContar));
					}
					lisDet.add(detVenta);
				}

				
				if (CollectionUtil.isEmpty(lisDet)) {
					ventaReporte.setVentaDetalleVentaList(lisDet);
				}else {
					ventaReporte.setVentaDetalleVentaList(listaDetVenta);
				}
			

				listaVentaGenerar.add(ventaReporte);
				NombreReporteType reporte = NombreReporteType.JR_REP_BOLETA_VENTA_A4;
				ParametroReporteVO parametroReporteVO = new ParametroReporteVO(parametros, listaVentaGenerar, reporte, subreportes, null, true, "", "");
				parametroReporteVO.setFormato(TipoReporteGenerarType.PDF.getKey());
				parametroReporteVO.setUserName(usuario);
				parametroReporteVO.setFileName(fileName);
				codigoGeneradoReporte = generarReporteServiceImpl.obtenerParametroReporteBigMemory(parametroReporteVO);
				//System.out.println("PDF:: " +codigoGeneradoReporte);
				Utilidades.imprimirFactura(codigoGeneradoReporte,usuario,venta);
				return codigoGeneradoReporte;
			} catch (Exception e) {
				log.error(e);
			}
			return codigoGeneradoReporte;
		}
		
		@Override
		public String generarReporteVistaPrevia(List<VentaFiltroVO> ventaFiltro,String usuario) throws Exception {
			//String fileName = UUIDUtil.generarElementUUID();
			String fileName = UUIDUtil.generarElementUUID(); 
			String codigoGeneradoReporte = fileName;
			try { 
				Map<String, Object> parametros = new HashMap<String, Object>();
				String[] subreportes;
				subreportes = new String[0];	  
				NombreReporteType reporte = NombreReporteType.JR_REP_VISTA_PREVIA;
				ParametroReporteVO parametroReporteVO = new ParametroReporteVO(parametros, ventaFiltro, reporte, subreportes, null, true, "", "");
				parametroReporteVO.setFormato(TipoReporteGenerarType.PDF.getKey());
				parametroReporteVO.setUserName(usuario);
				parametroReporteVO.setFileName(fileName);
				codigoGeneradoReporte = generarReporteServiceImpl.obtenerParametroReporteBigMemory(parametroReporteVO);
				//System.out.println("PDF:: " +codigoGeneradoReporte);
				Utilidades.imprimirVistaPrevia(codigoGeneradoReporte,usuario,ventaFiltro.get(0).getNombreImpresora());
				return codigoGeneradoReporte;
			} catch (Exception e) {
				log.error(e);
			}
			return codigoGeneradoReporte;
		}
		
	
	@Override
	public List<DetalleVentaDTO> verDetalleVentasRealizadas(String idVenta) throws Exception {
		List<DetalleVentaDTO> resultado = new ArrayList<DetalleVentaDTO>();
		List<DetalleVenta> listaTemo = detalleVentaDaoImpl.listarDetVenta(idVenta);
		for (DetalleVenta detControlPago : listaTemo) {
			DetalleVentaDTO detControlPagoDTO = TransferDataObjectUtil.transferObjetoEntityDTO(detControlPago, DetalleVentaDTO.class,"detalleProducto:{producto;itemByUnidadMedida}","venta");
			detControlPagoDTO.setCantidadProductos(detControlPagoDTO.getCantidad().intValue());
			resultado.add(detControlPagoDTO);
		}
		listaTemo = null;
		return resultado;
	}
	


	
	
	@Override
	public VentaDTO anularVenta(VentaDTO venta) throws Exception {
		VentaDTO resultado = null;
		Venta resultadoEntity = null;
		this.ventaDaoImpl.updateVentaEstado(venta.getIdVenta());
		List<DetalleVenta>  listaDetventa = this.detalleVentaDaoImpl.listarDetVenta(venta.getIdVenta());
		for(DetalleVenta detV : listaDetventa) {
			this.productoDaoImpl.updateProductoStock(detV.getDetalleProducto().getProducto().getIdProducto(), detV.getCantidad());
		}
		
		
		return resultado;
	}
	
	@Override
	public List<DetalleProformaDTO> verDetalleProformasRealizadas(String idProforma) throws Exception {
		List<DetalleProformaDTO> resultado = new ArrayList<DetalleProformaDTO>();
		List<DetalleProforma> listaTemo = detalleProformaDaoImpl.listarDetProforma(idProforma);
		for (DetalleProforma detControlPago : listaTemo) {
			DetalleProformaDTO detControlPagoDTO = TransferDataObjectUtil.transferObjetoEntityDTO(detControlPago, DetalleProformaDTO.class,"producto","proforma");
			detControlPagoDTO.setCantidadProductos(detControlPagoDTO.getCantidad().intValue());
			resultado.add(detControlPagoDTO);
		}
		listaTemo = null;
		return resultado;
	}
	
	
	@Override
	public List<VentaDTO> listarVentaReporte(VentaDTO venta) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.ventaDaoImpl.listarVentaReporte(venta),VentaDTO.class,"tipoDocSunat","itemByTipoMoneda","cliente","pedido");
	}

	
	@Override
	public List<ProformaDTO> listarProformaReporte(ProformaDTO proforma) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.proformaDaoImpl.listarProformaReporte(proforma),ProformaDTO.class,"tipoDocSunat","itemByTipoMoneda","cliente");
	}
	
	@Override
	public void updateEstadoProforma() throws Exception {
		// TODO Auto-generated method stub
		this.proformaDaoImpl.updateEstadoProforma();
		
	}
	//add
	@Override
	public String generarReporteProforma(String idProforma, String idCliente, String nroDoc, String usuario) throws Exception {
		// TODO Auto-generated method stub
		//String fileName = UUIDUtil.generarElementUUID();
		Proforma proforma = this.proformaDaoImpl.find(Proforma.class, idProforma);
		String fileName = proforma.getSerie() +"-"+proforma.getNroDoc();
		String codigoGeneradoReporte = proforma.getSerie() +"-"+proforma.getNroDoc();
		UsuarioDTO resultado = null;
		Usuario resultadoEntity = null;
		try {
			ClienteDTO cliente = new ClienteDTO();
			cliente.setIdCliente(idCliente);
			cliente = this.controladorAccionCliente(cliente, AccionType.FIND_BY_ID);
			Map<String, Object> parametros = new HashMap<String, Object>();
			String[] subreportes;
			subreportes = new String[0];	
			parametros.put("nombreCliente", cliente.getNombre() + " " +  cliente.getApellidoPaterno() + " " + cliente.getApellidoMaterno());
			parametros.put("ruta", "");
			parametros.put("nroDocCli", cliente.getNroDoc());
			parametros.put("direccion", cliente.getDireccion());
			
			List<ProformaDTO> listaProformaGenerar = new ArrayList<ProformaDTO>();
			ProformaDTO proformaReporte = new ProformaDTO();
			proformaReporte.setIdProforma(idProforma);
			proformaReporte.setId(idCliente);
			proformaReporte.setCliente(cliente);
			proformaReporte.setNroDoc(nroDoc);
	

			proformaReporte = this.listarProformaReporte(proformaReporte).get(0);
			if (proformaReporte.getCliente() == null || proformaReporte.getCliente().getIdCliente() == null) {
				proformaReporte.setCliente(new ClienteDTO());
			}
			parametros.put("nroDoc", proformaReporte.getNroDoc());

			resultadoEntity= this.usuarioServiceImpl.findUsuario(proformaReporte.getUsuarioCreacion());
			resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,UsuarioDTO.class);
			parametros.put("nombreCajero", resultado.getNombre()+ " " + resultado.getApellidoPaterno()+ " " +resultado.getApellidoMaterno());
			NumerosUtil numLetra= new NumerosUtil();
			parametros.put("montoLetra", numLetra.Convertir(proformaReporte.getMontoTotal().toString(), true));
			
			proformaReporte.setCliente(cliente);
			List<DetalleProformaDTO> listaDetProforma = verDetalleProformasRealizadas(idProforma) ;
			proformaReporte.setProformaDetalleProformaList(listaDetProforma);
			listaProformaGenerar.add(proformaReporte);
			NombreReporteType reporte = null;
			reporte = NombreReporteType.JR_REP_PDF_PROFORMA;
			ParametroReporteVO parametroReporteVO = new ParametroReporteVO(parametros, listaProformaGenerar, reporte, subreportes, null, true, "", "");
			parametroReporteVO.setFormato(TipoReporteGenerarType.PDF.getKey());
			parametroReporteVO.setUserName(usuario);
			parametroReporteVO.setFileName(fileName);
			codigoGeneradoReporte = generarReporteServiceImpl.obtenerParametroReporteBigMemory(parametroReporteVO);
			System.out.println("PDF:: " +codigoGeneradoReporte);
			return codigoGeneradoReporte;
		} catch (Exception e) {
			log.error(e);
		}
		return codigoGeneradoReporte;
		
	}    
	
	@Override
	public GuiaRemisionDTO controladorAccionGuiaRemision(GuiaRemisionDTO guiaRemision, AccionType accionType) throws Exception {
		GuiaRemisionDTO resultado = null;
		GuiaRemision resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				if (StringUtils.isNotNullOrBlank(guiaRemision.getTipoDocSunat().getIdItem())) {
					System.out.println("TipoDoc:::   ---");
					String nroDocCalc = tipoDocSunatEntidadDaoImpl.actualizarTipoDocSunat(guiaRemision.getTipoDocSunat().getIdItem(),guiaRemision.getIdEntidadSelect(),guiaRemision.getNroDoc(),guiaRemision.getSerie());
					if (!StringUtils.isNotNullOrBlank(guiaRemision.getNroDoc())) {
						//generar el nro doc
						guiaRemision.setNroDoc(nroDocCalc);
					}
				}
				guiaRemision.setIdGuiaRemision(this.guiaRemisionDaoImpl.generarIdGuiaRemision());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(guiaRemision, GuiaRemision.class,"venta@PK@","tipoDocSunat@PK@","ubigeoByPuntoPartida@PK@","ubigeoByPuntoLLegada@PK@","transportistaConductor@PK@","vehiculo@PK@");
				this.guiaRemisionDaoImpl.save(resultadoEntity);	
				resultado = guiaRemision;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(guiaRemision, GuiaRemision.class,"venta@PK@","tipoDocSunat@PK@","ubigeoByPuntoPartida@PK@","ubigeoByPuntoLLegada@PK@","transportistaConductor@PK@","vehiculo@PK@");
				this.guiaRemisionDaoImpl.update(resultadoEntity);
				resultado = guiaRemision;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.guiaRemisionDaoImpl.find(GuiaRemision.class, guiaRemision.getIdGuiaRemision());
				this.guiaRemisionDaoImpl.delete(resultadoEntity);
				resultado = guiaRemision;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.guiaRemisionDaoImpl.find(GuiaRemision.class, guiaRemision.getIdGuiaRemision());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,GuiaRemisionDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.guiaRemisionDaoImpl.findByNombre(guiaRemision),GuiaRemisionDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<GuiaRemisionDTO> listarGuiaRemision(GuiaRemisionDTO guiaRemision) throws Exception {
		//return TransferDataObjectUtil.transferObjetoEntityDTOList(this.guiaRemisionDaoImpl.listarGuiaRemision(guiaRemision),GuiaRemisionDTO.class,"","","");
		List<GuiaRemision> listaGuiaR = guiaRemisionDaoImpl.listarGuiaRemision(guiaRemision);
		List<GuiaRemisionDTO> listaGuiaRemision = new ArrayList<GuiaRemisionDTO>();
		
		for(GuiaRemision guiaObj : listaGuiaR ) {
			GuiaRemisionDTO guiaDTO = TransferDataObjectUtil.transferObjetoEntityDTO(guiaObj,GuiaRemisionDTO.class,"ubigeoByPuntoLLegada:{ubigeoByDependencia}","ubigeoByPuntoPartida:{ubigeoByDependencia}","venta:{cliente}","tipoDocSunat","vehiculo","transportistaConductor");
			guiaDTO.setVenta(TransferDataObjectUtil.transferObjetoEntityDTO(guiaObj.getVenta(), VentaDTO.class,"cliente:{itemByTipoDocumentoIdentidad}","tipoDocSunat"));
			
			UbigeoDTO ubigo = new UbigeoDTO();
			ubigo.setIdPadreView(guiaDTO.getUbigeoByPuntoPartida().getUbigeoByDependencia().getIdUbigeo().substring(0, 2));
			guiaDTO.setDepartamentoPuntoPartida(commonServiceLocal.listarUbigeo(ubigo).get(0).getDescripcion());
			
			UbigeoDTO ubigo2 = new UbigeoDTO();
			ubigo2.setIdPadreView(guiaDTO.getUbigeoByPuntoLLegada().getUbigeoByDependencia().getIdUbigeo().substring(0, 2));
			guiaDTO.setDepartamentoPuntoLLegada(commonServiceLocal.listarUbigeo(ubigo2).get(0).getDescripcion());
			
			listaGuiaRemision.add(guiaDTO);
		}
		listaGuiaR =  null;
		return listaGuiaRemision;
	}
	
	@Override
	public int contarListarGuiaRemision(GuiaRemisionDTO guiaRemision){
		return  this.guiaRemisionDaoImpl.contarListarGuiaRemision(guiaRemision);
	}
	@Override
	public VehiculoDTO controladorAccionVehiculo(VehiculoDTO vehiculo, AccionType accionType) throws Exception {
		VehiculoDTO resultado = null;
		Vehiculo resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				vehiculo.setIdVehiculo(this.vehiculoDaoImpl.generarIdVehiculo());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(vehiculo, Vehiculo.class,"modelo@PK@","itemByColor@PK@");
				this.vehiculoDaoImpl.save(resultadoEntity);	
				resultado = vehiculo;
				break;		
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(vehiculo, Vehiculo.class,"modelo@PK@","itemByColor@PK@");
				this.vehiculoDaoImpl.update(resultadoEntity);
				resultado = vehiculo;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.vehiculoDaoImpl.find(Vehiculo.class, vehiculo.getIdVehiculo());
				this.vehiculoDaoImpl.delete(resultadoEntity);
				resultado = vehiculo;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.vehiculoDaoImpl.find(Vehiculo.class, vehiculo.getIdVehiculo());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,VehiculoDTO.class,"modelo@PK@","itemByColor@PK@");
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.vehiculoDaoImpl.findByNombre(vehiculo),VehiculoDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<VehiculoDTO> listarVehiculo(VehiculoDTO vehiculo) throws Exception {
		//return TransferDataObjectUtil.transferObjetoEntityDTOList(this.vehiculoDaoImpl.listarVehiculo(vehiculo),VehiculoDTO.class,"itemByColor","idModelo");
		
		List<Vehiculo> listaVehi = vehiculoDaoImpl.listarVehiculo(vehiculo);
		List<VehiculoDTO> listaVehiculo = new ArrayList<VehiculoDTO>();
		
		for(Vehiculo vehiculoObj : listaVehi ) {
			VehiculoDTO vehiculoDTO = TransferDataObjectUtil.transferObjetoEntityDTO(vehiculoObj,VehiculoDTO.class,"itemByColor","modelo:{marca}");
			FileVO fileVO = new FileVO();
			fileVO.setRuta(ConstanteConfigUtil.RUTA_RECURSOS_FOTO_ALUMN + ConstanteConfigUtil.SEPARADOR_FILE + "V-" +  vehiculoObj.getFoto());
			vehiculoDTO.setFoto(commonServiceLocal.obtenerImagenEncodeBase64(fileVO));
			listaVehiculo.add(vehiculoDTO);
		}
		listaVehi =  null;
		return listaVehiculo;
	}
	@Override
	public int contarListarVehiculo(VehiculoDTO vehiculo){
		return  this.vehiculoDaoImpl.contarListarVehiculo(vehiculo);
	}
	
	@Override
	public TransportistaDTO controladorAccionTransportista(TransportistaDTO transportista, AccionType accionType) throws Exception {
		TransportistaDTO resultado = null;
		Transportista resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				transportista.setIdTransportistaConductor(this.transportistaDaoImpl.generarIdTransportista());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(transportista, Transportista.class,"itemByTipoDocumentoIdentidad@PK@");
				this.transportistaDaoImpl.save(resultadoEntity);	
				resultado = transportista;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(transportista, Transportista.class,"itemByTipoDocumentoIdentidad@PK@");
				this.transportistaDaoImpl.update(resultadoEntity);
				resultado = transportista;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.transportistaDaoImpl.find(Transportista.class, transportista.getIdTransportistaConductor());
				this.transportistaDaoImpl.delete(resultadoEntity);
				resultado = transportista;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.transportistaDaoImpl.find(Transportista.class, transportista.getIdTransportistaConductor());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,TransportistaDTO.class,"itemByTipoDocumentoIdentidad@PK@");
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.transportistaDaoImpl.findByNombre(transportista),TransportistaDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<TransportistaDTO> listarTransportista(TransportistaDTO transportista) throws Exception {
	//	return TransferDataObjectUtil.transferObjetoEntityDTOList(this.transportistaDaoImpl.listarTransportista(transportista),TransportistaDTO.class,"itemByTipoDocumentoIdentidad");
		List<Transportista> listaTrans = transportistaDaoImpl.listarTransportista(transportista);
		List<TransportistaDTO> listaTransportista = new ArrayList<TransportistaDTO>();
		
		for(Transportista transportistaoObj : listaTrans ) {
			TransportistaDTO vehiculoDTO = TransferDataObjectUtil.transferObjetoEntityDTO(transportistaoObj,TransportistaDTO.class,"itemByTipoDocumentoIdentidad");
			FileVO fileVO = new FileVO();
			fileVO.setRuta(ConstanteConfigUtil.RUTA_RECURSOS_FOTO_ALUMN + ConstanteConfigUtil.SEPARADOR_FILE + "T-" +  transportistaoObj.getFoto());
			vehiculoDTO.setFoto(commonServiceLocal.obtenerImagenEncodeBase64(fileVO));
			listaTransportista.add(vehiculoDTO);
		}
		listaTrans =  null;
		return listaTransportista;
	}
	@Override
	public int contarListarTransportista(TransportistaDTO transportista){
		return  this.transportistaDaoImpl.contarListarTransportista(transportista);
	}

	
	@Override
	public VentaDTO findVenta(String idVenta) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTO(this.ventaDaoImpl.findVenta(idVenta),VentaDTO.class,"tipoDocSunat","itemByTipoMoneda","cliente","pedido");
	}
	
	//add
	@Override
	public String generarReporteGuia(GuiaRemisionDTO idGuia) throws Exception {
		System.out.println("IdDGuia:: " +idGuia.getVenta().getIdVenta());
		//String fileName = UUIDUtil.generarElementUUID();
		GuiaRemision guiaRemision = this.guiaRemisionDaoImpl.find(GuiaRemision.class, idGuia.getIdGuiaRemision());
		String fileName = guiaRemision.getSerie() +"-"+guiaRemision.getNroDoc();
		String codigoGeneradoReporte = guiaRemision.getSerie() +"-"+guiaRemision.getNroDoc();
		Map<String, Object> parametros = new HashMap<String, Object>();
		String[] subreportes;
		subreportes = new String[0];	
		parametros.put("id_Guia", idGuia.getIdGuiaRemision()); 
		parametros.put("id_Venta", idGuia.getVenta().getIdVenta()); 
		parametros.put("ruta", "");
		NombreReporteType reporte = NombreReporteType.JR_REP_PDF_GUIA_REMISION;
		ParametroReporteVO parametroReporteVO = new ParametroReporteVO(parametros, null, reporte, subreportes, null, false, "", "");
 
		parametroReporteVO.setFormato(TipoReporteGenerarType.PDF.getKey());
 
		parametroReporteVO.setFileName(fileName);
		codigoGeneradoReporte = generarReporteServiceImpl.obtenerParametroReporteBigMemory(parametroReporteVO);
		return codigoGeneradoReporte;
		
	}

	@Override
	public String generarReporteMultiple(String tipoReporte, Date fechaInicio, Date fechaFin, String usuarioCrea,String serviceKey, String authToken) throws Exception {
		String fileName = UUIDUtil.generarElementUUID();
		String userName = AppAuthenticator.getInstance().getUserName(authToken);
		String codigoGeneradoReporte = "";
		UsuarioDTO resultado = null;
		Usuario resultadoEntity = null;
		Map<String, Object> parametros = new HashMap<String, Object>();
		String[] subreportes = new String[0];
		
		NombreReporteType reporte = null;
		if (tipoReporte != null) {
			switch (TipoReportePagoType.get(tipoReporte)) {
				case  REPORTE_INGRESO_DETALLADO:					
					reporte = NombreReporteType.JR_REP_INGRESO_DETALLADO;
					subreportes = new String[0];	
					parametros.put("FechaInicio", fechaInicio);
					parametros.put("FechaFinal", fechaFin);
					parametros.put("Usuario", usuarioCrea);	
					parametros.put("ruta", "");
					parametros.put("ruta_logo", "");
					
					VentaDTO ventaPagoReporte = new VentaDTO();						
					ventaPagoReporte.setFechaCreacion(fechaInicio);;
					ventaPagoReporte.setUsuarioCreacion(usuarioCrea);		
					
					resultadoEntity= this.usuarioServiceImpl.findUsuario(ventaPagoReporte.getUsuarioCreacion());
					resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,UsuarioDTO.class);
					
					//parametros.put("nombreCajero", resultado.getNombre()+ " " + resultado.getApellidoPaterno()+ " " +resultado.getApellidoMaterno());
					
					break;	
				case REPORTE_DE_VENTA_RESUMEN:
					reporte = NombreReporteType.JR_REP_VENTAS_RESUMEN;	
					subreportes = new String[0];	
					parametros.put("FechaInicio", fechaInicio);
					parametros.put("FechaFinal", fechaFin);
					parametros.put("Usuario", usuarioCrea);	
					parametros.put("ruta", "");
					parametros.put("ruta_logo", "");					
					break;			
				default:
					break;
			}
			ParametroReporteVO parametroReporteVO = new ParametroReporteVO(parametros, null, reporte, subreportes, null, false, "", "");
			//parametroReporteVO.setEsCopiaCorreo(true);
			parametroReporteVO.setFormato(TipoReporteGenerarType.PDF.getKey());
			parametroReporteVO.setUserName(userName);
			parametroReporteVO.setFileName(fileName);
			codigoGeneradoReporte = generarReporteServiceImpl.obtenerParametroReporteBigMemory(parametroReporteVO);
		}
		
		return codigoGeneradoReporte;
	}

	//add
	
	
	private String getFileExtension(File file) {
	    String name = file.getName();
	    int lastIndexOf = name.lastIndexOf(".");
	if (lastIndexOf == -1) {
	    return ""; // empty extension
	    }
	    return name.substring(lastIndexOf);
	}
	
	
	@Override
	public List<SunatDatosVO> generarExtracionTXT() throws Exception {
		String resultado = ""; 
		String userName = "sfs"; 	    
		
	    String archiName = ConstanteConfigUtil.RUTA_DATOS_EXTRACION + ConstanteConfigUtil.generarRuta(userName,"DATA");


	   File carpeta = new File(archiName);
	   File[] listado = carpeta.listFiles();
	   String extencion="";
	   List<String> listadoDoc = new ArrayList<String>(); 
	      for (int i=0; i< listado.length; i++) { 
	    	 File archivo = listado[i];  
	         extencion=this.getFileExtension(archivo); 
	    		if(extencion.equals(".cab")) {
	    			listadoDoc.add(archivo.getName().substring(0,28)); 
	    		}else if(extencion.equals(".rdi")) {
	    			listadoDoc.add(archivo.getName().substring(0,27)); 
	    		}
	      } 
	      
	      List<String> exten = new ArrayList<String>();
	      exten.add(".cab");
	      exten.add(".det");
	      exten.add(".tri");
	      
	      List<String> extenB = new ArrayList<String>();
	      extenB.add(".rdi");
	      extenB.add(".trd"); 
	      
	      for(String obj: listadoDoc) {
	    	  String ori = ConstanteConfigUtil.RUTA_DATOS_EXTRACION + ConstanteConfigUtil.generarRuta(userName,"PARSE") + obj+".xml";
	    	  File archivo = new File(ori);
	    	  if (archivo.exists()) { 
	    		  if(obj.length() == 28) { 
		    		  for(String ex :exten) {
			    		    String origen = ConstanteConfigUtil.RUTA_DATOS_EXTRACION + ConstanteConfigUtil.generarRuta(userName,"DATA")+obj+ex;
			    		    String destino = ConstanteConfigUtil.RUTA_DATOS_DATA_ENVIADO+obj+ex;
			    		    Path origenPath = FileSystems.getDefault().getPath(origen);
			    		    Path destinoPath = FileSystems.getDefault().getPath(destino); 
			    		    try {
			    		        Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
			    		    } catch (IOException e) {
			    		        System.err.println(e);
			    		    }
		    		  }  
	    		  }else{ 
	    			  for(String exB :extenB) {
			    		    String origen = ConstanteConfigUtil.RUTA_DATOS_EXTRACION + ConstanteConfigUtil.generarRuta(userName,"DATA")+obj+exB;
			    		    String destino = ConstanteConfigUtil.RUTA_DATOS_DATA_ENVIADO +obj+exB ;
			    		    Path origenPath = FileSystems.getDefault().getPath(origen);
			    		    Path destinoPath = FileSystems.getDefault().getPath(destino); 
			    		    try {
			    		        Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
			    		    } catch (IOException e) {
			    		        System.err.println(e);
			    		    }    
	    			  }
	    		  }
	    	  } 
	      }
	      
		List<SunatDatosVO> sunatachivos = new ArrayList<SunatDatosVO>(); 
		List<Object[]> listaControlPagoGrupByFechas  = ventaDaoImpl.listaControlPagoGrupByFechas();		
		if(listaControlPagoGrupByFechas.size() > 0) {
			for (Object[] objects : listaControlPagoGrupByFechas) {  
				VentaDTO control = new VentaDTO();
				control.setTipoDocSunat(new ItemDTO());
				control.setFechaVenta((Date)objects[0]); 
				control.getTipoDocSunat().setIdItem((Long)objects[3]);
				
				String cant = StringUtils.padLeft(Long.toString((Long)objects[1]), 3,"0");
				resultado = "20394066240"+"-"+"RC"+"-"+(String)objects[2]+"-"+cant; 
				List<Map<String,Object>> listaControlPagoExtracionB = new ArrayList<Map<String,Object>>();
				listaControlPagoExtracionB = this.generarArchivosPlanosXML(control);
				TXTUtil.escribirArchivoXML(listaControlPagoExtracionB, userName, resultado, "rdi", "|");
				if(!StringUtils.isNullOrEmpty(control.getFechaVenta())) {
					control.setId("TRD");
					List<Map<String,Object>> listaItemBTRI = new ArrayList<Map<String,Object>>();
					listaItemBTRI = this.generarArchivosPlanosXML(control);
					TXTUtil.escribirArchivoXML(listaItemBTRI, userName, resultado, "trd", "|");
				}
			}	
		}
		List<VentaDTO> listaF = listaControlPagoExtracionF();
		for(VentaDTO obj : listaF) { 
			resultado = "20394066240"+"-"+"01"+"-"+obj.getSerie()+"-"+obj.getNroDoc(); 
			List<Map<String,Object>> listaItemF = new ArrayList<Map<String,Object>>();
			listaItemF = this.generarArchivosPlanosXML(obj);
			TXTUtil.escribirArchivoXML(listaItemF, userName, resultado, "cab", "|");

			if(!StringUtils.isNullOrEmpty(obj.getIdVenta())) {
				obj.setId("TRI");
				List<Map<String,Object>> listaItemTriF = new ArrayList<Map<String,Object>>();
				listaItemTriF = this.generarArchivosPlanosXML(obj);
				TXTUtil.escribirArchivoXML(listaItemTriF, userName, resultado, "tri", "|");
			}
			
			if(!StringUtils.isNullOrEmpty(obj.getIdVenta())) {
				obj.setId("DET");
				List<Map<String,Object>> listaItemDetF = new ArrayList<Map<String,Object>>();
				listaItemDetF = this.generarArchivosPlanosXML(obj);
				TXTUtil.escribirArchivoXML(listaItemDetF, userName, resultado, "det", "|");
			}
		}
		this.envioDemo();
		ventaDaoImpl.updateVentaExtracion();
		return sunatachivos = this.envioDemo();
	}
	 
	
	private List<Map<String,Object>> generarArchivosPlanosXML(VentaDTO control) throws Exception {
		return  this.ventaDaoImpl.generarArchivosPlanosXML(control);
	}
	
	@Override
	public List<VentaDTO> listaControlPagoExtracionF() throws Exception {
		List<VentaDTO> resultado = new ArrayList<VentaDTO>();
		List<Venta> listaTemo = ventaDaoImpl.listaVentaExtracionF();
		for (Venta detControlPago : listaTemo) {
			VentaDTO detControlPagoDTO = TransferDataObjectUtil.transferObjetoEntityDTO(detControlPago, VentaDTO.class,"tipoDocSunat","itemByTipoMoneda","cliente","pedido","huesped","vehiculo");
			resultado.add(detControlPagoDTO);
		}
		listaTemo = null;
		return resultado;
	}
	
	
	public List<SunatDatosVO> envioDemo() throws ParseException { 
	    JsonObject sfs = new JsonObject();
	    List<SunatDatosVO> jsonobject2 = Utilidades.actualizarPantalla(sfs); 
	    return jsonobject2;
	}
	
	
	@Override
	public List<SunatDatosVO> generarComprobante(SunatDatosVO sfs) throws Exception {
	    String targetURL="http://localhost:9000/api/GenerarComprobante.htm";//here is my local server url
		List<SunatDatosVO> jsonobject2 = Utilidades.generaraccionComprobante(sfs,targetURL);
		
		return jsonobject2;
	}
	
	@Override
	public List<SunatDatosVO> enviarComprobante(SunatDatosVO sfs) throws Exception { 
	    String targetURL="http://localhost:9000/api/enviarXML.htm";//here is my local server url
		List<SunatDatosVO> jsonobject2 = Utilidades.generaraccionComprobante(sfs,targetURL);
		
		return jsonobject2;
	}
	
	@Override
	public List<SunatDatosVO> eliminarBandeja() throws Exception {
		List<SunatDatosVO> jsonobject2 = Utilidades.eliminarBandeja();
		return jsonobject2;
	}
	
	@Override
	public List<SunatDatosVO> actualizarBandeja() throws Exception {
	    JsonObject sfs = new JsonObject();
		List<SunatDatosVO> jsonobject2 = Utilidades.actualizarPantalla(sfs);
		return jsonobject2;
	}

	@Override
	public void updateOferta() throws Exception {
		// TODO Auto-generated method stub
		this.productoDaoImpl.updateOferta();
	}

	@Override
	public DetalleProductoDTO controladorAccionDetalleProducto(DetalleProductoDTO detalleProducto,Producto producto,
			AccionType accionType) throws Exception {
		DetalleProductoDTO resultado = null;
		DetalleProducto resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				detalleProducto.setIdDetalleProducto(this.detalleProductoDaoImpl.generarIdDetalleProducto());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(detalleProducto, DetalleProducto.class,"producto@PK@","itemByUnidadMedida@PK@");
				resultadoEntity.setProducto(producto);
				this.detalleProductoDaoImpl.save(resultadoEntity);					
				resultado = detalleProducto;
				
				if (!CollectionUtil.isEmpty(detalleProducto.getComboList())) {							
					for (ComboDTO comboDTO : detalleProducto.getComboList()) {		
						if (!comboDTO.isEsEliminado()) {
							if (StringUtils.isNullOrEmpty(comboDTO.getIdCombo())) {								
								controladorAccionCombo(comboDTO,resultadoEntity,AccionType.CREAR);	
							}	
						}							
					}
				}
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(detalleProducto, DetalleProducto.class,"producto@PK@","itemByUnidadMedida@PK@");
				resultadoEntity.setProducto(producto);
				this.detalleProductoDaoImpl.update(resultadoEntity);
				resultado = detalleProducto;
				
				if (!CollectionUtil.isEmpty(detalleProducto.getComboList())) {							
					for (ComboDTO comboDTO : detalleProducto.getComboList()) {
						if (!comboDTO.isEsEliminado()) {									
							if (StringUtils.isNullOrEmpty(comboDTO.getIdCombo())) {
								
								controladorAccionCombo(comboDTO,resultadoEntity,AccionType.CREAR);											
							}else {
								
								controladorAccionCombo(comboDTO,resultadoEntity,AccionType.MODIFICAR);
							}
						}else {
							controladorAccionCombo(comboDTO,resultadoEntity,AccionType.ELIMINAR);
						}
					}
				}
				break;
				
			case ELIMINAR:				
				resultadoEntity = this.detalleProductoDaoImpl.find(DetalleProducto.class, detalleProducto.getIdDetalleProducto());				
				for (ComboDTO comboDTO : detalleProducto.getComboList()) {					
					controladorAccionCombo(comboDTO,resultadoEntity,AccionType.ELIMINAR);
				}
				this.detalleProductoDaoImpl.delete(resultadoEntity);
				resultado = detalleProducto;
				break;
				
				
				
			case FIND_BY_ID:
				resultadoEntity = this.detalleProductoDaoImpl.find(DetalleProducto.class, detalleProducto.getIdDetalleProducto());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,DetalleProductoDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.productoDaoImpl.findByNombre(producto),ProductoDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}

	@Override
	public List<DetalleProductoDTO> listarDetalleProducto(DetalleProductoDTO detalleProducto) throws Exception {
		List<DetalleProducto> listaTemp = detalleProductoDaoImpl.listarDetalleProducto(detalleProducto);
		List<DetalleProductoDTO> listaPro = new ArrayList<DetalleProductoDTO>(); 
		for (DetalleProducto prod : listaTemp) {
			DetalleProductoDTO prodcutoProveedorDTO = TransferDataObjectUtil.transferObjetoEntityDTO(prod, DetalleProductoDTO.class,"producto","itemByUnidadMedida");
			prodcutoProveedorDTO.setProducto(TransferDataObjectUtil.transferObjetoEntityDTO(prod.getProducto(), ProductoDTO.class,"categoria","itemByUnidadMedida","modelo","itemByColor","itemByTalla","planContableVenta","planContableCompra"));
			FileVO fileVO = new FileVO();
			fileVO.setRuta(ConstanteConfigUtil.RUTA_RECURSOS_FOTO_ALUMN + ConstanteConfigUtil.SEPARADOR_FILE + "086" +  prodcutoProveedorDTO.getProducto().getFoto());
			prodcutoProveedorDTO.getProducto().setFoto(commonServiceLocal.obtenerImagenEncodeBase64(fileVO));
			List<ComboDTO> listadoCombo = listarComboBy(prodcutoProveedorDTO.getIdDetalleProducto());
			prodcutoProveedorDTO.setComboList(listadoCombo); 	
			listaPro.add(prodcutoProveedorDTO);
		}	
		listaTemp = null;
		return listaPro;
	}
	
	@Override
	public List<DetalleProductoDTO> listarDetalleProductoCombo(DetalleProductoDTO detalleProducto) throws Exception {
		List<DetalleProducto> listaTemp = detalleProductoDaoImpl.listarDetalleProductoCombo(detalleProducto);
		List<DetalleProductoDTO> listaPro = new ArrayList<DetalleProductoDTO>(); 
		for (DetalleProducto prod : listaTemp) {
			DetalleProductoDTO prodcutoProveedorDTO = TransferDataObjectUtil.transferObjetoEntityDTO(prod, DetalleProductoDTO.class,"producto","itemByUnidadMedida");
			prodcutoProveedorDTO.setProducto(TransferDataObjectUtil.transferObjetoEntityDTO(prod.getProducto(), ProductoDTO.class,"categoria","itemByUnidadMedida","modelo","itemByColor","itemByTalla","planContableVenta","planContableCompra"));
			FileVO fileVO = new FileVO();
			fileVO.setRuta(ConstanteConfigUtil.RUTA_RECURSOS_FOTO_ALUMN + ConstanteConfigUtil.SEPARADOR_FILE + "086" +  prodcutoProveedorDTO.getProducto().getFoto());
			prodcutoProveedorDTO.getProducto().setFoto(commonServiceLocal.obtenerImagenEncodeBase64(fileVO));
			List<ComboDTO> listadoCombo = listarComboBy(prodcutoProveedorDTO.getIdDetalleProducto());
			prodcutoProveedorDTO.setComboList(listadoCombo); 	
			listaPro.add(prodcutoProveedorDTO);
		}	
		listaTemp = null;
		return listaPro;
	}
	
	
	@Override
	public List<DetalleProductoDTO> listarDetalleProductoBy(Long idProducto) throws Exception {
		List<DetalleProducto> listaTemp = detalleProductoDaoImpl.listarDetalleProductoBy(idProducto);
		List<DetalleProductoDTO> listaPro = new ArrayList<DetalleProductoDTO>(); 
		for (DetalleProducto prod : listaTemp) {
			DetalleProductoDTO detalleProdcutoDTO = TransferDataObjectUtil.transferObjetoEntityDTO(prod, DetalleProductoDTO.class,"producto","itemByUnidadMedida");
			detalleProdcutoDTO.setProducto(TransferDataObjectUtil.transferObjetoEntityDTO(prod.getProducto(), ProductoDTO.class,"categoria","itemByUnidadMedida","modelo","itemByColor","itemByTalla","planContableVenta","planContableCompra"));
			FileVO fileVO = new FileVO();
			fileVO.setRuta(ConstanteConfigUtil.RUTA_RECURSOS_FOTO_ALUMN + ConstanteConfigUtil.SEPARADOR_FILE + "086" +  detalleProdcutoDTO.getProducto().getFoto());
			detalleProdcutoDTO.getProducto().setFoto(commonServiceLocal.obtenerImagenEncodeBase64(fileVO));
			List<ComboDTO> listadoCombo = listarComboBy(detalleProdcutoDTO.getIdDetalleProducto());
			detalleProdcutoDTO.setComboList(listadoCombo); 			
			listaPro.add(detalleProdcutoDTO);
		}	
		listaTemp = null;
		return listaPro;
	}

	@Override
	public int contarListarDetalleProducto(DetalleProductoDTO detalleProducto) {
		// TODO Auto-generated method stub
		return 0;
	}	
	
	
	@Override
	public ComboDTO controladorAccionCombo(ComboDTO combo,DetalleProducto detalleProducto,
			AccionType accionType) throws Exception {
		ComboDTO resultado = null;
		Combo resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				combo.setIdCombo(this.comboDaoImpl.generarIdCombo());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(combo, Combo.class,"detalleProducto@PK@","detalleCombo@PK@");
				resultadoEntity.setDetalleProducto(detalleProducto);
				this.comboDaoImpl.save(resultadoEntity);	
				resultado = combo;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(combo, Combo.class,"detalleProducto@PK@","detalleCombo@PK@");
				resultadoEntity.setDetalleProducto(detalleProducto);
				this.comboDaoImpl.update(resultadoEntity);
				resultado = combo;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.comboDaoImpl.find(Combo.class, combo.getIdCombo());
				this.comboDaoImpl.delete(resultadoEntity);
				resultado = combo;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.comboDaoImpl.find(Combo.class, combo.getIdCombo());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,ComboDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.productoDaoImpl.findByNombre(producto),ProductoDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}

	@Override
	public List<ComboDTO> listarCombo(ComboDTO combo) throws Exception {
		List<Combo> listaTemp = comboDaoImpl.listarCombo(combo);
		List<ComboDTO> listaComb = new ArrayList<ComboDTO>(); 
		for (Combo comb : listaTemp) {
			ComboDTO comboDTO = TransferDataObjectUtil.transferObjetoEntityDTO(comb, ComboDTO.class,"detalleProducto");
			comboDTO.setDetalleProducto(TransferDataObjectUtil.transferObjetoEntityDTO(comb.getDetalleProducto(),DetalleProductoDTO.class,"producto","itemByUnidadMedida"));
			comboDTO.getDetalleProducto().setProducto(TransferDataObjectUtil.transferObjetoEntityDTO(comb.getDetalleProducto().getProducto(), ProductoDTO.class,"categoria","itemByUnidadMedida","modelo","itemByColor","itemByTalla","planContableVenta","planContableCompra"));			
			listaComb.add(comboDTO);
		}	
		listaTemp = null;
		return listaComb;
	}
	
	
	@Override
	public List<ComboDTO> listarComboBy(String idDetalleProducto) throws Exception {
		List<Combo> listaTemp = comboDaoImpl.listarComboBy(idDetalleProducto);
		List<ComboDTO> listaComb = new ArrayList<ComboDTO>(); 
		for (Combo comb : listaTemp) {
			ComboDTO comboDTO = TransferDataObjectUtil.transferObjetoEntityDTO(comb, ComboDTO.class,"detalleProducto","detalleCombo");
			comboDTO.setDetalleProducto(TransferDataObjectUtil.transferObjetoEntityDTO(comb.getDetalleProducto(),DetalleProductoDTO.class,"producto","itemByUnidadMedida"));
			comboDTO.getDetalleProducto().setProducto(TransferDataObjectUtil.transferObjetoEntityDTO(comb.getDetalleProducto().getProducto(), ProductoDTO.class,"categoria","itemByUnidadMedida","modelo","itemByColor","itemByTalla","planContableVenta","planContableCompra"));			
			
			comboDTO.setDetalleCombo(TransferDataObjectUtil.transferObjetoEntityDTO(comb.getDetalleCombo(),DetalleProductoDTO.class,"producto","itemByUnidadMedida"));
			comboDTO.getDetalleCombo().setProducto(TransferDataObjectUtil.transferObjetoEntityDTO(comb.getDetalleCombo().getProducto(), ProductoDTO.class,"categoria","itemByUnidadMedida","modelo","itemByColor","itemByTalla","planContableVenta","planContableCompra"));	
			listaComb.add(comboDTO);
		}	
		listaTemp = null;
		return listaComb;
	}

	@Override
	public int contarListarCombo(ComboDTO combo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public ComboDTO findByComboIdDetalleProducto(Long idDetalleProducto) throws Exception { 
		ResultadoRestVO<ComboDTO> resultado2 = new ResultadoRestVO<ComboDTO>();
		Combo combo = comboDaoImpl.findByComboIdDetalleProducto(idDetalleProducto);
		ComboDTO resultado = new ComboDTO();
		try {
			resultado = TransferDataObjectUtil.transferObjetoEntityDTO(combo, ComboDTO.class,"detalleProducto");
			resultado.setDetalleProducto(TransferDataObjectUtil.transferObjetoEntityDTO(combo.getDetalleProducto(),DetalleProductoDTO.class,"producto","itemByUnidadMedida"));
			resultado.getDetalleProducto().setProducto(TransferDataObjectUtil.transferObjetoEntityDTO(combo.getDetalleProducto().getProducto(), ProductoDTO.class,"categoria","itemByUnidadMedida","modelo","itemByColor","itemByTalla","planContableVenta","planContableCompra"));			
			combo = null;
		} catch (Exception e) {
        	resultado2.setError(true);
            resultado2.setCodigoError(Response.Status.UNAUTHORIZED.getStatusCode() + "");
			resultado2.setMensajeError("No se encuentra el Producto");
		}
		return resultado;
	}
	
	@Override
	public void registrarProducto(ProductoDTO producto) throws Exception {
		ProductoDTO resultado = new ProductoDTO();
		Producto resultadoEntity = null; 
		DetalleProducto resultadoDetalleProductoEntity = null;
		if (!StringUtils.isNotNullOrBlank(producto.getIdProducto())) {		
			producto.setIdProducto(this.productoDaoImpl.generarIdProducto());
			resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(producto, Producto.class,"categoria@PK@","itemByUnidadMedida@PK@","modelo@PK@","itemByColor@PK@","itemByTalla@PK@","planContableVenta@PK@","planContableCompra@PK@","entidad@PK@");
			//isVentaMesa = true;
			this.productoDaoImpl.save(resultadoEntity); 
			resultado = producto;
		} else {
			resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(producto, Producto.class,"categoria@PK@","itemByUnidadMedida@PK@","modelo@PK@","itemByColor@PK@","itemByTalla@PK@","planContableVenta@PK@","planContableCompra@PK@","entidad@PK@");
			this.productoDaoImpl.update(resultadoEntity);
			resultado = producto;	 
		}
		
		if (!CollectionUtil.isEmpty(producto.getDetalleProductoList())) {
			for (DetalleProductoDTO detalleProducto : producto.getDetalleProductoList()) { 
				if (!detalleProducto.isEsEliminado()) {
					if (StringUtils.isNullOrEmpty(detalleProducto.getIdDetalleProducto())) {
						controladorAccionDetalleProducto(detalleProducto,resultadoEntity,AccionType.CREAR);						
						
					} else {
						controladorAccionDetalleProducto(detalleProducto,resultadoEntity,AccionType.MODIFICAR);						
					} 
				} else {
					controladorAccionDetalleProducto(detalleProducto,resultadoEntity, AccionType.ELIMINAR);
				}
			}
		} 

		
		
		if (!CollectionUtil.isEmpty(producto.getProductoProveedorList())) {
			for (ProductoProveedorDTO productoProveedor : producto.getProductoProveedorList()) { 
				if (!productoProveedor.isEsEliminado()) {
					if (StringUtils.isNullOrEmpty(productoProveedor.getIdProductoProveedor())) {
						controladorAccionProductoProveedor(productoProveedor,resultadoEntity,AccionType.CREAR);
					} else {
						controladorAccionProductoProveedor(productoProveedor,resultadoEntity,AccionType.MODIFICAR);
					} 
				} else {
					controladorAccionProductoProveedor(productoProveedor,resultadoEntity, AccionType.ELIMINAR);
				}
			}
		} 
		
		 
	}

	@Override
	public DetalleProductoDTO findByDetalleProducto(String codigo) throws Exception { 
		ResultadoRestVO<DetalleProductoDTO> resultado2 = new ResultadoRestVO<DetalleProductoDTO>();
		DetalleProducto detProducto = detalleProductoDaoImpl.findByDetProducto(codigo);
		DetalleProductoDTO resultado = new DetalleProductoDTO();
		try {
			resultado = TransferDataObjectUtil.transferObjetoEntityDTO(detProducto,DetalleProductoDTO.class,"producto","itemByUnidadMedida");
			resultado.setProducto(TransferDataObjectUtil.transferObjetoEntityDTO(detProducto.getProducto(),ProductoDTO.class,"categoria","itemByUnidadMedida","modelo","itemByColor","itemByTalla","planContableVenta","planContableCompra"));
			detProducto = null;
		} catch (Exception e) {
        	resultado2.setError(true);
            resultado2.setCodigoError(Response.Status.UNAUTHORIZED.getStatusCode() + "");
			resultado2.setMensajeError("No se encuentra el Producto");
		}
		return resultado;
	}
	
	@Override
	public DetalleProductoDTO findByDetalleProductoIdProducto(Long idProducto) throws Exception { 
		ResultadoRestVO<DetalleProductoDTO> resultado2 = new ResultadoRestVO<DetalleProductoDTO>();
		DetalleProducto detProducto = detalleProductoDaoImpl.findByDetalleProductoIdProducto(idProducto);
		DetalleProductoDTO resultado = new DetalleProductoDTO();
		try {
			resultado = TransferDataObjectUtil.transferObjetoEntityDTO(detProducto,DetalleProductoDTO.class,"producto","itemByUnidadMedida");
			resultado.setProducto(TransferDataObjectUtil.transferObjetoEntityDTO(detProducto.getProducto(),ProductoDTO.class,"categoria","itemByUnidadMedida","modelo","itemByColor","itemByTalla","planContableVenta","planContableCompra"));
			detProducto = null;
		} catch (Exception e) {
        	resultado2.setError(true);
            resultado2.setCodigoError(Response.Status.UNAUTHORIZED.getStatusCode() + "");
			resultado2.setMensajeError("No se encuentra el Producto");
		}
		return resultado;
	}
	
	//agregados
	@Override
	public CajaDTO controladorAccionCaja(CajaDTO caja, AccionType accionType) throws Exception {
		String userName = AppAuthenticator.getInstance().getUserName(caja.getAuthToken());
		CajaDTO resultado = null;
		Caja resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				caja.setIdCaja(this.cajaDaoImpl.generarIdCaja());
				caja.setHoraApertura(FechaUtil.obtenerHoraMinutos());
				caja.setDescripcion(FechaUtil.obtenerFechaFormatoPersonalizado(caja.getFechaCreacion(), "dd/MM/yyyy"));
				//caja.setFechaCreacion(FechaUtil.obtenerFecha());
				caja.setEstado(EstadoGeneralState.ABIERTO.getKey());
				caja.setUsuarioCreacion(userName);
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(caja, Caja.class,"usuario");
				this.cajaDaoImpl.save(resultadoEntity);	
				resultado = caja;
				break;				
			case MODIFICAR:
				caja.setFechaModificacion(FechaUtil.obtenerFecha());
				if(!StringUtils.isNotNullOrBlank(caja.getHoraCierre())) {
					caja.setEstado(EstadoGeneralState.ABIERTO.getKey());
				}else {
					caja.setEstado(EstadoGeneralState.CERRADO.getKey());	
				}

				caja.setUsuarioModificacion(userName);
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(caja, Caja.class,"usuario");
				this.cajaDaoImpl.update(resultadoEntity);
				resultado = caja;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.cajaDaoImpl.find(Caja.class, caja.getIdCaja());
				this.cajaDaoImpl.delete(resultadoEntity);
				resultado = caja;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.cajaDaoImpl.find(Caja.class, caja.getIdCaja());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,CajaDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.categoriaDaoImpl.findByNombre(categoria),CategoriaDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}

	@Override
	public List<CajaDTO> listarCaja(CajaDTO caja) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.cajaDaoImpl.listarCaja(caja),CajaDTO.class,"usuario");
	}

	@Override
	public int contarListarCaja(CajaDTO caja) {
		return this.cajaDaoImpl.contarListarCaja(caja);
	}
	
	@Override
	public ProductoProveedorDTO controladorAccionProductoProveedor(ProductoProveedorDTO productoProveedor,Producto producto,
			AccionType accionType) throws Exception {
		ProductoProveedorDTO resultado = null;
		ProductoProveedor resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				productoProveedor.setIdProductoProveedor(this.productoProveedorDaoImpl.generarIdProductoProveedor());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(productoProveedor, ProductoProveedor.class,"producto@PK@","proveedor@PK@","itemByUnidadMedida@PK@");
				if(producto != null) {
					resultadoEntity.setProducto(producto);
				}
				this.productoProveedorDaoImpl.save(resultadoEntity);	
				resultado = productoProveedor;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(productoProveedor, ProductoProveedor.class,"producto@PK@","proveedor@PK@","itemByUnidadMedida@PK@");
				this.productoProveedorDaoImpl.update(resultadoEntity);
				if(producto != null) {
					resultadoEntity.setProducto(producto);
				}
				resultado = productoProveedor;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.productoProveedorDaoImpl.find(ProductoProveedor.class, productoProveedor.getIdProductoProveedor());
				this.productoProveedorDaoImpl.delete(resultadoEntity);
				resultado = productoProveedor;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.productoProveedorDaoImpl.find(ProductoProveedor.class, productoProveedor.getIdProductoProveedor());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,ProductoProveedorDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.productoDaoImpl.findByNombre(producto),ProductoDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}

	@Override
	public List<ProductoProveedorDTO> listarProductoProveedor(ProductoProveedorDTO productoProveedor) throws Exception {
		List<ProductoProveedor> listaTemp = productoProveedorDaoImpl.listarProductoProveedor(productoProveedor);
		List<ProductoProveedorDTO> listaPro = new ArrayList<ProductoProveedorDTO>(); 
		for (ProductoProveedor prod : listaTemp) {
			ProductoProveedorDTO prodcutoProveedorDTO = TransferDataObjectUtil.transferObjetoEntityDTO(prod, ProductoProveedorDTO.class,"producto","proveedor","itemByUnidadMedida");
			prodcutoProveedorDTO.setProducto(TransferDataObjectUtil.transferObjetoEntityDTO(prod.getProducto(), ProductoDTO.class,"categoria","itemByUnidadMedida","modelo","itemByColor","itemByTalla","planContableVenta","planContableCompra"));
			FileVO fileVO = new FileVO();
			fileVO.setRuta(ConstanteConfigUtil.RUTA_RECURSOS_FOTO_ALUMN + ConstanteConfigUtil.SEPARADOR_FILE + "P-" +  prodcutoProveedorDTO.getProducto().getFoto());			
			prodcutoProveedorDTO.getProducto().setFoto(commonServiceLocal.obtenerImagenEncodeBase64(fileVO));
			listaPro.add(prodcutoProveedorDTO);
		}	
		listaTemp = null;
		return listaPro;
	}

	@Override
	public int contarListarProductoProveedor(ProductoProveedorDTO productoProveedor) {
		// TODO Auto-generated method stub
		return this.productoProveedorDaoImpl.contarListarProductoProveedor(productoProveedor);
	}

	
	@Override
	public String cerrarCaja(CajaDTO cajaTemp) throws Exception{
		UsuarioDTO resultado = null;
		Usuario resultadoEntity = null;
		String fileName = UUIDUtil.generarElementUUID();
		String codigoGeneradoReporte = fileName;
		try { 
			Map<String, Object> parametros = new HashMap<String, Object>();
			String[] subreportes;
			subreportes = new String[0];	
			
			//VentaDTO ventaDto = new VentaDTO();						
			//ventaDto.setUsuarioModificacion(cajaTemp.getUsuario().getUserName());		
			
			resultadoEntity= this.usuarioServiceImpl.find(Usuario.class, cajaTemp.getUsuario().getIdUsuario());
			resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,UsuarioDTO.class);
			parametros.put("nombreCajero", resultado.getNombre()+ " " + resultado.getApellidoPaterno()+ " " +resultado.getApellidoMaterno());
			parametros.put("userName", cajaTemp.getUsuario().getUserName());
			parametros.put("idCaja", cajaTemp.getIdCaja());
			parametros.put("fechaCreacion", FechaUtil.obtenerFechaFormatoPersonalizado(cajaTemp.getFechaCreacion(), "dd/MM/yyyy"));

			NombreReporteType reporte = NombreReporteType.JR_REP_CIERRE_CAJA_REPORTE;
			ParametroReporteVO parametroReporteVO = new ParametroReporteVO(parametros, null, reporte, subreportes, null, false, "", "");
			parametroReporteVO.setFormato(TipoReporteGenerarType.PDF.getKey());
			parametroReporteVO.setUserName(resultado.getUserName());
			parametroReporteVO.setFileName(fileName);
			codigoGeneradoReporte = generarReporteServiceImpl.obtenerParametroReporteBigMemory(parametroReporteVO);
			 
			return codigoGeneradoReporte;
		} catch (Exception e) {
			log.error(e);
		}
		return codigoGeneradoReporte;
	}
	
	@Override
	public String reporteCaja(String idUsuario,Date fechaCreacion,String idCaja) throws Exception{
		UsuarioDTO resultado = null;
		Usuario resultadoEntity = null;
		String fileName = UUIDUtil.generarElementUUID();
		String codigoGeneradoReporte = fileName;
		Map<String, Object> parametros = new HashMap<String, Object>();
		String[] subreportes;
		subreportes = new String[0];	
		 
		
		resultadoEntity= this.usuarioServiceImpl.find(Usuario.class, idUsuario);
		resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,UsuarioDTO.class);
		parametros.put("nombreCajero", resultado.getNombre()+ " " + resultado.getApellidoPaterno()+ " " +resultado.getApellidoMaterno());
		parametros.put("userName", resultado.getUserName());
		parametros.put("idCaja", idCaja);
		parametros.put("fechaCreacion", FechaUtil.obtenerFechaFormatoPersonalizado(fechaCreacion, "dd/MM/yyyy"));
 

		NombreReporteType reporte = NombreReporteType.JR_REP_CIERRE_CAJA_REPORTE;
		ParametroReporteVO parametroReporteVO = new ParametroReporteVO(parametros, null, reporte, subreportes, null, false, "", "");
		parametroReporteVO.setFormato(TipoReporteGenerarType.PDF.getKey());
		parametroReporteVO.setFileName(fileName);
		codigoGeneradoReporte = generarReporteServiceImpl.obtenerParametroReporteBigMemory(parametroReporteVO);
		
		return codigoGeneradoReporte;
	}
	
	@Override
	public String updateVentaCierre(CajaDTO cajaTemp) throws Exception{
		String codigoGeneradoReporte = "";
		VentaFiltroVO venta = new VentaFiltroVO();
		Usuario usraio = this.usuarioServiceImpl.find(Usuario.class, cajaTemp.getUsuario().getIdUsuario());
		venta.setIdUsuario(usraio.getUserName()); 
		List<VentaDTO> listadoCaja= obtenerListaCajaFiltro(venta);
		
		List<ControlPagoDTO> listaControl = empresaServiceLocal.obtenerListaCajaFiltroPago(venta);
		int con =0;
		BigDecimal contador = new BigDecimal(con);
		
		for(VentaDTO obj : listadoCaja) {
			contador = contador.add(obj.getMontoTotal());
		}
		for(ControlPagoDTO obj : listaControl) {
			contador = contador.add(obj.getMontoTotal());
		}

		CajaDTO cajaBy = findByCaja(cajaTemp);
		cajaBy.setMontoTotal(contador.add(cajaBy.getMontoApertuera()));
		cajaBy.setMontoCiere(contador);
		this.cajaDaoImpl.updateCajaCierre(cajaBy);
		this.ventaDaoImpl.updateVentaCierre(cajaBy.getIdCaja(),cajaBy.getUsuario().getUserName(),cajaBy.getFechaCreacion());  
		this.controlPagoDaoImpl.updateControlPagoCierre(cajaBy.getIdCaja(),cajaBy.getUsuario().getUserName(),cajaBy.getFechaCreacion());
		return codigoGeneradoReporte;
	}

	@Override
	public String iniciarAperturaCaja() throws Exception { 
		String codigoGeneradoReporte = "";
		CajaDTO caja = new CajaDTO();
		caja.setId("primaKey");
		List<CajaDTO> listadoCaj =this.listarCaja(caja); 
		if(listadoCaj.size() > 0) { 
			
		}else {
			CajaDTO cajaC = new CajaDTO(); 
			int montoApertuera = 0;
			cajaC.setMontoApertuera(new BigDecimal(montoApertuera));
			cajaC.setDescripcion(FechaUtil.obtenerFechaFormatoPersonalizado(FechaUtil.obtenerFechaActual(), "dd/MM/yyyy"));
			this.controladorAccionCaja(cajaC, AccionType.CREAR);
		}		
		return codigoGeneradoReporte;
	}
	
	@Override
	public CajaDTO findByCaja(CajaDTO cajaTemp) throws Exception {
		CajaDTO resultado = new CajaDTO();
		Caja caja = cajaDaoImpl.findByCaja(cajaTemp);
		resultado = TransferDataObjectUtil.transferObjetoEntityDTO(caja,CajaDTO.class,"usuario");
		caja = null;
		return resultado;
	}
	
	@Override
	public CajaDTO findByCajaByIdUsuario(String idUsuario) throws Exception {
		CajaDTO resultado = new CajaDTO();
		Caja caja = cajaDaoImpl.findByCajaByIdUsuario(idUsuario);
		resultado = TransferDataObjectUtil.transferObjetoEntityDTO(caja,CajaDTO.class,"usuario");
		caja = null;
		return resultado;
	}

	@Override
	public List<VentaDTO> obtenerListaCajaFiltro(VentaFiltroVO filtro) throws Exception {
		List<Venta> listaVen = ventaDaoImpl.obtenerListaCajaFiltro(filtro);
		BigDecimal Valor1 = new BigDecimal("0.00");
		for(Venta venta1 : listaVen) {
			Valor1 = Valor1.add(venta1.getMontoTotal());
		} 
		List<VentaDTO> listaVent = new ArrayList<VentaDTO>();
		for(Venta venta1 : listaVen ) { 
			VentaDTO ventaDTO = TransferDataObjectUtil.transferObjetoEntityDTO(venta1,VentaDTO.class,"tipoDocSunat","itemByTipoMoneda","cliente","pedido","huesped","vehiculo");
			DetalleVentaDTO detalleVenta = new DetalleVentaDTO();
			detalleVenta.setId(ventaDTO.getIdVenta()); 
			ventaDTO.setMontoTotalItem(Valor1);
			ventaDTO.setVarCantidad(detalleVentaDaoImpl.contarListarDetalleVenta(detalleVenta));
			listaVent.add(ventaDTO);
		}
		listaVen =  null;
		return listaVent;
		//return TransferDataObjectUtil.transferObjetoEntityDTOList(this.ventaDaoImpl.listarVenta(venta),VentaDTO.class,"tipoDocSunat","itemByTipoMoneda","cliente","pedido");
	}
	
	@Override
	public PedidoDTO registrarPedidoDTO(PedidoDTO pedido) throws Exception {
		PedidoDTO resultado = null;
		Pedido resultadoEntity = null; 
		String userName = AppAuthenticator.getInstance().getUserName(pedido.getAuthToken());
		pedido.setIdEntidadSelect(pedido.getEntidad().getIdEntidad());
		if (!StringUtils.isNotNullOrBlank(pedido.getIdPedido())) {	
			if (StringUtils.isNotNullOrBlank(pedido.getTipoDocSunat().getIdItem())) {
				String nroDocCalc = tipoDocSunatEntidadDaoImpl.actualizarTipoDocSunat(pedido.getTipoDocSunat().getIdItem(),pedido.getIdEntidadSelect(),pedido.getNroDoc(),pedido.getSerie());
				if (!StringUtils.isNotNullOrBlank(pedido.getNroDoc())) {
					pedido.setNroDoc(nroDocCalc);	
				}
			}
			pedido.setIdPedido(this.pedidoDaoImpl.generarIdPedido());
			pedido.setFechaCreacion(FechaUtil.obtenerFecha());
			pedido.setFechaPedido(FechaUtil.obtenerFecha());
			pedido.setUsuarioCreacion(userName);
			pedido.setEstado(EstadoGeneralState.ACTIVO.getKey());
			resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(pedido, Pedido.class,"cliente@PK@","tipoDocSunat@PK@","itemByTipoMoneda@PK@","entidad@PK@");
			this.pedidoDaoImpl.save(resultadoEntity); 
			resultado = pedido;
		} else {
			pedido.setFechaModificacion(FechaUtil.obtenerFecha()); 
			pedido.setUsuarioModificacion(userName);
			resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(pedido, Pedido.class,"cliente@PK@","tipoDocSunat@PK@","itemByTipoMoneda@PK@","entidad@PK@");
			this.pedidoDaoImpl.update(resultadoEntity);
			resultado = pedido;	 
		}
		
		if (!CollectionUtil.isEmpty(pedido.getPedidoDetallePedidoList())) {
			for (DetallePedidoDTO detallePedido : pedido.getPedidoDetallePedidoList()) { 
				if (!detallePedido.isEsEliminado()) {
					if (StringUtils.isNullOrEmpty(detallePedido.getIdDetallePedido())) {
						controladorAccionDetallePedido(detallePedido,resultadoEntity,AccionType.CREAR);
					} else {
						controladorAccionDetallePedido(detallePedido,resultadoEntity,AccionType.MODIFICAR);
					} 
				} else {
					controladorAccionDetallePedido(detallePedido,resultadoEntity, AccionType.ELIMINAR);
				}
			}
		} 
		
	 
		
		return resultado;
	}

	
	@Override
	public String generarReportePedido(String idPedido) throws Exception {
		String fileName = UUIDUtil.generarElementUUID();
		String codigoGeneradoReporte = fileName;
		 
		Map<String, Object> parametros = new HashMap<String, Object>();
		String[] subreportes;
		subreportes = new String[0];	
		parametros.put("Id_Pedido", idPedido);
		parametros.put("ruta", ""); 
		
		NombreReporteType reporte = NombreReporteType.JR_REP_ORDEN_COMPRA;
		ParametroReporteVO parametroReporteVO = new ParametroReporteVO(parametros, null, reporte, subreportes, null, false, "", "");
		parametroReporteVO.setFormato(TipoReporteGenerarType.PDF.getKey());
		parametroReporteVO.setFileName(fileName);
		codigoGeneradoReporte = generarReporteServiceImpl.obtenerParametroReporteBigMemory(parametroReporteVO);
		return codigoGeneradoReporte;
	}
	
	
	@Override
	public void eliminarPedido(String idPedido,String userName) throws Exception {
		//Eliminado el detalle de matricula y nota 
		eliminarPedidoTemp(idPedido,true);
	}
	
	private void eliminarPedidoTemp(String idPedido, boolean isAll) throws Exception {
		if (StringUtils.isNotNullOrBlank(idPedido)) { 
			List<DetallePedido> listarDetallePedido = detallePedidoDaoImpl.listarDetallePedido(idPedido);
			for (DetallePedido detallePedidoDelete : listarDetallePedido) {
				detallePedidoDaoImpl.delete(detallePedidoDelete);
			}
			//
			if (isAll) { 
				List<Venta> listarVenta = ventaDaoImpl.listarVentaPedidoTemp(idPedido);
				if(listarVenta.size() > 0) {
					List<String> idVenta= new ArrayList<String>();
					for (Venta ventaDelete : listarVenta) {
						idVenta.add(ventaDelete.getIdVenta());
					}
					List<DetalleVenta> listarDetalleVenta = detalleVentaDaoImpl.listarDetalleVentaPedidoTemp(idVenta);
					for (DetalleVenta detalleVentaDelete : listarDetalleVenta) { 
						detalleVentaDaoImpl.delete(detalleVentaDelete);
					}
					for (Venta ventaDelete : listarVenta) {
						ventaDaoImpl.delete(ventaDelete);
					}		
				}

				Pedido matriculaEliminar = pedidoDaoImpl.find(Pedido.class, idPedido);
				pedidoDaoImpl.delete(matriculaEliminar);
			}
			
		}
	}
	
	
	@Override
	public EntregaDTO controladorAccionEntrega(EntregaDTO entrega, AccionType accionType) throws Exception {
		EntregaDTO resultado = null;
		Entrega resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				entrega.setIdEntrega(this.entregaDaoImpl.generarIdEntrega());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(entrega, Entrega.class,"pedido@PK@");
				this.entregaDaoImpl.save(resultadoEntity);	
				resultado = entrega;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(entrega, Entrega.class,"pedido@PK@");			    
				this.entregaDaoImpl.update(resultadoEntity);
				if (!CollectionUtil.isEmpty(entrega.getEntregaDetalleEntregaList())) {
					for (DetalleEntregaDTO detalleEntrega : entrega.getEntregaDetalleEntregaList()) {
						if (!detalleEntrega.isEsEliminado()) {
							if (StringUtils.isNullOrEmpty(detalleEntrega.getIdDetalleEntrega())) {
								controladorAccionDetalleEntrega(detalleEntrega,resultadoEntity,AccionType.CREAR);
								System.out.println("Creando Detalle entrega:::: ");
							} else {
								controladorAccionDetalleEntrega(detalleEntrega,resultadoEntity,AccionType.MODIFICAR);
								System.out.println("Modificando Detallesde la Venta:::: ");
							}
							
						} else {
							controladorAccionDetalleEntrega(detalleEntrega,resultadoEntity, AccionType.ELIMINAR);
						}
					}
				}
				resultado = entrega;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.entregaDaoImpl.find(Entrega.class, entrega.getIdEntrega());
				this.entregaDaoImpl.delete(resultadoEntity);
				resultado = entrega;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.entregaDaoImpl.find(Entrega.class, entrega.getIdEntrega());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,EntregaDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.entregaDaoImpl.findByNombre(entrega),EntregaDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<EntregaDTO> listarEntrega(EntregaDTO entrega) throws Exception {		
		List<Entrega> listaEntrega = entregaDaoImpl.listarEntrega(entrega);
		List<EntregaDTO> listaComp = new ArrayList<EntregaDTO>(); 
		for(Entrega objEntrega : listaEntrega ) { 
			EntregaDTO entregaDTO = TransferDataObjectUtil.transferObjetoEntityDTO(objEntrega,EntregaDTO.class,"pedido:{cliente;tipoDocSunat}");
			DetalleEntregaDTO detalleEntrega = new DetalleEntregaDTO();
			detalleEntrega.setId(objEntrega.getIdEntrega());
			List<DetalleEntregaDTO> entregaDetalleEntregaList = listarDetalleEntrega(detalleEntrega);
			entregaDTO.setEntregaDetalleEntregaList(entregaDetalleEntregaList);
			listaComp.add(entregaDTO);
		}
		listaEntrega =  null;
		return listaComp;		
	}
	@Override
	public int contarListarEntrega(EntregaDTO entrega){
		return  this.entregaDaoImpl.contarListarEntrega(entrega);
	}
	
	@Override
	public EntregaDTO registrarEntregaDTO(EntregaDTO entrega) throws Exception {
		EntregaDTO resultado = null;
		Entrega resultadoEntity = null; 
		String userName = AppAuthenticator.getInstance().getUserName(entrega.getAuthToken());		
		if (!StringUtils.isNotNullOrBlank(entrega.getIdEntrega())) {
			entrega.setIdEntrega(this.entregaDaoImpl.generarIdEntrega());			
			resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(entrega, Entrega.class);
			this.entregaDaoImpl.save(resultadoEntity); 
			resultado = entrega;
		} else {			
			resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(entrega, Entrega.class);
			this.entregaDaoImpl.update(resultadoEntity);
			resultado = entrega;	 
		}			
		if (!CollectionUtil.isEmpty(entrega.getEntregaDetalleEntregaList())) {
			for (DetalleEntregaDTO detalleEntrega : entrega.getEntregaDetalleEntregaList()) { 
				if (!detalleEntrega.isEsEliminado()) {
					if (StringUtils.isNullOrEmpty(detalleEntrega.getIdDetalleEntrega())) {
						controladorAccionDetalleEntrega(detalleEntrega,resultadoEntity,AccionType.CREAR);
					} else {
						controladorAccionDetalleEntrega(detalleEntrega,resultadoEntity,AccionType.MODIFICAR);
					} 
				} else {
					controladorAccionDetalleEntrega(detalleEntrega,resultadoEntity, AccionType.ELIMINAR);
				}
			}
		} 		
		return resultado;
	}

	
	@Override
	public String generarReporteEntrega(String idEntrega) throws Exception {
		String fileName = UUIDUtil.generarElementUUID();
		String codigoGeneradoReporte = fileName;
		 
		Map<String, Object> parametros = new HashMap<String, Object>();
		String[] subreportes;
		subreportes = new String[0];	
		parametros.put("Id_Entrega", idEntrega);
		parametros.put("ruta", ""); 
		
		NombreReporteType reporte = NombreReporteType.JR_REP_ORDEN_COMPRA;
		ParametroReporteVO parametroReporteVO = new ParametroReporteVO(parametros, null, reporte, subreportes, null, false, "", "");
		parametroReporteVO.setFormato(TipoReporteGenerarType.PDF.getKey());
		parametroReporteVO.setFileName(fileName);
		codigoGeneradoReporte = generarReporteServiceImpl.obtenerParametroReporteBigMemory(parametroReporteVO);
		return codigoGeneradoReporte;
	}
	
	
	@Override
	public void eliminarEntrega(String idEntrega,String userName) throws Exception {
		//Eliminado el detalle de matricula y nota 
		eliminarEntregaTemp(idEntrega,true);
	}
	
	private void eliminarEntregaTemp(String idEntrega, boolean isAll) throws Exception {
		if (StringUtils.isNotNullOrBlank(idEntrega)) { 
			List<DetalleEntrega> listarDetalleEntrega = detalleEntregaDaoImpl.listarDetalleEntrega(idEntrega);
			for (DetalleEntrega detalleEntregaDelete : listarDetalleEntrega) {
				detalleEntregaDaoImpl.delete(detalleEntregaDelete);
			}
			//
			if (isAll) {
				Entrega matriculaEliminar = entregaDaoImpl.find(Entrega.class, idEntrega);
				entregaDaoImpl.delete(matriculaEliminar);
			}
			
		}
	}
	
	@Override
	public DetalleEntregaDTO controladorAccionDetalleEntrega(DetalleEntregaDTO detalleEntregaDTO,Entrega entrega, AccionType accionType) throws Exception {
		DetalleEntregaDTO resultado = null;
		DetalleEntrega resultadoEntity = null;
		switch (accionType) {
			case CREAR: 
				detalleEntregaDTO.setIdDetalleEntrega(this.detalleEntregaDaoImpl.generarIdDetalleEntrega());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(detalleEntregaDTO, DetalleEntrega.class,"usuario@PK@","entrega@PK@");
				resultadoEntity.setEntrega(entrega);
				this.detalleEntregaDaoImpl.save(resultadoEntity);	
				resultado = detalleEntregaDTO;				
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(detalleEntregaDTO, DetalleEntrega.class,"usuario@PK@","entrega@PK@" );
				resultadoEntity.setEntrega(entrega);
				this.detalleEntregaDaoImpl.update(resultadoEntity);
				resultado = detalleEntregaDTO;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.detalleEntregaDaoImpl.find(DetalleEntrega.class, detalleEntregaDTO.getIdDetalleEntrega());
				this.detalleEntregaDaoImpl.delete(resultadoEntity);				
				resultado = detalleEntregaDTO;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.detalleEntregaDaoImpl.find(DetalleEntrega.class, detalleEntregaDTO.getIdDetalleEntrega());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,DetalleEntregaDTO.class);
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
	public List<DetalleEntregaDTO> listarDetalleEntrega(DetalleEntregaDTO detalleEntrega) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.detalleEntregaDaoImpl.listarDetalleEntrega(detalleEntrega),DetalleEntregaDTO.class,"usuario:{tipoUsuario}","entrega");
	}
	
	@Override
	public int contarListarDetalleEntrega(DetalleEntregaDTO detalleEntrega){
		return  this.detalleEntregaDaoImpl.contarListarDetalleEntrega(detalleEntrega);
	}
	
	
	/* (non-Javadoc)
	 * @see pe.edu.upp.siaa.nota.service.NotaServiceLocal#obtenerNotaAlumnoMariculadoOrdenadoByCiclo(pe.edu.upp.siaa.entity.Alumno)
	 */
	@Override
	public VentaGraficoVO obtenerVentaGanacias() throws Exception {
		VentaGraficoVO resultado = new VentaGraficoVO();
		List<VentaGraficoVO> listaAnho = ventaDaoImpl.obtenerVentaAnho();		
		Map<Integer,List<FormatDataGraficoGananciasVO>> anho = new HashMap<Integer,List<FormatDataGraficoGananciasVO>>();
		List<VentaAnhoVO>  datatemp = new ArrayList<VentaAnhoVO>();
		
		for (VentaGraficoVO item : listaAnho) {		
			VentaAnhoVO listAnho = new VentaAnhoVO();
			listAnho.setAnho(item.getAnho());	
			List<FormatDataGraficoGananciasVO> fgf2 = new ArrayList<FormatDataGraficoGananciasVO>();
			FormatDataGraficoGananciasVO agre = new FormatDataGraficoGananciasVO();
			Map<String,List<BigDecimal>> listaMes = ventaDaoImpl.obtenerVentaMes(item.getAnho());
			agre.setFill("start");
			agre.setLabel("Soles");
			agre.setData(listaMes.get(item.getAnho()));
			fgf2.add(agre);			
			anho.put( Integer.parseInt(item.getAnho()), fgf2);			
			datatemp.add(listAnho);
			
		}	
		
		List<TendenciasVO> listTendencias = ventaDaoImpl.obtenerDataTendencias();
		List<TendenciasVO>  dataTendenciatemp = new ArrayList<TendenciasVO>();
		for (TendenciasVO itenTemp : listTendencias) {	
			TendenciasVO objetemp = new TendenciasVO();
			objetemp.setCount(itenTemp.getCount());
			objetemp.setNombre(itenTemp.getNombre());
			objetemp.setColor(itenTemp.getColor());
			objetemp.setIcon(itenTemp.getIcon());
			dataTendenciatemp.add(objetemp);
			
		}
		resultado.setDataTendencias(dataTendenciatemp);
		resultado.setDatasets(anho);
		resultado.setDatatemp(datatemp);
		resultado.setNumT(this.personalDaoImpl.contarListarPersonal(new PersonalDTO()));
		resultado.setNumC(this.asociadoDaoImpl.contarListarAsociado(new AsociadoDTO()));
		resultado.setChartType("line");
		return resultado;
	}
	
	
	@Override
	public ClienteDTO findAlumnoByCliente(String idCliente) throws Exception {
		ClienteDTO resultado = new ClienteDTO();
		Cliente cliente = clienteDaoImpl.findAlumnoByCliente(idCliente);
		resultado = TransferDataObjectUtil.transferObjetoEntityDTO(cliente,ClienteDTO.class,"itemByTipoDocumentoIdentidad","itemByCategoriaCliente");
		cliente = null;
		return resultado;
	}

	@Override
	public ClienteDTO findAlumnoByClienteNro(String nroDoc) throws Exception {
		ClienteDTO resultado = new ClienteDTO();
		Cliente cliente = clienteDaoImpl.findAlumnoByClienteNro(nroDoc);
		resultado = TransferDataObjectUtil.transferObjetoEntityDTO(cliente,ClienteDTO.class,"itemByTipoDocumentoIdentidad","itemByCategoriaCliente");
		cliente = null;
		return resultado;
	}
	
	@Override
	public List<VentaFiltroVO> optenerListaIngresoDetallado(Date fechaInicio, Date fechaFin) throws Exception {				
		List<VentaFiltroVO> listaTemp = this.ventaDaoImpl.obtenerIngresoDetalladoMap(fechaInicio, fechaFin);	
		return listaTemp;
	}
	
	
	
	@Override
	public List<DetalleVentaFiltroVO> optenerListaDetalleVenta(List<Long> listadoIdProdcutos, Date fechaInicio, Date fechaFin) throws Exception {				
		List<DetalleVentaFiltroVO> listaTemp = this.detalleVentaDaoImpl.obtenerDetallVentaMap(listadoIdProdcutos, fechaInicio, fechaFin);	
		return listaTemp;
	}
	
	@Override
	public  String generarReporVentasFechaByExcelByProducto(VentaFiltroVO filter) throws Exception {
		String fileName = UUIDUtil.generarElementUUID();		 
		 
		List<DetalleVentaFiltroVO> listadoVentasFecha = this.optenerListaDetalleVenta(filter.getListadoIds(),filter.getFechaInicio(),filter.getFechaFin());			
		String archivoName = fileName;
		String titulo = "VENTAS_FECHAS_PRODUCTOS" ;
		if (listadoVentasFecha != null) {
			
			Map<String, Object> propiedadesMap = new HashMap<String, Object>();
			propiedadesMap.put("calcularWitchDemanda", "true");
			propiedadesMap.put("exluirCabecera", "true");
			propiedadesMap.put("nombreArchivo", "formato_ingreso_detallado.xlsx");
			propiedadesMap.put("anexarHojaPosition",1);
			propiedadesMap.put("printTitleView", "printTitleView");

			
			List<ExcelHederDataVO> listaHeaderData = new ArrayList<ExcelHederDataVO>();
			//String nameHeader, String nameAtribute				
			listaHeaderData.add(new ExcelHederDataVO("FECHA EM","fechaVenta"));//C
			//listaHeaderData.add(new ExcelHederDataVO("HORA EMISION","venta.usuarioCreacion"));//C
			/*COMPROBANTE DE PAGO O DOCUMENTO*/
			listaHeaderData.add(new ExcelHederDataVO("NRO SERIE","serie"));//F
			listaHeaderData.add(new ExcelHederDataVO("NUMEROXXXXXXXXX","nroComprobante"));//F				
			/*INFORMACION DEL CLIENTE */
			listaHeaderData.add(new ExcelHederDataVO("DescripcionProducto","descipcionProducto"));//F
			listaHeaderData.add(new ExcelHederDataVO("MONTOXXXXXX","unidadMedida"));//F 
			listaHeaderData.add(new ExcelHederDataVO("MONTOXXXXXX","precioUnitario"));//F 
			
			listaHeaderData.add(new ExcelHederDataVO("MONTOXXXXXX","cantidad"));//F 
			listaHeaderData.add(new ExcelHederDataVO("MONTOXXXXXX","montoTotal"));//F 
			
			List<ExcelHederTitleVO> listaExcelHederTitle = new ArrayList<ExcelHederTitleVO>();
			listaExcelHederTitle.add(new ExcelHederTitleVO("FECHA VENTA", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),1,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex()));
		//	listaExcelHederTitle.add(new ExcelHederTitleVO("USUARIO VENTA", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),2,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex()));
			listaExcelHederTitle.add(new ExcelHederTitleVO("SERIE", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),2,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex()));
			listaExcelHederTitle.add(new ExcelHederTitleVO("NRO COMPROBANTE", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),3,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex()));
			listaExcelHederTitle.add(new ExcelHederTitleVO("DESCRIPCION PRODUCTO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),4,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex()));				
        	listaExcelHederTitle.add(new ExcelHederTitleVO("UNIDAD MEDIDA", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),5,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex()));	
        	listaExcelHederTitle.add(new ExcelHederTitleVO("PRECIO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),6,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex()));	
        	listaExcelHederTitle.add(new ExcelHederTitleVO("CANTIDAD", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),7,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex()));	
        	listaExcelHederTitle.add(new ExcelHederTitleVO("MONTO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),8,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex()));	
        				 
			propiedadesMap.put("listaTituloFinal", listaExcelHederTitle);//para crear con esta lista
			
			DataExportExcelPersonalizadoUtil.generarExcelXLSX(listaHeaderData, listadoVentasFecha, archivoName, titulo, propiedadesMap);
			
		}
		DataExportExcelPersonalizadoUtil.generarExcelXLSXViewMap(archivoName);
		return fileName;
	}
	
	@Override
	public  String generarReporteIngresoDetalladoByExcel(VentaFiltroVO filter) throws Exception {			
		String fecha = FechaUtil.obtenerFechaFormatoPersonalizado(filter.getFechaInicio(), "dd-MM-yyyy")+ "_" +FechaUtil.obtenerFechaFormatoPersonalizado(filter.getFechaFin(), "dd-MM-yyyy");
		String fileName = fecha;//UUIDUtil.generarElementUUID();
		String titulo = "REP_VENT(" +fecha+")" ;
		
		List<VentaFiltroVO> listarControlVentaByExcel = this.optenerListaIngresoDetallado(filter.getFechaInicio(), filter.getFechaFin());			
			String archivoName = fileName;
		if (listarControlVentaByExcel != null) {																																																																																																																																																														
		
			Map<String, Object> propiedadesMap = new HashMap<String, Object>();
			propiedadesMap.put("calcularWitchDemanda", "true");
			propiedadesMap.put("exluirCabecera", "true");
			propiedadesMap.put("nombreArchivo", "formato_ingreso_detallado.xlsx");
			propiedadesMap.put("anexarHojaPosition",1);
			propiedadesMap.put("printTitleView", "printTitleView");

			
			List<ExcelHederDataVO> listaHeaderData = new ArrayList<ExcelHederDataVO>();
			//String nameHeader, String nameAtribute				
			listaHeaderData.add(new ExcelHederDataVO("FECHA EMISION","fechaPago"));//C
			/*COMPROBANTE DE PAGO O DOCUMENTO*/
			listaHeaderData.add(new ExcelHederDataVO("TIPO compXxx","tipoComprobante"));//C
			listaHeaderData.add(new ExcelHederDataVO("NRO SERIE","serie"));//F
			listaHeaderData.add(new ExcelHederDataVO("NUMEROXXXXXXXXX","nroComprobante"));//F				
			/*INFORMACION DEL CLIENTE */
			listaHeaderData.add(new ExcelHederDataVO("TIPO DOCXxx","tipoDoc"));//F
			listaHeaderData.add(new ExcelHederDataVO("NUMEROXXXXXXXXX","nroDoc"));//F
			listaHeaderData.add(new ExcelHederDataVO("Nombrexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx","clienteNombre"));//F 
			
			listaHeaderData.add(new ExcelHederDataVO("MONTOXXXXXX","montoTotal"));//F 
			
			List<ExcelHederTitleVO> listaExcelHederTitle = new ArrayList<ExcelHederTitleVO>();
			listaExcelHederTitle.add(new ExcelHederTitleVO("FECHA VENTA", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),1,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex(),0,5));
			listaExcelHederTitle.add(new ExcelHederTitleVO("TIPO COMPROBANTE", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),2,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex()));
			listaExcelHederTitle.add(new ExcelHederTitleVO("SERIE", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),3,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex(),2,8));
			listaExcelHederTitle.add(new ExcelHederTitleVO("NRO COMPROBANTE", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),4,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex(),3,15));
			listaExcelHederTitle.add(new ExcelHederTitleVO("TIPO DOCUMENTO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),5,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex()));				
			listaExcelHederTitle.add(new ExcelHederTitleVO("N� DOCUMENTO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),6,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex(),5,10));
			listaExcelHederTitle.add(new ExcelHederTitleVO("CLIENTE", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),7,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex(),6,1));	
			listaExcelHederTitle.add(new ExcelHederTitleVO("MONTO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),8,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex()));	
					 
			propiedadesMap.put("listaTituloFinal", listaExcelHederTitle);//para crear con esta lista
			
			DataExportExcelPersonalizadoUtil.generarExcelXLSX(listaHeaderData, listarControlVentaByExcel, archivoName, titulo, propiedadesMap);
			
		}
		DataExportExcelPersonalizadoUtil.generarExcelXLSXViewMap(archivoName);
		return fileName;
	}
	
	
	@Override
	public  String generarReporVentasFechaByExcel(VentaFiltroVO filter) throws Exception {
		String fileName = UUIDUtil.generarElementUUID();			
		VentaDTO detVenta = new VentaDTO();
		detVenta.setFechaFin(FechaUtil.sumarDias(filter.getFechaFin(),1));
		detVenta.setFechaInicio(filter.getFechaInicio());
		List<VentaDTO> listadoVentasFecha = this.listarVenta(detVenta);			
		String archivoName = fileName;
		String titulo = "VENTAS_FECHAS" ;
		if (listadoVentasFecha != null) {
			
			/*for(VentaDTO obj : listadoVentasFecha) {
				obj.setHora(obj.getVenta().getFechaVenta().getHours());
			}*/
			
			Map<String, Object> propiedadesMap = new HashMap<String, Object>();
			propiedadesMap.put("calcularWitchDemanda", "true");
			propiedadesMap.put("exluirCabecera", "true");
			propiedadesMap.put("nombreArchivo", "formato_ingreso_detallado.xlsx");
			propiedadesMap.put("anexarHojaPosition",1);
			propiedadesMap.put("printTitleView", "printTitleView");

			
			List<ExcelHederDataVO> listaHeaderData = new ArrayList<ExcelHederDataVO>();
			//String nameHeader, String nameAtribute				
			listaHeaderData.add(new ExcelHederDataVO("FECHA EM","fechaVenta"));//C
			listaHeaderData.add(new ExcelHederDataVO("HORA EMISION","usuarioCreacion"));//C
			/*COMPROBANTE DE PAGO O DOCUMENTO*/
			listaHeaderData.add(new ExcelHederDataVO("NRO SERIE","serie"));//F
			listaHeaderData.add(new ExcelHederDataVO("NUMEROXXXXXXXXX","nroDoc"));//F				
			/*INFORMACION DEL CLIENTE */
			//listaHeaderData.add(new ExcelHederDataVO("DescripcionProducto","descripcionProducto"));//F
			//listaHeaderData.add(new ExcelHederDataVO("MONTOXXXXXX","detalleProducto.itemByUnidadMedida.nombre"));//F 
			listaHeaderData.add(new ExcelHederDataVO("MONTOXXXXXX","montoTotal"));//F 
			
			List<ExcelHederTitleVO> listaExcelHederTitle = new ArrayList<ExcelHederTitleVO>();
			listaExcelHederTitle.add(new ExcelHederTitleVO("FECHA VENTA", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),1,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex()));
			listaExcelHederTitle.add(new ExcelHederTitleVO("USUARIO VENTA", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),2,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex()));
			listaExcelHederTitle.add(new ExcelHederTitleVO("SERIE", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),3,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex()));
			listaExcelHederTitle.add(new ExcelHederTitleVO("NRO COMPROBANTE", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),4,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex()));
			//listaExcelHederTitle.add(new ExcelHederTitleVO("DESCRIPCION PRODUCTO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),5,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex()));				
        	//listaExcelHederTitle.add(new ExcelHederTitleVO("UNIDAD MEDIDA", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),6,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex()));	
         	listaExcelHederTitle.add(new ExcelHederTitleVO("MONTO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),5,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex()));	
        				 
			propiedadesMap.put("listaTituloFinal", listaExcelHederTitle);//para crear con esta lista
			
			DataExportExcelPersonalizadoUtil.generarExcelXLSX(listaHeaderData, listadoVentasFecha, archivoName, titulo, propiedadesMap);
			
		}
		DataExportExcelPersonalizadoUtil.generarExcelXLSXViewMap(archivoName);
		return fileName;
	}
	
	
	@Override
	public  String descargarProductoVendidoFecha(VentaFiltroVO filter) throws Exception {
		String fileName = UUIDUtil.generarElementUUID();			

		List<DetalleVentaFiltroVO> listadoProdsuctoFiltro = this.detalleVentaDaoImpl.obtenerProductoVendidos(filter);			
		String archivoName = fileName;
		String titulo = "VENTAS_PRODUCTOS_CANTIDAD" ;
		if (listadoProdsuctoFiltro != null) {
			
			/*for(VentaDTO obj : listadoVentasFecha) {
				obj.setHora(obj.getVenta().getFechaVenta().getHours());
			}*/
			
			Map<String, Object> propiedadesMap = new HashMap<String, Object>();
			propiedadesMap.put("calcularWitchDemanda", "true");
			propiedadesMap.put("exluirCabecera", "true");
			propiedadesMap.put("nombreArchivo", "formato_ingreso_detallado.xlsx");
			propiedadesMap.put("anexarHojaPosition",1);
			propiedadesMap.put("printTitleView", "printTitleView");

			
			List<ExcelHederDataVO> listaHeaderData = new ArrayList<ExcelHederDataVO>();
			//String nameHeader, String nameAtribute				
			listaHeaderData.add(new ExcelHederDataVO("DESCRIPCIPPRODUCTO","descipcionProducto"));//C
			listaHeaderData.add(new ExcelHederDataVO("UNIDADMEDIDAD","unidadMedida"));//C
			/*COMPROBANTE DE PAGO O DOCUMENTO*/
			listaHeaderData.add(new ExcelHederDataVO("CANTIDAD","cantidad"));//F 			
			/*INFORMACION DEL CLIENTE */
			//listaHeaderData.add(new ExcelHederDataVO("DescripcionProducto","descripcionProducto"));//F
			listaHeaderData.add(new ExcelHederDataVO("PRECIOXXXXXX","precioUnitario"));//F 
			listaHeaderData.add(new ExcelHederDataVO("MONTOXXXXXX","montoTotal"));//F 
			
			List<ExcelHederTitleVO> listaExcelHederTitle = new ArrayList<ExcelHederTitleVO>();
			listaExcelHederTitle.add(new ExcelHederTitleVO("DECRIPCION PRODCUTO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),1,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex()));
			listaExcelHederTitle.add(new ExcelHederTitleVO("UNIDAD MEDIDAD", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),2,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex()));
			listaExcelHederTitle.add(new ExcelHederTitleVO("CANTIDAD", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),3,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex()));
			listaExcelHederTitle.add(new ExcelHederTitleVO("PRECIO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),4,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex()));
			//listaExcelHederTitle.add(new ExcelHederTitleVO("DESCRIPCION PRODUCTO", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),5,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex()));				
        	//listaExcelHederTitle.add(new ExcelHederTitleVO("UNIDAD MEDIDA", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),6,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex()));	
         	listaExcelHederTitle.add(new ExcelHederTitleVO("MONTO TOTAL", HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),5,1,0,0,true,IndexedColors.LIGHT_BLUE.getIndex(),IndexedColors.WHITE.getIndex()));	
        				 
			propiedadesMap.put("listaTituloFinal", listaExcelHederTitle);//para crear con esta lista
			
			DataExportExcelPersonalizadoUtil.generarExcelXLSX(listaHeaderData, listadoProdsuctoFiltro, archivoName, titulo, propiedadesMap);
			
		}
		DataExportExcelPersonalizadoUtil.generarExcelXLSXViewMap(archivoName);
		return fileName;
	}
}