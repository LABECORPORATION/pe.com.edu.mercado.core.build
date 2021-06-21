package pe.com.builderp.core.facturacion.model.jpa.venta;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pe.com.edu.siaa.core.model.jpa.common.Item;
import pe.com.edu.siaa.core.model.jpa.common.Ubigeo;
import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class GuiaRemision.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu May 02 09:26:23 COT 2019
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "GuiaRemision", schema = ConfiguracionEntityManagerUtil.ESQUEMA_FACTURACION)
public class GuiaRemision implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id guia remision. */
    @Id
    @Column(name = "idGuiaRemision" , length = 32)
    private String idGuiaRemision;
   
    /** El fecha inicio traslado. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaInicioTraslado")
    private Date fechaInicioTraslado;
   
    /** El motivo traslado. */
    @Column(name = "motivoTraslado" , length = 150)
    private String motivoTraslado;
   
    /** El modalidad trasporte. */
    @Column(name = "modalidadTrasporte" , length = 50)
    private String modalidadTrasporte;
   
    /** El peso total. */
    @Column(name = "pesoTotal" , precision = 18 , scale = 2)
    private BigDecimal pesoTotal;
   
    /** El unidad medidad. */
    @Column(name = "unidadMedidad" , length = 150)
    private String unidadMedidad;
   
    /** El ubigeo by punto partida. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUbigeoPuntoPartida", referencedColumnName = "idUbigeo")
    private Ubigeo ubigeoByPuntoPartida;
   
    /** El direccion punto partida. */
    @Column(name = "direccionPuntoPartida" , length = 250)
    private String direccionPuntoPartida;
   
    /** El ubigeo by punto l legada. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUbigeoPuntoLLegada", referencedColumnName = "idUbigeo")
    private Ubigeo ubigeoByPuntoLLegada;
   
    /** El direccion punto l legada. */
    @Column(name = "direccionPuntoLLegada" , length = 250)
    private String direccionPuntoLLegada;
   
    /** El transportista conductor. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTransportistaConductor", referencedColumnName = "idTransportistaConductor")
    private Transportista transportistaConductor;
   
    /** El vehiculo. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVehiculo", referencedColumnName = "idVehiculo")
    private Vehiculo vehiculo;
    
    /** El venta. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVenta", referencedColumnName = "idVenta")
    private Venta venta;
   
    
    /** El tipo doc sunat. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoDocSunat", referencedColumnName = "idItem")
    private Item tipoDocSunat;
    
    /** El nro doc. */
    @Column(name = "nroDoc" , length = 50)
    private String nroDoc;
    
    /** El serie. */
    @Column(name = "serie" , length = 4)
    private String serie;
    
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
    /**
     * Instancia un nuevo guia remision.
     */
    public GuiaRemision() {
    }
   
   
    /**
     * Instancia un nuevo guia remision.
     *
     * @param idGuiaRemision el id guia remision
     * @param fechaInicioTraslado el fecha inicio traslado
     * @param motivoTraslado el motivo traslado
     * @param modalidadTrasporte el modalidad trasporte
     * @param pesoTotal el peso total
     * @param unidadMedidad el unidad medidad
     * @param ubigeoByPuntoPartida el ubigeo by punto partida
     * @param direccionPuntoPartida el direccion punto partida
     * @param ubigeoByPuntoLLegada el ubigeo by punto l legada
     * @param direccionPuntoLLegada el direccion punto l legada
     * @param transportistaConductor el transportista conductor
     * @param vehiculo el vehiculo
     */
    public GuiaRemision(String estado, Item tipoDocSunat,String serie,String nroDoc,String idGuiaRemision, Date fechaInicioTraslado, String motivoTraslado, String modalidadTrasporte, BigDecimal pesoTotal, String unidadMedidad, Ubigeo ubigeoByPuntoPartida,String direccionPuntoPartida, Ubigeo ubigeoByPuntoLLegada,String direccionPuntoLLegada, Transportista transportistaConductor,Vehiculo vehiculo, Venta venta) {
        super();
        this.idGuiaRemision = idGuiaRemision;
        this.fechaInicioTraslado = fechaInicioTraslado;
        this.motivoTraslado = motivoTraslado;
        this.modalidadTrasporte = modalidadTrasporte;
        this.pesoTotal = pesoTotal;
        this.unidadMedidad = unidadMedidad;
        this.ubigeoByPuntoPartida = ubigeoByPuntoPartida;
        this.direccionPuntoPartida = direccionPuntoPartida;
        this.ubigeoByPuntoLLegada = ubigeoByPuntoLLegada;
        this.direccionPuntoLLegada = direccionPuntoLLegada;
        this.transportistaConductor = transportistaConductor;
        this.vehiculo = vehiculo;
        this.venta = venta;
        this.tipoDocSunat = tipoDocSunat;
        this.serie = serie;
        this.nroDoc = nroDoc;
        this.estado = estado;
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
    
    public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
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
    
    
    


	public Item getTipoDocSunat() {
		return tipoDocSunat;
	}


	public void setTipoDocSunat(Item tipoDocSunat) {
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


	public void setSerie(String serie) {
		this.serie = serie;
	}


	public Venta getVenta() {
		return venta;
	}


	public void setVenta(Venta venta) {
		this.venta = venta;
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
    /**
     * Obtiene ubigeo by punto partida.
     *
     * @return ubigeo by punto partida
     */
     public Ubigeo getUbigeoByPuntoPartida() {
        return this.ubigeoByPuntoPartida;
    }
    /**
     * Establece el ubigeo by punto partida.
     *
     * @param ubigeoByPuntoPartida el new ubigeo by punto partida
     */
    public void setUbigeoByPuntoPartida(Ubigeo ubigeoByPuntoPartida) {
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
     public Ubigeo getUbigeoByPuntoLLegada() {
        return this.ubigeoByPuntoLLegada;
    }
    /**
     * Establece el ubigeo by punto l legada.
     *
     * @param ubigeoByPuntoLLegada el new ubigeo by punto l legada
     */
    public void setUbigeoByPuntoLLegada(Ubigeo ubigeoByPuntoLLegada) {
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
     public Transportista getTransportistaConductor() {
        return this.transportistaConductor;
    }
    /**
     * Establece el transportista conductor.
     *
     * @param transportistaConductor el new transportista conductor
     */
    public void setTransportistaConductor(Transportista transportistaConductor) {
        this.transportistaConductor = transportistaConductor;
    }
    /**
     * Obtiene vehiculo.
     *
     * @return vehiculo
     */
     public Vehiculo getVehiculo() {
        return this.vehiculo;
    }
    /**
     * Establece el vehiculo.
     *
     * @param vehiculo el new vehiculo
     */
    public void setVehiculo(Vehiculo vehiculo) {
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
        GuiaRemision other = (GuiaRemision) obj;
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
        return "GuiaRemision [idGuiaRemision=" + idGuiaRemision + "]";
    }
   
}