package pe.com.builderp.core.facturacion.model.dto.venta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.EntidadDTO;

/**
 * La Class VentaDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Fri Dec 22 11:07:04 COT 2017
 * @since SIAA-CORE 2.1
 */
public class VentaDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id venta. */
    private String idVenta;
   
    /** El entidad. */
    private EntidadDTO entidad;
   
    /** El pedido. */
    private PedidoDTO pedido;
   
    /** El cliente. */
    private ClienteDTO cliente;
   
    /** El tipo doc sunat. */
    private ItemDTO tipoDocSunat;
   
    /** El item by tipo moneda. */
    private ItemDTO itemByTipoMoneda;
   
    /** El tipo cambio. */
    private BigDecimal tipoCambio;
   
    /** El nro doc. */
    private String nroDoc;
   
    /** El procentaje igv. */
    private BigDecimal procentajeIgv;
    
    /** El igv. */
    private BigDecimal igv;
   
    /** El descuento total. */
    private BigDecimal descuentoTotal;
   
    /** El sub monto total. */
    private BigDecimal subMontoTotal;
   
    /** El monto total. */
    private BigDecimal montoTotal;
   
    /** El fecha venta. */
    private Date fechaVenta;
   
    /** El nro correlativo operacion. */
    private String nroCorrelativoOperacion;
   
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
   
    /** El ip acceso. */
    private String ipAcceso;
    
    /** El serie. */
    private String serie;
    
    /** El serie. */
    private String envioSunat;
    
    /** El anulado. */
    private String anulado;
    
    /** El descuento. */
    private BigDecimal descuento;
    
    private String tipoDescuento;
   
    /** El venta detalle venta list. */
    private List<DetalleVentaDTO> ventaDetalleVentaList = new ArrayList<DetalleVentaDTO>();
   
    private String estadoVenta ;
    
    private BigDecimal pagoCon;
    
    private BigDecimal vuelto;
    
    private String comentario;
    
    private String tipoPago;
    
    private BigDecimal montoEfectivo;
    
    private BigDecimal montoTarjeta;
    
    private String tipoTarjeta;  
    
    private BigDecimal contadorEdit;
    
    private VehiculoDTO vehiculo;
    
    private BigDecimal icbper;
    
    private BigDecimal codicbper;
    
    private Date fechaInicio;
	private Date fechaFin;
	
    /**
     * Instancia un nuevo ventaDTO.
     */
    public VentaDTO() {
    }
   
   
    /**
     * Instancia un nuevo ventaDTO.
     *
     * @param idVenta el id venta
     * @param entidad el entidad
     * @param pedido el pedido
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
     * @param fechaVenta el fecha venta
     * @param nroCorrelativoOperacion el nro correlativo operacion
     * @param fechaCreacion el fecha creacion
     * @param usuarioCreacion el usuario creacion
     * @param fechaModificacion el fecha modificacion
     * @param usuarioModificacion el usuario modificacion
     * @param estado el estado
     * @param ipAcceso el ip acceso
     */
    public VentaDTO(String estadoVenta,String idVenta, EntidadDTO entidad,PedidoDTO pedido,ClienteDTO cliente,ItemDTO tipoDocSunat,ItemDTO itemByTipoMoneda,BigDecimal tipoCambio, String nroDoc, BigDecimal igv, BigDecimal descuentoTotal, BigDecimal subMontoTotal, BigDecimal montoTotal, Date fechaVenta, String nroCorrelativoOperacion, Date fechaCreacion, String usuarioCreacion, Date fechaModificacion, String usuarioModificacion, String estado, String ipAcceso, String serie, String envioSunat,String anulado,BigDecimal descuento, String tipoDescuento
    		,BigDecimal pagoCon,BigDecimal vuelto,String comentario,VehiculoDTO vehiculo,
    		String tipoPago,String tipoTarjeta,BigDecimal montoEfectivo,BigDecimal montoTarjeta,BigDecimal contadorEdit,BigDecimal icbper,BigDecimal codicbper) {
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


	public VehiculoDTO getVehiculo() {
		return vehiculo;
	}


	public void setVehiculo(VehiculoDTO vehiculo) {
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
     public EntidadDTO getEntidad() {
        return this.entidad;
    }
    /**
     * Establece el entidad.
     *
     * @param entidad el new entidad
     */
    public void setEntidad(EntidadDTO entidad) {
        this.entidad = entidad;
    }
    /**
     * Obtiene pedido.
     *
     * @return pedido
     */
     public PedidoDTO getPedido() {
        return this.pedido;
    }
    /**
     * Establece el pedido.
     *
     * @param pedido el new pedido
     */
    public void setPedido(PedidoDTO pedido) {
        this.pedido = pedido;
    }
    /**
     * Obtiene cliente.
     *
     * @return cliente
     */
     public ClienteDTO getCliente() {
        return this.cliente;
    }
    /**
     * Establece el cliente.
     *
     * @param cliente el new cliente
     */
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
    /**
     * Obtiene tipo doc sunat.
     *
     * @return tipo doc sunat
     */
     public ItemDTO getTipoDocSunat() {
        return this.tipoDocSunat;
    }
    /**
     * Establece el tipo doc sunat.
     *
     * @param tipoDocSunat el new tipo doc sunat
     */
    public void setTipoDocSunat(ItemDTO tipoDocSunat) {
        this.tipoDocSunat = tipoDocSunat;
    }
    /**
     * Obtiene item by tipo moneda.
     *
     * @return item by tipo moneda
     */
     public ItemDTO getItemByTipoMoneda() {
        return this.itemByTipoMoneda;
    }
    /**
     * Establece el item by tipo moneda.
     *
     * @param itemByTipoMoneda el new item by tipo moneda
     */
    public void setItemByTipoMoneda(ItemDTO itemByTipoMoneda) {
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
    public List<DetalleVentaDTO> getVentaDetalleVentaList(){
        return this.ventaDetalleVentaList;
    }
    /**
     * Establece el venta list.
     *
     * @param ventaList el new venta list
     */
    public void setVentaDetalleVentaList(List<DetalleVentaDTO> ventaDetalleVentaList) {
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
        VentaDTO other = (VentaDTO) obj;
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
        return "VentaDTO [idVenta=" + idVenta + "]";
    }
   
}