package pe.com.builderp.core.facturacion.model.dto.venta;

import java.io.Serializable;

import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.EntidadDTO;
 

/**
 * La Class TipoDocSunatEntidadDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Fri Dec 15 11:57:56 COT 2017
 * @since SIAA-CORE 2.1
 */
public class TipoDocSunatEntidadDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id tipo doc sunat entidad. */
    private String idTipoDocSunatEntidad;
   
    /** El item by tipo doc sunat. */
    private ItemDTO itemByTipoDocSunat;
   
    /** El entidad. */
    private EntidadDTO entidad;
   
    /** El correla. */
    private String correla;
    
    /** El serie. */
    private String serie;
   
    /**
     * Instancia un nuevo tipo doc sunat entidadDTO.
     */
    public TipoDocSunatEntidadDTO() {
    }
   
   
    /**
     * Instancia un nuevo tipo doc sunat entidadDTO.
     *
     * @param idTipoDocSunatEntidad el id tipo doc sunat entidad
     * @param itemByTipoDocSunat el item by tipo doc sunat
     * @param entidad el entidad
     * @param correla el correla
     */
    public TipoDocSunatEntidadDTO(String idTipoDocSunatEntidad, ItemDTO itemByTipoDocSunat,EntidadDTO entidad,String correla ,String serie) {
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
     public ItemDTO getItemByTipoDocSunat() {
        return this.itemByTipoDocSunat;
    }
    /**
     * Establece el item by tipo doc sunat.
     *
     * @param itemByTipoDocSunat el new item by tipo doc sunat
     */
    public void setItemByTipoDocSunat(ItemDTO itemByTipoDocSunat) {
        this.itemByTipoDocSunat = itemByTipoDocSunat;
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
        TipoDocSunatEntidadDTO other = (TipoDocSunatEntidadDTO) obj;
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
        return "TipoDocSunatEntidadDTO [idTipoDocSunatEntidad=" + idTipoDocSunatEntidad + "]";
    }
   
}