package pe.com.builderp.core.facturacion.model.dto.empresa;

import java.io.Serializable; 

import pe.com.edu.siaa.core.model.dto.BasePaginator;
 

 

/**
 * La Class AnhioDTO.
 * <ul>
 * <li>Copyright 2020 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Wed Oct 21 20:17:19 COT 2020
 * @since BUILDERP-CORE 2.1
 */
public class AnhioDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id anhio. */
    private Long idAnhio;
   
    /** El nombre. */
    private String nombre;
   
    /** El estado. */
    private String estado;
    
   
    /**
     * Instancia un nuevo anhioDTO.
     */
    public AnhioDTO() {
		super();
    }
   
    /**
     * Instancia un nuevo anhioDTO.
     *
     * @param idAnhio el id anhio
     * @param nombre el nombre
     * @param estado el estado
     */
    public AnhioDTO(Long idAnhio, String nombre, String estado ) {
        super();
        this.idAnhio = idAnhio;
        this.nombre = nombre;
        this.estado = estado;
    }
   
    //get y set
    /**
     * Obtiene id anhio.
     *
     * @return id anhio
     */
     public Long getIdAnhio() {
        return this.idAnhio;
    }
    /**
     * Establece el id anhio.
     *
     * @param idAnhio el new id anhio
     */
    public void setIdAnhio(Long idAnhio) {
        this.idAnhio = idAnhio;
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
                + ((idAnhio == null) ? 0 : idAnhio.hashCode());
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
        AnhioDTO other = (AnhioDTO) obj;
        if (idAnhio == null) {
            if (other.idAnhio != null) {
                return false;
            }
        } else if (!idAnhio.equals(other.idAnhio)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AnhioDTO [idAnhio=" + idAnhio + "]";
    }
   
}