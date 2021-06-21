package pe.com.edu.siaa.core.ejb.seguridad.jwt;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;

import pe.com.edu.siaa.core.ejb.util.log.Logger;

public class JWTokenUtility {

	private static Logger log = Logger.getLogger(JWTokenUtility.class);
	
    public static String buildJWT(String subject) {
        RsaJsonWebKey rsaJsonWebKey = RsaKeyProducer.produce();
        //System.out.println("RSA hash code... " + rsaJsonWebKey.hashCode());
        JwtClaims claims = new JwtClaims();
        claims.setSubject(subject); // the subject/principal is whom the token is about
        //claims.setExpirationTimeMinutesInTheFuture(30);//TODO:aquitado para hacer por servidor la inactividad
       // claims.setExpirationTime(expirationTime);
        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setKey(rsaJsonWebKey.getPrivateKey());
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

        String jwt = null;
        try {
            jwt = jws.getCompactSerialization();
        } catch (JoseException ex) {
            log.error(ex);
        }

        /*System.out.println("Claim:\n" + claims);
        System.out.println("JWS:\n" + jws);
        System.out.println("JWT:\n" + jwt);*/

        return jwt;
    }
}
