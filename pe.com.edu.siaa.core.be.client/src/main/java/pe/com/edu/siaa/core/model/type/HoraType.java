package pe.com.edu.siaa.core.model.type;

/**
 * <ul>
 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Enum HoraType.
 *
 * @author ndavilal.
 * @version 1.0 , 22/12/2012
 * @since SIAA 2.0
 */
public enum HoraType {
	
	/** El AM. */
	AM("AM"), 
	
	/** El PM. */
	PM("PM");
	
	/** La nombre. */
	private String nombre;
	
	/**
	 * Instancia un nuevo hora type.
	 *
	 * @param nombre el nombre
	 */
	private HoraType(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene nombre.
	 *
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
}
