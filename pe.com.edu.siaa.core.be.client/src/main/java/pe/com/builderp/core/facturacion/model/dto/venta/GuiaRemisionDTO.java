package pe.com.builderp.core.facturacion.model.dto.venta;

import java.io.Serializable;
import java.util.Date;


import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO;
import pe.com.edu.siaa.core.model.dto.common.UbigeoDTO;


import java.math.BigDecimal;

/**
 * La Class GuiaRemisionDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Thu May 02 10:20:30 COT 2019
 * @since SIAA-CORE 2.1
 */
public class GuiaRemisionDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id guia remision. */
    private String idGuiaRemision;
   
    /** El fecha inicio traslado. */
    private Date fechaInicioTraslado;
   
    /** El motivo traslado. */
    private String motivoTraslado;
   
    /** El modalidad trasporte. */
    private String modalidadTrasporte;
   
    /** El peso total. */
    private BigDecimal pesoTotal;
   
    /** El unidad medidad. */
    private String unidadMedidad;
   
    /** El ubigeo by punto partida. */
    private UbigeoDTO ubigeoByPuntoPartida;
   
    /** El direccion punto partida. */
    private String direccionPuntoPartida;
   
    /** El ubigeo by punto l legada. */
    private UbigeoDTO ubigeoByPuntoLLegada;
   
    /** El direccion punto l legada. */
    private String direccionPuntoLLegada;
   
    /** El transportista conductor. */
    private TransportistaDTO transportistaConductor;
   
    /** El vehiculo. */
    private VehiculoDTO vehiculo;
    
    private VentaDTO venta;
    
    private ItemDTO tipoDocSunat;
    
    /** El nro doc. */

    private String nroDoc;
    
    /** El serie. */

    private String serie;
    
    private String estado;
    
    private String departamentoPuntoPartida;
    
    private String departamentoPuntoLLegada;
   
    /**
     * Instancia un nuevo guia remisionDTO.
     */
    public GuiaRemisionDTO() {
    }
   
   
    //get y set
    /**
     * Obtiene id guia remision.
     *
     * @return id guia remision
     */
     public String getIdGuiaRemision() {
        return this.idGuiaRemision;
    }
    /**
     * Establece el id guia remision.
     *
     * @param idGuiaRemision el new id guia remision
     */
    public void setIdGuiaRemision(String idGuiaRemision) {
        this.idGuiaRemision = idGuiaRemision;
    }
    /**
     * Obtiene fecha inicio traslado.
     *
     * @return fecha inicio traslado
     */
     public Date getFechaInicioTraslado() {
        return this.fechaInicioTraslado;
    }
    /**
     * Establece el fecha inicio traslado.
     *
     * @param fechaInicioTraslado el new fecha inicio traslado
     */
    public void setFechaInicioTraslado(Date fechaInicioTraslado) {
        this.fechaInicioTraslado = fechaInicioTraslado;
    }
    /**
     * Obtiene motivo traslado.
     *
     * @return motivo traslado
     */
     public String getMotivoTraslado() {
        return this.motivoTraslado;
    }
    /**
     * Establece el motivo traslado.
     *
     * @param motivoTraslado el new motivo traslado
     */
    public void setMotivoTraslado(String motivoTraslado) {
        this.motivoTraslado = motivoTraslado;
    }
    /**
     * Obtiene modalidad trasporte.
     *
     * @return modalidad trasporte
     */
     public String getModalidadTrasporte() {
        return this.modalidadTrasporte;
    }
    /**
     * Establece el modalidad trasporte.
     *
     * @param modalidadTrasporte el new modalidad trasporte
     */
    public void setModalidadTrasporte(String modalidadTrasporte) {
        this.modalidadTrasporte = modalidadTrasporte;
    }
    
    
    public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	/**
     * Obtiene peso total.
     *
     * @return peso total
     */
     public BigDecimal getPesoTotal() {
        return this.pesoTotal;
    }
    /**
     * Establece el peso total.
     *
     * @param pesoTotal el new peso total
     */
    public void setPesoTotal(BigDecimal pesoTotal) {
        this.pesoTotal = pesoTotal;
    }
    /**
     * Obtiene unidad medidad.
     *
     * @return unidad medidad
     */
     public String getUnidadMedidad() {
        return this.unidadMedidad;
    }
    /**
     * Establece el unidad medidad.
     *
     * @param unidadMedidad el new unidad medidad
     */
    public void setUnidadMedidad(String unidadMedidad) {
        this.unidadMedidad = unidadMedidad;
    }
    
    
    
    public ItemDTO getTipoDocSunat() {
		return tipoDocSunat;
	}


	public void setTipoDocSunat(ItemDTO tipoDocSunat) {
		this.tipoDocSunat = tipoDocSunat;
	}


	public String getNroDoc() {
		return nroDoc;
	}


	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}


	public String getSerie() {
		return serie;
	}
	


	public String getDepartamentoPuntoPartida() {
		return departamentoPuntoPartida;
	}


	public void setDepartamentoPuntoPartida(String departamentoPuntoPartida) {
		this.departamentoPuntoPartida = departamentoPuntoPartida;
	}

 


	public String getDepartamentoPuntoLLegada() {
		return departamentoPuntoLLegada;
	}


	public void setDepartamentoPuntoLLegada(String departamentoPuntoLLegada) {
		this.departamentoPuntoLLegada = departamentoPuntoLLegada;
	}


	public void setSerie(String serie) {
		this.serie = serie;
	}


	public VentaDTO getVenta() {
		return venta;
	}


	public void setVenta(VentaDTO venta) {
		this.venta = venta;
	}


	/**
     * Obtiene ubigeo by punto partida.
     *
     * @return ubigeo by punto partida
     */
     public UbigeoDTO getUbigeoByPuntoPartida() {
        return this.ubigeoByPuntoPartida;
    }
    /**
     * Establece el ubigeo by punto partida.
     *
     * @param ubigeoByPuntoPartida el new ubigeo by punto partida
     */
    public void setUbigeoByPuntoPartida(UbigeoDTO ubigeoByPuntoPartida) {
        this.ubigeoByPuntoPartida = ubigeoByPuntoPartida;
    }
    /**
     * Obtiene direccion punto partida.
     *
     * @return direccion punto partida
     */
     public String getDireccionPuntoPartida() {
        return this.direccionPuntoPartida;
    }
    /**
     * Establece el direccion punto partida.
     *
     * @param direccionPuntoPartida el new direccion punto partida
     */
    public void setDireccionPuntoPartida(String direccionPuntoPartida) {
        this.direccionPuntoPartida = direccionPuntoPartida;
    }
    /**
     * Obtiene ubigeo by punto l legada.
     *
     * @return ubigeo by punto l legada
     */
     public UbigeoDTO getUbigeoByPuntoLLegada() {
        return this.ubigeoByPuntoLLegada;
    }
    /**
     * Establece el ubigeo by punto l legada.
     *
     * @param ubigeoByPuntoLLegada el new ubigeo by punto l legada
     */
    public void setUbigeoByPuntoLLegada(UbigeoDTO ubigeoByPuntoLLegada) {
        this.ubigeoByPuntoLLegada = ubigeoByPuntoLLegada;
    }
    /**
     * Obtiene direccion punto l legada.
     *
     * @return direccion punto l legada
     */
     public String getDireccionPuntoLLegada() {
        return this.direccionPuntoLLegada;
    }
    /**
     * Establece el direccion punto l legada.
     *
     * @param direccionPuntoLLegada el new direccion punto l legada
     */
    public void setDireccionPuntoLLegada(String direccionPuntoLLegada) {
        this.direccionPuntoLLegada = direccionPuntoLLegada;
    }
    /**
     * Obtiene transportista conductor.
     *
     * @return transportista conductor
     */
     public TransportistaDTO getTransportistaConductor() {
        return this.transportistaConductor;
    }
    /**
     * Establece el transportista conductor.
     *
     * @param transportistaConductor el new transportista conductor
     */
    public void setTransportistaConductor(TransportistaDTO transportistaConductor) {
        this.transportistaConductor = transportistaConductor;
    }
    /**
     * Obtiene vehiculo.
     *
     * @return vehiculo
     */
     public VehiculoDTO getVehiculo() {
        return this.vehiculo;
    }
    /**
     * Establece el vehiculo.
     *
     * @param vehiculo el new vehiculo
     */
    public void setVehiculo(VehiculoDTO vehiculo) {
        this.vehiculo = vehiculo;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idGuiaRemision == null) ? 0 : idGuiaRemision.hashCode());
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
        GuiaRemisionDTO other = (GuiaRemisionDTO) obj;
        if (idGuiaRemision == null) {
            if (other.idGuiaRemision != null) {
                return false;
            }
        } else if (!idGuiaRemision.equals(other.idGuiaRemision)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "GuiaRemisionDTO [idGuiaRemision=" + idGuiaRemision + "]";
    }
   
}