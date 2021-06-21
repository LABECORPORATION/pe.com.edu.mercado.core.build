package pe.com.edu.siaa.core.model.vo;

import java.io.Serializable;

/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class ExcelHederTitleVO.
 *
 * @author ndavilal
 * @version 1.0 , 05/04/20156
 * @since SIAA-CORE 2.1
 */
public class ExcelHederTitleVO implements Serializable {

    /** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** El nameHeader. */
    private String nameHeader;
    
    /** El nameAtribute. */
    private short aling;
    
    private short verticalAlignment;
    
    private Integer posicionCelda = -1;
    
    private Integer posicionRow = -1;
    
    private Integer cantidadAgrupar =  0;
    
    private Integer cantidadAgruparHorizontal =  0;
    
    private short fontHeightInPoints = 9;
    
    private int columnIndex = -1;
    private int  width = -1;
    
    private boolean wrapText = false;
    
    private int rotacion = 0;
    
    private boolean esPiePagina = false;
    
    private short bg;
    
    private short colorFontText;
    
    public int  widthde;
    
    
	public ExcelHederTitleVO() {
		super();
	}

	public ExcelHederTitleVO(String nameHeader, short aling) {
		super();
		this.nameHeader = nameHeader;
		this.aling = aling;
	}
	
	//get y set

	public ExcelHederTitleVO(String nameHeader, short aling,
			Integer posicionCelda) {
		super();
		this.nameHeader = nameHeader;
		this.aling = aling;
		this.posicionCelda = posicionCelda;
	}

	public ExcelHederTitleVO(String nameHeader, short aling,
			Integer posicionCelda, Integer cantidadAgrupar) {
		super();
		this.nameHeader = nameHeader;
		this.aling = aling;
		this.posicionCelda = posicionCelda;
		this.cantidadAgrupar = cantidadAgrupar;
	}

	
	public ExcelHederTitleVO(String nameHeader, short aling,short verticalAlignment,
			Integer posicionCelda, Integer posicionRow, Integer cantidadAgrupar, Integer cantidadAgruparHorizontal, Integer columnIndex, Integer width,boolean wrapText,int rotacion,short fontHeightInPoints ) {
		super();
		this.nameHeader = nameHeader;
		this.aling = aling;
		this.verticalAlignment = verticalAlignment;
		this.posicionCelda = posicionCelda;
		this.posicionRow = posicionRow;
		this.cantidadAgrupar = cantidadAgrupar;
		this.cantidadAgruparHorizontal = cantidadAgruparHorizontal;
		this.columnIndex = columnIndex;
		this.width = width;
		this.wrapText = wrapText;
		this.rotacion = rotacion;
		this.fontHeightInPoints = fontHeightInPoints ;
	}
	
	public ExcelHederTitleVO(String nameHeader, short aling,short verticalAlignment,
			Integer posicionCelda, Integer posicionRow, Integer cantidadAgrupar, Integer cantidadAgruparHorizontal, Integer columnIndex, Integer width,boolean wrapText ) {
		super();
		this.nameHeader = nameHeader;
		this.aling = aling;
		this.verticalAlignment = verticalAlignment;
		this.posicionCelda = posicionCelda;
		this.posicionRow = posicionRow;
		this.cantidadAgrupar = cantidadAgrupar;
		this.cantidadAgruparHorizontal = cantidadAgruparHorizontal;
		this.columnIndex = columnIndex;
		this.width = width;
		this.wrapText = wrapText;
	}
	
	public ExcelHederTitleVO(String nameHeader, short aling,short verticalAlignment,
			Integer posicionCelda, Integer posicionRow, Integer cantidadAgrupar, Integer cantidadAgruparHorizontal, Integer columnIndex, Integer width,boolean wrapText, boolean esPiePagina ) {
		super();
		this.nameHeader = nameHeader;
		this.aling = aling;
		this.verticalAlignment = verticalAlignment;
		this.posicionCelda = posicionCelda;
		this.posicionRow = posicionRow;
		this.cantidadAgrupar = cantidadAgrupar;
		this.cantidadAgruparHorizontal = cantidadAgruparHorizontal;
		this.columnIndex = columnIndex;
		this.width = width;
		this.wrapText = wrapText;
		this.esPiePagina = esPiePagina;
	}
	
