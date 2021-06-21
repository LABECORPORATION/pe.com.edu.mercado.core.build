package pe.com.edu.siaa.core.model.dto.seguridad;

import java.io.Serializable;

import pe.com.edu.siaa.core.model.dto.BasePaginator;

 

/**
 * La Class AgrupaEntidadDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Wed Dec 20 10:42:10 COT 2017
 * @since SIAA-CORE 2.1
 */
public class AgrupaEntidadDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id agrupa entidad. */
    private String idAgrupaEntidad;
   
    /** El entidad. */
    private EntidadDTO entidad;
   
    /** El entidad agrupa. */
    private EntidadDTO entidadAgrupa;
   
    /** El estado. */
    private String estado;
   
    /**
     * Instancia un nuevo agrupa entidadDTO.
     */
    public AgrupaEntidadDTO() {
    }
   
   
    /**
     * Instancia un nuevo agrupa entidadDTO.
     *
     * @param idAgrupaEntidad el id agrupa entidad
     * @param entidad el entidad
     * @param entidadAgrupa el entidad agrupa
     * @param estado el estado
     */
    public AgrupaEntidadDTO(String idAgrupaEntidad, EntidadDTO entidad,EntidadDTO entidadAgrupa,String estado ) {
        super();
        this.idAgrupaEntidad = idAgrupaEntidad;
        this.entidad = entidad;
        this.entidadAgrupa = entidadAgrupa;
        this.estado = estado;
    }
   
    //get y set
    /**
     * Obtiene id agrupa entidad.
     *
     * @return id agrupa entidad
     */
     public String getIdAgrupaEntidad() {
        return this.idAgrupaEntidad;
    }
    /**
     * Establece el id agrupa entidad.
     *
     * @param idAgrupaEntidad el new id agrupa entidad
     */
    public void setIdAgrupaEntidad(String idAgrupaEntidad) {
        this.idAgrupaEntidad = idAgrupaEntidad;
    }
    /**
     * Obtiene entidad.
     *
     * @return entidad
     */
     public EntidadDTO getEntidad() {
        return this.entidad;
    }
    /**
     * Establece el entidad.
     *
     * @param entidad el new entidad
     */
    public void setEntidad(EntidadDTO entidad) {
        this.entidad = entidad;
    }
    /**
     * Obtiene entidad agrupa.
     *
     * @return entidad agrupa
     */
     public EntidadDTO getEntidadAgrupa() {
        return this.entidadAgrupa;
    }
    /**
     * Establece el entidad agrupa.
     *
     * @param entidadAgrupa el new entidad agrupa
     */
    public void setEntidadAgrupa(EntidadDTO entidadAgrupa) {
        this.entidadAgrupa = entidadAgrupa;
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
                + ((idAgrupaEntidad == null) ? 0 : idAgrupaEntidad.hashCode());
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
        AgrupaEntidadDTO other = (AgrupaEntidadDTO) obj;
        if (idAgrupaEntidad == null) {
            if (other.idAgrupaEntidad != null) {
                return false;
            }
        } else if (!idAgrupaEntidad.equals(other.idAgrupaEntidad)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AgrupaEntidadDTO [idAgrupaEntidad=" + idAgrupaEntidad + "]";
    }
   
}