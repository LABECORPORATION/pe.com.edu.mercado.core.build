package pe.com.builderp.core.facturacion.model.dto.venta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
public class ComboDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id producto. */
    private String idCombo;
   
    /** El categoria. */
    private DetalleProductoDTO detalleProducto;
    
    /** El categoria. */
    private DetalleProductoDTO detalleCombo;
   
    private BigDecimal cantidad;  
    
    private BigDecimal subMontoTotal;  
    
    private BigDecimal montoTotal;  
    
    private BigDecimal subMontoTemp;  
    
    
    
    /**
     * Instancia un nuevo productoDTO.
     */
    public ComboDTO() {
    }

	public ComboDTO(String idCombo, DetalleProductoDTO detalleProducto,DetalleProductoDTO detalleCombo, BigDecimal cantidad,
			 BigDecimal subMontoTotal,BigDecimal montoTotal) {
		super();
		this.idCombo = idCombo;
		this.detalleProducto = detalleProducto;
		this.detalleCombo = detalleCombo;
		this.cantidad = cantidad;
		this.subMontoTotal = subMontoTotal;
		this.montoTotal = montoTotal;		
	}
	

	
	public BigDecimal getSubMontoTemp() {
		return subMontoTemp;
	}

	public void setSubMontoTemp(BigDecimal subMontoTemp) {
		this.subMontoTemp = subMontoTemp;
	}

	public String getIdCombo() {
		return idCombo;
	}

	public void setIdCombo(String idCombo) {
		this.idCombo = idCombo;
	}

	public DetalleProductoDTO getDetalleProducto() {
		return detalleProducto;
	}

	public void setDetalleProducto(DetalleProductoDTO detalleProducto) {
		this.detalleProducto = detalleProducto;
	}
		

	public DetalleProductoDTO getDetalleCombo() {
		return detalleCombo;
	}

	public void setDetalleCombo(DetalleProductoDTO detalleCombo) {
		this.detalleCombo = detalleCombo;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getSubMontoTotal() {
		return subMontoTotal;
	}

	public void setSubMontoTotal(BigDecimal subMontoTotal) {
		this.subMontoTotal = subMontoTotal;
	}

	public BigDecimal getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCombo == null) ? 0 : idCombo.hashCode());
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
		ComboDTO other = (ComboDTO) obj;
		if (idCombo == null) {
			if (other.idCombo != null)
				return false;
		} else if (!idCombo.equals(other.idCombo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ComboDTO [idCombo=" + idCombo + "]";
	}
   
    
    
   
}