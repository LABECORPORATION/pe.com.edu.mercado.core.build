package pe.com.builderp.core.facturacion.model.vo.venta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.com.edu.siaa.core.model.dto.BasePaginator;


/**
 * <ul>
 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Class RegistroNotaDTO.
 *
 * @author ndavilal.
 * @version 1.0 , 06/10/2012
 * @since SIAA 2.0
 */
public class VentaAnhoVO extends BasePaginator  implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = -5568277544450873979L;

	
	
	private String anho;	
	
	
	/** La lista curso nota. */
    private List<FormatDataGraficoGananciasVO> datasets = new ArrayList<FormatDataGraficoGananciasVO>() ;
	
    List<BigDecimal>  data = new ArrayList<BigDecimal>();
	
	/**
	 * Instancia un nuevo concepto pago dto.
	 */
	public VentaAnhoVO() {
		super();
	}

	public String getAnho() {
		return anho;
	}

	public void setAnho(String anho) {
		this.anho = anho;
	}

	public List<FormatDataGraficoGananciasVO> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<FormatDataGraficoGananciasVO> datasets) {
		this.datasets = datasets;
	}

	public List<BigDecimal> getData() {
		return data;
	}

	public void setData(List<BigDecimal> data) {
		this.data = data;
	}

	

	
}
