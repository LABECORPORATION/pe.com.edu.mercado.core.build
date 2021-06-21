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
import pe.com.edu.siaa.core.model.dto.seguridad.PrivilegioDTO;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class PrivilegioRestController.
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
@Path("/privilegioRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class PrivilegioRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient SeguridadServiceLocal seguridadServiceLocal;
	
	@POST
	public ResultadoRestVO<PrivilegioDTO> crear(PrivilegioDTO privilegio) throws Exception {
		return controladorAccion(privilegio,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<PrivilegioDTO> modificar(PrivilegioDTO privilegio) throws Exception {
		return controladorAccion(privilegio,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<PrivilegioDTO> eliminar(@PathParam("id") Long idPrivilegio) throws Exception {
		PrivilegioDTO privilegio = new PrivilegioDTO();
		privilegio.setIdPrivilegio(idPrivilegio);		
		return controladorAccion(privilegio,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<PrivilegioDTO> controladorAccion(PrivilegioDTO privilegio, AccionType accionType){
		ResultadoRestVO<PrivilegioDTO> resultado = new ResultadoRestVO<PrivilegioDTO>();
		 try {
    		resultado.setObjetoResultado(seguridadServiceLocal.controladorAccionPrivilegio(privilegio,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<PrivilegioDTO> finById(@PathParam("id") Long idPrivilegio) throws Exception {
		PrivilegioDTO privilegio = new PrivilegioDTO();
		privilegio.setIdPrivilegio(idPrivilegio);
		return controladorAccion(privilegio,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<PrivilegioDTO> listarPrivilegio(@Context UriInfo info){
		ResultadoRestVO<PrivilegioDTO> resultado = new ResultadoRestVO<PrivilegioDTO>();
		PrivilegioDTO privilegio = transferUriInfo(info);
		 try {
			resultado.setListaResultado(seguridadServiceLocal.listarPrivilegio(privilegio));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<PrivilegioDTO> contarPrivilegio(@Context UriInfo info){
		ResultadoRestVO<PrivilegioDTO> resultado = new ResultadoRestVO<PrivilegioDTO>();
		PrivilegioDTO privilegio = transferUriInfo(info);
		 try {
			 resultado.setContador(seguridadServiceLocal.contarListarPrivilegio(privilegio));
			 if (resultado.isData()) {
				resultado.setListaResultado(seguridadServiceLocal.listarPrivilegio(privilegio));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<PrivilegioDTO> inicializarPrivilegio(@Context UriInfo info) throws Exception {
	     PrivilegioDTO privilegio = transferUriInfo(info);
		 ResultadoRestVO<PrivilegioDTO> resultado = new ResultadoRestVO<PrivilegioDTO>();
		 try {
    		resultado.setObjetoResultado(privilegio);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private PrivilegioDTO transferUriInfo(@Context UriInfo info) {
		PrivilegioDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,PrivilegioDTO.class);
		return resultado;
	}
}