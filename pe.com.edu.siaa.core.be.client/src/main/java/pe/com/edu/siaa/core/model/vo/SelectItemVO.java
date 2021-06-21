package pe.com.edu.siaa.core.model.vo;

import java.io.Serializable;

import pe.com.edu.siaa.core.model.dto.common.ItemDTO;


/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class SelectItemVO.
 *
 * @author ndavilal
 * @version 1.0 , 06/04/2015
 * @since SIAA-CORE 2.1
 */
public class SelectItemVO implements Serializable {
 
	/** La Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** El value. */
	private Object id;
	
	/** El nombre. */
	private String nombre;
	
	/** La descripcion. */
	private String descripcion;

	private boolean checked;
	
	/**
	 * Instancia un nuevo select item vo.
	 */
	public SelectItemVO() {
		super();
	}

	/**
	 * Instancia un nuevo select item vo.
	 *
	 * @param id el id
	 * @param nombre el nombre
	 * @param descripcion el descripcion
	 */
	public SelectItemVO(Object id, String nombre,String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public SelectItemVO(ItemDTO objItem) {
		super();
		this.id = objItem.getIdItem();
		this.nombre = objItem.getNombre();
		this.descripcion = objItem.getCodigo()  + "";
	}
	
	
	/**
	 * Instancia un nuevo select item vo.
	 *
	 * @param id el id
	 * @param nombre el nombre
	 * @param descripcion el descripcion
	 */
	public SelectItemVO(Object id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = nombre;
	}
	
	
	
	/**
	 * Obtiene id.
	 *
	 * @return id
	 */
	public Object getId() {
		return id;
	}

	/**
	 * Establece el id.
	 *
	 * @param id el new id
	 */
	public void setId(Object id) {
		this.id = id;
	}

	/**
	 * Obtiene nombre.
	 *
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre.
	 *
	 * @param nombre el new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	
	
}