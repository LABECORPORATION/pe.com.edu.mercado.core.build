package pe.com.builderp.core.facturacion.model.jpa.venta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import pe.com.edu.siaa.core.model.jpa.common.Item;
import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class Vehiculo.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu May 02 09:26:23 COT 2019
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "Vehiculo", schema = ConfiguracionEntityManagerUtil.ESQUEMA_FACTURACION)
public class Vehiculo implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id vehiculo. */
    @Id
    @Column(name = "idVehiculo" , length = 32)
    private String idVehiculo;
   
    /** El clase. */
    @Column(name = "clase" , length = 100)
    private String clase;
   
    /** El nro placa. */
    @Column(name = "nroPlaca" , length = 50)
    private String nroPlaca;
   
    /** El nro ruedas. */
    @Column(name = "nroRuedas" , length = 50)
    private String nroRuedas;
   
    /** El descripcion. */
    @Column(name = "descripcion" , length = 100)
    private String descripcion;
   
    /** El id modelo. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idModelo", referencedColumnName = "idModelo")
    private Modelo modelo;
    
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
    
    /** La foto. */
    @Column(name = "foto" , length = 50)
    private String foto;
   
    /** El nrocilindro. */
    @Column(name = "nroCilindro" , precision = 18 , scale = 2)
    private BigDecimal nrocilindro;
    
    @Column(name = "nroEjes" , precision = 18 , scale = 2)
    private BigDecimal nroejes;

    @Column(name = "anhoFabricacion" , length = 50)
    private String anhofabricacion;
    
    @Column(name = "serie" , length = 50)
    private String serie;

    @Column(name = "categoria" , length = 100)
    private String categoria;

    /** El item by talla. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcolor", referencedColumnName = "idItem")
    private Item itemByColor;
    
    @Column(name = "carroceria" , length = 50)
    private String carroceria;
    
    @Column(name = "combustible" , length = 50)
    private String combustible;
  
    @Column(name = "nroAsiento" , precision = 18 , scale = 2)
    private BigDecimal nroasiento;  
    
    /** El vehiculo guia remision list. */
    @OneToMany(mappedBy = "vehiculo", fetch = FetchType.LAZY)
    private List<GuiaRemision> vehiculoGuiaRemisionList = new ArrayList<GuiaRemision>();
    
    /**
     * Instancia un nuevo vehiculo.
     */
    public Vehiculo() {
    }
   
   
    /**
     * Instancia un nuevo vehiculo.
     *
     * @param idVehiculo el id vehiculo
     * @param clase el clase
     * @param nroPlaca el nro placa
     * @param nroRuedas el nro ruedas
     * @param descripcion el descripcion
     * @param idModelo el id modelo
     */
    public Vehiculo(String foto,String estado,String idVehiculo, String clase, String nroPlaca,
    		BigDecimal nrocilindro, BigDecimal nroejes,String serie,String anhofabricacion,String categoria,Item itemByColor,String carroceria,String combustible,BigDecimal nroasiento,
    		String nroRuedas, String descripcion, Modelo modelo) {
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


	public Item getItemByColor() {
		return itemByColor;
	}


	public void setItemByColor(Item itemByColor) {
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
     public Modelo getModelo() {
        return this.modelo;
    }
    /**
     * Establece el id modelo.
     *
     * @param idModelo el new id modelo
     */
    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
    /**
     * Establece el vehiculo guia remision list.
     *
     * @param vehiculoGuiaRemisionList el new vehiculo guia remision list
     */
    public List<GuiaRemision> getVehiculoGuiaRemisionList(){
        return this.vehiculoGuiaRemisionList;
    }
    /**
     * Establece el vehiculo list.
     *
     * @param vehiculoList el new vehiculo list
     */
    public void setVehiculoGuiaRemisionList(List<GuiaRemision> vehiculoGuiaRemisionList) {
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
        Vehiculo other = (Vehiculo) obj;
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
        return "Vehiculo [idVehiculo=" + idVehiculo + "]";
    }
   
}