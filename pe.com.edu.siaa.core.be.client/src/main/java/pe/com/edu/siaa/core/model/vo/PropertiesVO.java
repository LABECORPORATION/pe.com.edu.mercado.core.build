package pe.com.edu.siaa.core.model.vo;

import java.util.ArrayList;
import java.util.List;

// Generated 11/03/2012 12:36:39 AM by Hibernate Tools 3.4.0.CR1


/**
 * <ul>
 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Class PropertiesDTO.
 *
 * @author ndavilal.
 * @version 1.0 , 07/09/2013
 * @since SIAA 2.0
 */
public class PropertiesVO implements java.io.Serializable {

	
	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = -6754142459848216652L;

	
	/** La name. */
	private String key;
	
	/** La descripcion. */
	private String descripcion;
	
	/** El value. */
	private String value;
	
	/** La lista key name proeprties. */
	List<String> listaKeyNameProeprties = new ArrayList<String>();
	
	
	
	/**
	 * Instancia un nuevo properties dto.
	 */
	public PropertiesVO() {
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
	 * Establece el key.
	 *
	 * @param key el new key
	 */
	public void setKey(String key) {
		this.key = key;
	}



	/**
	 * Obtiene descripcion.
	 *
	 * @return descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}



	/**
	 * Establece el descripcion.
	 *
	 * @param descripcion el new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	 * Establece el value.
	 *
	 * @param value el new value
	 */
	public void setValue(String value) {
		this.value = value;
	}



	/**
	 * Obtiene lista key name proeprties.
	 *
	 * @return lista key name proeprties
	 */
	public List<String> getListaKeyNameProeprties() {
		return listaKeyNameProeprties;
	}



	/**
	 * Establece el lista key name proeprties.
	 *
	 * @param listaKeyNameProeprties el new lista key name proeprties
	 */
	public void setListaKeyNameProeprties(List<String> listaKeyNameProeprties) {
		this.listaKeyNameProeprties = listaKeyNameProeprties;
	}

	
	
}
