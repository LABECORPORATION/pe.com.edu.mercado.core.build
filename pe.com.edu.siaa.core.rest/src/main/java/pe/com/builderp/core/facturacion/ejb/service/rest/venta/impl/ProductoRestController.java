package pe.com.builderp.core.facturacion.ejb.service.rest.venta.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import pe.com.builderp.core.facturacion.ejb.service.venta.local.VentaServiceLocal;
import pe.com.builderp.core.facturacion.model.dto.venta.ComboDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.DetalleProductoDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.ProductoDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.ProductoProveedorDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class ProductoRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:24 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/productoRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class ProductoRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient VentaServiceLocal ventaServiceLocal;
	
	/*@POST
	public ResultadoRestVO<ProductoDTO> crear(ProductoDTO producto) throws Exception {
		return controladorAccion(producto,AccionType.CREAR);
	}*/
	
	@POST
	public ResultadoRestVO<ProductoDTO> crear(ProductoDTO producto) throws Exception {
		ResultadoRestVO<ProductoDTO> resultado = new ResultadoRestVO<ProductoDTO>();
		 try {
			 ventaServiceLocal.registrarProducto(producto);
			 //resultado.setObjetoResultado(ventaServiceLocal.registrarProducto(producto));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		 return resultado;
		//return controladorAccion(producto,AccionType.CREAR);
	}
	
	@PUT
	public ResultadoRestVO<ProductoDTO> modificar(ProductoDTO producto) throws Exception {
		return controladorAccion(producto,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<ProductoDTO> eliminar(@PathParam("id") Long idProducto) throws Exception {
		ProductoDTO producto = new ProductoDTO();
		producto.setIdProducto(idProducto);		
		return controladorAccion(producto,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<ProductoDTO> controladorAccion(ProductoDTO producto, AccionType accionType){
		ResultadoRestVO<ProductoDTO> resultado = new ResultadoRestVO<ProductoDTO>();
		 try {
    		resultado.setObjetoResultado(ventaServiceLocal.controladorAccionProducto(producto,accionType));
    		
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<ProductoDTO> finById(@PathParam("id") Long idProducto) throws Exception {
		ProductoDTO producto = new ProductoDTO();
		producto.setIdProducto(idProducto);
		return controladorAccion(producto,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<ProductoDTO> listarProducto(@Context UriInfo info){
		ResultadoRestVO<ProductoDTO> resultado = new ResultadoRestVO<ProductoDTO>();
		ProductoDTO producto = transferUriInfo(info);
		 try {
			resultado.setListaResultado(ventaServiceLocal.listarProducto(producto));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<ProductoDTO> contarProducto(@Context UriInfo info){
		ResultadoRestVO<ProductoDTO> resultado = new ResultadoRestVO<ProductoDTO>();
		ProductoDTO producto = transferUriInfo(info);
		 try {
			 resultado.setContador(ventaServiceLocal.contarListarProducto(producto));
			 if (resultado.isData()) {
				resultado.setListaResultado(ventaServiceLocal.listarProducto(producto));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@GET
    @Path("/generarReporteProductoCodigoBarra")
	public ResultadoRestVO<String> generarReporteProductoCodigoBarra(@Context UriInfo info){
		ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
		ProductoDTO producto = transferUriInfo(info);
		 try {
			resultado.setObjetoResultado(ventaServiceLocal.generarReporteProductoCodigoBarra(producto));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/updateOfertaProductos")
	public void updateOferta() {
	
		 try {
			 this.ventaServiceLocal.updateOferta();
			 
		} catch (Exception e) {
			
		}
	}
	
	@GET
    @Path("/listarProductoProveedor")
	public ResultadoRestVO<ProductoProveedorDTO> listarProductoProveedor(@Context UriInfo info){
		ResultadoRestVO<ProductoProveedorDTO> resultado = new ResultadoRestVO<ProductoProveedorDTO>();
		ProductoProveedorDTO detalproductoProveedorleVenta = transferUriInfoProductoProveedor(info);
		 try {
			resultado.setListaResultado(ventaServiceLocal.listarProductoProveedor(detalproductoProveedorleVenta));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	private ProductoProveedorDTO transferUriInfoProductoProveedor(@Context UriInfo info) {
		ProductoProveedorDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,ProductoProveedorDTO.class);
		return resultado;
	}
	
	@GET
    @Path("/listarDetalleProducto")
	public ResultadoRestVO<DetalleProductoDTO> listarDetalleProducto(@Context UriInfo info){
		ResultadoRestVO<DetalleProductoDTO> resultado = new ResultadoRestVO<DetalleProductoDTO>();
		DetalleProductoDTO detalleProducto = transferUriInfoDetalle(info);
		 try {
			resultado.setListaResultado(ventaServiceLocal.listarDetalleProducto(detalleProducto));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@GET
    @Path("/listarDetalleProductoCombo")
	public ResultadoRestVO<DetalleProductoDTO> listarDetalleProductoCombo(@Context UriInfo info){
		ResultadoRestVO<DetalleProductoDTO> resultado = new ResultadoRestVO<DetalleProductoDTO>();
		DetalleProductoDTO detalleProducto = transferUriInfoDetalle(info);
		 try {
			resultado.setListaResultado(ventaServiceLocal.listarDetalleProductoCombo(detalleProducto));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	private DetalleProductoDTO transferUriInfoDetalle(@Context UriInfo info) {
		DetalleProductoDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,DetalleProductoDTO.class);
		return resultado;
	}
	
	
	@GET
    @Path("/listarCombo")
	public ResultadoRestVO<ComboDTO> listarCombo(@Context UriInfo info){
		ResultadoRestVO<ComboDTO> resultado = new ResultadoRestVO<ComboDTO>();
		ComboDTO combo = transferUriInfoCombo(info);
		 try {
			resultado.setListaResultado(ventaServiceLocal.listarCombo(combo));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	private ComboDTO transferUriInfoCombo(@Context UriInfo info) {
		ComboDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,ComboDTO.class);
		return resultado;
	}
	
	@GET
	@Path("/findByDetalleProducto/{codigo}")
	public ResultadoRestVO<DetalleProductoDTO> findByDetalleProducto(@PathParam("codigo") String codigo) throws Exception {
		ResultadoRestVO<DetalleProductoDTO> resultado = new ResultadoRestVO<DetalleProductoDTO>();
		 try {
   		resultado.setObjetoResultado(ventaServiceLocal.findByDetalleProducto(codigo));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@GET
    @Path("/")
	public ResultadoRestVO<ProductoDTO> inicializarProducto(@Context UriInfo info) throws Exception {
	     ProductoDTO producto = transferUriInfo(info);
		 ResultadoRestVO<ProductoDTO> resultado = new ResultadoRestVO<ProductoDTO>();
		 try {
    		resultado.setObjetoResultado(producto);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private ProductoDTO transferUriInfo(@Context UriInfo info) {
		ProductoDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,ProductoDTO.class);
		return resultado;
	}
}