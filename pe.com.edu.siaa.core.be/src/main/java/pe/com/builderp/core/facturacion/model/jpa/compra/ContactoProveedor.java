package pe.com.builderp.core.facturacion.model.jpa.compra;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pe.com.edu.siaa.core.model.jpa.common.Item;
import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class ContactoProveedor.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Wed Dec 20 00:30:17 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "ContactoProveedor", schema = ConfiguracionEntityManagerUtil.ESQUEMA_FACTURACION)
public class ContactoProveedor implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id contacto proveedor. */
    @Id
    @Column(name = "idContactoProveedor" , length = 32)
    private String idContactoProveedor;
   
    /** El proveedor. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProveedor", referencedColumnName = "idProveedor")
    private Proveedor proveedor;
   
    /** El codigo. */
    @Column(name = "codigo" , length = 20)
    private String codigo;
   
    /** El apellido paterno. */
    @Column(name = "apellidoPaterno" , length = 150)
    private String apellidoPaterno;
   
    /** El apellido materno. */
    @Column(name = "apellidoMaterno" , length = 150)
    private String apellidoMaterno;
   
    /** El nombres. */
    @Column(name = "nombres" , length = 150)
    private String nombres;
   
    /** El item by contacto. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoContacto", referencedColumnName = "idItem")
    private Item itemByContacto;
   
    /** El direccion. */
    @Column(name = "direccion" , length = 150)
    private String direccion;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
    
    /** El celular. */
    @Column(name = "celular" , length = 10)
    private String celular;
   
   
    /**
     * Instancia un nuevo contacto proveedor.
     */
    public ContactoProveedor() {
    }
   
   
    /**
     * Instancia un nuevo contacto proveedor.
     *
     * @param idContactoProveedor el id contacto proveedor
     * @param proveedor el proveedor
     * @param codigo el codigo
     * @param apellidoPaterno el apellido paterno
     * @param apellidoMaterno el apellido materno
     * @param nombres el nombres
     * @param itemByContacto el item by contacto
     * @param direccion el direccion
     * @param estado el estado
     */
    public ContactoProveedor(String celular,String idContactoProveedor, Proveedor proveedor,String codigo, String apellidoPaterno, String apellidoMaterno, String nombres, Item itemByContacto,String direccion, String estado ) {
        super();
        this.idContactoProveedor = idContactoProveedor;
        this.proveedor = proveedor;
        this.codigo = codigo;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombres = nombres;
        this.itemByContacto = itemByContacto;
        this.direccion = direccion;
        this.estado = estado;
        this.celular = celular;
    }
   
    //get y set
    /**
     * Obtiene id contacto proveedor.
     *
     * @return id contacto proveedor
     */
     public String getIdContactoProveedor() {
        return this.idContactoProveedor;
    }
    /**
     * Establece el id contacto proveedor.
     *
     * @param idContactoProveedor el new id contacto proveedor
     */
    public void setIdContactoProveedor(String idContactoProveedor) {
        this.idContactoProveedor = idContactoProveedor;
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
    
    
    
    
    public String getCelular() {
		return celular;
	}


	public void setCelular(String celular) {
		this.celular = celular;
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
     * Obtiene nombres.
     *
     * @return nombres
     */
     public String getNombres() {
        return this.nombres;
    }
    /**
     * Establece el nombres.
     *
     * @param nombres el new nombres
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    /**
     * Obtiene item by contacto.
     *
     * @return item by contacto
     */
     public Item getItemByContacto() {
        return this.itemByContacto;
    }
    /**
     * Establece el item by contacto.
     *
     * @param itemByContacto el new item by contacto
     */
    public void setItemByContacto(Item itemByContacto) {
        this.itemByContacto = itemByContacto;
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
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idContactoProveedor == null) ? 0 : idContactoProveedor.hashCode());
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
        ContactoProveedor other = (ContactoProveedor) obj;
        if (idContactoProveedor == null) {
            if (other.idContactoProveedor != null) {
                return false;
            }
        } else if (!idContactoProveedor.equals(other.idContactoProveedor)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ContactoProveedor [idContactoProveedor=" + idContactoProveedor + "]";
    }
   
}