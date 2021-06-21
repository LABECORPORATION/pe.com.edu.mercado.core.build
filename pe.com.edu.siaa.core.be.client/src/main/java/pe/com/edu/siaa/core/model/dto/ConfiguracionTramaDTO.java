package pe.com.edu.siaa.core.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * La Class ConfiguracionTramaDTO.
 * <ul>
 * <li>Copyright 2015 MAPFRE -
 * MAPFRE. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Wed Aug 26 16:11:24 COT 2015
 * @since SIAA-CORE 2.1
 */
public class ConfiguracionTramaDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id configurador trama. */
    private Long idConfiguradorTrama;
   
    private Long filaData;
   
    /** El tiene separador. */
    private Character tieneSeparador;
   
    /** El numero orden. */
    private Long numeroOrden;
   
    /** El numero hoja. */
    private Long numeroHoja;
   
    /** El separador. */
    private String separador;
   
    /** El obligatorio. */
    private Character obligatorio;
   
    /** El estado. */
    private String estado;
   
    /** El tipo proceso. */
    private String tipoProceso;
   
    /** El nombre tabla. */
    private String nombreTabla;
    
    /** La descripcion tabla. */
    private String descripcionTabla;
   
    /** El delimitador data. */
    private String delimitadorData;
   
    /** La es coordenada. */
    private Character esCoordenada;
   
    /** El configuracion trama configuracion trama detalle list. */
    private List<ConfiguracionTramaDetalleDTO> configuracionTramaConfiguracionTramaDetalleList = new ArrayList<ConfiguracionTramaDetalleDTO>();
   
    
    /** El configuracion trama asociada configuracion trama detalle list. */
    private List<ConfiguracionTramaDetalleDTO> configuracionTramaAsociadaConfiguracionTramaDetalleList = new ArrayList<ConfiguracionTramaDetalleDTO>();
   
    /** La configuracion trama configuracion trama detalle list eliminado. */
    private List<ConfiguracionTramaDetalleDTO> configuracionTramaConfiguracionTramaDetalleListEliminado = new ArrayList<ConfiguracionTramaDetalleDTO>();
   
    /** La show accion error. */
    private boolean showAccionError = true;
    
	/** La show accion eliminar. */
	private boolean showAccionEliminar = false;
	
	/** La show accion activar. */
	private boolean showAccionActivar = false;
    
    /**
     * Instancia un nuevo configuracion tramaDTO.
     */
    public ConfiguracionTramaDTO() {
    }
   
   
    /**
     * Instancia un nuevo configuracion tramaDTO.
     *
     * @param idConfiguradorTrama el id configurador trama
     * @param juegoTrama el juego trama
     * @param tramaNomenclaturaArchivo el trama nomenclatura archivo
     * @param filaData el fila data
     * @param tieneSeparador el tiene separador
     * @param numeroOrden el numero orden
     * @param numeroHoja el numero hoja
     * @param separador el separador
     * @param obligatorio el obligatorio
     * @param estado el estado
     * @param tipoProceso el tipo proceso
     * @param nombreTabla el nombre tabla
     * @param delimitadorData el delimitador data
     */
    public ConfiguracionTramaDTO(Long idConfiguradorTrama,Long filaData, Character tieneSeparador, Long numeroOrden, Long numeroHoja, String separador, Character obligatorio, String estado, String tipoProceso, String nombreTabla, String delimitadorData ) {
        super();
        this.idConfiguradorTrama = idConfiguradorTrama;
        this.filaData = filaData;
        this.tieneSeparador = tieneSeparador;
        this.numeroOrden = numeroOrden;
        this.numeroHoja = numeroHoja;
        this.separador = separador;
        this.obligatorio = obligatorio;
        this.estado = estado;
        this.tipoProceso = tipoProceso;
        this.nombreTabla = nombreTabla;
        this.delimitadorData = delimitadorData;
    }
   
    //get y set
    /**
     * Obtiene id configurador trama.
     *
     * @return id configurador trama
     */
     public Long getIdConfiguradorTrama() {
        return this.idConfiguradorTrama;
    }
    /**
     * Establece el id configurador trama.
     *
     * @param idConfiguradorTrama el new id configurador trama
     */
    public void setIdConfiguradorTrama(Long idConfiguradorTrama) {
        this.idConfiguradorTrama = idConfiguradorTrama;
    }
   
    /**
     * Obtiene fila data.
     *
     * @return fila data
     */
     public Long getFilaData() {
        return this.filaData;
    }
    /**
     * Establece el fila data.
     *
     * @param filaData el new fila data
     */
    public void setFilaData(Long filaData) {
        this.filaData = filaData;
    }
    /**
     * Obtiene tiene separador.
     *
     * @return tiene separador
     */
     public Character getTieneSeparador() {
        return this.tieneSeparador;
    }
    /**
     * Establece el tiene separador.
     *
     * @param tieneSeparador el new tiene separador
     */
    public void setTieneSeparador(Character tieneSeparador) {
        this.tieneSeparador = tieneSeparador;
    }
    /**
     * Obtiene numero orden.
     *
     * @return numero orden
     */
     public Long getNumeroOrden() {
        return this.numeroOrden;
    }
    /**
     * Establece el numero orden.
     *
     * @param numeroOrden el new numero orden
     */
    public void setNumeroOrden(Long numeroOrden) {
        this.numeroOrden = numeroOrden;
    }
    /**
     * Obtiene numero hoja.
     *
     * @return numero hoja
     */
     public Long getNumeroHoja() {
        return this.numeroHoja;
    }
    /**
     * Establece el numero hoja.
     *
     * @param numeroHoja el new numero hoja
     */
    public void setNumeroHoja(Long numeroHoja) {
        this.numeroHoja = numeroHoja;
    }
    /**
     * Obtiene separador.
     *
     * @return separador
     */
     public String getSeparador() {
        return this.separador;
    }
    /**
     * Establece el separador.
     *
     * @param separador el new separador
     */
    public void setSeparador(String separador) {
        this.separador = separador;
    }
    /**
     * Obtiene obligatorio.
     *
     * @return obligatorio
     */
     public Character getObligatorio() {
        return this.obligatorio;
    }
    /**
     * Establece el obligatorio.
     *
     * @param obligatorio el new obligatorio
     */
    public void setObligatorio(Character obligatorio) {
        this.obligatorio = obligatorio;
    }
    /**
     * Obtiene estado.
     *
     * @return estado
     */
     public String getEstado() {
        return this.estado;
    }
    /**
     * Establece el estado.
     *
     * @param estado el new estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    /**
     * Obtiene tipo proceso.
     *
     * @return tipo proceso
     */
     public String getTipoProceso() {
        return this.tipoProceso;
    }
    /**
     * Establece el tipo proceso.
     *
     * @param tipoProceso el new tipo proceso
     */
    public void setTipoProceso(String tipoProceso) {
        this.tipoProceso = tipoProceso;
    }
    /**
     * Obtiene nombre tabla.
     *
     * @return nombre tabla
     */
     public String getNombreTabla() {
        return this.nombreTabla;
    }
    /**
     * Establece el nombre tabla.
     *
     * @param nombreTabla el new nombre tabla
     */
    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }
    /**
     * Obtiene delimitador data.
     *
     * @return delimitador data
     */
     public String getDelimitadorData() {
        return this.delimitadorData;
    }
    /**
     * Establece el delimitador data.
     *
     * @param delimitadorData el new delimitador data
     */
    public void setDelimitadorData(String delimitadorData) {
        this.delimitadorData = delimitadorData;
    }
    
    /**
     * Obtiene es coordenada.
     *
     * @return es coordenada
     */
    public Character getEsCoordenada() {
		return esCoordenada;
	}


	/**
	 * Establece el es coordenada.
	 *
	 * @param esCoordenada el new es coordenada
	 */
	public void setEsCoordenada(Character esCoordenada) {
		this.esCoordenada = esCoordenada;
	}
	
	
    /**
     * Obtiene descripcion tabla.
     *
     * @return descripcion tabla
     */
    public String getDescripcionTabla() {
		return descripcionTabla;
	}


	/**
	 * Establece el descripcion tabla.
	 *
	 * @param descripcionTabla el new descripcion tabla
	 */
	public void setDescripcionTabla(String descripcionTabla) {
		this.descripcionTabla = descripcionTabla;
	}

	/**
     * Establece el configuracion trama configuracion trama detalle list.
     *
     * @return configuracion trama configuracion trama detalle list
     */
    public List<ConfiguracionTramaDetalleDTO> getConfiguracionTramaConfiguracionTramaDetalleList(){
        return this.configuracionTramaConfiguracionTramaDetalleList;
    }
    
    /**
     * Establece el configuracion trama list.
     *
     * @param configuracionTramaConfiguracionTramaDetalleList el new configuracion trama configuracion trama detalle list
     */
    public void setConfiguracionTramaConfiguracionTramaDetalleList(List<ConfiguracionTramaDetalleDTO> configuracionTramaConfiguracionTramaDetalleList) {
        this.configuracionTramaConfiguracionTramaDetalleList = configuracionTramaConfiguracionTramaDetalleList;
    }   
    
    /**
     * Establece el configuracion trama asociada configuracion trama detalle list.
     *
     * @return configuracion trama asociada configuracion trama detalle list
     */
    public List<ConfiguracionTramaDetalleDTO> getConfiguracionTramaAsociadaConfiguracionTramaDetalleList(){
        return this.configuracionTramaAsociadaConfiguracionTramaDetalleList;
    }
    
    /**
     * Establece el configuracion trama asociada list.
     *
     * @param configuracionTramaAsociadaConfiguracionTramaDetalleList el new configuracion trama asociada configuracion trama detalle list
     */
    public void setConfiguracionTramaAsociadaConfiguracionTramaDetalleList(List<ConfiguracionTramaDetalleDTO> configuracionTramaAsociadaConfiguracionTramaDetalleList) {
        this.configuracionTramaAsociadaConfiguracionTramaDetalleList = configuracionTramaAsociadaConfiguracionTramaDetalleList;
    }   
    
    
    /**
     * Obtiene configuracion trama configuracion trama detalle list eliminado.
     *
     * @return configuracion trama configuracion trama detalle list eliminado
     */
    public List<ConfiguracionTramaDetalleDTO> getConfiguracionTramaConfiguracionTramaDetalleListEliminado() {
		return configuracionTramaConfiguracionTramaDetalleListEliminado;
	}

	/**
	 * Establece el configuracion trama configuracion trama detalle list eliminado.
	 *
	 * @param configuracionTramaConfiguracionTramaDetalleListEliminado el new configuracion trama configuracion trama detalle list eliminado
	 */
	public void setConfiguracionTramaConfiguracionTramaDetalleListEliminado(
			List<ConfiguracionTramaDetalleDTO> configuracionTramaConfiguracionTramaDetalleListEliminado) {
		this.configuracionTramaConfiguracionTramaDetalleListEliminado = configuracionTramaConfiguracionTramaDetalleListEliminado;
	}
    
    /**
     * Comprueba si es show accion error.
     *
     * @return true, si es show accion error
     */
    public boolean isShowAccionError() {
		return showAccionError;
	}


	/**
	 * Establece el show accion error.
	 *
	 * @param showAccionError el new show accion error
	 */
	public void setShowAccionError(boolean showAccionError) {
		this.showAccionError = showAccionError;
	}


	/**
	 * Comprueba si es show accion eliminar.
	 *
	 * @return true, si es show accion eliminar
	 */
	public boolean isShowAccionEliminar() {
		return showAccionEliminar;
	}


	/**
	 * Establece el show accion eliminar.
	 *
	 * @param showAccionEliminar el new show accion eliminar
	 */
	public void setShowAccionEliminar(boolean showAccionEliminar) {
		this.showAccionEliminar = showAccionEliminar;
	}

	

	/**
	 * Comprueba si es show accion activar.
	 *
	 * @return true, si es show accion activar
	 */
	public boolean isShowAccionActivar() {
		return showAccionActivar;
	}


	/**
	 * Establece el show accion activar.
	 *
	 * @param showAccionActivar el new show accion activar
	 */
	public void setShowAccionActivar(boolean showAccionActivar) {
		this.showAccionActivar = showAccionActivar;
	}


	/* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idConfiguradorTrama == null) ? 0 : idConfiguradorTrama.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ConfiguracionTramaDTO other = (ConfiguracionTramaDTO) obj;
        if (idConfiguradorTrama == null) {
            if (other.idConfiguradorTrama != null) {
                return false;
            }
        } else if (!idConfiguradorTrama.equals(other.idConfiguradorTrama)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ConfiguracionTramaDTO [idConfiguradorTrama=" + idConfiguradorTrama + "]";
    }
   
}