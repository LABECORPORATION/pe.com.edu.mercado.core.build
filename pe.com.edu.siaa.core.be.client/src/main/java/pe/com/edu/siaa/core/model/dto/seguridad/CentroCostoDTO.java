package pe.com.edu.siaa.core.model.dto.seguridad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.contabilidad.PlanContableDTO;
 

/**
 * La Class CentroCostoDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Wed Dec 20 10:42:11 COT 2017
 * @since SIAA-CORE 2.1
 */
public class CentroCostoDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id centro costo. */
    private String idCentroCosto;
   
    /** El entidad. */
    private EntidadDTO entidad;
   
    /** El codigo. */
    private String codigo;
   
    /** El descripcion. */
    private String descripcion;
   
    /** El abreviatura. */
    private String abreviatura;
   
    /** El centro costo padre. */
    private CentroCostoDTO centroCostoPadre;
   
    /** El plan contable. */
    private PlanContableDTO planContable;
   
    /** El estado. */
    private String estado;
   
    /** El centro costo padre centro costo list. */
    private List<CentroCostoDTO> centroCostoPadreCentroCostoList = new ArrayList<CentroCostoDTO>();
   
    /**
     * Instancia un nuevo centro costoDTO.
     */
    public CentroCostoDTO() {
    }
   
   
    /**
     * Instancia un nuevo centro costoDTO.
     *
     * @param idCentroCosto el id centro costo
     * @param entidad el entidad
     * @param codigo el codigo
     * @param descripcion el descripcion
     * @param abreviatura el abreviatura
     * @param centroCostoPadre el centro costo padre
     * @param planContable el plan contable
     * @param estado el estado
     */
    public CentroCostoDTO(String idCentroCosto, EntidadDTO entidad,String codigo, String descripcion, String abreviatura, CentroCostoDTO centroCostoPadre,PlanContableDTO planContable,String estado ) {
        super();
        this.idCentroCosto = idCentroCosto;
        this.entidad = entidad;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
        this.centroCostoPadre = centroCostoPadre;
        this.planContable = planContable;
        this.estado = estado;
    }
   
    //get y set
    /**
     * Obtiene id centro costo.
     *
     * @return id centro costo
     */
     public String getIdCentroCosto() {
        return this.idCentroCosto;
    }
    /**
     * Establece el id centro costo.
     *
     * @param idCentroCosto el new id centro costo
     */
    public void setIdCentroCosto(String idCentroCosto) {
        this.idCentroCosto = idCentroCosto;
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
     * Obtiene abreviatura.
     *
     * @return abreviatura
     */
     public String getAbreviatura() {
        return this.abreviatura;
    }
    /**
     * Establece el abreviatura.
     *
     * @param abreviatura el new abreviatura
     */
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
    /**
     * Obtiene centro costo padre.
     *
     * @return centro costo padre
     */
     public CentroCostoDTO getCentroCostoPadre() {
        return this.centroCostoPadre;
    }
    /**
     * Establece el centro costo padre.
     *
     * @param centroCostoPadre el new centro costo padre
     */
    public void setCentroCostoPadre(CentroCostoDTO centroCostoPadre) {
        this.centroCostoPadre = centroCostoPadre;
    }
    /**
     * Obtiene plan contable.
     *
     * @return plan contable
     */
     public PlanContableDTO getPlanContable() {
        return this.planContable;
    }
    /**
     * Establece el plan contable.
     *
     * @param planContable el new plan contable
     */
    public void setPlanContable(PlanContableDTO planContable) {
        this.planContable = planContable;
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
     * Establece el centro costo padre centro costo list.
     *
     * @param centroCostoPadreCentroCostoList el new centro costo padre centro costo list
     */
    public List<CentroCostoDTO> getCentroCostoPadreCentroCostoList(){
        return this.centroCostoPadreCentroCostoList;
    }
    /**
     * Establece el centro costo padre list.
     *
     * @param centroCostoPadreList el new centro costo padre list
     */
    public void setCentroCostoPadreCentroCostoList(List<CentroCostoDTO> centroCostoPadreCentroCostoList) {
        this.centroCostoPadreCentroCostoList = centroCostoPadreCentroCostoList;
    }   
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idCentroCosto == null) ? 0 : idCentroCosto.hashCode());
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
        CentroCostoDTO other = (CentroCostoDTO) obj;
        if (idCentroCosto == null) {
            if (other.idCentroCosto != null) {
                return false;
            }
        } else if (!idCentroCosto.equals(other.idCentroCosto)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CentroCostoDTO [idCentroCosto=" + idCentroCosto + "]";
    }
   
}