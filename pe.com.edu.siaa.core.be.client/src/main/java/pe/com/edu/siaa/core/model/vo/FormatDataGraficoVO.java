package pe.com.edu.siaa.core.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class FormatDataGraficoVO implements Serializable {
 
	/** La Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	
	/** El nombre. */
	private String name;
	
	private BigDecimal value;
	
	/**
	 * Instancia un nuevo select item vo.
	 */
	public FormatDataGraficoVO() {
		super();
	}

	public FormatDataGraficoVO(String name, BigDecimal value) {
		super();
		this.name = name;
		this.value = value;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public BigDecimal getValue() {
		return value;
	}


	public void setValue(BigDecimal value) {
		this.value = value;
	}

	
	
}