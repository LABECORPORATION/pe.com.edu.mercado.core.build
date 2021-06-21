package pe.com.edu.siaa.core.model.dto.seguridad;

import java.io.Serializable;

import pe.com.edu.siaa.core.model.dto.BasePaginator;

 

/**
 * La Class PrivilegioGrupoUsuarioDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Thu Dec 14 00:27:44 COT 2017
 * @since SIAA-CORE 2.1
 */
public class PrivilegioGrupoUsuarioDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id privilegio grupo usuario. */
    private String idPrivilegioGrupoUsuario;
   
    /** El grupo usuario. */
    private GrupoUsuarioDTO grupoUsuario;
   
    /** El privilegio. */
    private PrivilegioDTO privilegio;
   
    /** El estado. */
    private String estado;
   
    /**
     * Instancia un nuevo privilegio grupo usuarioDTO.
     */
    public PrivilegioGrupoUsuarioDTO() {
    }
   
   
    /**
     * Instancia un nuevo privilegio grupo usuarioDTO.
     *
     * @param idPrivilegioGrupoUsuario el id privilegio grupo usuario
     * @param grupoUsuario el grupo usuario
     * @param privilegio el privilegio
     * @param estado el estado
     */
    public PrivilegioGrupoUsuarioDTO(String idPrivilegioGrupoUsuario, GrupoUsuarioDTO grupoUsuario,PrivilegioDTO privilegio,String estado ) {
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
     public GrupoUsuarioDTO getGrupoUsuario() {
        return this.grupoUsuario;
    }
    /**
     * Establece el grupo usuario.
     *
     * @param grupoUsuario el new grupo usuario
     */
    public void setGrupoUsuario(GrupoUsuarioDTO grupoUsuario) {
        this.grupoUsuario = grupoUsuario;
    }
    /**
     * Obtiene privilegio.
     *
     * @return privilegio
     */
     public PrivilegioDTO getPrivilegio() {
        return this.privilegio;
    }
    /**
     * Establece el privilegio.
     *
     * @param privilegio el new privilegio
     */
    public void setPrivilegio(PrivilegioDTO privilegio) {
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
        PrivilegioGrupoUsuarioDTO other = (PrivilegioGrupoUsuarioDTO) obj;
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
        return "PrivilegioGrupoUsuarioDTO [idPrivilegioGrupoUsuario=" + idPrivilegioGrupoUsuario + "]";
    }
   
}