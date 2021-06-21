package pe.com.edu.siaa.core.model.vo;

import java.io.Serializable;
import java.util.Date;

import pe.com.edu.siaa.core.model.dto.BasePaginator;

public class CvPerfilesVO extends BasePaginator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idEgresado;
	private String idAlumno;
	private String idAnhoSemestre;
	private String idUusraio;
	private String idCaja;
	private Date fechaCreacion;
	
	
	public CvPerfilesVO() {
		super();
	}


	public String getIdEgresado() {
		return idEgresado;
	}


	public void setIdEgresado(String idEgresado) {
		this.idEgresado = idEgresado;
	}


	public String getIdAlumno() {
		return idAlumno;
	}


	public void setIdAlumno(String idAlumno) {
		this.idAlumno = idAlumno;
	}


	public String getIdAnhoSemestre() {
		return idAnhoSemestre;
	}


	public void setIdAnhoSemestre(String idAnhoSemestre) {
		this.idAnhoSemestre = idAnhoSemestre;
	}


	public String getIdUusraio() {
		return idUusraio;
	}


	public void setIdUusraio(String idUusraio) {
		this.idUusraio = idUusraio;
	}


	public String getIdCaja() {
		return idCaja;
	}


	public void setIdCaja(String idCaja) {
		this.idCaja = idCaja;
	}


	public Date getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	




}
