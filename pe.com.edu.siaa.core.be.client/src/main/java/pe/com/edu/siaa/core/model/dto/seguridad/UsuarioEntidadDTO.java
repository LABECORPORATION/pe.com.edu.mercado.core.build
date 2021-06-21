package pe.com.edu.siaa.core.model.dto.seguridad;

import java.io.Serializable;

import pe.com.edu.siaa.core.model.dto.BasePaginator;

 

/**
 * La Class UsuarioEntidadDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Thu Dec 14 00:27:45 COT 2017
 * @since SIAA-CORE 2.1
 */
public class UsuarioEntidadDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id usuario entidad. */
    private String idUsuarioEntidad;
   
    /** El id usuario. */
    private UsuarioDTO usuario;
   
    /** El entidad. */
    private EntidadDTO entidad;
   
    /** El estado. */
    private String estado;
   
    /**
     * Instancia un nuevo usuario entidadDTO.
     */
    public UsuarioEntidadDTO() {
    }
   
   
    /**
     * Instancia un nuevo usuario entidadDTO.
     *
     * @param idUsuarioEntidad el id usuario entidad
     * @param usuario el id usuario
     * @param entidad el entidad
     * @param estado el estado
     */
    public UsuarioEntidadDTO(String idUsuarioEntidad, UsuarioDTO usuario,EntidadDTO entidad,String estado ) {
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
     * Obtiene id usuario.
     *
     * @return id usuario
     */
     public UsuarioDTO getUsuario() {
        return this.usuario;
    }
    /**
     * Establece el id usuario.
     *
     * @param idUsuario el new id usuario
     */
    public void setUsuario(UsuarioDTO idUsuario) {
        this.usuario = idUsuario;
    }
    /**
     * Obtiene entidad.
     *
     * @return entidad
     */
     public EntidadDTO getEntidad() {
        return this.entidad;
    }
    /**
     * Establece el entidad.
     *
     * @param entidad el new entidad
     */
    public void setEntidad(EntidadDTO entidad) {
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
        UsuarioEntidadDTO other = (UsuarioEntidadDTO) obj;
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
        return "UsuarioEntidadDTO [idUsuarioEntidad=" + idUsuarioEntidad + "]";
    }
   
}