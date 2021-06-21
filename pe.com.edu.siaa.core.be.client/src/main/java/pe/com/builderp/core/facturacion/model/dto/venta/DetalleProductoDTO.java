package pe.com.builderp.core.facturacion.model.dto.venta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO;
import pe.com.edu.siaa.core.model.dto.contabilidad.PlanContableDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.EntidadDTO;

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
public class DetalleProductoDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id producto. */
    private String idDetalleProducto;
   
    /** El categoria. */
    private ProductoDTO producto;
   
    /** El item by unidad medida. */
    private ItemDTO itemByUnidadMedida;
   
    private BigDecimal precioUnitario;
    
    private BigDecimal precioVenta;
    
    private BigDecimal margen;
    
    private String codigoBarra;

    private String estado;    
   
    
    private BigDecimal precioOferta;   
    
    private String estadoOferta;
    
    private Date fechaVigenciaOferta;
    
    private String combo;
    
    private List<ComboDTO> comboList = new ArrayList<ComboDTO>();
    
    /**
     * Instancia un nuevo productoDTO.
     */
    public DetalleProductoDTO() {
    }

	public DetalleProductoDTO(String idDetalleProducto, ProductoDTO producto, ItemDTO itemByUnidadMedida,
			BigDecimal precioUnitario, BigDecimal precioVenta, BigDecimal margen, String codigoBarra,String estado,
			BigDecimal precioOferta,String estadoOferta,Date fechaVigenciaOferta,String combo) {
		super();
		this.idDetalleProducto = idDetalleProducto;
		this.producto = producto;
		this.itemByUnidadMedida = itemByUnidadMedida;
		this.precioUnitario = precioUnitario;
		this.precioVenta = precioVenta;
		this.margen = margen;
		this.codigoBarra = codigoBarra;
		this.estado=estado;		
		this.precioOferta=precioOferta;
		this.estadoOferta=estadoOferta;
		this.fechaVigenciaOferta=fechaVigenciaOferta;
		this.combo=combo;
	}	
	

	public String getCombo() {
		return combo;
	}

	public void setCombo(String combo) {
		this.combo = combo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}	
	
	public String getEstadoOferta() {
		return estadoOferta;
	}

	public void setEstadoOferta(String estadoOferta) {
		this.estadoOferta = estadoOferta;
	}

	public Date getFechaVigenciaOferta() {
		return fechaVigenciaOferta;
	}

	public void setFechaVigenciaOferta(Date fechaVigenciaOferta) {
		this.fechaVigenciaOferta = fechaVigenciaOferta;
	}


	public BigDecimal getPrecioOferta() {
		return precioOferta;
	}

	public void setPrecioOferta(BigDecimal precioOferta) {
		this.precioOferta = precioOferta;
	}

	public String getIdDetalleProducto() {
		return idDetalleProducto;
	}

	public void setIdDetalleProducto(String idDetalleProducto) {
		this.idDetalleProducto = idDetalleProducto;
	}

	public ProductoDTO getProducto() {
		return producto;
	}

	public void setProducto(ProductoDTO producto) {
		this.producto = producto;
	}

	public ItemDTO getItemByUnidadMedida() {
		return itemByUnidadMedida;
	}

	public void setItemByUnidadMedida(ItemDTO itemByUnidadMedida) {
		this.itemByUnidadMedida = itemByUnidadMedida;
	}

	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}

	public BigDecimal getMargen() {
		return margen;
	}

	public void setMargen(BigDecimal margen) {
		this.margen = margen;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
	
	

	public List<ComboDTO> getComboList() {
		return comboList;
	}

	public void setComboList(List<ComboDTO> comboList) {
		this.comboList = comboList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idDetalleProducto == null) ? 0 : idDetalleProducto.hashCode());
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
		DetalleProductoDTO other = (DetalleProductoDTO) obj;
		if (idDetalleProducto == null) {
			if (other.idDetalleProducto != null)
				return false;
		} else if (!idDetalleProducto.equals(other.idDetalleProducto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DetalleProductoDTO [idDetalleProducto=" + idDetalleProducto + "]";
	}
   
    
    
   
}