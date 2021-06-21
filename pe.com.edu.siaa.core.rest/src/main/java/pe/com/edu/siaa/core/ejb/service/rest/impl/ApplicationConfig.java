package pe.com.edu.siaa.core.ejb.service.rest.impl;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/*import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;*/

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */

//@ApplicationPath("/rs")
@ApplicationPath("/rest")
public class ApplicationConfig extends Application {

	/*  @Override
	    public Set<Class<?>> getClasses() {
	        Set<Class<?>> clazzes = new HashSet();
	        clazzes.add(JWTAuthFilter.class);
	        clazzes.add(BooksResource.class);
	        clazzes.add(JWTResponseFilter.class);

	        return clazzes;
	    }*/
}
