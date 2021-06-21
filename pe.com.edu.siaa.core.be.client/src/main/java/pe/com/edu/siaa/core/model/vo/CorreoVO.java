package pe.com.edu.siaa.core.model.vo;

import java.io.File;
import java.io.Serializable;
import java.util.List;


/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class CorreoVO.
 *
 * @author ndavilal
 * @version 1.0 , 06/04/2015
 * @since SIAA-CORE 2.1
 */
public class CorreoVO implements Serializable {

	/** El asunto. */
	private String asunto;
	
	/** El contenido. */
	private String contenido;
	
    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;
     
   
    /** La lista destinatarios. */
    private List<DetalleCorreoVO> detalleCorreo;
    
    /** La uuid. */
    private String UUID = null;
    
    /** La archivos adjuntos. */
    private File[] archivosAdjuntos;
    
    private String correoCorporativo;
    private String nombreCompleto;
    
     
   

    /**
     * Instantiates a new correo vo.
     *
     * @param asunto the asunto
     * @param contenido the contenido
     * @param detalleCorreo the detalle correo
     */
    public CorreoVO(String asunto, String contenido,
			List<DetalleCorreoVO> detalleCorreo) {
		super();
		this.asunto = asunto;
		this.contenido = contenido;
		this.detalleCorreo = detalleCorreo;
	}

	/**
     * Instancia un nuevo mensaje vo.
     */
    public CorreoVO() {

    }

    //get y set
    
    /**
     * Obtiene uuid.
     *
     * @return uuid
     */
    public String getUUID() {
		return UUID;
	}
    
    /**
     * Modificar uuid.
     *
     * @param UUID el uuid
     */
    public void modificarUUID(String UUID ) {
		this.UUID = UUID;
	}
    
    /**
	 * Obtiene contenido.
	 *
	 * @return contenido
	 */
	 public String getContenido() {
		return this.contenido;
	}
	
	 

	/**
	 * Establece el contenido.
	 *
	 * @param contenido el new contenido
	 */
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
	/**
	 * Obtiene asunto.
	 *
	 * @return asunto
	 */
	 public String getAsunto() {
		return this.asunto;
	}
	/**
	 * Establece el asunto.
	 *
	 * @param asunto el new asunto
	 */
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
    
    /**
     * Obtiene detalle correo.
     *
     * @return detalle correo
     */
    public List<DetalleCorreoVO> getDetalleCorreo() {
		return detalleCorreo;
	}


	/**
	 * Establece el detalle correo.
	 *
	 * @param detalleCorreo el new detalle correo
	 */
	public void setDetalleCorreo(List<DetalleCorreoVO> detalleCorreo) {
		this.detalleCorreo = detalleCorreo;
	}

	/**
	 * Obtiene archivos adjuntos.
	 *
	 * @return archivos adjuntos
	 */
	public File[] getArchivosAdjuntos() {
		return archivosAdjuntos;
	}

	/**
	 * Establece el archivos adjuntos.
	 *
	 * @param archivosAdjuntos el new archivos adjuntos
	 */
	public void setArchivosAdjuntos(File[] archivosAdjuntos) {
		this.archivosAdjuntos = archivosAdjuntos;
	}

	public String getCorreoCorporativo() {
		return correoCorporativo;
	}

	public void setCorreoCorporativo(String correoCorporativo) {
		this.correoCorporativo = correoCorporativo;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}


	
	
	

}
