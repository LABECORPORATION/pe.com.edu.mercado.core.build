package pe.com.edu.siaa.core.model.jpa.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class ListaValores.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 09:57:00 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "ListaValores", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_COMMON)
public class ListaValores implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id lista valores. */
    @Id
    @Column(name = "idListaValores" , precision = 18 , scale = 0)
    private Long idListaValores;
   
    /** El nombre. */
    @Column(name = "nombre" , length = 250)
    private String nombre;
   
    /** El descripcion. */
    @Column(name = "descripcion" , length = 500)
    private String descripcion;
   
    /** El sentencia sql. */
    @Column(name = "sentenciaSql" , length = 500)
    private String sentenciaSql;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
   
    /**
     * Instancia un nuevo lista valores.
     */
    public ListaValores() {
    }
   
   
    /**
     * Instancia un nuevo lista valores.
     *
     * @param idListaValores el id lista valores
     * @param nombre el nombre
     * @param descripcion el descripcion
     * @param sentenciaSql el sentencia sql
     * @param estado el estado
     */
    public ListaValores(Long idListaValores, String nombre, String descripcion, String sentenciaSql, String estado ) {
        super();
        this.idListaValores = idListaValores;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.sentenciaSql = sentenciaSql;
        this.estado = estado;
    }
   
    //get y set
    /**
     * Obtiene id lista valores.
     *
     * @return id lista valores
     */
     public Long getIdListaValores() {
        return this.idListaValores;
    }
    /**
     * Establece el id lista valores.
     *
     * @param idListaValores el new id lista valores
     */
    public void setIdListaValores(Long idListaValores) {
        this.idListaValores = idListaValores;
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
     * Obtiene sentencia sql.
     *
     * @return sentencia sql
     */
     public String getSentenciaSql() {
        return this.sentenciaSql;
    }
    /**
     * Establece el sentencia sql.
     *
     * @param sentenciaSql el new sentencia sql
     */
    public void setSentenciaSql(String sentenciaSql) {
        this.sentenciaSql = sentenciaSql;
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
                + ((idListaValores == null) ? 0 : idListaValores.hashCode());
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
        ListaValores other = (ListaValores) obj;
        if (idListaValores == null) {
            if (other.idListaValores != null) {
                return false;
            }
        } else if (!idListaValores.equals(other.idListaValores)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ListaValores [idListaValores=" + idListaValores + "]";
    }
   
}