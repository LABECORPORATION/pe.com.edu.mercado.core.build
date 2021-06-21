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
 * La Class ControlPago.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Fri Dec 15 11:01:56 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "Actividad", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_EMPRESA)
public class Actividad implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id control pago. */
    @Id
    @Column(name = "idActividad" , length = 12)
    private String idActividad;
   
    /** El anho semestre. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idResponsable", referencedColumnName = "idPersonal")
    private Personal responsable; 
   
    /** El tipo doc sunat. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoActividad", referencedColumnName = "idItem")
    private Item tipoActividad;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAnhio", referencedColumnName = "idAnhio")
    private Anhio anhio;
   
    /** El nro doc. */
    @Column(name = "nombre")
    private String nombre;
   
    /** El nro doc. */
    @Column(name = "descripcion")
    private String descripcion;
   
    /** El fecha pago. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaInicio")
    private Date fechaInicio;
    
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaFin")
    private Date fechaFin;
   
    /** El fecha creacion. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaCreacion")
    private Date fechaCreacion;
   
    /** El usuario creacion. */
    @Column(name = "usuarioCreacion" , length = 50)
    private String usuarioCreacion;
   
    /** El fecha modificacion. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechamodificacion")
    private Date fechaModificacion;
   
    /** El usuario modificacion. */
    @Column(name = "usuarioModificacion" , length = 50)
    private String usuarioModificacion;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
     
    /**
     * Instancia un nuevo control pago.
     */
    public Actividad() {
    }

	public Actividad(String idActividad, Personal responsable, Item tipoActividad, Anhio anhio, String nombre,
			String descripcion, Date fechaInicio, Date fechaFin, Date fechaCreacion, String usuarioCreacion,
			Date fechaModificacion, String usuarioModificacion, String estado) {
		super();
		this.idActividad = idActividad;
		this.responsable = responsable;
		this.tipoActividad = tipoActividad;
		this.anhio = anhio;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaModificacion = fechaModificacion;
		this.usuarioModificacion = usuarioModificacion;
		this.estado = estado;
	}

	public String getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(String idActividad) {
		this.idActividad = idActividad;
	}

	public Personal getResponsable() {
		return responsable;
	}

	public void setResponsable(Personal responsable) {
		this.responsable = responsable;
	}

	public Item getTipoActividad() {
		return tipoActividad;
	}

	public void setTipoActividad(Item tipoActividad) {
		this.tipoActividad = tipoActividad;
	}

	public Anhio getAnhio() {
		return anhio;
	}

	public void setAnhio(Anhio anhio) {
		this.anhio = anhio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idActividad == null) ? 0 : idActividad.hashCode());
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
		Actividad other = (Actividad) obj;
		if (idActividad == null) {
			if (other.idActividad != null)
				return false;
		} else if (!idActividad.equals(other.idActividad))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Actividad [idActividad=" + idActividad + "]";
	}
 
    
}