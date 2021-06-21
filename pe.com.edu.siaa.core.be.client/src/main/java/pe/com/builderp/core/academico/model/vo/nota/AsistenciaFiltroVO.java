package pe.com.builderp.core.academico.model.vo.nota;

import java.io.Serializable;
import java.util.Date;

import pe.com.edu.siaa.core.model.dto.BasePaginator;

public class AsistenciaFiltroVO extends BasePaginator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String idEntidad;
	private String idDetCargaLectiva;
	private String idAlumno;
	private Date fechaHorario;
	private String idAnhoSemestre;
	
	
	public AsistenciaFiltroVO() {
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


	public String getIdDetCargaLectiva() {
		return idDetCargaLectiva;
	}


	public void setIdDetCargaLectiva(String idDetCargaLectiva) {
		this.idDetCargaLectiva = idDetCargaLectiva;
	}


	public String getIdAlumno() {
		return idAlumno;
	}


	public void setIdAlumno(String idAlumno) {
		this.idAlumno = idAlumno;
	}


	public Date getFechaHorario() {
		return fechaHorario;
	}


	public void setFechaHorario(Date fechaHorario) {
		this.fechaHorario = fechaHorario;
	}


	public String getIdAnhoSemestre() {
		return idAnhoSemestre;
	}


	public void setIdAnhoSemestre(String idAnhoSemestre) {
		this.idAnhoSemestre = idAnhoSemestre;
	}

	

}
