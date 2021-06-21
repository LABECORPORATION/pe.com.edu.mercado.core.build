package pe.com.builderp.core.facturacion.model.dto.venta;

import java.io.Serializable;

import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.seguridad.EntidadDTO;
 

/**
 * La Class CategoriaDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Fri Dec 22 11:07:00 COT 2017
 * @since SIAA-CORE 2.1
 */
public class CategoriaDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id categoria. */
    private Long idCategoria;
   
    /** El linea. */
    private LineaDTO linea;
   
    /** El sub categoria. */
    private CategoriaDTO subCategoria;
   
    /** El nombre. */
    private String nombre;
   
    /** El descripcion. */
    private String descripcion;
   
    /** El codigo. */
    private String codigo;
   
    /** El codigo referencia. */
    private String codigoReferencia;
   
    /** El estado. */
    private String estado;
   
    /** El entidad. */
    private EntidadDTO entidad;
   
   
    /**
     * Instancia un nuevo categoriaDTO.
     */
    public CategoriaDTO() {
    }
   
   
    /**
     * Instancia un nuevo categoriaDTO.
     *
     * @param idCategoria el id categoria
     * @param linea el linea
     * @param subCategoria el sub categoria
     * @param nombre el nombre
     * @param descripcion el descripcion
     * @param codigo el codigo
     * @param codigoReferencia el codigo referencia
     * @param estado el estado
     * @param entidad el entidad
     */
    public CategoriaDTO(Long idCategoria, LineaDTO linea,CategoriaDTO subCategoria,String nombre, String descripcion, String codigo, String codigoReferencia, String estado, EntidadDTO entidad) {
        super();
        this.idCategoria = idCategoria;
        this.linea = linea;
        this.subCategoria = subCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.codigoReferencia = codigoReferencia;
        this.estado = estado;
        this.entidad = entidad;
    }
   
    //get y set
    /**
     * Obtiene id categoria.
     *
     * @return id categoria
     */
     public Long getIdCategoria() {
        return this.idCategoria;
    }
    /**
     * Establece el id categoria.
     *
     * @param idCategoria el new id categoria
     */
    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }
    /**
     * Obtiene linea.
     *
     * @return linea
     */
     public LineaDTO getLinea() {
        return this.linea;
    }
    /**
     * Establece el linea.
     *
     * @param linea el new linea
     */
    public void setLinea(LineaDTO linea) {
        this.linea = linea;
    }
    /**
     * Obtiene sub categoria.
     *
     * @return sub categoria
     */
     public CategoriaDTO getSubCategoria() {
        return this.subCategoria;
    }
    /**
     * Establece el sub categoria.
     *
     * @param subCategoria el new sub categoria
     */
    public void setSubCategoria(CategoriaDTO subCategoria) {
        this.subCategoria = subCategoria;
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
     * Obtiene codigo referencia.
     *
     * @return codigo referencia
     */
     public String getCodigoReferencia() {
        return this.codigoReferencia;
    }
    /**
     * Establece el codigo referencia.
     *
     * @param codigoReferencia el new codigo referencia
     */
    public void setCodigoReferencia(String codigoReferencia) {
        this.codigoReferencia = codigoReferencia;
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
       
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idCategoria == null) ? 0 : idCategoria.hashCode());
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
        CategoriaDTO other = (CategoriaDTO) obj;
        if (idCategoria == null) {
            if (other.idCategoria != null) {
                return false;
            }
        } else if (!idCategoria.equals(other.idCategoria)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CategoriaDTO [idCategoria=" + idCategoria + "]";
    }
   
}