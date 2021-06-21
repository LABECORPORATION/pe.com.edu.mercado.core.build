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
 * La Class PrivilegioPersonalizado.
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
@Table(name = "PrivilegioPersonalizado", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_SECURIDAD)
public class PrivilegioPersonalizado implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id privilegio personalizado. */
    @Id
    @Column(name = "idPrivilegioPersonalizado" , length = 32)
    private String idPrivilegioPersonalizado;
   
    /** El usuario. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private Usuario usuario;
   
    /** El privilegio. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPrivilegio", referencedColumnName = "idPrivilegio")
    private Privilegio privilegio;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
   
    /**
     * Instancia un nuevo privilegio personalizado.
     */
    public PrivilegioPersonalizado() {
    }
   
   
    /**
     * Instancia un nuevo privilegio personalizado.
     *
     * @param idPrivilegioPersonalizado el id privilegio personalizado
     * @param usuario el usuario
     * @param privilegio el privilegio
     * @param estado el estado
     */
    public PrivilegioPersonalizado(String idPrivilegioPersonalizado, Usuario usuario,Privilegio privilegio,String estado ) {
        super();
        this.idPrivilegioPersonalizado = idPrivilegioPersonalizado;
        this.usuario = usuario;
        this.privilegio = privilegio;
        this.estado = estado;
    }
   
    //get y set
    /**
     * Obtiene id privilegio personalizado.
     *
     * @return id privilegio personalizado
     */
     public String getIdPrivilegioPersonalizado() {
        return this.idPrivilegioPersonalizado;
    }
    /**
     * Establece el id privilegio personalizado.
     *
     * @param idPrivilegioPersonalizado el new id privilegio personalizado
     */
    public void setIdPrivilegioPersonalizado(String idPrivilegioPersonalizado) {
        this.idPrivilegioPersonalizado = idPrivilegioPersonalizado;
    }
    /**
     * Obtiene usuario.
     *
     * @return usuario
     */
     public Usuario getUsuario() {
        return this.usuario;
    }
    /**
     * Establece el usuario.
     *
     * @param usuario el new usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    /**
     * Obtiene privilegio.
     *
     * @return privilegio
     */
     public Privilegio getPrivilegio() {
        return this.privilegio;
    }
    /**
     * Establece el privilegio.
     *
     * @param privilegio el new privilegio
     */
    public void setPrivilegio(Privilegio privilegio) {
        this.privilegio = privilegio;
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
                + ((idPrivilegioPersonalizado == null) ? 0 : idPrivilegioPersonalizado.hashCode());
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
        PrivilegioPersonalizado other = (PrivilegioPersonalizado) obj;
        if (idPrivilegioPersonalizado == null) {
            if (other.idPrivilegioPersonalizado != null) {
                return false;
            }
        } else if (!idPrivilegioPersonalizado.equals(other.idPrivilegioPersonalizado)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PrivilegioPersonalizado [idPrivilegioPersonalizado=" + idPrivilegioPersonalizado + "]";
    }
   
}