package pe.com.builderp.core.facturacion.model.dto.compra;

import java.io.Serializable;
import java.math.BigDecimal;

import pe.com.builderp.core.facturacion.model.dto.venta.ProductoDTO;
import pe.com.edu.siaa.core.model.dto.BasePaginator;

/**
 * La Class DetalleOrdenCompraDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Fri Dec 22 11:06:59 COT 2017
 * @since SIAA-CORE 2.1
 */
public class DetalleOrdenCompraDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id detalle orden compra. */
    private String idDetalleOrdenCompra;
   
    /** El orden compra. */
    private OrdenCompraDTO ordenCompra;
   
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
   
    /**
     * Instancia un nuevo detalle orden compraDTO.
     */
    public DetalleOrdenCompraDTO() {
    }
   
   
    /**
     * Instancia un nuevo detalle orden compraDTO.
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
    public DetalleOrdenCompraDTO(String idDetalleOrdenCompra, OrdenCompraDTO ordenCompra,ProductoDTO producto,String descripcionProducto, BigDecimal precio, BigDecimal cantidad, BigDecimal descuento, BigDecimal subMontoTotal, BigDecimal montoTotal ) {
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
     public OrdenCompraDTO getOrdenCompra() {
        return this.ordenCompra;
    }
    /**
     * Establece el orden compra.
     *
     * @param ordenCompra el new orden compra
     */
    public void setOrdenCompra(OrdenCompraDTO ordenCompra) {
        this.ordenCompra = ordenCompra;
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
        DetalleOrdenCompraDTO other = (DetalleOrdenCompraDTO) obj;
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
        return "DetalleOrdenCompraDTO [idDetalleOrdenCompra=" + idDetalleOrdenCompra + "]";
    }
   
}