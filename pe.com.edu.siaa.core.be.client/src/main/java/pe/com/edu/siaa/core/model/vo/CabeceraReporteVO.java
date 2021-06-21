package pe.com.edu.siaa.core.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pe.com.edu.siaa.core.model.dto.BasePaginator;


/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class CabeceraReporteVO.
 *
 * @author ndavilal
 * @version 1.0 , 06/04/2015
 * @since SIAA-CORE 2.1
 */
public class CabeceraReporteVO extends BasePaginator implements Serializable {
 
	/** La Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** El usuario. */
	private String usuario;
	
	/** El usuario creacion. */
	private Date fechaGeneracion;
	
	/** El titulo. */
	private String titulo;

	/** El piePagina. */
	private String piePagina;
	
	/** La lista cabecera vo. */
	private List<CabeceraReporteVO> listaCabeceraVO = new ArrayList<CabeceraReporteVO>();
	
	private String rangoFechaTitle;
	
    /**
	 * Instancia un nuevo cabecera reporte.
	 */
	public CabeceraReporteVO() {
		listaCabeceraVO.add(this);
	}



	/**
	 * Instancia un nuevo cabecera reporte vo.
	 *
	 * @param usuario el usuario
	 * @param fechaGeneracion el fecha generacion
	 * @param titulo el titulo
	 */
	public CabeceraReporteVO(String usuario, Date fechaGeneracion, String titulo) {
		super();
		this.usuario = usuario;
		this.fechaGeneracion = fechaGeneracion;
		this.titulo = titulo;
	}

	//get y set
	/**
	 * Obtiene usuario.
	 *
	 * @return usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Establece el usuario.
	 *
	 * @param usuario el new usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Obtiene fecha generacion.
	 *
	 * @return fecha generacion
	 */
	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}

	/**
	 * Establece el fecha generacion.
	 *
	 * @param fechaGeneracion el new fecha generacion
	 */
	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	/**
	 * Obtiene titulo.
	 *
	 * @return titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Establece el titulo.
	 *
	 * @param titulo el new titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Obtiene pie pagina.
	 *
	 * @return pie pagina
	 */
	public String getPiePagina() {
		return piePagina;
	}

	/**
	 * Establece el pie pagina.
	 *
	 * @param piePagina el new pie pagina
	 */
	public void setPiePagina(String piePagina) {
		this.piePagina = piePagina;
	}

	/**
	 * Obtiene lista cabecera vo.
	 *
	 * @return lista cabecera vo
	 */
	public List<CabeceraReporteVO> getListaCabeceraVO() {
		return listaCabeceraVO;
	}

	/**
	 * Establece el lista cabecera vo.
	 *
	 * @param listaCabeceraVo el new lista cabecera vo
	 */
	public void setListaCabeceraVO(List<CabeceraReporteVO> listaCabeceraVo) {
		this.listaCabeceraVO = listaCabeceraVo;
	}

	public String getRangoFechaTitle() {
		return rangoFechaTitle;
	}

	public void setRangoFechaTitle(String rangoFechaTitle) {
		this.rangoFechaTitle = rangoFechaTitle;
	}
}