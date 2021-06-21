package pe.com.builderp.core.facturacion.model.dto.empresa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import pe.com.edu.siaa.core.model.dto.BasePaginator;

/**
 * <ul>
 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Class ConceptoPagoDTO.
 *
 * @author ndavilal.
 * @version 1.0 , 17/07/2012
 * @since SIAA 2.0
 */
public class ConceptoPagoDTO  extends BasePaginator implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 6381567456848622476L;
	
	/** El id padre. */
	private String idPadre;
	
	/** La descripcion. */
	private String descripcion;
	
	/** La clasificacion. */
	private String clasificacion;
	
	/** La flag fraccionado descripcion. */
	private String flagFraccionadoDescripcion;
	
	/** El monto. */
	private BigDecimal monto;
	
	/** El monto total. */
	private BigDecimal montoTotal;
	
	/** El monto fraccionado resta. */
	private BigDecimal montoResta;
	
	/** La fecha vencimiento. */
	private Date fechaVencimiento;

	/** El flag es fraccionado. */
	private boolean esFraccionado;
	
	/** El flag check. */
	private boolean check;
	
	/** El numero dias retrazo. */
	private Integer numeroDiasRetrazo;
	
	/** El flag desabilitar campo mora. */
	 private boolean desabilitarCampoMora = true;
	 
	/** El flag aplica mora. */
	private boolean aplicaMora;
	
	/** La mora. */
	private BigDecimal mora;
	
	private String idCuotaConcepto = "";
	
	private String nroCuota = "";
	
	private String idPlanContable = "";
	
	private boolean flagAplicaIGV = false;
	/**
	 * Instancia un nuevo concepto pago dto.
	 */
	public ConceptoPagoDTO() {
		super();
	}

	
	/**
	 * Instancia un nuevo concepto pago dto.
	 *
	 * @param id el id
	 * @param idPadre el id padre
	 * @param descripcion el descripcion
	 * @param clasificacion el clasificacion
	 * @param flagFraccionadoDescripcion el flag fraccionado descripcion
	 * @param monto el monto
	 * @param montoResta el monto resta
	 * @param fechaVencimiento el fecha vencimiento
	 * @param esFraccionado el es fraccionado
	 * @param check el check
	 * @param numeroDiasRetrazo el numero dias retrazo
	 * @param aplicaMora el aplica mora
	 * @param mora el mora
	 */
	public ConceptoPagoDTO(Object id, String idPadre, String descripcion,
			String clasificacion, String flagFraccionadoDescripcion,
			BigDecimal monto, BigDecimal montoResta, Date fechaVencimiento,
			boolean esFraccionado, boolean check, Integer numeroDiasRetrazo,
			boolean aplicaMora, BigDecimal mora) {
		super();
		super.setId(id);
		this.idPadre = idPadre;
		this.descripcion = descripcion;
		this.clasificacion = clasificacion;
		this.flagFraccionadoDescripcion = flagFraccionadoDescripcion;
		this.monto = monto;
		this.montoResta = montoResta;
		this.fechaVencimiento = fechaVencimiento;
		this.esFraccionado = esFraccionado;
		this.check = check;
		this.numeroDiasRetrazo = numeroDiasRetrazo;
		this.aplicaMora = aplicaMora;
		this.mora = mora;
	}






		//get y set
	
	/**
	 * Obtiene id padre.
	 *
	 * @return id padre
	 */
	public String getIdPadre() {
		return idPadre;
	}

	/**
	 * Establece el id padre.
	 *
	 * @param idPadre el new id padre
	 */
	public void setIdPadre(String idPadre) {
		this.idPadre = idPadre;
	}

	/**
	 * Obtiene descripcion.
	 *
	 * @return descripcion
	 */
	public String getDescripcion() {
		return descripcion;
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
	 * Obtiene clasificacion.
	 *
	 * @return clasificacion
	 */
	public String getClasificacion() {
		return clasificacion;
	}


	/**
	 * Establece el clasificacion.
	 *
	 * @param clasificacion el new clasificacion
	 */
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}


	/**
	 * Obtiene flag fraccionado descripcion.
	 *
	 * @return flag fraccionado descripcion
	 */
	public String getFlagFraccionadoDescripcion() {
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


	/**
	 * Obtiene monto.
	 *
	 * @return monto
	 */
	public BigDecimal getMonto() {
		return monto;
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
	 * Obtiene monto total.
	 *
	 * @return monto total
	 */
	public BigDecimal getMontoTotal() {
		return montoTotal;
	}

	/**
	 * Establece el monto total.
	 *
	 * @param montoTotal el new monto total
	 */
	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}


	/**
	 * Obtiene monto resta.
	 *
	 * @return monto resta
	 */
	public BigDecimal getMontoResta() {
		return montoResta;
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
		return fechaVencimiento;
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
	 * Comprueba si es es fraccionado.
	 *
	 * @return true, si es es fraccionado
	 */
	public boolean isEsFraccionado() {
		return esFraccionado;
	}
	

	/**
	 * Establece el es fraccionado.
	 *
	 * @param esFraccionado el new es fraccionado
	 */
	public void setEsFraccionado(boolean esFraccionado) {
		this.esFraccionado = esFraccionado;
	}

	/**
	 * Comprueba si es check.
	 *
	 * @return true, si es check
	 */
	public boolean isCheck() {
		return check;
	}

	/**
	 * Establece el check.
	 *
	 * @param check el new check
	 */
	public void setCheck(boolean check) {
		this.check = check;
	}

	/**
	 * Obtiene numero dias retrazo.
	 *
	 * @return numero dias retrazo
	 */
	public Integer getNumeroDiasRetrazo() {
		return numeroDiasRetrazo;
	}

	/**
	 * Establece el numero dias retrazo.
	 *
	 * @param numeroDiasRetrazo el new numero dias retrazo
	 */
	public void setNumeroDiasRetrazo(Integer numeroDiasRetrazo) {
		this.numeroDiasRetrazo = numeroDiasRetrazo;
	}

	/**
	 * Comprueba si es aplica mora.
	 *
	 * @return true, si es aplica mora
	 */
	public boolean isAplicaMora() {
		return aplicaMora;
	}

	/**
	 * Establece el aplica mora.
	 *
	 * @param aplicaMora el new aplica mora
	 */
	public void setAplicaMora(boolean aplicaMora) {
		this.aplicaMora = aplicaMora;
	}


	/**
	 * Obtiene mora.
	 *
	 * @return mora
	 */
	public BigDecimal getMora() {
		return mora;
	}


	/**
	 * Establece el mora.
	 *
	 * @param mora el new mora
	 */
	public void setMora(BigDecimal mora) {
		this.mora = mora;
	}


	/**
	 * Comprueba si es desabilitar campo mora.
	 *
	 * @return true, si es desabilitar campo mora
	 */
	public boolean isDesabilitarCampoMora() {
		return desabilitarCampoMora;
	}


	/**
	 * Establece el desabilitar campo mora.
	 *
	 * @param desabilitarCampoMora el new desabilitar campo mora
	 */
	public void setDesabilitarCampoMora(boolean desabilitarCampoMora) {
		this.desabilitarCampoMora = desabilitarCampoMora;
	}


	public String getIdCuotaConcepto() {
		return idCuotaConcepto;
	}


	public void setIdCuotaConcepto(String idCuotaConcepto) {
		this.idCuotaConcepto = idCuotaConcepto;
	}


	public String getNroCuota() {
		return nroCuota;
	}


	public void setNroCuota(String nroCuota) {
		this.nroCuota = nroCuota;
	}


	public String getIdPlanContable() {
		return idPlanContable;
	}


	public void setIdPlanContable(String idPlanContable) {
		this.idPlanContable = idPlanContable;
	}


	public boolean isFlagAplicaIGV() {
		return flagAplicaIGV;
	}


	public void setFlagAplicaIGV(boolean flagAplicaIGV) {
		this.flagAplicaIGV = flagAplicaIGV;
	}
	
	
	
	
}
