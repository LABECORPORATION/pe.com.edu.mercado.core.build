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
 * La Class Modelo.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Fri Dec 22 11:10:35 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "Modelo", schema = ConfiguracionEntityManagerUtil.ESQUEMA_FACTURACION)
public class Modelo implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id modelo. */
    @Id
    @Column(name = "idModelo" , length = 18)
    private Long idModelo;
   
    /** El marca. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMarca", referencedColumnName = "idMarca")
    private Marca marca;
   
    /** El sub modelo. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSubModelo", referencedColumnName = "idModelo")
    private Modelo subModelo;
   
    /** El nombre. */
    @Column(name = "nombre" , length = 150)
    private String nombre;
   
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
   
    /** El sub modelo modelo list. */
    @OneToMany(mappedBy = "subModelo", fetch = FetchType.LAZY)
    private List<Modelo> subModeloModeloList = new ArrayList<Modelo>();
    
    /** El modelo producto list. */
    @OneToMany(mappedBy = "modelo", fetch = FetchType.LAZY)
    private List<Producto> modeloProductoList = new ArrayList<Producto>();
    
    /**
     * Instancia un nuevo modelo.
     */
    public Modelo() {
    }
   
   
    /**
     * Instancia un nuevo modelo.
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
    public Modelo(Long idModelo, Marca marca,Modelo subModelo,String nombre, String codigo, String codigoReferencia, String estado, Entidad entidad) {
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
     public Marca getMarca() {
        return this.marca;
    }
    /**
     * Establece el marca.
     *
     * @param marca el new marca
     */
    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    /**
     * Obtiene sub modelo.
     *
     * @return sub modelo
     */
     public Modelo getSubModelo() {
        return this.subModelo;
    }
    /**
     * Establece el sub modelo.
     *
     * @param subModelo el new sub modelo
     */
    public void setSubModelo(Modelo subModelo) {
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
     * Establece el sub modelo modelo list.
     *
     * @param subModeloModeloList el new sub modelo modelo list
     */
    public List<Modelo> getSubModeloModeloList(){
        return this.subModeloModeloList;
    }
    /**
     * Establece el sub modelo list.
     *
     * @param subModeloList el new sub modelo list
     */
    public void setSubModeloModeloList(List<Modelo> subModeloModeloList) {
        this.subModeloModeloList = subModeloModeloList;
    }   
    /**
     * Establece el modelo producto list.
     *
     * @param modeloProductoList el new modelo producto list
     */
    public List<Producto> getModeloProductoList(){
        return this.modeloProductoList;
    }
    /**
     * Establece el modelo list.
     *
     * @param modeloList el new modelo list
     */
    public void setModeloProductoList(List<Producto> modeloProductoList) {
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
        Modelo other = (Modelo) obj;
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
        return "Modelo [idModelo=" + idModelo + "]";
    }
   
}