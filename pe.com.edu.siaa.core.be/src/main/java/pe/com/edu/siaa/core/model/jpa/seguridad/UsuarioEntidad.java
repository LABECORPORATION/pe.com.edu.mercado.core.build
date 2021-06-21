package pe.com.edu.siaa.core.model.jpa.seguridad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class UsuarioEntidad.
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
@Table(name = "UsuarioEntidad", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_SECURIDAD)
public class UsuarioEntidad implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id usuario entidad. */
    @Id
    @Column(name = "idUsuarioEntidad" , length = 32)
    private String idUsuarioEntidad;
   
    /** El id usuario. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private Usuario usuario;
   
    /** El entidad. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEntidad", referencedColumnName = "idEntidad")
    private Entidad entidad;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
   
    /**
     * Instancia un nuevo usuario entidad.
     */
    public UsuarioEntidad() {
    }
   
   
    /**
     * Instancia un nuevo usuario entidad.
     *
     * @param idUsuarioEntidad el id usuario entidad
     * @param usuario el id usuario
     * @param entidad el entidad
     * @param estado el estado
     */
    public UsuarioEntidad(String idUsuarioEntidad, Usuario usuario,Entidad entidad,String estado ) {
        super();
        this.idUsuarioEntidad = idUsuarioEntidad;
        this.usuario = usuario;
        this.entidad = entidad;
        this.estado = estado;
    }
   
    //get y set
    /**
     * Obtiene id usuario entidad.
     *
     * @return id usuario entidad
     */
     public String getIdUsuarioEntidad() {
        return this.idUsuarioEntidad;
    }
    /**
     * Establece el id usuario entidad.
     *
     * @param idUsuarioEntidad el new id usuario entidad
     */
    public void setIdUsuarioEntidad(String idUsuarioEntidad) {
        this.idUsuarioEntidad = idUsuarioEntidad;
    }
    /**
     * Obtiene  usuario.
     *
     * @return  usuario
     */
     public Usuario getUsuario() {
        return this.usuario;
    }
    /**
     * Establece el id usuario.
     *
     * @param usuario el new  usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
                + ((idUsuarioEntidad == null) ? 0 : idUsuarioEntidad.hashCode());
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
        UsuarioEntidad other = (UsuarioEntidad) obj;
        if (idUsuarioEntidad == null) {
            if (other.idUsuarioEntidad != null) {
                return false;
            }
        } else if (!idUsuarioEntidad.equals(other.idUsuarioEntidad)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UsuarioEntidad [idUsuarioEntidad=" + idUsuarioEntidad + "]";
    }
   
}