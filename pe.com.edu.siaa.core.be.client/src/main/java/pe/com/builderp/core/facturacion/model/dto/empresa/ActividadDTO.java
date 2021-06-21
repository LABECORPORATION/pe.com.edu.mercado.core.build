package pe.com.builderp.core.facturacion.model.dto.empresa;

import java.io.Serializable; 
import java.util.Date; 
 
import pe.com.edu.siaa.core.model.dto.BasePaginator; 
import pe.com.edu.siaa.core.model.dto.common.ItemDTO; 

/**
 * La Class ControlPagoDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Fri Dec 15 11:57:51 COT 2017
 * @since SIAA-CORE 2.1
 */
public class ActividadDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id control pago. */
    private String idActividad;
   
    /** El anho semestre. */
    private PersonalDTO responsable;
  
     /** El tipo doc sunat. */        
    private ItemDTO tipoActividad;   
     
     /** El alumno. */                                 
    private AnhioDTO anhio;

    private String nombre;

    private String descripcion;

    private Date fechaInicio;
    
    private Date fechaFin;

 
    /** El fecha creacion. */
    private Date fechaCreacion;
   
    /** El usuario creacion. */
    private String usuarioCreacion;
   
    /** El fecha modificacion. */
    private Date fechaModificacion;
   
    /** El usuario modificacion. */
    private String usuarioModificacion;
   
    /** El estado. */
    private String estado; 
   
    
    /**
     * Instancia un nuevo control pagoDTO.
     */
    public ActividadDTO() {
    }


	public ActividadDTO(String idActividad, PersonalDTO responsable, ItemDTO tipoActividad, AnhioDTO anhio,
			String nombre, String descripcion, Date fechaInicio, Date fechaFin, Date fechaCreacion,
			String usuarioCreacion, Date fechaModificacion, String usuarioModificacion, String estado) {
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


	public PersonalDTO getResponsable() {
		return responsable;
	}


	public void setResponsable(PersonalDTO responsable) {
		this.responsable = responsable;
	}


	public ItemDTO getTipoActividad() {
		return tipoActividad;
	}


	public void setTipoActividad(ItemDTO tipoActividad) {
		this.tipoActividad = tipoActividad;
	}


	public AnhioDTO getAnhio() {
		return anhio;
	}


	public void setAnhio(AnhioDTO anhio) {
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
		ActividadDTO other = (ActividadDTO) obj;
		if (idActividad == null) {
			if (other.idActividad != null)
				return false;
		} else if (!idActividad.equals(other.idActividad))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "ActividadDTO [idActividad=" + idActividad + "]";
	}
   
}