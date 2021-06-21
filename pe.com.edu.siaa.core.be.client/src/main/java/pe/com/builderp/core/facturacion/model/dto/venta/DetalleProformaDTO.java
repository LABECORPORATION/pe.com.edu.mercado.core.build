package pe.com.builderp.core.facturacion.model.dto.venta;

import java.io.Serializable;
import java.math.BigDecimal;



import pe.com.edu.siaa.core.model.dto.BasePaginator;

/**
 * La Class DetalleProformaDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Fri Dec 22 11:07:01 COT 2017
 * @since SIAA-CORE 2.1
 */
public class DetalleProformaDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id detalle proforma. */
    private String idDetalleProforma;
   
    /** El proforma. */
    private ProformaDTO proforma;
   
    /** El producto. */
    private ProductoDTO producto;
   
    /** El descripcion producto. */
    private String descripcionProducto;
   
    /** El precio. */
    private BigDecimal precio;
   
    /** El cantidad. */
    private BigDecimal cantidad;
   
    /** El descuento. */
    private BigDecimal descuento;
   
    /** El sub monto total. */
    private BigDecimal subMontoTotal;
   
    /** El monto total. */
    private BigDecimal montoTotal;
   
    /** El tipodescuento. */
    private String tipoDescuento;
    
    /** El descuento. */
    private BigDecimal descuentoTotal;
    
    public Integer cantidadProductos;

    
    /** El flagdescuento. */
    private Boolean flagEsDescuento;
    
    /** El producto. */
    private DetalleProductoDTO detalleProducto;
    
    /**
    /**
     * Instancia un nuevo detalle proformaDTO.
     */
    public DetalleProformaDTO() {
    }
   
   
    /**
     * Instancia un nuevo detalle proformaDTO.
     *
     * @param idDetalleProforma el id detalle proforma
     * @param proforma el proforma
     * @param producto el producto
     * @param descripcionProducto el descripcion producto
     * @param precio el precio
     * @param cantidad el cantidad
     * @param descuento el descuento
     * @param subMontoTotal el sub monto total
     * @param montoTotal el monto total
     */
    public DetalleProformaDTO(Boolean flagEsDescuento, BigDecimal descuentoTotal,String tipoDescuento,String idDetalleProforma, ProformaDTO proforma,ProductoDTO producto,String descripcionProducto, BigDecimal precio, BigDecimal cantidad, BigDecimal descuento, BigDecimal subMontoTotal, BigDecimal montoTotal,
    		DetalleProductoDTO detalleProducto) {
        super();
        this.idDetalleProforma = idDetalleProforma;
        this.proforma = proforma;
        this.producto = producto;
        this.descripcionProducto = descripcionProducto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.descuento = descuento;
        this.subMontoTotal = subMontoTotal;
        this.montoTotal = montoTotal;
        this.tipoDescuento=tipoDescuento;
        this.descuentoTotal=descuentoTotal;
        this.flagEsDescuento=flagEsDescuento;
        this.detalleProducto=detalleProducto;
    }
   
    //get y set
    /**
     * Obtiene id detalle proforma.
     *
     * @return id detalle proforma
     */
     public String getIdDetalleProforma() {
        return this.idDetalleProforma;
    }
    /**
     * Establece el id detalle proforma.
     *
     * @param idDetalleProforma el new id detalle proforma
     */
    public void setIdDetalleProforma(String idDetalleProforma) {
        this.idDetalleProforma = idDetalleProforma;
    }
    /**
     * Obtiene proforma.
     *
     * @return proforma
     */
     public ProformaDTO getProforma() {
        return this.proforma;
    }
    /**
     * Establece el proforma.
     *
     * @param proforma el new proforma
     */
    public void setProforma(ProformaDTO proforma) {
        this.proforma = proforma;
    }
    /**
     * Obtiene producto.
     *
     * @return producto
     */
     public ProductoDTO getProducto() {
        return this.producto;
    }
    /**
     * Establece el producto.
     *
     * @param producto el new producto
     */
    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }
    
    
    
    public Integer getCantidadProductos() {
		return cantidadProductos;
	}


	public void setCantidadProductos(Integer cantidadProductos) {
		this.cantidadProductos = cantidadProductos;
	}


	public String getTipoDescuento() {
		return tipoDescuento;
	}


	public void setTipoDescuento(String tipoDescuento) {
		this.tipoDescuento = tipoDescuento;
	}


	public BigDecimal getDescuentoTotal() {
		return descuentoTotal;
	}


	public void setDescuentoTotal(BigDecimal descuentoTotal) {
		this.descuentoTotal = descuentoTotal;
	}


	public Boolean getFlagEsDescuento() {
		return flagEsDescuento;
	}


	public void setFlagEsDescuento(Boolean flagEsDescuento) {
		this.flagEsDescuento = flagEsDescuento;
	}


	/**
     * Obtiene descripcion producto.
     *
     * @return descripcion producto
     */
     public String getDescripcionProducto() {
        return this.descripcionProducto;
    }
    /**
     * Establece el descripcion producto.
     *
     * @param descripcionProducto el new descripcion producto
     */
    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }
    /**
     * Obtiene precio.
     *
     * @return precio
     */
     public BigDecimal getPrecio() {
        return this.precio;
    }
    /**
     * Establece el precio.
     *
     * @param precio el new precio
     */
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    /**
     * Obtiene cantidad.
     *
     * @return cantidad
     */
     public BigDecimal getCantidad() {
        return this.cantidad;
    }
    /**
     * Establece el cantidad.
     *
     * @param cantidad el new cantidad
     */
    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }
    /**
     * Obtiene descuento.
     *
     * @return descuento
     */
     public BigDecimal getDescuento() {
        return this.descuento;
    }
    /**
     * Establece el descuento.
     *
     * @param descuento el new descuento
     */
    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }
    /**
     * Obtiene sub monto total.
     *
     * @return sub monto total
     */
     public BigDecimal getSubMontoTotal() {
        return this.subMontoTotal;
    }
    /**
     * Establece el sub monto total.
     *
     * @param subMontoTotal el new sub monto total
     */
    public void setSubMontoTotal(BigDecimal subMontoTotal) {
        this.subMontoTotal = subMontoTotal;
    }
    /**
     * Obtiene monto total.
     *
     * @return monto total
     */
     public BigDecimal getMontoTotal() {
        return this.montoTotal;
    }
    /**
     * Establece el monto total.
     *
     * @param montoTotal el new monto total
     */
    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }    
    
    public DetalleProductoDTO getDetalleProducto() {
		return detalleProducto;
	}


	public void setDetalleProducto(DetalleProductoDTO detalleProducto) {
		this.detalleProducto = detalleProducto;
	}


	/* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idDetalleProforma == null) ? 0 : idDetalleProforma.hashCode());
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
        DetalleProformaDTO other = (DetalleProformaDTO) obj;
        if (idDetalleProforma == null) {
            if (other.idDetalleProforma != null) {
                return false;
            }
        } else if (!idDetalleProforma.equals(other.idDetalleProforma)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DetalleProformaDTO [idDetalleProforma=" + idDetalleProforma + "]";
    }
   
}