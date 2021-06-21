package pe.com.builderp.core.facturacion.model.dto.empresa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

 
import pe.com.edu.siaa.core.model.dto.BasePaginator; 
import pe.com.edu.siaa.core.model.dto.common.ItemDTO; 

/**
 * La Class ControlPagoDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Fri Dec 15 11:57:51 COT 2017
 * @since SIAA-CORE 2.1
 */
public class ControlPagoDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id control pago. */
    private String idControlPago;
   
    /** El anho semestre. */
    private AnhioDTO anio;
   
    /** El alumno. */
    private AsociadoDTO asociado;
   
    /** El tipo doc sunat. */
    private ItemDTO tipoDocSunat;
   
    /** El item by tipo moneda. */
    private ItemDTO itemByTipoMoneda; 
    
    /** El tipo cambio. */
    private BigDecimal tipoCambio;
   
    /** El nro doc. */
    private String nroDoc;
   
    /** El igv. */
    private BigDecimal igv;
   
    /** El sub monto total. */
    private BigDecimal subMontoTotal;
   
    /** El monto total. */
    private BigDecimal montoTotal;
   
    /** El fecha pago. */
    private Date fechaPago;
   
    /** El fecha creacion. */
    private Date fechaCreacion;
   
    /** El usuario creacion. */
    private String usuarioCreacion;
   
    /** El fecha modificacion. */
    private Date fechaModificacion;
   
    /** El usuario modificacion. */
    private String usuarioModificacion;
   
    /** El estado. */
    private String estado;
    
    private String estadoConvertido;
   
    /** El ip. */
    private String ip;
    
    /** El nro correlativo operacion. */
    private String nroCorrelativoOperacion;
    
    
    /** El deposito. */
    private String deposito;
    
    /** El banco. */
    private String banco;
    
    /** El titular de la cuenta. */
    private String titularCuenta;
    
    /** El nro operacion. */
    private String nroOperacion;
    
    /** La fecha de operacion. */
    private Date fechaOperacion;
    
    /** El serie. */
    private String serie;
    
    /** El serie. */
    private String envioSunat;
    
    /** El anulado. */
    private String anulado;
    
    /** El descuento. */
    private BigDecimal descuento;
    
    private String tipoDescuento;
    
    private BigDecimal descuentoTotal;
    private String observacion;
   
    private String estadoPago;
   
    /** El control pago det control pago list. */
    private List<DetControlPagoDTO> controlPagoDetControlPagoList = new ArrayList<DetControlPagoDTO>();
   
    //trasient
    private List<ConceptoPagoDTO> listaConceptoPagoDTO = new ArrayList<ConceptoPagoDTO>();
    
    private boolean esAnulado;
    
	private Date fechaInicio;
	private Date fechaFin;
    /** El monto total. */
    private BigDecimal montoMinimo;
    /**
     * Instancia un nuevo control pagoDTO.
     */
    public ControlPagoDTO() {
    }
	public ControlPagoDTO(String idControlPago, AnhioDTO anio, AsociadoDTO asociado, ItemDTO tipoDocSunat,
			ItemDTO itemByTipoMoneda,  BigDecimal tipoCambio, String nroDoc, BigDecimal igv,
			BigDecimal subMontoTotal, BigDecimal montoTotal, Date fechaPago, Date fechaCreacion, String usuarioCreacion,
			Date fechaModificacion, String usuarioModificacion, String estado, String estadoConvertido, String ip,
			String nroCorrelativoOperacion, String deposito, String banco, String titularCuenta, String nroOperacion,
			Date fechaOperacion, String serie, String envioSunat, String anulado, BigDecimal descuento,
			String tipoDescuento, BigDecimal descuentoTotal, String observacion,String estadoPago) {
		super();
		this.idControlPago = idControlPago;
		this.anio = anio;
		this.asociado = asociado;
		this.tipoDocSunat = tipoDocSunat;
		this.itemByTipoMoneda = itemByTipoMoneda; 
		this.tipoCambio = tipoCambio;
		this.nroDoc = nroDoc;
		this.igv = igv;
		this.subMontoTotal = subMontoTotal;
		this.montoTotal = montoTotal;
		this.fechaPago = fechaPago;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaModificacion = fechaModificacion;
		this.usuarioModificacion = usuarioModificacion;
		this.estado = estado;
		this.estadoConvertido = estadoConvertido;
		this.ip = ip;
		this.nroCorrelativoOperacion = nroCorrelativoOperacion;
		this.deposito = deposito;
		this.banco = banco;
		this.titularCuenta = titularCuenta;
		this.nroOperacion = nroOperacion;
		this.fechaOperacion = fechaOperacion;
		this.serie = serie;
		this.envioSunat = envioSunat;
		this.anulado = anulado;
		this.descuento = descuento;
		this.tipoDescuento = tipoDescuento;
		this.descuentoTotal = descuentoTotal;
		this.observacion = observacion;
		this.estadoPago=estadoPago;
	}
	
	
	public String getEstadoPago() {
		return estadoPago;
	}
	public void setEstadoPago(String estadoPago) {
		this.estadoPago = estadoPago;
	}
	public String getIdControlPago() {
		return idControlPago;
	}
	public void setIdControlPago(String idControlPago) {
		this.idControlPago = idControlPago;
	}
 
	public AnhioDTO getAnio() {
		return anio;
	}
	public void setAnio(AnhioDTO anio) {
		this.anio = anio;
	}
	public AsociadoDTO getAsociado() {
		return asociado;
	}
	public void setAsociado(AsociadoDTO asociado) {
		this.asociado = asociado;
	}
	public ItemDTO getTipoDocSunat() {
		return tipoDocSunat;
	}
	public void setTipoDocSunat(ItemDTO tipoDocSunat) {
		this.tipoDocSunat = tipoDocSunat;
	}
	public ItemDTO getItemByTipoMoneda() {
		return itemByTipoMoneda;
	}
	public void setItemByTipoMoneda(ItemDTO itemByTipoMoneda) {
		this.itemByTipoMoneda = itemByTipoMoneda;
	}
 
	public BigDecimal getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	public String getNroDoc() {
		return nroDoc;
	}
	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}
	public BigDecimal getIgv() {
		return igv;
	}
	public void setIgv(BigDecimal igv) {
		this.igv = igv;
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
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}
	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getEstadoConvertido() {
		return estadoConvertido;
	}
	public void setEstadoConvertido(String estadoConvertido) {
		this.estadoConvertido = estadoConvertido;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getNroCorrelativoOperacion() {
		return nroCorrelativoOperacion;
	}
	public void setNroCorrelativoOperacion(String nroCorrelativoOperacion) {
		this.nroCorrelativoOperacion = nroCorrelativoOperacion;
	}
	public String getDeposito() {
		return deposito;
	}
	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getTitularCuenta() {
		return titularCuenta;
	}
	public void setTitularCuenta(String titularCuenta) {
		this.titularCuenta = titularCuenta;
	}
	public String getNroOperacion() {
		return nroOperacion;
	}
	public void setNroOperacion(String nroOperacion) {
		this.nroOperacion = nroOperacion;
	}
	public Date getFechaOperacion() {
		return fechaOperacion;
	}
	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getEnvioSunat() {
		return envioSunat;
	}
	public void setEnvioSunat(String envioSunat) {
		this.envioSunat = envioSunat;
	}
	public String getAnulado() {
		return anulado;
	}
	public void setAnulado(String anulado) {
		this.anulado = anulado;
	}
	public BigDecimal getDescuento() {
		return descuento;
	}
	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}
	public String getTipoDescuento() {
		return tipoDescuento;
	}
	public void setTipoDescuento(String tipoDescuento) {
		this.tipoDescuento = tipoDescuento;
	}
	public BigDecimal getDescuentoTotal() {
		return descuentoTotal;
	}
	public void setDescuentoTotal(BigDecimal descuentoTotal) {
		this.descuentoTotal = descuentoTotal;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public List<DetControlPagoDTO> getControlPagoDetControlPagoList() {
		return controlPagoDetControlPagoList;
	}
	public void setControlPagoDetControlPagoList(List<DetControlPagoDTO> controlPagoDetControlPagoList) {
		this.controlPagoDetControlPagoList = controlPagoDetControlPagoList;
	}
	public List<ConceptoPagoDTO> getListaConceptoPagoDTO() {
		return listaConceptoPagoDTO;
	}
	public void setListaConceptoPagoDTO(List<ConceptoPagoDTO> listaConceptoPagoDTO) {
		this.listaConceptoPagoDTO = listaConceptoPagoDTO;
	}
	public boolean isEsAnulado() {
		return esAnulado;
	}
	public void setEsAnulado(boolean esAnulado) {
		this.esAnulado = esAnulado;
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
	public BigDecimal getMontoMinimo() {
		return montoMinimo;
	}
	public void setMontoMinimo(BigDecimal montoMinimo) {
		this.montoMinimo = montoMinimo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idControlPago == null) ? 0 : idControlPago.hashCode());
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
		ControlPagoDTO other = (ControlPagoDTO) obj;
		if (idControlPago == null) {
			if (other.idControlPago != null)
				return false;
		} else if (!idControlPago.equals(other.idControlPago))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ControlPagoDTO [idControlPago=" + idControlPago + "]";
	}
   
    
}