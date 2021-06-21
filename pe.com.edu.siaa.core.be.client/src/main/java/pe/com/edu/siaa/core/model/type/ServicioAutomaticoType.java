package pe.com.edu.siaa.core.model.type;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Enum ServicioAutomaticoType.
 *
 * @author ndavilal
 * @version 1.0 , 06/04/2015
 * @since SIAA-CORE 2.1
 */
public enum ServicioAutomaticoType {
	
	/** ELIMINAR_REPORTE_HISTORICO_PTSWEB. */
	ELIMINAR_REPORTE_HISTORICO_PTSWEB(1, "Eliminar Reporte Historial",ModuloServicioType.PTSWEB,"", false),
	
	/** ACTUALIZAR_CACHE_CONFIGURADOR. */
	ACTUALIZAR_CACHE_INTEGRACION_TRON_2000(2, "Actualizar Cache Integración Tron2000",ModuloServicioType.PTSWEB,"",false),
	
	/** ACTUALIZAR_CACHE_CONFIGURACION. */
	ACTUALIZAR_CACHE_CONFIGURACION(3, "Actualizar Cache Configuración",ModuloServicioType.PTSWEB,"",false),
	
	/** ACTUALIZAR_CACHE_CONFIGURACION_ACTIVE_DIRECTORY_WS. */
	ACTUALIZAR_CACHE_CONFIGURACION_ACTIVE_DIRECTORY_WS(4, "Actualizar Cache Active Directory WS",ModuloServicioType.PTSWEB,"",false),
	
	/** ACTUALIZAR_CACHE_SQL_MAPING. */
	ACTUALIZAR_CACHE_SQL_MAPING(5, "Actualizar SQL Maping",ModuloServicioType.PTSWEB,"",false),
	
	/** ACTUALIZAR_CACHE_REGLA. */
	ACTUALIZAR_CACHE_REGLA(45, "Actualizar Cache Regla",ModuloServicioType.PTSWEB,"",false),
	
	/** TIMER_PSTWEB_ALL. */
	TIMER_PSTWEB_ALL(6, "Timer PSTWEB ALL",ModuloServicioType.PTSWEB,"",true),
	
	/** TIMER_CACHE_ALL. */
	TIMER_CACHE_ALL(27, "Timer Cache Configuracion General Cache",ModuloServicioType.PTSWEB,"",true),
	
	/** MIGRAR REPORTE DETALLE RESUMEN PRODUCCION. */
	MIGRAR_REPORTE_DETALLE_RESUMEN_PRODUCCION(28, "Migrar Datos Reporte Producción",ModuloServicioType.PTSWEB,"",false),
	
	/** MIGRAR REPORTE DETALLE RESUMEN PRODUCCION. */
//	MIGRAR_REPORTE_TABLERO_CONTROL(29, "Migrar Datos Tablero Control", ModuloServicioType.PTSWEB,"",false),
	
	/** DETENER REPORTE DETALLE RESUMEN PRODUCCION. */
	DETENER_MIGRAR_REPORTE_DETALLE_RESUMEN_PRODUCCION(30, "Detener Migración Reporte Producción",ModuloServicioType.PTSWEB,"",false),
	
	/** DETENER REPORTE DETALLE RESUMEN PRODUCCION. */
	DETENER_MIGRAR_TABLERO_CONTROL(31, "Detener Migración Tablero Control", ModuloServicioType.PTSWEB,"",false),
	
	/** The activar todo migrar tablero control. */
	MIGRAR_TODO_TABLERO_CONTROL(32, "MigraR Todo Tablero Control",ModuloServicioType.PTSWEB,"",false),
	
	/** ACTIVAR REPORTE DETALLE RESUMEN PRODUCCION. */
	ACTIVAR_MIGRAR_TABLERO_CONTROL(33, "Activar Migración Tablero Control",ModuloServicioType.PTSWEB,"",false),

	/** The activar proceso batch tablero control. */
	ACTIVAR_PROCESO_BATCH_TABLERO_CONTROL(34, "Activar Migración Bath Tablero Control",ModuloServicioType.PTSWEB,"",false),
	
	/** The detener proceso batch tablero control. */
	DETENER_PROCESO_BATCH_TABLERO_CONTROL(35, "Detener Migración Bath Tablero Control",ModuloServicioType.PTSWEB,"",false),
		
	/** The migrar reporte tablero control universal. */
	MIGRAR_REPORTE_TABLERO_CONTROL_UNIVERSAL(36, "Migrar ejecutarMigrarEmisionUniversal ", ModuloServicioType.PTSWEB,"",false),
	
