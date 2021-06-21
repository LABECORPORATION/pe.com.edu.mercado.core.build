package pe.com.edu.siaa.core.model.dto.seguridad;

import java.io.Serializable;
import java.util.Date;

import pe.com.edu.siaa.core.model.dto.BasePaginator;

 

/**
 * La Class MenuPersonalizadoDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Thu Dec 14 00:27:43 COT 2017
 * @since SIAA-CORE 2.1
 */
public class MenuPersonalizadoDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id menu personalizado. */
    private String idMenuPersonalizado;
   
    /** El persona. */
    private UsuarioDTO persona;
   
    /** El menu. */
    private MenuDTO menu;
   
    /** El estado. */
    private String estado;
   
    /** El fecha creacion. */
    private Date fechaCreacion;
   
    /** El usuario creacion. */
    private String usuarioCreacion;
   
    /**
     * Instancia un nuevo menu personalizadoDTO.
     */
    public MenuPersonalizadoDTO() {
    }
   
   
    /**
     * Instancia un nuevo menu personalizadoDTO.
     *
     * @param idMenuPersonalizado el id menu personalizado
     * @param persona el persona
     * @param menu el menu
     * @param estado el estado
     * @param fechaCreacion el fecha creacion
     * @param usuarioCreacion el usuario creacion
     */
    public MenuPersonalizadoDTO(String idMenuPersonalizado, UsuarioDTO persona,MenuDTO menu,String estado, Date fechaCreacion, String usuarioCreacion ) {
        super();
        this.idMenuPersonalizado = idMenuPersonalizado;
        this.persona = persona;
        this.menu = menu;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.usuarioCreacion = usuarioCreacion;
    }
   
    //get y set
    /**
     * Obtiene id menu personalizado.
     *
     * @return id menu personalizado
     */
     public String getIdMenuPersonalizado() {
        return this.idMenuPersonalizado;
    }
    /**
     * Establece el id menu personalizado.
     *
     * @param idMenuPersonalizado el new id menu personalizado
     */
    public void setIdMenuPersonalizado(String idMenuPersonalizado) {
        this.idMenuPersonalizado = idMenuPersonalizado;
    }
    /**
     * Obtiene persona.
     *
     * @return persona
     */
     public UsuarioDTO getPersona() {
        return this.persona;
    }
    /**
     * Establece el persona.
     *
     * @param persona el new persona
     */
    public void setPersona(UsuarioDTO persona) {
        this.persona = persona;
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
                + ((idMenuPersonalizado == null) ? 0 : idMenuPersonalizado.hashCode());
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
        MenuPersonalizadoDTO other = (MenuPersonalizadoDTO) obj;
        if (idMenuPersonalizado == null) {
            if (other.idMenuPersonalizado != null) {
                return false;
            }
        } else if (!idMenuPersonalizado.equals(other.idMenuPersonalizado)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MenuPersonalizadoDTO [idMenuPersonalizado=" + idMenuPersonalizado + "]";
    }
   
}