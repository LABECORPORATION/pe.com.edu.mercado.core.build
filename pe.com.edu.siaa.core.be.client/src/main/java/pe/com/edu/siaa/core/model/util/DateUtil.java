package pe.com.edu.siaa.core.model.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class DateUtil.
 *
 * @author ndavilal
 * @version 1.0 , 06/04/2015
 * @since SIAA-CORE 2.1
 */
public class DateUtil {
	
	/**
	 * Adds the.
	 *
	 * @param date el date
	 * @param amount el amount
	 * @return the date
	 */
	public static Date add(Date date, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, amount);
		return c.getTime();
	}
	
	/**
	 * Obtiene date.
	 *
	 * @return date
	 */
	public static Date getDate() {
		return new Date();
	}
	
	/**
	 * Fecha actual.
	 *
	 * @return the date
	 */
	public static Date fechaActual() {
		Calendar c = Calendar.getInstance();
		return c.getTime(); 
	}

	/**
	 * Obtener anio.
	 *
	 * @param date el date
	 * @return the int
	 */
	public static int obtenerAnio(Date date) {
	    if (null == date) {
	        return 0; 
	    } else {
	        String  formato = "yyyy";
	        SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
	        return Integer.parseInt(dateFormat.format(date));
	    }

	}
	
	/**
	 * Obtener mes.
	 *
	 * @param date el date
	 * @return the int
	 */
	public static int obtenerMes(Date date) {

	    if (null == date) {
	        return 0;
	    } else {
	        String formato = "MM";
	        SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
	        return Integer.parseInt(dateFormat.format(date));

	    }

	}
	
	/**
	 * Obtener dia.
	 *
	 * @param date el date
	 * @return the int
	 */
	public static int obtenerDia(Date date) {

	    if (null == date) {
	        return 0;
	    } else {
	        String formato = "dd";
	        SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
	        return Integer.parseInt(dateFormat.format(date));

	    }

	}

	/**
	 * Parses the fecha.
	 *
	 * @param fecha el fecha
	 * @return the date
	 */
	public static Date ParseFecha(String fecha) {
	    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	    Date fechaDate = null;
	    try {
	        fechaDate = formato.parse(fecha);
	    } catch (ParseException ex) {
	        System.out.println(ex);
	    }
	    return fechaDate;
	}





}

