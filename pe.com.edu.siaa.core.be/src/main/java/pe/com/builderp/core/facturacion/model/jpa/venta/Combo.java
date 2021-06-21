package pe.com.builderp.core.facturacion.model.jpa.venta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name = "Combo", schema = ConfiguracionEntityManagerUtil.ESQUEMA_FACTURACION)
public class Combo implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id producto. */
    @Id
    @Column(name = "idCombo" , length = 32)
    private String idCombo;
   
    /** El categoria. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDetalleProducto", referencedColumnName = "idDetalleProducto")
    private DetalleProducto detalleProducto;
    
    
    /** El categoria. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDetalleCombo", referencedColumnName = "idDetalleProducto")
    private DetalleProducto detalleCombo;
   
    
    /** El precio. */
    @Column(name = "cantidad" , precision = 18 , scale = 0)
    private BigDecimal cantidad;
    
    /** El precio. */
    @Column(name = "subMontoTotal" , precision = 18 , scale = 2)
    private BigDecimal subMontoTotal;
    
    /** El precio. */
    @Column(name = "montoTotal" , precision = 18 , scale = 2)
    private BigDecimal montoTotal;
   

    /**
     * Instancia un nuevo producto.
     */
    public Combo() {
    }


	public Combo(String idCombo, DetalleProducto detalleProducto, DetalleProducto detalleCombo,BigDecimal cantidad,
			BigDecimal subMontoTotal,BigDecimal montoTotal) {
		super();
		this.idCombo = idCombo;
		this.detalleProducto = detalleProducto;	
		this.detalleCombo = detalleCombo;	
		this.cantidad = cantidad;	
		this.subMontoTotal = subMontoTotal;	
		this.montoTotal = montoTotal;	
	}

	
	public String getIdCombo() {
		return idCombo;
	}


	public void setIdCombo(String idCombo) {
		this.idCombo = idCombo;
	}


	public DetalleProducto getDetalleProducto() {
		return detalleProducto;
	}


	public void setDetalleProducto(DetalleProducto detalleProducto) {
		this.detalleProducto = detalleProducto;
	}	

	public DetalleProducto getDetalleCombo() {
		return detalleCombo;
	}


	public void setDetalleCombo(DetalleProducto detalleCombo) {
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
		Combo other = (Combo) obj;
		if (idCombo == null) {
			if (other.idCombo != null)
				return false;
		} else if (!idCombo.equals(other.idCombo))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Combo [idCombo=" + idCombo + "]";
	}
   
  
}