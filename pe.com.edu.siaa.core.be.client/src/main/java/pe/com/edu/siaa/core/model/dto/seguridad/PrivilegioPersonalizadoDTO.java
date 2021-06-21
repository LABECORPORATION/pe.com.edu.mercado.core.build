package pe.com.edu.siaa.core.model.dto.seguridad;

import java.io.Serializable;

import pe.com.edu.siaa.core.model.dto.BasePaginator;

 

/**
 * La Class PrivilegioPersonalizadoDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Thu Dec 14 00:27:44 COT 2017
 * @since SIAA-CORE 2.1
 */
public class PrivilegioPersonalizadoDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id privilegio personalizado. */
    private String idPrivilegioPersonalizado;
   
    /** El usuario. */
    private UsuarioDTO usuario;
   
    /** El privilegio. */
    private PrivilegioDTO privilegio;
   
    /** El estado. */
    private String estado;
   
    /**
     * Instancia un nuevo privilegio personalizadoDTO.
     */
    public PrivilegioPersonalizadoDTO() {
    }
   
   
    /**
     * Instancia un nuevo privilegio personalizadoDTO.
     *
     * @param idPrivilegioPersonalizado el id privilegio personalizado
     * @param usuario el usuario
     * @param privilegio el privilegio
     * @param estado el estado
     */
    public PrivilegioPersonalizadoDTO(String idPrivilegioPersonalizado, UsuarioDTO usuario,PrivilegioDTO privilegio,String estado ) {
        super();
        this.idPrivilegioPersonalizado = idPrivilegioPersonalizado;
        this.usuario = usuario;
        this.privilegio = privilegio;
        this.estado = estado;
    }
   
    //get y set
    /**
     * Obtiene id privilegio personalizado.
     *
     * @return id privilegio personalizado
     */
     public String getIdPrivilegioPersonalizado() {
        return this.idPrivilegioPersonalizado;
    }
    /**
     * Establece el id privilegio personalizado.
     *
     * @param idPrivilegioPersonalizado el new id privilegio personalizado
     */
    public void setIdPrivilegioPersonalizado(String idPrivilegioPersonalizado) {
        this.idPrivilegioPersonalizado = idPrivilegioPersonalizado;
    }
    /**
     * Obtiene usuario.
     *
     * @return usuario
     */
     public UsuarioDTO getUsuario() {
        return this.usuario;
    }
    /**
     * Establece el usuario.
     *
     * @param usuario el new usuario
     */
    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
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
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idPrivilegioPersonalizado == null) ? 0 : idPrivilegioPersonalizado.hashCode());
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
        PrivilegioPersonalizadoDTO other = (PrivilegioPersonalizadoDTO) obj;
        if (idPrivilegioPersonalizado == null) {
            if (other.idPrivilegioPersonalizado != null) {
                return false;
            }
        } else if (!idPrivilegioPersonalizado.equals(other.idPrivilegioPersonalizado)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PrivilegioPersonalizadoDTO [idPrivilegioPersonalizado=" + idPrivilegioPersonalizado + "]";
    }
   
}