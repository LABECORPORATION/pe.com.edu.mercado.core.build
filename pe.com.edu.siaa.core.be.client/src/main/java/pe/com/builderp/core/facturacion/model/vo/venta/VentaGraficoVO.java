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
public class VentaGraficoVO extends BasePaginator  implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = -5568277544450873979L;

	/** El id. */
	private String chartType;
	
	private String anho;
		
	private String mes;
	
	private int numT;
	
	private int numC;
	
	/** La lista curso nota. */
    private List<TendenciasVO> dataTendencias = new ArrayList<TendenciasVO>();
	
	/** La lista curso nota. */
    private List<VentaAnhoVO> datatemp = new ArrayList<VentaAnhoVO>();
		
	/** La lista curso nota. */
    private Map<Integer,List<FormatDataGraficoGananciasVO>> datasets = new HashMap<Integer,List<FormatDataGraficoGananciasVO>>();
	

	
	/**
	 * Instancia un nuevo concepto pago dto.
	 */
	public VentaGraficoVO() {
		super();
	}


	public String getChartType() {
		return chartType;
	}


	public void setChartType(String chartType) {
		this.chartType = chartType;
	}


	 

	public int getNumC() {
		return numC;
	}


	public void setNumC(int numC) {
		this.numC = numC;
	}


	public int getNumT() {
		return numT;
	}


	public void setNumT(int numT) {
		this.numT = numT;
	}


	public String getAnho() {
		return anho;
	}


	public void setAnho(String anho) {
		this.anho = anho;
	}


	public String getMes() {
		return mes;
	}


	public void setMes(String mes) {
		this.mes = mes;
	}


	public Map<Integer, List<FormatDataGraficoGananciasVO>> getDatasets() {
		return datasets;
	}


	public void setDatasets(Map<Integer, List<FormatDataGraficoGananciasVO>> datasets) {
		this.datasets = datasets;
	}


	public List<VentaAnhoVO> getDatatemp() {
		return datatemp;
	}


	public void setDatatemp(List<VentaAnhoVO> datatemp) {
		this.datatemp = datatemp;
	}


	public List<TendenciasVO> getDataTendencias() {
		return dataTendencias;
	}


	public void setDataTendencias(List<TendenciasVO> dataTendencias) {
		this.dataTendencias = dataTendencias;
	}
	
	

}
