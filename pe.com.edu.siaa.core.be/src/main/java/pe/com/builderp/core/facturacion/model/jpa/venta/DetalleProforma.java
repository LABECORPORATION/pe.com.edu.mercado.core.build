package pe.com.builderp.core.facturacion.model.jpa.venta;

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
 * La Class DetalleProforma.
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
@Table(name = "DetalleProforma", schema = ConfiguracionEntityManagerUtil.ESQUEMA_FACTURACION)
public class DetalleProforma implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id detalle proforma. */
    @Id
    @Column(name = "idDetalleProforma" , length = 32)
    private String idDetalleProforma;
   
    /** El proforma. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProforma", referencedColumnName = "idProforma")
    private Proforma proforma;
   
    /** El producto. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProducto", referencedColumnName = "idProducto")
    private Producto producto;
   
    /** El descripcion producto. */
    @Column(name = "descripcionProducto" , length = 150)
    private String descripcionProducto;
   
    /** El precio. */
    @Column(name = "precio" , precision = 18 , scale = 2)
    private BigDecimal precio;
   
    /** El cantidad. */
    @Column(name = "cantidad" , precision = 18 , scale = 2)
    private BigDecimal cantidad;
   
    /** El descuento. */
    @Column(name = "descuento" , precision = 18 , scale = 2)
    private BigDecimal descuento;
   
    /** El sub monto total. */
    @Column(name = "subMontoTotal" , precision = 18 , scale = 2)
    private BigDecimal subMontoTotal;
   
    /** El monto total. */
    @Column(name = "montoTotal" , precision = 18 , scale = 2)
    private BigDecimal montoTotal;
   
    /** El tipodescuento. */
    @Column(name = "tipoDescuento" , length = 1)
    private String tipoDescuento;
    
    /** El descuento. */
    @Column(name = "descuentoTotal" , precision = 18 , scale = 2)
    private BigDecimal descuentoTotal;
    
    
    /** El flagdescuento. */
    @Column(name = "flagEsDescuento")
    private Boolean flagEsDescuento;
    
    /** El producto. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDetalleProducto", referencedColumnName = "idDetalleProducto")
    private DetalleProducto detalleProducto;
    
    /**
     * Instancia un nuevo detalle proforma.
     */
    public DetalleProforma() {
    }
   
   
    /**
     * Instancia un nuevo detalle proforma.
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
    public DetalleProforma(Boolean flagEsDescuento, BigDecimal descuentoTotal,String tipoDescuento,String idDetalleProforma, Proforma proforma,Producto producto,String descripcionProducto, BigDecimal precio, BigDecimal cantidad, BigDecimal descuento, BigDecimal subMontoTotal, BigDecimal montoTotal,
    		DetalleProducto detalleProducto) {
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
     public Proforma getProforma() {
        return this.proforma;
    }
    /**
     * Establece el proforma.
     *
     * @param proforma el new proforma
     */
    public void setProforma(Proforma proforma) {
        this.proforma = proforma;
    }
    /**
     * Obtiene producto.
     *
     * @return producto
     */
     public Producto getProducto() {
        return this.producto;
    }
    /**
     * Establece el producto.
     *
     * @param producto el new producto
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
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
    
    
    public DetalleProducto getDetalleProducto() {
		return detalleProducto;
	}


	public void setDetalleProducto(DetalleProducto detalleProducto) {
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
        DetalleProforma other = (DetalleProforma) obj;
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
        return "DetalleProforma [idDetalleProforma=" + idDetalleProforma + "]";
    }
   
}