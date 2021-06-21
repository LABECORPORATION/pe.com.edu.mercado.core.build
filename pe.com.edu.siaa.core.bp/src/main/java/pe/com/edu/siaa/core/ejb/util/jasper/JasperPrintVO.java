package pe.com.edu.siaa.core.ejb.util.jasper;

import java.io.Serializable;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.fill.JRAbstractLRUVirtualizer;

/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class JaspertPrintVO.
 *
 * @author ndavilal
 * @version 1.0 , 04/08/2016
 * @since SIAA-CORE 2.1
 */
public class JasperPrintVO implements Serializable {
	
	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 8226758990925789085L;

	/** La jasper print. */
	private JasperPrint jasperPrint;
	
	/** La virtualizer. */
	private JRAbstractLRUVirtualizer virtualizer;
	
	/**
	 * Instancia un nuevo jaspert print vo.
	 */
	public JasperPrintVO() {
		super();
	}
	
	/**
	 * Instancia un nuevo jaspert print vo.
	 *
	 * @param jasperPrint el jasper print
	 * @param virtualizer el virtualizer
	 */
	public JasperPrintVO(JasperPrint jasperPrint, JRAbstractLRUVirtualizer virtualizer) {
		super();
		this.jasperPrint = jasperPrint;
		this.virtualizer = virtualizer;
	}

	/**
	 * Obtiene jasper print.
	 *
	 * @return jasper print
	 */
	public JasperPrint getJasperPrint() {
		return jasperPrint;
	}

	/**
	 * Establece el jasper print.
	 *
	 * @param jasperPrint el new jasper print
	 */
	public void setJasperPrint(JasperPrint jasperPrint) {
		this.jasperPrint = jasperPrint;
	}

	/**
	 * Obtiene virtualizer.
	 *
	 * @return virtualizer
	 */
	public JRAbstractLRUVirtualizer getVirtualizer() {
		return virtualizer;
	}

	/**
	 * Establece el virtualizer.
	 *
	 * @param virtualizer el new virtualizer
	 */
	public void setVirtualizer(JRAbstractLRUVirtualizer virtualizer) {
		this.virtualizer = virtualizer;
	}

}
