package pe.com.builderp.core.facturacion.model.dto.empresa;

import java.io.Serializable;
import java.util.Date;

import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO; 

/**
 * La Class AsistenciaDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Tue Apr 18 09:56:47 COT 2017
 * @since SIAA-CORE 2.1
 */
public class AsistenciaDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id asistencia. */
    private String idAsistencia;
   
    /** El alumno. */
    private AsociadoDTO asociado;
    
    private PersonalDTO personal;
   
    /** El det carga lectiva. */
    private ActividadDTO actividad;
   
    /** El item by dia. */
    private ItemDTO itemByDia;
   
    /** El estado. */
    private String estado;
   
    /** El justificacion. */
    private String justificacion;
   
    /** El fecha horario. */
    private Date fechaHorario;
   
    /** El fecha creacion. */
    private Date fechaCreacion;
   
    /** El usuario creacion. */
    private String usuarioCreacion;
   
    /** El fecha modificacion. */
    private Date fechaModificacion;
   
    /** El usuario modificacion. */
    private String usuarioModificacion;
     
   
    /**
     * Instancia un nuevo asistenciaDTO.
     */
    public AsistenciaDTO() {
    }


	public AsistenciaDTO(String idAsistencia, AsociadoDTO asociado, PersonalDTO personal, ActividadDTO actividad,
			ItemDTO itemByDia, String estado, String justificacion, Date fechaHorario, Date fechaCreacion,
			String usuarioCreacion, Date fechaModificacion, String usuarioModificacion) {
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


	public AsociadoDTO getAsociado() {
		return asociado;
	}


	public void setAsociado(AsociadoDTO asociado) {
		this.asociado = asociado;
	}


	public PersonalDTO getPersonal() {
		return personal;
	}


	public void setPersonal(PersonalDTO personal) {
		this.personal = personal;
	}


	public ActividadDTO getActividad() {
		return actividad;
	}


	public void setActividad(ActividadDTO actividad) {
		this.actividad = actividad;
	}


	public ItemDTO getItemByDia() {
		return itemByDia;
	}


	public void setItemByDia(ItemDTO itemByDia) {
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
		AsistenciaDTO other = (AsistenciaDTO) obj;
		if (idAsistencia == null) {
			if (other.idAsistencia != null)
				return false;
		} else if (!idAsistencia.equals(other.idAsistencia))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "AsistenciaDTO [idAsistencia=" + idAsistencia + "]";
	}
     
}