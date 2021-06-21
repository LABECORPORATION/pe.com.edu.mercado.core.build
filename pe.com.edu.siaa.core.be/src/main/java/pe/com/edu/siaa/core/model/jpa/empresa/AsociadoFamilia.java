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
@Table(name = "AsociadoFamilia", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_EMPRESA)
public class AsociadoFamilia implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id agrupa entidad. */
    @Id
    @Column(name = "idAsociadoFamilia" , length = 32)
    private String idAsociadoFamilia;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAsociado", referencedColumnName = "idAsociado")
    private Asociado asociado;

    /** El nombre. */
    @Column(name = "nombre" , length = 50)
    private String nombre;
    
    /** El apellidoPaterno. */
    @Column(name = "apellidoPaterno" , length = 50)
    private String apellidoPaterno;
    
    /** El apellidoPaterno. */
    @Column(name = "apellidoMaterno" , length = 50)
    private String apellidoMaterno; 
    
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaNacimiento")
    private Date fechaNacimiento;
    
    /** El nombre. */
    @Column(name = "celular" , length = 10)
    private String celular;
    
    /** El nroCuentaCII. */
    @Column(name = "nrodoc" , length = 10)
    private String nrodoc;
    
    /** El nroCuentaCII. */
    @Column(name = "ruc" , length = 10)
    private String ruc;
     
    /** El foto. */
    @Column(name = "parentesco" , length = 50)
    private String parentesco;
   
    /**
     * Instancia un nuevo agrupa entidad.
     */
    public AsociadoFamilia() {
    }

	public AsociadoFamilia(String idAsociadoFamilia, Asociado asociado, String nombre, String apellidoPaterno,
			String apellidoMaterno, Date fechaNacimiento, String celular, String nrodoc, String ruc,
			String parentesco) {
		super();
		this.idAsociadoFamilia = idAsociadoFamilia;
		this.asociado = asociado;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.fechaNacimiento = fechaNacimiento;
		this.celular = celular;
		this.nrodoc = nrodoc;
		this.ruc = ruc;
		this.parentesco = parentesco;
	}

	public String getIdAsociadoFamilia() {
		return idAsociadoFamilia;
	}

	public void setIdAsociadoFamilia(String idAsociadoFamilia) {
		this.idAsociadoFamilia = idAsociadoFamilia;
	}

	public Asociado getAsociado() {
		return asociado;
	}

	public void setAsociado(Asociado asociado) {
		this.asociado = asociado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getNrodoc() {
		return nrodoc;
	}

	public void setNrodoc(String nrodoc) {
		this.nrodoc = nrodoc;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAsociadoFamilia == null) ? 0 : idAsociadoFamilia.hashCode());
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
		AsociadoFamilia other = (AsociadoFamilia) obj;
		if (idAsociadoFamilia == null) {
			if (other.idAsociadoFamilia != null)
				return false;
		} else if (!idAsociadoFamilia.equals(other.idAsociadoFamilia))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AsociadoFamilia [idAsociadoFamilia=" + idAsociadoFamilia + "]";
	}
     
}