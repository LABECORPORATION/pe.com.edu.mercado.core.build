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
 * La Class DetallePedido.
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
@Table(name = "DetallePedido", schema = ConfiguracionEntityManagerUtil.ESQUEMA_FACTURACION)
public class DetallePedido implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id detalle pedido. */
    @Id
    @Column(name = "idDetallePedido" , length = 32)
    private String idDetallePedido;
   
    /** El pedido. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPedido", referencedColumnName = "idPedido")
    private Pedido pedido;
   
    /** El producto. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDetalleProducto", referencedColumnName = "idDetalleProducto")
    private DetalleProducto detalleProducto;
   
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
     * Instancia un nuevo detalle pedido.
     */
    public DetallePedido() {
    }
   
   
    /**
     * Instancia un nuevo detalle pedido.
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
    public DetallePedido(String idDetallePedido, Pedido pedido,DetalleProducto detalleProducto,String descripcionProducto, BigDecimal precio, BigDecimal cantidad, BigDecimal descuento, BigDecimal subMontoTotal, BigDecimal montoTotal ) {
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
     public Pedido getPedido() {
        return this.pedido;
    }
    /**
     * Establece el pedido.
     *
     * @param pedido el new pedido
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    
    public DetalleProducto getDetalleProducto() {
		return detalleProducto;
	}


	public void setDetalleProducto(DetalleProducto detalleProducto) {
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
        DetallePedido other = (DetallePedido) obj;
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
        return "DetallePedido [idDetallePedido=" + idDetallePedido + "]";
    }
   
}