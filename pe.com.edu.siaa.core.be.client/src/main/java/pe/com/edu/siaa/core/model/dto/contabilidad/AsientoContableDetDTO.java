package pe.com.edu.siaa.core.model.dto.contabilidad;

import java.io.Serializable;
import java.math.BigDecimal;

import pe.com.edu.siaa.core.model.dto.BasePaginator;

/**
 * La Class AsientoContableDetDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Wed Dec 20 10:42:11 COT 2017
 * @since SIAA-CORE 2.1
 */
public class AsientoContableDetDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id asiento contable det. */
    private String idAsientoContableDet;
   
    /** El asiento contable. */
    private AsientoContableDTO asientoContable;
   
    /** El plan contable. */
    private PlanContableDTO planContable;
   
    /** El nro cuenta. */
    private String nroCuenta;
   
    /** El descripcion cuenta. */
    private String descripcionCuenta;
   
    /** El monto. */
    private BigDecimal monto;
   
    /** El tipo. */
    private String tipo;
    
    //trasient
    private BigDecimal debe;
    private BigDecimal haber;
   
    /**
     * Instancia un nuevo asiento contable detDTO.
     */
    public AsientoContableDetDTO() {
    }
   
   
    /**
     * Instancia un nuevo asiento contable detDTO.
     *
     * @param idAsientoContableDet el id asiento contable det
     * @param asientoContable el asiento contable
     * @param planContable el plan contable
     * @param nroCuenta el nro cuenta
     * @param descripcionCuenta el descripcion cuenta
     * @param monto el monto
     * @param tipo el tipo
     */
    public AsientoContableDetDTO(String idAsientoContableDet, AsientoContableDTO asientoContable,PlanContableDTO planContable,String nroCuenta, String descripcionCuenta, BigDecimal monto, String tipo ) {
        super();
        this.idAsientoContableDet = idAsientoContableDet;
        this.asientoContable = asientoContable;
        this.planContable = planContable;
        this.nroCuenta = nroCuenta;
        this.descripcionCuenta = descripcionCuenta;
        this.monto = monto;
        this.tipo = tipo;
    }
   
    //get y set
    /**
     * Obtiene id asiento contable det.
     *
     * @return id asiento contable det
     */
     public String getIdAsientoContableDet() {
        return this.idAsientoContableDet;
    }
    /**
     * Establece el id asiento contable det.
     *
     * @param idAsientoContableDet el new id asiento contable det
     */
    public void setIdAsientoContableDet(String idAsientoContableDet) {
        this.idAsientoContableDet = idAsientoContableDet;
    }
    /**
     * Obtiene asiento contable.
     *
     * @return asiento contable
     */
     public AsientoContableDTO getAsientoContable() {
        return this.asientoContable;
    }
    /**
     * Establece el asiento contable.
     *
     * @param asientoContable el new asiento contable
     */
    public void setAsientoContable(AsientoContableDTO asientoContable) {
        this.asientoContable = asientoContable;
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
     * Obtiene nro cuenta.
     *
     * @return nro cuenta
     */
     public String getNroCuenta() {
        return this.nroCuenta;
    }
    /**
     * Establece el nro cuenta.
     *
     * @param nroCuenta el new nro cuenta
     */
    public void setNroCuenta(String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }
    /**
     * Obtiene descripcion cuenta.
     *
     * @return descripcion cuenta
     */
     public String getDescripcionCuenta() {
        return this.descripcionCuenta;
    }
    /**
     * Establece el descripcion cuenta.
     *
     * @param descripcionCuenta el new descripcion cuenta
     */
    public void setDescripcionCuenta(String descripcionCuenta) {
        this.descripcionCuenta = descripcionCuenta;
    }
    /**
     * Obtiene monto.
     *
     * @return monto
     */
     public BigDecimal getMonto() {
        return this.monto;
    }
    /**
     * Establece el monto.
     *
     * @param monto el new monto
     */
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
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
    
    
    public BigDecimal getDebe() {
		return debe;
	}


	public void setDebe(BigDecimal debe) {
		this.debe = debe;
	}


	public BigDecimal getHaber() {
		return haber;
	}


	public void setHaber(BigDecimal haber) {
		this.haber = haber;
	}


	/* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idAsientoContableDet == null) ? 0 : idAsientoContableDet.hashCode());
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
        AsientoContableDetDTO other = (AsientoContableDetDTO) obj;
        if (idAsientoContableDet == null) {
            if (other.idAsientoContableDet != null) {
                return false;
            }
        } else if (!idAsientoContableDet.equals(other.idAsientoContableDet)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AsientoContableDetDTO [idAsientoContableDet=" + idAsientoContableDet + "]";
    }
   
}