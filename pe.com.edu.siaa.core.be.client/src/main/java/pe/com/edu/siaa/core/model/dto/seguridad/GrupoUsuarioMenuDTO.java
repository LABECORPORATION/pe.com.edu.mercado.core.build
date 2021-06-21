package pe.com.edu.siaa.core.model.dto.seguridad;

import java.io.Serializable;
import java.util.Date;

import pe.com.edu.siaa.core.model.dto.BasePaginator;

 

/**
 * La Class GrupoUsuarioMenuDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Thu Dec 14 00:27:43 COT 2017
 * @since SIAA-CORE 2.1
 */
public class GrupoUsuarioMenuDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id grupo usuario menu. */
    private String idGrupoUsuarioMenu;
   
    /** El grupo usuario. */
    private GrupoUsuarioDTO grupoUsuario;
   
    /** El menu. */
    private MenuDTO menu;
   
    /** El estado. */
    private String estado;
   
    /** El fecha creacion. */
    private Date fechaCreacion;
   
    /** El usuario creacion. */
    private String usuarioCreacion;
   
    /**
     * Instancia un nuevo grupo usuario menuDTO.
     */
    public GrupoUsuarioMenuDTO() {
    }
   
   
    /**
     * Instancia un nuevo grupo usuario menuDTO.
     *
     * @param idGrupoUsuarioMenu el id grupo usuario menu
     * @param grupoUsuario el grupo usuario
     * @param menu el menu
     * @param estado el estado
     * @param fechaCreacion el fecha creacion
     * @param usuarioCreacion el usuario creacion
     */
    public GrupoUsuarioMenuDTO(String idGrupoUsuarioMenu, GrupoUsuarioDTO grupoUsuario,MenuDTO menu,String estado, Date fechaCreacion, String usuarioCreacion ) {
        super();
        this.idGrupoUsuarioMenu = idGrupoUsuarioMenu;
        this.grupoUsuario = grupoUsuario;
        this.menu = menu;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.usuarioCreacion = usuarioCreacion;
    }
   
    //get y set
    /**
     * Obtiene id grupo usuario menu.
     *
     * @return id grupo usuario menu
     */
     public String getIdGrupoUsuarioMenu() {
        return this.idGrupoUsuarioMenu;
    }
    /**
     * Establece el id grupo usuario menu.
     *
     * @param idGrupoUsuarioMenu el new id grupo usuario menu
     */
    public void setIdGrupoUsuarioMenu(String idGrupoUsuarioMenu) {
        this.idGrupoUsuarioMenu = idGrupoUsuarioMenu;
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
     * Obtiene menu.
     *
     * @return menu
     */
     public MenuDTO getMenu() {
        return this.menu;
    }
    /**
     * Establece el menu.
     *
     * @param menu el new menu
     */
    public void setMenu(MenuDTO menu) {
        this.menu = menu;
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
                + ((idGrupoUsuarioMenu == null) ? 0 : idGrupoUsuarioMenu.hashCode());
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
        GrupoUsuarioMenuDTO other = (GrupoUsuarioMenuDTO) obj;
        if (idGrupoUsuarioMenu == null) {
            if (other.idGrupoUsuarioMenu != null) {
                return false;
            }
        } else if (!idGrupoUsuarioMenu.equals(other.idGrupoUsuarioMenu)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "GrupoUsuarioMenuDTO [idGrupoUsuarioMenu=" + idGrupoUsuarioMenu + "]";
    }
   
}