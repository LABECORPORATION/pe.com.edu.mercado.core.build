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
import pe.com.builderp.core.facturacion.model.dto.venta.ProductoProveedorDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.Producto;
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
@Path("/productoProveedorRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class ProductoProveedorRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient VentaServiceLocal ventaServiceLocal;
	
	@POST
	public ResultadoRestVO<ProductoProveedorDTO> crear(ProductoProveedorDTO productoProveedor) throws Exception {
		return controladorAccion(productoProveedor,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<ProductoProveedorDTO> modificar(ProductoProveedorDTO productoProveedor) throws Exception {
		return controladorAccion(productoProveedor,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<ProductoProveedorDTO> eliminar(@PathParam("id") String idProductoProveedor) throws Exception {
		ProductoProveedorDTO productoProveedor = new ProductoProveedorDTO();
		productoProveedor.setIdProductoProveedor(idProductoProveedor);		
		return controladorAccion(productoProveedor,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<ProductoProveedorDTO> controladorAccion(ProductoProveedorDTO ProductoProveedor, AccionType accionType){
		ResultadoRestVO<ProductoProveedorDTO> resultado = new ResultadoRestVO<ProductoProveedorDTO>();
		 try {
    		resultado.setObjetoResultado(ventaServiceLocal.controladorAccionProductoProveedor(ProductoProveedor,null,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<ProductoProveedorDTO> finById(@PathParam("id") String idProductoProveedor) throws Exception {
		ProductoProveedorDTO productoProveedor = new ProductoProveedorDTO();
		productoProveedor.setIdProductoProveedor(idProductoProveedor);
		return controladorAccion(productoProveedor,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<ProductoProveedorDTO> listarProductoProveedor(@Context UriInfo info){
		ResultadoRestVO<ProductoProveedorDTO> resultado = new ResultadoRestVO<ProductoProveedorDTO>();
		ProductoProveedorDTO productoProveedor = transferUriInfo(info);
		 try {
			resultado.setListaResultado(ventaServiceLocal.listarProductoProveedor(productoProveedor));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<ProductoProveedorDTO> contarProductoProveedor(@Context UriInfo info){
		ResultadoRestVO<ProductoProveedorDTO> resultado = new ResultadoRestVO<ProductoProveedorDTO>();
		ProductoProveedorDTO productoProveedor = transferUriInfo(info);
		 try {
			 resultado.setContador(ventaServiceLocal.contarListarProductoProveedor(productoProveedor));
			 if (resultado.isData()) {
				resultado.setListaResultado(ventaServiceLocal.listarProductoProveedor(productoProveedor));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
 
	@GET
    @Path("/")
	public ResultadoRestVO<ProductoProveedorDTO> inicializarProductoProveedor(@Context UriInfo info) throws Exception {
	     ProductoProveedorDTO productoProveedor = transferUriInfo(info);
		 ResultadoRestVO<ProductoProveedorDTO> resultado = new ResultadoRestVO<ProductoProveedorDTO>();
		 try {
    		resultado.setObjetoResultado(productoProveedor);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private ProductoProveedorDTO transferUriInfo(@Context UriInfo info) {
		ProductoProveedorDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,ProductoProveedorDTO.class);
		return resultado;
	}
 
}