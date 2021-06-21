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
 * La Class TipoUsuario.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Wed Dec 20 00:30:24 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "TipoUsuario", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_SECURIDAD)
public class TipoUsuario implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id tipo usuario. */
    @Id
    @Column(name = "idTipoUsuario" , precision = 18 , scale = 0)
    private Long idTipoUsuario;
   
    /** El descripcion. */
    @Column(name = "descripcion" , length = 100)
    private String descripcion;
   
    /** El codigo. */
    @Column(name = "codigo" , length = 20)
    private String codigo;
   
    /** El codigo externo. */
    @Column(name = "codigoExterno" , length = 20)
    private String codigoExterno;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
   
    /** El tipo usuario usuario list. */
    @OneToMany(mappedBy = "tipoUsuario", fetch = FetchType.LAZY)
    private List<Usuario> tipoUsuarioUsuarioList = new ArrayList<Usuario>();
    
    /**
     * Instancia un nuevo tipo usuario.
     */
    public TipoUsuario() {
    }
   
   
    /**
     * Instancia un nuevo tipo usuario.
     *
     * @param idTipoUsuario el id tipo usuario
     * @param descripcion el descripcion
     * @param codigo el codigo
     * @param codigoExterno el codigo externo
     * @param estado el estado
     */
    public TipoUsuario(Long idTipoUsuario, String descripcion, String codigo, String codigoExterno, String estado ) {
        super();
        this.idTipoUsuario = idTipoUsuario;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.codigoExterno = codigoExterno;
        this.estado = estado;
    }
   
    //get y set
    /**
     * Obtiene id tipo usuario.
     *
     * @return id tipo usuario
     */
     public Long getIdTipoUsuario() {
        return this.idTipoUsuario;
    }
    /**
     * Establece el id tipo usuario.
     *
     * @param idTipoUsuario el new id tipo usuario
     */
    public void setIdTipoUsuario(Long idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
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
     * Obtiene codigo.
     *
     * @return codigo
     */
     public String getCodigo() {
        return this.codigo;
    }
    /**
     * Establece el codigo.
     *
     * @param codigo el new codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    /**
     * Obtiene codigo externo.
     *
     * @return codigo externo
     */
     public String getCodigoExterno() {
        return this.codigoExterno;
    }
    /**
     * Establece el codigo externo.
     *
     * @param codigoExterno el new codigo externo
     */
    public void setCodigoExterno(String codigoExterno) {
        this.codigoExterno = codigoExterno;
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
     * Establece el tipo usuario usuario list.
     *
     * @param tipoUsuarioUsuarioList el new tipo usuario usuario list
     */
    public List<Usuario> getTipoUsuarioUsuarioList(){
        return this.tipoUsuarioUsuarioList;
    }
    /**
     * Establece el tipo usuario list.
     *
     * @param tipoUsuarioList el new tipo usuario list
     */
    public void setTipoUsuarioUsuarioList(List<Usuario> tipoUsuarioUsuarioList) {
        this.tipoUsuarioUsuarioList = tipoUsuarioUsuarioList;
    }   
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idTipoUsuario == null) ? 0 : idTipoUsuario.hashCode());
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
        TipoUsuario other = (TipoUsuario) obj;
        if (idTipoUsuario == null) {
            if (other.idTipoUsuario != null) {
                return false;
            }
        } else if (!idTipoUsuario.equals(other.idTipoUsuario)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TipoUsuario [idTipoUsuario=" + idTipoUsuario + "]";
    }
   
}