package pe.com.edu.siaa.core.ejb.factory;

public class ExcepcionAlumnoPagoVO {
	//SEMESTRE	CODIGO DEL ALUMNO	APELLIDOS Y NOMBRES	CONCEPTO	MONTO	N� DE FRACCI�N	CARRERA	OBSERVACI�N 
	private String semestre;
	private String codigoAlumno;
	private String apellidosNombres;
	private String concepto;
	private String monto;
	private String nroFraccion;
	private String carrera;
	private String observacion;
	private String flagConvenioFinalPaga;
	
	//Constructor vacio
	public ExcepcionAlumnoPagoVO() {
		super();
	}
	//Constructor con parametros
	public ExcepcionAlumnoPagoVO(String semestre, String codigoAlumno,
			String apellidosNombres, String concepto, String monto,
			String nroFraccion, String carrera, String observacion,
			String flagConvenioFinalPaga) {
		super();
		this.semestre = semestre;
		this.codigoAlumno = codigoAlumno;
		this.apellidosNombres = apellidosNombres;
		this.concepto = concepto;
		this.monto = monto;
		this.nroFraccion = nroFraccion;
		this.carrera = carrera;
		this.observacion = observacion;
		this.flagConvenioFinalPaga = flagConvenioFinalPaga;
	}
	// GET Y SET
	public String getSemestre() {
		return semestre;
	}
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	public String getCodigoAlumno() {
		return codigoAlumno;
	}
	public void setCodigoAlumno(String codigoAlumno) {
		this.codigoAlumno = codigoAlumno;
	}
	public String getApellidosNombres() {
		return apellidosNombres;
	}
	public void setApellidosNombres(String apellidosNombres) {
		this.apellidosNombres = apellidosNombres;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getNroFraccion() {
		return nroFraccion;
	}
	public void setNroFraccion(String nroFraccion) {
		this.nroFraccion = nroFraccion;
	}
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getFlagConvenioFinalPaga() {
		return flagConvenioFinalPaga;
	}
	public void setFlagConvenioFinalPaga(String flagConvenioFinalPaga) {
		this.flagConvenioFinalPaga = flagConvenioFinalPaga;
	}
	@Override
	public String toString() {
		return "ExcepcionAlumnoPagoVO [semestre=" + semestre
				+ ", codigoAlumno=" + codigoAlumno + ", apellidosNombres="
				+ apellidosNombres + ", concepto=" + concepto + ", monto="
				+ monto + ", nroFraccion=" + nroFraccion + ", carrera="
				+ carrera + ", observacion=" + observacion
				+ ", flagConvenioFinalPaga=" + flagConvenioFinalPaga + "]";
	}
	
	
}
