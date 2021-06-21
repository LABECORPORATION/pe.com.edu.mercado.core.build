package pe.com.edu.siaa.core.model.jpa.empresa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import pe.com.edu.siaa.core.model.jpa.common.Item; 
import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class Asistencia.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 09:56:47 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "Asistencia", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_EMPRESA)
public class Asistencia implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id asistencia. */
    @Id
    @Column(name = "idAsistencia" , length = 32)
    private String idAsistencia;
   
    /** El alumno. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAsociado", referencedColumnName = "idAsociado")
    private Asociado asociado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersonal", referencedColumnName = "idPersonal")
    private Personal personal;
   
    /** El det carga lectiva. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idActividad", referencedColumnName = "idActividad")
    private Actividad actividad;
   
    /** El item by dia. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDia", referencedColumnName = "idItem")
    private Item itemByDia;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
   
    /** El justificacion. */
    @Column(name = "justificacion" , length = 200)
    private String justificacion;
   
    /** El fecha horario. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaHorario")
    private Date fechaHorario;
   
    /** El fecha creacion. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaCreacion")
    private Date fechaCreacion;
   
    /** El usuario creacion. */
    @Column(name = "usuarioCreacion" , length = 50)
    private String usuarioCreacion;
   
    /** El fecha modificacion. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaModificacion")
    private Date fechaModificacion;
   
    /** El usuario modificacion. */
    @Column(name = "usuarioModificacion" , length = 50)
    private String usuarioModificacion;
   
    /**
     * Instancia un nuevo asistencia.
     */
    public Asistencia() {
    }

	public Asistencia(String idAsistencia, Asociado asociado, Personal personal, Actividad actividad, Item itemByDia,
			String estado, String justificacion, Date fechaHorario, Date fechaCreacion, String usuarioCreacion,
			Date fechaModificacion, String usuarioModificacion) {
		super();
		this.idAsistencia = idAsistencia;
		this.asociado = asociado;
		this.personal = personal;
		this.actividad = actividad;
		this.itemByDia = itemByDia;
		this.estado = estado;
		this.justificacion = justificacion;
		this.fechaHorario = fechaHorario;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaModificacion = fechaModificacion;
		this.usuarioModificacion = usuarioModificacion;
	}

	public String getIdAsistencia() {
		return idAsistencia;
	}

	public void setIdAsistencia(String idAsistencia) {
		this.idAsistencia = idAsistencia;
	}

	public Asociado getAsociado() {
		return asociado;
	}

	public void setAsociado(Asociado asociado) {
		this.asociado = asociado;
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Item getItemByDia() {
		return itemByDia;
	}

	public void setItemByDia(Item itemByDia) {
		this.itemByDia = itemByDia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

	public Date getFechaHorario() {
		return fechaHorario;
	}

	public void setFechaHorario(Date fechaHorario) {
		this.fechaHorario = fechaHorario;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAsistencia == null) ? 0 : idAsistencia.hashCode());
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
		Asistencia other = (Asistencia) obj;
		if (idAsistencia == null) {
			if (other.idAsistencia != null)
				return false;
		} else if (!idAsistencia.equals(other.idAsistencia))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Asistencia [idAsistencia=" + idAsistencia + "]";
	}
   
}