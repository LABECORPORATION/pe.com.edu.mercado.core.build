package pe.com.edu.siaa.core.model.type;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * <ul>
 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Enum CronogramaType.
 *
 * @author ndavilal.
 * @version 1.0 , 24/09/2012
 * @since SIAA 2.0
 */
public enum CronogramaType {

	//Los datos se encuentrar registrados en item//TODO:VER ESTO
    /** La ADMISIOM. */
	ADMISIOM(1L,"Admisión"),
	
	/**EL CIERRE_CARGA_LECTIVA. */
	CIERRE_CARGA_LECTIVA(13L,"Cierre Carga Lectiva"),
	
	/** La CONVALIDACION. */
	CONVALIDACION(14L,"Convalidaciones"), 
	
	/** EL DOCUMENTO_RESERVA_MATRICULA. */
	DOCUMENTO_RESERVA_MATRICULA(4L,"Documentos Reserva de Matricula"),
	
	/** La ENTREGA_REGISTROS_DIRECCION_REGISTROS_ACADEMICOS. */
	ENTREGA_REGISTROS_DIRECCION_REGISTROS_ACADEMICOS(19L,"Entrega de Registros a la Dirección de Registros Académicos"), 
	
	/** La ENTREGA_REGISTROS_A_FACULTADES. */
	ENTREGA_REGISTROS_A_FACULTADES(11L,"Entrega de Registros a las Facultades"),
	
	/** EL EXAMEN_PRIMERA_UNIDAD. */
	EXAMEN_PRIMERA_UNIDAD(7L,"Exámen 1º Unidad"),
	
	/** EL EXAMEN_SEGUNDA_UNIDAD. */
	EXAMEN_SEGUNDA_UNIDAD(8L,"Exámen 2º Unidad"),
	
	/** EL EXAMEN_TERCERA_UNIDAD. */
	EXAMEN_TERCERA_UNIDAD(9L,"Exámen 3º Unidad"),
	
	/** EL EXAMEN_CUARTA_UNIDAD. */
	EXAMEN_CUARTA_UNIDAD(10L,"Exámen 4º Unidad"),
	
	
	
	/** EL INICIO_CLASES. */
	INICIO_CLASES(12L,"Inicio de Clases"),
	
	
 	
 	/** EL RATIFICACION_HORARIO_CARGA_LECTIVA. */
 	RATIFICACION_HORARIO_CARGA_LECTIVA(15L,"Ratificacion Carga Lectiva y Horario por Facultades"),
 	
 	/** LA REINCORPORACION. */
 	REINCORPORACION(16L,"Reincorporacion Alumnos por Escuela"),
 	
 	/** EL RETIRO_REINSCRIPCION_CURSO. */
 	RETIRO_REINSCRIPCION_CURSO(17L,"Retiro y/o Reinscripción de Cursos"),
 	
 	/** EL TRASLADO_EXTERNO_INTERNO. */
 	TRASLADO_EXTERNO_INTERNO(18L,"Traslados Externos e Internos"),
 	
 	/** LA LICENCIA_DE_ESTUDIOS. */
 	LICENCIA_DE_ESTUDIOS(22L,"Entrega de Oficios o Resoluciones de Reserva de Matricula(Licencia de Estudios) por las Facultades"),
 	
 	/** LA PRIMERA_CUOTA. */
 	PRIMERA_CUOTA(23L,"1º Cuota"),
 	
 	/** LA 	SEGUNDA_CUOTA. */
 	SEGUNDA_CUOTA(24L,"2º Cuota"),
 	
 	/** LA TERCERA_CUOTA. */
 	TERCERA_CUOTA(25L,"3º Cuota"),
 	
 	/** LA CUARTA_CUOTA. */
 	CUARTA_CUOTA(26L,"4º Cuota"),
 	
 	/** LA QUINTA_CUOTA. */
 	QUINTA_CUOTA(27L,"5º Cuota"),
	
 	/*DATOS VALIDACION */
 	
 	/** EL CIERRE_NOTAS. */
	 CIERRE_NOTAS(5L,"Cierre notas"),
 	
	 /** La MATRICULA_EXTEMPORANEA. */
	 MATRICULA_EXTEMPORANEA(3L,"Matricula Extemporanea"),
		
	/** La MATRICULA_NORMAL. */
	 MATRICULA_NORMAL(2L,"Matricula normal"),
	 
	 /** EL FECHA_APLAZADO. */
	 FECHA_APLAZADO(6L,"Fecha Aplazado"),
	 
	 
	/* datos combo extistentes */ 	 	
	 
	 /** EL LLENAR_FICHA_INSCRIPCIPCION. */
	 LLENAR_FICHA_INSCRIPCIPCION(21L,"Llenar ficha de Inscripción"),
	 
	 /** EL RECEPCION_FICHAS. */
	 RECEPCION_FICHAS(20L,"Recepción de Fichas"),
	
	/** LA PRIMERA_UNIDAD_NOTA. */
	REGISTRAR_PRIMERA_UNIDAD_NOTA(28L,"Registrar Promedio 1ª Unidad"),
	
	/** LA PRIMERA_UNIDAD_NOTA. */
	REGISTRAR_SEGUNDA_UNIDAD_NOTA(29L,"Registrar Promedio 2ª Unidad"),
	
	/** LA PRIMERA_UNIDAD_NOTA. */
	REGISTRAR_TERCERA_UNIDAD_NOTA(30L,"Registrar Promedio 3ª Unidad"),
	
	/** LA PRIMERA_UNIDAD_NOTA. */
	REGISTRAR_CUARTA_UNIDAD_NOTA(31L,"Registrar Promedio 4ª Unidad");
	
	 
	/** La Constante looKup. */
	private static final Map<Long, CronogramaType> LOO_KUP_MAP = new HashMap<Long, CronogramaType>();
	
	
	static {
		for (CronogramaType s : EnumSet.allOf(CronogramaType.class)) {
			LOO_KUP_MAP.put(s.getKey(), s);
		}
	}

	/** El key. */
	private Long key;
	
	/** El value. */
	private String value;

	/**
	 * Instancia un nuevo tipo asinatura type.
	 *
	 * @param key el key
	 * @param value el value
	 */
	private CronogramaType(Long key, String value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Gets the.
	 *
	 * @param key el key
	 * @return the tipo asinatura type
	 */
	public static CronogramaType get(Long key) {
		return LOO_KUP_MAP.get(key);
	}

	/**
	 * Obtiene key.
	 *
	 * @return key
	 */
	public Long getKey() {
		return key;
	}

	/**
	 * Obtiene nombre.
	 *
	 * @return nombre
	 */
	public String getValue() {
		return value;
	}

}
