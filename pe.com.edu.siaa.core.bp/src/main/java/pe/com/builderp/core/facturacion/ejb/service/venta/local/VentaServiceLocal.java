package pe.com.builderp.core.facturacion.ejb.service.venta.local;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.contabilidad.model.vo.RegistroAsientoFiltroVO;
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
import pe.com.builderp.core.facturacion.model.jpa.venta.DetalleProducto;
import pe.com.builderp.core.facturacion.model.jpa.venta.Entrega;
import pe.com.builderp.core.facturacion.model.jpa.venta.Pedido;
import pe.com.builderp.core.facturacion.model.jpa.venta.Producto;
import pe.com.builderp.core.facturacion.model.jpa.venta.Venta;
import pe.com.builderp.core.facturacion.model.vo.venta.VentaGraficoVO;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.model.vo.DetalleVentaFiltroVO;
import pe.com.edu.siaa.core.model.vo.SunatDatosVO;
import pe.com.edu.siaa.core.model.vo.VentaFiltroVO;

/**
 * La Class VentaServiceLocal.
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
public interface VentaServiceLocal{
	
	void registrarProducto(ProductoDTO Producto) throws Exception;
	
	String generarReporteProductoCodigoBarra(ProductoDTO productoFiltro) throws Exception;
	
	List<DetalleVentaDTO> listarDetalleVenta(DetalleVentaDTO detalleVenta) throws Exception;
	
	void updateOferta() throws Exception;
	
	String generarReporteRegistroVentaTXT(RegistroAsientoFiltroVO registroVentaFiltro) throws Exception;
	
	String generarReporteRegistroVenta(RegistroAsientoFiltroVO registroVentaFiltro) throws Exception;
	
	 DetalleProductoDTO controladorAccionDetalleProducto(DetalleProductoDTO detalleProducto,Producto producto, AccionType accionType) throws Exception; 
	
	 DetalleProductoDTO findByDetalleProductoIdProducto(Long idProducto) throws Exception;
		/**
		 * Listar DetalleProducto.
		 *
		 * @param DetalleProducto el DetalleProducto
		 * @return the list
		 * @throws Exception the exception
		 */
		List<DetalleProductoDTO> listarDetalleProducto(DetalleProductoDTO detalleProducto) throws Exception;
		
		List<DetalleProductoDTO> listarDetalleProductoBy(Long idProducto) throws Exception;
		/**
		 * contar lista DetalleProducto.
		 *
		 * @param DetalleProducto el DetalleProducto
		 * @return the list
		 * @throws Exception the exception
		 */
		int contarListarDetalleProducto(DetalleProductoDTO detalleProducto);
		
		
		
		
		DetalleProductoDTO findByDetalleProducto(String codigo) throws Exception;
		
		List<DetalleProductoDTO> listarDetalleProductoCombo(DetalleProductoDTO detalleProducto) throws Exception;
	
	/**
	 * Controlador accion categoria.
	 *
	 * @param categoria el categoria
	 * @param accionType el accion type
	 * @return the categoria
	 * @throws Exception the exception
	 */
	CategoriaDTO controladorAccionCategoria(CategoriaDTO categoria,AccionType accionType) throws Exception; 
	
	/**
	 * Listar categoria.
	 *
	 * @param categoria el categoria
	 * @return the list
	 * @throws Exception the exception
	 */
	List<CategoriaDTO> listarCategoria(CategoriaDTO categoria) throws Exception;
	
	/**
	 * contar lista categoria.
	 *
	 * @param categoria el categoria
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarCategoria(CategoriaDTO categoria);
	
	/**
	 * Controlador accion cliente.
	 *
	 * @param cliente el cliente
	 * @param accionType el accion type
	 * @return the cliente
	 * @throws Exception the exception
	 */
	ClienteDTO controladorAccionCliente(ClienteDTO cliente,AccionType accionType) throws Exception; 
	
	/**
	 * Listar cliente.
	 *
	 * @param cliente el cliente
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ClienteDTO> listarCliente(ClienteDTO cliente) throws Exception;
	
	/**
	 * contar lista cliente.
	 *
	 * @param cliente el cliente
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarCliente(ClienteDTO cliente);
	
	/**
	 * Controlador accion configuracion atributo.
	 *
	 * @param configuracionAtributo el configuracion atributo
	 * @param accionType el accion type
	 * @return the configuracion atributo
	 * @throws Exception the exception
	 */
	ConfiguracionAtributoDTO controladorAccionConfiguracionAtributo(ConfiguracionAtributoDTO configuracionAtributo,AccionType accionType) throws Exception; 
	
	/**
	 * Listar configuracion atributo.
	 *
	 * @param configuracionAtributo el configuracion atributo
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ConfiguracionAtributoDTO> listarConfiguracionAtributo(ConfiguracionAtributoDTO configuracionAtributo) throws Exception;
	
	/**
	 * contar lista configuracion atributo.
	 *
	 * @param configuracionAtributo el configuracion atributo
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarConfiguracionAtributo(ConfiguracionAtributoDTO configuracionAtributo);
	
	/**
	 * Controlador accion configuracion atributo value.
	 *
	 * @param configuracionAtributoValue el configuracion atributo value
	 * @param accionType el accion type
	 * @return the configuracion atributo value
	 * @throws Exception the exception
	 */
	ConfiguracionAtributoValueDTO controladorAccionConfiguracionAtributoValue(ConfiguracionAtributoValueDTO configuracionAtributoValue,AccionType accionType) throws Exception; 
	
	/**
	 * Listar configuracion atributo value.
	 *
	 * @param configuracionAtributoValue el configuracion atributo value
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ConfiguracionAtributoValueDTO> listarConfiguracionAtributoValue(ConfiguracionAtributoValueDTO configuracionAtributoValue) throws Exception;
	
	/**
	 * contar lista configuracion atributo value.
	 *
	 * @param configuracionAtributoValue el configuracion atributo value
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarConfiguracionAtributoValue(ConfiguracionAtributoValueDTO configuracionAtributoValue);
	
	/**
	 * Controlador accion detalle pedido.
	 *
	 * @param detallePedido el detalle pedido
	 * @param accionType el accion type
	 * @return the detalle pedido
	 * @throws Exception the exception
	 */
	DetallePedidoDTO controladorAccionDetallePedido(DetallePedidoDTO detallePedidoDTO,Pedido pedido, AccionType accionType) throws Exception;
	//DetallePedidoDTO controladorAccionDetallePedido(DetallePedidoDTO detallePedido,AccionType accionType) throws Exception; 
	
	/**
	 * Listar detalle pedido.
	 *
	 * @param detallePedido el detalle pedido
	 * @return the list
	 * @throws Exception the exception
	 */
	List<DetallePedidoDTO> listarDetallePedido(DetallePedidoDTO detallePedido) throws Exception;
	
	/**
	 * contar lista detalle pedido.
	 *
	 * @param detallePedido el detalle pedido
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarDetallePedido(DetallePedidoDTO detallePedido);
	
	/**
	 * Controlador accion detalle proforma.
	 *
	 * @param detalleProforma el detalle proforma
	 * @param accionType el accion type
	 * @return the detalle proforma
	 * @throws Exception the exception
	 */
	//DetalleProformaDTO controladorAccionDetalleProforma(DetalleProformaDTO detalleProforma,AccionType accionType) throws Exception; 
	
	/**
	 * Listar detalle proforma.
	 *
	 * @param detalleProforma el detalle proforma
	 * @return the list
	 * @throws Exception the exception
	 */
	List<DetalleProformaDTO> listarDetalleProforma(DetalleProformaDTO detalleProforma) throws Exception;
	

	
	/**
	 * contar lista detalle proforma.
	 *
	 * @param detalleProforma el detalle proforma
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarDetalleProforma(DetalleProformaDTO detalleProforma);
	
	
	/**
	 * Controlador accion linea.
	 *
	 * @param linea el linea
	 * @param accionType el accion type
	 * @return the linea
	 * @throws Exception the exception
	 */
	LineaDTO controladorAccionLinea(LineaDTO linea,AccionType accionType) throws Exception; 
	
	/**
	 * Listar linea.
	 *
	 * @param linea el linea
	 * @return the list
	 * @throws Exception the exception
	 */
	List<LineaDTO> listarLinea(LineaDTO linea) throws Exception;
	
	/**
	 * contar lista linea.
	 *
	 * @param linea el linea
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarLinea(LineaDTO linea);
	
	/**
	 * Controlador accion marca.
	 *
	 * @param marca el marca
	 * @param accionType el accion type
	 * @return the marca
	 * @throws Exception the exception
	 */
	MarcaDTO controladorAccionMarca(MarcaDTO marca,AccionType accionType) throws Exception; 
	
	/**
	 * Listar marca.
	 *
	 * @param marca el marca
	 * @return the list
	 * @throws Exception the exception
	 */
	List<MarcaDTO> listarMarca(MarcaDTO marca) throws Exception;
	
	/**
	 * contar lista marca.
	 *
	 * @param marca el marca
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarMarca(MarcaDTO marca);
	
	/**
	 * Controlador accion modelo.
	 *
	 * @param modelo el modelo
	 * @param accionType el accion type
	 * @return the modelo
	 * @throws Exception the exception
	 */
	ModeloDTO controladorAccionModelo(ModeloDTO modelo,AccionType accionType) throws Exception; 
	
	/**
	 * Listar modelo.
	 *
	 * @param modelo el modelo
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ModeloDTO> listarModelo(ModeloDTO modelo) throws Exception;
	
	/**
	 * contar lista modelo.
	 *
	 * @param modelo el modelo
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarModelo(ModeloDTO modelo);
	
	/**
	 * Controlador accion pedido.
	 *
	 * @param pedido el pedido
	 * @param accionType el accion type
	 * @return the pedido
	 * @throws Exception the exception
	 */
	PedidoDTO controladorAccionPedido(PedidoDTO pedido,AccionType accionType) throws Exception; 
	
	/**
	 * Listar pedido.
	 *
	 * @param pedido el pedido
	 * @return the list
	 * @throws Exception the exception
	 */
	List<PedidoDTO> listarPedido(PedidoDTO pedido) throws Exception;
	
	/**
	 * contar lista pedido.
	 *
	 * @param pedido el pedido
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarPedido(PedidoDTO pedido);
	
	/**
	 * Controlador accion producto.
	 *
	 * @param producto el producto
	 * @param accionType el accion type
	 * @return the producto
	 * @throws Exception the exception
	 */
	ProductoDTO controladorAccionProducto(ProductoDTO producto,AccionType accionType) throws Exception; 
	
	/**
	 * Listar producto.
	 *
	 * @param producto el producto
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ProductoDTO> listarProducto(ProductoDTO producto) throws Exception;
	
	/**
	 * contar lista producto.
	 *
	 * @param producto el producto
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarProducto(ProductoDTO producto);
	
	/**
	 * Controlador accion proforma.
	 *
	 * @param proforma el proforma
	 * @param accionType el accion type
	 * @return the proforma
	 * @throws Exception the exception
	 */
	//ProformaDTO controladorAccionProforma(ProformaDTO proforma,AccionType accionType) throws Exception; 
	
	/**
	 * Listar proforma.
	 *
	 * @param proforma el proforma
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ProformaDTO> listarProforma(ProformaDTO proforma) throws Exception;
	
	/**
	 * contar lista proforma.
	 *
	 * @param proforma el proforma
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarProforma(ProformaDTO proforma);
	
	/**
	 * Controlador accion tipo doc sunat entidad.
	 *
	 * @param tipoDocSunatEntidad el tipo doc sunat entidad
	 * @param accionType el accion type
	 * @return the tipo doc sunat entidad
	 * @throws Exception the exception
	 */
	TipoDocSunatEntidadDTO controladorAccionTipoDocSunatEntidad(TipoDocSunatEntidadDTO tipoDocSunatEntidad,AccionType accionType) throws Exception; 
	
	/**
	 * Listar tipo doc sunat entidad.
	 *
	 * @param tipoDocSunatEntidad el tipo doc sunat entidad
	 * @return the list
	 * @throws Exception the exception
	 */
	List<TipoDocSunatEntidadDTO> listarTipoDocSunatEntidad(TipoDocSunatEntidadDTO tipoDocSunatEntidad) throws Exception;
	
	/**
	 * contar lista tipo doc sunat entidad.
	 *
	 * @param tipoDocSunatEntidad el tipo doc sunat entidad
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarTipoDocSunatEntidad(TipoDocSunatEntidadDTO tipoDocSunatEntidad);
	
	/**
	 * Controlador accion venta.
	 *
	 * @param venta el venta
	 * @param accionType el accion type
	 * @return the venta
	 * @throws Exception the exception
	 */
	VentaDTO registrarVenta(VentaDTO venta) throws Exception; 
	
	VentaDTO registrarVentaManual(VentaDTO venta) throws Exception; 
	
	ProformaDTO registrarProforma(ProformaDTO proforma) throws Exception;
	
	/**
	 * Listar venta.
	 *
	 * @param venta el venta
	 * @return the list
	 * @throws Exception the exception
	 */
	List<VentaDTO> listarVenta(VentaDTO venta) throws Exception;
	
	/**
	 * contar lista venta.
	 *
	 * @param venta el venta
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarVenta(VentaDTO venta);
	
	String generarReporteVenta(VentaDTO venta,String usuario) throws Exception;
	
	String generarReporteVentaBase64(VentaDTO venta,String usuario) throws Exception;
	
	String generarReporteVentaA4(VentaDTO venta,String usuario) throws Exception;
	
	String generarReporteVistaPrevia(List<VentaFiltroVO> ventaFiltro,String usuario) throws Exception;
	
	List<DetalleVentaDTO> verDetalleVentasRealizadas(String idVenta) throws Exception;
	
	VentaDTO anularVenta(VentaDTO venta) throws Exception ;
	
	List<VentaDTO> listarVentaReporte(VentaDTO venta) throws Exception;
	
	List<ProformaDTO> listarProformaReporte(ProformaDTO proforma) throws Exception;
	
	void updateEstadoProforma() throws Exception;
	
	List<DetalleProformaDTO> verDetalleProformasRealizadas(String idProforma) throws Exception;
	
	String generarReporteProforma(String idProforma,String idCliente,String nroDoc,String usuario) throws Exception;
	
	DetalleVentaDTO controladorAccionDetalleVenta(DetalleVentaDTO detalleVenta,Venta venta, AccionType accionType) throws Exception;
	 
	
	/**
	 * Controlador accion guia remision.
	 *
	 * @param guiaRemision el guia remision
	 * @param accionType el accion type
	 * @return the guia remision
	 * @throws Exception the exception
	 */
	GuiaRemisionDTO controladorAccionGuiaRemision(GuiaRemisionDTO guiaRemision,AccionType accionType) throws Exception; 
	
	/**
	 * Listar guia remision.
	 *
	 * @param guiaRemision el guia remision
	 * @return the list
	 * @throws Exception the exception
	 */
	List<GuiaRemisionDTO> listarGuiaRemision(GuiaRemisionDTO guiaRemision) throws Exception;
	
	/**
	 * contar lista guia remision.
	 *
	 * @param guiaRemision el guia remision
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarGuiaRemision(GuiaRemisionDTO guiaRemision);
	
	/**
	 * Controlador accion vehiculo.
	 *
	 * @param vehiculo el vehiculo
	 * @param accionType el accion type
	 * @return the vehiculo
	 * @throws Exception the exception
	 */
	VehiculoDTO controladorAccionVehiculo(VehiculoDTO vehiculo,AccionType accionType) throws Exception; 
	
	/**
	 * Listar vehiculo.
	 *
	 * @param vehiculo el vehiculo
	 * @return the list
	 * @throws Exception the exception
	 */
	List<VehiculoDTO> listarVehiculo(VehiculoDTO vehiculo) throws Exception;
	
	/**
	 * contar lista vehiculo.
	 *
	 * @param vehiculo el vehiculo
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarVehiculo(VehiculoDTO vehiculo);
	
	/**
	 * Controlador accion transportista.
	 *
	 * @param transportista el transportista
	 * @param accionType el accion type
	 * @return the transportista
	 * @throws Exception the exception
	 */
	TransportistaDTO controladorAccionTransportista(TransportistaDTO transportista,AccionType accionType) throws Exception; 
	
	/**
	 * Listar transportista.
	 *
	 * @param transportista el transportista
	 * @return the list
	 * @throws Exception the exception
	 */
	List<TransportistaDTO> listarTransportista(TransportistaDTO transportista) throws Exception;
	
	/**
	 * contar lista transportista.
	 *
	 * @param transportista el transportista
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarTransportista(TransportistaDTO transportista);
	
	
	//add
	
	String generarReporteGuia(GuiaRemisionDTO idGuia) throws Exception;
	
	VentaDTO findVenta(String idVenta) throws Exception;
	
	String generarReporteMultiple(String tipoReporte, Date fechaInicio, Date fechaFin,String usuarioCrea,String serviceKey,String authToken) throws Exception;
	//add
	List<SunatDatosVO> generarExtracionTXT() throws Exception;
	
	 List<VentaDTO> listaControlPagoExtracionF() throws Exception;
	 
	List<SunatDatosVO> generarComprobante(SunatDatosVO sfs) throws Exception;
	
	List<SunatDatosVO> enviarComprobante(SunatDatosVO sfs) throws Exception;
	
	 List<SunatDatosVO> actualizarBandeja() throws Exception;
	 
	List<SunatDatosVO> eliminarBandeja() throws Exception;
	
	/***  
	 * @param Caja
	 * @param accionType
	 * @return
	 * @throws Exception
	 */
	CajaDTO controladorAccionCaja(CajaDTO Caja,AccionType accionType) throws Exception; 

	List<CajaDTO> listarCaja(CajaDTO Caja) throws Exception;
	
	int contarListarCaja(CajaDTO Caja);
	
	String cerrarCaja(CajaDTO caja)throws Exception;
	
	String reporteCaja(String idUsuario,Date fechaCreacion,String idCaja) throws Exception;
	
	
	String updateVentaCierre(CajaDTO caja) throws Exception;
	
	String iniciarAperturaCaja() throws Exception;

	CajaDTO findByCaja(CajaDTO cajaTemp) throws Exception;
	
	 CajaDTO findByCajaByIdUsuario(String idUsuario) throws Exception ;
	
	List<VentaDTO> obtenerListaCajaFiltro(VentaFiltroVO filtro) throws Exception;
	
	List<VentaFiltroVO> optenerListaIngresoDetallado(Date fechaInicio, Date fechaFin) throws Exception;
	
	String generarReporteIngresoDetalladoByExcel(VentaFiltroVO filter) throws Exception;
	
	String generarReporVentasFechaByExcel(VentaFiltroVO filter) throws Exception;
	
	List<DetalleVentaFiltroVO> optenerListaDetalleVenta(List<Long> listadoIdProdcutos, Date fechaInicio, Date fechaFin) throws Exception;
	
	String generarReporVentasFechaByExcelByProducto(VentaFiltroVO filter) throws Exception;
	
	String descargarProductoVendidoFecha(VentaFiltroVO filter) throws Exception;
	/**
	 *  
	 * @param productoProveedor
	 * @param producto
	 * @param accionType
	 * @return
	 * @throws Exception
	 */
	
    ProductoProveedorDTO controladorAccionProductoProveedor(ProductoProveedorDTO productoProveedor,Producto producto, AccionType accionType) throws Exception; 
	
	/**
	 * Listar ProductoProveedor.
	 *
	 * @param ProductoProveedor el ProductoProveedor
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ProductoProveedorDTO> listarProductoProveedor(ProductoProveedorDTO productoProveedor) throws Exception;
	
	/**
	 * contar lista ProductoProveedor.
	 *
	 * @param ProductoProveedor el ProductoProveedor
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarProductoProveedor(ProductoProveedorDTO productoProveedor);
	
	ComboDTO controladorAccionCombo(ComboDTO Combo,DetalleProducto detalleProducto, AccionType accionType) throws Exception; 
	
	ComboDTO findByComboIdDetalleProducto(Long idDetalleProducto) throws Exception;
	
	/**
	 * Listar DetalleProducto.
	 *
	 * @param DetalleProducto el DetalleProducto
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ComboDTO> listarCombo(ComboDTO combo) throws Exception;
	
	List<ComboDTO> listarComboBy(String idDetalleProducto) throws Exception;
	/**
	 * contar lista DetalleProducto.
	 *
	 * @param DetalleProducto el DetalleProducto
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarCombo(ComboDTO combo);
	
	PedidoDTO registrarPedidoDTO(PedidoDTO pedido) throws Exception;
	String generarReportePedido(String idPedido) throws Exception;
	void eliminarPedido(String idPedido,String userName) throws Exception;
	
	/**
	 * Controlador accion entrega.
	 *
	 * @param entrega el entrega
	 * @param accionType el accion type
	 * @return the entrega
	 * @throws Exception the exception
	 */
	EntregaDTO controladorAccionEntrega(EntregaDTO entrega,AccionType accionType) throws Exception; 
	
	/**
	 * Listar entrega.
	 *
	 * @param entrega el entrega
	 * @return the list
	 * @throws Exception the exception
	 */
	List<EntregaDTO> listarEntrega(EntregaDTO entrega) throws Exception;
	
	
	
	
	/**
	 * contar lista entrega.
	 *
	 * @param pedido el entrega
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarEntrega(EntregaDTO entrega);
	
	EntregaDTO registrarEntregaDTO(EntregaDTO pedido) throws Exception;
	String generarReporteEntrega(String idEntrega) throws Exception;
	void eliminarEntrega(String idEntrega,String userName) throws Exception;
	
	/**
	 * Controlador accion detalle pedido.
	 *
	 * @param detallePedido el detalle pedido
	 * @param accionType el accion type
	 * @return the detalle pedido
	 * @throws Exception the exception
	 */
	DetalleEntregaDTO controladorAccionDetalleEntrega(DetalleEntregaDTO detalleEntregaDTO,Entrega pedido, AccionType accionType) throws Exception;
		
	/**
	 * Listar detalle pedido.
	 *
	 * @param detalleEntrega el detalle pedido
	 * @return the list
	 * @throws Exception the exception
	 */
	List<DetalleEntregaDTO> listarDetalleEntrega(DetalleEntregaDTO detalleEntrega) throws Exception;
	
	/**
	 * contar lista detalle pedido.
	 *
	 * @param detalleEntrega el detalle pedido
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarDetalleEntrega(DetalleEntregaDTO detalleEntrega);
	
	/**
	 * Obtener ventas para grafico
	 * @return
	 * @throws Exception
	 */
	VentaGraficoVO obtenerVentaGanacias() throws Exception;
	
	ClienteDTO findAlumnoByCliente(String idCliente) throws Exception;
	
	ClienteDTO findAlumnoByClienteNro(String nroDoc) throws Exception;
}