package pe.com.builderp.core.facturacion.model.dto.venta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.EntidadDTO;

/**
 * La Class ClienteDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Fri Dec 22 11:07:00 COT 2017
 * @since SIAA-CORE 2.1
 */
public class ClienteDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id cliente. */
    private String idCliente;
   
    /** El entidad. */
    private EntidadDTO entidad;
   
    /** El tipo cliente. */
    private String tipoCliente;
   
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
   
    /** El user name. */
    private String userName;
   
    /** El user password. */
    private String userPassword;
   
    /** El limite creito. */
    private BigDecimal limiteCreito;
   
    /** El item by categoria cliente. */
    private ItemDTO itemByCategoriaCliente;
   
    /** El estado. */
    private String estado;  
   
    /** El cliente pedido list. */
    private List<PedidoDTO> clientePedidoList = new ArrayList<PedidoDTO>();
   
    /** El cliente proforma list. */
    private List<ProformaDTO> clienteProformaList = new ArrayList<ProformaDTO>();
   
    /** El cliente venta list. */
    private List<VentaDTO> clienteVentaList = new ArrayList<VentaDTO>();
   
    /**
     * Instancia un nuevo clienteDTO.
     */
    public ClienteDTO() {
    }
   
   
    /**
     * Instancia un nuevo clienteDTO.
     *
     * @param idCliente el id cliente
     * @param entidad el entidad
     * @param tipoCliente el tipo cliente
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
     * @param userName el user name
     * @param userPassword el user password
     * @param limiteCreito el limite creito
     * @param itemByCategoriaCliente el item by categoria cliente
     * @param estado el estado
     */
    public ClienteDTO(String idCliente, EntidadDTO entidad,String tipoCliente, String nombre, String apellidoPaterno, String apellidoMaterno, ItemDTO itemByTipoDocumentoIdentidad,String nroDoc, String email, String telefono, String celular, String paginaWeb, String direccion, String userName, String userPassword, BigDecimal limiteCreito, ItemDTO itemByCategoriaCliente,String estado ) {
        super();
        this.idCliente = idCliente;
        this.entidad = entidad;
        this.tipoCliente = tipoCliente;
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
        this.userName = userName;
        this.userPassword = userPassword;
        this.limiteCreito = limiteCreito;
        this.itemByCategoriaCliente = itemByCategoriaCliente;
        this.estado = estado;
    }
   
    //get y set
    /**
     * Obtiene id cliente.
     *
     * @return id cliente
     */
     public String getIdCliente() {
        return this.idCliente;
    }
    /**
     * Establece el id cliente.
     *
     * @param idCliente el new id cliente
     */
    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
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
     * Obtiene tipo cliente.
     *
     * @return tipo cliente
     */
     public String getTipoCliente() {
        return this.tipoCliente;
    }
    /**
     * Establece el tipo cliente.
     *
     * @param tipoCliente el new tipo cliente
     */
    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
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
     * Obtiene user name.
     *
     * @return user name
     */
     public String getUserName() {
        return this.userName;
    }
    /**
     * Establece el user name.
     *
     * @param userName el new user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * Obtiene user password.
     *
     * @return user password
     */
     public String getUserPassword() {
        return this.userPassword;
    }
    /**
     * Establece el user password.
     *
     * @param userPassword el new user password
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    /**
     * Obtiene limite creito.
     *
     * @return limite creito
     */
     public BigDecimal getLimiteCreito() {
        return this.limiteCreito;
    }
    /**
     * Establece el limite creito.
     *
     * @param limiteCreito el new limite creito
     */
    public void setLimiteCreito(BigDecimal limiteCreito) {
        this.limiteCreito = limiteCreito;
    }
    /**
     * Obtiene item by categoria cliente.
     *
     * @return item by categoria cliente
     */
     public ItemDTO getItemByCategoriaCliente() {
        return this.itemByCategoriaCliente;
    }
    /**
     * Establece el item by categoria cliente.
     *
     * @param itemByCategoriaCliente el new item by categoria cliente
     */
    public void setItemByCategoriaCliente(ItemDTO itemByCategoriaCliente) {
        this.itemByCategoriaCliente = itemByCategoriaCliente;
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
     * Establece el cliente pedido list.
     *
     * @param clientePedidoList el new cliente pedido list
     */
    public List<PedidoDTO> getClientePedidoList(){
        return this.clientePedidoList;
    }
    /**
     * Establece el cliente list.
     *
     * @param clienteList el new cliente list
     */
    public void setClientePedidoList(List<PedidoDTO> clientePedidoList) {
        this.clientePedidoList = clientePedidoList;
    }   
    /**
     * Establece el cliente proforma list.
     *
     * @param clienteProformaList el new cliente proforma list
     */
    public List<ProformaDTO> getClienteProformaList(){
        return this.clienteProformaList;
    }
    /**
     * Establece el cliente list.
     *
     * @param clienteList el new cliente list
     */
    public void setClienteProformaList(List<ProformaDTO> clienteProformaList) {
        this.clienteProformaList = clienteProformaList;
    }   
    /**
     * Establece el cliente venta list.
     *
     * @param clienteVentaList el new cliente venta list
     */
    public List<VentaDTO> getClienteVentaList(){
        return this.clienteVentaList;
    }
    /**
     * Establece el cliente list.
     *
     * @param clienteList el new cliente list
     */
    public void setClienteVentaList(List<VentaDTO> clienteVentaList) {
        this.clienteVentaList = clienteVentaList;
    }   
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idCliente == null) ? 0 : idCliente.hashCode());
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
        ClienteDTO other = (ClienteDTO) obj;
        if (idCliente == null) {
            if (other.idCliente != null) {
                return false;
            }
        } else if (!idCliente.equals(other.idCliente)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ClienteDTO [idCliente=" + idCliente + "]";
    }
   
}