package pe.com.edu.siaa.core.model.jpa.empresa;

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
 * La Class ControlPago.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Fri Dec 15 11:01:56 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "ControlPago", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_EMPRESA)
public class ControlPago implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id control pago. */
    @Id
    @Column(name = "idControlPago" , length = 12)
    private String idControlPago;
   
    /** El anho semestre. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAnio", referencedColumnName = "idAnhio")
    private Anhio anio;
   
    /** El alumno. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAsociado", referencedColumnName = "idAsociado")
    private Asociado asociado;
   
    /** El tipo doc sunat. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoDocSunat", referencedColumnName = "idItem")
    private Item tipoDocSunat;
   
    /** El item by tipo moneda. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoMoneda", referencedColumnName = "idItem")
    private Item itemByTipoMoneda;
 
    /** El tipo cambio. */
    @Column(name = "tipoCambio" , precision = 18 , scale = 2)
    private BigDecimal tipoCambio;
   
    /** El nro doc. */
    @Column(name = "nroDoc" , length = 50)
    private String nroDoc;
   
    /** El igv. */
    @Column(name = "igv" , precision = 18 , scale = 2)
    private BigDecimal igv;
   
    /** El sub monto total. */
    @Column(name = "subMontoTotal" , precision = 18 , scale = 2)
    private BigDecimal subMontoTotal;
   
    /** El monto total. */
    @Column(name = "montoTotal" , precision = 18 , scale = 2)
    private BigDecimal montoTotal;
   
    /** El fecha pago. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaPago")
    private Date fechaPago;
   
    /** El fecha creacion. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaCreacion")
    private Date fechaCreacion;
   
    /** El usuario creacion. */
    @Column(name = "usuarioCreacion" , length = 50)
    private String usuarioCreacion;
   
    /** El fecha modificacion. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechamodificacion")
    private Date fechaModificacion;
   
    /** El usuario modificacion. */
    @Column(name = "usuarioModificacion" , length = 50)
    private String usuarioModificacion;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
    
    /** El estado. */
    @Column(name = "estadoConvertido" , length = 1)
    private String estadoConvertido;
    
    
    /** El ip. */
    @Column(name = "ip" , length = 150)
    private String ip;
   
    /** El nro correlativo operacion. */
    @Column(name = "nroCorrelativoOperacion" , length = 10)
    private String nroCorrelativoOperacion;
    
    /** El deposito. */
    @Column(name = "deposito" , length = 1)
    private String deposito;
    
    /** El banco. */
    @Column(name = "banco" , length = 200)
    private String banco;
    
    /** El titular de la cuenta. */
    @Column(name = "titularCuenta" , length = 200)
    private String titularCuenta;
    
    /** El nro operacion. */
    @Column(name = "nroOperacion" , length = 20)
    private String nroOperacion;
    
    /** El serie. */
    @Column(name = "serie" , length = 4)
    private String serie;
    
    /** El envioSunat. */
    @Column(name = "envioSunat" , length = 2)
    private String envioSunat;
    
    /** La fecha de operacion. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaOperacion")
    private Date fechaOperacion;
    
    /** El anulado. */
    @Column(name = "anulado" , length = 1)
    private String anulado;
    
    
    /** El descuento. */
    @Column(name = "descuento" , precision = 18 , scale = 2)
    private BigDecimal descuento;
    
    /** El tipodescuento. */
    @Column(name = "tipoDescuento" , length = 1)
    private String tipoDescuento;
    
    /** El descuento total. */
    @Column(name = "descuentoTotal" , precision = 18 , scale = 2)
    private BigDecimal descuentoTotal;
    
    /** El usuario modificacion. */
    @Column(name = "observacion" , length = 500)
    private String observacion;
    
    /** El usuario modificacion. */
    @Column(name = "estadoPago" , length = 32)
    private String estadoPago;
     
    
    /**
     * Instancia un nuevo control pago.
     */
    public ControlPago() {
    }

	public ControlPago(String idControlPago, Anhio anio, Asociado asociado, Item tipoDocSunat, Item itemByTipoMoneda,
		  BigDecimal tipoCambio, String nroDoc, BigDecimal igv, BigDecimal subMontoTotal,
			BigDecimal montoTotal, Date fechaPago, Date fechaCreacion, String usuarioCreacion, Date fechaModificacion,
			String usuarioModificacion, String estado, String estadoConvertido, String ip,
			String nroCorrelativoOperacion, String deposito, String banco, String titularCuenta, String nroOperacion,
			String serie, String envioSunat, Date fechaOperacion, String anulado, BigDecimal descuento,
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
		this.serie = serie;
		this.envioSunat = envioSunat;
		this.fechaOperacion = fechaOperacion;
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

	public Anhio getAnio() {
		return anio;
	}

	public void setAnio(Anhio anio) {
		this.anio = anio;
	}

	public Asociado getAsociado() {
		return asociado;
	}

	public void setAsociado(Asociado asociado) {
		this.asociado = asociado;
	}

	public Item getTipoDocSunat() {
		return tipoDocSunat;
	}

	public void setTipoDocSunat(Item tipoDocSunat) {
		this.tipoDocSunat = tipoDocSunat;
	}

	public Item getItemByTipoMoneda() {
		return itemByTipoMoneda;
	}

	public void setItemByTipoMoneda(Item itemByTipoMoneda) {
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

	public Date getFechaOperacion() {
		return fechaOperacion;
	}

	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
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
		ControlPago other = (ControlPago) obj;
		if (idControlPago == null) {
			if (other.idControlPago != null)
				return false;
		} else if (!idControlPago.equals(other.idControlPago))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ControlPago [idControlPago=" + idControlPago + "]";
	}
    
    
}