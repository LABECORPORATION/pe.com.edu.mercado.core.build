package pe.com.builderp.core.facturacion.model.dto.venta;

import java.io.Serializable;

import pe.com.edu.siaa.core.model.dto.BasePaginator;

 

/**
 * La Class ConfiguracionAtributoValueDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Fri Dec 22 11:07:01 COT 2017
 * @since SIAA-CORE 2.1
 */
public class ConfiguracionAtributoValueDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id configuracion atributo value. */
    private String idConfiguracionAtributoValue;
   
    /** El configuracion atributo. */
    private ConfiguracionAtributoDTO configuracionAtributo;
   
    /** El id tupla entidad. */
    private String idTuplaEntidad;
   
    /** El value. */
    private String value;
   
    /**
     * Instancia un nuevo configuracion atributo valueDTO.
     */
    public ConfiguracionAtributoValueDTO() {
    }
   
   
    /**
     * Instancia un nuevo configuracion atributo valueDTO.
     *
     * @param idConfiguracionAtributoValue el id configuracion atributo value
     * @param configuracionAtributo el configuracion atributo
     * @param idTuplaEntidad el id tupla entidad
     * @param value el value
     */
    public ConfiguracionAtributoValueDTO(String idConfiguracionAtributoValue, ConfiguracionAtributoDTO configuracionAtributo,String idTuplaEntidad, String value ) {
        super();
        this.idConfiguracionAtributoValue = idConfiguracionAtributoValue;
        this.configuracionAtributo = configuracionAtributo;
        this.idTuplaEntidad = idTuplaEntidad;
        this.value = value;
    }
   
    //get y set
    /**
     * Obtiene id configuracion atributo value.
     *
     * @return id configuracion atributo value
     */
     public String getIdConfiguracionAtributoValue() {
        return this.idConfiguracionAtributoValue;
    }
    /**
     * Establece el id configuracion atributo value.
     *
     * @param idConfiguracionAtributoValue el new id configuracion atributo value
     */
    public void setIdConfiguracionAtributoValue(String idConfiguracionAtributoValue) {
        this.idConfiguracionAtributoValue = idConfiguracionAtributoValue;
    }
    /**
     * Obtiene configuracion atributo.
     *
     * @return configuracion atributo
     */
     public ConfiguracionAtributoDTO getConfiguracionAtributo() {
        return this.configuracionAtributo;
    }
    /**
     * Establece el configuracion atributo.
     *
     * @param configuracionAtributo el new configuracion atributo
     */
    public void setConfiguracionAtributo(ConfiguracionAtributoDTO configuracionAtributo) {
        this.configuracionAtributo = configuracionAtributo;
    }
    /**
     * Obtiene id tupla entidad.
     *
     * @return id tupla entidad
     */
     public String getIdTuplaEntidad() {
        return this.idTuplaEntidad;
    }
    /**
     * Establece el id tupla entidad.
     *
     * @param idTuplaEntidad el new id tupla entidad
     */
    public void setIdTuplaEntidad(String idTuplaEntidad) {
        this.idTuplaEntidad = idTuplaEntidad;
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
                + ((idConfiguracionAtributoValue == null) ? 0 : idConfiguracionAtributoValue.hashCode());
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
        ConfiguracionAtributoValueDTO other = (ConfiguracionAtributoValueDTO) obj;
        if (idConfiguracionAtributoValue == null) {
            if (other.idConfiguracionAtributoValue != null) {
                return false;
            }
        } else if (!idConfiguracionAtributoValue.equals(other.idConfiguracionAtributoValue)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ConfiguracionAtributoValueDTO [idConfiguracionAtributoValue=" + idConfiguracionAtributoValue + "]";
    }
   
}