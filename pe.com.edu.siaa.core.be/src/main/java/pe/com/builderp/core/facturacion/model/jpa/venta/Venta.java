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
 * La Class Venta.
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
@Table(name = "Venta", schema = ConfiguracionEntityManagerUtil.ESQUEMA_FACTURACION)
public class Venta implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id venta. */
    @Id
    @Column(name = "idVenta" , length = 32)
    private String idVenta;
   
    /** El entidad. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEntidad", referencedColumnName = "idEntidad")
    private Entidad entidad;
   
    /** El pedido. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPedido", referencedColumnName = "idPedido")
    private Pedido pedido;
   
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
   
    /** El fecha venta. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaVenta")
    private Date fechaVenta;
   
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
    @Column(name = "serie" , length = 4)
    private String serie;
    
    /** El envioSunat. */
    @Column(name = "envioSunat" , length = 2)
    private String envioSunat;
    
    /** El anulado. */
    @Column(name = "anulado" , length = 1)
    private String anulado;
    
    
    /** El descuento. */
    @Column(name = "descuento" , precision = 18 , scale = 2)
    private BigDecimal descuento;
    
    /** El tipodescuento. */
    @Column(name = "tipoDescuento" , length = 1)
    private String tipoDescuento;
   
    /** El venta detalle venta list. */
    @OneToMany(mappedBy = "venta", fetch = FetchType.LAZY)
    private List<DetalleVenta> ventaDetalleVentaList = new ArrayList<DetalleVenta>();
    
    /** El estado. */
    @Column(name = "estadoVenta" , length = 32)
    private String estadoVenta;
    
    /** El monto total. */
    @Column(name = "pagoCon" , precision = 18 , scale = 2)
    private BigDecimal pagoCon;
    
    
    /** El monto total. */
    @Column(name = "vuelto" , precision = 18 , scale = 2)
    private BigDecimal vuelto;
    
    /** El descripcioncargo. */
    @Column(name = "comentario")
    private String comentario;
    
    
    /** El descripcioncargo. */
    @Column(name = "tipoPago")
    private String tipoPago;
    
    /** El descripcioncargo. */
    @Column(name = "montoEfectivo" , precision = 18 , scale = 2)
    private BigDecimal montoEfectivo;
    
    
    /** El descripcioncargo. */
    @Column(name = "montoTarjeta" , precision = 18 , scale = 2)
    private BigDecimal montoTarjeta;
    
    
    /** El descripcioncargo. */
    @Column(name = "tipoTarjeta")
    private String tipoTarjeta;    
   

    /** El monto total. */
    @Column(name = "contadorEdit" , precision = 18 , scale = 2)
    private BigDecimal contadorEdit;
    
    /** El cliente. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVehiculo", referencedColumnName = "idVehiculo")
    private Vehiculo vehiculo;
    
    /** El igv. */
    @Column(name = "icbper" , precision = 18 , scale = 2)
    private BigDecimal icbper;
    
    
    /** El igv. */
    @Column(name = "codicbper" , precision = 18 , scale = 0)
    private BigDecimal codicbper;
    
    
    /**
     * Instancia un nuevo venta.
     */
    public Venta() {
    }
   
   
    /**
     * Instancia un nuevo venta.
     *
     * @param idVenta el id venta
     * @param entidad el entidad
     * @param pedido el pedido
     * @param cliente el cliente
     * @param tipoDocSunat el tipo doc sunat
     * @param itemByTipoMoneda el item by tipo moneda
     * @param tipoCambio el tipo cambio
     * @param nroDoc el nro doc
     * @param igv el igv
     * @param descuentoTotal el descuento total
     * @param subMontoTotal el sub monto total
     * @param montoTotal el monto total
     * @param fechaVenta el fecha venta
     * @param nroCorrelativoOperacion el nro correlativo operacion
     * @param fechaCreacion el fecha creacion
     * @param usuarioCreacion el usuario creacion
     * @param fechaModificacion el fecha modificacion
     * @param usuarioModificacion el usuario modificacion
     * @param estado el estado
     * @param ipAcceso el ip acceso
     */
    public Venta(String estadoVenta, String idVenta, Entidad entidad,Pedido pedido,Cliente cliente,Item tipoDocSunat,Item itemByTipoMoneda,BigDecimal tipoCambio, String nroDoc, BigDecimal igv, BigDecimal descuentoTotal, BigDecimal subMontoTotal, BigDecimal montoTotal, Date fechaVenta, String nroCorrelativoOperacion, Date fechaCreacion, String usuarioCreacion, Date fechaModificacion, String usuarioModificacion, String estado, String ipAcceso, String serie, String envioSunat,
    		String anulado,BigDecimal descuento,String tipoDescuento,BigDecimal pagoCon,BigDecimal vuelto,String comentario,Vehiculo vehiculo,
    		String tipoPago,String tipoTarjeta,BigDecimal montoEfectivo,BigDecimal montoTarjeta,BigDecimal contadorEdit,BigDecimal icbper,BigDecimal codicbper ) {
        super();
        this.idVenta = idVenta;
        this.entidad = entidad;
        this.pedido = pedido;
        this.cliente = cliente;
        this.tipoDocSunat = tipoDocSunat;
        this.itemByTipoMoneda = itemByTipoMoneda;
        this.tipoCambio = tipoCambio;
        this.nroDoc = nroDoc;
        this.igv = igv;
        this.descuentoTotal = descuentoTotal;
        this.subMontoTotal = subMontoTotal;
        this.montoTotal = montoTotal;
        this.fechaVenta = fechaVenta;
        this.nroCorrelativoOperacion = nroCorrelativoOperacion;
        this.fechaCreacion = fechaCreacion;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioModificacion = usuarioModificacion;
        this.estado = estado;
        this.ipAcceso = ipAcceso;
        this.serie = serie;
        this.envioSunat = envioSunat;
        this.anulado = anulado;
        this.descuento = descuento;
        this.tipoDescuento = tipoDescuento;
        this.estadoVenta=estadoVenta;        
        this.pagoCon=pagoCon;
        this.vuelto=vuelto;
        this.comentario=comentario;
        this.tipoPago=tipoPago;
        this.tipoTarjeta=tipoTarjeta;
        this.montoEfectivo=montoEfectivo;
        this.montoTarjeta=montoTarjeta;
        this.contadorEdit=contadorEdit;
        this.vehiculo=vehiculo;
        this.icbper=icbper;
        this.codicbper =codicbper;
    }
   
    //get y set
    
    
    
    /**
     * Obtiene id venta.
     *
     * @return id venta
     */
     public String getIdVenta() {
        return this.idVenta;
    }
    public BigDecimal getPagoCon() {
		return pagoCon;
	}


	public void setPagoCon(BigDecimal pagoCon) {
		this.pagoCon = pagoCon;
	}


	public BigDecimal getVuelto() {
		return vuelto;
	}


	public void setVuelto(BigDecimal vuelto) {
		this.vuelto = vuelto;
	}


	public String getComentario() {
		return comentario;
	}


	public void setComentario(String comentario) {
		this.comentario = comentario;
	}


	public String getTipoPago() {
		return tipoPago;
	}


	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}


	public BigDecimal getMontoEfectivo() {
		return montoEfectivo;
	}


	public void setMontoEfectivo(BigDecimal montoEfectivo) {
		this.montoEfectivo = montoEfectivo;
	}


	public BigDecimal getMontoTarjeta() {
		return montoTarjeta;
	}


	public void setMontoTarjeta(BigDecimal montoTarjeta) {
		this.montoTarjeta = montoTarjeta;
	}


	public String getTipoTarjeta() {
		return tipoTarjeta;
	}


	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}


	public BigDecimal getContadorEdit() {
		return contadorEdit;
	}


	public void setContadorEdit(BigDecimal contadorEdit) {
		this.contadorEdit = contadorEdit;
	}


	public Vehiculo getVehiculo() {
		return vehiculo;
	}


	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}


	public BigDecimal getIcbper() {
		return icbper;
	}


	public void setIcbper(BigDecimal icbper) {
		this.icbper = icbper;
	}


	public BigDecimal getCodicbper() {
		return codicbper;
	}


	public void setCodicbper(BigDecimal codicbper) {
		this.codicbper = codicbper;
	}


	public String getEstadoVenta() {
		return estadoVenta;
	}


	public void setEstadoVenta(String estadoVenta) {
		this.estadoVenta = estadoVenta;
	}


	/**
     * Establece el id venta.
     *
     * @param idVenta el new id venta
     */
    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
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
     * Obtiene pedido.
     *
     * @return pedido
     */
     public Pedido getPedido() {
        return this.pedido;
    }
    /**
     * Establece el pedido.
     *
     * @param pedido el new pedido
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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
     * Obtiene fecha venta.
     *
     * @return fecha venta
     */
     public Date getFechaVenta() {
        return this.fechaVenta;
    }
    /**
     * Establece el fecha venta.
     *
     * @param fechaVenta el new fecha venta
     */
    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
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
     * Establece el venta detalle venta list.
     *
     * @param ventaDetalleVentaList el new venta detalle venta list
     */
    public List<DetalleVenta> getVentaDetalleVentaList(){
        return this.ventaDetalleVentaList;
    }
    /**
     * Establece el venta list.
     *
     * @param ventaList el new venta list
     */
    public void setVentaDetalleVentaList(List<DetalleVenta> ventaDetalleVentaList) {
        this.ventaDetalleVentaList = ventaDetalleVentaList;
    }   
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idVenta == null) ? 0 : idVenta.hashCode());
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
        Venta other = (Venta) obj;
        if (idVenta == null) {
            if (other.idVenta != null) {
                return false;
            }
        } else if (!idVenta.equals(other.idVenta)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Venta [idVenta=" + idVenta + "]";
    }
   
}