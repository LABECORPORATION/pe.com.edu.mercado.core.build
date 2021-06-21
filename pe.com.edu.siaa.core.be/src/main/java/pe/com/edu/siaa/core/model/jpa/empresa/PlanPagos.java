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

import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class PlanPagos.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 09:56:54 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "PlanPagos", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_EMPRESA)
public class PlanPagos implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id plan pagos. */
    @Id
    @Column(name = "idPlanPagos" , length = 37)
    private String idPlanPagos;
    
    /** El alumno. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAsociado", referencedColumnName = "idAsociado")
    private Asociado asociado;
   
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
  
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAnio", referencedColumnName = "idAnhio")
    private Anhio anio;
    
    /**
     * Instancia un nuevo plan pagos.
     */
    public PlanPagos() {
    }


	public PlanPagos(String idPlanPagos, Asociado asociado, Date fechaCreacion, String usuarioCreacion,
			Date fechaModificacion, String usuarioModificacion,Anhio anio) {
		super();
		this.idPlanPagos = idPlanPagos;
		this.asociado = asociado;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaModificacion = fechaModificacion;
		this.usuarioModificacion = usuarioModificacion;
		this.anio=anio;
	}


	public Anhio getAnio() {
		return anio;
	}


	public void setAnio(Anhio anio) {
		this.anio = anio;
	}


	public String getIdPlanPagos() {
		return idPlanPagos;
	}


	public void setIdPlanPagos(String idPlanPagos) {
		this.idPlanPagos = idPlanPagos;
	}


	public Asociado getAsociado() {
		return asociado;
	}


	public void setAsociado(Asociado asociado) {
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
		PlanPagos other = (PlanPagos) obj;
		if (idPlanPagos == null) {
			if (other.idPlanPagos != null)
				return false;
		} else if (!idPlanPagos.equals(other.idPlanPagos))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "PlanPagos [idPlanPagos=" + idPlanPagos + "]";
	}
   
}