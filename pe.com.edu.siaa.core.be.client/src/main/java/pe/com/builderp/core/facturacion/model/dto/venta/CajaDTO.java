package pe.com.builderp.core.facturacion.model.dto.venta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.seguridad.UsuarioDTO; 

/**
 * La Class ProformaDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Fri Dec 22 11:07:03 COT 2017
 * @since SIAA-CORE 2.1
 */
public class CajaDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id proforma. */
    private String idCaja;
  
   
    /** El codigo proforma. */
    private String descripcion;
 
   
    /** El tipo cambio. */
    private BigDecimal montoApertuera;
   
    /** El nro doc. */
    private String horaApertura;
   
    /** El monto total. */
    private BigDecimal montoTotal;
    
    /** El monto total. */
    private BigDecimal montoCiere;
   
    /** El usuario creacion. */
    private String horaCierre;
   
    
    private UsuarioDTO usuario;
 
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
   
    /**
     * Instancia un nuevo proformaDTO.
     */
    public CajaDTO() {
    }

	public CajaDTO(String idCaja, String descripcion, BigDecimal montoApertuera, String horaApertura,
			BigDecimal montoTotal, BigDecimal montoCiere, String horaCierre, UsuarioDTO usuario, Date fechaCreacion,
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

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
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
		CajaDTO other = (CajaDTO) obj;
		if (idCaja == null) {
			if (other.idCaja != null)
				return false;
		} else if (!idCaja.equals(other.idCaja))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CajaDTO [idCaja=" + idCaja + "]";
	}
    
    
}