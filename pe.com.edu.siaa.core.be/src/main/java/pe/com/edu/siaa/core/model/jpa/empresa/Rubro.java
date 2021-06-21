package pe.com.edu.siaa.core.model.jpa.empresa;

import java.io.Serializable; 

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
@Table(name = "Rubro", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_EMPRESA)
public class Rubro implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id agrupa entidad. */
    @Id
    @Column(name = "idRubro" , length = 32)
    private String idRubro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSector", referencedColumnName = "idSector")
    private Sector sector;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idItemRubro", referencedColumnName = "idItem")
    private Item itemRubro;
    
    /** El nombre. */
    @Column(name = "descripcionOtro" , length = 200)
    private String descripcionOtro;
    
   
   
    /**
     * Instancia un nuevo agrupa entidad.2wq
     */
    public Rubro() {
    }


 



	public Rubro(String idRubro, Sector sector, Item itemRubro, String descripcionOtro) {
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






	public Sector getSector() {
		return sector;
	}






	public void setSector(Sector sector) {
		this.sector = sector;
	}






	public Item getItemRubro() {
		return itemRubro;
	}






	public void setItemRubro(Item itemRubro) {
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
		Rubro other = (Rubro) obj;
		if (idRubro == null) {
			if (other.idRubro != null)
				return false;
		} else if (!idRubro.equals(other.idRubro))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Rubro [idRubro=" + idRubro + "]";
	}

}