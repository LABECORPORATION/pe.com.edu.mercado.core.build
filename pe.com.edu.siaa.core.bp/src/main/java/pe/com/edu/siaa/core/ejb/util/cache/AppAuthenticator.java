package pe.com.edu.siaa.core.ejb.util.cache;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.LoginException;

import pe.com.edu.siaa.core.ejb.seguridad.jwt.JWTokenUtility;
import pe.com.edu.siaa.core.ejb.service.helper.Referencia;
import pe.com.edu.siaa.core.ejb.service.seguridad.local.SeguridadServiceLocal;
import pe.com.edu.siaa.core.ejb.service.util.FechaUtil;
import pe.com.edu.siaa.core.ejb.util.date.FechaDateUtil;
import pe.com.edu.siaa.core.model.dto.seguridad.RecuperarPasswordDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.UsuarioDTO;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;

public final class AppAuthenticator {

    private static AppAuthenticator authenticator = null;

    // A service key storage which stores <service_key, username>
    private final Map<String, Map<String,String>> serviceKeysStorage = new HashMap<String,Map<String,String>>();

    // An authentication token storage which stores <service_key, auth_token>.
    private final Map<String, String> authorizationTokensStorage = new HashMap<String,String>();
    private final Map<String, UsuarioDTO> authorizationTokensUserStorage = new HashMap<String,UsuarioDTO>();

    private AppAuthenticator() {
        // The usersStorage pretty much represents a user table in the database
       // usersStorage.put( "username2", "passwordForUser2" );

        /**
         * Service keys are pre-generated by the system and is given to the
         * authorized client who wants to have access to the REST API. Here,
         * only username1 and username2 is given the REST service access with
         * their respective service keys.
         */
        serviceKeysStorage.put( "3b91cab8-926f-49b6-ba00-920bcf934c2a", new HashMap<String,String>() );
    }

    public static AppAuthenticator getInstance() {
        if ( authenticator == null ) {
            authenticator = new AppAuthenticator();
        }

        return authenticator;
    }
    public synchronized Map<String,Object> login( String serviceKey, String userName, String userPassword ) throws Exception {
    	  Map<String,Object> resultado = new HashMap<String, Object>();
    	  if ( serviceKeysStorage.containsKey( serviceKey ) ) {
    		  SeguridadServiceLocal seguridadServiceLocal = Referencia.getReference(SeguridadServiceLocal.class);
    		  UsuarioDTO usuario = seguridadServiceLocal.validarLogin(userName, userPassword);
    		  if (usuario != null) {
            	 Map<String, String> userMap =   serviceKeysStorage.get(serviceKey);
            	 userMap.put(userName, userName);
            	 serviceKeysStorage.put(userPassword, userMap);
            	 /**
                  * Once all params are matched, the authToken will be
                  * generated and will be stored in the
                  * authorizationTokensStorage. The authToken will be needed
                  * for every REST API invocation and is only valid within
                  * the login session
                  */
                 String authToken = JWTokenUtility.buildJWT(userName);
                 authorizationTokensStorage.put( authToken, userName );
                 usuario.setFechaUltimoAcceso(FechaUtil.obtenerFechaActual());
                 authorizationTokensUserStorage.put(authToken, usuario);
                 resultado.put("authToken", authToken);
                 resultado.put("usuario", usuario);
                 return resultado;
             }
    	  }
    	  throw new LoginException( "No se puedo validar login" );
    }
    public synchronized void actualizarFechaUltimoAcceso( String serviceKey, String authToken ) throws Exception {
    	 if ( serviceKeysStorage.containsKey( serviceKey ) ) {
    		 if (authorizationTokensUserStorage.containsKey(authToken)) {
    			 UsuarioDTO usuario = authorizationTokensUserStorage.get(authToken);
    			 usuario.setFechaUltimoAcceso(FechaUtil.obtenerFechaActual());
                 authorizationTokensUserStorage.put(authToken, usuario);
    		 }
    	 }
    }


