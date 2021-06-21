package pe.com.edu.siaa.core.ejb.util.jms;


/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class UUIDUtil.
 *
 * @author ndavilal
 * @version 1.0 , 11/04/2016
 * @since SIAA-CORE 2.1
 */
public class UUIDUtil {

	/**
	 * Generar uuid.
	 *
	 * @return the string
	 */
	public static String generarUUID() {
		return java.util.UUID.randomUUID().toString();
	}
	
	/**
	 * Generar element uuid.
	 *
	 * @return the string
	 */
	public static String generarElementUUID() {
		return java.util.UUID.randomUUID().toString().replaceAll("-", "");
	}
}
