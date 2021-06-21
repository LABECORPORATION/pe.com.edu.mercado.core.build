package pe.com.builderp.core.facturacion.model.dto.venta;

import java.io.Serializable;
import java.math.BigDecimal;

import pe.com.edu.siaa.core.model.dto.BasePaginator;

/**
 * La Class DetallePedidoDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Fri Dec 22 11:07:01 COT 2017
 * @since SIAA-CORE 2.1
 */
public class DetallePedidoDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id detalle pedido. */
    private String idDetallePedido;
   
    /** El pedido. */
    private PedidoDTO pedido;
   
    /** El producto. */
    private DetalleProductoDTO detalleProducto;
   
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
     * Instancia un nuevo detalle pedidoDTO.
     */
    public DetallePedidoDTO() {
    }
   
   
    /**
     * Instancia un nuevo detalle pedidoDTO.
     *
     * @param idDetallePedido el id detalle pedido
     * @param pedido el pedido
     * @param producto el producto
     * @param descripcionProducto el descripcion producto
     * @param precio el precio
     * @param cantidad el cantidad
     * @param descuento el descuento
     * @param subMontoTotal el sub monto total
     * @param montoTotal el monto total
     */
    public DetallePedidoDTO(String idDetallePedido, PedidoDTO pedido,DetalleProductoDTO detalleProducto,String descripcionProducto, BigDecimal precio, BigDecimal cantidad, BigDecimal descuento, BigDecimal subMontoTotal, BigDecimal montoTotal ) {
        super();
        this.idDetallePedido = idDetallePedido;
        this.pedido = pedido;
        this.detalleProducto = detalleProducto;
        this.descripcionProducto = descripcionProducto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.descuento = descuento;
        this.subMontoTotal = subMontoTotal;
        this.montoTotal = montoTotal;
    }
   
    //get y set
    /**
     * Obtiene id detalle pedido.
     *
     * @return id detalle pedido
     */
     public String getIdDetallePedido() {
        return this.idDetallePedido;
    }
    /**
     * Establece el id detalle pedido.
     *
     * @param idDetallePedido el new id detalle pedido
     */
    public void setIdDetallePedido(String idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }
    /**
     * Obtiene pedido.
     *
     * @return pedido
     */
     public PedidoDTO getPedido() {
        return this.pedido;
    }
    /**
     * Establece el pedido.
     *
     * @param pedido el new pedido
     */
    public void setPedido(PedidoDTO pedido) {
        this.pedido = pedido;
    }
    
    public DetalleProductoDTO getDetalleProducto() {
		return detalleProducto;
	}


	public void setDetalleProducto(DetalleProductoDTO detalleProducto) {
		this.detalleProducto = detalleProducto;
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
                + ((idDetallePedido == null) ? 0 : idDetallePedido.hashCode());
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
        DetallePedidoDTO other = (DetallePedidoDTO) obj;
        if (idDetallePedido == null) {
            if (other.idDetallePedido != null) {
                return false;
            }
        } else if (!idDetallePedido.equals(other.idDetallePedido)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DetallePedidoDTO [idDetallePedido=" + idDetallePedido + "]";
    }
   
}