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

import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class Usuario.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Wed Dec 13 22:04:28 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "Usuario", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_SECURIDAD)
public class Usuario implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id usuario. */
    @Id
    @Column(name = "idUsuario" , length = 32)
    private String idUsuario;
   
    /** El nombre. */
    @Column(name = "nombre" , length = 150)
    private String nombre;
   
    /** El apellido paterno. */
    @Column(name = "apellidoPaterno" , length = 150)
    private String apellidoPaterno;
   
    /** El apellido materno. */
    @Column(name = "apellidoMaterno" , length = 150)
    private String apellidoMaterno;
   
    /** El email. */
    @Column(name = "email" , length = 150)
    private String email;
   
    /** El telefono. */
    @Column(name = "telefono" , length = 150)
    private String telefono;
   
    /** El celular. */
    @Column(name = "celular" , length = 150)
    private String celular;
   
    /** El user name. */
    @Column(name = "userName" , length = 50)
    private String userName;
   
    /** El user password. */
    @Column(name = "userPassword" , length = 100)
    private String userPassword;
   
    /** El tipo usuario. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoUsuario", referencedColumnName = "idTipoUsuario")
    private TipoUsuario tipoUsuario;
   
    /** El codigo externo. */
    @Column(name = "codigoExterno" , length = 18)
    private String codigoExterno;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
    
    //agregando
    /** La foto. */
    @Column(name = "foto" , length = 250)
    private String foto;
   
    /** El nro doc. */
    @Column(name = "nroDoc" , length = 20)
    private String nroDoc;
    
    /** El direccion. */
    @Column(name = "direccion" , length = 200)
    private String direccion;
    
    /** El nro doc. */
    @Column(name = "genero" , length = 1)
    private String genero;
    
    /** El fecha pago. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaNacimiento")
    private Date fechaNacimiento;
   
    /**
     * Instancia un nuevo usuario.
     */
    public Usuario() {
    }
   
   
    /**
     * Instancia un nuevo usuario.
     *
     * @param idUsuario el id usuario
     * @param nombre el nombre
     * @param apellidoPaterno el apellido paterno
     * @param apellidoMaterno el apellido materno
     * @param email el email
     * @param telefono el telefono
     * @param celular el celular
     * @param userName el user name
     * @param userPassword el user password
     * @param tipoUsuario el tipo usuario
     * @param codigoExterno el codigo externo
     * @param estado el estado
     */
    public Usuario(String idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno, String email, String telefono, String celular, String userName, String userPassword, TipoUsuario tipoUsuario,String codigoExterno, String estado, String foto, String nroDoc, String direccion, String genero,
			Date fechaNacimiento ) {
        super();
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
        this.telefono = telefono;
        this.celular = celular;
        this.userName = userName;
        this.userPassword = userPassword;
        this.tipoUsuario = tipoUsuario;
        this.codigoExterno = codigoExterno;
        this.estado = estado;
        this.foto = foto;
        this.nroDoc = nroDoc;
		this.direccion = direccion;
		this.genero = genero;
		this.fechaNacimiento = fechaNacimiento;
        
    }
   
    //get y set
    /**
     * Obtiene id usuario.
     *
     * @return id usuario
     */
     public String getIdUsuario() {
        return this.idUsuario;
    }
    /**
     * Establece el id usuario.
     *
     * @param idUsuario el new id usuario
     */
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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
     * Obtiene tipo usuario.
     *
     * @return tipo usuario
     */
     public TipoUsuario getTipoUsuario() {
        return this.tipoUsuario;
    }
    /**
     * Establece el tipo usuario.
     *
     * @param tipoUsuario el new tipo usuario
     */
    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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
   
    //agregando
    public String getFoto() {
		return foto;
	}

  //agregando
	public void setFoto(String foto) {
		this.foto = foto;
	}	
	
	public String getNroDoc() {
		return nroDoc;
	}


	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	/* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idUsuario == null) ? 0 : idUsuario.hashCode());
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
        Usuario other = (Usuario) obj;
        if (idUsuario == null) {
            if (other.idUsuario != null) {
                return false;
            }
        } else if (!idUsuario.equals(other.idUsuario)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Usuario [idUsuario=" + idUsuario + "]";
    }
   
}