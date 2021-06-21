package pe.com.edu.siaa.core.model.jpa.empresa;

import java.io.Serializable; 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table; 
 
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
@Table(name = "Sector", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_EMPRESA)
public class Sector implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id agrupa entidad. */
    @Id
    @Column(name = "idSector" , length = 32)
    private String idSector;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAsociado", referencedColumnName = "idAsociado")
    private Asociado delegado;
    
    /** El nombre. */
    @Column(name = "descripcion" , length = 200)
    private String descripcion;
    
    /** El nombre. */
    @Column(name = "direccion" , length = 32)
    private String direccion;
    
    /** El nombre. */
    @Column(name = "referencia" , length = 32)
    private String referencia;
    
    @Column(name = "codigo" , length = 32)
    private String codigo;
     
     
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado; 
   
    /**
     * Instancia un nuevo agrupa entidad.
     */
    public Sector() {
    }

	public Sector(String idSector, Asociado delegado, String descripcion, String direccion, String referencia,
			String codigo, String estado) {
		super();
		this.idSector = idSector;
		this.delegado = delegado;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.referencia = referencia;
		this.codigo = codigo;
		this.estado = estado;
	}

	public String getIdSector() {
		return idSector;
	}

	public void setIdSector(String idSector) {
		this.idSector = idSector;
	}

	public Asociado getDelegado() {
		return delegado;
	}

	public void setDelegado(Asociado delegado) {
		this.delegado = delegado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
		result = prime * result + ((idSector == null) ? 0 : idSector.hashCode());
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
		Sector other = (Sector) obj;
		if (idSector == null) {
			if (other.idSector != null)
				return false;
		} else if (!idSector.equals(other.idSector))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sector [idSector=" + idSector + "]";
	}

}