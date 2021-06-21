package pe.com.builderp.core.facturacion.model.dto.compra;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.EntidadDTO;

/**
 * La Class OrdenCompraDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Fri Dec 22 11:07:00 COT 2017
 * @since SIAA-CORE 2.1
 */
public class OrdenCompraDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id orden compra. */
    private String idOrdenCompra;
   
    /** El entidad. */
    private EntidadDTO entidad;
   
    /** El proveedor. */
    private ProveedorDTO proveedor;
   
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
   
    /** El fecha orden compra. */
    private Date fechaOrdenCompra;
   
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
   
    /** El orden compra detalle orden compra list. */
    private List<DetalleOrdenCompraDTO> ordenCompraDetalleOrdenCompraList = new ArrayList<DetalleOrdenCompraDTO>();
   
    /** El orden compra compra list. */
    private List<CompraDTO> ordenCompraCompraList = new ArrayList<CompraDTO>();
   
    /**
     * Instancia un nuevo orden compraDTO.
     */
    public OrdenCompraDTO() {
    }
   
   
    /**
     * Instancia un nuevo orden compraDTO.
     *
     * @param idOrdenCompra el id orden compra
     * @param entidad el entidad
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
     * @param fechaOrdenCompra el fecha orden compra
     * @param fechaCreacion el fecha creacion
     * @param usuarioCreacion el usuario creacion
     * @param fechaModificacion el fecha modificacion
     * @param usuarioModificacion el usuario modificacion
     * @param estado el estado
     * @param ipAcceso el ip acceso
     */
    public OrdenCompraDTO(String serie,String idOrdenCompra, EntidadDTO entidad,ProveedorDTO proveedor,ItemDTO tipoDocSunat,ItemDTO itemByTipoMoneda,BigDecimal tipoCambio, String nroDoc, BigDecimal procentajeIgv, BigDecimal igv, BigDecimal descuentoTotal, BigDecimal subMontoTotal, BigDecimal montoTotal, Date fechaOrdenCompra, Date fechaCreacion, String usuarioCreacion, Date fechaModificacion, String usuarioModificacion, String estado, String ipAcceso ) {
        super();
        this.idOrdenCompra = idOrdenCompra;
        this.entidad = entidad;
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
        this.fechaOrdenCompra = fechaOrdenCompra;
        this.fechaCreacion = fechaCreacion;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioModificacion = usuarioModificacion;
        this.estado = estado;
        this.ipAcceso = ipAcceso;
        this.serie=serie;
    }
   
    //get y set
    
    public String getSerie() {
 		return serie;
 	}


 	public void setSerie(String serie) {
 		this.serie = serie;
 	}    
    
    /**
     * Obtiene id orden compra.
     *
     * @return id orden compra
     */
     public String getIdOrdenCompra() {
        return this.idOrdenCompra;
    }
    /**
     * Establece el id orden compra.
     *
     * @param idOrdenCompra el new id orden compra
     */
    public void setIdOrdenCompra(String idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
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
     * Obtiene proveedor.
     *
     * @return proveedor
     */
     public ProveedorDTO getProveedor() {
        return this.proveedor;
    }
    /**
     * Establece el proveedor.
     *
     * @param proveedor el new proveedor
     */
    public void setProveedor(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
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
     * Obtiene fecha orden compra.
     *
     * @return fecha orden compra
     */
     public Date getFechaOrdenCompra() {
        return this.fechaOrdenCompra;
    }
    /**
     * Establece el fecha orden compra.
     *
     * @param fechaOrdenCompra el new fecha orden compra
     */
    public void setFechaOrdenCompra(Date fechaOrdenCompra) {
        this.fechaOrdenCompra = fechaOrdenCompra;
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
     * Establece el orden compra detalle orden compra list.
     *
     * @param ordenCompraDetalleOrdenCompraList el new orden compra detalle orden compra list
     */
    public List<DetalleOrdenCompraDTO> getOrdenCompraDetalleOrdenCompraList(){
        return this.ordenCompraDetalleOrdenCompraList;
    }
    /**
     * Establece el orden compra list.
     *
     * @param ordenCompraList el new orden compra list
     */
    public void setOrdenCompraDetalleOrdenCompraList(List<DetalleOrdenCompraDTO> ordenCompraDetalleOrdenCompraList) {
        this.ordenCompraDetalleOrdenCompraList = ordenCompraDetalleOrdenCompraList;
    }   
    /**
     * Establece el orden compra compra list.
     *
     * @param ordenCompraCompraList el new orden compra compra list
     */
    public List<CompraDTO> getOrdenCompraCompraList(){
        return this.ordenCompraCompraList;
    }
    /**
     * Establece el orden compra list.
     *
     * @param ordenCompraList el new orden compra list
     */
    public void setOrdenCompraCompraList(List<CompraDTO> ordenCompraCompraList) {
        this.ordenCompraCompraList = ordenCompraCompraList;
    }   
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idOrdenCompra == null) ? 0 : idOrdenCompra.hashCode());
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
        OrdenCompraDTO other = (OrdenCompraDTO) obj;
        if (idOrdenCompra == null) {
            if (other.idOrdenCompra != null) {
                return false;
            }
        } else if (!idOrdenCompra.equals(other.idOrdenCompra)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "OrdenCompraDTO [idOrdenCompra=" + idOrdenCompra + "]";
    }
   
}