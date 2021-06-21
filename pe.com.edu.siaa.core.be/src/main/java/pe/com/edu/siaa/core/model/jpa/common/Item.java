package pe.com.edu.siaa.core.model.jpa.common;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class Item.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 09:56:47 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "Item", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_COMMON)
public class Item implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id item. */
    @Id
    @Column(name = "idItem" , precision = 18 , scale = 0)
    private Long idItem;
   
    /** El lista items. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idListaitems", referencedColumnName = "idListaItems")
    private ListaItems listaItems;
   
    /** El descripcion. */
    @Column(name = "descripcion" , length = 250)
    private String descripcion;
   
    /** El nombre. */
    @Column(name = "nombre" , length = 250)
    private String nombre;
   
    /** El codigo. */
    @Column(name = "codigo" , precision = 18 , scale = 0)
    private Long codigo;
   
    /** El codigo externo. */
    @Column(name = "codigoExterno" , length = 20)
    private String codigoExterno;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
    
    /** El codigo. */
    @Column(name = "factor" , precision = 18 , scale = 2)
    private BigDecimal factor;
   
    
    /**
     * Instancia un nuevo item.
     */
    public Item() {
    }
    
    public Item(Long idItem) {
    	super();
        this.idItem = idItem;
    }
   
   
    /**
     * Instancia un nuevo item.
     *
     * @param idItem el id item
     * @param listaItems el lista items
     * @param descripcion el descripcion
     * @param nombre el nombre
     * @param codigo el codigo
     * @param codigoExterno el codigo externo
     * @param estado el estado
     */
    public Item(BigDecimal factor,Long idItem, ListaItems listaItems,String descripcion, String nombre, Long codigo, String codigoExterno, String estado ) {
        super();
        this.idItem = idItem;
        this.listaItems = listaItems;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.codigo = codigo;
        this.codigoExterno = codigoExterno;
        this.estado = estado;
        this.factor=factor;
    }
   
    //get y set
    /**
     * Obtiene id item.
     *
     * @return id item
     */
    
    
    
     public Long getIdItem() {
        return this.idItem;
    }
    public BigDecimal getFactor() {
		return factor;
	}

	public void setFactor(BigDecimal factor) {
		this.factor = factor;
	}

	/**
     * Establece el id item.
     *
     * @param idItem el new id item
     */
    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }
    /**
     * Obtiene lista items.
     *
     * @return lista items
     */
     public ListaItems getListaItems() {
        return this.listaItems;
    }
    /**
     * Establece el lista items.
     *
     * @param listaItems el new lista items
     */
    public void setListaItems(ListaItems listaItems) {
        this.listaItems = listaItems;
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
     * Obtiene nombre.
     *
     * @return nombre
     */
     public String getNombre() {
        return this.nombre;
    }
    /**
     * Establece el nombre.
     *
     * @param nombre el new nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Obtiene codigo.
     *
     * @return codigo
     */
     public Long getCodigo() {
        return this.codigo;
    }
    /**
     * Establece el codigo.
     *
     * @param codigo el new codigo
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    /**
     * Obtiene codigo externo.
     *
     * @return codigo externo
     */
     public String getCodigoExterno() {
        return this.codigoExterno;
    }
    /**
     * Establece el codigo externo.
     *
     * @param codigoExterno el new codigo externo
     */
    public void setCodigoExterno(String codigoExterno) {
        this.codigoExterno = codigoExterno;
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
                + ((idItem == null) ? 0 : idItem.hashCode());
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
        Item other = (Item) obj;
        if (idItem == null) {
            if (other.idItem != null) {
                return false;
            }
        } else if (!idItem.equals(other.idItem)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Item [idItem=" + idItem + "]";
    }
   
}