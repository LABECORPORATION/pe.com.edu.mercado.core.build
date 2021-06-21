package pe.com.edu.siaa.core.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * La Class ConfiguracionTramaDetalleDTO.
 * <ul>
 * <li>Copyright 2015 MAPFRE -
 * MAPFRE. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Wed Aug 26 16:11:24 COT 2015
 * @since SIAA-CORE 2.1
 */
public class ConfiguracionTramaDetalleDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id configurador trama detalle. */
    private Long idConfiguradorTramaDetalle;
   
    /** El configuracion trama. */
    private ConfiguracionTramaDTO configuracionTrama;
   
    /** El nombre campo. */
    private String nombreCampo = "";
   
    /** El descripcion campo. */
    private String descripcionCampo;
    
    /** El obligatorio. */
    private Character obligatorio;
   
    /** El tipo campo. */
    private String tipoCampo;
   
    /** El posicion campo inicial. */
    private Long posicionCampoInicial;
   
    /** El posicion campo final. */
    private Long posicionCampoFinal;
   
    /** El valor defecto campo. */
    private String valorDefectoCampo;
   
    /** El formato campo. */
    private String formatoCampo;
   
    /** El nombe campo tabla. */
    private String nombeCampoTabla;
    
    /** El orden. */
    private Long orden;
   
    /** El fila data. */
    private Long filaData;
   
    /** El flag campo agrupador. */
    private Character flagCampoAgrupador;
   
    /** El flag campo no leido trama. */
    private Character flagCampoNoLeidoTrama;
   
    /** El longitud. */
    private Long longitud;
   
    /** El campo fijo. */
    private Long campoFijo;
   
    /** El configuracion trama asociada. */
    private ConfiguracionTramaDTO configuracionTramaAsociada = new ConfiguracionTramaDTO();
   
    /** El campo asociado match inicio. */
    private ConfiguracionTramaDetalleDTO campoAsociadoMatchInicio;
    
    /** El campo asociado match fin. */
    private ConfiguracionTramaDetalleDTO campoAsociadoMatchFin;
    
    /** El campo asociado. */
    private ConfiguracionTramaDetalleDTO campoAsociado;
    
    /** El es persistente. */
    private Character esPersistente;

    /** El es campo negocio. */
    private Character flagCampoNegocio;
    
    /** El tipo homologacion. */
    private Long tipoHomologacion;
   
    /** El campo dependiente. */
    private ConfiguracionTramaDetalleDTO campoDependiente;
   
    /** El campo asociado configuracion trama detalle list. */
    private List<ConfiguracionTramaDetalleDTO> campoAsociadoConfiguracionTramaDetalleList = new ArrayList<ConfiguracionTramaDetalleDTO>();
   
    /** El es campo asociado. */
    private boolean esCampoAsociado;
    
    /** El es campo persistente. */
    private boolean esCampoPersistente;
    
    /** El style class. */
    private String styleClass;
    
    /** El codigo uuid. */
    private String codigoUUID;
       
    /** La regla negocio. */
    private String reglaNegocio; 
    
    /**
     * Instancia un nuevo configuracion trama detalleDTO.
     */
    public ConfiguracionTramaDetalleDTO() {
    }
   
   
    /**
     * Instancia un nuevo configuracion trama detalleDTO.
     *
     * @param idConfiguradorTramaDetalle el id configurador trama detalle
     * @param configuracionTrama el configuracion trama
     * @param nombreCampo el nombre campo
     * @param descripcionCampo el descripcion campo
     * @param obligatorio el obligatorio
     * @param tipoCampo el tipo campo
     * @param posicionCampoInicial el posicion campo inicial
     * @param posicionCampoFinal el posicion campo final
     * @param valorDefectoCampo el valor defecto campo
     * @param formatoCampo el formato campo
     * @param nombeCampoTabla el nombe campo tabla
     * @param orden el orden
     * @param filaData el fila data
     * @param flagCampoAgrupador el flag campo agrupador
     * @param flagCampoNoLeidoTrama el flag campo no leido trama
     * @param longitud el longitud
     * @param campoFijo el campo fijo
     * @param configuracionTramaAsociada el configuracion trama asociada
     * @param campoAsociado el campo asociado
     * @param tipoHomologacion the tipo homologacion
     * @param campoDependiente the campo dependiente
     */
    public ConfiguracionTramaDetalleDTO(Long idConfiguradorTramaDetalle, ConfiguracionTramaDTO configuracionTrama,String nombreCampo,String descripcionCampo, Character obligatorio, String tipoCampo, Long posicionCampoInicial, Long posicionCampoFinal, String valorDefectoCampo, String formatoCampo, String nombeCampoTabla, Long orden, Long filaData, Character flagCampoAgrupador, Character flagCampoNoLeidoTrama, Long longitud, Long campoFijo, ConfiguracionTramaDTO configuracionTramaAsociada,ConfiguracionTramaDetalleDTO campoAsociado,Long tipoHomologacion, ConfiguracionTramaDetalleDTO campoDependiente) {
        super();
        this.idConfiguradorTramaDetalle = idConfiguradorTramaDetalle;
        this.configuracionTrama = configuracionTrama;
        this.nombreCampo = nombreCampo;
        this.descripcionCampo = descripcionCampo;        
        this.obligatorio = obligatorio;
        this.tipoCampo = tipoCampo;
        this.posicionCampoInicial = posicionCampoInicial;
        this.posicionCampoFinal = posicionCampoFinal;
        this.valorDefectoCampo = valorDefectoCampo;
        this.formatoCampo = formatoCampo;
        this.nombeCampoTabla = nombeCampoTabla;
        this.orden = orden;
        this.filaData = filaData;
        this.flagCampoAgrupador = flagCampoAgrupador;
        this.flagCampoNoLeidoTrama = flagCampoNoLeidoTrama;
        this.longitud = longitud;
        this.campoFijo = campoFijo;
        this.configuracionTramaAsociada = configuracionTramaAsociada;
        this.campoAsociado = campoAsociado;
        this.tipoHomologacion = tipoHomologacion;
        this.campoDependiente = campoDependiente;
    }
   
    /**
     * Obtiene id configurador trama detalle.
     *
     * @return id configurador trama detalle
     */
     public Long getIdConfiguradorTramaDetalle() {
        return this.idConfiguradorTramaDetalle;
    }
    /**
     * Establece el id configurador trama detalle.
     *
     * @param idConfiguradorTramaDetalle el new id configurador trama detalle
     */
    public void setIdConfiguradorTramaDetalle(Long idConfiguradorTramaDetalle) {
        this.idConfiguradorTramaDetalle = idConfiguradorTramaDetalle;
    }
    /**
     * Obtiene configuracion trama.
     *
     * @return configuracion trama
     */
     public ConfiguracionTramaDTO getConfiguracionTrama() {
        return this.configuracionTrama;
    }
    /**
     * Establece el configuracion trama.
     *
     * @param configuracionTrama el new configuracion trama
     */
    public void setConfiguracionTrama(ConfiguracionTramaDTO configuracionTrama) {
        this.configuracionTrama = configuracionTrama;
    }
    /**
     * Obtiene nombre campo.
     *
     * @return nombre campo
     */
     public String getNombreCampo() {
        return this.nombreCampo;
    }
    /**
     * Establece el nombre campo.
     *
     * @param nombreCampo el new nombre campo
     */
    public void setNombreCampo(String nombreCampo) {
        this.nombreCampo = nombreCampo;
    }
    /**
     * Obtiene descripcion campo.
     *
     * @return descripcion campo
     */
     public String getDescripcionCampo() {
        return this.descripcionCampo;
    }
    /**
     * Establece el descripcion campo.
     *
     * @param descripcionCampo el new descripcion campo
     */
    public void setDescripcionCampo(String descripcionCampo) {
        this.descripcionCampo = descripcionCampo;
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
     * Obtiene tipo campo.
     *
     * @return tipo campo
     */
     public String getTipoCampo() {
        return this.tipoCampo;
    }
    /**
     * Establece el tipo campo.
     *
     * @param tipoCampo el new tipo campo
     */
    public void setTipoCampo(String tipoCampo) {
        this.tipoCampo = tipoCampo;
    }
    /**
     * Obtiene posicion campo inicial.
     *
     * @return posicion campo inicial
     */
     public Long getPosicionCampoInicial() {
        return this.posicionCampoInicial;
    }
    /**
     * Establece el posicion campo inicial.
     *
     * @param posicionCampoInicial el new posicion campo inicial
     */
    public void setPosicionCampoInicial(Long posicionCampoInicial) {
        this.posicionCampoInicial = posicionCampoInicial;
    }
    /**
     * Obtiene posicion campo final.
     *
     * @return posicion campo final
     */
     public Long getPosicionCampoFinal() {
        return this.posicionCampoFinal;
    }
    /**
     * Establece el posicion campo final.
     *
     * @param posicionCampoFinal el new posicion campo final
     */
    public void setPosicionCampoFinal(Long posicionCampoFinal) {
        this.posicionCampoFinal = posicionCampoFinal;
    }
    /**
     * Obtiene valor defecto campo.
     *
     * @return valor defecto campo
     */
     public String getValorDefectoCampo() {
        return this.valorDefectoCampo;
    }
    /**
     * Establece el valor defecto campo.
     *
     * @param valorDefectoCampo el new valor defecto campo
     */
    public void setValorDefectoCampo(String valorDefectoCampo) {
        this.valorDefectoCampo = valorDefectoCampo;
    }
    /**
     * Obtiene formato campo.
     *
     * @return formato campo
     */
     public String getFormatoCampo() {
        return this.formatoCampo;
    }
    /**
     * Establece el formato campo.
     *
     * @param formatoCampo el new formato campo
     */
    public void setFormatoCampo(String formatoCampo) {
        this.formatoCampo = formatoCampo;
    }
    /**
     * Obtiene nombe campo tabla.
     *
     * @return nombe campo tabla
     */
     public String getNombeCampoTabla() {
        return this.nombeCampoTabla;
    }
    /**
     * Establece el nombe campo tabla.
     *
     * @param nombeCampoTabla el new nombe campo tabla
     */
    public void setNombeCampoTabla(String nombeCampoTabla) {
        this.nombeCampoTabla = nombeCampoTabla;
    }
    /**
     * Obtiene orden.
     *
     * @return orden
     */
     public Long getOrden() {
        return this.orden;
    }
    /**
     * Establece el orden.
     *
     * @param orden el new orden
     */
    public void setOrden(Long orden) {
        this.orden = orden;
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
     * Obtiene flag campo agrupador.
     *
     * @return flag campo agrupador
     */
     public Character getFlagCampoAgrupador() {
        return this.flagCampoAgrupador;
    }
    /**
     * Establece el flag campo agrupador.
     *
     * @param flagCampoAgrupador el new flag campo agrupador
     */
    public void setFlagCampoAgrupador(Character flagCampoAgrupador) {
        this.flagCampoAgrupador = flagCampoAgrupador;
    }
    /**
     * Obtiene flag campo no leido trama.
     *
     * @return flag campo no leido trama
     */
     public Character getFlagCampoNoLeidoTrama() {
        return this.flagCampoNoLeidoTrama;
    }
    /**
     * Establece el flag campo no leido trama.
     *
     * @param flagCampoNoLeidoTrama el new flag campo no leido trama
     */
    public void setFlagCampoNoLeidoTrama(Character flagCampoNoLeidoTrama) {
        this.flagCampoNoLeidoTrama = flagCampoNoLeidoTrama;
    }
    /**
     * Obtiene longitud.
     *
     * @return longitud
     */
     public Long getLongitud() {
        return this.longitud;
    }
    /**
     * Establece el longitud.
     *
     * @param longitud el new longitud
     */
    public void setLongitud(Long longitud) {
        this.longitud = longitud;
    }
    /**
     * Obtiene campo fijo.
     *
     * @return campo fijo
     */
     public Long getCampoFijo() {
        return this.campoFijo;
    }
    /**
     * Establece el campo fijo.
     *
     * @param campoFijo el new campo fijo
     */
    public void setCampoFijo(Long campoFijo) {
        this.campoFijo = campoFijo;
    }
    /**
     * Obtiene configuracion trama asociada.
     *
     * @return configuracion trama asociada
     */
     public ConfiguracionTramaDTO getConfiguracionTramaAsociada() {
        return this.configuracionTramaAsociada;
    }
    /**
     * Establece el configuracion trama asociada.
     *
     * @param configuracionTramaAsociada el new configuracion trama asociada
     */
    public void setConfiguracionTramaAsociada(ConfiguracionTramaDTO configuracionTramaAsociada) {
        this.configuracionTramaAsociada = configuracionTramaAsociada;
    }
    
    
    /**
     * Gets the campo asociado match inicio.
     *
     * @return the campo asociado match inicio
     */
    public ConfiguracionTramaDetalleDTO getCampoAsociadoMatchInicio() {
		return campoAsociadoMatchInicio;
	}


	/**
	 * Sets the campo asociado match inicio.
	 *
	 * @param campoAsociadoMatchInicio the new campo asociado match inicio
	 */
	public void setCampoAsociadoMatchInicio(
			ConfiguracionTramaDetalleDTO campoAsociadoMatchInicio) {
		this.campoAsociadoMatchInicio = campoAsociadoMatchInicio;
	}


	/**
	 * Gets the campo asociado match fin.
	 *
	 * @return the campo asociado match fin
	 */
	public ConfiguracionTramaDetalleDTO getCampoAsociadoMatchFin() {
		return campoAsociadoMatchFin;
	}


	/**
	 * Sets the campo asociado match fin.
	 *
	 * @param campoAsociadoMatchFin the new campo asociado match fin
	 */
	public void setCampoAsociadoMatchFin(
			ConfiguracionTramaDetalleDTO campoAsociadoMatchFin) {
		this.campoAsociadoMatchFin = campoAsociadoMatchFin;
	}


	/**
     * Obtiene campo asociado.
     *
     * @return campo asociado
     */
     public ConfiguracionTramaDetalleDTO getCampoAsociado() {
        return this.campoAsociado;
    }
    /**
     * Establece el campo asociado.
     *
     * @param campoAsociado el new campo asociado
     */
    public void setCampoAsociado(ConfiguracionTramaDetalleDTO campoAsociado) {
        this.campoAsociado = campoAsociado;
    }
    
    /**
     * Gets the es persistente.
     *
     * @return the es persistente
     */
    public Character getEsPersistente() {
		return esPersistente;
	}


	/**
	 * Sets the es persistente.
	 *
	 * @param esPersistente the new es persistente
	 */
	public void setEsPersistente(Character esPersistente) {
		this.esPersistente = esPersistente;
	}


	/**
	 * Establece el campo asociado configuracion trama detalle list.
	 *
	 * @return the campo asociado configuracion trama detalle list
	 */
    public List<ConfiguracionTramaDetalleDTO> getCampoAsociadoConfiguracionTramaDetalleList(){
        return this.campoAsociadoConfiguracionTramaDetalleList;
    }
    
    /**
     * Establece el campo asociado list.
     *
     * @param campoAsociadoConfiguracionTramaDetalleList the new campo asociado configuracion trama detalle list
     */
    public void setCampoAsociadoConfiguracionTramaDetalleList(List<ConfiguracionTramaDetalleDTO> campoAsociadoConfiguracionTramaDetalleList) {
        this.campoAsociadoConfiguracionTramaDetalleList = campoAsociadoConfiguracionTramaDetalleList;
    }   
    
    
    /**
     * Checks if is es campo asociado.
     *
     * @return true, if is es campo asociado
     */
    public boolean isEsCampoAsociado() {
		return esCampoAsociado;
	}


	/**
	 * Sets the es campo asociado.
	 *
	 * @param esCampoAsociado the new es campo asociado
	 */
	public void setEsCampoAsociado(boolean esCampoAsociado) {
		this.esCampoAsociado = esCampoAsociado;
	}


	/**
	 * Checks if is es campo persistente.
	 *
	 * @return true, if is es campo persistente
	 */
	public boolean isEsCampoPersistente() {
		return esCampoPersistente;
	}


	/**
	 * Sets the es campo persistente.
	 *
	 * @param esCampoPersistente the new es campo persistente
	 */
	public void setEsCampoPersistente(boolean esCampoPersistente) {
		this.esCampoPersistente = esCampoPersistente;
	}


	/**
	 * Gets the style class.
	 *
	 * @return the style class
	 */
	public String getStyleClass() {
		return styleClass;
	}


	/**
	 * Sets the style class.
	 *
	 * @param styleClass the new style class
	 */
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}


	/**
	 * Gets the codigo uuid.
	 *
	 * @return the codigo uuid
	 */
	public String getCodigoUUID() {
		return codigoUUID;
	}


	/**
	 * Sets the codigo uuid.
	 *
	 * @param codigoUUID the new codigo uuid
	 */
	public void setCodigoUUID(String codigoUUID) {
		this.codigoUUID = codigoUUID;
	}

    /**
     * Obtiene tipo homologacion.
     *
     * @return tipo homologacion
     */
     public Long getTipoHomologacion() {
        return this.tipoHomologacion;
    }
    /**
     * Establece el tipo homologacion.
     *
     * @param tipoHomologacion el new tipo homologacion
     */
    public void setTipoHomologacion(Long tipoHomologacion) {
        this.tipoHomologacion = tipoHomologacion;
    }
    /**
     * Obtiene campo dependiente.
     *
     * @return campo dependiente
     */
     public ConfiguracionTramaDetalleDTO getCampoDependiente() {
        return this.campoDependiente;
    }
    /**
     * Establece el campo dependiente.
     *
     * @param campoDependiente el new campo dependiente
     */
    public void setCampoDependiente(ConfiguracionTramaDetalleDTO campoDependiente) {
        this.campoDependiente = campoDependiente;
    }

    
	/**
	 * Gets the flag campo negocio.
	 *
	 * @return the flag campo negocio
	 */
	public Character getFlagCampoNegocio() {
		return flagCampoNegocio;
	}


	/**
	 * Sets the flag campo negocio.
	 *
	 * @param flagCampoNegocio the new flag campo negocio
	 */
	public void setFlagCampoNegocio(Character flagCampoNegocio) {
		this.flagCampoNegocio = flagCampoNegocio;
	}

	/**
	 * Obtiene regla negocio.
	 *
	 * @return regla negocio
	 */
	public String getReglaNegocio() {
		return reglaNegocio;
	}


	/**
	 * Establece el regla negocio.
	 *
	 * @param reglaNegocio el new regla negocio
	 */
	public void setReglaNegocio(String reglaNegocio) {
		this.reglaNegocio = reglaNegocio;
	}
	/* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idConfiguradorTramaDetalle == null) ? 0 : idConfiguradorTramaDetalle.hashCode());
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
        ConfiguracionTramaDetalleDTO other = (ConfiguracionTramaDetalleDTO) obj;
        if (idConfiguradorTramaDetalle == null) {
            if (other.codigoUUID != null) {
                return false;
            }
        } else if (!idConfiguradorTramaDetalle.equals(other.codigoUUID)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ConfiguracionTramaDetalleDTO [idConfiguradorTramaDetalle=" + idConfiguradorTramaDetalle + "]";
    }

   
}