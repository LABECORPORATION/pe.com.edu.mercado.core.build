package pe.com.edu.siaa.core.model.dto.seguridad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pe.com.edu.siaa.core.model.dto.BasePaginator;

 

/**
 * La Class PrivilegioDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Thu Dec 14 00:27:43 COT 2017
 * @since SIAA-CORE 2.1
 */
public class PrivilegioDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id privilegio. */
    private Long idPrivilegio;
   
    /** El acronimo. */
    private String acronimo;
   
    /** El nombre. */
    private String nombre;
   
    /** El descripcion. */
    private String descripcion;
   
    /** El accion. */
    private String accion;
   
    /** El estado. */
    private String estado;
   
    /** El privilegio privilegio grupo usuario list. */
    private List<PrivilegioGrupoUsuarioDTO> privilegioPrivilegioGrupoUsuarioList = new ArrayList<PrivilegioGrupoUsuarioDTO>();
   
    /** El privilegio privilegio menu list. */
    private List<PrivilegioMenuDTO> privilegioPrivilegioMenuList = new ArrayList<PrivilegioMenuDTO>();
   
    /** El privilegio privilegio personalizado list. */
    private List<PrivilegioPersonalizadoDTO> privilegioPrivilegioPersonalizadoList = new ArrayList<PrivilegioPersonalizadoDTO>();
   
    /**
     * Instancia un nuevo privilegioDTO.
     */
    public PrivilegioDTO() {
    }
   
   
    /**
     * Instancia un nuevo privilegioDTO.
     *
     * @param idPrivilegio el id privilegio
     * @param acronimo el acronimo
     * @param nombre el nombre
     * @param descripcion el descripcion
     * @param accion el accion
     * @param estado el estado
     */
    public PrivilegioDTO(Long idPrivilegio, String acronimo, String nombre, String descripcion, String accion, String estado ) {
        super();
        this.idPrivilegio = idPrivilegio;
        this.acronimo = acronimo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.accion = accion;
        this.estado = estado;
    }
   
    //get y set
    /**
     * Obtiene id privilegio.
     *
     * @return id privilegio
     */
     public Long getIdPrivilegio() {
        return this.idPrivilegio;
    }
    /**
     * Establece el id privilegio.
     *
     * @param idPrivilegio el new id privilegio
     */
    public void setIdPrivilegio(Long idPrivilegio) {
        this.idPrivilegio = idPrivilegio;
    }
    /**
     * Obtiene acronimo.
     *
     * @return acronimo
     */
     public String getAcronimo() {
        return this.acronimo;
    }
    /**
     * Establece el acronimo.
     *
     * @param acronimo el new acronimo
     */
    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
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
     * Obtiene accion.
     *
     * @return accion
     */
     public String getAccion() {
        return this.accion;
    }
    /**
     * Establece el accion.
     *
     * @param accion el new accion
     */
    public void setAccion(String accion) {
        this.accion = accion;
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
     * Establece el privilegio privilegio grupo usuario list.
     *
     * @param privilegioPrivilegioGrupoUsuarioList el new privilegio privilegio grupo usuario list
     */
    public List<PrivilegioGrupoUsuarioDTO> getPrivilegioPrivilegioGrupoUsuarioList(){
        return this.privilegioPrivilegioGrupoUsuarioList;
    }
    /**
     * Establece el privilegio list.
     *
     * @param privilegioList el new privilegio list
     */
    public void setPrivilegioPrivilegioGrupoUsuarioList(List<PrivilegioGrupoUsuarioDTO> privilegioPrivilegioGrupoUsuarioList) {
        this.privilegioPrivilegioGrupoUsuarioList = privilegioPrivilegioGrupoUsuarioList;
    }   
    /**
     * Establece el privilegio privilegio menu list.
     *
     * @param privilegioPrivilegioMenuList el new privilegio privilegio menu list
     */
    public List<PrivilegioMenuDTO> getPrivilegioPrivilegioMenuList(){
        return this.privilegioPrivilegioMenuList;
    }
    /**
     * Establece el privilegio list.
     *
     * @param privilegioList el new privilegio list
     */
    public void setPrivilegioPrivilegioMenuList(List<PrivilegioMenuDTO> privilegioPrivilegioMenuList) {
        this.privilegioPrivilegioMenuList = privilegioPrivilegioMenuList;
    }   
    /**
     * Establece el privilegio privilegio personalizado list.
     *
     * @param privilegioPrivilegioPersonalizadoList el new privilegio privilegio personalizado list
     */
    public List<PrivilegioPersonalizadoDTO> getPrivilegioPrivilegioPersonalizadoList(){
        return this.privilegioPrivilegioPersonalizadoList;
    }
    /**
     * Establece el privilegio list.
     *
     * @param privilegioList el new privilegio list
     */
    public void setPrivilegioPrivilegioPersonalizadoList(List<PrivilegioPersonalizadoDTO> privilegioPrivilegioPersonalizadoList) {
        this.privilegioPrivilegioPersonalizadoList = privilegioPrivilegioPersonalizadoList;
    }   
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idPrivilegio == null) ? 0 : idPrivilegio.hashCode());
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
        PrivilegioDTO other = (PrivilegioDTO) obj;
        if (idPrivilegio == null) {
            if (other.idPrivilegio != null) {
                return false;
            }
        } else if (!idPrivilegio.equals(other.idPrivilegio)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PrivilegioDTO [idPrivilegio=" + idPrivilegio + "]";
    }
   
}