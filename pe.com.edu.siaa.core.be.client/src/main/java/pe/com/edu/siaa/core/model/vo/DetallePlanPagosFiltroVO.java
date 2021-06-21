package pe.com.edu.siaa.core.model.vo;

import java.io.Serializable; 
import java.util.Date; 
 
public class DetallePlanPagosFiltroVO   implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date fechaInicio;
	private Date fechaFin;
	
	
	private String idDetPlanPgos;	
	private String idPlanpagos;
	private String idAsociado;
	private String idCuotaConcepto;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String idPuesto;
	private String puesto;
	 
	
	public DetallePlanPagosFiltroVO() {
		super();
	}


	public String getIdCuotaConcepto() {
		return idCuotaConcepto;
	}


	public void setIdCuotaConcepto(String idCuotaConcepto) {
		this.idCuotaConcepto = idCuotaConcepto;
	}


	public String getIdAsociado() {
		return idAsociado;
	}


	public void setIdAsociado(String idAsociado) {
		this.idAsociado = idAsociado;
	}


	public Date getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public Date getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	public String getIdDetPlanPgos() {
		return idDetPlanPgos;
	}


	public void setIdDetPlanPgos(String idDetPlanPgos) {
		this.idDetPlanPgos = idDetPlanPgos;
	}


	public String getIdPlanpagos() {
		return idPlanpagos;
	}


	public void setIdPlanpagos(String idPlanpagos) {
		this.idPlanpagos = idPlanpagos;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidoPaterno() {
		return apellidoPaterno;
	}


	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}


	public String getApellidoMaterno() {
		return apellidoMaterno;
	}


	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}


	public String getIdPuesto() {
		return idPuesto;
	}


	public void setIdPuesto(String idPuesto) {
		this.idPuesto = idPuesto;
	}


	public String getPuesto() {
		return puesto;
	}


	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	
	
	 

}
