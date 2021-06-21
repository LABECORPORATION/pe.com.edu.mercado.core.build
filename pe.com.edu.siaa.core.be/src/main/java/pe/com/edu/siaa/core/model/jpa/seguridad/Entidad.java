package pe.com.edu.siaa.core.model.jpa.seguridad;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import pe.com.edu.siaa.core.model.jpa.common.Item;
import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class Entidad.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Wed Dec 13 22:04:25 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "Entidad", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_SECURIDAD)
public class Entidad implements Serializable {
	 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id entidad. */
    @Id
    @Column(name = "idEntidad" , length = 32)
    private String idEntidad;
   
    /** El item by tipo via. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoVia", referencedColumnName = "idItem")
    private Item itemByTipoVia;
   
    /** El item by zona. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idZona", referencedColumnName = "idItem")
    private Item itemByZona;
   
    /** El nombre zona. */
    @Column(name = "nombreZona" , length = 200)
    private String nombreZona;
   
    /** El nombre tipo via. */
    @Column(name = "nombreTipoVia" , length = 200)
    private String nombreTipoVia;
   
    /** El codigo. */
    @Column(name = "codigo" , length = 50)
    private String codigo;
   
    /** El codigo externo. */
    @Column(name = "codigoExterno" , length = 50)
    private String codigoExterno;
   
    /** El codigo referencia. */
    @Column(name = "codigoReferencia" , length = 50)
    private String codigoReferencia;
   
    /** El nombre. */
    @Column(name = "nombre" , length = 200)
    private String nombre;
   
    /** El direccion. */
    @Column(name = "direccion" , length = 150)
    private String direccion;
   
    /** El telefono. */
    @Column(name = "telefono" , length = 50)
    private String telefono;
   
    /** El email. */
    @Column(name = "email" , length = 50)
    private String email;
   
    /** El web. */
    @Column(name = "web" , length = 150)
    private String web;
   
    /** El fecha creacion. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaCreacion")
    private Date fechaCreacion;
   
    /** El usuario creacion. */
    @Column(name = "usuarioCreacion" , length = 50)
    private String usuarioCreacion;
   
    @Column(name = "logo" , length = 50)
    private String logo;
   
    @Column(name = "vaner" , length = 50)
    private String vaner;
    
    @Column(name = "certificadoDigital" , length = 50)
    private String certificadoDigital;
    
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaVenceCertificado")
    private Date fechaVenceCertificado;
    
    /**
     * Instancia un nuevo entidad.
     */
    public Entidad() {
    }
   
   
    /**
     * Instancia un nuevo entidad.
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
    public Entidad(Date fechaVenceCertificado, String certificadoDigital,String vaner, String logo,String idEntidad, Item itemByTipoVia,Item itemByZona,String nombreZona, String nombreTipoVia, String codigo, String codigoExterno, String codigoReferencia, String nombre, String direccion, String telefono, String email, String web, Date fechaCreacion, String usuarioCreacion ) {
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
    /**
     * Obtiene item by tipo via.
     *
     * @return item by tipo via
     */
     public Item getItemByTipoVia() {
        return this.itemByTipoVia;
    }
    /**
     * Establece el item by tipo via.
     *
     * @param itemByTipoVia el new item by tipo via
     */
    public void setItemByTipoVia(Item itemByTipoVia) {
        this.itemByTipoVia = itemByTipoVia;
    }
    
    
    
    
    public Date getFechaVenceCertificado() {
		return fechaVenceCertificado;
	}


	public void setFechaVenceCertificado(Date fechaVenceCertificado) {
		this.fechaVenceCertificado = fechaVenceCertificado;
	}


	public String getLogo() {
		return logo;
	}


	public void setLogo(String logo) {
		this.logo = logo;
	}


	public String getVaner() {
		return vaner;
	}


	public void setVaner(String vaner) {
		this.vaner = vaner;
	}


	public String getCertificadoDigital() {
		return certificadoDigital;
	}


	public void setCertificadoDigital(String certificadoDigital) {
		this.certificadoDigital = certificadoDigital;
	}


	/**
     * Obtiene item by zona.
     *
     * @return item by zona
     */
     public Item getItemByZona() {
        return this.itemByZona;
    }
    /**
     * Establece el item by zona.
     *
     * @param itemByZona el new item by zona
     */
    public void setItemByZona(Item itemByZona) {
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
        Entidad other = (Entidad) obj;
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
        return "Entidad [idEntidad=" + idEntidad + "]";
    }
   
}