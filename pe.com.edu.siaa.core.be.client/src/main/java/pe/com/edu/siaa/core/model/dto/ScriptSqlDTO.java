package pe.com.edu.siaa.core.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pe.com.edu.siaa.core.model.dto.reporte.ParametrosScriptSqlDTO;


/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class ScriptSqlDTO.
 *
 * @author ndavilal
 * @version 1.0 , 06/04/2015
 * @since SIAA-CORE 2.1
 */
public class ScriptSqlDTO implements Serializable {
 
	/** La Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** La codigo compania. */
	private Long codigoCompania;
	
	/** La codigo programa. */
	private String codigoPrograma;
	
	/** La tipo sentencia. */
	private String tipoSentencia;
	
	/** La descripcion. */
	private String descripcion;
	
	/** La observacion. */
	private String  observacion;
	
	/** La sentencia. */
	private String sentencia  = "";
	
	/** La lista parametros script sql dto. */
	private List<ParametrosScriptSqlDTO> listaParametrosScriptSqlDTO = new ArrayList<ParametrosScriptSqlDTO>();
	  
	
	/**
	 * Instancia un nuevo script sql dto.
	 */
	public ScriptSqlDTO() {
		super();
	}

	
	/**
	 * Instancia un nuevo script sql dto.
	 *
	 * @param codigoCompania el codigo compania
	 * @param codigoPrograma el codigo programa
	 * @param tipoSentencia el tipo sentencia
	 * @param descripcion el descripcion
	 * @param observacion el observacion
	 * @param sentencia el sentencia
	 */
	public ScriptSqlDTO(Long codigoCompania, String codigoPrograma,
			String tipoSentencia, String descripcion, String observacion,
			String sentencia) {
		super();
		this.codigoCompania = codigoCompania;
		this.codigoPrograma = codigoPrograma;
		this.tipoSentencia = tipoSentencia;
		this.descripcion = descripcion;
		this.observacion = observacion;
		this.sentencia = sentencia;
	}

	
	
	/**
	 * Obtiene codigo compania.
	 *
	 * @return codigo compania
	 */
	public Long getCodigoCompania() {
		return codigoCompania;
	}

	/**
	 * Establece el codigo compania.
	 *
	 * @param codigoCompania el new codigo compania
	 */
	public void setCodigoCompania(Long codigoCompania) {
		this.codigoCompania = codigoCompania;
	}

	/**
	 * Obtiene codigo programa.
	 *
	 * @return codigo programa
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}

	/**
	 * Establece el codigo programa.
	 *
	 * @param codigoPrograma el new codigo programa
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}

	/**
	 * Obtiene tipo sentencia.
	 *
	 * @return tipo sentencia
	 */
	public String getTipoSentencia() {
		return tipoSentencia;
	}

	/**
	 * Establece el tipo sentencia.
	 *
	 * @param tipoSentencia el new tipo sentencia
	 */
	public void setTipoSentencia(String tipoSentencia) {
		this.tipoSentencia = tipoSentencia;
	}

	/**
	 * Obtiene descripcion.
	 *
	 * @return descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Establece el descripcion.
	 *
	 * @param descripcion el new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Obtiene observacion.
	 *
	 * @return observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * Establece el observacion.
	 *
	 * @param observacion el new observacion
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * Obtiene sentencia.
	 *
	 * @return sentencia
	 */
	public String getSentencia() {
		return sentencia;
	}

	/**
	 * Establece el sentencia.
	 *
	 * @param sentencia el new sentencia
	 */
	public void setSentencia(String sentencia) {
		this.sentencia = sentencia;
	}


	/**
	 * Obtiene lista parametros script sql dto.
	 *
	 * @return lista parametros script sql dto
	 */
	public List<ParametrosScriptSqlDTO> getListaParametrosScriptSqlDTO() {
		return listaParametrosScriptSqlDTO;
	}


	/**
	 * Establece el lista parametros script sql dto.
	 *
	 * @param listaParametrosScriptSqlDTO el new lista parametros script sql dto
	 */
	public void setListaParametrosScriptSqlDTO(
			List<ParametrosScriptSqlDTO> listaParametrosScriptSqlDTO) {
		this.listaParametrosScriptSqlDTO = listaParametrosScriptSqlDTO;
	}
	
	
	
}