package pe.com.builderp.core.facturacion.model.dto.compra;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.EntidadDTO;
 

/**
 * La Class ProveedorDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Fri Dec 22 11:07:00 COT 2017
 * @since SIAA-CORE 2.1
 */
public class ProveedorDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id proveedor. */
    private String idProveedor;
   
    /** El entidad. */
    private EntidadDTO entidad;
   
    /** El tipo proveedor. */
    private String tipoProveedor;
   
    /** El nombre. */
    private String nombre;
   
    /** El apellido paterno. */
    private String apellidoPaterno;
   
    /** El apellido materno. */
    private String apellidoMaterno;
   
    /** El item by tipo documento identidad. */
    private ItemDTO itemByTipoDocumentoIdentidad;
   
    /** El nro doc. */
    private String nroDoc;
   
    /** El email. */
    private String email;
   
    /** El telefono. */
    private String telefono;
   
    /** El celular. */
    private String celular;
   
    /** El pagina web. */
    private String paginaWeb;
   
    /** El direccion. */
    private String direccion;
   
    /** El estado. */
    private String estado;
   
    /** El proveedor orden compra list. */
    private List<OrdenCompraDTO> proveedorOrdenCompraList = new ArrayList<OrdenCompraDTO>();
   
    /** El proveedor compra list. */
    private List<CompraDTO> proveedorCompraList = new ArrayList<CompraDTO>();
   
    /** El proveedor contacto proveedor list. */
    private List<ContactoProveedorDTO> proveedorContactoProveedorList = new ArrayList<ContactoProveedorDTO>();
   
    /** El proveedor cuenta bancaria list. */
    private List<CuentaBancariaDTO> proveedorCuentaBancariaList = new ArrayList<CuentaBancariaDTO>();
   
    /** El proveedor cuenta tipo documento list. */
    private List<CuentaTipoDocumentoDTO> proveedorCuentaTipoDocumentoList = new ArrayList<CuentaTipoDocumentoDTO>();
   
    /**
     * Instancia un nuevo proveedorDTO.
     */
    public ProveedorDTO() {
    }
   
   
    /**
     * Instancia un nuevo proveedorDTO.
     *
     * @param idProveedor el id proveedor
     * @param entidad el entidad
     * @param tipoProveedor el tipo proveedor
     * @param nombre el nombre
     * @param apellidoPaterno el apellido paterno
     * @param apellidoMaterno el apellido materno
     * @param itemByTipoDocumentoIdentidad el item by tipo documento identidad
     * @param nroDoc el nro doc
     * @param email el email
     * @param telefono el telefono
     * @param celular el celular
     * @param paginaWeb el pagina web
     * @param direccion el direccion
     * @param estado el estado
     */
    public ProveedorDTO(String idProveedor, EntidadDTO entidad,String tipoProveedor, String nombre, String apellidoPaterno, String apellidoMaterno, ItemDTO itemByTipoDocumentoIdentidad,String nroDoc, String email, String telefono, String celular, String paginaWeb, String direccion, String estado ) {
        super();
        this.idProveedor = idProveedor;
        this.entidad = entidad;
        this.tipoProveedor = tipoProveedor;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.itemByTipoDocumentoIdentidad = itemByTipoDocumentoIdentidad;
        this.nroDoc = nroDoc;
        this.email = email;
        this.telefono = telefono;
        this.celular = celular;
        this.paginaWeb = paginaWeb;
        this.direccion = direccion;
        this.estado = estado;
    }
   
    //get y set
    /**
     * Obtiene id proveedor.
     *
     * @return id proveedor
     */
     public String getIdProveedor() {
        return this.idProveedor;
    }
    /**
     * Establece el id proveedor.
     *
     * @param idProveedor el new id proveedor
     */
    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
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
     * Obtiene tipo proveedor.
     *
     * @return tipo proveedor
     */
     public String getTipoProveedor() {
        return this.tipoProveedor;
    }
    /**
     * Establece el tipo proveedor.
     *
     * @param tipoProveedor el new tipo proveedor
     */
    public void setTipoProveedor(String tipoProveedor) {
        this.tipoProveedor = tipoProveedor;
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
     * Obtiene apellido paterno.
     *
     * @return apellido paterno
     */
     public String getApellidoPaterno() {
        return this.apellidoPaterno;
    }
    /**
     * Establece el apellido paterno.
     *
     * @param apellidoPaterno el new apellido paterno
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    /**
     * Obtiene apellido materno.
     *
     * @return apellido materno
     */
     public String getApellidoMaterno() {
        return this.apellidoMaterno;
    }
    /**
     * Establece el apellido materno.
     *
     * @param apellidoMaterno el new apellido materno
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    /**
     * Obtiene item by tipo documento identidad.
     *
     * @return item by tipo documento identidad
     */
     public ItemDTO getItemByTipoDocumentoIdentidad() {
        return this.itemByTipoDocumentoIdentidad;
    }
    /**
     * Establece el item by tipo documento identidad.
     *
     * @param itemByTipoDocumentoIdentidad el new item by tipo documento identidad
     */
    public void setItemByTipoDocumentoIdentidad(ItemDTO itemByTipoDocumentoIdentidad) {
        this.itemByTipoDocumentoIdentidad = itemByTipoDocumentoIdentidad;
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
     * Obtiene celular.
     *
     * @return celular
     */
     public String getCelular() {
        return this.celular;
    }
    /**
     * Establece el celular.
     *
     * @param celular el new celular
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }
    /**
     * Obtiene pagina web.
     *
     * @return pagina web
     */
     public String getPaginaWeb() {
        return this.paginaWeb;
    }
    /**
     * Establece el pagina web.
     *
     * @param paginaWeb el new pagina web
     */
    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
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
     * Establece el proveedor orden compra list.
     *
     * @param proveedorOrdenCompraList el new proveedor orden compra list
     */
    public List<OrdenCompraDTO> getProveedorOrdenCompraList(){
        return this.proveedorOrdenCompraList;
    }
    /**
     * Establece el proveedor list.
     *
     * @param proveedorList el new proveedor list
     */
    public void setProveedorOrdenCompraList(List<OrdenCompraDTO> proveedorOrdenCompraList) {
        this.proveedorOrdenCompraList = proveedorOrdenCompraList;
    }   
    /**
     * Establece el proveedor compra list.
     *
     * @param proveedorCompraList el new proveedor compra list
     */
    public List<CompraDTO> getProveedorCompraList(){
        return this.proveedorCompraList;
    }
    /**
     * Establece el proveedor list.
     *
     * @param proveedorList el new proveedor list
     */
    public void setProveedorCompraList(List<CompraDTO> proveedorCompraList) {
        this.proveedorCompraList = proveedorCompraList;
    }   
    /**
     * Establece el proveedor contacto proveedor list.
     *
     * @param proveedorContactoProveedorList el new proveedor contacto proveedor list
     */
    public List<ContactoProveedorDTO> getProveedorContactoProveedorList(){
        return this.proveedorContactoProveedorList;
    }
    /**
     * Establece el proveedor list.
     *
     * @param proveedorList el new proveedor list
     */
    public void setProveedorContactoProveedorList(List<ContactoProveedorDTO> proveedorContactoProveedorList) {
        this.proveedorContactoProveedorList = proveedorContactoProveedorList;
    }   
    /**
     * Establece el proveedor cuenta bancaria list.
     *
     * @param proveedorCuentaBancariaList el new proveedor cuenta bancaria list
     */
    public List<CuentaBancariaDTO> getProveedorCuentaBancariaList(){
        return this.proveedorCuentaBancariaList;
    }
    /**
     * Establece el proveedor list.
     *
     * @param proveedorList el new proveedor list
     */
    public void setProveedorCuentaBancariaList(List<CuentaBancariaDTO> proveedorCuentaBancariaList) {
        this.proveedorCuentaBancariaList = proveedorCuentaBancariaList;
    }   
    /**
     * Establece el proveedor cuenta tipo documento list.
     *
     * @param proveedorCuentaTipoDocumentoList el new proveedor cuenta tipo documento list
     */
    public List<CuentaTipoDocumentoDTO> getProveedorCuentaTipoDocumentoList(){
        return this.proveedorCuentaTipoDocumentoList;
    }
    /**
     * Establece el proveedor list.
     *
     * @param proveedorList el new proveedor list
     */
    public void setProveedorCuentaTipoDocumentoList(List<CuentaTipoDocumentoDTO> proveedorCuentaTipoDocumentoList) {
        this.proveedorCuentaTipoDocumentoList = proveedorCuentaTipoDocumentoList;
    }   
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idProveedor == null) ? 0 : idProveedor.hashCode());
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
        ProveedorDTO other = (ProveedorDTO) obj;
        if (idProveedor == null) {
            if (other.idProveedor != null) {
                return false;
            }
        } else if (!idProveedor.equals(other.idProveedor)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ProveedorDTO [idProveedor=" + idProveedor + "]";
    }
   
}