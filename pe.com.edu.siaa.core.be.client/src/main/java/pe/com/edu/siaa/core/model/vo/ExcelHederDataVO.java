package pe.com.edu.siaa.core.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class ExcelHederDataVO.
 *
 * @author ndavilal
 * @version 1.0 , 06/04/2015
 * @since SIAA-CORE 2.1
 */
public class ExcelHederDataVO implements Serializable {

    /** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** El nameHeader. */
    private String nameHeader;
    
    /** El nameAtribute. */
    private String nameAtribute;
    
    private Integer posicionCelda = -1;
    
    private Integer cantidadAgrupar =  0;

    private List<ExcelHederDataVO> listaExcelHederDataVO = new ArrayList<ExcelHederDataVO>();
    
    
	public ExcelHederDataVO() {
		super();
	}

	/**
	 * Instancia un nuevo excel heder data vo.
	 *
	 * @param nameHeader el name header
	 * @param nameAtribute el name atribute
	 */
	public ExcelHederDataVO(String nameHeader, String nameAtribute,Integer cantidadAgrupar ) {
		super();
		this.nameHeader = nameHeader;
		this.nameAtribute = nameAtribute;
		this.cantidadAgrupar = cantidadAgrupar;
	}
	
	public ExcelHederDataVO(String nameHeader, String nameAtribute ) {
		super();
		this.nameHeader = nameHeader;
		this.nameAtribute = nameAtribute;
	}

	
	public ExcelHederDataVO(String nameHeader, String nameAtribute,Integer cantidadAgrupar,
			Integer posicionCelda) {
		super();
		this.nameHeader = nameHeader;
		this.nameAtribute = nameAtribute;
		this.cantidadAgrupar = cantidadAgrupar;
		this.posicionCelda = posicionCelda;
	}

	/**
	 * Obtiene name header.
	 *
	 * @return name header
	 */
	public String getNameHeader() {
		return nameHeader;
	}

	/**
	 * Establece el name header.
	 *
	 * @param nameHeader el new name header
	 */
	public void setNameHeader(String nameHeader) {
		this.nameHeader = nameHeader;
	}

	/**
	 * Obtiene name atribute.
	 *
	 * @return name atribute
	 */
	public String getNameAtribute() {
		return nameAtribute;
	}

	/**
	 * Establece el name atribute.
	 *
	 * @param nameAtribute el new name atribute
	 */
	public void setNameAtribute(String nameAtribute) {
		this.nameAtribute = nameAtribute;
	}

	public Integer getPosicionCelda() {
		return posicionCelda;
	}

	public void setPosicionCelda(Integer posicionCelda) {
		this.posicionCelda = posicionCelda;
	}
	
	

	public Integer getCantidadAgrupar() {
		return cantidadAgrupar;
	}

	public void setCantidadAgrupar(Integer cantidadAgrupar) {
		this.cantidadAgrupar = cantidadAgrupar;
	}

	public List<ExcelHederDataVO> getListaExcelHederDataVO() {
		return listaExcelHederDataVO;
	}

	public void setListaExcelHederDataVO(
			List<ExcelHederDataVO> listaExcelHederDataVO) {
		this.listaExcelHederDataVO = listaExcelHederDataVO;
	}
	
}