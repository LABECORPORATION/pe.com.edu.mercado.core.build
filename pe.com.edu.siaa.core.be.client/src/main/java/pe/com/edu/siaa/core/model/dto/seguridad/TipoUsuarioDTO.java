package pe.com.edu.siaa.core.model.dto.seguridad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pe.com.edu.siaa.core.model.dto.BasePaginator;

 

/**
 * La Class TipoUsuarioDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Thu Dec 14 00:27:45 COT 2017
 * @since SIAA-CORE 2.1
 */
public class TipoUsuarioDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id tipo usuario. */
    private Long idTipoUsuario;
   
    /** El descripcion. */
    private String descripcion;
   
    /** El codigo. */
    private String codigo;
   
    /** El codigo externo. */
    private String codigoExterno;
   
    /** El estado. */
    private String estado;
   
    /** El tipo usuario usuario list. */
    private List<UsuarioDTO> tipoUsuarioUsuarioList = new ArrayList<UsuarioDTO>();
   
    /**
     * Instancia un nuevo tipo usuarioDTO.
     */
    public TipoUsuarioDTO() {
    }
   
   
    /**
     * Instancia un nuevo tipo usuarioDTO.
     *
     * @param idTipoUsuario el id tipo usuario
     * @param descripcion el descripcion
     * @param codigo el codigo
     * @param codigoExterno el codigo externo
     * @param estado el estado
     */
    public TipoUsuarioDTO(Long idTipoUsuario, String descripcion, String codigo, String codigoExterno, String estado ) {
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
    public List<UsuarioDTO> getTipoUsuarioUsuarioList(){
        return this.tipoUsuarioUsuarioList;
    }
    /**
     * Establece el tipo usuario list.
     *
     * @param tipoUsuarioList el new tipo usuario list
     */
    public void setTipoUsuarioUsuarioList(List<UsuarioDTO> tipoUsuarioUsuarioList) {
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
        TipoUsuarioDTO other = (TipoUsuarioDTO) obj;
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
        return "TipoUsuarioDTO [idTipoUsuario=" + idTipoUsuario + "]";
    }
   
}