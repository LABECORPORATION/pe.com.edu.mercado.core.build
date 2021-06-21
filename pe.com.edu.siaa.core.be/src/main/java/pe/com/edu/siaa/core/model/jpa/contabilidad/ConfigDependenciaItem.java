package pe.com.edu.siaa.core.model.jpa.contabilidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pe.com.edu.siaa.core.model.jpa.common.Item;
import pe.com.edu.siaa.core.model.jpa.seguridad.Entidad;
import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class ConfigDependenciaItem.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Mon Feb 05 11:43:14 COT 2018
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "ConfigDependenciaItem", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CONTABILIDAD)
public class ConfigDependenciaItem implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id config dependencia item. */
    @Id
    @Column(name = "idConfigDependenciaItem" , length = 32)
    private String idConfigDependenciaItem;
   
    /** El entidad. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEntidad", referencedColumnName = "idEntidad")
    private Entidad entidad;
   
    /** El item. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idItem", referencedColumnName = "idItem")
    private Item item;
   
    /** El item hijo. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idItemHijo", referencedColumnName = "idItem")
    private Item itemHijo;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
   
    /**
     * Instancia un nuevo config dependencia item.
     */
    public ConfigDependenciaItem() {
    }
   
   
    /**
     * Instancia un nuevo config dependencia item.
     *
     * @param idConfigDependenciaItem el id config dependencia item
     * @param entidad el entidad
     * @param item el item
     * @param itemHijo el item hijo
     * @param estado el estado
     */
    public ConfigDependenciaItem(String idConfigDependenciaItem, Entidad entidad,Item item,Item itemHijo,String estado ) {
        super();
        this.idConfigDependenciaItem = idConfigDependenciaItem;
        this.entidad = entidad;
        this.item = item;
        this.itemHijo = itemHijo;
        this.estado = estado;
    }
   
    //get y set
    /**
     * Obtiene id config dependencia item.
     *
     * @return id config dependencia item
     */
     public String getIdConfigDependenciaItem() {
        return this.idConfigDependenciaItem;
    }
    /**
     * Establece el id config dependencia item.
     *
     * @param idConfigDependenciaItem el new id config dependencia item
     */
    public void setIdConfigDependenciaItem(String idConfigDependenciaItem) {
        this.idConfigDependenciaItem = idConfigDependenciaItem;
    }
    /**
     * Obtiene entidad.
     *
     * @return entidad
     */
     public Entidad getEntidad() {
        return this.entidad;
    }
    /**
     * Establece el entidad.
     *
     * @param entidad el new entidad
     */
    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }
    /**
     * Obtiene item.
     *
     * @return item
     */
     public Item getItem() {
        return this.item;
    }
    /**
     * Establece el item.
     *
     * @param item el new item
     */
    public void setItem(Item item) {
        this.item = item;
    }
    /**
     * Obtiene item hijo.
     *
     * @return item hijo
     */
     public Item getItemHijo() {
        return this.itemHijo;
    }
    /**
     * Establece el item hijo.
     *
     * @param itemHijo el new item hijo
     */
    public void setItemHijo(Item itemHijo) {
        this.itemHijo = itemHijo;
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
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idConfigDependenciaItem == null) ? 0 : idConfigDependenciaItem.hashCode());
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
        ConfigDependenciaItem other = (ConfigDependenciaItem) obj;
        if (idConfigDependenciaItem == null) {
            if (other.idConfigDependenciaItem != null) {
                return false;
            }
        } else if (!idConfigDependenciaItem.equals(other.idConfigDependenciaItem)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ConfigDependenciaItem [idConfigDependenciaItem=" + idConfigDependenciaItem + "]";
    }
   
}