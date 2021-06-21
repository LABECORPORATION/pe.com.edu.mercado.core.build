package pe.com.edu.siaa.core.model.vo.encuesta;

import java.io.Serializable;
import java.util.Date;

import pe.com.edu.siaa.core.model.dto.BasePaginator;
 

/**
 * La Class EncuestaVO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Tue Feb 20 09:07:26 COT 2018
 * @since SIAA-CORE 2.1
 */
public class EncuestaVO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id encuesta. */
    private String idEncuesta;
   
    /** El det carga lectiva. */
    private String descripcionCurso;
   
    /** El tema. */
    private String tema;
   
    /** El fecha inicio. */
    private Date fechaInicio;
   
    /** El fecha termino. */
    private Date fechaTermino;
   
    /** El estado. */
    private String estado;
   
    /** El rango. */
    private Long rango;
   
    private String nombreDocente;
  	private String apellidoPaternoDocente;
  	private String apellidoMaternoDocente;
  
  	private String idAlumno;
  	private String idDocente;
  	
  	private String estadoEncuestaCampo;
  	private Long idTema;
  	
  	private String idEncuestaCampo;
  	
    /**
     * Instancia un nuevo encuestaDTO.
     */
    public EncuestaVO() {
    }
   
    //get y set
    public String getIdEncuesta() {
		return idEncuesta;
	}

	public void setIdEncuesta(String idEncuesta) {
		this.idEncuesta = idEncuesta;
	}

	public String getDescripcionCurso() {
		return descripcionCurso;
	}

	public void setDescripcionCurso(String descripcionCurso) {
		this.descripcionCurso = descripcionCurso;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaTermino() {
		return fechaTermino;
	}

	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getRango() {
		return rango;
	}

	public void setRango(Long rango) {
		this.rango = rango;
	}

	public String getNombreDocente() {
		return nombreDocente;
	}

	public void setNombreDocente(String nombreDocente) {
		this.nombreDocente = nombreDocente;
	}

	public String getApellidoPaternoDocente() {
		return apellidoPaternoDocente;
	}

	public void setApellidoPaternoDocente(String apellidoPaternoDocente) {
		this.apellidoPaternoDocente = apellidoPaternoDocente;
	}

	public String getApellidoMaternoDocente() {
		return apellidoMaternoDocente;
	}

	public void setApellidoMaternoDocente(String apellidoMaternoDocente) {
		this.apellidoMaternoDocente = apellidoMaternoDocente;
	}

	public String getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(String idAlumno) {
		this.idAlumno = idAlumno;
	}

	public String getIdDocente() {
		return idDocente;
	}

	public void setIdDocente(String idDocente) {
		this.idDocente = idDocente;
	}

	public String getEstadoEncuestaCampo() {
		return estadoEncuestaCampo;
	}

	public void setEstadoEncuestaCampo(String estadoEncuestaCampo) {
		this.estadoEncuestaCampo = estadoEncuestaCampo;
	}

	public Long getIdTema() {
		return idTema;
	}

	public void setIdTema(Long idTema) {
		this.idTema = idTema;
	}
	
    public String getIdEncuestaCampo() {
		return idEncuestaCampo;
	}

	public void setIdEncuestaCampo(String idEncuestaCampo) {
		this.idEncuestaCampo = idEncuestaCampo;
	}

	/* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        EncuestaVO other = (EncuestaVO) obj;
        if (idEncuesta == null) {
            if (other.idEncuesta != null) {
                return false;
            }
        } else if (!idEncuesta.equals(other.idEncuesta)) {
            return false;
        }
        return true;
    }
    
	/* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "EncuestaDTO [idEncuesta=" + idEncuesta + "]";
    }
   
}