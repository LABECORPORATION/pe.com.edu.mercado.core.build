package pe.com.edu.siaa.core.model.jpa.seguridad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class AgrupaEntidad.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Wed Dec 20 00:30:21 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "AgrupaEntidad", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_SECURIDAD)
public class AgrupaEntidad implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id agrupa entidad. */
    @Id
    @Column(name = "idAgrupaEntidad" , length = 32)
    private String idAgrupaEntidad;
   
    /** El entidad. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEntidad", referencedColumnName = "idEntidad")
    private Entidad entidad;
   
    /** El entidad agrupa. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEntidadAgrupa", referencedColumnName = "idEntidad")
    private Entidad entidadAgrupa;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
   
    /**
     * Instancia un nuevo agrupa entidad.
     */
    public AgrupaEntidad() {
    }
   
   
    /**
     * Instancia un nuevo agrupa entidad.
     *
     * @param idAgrupaEntidad el id agrupa entidad
     * @param entidad el entidad
     * @param entidadAgrupa el entidad agrupa
     * @param estado el estado
     */
    public AgrupaEntidad(String idAgrupaEntidad, Entidad entidad,Entidad entidadAgrupa,String estado ) {
        super();
        this.idAgrupaEntidad = idAgrupaEntidad;
        this.entidad = entidad;
        this.entidadAgrupa = entidadAgrupa;
        this.estado = estado;
    }
   
    //get y set
    /**
     * Obtiene id agrupa entidad.
     *
     * @return id agrupa entidad
     */
     public String getIdAgrupaEntidad() {
        return this.idAgrupaEntidad;
    }
    /**
     * Establece el id agrupa entidad.
     *
     * @param idAgrupaEntidad el new id agrupa entidad
     */
    public void setIdAgrupaEntidad(String idAgrupaEntidad) {
        this.idAgrupaEntidad = idAgrupaEntidad;
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
     * Obtiene entidad agrupa.
     *
     * @return entidad agrupa
     */
     public Entidad getEntidadAgrupa() {
        return this.entidadAgrupa;
    }
    /**
     * Establece el entidad agrupa.
     *
     * @param entidadAgrupa el new entidad agrupa
     */
    public void setEntidadAgrupa(Entidad entidadAgrupa) {
        this.entidadAgrupa = entidadAgrupa;
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
                + ((idAgrupaEntidad == null) ? 0 : idAgrupaEntidad.hashCode());
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
        AgrupaEntidad other = (AgrupaEntidad) obj;
        if (idAgrupaEntidad == null) {
            if (other.idAgrupaEntidad != null) {
                return false;
            }
        } else if (!idAgrupaEntidad.equals(other.idAgrupaEntidad)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AgrupaEntidad [idAgrupaEntidad=" + idAgrupaEntidad + "]";
    }
   
}