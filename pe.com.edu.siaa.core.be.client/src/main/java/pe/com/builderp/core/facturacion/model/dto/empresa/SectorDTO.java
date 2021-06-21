package pe.com.builderp.core.facturacion.model.dto.empresa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.vo.SelectItemVO; 
 

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
public class SectorDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    private String idSector;
     
    private AsociadoDTO delegado;
    
    private String descripcion;
    
    private String direccion;
    
    private String referencia;
    
    private String codigo;
          
    private String estado;
    
    
    /** El postulante documento requerido list. */
    private List<SelectItemVO> listaRubroRequerido = new ArrayList<SelectItemVO>();
 
    /**
     * Instancia un nuevo categoriaDTO.
     */
    public SectorDTO() {
    }

	public SectorDTO(String idSector, AsociadoDTO delegado, String descripcion, String direccion, String referencia,
			String codigo, String estado) {
		super();
		this.idSector = idSector;
		this.delegado = delegado;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.referencia = referencia;
		this.codigo = codigo;
		this.estado = estado;
	}

	public List<SelectItemVO> getListaRubroRequerido() {
		return listaRubroRequerido;
	}

	public void setListaRubroRequerido(List<SelectItemVO> listaRubroRequerido) {
		this.listaRubroRequerido = listaRubroRequerido;
	}

	public String getIdSector() {
		return idSector;
	}

	public void setIdSector(String idSector) {
		this.idSector = idSector;
	}

	public AsociadoDTO getDelegado() {
		return delegado;
	}

	public void setDelegado(AsociadoDTO delegado) {
		this.delegado = delegado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
		result = prime * result + ((idSector == null) ? 0 : idSector.hashCode());
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
		SectorDTO other = (SectorDTO) obj;
		if (idSector == null) {
			if (other.idSector != null)
				return false;
		} else if (!idSector.equals(other.idSector))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SectorDTO [idSector=" + idSector + "]";
	}

 
}