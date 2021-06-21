package pe.com.builderp.core.facturacion.model.dto.compra;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import pe.com.builderp.core.facturacion.model.dto.venta.ProductoDTO;
import pe.com.edu.siaa.core.model.dto.BasePaginator;

/**
 * La Class DetalleCompraDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Thu Jan 18 00:11:40 COT 2018
 * @since SIAA-CORE 2.1
 */
public class DetalleCompraDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id detalle compra. */
    private String idDetalleCompra;
   
    /** El compra. */
    private CompraDTO compra;
   
    /** El producto. */
    private ProductoDTO producto;
   
    /** El descripcion producto. */
    private String descripcionProducto;
   
    /** El precio. */
    private BigDecimal precio;
   
    /** El cantidad. */
    private BigDecimal cantidad;
   
    /** El igv. */
    private BigDecimal igv;
   
    /** El descuento. */
    private BigDecimal descuento;
   
    /** El sub monto total. */
    private BigDecimal subMontoTotal;
   
    /** El monto total. */
    private BigDecimal montoTotal;
    
    /** El fecha vencimiento. */
    private Date fechaVencimiento;
   
    /**
     * Instancia un nuevo detalle compraDTO.
     */
    public DetalleCompraDTO() {
    }
   
   
    /**
     * Instancia un nuevo detalle compraDTO.
     *
     * @param idDetalleCompra el id detalle compra
     * @param compra el compra
     * @param producto el producto
     * @param descripcionProducto el descripcion producto
     * @param precio el precio
     * @param cantidad el cantidad
     * @param igv el igv
     * @param descuento el descuento
     * @param subMontoTotal el sub monto total
     * @param montoTotal el monto total
     */
    public DetalleCompraDTO(String idDetalleCompra, CompraDTO compra,ProductoDTO producto,String descripcionProducto, BigDecimal precio, BigDecimal cantidad, BigDecimal igv, BigDecimal descuento, BigDecimal subMontoTotal, BigDecimal montoTotal,
    		Date fechaVencimiento) {
        super();
        this.idDetalleCompra = idDetalleCompra;
        this.compra = compra;
        this.producto = producto;
        this.descripcionProducto = descripcionProducto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.igv = igv;
        this.descuento = descuento;
        this.subMontoTotal = subMontoTotal;
        this.montoTotal = montoTotal;
        this.fechaVencimiento = fechaVencimiento;
    }
   
    //get y set
    /**
     * Obtiene id detalle compra.
     *
     * @return id detalle compra
     */
     public String getIdDetalleCompra() {
        return this.idDetalleCompra;
    }
    /**
     * Establece el id detalle compra.
     *
     * @param idDetalleCompra el new id detalle compra
     */
    public void setIdDetalleCompra(String idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }    
    
    public Date getFechaVencimiento() {
		return fechaVencimiento;
	}


	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}


	/**
     * Obtiene compra.
     *
     * @return compra
     */
     public CompraDTO getCompra() {
        return this.compra;
    }
    /**
     * Establece el compra.
     *
     * @param compra el new compra
     */
    public void setCompra(CompraDTO compra) {
        this.compra = compra;
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
     * Obtiene igv.
     *
     * @return igv
     */
     public BigDecimal getIgv() {
        return this.igv;
    }
    /**
     * Establece el igv.
     *
     * @param igv el new igv
     */
    public void setIgv(BigDecimal igv) {
        this.igv = igv;
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
                + ((idDetalleCompra == null) ? 0 : idDetalleCompra.hashCode());
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
        DetalleCompraDTO other = (DetalleCompraDTO) obj;
        if (idDetalleCompra == null) {
            if (other.idDetalleCompra != null) {
                return false;
            }
        } else if (!idDetalleCompra.equals(other.idDetalleCompra)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DetalleCompraDTO [idDetalleCompra=" + idDetalleCompra + "]";
    }
   
}