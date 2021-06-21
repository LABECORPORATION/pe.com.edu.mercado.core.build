package pe.com.edu.siaa.core.model.dto.seguridad;

import java.io.Serializable; 

import pe.com.edu.siaa.core.model.dto.BasePaginator; 

 

/**
 * La Class UsuarioDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Thu Dec 14 00:27:45 COT 2017
 * @since SIAA-CORE 2.1
 */
public class RecuperarPasswordDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id usuario. */
    private String idRecuperar;
   
    /** El nombre. */
    private UsuarioDTO usuario;
   
    
   
    /**
     * Instancia un nuevo usuarioDTO.
     */
    public RecuperarPasswordDTO() {
    }



 



	public RecuperarPasswordDTO(String idRecuperar, UsuarioDTO usuario) {
		super();
		this.idRecuperar = idRecuperar;
		this.usuario = usuario;
	}




	public String getIdRecuperar() {
		return idRecuperar;
	}


	public void setIdRecuperar(String idRecuperar) {
		this.idRecuperar = idRecuperar;
	}




	public UsuarioDTO getUsuario() {
		return usuario;
	}




	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}







	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRecuperar == null) ? 0 : idRecuperar.hashCode());
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
		RecuperarPasswordDTO other = (RecuperarPasswordDTO) obj;
		if (idRecuperar == null) {
			if (other.idRecuperar != null)
				return false;
		} else if (!idRecuperar.equals(other.idRecuperar))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "RecuperarPasswordDTO [idRecuperar=" + idRecuperar + "]";
	}
   
    
    
}