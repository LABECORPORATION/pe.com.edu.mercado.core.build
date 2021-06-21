package pe.com.edu.siaa.core.model.vo;

import java.io.Serializable;

import pe.com.edu.siaa.core.model.type.TipoCampoType;

/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class ValueDataVO.
 *
 * @author ndavilal
 * @version 1.0 , 23/06/2016
 * @since SIAA-CORE 2.1
 */
public class ValueDataVO implements Serializable {
	
	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** La fila. */
	private String fila;
	
	/** La data. */
	private Object data;
	
	/** La tipo campo type. */
	private TipoCampoType tipoCampoType = null; 
	
	/**
	 * Instancia un nuevo value data vo.
	 */
	public ValueDataVO() {
		super();
	}

	/**
	 * Instancia un nuevo value data vo.
	 *
	 * @param data el data
	 */
	public ValueDataVO(Object data) {
		super();
		this.data = data;
	}

	/**
	 * Instancia un nuevo value data vo.
	 *
	 * @param valueDataVO el value data vo
	 */
	public ValueDataVO(ValueDataVO valueDataVO) {
		super();
		this.data = valueDataVO.getData();
		this.fila = valueDataVO.getFila();
	}

	/**
	 * Obtiene fila.
	 *
	 * @return fila
	 */
	public String getFila() {
		return fila;
	}
	
	/**
	 * Establece el fila.
	 *
	 * @param fila el new fila
	 */
	public void setFila(String fila) {
		this.fila = fila;
	}
	
	/**
	 * Obtiene data.
	 *
	 * @return data
	 */
	public Object getData() {
		return data;
	}
	
	/**
	 * Establece el data.
	 *
	 * @param data el new data
	 */
	public void setData(Object data) {
		this.data = data;
	}


	/**
	 * Obtiene tipo campo type.
	 *
	 * @return tipo campo type
	 */
	public TipoCampoType getTipoCampoType() {
		return tipoCampoType;
	}

	/**
	 * Establece el tipo campo type.
	 *
	 * @param tipoCampoType el new tipo campo type
	 */
	public void setTipoCampoType(TipoCampoType tipoCampoType) {
		this.tipoCampoType = tipoCampoType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (data != null) {
			return  data + "";
		}
		return  "";
	} 
	
}