    /**
     * The method that pre-validates if the client which invokes the REST API is
     * from a authorized and authenticated source.
     *
     * @param serviceKey The service key
     * @param authToken The authorization token generated after login
     * @return TRUE for acceptance and FALSE for denied.
     */
    public boolean isAuthTokenValid( String serviceKey, String authToken ) {
        if ( isServiceKeyValid( serviceKey ) ) {
        	Map<String, String> userMap = serviceKeysStorage.get( serviceKey );
            if ( authorizationTokensStorage.containsKey( authToken ) ) {
                String usernameMatch2 = authorizationTokensStorage.get( authToken );
                if ( userMap.containsKey( usernameMatch2 ) ) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * This method checks is the service key is valid
     *
     * @param serviceKey
     * @return TRUE if service key matches the pre-generated ones in service key
     * storage. FALSE for otherwise.
     */
    public boolean isServiceKeyValid( String serviceKey ) {
        return serviceKeysStorage.containsKey( serviceKey );
    }

    public void logout( String serviceKey, String authToken ) throws GeneralSecurityException {
        if ( serviceKeysStorage.containsKey( serviceKey ) ) {
        	Map<String, String> userMap = serviceKeysStorage.get( serviceKey );
            if ( authorizationTokensStorage.containsKey( authToken ) ) {
                String usernameMatch2 = authorizationTokensStorage.get( authToken );
                if ( userMap.containsKey( usernameMatch2 ) ) {
                    /**
                     * When a client logs out, the authentication token will be
                     * remove and will be made invalid.
                     */
                	userMap.remove(usernameMatch2);
                    authorizationTokensStorage.remove( authToken );
                    authorizationTokensUserStorage.remove( authToken );
                    return;
                }
            }
        }

        throw new GeneralSecurityException( "Invalid service key and authorization token match." );
    }
   public UsuarioDTO getUsuario( String serviceKey, String authToken) throws Exception {
    	  if ( serviceKeysStorage.containsKey( serviceKey ) ) {
    		  return  authorizationTokensUserStorage.get(authToken);	  
    	  }
    	  return null;
    	
    }
   
   public synchronized boolean isSessionActiva( String serviceKey, String authToken) throws Exception {
 	  if ( !serviceKeysStorage.containsKey( serviceKey ) ) {
 		  return false;	  
 	  }
 	  if ( !authorizationTokensUserStorage.containsKey(authToken)) {
 		   return false;
 	  }
 	  UsuarioDTO usuario = authorizationTokensUserStorage.get(authToken);
 	  Date fechaActual = FechaUtil.obtenerFechaActual();
 	 /* if (FechaDateUtil.restaMinutos(usuario.getFechaUltimoAcceso(),fechaActual) > 30) {
 		 return false;
 	  }*/
 	  actualizarFechaUltimoAcceso(serviceKey, authToken);
 	  return true;
 	
 }
    
    public String getUserName( String authToken) throws Exception {
    	return  authorizationTokensStorage.get(authToken);
    }
    
    
   //agregado
    
    public synchronized ResultadoRestVO<UsuarioDTO>  updaterUsuario( String serviceKey, String idUsuario,String filtro, AccionType accionType ) throws Exception {
    	ResultadoRestVO<UsuarioDTO> resultado = new ResultadoRestVO<UsuarioDTO>();
    	  if ( serviceKeysStorage.containsKey( serviceKey ) ) {
    		  SeguridadServiceLocal seguridadServiceLocal = Referencia.getReference(SeguridadServiceLocal.class);
    		   
    		  if (filtro != null) {
    			  resultado.setObjetoResultado(seguridadServiceLocal.controladorAccionUsuario2(idUsuario,filtro,accionType));
    			  return resultado;
             }
    	  }

    	  throw new LoginException( "No se pudo crear la nueva contraseņa" );
    }
    
    public synchronized ResultadoRestVO<RecuperarPasswordDTO>  verificacionCodigo( String serviceKey, String filtro ) throws Exception {
    	ResultadoRestVO<RecuperarPasswordDTO> resultado = new ResultadoRestVO<RecuperarPasswordDTO>();
    	  if ( serviceKeysStorage.containsKey( serviceKey ) ) {
    		  SeguridadServiceLocal seguridadServiceLocal = Referencia.getReference(SeguridadServiceLocal.class);
    		  if (filtro != null) {
    			  resultado.setObjetoResultado(seguridadServiceLocal.listarRecuperarPassword(filtro));
    			 return resultado;
             }
    	  }

    	  throw new LoginException( "No se pudo validar la clave" );
    }
    
    public synchronized Map<String,Object> loginpassword( String serviceKey, String userName, String email ) throws Exception {
    	  Map<String,Object> resultado = new HashMap<String, Object>();
    	  if ( serviceKeysStorage.containsKey( serviceKey ) ) {
    		  SeguridadServiceLocal seguridadServiceLocal = Referencia.getReference(SeguridadServiceLocal.class);
    		  UsuarioDTO usuario = seguridadServiceLocal.validarLoginPassword(userName, email);
    		  if (usuario != null) {
            	 Map<String, String> userMap =   serviceKeysStorage.get(serviceKey);
            	 userMap.put(userName, userName);
            	 serviceKeysStorage.put(email, userMap);
          	 
            	 /**
                  * Once all params are matched, the authToken will be
                  * generated and will be stored in the
                  * authorizationTokensStorage. The authToken will be needed
                  * for every REST API invocation and is only valid within
                  * the login session
                  */
                 String authToken = JWTokenUtility.buildJWT(userName);
                 authorizationTokensStorage.put( authToken, userName );
                 usuario.setFechaUltimoAcceso(FechaUtil.obtenerFechaActual());
                 authorizationTokensUserStorage.put(authToken, usuario);
                 resultado.put("authToken", authToken);
                 resultado.put("usuario", usuario);
                 return resultado;
             }
    	  }
    	  throw new LoginException( "No se puedo enviar el correo" );
    }
}