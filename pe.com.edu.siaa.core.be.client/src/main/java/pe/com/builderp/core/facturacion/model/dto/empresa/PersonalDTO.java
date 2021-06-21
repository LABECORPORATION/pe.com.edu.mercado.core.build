package pe.com.builderp.core.facturacion.model.dto.empresa;

import java.io.Serializable;
 

import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO; 
 

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
public class PersonalDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    private String idPersonal;
    
    private String nombre;
    
    private String apellidoPaterno;
    
    private String apellidoMaterno;
    
    private String celular;
    
    private String nrodoc;
    
    private ItemDTO tipoPersonal;
    
    private ItemDTO tipoDocumento;
    
    private String direccion;
    
    private String email;
   
    private String estado;
    
    private String foto;
   
   
    /**
     * Instancia un nuevo categoriaDTO.
     */
    public PersonalDTO() {
    }


	public PersonalDTO(String idPersonal, String nombre, String apellidoPaterno, String apellidoMaterno, String celular,
			String nrodoc, ItemDTO tipoPersonal, ItemDTO tipoDocumento, String direccion, String email, String estado,String foto) {
		super();
		this.idPersonal = idPersonal;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.celular = celular;
		this.nrodoc = nrodoc;
		this.tipoPersonal = tipoPersonal;
		this.tipoDocumento = tipoDocumento;
		this.direccion = direccion;
		this.email = email;
		this.estado = estado;
		this.foto=foto;
	}


	public String getIdPersonal() {
		return idPersonal;
	}


	public void setIdPersonal(String idPersonal) {
		this.idPersonal = idPersonal;
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


	public ItemDTO getTipoPersonal() {
		return tipoPersonal;
	}


	public void setTipoPersonal(ItemDTO tipoPersonal) {
		this.tipoPersonal = tipoPersonal;
	}


	public ItemDTO getTipoDocumento() {
		return tipoDocumento;
	}


	public void setTipoDocumento(ItemDTO tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	
	public String getFoto() {
		return foto;
	}


	public void setFoto(String foto) {
		this.foto = foto;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPersonal == null) ? 0 : idPersonal.hashCode());
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
		PersonalDTO other = (PersonalDTO) obj;
		if (idPersonal == null) {
			if (other.idPersonal != null)
				return false;
		} else if (!idPersonal.equals(other.idPersonal))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "PersonalDTO [idPersonal=" + idPersonal + "]";
	}

 
}