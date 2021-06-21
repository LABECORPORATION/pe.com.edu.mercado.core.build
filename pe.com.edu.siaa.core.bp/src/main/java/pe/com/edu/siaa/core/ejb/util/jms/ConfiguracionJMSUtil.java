package pe.com.edu.siaa.core.ejb.util.jms;

/**
 * La Class ConfiguracionJMSUtil.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * mapfre. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Fri Apr 25 18:56:23 COT 2017
 * @since Rep v1.0
 */
public final class ConfiguracionJMSUtil {

	/** La Constante QCF_NAME. */
	public static final String QCF_NAME = "siaaConnectionFactory";
	   
   	/** La Constante QUEUE_NAME. */
	public static final String QUEUE_NAME = "queue/siaaQueue";
	
	/** La Constante QCF_NAME. */
	public static final String QCF_TABLERO_CONTROL_NAME = "pwrTableroControlConnectionFactory";
	
	/** La Constante QUEUE_NAME. */
	public static final String QUEUE_TABLERO_CONTROL_NAME = "queue/pwrTableroControlQueue";
	
	/** La Constante QCF_TRAMA_CONTROL_NAME. */
	public static final String QCF_TRAMA_CONTROL_NAME = "pwrTramaConnectionFactory";
	
	/** La Constante QUEUE_TRAMA_CONTROL_NAME. */
	public static final String QUEUE_TRAMA_CONTROL_NAME = "queue/pwrTramaQueue";
	
	/** La Constante QCF_REPORTE_HEAVY_NAME. */
	public static final String QCF_REPORTE_HEAVY_NAME = "pwrReporteHeavyConnectionFactory";
	
	/** La Constante QUEUE_REPORTE_HEAVY_NAME. */
	public static final String QUEUE_REPORTE_HEAVY_NAME = "queue/pwrReporteHeavyQueue";
	
	/** La Constante QCF_TRAMA_CONTROL_NAME. */
	public static final String QCF_CORREO_NAME = "siaaCorreoConnectionFactory";
	
	/** La Constante QUEUE_TRAMA_CONTROL_NAME. */
	public static final String QUEUE_CORREO_NAME = "queue/siaaCorreoQueue";
	
	/** La Constante QCF_NAME. */
	public static final String QCF_NAME_DETALLE_PRODUCCION = "pwrConnectionDetalleProduccionFactory";
	
 	/** La Constante QUEUE_NAME. */
	public static final String QUEUE_NAME_DETALLE_PRODUCCION = "queue/pwrDetalleProduccionQueue";
	
	/** La Constante QUEUE_NAME. */
	public static final String TRANSACCTION_TIMEOUT = "86400";

	/**
	 * Instancia un nuevo configuracion jms util.
	 */
	private ConfiguracionJMSUtil() {
		
	}
}
