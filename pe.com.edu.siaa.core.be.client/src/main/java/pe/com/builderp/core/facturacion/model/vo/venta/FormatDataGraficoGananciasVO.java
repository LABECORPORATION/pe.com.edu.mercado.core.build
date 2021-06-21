package pe.com.builderp.core.facturacion.model.vo.venta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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
public class FormatDataGraficoGananciasVO implements Serializable {
 
	/** La Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	
	/** El nombre. */
	private String fill;
		
	/** La lista curso nota. */
    private List<BigDecimal>  data = new ArrayList<BigDecimal>();
	
    private String label;
	/**
	 * Instancia un nuevo select item vo.
	 */
	public FormatDataGraficoGananciasVO() {
		super();
	}

	public FormatDataGraficoGananciasVO(String fill, String label) {
		super();
		this.fill = fill;
		this.label = label;
	}

	

	public String getFill() {
		return fill;
	}

	public void setFill(String fill) {
		this.fill = fill;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<BigDecimal> getData() {
		return data;
	}

	public void setData(List<BigDecimal> data) {
		this.data = data;
	}


	
}