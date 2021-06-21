package pe.com.edu.siaa.core.ejb.service.rest.seguridad.impl;

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

import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.ejb.service.seguridad.local.SeguridadServiceLocal;
import pe.com.edu.siaa.core.model.dto.seguridad.PrivilegioMenuDTO;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class PrivilegioMenuRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Mon Oct 16 14:31:02 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/privilegioMenuRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class PrivilegioMenuRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient SeguridadServiceLocal seguridadServiceLocal;
	
	@POST
	public ResultadoRestVO<PrivilegioMenuDTO> crear(PrivilegioMenuDTO privilegioMenu) throws Exception {
		return controladorAccion(privilegioMenu,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<PrivilegioMenuDTO> modificar(PrivilegioMenuDTO privilegioMenu) throws Exception {
		return controladorAccion(privilegioMenu,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<PrivilegioMenuDTO> eliminar(@PathParam("id") String idPrivilegioMenu) throws Exception {
		PrivilegioMenuDTO privilegioMenu = new PrivilegioMenuDTO();
		privilegioMenu.setIdPrivilegioMenu(idPrivilegioMenu);		
		return controladorAccion(privilegioMenu,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<PrivilegioMenuDTO> controladorAccion(PrivilegioMenuDTO privilegioMenu, AccionType accionType){
		ResultadoRestVO<PrivilegioMenuDTO> resultado = new ResultadoRestVO<PrivilegioMenuDTO>();
		 try {
    		resultado.setObjetoResultado(seguridadServiceLocal.controladorAccionPrivilegioMenu(privilegioMenu,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<PrivilegioMenuDTO> finById(@PathParam("id") String idPrivilegioMenu) throws Exception {
		PrivilegioMenuDTO privilegioMenu = new PrivilegioMenuDTO();
		privilegioMenu.setIdPrivilegioMenu(idPrivilegioMenu);
		return controladorAccion(privilegioMenu,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<PrivilegioMenuDTO> listarPrivilegioMenu(@Context UriInfo info){
		ResultadoRestVO<PrivilegioMenuDTO> resultado = new ResultadoRestVO<PrivilegioMenuDTO>();
		PrivilegioMenuDTO privilegioMenu = transferUriInfo(info);
		 try {
			resultado.setListaResultado(seguridadServiceLocal.listarPrivilegioMenu(privilegioMenu));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<PrivilegioMenuDTO> contarPrivilegioMenu(@Context UriInfo info){
		ResultadoRestVO<PrivilegioMenuDTO> resultado = new ResultadoRestVO<PrivilegioMenuDTO>();
		PrivilegioMenuDTO privilegioMenu = transferUriInfo(info);
		 try {
			 resultado.setContador(seguridadServiceLocal.contarListarPrivilegioMenu(privilegioMenu));
			 if (resultado.isData()) {
				resultado.setListaResultado(seguridadServiceLocal.listarPrivilegioMenu(privilegioMenu));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<PrivilegioMenuDTO> inicializarPrivilegioMenu(@Context UriInfo info) throws Exception {
	     PrivilegioMenuDTO privilegioMenu = transferUriInfo(info);
		 ResultadoRestVO<PrivilegioMenuDTO> resultado = new ResultadoRestVO<PrivilegioMenuDTO>();
		 try {
    		resultado.setObjetoResultado(privilegioMenu);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private PrivilegioMenuDTO transferUriInfo(@Context UriInfo info) {
		PrivilegioMenuDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,PrivilegioMenuDTO.class);
		return resultado;
	}
}