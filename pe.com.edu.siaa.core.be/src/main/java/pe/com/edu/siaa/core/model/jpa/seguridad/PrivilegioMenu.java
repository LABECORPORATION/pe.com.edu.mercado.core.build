package pe.com.edu.siaa.core.model.jpa.seguridad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class PrivilegioMenu.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Wed Dec 13 22:04:27 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "PrivilegioMenu", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_SECURIDAD)
public class PrivilegioMenu implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id privilegio menu. */
    @Id
    @Column(name = "idPrivilegioMenu" , length = 32)
    private String idPrivilegioMenu;
   
    /** El menu. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMenu", referencedColumnName = "idMenu")
    private Menu menu;
   
    /** El privilegio. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPrivilegio", referencedColumnName = "idPrivilegio")
    private Privilegio privilegio;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
   
    /**
     * Instancia un nuevo privilegio menu.
     */
    public PrivilegioMenu() {
    }
   
   
    /**
     * Instancia un nuevo privilegio menu.
     *
     * @param idPrivilegioMenu el id privilegio menu
     * @param menu el menu
     * @param privilegio el privilegio
     * @param estado el estado
     */
    public PrivilegioMenu(String idPrivilegioMenu, Menu menu,Privilegio privilegio,String estado ) {
        super();
        this.idPrivilegioMenu = idPrivilegioMenu;
        this.menu = menu;
        this.privilegio = privilegio;
        this.estado = estado;
    }
   
    //get y set
    /**
     * Obtiene id privilegio menu.
     *
     * @return id privilegio menu
     */
     public String getIdPrivilegioMenu() {
        return this.idPrivilegioMenu;
    }
    /**
     * Establece el id privilegio menu.
     *
     * @param idPrivilegioMenu el new id privilegio menu
     */
    public void setIdPrivilegioMenu(String idPrivilegioMenu) {
        this.idPrivilegioMenu = idPrivilegioMenu;
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
     * Obtiene privilegio.
     *
     * @return privilegio
     */
     public Privilegio getPrivilegio() {
        return this.privilegio;
    }
    /**
     * Establece el privilegio.
     *
     * @param privilegio el new privilegio
     */
    public void setPrivilegio(Privilegio privilegio) {
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
                + ((idPrivilegioMenu == null) ? 0 : idPrivilegioMenu.hashCode());
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
        PrivilegioMenu other = (PrivilegioMenu) obj;
        if (idPrivilegioMenu == null) {
            if (other.idPrivilegioMenu != null) {
                return false;
            }
        } else if (!idPrivilegioMenu.equals(other.idPrivilegioMenu)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PrivilegioMenu [idPrivilegioMenu=" + idPrivilegioMenu + "]";
    }
   
}