package pe.com.edu.siaa.core.model.jpa.empresa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class DetPlanPagos.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 09:56:45 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "DetPlanPagos", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_EMPRESA)
public class DetPlanPagos implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id det plan pagos. */
    @Id
    @Column(name = "idDetPlanPagos" , length = 40)
    private String idDetPlanPagos;
   
    /** El plan pagos. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPlanPagos", referencedColumnName = "idPlanPagos")
    private PlanPagos planPagos;
   
    /** El cuota concepto. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCuotaConcepto", referencedColumnName = "idCuotaConcepto")
    private CuotaConcepto cuotaConcepto;
   
    /** El cuota. */
    @Column(name = "cuota" , precision = 18 , scale = 2)
    private BigDecimal cuota;
   
    /** El monto resta. */
    @Column(name = "montoResta" , precision = 18 , scale = 2)
    private BigDecimal montoResta;
   
    /** El fecha vencimiento. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaVencimiento")
    private Date fechaVencimiento;
   
    /** El flag fraccionado. */
    @Column(name = "flagFraccionado" , length = 1)
    private String flagFraccionado;
    
    /** El nro. */
    @Column(name = "nro" , length = 5)
    private String nro;
 
    @Column(name = "idPuesto" , length = 32)
    private String idPuesto;
    
    /**
     * Instancia un nuevo det plan pagos.
     */
    public DetPlanPagos() {
    }

	public DetPlanPagos(String idDetPlanPagos, PlanPagos planPagos, CuotaConcepto cuotaConcepto, BigDecimal cuota,
			BigDecimal montoResta, Date fechaVencimiento, String flagFraccionado, String nro,String idPuesto) {
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

	public String getIdDetPlanPagos() {
		return idDetPlanPagos;
	}

	public void setIdDetPlanPagos(String idDetPlanPagos) {
		this.idDetPlanPagos = idDetPlanPagos;
	}

	public PlanPagos getPlanPagos() {
		return planPagos;
	}

	public void setPlanPagos(PlanPagos planPagos) {
		this.planPagos = planPagos;
	}

	public CuotaConcepto getCuotaConcepto() {
		return cuotaConcepto;
	}

	public void setCuotaConcepto(CuotaConcepto cuotaConcepto) {
		this.cuotaConcepto = cuotaConcepto;
	}

	public BigDecimal getCuota() {
		return cuota;
	}

	public void setCuota(BigDecimal cuota) {
		this.cuota = cuota;
	}

	public BigDecimal getMontoResta() {
		return montoResta;
	}

	public void setMontoResta(BigDecimal montoResta) {
		this.montoResta = montoResta;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getFlagFraccionado() {
		return flagFraccionado;
	}

	public void setFlagFraccionado(String flagFraccionado) {
		this.flagFraccionado = flagFraccionado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idDetPlanPagos == null) ? 0 : idDetPlanPagos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetPlanPagos other = (DetPlanPagos) obj;
		if (idDetPlanPagos == null) {
			if (other.idDetPlanPagos != null)
				return false;
		} else if (!idDetPlanPagos.equals(other.idDetPlanPagos))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DetPlanPagos [idDetPlanPagos=" + idDetPlanPagos + "]";
	} 
   
}