package pe.com.edu.siaa.core.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import pe.com.edu.siaa.core.model.dto.BasePaginator;

public class PagoFiltroVO extends BasePaginator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date fechaInicio;
	private Date fechaFin;
	private String idEscuela;
	private String idAnhoSemestre;	
	private String idAlumno;
	private Long idItem;
	private String idFiltro;
	private String idUsuario;
	
	private String fechaPago;	
	private String tipoComprobante;
	private String serie;
	private String nroComprobante;
	private String clienteNombre;
	private String tipoDoc;
	private String nroDoc;
	private String moneda;
	private String condicion;
	private String nroCuenta;
	private Integer cuentaContableTotal;
	private String medioPago;
	private String Banco;
	private String alumno;
	private String concepto;
	private String efectivo;
	private String esDeposito;
	private String fechaOperacion;
	private String nroVoucher;
	private String observacion;
	private BigDecimal montoMInimo;
	 
	
	
	public PagoFiltroVO() {
		super();
	}



	public BigDecimal getMontoMInimo() {
		return montoMInimo;
	}



	public void setMontoMInimo(BigDecimal montoMInimo) {
		this.montoMInimo = montoMInimo;
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



	public String getIdEscuela() {
		return idEscuela;
	}



	public void setIdEscuela(String idEscuela) {
		this.idEscuela = idEscuela;
	}



	public String getIdAnhoSemestre() {
		return idAnhoSemestre;
	}



	public void setIdAnhoSemestre(String idAnhoSemestre) {
		this.idAnhoSemestre = idAnhoSemestre;
	}



	public String getIdAlumno() {
		return idAlumno;
	}



	public void setIdAlumno(String idAlumno) {
		this.idAlumno = idAlumno;
	}



	public Long getIdItem() {
		return idItem;
	}



	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}



	public String getIdFiltro() {
		return idFiltro;
	}



	public void setIdFiltro(String idFiltro) {
		this.idFiltro = idFiltro;
	}



	public String getIdUsuario() {
		return idUsuario;
	}



	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}



	public String getFechaPago() {
		return fechaPago;
	}



	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}




	public String getTipoDoc() {
		return tipoDoc;
	}



	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}



	public String getSerie() {
		return serie;
	}



	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getClienteNombre() {
		return clienteNombre;
	}



	public void setClienteNombre(String clienteNombre) {
		this.clienteNombre = clienteNombre;
	}



	public String getBanco() {
		return Banco;
	}



	public void setBanco(String banco) {
		Banco = banco;
	}



	public String getAlumno() {
		return alumno;
	}



	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}



	public String getConcepto() {
		return concepto;
	}



	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}


 


	public String getEfectivo() {
		return efectivo;
	}



	public void setEfectivo(String efectivo) {
		this.efectivo = efectivo;
	}



	public String getEsDeposito() {
		return esDeposito;
	}



	public void setEsDeposito(String esDeposito) {
		this.esDeposito = esDeposito;
	}



	public String getFechaOperacion() {
		return fechaOperacion;
	}



	public void setFechaOperacion(String fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}



	public String getNroVoucher() {
		return nroVoucher;
	}



	public void setNroVoucher(String nroVoucher) {
		this.nroVoucher = nroVoucher;
	}



	public String getObservacion() {
		return observacion;
	}



	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}


 

	public String getTipoComprobante() {
		return tipoComprobante;
	}



	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}



	public String getNroComprobante() {
		return nroComprobante;
	}



	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}



	public String getNroDoc() {
		return nroDoc;
	}



	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}



	public String getMoneda() {
		return moneda;
	}



	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}



	public String getNroCuenta() {
		return nroCuenta;
	}



	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}


	public Integer getCuentaContableTotal() {
		return cuentaContableTotal;
	}



	public void setCuentaContableTotal(Integer cuentaContableTotal) {
		this.cuentaContableTotal = cuentaContableTotal;
	}



	public String getMedioPago() {
		return medioPago;
	}



	public void setMedioPago(String medioPago) {
		this.medioPago = medioPago;
	}



	public String getCondicion() {
		return condicion;
	}



	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}
	
	
		
}
