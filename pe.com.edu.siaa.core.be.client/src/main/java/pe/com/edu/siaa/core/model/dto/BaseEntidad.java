package pe.com.edu.siaa.core.model.dto;

/**
 * <ul>
 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Interface BaseEntidad.
 *
 * @author ndavilal.
 * @version 1.0 , 18/03/2012
 * @since SIAA 2.0
 */
public interface BaseEntidad {

	/**
	 * Identificador del Entitie.
	 *
	 * @return id de la entidad.
	 */
	Object getId();

	/**
	 * Valor de texto que contiene lo mas representativo del entity. Por ejemplo
	 * un nombre, denominacion ,etc
	 * 
	 * @return Nombreo o descriptor
	 */
	String getLabel();

}
