package pe.com.builderp.core.facturacion.model.dto.empresa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pe.com.edu.siaa.core.model.dto.BasePaginator; 

/**
 * La Class CuotaConceptoDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Tue Apr 18 09:56:45 COT 2017
 * @since SIAA-CORE 2.1
 */
public class CuotaConceptoDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id cuota concepto. */
    private String idCuotaConcepto;
   
    /** El cuenta. */
    private String cuenta;
   
    /** El nro cuenta. */
    private String nroCuenta;
   
    /** El nro min fraccionamiento. */
    private Integer nroMinFraccionamiento;
   
    /** El nro max fraccionamiento. */
    private Integer nroMaxFraccionamiento;
   
    /** El monto. */
    private BigDecimal monto;
   
    /** El permanente. */
    private String permanente;
   
    /** El fecha tentativa. */
    private Date fechaTentativa;
   
    /** El fecha creacion. */
    private Date fechaCreacion;
   
    /** El usuario creacion. */
    private String usuarioCreacion;
   
    /** El fecha modificacion. */
    private Date fechaModificacion;
   
    /** El usuario modificacion. */
    private String usuarioModificacion;
   
    /** El cuota concepto det plan pagos list. */
    private List<DetPlanPagosDTO> cuotaConceptoDetPlanPagosList = new ArrayList<DetPlanPagosDTO>();
   
    /**
     * Instancia un nuevo cuota conceptoDTO.
     */
    public CuotaConceptoDTO() {
    }

	public CuotaConceptoDTO(String idCuotaConcepto, String cuenta, String nroCuenta, Integer nroMinFraccionamiento,
			Integer nroMaxFraccionamiento, BigDecimal monto, String permanente, Date fechaTentativa, Date fechaCreacion,
			String usuarioCreacion, Date fechaModificacion, String usuarioModificacion) {
		super();
		this.idCuotaConcepto = idCuotaConcepto;
		this.cuenta = cuenta;
		this.nroCuenta = nroCuenta;
		this.nroMinFraccionamiento = nroMinFraccionamiento;
		this.nroMaxFraccionamiento = nroMaxFraccionamiento;
		this.monto = monto;
		this.permanente = permanente;
		this.fechaTentativa = fechaTentativa;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaModificacion = fechaModificacion;
		this.usuarioModificacion = usuarioModificacion;
	}

	public String getIdCuotaConcepto() {
		return idCuotaConcepto;
	}

	public void setIdCuotaConcepto(String idCuotaConcepto) {
		this.idCuotaConcepto = idCuotaConcepto;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public Integer getNroMinFraccionamiento() {
		return nroMinFraccionamiento;
	}

	public void setNroMinFraccionamiento(Integer nroMinFraccionamiento) {
		this.nroMinFraccionamiento = nroMinFraccionamiento;
	}

	public Integer getNroMaxFraccionamiento() {
		return nroMaxFraccionamiento;
	}

	public void setNroMaxFraccionamiento(Integer nroMaxFraccionamiento) {
		this.nroMaxFraccionamiento = nroMaxFraccionamiento;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getPermanente() {
		return permanente;
	}

	public void setPermanente(String permanente) {
		this.permanente = permanente;
	}

	public Date getFechaTentativa() {
		return fechaTentativa;
	}

	public void setFechaTentativa(Date fechaTentativa) {
		this.fechaTentativa = fechaTentativa;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public List<DetPlanPagosDTO> getCuotaConceptoDetPlanPagosList() {
		return cuotaConceptoDetPlanPagosList;
	}

	public void setCuotaConceptoDetPlanPagosList(List<DetPlanPagosDTO> cuotaConceptoDetPlanPagosList) {
		this.cuotaConceptoDetPlanPagosList = cuotaConceptoDetPlanPagosList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCuotaConcepto == null) ? 0 : idCuotaConcepto.hashCode());
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
		CuotaConceptoDTO other = (CuotaConceptoDTO) obj;
		if (idCuotaConcepto == null) {
			if (other.idCuotaConcepto != null)
				return false;
		} else if (!idCuotaConcepto.equals(other.idCuotaConcepto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CuotaConceptoDTO [idCuotaConcepto=" + idCuotaConcepto + "]";
	}
   
  
}