package pe.com.edu.siaa.core.model.jpa.empresa;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table; 

import pe.com.edu.siaa.core.model.jpa.common.Item; 
import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class AgrupaEntidad.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Wed Dec 20 00:30:21 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "Puesto", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_EMPRESA)
public class Puesto implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id agrupa entidad. */
    @Id
    @Column(name = "idPuesto" , length = 32)
    private String idPuesto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCondicion", referencedColumnName = "idItem")
    private Item itemCondicion;
    
    /** El nombre. */
    @Column(name = "descripcion" , length = 200)
    private String descripcion;
    
    /** El nombre. */
    @Column(name = "codigo" , length = 32)
    private String codigo;
    
    /** El nombre. */
    @Column(name = "codigoReferencia" , length = 32)
    private String codigoReferencia;
     
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSector", referencedColumnName = "idSector")
    private Sector sector;
     
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAsociado", referencedColumnName = "idAsociado")
    private Asociado asociado;
     
    @Column(name = "pago" , precision = 18 , scale = 2)
    private BigDecimal pago;
     
   
    /**
     * Instancia un nuevo agrupa entidad.
     */
    public Puesto() {
    }



	public Puesto(String idPuesto, Item itemCondicion, String descripcion, String codigo, String codigoReferencia,
			Sector sector, String estado,Asociado asociado,BigDecimal pago) {
		super();
		this.idPuesto = idPuesto;
		this.itemCondicion = itemCondicion;
		this.descripcion = descripcion;
		this.codigo = codigo;
		this.codigoReferencia = codigoReferencia;
		this.sector = sector;
		this.estado = estado;
		this.asociado =asociado;
		this.pago=pago;
	}



	public BigDecimal getPago() {
		return pago;
	}



	public void setPago(BigDecimal pago) {
		this.pago = pago;
	}



	public Asociado getAsociado() {
		return asociado;
	}



	public void setAsociado(Asociado asociado) {
		this.asociado = asociado;
	}



	public String getIdPuesto() {
		return idPuesto;
	}



	public void setIdPuesto(String idPuesto) {
		this.idPuesto = idPuesto;
	}



	public Item getItemCondicion() {
		return itemCondicion;
	}



	public void setItemCondicion(Item itemCondicion) {
		this.itemCondicion = itemCondicion;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public String getCodigo() {
		return codigo;
	}



	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}



	public String getCodigoReferencia() {
		return codigoReferencia;
	}



	public void setCodigoReferencia(String codigoReferencia) {
		this.codigoReferencia = codigoReferencia;
	}



	public Sector getSector() {
		return sector;
	}



	public void setSector(Sector sector) {
		this.sector = sector;
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
		result = prime * result + ((idPuesto == null) ? 0 : idPuesto.hashCode());
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
		Puesto other = (Puesto) obj;
		if (idPuesto == null) {
			if (other.idPuesto != null)
				return false;
		} else if (!idPuesto.equals(other.idPuesto))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Puesto [idPuesto=" + idPuesto + "]";
	}

	 
}