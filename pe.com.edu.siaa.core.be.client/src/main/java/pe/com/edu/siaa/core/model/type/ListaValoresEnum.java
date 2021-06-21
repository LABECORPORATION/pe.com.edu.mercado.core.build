/*
 * 
 */
package pe.com.edu.siaa.core.model.type;

/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Enum ListaValoresEnum.
 *
 * @author ndavilal
 * @version 1.0 , 06/04/2015
 * @since SIAA-CORE 2.1
 */
public enum ListaValoresEnum {
	
	/** La lista tipo documento identidad. */
	LISTA_TIPO_DOCUMENTO_IDENTIDAD("obtenerListaTipoDocumento"),//cbo
	/** La lista cajero. */
LISTA_CAJERO("obtenerListaCajero"),//Modal
	/** La lista tipo gestor. */
LISTA_TIPO_GESTOR("obtenerListaTipoGestor"),//cbo
	/** La lista tipo moneda. */
LISTA_TIPO_MONEDA("obtenerListaTipoMoneda"),//cbo
/** La lista tipo moneda cubo. */
LISTA_TIPO_MONEDA_CUBO("obtenerListaTipoMonedaCubo"),//cbo
	/** La lista situacion poliza. */
LISTA_SITUACION_POLIZA("obtenerListaSituacionPoliza"),//cbo
	/** La lista ejecutivo. */
LISTA_EJECUTIVO("obtenerListaEjecutivo"),//modal
/** La lista ejecutivo. */
LISTA_EJECUTIVO_CUBO("obtenerListaEjecutivoCubo"),//modal
	/** La lista modalidad. */
LISTA_MODALIDAD("obtenerListaModalidad"),//modal
/** La lista modalidad. */
LISTA_MODALIDAD_DETALLE_PRODUCCION("obtenerListaModalidadDetalleProduccion"),//modal
	/** La lista gestor. */
LISTA_GESTOR("obtenerListaGestor"),//modal
	/** La lista tratamiento. */
LISTA_TRATAMIENTO("obtenerListaTratamiento"),//cbo		
	/** La lista tipo vehiculo. */
LISTA_TIPO_VEHICULO("obtenerListaTipoVehiculo"),//cbo
	/** La lista zona. */
LISTA_ZONA("obtenerListaZona"),//CBO--TarifaSOAT
	/** La lista tipo volante. */
LISTA_TIPO_VOLANTE("obtenerListaTipoVolante"),//CBO--TarifaSOAT
	/** La lista tipo estado. */
LISTA_TIPO_ESTADO("obtenerListaEstado"),//CBO--TarifaSOAT
	/** La lista entidad bancaria. */
LISTA_ENTIDAD_BANCARIA("obtenerListaEntidadBancaria"),//Modal
/** La lista ejecutivo. */
LISTA_RED("obtenerListaRed"),//modal
/** La lista motivo anulacion. */
LISTA_MOTIVO_ANULACION("obtenerListaMotivoAnulacion"),//modal

/** La lista canal. */
LISTA_CANAL("obtenerListaCanal"),//cbo
/** La lista producto. */
LISTA_PRODUCTO("obtenerListaProducto"),//cbo

/** La lista tabla disponible. */
LISTA_TABLA_DISPONINLE("obtenerListaTablaDisponible"),//cbo

/* NFIRE 13/05/2015 inicio */
/** The lista tipo reporte. */
LISTA_TIPO_REPORTE("obtenerListaTipoReporte"), // --Indicador Siniestro
/** The lista ejecutivo siniestro. */
LISTA_EJECUTIVO_SINIESTRO("obtenerListaEjecutivoSiniestro"), // modal
/** The lista tipo expediente. */
LISTA_TIPO_EXPEDIENTE("obtenerListaTipoExpediente"), // modal
/** The lista estado siniestro seguimiento. */
LISTA_ESTADO_SINIESTRO_SEGUIMIENTO("obtenerListaEstadoSiniestroSeguimiento"), // ESTADO SINIESTRO
/** The lista estado siniestro gestion. */
LISTA_ESTADO_SINIESTRO_GESTION("obtenerListaEstadoSiniestroGestion"), // ESTADO SINIESTRO
/* NFIRE 13/05/2015 fin */

/** La lista LISTA_TIPO_TRAMA. */
LISTA_TIPO_TRAMA("obtenerListaTipoTrama"),//cbo
	
/** La lista tipo deducible equivalencia siteds. */
LISTA_TIPO_DEDUCIBLE_EQUIVALENCIA_SITEDS("obtenerListaTipoDeducibleEquivSITED"),//cbo	
	
/** La lista modalidad multiproducto. */
LISTA_MODALIDAD_MULTIPRODUCTO("obtenerListaModalidadMultiproducto"),//modal	
/** La lista tipo clase siniestro. */

LISTA_TIPO_CLASE_SINIESTRO("obtenerListaTipoClaseSiniestro"),//cbo

/** La lista codigo clase siniestro. */
LISTA_CODIGO_CLASE_SINIESTRO("obtenerListaCodigoClaseSiniestro"),//cbo
	
LISTA_CODIGO_OFICINA_SINIESTRO("obtenerCodigoOficinaSiniestro");//cbo	
	
	
	/** La id. */

	/** Identificador de la lista */
	private String id;

	/**
	 * Contructor por defecto.
	 *
	 * @param id id
	 */
	private ListaValoresEnum(String id) {
		this.id = id;
	}

	/**
	 * Obtiene id.
	 *
	 * @return id
	 * @see #id
	 */
	public String getId() {
		return id;
	}

}
