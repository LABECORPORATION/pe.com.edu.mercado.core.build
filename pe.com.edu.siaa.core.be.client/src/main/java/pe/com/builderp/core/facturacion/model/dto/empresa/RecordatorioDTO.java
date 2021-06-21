package pe.com.builderp.core.facturacion.model.dto.empresa;

import java.io.Serializable; 
import java.util.Date;
 
import pe.com.edu.siaa.core.model.dto.BasePaginator; 
 

/**
 * La Class CategoriaDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Fri Dec 22 11:07:00 COT 2017
 * @since SIAA-CORE 2.1
 */
public class RecordatorioDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    private String idRecordatorio;
   
    private String nombre;
    
    private String descripcion;
    
    private Date fechaInicio;
    
    private Date fechaFin;
    
    private String estado;
   
    /**
     * Instancia un nuevo categoriaDTO.
     */
    public RecordatorioDTO() {
    }

	public RecordatorioDTO(String idRecordatorio, String nombre, String descripcion, Date fechaInicio, Date fechaFin,
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
		RecordatorioDTO other = (RecordatorioDTO) obj;
		if (idRecordatorio == null) {
			if (other.idRecordatorio != null)
				return false;
		} else if (!idRecordatorio.equals(other.idRecordatorio))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RecordatorioDTO [idRecordatorio=" + idRecordatorio + "]";
	}

 }