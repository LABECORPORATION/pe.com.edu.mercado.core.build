package pe.com.edu.siaa.core.model.jpa.empresa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.Id; 
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
 

import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class AgrupaEntidad.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Wed Dec 20 00:30:21 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "Recordatorio", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_EMPRESA)
public class Recordatorio implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id agrupa entidad. */
    @Id
    @Column(name = "idRecordatorio" , length = 32)
    private String idRecordatorio;

    /** El estado. */
    @Column(name = "nombre" , length = 200)
    private String nombre;
    
    /** El estado. */
    @Column(name = "descripcion" , length = 200)
    private String descripcion;
    
    
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaInicio")
    private Date fechaInicio;
    
    
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaFin")
    private Date fechaFin;
     
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
   
    /**
     * Instancia un nuevo agrupa entidad.
     */
    public Recordatorio() {
    }

	public Recordatorio(String idRecordatorio, String nombre, String descripcion, Date fechaInicio, Date fechaFin,
			String estado) {
		super();
		this.idRecordatorio = idRecordatorio;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estado = estado;
	}

	public String getIdRecordatorio() {
		return idRecordatorio;
	}

	public void setIdRecordatorio(String idRecordatorio) {
		this.idRecordatorio = idRecordatorio;
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
		result = prime * result + ((idRecordatorio == null) ? 0 : idRecordatorio.hashCode());
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
		Recordatorio other = (Recordatorio) obj;
		if (idRecordatorio == null) {
			if (other.idRecordatorio != null)
				return false;
		} else if (!idRecordatorio.equals(other.idRecordatorio))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Recordatorio [idRecordatorio=" + idRecordatorio + "]";
	}

}