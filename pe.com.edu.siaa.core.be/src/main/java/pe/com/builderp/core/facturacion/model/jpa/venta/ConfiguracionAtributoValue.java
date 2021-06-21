package pe.com.builderp.core.facturacion.model.jpa.venta;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class ConfiguracionAtributoValue.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Fri Dec 22 11:10:34 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "ConfiguracionAtributoValue", schema = ConfiguracionEntityManagerUtil.ESQUEMA_FACTURACION)
public class ConfiguracionAtributoValue implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id configuracion atributo value. */
    @Id
    @Column(name = "idConfiguracionAtributoValue" , length = 32)
    private String idConfiguracionAtributoValue;
   
    /** El configuracion atributo. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idConfiguracionAtributo", referencedColumnName = "idConfiguracionAtributo")
    private ConfiguracionAtributo configuracionAtributo;
   
    /** El id tupla entidad. */
    @Column(name = "idTuplaEntidad" , length = 32)
    private String idTuplaEntidad;
   
    /** El value. */
    @Column(name = "value" , length = 200)
    private String value;
   
    /**
     * Instancia un nuevo configuracion atributo value.
     */
    public ConfiguracionAtributoValue() {
    }
   
   
    /**
     * Instancia un nuevo configuracion atributo value.
     *
     * @param idConfiguracionAtributoValue el id configuracion atributo value
     * @param configuracionAtributo el configuracion atributo
     * @param idTuplaEntidad el id tupla entidad
     * @param value el value
     */
    public ConfiguracionAtributoValue(String idConfiguracionAtributoValue, ConfiguracionAtributo configuracionAtributo,String idTuplaEntidad, String value ) {
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
     public ConfiguracionAtributo getConfiguracionAtributo() {
        return this.configuracionAtributo;
    }
    /**
     * Establece el configuracion atributo.
     *
     * @param configuracionAtributo el new configuracion atributo
     */
    public void setConfiguracionAtributo(ConfiguracionAtributo configuracionAtributo) {
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
        ConfiguracionAtributoValue other = (ConfiguracionAtributoValue) obj;
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
        return "ConfiguracionAtributoValue [idConfiguracionAtributoValue=" + idConfiguracionAtributoValue + "]";
    }
   
}