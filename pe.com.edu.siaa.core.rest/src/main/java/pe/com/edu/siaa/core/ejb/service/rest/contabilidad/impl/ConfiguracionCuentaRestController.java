package pe.com.edu.siaa.core.ejb.service.rest.contabilidad.impl;

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
import pe.com.edu.siaa.core.ejb.service.contabilidad.local.ContabilidadServiceLocal;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.dto.contabilidad.ConfiguracionCuentaDTO;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class ConfiguracionCuentaRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Mon Oct 16 10:52:28 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/configuracionCuentaRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class ConfiguracionCuentaRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient ContabilidadServiceLocal contabilidadServiceLocal;
	
	@POST
	public ResultadoRestVO<ConfiguracionCuentaDTO> crear(ConfiguracionCuentaDTO configuracionCuenta) throws Exception {
		return controladorAccion(configuracionCuenta,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<ConfiguracionCuentaDTO> modificar(ConfiguracionCuentaDTO configuracionCuenta) throws Exception {
		return controladorAccion(configuracionCuenta,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<ConfiguracionCuentaDTO> eliminar(@PathParam("id") String idConfiguracionCuenta) throws Exception {
		ConfiguracionCuentaDTO configuracionCuenta = new ConfiguracionCuentaDTO();
		configuracionCuenta.setIdConfiguracionCuenta(idConfiguracionCuenta);		
		return controladorAccion(configuracionCuenta,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<ConfiguracionCuentaDTO> controladorAccion(ConfiguracionCuentaDTO configuracionCuenta, AccionType accionType){
		ResultadoRestVO<ConfiguracionCuentaDTO> resultado = new ResultadoRestVO<ConfiguracionCuentaDTO>();
		 try {
    		resultado.setObjetoResultado(contabilidadServiceLocal.controladorAccionConfiguracionCuenta(configuracionCuenta,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<ConfiguracionCuentaDTO> finById(@PathParam("id") String idConfiguracionCuenta) throws Exception {
		ConfiguracionCuentaDTO configuracionCuenta = new ConfiguracionCuentaDTO();
		configuracionCuenta.setIdConfiguracionCuenta(idConfiguracionCuenta);
		return controladorAccion(configuracionCuenta,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<ConfiguracionCuentaDTO> listarConfiguracionCuenta(@Context UriInfo info){
		ResultadoRestVO<ConfiguracionCuentaDTO> resultado = new ResultadoRestVO<ConfiguracionCuentaDTO>();
		ConfiguracionCuentaDTO configuracionCuenta = transferUriInfo(info);
		 try {
			resultado.setListaResultado(contabilidadServiceLocal.listarConfiguracionCuenta(configuracionCuenta));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<ConfiguracionCuentaDTO> contarConfiguracionCuenta(@Context UriInfo info){
		ResultadoRestVO<ConfiguracionCuentaDTO> resultado = new ResultadoRestVO<ConfiguracionCuentaDTO>();
		ConfiguracionCuentaDTO configuracionCuenta = transferUriInfo(info);
		 try {
			 resultado.setContador(contabilidadServiceLocal.contarListarConfiguracionCuenta(configuracionCuenta));
			 if (resultado.isData()) {
				resultado.setListaResultado(contabilidadServiceLocal.listarConfiguracionCuenta(configuracionCuenta));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<ConfiguracionCuentaDTO> inicializarConfiguracionCuenta(@Context UriInfo info) throws Exception {
	     ConfiguracionCuentaDTO configuracionCuenta = transferUriInfo(info);
		 ResultadoRestVO<ConfiguracionCuentaDTO> resultado = new ResultadoRestVO<ConfiguracionCuentaDTO>();
		 try {
    		resultado.setObjetoResultado(configuracionCuenta);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private ConfiguracionCuentaDTO transferUriInfo(@Context UriInfo info) {
		ConfiguracionCuentaDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,ConfiguracionCuentaDTO.class);
		return resultado;
	}
}