package pe.com.edu.siaa.core.model.dto.seguridad;

import java.io.Serializable;
import java.util.Date;

import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO;
 

/**
 * La Class ConfiguracionMenuDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Thu Dec 14 00:27:42 COT 2017
 * @since SIAA-CORE 2.1
 */
public class ConfiguracionMenuDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id configuracion menu. */
    private String idConfiguracionMenu;
   
    /** El menu. */
    private MenuDTO menu;
   
    /** El item by componente. */
    private ItemDTO itemByComponente;
   
    /** El properties. */
    private PropertiesDTO properties;
   
    /** El required. */
    private Boolean required;
   
    /** El readonly. */
    private Boolean readonly;
   
    /** El rendered. */
    private Boolean rendered;
   
    /** El disabled. */
    private Boolean disabled;
   
    /** El estado. */
    private String estado;
   
    /** El usuario modificacion. */
    private String usuarioModificacion;
   
    /** El fecha modificacion. */
    private Date fechaModificacion;
   
  //trasient	
  	/** El flag show required. */
  	private boolean showRequired;
  	
  	/** El flag show readonly. */
  	private boolean showReadonly;
  	
  	/** El flag show rendered. */
  	private boolean showRendered;
  	
  	/** El flag show disabled. */
  	private boolean showDisabled;
  	
    /**
     * Instancia un nuevo configuracion menuDTO.
     */
    public ConfiguracionMenuDTO() {
    }
   
   
    /**
     * Instancia un nuevo configuracion menuDTO.
     *
     * @param idConfiguracionMenu el id configuracion menu
     * @param menu el menu
     * @param itemByComponente el item by componente
     * @param properties el properties
     * @param required el required
     * @param readonly el readonly
     * @param rendered el rendered
     * @param disabled el disabled
     * @param estado el estado
     * @param usuarioModificacion el usuario modificacion
     * @param fechaModificacion el fecha modificacion
     */
    public ConfiguracionMenuDTO(String idConfiguracionMenu, MenuDTO menu,ItemDTO itemByComponente,PropertiesDTO properties,Boolean required, Boolean readonly, Boolean rendered, Boolean disabled, String estado, String usuarioModificacion, Date fechaModificacion ) {
        super();
        this.idConfiguracionMenu = idConfiguracionMenu;
        this.menu = menu;
        this.itemByComponente = itemByComponente;
        this.properties = properties;
        this.required = required;
        this.readonly = readonly;
        this.rendered = rendered;
        this.disabled = disabled;
        this.estado = estado;
        this.usuarioModificacion = usuarioModificacion;
        this.fechaModificacion = fechaModificacion;
    }
   
    //get y set
    /**
     * Obtiene id configuracion menu.
     *
     * @return id configuracion menu
     */
     public String getIdConfiguracionMenu() {
        return this.idConfiguracionMenu;
    }
    /**
     * Establece el id configuracion menu.
     *
     * @param idConfiguracionMenu el new id configuracion menu
     */
    public void setIdConfiguracionMenu(String idConfiguracionMenu) {
        this.idConfiguracionMenu = idConfiguracionMenu;
    }
    /**
     * Obtiene menu.
     *
     * @return menu
     */
     public MenuDTO getMenu() {
        return this.menu;
    }
    /**
     * Establece el menu.
     *
     * @param menu el new menu
     */
    public void setMenu(MenuDTO menu) {
        this.menu = menu;
    }
    /**
     * Obtiene item by componente.
     *
     * @return item by componente
     */
     public ItemDTO getItemByComponente() {
        return this.itemByComponente;
    }
    /**
     * Establece el item by componente.
     *
     * @param itemByComponente el new item by componente
     */
    public void setItemByComponente(ItemDTO itemByComponente) {
        this.itemByComponente = itemByComponente;
    }
    /**
     * Obtiene properties.
     *
     * @return properties
     */
     public PropertiesDTO getProperties() {
        return this.properties;
    }
    /**
     * Establece el properties.
     *
     * @param properties el new properties
     */
    public void setProperties(PropertiesDTO properties) {
        this.properties = properties;
    }
    /**
     * Obtiene required.
     *
     * @return required
     */
     public Boolean getRequired() {
        return this.required;
    }
    /**
     * Establece el required.
     *
     * @param required el new required
     */
    public void setRequired(Boolean required) {
        this.required = required;
    }
    /**
     * Obtiene readonly.
     *
     * @return readonly
     */
     public Boolean getReadonly() {
        return this.readonly;
    }
    /**
     * Establece el readonly.
     *
     * @param readonly el new readonly
     */
    public void setReadonly(Boolean readonly) {
        this.readonly = readonly;
    }
    /**
     * Obtiene rendered.
     *
     * @return rendered
     */
     public Boolean getRendered() {
        return this.rendered;
    }
    /**
     * Establece el rendered.
     *
     * @param rendered el new rendered
     */
    public void setRendered(Boolean rendered) {
        this.rendered = rendered;
    }
    /**
     * Obtiene disabled.
     *
     * @return disabled
     */
     public Boolean getDisabled() {
        return this.disabled;
    }
    /**
     * Establece el disabled.
     *
     * @param disabled el new disabled
     */
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
    /**
     * Obtiene estado.
     *
     * @return estado
     */
     public String getEstado() {
        return this.estado;
    }
    /**
     * Establece el estado.
     *
     * @param estado el new estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    /**
     * Obtiene usuario modificacion.
     *
     * @return usuario modificacion
     */
     public String getUsuarioModificacion() {
        return this.usuarioModificacion;
    }
    /**
     * Establece el usuario modificacion.
     *
     * @param usuarioModificacion el new usuario modificacion
     */
    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }
    /**
     * Obtiene fecha modificacion.
     *
     * @return fecha modificacion
     */
     public Date getFechaModificacion() {
        return this.fechaModificacion;
    }
    /**
     * Establece el fecha modificacion.
     *
     * @param fechaModificacion el new fecha modificacion
     */
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
    
    /**
	 * Comprueba si es show required.
	 *
	 * @return true, si es show required
	 */
	public boolean isShowRequired() {
		return showRequired;
	}


	/**
	 * Establece el show required.
	 *
	 * @param showRequired el new show required
	 */
	public void setShowRequired(boolean showRequired) {
		this.showRequired = showRequired;
	}

	/**
	 * Comprueba si es show readonly.
	 *
	 * @return true, si es show readonly
	 */
	public boolean isShowReadonly() {
		return showReadonly;
	}


	/**
	 * Establece el show readonly.
	 *
	 * @param showReadonly el new show readonly
	 */
	public void setShowReadonly(boolean showReadonly) {
		this.showReadonly = showReadonly;
	}

	/**
	 * Comprueba si es show rendered.
	 *
	 * @return true, si es show rendered
	 */
	public boolean isShowRendered() {
		return showRendered;
	}


	/**
	 * Establece el show rendered.
	 *
	 * @param showRendered el new show rendered
	 */
	public void setShowRendered(boolean showRendered) {
		this.showRendered = showRendered;
	}

	/**
	 * Comprueba si es show disabled.
	 *
	 * @return true, si es show disabled
	 */
	public boolean isShowDisabled() {
		return showDisabled;
	}


	/**
	 * Establece el show disabled.
	 *
	 * @param showDisabled el new show disabled
	 */
	public void setShowDisabled(boolean showDisabled) {
		this.showDisabled = showDisabled;
	}
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idConfiguracionMenu == null) ? 0 : idConfiguracionMenu.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ConfiguracionMenuDTO other = (ConfiguracionMenuDTO) obj;
        if (idConfiguracionMenu == null) {
            if (other.idConfiguracionMenu != null) {
                return false;
            }
        } else if (!idConfiguracionMenu.equals(other.idConfiguracionMenu)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ConfiguracionMenuDTO [idConfiguracionMenu=" + idConfiguracionMenu + "]";
    }
   
}