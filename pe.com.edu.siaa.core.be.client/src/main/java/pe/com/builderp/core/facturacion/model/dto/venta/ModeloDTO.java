package pe.com.builderp.core.facturacion.model.dto.venta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.seguridad.EntidadDTO;
 

/**
 * La Class ModeloDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Fri Dec 22 11:07:02 COT 2017
 * @since SIAA-CORE 2.1
 */
public class ModeloDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id modelo. */
    private Long idModelo;
   
    /** El marca. */
    private MarcaDTO marca;
   
    /** El sub modelo. */
    private ModeloDTO subModelo;
   
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
   
    /** El sub modelo modelo list. */
    private List<ModeloDTO> subModeloModeloList = new ArrayList<ModeloDTO>();
   
    /** El modelo producto list. */
    private List<ProductoDTO> modeloProductoList = new ArrayList<ProductoDTO>();
   
    /**
     * Instancia un nuevo modeloDTO.
     */
    public ModeloDTO() {
    }
   
   
    /**
     * Instancia un nuevo modeloDTO.
     *
     * @param idModelo el id modelo
     * @param marca el marca
     * @param subModelo el sub modelo
     * @param nombre el nombre
     * @param codigo el codigo
     * @param codigoReferencia el codigo referencia
     * @param estado el estado
     * @param entidad el entidad
     */
    public ModeloDTO(Long idModelo, MarcaDTO marca,ModeloDTO subModelo,String nombre, String codigo, String codigoReferencia, String estado, EntidadDTO entidad) {
        super();
        this.idModelo = idModelo;
        this.marca = marca;
        this.subModelo = subModelo;
        this.nombre = nombre;
        this.codigo = codigo;
        this.codigoReferencia = codigoReferencia;
        this.estado = estado;
        this.entidad = entidad;
    }
   
    //get y set
    /**
     * Obtiene id modelo.
     *
     * @return id modelo
     */
     public Long getIdModelo() {
        return this.idModelo;
    }
    /**
     * Establece el id modelo.
     *
     * @param idModelo el new id modelo
     */
    public void setIdModelo(Long idModelo) {
        this.idModelo = idModelo;
    }
    /**
     * Obtiene marca.
     *
     * @return marca
     */
     public MarcaDTO getMarca() {
        return this.marca;
    }
    /**
     * Establece el marca.
     *
     * @param marca el new marca
     */
    public void setMarca(MarcaDTO marca) {
        this.marca = marca;
    }
    /**
     * Obtiene sub modelo.
     *
     * @return sub modelo
     */
     public ModeloDTO getSubModelo() {
        return this.subModelo;
    }
    /**
     * Establece el sub modelo.
     *
     * @param subModelo el new sub modelo
     */
    public void setSubModelo(ModeloDTO subModelo) {
        this.subModelo = subModelo;
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
     * Establece el sub modelo modelo list.
     *
     * @param subModeloModeloList el new sub modelo modelo list
     */
    public List<ModeloDTO> getSubModeloModeloList(){
        return this.subModeloModeloList;
    }
    /**
     * Establece el sub modelo list.
     *
     * @param subModeloList el new sub modelo list
     */
    public void setSubModeloModeloList(List<ModeloDTO> subModeloModeloList) {
        this.subModeloModeloList = subModeloModeloList;
    }   
    /**
     * Establece el modelo producto list.
     *
     * @param modeloProductoList el new modelo producto list
     */
    public List<ProductoDTO> getModeloProductoList(){
        return this.modeloProductoList;
    }
    /**
     * Establece el modelo list.
     *
     * @param modeloList el new modelo list
     */
    public void setModeloProductoList(List<ProductoDTO> modeloProductoList) {
        this.modeloProductoList = modeloProductoList;
    }   
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idModelo == null) ? 0 : idModelo.hashCode());
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
        ModeloDTO other = (ModeloDTO) obj;
        if (idModelo == null) {
            if (other.idModelo != null) {
                return false;
            }
        } else if (!idModelo.equals(other.idModelo)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ModeloDTO [idModelo=" + idModelo + "]";
    }
   
}