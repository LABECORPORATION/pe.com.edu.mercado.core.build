package pe.com.edu.siaa.core.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import pe.com.edu.siaa.core.model.dto.BasePaginator;

public class PagoPersonalVO extends BasePaginator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	private String idPersonal;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String tipoPersonal;
	private Long idMetodoPago;
	private Date fechaPago;
	private String metodoPago;
	private String idCategoria; 
	private String categoria; 
	private String idCuentaBancaria;
	private String cuentaBancaria;
	private String descripcion;
	private boolean esFacturado;
	private String adjunto;
	private String foto;
	private BigDecimal monto;
	
	public PagoPersonalVO() {
		super();
	}


	public Date getFechaPago() {
		return fechaPago;
	}


	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}


	public String getIdPersonal() {
		return idPersonal;
	}


	public void setIdPersonal(String idPersonal) {
		this.idPersonal = idPersonal;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidoPaterno() {
		return apellidoPaterno;
	}


	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}


	public String getApellidoMaterno() {
		return apellidoMaterno;
	}


	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}


 


	public Long getIdMetodoPago() {
		return idMetodoPago;
	}


	public void setIdMetodoPago(Long idMetodoPago) {
		this.idMetodoPago = idMetodoPago;
	}


	public String getIdCategoria() {
		return idCategoria;
	}


	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}


	public String getIdCuentaBancaria() {
		return idCuentaBancaria;
	}


	public void setIdCuentaBancaria(String idCuentaBancaria) {
		this.idCuentaBancaria = idCuentaBancaria;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


 


	public boolean isEsFacturado() {
		return esFacturado;
	}


	public void setEsFacturado(boolean esFacturado) {
		this.esFacturado = esFacturado;
	}


	public String getAdjunto() {
		return adjunto;
	}


	public void setAdjunto(String adjunto) {
		this.adjunto = adjunto;
	}


	public String getFoto() {
		return foto;
	}


	public void setFoto(String foto) {
		this.foto = foto;
	}


	public String getTipoPersonal() {
		return tipoPersonal;
	}


	public void setTipoPersonal(String tipoPersonal) {
		this.tipoPersonal = tipoPersonal;
	}


	public String getMetodoPago() {
		return metodoPago;
	}


	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public String getCuentaBancaria() {
		return cuentaBancaria;
	}


	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}


	public BigDecimal getMonto() {
		return monto;
	}


	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	 
	
}
