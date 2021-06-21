package pe.com.edu.siaa.core.vo;

import java.io.Serializable;
import java.util.List;

/**
 * La Class ResultadoRestVO<T>.
 * <ul>
 * <li>Copyright 2015 NDAVILAL -
 * NDAVILAL. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author Natan Angel Davila Lopez
 * @version 1.0, Wed Feb 11 11:36:54 COT 2015
 * @since BIBLIA-MMM 1.0
 */
public class ResultadoRestVO<T> implements Serializable{

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private List<T> listaResultado;
	private T objetoResultado;
	private Integer contador;
	private String codigoError;
	private String mensajeError;
	private boolean isError;
	private String authToken;
	
	
	
	public ResultadoRestVO() {
		super();
	}


	public ResultadoRestVO(List<T> listaResultado, T objetoResultado,
			String codigoError, String mensajeError, boolean isError) {
		super();
		this.listaResultado = listaResultado;
		this.objetoResultado = objetoResultado;
		this.codigoError = codigoError;
		this.mensajeError = mensajeError;
		this.isError = isError;
	}


	public List<T> getListaResultado() {
		return listaResultado;
	}


	public void setListaResultado(List<T> listaResultado) {
		this.listaResultado = listaResultado;
	}


	public T getObjetoResultado() {
		if (listaResultado != null && listaResultado.size() > 0) {
			objetoResultado = listaResultado.get(0);
		} 
		return objetoResultado;
	}



	public Integer getContador() {
		return contador;
	}


	public void setContador(Integer contador) {
		this.contador = contador;
	}


	public String getCodigoError() {
		return codigoError;
	}


	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}


	public String getMensajeError() {
		return mensajeError;
	}


	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}


	public boolean isError() {
		return isError;
	}


	public void setError(boolean isError) {
		this.isError = isError;
	}


	public void setObjetoResultado(T objetoResultado) {
		this.objetoResultado = objetoResultado;
	}


	public String getAuthToken() {
		return authToken;
	}


	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}


	public boolean isData() {
		return contador != null && contador > 0;
	}
	
}