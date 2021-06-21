package pe.com.builderp.core.facturacion.model.vo.venta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RegistroVentaVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Nº CORRELATIVO DEL REGISTRO O CODIGO UNICO DE LA OPERACION
	private String codigoOperacion;
	//FECHA DE EMISION DEL COMPROBANTE DE PAGO O DOCUMENTO
	private Date fechaEmision;
	//FECHA DE VENCIMIENTO Y/O PAGO
	private Date fechaVencimientoOPago;
	
	/*
	 COMPROBANTE DE PAGO O DOCUMENTO {		
		TIPO
		Nº SERIE
		NUMERO
	}		
	 * */
	private String tipoDocumentoPago;
	private String serieDocumentoPago;
	private String nroDocumentoPago;
	/*
	 INFORMACION DEL CLIENTE {	
		DOCUMENTO DE IDENTIDAD {TIPO,NUMERO	}
		APELLIDOS Y NOMBRES, DENOMINACION O RAZON SOCIAL
    }
	 * */
	private String tipoDocIdentidad;
	private String nroDocIdentidad;
	private String nombreORazonSocial;
	
	//VALOR FACTURADO DE LA EXPORTACION	
	private BigDecimal valorFacturadoExportacion;
	//BASE IMPONIBLE DE LA OPERACION GRAVADA
	private BigDecimal baseImponibleOperacionGrabada;
	
	/*
	IMPORTE TOTAL DE LA OPERACION EXONERADA O INAFECTA {	
		EXONERADA	
		INAFECTA
	}
	 * */
	private BigDecimal importeTotalOperacionExonerada;
	private BigDecimal importeTotalOperacionInafecto;
	//ISC
	private String ISC;
	//IGV Y/O IPM
	private BigDecimal IGVoIPM;
	
	//OTROS TRIBUTOS Y CARGOS QUE NO FORMAN PARTE DE LA BASE IMPONIBLE
	private BigDecimal otroTributoBaseImponible;
	//IMPORTE TOTAL DEL COMPROBANTE DE PAGO	
	private BigDecimal importeTotalComprobantePago;
	//TIPO DE CAMBIO
	private BigDecimal tipoCambio;
	
	/*
	 REFERENCIA DEL COMPROBANTE DE PAGO O DOCUMENTO ORIGINAL QUE SE MODIFICA{		
		FECHA	
		TIPO	
		SERIE	
		Nº DEL COMPROBANTE DE PAGO O DOCUMENTO
	}
	 * */
	private Date fehaRefComprobanteDocModifica;
	private String tipoDocRefComprobanteDocModifica;
	private String serieDocRefComprobanteDocModifica;
	private String nroDocRefComprobanteDocModifica;
	
	//Constructor
	public RegistroVentaVO() {
		super();
	}
	
	//get y set
	
	public String getCodigoOperacion() {
		return codigoOperacion;
	}

	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Date getFechaVencimientoOPago() {
		return fechaVencimientoOPago;
	}

	public void setFechaVencimientoOPago(Date fechaVencimientoOPago) {
		this.fechaVencimientoOPago = fechaVencimientoOPago;
	}

	public String getTipoDocumentoPago() {
		return tipoDocumentoPago;
	}

	public void setTipoDocumentoPago(String tipoDocumentoPago) {
		this.tipoDocumentoPago = tipoDocumentoPago;
	}

	public String getSerieDocumentoPago() {
		return serieDocumentoPago;
	}

	public void setSerieDocumentoPago(String serieDocumentoPago) {
		this.serieDocumentoPago = serieDocumentoPago;
	}

	public String getNroDocumentoPago() {
		return nroDocumentoPago;
	}

	public void setNroDocumentoPago(String nroDocumentoPago) {
		this.nroDocumentoPago = nroDocumentoPago;
	}

	public String getTipoDocIdentidad() {
		return tipoDocIdentidad;
	}

	public void setTipoDocIdentidad(String tipoDocIdentidad) {
		this.tipoDocIdentidad = tipoDocIdentidad;
	}

	public String getNroDocIdentidad() {
		return nroDocIdentidad;
	}

	public void setNroDocIdentidad(String nroDocIdentidad) {
		this.nroDocIdentidad = nroDocIdentidad;
	}

	public String getNombreORazonSocial() {
		return nombreORazonSocial;
	}

	public void setNombreORazonSocial(String nombreORazonSocial) {
		this.nombreORazonSocial = nombreORazonSocial;
	}

	public BigDecimal getValorFacturadoExportacion() {
		return valorFacturadoExportacion;
	}

	public void setValorFacturadoExportacion(BigDecimal valorFacturadoExportacion) {
		this.valorFacturadoExportacion = valorFacturadoExportacion;
	}

	public BigDecimal getBaseImponibleOperacionGrabada() {
		return baseImponibleOperacionGrabada;
	}

	public void setBaseImponibleOperacionGrabada(BigDecimal baseImponibleOperacionGrabada) {
		this.baseImponibleOperacionGrabada = baseImponibleOperacionGrabada;
	}

	public BigDecimal getImporteTotalOperacionExonerada() {
		return importeTotalOperacionExonerada;
	}

	public void setImporteTotalOperacionExonerada(BigDecimal importeTotalOperacionExonerada) {
		this.importeTotalOperacionExonerada = importeTotalOperacionExonerada;
	}

	public BigDecimal getImporteTotalOperacionInafecto() {
		return importeTotalOperacionInafecto;
	}

	public void setImporteTotalOperacionInafecto(BigDecimal importeTotalOperacionInafecto) {
		this.importeTotalOperacionInafecto = importeTotalOperacionInafecto;
	}

	public String getISC() {
		return ISC;
	}

	public void setISC(String iSC) {
		ISC = iSC;
	}

	public BigDecimal getIGVoIPM() {
		return IGVoIPM;
	}

	public void setIGVoIPM(BigDecimal iGVoIPM) {
		IGVoIPM = iGVoIPM;
	}

	public BigDecimal getOtroTributoBaseImponible() {
		return otroTributoBaseImponible;
	}

	public void setOtroTributoBaseImponible(BigDecimal otroTributoBaseImponible) {
		this.otroTributoBaseImponible = otroTributoBaseImponible;
	}

	public BigDecimal getImporteTotalComprobantePago() {
		return importeTotalComprobantePago;
	}

	public void setImporteTotalComprobantePago(BigDecimal importeTotalComprobantePago) {
		this.importeTotalComprobantePago = importeTotalComprobantePago;
	}

	public BigDecimal getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public Date getFehaRefComprobanteDocModifica() {
		return fehaRefComprobanteDocModifica;
	}

	public void setFehaRefComprobanteDocModifica(Date fehaRefComprobanteDocModifica) {
		this.fehaRefComprobanteDocModifica = fehaRefComprobanteDocModifica;
	}

	public String getTipoDocRefComprobanteDocModifica() {
		return tipoDocRefComprobanteDocModifica;
	}

	public void setTipoDocRefComprobanteDocModifica(String tipoDocRefComprobanteDocModifica) {
		this.tipoDocRefComprobanteDocModifica = tipoDocRefComprobanteDocModifica;
	}

	public String getSerieDocRefComprobanteDocModifica() {
		return serieDocRefComprobanteDocModifica;
	}

	public void setSerieDocRefComprobanteDocModifica(String serieDocRefComprobanteDocModifica) {
		this.serieDocRefComprobanteDocModifica = serieDocRefComprobanteDocModifica;
	}

	public String getNroDocRefComprobanteDocModifica() {
		return nroDocRefComprobanteDocModifica;
	}

	public void setNroDocRefComprobanteDocModifica(String nroDocRefComprobanteDocModifica) {
		this.nroDocRefComprobanteDocModifica = nroDocRefComprobanteDocModifica;
	}
	

}
