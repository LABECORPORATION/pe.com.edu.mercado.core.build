package pe.com.edu.siaa.core.model.dto.seguridad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pe.com.edu.siaa.core.model.dto.BasePaginator;

 

/**
 * La Class MenuDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Thu Dec 14 00:27:43 COT 2017
 * @since SIAA-CORE 2.1
 */
public class MenuDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id menu. */
    private Long idMenu;
   
    /** El sistema. */
    private SistemaDTO sistema;
   
    /** El nombre. */
    private String nombre;
   
    /** El descripcion. */
    private String descripcion;
   
    /** El url. */
    private String url;
   
    /** El parametro. */
    private String parametro;
   
    /** El icono. */
    private String icono;
   
    /** El target. */
    private String target;
   
    /** El menu padre. */
    private MenuDTO menuPadre;
   
    /** El fecha creacion. */
    private Date fechaCreacion;
   
    /** El usuario creacion. */
    private String usuarioCreacion;
   
    /** El fecha modificacion. */
    private Date fechaModificacion;
   
    /** El usuario modificacion. */
    private String usuarioModificacion;
   
    /** El estado. */
    private String estado;
   
    /** El menu padre menu list. */
    private List<MenuDTO> menuHijos = new ArrayList<MenuDTO>();
   
   
    /**
     * Instancia un nuevo menuDTO.
     */
    public MenuDTO() {
    }
   
   
    /**
     * Instancia un nuevo menuDTO.
     *
     * @param idMenu el id menu
     * @param sistema el sistema
     * @param nombre el nombre
     * @param descripcion el descripcion
     * @param url el url
     * @param parametro el parametro
     * @param icono el icono
     * @param target el target
     * @param menuPadre el menu padre
     * @param fechaCreacion el fecha creacion
     * @param usuarioCreacion el usuario creacion
     * @param fechaModificacion el fecha modificacion
     * @param usuarioModificacion el usuario modificacion
     * @param estado el estado
     */
    public MenuDTO(Long idMenu, SistemaDTO sistema,String nombre, String descripcion, String url, String parametro, String icono, String target, MenuDTO menuPadre,Date fechaCreacion, String usuarioCreacion, Date fechaModificacion, String usuarioModificacion, String estado ) {
        super();
        this.idMenu = idMenu;
        this.sistema = sistema;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.url = url;
        this.parametro = parametro;
        this.icono = icono;
        this.target = target;
        this.menuPadre = menuPadre;
        this.fechaCreacion = fechaCreacion;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioModificacion = usuarioModificacion;
        this.estado = estado;
    }
   
    //get y set
    /**
     * Obtiene id menu.
     *
     * @return id menu
     */
     public Long getIdMenu() {
        return this.idMenu;
    }
    /**
     * Establece el id menu.
     *
     * @param idMenu el new id menu
     */
    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }
    /**
     * Obtiene sistema.
     *
     * @return sistema
     */
     public SistemaDTO getSistema() {
        return this.sistema;
    }
    /**
     * Establece el sistema.
     *
     * @param sistema el new sistema
     */
    public void setSistema(SistemaDTO sistema) {
        this.sistema = sistema;
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
     * Obtiene url.
     *
     * @return url
     */
     public String getUrl() {
        return this.url;
    }
    /**
     * Establece el url.
     *
     * @param url el new url
     */
    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * Obtiene parametro.
     *
     * @return parametro
     */
     public String getParametro() {
        return this.parametro;
    }
    /**
     * Establece el parametro.
     *
     * @param parametro el new parametro
     */
    public void setParametro(String parametro) {
        this.parametro = parametro;
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
     * Obtiene target.
     *
     * @return target
     */
     public String getTarget() {
        return this.target;
    }
    /**
     * Establece el target.
     *
     * @param target el new target
     */
    public void setTarget(String target) {
        this.target = target;
    }
    /**
     * Obtiene menu padre.
     *
     * @return menu padre
     */
     public MenuDTO getMenuPadre() {
        return this.menuPadre;
    }
    /**
     * Establece el menu padre.
     *
     * @param menuPadre el new menu padre
     */
    public void setMenuPadre(MenuDTO menuPadre) {
        this.menuPadre = menuPadre;
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
    /**
     * Obtiene fecha modificacion.
     *
     * @return fecha modificacion
     */
     public Date getFechaModificacion() {
        return this.fechaModificacion;
    }
    /**
     * Establece el fecha modificacion.
     *
     * @param fechaModificacion el new fecha modificacion
     */
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
    /**
     * Obtiene usuario modificacion.
     *
     * @return usuario modificacion
     */
     public String getUsuarioModificacion() {
        return this.usuarioModificacion;
    }
    /**
     * Establece el usuario modificacion.
     *
     * @param usuarioModificacion el new usuario modificacion
     */
    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
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
     * Establece el menu padre menu list.
     *
     * @param menuPadreMenuList el new menu padre menu list
     */
    public List<MenuDTO> getMenuHijos(){
        return this.menuHijos;
    }
    /**
     * Establece el menu padre list.
     *
     * @param menuPadreList el new menu padre list
     */
    public void setMenuHijos(List<MenuDTO> menuHijos) {
        this.menuHijos = menuHijos;
    }   
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idMenu == null) ? 0 : idMenu.hashCode());
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
        MenuDTO other = (MenuDTO) obj;
        if (idMenu == null) {
            if (other.idMenu != null) {
                return false;
            }
        } else if (!idMenu.equals(other.idMenu)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MenuDTO [idMenu=" + idMenu + "]";
    }
   
}