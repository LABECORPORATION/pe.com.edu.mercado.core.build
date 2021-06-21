package pe.com.edu.siaa.core.model.jpa.empresa;

import java.io.Serializable;
import java.math.BigDecimal; 
import java.util.Date; 

import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.Id; 
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
 
import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class CuotaConcepto.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 09:56:45 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "CuotaConcepto", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_EMPRESA)
public class CuotaConcepto implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id cuota concepto. */
    @Id
    @Column(name = "idCuotaConcepto" , length = 8)
    private String idCuotaConcepto;
    
    /** El cuenta. */
    @Column(name = "cuenta" , length = 200)
    private String cuenta;
   
    /** El nro cuenta. */
    @Column(name = "nroCuenta" , length = 50)
    private String nroCuenta;
    
   
    /** El nro min fraccionamiento. */
    @Column(name = "nroMinFraccionamiento" , precision = 32 , scale = 0)
    private Integer nroMinFraccionamiento;
   
    /** El nro max fraccionamiento. */
    @Column(name = "nroMaxFraccionamiento" , precision = 32 , scale = 0)
    private Integer nroMaxFraccionamiento;
   
    /** El monto. */
    @Column(name = "monto" , precision = 18 , scale = 2)
    private BigDecimal monto;
   
    /** El permanente. */
    @Column(name = "permanente" , length = 1)
    private String permanente;
   
    /** El fecha tentativa. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaTentativa")
    private Date fechaTentativa;
   
    /** El fecha creacion. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaCreacion")
    private Date fechaCreacion;
   
    /** El usuario creacion. */
    @Column(name = "usuarioCreacion" , length = 50)
    private String usuarioCreacion;
   
    /** El fecha modificacion. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaModificacion")
    private Date fechaModificacion;
   
    /** El usuario modificacion. */
    @Column(name = "usuarioModificacion" , length = 50)
    private String usuarioModificacion;
   
 
    /**
     * Instancia un nuevo cuota concepto.
     */
    public CuotaConcepto() {
    }


	public CuotaConcepto(String idCuotaConcepto, String cuenta, String nroCuenta, Integer nroMinFraccionamiento,
			Integer nroMaxFraccionamiento, BigDecimal monto, String permanente, Date fechaTentativa, Date fechaCreacion,
			String usuarioCreacion, Date fechaModificacion, String usuarioModificacion) {
		super();
		this.idCuotaConcepto = idCuotaConcepto;
		this.cuenta = cuenta;
		this.nroCuenta = nroCuenta;
		this.nroMinFraccionamiento = nroMinFraccionamiento;
		this.nroMaxFraccionamiento = nroMaxFraccionamiento;
		this.monto = monto;
		this.permanente = permanente;
		this.fechaTentativa = fechaTentativa;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaModificacion = fechaModificacion;
		this.usuarioModificacion = usuarioModificacion;
	}


	public String getIdCuotaConcepto() {
		return idCuotaConcepto;
	}


	public void setIdCuotaConcepto(String idCuotaConcepto) {
		this.idCuotaConcepto = idCuotaConcepto;
	}


	public String getCuenta() {
		return cuenta;
	}


	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}


	public String getNroCuenta() {
		return nroCuenta;
	}


	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}


	public Integer getNroMinFraccionamiento() {
		return nroMinFraccionamiento;
	}


	public void setNroMinFraccionamiento(Integer nroMinFraccionamiento) {
		this.nroMinFraccionamiento = nroMinFraccionamiento;
	}


	public Integer getNroMaxFraccionamiento() {
		return nroMaxFraccionamiento;
	}


	public void setNroMaxFraccionamiento(Integer nroMaxFraccionamiento) {
		this.nroMaxFraccionamiento = nroMaxFraccionamiento;
	}


	public BigDecimal getMonto() {
		return monto;
	}


	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}


	public String getPermanente() {
		return permanente;
	}


	public void setPermanente(String permanente) {
		this.permanente = permanente;
	}


	public Date getFechaTentativa() {
		return fechaTentativa;
	}


	public void setFechaTentativa(Date fechaTentativa) {
		this.fechaTentativa = fechaTentativa;
	}


	public Date getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}


	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}


	public Date getFechaModificacion() {
		return fechaModificacion;
	}


	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}


	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}


	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCuotaConcepto == null) ? 0 : idCuotaConcepto.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CuotaConcepto other = (CuotaConcepto) obj;
		if (idCuotaConcepto == null) {
			if (other.idCuotaConcepto != null)
				return false;
		} else if (!idCuotaConcepto.equals(other.idCuotaConcepto))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "CuotaConcepto [idCuotaConcepto=" + idCuotaConcepto + "]";
	}
   
    
}