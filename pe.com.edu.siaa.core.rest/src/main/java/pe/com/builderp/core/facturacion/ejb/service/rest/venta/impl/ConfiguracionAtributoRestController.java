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
import pe.com.builderp.core.facturacion.model.dto.venta.ConfiguracionAtributoDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class ConfiguracionAtributoRestController.
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
@Path("/configuracionAtributoRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class ConfiguracionAtributoRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient VentaServiceLocal ventaServiceLocal;
	
	@POST
	public ResultadoRestVO<ConfiguracionAtributoDTO> crear(ConfiguracionAtributoDTO configuracionAtributo) throws Exception {
		return controladorAccion(configuracionAtributo,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<ConfiguracionAtributoDTO> modificar(ConfiguracionAtributoDTO configuracionAtributo) throws Exception {
		return controladorAccion(configuracionAtributo,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<ConfiguracionAtributoDTO> eliminar(@PathParam("id") String idConfiguracionAtributo) throws Exception {
		ConfiguracionAtributoDTO configuracionAtributo = new ConfiguracionAtributoDTO();
		configuracionAtributo.setIdConfiguracionAtributo(idConfiguracionAtributo);		
		return controladorAccion(configuracionAtributo,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<ConfiguracionAtributoDTO> controladorAccion(ConfiguracionAtributoDTO configuracionAtributo, AccionType accionType){
		ResultadoRestVO<ConfiguracionAtributoDTO> resultado = new ResultadoRestVO<ConfiguracionAtributoDTO>();
		 try {
    		resultado.setObjetoResultado(ventaServiceLocal.controladorAccionConfiguracionAtributo(configuracionAtributo,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<ConfiguracionAtributoDTO> finById(@PathParam("id") String idConfiguracionAtributo) throws Exception {
		ConfiguracionAtributoDTO configuracionAtributo = new ConfiguracionAtributoDTO();
		configuracionAtributo.setIdConfiguracionAtributo(idConfiguracionAtributo);
		return controladorAccion(configuracionAtributo,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<ConfiguracionAtributoDTO> listarConfiguracionAtributo(@Context UriInfo info){
		ResultadoRestVO<ConfiguracionAtributoDTO> resultado = new ResultadoRestVO<ConfiguracionAtributoDTO>();
		ConfiguracionAtributoDTO configuracionAtributo = transferUriInfo(info);
		 try {
			resultado.setListaResultado(ventaServiceLocal.listarConfiguracionAtributo(configuracionAtributo));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<ConfiguracionAtributoDTO> contarConfiguracionAtributo(@Context UriInfo info){
		ResultadoRestVO<ConfiguracionAtributoDTO> resultado = new ResultadoRestVO<ConfiguracionAtributoDTO>();
		ConfiguracionAtributoDTO configuracionAtributo = transferUriInfo(info);
		 try {
			 resultado.setContador(ventaServiceLocal.contarListarConfiguracionAtributo(configuracionAtributo));
			 if (resultado.isData()) {
				resultado.setListaResultado(ventaServiceLocal.listarConfiguracionAtributo(configuracionAtributo));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<ConfiguracionAtributoDTO> inicializarConfiguracionAtributo(@Context UriInfo info) throws Exception {
	     ConfiguracionAtributoDTO configuracionAtributo = transferUriInfo(info);
		 ResultadoRestVO<ConfiguracionAtributoDTO> resultado = new ResultadoRestVO<ConfiguracionAtributoDTO>();
		 try {
    		resultado.setObjetoResultado(configuracionAtributo);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private ConfiguracionAtributoDTO transferUriInfo(@Context UriInfo info) {
		ConfiguracionAtributoDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,ConfiguracionAtributoDTO.class);
		return resultado;
	}
}