package pe.com.edu.siaa.core.model.jpa.seguridad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class Privilegio.
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
@Table(name = "Privilegio", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_SECURIDAD)
public class Privilegio implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id privilegio. */
    @Id
    @Column(name = "idPrivilegio" , precision = 18 , scale = 0)
    private Long idPrivilegio;
   
    /** El acronimo. */
    @Column(name = "acronimo" , length = 100)
    private String acronimo;
   
    /** El nombre. */
    @Column(name = "nombre" , length = 100)
    private String nombre;
   
    /** El descripcion. */
    @Column(name = "descripcion" , length = 100)
    private String descripcion;
   
    /** El accion. */
    @Column(name = "accion" , length = 100)
    private String accion;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
   
    /** El privilegio privilegio grupo usuario list. */
    @OneToMany(mappedBy = "privilegio", fetch = FetchType.LAZY)
    private List<PrivilegioGrupoUsuario> privilegioPrivilegioGrupoUsuarioList = new ArrayList<PrivilegioGrupoUsuario>();
    
    /** El privilegio privilegio menu list. */
    @OneToMany(mappedBy = "privilegio", fetch = FetchType.LAZY)
    private List<PrivilegioMenu> privilegioPrivilegioMenuList = new ArrayList<PrivilegioMenu>();
    
    /** El privilegio privilegio personalizado list. */
    @OneToMany(mappedBy = "privilegio", fetch = FetchType.LAZY)
    private List<PrivilegioPersonalizado> privilegioPrivilegioPersonalizadoList = new ArrayList<PrivilegioPersonalizado>();
    
    /**
     * Instancia un nuevo privilegio.
     */
    public Privilegio() {
    }
   
   
    /**
     * Instancia un nuevo privilegio.
     *
     * @param idPrivilegio el id privilegio
     * @param acronimo el acronimo
     * @param nombre el nombre
     * @param descripcion el descripcion
     * @param accion el accion
     * @param estado el estado
     */
    public Privilegio(Long idPrivilegio, String acronimo, String nombre, String descripcion, String accion, String estado ) {
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
    public List<PrivilegioGrupoUsuario> getPrivilegioPrivilegioGrupoUsuarioList(){
        return this.privilegioPrivilegioGrupoUsuarioList;
    }
    /**
     * Establece el privilegio list.
     *
     * @param privilegioList el new privilegio list
     */
    public void setPrivilegioPrivilegioGrupoUsuarioList(List<PrivilegioGrupoUsuario> privilegioPrivilegioGrupoUsuarioList) {
        this.privilegioPrivilegioGrupoUsuarioList = privilegioPrivilegioGrupoUsuarioList;
    }   
    /**
     * Establece el privilegio privilegio menu list.
     *
     * @param privilegioPrivilegioMenuList el new privilegio privilegio menu list
     */
    public List<PrivilegioMenu> getPrivilegioPrivilegioMenuList(){
        return this.privilegioPrivilegioMenuList;
    }
    /**
     * Establece el privilegio list.
     *
     * @param privilegioList el new privilegio list
     */
    public void setPrivilegioPrivilegioMenuList(List<PrivilegioMenu> privilegioPrivilegioMenuList) {
        this.privilegioPrivilegioMenuList = privilegioPrivilegioMenuList;
    }   
    /**
     * Establece el privilegio privilegio personalizado list.
     *
     * @param privilegioPrivilegioPersonalizadoList el new privilegio privilegio personalizado list
     */
    public List<PrivilegioPersonalizado> getPrivilegioPrivilegioPersonalizadoList(){
        return this.privilegioPrivilegioPersonalizadoList;
    }
    /**
     * Establece el privilegio list.
     *
     * @param privilegioList el new privilegio list
     */
    public void setPrivilegioPrivilegioPersonalizadoList(List<PrivilegioPersonalizado> privilegioPrivilegioPersonalizadoList) {
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
        Privilegio other = (Privilegio) obj;
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
        return "Privilegio [idPrivilegio=" + idPrivilegio + "]";
    }
   
}