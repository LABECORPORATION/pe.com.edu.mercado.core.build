package pe.com.edu.siaa.core.model.vo;

import java.util.ArrayList;
import java.util.List;


/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class AtributoEntityVO.
 *
 * @author ndavilal
 * @version 1.0 , 06/04/2015
 * @since SIAA-CORE 2.1
 */
public class AtributoEntityVO {

	private String tableName;
	private String schema;
	
	/** La nombre atributo. */
	private String nombreAtributo;
	
	/** La nombre columna. */
	private String nombreColumna;
	
	/** La classs atributo. */
	private Object classsAtributo;
	
	/** La classs atributo. */
	private Class<?> classsAtributoType;
	
	/** La posicion. */
	private int posicion;
	
	private boolean pKCompuesta = false;
	private boolean column = false;
	
	private List<AtributoEntityVO> listaAtributoEntityVOPK = new ArrayList<AtributoEntityVO>();
	
	private boolean esPK = false;
	/**
	 * Instancia un nuevo atributo entity vo.
	 */
	public AtributoEntityVO() {
		super();
	}

	
	/**
	 * Instancia un nuevo atributo entity vo.
	 *
	 * @param nombreAtributo el nombre atributo
	 * @param classsAtributo el classs atributo
	 */
	public AtributoEntityVO(String nombreAtributo, Object classsAtributo) {
		super();
		this.nombreAtributo = nombreAtributo;
		this.classsAtributo = classsAtributo;
	}


	/**
	 * Instancia un nuevo atributo entity vo.
	 *
	 * @param nombreAtributo el nombre atributo
	 * @param nombreColumna el nombre columna
	 * @param classsAtributo el classs atributo
	 * @param posicion el posicion
	 */
	public AtributoEntityVO(String nombreAtributo, String nombreColumna, Object classsAtributo,
			int posicion) {
		super();
		this.nombreAtributo = nombreAtributo;
		this.nombreColumna = nombreColumna;
		this.classsAtributo = classsAtributo;
		this.posicion = posicion;
	}

	/**
	 * Obtiene nombre atributo.
	 *
	 * @return nombre atributo
	 */
	public String getNombreAtributo() {
		return nombreAtributo;
	}

	/**
	 * Establece el nombre atributo.
	 *
	 * @param nombreAtributo el new nombre atributo
	 */
	public void setNombreAtributo(String nombreAtributo) {
		this.nombreAtributo = nombreAtributo;
	}

	
	/**
	 * Obtiene nombre columna.
	 *
	 * @return nombre columna
	 */
	public String getNombreColumna() {
		return nombreColumna;
	}


	/**
	 * Establece el nombre columna.
	 *
	 * @param nombreColumna el new nombre columna
	 */
	public void setNombreColumna(String nombreColumna) {
		this.nombreColumna = nombreColumna;
	}


	/**
	 * Obtiene classs atributo.
	 *
	 * @return classs atributo
	 */
	public Object getClasssAtributo() {
		return classsAtributo;
	}

	/**
	 * Establece el classs atributo.
	 *
	 * @param classsAtributo el new classs atributo
	 */
	public void setClasssAtributo(Object classsAtributo) {
		this.classsAtributo = classsAtributo;
	}

	/**
	 * Obtiene posicion.
	 *
	 * @return posicion
	 */
	public int getPosicion() {
		return posicion;
	}

	/**
	 * Establece el posicion.
	 *
	 * @param posicion el new posicion
	 */
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}


	public Class<?> getClasssAtributoType() {
		return classsAtributoType;
	}


	public void setClasssAtributoType(Class<?> classsAtributoType) {
		this.classsAtributoType = classsAtributoType;
	}


	public String getTableName() {
		return tableName;
	}


	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	public String getSchema() {
		return schema;
	}


	public void setSchema(String schema) {
		this.schema = schema;
	}


	public List<AtributoEntityVO> getListaAtributoEntityVOPK() {
		return listaAtributoEntityVOPK;
	}


	public void setListaAtributoEntityVOPK(
			List<AtributoEntityVO> listaAtributoEntityVOPK) {
		this.listaAtributoEntityVOPK = listaAtributoEntityVOPK;
	}


	public boolean isPKCompuesta() {
		return pKCompuesta;
	}


	public void setPKCompuesta(boolean pKCompuesta) {
		this.pKCompuesta = pKCompuesta;
	}


	public boolean isColumn() {
		return column;
	}


	public void setColumn(boolean column) {
		this.column = column;
	}

	public boolean isEsPK() {
		return esPK;
	}


	public void setEsPK(boolean esPK) {
		this.esPK = esPK;
	}


	

	@Override
	public String toString() {
		return "AtributoEntityVO [tableName=" + tableName + ", schema="
				+ schema + ", nombreAtributo=" + nombreAtributo
				+ ", nombreColumna=" + nombreColumna + ", classsAtributo="
				+ classsAtributo + ", classsAtributoType=" + classsAtributoType
				+ ", posicion=" + posicion + ", pKCompuesta=" + pKCompuesta
				+ ", column=" + column + ", listaAtributoEntityVOPK="
				+ listaAtributoEntityVOPK + "]";
	}


	
	
	
	
}
