package pe.com.edu.siaa.core.ejb.factory;

public class ControPagoVO {

	private String codAlumno;
	private String nombres;
	private String semestre;
	private String inscripcion;
	private String carrera;
	private String ciclo;
	private String matricula;
	private String primeraCuota;
	private String segundaCuota;
	private String terceraCuota;
	private String cuartaCuota;
	private String quintaCuota;
	
	private String comentario;
	private String keyConcepto;
	private String cuota;
	private String monto;
	
	private String fechaVencimiento;
	private String fechaPago;
	private String tipoDocSunat;
	private String nroDocSunat;
	
	public ControPagoVO(String codAlumno, String nombres, String semestre,
			String inscripcion, String carrera, String ciclo, String matricula,
			String primeraCuota, String segundaCuota, String terceraCuota,
			String cuartaCuota,String quintaCuota) {
		super();
		this.codAlumno = codAlumno;
		this.nombres = nombres;
		this.semestre = semestre;
		this.inscripcion = inscripcion;
		this.carrera = carrera;
		this.ciclo = ciclo;
		this.matricula = matricula;
		this.primeraCuota = primeraCuota;
		this.segundaCuota = segundaCuota;
		this.terceraCuota = terceraCuota;
		this.cuartaCuota = cuartaCuota;
		this.quintaCuota = quintaCuota;
	}
	public ControPagoVO() {
		super();
	}
	public String getCodAlumno() {
		return codAlumno;
	}
	public void setCodAlumno(String codAlumno) {
		this.codAlumno = codAlumno;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getSemestre() {
		return semestre;
	}
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	public String getInscripcion() {
		return inscripcion;
	}
	public void setInscripcion(String inscripcion) {
		this.inscripcion = inscripcion;
	}
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	public String getCiclo() {
		return ciclo;
	}
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getPrimeraCuota() {
		return primeraCuota;
	}
	public void setPrimeraCuota(String primeraCuota) {
		this.primeraCuota = primeraCuota;
	}
	public String getSegundaCuota() {
		return segundaCuota;
	}
	public void setSegundaCuota(String segundaCuota) {
		this.segundaCuota = segundaCuota;
	}
	public String getTerceraCuota() {
		return terceraCuota;
	}
	public void setTerceraCuota(String terceraCuota) {
		this.terceraCuota = terceraCuota;
	}
	public String getCuartaCuota() {
		return cuartaCuota;
	}
	public void setCuartaCuota(String cuartaCuota) {
		this.cuartaCuota = cuartaCuota;
	}
	public String getQuintaCuota() {
		return quintaCuota;
	}
	public void setQuintaCuota(String quintaCuota) {
		this.quintaCuota = quintaCuota;
	}
	
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	public String getKeyConcepto() {
		return keyConcepto;
	}
	public void setKeyConcepto(String keyConcepto) {
		this.keyConcepto = keyConcepto;
	}
	public String getCuota() {
		return cuota;
	}
	public void setCuota(String cuota) {
		this.cuota = cuota;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	
	
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public String getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	public String getTipoDocSunat() {
		return tipoDocSunat;
	}
	public void setTipoDocSunat(String tipoDocSunat) {
		this.tipoDocSunat = tipoDocSunat;
	}
	public String getNroDocSunat() {
		return nroDocSunat;
	}
	public void setNroDocSunat(String nroDocSunat) {
		this.nroDocSunat = nroDocSunat;
	}
	@Override
	public String toString() {
		return "ControPagoVO [codAlumno=" + codAlumno + ", nombres=" + nombres + ", semestre=" + semestre
				+ ", carrera=" + carrera + ", ciclo=" + ciclo + ", comentario="
				+ comentario + ", keyConcepto=" + keyConcepto + ", cuota=" + cuota + ", monto=" + monto
				+ ", fechaVencimiento=" + fechaVencimiento + ", fechaPago=" + fechaPago + ", tipoDocSunat="
				+ tipoDocSunat + ", nroDocSunat=" + nroDocSunat + "]";
	}
	
	
	
}
