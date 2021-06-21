package pe.com.edu.siaa.core.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class MensajeVO.
 *
 * @author ndavilal
 * @version 1.0 , 06/04/2015
 * @since SIAA-CORE 2.1
 */
public class MensajeVO implements Serializable {
 
	/** La Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** El contenido. */
	private String contenido;
	
	/** El usuario creacion. */
	private String usuarioCreacion;
	
	/** El usuario modifica. */
	private String usuarioModifica;
	
	/** El fecha creacion. */
	private Date fechaCreacion;
	
	/** El fecha modificacion. */
	private Date fechaModificacion;
	
	/** El origen mensaje. */
	private String origenMensaje;
	
	
	/** El importancia. */
	private String importancia;

	/** El asunto. */
	private String asunto;
	
	 /** La uuid. */
 	private String UUID = null;
	
	/** La archivo adjunto vo list. */
	private List<ArchivoAdjuntoVO> archivoAdjuntoVOList = new ArrayList<ArchivoAdjuntoVO>(); 
	
    /**
	 * Instancia un nuevo mensaje.
	 */
	public MensajeVO() {
	}
	
	
	/**
	 * Instancia un nuevo mensaje.
	 *
	 * @param contenido el contenido
	 * @param usuarioCreacion el usuario creacion
	 * @param usuarioModifica el usuario modifica
	 * @param fechaCreacion el fecha creacion
	 * @param fechaModificacion el fecha modificacion
	 * @param origenMensaje el origen mensaje
	 * @param importancia el importancia
	 * @param asunto el asunto
	 */
	public MensajeVO(String contenido, String usuarioCreacion, String usuarioModifica, Date fechaCreacion, Date fechaModificacion, String origenMensaje, String importancia, String asunto ) {
		super();
		this.contenido = contenido;
		this.usuarioCreacion = usuarioCreacion;
		this.usuarioModifica = usuarioModifica;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.origenMensaje = origenMensaje;
		this.importancia = importancia;
		this.asunto = asunto;
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
	 * Obtiene usuario creacion.
	 *
	 * @return usuario creacion
	 */
	 public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}
	/**
	 * Establece el usuario creacion.
	 *
	 * @param usuarioCreacion el new usuario creacion
	 */
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	/**
	 * Obtiene usuario modifica.
	 *
	 * @return usuario modifica
	 */
	 public String getUsuarioModifica() {
		return this.usuarioModifica;
	}
	/**
	 * Establece el usuario modifica.
	 *
	 * @param usuarioModifica el new usuario modifica
	 */
	public void setUsuarioModifica(String usuarioModifica) {
		this.usuarioModifica = usuarioModifica;
	}
	/**
	 * Obtiene fecha creacion.
	 *
	 * @return fecha creacion
	 */
	 public Date getFechaCreacion() {
		return this.fechaCreacion;
	}
	/**
	 * Establece el fecha creacion.
	 *
	 * @param fechaCreacion el new fecha creacion
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/**
	 * Obtiene fecha modificacion.
	 *
	 * @return fecha modificacion
	 */
	 public Date getFechaModificacion() {
		return this.fechaModificacion;
	}
	/**
	 * Establece el fecha modificacion.
	 *
	 * @param fechaModificacion el new fecha modificacion
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	/**
	 * Obtiene origen mensaje.
	 *
	 * @return origen mensaje
	 */
	 public String getOrigenMensaje() {
		return this.origenMensaje;
	}
	/**
	 * Establece el origen mensaje.
	 *
	 * @param origenMensaje el new origen mensaje
	 */
	public void setOrigenMensaje(String origenMensaje) {
		this.origenMensaje = origenMensaje;
	}
	
	/**
	 * Obtiene importancia.
	 *
	 * @return importancia
	 */
	 public String getImportancia() {
		return this.importancia;
	}
	/**
	 * Establece el importancia.
	 *
	 * @param importancia el new importancia
	 */
	public void setImportancia(String importancia) {
		this.importancia = importancia;
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
	 * Obtiene archivo adjunto vo list.
	 *
	 * @return archivo adjunto vo list
	 */
	public List<ArchivoAdjuntoVO> getArchivoAdjuntoVOList() {
		return archivoAdjuntoVOList;
	}


	/**
	 * Establece el archivo adjunto vo list.
	 *
	 * @param archivoAdjuntoVOList el new archivo adjunto vo list
	 */
	public void setArchivoAdjuntoVOList(List<ArchivoAdjuntoVO> archivoAdjuntoVOList) {
		this.archivoAdjuntoVOList = archivoAdjuntoVOList;
	}

}