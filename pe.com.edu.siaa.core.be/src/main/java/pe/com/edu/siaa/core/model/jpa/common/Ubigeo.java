package pe.com.edu.siaa.core.model.jpa.common;

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

import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class Ubigeo.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 09:56:54 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "Ubigeo", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_COMMON)
public class Ubigeo implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id ubigeo. */
    @Id
    @Column(name = "idUbigeo" , length = 6)
    private String idUbigeo;
   
    /** El descripcion. */
    @Column(name = "descripcion" , length = 150)
    private String descripcion;
   
    /** El ubigeo by dependencia. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dependencia", referencedColumnName = "idUbigeo")
    private Ubigeo ubigeoByDependencia;
   
    /** El tipo. */
    @Column(name = "tipo" , length = 2)
    private String tipo;
          
    /** El ubigeo by dependencia ubigeo list. */
    @OneToMany(mappedBy = "ubigeoByDependencia", fetch = FetchType.LAZY)
    private List<Ubigeo> ubigeoByDependenciaUbigeoList = new ArrayList<Ubigeo>();
    
    /**
     * Instancia un nuevo ubigeo.
     */
    public Ubigeo() {
    }
   
   
    /**
     * Instancia un nuevo ubigeo.
     *
     * @param idUbigeo el id ubigeo
     * @param descripcion el descripcion
     * @param ubigeoByDependencia el ubigeo by dependencia
     * @param tipo el tipo
     */
    public Ubigeo(String idUbigeo, String descripcion, Ubigeo ubigeoByDependencia,String tipo ) {
        super();
        this.idUbigeo = idUbigeo;
        this.descripcion = descripcion;
        this.ubigeoByDependencia = ubigeoByDependencia;
        this.tipo = tipo;
    }
   
    //get y set
    /**
     * Obtiene id ubigeo.
     *
     * @return id ubigeo
     */
     public String getIdUbigeo() {
        return this.idUbigeo;
    }
    /**
     * Establece el id ubigeo.
     *
     * @param idUbigeo el new id ubigeo
     */
    public void setIdUbigeo(String idUbigeo) {
        this.idUbigeo = idUbigeo;
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
     * Obtiene ubigeo by dependencia.
     *
     * @return ubigeo by dependencia
     */
     public Ubigeo getUbigeoByDependencia() {
        return this.ubigeoByDependencia;
    }
    /**
     * Establece el ubigeo by dependencia.
     *
     * @param ubigeoByDependencia el new ubigeo by dependencia
     */
    public void setUbigeoByDependencia(Ubigeo ubigeoByDependencia) {
        this.ubigeoByDependencia = ubigeoByDependencia;
    }
    /**
     * Obtiene tipo.
     *
     * @return tipo
     */
     public String getTipo() {
        return this.tipo;
    }
    /**
     * Establece el tipo.
     *
     * @param tipo el new tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    /**
     * Establece el ubigeo by dependencia ubigeo list.
     *
     * @param ubigeoByDependenciaUbigeoList el new ubigeo by dependencia ubigeo list
     */
    public List<Ubigeo> getUbigeoByDependenciaUbigeoList(){
        return this.ubigeoByDependenciaUbigeoList;
    }
    /**
     * Establece el ubigeo by dependencia list.
     *
     * @param ubigeoByDependenciaList el new ubigeo by dependencia list
     */
    public void setUbigeoByDependenciaUbigeoList(List<Ubigeo> ubigeoByDependenciaUbigeoList) {
        this.ubigeoByDependenciaUbigeoList = ubigeoByDependenciaUbigeoList;
    }   
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idUbigeo == null) ? 0 : idUbigeo.hashCode());
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
        Ubigeo other = (Ubigeo) obj;
        if (idUbigeo == null) {
            if (other.idUbigeo != null) {
                return false;
            }
        } else if (!idUbigeo.equals(other.idUbigeo)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Ubigeo [idUbigeo=" + idUbigeo + "]";
    }
   
}