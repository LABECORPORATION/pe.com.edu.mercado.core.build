package pe.com.builderp.core.facturacion.model.jpa.venta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import pe.com.edu.siaa.core.model.jpa.seguridad.Entidad;
import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class Categoria.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Fri Dec 22 11:10:34 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "Categoria", schema = ConfiguracionEntityManagerUtil.ESQUEMA_FACTURACION)
public class Categoria implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id categoria. */
    @Id
    @Column(name = "idCategoria" , length = 18)
    private Long idCategoria;
   
    /** El linea. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idLiena", referencedColumnName = "idLinea")
    private Linea linea;
   
    /** El sub categoria. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSubCategoria", referencedColumnName = "idCategoria")
    private Categoria subCategoria;
   
    /** El nombre. */
    @Column(name = "nombre" , length = 150)
    private String nombre;
   
    /** El descripcion. */
    @Column(name = "descripcion" , length = 150)
    private String descripcion;
   
    /** El codigo. */
    @Column(name = "codigo" , length = 50)
    private String codigo;
   
    /** El codigo referencia. */
    @Column(name = "codigoReferencia" , length = 50)
    private String codigoReferencia;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
   
    /** El entidad. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEntidad", referencedColumnName = "idEntidad")
    private Entidad entidad;
   
    /** El sub categoria categoria list. */
    @OneToMany(mappedBy = "subCategoria", fetch = FetchType.LAZY)
    private List<Categoria> subCategoriaCategoriaList = new ArrayList<Categoria>();
    
    /** El categoria producto list. */
    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
    private List<Producto> categoriaProductoList = new ArrayList<Producto>();
    
    /**
     * Instancia un nuevo categoria.
     */
    public Categoria() {
    }
   
   
    /**
     * Instancia un nuevo categoria.
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
    public Categoria(Long idCategoria, Linea linea,Categoria subCategoria,String nombre, String descripcion, String codigo, String codigoReferencia, String estado, Entidad entidad) {
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
     public Linea getLinea() {
        return this.linea;
    }
    /**
     * Establece el linea.
     *
     * @param linea el new linea
     */
    public void setLinea(Linea linea) {
        this.linea = linea;
    }
    /**
     * Obtiene sub categoria.
     *
     * @return sub categoria
     */
     public Categoria getSubCategoria() {
        return this.subCategoria;
    }
    /**
     * Establece el sub categoria.
     *
     * @param subCategoria el new sub categoria
     */
    public void setSubCategoria(Categoria subCategoria) {
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
     public Entidad getEntidad() {
        return this.entidad;
    }
    /**
     * Establece el entidad.
     *
     * @param entidad el new entidad
     */
    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }
    /**
     * Establece el sub categoria categoria list.
     *
     * @param subCategoriaCategoriaList el new sub categoria categoria list
     */
    public List<Categoria> getSubCategoriaCategoriaList(){
        return this.subCategoriaCategoriaList;
    }
    /**
     * Establece el sub categoria list.
     *
     * @param subCategoriaList el new sub categoria list
     */
    public void setSubCategoriaCategoriaList(List<Categoria> subCategoriaCategoriaList) {
        this.subCategoriaCategoriaList = subCategoriaCategoriaList;
    }   
    /**
     * Establece el categoria producto list.
     *
     * @param categoriaProductoList el new categoria producto list
     */
    public List<Producto> getCategoriaProductoList(){
        return this.categoriaProductoList;
    }
    /**
     * Establece el categoria list.
     *
     * @param categoriaList el new categoria list
     */
    public void setCategoriaProductoList(List<Producto> categoriaProductoList) {
        this.categoriaProductoList = categoriaProductoList;
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
        Categoria other = (Categoria) obj;
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
        return "Categoria [idCategoria=" + idCategoria + "]";
    }
   
}