package pe.com.edu.siaa.core.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class ScriptSqlResulVO.
 *
 * @author ndavilal
 * @version 1.0 , 06/04/2015
 * @since SIAA-CORE 2.1
 */
public class ScriptSqlResulVO implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
    /** La lista header. */
    private List<String> listaHeader = new ArrayList<String>();
    
    /** La lista data. */
    private List<Map<String,Object>> listaData = new ArrayList<Map<String,Object>>();
    
    /** La tiene error. */
    private boolean tieneError = false;
    
    /** La mensaje error. */
    private String mensajeError = "";
    
	/**
	 * Instancia un nuevo script sql resul vo.
	 */
	public ScriptSqlResulVO() {
		super();
	}

	/**
	 * Instancia un nuevo script sql resul vo.
	 *
	 * @param listaHeader el lista header
	 * @param listaData el lista data
	 */
	public ScriptSqlResulVO(List<String> listaHeader, List<Map<String,Object>> listaData) {
		super();
		this.listaHeader = listaHeader;
		this.listaData = listaData;
	}

	/**
     * Obtiene lista header.
     *
     * @return lista header
     */
    public List<String> getListaHeader() {
		return listaHeader;
	}

	/**
	 * Establece el lista header.
	 *
	 * @param listaHeader el new lista header
	 */
	public void setListaHeader(List<String> listaHeader) {
		this.listaHeader = listaHeader;
	}

	/**
	 * Obtiene lista data.
	 *
	 * @return lista data
	 */
	public List<Map<String,Object>> getListaData() {
		return listaData;
	}

	/**
	 * Establece el lista data.
	 *
	 * @param listaData el new lista data
	 */
	public void setListaData(List<Map<String,Object>> listaData) {
		this.listaData = listaData;
	}

	/**
	 * Comprueba si es tiene error.
	 *
	 * @return true, si es tiene error
	 */
	public boolean isTieneError() {
		return tieneError;
	}

	/**
	 * Establece el tiene error.
	 *
	 * @param tieneError el new tiene error
	 */
	public void setTieneError(boolean tieneError) {
		this.tieneError = tieneError;
	}

	/**
	 * Obtiene mensaje error.
	 *
	 * @return mensaje error
	 */
	public String getMensajeError() {
		return mensajeError;
	}

	/**
	 * Establece el mensaje error.
	 *
	 * @param mensajeError el new mensaje error
	 */
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
	
	
}
