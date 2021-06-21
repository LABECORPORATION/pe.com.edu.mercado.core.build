package pe.com.edu.siaa.core.model.jpa.seguridad;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class GrupoUsuarioMenu.
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
@Table(name = "GrupoUsuarioMenu", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_SECURIDAD)
public class GrupoUsuarioMenu implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id grupo usuario menu. */
    @Id
    @Column(name = "idGrupoUsuarioMenu" , length = 32)
    private String idGrupoUsuarioMenu;
   
    /** El grupo usuario. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGrupoUsuario", referencedColumnName = "idGrupoUsuario")
    private GrupoUsuario grupoUsuario;
   
    /** El menu. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMenu", referencedColumnName = "idMenu")
    private Menu menu;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
   
    /** El fecha creacion. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaCreacion")
    private Date fechaCreacion;
   
    /** El usuario creacion. */
    @Column(name = "usuarioCreacion" , length = 50)
    private String usuarioCreacion;
   
    /**
     * Instancia un nuevo grupo usuario menu.
     */
    public GrupoUsuarioMenu() {
    }
   
   
    /**
     * Instancia un nuevo grupo usuario menu.
     *
     * @param idGrupoUsuarioMenu el id grupo usuario menu
     * @param grupoUsuario el grupo usuario
     * @param menu el menu
     * @param estado el estado
     * @param fechaCreacion el fecha creacion
     * @param usuarioCreacion el usuario creacion
     */
    public GrupoUsuarioMenu(String idGrupoUsuarioMenu, GrupoUsuario grupoUsuario,Menu menu,String estado, Date fechaCreacion, String usuarioCreacion ) {
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
     * Obtiene menu.
     *
     * @return menu
     */
     public Menu getMenu() {
        return this.menu;
    }
    /**
     * Establece el menu.
     *
     * @param menu el new menu
     */
    public void setMenu(Menu menu) {
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
        GrupoUsuarioMenu other = (GrupoUsuarioMenu) obj;
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
        return "GrupoUsuarioMenu [idGrupoUsuarioMenu=" + idGrupoUsuarioMenu + "]";
    }
   
}