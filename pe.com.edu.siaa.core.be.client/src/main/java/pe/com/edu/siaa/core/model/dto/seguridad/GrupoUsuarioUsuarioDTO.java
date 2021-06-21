package pe.com.edu.siaa.core.model.dto.seguridad;

import java.io.Serializable;
import java.util.Date;

import pe.com.edu.siaa.core.model.dto.BasePaginator;

 

/**
 * La Class GrupoUsuarioUsuarioDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Thu Dec 14 00:27:43 COT 2017
 * @since SIAA-CORE 2.1
 */
public class GrupoUsuarioUsuarioDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id grupo usuario usuario. */
    private String idGrupoUsuarioUsuario;
   
    /** El grupo usuario. */
    private GrupoUsuarioDTO grupoUsuario;
   
    /** El usuario. */
    private UsuarioDTO usuario;
   
    /** El estado. */
    private String estado;
   
    /** El fecha creacion. */
    private Date fechaCreacion;
   
    /** El usuario creacion. */
    private String usuarioCreacion;
   
    /**
     * Instancia un nuevo grupo usuario usuarioDTO.
     */
    public GrupoUsuarioUsuarioDTO() {
    }
   
   
    /**
     * Instancia un nuevo grupo usuario usuarioDTO.
     *
     * @param idGrupoUsuarioUsuario el id grupo usuario usuario
     * @param grupoUsuario el grupo usuario
     * @param usuario el usuario
     * @param estado el estado
     * @param fechaCreacion el fecha creacion
     * @param usuarioCreacion el usuario creacion
     */
    public GrupoUsuarioUsuarioDTO(String idGrupoUsuarioUsuario, GrupoUsuarioDTO grupoUsuario,UsuarioDTO usuario,String estado, Date fechaCreacion, String usuarioCreacion ) {
        super();
        this.idGrupoUsuarioUsuario = idGrupoUsuarioUsuario;
        this.grupoUsuario = grupoUsuario;
        this.usuario = usuario;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.usuarioCreacion = usuarioCreacion;
    }
   
    //get y set
    /**
     * Obtiene id grupo usuario usuario.
     *
     * @return id grupo usuario usuario
     */
     public String getIdGrupoUsuarioUsuario() {
        return this.idGrupoUsuarioUsuario;
    }
    /**
     * Establece el id grupo usuario usuario.
     *
     * @param idGrupoUsuarioUsuario el new id grupo usuario usuario
     */
    public void setIdGrupoUsuarioUsuario(String idGrupoUsuarioUsuario) {
        this.idGrupoUsuarioUsuario = idGrupoUsuarioUsuario;
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
     * Obtiene usuario.
     *
     * @return usuario
     */
     public UsuarioDTO getUsuario() {
        return this.usuario;
    }
    /**
     * Establece el usuario.
     *
     * @param usuario el new usuario
     */
    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
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
                + ((idGrupoUsuarioUsuario == null) ? 0 : idGrupoUsuarioUsuario.hashCode());
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
        GrupoUsuarioUsuarioDTO other = (GrupoUsuarioUsuarioDTO) obj;
        if (idGrupoUsuarioUsuario == null) {
            if (other.idGrupoUsuarioUsuario != null) {
                return false;
            }
        } else if (!idGrupoUsuarioUsuario.equals(other.idGrupoUsuarioUsuario)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "GrupoUsuarioUsuarioDTO [idGrupoUsuarioUsuario=" + idGrupoUsuarioUsuario + "]";
    }
   
}