package pe.com.builderp.core.facturacion.model.jpa.compra;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import pe.com.edu.siaa.core.model.jpa.common.Item;
import pe.com.edu.siaa.core.model.jpa.seguridad.Entidad;
import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class Proveedor.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Wed Dec 20 00:30:18 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "Proveedor", schema = ConfiguracionEntityManagerUtil.ESQUEMA_FACTURACION)
public class Proveedor implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id proveedor. */
    @Id
    @Column(name = "idProveedor" , length = 32)
    private String idProveedor;
   
    /** El entidad. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEntidad", referencedColumnName = "idEntidad")
    private Entidad entidad;
   
    /** El tipo proveedor. */
    @Column(name = "idTipoProveedor" , length = 1)
    private String tipoProveedor;
   
    /** El nombre. */
    @Column(name = "nombre" , length = 200)
    private String nombre;
   
    /** El apellido paterno. */
    @Column(name = "apellidoPaterno" , length = 100)
    private String apellidoPaterno;
   
    /** El apellido materno. */
    @Column(name = "apellidoMaterno" , length = 100)
    private String apellidoMaterno;
   
    /** El item by tipo documento identidad. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoDocumentoIdentidad", referencedColumnName = "idItem")
    private Item itemByTipoDocumentoIdentidad;
   
    /** El nro doc. */
    @Column(name = "nroDoc" , length = 20)
    private String nroDoc;
   
    /** El email. */
    @Column(name = "email" , length = 80)
    private String email;
   
    /** El telefono. */
    @Column(name = "telefono" , length = 20)
    private String telefono;
   
    /** El celular. */
    @Column(name = "celular" , length = 20)
    private String celular;
   
    /** El pagina web. */
    @Column(name = "paginaWeb" , length = 80)
    private String paginaWeb;
   
    /** El direccion. */
    @Column(name = "direccion" , length = 200)
    private String direccion;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
   
    /** El proveedor orden compra list. */
    @OneToMany(mappedBy = "proveedor", fetch = FetchType.LAZY)
    private List<OrdenCompra> proveedorOrdenCompraList = new ArrayList<OrdenCompra>();
    
    /** El proveedor compra list. */
    @OneToMany(mappedBy = "proveedor", fetch = FetchType.LAZY)
    private List<Compra> proveedorCompraList = new ArrayList<Compra>();
    
    /** El proveedor contacto proveedor list. */
    @OneToMany(mappedBy = "proveedor", fetch = FetchType.LAZY)
    private List<ContactoProveedor> proveedorContactoProveedorList = new ArrayList<ContactoProveedor>();
    
    /** El proveedor cuenta bancaria list. */
    @OneToMany(mappedBy = "proveedor", fetch = FetchType.LAZY)
    private List<CuentaBancaria> proveedorCuentaBancariaList = new ArrayList<CuentaBancaria>();
    
    /** El proveedor cuenta tipo documento list. */
    @OneToMany(mappedBy = "proveedor", fetch = FetchType.LAZY)
    private List<CuentaTipoDocumento> proveedorCuentaTipoDocumentoList = new ArrayList<CuentaTipoDocumento>();
    
    /**
     * Instancia un nuevo proveedor.
     */
    public Proveedor() {
    }
   
   
    /**
     * Instancia un nuevo proveedor.
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
    public Proveedor(String idProveedor, Entidad entidad,String tipoProveedor, String nombre, String apellidoPaterno, String apellidoMaterno, Item itemByTipoDocumentoIdentidad,String nroDoc, String email, String telefono, String celular, String paginaWeb, String direccion, String estado ) {
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
     public Item getItemByTipoDocumentoIdentidad() {
        return this.itemByTipoDocumentoIdentidad;
    }
    /**
     * Establece el item by tipo documento identidad.
     *
     * @param itemByTipoDocumentoIdentidad el new item by tipo documento identidad
     */
    public void setItemByTipoDocumentoIdentidad(Item itemByTipoDocumentoIdentidad) {
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
    public List<OrdenCompra> getProveedorOrdenCompraList(){
        return this.proveedorOrdenCompraList;
    }
    /**
     * Establece el proveedor list.
     *
     * @param proveedorList el new proveedor list
     */
    public void setProveedorOrdenCompraList(List<OrdenCompra> proveedorOrdenCompraList) {
        this.proveedorOrdenCompraList = proveedorOrdenCompraList;
    }   
    /**
     * Establece el proveedor compra list.
     *
     * @param proveedorCompraList el new proveedor compra list
     */
    public List<Compra> getProveedorCompraList(){
        return this.proveedorCompraList;
    }
    /**
     * Establece el proveedor list.
     *
     * @param proveedorList el new proveedor list
     */
    public void setProveedorCompraList(List<Compra> proveedorCompraList) {
        this.proveedorCompraList = proveedorCompraList;
    }   
    /**
     * Establece el proveedor contacto proveedor list.
     *
     * @param proveedorContactoProveedorList el new proveedor contacto proveedor list
     */
    public List<ContactoProveedor> getProveedorContactoProveedorList(){
        return this.proveedorContactoProveedorList;
    }
    /**
     * Establece el proveedor list.
     *
     * @param proveedorList el new proveedor list
     */
    public void setProveedorContactoProveedorList(List<ContactoProveedor> proveedorContactoProveedorList) {
        this.proveedorContactoProveedorList = proveedorContactoProveedorList;
    }   
    /**
     * Establece el proveedor cuenta bancaria list.
     *
     * @param proveedorCuentaBancariaList el new proveedor cuenta bancaria list
     */
    public List<CuentaBancaria> getProveedorCuentaBancariaList(){
        return this.proveedorCuentaBancariaList;
    }
    /**
     * Establece el proveedor list.
     *
     * @param proveedorList el new proveedor list
     */
    public void setProveedorCuentaBancariaList(List<CuentaBancaria> proveedorCuentaBancariaList) {
        this.proveedorCuentaBancariaList = proveedorCuentaBancariaList;
    }   
    /**
     * Establece el proveedor cuenta tipo documento list.
     *
     * @param proveedorCuentaTipoDocumentoList el new proveedor cuenta tipo documento list
     */
    public List<CuentaTipoDocumento> getProveedorCuentaTipoDocumentoList(){
        return this.proveedorCuentaTipoDocumentoList;
    }
    /**
     * Establece el proveedor list.
     *
     * @param proveedorList el new proveedor list
     */
    public void setProveedorCuentaTipoDocumentoList(List<CuentaTipoDocumento> proveedorCuentaTipoDocumentoList) {
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
        Proveedor other = (Proveedor) obj;
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
        return "Proveedor [idProveedor=" + idProveedor + "]";
    }
   
}