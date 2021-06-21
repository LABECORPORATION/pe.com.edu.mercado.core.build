package pe.com.edu.siaa.core.model.dto.seguridad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pe.com.builderp.core.facturacion.model.dto.compra.CompraDTO;
import pe.com.builderp.core.facturacion.model.dto.compra.OrdenCompraDTO;
import pe.com.builderp.core.facturacion.model.dto.compra.ProveedorDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.CategoriaDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.ClienteDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.LineaDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.MarcaDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.ModeloDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.PedidoDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.ProductoDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.ProformaDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.TipoDocSunatEntidadDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.VentaDTO;
import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO;
import pe.com.edu.siaa.core.model.dto.common.ParametroDTO;
import pe.com.edu.siaa.core.model.dto.contabilidad.PlanContableDTO;
 

/**
 * La Class EntidadDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Wed Dec 20 13:16:01 COT 2017
 * @since SIAA-CORE 2.1
 */
public class EntidadDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id entidad. */
    private String idEntidad;
   
    /** El item by tipo via. */
    private ItemDTO itemByTipoVia;
   
    /** El item by zona. */
    private ItemDTO itemByZona;
   
    /** El nombre zona. */
    private String nombreZona;
   
    /** El nombre tipo via. */
    private String nombreTipoVia;
   
    /** El codigo. */
    private String codigo;
   
    /** El codigo externo. */
    private String codigoExterno;
   
    /** El codigo referencia. */
    private String codigoReferencia;
   
    /** El nombre. */
    private String nombre;
   
    /** El direccion. */
    private String direccion;
   
    /** El telefono. */
    private String telefono;
   
    /** El email. */
    private String email;
   
    /** El web. */
    private String web;
   
    /** El fecha creacion. */
    private Date fechaCreacion;
   
    /** El usuario creacion. */
    private String usuarioCreacion;
   
    private String certificadoDigital;
    
    private String vaner;
    
    private String logo;
   
    private Date fechaVenceCertificado;
   
    /** El entidad tipo doc sunat entidad list. */
    private List<TipoDocSunatEntidadDTO> entidadTipoDocSunatEntidadList = new ArrayList<TipoDocSunatEntidadDTO>();
   
    /** El entidad plan contable list. */
    private List<PlanContableDTO> entidadPlanContableList = new ArrayList<PlanContableDTO>();
   
    /** El entidad centro costo list. */
    private List<CentroCostoDTO> entidadCentroCostoList = new ArrayList<CentroCostoDTO>();
   
    /** El entidad agrupa entidad list. */
    private List<AgrupaEntidadDTO> entidadAgrupaEntidadList = new ArrayList<AgrupaEntidadDTO>();
   
    /** El entidad agrupa agrupa entidad list. */
    private List<AgrupaEntidadDTO> entidadAgrupaAgrupaEntidadList = new ArrayList<AgrupaEntidadDTO>();
   
    /** El entidad usuario entidad list. */
    private List<UsuarioEntidadDTO> entidadUsuarioEntidadList = new ArrayList<UsuarioEntidadDTO>();
   
    /** El id entidad parametro list. */
    private List<ParametroDTO> idEntidadParametroList = new ArrayList<ParametroDTO>();
   
    /** El entidad linea list. */
    private List<LineaDTO> entidadLineaList = new ArrayList<LineaDTO>();
   
    /** El entidad categoria list. */
    private List<CategoriaDTO> entidadCategoriaList = new ArrayList<CategoriaDTO>();
   
    /** El entidad marca list. */
    private List<MarcaDTO> entidadMarcaList = new ArrayList<MarcaDTO>();
   
    /** El entidad modelo list. */
    private List<ModeloDTO> entidadModeloList = new ArrayList<ModeloDTO>();
   
    /** El entidad producto list. */
    private List<ProductoDTO> entidadProductoList = new ArrayList<ProductoDTO>();
   
    /** El entidad pedido list. */
    private List<PedidoDTO> entidadPedidoList = new ArrayList<PedidoDTO>();
   
    /** El entidad proforma list. */
    private List<ProformaDTO> entidadProformaList = new ArrayList<ProformaDTO>();
   
    /** El entidad venta list. */
    private List<VentaDTO> entidadVentaList = new ArrayList<VentaDTO>();
   
    /** El entidad orden compra list. */
    private List<OrdenCompraDTO> entidadOrdenCompraList = new ArrayList<OrdenCompraDTO>();
   
    /** El entidad compra list. */
    private List<CompraDTO> entidadCompraList = new ArrayList<CompraDTO>();
   
    /** El entidad cliente list. */
    private List<ClienteDTO> entidadClienteList = new ArrayList<ClienteDTO>();
   
    /** El entidad proveedor list. */
    private List<ProveedorDTO> entidadProveedorList = new ArrayList<ProveedorDTO>();
   
    /**
     * Instancia un nuevo entidadDTO.
     */
    public EntidadDTO() {
    }
   
   
    /**
     * Instancia un nuevo entidadDTO.
     *
     * @param idEntidad el id entidad
     * @param itemByTipoVia el item by tipo via
     * @param itemByZona el item by zona
     * @param nombreZona el nombre zona
     * @param nombreTipoVia el nombre tipo via
     * @param codigo el codigo
     * @param codigoExterno el codigo externo
     * @param codigoReferencia el codigo referencia
     * @param nombre el nombre
     * @param direccion el direccion
     * @param telefono el telefono
     * @param email el email
     * @param web el web
     * @param fechaCreacion el fecha creacion
     * @param usuarioCreacion el usuario creacion
     */
    public EntidadDTO(Date fechaVenceCertificado,String certificadoDigital,String vaner, String logo,String idEntidad, ItemDTO itemByTipoVia,ItemDTO itemByZona,String nombreZona, String nombreTipoVia, String codigo, String codigoExterno, String codigoReferencia, String nombre, String direccion, String telefono, String email, String web, Date fechaCreacion, String usuarioCreacion ) {
        super();
        this.idEntidad = idEntidad;
        this.itemByTipoVia = itemByTipoVia;
        this.itemByZona = itemByZona;
        this.nombreZona = nombreZona;
        this.nombreTipoVia = nombreTipoVia;
        this.codigo = codigo;
        this.codigoExterno = codigoExterno;
        this.codigoReferencia = codigoReferencia;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.web = web;
        this.fechaCreacion = fechaCreacion;
        this.usuarioCreacion = usuarioCreacion;
        this.certificadoDigital = certificadoDigital;
        this.logo = logo;
        this.vaner=vaner;
        this.fechaVenceCertificado = fechaVenceCertificado;
    }
   
    //get y set
    /**
     * Obtiene id entidad.
     *
     * @return id entidad
     */
     public String getIdEntidad() {
        return this.idEntidad;
    }
    /**
     * Establece el id entidad.
     *
     * @param idEntidad el new id entidad
     */
    public void setIdEntidad(String idEntidad) {
        this.idEntidad = idEntidad;
    }
    
    
    
    
    
    public Date getFechaVenceCertificado() {
		return fechaVenceCertificado;
	}


	public void setFechaVenceCertificado(Date fechaVenceCertificado) {
		this.fechaVenceCertificado = fechaVenceCertificado;
	}


	public String getCertificadoDigital() {
		return certificadoDigital;
	}


	public void setCertificadoDigital(String certificadoDigital) {
		this.certificadoDigital = certificadoDigital;
	}


	public String getVaner() {
		return vaner;
	}


	public void setVaner(String vaner) {
		this.vaner = vaner;
	}


	public String getLogo() {
		return logo;
	}


	public void setLogo(String logo) {
		this.logo = logo;
	}


	/**
     * Obtiene item by tipo via.
     *
     * @return item by tipo via
     */
     public ItemDTO getItemByTipoVia() {
        return this.itemByTipoVia;
    }
    /**
     * Establece el item by tipo via.
     *
     * @param itemByTipoVia el new item by tipo via
     */
    public void setItemByTipoVia(ItemDTO itemByTipoVia) {
        this.itemByTipoVia = itemByTipoVia;
    }
    /**
     * Obtiene item by zona.
     *
     * @return item by zona
     */
     public ItemDTO getItemByZona() {
        return this.itemByZona;
    }
    /**
     * Establece el item by zona.
     *
     * @param itemByZona el new item by zona
     */
    public void setItemByZona(ItemDTO itemByZona) {
        this.itemByZona = itemByZona;
    }
    /**
     * Obtiene nombre zona.
     *
     * @return nombre zona
     */
     public String getNombreZona() {
        return this.nombreZona;
    }
    /**
     * Establece el nombre zona.
     *
     * @param nombreZona el new nombre zona
     */
    public void setNombreZona(String nombreZona) {
        this.nombreZona = nombreZona;
    }
    /**
     * Obtiene nombre tipo via.
     *
     * @return nombre tipo via
     */
     public String getNombreTipoVia() {
        return this.nombreTipoVia;
    }
    /**
     * Establece el nombre tipo via.
     *
     * @param nombreTipoVia el new nombre tipo via
     */
    public void setNombreTipoVia(String nombreTipoVia) {
        this.nombreTipoVia = nombreTipoVia;
    }
    /**
     * Obtiene codigo.
     *
     * @return codigo
     */
     public String getCodigo() {
        return this.codigo;
    }
    /**
     * Establece el codigo.
     *
     * @param codigo el new codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    /**
     * Obtiene codigo externo.
     *
     * @return codigo externo
     */
     public String getCodigoExterno() {
        return this.codigoExterno;
    }
    /**
     * Establece el codigo externo.
     *
     * @param codigoExterno el new codigo externo
     */
    public void setCodigoExterno(String codigoExterno) {
        this.codigoExterno = codigoExterno;
    }
    /**
     * Obtiene codigo referencia.
     *
     * @return codigo referencia
     */
     public String getCodigoReferencia() {
        return this.codigoReferencia;
    }
    /**
     * Establece el codigo referencia.
     *
     * @param codigoReferencia el new codigo referencia
     */
    public void setCodigoReferencia(String codigoReferencia) {
        this.codigoReferencia = codigoReferencia;
    }
    /**
     * Obtiene nombre.
     *
     * @return nombre
     */
     public String getNombre() {
        return this.nombre;
    }
    /**
     * Establece el nombre.
     *
     * @param nombre el new nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Obtiene direccion.
     *
     * @return direccion
     */
     public String getDireccion() {
        return this.direccion;
    }
    /**
     * Establece el direccion.
     *
     * @param direccion el new direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    /**
     * Obtiene telefono.
     *
     * @return telefono
     */
     public String getTelefono() {
        return this.telefono;
    }
    /**
     * Establece el telefono.
     *
     * @param telefono el new telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    /**
     * Obtiene email.
     *
     * @return email
     */
     public String getEmail() {
        return this.email;
    }
    /**
     * Establece el email.
     *
     * @param email el new email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Obtiene web.
     *
     * @return web
     */
     public String getWeb() {
        return this.web;
    }
    /**
     * Establece el web.
     *
     * @param web el new web
     */
    public void setWeb(String web) {
        this.web = web;
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
     * Establece el entidad tipo doc sunat entidad list.
     *
     * @param entidadTipoDocSunatEntidadList el new entidad tipo doc sunat entidad list
     */
    public List<TipoDocSunatEntidadDTO> getEntidadTipoDocSunatEntidadList(){
        return this.entidadTipoDocSunatEntidadList;
    }
    /**
     * Establece el entidad list.
     *
     * @param entidadList el new entidad list
     */
    public void setEntidadTipoDocSunatEntidadList(List<TipoDocSunatEntidadDTO> entidadTipoDocSunatEntidadList) {
        this.entidadTipoDocSunatEntidadList = entidadTipoDocSunatEntidadList;
    }   
    /**
     * Establece el entidad plan contable list.
     *
     * @param entidadPlanContableList el new entidad plan contable list
     */
    public List<PlanContableDTO> getEntidadPlanContableList(){
        return this.entidadPlanContableList;
    }
    /**
     * Establece el entidad list.
     *
     * @param entidadList el new entidad list
     */
    public void setEntidadPlanContableList(List<PlanContableDTO> entidadPlanContableList) {
        this.entidadPlanContableList = entidadPlanContableList;
    }   
    /**
     * Establece el entidad centro costo list.
     *
     * @param entidadCentroCostoList el new entidad centro costo list
     */
    public List<CentroCostoDTO> getEntidadCentroCostoList(){
        return this.entidadCentroCostoList;
    }
    /**
     * Establece el entidad list.
     *
     * @param entidadList el new entidad list
     */
    public void setEntidadCentroCostoList(List<CentroCostoDTO> entidadCentroCostoList) {
        this.entidadCentroCostoList = entidadCentroCostoList;
    }   
    /**
     * Establece el entidad agrupa entidad list.
     *
     * @param entidadAgrupaEntidadList el new entidad agrupa entidad list
     */
    public List<AgrupaEntidadDTO> getEntidadAgrupaEntidadList(){
        return this.entidadAgrupaEntidadList;
    }
    /**
     * Establece el entidad list.
     *
     * @param entidadList el new entidad list
     */
    public void setEntidadAgrupaEntidadList(List<AgrupaEntidadDTO> entidadAgrupaEntidadList) {
        this.entidadAgrupaEntidadList = entidadAgrupaEntidadList;
    }   
    /**
     * Establece el entidad agrupa agrupa entidad list.
     *
     * @param entidadAgrupaAgrupaEntidadList el new entidad agrupa agrupa entidad list
     */
    public List<AgrupaEntidadDTO> getEntidadAgrupaAgrupaEntidadList(){
        return this.entidadAgrupaAgrupaEntidadList;
    }
    /**
     * Establece el entidad agrupa list.
     *
     * @param entidadAgrupaList el new entidad agrupa list
     */
    public void setEntidadAgrupaAgrupaEntidadList(List<AgrupaEntidadDTO> entidadAgrupaAgrupaEntidadList) {
        this.entidadAgrupaAgrupaEntidadList = entidadAgrupaAgrupaEntidadList;
    }   
    /**
     * Establece el entidad usuario entidad list.
     *
     * @param entidadUsuarioEntidadList el new entidad usuario entidad list
     */
    public List<UsuarioEntidadDTO> getEntidadUsuarioEntidadList(){
        return this.entidadUsuarioEntidadList;
    }
    /**
     * Establece el entidad list.
     *
     * @param entidadList el new entidad list
     */
    public void setEntidadUsuarioEntidadList(List<UsuarioEntidadDTO> entidadUsuarioEntidadList) {
        this.entidadUsuarioEntidadList = entidadUsuarioEntidadList;
    }   
    /**
     * Establece el id entidad parametro list.
     *
     * @param idEntidadParametroList el new id entidad parametro list
     */
    public List<ParametroDTO> getIdEntidadParametroList(){
        return this.idEntidadParametroList;
    }
    /**
     * Establece el id entidad list.
     *
     * @param idEntidadList el new id entidad list
     */
    public void setIdEntidadParametroList(List<ParametroDTO> idEntidadParametroList) {
        this.idEntidadParametroList = idEntidadParametroList;
    }   
    /**
     * Establece el entidad linea list.
     *
     * @param entidadLineaList el new entidad linea list
     */
    public List<LineaDTO> getEntidadLineaList(){
        return this.entidadLineaList;
    }
    /**
     * Establece el entidad list.
     *
     * @param entidadList el new entidad list
     */
    public void setEntidadLineaList(List<LineaDTO> entidadLineaList) {
        this.entidadLineaList = entidadLineaList;
    }   
    /**
     * Establece el entidad categoria list.
     *
     * @param entidadCategoriaList el new entidad categoria list
     */
    public List<CategoriaDTO> getEntidadCategoriaList(){
        return this.entidadCategoriaList;
    }
    /**
     * Establece el entidad list.
     *
     * @param entidadList el new entidad list
     */
    public void setEntidadCategoriaList(List<CategoriaDTO> entidadCategoriaList) {
        this.entidadCategoriaList = entidadCategoriaList;
    }   
    /**
     * Establece el entidad marca list.
     *
     * @param entidadMarcaList el new entidad marca list
     */
    public List<MarcaDTO> getEntidadMarcaList(){
        return this.entidadMarcaList;
    }
    /**
     * Establece el entidad list.
     *
     * @param entidadList el new entidad list
     */
    public void setEntidadMarcaList(List<MarcaDTO> entidadMarcaList) {
        this.entidadMarcaList = entidadMarcaList;
    }   
    /**
     * Establece el entidad modelo list.
     *
     * @param entidadModeloList el new entidad modelo list
     */
    public List<ModeloDTO> getEntidadModeloList(){
        return this.entidadModeloList;
    }
    /**
     * Establece el entidad list.
     *
     * @param entidadList el new entidad list
     */
    public void setEntidadModeloList(List<ModeloDTO> entidadModeloList) {
        this.entidadModeloList = entidadModeloList;
    }   
    /**
     * Establece el entidad producto list.
     *
     * @param entidadProductoList el new entidad producto list
     */
    public List<ProductoDTO> getEntidadProductoList(){
        return this.entidadProductoList;
    }
    /**
     * Establece el entidad list.
     *
     * @param entidadList el new entidad list
     */
    public void setEntidadProductoList(List<ProductoDTO> entidadProductoList) {
        this.entidadProductoList = entidadProductoList;
    }   
    /**
     * Establece el entidad pedido list.
     *
     * @param entidadPedidoList el new entidad pedido list
     */
    public List<PedidoDTO> getEntidadPedidoList(){
        return this.entidadPedidoList;
    }
    /**
     * Establece el entidad list.
     *
     * @param entidadList el new entidad list
     */
    public void setEntidadPedidoList(List<PedidoDTO> entidadPedidoList) {
        this.entidadPedidoList = entidadPedidoList;
    }   
    /**
     * Establece el entidad proforma list.
     *
     * @param entidadProformaList el new entidad proforma list
     */
    public List<ProformaDTO> getEntidadProformaList(){
        return this.entidadProformaList;
    }
    /**
     * Establece el entidad list.
     *
     * @param entidadList el new entidad list
     */
    public void setEntidadProformaList(List<ProformaDTO> entidadProformaList) {
        this.entidadProformaList = entidadProformaList;
    }   
    /**
     * Establece el entidad venta list.
     *
     * @param entidadVentaList el new entidad venta list
     */
    public List<VentaDTO> getEntidadVentaList(){
        return this.entidadVentaList;
    }
    /**
     * Establece el entidad list.
     *
     * @param entidadList el new entidad list
     */
    public void setEntidadVentaList(List<VentaDTO> entidadVentaList) {
        this.entidadVentaList = entidadVentaList;
    }   
    /**
     * Establece el entidad orden compra list.
     *
     * @param entidadOrdenCompraList el new entidad orden compra list
     */
    public List<OrdenCompraDTO> getEntidadOrdenCompraList(){
        return this.entidadOrdenCompraList;
    }
    /**
     * Establece el entidad list.
     *
     * @param entidadList el new entidad list
     */
    public void setEntidadOrdenCompraList(List<OrdenCompraDTO> entidadOrdenCompraList) {
        this.entidadOrdenCompraList = entidadOrdenCompraList;
    }   
    /**
     * Establece el entidad compra list.
     *
     * @param entidadCompraList el new entidad compra list
     */
    public List<CompraDTO> getEntidadCompraList(){
        return this.entidadCompraList;
    }
    /**
     * Establece el entidad list.
     *
     * @param entidadList el new entidad list
     */
    public void setEntidadCompraList(List<CompraDTO> entidadCompraList) {
        this.entidadCompraList = entidadCompraList;
    }   
    /**
     * Establece el entidad cliente list.
     *
     * @param entidadClienteList el new entidad cliente list
     */
    public List<ClienteDTO> getEntidadClienteList(){
        return this.entidadClienteList;
    }
    /**
     * Establece el entidad list.
     *
     * @param entidadList el new entidad list
     */
    public void setEntidadClienteList(List<ClienteDTO> entidadClienteList) {
        this.entidadClienteList = entidadClienteList;
    }   
    /**
     * Establece el entidad proveedor list.
     *
     * @param entidadProveedorList el new entidad proveedor list
     */
    public List<ProveedorDTO> getEntidadProveedorList(){
        return this.entidadProveedorList;
    }
    /**
     * Establece el entidad list.
     *
     * @param entidadList el new entidad list
     */
    public void setEntidadProveedorList(List<ProveedorDTO> entidadProveedorList) {
        this.entidadProveedorList = entidadProveedorList;
    }   
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idEntidad == null) ? 0 : idEntidad.hashCode());
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
        EntidadDTO other = (EntidadDTO) obj;
        if (idEntidad == null) {
            if (other.idEntidad != null) {
                return false;
            }
        } else if (!idEntidad.equals(other.idEntidad)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "EntidadDTO [idEntidad=" + idEntidad + "]";
    }
   
}