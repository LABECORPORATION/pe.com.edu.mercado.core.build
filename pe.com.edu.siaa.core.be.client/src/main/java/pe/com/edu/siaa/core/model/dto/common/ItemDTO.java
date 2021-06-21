package pe.com.edu.siaa.core.model.dto.common;

import java.io.Serializable;
import java.math.BigDecimal;

import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.contabilidad.PlanContableDTO;

/**
 * La Class ItemDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Tue Apr 18 09:56:47 COT 2017
 * @since SIAA-CORE 2.1
 */
public class ItemDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id item. */
    private Long idItem;
   
    /** El lista items. */
    private ListaItemsDTO listaItems;
   
    /** El descripcion. */
    private String descripcion;
   
    /** El nombre. */
    private String nombre;
   
    /** El codigo. */
    private Long codigo;
   
    /** El codigo externo. */
    private String codigoExterno;
   
    /** El estado. */
    private String estado;
    
    private PlanContableDTO planContable;
   
    
    private BigDecimal factor;
    
    /**
     * Instancia un nuevo itemDTO.
     */
    public ItemDTO() {
    }
   
   
    /**
     * Instancia un nuevo itemDTO.
     *
     * @param idItem el id item
     * @param listaItems el lista items
     * @param descripcion el descripcion
     * @param nombre el nombre
     * @param codigo el codigo
     * @param codigoExterno el codigo externo
     * @param estado el estado
     */
    public ItemDTO(BigDecimal factor,Long idItem, ListaItemsDTO listaItems,String descripcion, String nombre, Long codigo, String codigoExterno, String estado ) {
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
     public ListaItemsDTO getListaItems() {
        return this.listaItems;
    }
    /**
     * Establece el lista items.
     *
     * @param listaItems el new lista items
     */
    public void setListaItems(ListaItemsDTO listaItems) {
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
    
    public PlanContableDTO getPlanContable() {
		return planContable;
	}


	public void setPlanContable(PlanContableDTO planContable) {
		this.planContable = planContable;
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
        ItemDTO other = (ItemDTO) obj;
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
        return "ItemDTO [idItem=" + idItem + "]";
    }
   
}