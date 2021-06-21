package pe.com.edu.siaa.core.model.vo;

import java.io.Serializable;
import java.util.List;


/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class SelectItemVO.
 *
 * @author ndavilal
 * @version 1.0 , 06/04/2015
 * @since SIAA-CORE 2.1
 */
public class FormatDataSerieGraficoVO implements Serializable {
 
	/** La Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	
	/** El nombre. */
	private String name;
	
	/** La descripcion. */
	private List<FormatDataGraficoVO> series;

	
	
	public FormatDataSerieGraficoVO(String name, List<FormatDataGraficoVO> series) {
		super();
		this.name = name;
		this.series = series;
	}


	/**
	 * Instancia un nuevo select item vo.
	 */
	public FormatDataSerieGraficoVO() {
		super();
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<FormatDataGraficoVO> getSeries() {
		return series;
	}


	public void setSeries(List<FormatDataGraficoVO> series) {
		this.series = series;
	}


	

	
	
	
}