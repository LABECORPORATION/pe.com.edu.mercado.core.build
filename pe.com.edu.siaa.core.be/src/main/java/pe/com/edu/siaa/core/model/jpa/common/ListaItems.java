package pe.com.edu.siaa.core.model.jpa.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class ListaItems.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 09:56:46 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "ListaItems", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_COMMON)
public class ListaItems implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id lista items. */
    @Id
    @Column(name = "idListaItems" , precision = 18 , scale = 0)
    private Long idListaItems;
   
    /** El descripcion. */
    @Column(name = "descripcion" , length = 50)
    private String descripcion;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
   
    /** El lista items item list. */
    @OneToMany(mappedBy = "listaItems", fetch = FetchType.LAZY)
    private List<Item> listaItemsItemList = new ArrayList<Item>();
    
    /**
     * Instancia un nuevo lista items.
     */
    public ListaItems() {
    }
   
   
    /**
     * Instancia un nuevo lista items.
     *
     * @param idListaItems el id lista items
     * @param descripcion el descripcion
     * @param estado el estado
     */
    public ListaItems(Long idListaItems, String descripcion, String estado ) {
        super();
        this.idListaItems = idListaItems;
        this.descripcion = descripcion;
        this.estado = estado;
    }
   
    //get y set
    /**
     * Obtiene id lista items.
     *
     * @return id lista items
     */
     public Long getIdListaItems() {
        return this.idListaItems;
    }
    /**
     * Establece el id lista items.
     *
     * @param idListaItems el new id lista items
     */
    public void setIdListaItems(Long idListaItems) {
        this.idListaItems = idListaItems;
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
     * Establece el lista items item list.
     *
     * @param listaItemsItemList el new lista items item list
     */
    public List<Item> getListaItemsItemList(){
        return this.listaItemsItemList;
    }
    /**
     * Establece el lista items list.
     *
     * @param listaItemsList el new lista items list
     */
    public void setListaItemsItemList(List<Item> listaItemsItemList) {
        this.listaItemsItemList = listaItemsItemList;
    }   
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idListaItems == null) ? 0 : idListaItems.hashCode());
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
        ListaItems other = (ListaItems) obj;
        if (idListaItems == null) {
            if (other.idListaItems != null) {
                return false;
            }
        } else if (!idListaItems.equals(other.idListaItems)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ListaItems [idListaItems=" + idListaItems + "]";
    }
   
}