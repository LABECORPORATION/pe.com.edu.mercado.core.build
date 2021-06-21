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
 
import pe.com.builderp.core.facturacion.model.jpa.compra.Proveedor;
import pe.com.edu.siaa.core.model.jpa.common.Item;
import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class Producto.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Fri Dec 22 11:10:36 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "ProductoProveedor", schema = ConfiguracionEntityManagerUtil.ESQUEMA_FACTURACION)
public class ProductoProveedor implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id producto. */
    @Id
    @Column(name = "idProductoProveedor" , length = 32)
    private String idProductoProveedor;
   
    /** El categoria. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProducto", referencedColumnName = "idProducto")
    private Producto producto;
   
    /** El item by unidad medida. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProveedor", referencedColumnName = "idProveedor")
    private Proveedor proveedor;
   
    /** El precio. */
    @Column(name = "precio" , precision = 18 , scale = 2)
    private BigDecimal precio;
   
    /** El precio. */
    @Column(name = "precioMayor" , precision = 18 , scale = 2)
    private BigDecimal precioMayor;
    
    /** El item by unidad medida. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidadMedida", referencedColumnName = "idItem")
    private Item itemByUnidadMedida;
    
    
    /**
     * Instancia un nuevo producto.
     */
    public ProductoProveedor() {
    }

	public ProductoProveedor(Item itemByUnidadMedida,String idProductoProveedor, Producto producto, Proveedor proveedor, BigDecimal precio,
			BigDecimal precioMayor) {
		super();
		this.idProductoProveedor = idProductoProveedor;
		this.producto = producto;
		this.proveedor = proveedor;
		this.precio = precio;
		this.precioMayor = precioMayor;
		this.itemByUnidadMedida=itemByUnidadMedida;
	}

	
	
	public Item getItemByUnidadMedida() {
		return itemByUnidadMedida;
	}

	public void setItemByUnidadMedida(Item itemByUnidadMedida) {
		this.itemByUnidadMedida = itemByUnidadMedida;
	}

	public String getIdProductoProveedor() {
		return idProductoProveedor;
	}

	public void setIdProductoProveedor(String idProductoProveedor) {
		this.idProductoProveedor = idProductoProveedor;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public BigDecimal getPrecioMayor() {
		return precioMayor;
	}

	public void setPrecioMayor(BigDecimal precioMayor) {
		this.precioMayor = precioMayor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProductoProveedor == null) ? 0 : idProductoProveedor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductoProveedor other = (ProductoProveedor) obj;
		if (idProductoProveedor == null) {
			if (other.idProductoProveedor != null)
				return false;
		} else if (!idProductoProveedor.equals(other.idProductoProveedor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductoProveedor [idProductoProveedor=" + idProductoProveedor + "]";
	}
   
    
}