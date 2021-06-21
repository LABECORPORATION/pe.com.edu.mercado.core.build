package pe.com.edu.siaa.core.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pe.com.edu.siaa.core.model.dto.BasePaginator;

public class DetalleVentaFiltroVO   implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date fechaInicio;
	private Date fechaFin;
	
	
	private String fechaVenta;	
	private String horaEmision;
	private String serie;
	private String nroComprobante;
	private String clienteNombre;
	private String tipoDoc;
	private String unidadMedida;
	private String nroDoc;
	private String monto;
	private String idUsuario;
	
	//
	private BigDecimal cantidad;
	private String descipcionProducto;
	private BigDecimal precioUnitario;
	private BigDecimal montoTotal;
	
	private String search;
	private String nombreImpresora;
	
	private List<String>listadoIds= new ArrayList<String>();
	
	public DetalleVentaFiltroVO() {
		super();
	}
	
	
	
	
	public String getUnidadMedida() {
		return unidadMedida;
	}




	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}




	public List<String> getListadoIds() {
		return listadoIds;
	}



	public void setListadoIds(List<String> listadoIds) {
		this.listadoIds = listadoIds;
	}



	public String getNombreImpresora() {
		return nombreImpresora;
	}



	public void setNombreImpresora(String nombreImpresora) {
		this.nombreImpresora = nombreImpresora;
	}



	public String getSearch() {
		return search;
	}



	public void setSearch(String search) {
		this.search = search;
	}



 
	public BigDecimal getCantidad() {
		return cantidad;
	}



	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}



	public String getDescipcionProducto() {
		return descipcionProducto;
	}



	public void setDescipcionProducto(String descipcionProducto) {
		this.descipcionProducto = descipcionProducto;
	}



	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}



	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}



	public BigDecimal getMontoTotal() {
		return montoTotal;
	}



	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}



	public String getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}


	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	 
	
	public String getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(String fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public String getHoraEmision() {
		return horaEmision;
	}

	public void setHoraEmision(String horaEmision) {
		this.horaEmision = horaEmision;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getNroComprobante() {
		return nroComprobante;
	}

	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	public String getClienteNombre() {
		return clienteNombre;
	}

	public void setClienteNombre(String clienteNombre) {
		this.clienteNombre = clienteNombre;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getNroDoc() {
		return nroDoc;
	}

	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	 
	
	
	

}
