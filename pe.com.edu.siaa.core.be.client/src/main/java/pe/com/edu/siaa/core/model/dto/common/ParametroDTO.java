package pe.com.edu.siaa.core.model.dto.common;

import java.io.Serializable;

import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.seguridad.EntidadDTO;
 

/**
 * La Class ParametroDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Thu Dec 14 00:27:42 COT 2017
 * @since SIAA-CORE 2.1
 */
public class ParametroDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id parametro. */
    private String idParametro;
   
    /** El id entidad. */
    private EntidadDTO entidad;
   
    /** El descripcion. */
    private String descripcion;
   
    /** El codigo. */
    private String codigo;
   
    /** El valor. */
    private String valor;
   
    /** El estado. */
    private String estado;
   
    /**
     * Instancia un nuevo parametroDTO.
     */
    public ParametroDTO() {
    }
   
   
    /**
     * Instancia un nuevo parametroDTO.
     *
     * @param idParametro el id parametro
     * @param entidad el id entidad
     * @param descripcion el descripcion
     * @param codigo el codigo
     * @param valor el valor
     * @param estado el estado
     */
    public ParametroDTO(String idParametro, EntidadDTO entidad,String descripcion, String codigo, String valor, String estado ) {
        super();
        this.idParametro = idParametro;
        this.entidad = entidad;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.valor = valor;
        this.estado = estado;
    }
   
    //get y set
    /**
     * Obtiene id parametro.
     *
     * @return id parametro
     */
     public String getIdParametro() {
        return this.idParametro;
    }
    /**
     * Establece el id parametro.
     *
     * @param idParametro el new id parametro
     */
    public void setIdParametro(String idParametro) {
        this.idParametro = idParametro;
    }
    /**
     * Obtiene id entidad.
     *
     * @return id entidad
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
     * Obtiene valor.
     *
     * @return valor
     */
     public String getValor() {
        return this.valor;
    }
    /**
     * Establece el valor.
     *
     * @param valor el new valor
     */
    public void setValor(String valor) {
        this.valor = valor;
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
                + ((idParametro == null) ? 0 : idParametro.hashCode());
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
        ParametroDTO other = (ParametroDTO) obj;
        if (idParametro == null) {
            if (other.idParametro != null) {
                return false;
            }
        } else if (!idParametro.equals(other.idParametro)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ParametroDTO [idParametro=" + idParametro + "]";
    }
   
}