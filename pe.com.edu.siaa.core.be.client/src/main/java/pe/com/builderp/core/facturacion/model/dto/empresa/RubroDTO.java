package pe.com.builderp.core.facturacion.model.dto.empresa;

import java.io.Serializable; 
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
public class RubroDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    private String idRubro;
     
    private SectorDTO sector;
    
    private ItemDTO itemRubro;
    
    private String descripcionOtro;
     
     
    /**
     * Instancia un nuevo categoriaDTO.
     */
    public RubroDTO() {
    }


	public RubroDTO(String idRubro, SectorDTO sector, ItemDTO itemRubro, String descripcionOtro) {
		super();
		this.idRubro = idRubro;
		this.sector = sector;
		this.itemRubro = itemRubro;
		this.descripcionOtro = descripcionOtro;
	}


	public String getIdRubro() {
		return idRubro;
	}


	public void setIdRubro(String idRubro) {
		this.idRubro = idRubro;
	}


	public SectorDTO getSector() {
		return sector;
	}


	public void setSector(SectorDTO sector) {
		this.sector = sector;
	}


	public ItemDTO getItemRubro() {
		return itemRubro;
	}


	public void setItemRubro(ItemDTO itemRubro) {
		this.itemRubro = itemRubro;
	}


	public String getDescripcionOtro() {
		return descripcionOtro;
	}


	public void setDescripcionOtro(String descripcionOtro) {
		this.descripcionOtro = descripcionOtro;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRubro == null) ? 0 : idRubro.hashCode());
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
		RubroDTO other = (RubroDTO) obj;
		if (idRubro == null) {
			if (other.idRubro != null)
				return false;
		} else if (!idRubro.equals(other.idRubro))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "RubroDTO [idRubro=" + idRubro + "]";
	}
 
}