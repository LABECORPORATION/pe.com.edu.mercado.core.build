package pe.com.edu.siaa.core.ejb.util.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateUtils;

import pe.com.edu.siaa.core.model.type.HoraType;

/**
 * <ul>
 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
 * </ul>
 * 
 * Clase que ofrece m�todos de manejo de calendarios
 *
 * @author ndavilal.
 * @version 1.0 , 25/03/2012
 * @since SIAA 2.0
 */
public class FechaDateUtil {

	/** Formato de la fecha para archivos. */
	public static final String FECHA_ARCHIVO_FECHA_HORA_PUNTO = "yyyy.MM.dd.hh.mm.ss";

	/** Formato de la fecha para recibir desde sistemas externos. */
	public static final String FECHA_SLASH = "dd/MM/yyyy";

	/** Formato de la fecha larga. */
	public static final String FECHA_LARGA = "EEEEE, dd 'de' MMMMM 'de' yyyy";

	/** Formato de la fecha larga con hora. */
	public static final String FECHA_LARGA_HORA = "EEEEE, dd 'de' MMMMM 'de' yyyy hh:mm aaa";

	/** Formato de la fecha para mostrar solo la hora. */
	public static final String HORA = "hh:mm a";

	/** zona Horaria. */
	private static final String ZONA_HORARIA = "GMT-5";

	/**
	 * Metodo para retornar la hora teniendo en cuenta un meridiano especifico.
	 *
	 * @return nueva fecha.
	 */
	public static Date obtenerFecha() {
		return Calendar.getInstance(TimeZone.getDefault()).getTime();
	}

	/**
	 * Obtener fecha formato.
	 *
	 * @param formato el formato
	 * @return the string
	 */
	public static String obtenerFechaFormato(String formato) {
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		return sdf.format(FechaDateUtil.obtenerFecha());
	}

	/**
	 * Obtiene formato fecha web.
	 *
	 * @return formato fecha web
	 */
	public static String getFormatoFechaWeb() {
		return FECHA_SLASH;
	}

	/**
	 * Convierte una fecha en string en un date.
	 *
	 * @param fecha el fecha
	 * @return Fecha en formato Date
	 * @throws BusinessException the business exception
	 */
	public static Date obtenerFecha(String fecha) throws Exception {
		DateFormat df = new SimpleDateFormat(FECHA_SLASH);
		Date date = null;
		try {
			date = df.parse(fecha);
		} catch (Exception e) {
			throw  e;
		}
		return date;
	}

	/**
	 * Convierte una fecha en string en un date enviando el formato en el que se encuentra.
	 *
	 * @param fecha a cambiar
	 * @param formato a usar para el cambio
	 * @return Fecha en formato Date
	 * @throws BusinessException the business exception
	 */
	public static Date obtenerFecha(String fecha, String formato) throws Exception {
		DateFormat df = new SimpleDateFormat(formato);
		Date date = null;
		try {
			date = df.parse(fecha);
		} catch (Exception e) {
			throw  e;
		}
		return date;
	}

