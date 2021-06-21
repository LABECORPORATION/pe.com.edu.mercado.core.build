package pe.com.builderp.core.facturacion.model.dto.empresa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List; 
import pe.com.edu.siaa.core.model.dto.BasePaginator; 
/**
 * La Class PlanPagosDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Tue Apr 18 09:56:54 COT 2017
 * @since SIAA-CORE 2.1
 */
public class PlanPagosDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id plan pagos. */
    private String idPlanPagos;
   
    /** El anho semestre. */
    private AsociadoDTO asociado;
    
   
    /** El fecha creacion. */
    private Date fechaCreacion;
   
    /** El usuario creacion. */
    private String usuarioCreacion;
   
    /** El fecha modificacion. */
    private Date fechaModificacion;
   
    /** El usuario modificacion. */
    private String usuarioModificacion; 
    
    private AnhioDTO anio;
    
    /** El plan pagos det plan pagos list. */
    private List<DetPlanPagosDTO> planPagosDetPlanPagosList = new ArrayList<DetPlanPagosDTO>();
   
    /**
     * Instancia un nuevo plan pagosDTO.
     */
    public PlanPagosDTO() {
    }

	public PlanPagosDTO(String idPlanPagos, AsociadoDTO asociado, Date fechaCreacion, String usuarioCreacion,
			Date fechaModificacion, String usuarioModificacion,AnhioDTO anio) {
		super();
		this.idPlanPagos = idPlanPagos;
		this.asociado = asociado;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaModificacion = fechaModificacion;
		this.usuarioModificacion = usuarioModificacion;
		this.anio=anio;
	}

	public AnhioDTO getAnio() {
		return anio;
	}

	public void setAnio(AnhioDTO anio) {
		this.anio = anio;
	}

	public String getIdPlanPagos() {
		return idPlanPagos;
	}

	public void setIdPlanPagos(String idPlanPagos) {
		this.idPlanPagos = idPlanPagos;
	}

	public AsociadoDTO getAsociado() {
		return asociado;
	}

	public void setAsociado(AsociadoDTO asociado) {
		this.asociado = asociado;
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

	public List<DetPlanPagosDTO> getPlanPagosDetPlanPagosList() {
		return planPagosDetPlanPagosList;
	}

	public void setPlanPagosDetPlanPagosList(List<DetPlanPagosDTO> planPagosDetPlanPagosList) {
		this.planPagosDetPlanPagosList = planPagosDetPlanPagosList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPlanPagos == null) ? 0 : idPlanPagos.hashCode());
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
		PlanPagosDTO other = (PlanPagosDTO) obj;
		if (idPlanPagos == null) {
			if (other.idPlanPagos != null)
				return false;
		} else if (!idPlanPagos.equals(other.idPlanPagos))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PlanPagosDTO [idPlanPagos=" + idPlanPagos + "]";
	}
   
    
}