	public ExcelHederTitleVO(String nameHeader, short aling,short verticalAlignment,
			Integer posicionCelda, Integer posicionRow, Integer cantidadAgrupar, Integer cantidadAgruparHorizontal,boolean wrapText) {
		super();
		this.nameHeader = nameHeader;
		this.aling = aling;
		this.verticalAlignment = verticalAlignment;
		this.posicionCelda = posicionCelda;
		this.posicionRow = posicionRow;
		this.cantidadAgrupar = cantidadAgrupar;
		this.cantidadAgruparHorizontal = cantidadAgruparHorizontal;
		this.wrapText = wrapText;
	}
	

	public ExcelHederTitleVO(String nameHeader, short aling,short verticalAlignment,
			Integer posicionCelda, Integer posicionRow, Integer cantidadAgrupar, Integer cantidadAgruparHorizontal, short fontHeightInPoints,Integer columnIndex, Integer width) {
		super();
		this.nameHeader = nameHeader;
		this.aling = aling;
		this.verticalAlignment = verticalAlignment;
		this.posicionCelda = posicionCelda;
		this.posicionRow = posicionRow;
		this.cantidadAgrupar = cantidadAgrupar;
		this.cantidadAgruparHorizontal = cantidadAgruparHorizontal;
		this.fontHeightInPoints = fontHeightInPoints;
		this.columnIndex = columnIndex;
		this.width = width;
	}
	
	public ExcelHederTitleVO(String nameHeader, short aling,short verticalAlignment,
			Integer posicionCelda, Integer posicionRow, Integer cantidadAgrupar, Integer cantidadAgruparHorizontal, short fontHeightInPoints,boolean wrapText) {
		super();
		this.nameHeader = nameHeader;
		this.aling = aling;
		this.verticalAlignment = verticalAlignment;
		this.posicionCelda = posicionCelda;
		this.posicionRow = posicionRow;
		this.cantidadAgrupar = cantidadAgrupar;
		this.cantidadAgruparHorizontal = cantidadAgruparHorizontal;
		this.fontHeightInPoints = fontHeightInPoints;
		this.wrapText = wrapText;
	}
	
	//agregado
	
	public ExcelHederTitleVO(String nameHeader, short aling,short verticalAlignment,
			Integer posicionCelda, Integer posicionRow, Integer cantidadAgrupar, Integer cantidadAgruparHorizontal,boolean wrapText,short bg) {
		super();
		this.nameHeader = nameHeader;
		this.aling = aling;
		this.verticalAlignment = verticalAlignment;
		this.posicionCelda = posicionCelda;
		this.posicionRow = posicionRow;
		this.cantidadAgrupar = cantidadAgrupar;
		this.cantidadAgruparHorizontal = cantidadAgruparHorizontal;
		this.wrapText = wrapText;
		this.bg=bg;
	}
	public ExcelHederTitleVO(String nameHeader, short aling,short verticalAlignment,
			Integer posicionCelda, Integer posicionRow, Integer cantidadAgrupar, Integer cantidadAgruparHorizontal,boolean wrapText,short bg,short colorFontText) {
		super();
		this.nameHeader = nameHeader;
		this.aling = aling;
		this.verticalAlignment = verticalAlignment;
		this.posicionCelda = posicionCelda;
		this.posicionRow = posicionRow;
		this.cantidadAgrupar = cantidadAgrupar;
		this.cantidadAgruparHorizontal = cantidadAgruparHorizontal;
		this.wrapText = wrapText;
		this.bg=bg;
		this.colorFontText=colorFontText;
	}
	
	public ExcelHederTitleVO(String nameHeader, short aling,short verticalAlignment,
			Integer posicionCelda, Integer posicionRow, Integer cantidadAgrupar, Integer cantidadAgruparHorizontal,boolean wrapText,short bg,short colorFontText,
			Integer columnIndex, Integer width) {
		super();
		this.nameHeader = nameHeader;
		this.aling = aling;
		this.verticalAlignment = verticalAlignment;
		this.posicionCelda = posicionCelda;
		this.posicionRow = posicionRow;
		this.cantidadAgrupar = cantidadAgrupar;
		this.cantidadAgruparHorizontal = cantidadAgruparHorizontal;
		this.wrapText = wrapText;
		this.bg=bg;
		this.colorFontText=colorFontText;
		this.columnIndex = columnIndex;
		this.width = width;
	
	}

