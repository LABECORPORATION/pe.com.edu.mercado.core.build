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
 * La Class PedidoDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Fri Dec 22 11:07:03 COT 2017
 * @since SIAA-CORE 2.1
 */
public class PedidoDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id pedido. */
    private String idPedido;
   
    /** El entidad. */
    private EntidadDTO entidad;
   
    /** El codigo pedido. */
    private String codigoPedido;
   
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
   
    /** El fecha pedido. */
    private Date fechaPedido;
   
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
    
    private String serie;
    
    private String pedidoDe;
    
    private String pedidoPara;
    
    private String direccion;
    
    private String referencia;
    
    private String cancion;
    
    private String celular;
    
    private String motivo;
    
    private String carta;
    
    private Date fechaEntrega;
    
   
   
    /** El pedido detalle pedido list. */
    private List<DetallePedidoDTO> pedidoDetallePedidoList = new ArrayList<DetallePedidoDTO>();
   
    /** El pedido venta list. */
    private List<VentaDTO> pedidoVentaList = new ArrayList<VentaDTO>();
   
    /**
     * Instancia un nuevo pedidoDTO.
     */
    public PedidoDTO() {
    }
   
   
    /**
     * Instancia un nuevo pedidoDTO.
     *
     * @param idPedido el id pedido
     * @param entidad el entidad
     * @param codigoPedido el codigo pedido
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
     * @param fechaPedido el fecha pedido
     * @param fechaCreacion el fecha creacion
     * @param usuarioCreacion el usuario creacion
     * @param fechaModificacion el fecha modificacion
     * @param usuarioModificacion el usuario modificacion
     * @param estado el estado
     * @param ipAcceso el ip acceso
     */
    public PedidoDTO(String idPedido, EntidadDTO entidad,String codigoPedido, ClienteDTO cliente,ItemDTO tipoDocSunat,ItemDTO itemByTipoMoneda,BigDecimal tipoCambio, String nroDoc, BigDecimal procentajeIgv, BigDecimal igv, BigDecimal descuentoTotal, BigDecimal subMontoTotal, BigDecimal montoTotal, Date fechaPedido, Date fechaCreacion, String usuarioCreacion, Date fechaModificacion, String usuarioModificacion, String estado, String ipAcceso,
    		String serie,String pedidoDe,String pedidoPara,String direccion,String referencia,String cancion,String celular,String motivo,String carta,Date fechaEntrega) {
        super();
        this.idPedido = idPedido;
        this.entidad = entidad;
        this.codigoPedido = codigoPedido;
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
        this.fechaPedido = fechaPedido;
        this.fechaCreacion = fechaCreacion;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioModificacion = usuarioModificacion;
        this.estado = estado;
        this.ipAcceso = ipAcceso;
        this.serie = serie;
        
        this.pedidoDe = pedidoDe;
        this.pedidoPara = pedidoPara;
        this.direccion = direccion;
        this.referencia = referencia;
        this.cancion = cancion;
        this.celular = celular;
        this.motivo = motivo;
        this.carta = carta;
        this.fechaEntrega = fechaEntrega;
       
        
    }
   
    //get y set
    /**
     * Obtiene id pedido.
     *
     * @return id pedido
     */
     public String getIdPedido() {
        return this.idPedido;
    }
    /**
     * Establece el id pedido.
     *
     * @param idPedido el new id pedido
     */
    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
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
     * Obtiene codigo pedido.
     *
     * @return codigo pedido
     */
     public String getCodigoPedido() {
        return this.codigoPedido;
    }
    /**
     * Establece el codigo pedido.
     *
     * @param codigoPedido el new codigo pedido
     */
    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
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
     * Obtiene fecha pedido.
     *
     * @return fecha pedido
     */
     public Date getFechaPedido() {
        return this.fechaPedido;
    }
    /**
     * Establece el fecha pedido.
     *
     * @param fechaPedido el new fecha pedido
     */
    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
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
    
    
    
    public String getPedidoDe() {
		return pedidoDe;
	}


	public void setPedidoDe(String pedidoDe) {
		this.pedidoDe = pedidoDe;
	}


	public String getPedidoPara() {
		return pedidoPara;
	}


	public void setPedidoPara(String pedidoPara) {
		this.pedidoPara = pedidoPara;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getReferencia() {
		return referencia;
	}


	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}


	public String getCancion() {
		return cancion;
	}


	public void setCancion(String cancion) {
		this.cancion = cancion;
	}


	


	public String getCelular() {
		return celular;
	}


	public void setCelular(String celular) {
		this.celular = celular;
	}


	public String getMotivo() {
		return motivo;
	}


	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}


	public String getCarta() {
		return carta;
	}


	public void setCarta(String carta) {
		this.carta = carta;
	}


	public Date getFechaEntrega() {
		return fechaEntrega;
	}


	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

		



	/**
     * Establece el pedido detalle pedido list.
     *
     * @param pedidoDetallePedidoList el new pedido detalle pedido list
     */
    public List<DetallePedidoDTO> getPedidoDetallePedidoList(){
        return this.pedidoDetallePedidoList;
    }
    /**
     * Establece el pedido list.
     *
     * @param pedidoList el new pedido list
     */
    public void setPedidoDetallePedidoList(List<DetallePedidoDTO> pedidoDetallePedidoList) {
        this.pedidoDetallePedidoList = pedidoDetallePedidoList;
    }   
    /**
     * Establece el pedido venta list.
     *
     * @param pedidoVentaList el new pedido venta list
     */
    public List<VentaDTO> getPedidoVentaList(){
        return this.pedidoVentaList;
    }
    /**
     * Establece el pedido list.
     *
     * @param pedidoList el new pedido list
     */
    public void setPedidoVentaList(List<VentaDTO> pedidoVentaList) {
        this.pedidoVentaList = pedidoVentaList;
    }   
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idPedido == null) ? 0 : idPedido.hashCode());
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
        PedidoDTO other = (PedidoDTO) obj;
        if (idPedido == null) {
            if (other.idPedido != null) {
                return false;
            }
        } else if (!idPedido.equals(other.idPedido)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PedidoDTO [idPedido=" + idPedido + "]";
    }
   
}