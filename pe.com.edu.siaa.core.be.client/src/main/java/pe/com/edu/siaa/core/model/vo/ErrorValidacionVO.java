package pe.com.edu.siaa.core.model.vo;

import java.io.Serializable;

import pe.com.edu.siaa.core.model.dto.BasePaginator;

/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class ErrorDroolsVO.
 *
 * @author ndavilal
 * @version 1.0 , 06/06/2016
 * @since SIAA-CORE 2.1
 */
public class ErrorValidacionVO extends BasePaginator implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 9166621199365741020L;
	
	/** La id correlativo error. */
	private Long idCorrelativoError;
	
	/** La linea error. */
	private Long lineaError;
	
	/** La tipo error. */
	private String tipoError;
	
	/** La descripcion error. */
	private String descripcionError;
	
	/** La error tecnico. */
	private String errorTecnico;
	
	/** La clase error. */
	private String claseError;
	
	/**
	 * Instancia un nuevo error regla vo.
	 */
	public ErrorValidacionVO() {
		super();
	}
	
	/**
	 * Instancia un nuevo error drools vo.
	 *
	 * @param idCorrelativoError el id correlativo error
	 * @param lineaError el linea error
	 * @param tipoError el tipo error
	 * @param descripcionError el descripcion error
	 */
	public ErrorValidacionVO(Long idCorrelativoError, Long lineaError, String tipoError, String descripcionError) {
		super();
		this.idCorrelativoError = idCorrelativoError;
		this.lineaError = lineaError;
		this.tipoError = tipoError;
		this.descripcionError = descripcionError;
	}
	
	/**
	 * Obtiene id correlativo error.
	 *
	 * @return id correlativo error
	 */
	public Long getIdCorrelativoError() {
		return idCorrelativoError;
	}
	
	/**
	 * Establece el id correlativo error.
	 *
	 * @param idCorrelativoError el new id correlativo error
	 */
	public void setIdCorrelativoError(Long idCorrelativoError) {
		this.idCorrelativoError = idCorrelativoError;
	}
	
	/**
	 * Obtiene linea error.
	 *
	 * @return linea error
	 */
	public Long getLineaError() {
		return lineaError;
	}
	
	/**
	 * Establece el linea error.
	 *
	 * @param lineaError el new linea error
	 */
	public void setLineaError(Long lineaError) {
		this.lineaError = lineaError;
	}
	
	/**
	 * Obtiene descripcion error.
	 *
	 * @return descripcion error
	 */
	public String getDescripcionError() {
		return descripcionError;
	}
	
	/**
	 * Establece el descripcion error.
	 *
	 * @param descripcionError el new descripcion error
	 */
	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}

	/**
	 * Obtiene tipo error.
	 *
	 * @return tipo error
	 */
	public String getTipoError() {
		return tipoError;
	}

	/**
	 * Establece el tipo error.
	 *
	 * @param tipoError el new tipo error
	 */
	public void setTipoError(String tipoError) {
		this.tipoError = tipoError;
	}

	/**
	 * Obtiene error tecnico.
	 *
	 * @return error tecnico
	 */
	public String getErrorTecnico() {
		return errorTecnico;
	}

	/**
	 * Establece el error tecnico.
	 *
	 * @param errorTecnico el new error tecnico
	 */
	public void setErrorTecnico(String errorTecnico) {
		this.errorTecnico = errorTecnico;
	}

	/**
	 * Obtiene clase error.
	 *
	 * @return clase error
	 */
	public String getClaseError() {
		return claseError;
	}

	/**
	 * Establece el clase error.
	 *
	 * @param claseError el new clase error
	 */
	public void setClaseError(String claseError) {
		this.claseError = claseError;
	}
}