	//agregados:::
	
		public ExcelHederTitleVO(String nameHeader, short aling,short verticalAlignment,
				Integer posicionCelda, Integer posicionRow, Integer cantidadAgrupar, Integer cantidadAgruparHorizontal, Integer columnIndex, Integer width,boolean wrapText,int rotacion,short fontHeightInPoints ,int  widthde) {
			super();
			this.nameHeader = nameHeader;
			this.aling = aling;
			this.verticalAlignment = verticalAlignment;
			this.posicionCelda = posicionCelda;
			this.posicionRow = posicionRow;
			this.cantidadAgrupar = cantidadAgrupar;
			this.cantidadAgruparHorizontal = cantidadAgruparHorizontal;
			this.columnIndex = columnIndex;
			this.width = width;
			this.wrapText = wrapText;
			this.rotacion = rotacion;
			this.fontHeightInPoints = fontHeightInPoints ;
			this.widthde=widthde;
		}
		
		
		
		public ExcelHederTitleVO(String nameHeader, short aling,short verticalAlignment,
				Integer posicionCelda, Integer posicionRow, Integer cantidadAgrupar, Integer cantidadAgruparHorizontal, Integer columnIndex, Integer width,boolean wrapText,int  widthde ) {
			super();
			this.nameHeader = nameHeader;
			this.aling = aling;
			this.verticalAlignment = verticalAlignment;
			this.posicionCelda = posicionCelda;
			this.posicionRow = posicionRow;
			this.cantidadAgrupar = cantidadAgrupar;
			this.cantidadAgruparHorizontal = cantidadAgruparHorizontal;
			this.columnIndex = columnIndex;
			this.width = width;
			this.wrapText = wrapText;
			this.widthde=widthde;
		}
		
		///agregados::
		
	public String getNameHeader() {
		return nameHeader;
	}

	public void setNameHeader(String nameHeader) {
		this.nameHeader = nameHeader;
	}

	public short getAling() {
		return aling;
	}

	public void setAling(short aling) {
		this.aling = aling;
	}

	public Integer getPosicionCelda() {
		return posicionCelda;
	}

	public void setPosicionCelda(Integer posicionCelda) {
		this.posicionCelda = posicionCelda;
	}

	public Integer getPosicionRow() {
		return posicionRow;
	}

	public void setPosicionRow(Integer posicionRow) {
		this.posicionRow = posicionRow;
	}

	public Integer getCantidadAgrupar() {
		return cantidadAgrupar;
	}

	public void setCantidadAgrupar(Integer cantidadAgrupar) {
		this.cantidadAgrupar = cantidadAgrupar;
	}

	public Integer getCantidadAgruparHorizontal() {
		return cantidadAgruparHorizontal;
	}

	public void setCantidadAgruparHorizontal(Integer cantidadAgruparHorizontal) {
		this.cantidadAgruparHorizontal = cantidadAgruparHorizontal;
	}

	public short getVerticalAlignment() {
		return verticalAlignment;
	}

	public void setVerticalAlignment(short verticalAlignment) {
		this.verticalAlignment = verticalAlignment;
	}

	public short getFontHeightInPoints() {
		return fontHeightInPoints;
	}

	public void setFontHeightInPoints(short fontHeightInPoints) {
		this.fontHeightInPoints = fontHeightInPoints;
	}

	public int getColumnIndex() {
		return columnIndex;
	}

	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public boolean isWrapText() {
		return wrapText;
	}

	public void setWrapText(boolean wrapText) {
		this.wrapText = wrapText;
	}

	public int getRotacion() {
		return rotacion;
	}

	public void setRotacion(int rotacion) {
		this.rotacion = rotacion;
	}

	public boolean isEsPiePagina() {
		return esPiePagina;
	}

	public void setEsPiePagina(boolean esPiePagina) {
		this.esPiePagina = esPiePagina;
	}

	public short getBg() {
		return bg;
	}

	public void setBg(short bg) {
		this.bg = bg;
	}

	public short getColorFontText() {
		return colorFontText;
	}

	public void setColorFontText(short colorFontText) {
		this.colorFontText = colorFontText;
	}

	public int getWidthde() {
		return widthde;
	}

	public void setWidthde(int widthde) {
		this.widthde = widthde;
	}

	
}