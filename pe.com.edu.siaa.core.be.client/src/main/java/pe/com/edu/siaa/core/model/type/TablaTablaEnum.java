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
public enum TablaTablaEnum {
	
	/** La lista LISTA_TIPO_TRAMA. */
	LISTA_TIPO_TRAMA("1"),//cbo
	CODIGO_TIPO_HOMOLOGACION("2"),//cbo
	CODIGO_TIPO_ORIGEN("3"),//cbo
	CODIGO_ESTADO_ORQUESTADOR("8");//cbo
	
	/** La id. */

	/** Identificador de la lista */
	private String id;

	/**
	 * Contructor por defecto.
	 *
	 * @param id id
	 */
	private TablaTablaEnum(String id) {
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
