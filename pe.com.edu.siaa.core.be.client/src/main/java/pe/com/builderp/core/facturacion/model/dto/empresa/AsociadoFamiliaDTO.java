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
public class AsociadoFamiliaDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    private String idAsociadoFamilia;
    
    private AsociadoDTO asociado;
    
    private String nombre;
    
    private String apellidoPaterno;
    
    private String apellidoMaterno; 
    
    private Date fechaNacimiento;
    
    private String celular;
    
    private String nrodoc;
    
    private String ruc;
     
    private String parentesco;
   
   
    /**
     * Instancia un nuevo categoriaDTO.
     */
    public AsociadoFamiliaDTO() {
    }


	public AsociadoFamiliaDTO(String idAsociadoFamilia, AsociadoDTO asociado, String nombre, String apellidoPaterno,
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


	public AsociadoDTO getAsociado() {
		return asociado;
	}


	public void setAsociado(AsociadoDTO asociado) {
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
		AsociadoFamiliaDTO other = (AsociadoFamiliaDTO) obj;
		if (idAsociadoFamilia == null) {
			if (other.idAsociadoFamilia != null)
				return false;
		} else if (!idAsociadoFamilia.equals(other.idAsociadoFamilia))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "AsociadoFamiliaDTO [idAsociadoFamilia=" + idAsociadoFamilia + "]";
	}

}