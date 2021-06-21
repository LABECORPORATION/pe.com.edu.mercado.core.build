package pe.com.edu.siaa.core.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class ServicioAutomaticoVO.
 *
 * @author ndavilal
 * @version 1.0 , 06/04/2015
 * @since SIAA-CORE 2.1
 */
public class ServicioAutomaticoVO implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** La check. */
	private boolean check;
	
	/** La codigo. */
	private Integer codigo;
	
	/** La descripcion. */
	private String descripcion;
	
	/** La modulo. */
	private String modulo;
	
	/** La lista dependeica. */
	private List<ServicioAutomaticoVO> listaDependeica = new ArrayList<ServicioAutomaticoVO>();
	
	
	
	/**
	 * Instancia un nuevo servicio automatico vo.
	 */
	public ServicioAutomaticoVO() {
		super();
	}



	/**
	 * Instancia un nuevo servicio automatico vo.
	 *
	 * @param check el check
	 * @param codigo el codigo
	 * @param descripcion el descripcion
	 * @param modulo el modulo
	 */
	public ServicioAutomaticoVO(boolean check, Integer codigo,
			String descripcion, String modulo) {
		super();
		this.check = check;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.modulo = modulo;
	}



	/**
	 * Instancia un nuevo servicio automatico vo.
	 *
	 * @param check el check
	 * @param codigo el codigo
	 * @param descripcion el descripcion
	 * @param modulo el modulo
	 * @param listaDependeica el lista dependeica
	 */
	public ServicioAutomaticoVO(boolean check, Integer codigo,
			String descripcion,String modulo, List<ServicioAutomaticoVO> listaDependeica) {
		super();
		this.check = check;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.modulo = modulo;
		this.listaDependeica = listaDependeica;
	}



	
	/**
	 * Comprueba si es check.
	 *
	 * @return true, si es check
	 */
	public boolean isCheck() {
		return check;
	}



	/**
	 * Establece el check.
	 *
	 * @param check el new check
	 */
	public void setCheck(boolean check) {
		this.check = check;
	}



	/**
	 * Obtiene codigo.
	 *
	 * @return codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}



	/**
	 * Establece el codigo.
	 *
	 * @param codigo el new codigo
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
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
	 * Obtiene modulo.
	 *
	 * @return modulo
	 */
	public String getModulo() {
		return modulo;
	}



	/**
	 * Establece el modulo.
	 *
	 * @param modulo el new modulo
	 */
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}



	/**
	 * Obtiene lista dependeica.
	 *
	 * @return lista dependeica
	 */
	public List<ServicioAutomaticoVO> getListaDependeica() {
		return listaDependeica;
	}



	/**
	 * Establece el lista dependeica.
	 *
	 * @param listaDependeica el new lista dependeica
	 */
	public void setListaDependeica(List<ServicioAutomaticoVO> listaDependeica) {
		this.listaDependeica = listaDependeica;
	}



	
}
