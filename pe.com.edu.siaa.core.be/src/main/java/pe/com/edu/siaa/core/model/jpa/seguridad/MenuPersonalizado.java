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
 * La Class MenuPersonalizado.
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
@Table(name = "MenuPersonalizado", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_SECURIDAD)
public class MenuPersonalizado implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id menu personalizado. */
    @Id
    @Column(name = "idMenuPersonalizado" , length = 32)
    private String idMenuPersonalizado;
   
    /** El persona. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private Usuario persona;
   
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
     * Instancia un nuevo menu personalizado.
     */
    public MenuPersonalizado() {
    }
   
   
    /**
     * Instancia un nuevo menu personalizado.
     *
     * @param idMenuPersonalizado el id menu personalizado
     * @param persona el persona
     * @param menu el menu
     * @param estado el estado
     * @param fechaCreacion el fecha creacion
     * @param usuarioCreacion el usuario creacion
     */
    public MenuPersonalizado(String idMenuPersonalizado, Usuario persona,Menu menu,String estado, Date fechaCreacion, String usuarioCreacion ) {
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
     public Usuario getPersona() {
        return this.persona;
    }
    /**
     * Establece el persona.
     *
     * @param persona el new persona
     */
    public void setPersona(Usuario persona) {
        this.persona = persona;
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
        MenuPersonalizado other = (MenuPersonalizado) obj;
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
        return "MenuPersonalizado [idMenuPersonalizado=" + idMenuPersonalizado + "]";
    }
   
}