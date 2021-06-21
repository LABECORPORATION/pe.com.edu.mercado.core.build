package pe.com.edu.siaa.core.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class ExcelComboDataVO.
 *
 * @author ndavilal
 * @version 1.0 , 13/04/2016
 * @since SIAA-CORE 2.1
 */
public class ExcelComboDataVO implements Serializable {

    /** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String nombreCampo;
	private Integer posicionCampo;
    private List<String> listaExcelComboData = new ArrayList<String>();
    
	public ExcelComboDataVO() {
		super();
	}

	public String getNombreCampo() {
		return nombreCampo;
	}

	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}

	public Integer getPosicionCampo() {
		return posicionCampo;
	}

	public void setPosicionCampo(Integer posicionCampo) {
		this.posicionCampo = posicionCampo;
	}

	public List<String> getListaExcelComboData() {
		return listaExcelComboData;
	}

	public void setListaExcelComboData(List<String> listaExcelComboData) {
		this.listaExcelComboData = listaExcelComboData;
	}

	
}