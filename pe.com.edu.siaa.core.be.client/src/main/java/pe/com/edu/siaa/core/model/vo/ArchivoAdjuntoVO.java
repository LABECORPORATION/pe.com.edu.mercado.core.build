package pe.com.edu.siaa.core.model.vo;

import java.io.Serializable;

/**
 * La Class ArchivoAdjuntoVO.
 * <ul>
 * <li>Copyright 2017 ndavilal-
 * mapfre. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Mon Aug 11 09:00:30 COT 2017
 * @since SIAA-CORE 2.1
 */
public class ArchivoAdjuntoVO implements Serializable {
 
	/** La Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** El tamano adjunto. */
	private Long tamanoAdjunto;
	
	/** El nombre adjunto. */
	private String nombreAdjunto;
	
	/** El extension. */
	private String extension;
	
	/** El codigo archivo. */
	private String codigoArchivo;
	
	/**
	 * Instancia un nuevo archivo adjunto.
	 */
	public ArchivoAdjuntoVO() {
	}
	
	
	/**
	 * Instancia un nuevo archivo adjunto.
	 *
	 * @param tamanoAdjunto el tamano adjunto
	 * @param nombreAdjunto el nombre adjunto
	 * @param extension el extension
	 * @param codigoArchivo el codigo archivo
	 */
	public ArchivoAdjuntoVO(Long tamanoAdjunto, String nombreAdjunto, String extension, String codigoArchivo ) {
		super();
		this.tamanoAdjunto = tamanoAdjunto;
		this.nombreAdjunto = nombreAdjunto;
		this.extension = extension;
		this.codigoArchivo = codigoArchivo;
	}
	
	//get y set
	
	/**
	 * Obtiene tamano adjunto.
	 *
	 * @return tamano adjunto
	 */
	 public Long getTamanoAdjunto() {
		return this.tamanoAdjunto;
	}
	/**
	 * Establece el tamano adjunto.
	 *
	 * @param tamanoAdjunto el new tamano adjunto
	 */
	public void setTamanoAdjunto(Long tamanoAdjunto) {
		this.tamanoAdjunto = tamanoAdjunto;
	}
	/**
	 * Obtiene nombre adjunto.
	 *
	 * @return nombre adjunto
	 */
	 public String getNombreAdjunto() {
		return this.nombreAdjunto;
	}
	/**
	 * Establece el nombre adjunto.
	 *
	 * @param nombreAdjunto el new nombre adjunto
	 */
	public void setNombreAdjunto(String nombreAdjunto) {
		this.nombreAdjunto = nombreAdjunto;
	}
	/**
	 * Obtiene extension.
	 *
	 * @return extension
	 */
	 public String getExtension() {
		return this.extension;
	}
	/**
	 * Establece el extension.
	 *
	 * @param extension el new extension
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}
	/**
	 * Obtiene codigo archivo.
	 *
	 * @return codigo archivo
	 */
	 public String getCodigoArchivo() {
		return this.codigoArchivo;
	}
	/**
	 * Establece el codigo archivo.
	 *
	 * @param codigoArchivo el new codigo archivo
	 */
	public void setCodigoArchivo(String codigoArchivo) {
		this.codigoArchivo = codigoArchivo;
	}
	
}