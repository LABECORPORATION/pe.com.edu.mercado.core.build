package pe.com.edu.siaa.core.model.dto.seguridad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pe.com.edu.siaa.core.model.dto.BasePaginator;

 

/**
 * La Class PropertiesDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Thu Dec 14 00:27:44 COT 2017
 * @since SIAA-CORE 2.1
 */
public class PropertiesDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id properties. */
    private Long idProperties;
   
    /** El name. */
    private String name;
   
    /** El descripcion. */
    private String descripcion;
   
    /** El value default. */
    private String valueDefault;
   
    /** El value. */
    private String value;
   
    /** El estado. */
    private String estado;
   
    /** El properties configuracion menu list. */
    private List<ConfiguracionMenuDTO> propertiesConfiguracionMenuList = new ArrayList<ConfiguracionMenuDTO>();
   
    /** El properties properties lenguaje list. */
    private List<PropertiesLenguajeDTO> propertiesPropertiesLenguajeList = new ArrayList<PropertiesLenguajeDTO>();
   
    /**
     * Instancia un nuevo propertiesDTO.
     */
    public PropertiesDTO() {
    }
   
   
    /**
     * Instancia un nuevo propertiesDTO.
     *
     * @param idProperties el id properties
     * @param name el name
     * @param descripcion el descripcion
     * @param valueDefault el value default
     * @param value el value
     * @param estado el estado
     */
    public PropertiesDTO(Long idProperties, String name, String descripcion, String valueDefault, String value, String estado ) {
        super();
        this.idProperties = idProperties;
        this.name = name;
        this.descripcion = descripcion;
        this.valueDefault = valueDefault;
        this.value = value;
        this.estado = estado;
    }
   
    //get y set
    /**
     * Obtiene id properties.
     *
     * @return id properties
     */
     public Long getIdProperties() {
        return this.idProperties;
    }
    /**
     * Establece el id properties.
     *
     * @param idProperties el new id properties
     */
    public void setIdProperties(Long idProperties) {
        this.idProperties = idProperties;
    }
    /**
     * Obtiene name.
     *
     * @return name
     */
     public String getName() {
        return this.name;
    }
    /**
     * Establece el name.
     *
     * @param name el new name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Obtiene descripcion.
     *
     * @return descripcion
     */
     public String getDescripcion() {
        return this.descripcion;
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
     * Obtiene value default.
     *
     * @return value default
     */
     public String getValueDefault() {
        return this.valueDefault;
    }
    /**
     * Establece el value default.
     *
     * @param valueDefault el new value default
     */
    public void setValueDefault(String valueDefault) {
        this.valueDefault = valueDefault;
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
     * Establece el properties configuracion menu list.
     *
     * @param propertiesConfiguracionMenuList el new properties configuracion menu list
     */
    public List<ConfiguracionMenuDTO> getPropertiesConfiguracionMenuList(){
        return this.propertiesConfiguracionMenuList;
    }
    /**
     * Establece el properties list.
     *
     * @param propertiesList el new properties list
     */
    public void setPropertiesConfiguracionMenuList(List<ConfiguracionMenuDTO> propertiesConfiguracionMenuList) {
        this.propertiesConfiguracionMenuList = propertiesConfiguracionMenuList;
    }   
    /**
     * Establece el properties properties lenguaje list.
     *
     * @param propertiesPropertiesLenguajeList el new properties properties lenguaje list
     */
    public List<PropertiesLenguajeDTO> getPropertiesPropertiesLenguajeList(){
        return this.propertiesPropertiesLenguajeList;
    }
    /**
     * Establece el properties list.
     *
     * @param propertiesList el new properties list
     */
    public void setPropertiesPropertiesLenguajeList(List<PropertiesLenguajeDTO> propertiesPropertiesLenguajeList) {
        this.propertiesPropertiesLenguajeList = propertiesPropertiesLenguajeList;
    }   
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idProperties == null) ? 0 : idProperties.hashCode());
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
        PropertiesDTO other = (PropertiesDTO) obj;
        if (idProperties == null) {
            if (other.idProperties != null) {
                return false;
            }
        } else if (!idProperties.equals(other.idProperties)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PropertiesDTO [idProperties=" + idProperties + "]";
    }
   
}