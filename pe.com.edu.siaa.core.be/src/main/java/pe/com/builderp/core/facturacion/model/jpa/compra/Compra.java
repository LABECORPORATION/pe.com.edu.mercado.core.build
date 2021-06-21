package pe.com.builderp.core.facturacion.model.jpa.compra;

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
 * La Class Compra.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Jan 18 00:11:39 COT 2018
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "Compra", schema = ConfiguracionEntityManagerUtil.ESQUEMA_FACTURACION)
public class Compra implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id compra. */
    @Id
    @Column(name = "idCompra" , length = 32)
    private String idCompra;
   
    /** El entidad. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEntidad", referencedColumnName = "idEntidad")
    private Entidad entidad;
   
    /** El orden compra. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idOrdenCompra", referencedColumnName = "idOrdenCompra")
    private OrdenCompra ordenCompra;
   
    /** El proveedor. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProveedor", referencedColumnName = "idProveedor")
    private Proveedor proveedor;
   
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
   
    /** El fecha compra. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaCompra")
    private Date fechaCompra;
   
    /** El nro correlativo operacion. */
    @Column(name = "nroCorrelativoOperacion" , length = 10)
    private String nroCorrelativoOperacion;
   
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
    
    /** El serie. */
    @Column(name = "serie" , length = 10)
    private String serie;
   
    /** El compra detalle compra list. */
    @OneToMany(mappedBy = "compra", fetch = FetchType.LAZY)
    private List<DetalleCompra> compraDetalleCompraList = new ArrayList<DetalleCompra>();
    
    /**
     * Instancia un nuevo compra.
     */
    public Compra() {
    }
   
   
    /**
     * Instancia un nuevo compra.
     *
     * @param idCompra el id compra
     * @param entidad el entidad
     * @param ordenCompra el orden compra
     * @param proveedor el proveedor
     * @param tipoDocSunat el tipo doc sunat
     * @param itemByTipoMoneda el item by tipo moneda
     * @param tipoCambio el tipo cambio
     * @param nroDoc el nro doc
     * @param procentajeIgv el procentaje igv
     * @param igv el igv
     * @param descuentoTotal el descuento total
     * @param subMontoTotal el sub monto total
     * @param montoTotal el monto total
     * @param fechaCompra el fecha compra
     * @param nroCorrelativoOperacion el nro correlativo operacion
     * @param fechaCreacion el fecha creacion
     * @param usuarioCreacion el usuario creacion
     * @param fechaModificacion el fecha modificacion
     * @param usuarioModificacion el usuario modificacion
     * @param estado el estado
     * @param ipAcceso el ip acceso
     */
    public Compra(String idCompra, Entidad entidad,OrdenCompra ordenCompra,Proveedor proveedor,Item tipoDocSunat,Item itemByTipoMoneda,BigDecimal tipoCambio, String nroDoc, BigDecimal procentajeIgv, BigDecimal igv, BigDecimal descuentoTotal, BigDecimal subMontoTotal, BigDecimal montoTotal, Date fechaCompra, String nroCorrelativoOperacion, Date fechaCreacion, String usuarioCreacion, Date fechaModificacion, String usuarioModificacion, String estado, String ipAcceso,
    		String serie) {
        super();
        this.idCompra = idCompra;
        this.entidad = entidad;
        this.ordenCompra = ordenCompra;
        this.proveedor = proveedor;
        this.tipoDocSunat = tipoDocSunat;
        this.itemByTipoMoneda = itemByTipoMoneda;
        this.tipoCambio = tipoCambio;
        this.nroDoc = nroDoc;
        this.procentajeIgv = procentajeIgv;
        this.igv = igv;
        this.descuentoTotal = descuentoTotal;
        this.subMontoTotal = subMontoTotal;
        this.montoTotal = montoTotal;
        this.fechaCompra = fechaCompra;
        this.nroCorrelativoOperacion = nroCorrelativoOperacion;
        this.fechaCreacion = fechaCreacion;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioModificacion = usuarioModificacion;
        this.estado = estado;
        this.ipAcceso = ipAcceso;
        this.serie = serie;
    }
   
    //get y set
    /**
     * Obtiene id compra.
     *
     * @return id compra
     */
     public String getIdCompra() {
        return this.idCompra;
    }
    /**
     * Establece el id compra.
     *
     * @param idCompra el new id compra
     */
    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }    
    
    public String getSerie() {
		return serie;
	}


	public void setSerie(String serie) {
		this.serie = serie;
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
    /**
     * Obtiene orden compra.
     *
     * @return orden compra
     */
     public OrdenCompra getOrdenCompra() {
        return this.ordenCompra;
    }
    /**
     * Establece el orden compra.
     *
     * @param ordenCompra el new orden compra
     */
    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }
    /**
     * Obtiene proveedor.
     *
     * @return proveedor
     */
     public Proveedor getProveedor() {
        return this.proveedor;
    }
    /**
     * Establece el proveedor.
     *
     * @param proveedor el new proveedor
     */
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
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
     * Obtiene fecha compra.
     *
     * @return fecha compra
     */
     public Date getFechaCompra() {
        return this.fechaCompra;
    }
    /**
     * Establece el fecha compra.
     *
     * @param fechaCompra el new fecha compra
     */
    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    /**
     * Obtiene nro correlativo operacion.
     *
     * @return nro correlativo operacion
     */
     public String getNroCorrelativoOperacion() {
        return this.nroCorrelativoOperacion;
    }
    /**
     * Establece el nro correlativo operacion.
     *
     * @param nroCorrelativoOperacion el new nro correlativo operacion
     */
    public void setNroCorrelativoOperacion(String nroCorrelativoOperacion) {
        this.nroCorrelativoOperacion = nroCorrelativoOperacion;
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
     * Establece el compra detalle compra list.
     *
     * @param compraDetalleCompraList el new compra detalle compra list
     */
    public List<DetalleCompra> getCompraDetalleCompraList(){
        return this.compraDetalleCompraList;
    }
    /**
     * Establece el compra list.
     *
     * @param compraList el new compra list
     */
    public void setCompraDetalleCompraList(List<DetalleCompra> compraDetalleCompraList) {
        this.compraDetalleCompraList = compraDetalleCompraList;
    }   
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idCompra == null) ? 0 : idCompra.hashCode());
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
        Compra other = (Compra) obj;
        if (idCompra == null) {
            if (other.idCompra != null) {
                return false;
            }
        } else if (!idCompra.equals(other.idCompra)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Compra [idCompra=" + idCompra + "]";
    }
   
}