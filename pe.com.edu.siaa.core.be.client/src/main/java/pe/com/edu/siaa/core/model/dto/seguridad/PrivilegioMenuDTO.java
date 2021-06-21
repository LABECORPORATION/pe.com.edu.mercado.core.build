package pe.com.edu.siaa.core.model.dto.seguridad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pe.com.edu.siaa.core.model.dto.BasePaginator;

 

/**
 * La Class PrivilegioMenuDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Thu Dec 14 00:27:44 COT 2017
 * @since SIAA-CORE 2.1
 */
public class PrivilegioMenuDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id privilegio menu. */
    private String idPrivilegioMenu;
   
    /** El menu. */
    private MenuDTO menu;
   
    /** El privilegio. */
    private PrivilegioDTO privilegio;
   
    /** El estado. */
    private String estado;
   
    private List<Long> listaIdMenu = new ArrayList<Long>();
    
    /**
     * Instancia un nuevo privilegio menuDTO.
     */
    public PrivilegioMenuDTO() {
    }
   
   
    /**
     * Instancia un nuevo privilegio menuDTO.
     *
     * @param idPrivilegioMenu el id privilegio menu
     * @param menu el menu
     * @param privilegio el privilegio
     * @param estado el estado
     */
    public PrivilegioMenuDTO(String idPrivilegioMenu, MenuDTO menu,PrivilegioDTO privilegio,String estado ) {
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
     * Obtiene privilegio.
     *
     * @return privilegio
     */
     public PrivilegioDTO getPrivilegio() {
        return this.privilegio;
    }
    /**
     * Establece el privilegio.
     *
     * @param privilegio el new privilegio
     */
    public void setPrivilegio(PrivilegioDTO privilegio) {
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
    
    public List<Long> getListaIdMenu() {
		return listaIdMenu;
	}


	public void setListaIdMenu(List<Long> listaIdMenu) {
		this.listaIdMenu = listaIdMenu;
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
        PrivilegioMenuDTO other = (PrivilegioMenuDTO) obj;
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
        return "PrivilegioMenuDTO [idPrivilegioMenu=" + idPrivilegioMenu + "]";
    }
   
}