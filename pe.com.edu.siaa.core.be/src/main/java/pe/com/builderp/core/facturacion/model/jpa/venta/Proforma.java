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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import pe.com.edu.siaa.core.model.jpa.common.Item;
import pe.com.edu.siaa.core.model.jpa.seguridad.Entidad;
import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class Proforma.
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
@Table(name = "Proforma", schema = ConfiguracionEntityManagerUtil.ESQUEMA_FACTURACION)
public class Proforma implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id proforma. */
    @Id
    @Column(name = "idProforma" , length = 32)
    private String idProforma;
   
    /** El entidad. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEntidad", referencedColumnName = "idEntidad")
    private Entidad entidad;
   
    /** El codigo proforma. */
    @Column(name = "codigoProforma" , length = 32)
    private String codigoProforma;
   
    /** El cliente. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    private Cliente cliente;
   
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
   
    /** El procentaje igv. */
    @Column(name = "procentajeIgv" , precision = 18 , scale = 2)
    private BigDecimal procentajeIgv;
   
    /** El igv. */
    @Column(name = "igv" , precision = 18 , scale = 2)
    private BigDecimal igv;
   
    /** El descuento total. */
    @Column(name = "descuentoTotal" , precision = 18 , scale = 2)
    private BigDecimal descuentoTotal;
   
    /** El sub monto total. */
    @Column(name = "subMontoTotal" , precision = 18 , scale = 2)
    private BigDecimal subMontoTotal;
   
    /** El monto total. */
    @Column(name = "montoTotal" , precision = 18 , scale = 2)
    private BigDecimal montoTotal;
   
    /** El fecha proforma. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaProforma")
    private Date fechaProforma;
   
    /** El fecha creacion. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaCreacion")
    private Date fechaCreacion;
   
    /** El usuario creacion. */
    @Column(name = "usuarioCreacion" , length = 50)
    private String usuarioCreacion;
   
    /** El fecha modificacion. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaModificacion")
    private Date fechaModificacion;
   
    /** El usuario modificacion. */
    @Column(name = "usuarioModificacion" , length = 50)
    private String usuarioModificacion;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
   
    /** El ip acceso. */
    @Column(name = "ipAcceso" , length = 150)
    private String ipAcceso;  
    
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaValidaProforma")
    private Date fechaValidaProforma;
    
    /** El descuento. */
    @Column(name = "descuento" , precision = 18 , scale = 2)
    private BigDecimal descuento;
    
    /** El tipodescuento. */
    @Column(name = "tipoDescuento" , length = 1)
    private String tipoDescuento;
    
    /** El serie. */
    @Column(name = "serie" , length = 4)
    private String serie;
   
    /** El proforma detalle proforma list. */
    @OneToMany(mappedBy = "proforma", fetch = FetchType.LAZY)
    private List<DetalleProforma> proformaDetalleProformaList = new ArrayList<DetalleProforma>();
    
    /**
     * Instancia un nuevo proforma.
     */
    public Proforma() {
    }
   
   
    /**
     * Instancia un nuevo proforma.
     *
     * @param idProforma el id proforma
     * @param entidad el entidad
     * @param codigoProforma el codigo proforma
     * @param cliente el cliente
     * @param tipoDocSunat el tipo doc sunat
     * @param itemByTipoMoneda el item by tipo moneda
     * @param tipoCambio el tipo cambio
     * @param nroDoc el nro doc
     * @param procentajeIgv el procentaje igv
     * @param igv el igv
     * @param descuentoTotal el descuento total
     * @param subMontoTotal el sub monto total
     * @param montoTotal el monto total
     * @param fechaProforma el fecha proforma
     * @param fechaCreacion el fecha creacion
     * @param usuarioCreacion el usuario creacion
     * @param fechaModificacion el fecha modificacion
     * @param usuarioModificacion el usuario modificacion
     * @param estado el estado
     * @param ipAcceso el ip acceso
     */
    public Proforma(String serie,BigDecimal descuento,String tipoDescuento,String idProforma, Entidad entidad,String codigoProforma, Cliente cliente,Item tipoDocSunat,
    		Item itemByTipoMoneda,BigDecimal tipoCambio, String nroDoc, BigDecimal procentajeIgv, 
    		BigDecimal igv, BigDecimal descuentoTotal, BigDecimal subMontoTotal, BigDecimal montoTotal, Date fechaProforma,  Date fechaValidaProforma,Date fechaCreacion, String usuarioCreacion, Date fechaModificacion, String usuarioModificacion, String estado, String ipAcceso ) {
        super();
        this.idProforma = idProforma;
        this.entidad = entidad;
        this.codigoProforma = codigoProforma;
        this.cliente = cliente;
        this.tipoDocSunat = tipoDocSunat;
        this.itemByTipoMoneda = itemByTipoMoneda;
        this.tipoCambio = tipoCambio;
        this.nroDoc = nroDoc;
        this.procentajeIgv = procentajeIgv;
        this.igv = igv;
        this.descuentoTotal = descuentoTotal;
        this.subMontoTotal = subMontoTotal;
        this.montoTotal = montoTotal;
        this.fechaProforma = fechaProforma;
        this.fechaCreacion = fechaCreacion;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioModificacion = usuarioModificacion;
        this.estado = estado;
        this.ipAcceso = ipAcceso;
        this.fechaValidaProforma = fechaValidaProforma;
        this.descuento = descuento;
        this.tipoDescuento = tipoDescuento;
        this.serie = serie;
    }
   
    //get y set
    /**
     * Obtiene id proforma.
     *
     * @return id proforma
     */
     public String getIdProforma() {
        return this.idProforma;
    }
    /**
     * Establece el id proforma.
     *
     * @param idProforma el new id proforma
     */
    public void setIdProforma(String idProforma) {
        this.idProforma = idProforma;
    }
    /**
     * Obtiene entidad.
     *
     * @return entidad
     */
     public Entidad getEntidad() {
        return this.entidad;
    }
    /**
     * Establece el entidad.
     *
     * @param entidad el new entidad
     */
    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }
    
    public Date getFechaValidaProforma() {
		return fechaValidaProforma;
	}


	public void setFechaValidaProforma(Date fechaValidaProforma) {
		this.fechaValidaProforma = fechaValidaProforma;
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


	public String getSerie() {
		return serie;
	}


	public void setSerie(String serie) {
		this.serie = serie;
	}


	/**
     * Obtiene codigo proforma.
     *
     * @return codigo proforma
     */
     public String getCodigoProforma() {
        return this.codigoProforma;
    }
    /**
     * Establece el codigo proforma.
     *
     * @param codigoProforma el new codigo proforma
     */
    public void setCodigoProforma(String codigoProforma) {
        this.codigoProforma = codigoProforma;
    }
    /**
     * Obtiene cliente.
     *
     * @return cliente
     */
     public Cliente getCliente() {
        return this.cliente;
    }
    /**
     * Establece el cliente.
     *
     * @param cliente el new cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    /**
     * Obtiene tipo doc sunat.
     *
     * @return tipo doc sunat
     */
     public Item getTipoDocSunat() {
        return this.tipoDocSunat;
    }
    /**
     * Establece el tipo doc sunat.
     *
     * @param tipoDocSunat el new tipo doc sunat
     */
    public void setTipoDocSunat(Item tipoDocSunat) {
        this.tipoDocSunat = tipoDocSunat;
    }
    /**
     * Obtiene item by tipo moneda.
     *
     * @return item by tipo moneda
     */
     public Item getItemByTipoMoneda() {
        return this.itemByTipoMoneda;
    }
    /**
     * Establece el item by tipo moneda.
     *
     * @param itemByTipoMoneda el new item by tipo moneda
     */
    public void setItemByTipoMoneda(Item itemByTipoMoneda) {
        this.itemByTipoMoneda = itemByTipoMoneda;
    }
    /**
     * Obtiene tipo cambio.
     *
     * @return tipo cambio
     */
     public BigDecimal getTipoCambio() {
        return this.tipoCambio;
    }
    /**
     * Establece el tipo cambio.
     *
     * @param tipoCambio el new tipo cambio
     */
    public void setTipoCambio(BigDecimal tipoCambio) {
        this.tipoCambio = tipoCambio;
    }
    /**
     * Obtiene nro doc.
     *
     * @return nro doc
     */
     public String getNroDoc() {
        return this.nroDoc;
    }
    /**
     * Establece el nro doc.
     *
     * @param nroDoc el new nro doc
     */
    public void setNroDoc(String nroDoc) {
        this.nroDoc = nroDoc;
    }
    /**
     * Obtiene procentaje igv.
     *
     * @return procentaje igv
     */
     public BigDecimal getProcentajeIgv() {
        return this.procentajeIgv;
    }
    /**
     * Establece el procentaje igv.
     *
     * @param procentajeIgv el new procentaje igv
     */
    public void setProcentajeIgv(BigDecimal procentajeIgv) {
        this.procentajeIgv = procentajeIgv;
    }
    /**
     * Obtiene igv.
     *
     * @return igv
     */
     public BigDecimal getIgv() {
        return this.igv;
    }
    /**
     * Establece el igv.
     *
     * @param igv el new igv
     */
    public void setIgv(BigDecimal igv) {
        this.igv = igv;
    }
    /**
     * Obtiene descuento total.
     *
     * @return descuento total
     */
     public BigDecimal getDescuentoTotal() {
        return this.descuentoTotal;
    }
    /**
     * Establece el descuento total.
     *
     * @param descuentoTotal el new descuento total
     */
    public void setDescuentoTotal(BigDecimal descuentoTotal) {
        this.descuentoTotal = descuentoTotal;
    }
    /**
     * Obtiene sub monto total.
     *
     * @return sub monto total
     */
     public BigDecimal getSubMontoTotal() {
        return this.subMontoTotal;
    }
    /**
     * Establece el sub monto total.
     *
     * @param subMontoTotal el new sub monto total
     */
    public void setSubMontoTotal(BigDecimal subMontoTotal) {
        this.subMontoTotal = subMontoTotal;
    }
    /**
     * Obtiene monto total.
     *
     * @return monto total
     */
     public BigDecimal getMontoTotal() {
        return this.montoTotal;
    }
    /**
     * Establece el monto total.
     *
     * @param montoTotal el new monto total
     */
    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }
    /**
     * Obtiene fecha proforma.
     *
     * @return fecha proforma
     */
     public Date getFechaProforma() {
        return this.fechaProforma;
    }
    /**
     * Establece el fecha proforma.
     *
     * @param fechaProforma el new fecha proforma
     */
    public void setFechaProforma(Date fechaProforma) {
        this.fechaProforma = fechaProforma;
    }
    /**
     * Obtiene fecha creacion.
     *
     * @return fecha creacion
     */
     public Date getFechaCreacion() {
        return this.fechaCreacion;
    }
    /**
     * Establece el fecha creacion.
     *
     * @param fechaCreacion el new fecha creacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    /**
     * Obtiene usuario creacion.
     *
     * @return usuario creacion
     */
     public String getUsuarioCreacion() {
        return this.usuarioCreacion;
    }
    /**
     * Establece el usuario creacion.
     *
     * @param usuarioCreacion el new usuario creacion
     */
    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }
    /**
     * Obtiene fecha modificacion.
     *
     * @return fecha modificacion
     */
     public Date getFechaModificacion() {
        return this.fechaModificacion;
    }
    /**
     * Establece el fecha modificacion.
     *
     * @param fechaModificacion el new fecha modificacion
     */
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
    /**
     * Obtiene usuario modificacion.
     *
     * @return usuario modificacion
     */
     public String getUsuarioModificacion() {
        return this.usuarioModificacion;
    }
    /**
     * Establece el usuario modificacion.
     *
     * @param usuarioModificacion el new usuario modificacion
     */
    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }
    /**
     * Obtiene estado.
     *
     * @return estado
     */
     public String getEstado() {
        return this.estado;
    }
    /**
     * Establece el estado.
     *
     * @param estado el new estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    /**
     * Obtiene ip acceso.
     *
     * @return ip acceso
     */
     public String getIpAcceso() {
        return this.ipAcceso;
    }
    /**
     * Establece el ip acceso.
     *
     * @param ipAcceso el new ip acceso
     */
    public void setIpAcceso(String ipAcceso) {
        this.ipAcceso = ipAcceso;
    }
    /**
     * Establece el proforma detalle proforma list.
     *
     * @param proformaDetalleProformaList el new proforma detalle proforma list
     */
    public List<DetalleProforma> getProformaDetalleProformaList(){
        return this.proformaDetalleProformaList;
    }
    /**
     * Establece el proforma list.
     *
     * @param proformaList el new proforma list
     */
    public void setProformaDetalleProformaList(List<DetalleProforma> proformaDetalleProformaList) {
        this.proformaDetalleProformaList = proformaDetalleProformaList;
    }   
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idProforma == null) ? 0 : idProforma.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Proforma other = (Proforma) obj;
        if (idProforma == null) {
            if (other.idProforma != null) {
                return false;
            }
        } else if (!idProforma.equals(other.idProforma)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Proforma [idProforma=" + idProforma + "]";
    }
   
}