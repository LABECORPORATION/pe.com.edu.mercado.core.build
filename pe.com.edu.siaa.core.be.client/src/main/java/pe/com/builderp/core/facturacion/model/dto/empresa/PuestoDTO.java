package pe.com.builderp.core.facturacion.model.dto.empresa;

import java.io.Serializable;
import java.math.BigDecimal;

import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO; 
 

/**
 * La Class CategoriaDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Fri Dec 22 11:07:00 COT 2017
 * @since SIAA-CORE 2.1
 */
public class PuestoDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    private String idPuesto;
     
    private ItemDTO itemCondicion;
    
    private String descripcion;
    
    private String codigo;
    
    private String codigoReferencia;
    
    private SectorDTO sector;
    
    private String estado;
    
    private AsociadoDTO asociado;
     
    private BigDecimal pago;
    /**
     * Instancia un nuevo categoriaDTO.
     */
    public PuestoDTO() {
    }

	public PuestoDTO(String idPuesto, ItemDTO itemCondicion, String descripcion, String codigo, String codigoReferencia,
			SectorDTO sector, String estado,AsociadoDTO asociado,BigDecimal pago) {
		super();
		this.idPuesto = idPuesto;
		this.itemCondicion = itemCondicion;
		this.descripcion = descripcion;
		this.codigo = codigo;
		this.codigoReferencia = codigoReferencia;
		this.sector = sector;
		this.estado = estado;
		this.asociado=asociado;
		this.pago=pago;
	}

	public BigDecimal getPago() {
		return pago;
	}

	public void setPago(BigDecimal pago) {
		this.pago = pago;
	}

	public AsociadoDTO getAsociado() {
		return asociado;
	}

	public void setAsociado(AsociadoDTO asociado) {
		this.asociado = asociado;
	}

	public String getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(String idPuesto) {
		this.idPuesto = idPuesto;
	}

	public ItemDTO getItemCondicion() {
		return itemCondicion;
	}

	public void setItemCondicion(ItemDTO itemCondicion) {
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

	public SectorDTO getSector() {
		return sector;
	}

	public void setSector(SectorDTO sector) {
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
		PuestoDTO other = (PuestoDTO) obj;
		if (idPuesto == null) {
			if (other.idPuesto != null)
				return false;
		} else if (!idPuesto.equals(other.idPuesto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PuestoDTO [idPuesto=" + idPuesto + "]";
	}

 
}