	/** The migrar reporte tablero control cobros cobranza. */
	MIGRAR_REPORTE_TABLERO_CONTROL_COBROS_COBRANZA(37, "Migrar ejecutarMigrarCobrosCobranza ", ModuloServicioType.PTSWEB,"",false),
	
	/** The migrar reporte tablero control pendiente cobro. */
	MIGRAR_REPORTE_TABLERO_CONTROL_PENDIENTE_COBRO(38, "Migrar ejecutarMigrarCobrosPendienteCobro ", ModuloServicioType.PTSWEB,"",false),
	
	/** The migrar reporte tablero control comisiones ganadas. */
	MIGRAR_REPORTE_TABLERO_CONTROL_COMISIONES_GANADAS(39, "Migrar ejecutarMigrarComisionesGanadas ", ModuloServicioType.PTSWEB,"",false),
	
	/** The migrar reporte tablero control emision stock. */
	MIGRAR_REPORTE_TABLERO_CONTROL_EMISION_STOCK(40, "Migrar ejecutarMigrarEmisionStock ", ModuloServicioType.PTSWEB,"",false),
	
	/** The migrar reporte tablero control siniestro cantidad. */
	MIGRAR_REPORTE_TABLERO_CONTROL_SINIESTRO_CANTIDAD(41, "Migrar ejecutarMigrarSiniestroCantidad ", ModuloServicioType.PTSWEB,"",false),
	
	/** The migrar reporte tablero control siniestro valor. */
	MIGRAR_REPORTE_TABLERO_CONTROL_SINIESTRO_VALOR(42, "Migrar ejecutarMigrarSiniestroValor ", ModuloServicioType.PTSWEB,"",false),
	
	/** The eliminar carpeta tablero control. */
	ELIMINAR_CARPETA_TABLERO_CONTROL(43, "Eliminar Carpeta Tablero Control",ModuloServicioType.PTSWEB,"",false),
	
	/** The eliminar carpeta index tablero control. */
	ELIMINAR_CARPETA_INDEX_TABLERO_CONTROL(44, "Eliminar Carpeta Index Tablero Control",ModuloServicioType.PTSWEB,"",false);
	
	/** El key. */
	private Integer key;
	
	/** El valor. */
	private String value;
	
	/** La modulo. */
	private ModuloServicioType modulo;
	
	/** La dependencia. */
	private String dependencia;
	
	/** La is timer. */
	private Boolean isTimer;
	

	/** La Constante lista. */
	private static final List<ServicioAutomaticoType> list = new ArrayList<ServicioAutomaticoType>();
	
	/** La Constante lookup. */
	private static final Map<Integer, ServicioAutomaticoType> lookup = new HashMap<Integer, ServicioAutomaticoType>();

	static {
		for (ServicioAutomaticoType s : EnumSet
				.allOf(ServicioAutomaticoType.class)) {
			list.add(s);
			lookup.put(s.getKey(), s);
		}
	}

	/**
	 * Instancia un nuevo modulo type.
	 *
	 * @param key el key
	 * @param value el value
	 * @param modulo el modulo
	 * @param dependencia el dependencia
	 * @param isTimer el is timer
	 */
	private ServicioAutomaticoType(Integer key, String value,ModuloServicioType modulo, String dependencia, Boolean isTimer) {
		this.key = key;
		this.value = value;
		this.isTimer = isTimer;
		this.modulo = modulo;
		this.dependencia = dependencia;
	}

	/**
	 * Obtiene key.
	 *
	 * @return key
	 */
	public Integer getKey() {
		return key;
	}
	
	/**
	 * Obtiene value.
	 *
	 * @return value
	 */
	public String getValue() {
		return value;
	}
	

	/**
	 * Obtiene modulo.
	 *
	 * @return modulo
	 */
	public ModuloServicioType getModulo() {
		return modulo;
	}

	
	
	/**
	 * Obtiene dependencia.
	 *
	 * @return dependencia
	 */
	public String getDependencia() {
		return dependencia;
	}

	
	/**
	 * Obtiene checks if is timer.
	 *
	 * @return checks if is timer
	 */
	public Boolean getIsTimer() {
		return isTimer;
	}

	/**
	 * Gets the.
	 *
	 * @param key el key
	 * @return the modulo type
	 */
	public static ServicioAutomaticoType get(Integer key) {
		return lookup.get(key);
	}
}