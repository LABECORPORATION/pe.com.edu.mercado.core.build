package pe.com.builderp.core.facturacion.model.dto.venta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO;


 

/**
 * La Class VehiculoDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Thu May 02 10:20:30 COT 2019
 * @since SIAA-CORE 2.1
 */
public class VehiculoDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id vehiculo. */
    private String idVehiculo;
   
    /** El clase. */
    private String clase;
   
    /** El nro placa. */
    private String nroPlaca;
   
    /** El nro ruedas. */
    private String nroRuedas;
   
    /** El descripcion. */
    private String descripcion;
   
    /** El id modelo. */
    private ModeloDTO modelo;
    
    private String estado;
    
    /** La foto. */
   
    private String foto;
    
    
    private BigDecimal nrocilindro;
    
    
    private BigDecimal nroejes;

    
    private String anhofabricacion;
    
    
    private String serie;

    
    private String categoria;

    private ItemDTO itemByColor;
    
 
    private String carroceria;
    
   
    private String combustible;
  
 
    private BigDecimal nroasiento;  
   
    /** El vehiculo guia remision list. */
    private List<GuiaRemisionDTO> vehiculoGuiaRemisionList = new ArrayList<GuiaRemisionDTO>();
   
    /**
     * Instancia un nuevo vehiculoDTO.
     */
    public VehiculoDTO() {
    }
   
    /**
     * Instancia un nuevo vehiculoDTO.
     *
     * @param idVehiculo el id vehiculo
     * @param clase el clase
     * @param nroPlaca el nro placa
     * @param nroRuedas el nro ruedas
     * @param descripcion el descripcion
     * @param idModelo el id modelo
     */
    public VehiculoDTO(String foto,String estado,String idVehiculo, String clase, 
    		BigDecimal nrocilindro, BigDecimal nroejes,String serie,String anhofabricacion,String categoria,ItemDTO itemByColor,String carroceria,String combustible,BigDecimal nroasiento,String nroPlaca, String nroRuedas, String descripcion, ModeloDTO modelo) {
        super();
        this.idVehiculo = idVehiculo;
        this.clase = clase;
        this.nroPlaca = nroPlaca;
        this.nroRuedas = nroRuedas;
        this.descripcion = descripcion;
        this.modelo = modelo;
        this.estado = estado;
        this.foto = foto;
        this.nrocilindro = nrocilindro;
        this.nroejes = nroejes;
        this.serie = serie;
        this.anhofabricacion = anhofabricacion;
        this.categoria = categoria;
        this.itemByColor = itemByColor;
        this.carroceria = carroceria;
        this.combustible = combustible;
        this.nroasiento = nroasiento;
    }
   
    //get y set
    /**
     * Obtiene id vehiculo.
     *
     * @return id vehiculo
     */
     public String getIdVehiculo() {
        return this.idVehiculo;
    }
    /**
     * Establece el id vehiculo.
     *
     * @param idVehiculo el new id vehiculo
     */
    public void setIdVehiculo(String idVehiculo) {
        this.idVehiculo = idVehiculo;
    }
    /**
     * Obtiene clase.
     *
     * @return clase
     */
     public String getClase() {
        return this.clase;
    }
    /**
     * Establece el clase.
     *
     * @param clase el new clase
     */
    public void setClase(String clase) {
        this.clase = clase;
    }
    
    public BigDecimal getNrocilindro() {
		return nrocilindro;
	}

	public void setNrocilindro(BigDecimal nrocilindro) {
		this.nrocilindro = nrocilindro;
	}

	public BigDecimal getNroejes() {
		return nroejes;
	}

	public void setNroejes(BigDecimal nroejes) {
		this.nroejes = nroejes;
	}

	public String getAnhofabricacion() {
		return anhofabricacion;
	}

	public void setAnhofabricacion(String anhofabricacion) {
		this.anhofabricacion = anhofabricacion;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public ItemDTO getItemByColor() {
		return itemByColor;
	}

	public void setItemByColor(ItemDTO itemByColor) {
		this.itemByColor = itemByColor;
	}

	public String getCarroceria() {
		return carroceria;
	}

	public void setCarroceria(String carroceria) {
		this.carroceria = carroceria;
	}

	public String getCombustible() {
		return combustible;
	}

	public void setCombustible(String combustible) {
		this.combustible = combustible;
	}

	public BigDecimal getNroasiento() {
		return nroasiento;
	}

	public void setNroasiento(BigDecimal nroasiento) {
		this.nroasiento = nroasiento;
	}

	/**
     * Obtiene nro placa.
     *
     * @return nro placa
     */
     public String getNroPlaca() {
        return this.nroPlaca;
    }
    /**
     * Establece el nro placa.
     *
     * @param nroPlaca el new nro placa
     */
    public void setNroPlaca(String nroPlaca) {
        this.nroPlaca = nroPlaca;
    }
    
    
    public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	/**
     * Obtiene nro ruedas.
     *
     * @return nro ruedas
     */
     public String getNroRuedas() {
        return this.nroRuedas;
    }
    /**
     * Establece el nro ruedas.
     *
     * @param nroRuedas el new nro ruedas
     */
    public void setNroRuedas(String nroRuedas) {
        this.nroRuedas = nroRuedas;
    }
    /**
     * Obtiene descripcion.
     *
     * @return descripcion
     */
     public String getDescripcion() {
        return this.descripcion;
    }
    /**
     * Establece el descripcion.
     *
     * @param descripcion el new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * Obtiene id modelo.
     *
     * @return id modelo
     */
     public ModeloDTO getModelo() {
        return this.modelo;
    }
    /**
     * Establece el id modelo.
     *
     * @param idModelo el new id modelo
     */
    public void setModelo(ModeloDTO modelo) {
        this.modelo = modelo;
    }
    /**
     * Establece el vehiculo guia remision list.
     *
     * @param vehiculoGuiaRemisionList el new vehiculo guia remision list
     */
    public List<GuiaRemisionDTO> getVehiculoGuiaRemisionList(){
        return this.vehiculoGuiaRemisionList;
    }
    /**
     * Establece el vehiculo list.
     *
     * @param vehiculoList el new vehiculo list
     */
    public void setVehiculoGuiaRemisionList(List<GuiaRemisionDTO> vehiculoGuiaRemisionList) {
        this.vehiculoGuiaRemisionList = vehiculoGuiaRemisionList;
    }   
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idVehiculo == null) ? 0 : idVehiculo.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        VehiculoDTO other = (VehiculoDTO) obj;
        if (idVehiculo == null) {
            if (other.idVehiculo != null) {
                return false;
            }
        } else if (!idVehiculo.equals(other.idVehiculo)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "VehiculoDTO [idVehiculo=" + idVehiculo + "]";
    }
   
}