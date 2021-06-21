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
import pe.com.builderp.core.facturacion.model.dto.compra.ContactoProveedorDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class ContactoProveedorRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:17 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/contactoProveedorRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class ContactoProveedorRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient CompraServiceLocal compraServiceLocal;
	
	@POST
	public ResultadoRestVO<ContactoProveedorDTO> crear(ContactoProveedorDTO contactoProveedor) throws Exception {
		return controladorAccion(contactoProveedor,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<ContactoProveedorDTO> modificar(ContactoProveedorDTO contactoProveedor) throws Exception {
		return controladorAccion(contactoProveedor,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<ContactoProveedorDTO> eliminar(@PathParam("id") String idContactoProveedor) throws Exception {
		ContactoProveedorDTO contactoProveedor = new ContactoProveedorDTO();
		contactoProveedor.setIdContactoProveedor(idContactoProveedor);		
		return controladorAccion(contactoProveedor,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<ContactoProveedorDTO> controladorAccion(ContactoProveedorDTO contactoProveedor, AccionType accionType){
		ResultadoRestVO<ContactoProveedorDTO> resultado = new ResultadoRestVO<ContactoProveedorDTO>();
		 try {
    		resultado.setObjetoResultado(compraServiceLocal.controladorAccionContactoProveedor(contactoProveedor,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<ContactoProveedorDTO> finById(@PathParam("id") String idContactoProveedor) throws Exception {
		ContactoProveedorDTO contactoProveedor = new ContactoProveedorDTO();
		contactoProveedor.setIdContactoProveedor(idContactoProveedor);
		return controladorAccion(contactoProveedor,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<ContactoProveedorDTO> listarContactoProveedor(@Context UriInfo info){
		ResultadoRestVO<ContactoProveedorDTO> resultado = new ResultadoRestVO<ContactoProveedorDTO>();
		ContactoProveedorDTO contactoProveedor = transferUriInfo(info);
		 try {
			resultado.setListaResultado(compraServiceLocal.listarContactoProveedor(contactoProveedor));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<ContactoProveedorDTO> contarContactoProveedor(@Context UriInfo info){
		ResultadoRestVO<ContactoProveedorDTO> resultado = new ResultadoRestVO<ContactoProveedorDTO>();
		ContactoProveedorDTO contactoProveedor = transferUriInfo(info);
		 try {
			 resultado.setContador(compraServiceLocal.contarListarContactoProveedor(contactoProveedor));
			 if (resultado.isData()) {
				resultado.setListaResultado(compraServiceLocal.listarContactoProveedor(contactoProveedor));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<ContactoProveedorDTO> inicializarContactoProveedor(@Context UriInfo info) throws Exception {
	     ContactoProveedorDTO contactoProveedor = transferUriInfo(info);
		 ResultadoRestVO<ContactoProveedorDTO> resultado = new ResultadoRestVO<ContactoProveedorDTO>();
		 try {
    		resultado.setObjetoResultado(contactoProveedor);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private ContactoProveedorDTO transferUriInfo(@Context UriInfo info) {
		ContactoProveedorDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,ContactoProveedorDTO.class);
		return resultado;
	}
}