package pe.com.edu.siaa.core.ejb.factory;

public class ConceptoPorEscuelaSemestreVO {
	private String semestre;
	private String concepto	;
	private String monto;
	private String nroFraccion;
	private String carrera;
	private String observacion;
	
	//Constructor vacio
	public ConceptoPorEscuelaSemestreVO() {
		super();
	}

	//Constructor con parametros
	public ConceptoPorEscuelaSemestreVO(String semestre, String concepto, String monto,
			String nroFraccion, String carrera, String observacion) {
		super();
		this.semestre = semestre;
		this.concepto = concepto;
		this.monto = monto;
		this.nroFraccion = nroFraccion;
		this.carrera = carrera;
		this.observacion = observacion;
	}
	
	// GET Y SET
	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
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

	@Override
	public String toString() {
		return "ConceptoPorEscuelaSemestreVO [semestre=" + semestre
				+ ", concepto=" + concepto + ", monto=" + monto
				+ ", nroFraccion=" + nroFraccion + ", carrera=" + carrera
				+ ", observacion=" + observacion + "]";
	}


}
