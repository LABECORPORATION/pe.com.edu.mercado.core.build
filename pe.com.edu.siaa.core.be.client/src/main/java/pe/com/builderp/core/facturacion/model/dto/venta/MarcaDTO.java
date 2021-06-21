package pe.com.builderp.core.facturacion.model.dto.venta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.seguridad.EntidadDTO;
 

/**
 * La Class MarcaDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Fri Dec 22 11:07:02 COT 2017
 * @since SIAA-CORE 2.1
 */
public class MarcaDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id marca. */
    private Long idMarca;
   
    /** El nombre. */
    private String nombre;
   
    /** El codigo. */
    private String codigo;
   
    /** El codigo referencia. */
    private String codigoReferencia;
   
    /** El estado. */
    private String estado;
   
    /** El entidad. */
    private EntidadDTO entidad;
   
    /** El marca modelo list. */
    private List<ModeloDTO> marcaModeloList = new ArrayList<ModeloDTO>();
   
    /**
     * Instancia un nuevo marcaDTO.
     */
    public MarcaDTO() {
    }
   
   
    /**
     * Instancia un nuevo marcaDTO.
     *
     * @param idMarca el id marca
     * @param nombre el nombre
     * @param codigo el codigo
     * @param codigoReferencia el codigo referencia
     * @param estado el estado
     * @param entidad el entidad
     */
    public MarcaDTO(Long idMarca, String nombre, String codigo, String codigoReferencia, String estado, EntidadDTO entidad) {
        super();
        this.idMarca = idMarca;
        this.nombre = nombre;
        this.codigo = codigo;
        this.codigoReferencia = codigoReferencia;
        this.estado = estado;
        this.entidad = entidad;
    }
   
    //get y set
    /**
     * Obtiene id marca.
     *
     * @return id marca
     */
     public Long getIdMarca() {
        return this.idMarca;
    }
    /**
     * Establece el id marca.
     *
     * @param idMarca el new id marca
     */
    public void setIdMarca(Long idMarca) {
        this.idMarca = idMarca;
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
    /**
     * Establece el marca modelo list.
     *
     * @param marcaModeloList el new marca modelo list
     */
    public List<ModeloDTO> getMarcaModeloList(){
        return this.marcaModeloList;
    }
    /**
     * Establece el marca list.
     *
     * @param marcaList el new marca list
     */
    public void setMarcaModeloList(List<ModeloDTO> marcaModeloList) {
        this.marcaModeloList = marcaModeloList;
    }   
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idMarca == null) ? 0 : idMarca.hashCode());
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
        MarcaDTO other = (MarcaDTO) obj;
        if (idMarca == null) {
            if (other.idMarca != null) {
                return false;
            }
        } else if (!idMarca.equals(other.idMarca)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MarcaDTO [idMarca=" + idMarca + "]";
    }
   
}