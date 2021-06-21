package pe.com.builderp.core.facturacion.model.dto.empresa;

import java.io.Serializable;
import java.math.BigDecimal; 
import java.util.Date; 

import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.type.FlagConceptoPagoFraccionadoType;

/**
 * La Class DetPlanPagosDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Tue Apr 18 09:56:45 COT 2017
 * @since SIAA-CORE 2.1
 */
public class DetPlanPagosDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id det plan pagos. */
    private String idDetPlanPagos;
   
    /** El plan pagos. */
    private PlanPagosDTO planPagos;
   
    /** El cuota concepto. */
    private CuotaConceptoDTO cuotaConcepto;
   
    /** El cuota. */
    private BigDecimal cuota;
   
    /** El monto resta. */
    private BigDecimal montoResta;
   
    /** El fecha vencimiento. */
    private Date fechaVencimiento;
   
    /** El flag fraccionado. */
    private String flagFraccionado;
    
    /** El nro. */
    private String nro;
    
    private String idPuesto;
    
    /** El flag falta monto resta. */
	private boolean flagFaltaMontoResta;
	
	

	
    //
    private Long numeroDiasRetrazo = 0L;
    
    
    /** La flag fraccionado descripcion. */
	private String flagFraccionadoDescripcion;
	
   
    /**
     * Instancia un nuevo det plan pagosDTO.
     */
    public DetPlanPagosDTO() {
    }
   
   
    /**
     * Instancia un nuevo det plan pagosDTO.
     *
     * @param idDetPlanPagos el id det plan pagos
     * @param planPagos el plan pagos
     * @param cuotaConcepto el cuota concepto
     * @param cuota el cuota
     * @param montoResta el monto resta
     * @param fechaVencimiento el fecha vencimiento
     * @param flagFraccionado el flag fraccionado
     */
    public DetPlanPagosDTO(String idDetPlanPagos, PlanPagosDTO planPagos,CuotaConceptoDTO cuotaConcepto,BigDecimal cuota, BigDecimal montoResta, Date fechaVencimiento, 
    		String flagFraccionado,String nro ,String idPuesto) {
        super();
        this.idDetPlanPagos = idDetPlanPagos;
        this.planPagos = planPagos;
        this.cuotaConcepto = cuotaConcepto;
        this.cuota = cuota;
        this.montoResta = montoResta;
        this.fechaVencimiento = fechaVencimiento;
        this.flagFraccionado = flagFraccionado;
        this.nro=nro;
        this.idPuesto=idPuesto;
    }
   
    //get y set
    
    
    /**
     * Obtiene id det plan pagos.
     *
     * @return id det plan pagos
     */
     public String getIdDetPlanPagos() {
        return this.idDetPlanPagos;
    }
    public String getIdPuesto() {
		return idPuesto;
	}


	public void setIdPuesto(String idPuesto) {
		this.idPuesto = idPuesto;
	}


	public String getNro() {
		return nro;
	}


	public void setNro(String nro) {
		this.nro = nro;
	}


	/**
     * Establece el id det plan pagos.
     *
     * @param idDetPlanPagos el new id det plan pagos
     */
    public void setIdDetPlanPagos(String idDetPlanPagos) {
        this.idDetPlanPagos = idDetPlanPagos;
    }
    /**
     * Obtiene plan pagos.
     *
     * @return plan pagos
     */
     public PlanPagosDTO getPlanPagos() {
        return this.planPagos;
    }
    /**
     * Establece el plan pagos.
     *
     * @param planPagos el new plan pagos
     */
    public void setPlanPagos(PlanPagosDTO planPagos) {
        this.planPagos = planPagos;
    }
    /**
     * Obtiene cuota concepto.
     *
     * @return cuota concepto
     */
     public CuotaConceptoDTO getCuotaConcepto() {
        return this.cuotaConcepto;
    }
    /**
     * Establece el cuota concepto.
     *
     * @param cuotaConcepto el new cuota concepto
     */
    public void setCuotaConcepto(CuotaConceptoDTO cuotaConcepto) {
        this.cuotaConcepto = cuotaConcepto;
    }
    /**
     * Obtiene cuota.
     *
     * @return cuota
     */
     public BigDecimal getCuota() {
        return this.cuota;
    }
    /**
     * Establece el cuota.
     *
     * @param cuota el new cuota
     */
    public void setCuota(BigDecimal cuota) {
        this.cuota = cuota;
    }
    /**
     * Obtiene monto resta.
     *
     * @return monto resta
     */
     public BigDecimal getMontoResta() {
        return this.montoResta;
    }
    /**
     * Establece el monto resta.
     *
     * @param montoResta el new monto resta
     */
    public void setMontoResta(BigDecimal montoResta) {
        this.montoResta = montoResta;
    }
    /**
     * Obtiene fecha vencimiento.
     *
     * @return fecha vencimiento
     */
     public Date getFechaVencimiento() {
        return this.fechaVencimiento;
    }
    /**
     * Establece el fecha vencimiento.
     *
     * @param fechaVencimiento el new fecha vencimiento
     */
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
    /**
     * Obtiene flag fraccionado.
     *
     * @return flag fraccionado
     */
     public String getFlagFraccionado() {
        return this.flagFraccionado;
    }
    /**
     * Establece el flag fraccionado.
     *
     * @param flagFraccionado el new flag fraccionado
     */
    public void setFlagFraccionado(String flagFraccionado) {
        this.flagFraccionado = flagFraccionado;
    }
 

	public boolean isFlagFaltaMontoResta() {
		return flagFaltaMontoResta;
	}


	public void setFlagFaltaMontoResta(boolean flagFaltaMontoResta) {
		this.flagFaltaMontoResta = flagFaltaMontoResta;
	}


	public Long getNumeroDiasRetrazo() {
		return numeroDiasRetrazo;
	}


	public void setNumeroDiasRetrazo(Long numeroDiasRetrazo) {
		this.numeroDiasRetrazo = numeroDiasRetrazo;
	}

	
	public String getFlagFraccionadoDescripcion() {
		if (flagFraccionado != null) {
			if (FlagConceptoPagoFraccionadoType.get(flagFraccionado) != null) {
				flagFraccionadoDescripcion = FlagConceptoPagoFraccionadoType.get(flagFraccionado).getValue();
			}
		}
		return flagFraccionadoDescripcion;
	}

	/**
	 * Establece el flag fraccionado descripcion.
	 *
	 * @param flagFraccionadoDescripcion el new flag fraccionado descripcion
	 */
	public void setFlagFraccionadoDescripcion(String flagFraccionadoDescripcion) {
		this.flagFraccionadoDescripcion = flagFraccionadoDescripcion;
	}

	/* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idDetPlanPagos == null) ? 0 : idDetPlanPagos.hashCode());
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
        DetPlanPagosDTO other = (DetPlanPagosDTO) obj;
        if (idDetPlanPagos == null) {
            if (other.idDetPlanPagos != null) {
                return false;
            }
        } else if (!idDetPlanPagos.equals(other.idDetPlanPagos)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DetPlanPagosDTO [idDetPlanPagos=" + idDetPlanPagos + "]";
    }
   
}