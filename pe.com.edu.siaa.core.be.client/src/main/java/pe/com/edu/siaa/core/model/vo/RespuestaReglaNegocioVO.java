package pe.com.edu.siaa.core.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class RespuestaReglaNegocioVO extends BasePaginator implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 9166621199365741020L;
	
	
	/** La respuesta validacion. */
	private boolean respuestaValidacion;
	
	/** La error regla negocio v os. */
	private List<ErrorValidacionVO> errorReglaNegocioList = new ArrayList<ErrorValidacionVO>();
	
	/**
	 * Instancia un nuevo error regla vo.
	 */
	public RespuestaReglaNegocioVO() {
		super();
	}

	/**
	 * Comprueba si es respuesta validacion.
	 *
	 * @return true, si es respuesta validacion
	 */
	public boolean isRespuestaValidacion() {
		return respuestaValidacion;
	}

	/**
	 * Establece el respuesta validacion.
	 *
	 * @param respuestaValidacion el new respuesta validacion
	 */
	public void setRespuestaValidacion(boolean respuestaValidacion) {
		this.respuestaValidacion = respuestaValidacion;
	}

	/**
	 * Obtiene error regla negocio list.
	 *
	 * @return error regla negocio list
	 */
	public List<ErrorValidacionVO> getErrorReglaNegocioList() {
		return errorReglaNegocioList;
	}

	/**
	 * Establece el error regla negocio list.
	 *
	 * @param errorReglaNegocioList el new error regla negocio list
	 */
	public void setErrorReglaNegocioList(List<ErrorValidacionVO> errorReglaNegocioList) {
		this.errorReglaNegocioList = errorReglaNegocioList;
	}

	
}
