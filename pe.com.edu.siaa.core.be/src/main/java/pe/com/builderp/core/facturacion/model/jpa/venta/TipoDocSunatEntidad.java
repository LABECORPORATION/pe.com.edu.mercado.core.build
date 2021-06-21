package pe.com.builderp.core.facturacion.model.jpa.venta;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pe.com.edu.siaa.core.model.jpa.common.Item;
import pe.com.edu.siaa.core.model.jpa.seguridad.Entidad;
import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class TipoDocSunatEntidad.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Fri Dec 15 11:04:47 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "TipoDocSunatEntidad", schema = ConfiguracionEntityManagerUtil.ESQUEMA_FACTURACION)
public class TipoDocSunatEntidad implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id tipo doc sunat entidad. */
    @Id
    @Column(name = "idTipoDocSunatEntidad" , length = 32)
    private String idTipoDocSunatEntidad;
   
    /** El item by tipo doc sunat. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoDocSunat", referencedColumnName = "idItem")
    private Item itemByTipoDocSunat;
   
    /** El entidad. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEntidad", referencedColumnName = "idEntidad")
    private Entidad entidad;
   
    /** El correla. */
    @Column(name = "correla" , length = 50)
    private String correla;
    
    /** El serie. */
    @Column(name = "serie" , length = 4)
    private String serie;
   
    /**
     * Instancia un nuevo tipo doc sunat entidad.
     */
    public TipoDocSunatEntidad() {
    }
   
   
    /**
     * Instancia un nuevo tipo doc sunat entidad.
     *
     * @param idTipoDocSunatEntidad el id tipo doc sunat entidad
     * @param itemByTipoDocSunat el item by tipo doc sunat
     * @param entidad el entidad
     * @param correla el correla
     */
    public TipoDocSunatEntidad(String idTipoDocSunatEntidad, Item itemByTipoDocSunat,Entidad entidad,String correla, String serie ) {
        super();
        this.idTipoDocSunatEntidad = idTipoDocSunatEntidad;
        this.itemByTipoDocSunat = itemByTipoDocSunat;
        this.entidad = entidad;
        this.correla = correla;
        this.serie = serie;
    }
   
    //get y set
    /**
     * Obtiene id tipo doc sunat entidad.
     *
     * @return id tipo doc sunat entidad
     */
     public String getIdTipoDocSunatEntidad() {
        return this.idTipoDocSunatEntidad;
    }
    /**
     * Establece el id tipo doc sunat entidad.
     *
     * @param idTipoDocSunatEntidad el new id tipo doc sunat entidad
     */
    public void setIdTipoDocSunatEntidad(String idTipoDocSunatEntidad) {
        this.idTipoDocSunatEntidad = idTipoDocSunatEntidad;
    }
    /**
     * Obtiene item by tipo doc sunat.
     *
     * @return item by tipo doc sunat
     */
     public Item getItemByTipoDocSunat() {
        return this.itemByTipoDocSunat;
    }
    /**
     * Establece el item by tipo doc sunat.
     *
     * @param itemByTipoDocSunat el new item by tipo doc sunat
     */
    public void setItemByTipoDocSunat(Item itemByTipoDocSunat) {
        this.itemByTipoDocSunat = itemByTipoDocSunat;
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
    
    
    
    
    public String getSerie() {
		return serie;
	}


	public void setSerie(String serie) {
		this.serie = serie;
	}


	/**
     * Obtiene correla.
     *
     * @return correla
     */
     public String getCorrela() {
        return this.correla;
    }
    /**
     * Establece el correla.
     *
     * @param correla el new correla
     */
    public void setCorrela(String correla) {
        this.correla = correla;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idTipoDocSunatEntidad == null) ? 0 : idTipoDocSunatEntidad.hashCode());
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
        TipoDocSunatEntidad other = (TipoDocSunatEntidad) obj;
        if (idTipoDocSunatEntidad == null) {
            if (other.idTipoDocSunatEntidad != null) {
                return false;
            }
        } else if (!idTipoDocSunatEntidad.equals(other.idTipoDocSunatEntidad)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TipoDocSunatEntidad [idTipoDocSunatEntidad=" + idTipoDocSunatEntidad + "]";
    }
   
}