package pe.com.edu.siaa.core.model.jpa.contabilidad;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class AsientoContableDet.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Wed Dec 20 00:30:24 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "AsientoContableDet", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CONTABILIDAD)
public class AsientoContableDet implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id asiento contable det. */
    @Id
    @Column(name = "idAsientoContableDet" , length = 32)
    private String idAsientoContableDet;
   
    /** El asiento contable. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAsientoContable", referencedColumnName = "idAsientoContable")
    private AsientoContable asientoContable;
   
    /** El plan contable. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPlanContable", referencedColumnName = "idPlanContable")
    private PlanContable planContable;
   
    /** El nro cuenta. */
    @Column(name = "nroCuenta" , length = 20)
    private String nroCuenta;
   
    /** El descripcion cuenta. */
    @Column(name = "descripcionCuenta" , length = 200)
    private String descripcionCuenta;
   
    /** El monto. */
    @Column(name = "monto" , precision = 18 , scale = 2)
    private BigDecimal monto;
   
    /** El tipo. */
    @Column(name = "tipo" , length = 1)
    private String tipo;
   
    /**
     * Instancia un nuevo asiento contable det.
     */
    public AsientoContableDet() {
    }
   
   
    /**
     * Instancia un nuevo asiento contable det.
     *
     * @param idAsientoContableDet el id asiento contable det
     * @param asientoContable el asiento contable
     * @param planContable el plan contable
     * @param nroCuenta el nro cuenta
     * @param descripcionCuenta el descripcion cuenta
     * @param monto el monto
     * @param tipo el tipo
     */
    public AsientoContableDet(String idAsientoContableDet, AsientoContable asientoContable,PlanContable planContable,String nroCuenta, String descripcionCuenta, BigDecimal monto, String tipo ) {
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
     public AsientoContable getAsientoContable() {
        return this.asientoContable;
    }
    /**
     * Establece el asiento contable.
     *
     * @param asientoContable el new asiento contable
     */
    public void setAsientoContable(AsientoContable asientoContable) {
        this.asientoContable = asientoContable;
    }
    /**
     * Obtiene plan contable.
     *
     * @return plan contable
     */
     public PlanContable getPlanContable() {
        return this.planContable;
    }
    /**
     * Establece el plan contable.
     *
     * @param planContable el new plan contable
     */
    public void setPlanContable(PlanContable planContable) {
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
        AsientoContableDet other = (AsientoContableDet) obj;
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
        return "AsientoContableDet [idAsientoContableDet=" + idAsientoContableDet + "]";
    }
   
}