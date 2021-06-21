package pe.com.builderp.core.facturacion.model.dto.venta;

import java.io.Serializable;
import java.math.BigDecimal;
import pe.com.builderp.core.facturacion.model.dto.compra.ProveedorDTO;
import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO;

/**
 * La Class ProductoDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Fri Dec 22 11:07:03 COT 2017
 * @since SIAA-CORE 2.1
 */
public class ProductoProveedorDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id producto. */
    private String idProductoProveedor;
   
    /** El categoria. */
    private ProductoDTO producto;
   
    /** El item by unidad medida. */
    private ProveedorDTO proveedor;
   
    private BigDecimal precio;
    
    private BigDecimal precioMayor;
   
    private ItemDTO itemByUnidadMedida;
    /**
     * Instancia un nuevo productoDTO.
     */
    public ProductoProveedorDTO() {
    }


	public ProductoProveedorDTO(ItemDTO itemByUnidadMedida,String idProductoProveedor, ProductoDTO producto, ProveedorDTO proveedor,
			BigDecimal precio, BigDecimal precioMayor) {
		super();
		this.idProductoProveedor = idProductoProveedor;
		this.producto = producto;
		this.proveedor = proveedor;
		this.precio = precio;
		this.precioMayor = precioMayor;
		this.itemByUnidadMedida=itemByUnidadMedida;
	}


	
	
	public ItemDTO getItemByUnidadMedida() {
		return itemByUnidadMedida;
	}


	public void setItemByUnidadMedida(ItemDTO itemByUnidadMedida) {
		this.itemByUnidadMedida = itemByUnidadMedida;
	}


	public String getIdProductoProveedor() {
		return idProductoProveedor;
	}


	public void setIdProductoProveedor(String idProductoProveedor) {
		this.idProductoProveedor = idProductoProveedor;
	}


	public ProductoDTO getProducto() {
		return producto;
	}


	public void setProducto(ProductoDTO producto) {
		this.producto = producto;
	}


	public ProveedorDTO getProveedor() {
		return proveedor;
	}


	public void setProveedor(ProveedorDTO proveedor) {
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
		ProductoProveedorDTO other = (ProductoProveedorDTO) obj;
		if (idProductoProveedor == null) {
			if (other.idProductoProveedor != null)
				return false;
		} else if (!idProductoProveedor.equals(other.idProductoProveedor))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "ProductoProveedorDTO [idProductoProveedor=" + idProductoProveedor + "]";
	}
   
    
}