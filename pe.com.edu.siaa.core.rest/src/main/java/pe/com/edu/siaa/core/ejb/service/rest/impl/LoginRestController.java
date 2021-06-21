package pe.com.edu.siaa.core.ejb.service.rest.impl;

import java.security.GeneralSecurityException;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import pe.com.edu.siaa.core.ejb.seguridad.jwt.rsa.util.AppHTTPHeaderNames;
import pe.com.edu.siaa.core.ejb.service.seguridad.local.SeguridadServiceLocal;
import pe.com.edu.siaa.core.ejb.util.cache.AppAuthenticator;
import pe.com.edu.siaa.core.ejb.util.cache.CacheUtil;
import pe.com.edu.siaa.core.model.dto.seguridad.RecuperarPasswordDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.UsuarioDTO;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.model.vo.ConfiguracionFormularioVO;
import pe.com.edu.siaa.core.model.vo.EgresadoFiltroVO;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class LoginController.
 * <ul>
 * <li>Copyright 2016 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Mon Jan 16 21:01:47 COT 2017
 * @since ERP-CRIS 1.0
 */
@Path("/loginRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class LoginRestController extends GenericServiceRestImpl {
 
	@Context
	SecurityContext sctx;
	 
	 @Context
	 HttpServletRequest webRequest;
	 
	@EJB
	private transient SeguridadServiceLocal seguridadServiceLocal;
	
	@POST
	public ResultadoRestVO<UsuarioDTO>  login(@Context HttpHeaders httpHeaders,User user ) {
		ResultadoRestVO<UsuarioDTO> resultado = new ResultadoRestVO<UsuarioDTO>();
		AppAuthenticator appAuthenticator = AppAuthenticator.getInstance();
        String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
        try {
            Map<String,Object> resulTempMap = appAuthenticator.login( serviceKey, user.getUsername(), user.getToken() );
            String authToken  = (String) resulTempMap.get("authToken");
            UsuarioDTO usuarioDTO = (UsuarioDTO) resulTempMap.get("usuario");
            resultado.setObjetoResultado(usuarioDTO);
            resultado.setAuthToken(authToken);
        } catch ( Exception ex ) {
        	resultado.setError(true);
            resultado.setCodigoError(Response.Status.UNAUTHORIZED.getStatusCode() + "");
            resultado.setMensajeError("El usuario o password es incorrecto ==> " + ex.getMessage());
        }
        return resultado;
	}
	@POST
	@Path("/dataStore")
	public ResultadoRestVO<UsuarioDTO>  dataStore(@Context HttpHeaders httpHeaders) {
		ResultadoRestVO<UsuarioDTO> resultado = new ResultadoRestVO<UsuarioDTO>();
		AppAuthenticator appAuthenticator = AppAuthenticator.getInstance();
		 String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
        try {
            UsuarioDTO usuarioDTO = appAuthenticator.getUsuario(serviceKey,authToken);
            if (usuarioDTO != null) {
            	resultado.setObjetoResultado(usuarioDTO);
                resultado.setAuthToken(authToken);	
            } else {
            	resultado.setError(true);
                resultado.setCodigoError(Response.Status.UNAUTHORIZED.getStatusCode() + "");
                resultado.setMensajeError("Usted no tiene privilegios" );
            }
            
        } catch ( Exception ex ) {
        	resultado.setError(true);
            resultado.setCodigoError(Response.Status.UNAUTHORIZED.getStatusCode() + "");
            resultado.setMensajeError("Usted no tiene privilegios ==> " + ex.getMessage()  );
        }
        return resultado;
    }
	
	@POST
	@Path("/dataStoreConf")
	public ResultadoRestVO<ConfiguracionFormularioVO>  dataStoreMsg(@Context HttpHeaders httpHeaders) {
		ResultadoRestVO<ConfiguracionFormularioVO> resultado = new ResultadoRestVO<ConfiguracionFormularioVO>();
		AppAuthenticator appAuthenticator = AppAuthenticator.getInstance();
		String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
        try {
        	UsuarioDTO usuarioDTO = appAuthenticator.getUsuario(serviceKey,authToken);
        	if (usuarioDTO != null) {
        		ConfiguracionFormularioVO cofigVO = CacheUtil.getInstance().getConfiguracionFormularioVO();
            	resultado.setObjetoResultado(cofigVO);
                resultado.setAuthToken(authToken);	
            } else {
            	resultado.setError(true);
                resultado.setCodigoError(Response.Status.UNAUTHORIZED.getStatusCode() + "");
                resultado.setMensajeError("Usted no tiene privilegios" );
            }
        } catch ( Exception ex ) {
        	resultado.setError(true);
            resultado.setCodigoError(Response.Status.UNAUTHORIZED.getStatusCode() + "");
            resultado.setMensajeError("Usted no tiene privilegios ==> " + ex.getMessage() );
        }
        return resultado;
    }
	
	@PUT
	public Response logout(@Context HttpHeaders httpHeaders,User user) {
	        try {
	            AppAuthenticator demoAuthenticator = AppAuthenticator.getInstance();
	            String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
	            String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
	            demoAuthenticator.logout( serviceKey, authToken );
	            return getNoCacheResponseBuilder( Response.Status.NO_CONTENT ).build();
	        } catch ( final GeneralSecurityException ex ) {
	            return getNoCacheResponseBuilder( Response.Status.INTERNAL_SERVER_ERROR ).build();
	        }
	    }

	
	private Response.ResponseBuilder getNoCacheResponseBuilder( Response.Status status ) {
        CacheControl cc = new CacheControl();
        cc.setNoCache( true );
        cc.setMaxAge( -1 );
        cc.setMustRevalidate( true );

        return Response.status( status ).cacheControl( cc );
    }
	
	//agregado
	@POST
    @Path("/updaterUsuario")
	public ResultadoRestVO<UsuarioDTO> updaterUsuario(@Context HttpHeaders httpHeaders,EgresadoFiltroVO filtro){
		ResultadoRestVO<UsuarioDTO> resultado = new ResultadoRestVO<UsuarioDTO>();
		AppAuthenticator appAuthenticator = AppAuthenticator.getInstance();
		String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
		 try {
			 resultado = appAuthenticator.updaterUsuario(serviceKey, filtro.getIdUsuario(),filtro.getIdFiltro(),AccionType.MODIFICAR );
    
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		 return resultado;
	}
	
	@POST
    @Path("/listarRecuperarPassword")
	public ResultadoRestVO<RecuperarPasswordDTO> listarRecuperarPassword(@Context HttpHeaders httpHeaders,EgresadoFiltroVO filtro){
		ResultadoRestVO<RecuperarPasswordDTO> resultado = new ResultadoRestVO<RecuperarPasswordDTO>();
		AppAuthenticator appAuthenticator = AppAuthenticator.getInstance();
		String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
		 try {
	            resultado = appAuthenticator.verificacionCodigo( serviceKey, filtro.getIdFiltro() );
    
		} catch (Exception e) {
			resultado.setMensajeError("Usted no tiene privilegios ==> " + e.getMessage() );
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@POST
	@Path("/loginPassword")
	public ResultadoRestVO<UsuarioDTO>  loginPassword(@Context HttpHeaders httpHeaders,User user ) {
		ResultadoRestVO<UsuarioDTO> resultado = new ResultadoRestVO<UsuarioDTO>();
		AppAuthenticator appAuthenticator = AppAuthenticator.getInstance();
        String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
        try {
            Map<String,Object> resulTempMap = appAuthenticator.loginpassword( serviceKey, user.getUsername(), user.getEmail() );
            String authToken  = (String) resulTempMap.get("authToken");
            UsuarioDTO usuarioDTO = (UsuarioDTO) resulTempMap.get("usuario");
            resultado.setObjetoResultado(usuarioDTO);
            resultado.setAuthToken(authToken);
        } catch ( Exception ex ) {
        	resultado.setError(true);
            resultado.setCodigoError(Response.Status.UNAUTHORIZED.getStatusCode() + "");
            resultado.setMensajeError("El usuario o email es incorrecto ==> " + ex.getMessage());
        }
        return resultado;
	}
}