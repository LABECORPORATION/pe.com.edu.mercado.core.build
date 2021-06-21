package pe.com.edu.siaa.core.model.dto.seguridad;

import java.io.Serializable;

import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO;
 

/**
 * La Class PropertiesLenguajeDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Thu Dec 14 00:27:44 COT 2017
 * @since SIAA-CORE 2.1
 */
public class PropertiesLenguajeDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id properties lenguaje. */
    private String idPropertiesLenguaje;
   
    /** El item by lenguaje. */
    private ItemDTO itemByLenguaje;
   
    /** El properties. */
    private PropertiesDTO properties;
   
    /** El value. */
    private String value;
   
    /**
     * Instancia un nuevo properties lenguajeDTO.
     */
    public PropertiesLenguajeDTO() {
    }
   
   
    /**
     * Instancia un nuevo properties lenguajeDTO.
     *
     * @param idPropertiesLenguaje el id properties lenguaje
     * @param itemByLenguaje el item by lenguaje
     * @param properties el properties
     * @param value el value
     */
    public PropertiesLenguajeDTO(String idPropertiesLenguaje, ItemDTO itemByLenguaje,PropertiesDTO properties,String value ) {
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
     public ItemDTO getItemByLenguaje() {
        return this.itemByLenguaje;
    }
    /**
     * Establece el item by lenguaje.
     *
     * @param itemByLenguaje el new item by lenguaje
     */
    public void setItemByLenguaje(ItemDTO itemByLenguaje) {
        this.itemByLenguaje = itemByLenguaje;
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
        PropertiesLenguajeDTO other = (PropertiesLenguajeDTO) obj;
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
        return "PropertiesLenguajeDTO [idPropertiesLenguaje=" + idPropertiesLenguaje + "]";
    }
   
}