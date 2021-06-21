package pe.com.edu.siaa.core.model.jpa.seguridad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class Menu.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Wed Dec 13 22:04:26 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "Menu", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_SECURIDAD)
public class Menu implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id menu. */
    @Id
    @Column(name = "idMenu" , precision = 18 , scale = 0)
    private Long idMenu;
   
    /** El sistema. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSistema", referencedColumnName = "idSistema")
    private Sistema sistema;
   
    /** El nombre. */
    @Column(name = "nombre" , length = 100)
    private String nombre;
   
    /** El descripcion. */
    @Column(name = "descripcion" , length = 100)
    private String descripcion;
   
    /** El url. */
    @Column(name = "url" , length = 120)
    private String url;
   
    /** El parametro. */
    @Column(name = "parametro" , length = 50)
    private String parametro;
   
    /** El icono. */
    @Column(name = "icono" , length = 50)
    private String icono;
   
    /** El target. */
    @Column(name = "target" , length = 50)
    private String target;
   
    /** El menu padre. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPadreMenu", referencedColumnName = "idMenu")
    private Menu menuPadre;
   
    /** El fecha creacion. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaCreacion")
    private Date fechaCreacion;
   
    /** El usuario creacion. */
    @Column(name = "usuarioCreacion" , length = 50)
    private String usuarioCreacion;
   
    /** El fecha modificacion. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaModificacion")
    private Date fechaModificacion;
   
    /** El usuario modificacion. */
    @Column(name = "usuarioModificacion" , length = 50)
    private String usuarioModificacion;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
   
    /** El menu padre menu list. */
    @OneToMany(mappedBy = "menuPadre", fetch = FetchType.LAZY)
    private List<Menu> menuPadreMenuList = new ArrayList<Menu>();
    
    /** El menu configuracion menu list. */
    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
    private List<ConfiguracionMenu> menuConfiguracionMenuList = new ArrayList<ConfiguracionMenu>();
    
    /** El menu grupo usuario menu list. */
    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
    private List<GrupoUsuarioMenu> menuGrupoUsuarioMenuList = new ArrayList<GrupoUsuarioMenu>();
    
    /** El menu menu personalizado list. */
    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
    private List<MenuPersonalizado> menuMenuPersonalizadoList = new ArrayList<MenuPersonalizado>();
    
    /** El menu privilegio menu list. */
    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
    private List<PrivilegioMenu> menuPrivilegioMenuList = new ArrayList<PrivilegioMenu>();
    
    /**
     * Instancia un nuevo menu.
     */
    public Menu() {
    }
   
   
    /**
     * Instancia un nuevo menu.
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
    public Menu(Long idMenu, Sistema sistema,String nombre, String descripcion, String url, String parametro, String icono, String target, Menu menuPadre,Date fechaCreacion, String usuarioCreacion, Date fechaModificacion, String usuarioModificacion, String estado ) {
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
     public Sistema getSistema() {
        return this.sistema;
    }
    /**
     * Establece el sistema.
     *
     * @param sistema el new sistema
     */
    public void setSistema(Sistema sistema) {
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
     public Menu getMenuPadre() {
        return this.menuPadre;
    }
    /**
     * Establece el menu padre.
     *
     * @param menuPadre el new menu padre
     */
    public void setMenuPadre(Menu menuPadre) {
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
    public List<Menu> getMenuPadreMenuList(){
        return this.menuPadreMenuList;
    }
    /**
     * Establece el menu padre list.
     *
     * @param menuPadreList el new menu padre list
     */
    public void setMenuPadreMenuList(List<Menu> menuPadreMenuList) {
        this.menuPadreMenuList = menuPadreMenuList;
    }   
    /**
     * Establece el menu configuracion menu list.
     *
     * @param menuConfiguracionMenuList el new menu configuracion menu list
     */
    public List<ConfiguracionMenu> getMenuConfiguracionMenuList(){
        return this.menuConfiguracionMenuList;
    }
    /**
     * Establece el menu list.
     *
     * @param menuList el new menu list
     */
    public void setMenuConfiguracionMenuList(List<ConfiguracionMenu> menuConfiguracionMenuList) {
        this.menuConfiguracionMenuList = menuConfiguracionMenuList;
    }   
    /**
     * Establece el menu grupo usuario menu list.
     *
     * @param menuGrupoUsuarioMenuList el new menu grupo usuario menu list
     */
    public List<GrupoUsuarioMenu> getMenuGrupoUsuarioMenuList(){
        return this.menuGrupoUsuarioMenuList;
    }
    /**
     * Establece el menu list.
     *
     * @param menuList el new menu list
     */
    public void setMenuGrupoUsuarioMenuList(List<GrupoUsuarioMenu> menuGrupoUsuarioMenuList) {
        this.menuGrupoUsuarioMenuList = menuGrupoUsuarioMenuList;
    }   
    /**
     * Establece el menu menu personalizado list.
     *
     * @param menuMenuPersonalizadoList el new menu menu personalizado list
     */
    public List<MenuPersonalizado> getMenuMenuPersonalizadoList(){
        return this.menuMenuPersonalizadoList;
    }
    /**
     * Establece el menu list.
     *
     * @param menuList el new menu list
     */
    public void setMenuMenuPersonalizadoList(List<MenuPersonalizado> menuMenuPersonalizadoList) {
        this.menuMenuPersonalizadoList = menuMenuPersonalizadoList;
    }   
    /**
     * Establece el menu privilegio menu list.
     *
     * @param menuPrivilegioMenuList el new menu privilegio menu list
     */
    public List<PrivilegioMenu> getMenuPrivilegioMenuList(){
        return this.menuPrivilegioMenuList;
    }
    /**
     * Establece el menu list.
     *
     * @param menuList el new menu list
     */
    public void setMenuPrivilegioMenuList(List<PrivilegioMenu> menuPrivilegioMenuList) {
        this.menuPrivilegioMenuList = menuPrivilegioMenuList;
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
        Menu other = (Menu) obj;
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
        return "Menu [idMenu=" + idMenu + "]";
    }
   
}