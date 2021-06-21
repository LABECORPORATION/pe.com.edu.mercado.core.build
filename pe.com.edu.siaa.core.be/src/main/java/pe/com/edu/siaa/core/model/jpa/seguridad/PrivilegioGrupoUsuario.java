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
 * La Class PrivilegioGrupoUsuario.
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
@Table(name = "PrivilegioGrupoUsuario", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_SECURIDAD)
public class PrivilegioGrupoUsuario implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id privilegio grupo usuario. */
    @Id
    @Column(name = "idPrivilegioGrupoUsuario" , length = 32)
    private String idPrivilegioGrupoUsuario;
   
    /** El grupo usuario. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGrupoUsuario", referencedColumnName = "idGrupoUsuario")
    private GrupoUsuario grupoUsuario;
   
    /** El privilegio. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPrivilegio", referencedColumnName = "idPrivilegio")
    private Privilegio privilegio;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
   
    /**
     * Instancia un nuevo privilegio grupo usuario.
     */
    public PrivilegioGrupoUsuario() {
    }
   
   
    /**
     * Instancia un nuevo privilegio grupo usuario.
     *
     * @param idPrivilegioGrupoUsuario el id privilegio grupo usuario
     * @param grupoUsuario el grupo usuario
     * @param privilegio el privilegio
     * @param estado el estado
     */
    public PrivilegioGrupoUsuario(String idPrivilegioGrupoUsuario, GrupoUsuario grupoUsuario,Privilegio privilegio,String estado ) {
        super();
        this.idPrivilegioGrupoUsuario = idPrivilegioGrupoUsuario;
        this.grupoUsuario = grupoUsuario;
        this.privilegio = privilegio;
        this.estado = estado;
    }
   
    //get y set
    /**
     * Obtiene id privilegio grupo usuario.
     *
     * @return id privilegio grupo usuario
     */
     public String getIdPrivilegioGrupoUsuario() {
        return this.idPrivilegioGrupoUsuario;
    }
    /**
     * Establece el id privilegio grupo usuario.
     *
     * @param idPrivilegioGrupoUsuario el new id privilegio grupo usuario
     */
    public void setIdPrivilegioGrupoUsuario(String idPrivilegioGrupoUsuario) {
        this.idPrivilegioGrupoUsuario = idPrivilegioGrupoUsuario;
    }
    /**
     * Obtiene grupo usuario.
     *
     * @return grupo usuario
     */
     public GrupoUsuario getGrupoUsuario() {
        return this.grupoUsuario;
    }
    /**
     * Establece el grupo usuario.
     *
     * @param grupoUsuario el new grupo usuario
     */
    public void setGrupoUsuario(GrupoUsuario grupoUsuario) {
        this.grupoUsuario = grupoUsuario;
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
                + ((idPrivilegioGrupoUsuario == null) ? 0 : idPrivilegioGrupoUsuario.hashCode());
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
        PrivilegioGrupoUsuario other = (PrivilegioGrupoUsuario) obj;
        if (idPrivilegioGrupoUsuario == null) {
            if (other.idPrivilegioGrupoUsuario != null) {
                return false;
            }
        } else if (!idPrivilegioGrupoUsuario.equals(other.idPrivilegioGrupoUsuario)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PrivilegioGrupoUsuario [idPrivilegioGrupoUsuario=" + idPrivilegioGrupoUsuario + "]";
    }
   
}