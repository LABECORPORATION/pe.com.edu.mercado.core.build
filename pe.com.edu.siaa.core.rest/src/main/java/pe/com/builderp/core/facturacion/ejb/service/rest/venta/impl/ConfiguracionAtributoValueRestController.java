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
import pe.com.builderp.core.facturacion.model.dto.venta.ConfiguracionAtributoValueDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class ConfiguracionAtributoValueRestController.
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
@Path("/configuracionAtributoValueRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class ConfiguracionAtributoValueRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient VentaServiceLocal ventaServiceLocal;
	
	@POST
	public ResultadoRestVO<ConfiguracionAtributoValueDTO> crear(ConfiguracionAtributoValueDTO configuracionAtributoValue) throws Exception {
		return controladorAccion(configuracionAtributoValue,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<ConfiguracionAtributoValueDTO> modificar(ConfiguracionAtributoValueDTO configuracionAtributoValue) throws Exception {
		return controladorAccion(configuracionAtributoValue,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<ConfiguracionAtributoValueDTO> eliminar(@PathParam("id") String idConfiguracionAtributoValue) throws Exception {
		ConfiguracionAtributoValueDTO configuracionAtributoValue = new ConfiguracionAtributoValueDTO();
		configuracionAtributoValue.setIdConfiguracionAtributoValue(idConfiguracionAtributoValue);		
		return controladorAccion(configuracionAtributoValue,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<ConfiguracionAtributoValueDTO> controladorAccion(ConfiguracionAtributoValueDTO configuracionAtributoValue, AccionType accionType){
		ResultadoRestVO<ConfiguracionAtributoValueDTO> resultado = new ResultadoRestVO<ConfiguracionAtributoValueDTO>();
		 try {
    		resultado.setObjetoResultado(ventaServiceLocal.controladorAccionConfiguracionAtributoValue(configuracionAtributoValue,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<ConfiguracionAtributoValueDTO> finById(@PathParam("id") String idConfiguracionAtributoValue) throws Exception {
		ConfiguracionAtributoValueDTO configuracionAtributoValue = new ConfiguracionAtributoValueDTO();
		configuracionAtributoValue.setIdConfiguracionAtributoValue(idConfiguracionAtributoValue);
		return controladorAccion(configuracionAtributoValue,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<ConfiguracionAtributoValueDTO> listarConfiguracionAtributoValue(@Context UriInfo info){
		ResultadoRestVO<ConfiguracionAtributoValueDTO> resultado = new ResultadoRestVO<ConfiguracionAtributoValueDTO>();
		ConfiguracionAtributoValueDTO configuracionAtributoValue = transferUriInfo(info);
		 try {
			resultado.setListaResultado(ventaServiceLocal.listarConfiguracionAtributoValue(configuracionAtributoValue));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<ConfiguracionAtributoValueDTO> contarConfiguracionAtributoValue(@Context UriInfo info){
		ResultadoRestVO<ConfiguracionAtributoValueDTO> resultado = new ResultadoRestVO<ConfiguracionAtributoValueDTO>();
		ConfiguracionAtributoValueDTO configuracionAtributoValue = transferUriInfo(info);
		 try {
			 resultado.setContador(ventaServiceLocal.contarListarConfiguracionAtributoValue(configuracionAtributoValue));
			 if (resultado.isData()) {
				resultado.setListaResultado(ventaServiceLocal.listarConfiguracionAtributoValue(configuracionAtributoValue));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<ConfiguracionAtributoValueDTO> inicializarConfiguracionAtributoValue(@Context UriInfo info) throws Exception {
	     ConfiguracionAtributoValueDTO configuracionAtributoValue = transferUriInfo(info);
		 ResultadoRestVO<ConfiguracionAtributoValueDTO> resultado = new ResultadoRestVO<ConfiguracionAtributoValueDTO>();
		 try {
    		resultado.setObjetoResultado(configuracionAtributoValue);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private ConfiguracionAtributoValueDTO transferUriInfo(@Context UriInfo info) {
		ConfiguracionAtributoValueDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,ConfiguracionAtributoValueDTO.class);
		return resultado;
	}
}