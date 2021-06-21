package pe.com.edu.siaa.core.model.jpa.empresa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.Id; 
import javax.persistence.Table;
 
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
@Table(name = "CategoriaByEmpresa", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_EMPRESA)
public class CategoriaByEmpresa implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id agrupa entidad. */
    @Id
    @Column(name = "idCategoria" , length = 32)
    private String idCategoria;

    /** El estado. */
    @Column(name = "nombre" , length = 200)
    private String nombre;
    
    /** El estado. */
    @Column(name = "descripcion" , length = 200)
    private String descripcion;
    
    
    /** El estado. */
    @Column(name = "tipoCat" , length = 1)
    private String tipoCat;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
   
    /**
     * Instancia un nuevo agrupa entidad.
     */
    public CategoriaByEmpresa() {
    }

	public CategoriaByEmpresa(String idCategoria, String nombre, String descripcion, String tipoCat, String estado) {
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
		CategoriaByEmpresa other = (CategoriaByEmpresa) obj;
		if (idCategoria == null) {
			if (other.idCategoria != null)
				return false;
		} else if (!idCategoria.equals(other.idCategoria))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idCategoria + "]";
	}
   
   
    
}