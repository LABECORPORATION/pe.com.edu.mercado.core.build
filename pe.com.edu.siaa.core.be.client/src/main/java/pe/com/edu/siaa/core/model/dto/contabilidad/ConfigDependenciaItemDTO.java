package pe.com.edu.siaa.core.model.dto.contabilidad;

import java.io.Serializable;

import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.EntidadDTO;

/**
 * La Class ConfigDependenciaItemDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Mon Feb 05 11:43:14 COT 2018
 * @since SIAA-CORE 2.1
 */
public class ConfigDependenciaItemDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id config dependencia item. */
    private String idConfigDependenciaItem;
   
    /** El entidad. */
    private EntidadDTO entidad;
   
    /** El item. */
    private ItemDTO item;
   
    /** El item hijo. */
    private ItemDTO itemHijo;
   
    /** El estado. */
    private String estado;
   
    /**
     * Instancia un nuevo config dependencia itemDTO.
     */
    public ConfigDependenciaItemDTO() {
    }
   
   
    /**
     * Instancia un nuevo config dependencia itemDTO.
     *
     * @param idConfigDependenciaItem el id config dependencia item
     * @param entidad el entidad
     * @param item el item
     * @param itemHijo el item hijo
     * @param estado el estado
     */
    public ConfigDependenciaItemDTO(String idConfigDependenciaItem, EntidadDTO entidad,ItemDTO item,ItemDTO itemHijo,String estado ) {
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
     public EntidadDTO getEntidad() {
        return this.entidad;
    }
    /**
     * Establece el entidad.
     *
     * @param entidad el new entidad
     */
    public void setEntidad(EntidadDTO entidad) {
        this.entidad = entidad;
    }
    /**
     * Obtiene item.
     *
     * @return item
     */
     public ItemDTO getItem() {
        return this.item;
    }
    /**
     * Establece el item.
     *
     * @param item el new item
     */
    public void setItem(ItemDTO item) {
        this.item = item;
    }
    /**
     * Obtiene item hijo.
     *
     * @return item hijo
     */
     public ItemDTO getItemHijo() {
        return this.itemHijo;
    }
    /**
     * Establece el item hijo.
     *
     * @param itemHijo el new item hijo
     */
    public void setItemHijo(ItemDTO itemHijo) {
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
        ConfigDependenciaItemDTO other = (ConfigDependenciaItemDTO) obj;
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
        return "ConfigDependenciaItemDTO [idConfigDependenciaItem=" + idConfigDependenciaItem + "]";
    }
   
}