	/**
	 * Convierte un objeto Date a String teniendo en cuenta el formato de presentaci�n de fechas.
	 *
	 * @param date El objeto de tipo Date
	 * @return String con la fecha
	 */
	public static String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(FECHA_SLASH);
		return sdf.format(date);
	}

	/**
	 * Convierte un objeto Date a String teniendo en cuenta el formato enviado.
	 *
	 * @param date El objeto de tipo Date
	 * @param formato con el formato a utilizar para la fecha
	 * @return String con la fecha
	 */
	public static String dateToString(Date date, String formato) {
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		return sdf.format(date);
	}

	/**
	 * Adiciona un n�mero de d�as calendario a una fecha dada como tipo java.util.Date.
	 * @param date Fecha de tipo java.util.Date
	 * @param dias N�mero de d�as calendario
	 * @return fecha con los nuevos valores
	 */
	public static Date adicionarDiasCalendario(Date date, int dias) {
		return DateUtils.addDays(date, dias);
	}

	/**
	 * Adiciona un n�mero de horas a una fecha dada como tipo java.util.Date.
	 *
	 * @param date Fecha de tipo java.util.Date
	 * @param horas el horas
	 * @return fecha con los nuevos valores
	 */
	public static Date adicionarHorasCalendario(Date date, int horas) {
		return DateUtils.addHours(date, horas);
	}

	/**
	 * Adiciona un n�mero de horas a una fecha dada como tipo java.util.Date.
	 *
	 * @param date Fecha de tipo java.util.Date
	 * @param minutos el minutos
	 * @return fecha con los nuevos valores
	 */
	public static Date adicionarMinutosCalendario(Date date, int minutos) {
		return DateUtils.addMinutes(date, minutos);
	}

	/**
	 * Retorna la diferencia de dias entre una fecha mayor y una fecha menor.
	 * @param menor Fecha de tipo java.util.Date fecha menor
	 * @param mayor Fecha de tipo java.util.Date fecha mayor
	 * @return int numero de dias de diferencia
	 */
	public static int restaFechas(Date menor, Date mayor) {
		int diferencia = 0;
		Date temp = new Date();
		temp.setTime(mayor.getTime());
		while (temp.after(menor)) {
			temp = adicionarDiasCalendario(temp, -1);
			diferencia++;
		}
		return diferencia;
	}

	/**
	 * Retorna la diferencia de horas entre una fecha mayor y una fecha menor.
	 * @param menor Fecha de tipo java.util.Date fecha menor
	 * @param mayor Fecha de tipo java.util.Date fecha mayor
	 * @return int numero de dias de diferencia
	 */
	public static int restaHoras(Date menor, Date mayor) {
		int diferencia = 0;
		Date temp = new Date();
		temp.setTime(mayor.getTime());
		while (temp.after(menor)) {
			temp = adicionarHorasCalendario(temp, -1);
			diferencia++;
		}
		return diferencia;
	}

	/**
	 * Retorna la diferencia de horas entre una fecha mayor y una fecha menor.
	 * @param menor Fecha de tipo java.util.Date fecha menor
	 * @param mayor Fecha de tipo java.util.Date fecha mayor
	 * @return int numero de dias de diferencia
	 */
	public static int restaMinutos(Date menor, Date mayor) {
		int diferencia = 0;
		Date temp = new Date();
		temp.setTime(mayor.getTime());
		while (temp.after(menor)) {
			temp = adicionarMinutosCalendario(temp, -1);
			diferencia++;
		}
		return diferencia;
	}

	/**
	 * Metodo que evalua si una fecha es mayor a otra por un numero de dias determina teniendo en cuenta horas y minutos.
	 *
	 * @param menor fecha menor
	 * @param mayor fecha mayor
	 * @param dia numero de dias de diferencia entre las fechas
	 * @return true si cumple ser mayor la diferencia al numero de dias false de lo contrario
	 */
	public static boolean diferenciaEntreFechasMayor(Date menor, Date mayor, int dia) {
		if (menor.before(mayor)) {
			if (FechaDateUtil.restaFechas(menor, mayor) > dia) {
				return true;
			}
			if (FechaDateUtil.restaFechas(menor, mayor) == dia) {
				if (FechaDateUtil.restaHoras(menor, mayor) > (dia * 24)) {
					return true;
				} else {
					if (FechaDateUtil.restaHoras(menor, mayor) == dia * 24) {
						if (FechaDateUtil.restaMinutos(menor, mayor) >= (60 * 24 * dia)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * calcula la diferencia entre fechas y retorna true si la fecha de inicio es menor o igual a la fecha final de lo contrario retorna
	 * false.
	 *
	 * @param inicio el inicio
	 * @param fin el fin
	 * @return true, en caso de exito
	 */
	public static boolean diferenciaEntreFechasMayor(Date inicio, Date fin) {
		Calendar calendarInicio = Calendar.getInstance();
		calendarInicio.setTime(inicio);
		long timeInicioEnMilis = calendarInicio.getTimeInMillis();
		Calendar calendarFin = Calendar.getInstance();
		calendarFin.setTime(fin);
		long timeFinEnMilis = calendarFin.getTimeInMillis();
		if (timeInicioEnMilis <= timeFinEnMilis) {
			return true;
		}
		return false;
	}

	/**
	 * M�todo para fijar crear un horario a partir de datos independientes de hora, minutos y segundos.
	 *
	 * @param hora Hora que se desea fijar
	 * @param minutos Minutos que se desean fijar
	 * @param segundos Segundos que se desean fijar
	 * @param periodoDia Periodo del d�a al que corresponde el horario (AM o PM)
	 * @return the calendar
	 */
	public static Calendar fijarHoraBloque(int hora, int minutos, int segundos, String periodoDia) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(obtenerFecha());
		calendario.set(Calendar.HOUR, hora);
		calendario.set(Calendar.MINUTE, minutos);
		calendario.set(Calendar.SECOND, segundos);
		if ((periodoDia.equals(HoraType.PM.getNombre())) && (hora < 12)) {
			calendario.set(Calendar.HOUR_OF_DAY, hora + 12);
		} else if ((periodoDia.equals(HoraType.AM.getNombre())) && (hora == 12)) {
			calendario.set(Calendar.HOUR_OF_DAY, 0);
		} else {
			calendario.set(Calendar.HOUR_OF_DAY, hora);
		}
		return calendario;
	}

	// ***********************************************
	// ************** Getters y Setters **************
	// ***********************************************

	/**
	 * Obtiene zonahoraria.
	 *
	 * @return zonahoraria
	 */
	public static String getZonahoraria() {
		return ZONA_HORARIA;
	}

	/**
	 * Es antes.
	 *
	 * @param fecha1 el fecha1
	 * @param fecha2 el fecha2
	 * @return true, en caso de exito
	 */
	public static boolean esAntes(Date fecha1, Date fecha2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(fecha1);

		Calendar c2 = Calendar.getInstance();
		c2.setTime(fecha2);

		return c1.before(c2);
	}

	/**
	 * Es despues.
	 *
	 * @param fecha1 el fecha1
	 * @param fecha2 el fecha2
	 * @return true, en caso de exito
	 */
	public static boolean esDespues(Date fecha1, Date fecha2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(fecha1);

		Calendar c2 = Calendar.getInstance();
		c2.setTime(fecha2);

		return c1.after(c2);
	}

}
