package pe.com.edu.siaa.core.model.dto.contabilidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pe.com.builderp.core.facturacion.model.dto.compra.CuentaTipoDocumentoDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.ProductoDTO;
import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.seguridad.CentroCostoDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.EntidadDTO;
 

/**
 * La Class PlanContableDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Wed Dec 20 10:42:12 COT 2017
 * @since SIAA-CORE 2.1
 */
public class PlanContableDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id plan contable. */
    private String idPlanContable;
   
    /** El codigo. */
    private String codigo;
   
    /** El nombre. */
    private String nombre;
   
    /** El plan contable depedencia. */
    private PlanContableDTO planContableDepedencia;
   
    /** El plan contable debe. */
    private PlanContableDTO planContableDebe;
   
    /** El plan contable haber. */
    private PlanContableDTO planContableHaber;
   
    /** El entidad. */
    private EntidadDTO entidad;
   
    /** El anho. */
    private Long anho;
   
    /** El estado. */
    private String estado;
   
    /** El plan contable depedencia plan contable list. */
    private List<PlanContableDTO> planContableDepedenciaPlanContableList = new ArrayList<PlanContableDTO>();
   
    /** El plan contable debe plan contable list. */
    private List<PlanContableDTO> planContableDebePlanContableList = new ArrayList<PlanContableDTO>();
   
    /** El plan contable haber plan contable list. */
    private List<PlanContableDTO> planContableHaberPlanContableList = new ArrayList<PlanContableDTO>();
   
    /** El plan contable configuracion cuenta list. */
    private List<ConfiguracionCuentaDTO> planContableConfiguracionCuentaList = new ArrayList<ConfiguracionCuentaDTO>();
   
    /** El plan contable asiento contable det list. */
    private List<AsientoContableDetDTO> planContableAsientoContableDetList = new ArrayList<AsientoContableDetDTO>();
   
    /** El plan contable centro costo list. */
    private List<CentroCostoDTO> planContableCentroCostoList = new ArrayList<CentroCostoDTO>();
   
    /** El plan contable producto list. */
    private List<ProductoDTO> planContableProductoList = new ArrayList<ProductoDTO>();
   
    /** El id plan contable cuenta tipo documento list. */
    private List<CuentaTipoDocumentoDTO> idPlanContableCuentaTipoDocumentoList = new ArrayList<CuentaTipoDocumentoDTO>();
   
    /**
     * Instancia un nuevo plan contableDTO.
     */
    public PlanContableDTO() {
    }
   
   
    /**
     * Instancia un nuevo plan contableDTO.
     *
     * @param idPlanContable el id plan contable
     * @param codigo el codigo
     * @param nombre el nombre
     * @param planContableDepedencia el plan contable depedencia
     * @param planContableDebe el plan contable debe
     * @param planContableHaber el plan contable haber
     * @param entidad el entidad
     * @param anho el anho
     * @param estado el estado
     */
    public PlanContableDTO(String idPlanContable, String codigo, String nombre, PlanContableDTO planContableDepedencia,PlanContableDTO planContableDebe,PlanContableDTO planContableHaber,EntidadDTO entidad,Long anho, String estado ) {
        super();
        this.idPlanContable = idPlanContable;
        this.codigo = codigo;
        this.nombre = nombre;
        this.planContableDepedencia = planContableDepedencia;
        this.planContableDebe = planContableDebe;
        this.planContableHaber = planContableHaber;
        this.entidad = entidad;
        this.anho = anho;
        this.estado = estado;
    }
   
    //get y set
    /**
     * Obtiene id plan contable.
     *
     * @return id plan contable
     */
     public String getIdPlanContable() {
        return this.idPlanContable;
    }
    /**
     * Establece el id plan contable.
     *
     * @param idPlanContable el new id plan contable
     */
    public void setIdPlanContable(String idPlanContable) {
        this.idPlanContable = idPlanContable;
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
     * Obtiene plan contable depedencia.
     *
     * @return plan contable depedencia
     */
     public PlanContableDTO getPlanContableDepedencia() {
        return this.planContableDepedencia;
    }
    /**
     * Establece el plan contable depedencia.
     *
     * @param planContableDepedencia el new plan contable depedencia
     */
    public void setPlanContableDepedencia(PlanContableDTO planContableDepedencia) {
        this.planContableDepedencia = planContableDepedencia;
    }
    /**
     * Obtiene plan contable debe.
     *
     * @return plan contable debe
     */
     public PlanContableDTO getPlanContableDebe() {
        return this.planContableDebe;
    }
    /**
     * Establece el plan contable debe.
     *
     * @param planContableDebe el new plan contable debe
     */
    public void setPlanContableDebe(PlanContableDTO planContableDebe) {
        this.planContableDebe = planContableDebe;
    }
    /**
     * Obtiene plan contable haber.
     *
     * @return plan contable haber
     */
     public PlanContableDTO getPlanContableHaber() {
        return this.planContableHaber;
    }
    /**
     * Establece el plan contable haber.
     *
     * @param planContableHaber el new plan contable haber
     */
    public void setPlanContableHaber(PlanContableDTO planContableHaber) {
        this.planContableHaber = planContableHaber;
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
     * Obtiene anho.
     *
     * @return anho
     */
     public Long getAnho() {
        return this.anho;
    }
    /**
     * Establece el anho.
     *
     * @param anho el new anho
     */
    public void setAnho(Long anho) {
        this.anho = anho;
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
     * Establece el plan contable depedencia plan contable list.
     *
     * @param planContableDepedenciaPlanContableList el new plan contable depedencia plan contable list
     */
    public List<PlanContableDTO> getPlanContableDepedenciaPlanContableList(){
        return this.planContableDepedenciaPlanContableList;
    }
    /**
     * Establece el plan contable depedencia list.
     *
     * @param planContableDepedenciaList el new plan contable depedencia list
     */
    public void setPlanContableDepedenciaPlanContableList(List<PlanContableDTO> planContableDepedenciaPlanContableList) {
        this.planContableDepedenciaPlanContableList = planContableDepedenciaPlanContableList;
    }   
    /**
     * Establece el plan contable debe plan contable list.
     *
     * @param planContableDebePlanContableList el new plan contable debe plan contable list
     */
    public List<PlanContableDTO> getPlanContableDebePlanContableList(){
        return this.planContableDebePlanContableList;
    }
    /**
     * Establece el plan contable debe list.
     *
     * @param planContableDebeList el new plan contable debe list
     */
    public void setPlanContableDebePlanContableList(List<PlanContableDTO> planContableDebePlanContableList) {
        this.planContableDebePlanContableList = planContableDebePlanContableList;
    }   
    /**
     * Establece el plan contable haber plan contable list.
     *
     * @param planContableHaberPlanContableList el new plan contable haber plan contable list
     */
    public List<PlanContableDTO> getPlanContableHaberPlanContableList(){
        return this.planContableHaberPlanContableList;
    }
    /**
     * Establece el plan contable haber list.
     *
     * @param planContableHaberList el new plan contable haber list
     */
    public void setPlanContableHaberPlanContableList(List<PlanContableDTO> planContableHaberPlanContableList) {
        this.planContableHaberPlanContableList = planContableHaberPlanContableList;
    }   
    /**
     * Establece el plan contable configuracion cuenta list.
     *
     * @param planContableConfiguracionCuentaList el new plan contable configuracion cuenta list
     */
    public List<ConfiguracionCuentaDTO> getPlanContableConfiguracionCuentaList(){
        return this.planContableConfiguracionCuentaList;
    }
    /**
     * Establece el plan contable list.
     *
     * @param planContableList el new plan contable list
     */
    public void setPlanContableConfiguracionCuentaList(List<ConfiguracionCuentaDTO> planContableConfiguracionCuentaList) {
        this.planContableConfiguracionCuentaList = planContableConfiguracionCuentaList;
    }   
    /**
     * Establece el plan contable asiento contable det list.
     *
     * @param planContableAsientoContableDetList el new plan contable asiento contable det list
     */
    public List<AsientoContableDetDTO> getPlanContableAsientoContableDetList(){
        return this.planContableAsientoContableDetList;
    }
    /**
     * Establece el plan contable list.
     *
     * @param planContableList el new plan contable list
     */
    public void setPlanContableAsientoContableDetList(List<AsientoContableDetDTO> planContableAsientoContableDetList) {
        this.planContableAsientoContableDetList = planContableAsientoContableDetList;
    }   
    /**
     * Establece el plan contable centro costo list.
     *
     * @param planContableCentroCostoList el new plan contable centro costo list
     */
    public List<CentroCostoDTO> getPlanContableCentroCostoList(){
        return this.planContableCentroCostoList;
    }
    /**
     * Establece el plan contable list.
     *
     * @param planContableList el new plan contable list
     */
    public void setPlanContableCentroCostoList(List<CentroCostoDTO> planContableCentroCostoList) {
        this.planContableCentroCostoList = planContableCentroCostoList;
    }   
    /**
     * Establece el plan contable producto list.
     *
     * @param planContableProductoList el new plan contable producto list
     */
    public List<ProductoDTO> getPlanContableProductoList(){
        return this.planContableProductoList;
    }
    /**
     * Establece el plan contable list.
     *
     * @param planContableList el new plan contable list
     */
    public void setPlanContableProductoList(List<ProductoDTO> planContableProductoList) {
        this.planContableProductoList = planContableProductoList;
    }   
    /**
     * Establece el id plan contable cuenta tipo documento list.
     *
     * @param idPlanContableCuentaTipoDocumentoList el new id plan contable cuenta tipo documento list
     */
    public List<CuentaTipoDocumentoDTO> getIdPlanContableCuentaTipoDocumentoList(){
        return this.idPlanContableCuentaTipoDocumentoList;
    }
    /**
     * Establece el id plan contable list.
     *
     * @param idPlanContableList el new id plan contable list
     */
    public void setIdPlanContableCuentaTipoDocumentoList(List<CuentaTipoDocumentoDTO> idPlanContableCuentaTipoDocumentoList) {
        this.idPlanContableCuentaTipoDocumentoList = idPlanContableCuentaTipoDocumentoList;
    }   
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idPlanContable == null) ? 0 : idPlanContable.hashCode());
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
        PlanContableDTO other = (PlanContableDTO) obj;
        if (idPlanContable == null) {
            if (other.idPlanContable != null) {
                return false;
            }
        } else if (!idPlanContable.equals(other.idPlanContable)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PlanContableDTO [idPlanContable=" + idPlanContable + "]";
    }
   
}