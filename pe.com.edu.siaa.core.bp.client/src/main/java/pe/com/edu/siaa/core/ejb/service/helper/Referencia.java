package pe.com.edu.siaa.core.ejb.service.helper;

import javax.naming.NamingException;

import pe.com.edu.siaa.core.ejb.service.util.ServiceLocatorUtil;

/**
 * La Class Referencia.
 * <ul>
 * <li>Copyright 2017 ndavilal-
 * mapfre. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Tue Apr 29 19:20:13 COT 2017
 * @since SIAA-CORE 2.1
 */
public class Referencia {
	
	/** La Constante mapfre_REF. */
	//private static final String MAPFRE_REF = "mapfre.ref.";
	
	private static final String SUFIJO_REMOTE = "Remote";
	private static final String JAVA_APP_EJB_REF = "java:app/";
	private static final String JAVA_GLOBAL_EJB_REF_REMOTE = "java:global/";
	
	/** La log. 
	private static Logger log = Logger.getLogger(Referencia.class);*/

	/**
	 * M&eacute;todo que obtiene la referencia del servicio de negocio.
	 * 
	 * @param <T>
	 *            Representa al tipo de objeto de la referencia a obtener
	 * @param clazz
	 *            Representa a la clase de la referencia que se desea obtener
	 * @return La referencia del servicio de negocio.
	 */
	@SuppressWarnings("unchecked")
	public static final <T> T getReference(Class<T> clazz) {
		try {
			String nombreClase = clazz.getSimpleName();
			if (!clazz.getSimpleName().toString().contains(SUFIJO_REMOTE)) {
				nombreClase = nombreClase.replace("Local", "").trim();
				return (T) ServiceLocatorUtil.getInstance().getService(JAVA_APP_EJB_REF + nombreClase);
			} else {
				return (T) ServiceLocatorUtil.getInstance().getService(JAVA_GLOBAL_EJB_REF_REMOTE + nombreClase);
			}

		} catch (NamingException e) {
		
			return null;
		} 
	}
}
