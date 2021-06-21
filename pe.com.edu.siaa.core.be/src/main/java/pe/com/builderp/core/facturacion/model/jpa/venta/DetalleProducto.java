package pe.com.builderp.core.facturacion.model.jpa.venta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@Table(name = "DetalleProducto", schema = ConfiguracionEntityManagerUtil.ESQUEMA_FACTURACION)
public class DetalleProducto implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id producto. */
    @Id
    @Column(name = "idDetalleProducto" , length = 32)
    private String idDetalleProducto;
   
    /** El categoria. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProducto", referencedColumnName = "idProducto")
    private Producto producto;
   
    /** El item by unidad medida. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUnidadmedida", referencedColumnName = "idItem")
    private Item itemByUnidadMedida;
      
    /** El precio. */
    @Column(name = "precioUnitario" , precision = 18 , scale = 2)
    private BigDecimal precioUnitario;
    
    /** El precio. */
    @Column(name = "precioVenta" , precision = 18 , scale = 2)
    private BigDecimal precioVenta;
    
    
    /** El precio. */
    @Column(name = "margen" , precision = 18 , scale = 2)
    private BigDecimal margen;
   
    /** El nro serie. */
    @Column(name = "codigoBarra" , length = 50)
    private String codigoBarra;
    
    /** El nro serie. */
    @Column(name = "estado" , length = 2)
    private String estado;
    
    /** El nro serie. */
    @Column(name = "precioOferta" , precision = 18 , scale = 2)
    private BigDecimal precioOferta;
    
    @Column(name = "estadoOferta" , length = 50)
    private String estadoOferta;
    
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaVigenciaOferta")
    private Date fechaVigenciaOferta;  
    
    /** El nro serie. */
    @Column(name = "combo" , length = 1)
    private String combo;
  
    
    /**
     * Instancia un nuevo producto.
     */
    public DetalleProducto() {
    }


	public DetalleProducto(String idDetalleProducto, Producto producto, Item itemByUnidadMedida,
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


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	public Item getItemByUnidadMedida() {
		return itemByUnidadMedida;
	}


	public void setItemByUnidadMedida(Item itemByUnidadMedida) {
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
		DetalleProducto other = (DetalleProducto) obj;
		if (idDetalleProducto == null) {
			if (other.idDetalleProducto != null)
				return false;
		} else if (!idDetalleProducto.equals(other.idDetalleProducto))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "DetalleProducto [idDetalleProducto=" + idDetalleProducto + "]";
	}
   
  
}