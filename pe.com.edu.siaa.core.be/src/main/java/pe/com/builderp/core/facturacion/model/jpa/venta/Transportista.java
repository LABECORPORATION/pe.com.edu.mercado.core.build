package pe.com.builderp.core.facturacion.model.jpa.venta;

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
import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class Transportista.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu May 02 09:26:24 COT 2019
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "Transportista", schema = ConfiguracionEntityManagerUtil.ESQUEMA_FACTURACION)
public class Transportista implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id transportista conductor. */
    @Id
    @Column(name = "idTransportistaConductor" , length = 32)
    private String idTransportistaConductor;
   
    /** El tipo transportista conductor. */
    @Column(name = "tipoTransportistaConductor" , length = 1)
    private String tipoTransportistaConductor;
   
    /** El nombre. */
    @Column(name = "nombre" , length = 100)
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
    @Column(name = "email" , length = 20)
    private String email;
   
    /** El celular. */
    @Column(name = "celular" , length = 20)
    private String celular;
   
    /** El direccion. */
    @Column(name = "direccion" , length = 200)
    private String direccion;
   
    /** El flag licencia. */
    @Column(name = "flagLicencia" , length = 1)
    private String flagLicencia;
   
    /** El tipo licencia. */
    @Column(name = "tipoLicencia" , length = 50)
    private String tipoLicencia;
   
    /** El nro licencia. */
    @Column(name = "nroLicencia" , length = 50)
    private String nroLicencia;
   
    /** El clase licencia. */
    @Column(name = "claseLicencia" , length = 50)
    private String claseLicencia;
   
    /** El categoria licencia. */
    @Column(name = "categoriaLicencia" , length = 50)
    private String categoriaLicencia;
    
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
    
    /** La foto. */
    @Column(name = "foto" , length = 50)
    private String foto;
   
    /** El transportista conductor guia remision list. */
    @OneToMany(mappedBy = "transportistaConductor", fetch = FetchType.LAZY)
    private List<GuiaRemision> transportistaConductorGuiaRemisionList = new ArrayList<GuiaRemision>();
    
    /**
     * Instancia un nuevo transportista.
     */
    public Transportista() {
    }
   
   
    /**
     * Instancia un nuevo transportista.
     *
     * @param idTransportistaConductor el id transportista conductor
     * @param tipoTransportistaConductor el tipo transportista conductor
     * @param nombre el nombre
     * @param apellidoPaterno el apellido paterno
     * @param apellidoMaterno el apellido materno
     * @param itemByTipoDocumentoIdentidad el item by tipo documento identidad
     * @param nroDoc el nro doc
     * @param email el email
     * @param celular el celular
     * @param direccion el direccion
     * @param flagLicencia el flag licencia
     * @param tipoLicencia el tipo licencia
     * @param nroLicencia el nro licencia
     * @param claseLicencia el clase licencia
     * @param categoriaLicencia el categoria licencia
     */
    public Transportista(String foto,String estado,String idTransportistaConductor, String tipoTransportistaConductor, String nombre, String apellidoPaterno, String apellidoMaterno, Item itemByTipoDocumentoIdentidad,String nroDoc, String email, String celular, String direccion, String flagLicencia, String tipoLicencia, String nroLicencia, String claseLicencia, String categoriaLicencia ) {
        super();
        this.idTransportistaConductor = idTransportistaConductor;
        this.tipoTransportistaConductor = tipoTransportistaConductor;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.itemByTipoDocumentoIdentidad = itemByTipoDocumentoIdentidad;
        this.nroDoc = nroDoc;
        this.email = email;
        this.celular = celular;
        this.direccion = direccion;
        this.flagLicencia = flagLicencia;
        this.tipoLicencia = tipoLicencia;
        this.nroLicencia = nroLicencia;
        this.claseLicencia = claseLicencia;
        this.categoriaLicencia = categoriaLicencia;
        this.estado = estado;
        this.foto = foto;
    }
   
    //get y set
    /**
     * Obtiene id transportista conductor.
     *
     * @return id transportista conductor
     */
     public String getIdTransportistaConductor() {
        return this.idTransportistaConductor;
    }
    /**
     * Establece el id transportista conductor.
     *
     * @param idTransportistaConductor el new id transportista conductor
     */
    public void setIdTransportistaConductor(String idTransportistaConductor) {
        this.idTransportistaConductor = idTransportistaConductor;
    }
    /**
     * Obtiene tipo transportista conductor.
     *
     * @return tipo transportista conductor
     */
     public String getTipoTransportistaConductor() {
        return this.tipoTransportistaConductor;
    }
    /**
     * Establece el tipo transportista conductor.
     *
     * @param tipoTransportistaConductor el new tipo transportista conductor
     */
    public void setTipoTransportistaConductor(String tipoTransportistaConductor) {
        this.tipoTransportistaConductor = tipoTransportistaConductor;
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
    
    
    public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getFoto() {
		return foto;
	}


	public void setFoto(String foto) {
		this.foto = foto;
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
     * Obtiene flag licencia.
     *
     * @return flag licencia
     */
     public String getFlagLicencia() {
        return this.flagLicencia;
    }
    /**
     * Establece el flag licencia.
     *
     * @param flagLicencia el new flag licencia
     */
    public void setFlagLicencia(String flagLicencia) {
        this.flagLicencia = flagLicencia;
    }
    /**
     * Obtiene tipo licencia.
     *
     * @return tipo licencia
     */
     public String getTipoLicencia() {
        return this.tipoLicencia;
    }
    /**
     * Establece el tipo licencia.
     *
     * @param tipoLicencia el new tipo licencia
     */
    public void setTipoLicencia(String tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }
    /**
     * Obtiene nro licencia.
     *
     * @return nro licencia
     */
     public String getNroLicencia() {
        return this.nroLicencia;
    }
    /**
     * Establece el nro licencia.
     *
     * @param nroLicencia el new nro licencia
     */
    public void setNroLicencia(String nroLicencia) {
        this.nroLicencia = nroLicencia;
    }
    /**
     * Obtiene clase licencia.
     *
     * @return clase licencia
     */
     public String getClaseLicencia() {
        return this.claseLicencia;
    }
    /**
     * Establece el clase licencia.
     *
     * @param claseLicencia el new clase licencia
     */
    public void setClaseLicencia(String claseLicencia) {
        this.claseLicencia = claseLicencia;
    }
    /**
     * Obtiene categoria licencia.
     *
     * @return categoria licencia
     */
     public String getCategoriaLicencia() {
        return this.categoriaLicencia;
    }
    /**
     * Establece el categoria licencia.
     *
     * @param categoriaLicencia el new categoria licencia
     */
    public void setCategoriaLicencia(String categoriaLicencia) {
        this.categoriaLicencia = categoriaLicencia;
    }
    /**
     * Establece el transportista conductor guia remision list.
     *
     * @param transportistaConductorGuiaRemisionList el new transportista conductor guia remision list
     */
    public List<GuiaRemision> getTransportistaConductorGuiaRemisionList(){
        return this.transportistaConductorGuiaRemisionList;
    }
    /**
     * Establece el transportista conductor list.
     *
     * @param transportistaConductorList el new transportista conductor list
     */
    public void setTransportistaConductorGuiaRemisionList(List<GuiaRemision> transportistaConductorGuiaRemisionList) {
        this.transportistaConductorGuiaRemisionList = transportistaConductorGuiaRemisionList;
    }   
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idTransportistaConductor == null) ? 0 : idTransportistaConductor.hashCode());
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
        Transportista other = (Transportista) obj;
        if (idTransportistaConductor == null) {
            if (other.idTransportistaConductor != null) {
                return false;
            }
        } else if (!idTransportistaConductor.equals(other.idTransportistaConductor)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Transportista [idTransportistaConductor=" + idTransportistaConductor + "]";
    }
   
}