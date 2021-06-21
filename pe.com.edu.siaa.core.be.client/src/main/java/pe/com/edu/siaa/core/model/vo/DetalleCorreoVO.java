package pe.com.edu.siaa.core.model.vo;

import java.io.Serializable;

/**
 * La Class DetalleCorreoVO.
 * <ul>
 * <li>Copyright 2017 ndavilal-
 * mapfre. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Mon Aug 11 09:00:30 COT 2017
 * @since SIAA-CORE 2.1
 */
public class DetalleCorreoVO implements Serializable {
 
	/** La Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** El email. */
	private String email;
	
	/** El tipo. */
	private String tipo;
	
	/**
	 * Instancia un nuevo detalle correo.
	 */
	public DetalleCorreoVO() {
	}
	
	

	/**
	 * Instancia un nuevo detalle correo vo.
	 *
	 * @param email el email
	 * @param tipo el tipo
	 */
	public DetalleCorreoVO(String email, String tipo ) {
		super();
		this.email = email;
		this.tipo = tipo;
	}
	
	//get y set
	
	/**
	 * Obtiene email.
	 *
	 * @return email
	 */
	 public String getEmail() {
		return this.email;
	}
	/**
	 * Establece el email.
	 *
	 * @param email el new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Obtiene tipo.
	 *
	 * @return tipo
	 */
	 public String getTipo() {
		return this.tipo;
	}
	/**
	 * Establece el tipo.
	 *
	 * @param tipo el new tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}