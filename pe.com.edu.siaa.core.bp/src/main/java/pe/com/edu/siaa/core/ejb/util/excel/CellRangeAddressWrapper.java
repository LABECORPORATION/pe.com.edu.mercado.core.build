package pe.com.edu.siaa.core.ejb.util.excel;

import org.apache.poi.ss.util.CellRangeAddress;

import pe.com.edu.siaa.core.ejb.util.log.Logger;

/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Class CellRangeAddressWrapper.
 *
 * @author ndavilal
 * @version 1.0 , 29/04/2016
 * @since SIAA-CORE 2.1
 */
public class CellRangeAddressWrapper implements Comparable<CellRangeAddressWrapper> {

	/** El log. */
	private static Logger log = Logger.getLogger(DataExportExcelPersonalizadoUtil.class);
	
	/** La range. */
	public CellRangeAddress range;

	/**
	 * Instancia un nuevo cell range address wrapper.
	 *
	 * @param theRange el the range
	 */
	public CellRangeAddressWrapper(CellRangeAddress theRange) {
		this.range = theRange;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(CellRangeAddressWrapper o) {
		if (range.getFirstColumn() < o.range.getFirstColumn() && range.getFirstRow() < o.range.getFirstRow()) {
			return -1;
		} else if (range.getFirstColumn() == o.range.getFirstColumn() && range.getFirstRow() == o.range.getFirstRow()) {
			return 0;
		} else {
			return 1;
		}
	}
}