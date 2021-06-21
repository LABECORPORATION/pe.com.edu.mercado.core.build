package pe.com.builderp.core.facturacion.model.dto.empresa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import pe.com.edu.siaa.core.model.dto.BasePaginator;

/**
 * La Class DetControlPagoDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Tue Apr 18 09:56:57 COT 2017
 * @since SIAA-CORE 2.1
 */
public class DetControlPagoDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id det control pago. */
    private String idDetControlPago;
   
    /** El control pago. */
    private ControlPagoDTO controlPago;
   
    /** El det plan pagos. */
    private CuotaConceptoDTO cuotaConcepto;
   
    /** El fraccionamiento. */
    private String nroCuota;
   
    /** El descripcionconcepto. */
    private String descripcionConcepto;
   
    /** El monto. */
    private BigDecimal monto;
   
    /** El mora. */
    private BigDecimal mora;
   
    /** El fecha reversion. */
    private Date fechaReversion;
   
    /** El fecha creacion. */
    private Date fechaCreacion;
   
    /** El usuario creacion. */
    private String usuarioCreacion;
   
    /** El fecha modificacion. */
    private Date fechaModificacion;
   
    /** El usuario modificacion. */
    private String usuarioModificacion;
   
    /** El estado. */
    private String estado;
   
    /** El ip. */
    private String ip;
   
    /**
     * Instancia un nuevo det control pagoDTO.
     */
    public DetControlPagoDTO() {
    }
   
   
    /**
     * Instancia un nuevo det control pagoDTO.
     *
     * @param idDetControlPago el id det control pago
     * @param controlPago el control pago
     * @param detPlanPagos el det plan pagos
     * @param nroCuota el fraccionamiento
     * @param descripcionconcepto el descripcionconcepto
     * @param monto el monto
     * @param mora el mora
     * @param fechaReversion el fecha reversion
     * @param fechaCreacion el fecha creacion
     * @param usuarioCreacion el usuario creacion
     * @param fechaModificacion el fecha modificacion
     * @param usuarioModificacion el usuario modificacion
     * @param estado el estado
     * @param ip el ip
     */
    public DetControlPagoDTO(String idDetControlPago, ControlPagoDTO controlPago,CuotaConceptoDTO cuotaConcepto,String nroCuota,String descripcionConcepto, BigDecimal monto, BigDecimal mora, Date fechaReversion, Date fechaCreacion, String usuarioCreacion, Date fechaModificacion, String usuarioModificacion, String estado, String ip ) {
        super();
        this.idDetControlPago = idDetControlPago;
        this.controlPago = controlPago;
        this.cuotaConcepto = cuotaConcepto;
        this.nroCuota = nroCuota;
        this.descripcionConcepto = descripcionConcepto;
        this.monto = monto;
        this.mora = mora;
        this.fechaReversion = fechaReversion;
        this.fechaCreacion = fechaCreacion;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioModificacion = usuarioModificacion;
        this.estado = estado;
        this.ip = ip;
    }
   
    //get y set
    /**
     * Obtiene id det control pago.
     *
     * @return id det control pago
     */
     public String getIdDetControlPago() {
        return this.idDetControlPago;
    }
    /**
     * Establece el id det control pago.
     *
     * @param idDetControlPago el new id det control pago
     */
    public void setIdDetControlPago(String idDetControlPago) {
        this.idDetControlPago = idDetControlPago;
    }
    /**
     * Obtiene control pago.
     *
     * @return control pago
     */
     public ControlPagoDTO getControlPago() {
        return this.controlPago;
    }
    /**
     * Establece el control pago.
     *
     * @param controlPago el new control pago
     */
    public void setControlPago(ControlPagoDTO controlPago) {
        this.controlPago = controlPago;
    }
    /**
     * Obtiene det plan pagos.
     *
     * @return det plan pagos
     */
     public CuotaConceptoDTO getCuotaConcepto() {
        return this.cuotaConcepto;
    }
    /**
     * Establece el det plan pagos.
     *
     * @param detPlanPagos el new det plan pagos
     */
    public void setCuotaConceptoDTO(CuotaConceptoDTO cuotaConcepto) {
        this.cuotaConcepto = cuotaConcepto;
    }
    /**
     * Obtiene fraccionamiento.
     *
     * @return fraccionamiento
     */
     public String getNroCuota() {
        return this.nroCuota;
    }
    /**
     * Establece el fraccionamiento.
     *
     * @param fraccionamiento el new fraccionamiento
     */
    public void setNroCuota(String  nroCuota) {
        this.nroCuota = nroCuota;
    }
    /**
     * Obtiene descripcionconcepto.
     *
     * @return descripcionconcepto
     */
     public String getDescripcionConcepto() {
        return this.descripcionConcepto;
    }
    /**
     * Establece el descripcionconcepto.
     *
     * @param descripcionconcepto el new descripcionconcepto
     */
    public void setDescripcionConcepto(String descripcionConcepto) {
        this.descripcionConcepto = descripcionConcepto;
    }
    /**
     * Obtiene monto.
     *
     * @return monto
     */
     public BigDecimal getMonto() {
        return this.monto;
    }
    /**
     * Establece el monto.
     *
     * @param monto el new monto
     */
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
    /**
     * Obtiene mora.
     *
     * @return mora
     */
     public BigDecimal getMora() {
        return this.mora;
    }
    /**
     * Establece el mora.
     *
     * @param mora el new mora
     */
    public void setMora(BigDecimal mora) {
        this.mora = mora;
    }
    /**
     * Obtiene fecha reversion.
     *
     * @return fecha reversion
     */
     public Date getFechaReversion() {
        return this.fechaReversion;
    }
    /**
     * Establece el fecha reversion.
     *
     * @param fechaReversion el new fecha reversion
     */
    public void setFechaReversion(Date fechaReversion) {
        this.fechaReversion = fechaReversion;
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
     * Obtiene usuario modificacion.
     *
     * @return usuario modificacion
     */
     public String getUsuarioModificacion() {
        return this.usuarioModificacion;
    }
    /**
     * Establece el usuario modificacion.
     *
     * @param usuarioModificacion el new usuario modificacion
     */
    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
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
     * Obtiene ip.
     *
     * @return ip
     */
     public String getIp() {
        return this.ip;
    }
    /**
     * Establece el ip.
     *
     * @param ip el new ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idDetControlPago == null) ? 0 : idDetControlPago.hashCode());
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
        DetControlPagoDTO other = (DetControlPagoDTO) obj;
        if (idDetControlPago == null) {
            if (other.idDetControlPago != null) {
                return false;
            }
        } else if (!idDetControlPago.equals(other.idDetControlPago)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DetControlPagoDTO [idDetControlPago=" + idDetControlPago + "]";
    }
   
}