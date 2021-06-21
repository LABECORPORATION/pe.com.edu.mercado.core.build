package pe.com.edu.siaa.core.model.dto;

import java.io.Serializable;

/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class ReporteGeneradoDTO.
 *
 * @author ndavilal
 * @version 1.0 , 01/04/2015
 * @since SIAA-CORE 2.1
 */
public class ReporteGeneradoDTO implements Serializable {
 
    /** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** La disabled. */
	private String disabled;
     
    /** La sbuffer. */
    private String sbuffer;
     
    /** La page index. */
    private Integer pageIndex;
    
    /** La last page index. */
    private Integer lastPageIndex;
    
    /** La nombre repore. */
    private String nombreRepore;

    
	/**
	 * Instancia un nuevo reporte generado dto.
	 */
	public ReporteGeneradoDTO() {
		super();
	}

	/**
	 * Instancia un nuevo reporte generado dto.
	 *
	 * @param disabled el disabled
	 * @param sbuffer el sbuffer
	 * @param pageIndex el page index
	 * @param lastPageIndex el last page index
	 * @param nombreRepore el nombre repore
	 */
	public ReporteGeneradoDTO(String disabled, String sbuffer,
			Integer pageIndex, Integer lastPageIndex, String nombreRepore) {
		super();
		this.disabled = disabled;
		this.sbuffer = sbuffer;
		this.pageIndex = pageIndex;
		this.lastPageIndex = lastPageIndex;
		this.nombreRepore = nombreRepore;
	}
	
	//get y set

	/**
	 * Obtiene disabled.
	 *
	 * @return disabled
	 */
	public String getDisabled() {
		return disabled;
	}

	/**
	 * Establece el disabled.
	 *
	 * @param disabled el new disabled
	 */
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	/**
	 * Obtiene sbuffer.
	 *
	 * @return sbuffer
	 */
	public String getSbuffer() {
		return sbuffer;
	}

	/**
	 * Establece el sbuffer.
	 *
	 * @param sbuffer el new sbuffer
	 */
	public void setSbuffer(String sbuffer) {
		this.sbuffer = sbuffer;
	}

	/**
	 * Obtiene page index.
	 *
	 * @return page index
	 */
	public Integer getPageIndex() {
		return pageIndex;
	}

	/**
	 * Establece el page index.
	 *
	 * @param pageIndex el new page index
	 */
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * Obtiene last page index.
	 *
	 * @return last page index
	 */
	public Integer getLastPageIndex() {
		return lastPageIndex;
	}

	/**
	 * Establece el last page index.
	 *
	 * @param lastPageIndex el new last page index
	 */
	public void setLastPageIndex(Integer lastPageIndex) {
		this.lastPageIndex = lastPageIndex;
	}

	/**
	 * Obtiene nombre repore.
	 *
	 * @return nombre repore
	 */
	public String getNombreRepore() {
		return nombreRepore;
	}

	/**
	 * Establece el nombre repore.
	 *
	 * @param nombreRepore el new nombre repore
	 */
	public void setNombreRepore(String nombreRepore) {
		this.nombreRepore = nombreRepore;
	}

    
  
}  