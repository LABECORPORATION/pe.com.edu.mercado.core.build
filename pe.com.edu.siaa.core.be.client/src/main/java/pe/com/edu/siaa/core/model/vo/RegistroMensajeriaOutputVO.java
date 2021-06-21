package pe.com.edu.siaa.core.model.vo;

import java.io.Serializable;


/**
 * La Class RegistroMensajeriaOutputVO.
 * <ul>
 * <li>Copyright 2017 ndavilal-
 * mapfre. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Mon Aug 11 09:00:30 COT 2017
 * @since SIAA-CORE 2.1
 */
public class RegistroMensajeriaOutputVO implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** La error. */
	private String error;
	
	/** La estado. */
	private Integer estado;
	
	/** La descripcion estado. */
	private String descripcionEstado;
	
	/**
	 * Obtiene error.
	 *
	 * @return the error
	 */
	public String getError() {
		return error;
	}
	
	/**
	 * Establece el error.
	 *
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}
	
	/**
	 * Obtiene estado.
	 *
	 * @return the estado
	 */
	public Integer getEstado() {
		return estado;
	}
	
	/**
	 * Establece el estado.
	 *
	 * @param estado the estado to set
	 */
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	/**
	 * Obtiene descripcion estado.
	 *
	 * @return the descripcionEstado
	 */
	public String getDescripcionEstado() {
		return descripcionEstado;
	}
	
	/**
	 * Establece el descripcion estado.
	 *
	 * @param descripcionEstado the descripcionEstado to set
	 */
	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}

}
