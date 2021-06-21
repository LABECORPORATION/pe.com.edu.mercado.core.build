package pe.com.edu.siaa.core.model.jpa.seguridad;

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
 * La Class Usuario.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Wed Dec 13 22:04:28 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "RecuperarPassword", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_SECURIDAD)
public class RecuperarPassword implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id usuario. */
    @Id
    @Column(name = "idRecuperar" , length = 32)
    private String idRecuperar;
   
    /** El tipo usuario. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private Usuario usuario;
   

    /**
     * Instancia un nuevo usuario.
     */
    public RecuperarPassword() {
    }


 

	public RecuperarPassword(String idRecuperar, Usuario usuario) {
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




	public Usuario getUsuario() {
		return usuario;
	}




	public void setUsuario(Usuario usuario) {
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
		RecuperarPassword other = (RecuperarPassword) obj;
		if (idRecuperar == null) {
			if (other.idRecuperar != null)
				return false;
		} else if (!idRecuperar.equals(other.idRecuperar))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "RecuperarPassword [idRecuperar=" + idRecuperar + "]";
	}
   
   
     
}