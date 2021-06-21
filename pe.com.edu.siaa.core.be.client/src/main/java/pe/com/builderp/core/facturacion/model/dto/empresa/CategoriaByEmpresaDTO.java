package pe.com.builderp.core.facturacion.model.dto.empresa;

import java.io.Serializable;
 
import pe.com.edu.siaa.core.model.dto.BasePaginator; 
 

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
public class CategoriaByEmpresaDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    private String idCategoria;
   
    private String nombre;
   
    private String descripcion;
    
    private String tipoCat;
   
    private String estado;
   
   
    /**
     * Instancia un nuevo categoriaDTO.
     */
    public CategoriaByEmpresaDTO() {
    }


	public CategoriaByEmpresaDTO(String idCategoria, String nombre, String descripcion, String tipoCat, String estado) {
		super();
		this.idCategoria = idCategoria;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipoCat = tipoCat;
		this.estado = estado;
	}
 
	

	public String getIdCategoria() {
		return idCategoria;
	}


	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

  

	public String getTipoCat() {
		return tipoCat;
	}


	public void setTipoCat(String tipoCat) {
		this.tipoCat = tipoCat;
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
		result = prime * result + ((idCategoria == null) ? 0 : idCategoria.hashCode());
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
		CategoriaByEmpresaDTO other = (CategoriaByEmpresaDTO) obj;
		if (idCategoria == null) {
			if (other.idCategoria != null)
				return false;
		} else if (!idCategoria.equals(other.idCategoria))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "CategoriaDTO [idCategoria=" + idCategoria + "]";
	}
   
    
}