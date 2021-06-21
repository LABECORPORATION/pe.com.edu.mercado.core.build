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
 * La Class GrupoUsuario.
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
@Table(name = "GrupoUsuario", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_SECURIDAD)
public class GrupoUsuario implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id grupo usuario. */
    @Id
    @Column(name = "idGrupoUsuario" , precision = 18 , scale = 0)
    private Long idGrupoUsuario;
   
    /** El descripcion. */
    @Column(name = "descripcion" , length = 100)
    private String descripcion;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
   
    /** El grupo usuario grupo usuario menu list. */
    @OneToMany(mappedBy = "grupoUsuario", fetch = FetchType.LAZY)
    private List<GrupoUsuarioMenu> grupoUsuarioGrupoUsuarioMenuList = new ArrayList<GrupoUsuarioMenu>();
    
    /** El grupo usuario grupo usuario usuario list. */
    @OneToMany(mappedBy = "grupoUsuario", fetch = FetchType.LAZY)
    private List<GrupoUsuarioUsuario> grupoUsuarioGrupoUsuarioUsuarioList = new ArrayList<GrupoUsuarioUsuario>();
    
    /** El grupo usuario privilegio grupo usuario list. */
    @OneToMany(mappedBy = "grupoUsuario", fetch = FetchType.LAZY)
    private List<PrivilegioGrupoUsuario> grupoUsuarioPrivilegioGrupoUsuarioList = new ArrayList<PrivilegioGrupoUsuario>();
    
    /**
     * Instancia un nuevo grupo usuario.
     */
    public GrupoUsuario() {
    }
   
   
    /**
     * Instancia un nuevo grupo usuario.
     *
     * @param idGrupoUsuario el id grupo usuario
     * @param descripcion el descripcion
     * @param estado el estado
     */
    public GrupoUsuario(Long idGrupoUsuario, String descripcion, String estado ) {
        super();
        this.idGrupoUsuario = idGrupoUsuario;
        this.descripcion = descripcion;
        this.estado = estado;
    }
   
    //get y set
    /**
     * Obtiene id grupo usuario.
     *
     * @return id grupo usuario
     */
     public Long getIdGrupoUsuario() {
        return this.idGrupoUsuario;
    }
    /**
     * Establece el id grupo usuario.
     *
     * @param idGrupoUsuario el new id grupo usuario
     */
    public void setIdGrupoUsuario(Long idGrupoUsuario) {
        this.idGrupoUsuario = idGrupoUsuario;
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
     * Establece el grupo usuario grupo usuario menu list.
     *
     * @param grupoUsuarioGrupoUsuarioMenuList el new grupo usuario grupo usuario menu list
     */
    public List<GrupoUsuarioMenu> getGrupoUsuarioGrupoUsuarioMenuList(){
        return this.grupoUsuarioGrupoUsuarioMenuList;
    }
    /**
     * Establece el grupo usuario list.
     *
     * @param grupoUsuarioList el new grupo usuario list
     */
    public void setGrupoUsuarioGrupoUsuarioMenuList(List<GrupoUsuarioMenu> grupoUsuarioGrupoUsuarioMenuList) {
        this.grupoUsuarioGrupoUsuarioMenuList = grupoUsuarioGrupoUsuarioMenuList;
    }   
    /**
     * Establece el grupo usuario grupo usuario usuario list.
     *
     * @param grupoUsuarioGrupoUsuarioUsuarioList el new grupo usuario grupo usuario usuario list
     */
    public List<GrupoUsuarioUsuario> getGrupoUsuarioGrupoUsuarioUsuarioList(){
        return this.grupoUsuarioGrupoUsuarioUsuarioList;
    }
    /**
     * Establece el grupo usuario list.
     *
     * @param grupoUsuarioList el new grupo usuario list
     */
    public void setGrupoUsuarioGrupoUsuarioUsuarioList(List<GrupoUsuarioUsuario> grupoUsuarioGrupoUsuarioUsuarioList) {
        this.grupoUsuarioGrupoUsuarioUsuarioList = grupoUsuarioGrupoUsuarioUsuarioList;
    }   
    /**
     * Establece el grupo usuario privilegio grupo usuario list.
     *
     * @param grupoUsuarioPrivilegioGrupoUsuarioList el new grupo usuario privilegio grupo usuario list
     */
    public List<PrivilegioGrupoUsuario> getGrupoUsuarioPrivilegioGrupoUsuarioList(){
        return this.grupoUsuarioPrivilegioGrupoUsuarioList;
    }
    /**
     * Establece el grupo usuario list.
     *
     * @param grupoUsuarioList el new grupo usuario list
     */
    public void setGrupoUsuarioPrivilegioGrupoUsuarioList(List<PrivilegioGrupoUsuario> grupoUsuarioPrivilegioGrupoUsuarioList) {
        this.grupoUsuarioPrivilegioGrupoUsuarioList = grupoUsuarioPrivilegioGrupoUsuarioList;
    }   
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idGrupoUsuario == null) ? 0 : idGrupoUsuario.hashCode());
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
        GrupoUsuario other = (GrupoUsuario) obj;
        if (idGrupoUsuario == null) {
            if (other.idGrupoUsuario != null) {
                return false;
            }
        } else if (!idGrupoUsuario.equals(other.idGrupoUsuario)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "GrupoUsuario [idGrupoUsuario=" + idGrupoUsuario + "]";
    }
   
}