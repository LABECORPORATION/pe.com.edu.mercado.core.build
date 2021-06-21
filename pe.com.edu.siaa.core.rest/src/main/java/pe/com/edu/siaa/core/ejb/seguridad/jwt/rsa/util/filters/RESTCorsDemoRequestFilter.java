package pe.com.edu.siaa.core.ejb.seguridad.jwt.rsa.util.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;

import pe.com.edu.siaa.core.ejb.seguridad.jwt.RsaKeyProducer;
import pe.com.edu.siaa.core.ejb.seguridad.jwt.rsa.util.AppHTTPHeaderNames;
import pe.com.edu.siaa.core.ejb.util.cache.AppAuthenticator;
import pe.com.edu.siaa.core.ejb.util.log.Logger;

@Provider
@PreMatching
public class RESTCorsDemoRequestFilter implements ContainerRequestFilter {

    private final static Logger log = Logger.getLogger( RESTCorsDemoRequestFilter.class.getName() );

    @Override
    public void filter( ContainerRequestContext requestCtx ) throws IOException {
        //log.info( "Executing REST request filter" );
        String path = requestCtx.getUriInfo().getPath();
        log.info( "Filtering request path: " + path );

        // When HttpMethod comes as OPTIONS, just acknowledge that it accepts...
        if ( requestCtx.getRequest().getMethod().equals( "OPTIONS" ) ) {
            log.info( "HTTP Method (OPTIONS) - Detected!" );
            // Just send a OK signal back to the browser
            /*requestCtx.abortWith( Response.status( Response.Status.OK ).build() );
             * */
        } else {
	        // Then check is the service key exists and is valid.
	        AppAuthenticator appAuthenticator = AppAuthenticator.getInstance();
	        String serviceKey = requestCtx.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
	        if ( !appAuthenticator.isServiceKeyValid( serviceKey ) ) {
	            // Kick anyone without a valid service key
	            /*requestCtx.abortWith( Response.status( Response.Status.UNAUTHORIZED ).build() ); 
	            return;*/
	        }
	        // For any pther methods besides login, the authToken must be verified
	        if ( !path.startsWith( "/loginRestController" )  && !path.startsWith( "/propertiesRestController" ) ) {
	            String authToken = requestCtx.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN ); 
	            // if it isn't valid, just kick them out.
	            if ( !appAuthenticator.isAuthTokenValid( serviceKey, authToken ) ) {
	            	logout( serviceKey, authToken );
	                requestCtx.abortWith( Response.status( Response.Status.UNAUTHORIZED ).build() );
	            } else {
	            	try {
	            		validate(authToken);
	            		 if ( !appAuthenticator.isSessionActiva( serviceKey, authToken ) ) {
	            			 logout( serviceKey, authToken );
	            			 requestCtx.abortWith( Response.status( Response.Status.UNAUTHORIZED ).build() );
	            		 }
					} catch (Exception e) {
						try {
							logout( serviceKey, authToken );
						} catch (Exception e2) {
							// TODO: handle exception
						}						
						requestCtx.abortWith( Response.status( Response.Status.UNAUTHORIZED ).build() );
					}
	            }
	        }
      }
    }
    private void logout(String serviceKey ,String authToken) {
    	AppAuthenticator appAuthenticator = AppAuthenticator.getInstance();
    	try {
    		appAuthenticator.logout( serviceKey, authToken );
		} catch (Exception e2) {
			// TODO: handle exception
		}	
    }
    
    private String validate(String jwt) throws InvalidJwtException {
        String subject = null;
        RsaJsonWebKey rsaJsonWebKey = RsaKeyProducer.produce();

        System.out.println("RSA hash code... " + rsaJsonWebKey.hashCode());

        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireSubject() // the JWT must have a subject claim
                .setVerificationKey(rsaJsonWebKey.getKey()) // verify the signature with the public key
                .build(); // create the JwtConsumer instance

        try {
            //  Validate the JWT and process it to the Claims
            JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
            subject = (String) jwtClaims.getClaimValue("sub");
            System.out.println("JWT validation succeeded! " + jwtClaims);
        } catch (InvalidJwtException e) {
            e.printStackTrace(); //on purpose
            throw e;
        }

        return subject;
    }
}