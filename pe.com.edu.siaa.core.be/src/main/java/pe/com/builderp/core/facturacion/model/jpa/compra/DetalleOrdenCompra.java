package pe.com.builderp.core.facturacion.model.jpa.compra;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pe.com.builderp.core.facturacion.model.jpa.venta.Producto;
import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class DetalleOrdenCompra.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Wed Dec 20 00:30:17 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "DetalleOrdenCompra", schema = ConfiguracionEntityManagerUtil.ESQUEMA_FACTURACION)
public class DetalleOrdenCompra implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id detalle orden compra. */
    @Id
    @Column(name = "idDetalleOrdenCompra" , length = 32)
    private String idDetalleOrdenCompra;
   
    /** El orden compra. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idOrdenCompra", referencedColumnName = "idOrdenCompra")
    private OrdenCompra ordenCompra;
   
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
   
    /**
     * Instancia un nuevo detalle orden compra.
     */
    public DetalleOrdenCompra() {
    }
   
   
    /**
     * Instancia un nuevo detalle orden compra.
     *
     * @param idDetalleOrdenCompra el id detalle orden compra
     * @param ordenCompra el orden compra
     * @param producto el producto
     * @param descripcionProducto el descripcion producto
     * @param precio el precio
     * @param cantidad el cantidad
     * @param descuento el descuento
     * @param subMontoTotal el sub monto total
     * @param montoTotal el monto total
     */
    public DetalleOrdenCompra(String idDetalleOrdenCompra, OrdenCompra ordenCompra,Producto producto,String descripcionProducto, BigDecimal precio, BigDecimal cantidad, BigDecimal descuento, BigDecimal subMontoTotal, BigDecimal montoTotal ) {
        super();
        this.idDetalleOrdenCompra = idDetalleOrdenCompra;
        this.ordenCompra = ordenCompra;
        this.producto = producto;
        this.descripcionProducto = descripcionProducto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.descuento = descuento;
        this.subMontoTotal = subMontoTotal;
        this.montoTotal = montoTotal;
    }
   
    //get y set
    /**
     * Obtiene id detalle orden compra.
     *
     * @return id detalle orden compra
     */
     public String getIdDetalleOrdenCompra() {
        return this.idDetalleOrdenCompra;
    }
    /**
     * Establece el id detalle orden compra.
     *
     * @param idDetalleOrdenCompra el new id detalle orden compra
     */
    public void setIdDetalleOrdenCompra(String idDetalleOrdenCompra) {
        this.idDetalleOrdenCompra = idDetalleOrdenCompra;
    }
    /**
     * Obtiene orden compra.
     *
     * @return orden compra
     */
     public OrdenCompra getOrdenCompra() {
        return this.ordenCompra;
    }
    /**
     * Establece el orden compra.
     *
     * @param ordenCompra el new orden compra
     */
    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
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
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idDetalleOrdenCompra == null) ? 0 : idDetalleOrdenCompra.hashCode());
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
        DetalleOrdenCompra other = (DetalleOrdenCompra) obj;
        if (idDetalleOrdenCompra == null) {
            if (other.idDetalleOrdenCompra != null) {
                return false;
            }
        } else if (!idDetalleOrdenCompra.equals(other.idDetalleOrdenCompra)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DetalleOrdenCompra [idDetalleOrdenCompra=" + idDetalleOrdenCompra + "]";
    }
   
}