package pe.com.builderp.core.facturacion.ejb.service.rest.compra.impl;

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

import pe.com.builderp.core.facturacion.ejb.service.compra.local.CompraServiceLocal;
import pe.com.builderp.core.facturacion.model.dto.compra.ProveedorDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.PersonalDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class ProveedorRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:20 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/proveedorRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class ProveedorRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient CompraServiceLocal compraServiceLocal;
	
	@POST
	public ResultadoRestVO<ProveedorDTO> crear(ProveedorDTO proveedor) throws Exception {
		return controladorAccion(proveedor,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<ProveedorDTO> modificar(ProveedorDTO proveedor) throws Exception {
		return controladorAccion(proveedor,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<ProveedorDTO> eliminar(@PathParam("id") String idProveedor) throws Exception {
		ProveedorDTO proveedor = new ProveedorDTO();
		proveedor.setIdProveedor(idProveedor);		
		return controladorAccion(proveedor,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<ProveedorDTO> controladorAccion(ProveedorDTO proveedor, AccionType accionType){
		ResultadoRestVO<ProveedorDTO> resultado = new ResultadoRestVO<ProveedorDTO>();
		 try {
    		resultado.setObjetoResultado(compraServiceLocal.controladorAccionProveedor(proveedor,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<ProveedorDTO> finById(@PathParam("id") String idProveedor) throws Exception {
		ProveedorDTO proveedor = new ProveedorDTO();
		proveedor.setIdProveedor(idProveedor);
		return controladorAccion(proveedor,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<ProveedorDTO> listarProveedor(@Context UriInfo info){
		ResultadoRestVO<ProveedorDTO> resultado = new ResultadoRestVO<ProveedorDTO>();
		ProveedorDTO proveedor = transferUriInfo(info);
		 try {
			resultado.setListaResultado(compraServiceLocal.listarProveedor(proveedor));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<ProveedorDTO> contarProveedor(@Context UriInfo info){
		ResultadoRestVO<ProveedorDTO> resultado = new ResultadoRestVO<ProveedorDTO>();
		ProveedorDTO proveedor = transferUriInfo(info);
		 try {
			 resultado.setContador(compraServiceLocal.contarListarProveedor(proveedor));
			 if (resultado.isData()) {
				resultado.setListaResultado(compraServiceLocal.listarProveedor(proveedor));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<ProveedorDTO> inicializarProveedor(@Context UriInfo info) throws Exception {
	     ProveedorDTO proveedor = transferUriInfo(info);
		 ResultadoRestVO<ProveedorDTO> resultado = new ResultadoRestVO<ProveedorDTO>();
		 try {
    		resultado.setObjetoResultado(proveedor);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private ProveedorDTO transferUriInfo(@Context UriInfo info) {
		ProveedorDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,ProveedorDTO.class);
		return resultado;
	}
	
	@GET
	@Path("/findProveedorByNro/{nroDco}")
	public ResultadoRestVO<ProveedorDTO> findProveedorByNro(@PathParam("nroDco") String nroDco) throws Exception {
		ResultadoRestVO<ProveedorDTO> resultado = new ResultadoRestVO<ProveedorDTO>();
		 try {
   		resultado.setObjetoResultado(compraServiceLocal.findProveedorByNro(nroDco));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
}