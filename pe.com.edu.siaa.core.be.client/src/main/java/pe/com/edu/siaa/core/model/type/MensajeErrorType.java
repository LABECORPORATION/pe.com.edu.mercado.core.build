package pe.com.edu.siaa.core.model.type;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import pe.com.edu.siaa.core.model.util.ConstanteTypeUtil;
import pe.com.edu.siaa.core.model.util.ResourceUtil;


/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Enum MensajeErrorType.
 *
 * @author ndavilal
 * @version 1.0 , 06/04/2015
 * @since SIAA-CORE 2.1
 */
public enum MensajeErrorType {
	
	/** La error reporte estado cuenta no ejecuta no incide fecha solicitud fecha programada. */
	ERROR_REPORTE_ESTADO_CUENTA_NO_EJECUTA_NO_INCIDE_FECHA_SOLICITUD_FECHA_PROGRAMADA("0", "errorReporteEstadoCuentaAgente.fechaNocoIncide"),
	
	/** La error reporte estado cuenta no ejecuta no incide fecha solicitud hora programada. */
	ERROR_REPORTE_ESTADO_CUENTA_NO_EJECUTA_NO_INCIDE_FECHA_SOLICITUD_HORA_PROGRAMADA("1", "errorReporteEstadoCuentaAgente.HoraNocoIncide"),

	/** La error reporte estado cuenta no ejecuta no incide fecha solicitud horainicio programada. */
	ERROR_REPORTE_ESTADO_CUENTA_NO_EJECUTA_NO_INCIDE_FECHA_SOLICITUD_HORAINICIO_PROGRAMADA("2", "errorReporteEstadoCuentaAgente.HoraInicioNocoIncide"),
	
	/** La error reporte estado cuenta no ejecuta no incide fecha solicitud horafin programada. */
	ERROR_REPORTE_ESTADO_CUENTA_NO_EJECUTA_NO_INCIDE_FECHA_SOLICITUD_HORAFIN_PROGRAMADA("3", "errorReporteEstadoCuentaAgente.HoraFinNocoIncide"),
	
	/** La error reporte estado cuenta sin ejecucion multiple solicitud progreso. */
	ERROR_REPORTE_ESTADO_CUENTA_SIN_EJECUCION_MULTIPLE_SOLICITUD_PROGRESO("4", "errorReporteEstadoCuentaAgente.sinEjecucionMultiple.SolicitudProgreso"),

	/** La error reporte estado cuenta sin ejecucion multiple solicitud pendiente. */
	ERROR_REPORTE_ESTADO_CUENTA_SIN_EJECUCION_MULTIPLE_SOLICITUD_PENDIENTE("5", "errorReporteEstadoCuentaAgente.sinEjecucionMultiple.SolicitudPendiente"),

	/** La reporte solicitud exito envia correo. */
	REPORTE_SOLICITUD_EXITO_ENVIA_CORREO("6", "reporteEstadoCuentaAgente.reporteSoliciudExitoEnviaCorreo");
	
	/** La Constante LOO_KUP_MAP. */
	private static final Map<String, MensajeErrorType> LOO_KUP_MAP = new HashMap<String, MensajeErrorType>();

	static {
		for (MensajeErrorType s : EnumSet.allOf(MensajeErrorType.class)) {
			LOO_KUP_MAP.put(s.getKey(), s);
		}
	}

	/** El key. */
	private String key;

	/** El value. */
	private String value;

	/**
	 * Instancia un nuevo accion type.
	 * 
	 * @param key
	 *            el key
	 * @param value
	 *            el value
	 */
	private MensajeErrorType(String key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Gets the.
	 * 
	 * @param key
	 *            el key
	 * @return the accion type
	 */
	public static MensajeErrorType get(String key) {
		return LOO_KUP_MAP.get(key);
	}

	/**
	 * Obtiene key.
	 * 
	 * @return key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Obtiene value.
	 * 
	 * @return value
	 */
	public String getValue() {
		return  ResourceUtil.getString(ConstanteTypeUtil.BUNDLE_NAME_ERROR_TYPE, value); 
	}	


}
