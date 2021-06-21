package pe.com.edu.siaa.core.model.jpa.seguridad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pe.com.edu.siaa.core.model.jpa.common.Item;
import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class PropertiesLenguaje.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Wed Dec 13 22:04:27 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "PropertiesLenguaje", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_SECURIDAD)
public class PropertiesLenguaje implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id properties lenguaje. */
    @Id
    @Column(name = "idPropertiesLenguaje" , length = 32)
    private String idPropertiesLenguaje;
   
    /** El item by lenguaje. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idLenguaje", referencedColumnName = "idItem")
    private Item itemByLenguaje;
   
    /** El properties. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProperties", referencedColumnName = "idProperties")
    private Properties properties;
   
    /** El value. */
    @Column(name = "value" , length = 100)
    private String value;
   
    /**
     * Instancia un nuevo properties lenguaje.
     */
    public PropertiesLenguaje() {
    }
   
   
    /**
     * Instancia un nuevo properties lenguaje.
     *
     * @param idPropertiesLenguaje el id properties lenguaje
     * @param itemByLenguaje el item by lenguaje
     * @param properties el properties
     * @param value el value
     */
    public PropertiesLenguaje(String idPropertiesLenguaje, Item itemByLenguaje,Properties properties,String value ) {
        super();
        this.idPropertiesLenguaje = idPropertiesLenguaje;
        this.itemByLenguaje = itemByLenguaje;
        this.properties = properties;
        this.value = value;
    }
   
    //get y set
    /**
     * Obtiene id properties lenguaje.
     *
     * @return id properties lenguaje
     */
     public String getIdPropertiesLenguaje() {
        return this.idPropertiesLenguaje;
    }
    /**
     * Establece el id properties lenguaje.
     *
     * @param idPropertiesLenguaje el new id properties lenguaje
     */
    public void setIdPropertiesLenguaje(String idPropertiesLenguaje) {
        this.idPropertiesLenguaje = idPropertiesLenguaje;
    }
    /**
     * Obtiene item by lenguaje.
     *
     * @return item by lenguaje
     */
     public Item getItemByLenguaje() {
        return this.itemByLenguaje;
    }
    /**
     * Establece el item by lenguaje.
     *
     * @param itemByLenguaje el new item by lenguaje
     */
    public void setItemByLenguaje(Item itemByLenguaje) {
        this.itemByLenguaje = itemByLenguaje;
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
     * Obtiene value.
     *
     * @return value
     */
     public String getValue() {
        return this.value;
    }
    /**
     * Establece el value.
     *
     * @param value el new value
     */
    public void setValue(String value) {
        this.value = value;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idPropertiesLenguaje == null) ? 0 : idPropertiesLenguaje.hashCode());
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
        PropertiesLenguaje other = (PropertiesLenguaje) obj;
        if (idPropertiesLenguaje == null) {
            if (other.idPropertiesLenguaje != null) {
                return false;
            }
        } else if (!idPropertiesLenguaje.equals(other.idPropertiesLenguaje)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PropertiesLenguaje [idPropertiesLenguaje=" + idPropertiesLenguaje + "]";
    }
   
}