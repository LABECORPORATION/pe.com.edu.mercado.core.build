package pe.com.edu.siaa.core.model.jpa.seguridad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class Sistema.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Wed Dec 13 22:04:27 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "Sistema", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_SECURIDAD)
public class Sistema implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id sistema. */
    @Id
    @Column(name = "idSistema" , precision = 18 , scale = 0)
    private Long idSistema;
   
    /** El nombre. */
    @Column(name = "nombre" , length = 50)
    private String nombre;
   
    /** El descripcion. */
    @Column(name = "descripcion" , length = 100)
    private String descripcion;
   
    /** El abreviatura. */
    @Column(name = "abreviatura" , length = 50)
    private String abreviatura;
   
    /** El fecha. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fecha")
    private Date fecha;
   
    /** El version. */
    @Column(name = "version" , length = 50)
    private String version;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
   
    /** El icono. */
    @Column(name = "icono" , length = 50)
    private String icono;
   
    @Column(name = "orden" , precision = 18 , scale = 0)
    private Long orden;
    
    /** El sistema menu list. */
    @OneToMany(mappedBy = "sistema", fetch = FetchType.LAZY)
    private List<Menu> sistemaMenuList = new ArrayList<Menu>();
    
    /**
     * Instancia un nuevo sistema.
     */
    public Sistema() {
    }
   
   
    /**
     * Instancia un nuevo sistema.
     *
     * @param idSistema el id sistema
     * @param nombre el nombre
     * @param descripcion el descripcion
     * @param abreviatura el abreviatura
     * @param fecha el fecha
     * @param version el version
     * @param estado el estado
     * @param icono el icono
     */
    public Sistema(Long idSistema, String nombre, String descripcion, String abreviatura, Date fecha, String version, String estado, String icono ,Long orden) {
        super();
        this.idSistema = idSistema;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
        this.fecha = fecha;
        this.version = version;
        this.estado = estado;
        this.icono = icono;
        this.orden=orden;
    }
   
    //get y set
    
    /**
     * Obtiene id sistema.
     *
     * @return id sistema
     */
     public Long getIdSistema() {
        return this.idSistema;
    }
    public Long getOrden() {
		return orden;
	}


	public void setOrden(Long orden) {
		this.orden = orden;
	}


	/**
     * Establece el id sistema.
     *
     * @param idSistema el new id sistema
     */
    public void setIdSistema(Long idSistema) {
        this.idSistema = idSistema;
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
     * Obtiene descripcion.
     *
     * @return descripcion
     */
     public String getDescripcion() {
        return this.descripcion;
    }
    /**
     * Establece el descripcion.
     *
     * @param descripcion el new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * Obtiene abreviatura.
     *
     * @return abreviatura
     */
     public String getAbreviatura() {
        return this.abreviatura;
    }
    /**
     * Establece el abreviatura.
     *
     * @param abreviatura el new abreviatura
     */
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
    /**
     * Obtiene fecha.
     *
     * @return fecha
     */
     public Date getFecha() {
        return this.fecha;
    }
    /**
     * Establece el fecha.
     *
     * @param fecha el new fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    /**
     * Obtiene version.
     *
     * @return version
     */
     public String getVersion() {
        return this.version;
    }
    /**
     * Establece el version.
     *
     * @param version el new version
     */
    public void setVersion(String version) {
        this.version = version;
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
     * Obtiene icono.
     *
     * @return icono
     */
     public String getIcono() {
        return this.icono;
    }
    /**
     * Establece el icono.
     *
     * @param icono el new icono
     */
    public void setIcono(String icono) {
        this.icono = icono;
    }
    /**
     * Establece el sistema menu list.
     *
     * @param sistemaMenuList el new sistema menu list
     */
    public List<Menu> getSistemaMenuList(){
        return this.sistemaMenuList;
    }
    /**
     * Establece el sistema list.
     *
     * @param sistemaList el new sistema list
     */
    public void setSistemaMenuList(List<Menu> sistemaMenuList) {
        this.sistemaMenuList = sistemaMenuList;
    }   
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idSistema == null) ? 0 : idSistema.hashCode());
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
        Sistema other = (Sistema) obj;
        if (idSistema == null) {
            if (other.idSistema != null) {
                return false;
            }
        } else if (!idSistema.equals(other.idSistema)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Sistema [idSistema=" + idSistema + "]";
    }
   
}