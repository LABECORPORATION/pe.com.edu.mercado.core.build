package pe.com.builderp.core.facturacion.model.jpa.venta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import pe.com.edu.siaa.core.model.jpa.seguridad.Usuario;
import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class Proforma.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Fri Dec 22 11:10:36 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "Caja", schema = ConfiguracionEntityManagerUtil.ESQUEMA_FACTURACION)
public class Caja implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id proforma. */
    @Id
    @Column(name = "idCaja" , length = 32)
    private String idCaja;
   
    /** El codigo proforma. */
    @Column(name = "descripcion" , length = 150)
    private String descripcion;
  
   
    /** El igv. */
    @Column(name = "montoApertuera" , precision = 18 , scale = 2)
    private BigDecimal montoApertuera;
   
    /** El usuario creacion. */
    @Column(name = "horaApertura" , length = 50)
    private String horaApertura;
    
    /** El igv. */
    @Column(name = "montoTotal" , precision = 18 , scale = 2)
    private BigDecimal montoTotal;
    
    
    /** El igv. */
    @Column(name = "montoCiere" , precision = 18 , scale = 2)
    private BigDecimal montoCiere;
   
    /** El estado. */
    @Column(name = "horaCierre" , length = 50)
    private String horaCierre;
   
    /** El tipo usuario. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private Usuario usuario;
    

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
    
    
    /** El estado. */
    @Column(name = "estado" , length = 2)
    private String estado;
    
    /**
     * Instancia un nuevo proforma.
     */
    public Caja() {
    }

	public Caja(String idCaja, String descripcion, BigDecimal montoApertuera, String horaApertura,
			BigDecimal montoTotal, BigDecimal montoCiere, String horaCierre, Usuario usuario, Date fechaCreacion,
			String usuarioCreacion, Date fechaModificacion, String usuarioModificacion, String estado) {
		super();
		this.idCaja = idCaja;
		this.descripcion = descripcion;
		this.montoApertuera = montoApertuera;
		this.horaApertura = horaApertura;
		this.montoTotal = montoTotal;
		this.montoCiere = montoCiere;
		this.horaCierre = horaCierre;
		this.usuario = usuario;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaModificacion = fechaModificacion;
		this.usuarioModificacion = usuarioModificacion;
		this.estado = estado;
	}

	public String getIdCaja() {
		return idCaja;
	}

	public void setIdCaja(String idCaja) {
		this.idCaja = idCaja;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getMontoApertuera() {
		return montoApertuera;
	}

	public void setMontoApertuera(BigDecimal montoApertuera) {
		this.montoApertuera = montoApertuera;
	}

	public String getHoraApertura() {
		return horaApertura;
	}

	public void setHoraApertura(String horaApertura) {
		this.horaApertura = horaApertura;
	}

	public BigDecimal getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}

	public BigDecimal getMontoCiere() {
		return montoCiere;
	}

	public void setMontoCiere(BigDecimal montoCiere) {
		this.montoCiere = montoCiere;
	}

	public String getHoraCierre() {
		return horaCierre;
	}

	public void setHoraCierre(String horaCierre) {
		this.horaCierre = horaCierre;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCaja == null) ? 0 : idCaja.hashCode());
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
		Caja other = (Caja) obj;
		if (idCaja == null) {
			if (other.idCaja != null)
				return false;
		} else if (!idCaja.equals(other.idCaja))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Caja [idCaja=" + idCaja + "]";
	}
    
    
}