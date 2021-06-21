package pe.com.edu.siaa.core.model.dto.seguridad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pe.com.edu.siaa.core.model.dto.BasePaginator;

 

/**
 * La Class GrupoUsuarioDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Thu Dec 14 00:27:42 COT 2017
 * @since SIAA-CORE 2.1
 */
public class GrupoUsuarioDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id grupo usuario. */
    private Long idGrupoUsuario;
   
    /** El descripcion. */
    private String descripcion;
   
    /** El estado. */
    private String estado;
   
    /** El grupo usuario grupo usuario menu list. */
    private List<GrupoUsuarioMenuDTO> grupoUsuarioGrupoUsuarioMenuList = new ArrayList<GrupoUsuarioMenuDTO>();
   
    /** El grupo usuario grupo usuario usuario list. */
    private List<GrupoUsuarioUsuarioDTO> grupoUsuarioGrupoUsuarioUsuarioList = new ArrayList<GrupoUsuarioUsuarioDTO>();
   
    /** El grupo usuario privilegio grupo usuario list. */
    private List<PrivilegioGrupoUsuarioDTO> grupoUsuarioPrivilegioGrupoUsuarioList = new ArrayList<PrivilegioGrupoUsuarioDTO>();
   
    /**
     * Instancia un nuevo grupo usuarioDTO.
     */
    public GrupoUsuarioDTO() {
    }
   
   
    /**
     * Instancia un nuevo grupo usuarioDTO.
     *
     * @param idGrupoUsuario el id grupo usuario
     * @param descripcion el descripcion
     * @param estado el estado
     */
    public GrupoUsuarioDTO(Long idGrupoUsuario, String descripcion, String estado ) {
        super();
        this.idGrupoUsuario = idGrupoUsuario;
        this.descripcion = descripcion;
        this.estado = estado;
    }
   
    //get y set
    /**
     * Obtiene id grupo usuario.
     *
     * @return id grupo usuario
     */
     public Long getIdGrupoUsuario() {
        return this.idGrupoUsuario;
    }
    /**
     * Establece el id grupo usuario.
     *
     * @param idGrupoUsuario el new id grupo usuario
     */
    public void setIdGrupoUsuario(Long idGrupoUsuario) {
        this.idGrupoUsuario = idGrupoUsuario;
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
     * Establece el grupo usuario grupo usuario menu list.
     *
     * @param grupoUsuarioGrupoUsuarioMenuList el new grupo usuario grupo usuario menu list
     */
    public List<GrupoUsuarioMenuDTO> getGrupoUsuarioGrupoUsuarioMenuList(){
        return this.grupoUsuarioGrupoUsuarioMenuList;
    }
    /**
     * Establece el grupo usuario list.
     *
     * @param grupoUsuarioList el new grupo usuario list
     */
    public void setGrupoUsuarioGrupoUsuarioMenuList(List<GrupoUsuarioMenuDTO> grupoUsuarioGrupoUsuarioMenuList) {
        this.grupoUsuarioGrupoUsuarioMenuList = grupoUsuarioGrupoUsuarioMenuList;
    }   
    /**
     * Establece el grupo usuario grupo usuario usuario list.
     *
     * @param grupoUsuarioGrupoUsuarioUsuarioList el new grupo usuario grupo usuario usuario list
     */
    public List<GrupoUsuarioUsuarioDTO> getGrupoUsuarioGrupoUsuarioUsuarioList(){
        return this.grupoUsuarioGrupoUsuarioUsuarioList;
    }
    /**
     * Establece el grupo usuario list.
     *
     * @param grupoUsuarioList el new grupo usuario list
     */
    public void setGrupoUsuarioGrupoUsuarioUsuarioList(List<GrupoUsuarioUsuarioDTO> grupoUsuarioGrupoUsuarioUsuarioList) {
        this.grupoUsuarioGrupoUsuarioUsuarioList = grupoUsuarioGrupoUsuarioUsuarioList;
    }   
    /**
     * Establece el grupo usuario privilegio grupo usuario list.
     *
     * @param grupoUsuarioPrivilegioGrupoUsuarioList el new grupo usuario privilegio grupo usuario list
     */
    public List<PrivilegioGrupoUsuarioDTO> getGrupoUsuarioPrivilegioGrupoUsuarioList(){
        return this.grupoUsuarioPrivilegioGrupoUsuarioList;
    }
    /**
     * Establece el grupo usuario list.
     *
     * @param grupoUsuarioList el new grupo usuario list
     */
    public void setGrupoUsuarioPrivilegioGrupoUsuarioList(List<PrivilegioGrupoUsuarioDTO> grupoUsuarioPrivilegioGrupoUsuarioList) {
        this.grupoUsuarioPrivilegioGrupoUsuarioList = grupoUsuarioPrivilegioGrupoUsuarioList;
    }   
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idGrupoUsuario == null) ? 0 : idGrupoUsuario.hashCode());
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
        GrupoUsuarioDTO other = (GrupoUsuarioDTO) obj;
        if (idGrupoUsuario == null) {
            if (other.idGrupoUsuario != null) {
                return false;
            }
        } else if (!idGrupoUsuario.equals(other.idGrupoUsuario)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "GrupoUsuarioDTO [idGrupoUsuario=" + idGrupoUsuario + "]";
    }
   
}