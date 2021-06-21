package pe.com.builderp.core.contabilidad.model.vo;

import java.io.Serializable;
import java.util.Date;

import pe.com.edu.siaa.core.model.dto.BasePaginator;

public class RegistroAsientoFiltroVO extends BasePaginator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String idEntidad;
	private String ejercicio;
	private String periodo;
	private Date fechaAsientoDesde;
	private Date fechaAsientoHasta;
	
	private Date fechaOperacion;
	
	private Long idLibro;
	private Long idSubLibro;
	
	private Object codigoEntidadUniversitaria = null;
	
	
	public RegistroAsientoFiltroVO() {
		super();
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getIdEntidad() {
		return idEntidad;
	}


	public void setIdEntidad(String idEntidad) {
		this.idEntidad = idEntidad;
	}


	public String getEjercicio() {
		return ejercicio;
	}


	public void setEjercicio(String ejercicio) {
		this.ejercicio = ejercicio;
	}


	public String getPeriodo() {
		return periodo;
	}


	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}


	public Date getFechaAsientoDesde() {
		return fechaAsientoDesde;
	}


	public void setFechaAsientoDesde(Date fechaAsientoDesde) {
		this.fechaAsientoDesde = fechaAsientoDesde;
	}


	public Date getFechaAsientoHasta() {
		return fechaAsientoHasta;
	}


	public void setFechaAsientoHasta(Date fechaAsientoHasta) {
		this.fechaAsientoHasta = fechaAsientoHasta;
	}


	public Long getIdLibro() {
		return idLibro;
	}


	public void setIdLibro(Long idLibro) {
		this.idLibro = idLibro;
	}


	public Long getIdSubLibro() {
		return idSubLibro;
	}


	public void setIdSubLibro(Long idSubLibro) {
		this.idSubLibro = idSubLibro;
	}


	public Object getCodigoEntidadUniversitaria() {
		return codigoEntidadUniversitaria;
	}


	public void setCodigoEntidadUniversitaria(Object codigoEntidadUniversitaria) {
		this.codigoEntidadUniversitaria = codigoEntidadUniversitaria;
	}


	public Date getFechaOperacion() {
		return fechaOperacion;
	}


	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}
	
	
	

}
