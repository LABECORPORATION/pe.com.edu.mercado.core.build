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
import pe.com.builderp.core.facturacion.model.dto.venta.ClienteDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class ClienteRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:21 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/clienteRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class ClienteRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient VentaServiceLocal ventaServiceLocal;
	
	@POST
	public ResultadoRestVO<ClienteDTO> crear(ClienteDTO cliente) throws Exception {
		return controladorAccion(cliente,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<ClienteDTO> modificar(ClienteDTO cliente) throws Exception {
		return controladorAccion(cliente,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<ClienteDTO> eliminar(@PathParam("id") String idCliente) throws Exception {
		ClienteDTO cliente = new ClienteDTO();
		cliente.setIdCliente(idCliente);		
		return controladorAccion(cliente,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<ClienteDTO> controladorAccion(ClienteDTO cliente, AccionType accionType){
		ResultadoRestVO<ClienteDTO> resultado = new ResultadoRestVO<ClienteDTO>();
		 try {
    		resultado.setObjetoResultado(ventaServiceLocal.controladorAccionCliente(cliente,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<ClienteDTO> finById(@PathParam("id") String idCliente) throws Exception {
		ClienteDTO cliente = new ClienteDTO();
		cliente.setIdCliente(idCliente);
		return controladorAccion(cliente,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<ClienteDTO> listarCliente(@Context UriInfo info){
		ResultadoRestVO<ClienteDTO> resultado = new ResultadoRestVO<ClienteDTO>();
		ClienteDTO cliente = transferUriInfo(info);
		 try {
			resultado.setListaResultado(ventaServiceLocal.listarCliente(cliente));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<ClienteDTO> contarCliente(@Context UriInfo info){
		ResultadoRestVO<ClienteDTO> resultado = new ResultadoRestVO<ClienteDTO>();
		ClienteDTO cliente = transferUriInfo(info);
		 try {
			 resultado.setContador(ventaServiceLocal.contarListarCliente(cliente));
			 if (resultado.isData()) {
				resultado.setListaResultado(ventaServiceLocal.listarCliente(cliente));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<ClienteDTO> inicializarCliente(@Context UriInfo info) throws Exception {
	     ClienteDTO cliente = transferUriInfo(info);
		 ResultadoRestVO<ClienteDTO> resultado = new ResultadoRestVO<ClienteDTO>();
		 try {
    		resultado.setObjetoResultado(cliente);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private ClienteDTO transferUriInfo(@Context UriInfo info) {
		ClienteDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,ClienteDTO.class);
		return resultado;
	}
	
	@GET
	@Path("/findAlumnoByCliente/{idCliente}")
	public ResultadoRestVO<ClienteDTO> findAlumnoByCliente(@PathParam("idCliente") String idCliente) throws Exception {
		ResultadoRestVO<ClienteDTO> resultado = new ResultadoRestVO<ClienteDTO>();
		 try {
   		resultado.setObjetoResultado(ventaServiceLocal.findAlumnoByCliente(idCliente));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	
	@GET
	@Path("/findAlumnoByClienteNro/{nroDco}")
	public ResultadoRestVO<ClienteDTO> findAlumnoByClienteNro(@PathParam("nroDco") String nroDco) throws Exception {
		ResultadoRestVO<ClienteDTO> resultado = new ResultadoRestVO<ClienteDTO>();
		 try {
   		resultado.setObjetoResultado(ventaServiceLocal.findAlumnoByClienteNro(nroDco));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
}