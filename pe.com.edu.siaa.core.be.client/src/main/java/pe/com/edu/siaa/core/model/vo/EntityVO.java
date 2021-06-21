package pe.com.edu.siaa.core.model.vo;

/**
 * La Class EntityVO.
 * <ul>
 * <li>Copyright 2017 ndavilal-
 * mapfre. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Mon Aug 11 09:00:30 COT 2017
 * @since SIAA-CORE 2.1
 */
public class EntityVO {

	/** El alias. */
	private String alias;
	
	/** El classs. */
	private String classs;
	
	/** La posicion. */
	private int posicion;
	
	
	/**
	 * Instancia un nuevo entity vo.
	 */
	public EntityVO() {
		super();
	}

	

	/**
	 * Instancia un nuevo entity vo.
	 *
	 * @param alias el alias
	 * @param classs el classs
	 */
	public EntityVO(String alias, String classs) {
		super();
		this.alias = alias;
		this.classs = classs;
	}



	/**
	 * Instancia un nuevo entity vo.
	 *
	 * @param alias el alias
	 * @param classs el classs
	 * @param posicion el posicion
	 */
	public EntityVO(String alias, String classs, int posicion) {
		super();
		this.alias = alias;
		this.classs = classs;
		this.posicion = posicion;
	}



	/**
	 * Obtiene alias.
	 *
	 * @return alias
	 */
	public String getAlias() {
		return alias;
	}


	/**
	 * Establece el alias.
	 *
	 * @param alias el new alias
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}


	/**
	 * Obtiene classs.
	 *
	 * @return classs
	 */
	public String getClasss() {
		return classs;
	}


	/**
	 * Establece el classs.
	 *
	 * @param classs el new classs
	 */
	public void setClasss(String classs) {
		this.classs = classs;
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

}
