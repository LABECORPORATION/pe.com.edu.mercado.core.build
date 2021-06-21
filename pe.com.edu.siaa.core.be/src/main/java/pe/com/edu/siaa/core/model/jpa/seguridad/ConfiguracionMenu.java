package pe.com.edu.siaa.core.model.jpa.seguridad;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import pe.com.edu.siaa.core.model.jpa.common.Item;
import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class ConfiguracionMenu.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Wed Dec 13 22:04:25 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "ConfiguracionMenu", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_SECURIDAD)
public class ConfiguracionMenu implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id configuracion menu. */
    @Id
    @Column(name = "idConfiguracionMenu" , length = 32)
    private String idConfiguracionMenu;
   
    /** El menu. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMenu", referencedColumnName = "idMenu")
    private Menu menu;
   
    /** El item by componente. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idComponente", referencedColumnName = "idItem")
    private Item itemByComponente;
   
    /** El properties. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProperties", referencedColumnName = "idProperties")
    private Properties properties;
   
    /** El required. */
    @Column(name = "required")
    private Boolean required;
   
    /** El readonly. */
    @Column(name = "readonly")
    private Boolean readonly;
   
    /** El rendered. */
    @Column(name = "rendered")
    private Boolean rendered;
   
    /** El disabled. */
    @Column(name = "disabled")
    private Boolean disabled;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
   
    /** El usuario modificacion. */
    @Column(name = "usuarioModificacion" , length = 50)
    private String usuarioModificacion;
   
    /** El fecha modificacion. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaModificacion")
    private Date fechaModificacion;
   
    /**
     * Instancia un nuevo configuracion menu.
     */
    public ConfiguracionMenu() {
    }
   
   
    /**
     * Instancia un nuevo configuracion menu.
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
    public ConfiguracionMenu(String idConfiguracionMenu, Menu menu,Item itemByComponente,Properties properties,Boolean required, Boolean readonly, Boolean rendered, Boolean disabled, String estado, String usuarioModificacion, Date fechaModificacion ) {
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
     public Menu getMenu() {
        return this.menu;
    }
    /**
     * Establece el menu.
     *
     * @param menu el new menu
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    /**
     * Obtiene item by componente.
     *
     * @return item by componente
     */
     public Item getItemByComponente() {
        return this.itemByComponente;
    }
    /**
     * Establece el item by componente.
     *
     * @param itemByComponente el new item by componente
     */
    public void setItemByComponente(Item itemByComponente) {
        this.itemByComponente = itemByComponente;
    }
    /**
     * Obtiene properties.
     *
     * @return properties
     */
     public Properties getProperties() {
        return this.properties;
    }
    /**
     * Establece el properties.
     *
     * @param properties el new properties
     */
    public void setProperties(Properties properties) {
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
        ConfiguracionMenu other = (ConfiguracionMenu) obj;
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
        return "ConfiguracionMenu [idConfiguracionMenu=" + idConfiguracionMenu + "]";
    }
   
}