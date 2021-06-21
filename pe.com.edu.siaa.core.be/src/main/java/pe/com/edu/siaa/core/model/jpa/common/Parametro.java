package pe.com.edu.siaa.core.model.jpa.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pe.com.edu.siaa.core.model.jpa.seguridad.Entidad;
import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class Parametro.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 14 00:27:42 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "Parametro", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_COMMON)
public class Parametro implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id parametro. */
    @Id
    @Column(name = "idParametro" , length = 32)
    private String idParametro;
   
    /** El id entidad. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEntidad", referencedColumnName = "idEntidad")
    private Entidad entidad;
   
    /** El descripcion. */
    @Column(name = "descripcion" , length = 100)
    private String descripcion;
   
    /** El codigo. */
    @Column(name = "codigo" , length = 20)
    private String codigo;
   
    /** El valor. */
    @Column(name = "valor" , length = 32)
    private String valor;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
   
    /**
     * Instancia un nuevo parametro.
     */
    public Parametro() {
    }
   
   
    /**
     * Instancia un nuevo parametro.
     *
     * @param idParametro el id parametro
     * @param entidad el id entidad
     * @param descripcion el descripcion
     * @param codigo el codigo
     * @param valor el valor
     * @param estado el estado
     */
    public Parametro(String idParametro, Entidad entidad,String descripcion, String codigo, String valor, String estado ) {
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
        Parametro other = (Parametro) obj;
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
        return "Parametro [idParametro=" + idParametro + "]";
    }
   
}