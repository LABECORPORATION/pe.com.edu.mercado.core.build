package pe.com.builderp.core.facturacion.model.vo.compra;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RegistroCompraVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoOperacion;
	private Date fechaEmision;
	private Date fechaVencimiento;
	//COMPROBANTE DE PAGO O DOCUMENTO
	private String tipoDocumentoComprobante;
	private String serieOCodigoDepAduanera;
	private String anhoEmisionDuaODSI;
	
	//Nº DEL COMOPROBANTE DE PAGO DOCUMENTO U ORDEN DE FORMULARIO FISICO O VIRTUAL
	private String nroDocumentoComprante;
	
	/*INFORMACION DEL PROVEEDOR {
	DOCUMENTO DE IDENTIDAD { TIPO, NUMERO},
	APELLIDOS Y NOMBRES, DENOMINACION O RAZON SOCIAL
	}*/
	private String tipoDocIdentidad;
	private String nroDocIdentidad;
	private String nombreORazonSocial;
    
	/*
	 ADQUISICIONES GRAVADAS DESTINADAS A OPERACIONES GRAVADAS Y/O DE EXPORTACION {
			BASE IMPONIBLE
			IGV
	} 
	  */
	private BigDecimal baseImponibleOperacionGrabadaExportacion;
	private BigDecimal IGVOperacionGrabadaExportacion;
	/*
	 ADQUISICIONES GRAVADAS DESTINADAS A OPERACIONES GRAVADAS Y/O DE EXPORTACION Y OPERACIONES NO GRAVADAS {
         BASE IMPONIBLE	
         IGV
}
	 * */
	private BigDecimal baseImponibleOperacionExportacionNoGrabada;
	private BigDecimal IGVOperacionExportacionNoGrabada;
	/*
	 ADQUISICIONES GRAVADAS DESTINADAS A OPERACIONES NO GRAVADAS {
		BASE IMPONIBLE
		IGV
	}
	 * */
	private BigDecimal baseImponibleOperacionNoGrabada;
	private BigDecimal IGVOperacionNoGrabada;
	
	//VALOR DE LAS ADQUISICIONES NO GRAVADAS
	private BigDecimal valorAdquisicionNoGrabada;
	//ISC	
	private String ISC;
	//OTROS TRIBUTOS Y CARGOS
	private BigDecimal otroTributoCargo;
	//IMPORTE TOTAL
	private BigDecimal importeTotal;
	//Nº DE COMPROBANTE DE PAGO EMITIDO POR SUJETO NO DOMICILIADO
	private String nroComprobanteEmitidoSujetoNoDomiciliado;
	/*
	 CONSTANCIA DE DEPOSITO DE DETRACCION {	
		NUMERO
		FECHA DE EMISION
	}
	 * */
	private String nroConstaciaDepositoDetraccion;
	private Date fechaEmisionConstaciaDepositoDetraccion;
	
	private BigDecimal tipoCambio;
	/*
	 REFERENCIA DE COMPROBANTE DE PAGO O DOCUMENTO ORIGINAL QUE SE MODIFICA {			
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
	
	//constructor
	public RegistroCompraVO() {
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

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getTipoDocumentoComprobante() {
		return tipoDocumentoComprobante;
	}

	public void setTipoDocumentoComprobante(String tipoDocumentoComprobante) {
		this.tipoDocumentoComprobante = tipoDocumentoComprobante;
	}

	public String getSerieOCodigoDepAduanera() {
		return serieOCodigoDepAduanera;
	}

	public void setSerieOCodigoDepAduanera(String serieOCodigoDepAduanera) {
		this.serieOCodigoDepAduanera = serieOCodigoDepAduanera;
	}

	public String getAnhoEmisionDuaODSI() {
		return anhoEmisionDuaODSI;
	}

	public void setAnhoEmisionDuaODSI(String anhoEmisionDuaODSI) {
		this.anhoEmisionDuaODSI = anhoEmisionDuaODSI;
	}

	public String getNroDocumentoComprante() {
		return nroDocumentoComprante;
	}

	public void setNroDocumentoComprante(String nroDocumentoComprante) {
		this.nroDocumentoComprante = nroDocumentoComprante;
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

	public BigDecimal getBaseImponibleOperacionGrabadaExportacion() {
		return baseImponibleOperacionGrabadaExportacion;
	}

	public void setBaseImponibleOperacionGrabadaExportacion(BigDecimal baseImponibleOperacionGrabadaExportacion) {
		this.baseImponibleOperacionGrabadaExportacion = baseImponibleOperacionGrabadaExportacion;
	}

	public BigDecimal getIGVOperacionGrabadaExportacion() {
		return IGVOperacionGrabadaExportacion;
	}

	public void setIGVOperacionGrabadaExportacion(BigDecimal iGVOperacionGrabadaExportacion) {
		IGVOperacionGrabadaExportacion = iGVOperacionGrabadaExportacion;
	}

	public BigDecimal getBaseImponibleOperacionExportacionNoGrabada() {
		return baseImponibleOperacionExportacionNoGrabada;
	}

	public void setBaseImponibleOperacionExportacionNoGrabada(BigDecimal baseImponibleOperacionExportacionNoGrabada) {
		this.baseImponibleOperacionExportacionNoGrabada = baseImponibleOperacionExportacionNoGrabada;
	}

	public BigDecimal getIGVOperacionExportacionNoGrabada() {
		return IGVOperacionExportacionNoGrabada;
	}

	public void setIGVOperacionExportacionNoGrabada(BigDecimal iGVOperacionExportacionNoGrabada) {
		IGVOperacionExportacionNoGrabada = iGVOperacionExportacionNoGrabada;
	}

	public BigDecimal getBaseImponibleOperacionNoGrabada() {
		return baseImponibleOperacionNoGrabada;
	}

	public void setBaseImponibleOperacionNoGrabada(BigDecimal baseImponibleOperacionNoGrabada) {
		this.baseImponibleOperacionNoGrabada = baseImponibleOperacionNoGrabada;
	}

	public BigDecimal getIGVOperacionNoGrabada() {
		return IGVOperacionNoGrabada;
	}

	public void setIGVOperacionNoGrabada(BigDecimal iGVOperacionNoGrabada) {
		IGVOperacionNoGrabada = iGVOperacionNoGrabada;
	}

	public BigDecimal getValorAdquisicionNoGrabada() {
		return valorAdquisicionNoGrabada;
	}

	public void setValorAdquisicionNoGrabada(BigDecimal valorAdquisicionNoGrabada) {
		this.valorAdquisicionNoGrabada = valorAdquisicionNoGrabada;
	}

	public String getISC() {
		return ISC;
	}

	public void setISC(String iSC) {
		ISC = iSC;
	}

	public BigDecimal getOtroTributoCargo() {
		return otroTributoCargo;
	}

	public void setOtroTributoCargo(BigDecimal otroTributoCargo) {
		this.otroTributoCargo = otroTributoCargo;
	}

	public BigDecimal getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

	public String getNroComprobanteEmitidoSujetoNoDomiciliado() {
		return nroComprobanteEmitidoSujetoNoDomiciliado;
	}

	public void setNroComprobanteEmitidoSujetoNoDomiciliado(String nroComprobanteEmitidoSujetoNoDomiciliado) {
		this.nroComprobanteEmitidoSujetoNoDomiciliado = nroComprobanteEmitidoSujetoNoDomiciliado;
	}

	public String getNroConstaciaDepositoDetraccion() {
		return nroConstaciaDepositoDetraccion;
	}

	public void setNroConstaciaDepositoDetraccion(String nroConstaciaDepositoDetraccion) {
		this.nroConstaciaDepositoDetraccion = nroConstaciaDepositoDetraccion;
	}

	public Date getFechaEmisionConstaciaDepositoDetraccion() {
		return fechaEmisionConstaciaDepositoDetraccion;
	}

	public void setFechaEmisionConstaciaDepositoDetraccion(Date fechaEmisionConstaciaDepositoDetraccion) {
		this.fechaEmisionConstaciaDepositoDetraccion = fechaEmisionConstaciaDepositoDetraccion;
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
