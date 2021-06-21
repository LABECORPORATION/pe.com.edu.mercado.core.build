package pe.com.edu.siaa.core.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pe.com.edu.siaa.core.model.dto.seguridad.ConfiguracionMenuDTO;


/**
 * <ul>
 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Class ConfiguracionMenuVO.
 *
 * @author ndavilal.
 * @version 1.0 , 31/08/2013
 * @since SIAA 2.0
 */
public class ConfiguracionMenuVO  implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1;
	
	/** El id. */
	private Long id;
	
	/** El codigo. */
	private Long codigo;
		
	/** La descripcion. */
	private String descripcion;
		
	/** La lista configuracion menus. */
	private List<ConfiguracionMenuDTO> listaConfiguracionMenus = new ArrayList<ConfiguracionMenuDTO>();
	
	/**
	 * Instancia un nuevo concepto pago dto.
	 */
	public ConfiguracionMenuVO() {
		super();
	}
	
	

	/**
	 * Instancia un nuevo configuracion menu dto.
	 *
	 * @param id el id
	 * @param descripcion el descripcion
	 * @param listaConfiguracionMenus el lista configuracion menus
	 */
	public ConfiguracionMenuVO(Long id, String descripcion,
			List<ConfiguracionMenuDTO> listaConfiguracionMenus) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.listaConfiguracionMenus = listaConfiguracionMenus;
	}



	//get y set
	/**
	 * Obtiene id.
	 *
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Establece el id.
	 *
	 * @param id el new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	
	/**
	 * Obtiene codigo.
	 *
	 * @return codigo
	 */
	public Long getCodigo() {
		return codigo;
	}



	/**
	 * Establece el codigo.
	 *
	 * @param codigo el new codigo
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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
	 * Obtiene lista configuracion menus.
	 *
	 * @return lista configuracion menus
	 */
	public List<ConfiguracionMenuDTO> getListaConfiguracionMenus() {
		return listaConfiguracionMenus;
	}

	/**
	 * Establece el lista configuracion menus.
	 *
	 * @param listaConfiguracionMenus el new lista configuracion menus
	 */
	public void setListaConfiguracionMenus(
			List<ConfiguracionMenuDTO> listaConfiguracionMenus) {
		this.listaConfiguracionMenus = listaConfiguracionMenus;
	}

	
	
	
}
