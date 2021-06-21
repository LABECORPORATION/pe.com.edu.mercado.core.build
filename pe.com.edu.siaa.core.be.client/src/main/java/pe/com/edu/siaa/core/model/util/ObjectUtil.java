package pe.com.edu.siaa.core.model.util;

import java.math.BigDecimal;

// TODO: Auto-generated Javadoc
/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class ObjectUtil.
 *
 * @author ndavilal
 * @version 1.0 , 06/05/2016
 * @since SIAA-CORE 2.1
 */
public final class ObjectUtil {

	
	/**
	 * Instancia un nuevo object util.
	 */
	private ObjectUtil() {	
	}
	
	/**
	 * Object to long.
	 *
	 * @param object el object
	 * @return the long
	 */
	public static Long objectToLong(Object object) {
		if (!StringUtils.isNullOrEmptyNumeric(object)) {
			return Long.valueOf(object + "");
		} else {
			return 0L;
		}
	}
	
	public static Integer objectToInteger(Object object) {
		if (!StringUtils.isNullOrEmptyNumeric(object)) {
			return Integer.valueOf(object + "");
		} else {
			return 0;
		}
	}
	
	/**
	 * Object to string.
	 *
	 * @param object el object
	 * @return the string
	 */
	public static String objectToString(Object object) {
		if (object  != null) {
			return object + "";
		}
		return "";
	}
	
	
	/**
	 * Object to big decimal.
	 *
	 * @param object el object
	 * @return the big decimal
	 */
	public static BigDecimal objectToBigDecimal(Object object) {
		if (!StringUtils.isNullOrEmptyNumeric(object)) {
			return new BigDecimal(object + "");
		} else {
			return BigDecimal.ZERO;
		}
	}
}
