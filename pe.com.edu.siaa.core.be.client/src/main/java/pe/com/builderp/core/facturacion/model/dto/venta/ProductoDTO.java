package pe.com.builderp.core.facturacion.model.dto.venta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO;
import pe.com.edu.siaa.core.model.dto.contabilidad.PlanContableDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.EntidadDTO;

/**
 * La Class ProductoDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Fri Dec 22 11:07:03 COT 2017
 * @since SIAA-CORE 2.1
 */
public class ProductoDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id producto. */
    private Long idProducto;
   
    /** El categoria. */
    private CategoriaDTO categoria;
   
    /** El item by unidad medida. */
    private ItemDTO itemByUnidadMedida;
   
    /** El modelo. */
    private ModeloDTO modelo;
   
    /** El item by color. */
    private ItemDTO itemByColor;
    
    /** El item by talla. */
    private ItemDTO itemByTalla;
   
    /** El nombre. */
    private String nombre;
   
    /** El descripcion. */
    private String descripcion;
   
    /** El codigo. */
    private String codigo;
   
    /** El codigo equivalente. */
    private String codigoEquivalente;
   
    /** El codigo externo. */
    private String codigoExterno;
   
    /** El codigo referencia. */
    private String codigoReferencia;
   
    /** El precio. */
    private BigDecimal precio;
   
    /** El stock. */
    private BigDecimal stock;
   
    /** El fecha vencimiento. */
    private Date fechaVencimiento;
   
    /** El version. */
    private String version;
   
    /** El anho. */
    private String anho;
   
    /** El nro serie. */
    private String nroSerie;
   
    /** El tipo. */
    private String tipo;
   
    /** El plan contable venta. */
    private PlanContableDTO planContableVenta;
   
    /** El plan contable compra. */
    private PlanContableDTO planContableCompra;
   
    /** El es afecto i g v. */
    private String esAfectoIGV;
   
    /** El entidad. */
    private EntidadDTO entidad;
   
    /** El estado. */
    private String estado;
    
    private String foto;
    
    private String estadoOferta;
    
    private BigDecimal precioCompra;

    private Date fechaVigenciaOferta;
    
    private BigDecimal precioOferta;
    
    private BigDecimal precioPorMayor;
    
    private BigDecimal descuentoMinimo;
    
    //trasient   
    private Integer cantidad = 1;
    
    private Integer descuentoPro = 0;
   
    private BigDecimal precioCompraUnitario;
    
    private BigDecimal stockMinimo;
   
    private BigDecimal utilidadPorProducto;
    
    private List<DetalleProductoDTO> detalleProductoList = new ArrayList<DetalleProductoDTO>();
    
    private List<ProductoProveedorDTO>productoProveedorList = new ArrayList<ProductoProveedorDTO>();
    /**
     * Instancia un nuevo productoDTO.
     */
    public ProductoDTO() {
    }
   
   
    /**
     * Instancia un nuevo productoDTO.
     *
     * @param idProducto el id producto
     * @param categoria el categoria
     * @param itemByUnidadMedida el item by unidad medida
     * @param modelo el modelo
     * @param itemByColor el item by color
     * @param nombre el nombre
     * @param descripcion el descripcion
     * @param codigo el codigo
     * @param codigoEquivalente el codigo equivalente
     * @param codigoExterno el codigo externo
     * @param codigoReferencia el codigo referencia
     * @param precio el precio
     * @param stock el stock
     * @param fechaVencimiento el fecha vencimiento
     * @param version el version
     * @param anho el anho
     * @param nroSerie el nro serie
     * @param tipo el tipo
     * @param planContableVenta el plan contable venta
     * @param planContableCompra el plan contable compra
     * @param esAfectoIGV el es afecto i g v
     * @param entidad el entidad
     * @param estado el estado
     */
    public ProductoDTO(BigDecimal precioOferta, Date fechaVigenciaOferta, String estadoOferta,BigDecimal precioPorMayor,BigDecimal descuentoMinimo,String foto,BigDecimal utilidadPorProducto, BigDecimal stockMinimo,BigDecimal precioCompraUnitario, Long idProducto, CategoriaDTO categoria,ItemDTO itemByUnidadMedida,ModeloDTO modelo,ItemDTO itemByColor,String nombre, String descripcion, String codigo, String codigoEquivalente, String codigoExterno, String codigoReferencia, BigDecimal precio, BigDecimal stock, Date fechaVencimiento, String version, String anho, String nroSerie, String tipo, PlanContableDTO planContableVenta,PlanContableDTO planContableCompra,String esAfectoIGV, EntidadDTO entidad,String estado,BigDecimal precioCompra ) {
        super();
        this.idProducto = idProducto;
        this.categoria = categoria;
        this.itemByUnidadMedida = itemByUnidadMedida;
        this.modelo = modelo;
        this.itemByColor = itemByColor;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.codigoEquivalente = codigoEquivalente;
        this.codigoExterno = codigoExterno;
        this.codigoReferencia = codigoReferencia;
        this.precio = precio;
        this.stock = stock;
        this.fechaVencimiento = fechaVencimiento;
        this.version = version;
        this.anho = anho;
        this.nroSerie = nroSerie;
        this.tipo = tipo;
        this.planContableVenta = planContableVenta;
        this.planContableCompra = planContableCompra;
        this.esAfectoIGV = esAfectoIGV;
        this.entidad = entidad;
        this.estado = estado;
        this.precioCompraUnitario = precioCompraUnitario;
        this.stockMinimo = stockMinimo;
        this.utilidadPorProducto = utilidadPorProducto;
        this.foto = foto;
        this.precioPorMayor = precioPorMayor;
        this.descuentoMinimo = descuentoMinimo;
        this.estadoOferta = estadoOferta;
        this.fechaVigenciaOferta = fechaVigenciaOferta;
        this.precioOferta = precioOferta;
        this.precioCompra = precioCompra;
    }
   
    //get y set
    
    
    
	public List<ProductoProveedorDTO> getProductoProveedorList() {
		return productoProveedorList;
	}


	public void setProductoProveedorList(List<ProductoProveedorDTO> productoProveedorList) {
		this.productoProveedorList = productoProveedorList;
	}
    
    /**
     * Obtiene id producto.
     *
     * @return id producto
     */
     public Long getIdProducto() {
        return this.idProducto;
    }
    public BigDecimal getStockMinimo() {
		return stockMinimo;
	}


	public void setStockMinimo(BigDecimal stockMinimo) {
		this.stockMinimo = stockMinimo;
	}


	public BigDecimal getUtilidadPorProducto() {
		return utilidadPorProducto;
	}


	public void setUtilidadPorProducto(BigDecimal utilidadPorProducto) {
		this.utilidadPorProducto = utilidadPorProducto;
	}


	public BigDecimal getPrecioCompraUnitario() {
		return precioCompraUnitario;
	}


	public void setPrecioCompraUnitario(BigDecimal precioCompraUnitario) {
		this.precioCompraUnitario = precioCompraUnitario;
	}


	/**
     * Establece el id producto.
     *
     * @param idProducto el new id producto
     */
    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }
    /**
     * Obtiene categoria.
     *
     * @return categoria
     */
     public CategoriaDTO getCategoria() {
        return this.categoria;
    }
     
     
    public String getFoto() {
		return foto;
	}


	public void setFoto(String foto) {
		this.foto = foto;
	}

	
	
	public String getEstadoOferta() {
		return estadoOferta;
	}


	public void setEstadoOferta(String estadoOferta) {
		this.estadoOferta = estadoOferta;
	}


	public Date getFechaVigenciaOferta() {
		return fechaVigenciaOferta;
	}


	public void setFechaVigenciaOferta(Date fechaVigenciaOferta) {
		this.fechaVigenciaOferta = fechaVigenciaOferta;
	}


	public BigDecimal getPrecioOferta() {
		return precioOferta;
	}


	public void setPrecioOferta(BigDecimal precioOferta) {
		this.precioOferta = precioOferta;
	}


	public BigDecimal getPrecioPorMayor() {
		return precioPorMayor;
	}


	public void setPrecioPorMayor(BigDecimal precioPorMayor) {
		this.precioPorMayor = precioPorMayor;
	}


	public BigDecimal getDescuentoMinimo() {
		return descuentoMinimo;
	}


	public void setDescuentoMinimo(BigDecimal descuentoMinimo) {
		this.descuentoMinimo = descuentoMinimo;
	}


	/**
     * Establece el categoria.
     *
     * @param categoria el new categoria
     */
    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }
    /**
     * Obtiene item by unidad medida.
     *
     * @return item by unidad medida
     */
     public ItemDTO getItemByUnidadMedida() {
        return this.itemByUnidadMedida;
    }
    /**
     * Establece el item by unidad medida.
     *
     * @param itemByUnidadMedida el new item by unidad medida
     */
    public void setItemByUnidadMedida(ItemDTO itemByUnidadMedida) {
        this.itemByUnidadMedida = itemByUnidadMedida;
    }
    /**
     * Obtiene modelo.
     *
     * @return modelo
     */
     public ModeloDTO getModelo() {
        return this.modelo;
    }
    /**
     * Establece el modelo.
     *
     * @param modelo el new modelo
     */
    public void setModelo(ModeloDTO modelo) {
        this.modelo = modelo;
    }
    /**
     * Obtiene item by color.
     *
     * @return item by color
     */
     public ItemDTO getItemByColor() {
        return this.itemByColor;
    }
    /**
     * Establece el item by color.
     *
     * @param itemByColor el new item by color
     */
    public void setItemByColor(ItemDTO itemByColor) {
        this.itemByColor = itemByColor;
    }
    /**
     * Obtiene item by talla.
     *
     * @return item by talla
     */
     public ItemDTO getItemByTalla() {
        return this.itemByTalla;
    }
    /**
     * Establece el item by talla.
     *
     * @param itemByTalla el new item by talla
     */
    public void setItemByTalla(ItemDTO itemByTalla) {
        this.itemByTalla = itemByTalla;
    }
    /**
     * Obtiene nombre.
     *
     * @return nombre
     */
     public String getNombre() {
        return this.nombre;
    }
    /**
     * Establece el nombre.
     *
     * @param nombre el new nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
     * Obtiene codigo.
     *
     * @return codigo
     */
     public String getCodigo() {
        return this.codigo;
    }
    /**
     * Establece el codigo.
     *
     * @param codigo el new codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    /**
     * Obtiene codigo equivalente.
     *
     * @return codigo equivalente
     */
     public String getCodigoEquivalente() {
        return this.codigoEquivalente;
    }
    /**
     * Establece el codigo equivalente.
     *
     * @param codigoEquivalente el new codigo equivalente
     */
    public void setCodigoEquivalente(String codigoEquivalente) {
        this.codigoEquivalente = codigoEquivalente;
    }
    /**
     * Obtiene codigo externo.
     *
     * @return codigo externo
     */
     public String getCodigoExterno() {
        return this.codigoExterno;
    }
    /**
     * Establece el codigo externo.
     *
     * @param codigoExterno el new codigo externo
     */
    public void setCodigoExterno(String codigoExterno) {
        this.codigoExterno = codigoExterno;
    }
    /**
     * Obtiene codigo referencia.
     *
     * @return codigo referencia
     */
     public String getCodigoReferencia() {
        return this.codigoReferencia;
    }
    /**
     * Establece el codigo referencia.
     *
     * @param codigoReferencia el new codigo referencia
     */
    public void setCodigoReferencia(String codigoReferencia) {
        this.codigoReferencia = codigoReferencia;
    }
    /**
     * Obtiene precio.
     *
     * @return precio
     */
     public BigDecimal getPrecio() {
        return this.precio;
    }
    /**
     * Establece el precio.
     *
     * @param precio el new precio
     */
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    /**
     * Obtiene stock.
     *
     * @return stock
     */
     public BigDecimal getStock() {
        return this.stock;
    }
    /**
     * Establece el stock.
     *
     * @param stock el new stock
     */
    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }
    /**
     * Obtiene fecha vencimiento.
     *
     * @return fecha vencimiento
     */
     public Date getFechaVencimiento() {
        return this.fechaVencimiento;
    }
    /**
     * Establece el fecha vencimiento.
     *
     * @param fechaVencimiento el new fecha vencimiento
     */
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
    /**
     * Obtiene version.
     *
     * @return version
     */
     public String getVersion() {
        return this.version;
    }
    /**
     * Establece el version.
     *
     * @param version el new version
     */
    public void setVersion(String version) {
        this.version = version;
    }
    /**
     * Obtiene anho.
     *
     * @return anho
     */
     public String getAnho() {
        return this.anho;
    }
    /**
     * Establece el anho.
     *
     * @param anho el new anho
     */
    public void setAnho(String anho) {
        this.anho = anho;
    }
    /**
     * Obtiene nro serie.
     *
     * @return nro serie
     */
     public String getNroSerie() {
        return this.nroSerie;
    }
    /**
     * Establece el nro serie.
     *
     * @param nroSerie el new nro serie
     */
    public void setNroSerie(String nroSerie) {
        this.nroSerie = nroSerie;
    }
    /**
     * Obtiene tipo.
     *
     * @return tipo
     */
     public String getTipo() {
        return this.tipo;
    }
    /**
     * Establece el tipo.
     *
     * @param tipo el new tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    /**
     * Obtiene plan contable venta.
     *
     * @return plan contable venta
     */
     public PlanContableDTO getPlanContableVenta() {
        return this.planContableVenta;
    }
    /**
     * Establece el plan contable venta.
     *
     * @param planContableVenta el new plan contable venta
     */
    public void setPlanContableVenta(PlanContableDTO planContableVenta) {
        this.planContableVenta = planContableVenta;
    }
    /**
     * Obtiene plan contable compra.
     *
     * @return plan contable compra
     */
     public PlanContableDTO getPlanContableCompra() {
        return this.planContableCompra;
    }
     
     
    public Integer getDescuentoPro() {
		return descuentoPro;
	}


	public void setDescuentoPro(Integer descuentoPro) {
		this.descuentoPro = descuentoPro;
	}

	public List<DetalleProductoDTO> getDetalleProductoList() {
		return detalleProductoList;
	}


	public void setDetalleProductoList(List<DetalleProductoDTO> detalleProductoList) {
		this.detalleProductoList = detalleProductoList;
	}

	/**
     * Establece el plan contable compra.
     *
     * @param planContableCompra el new plan contable compra
     */
    public void setPlanContableCompra(PlanContableDTO planContableCompra) {
        this.planContableCompra = planContableCompra;
    }
    /**
     * Obtiene es afecto i g v.
     *
     * @return es afecto i g v
     */
     public String getEsAfectoIGV() {
        return this.esAfectoIGV;
    }
    /**
     * Establece el es afecto i g v.
     *
     * @param esAfectoIGV el new es afecto i g v
     */
    public void setEsAfectoIGV(String esAfectoIGV) {
        this.esAfectoIGV = esAfectoIGV;
    }
    /**
     * Obtiene entidad.
     *
     * @return entidad
     */
     public EntidadDTO getEntidad() {
        return this.entidad;
    }
    /**
     * Establece el entidad.
     *
     * @param entidad el new entidad
     */
    public void setEntidad(EntidadDTO entidad) {
        this.entidad = entidad;
    }
    /**
     * Obtiene estado.
     *
     * @return estado
     */
     public String getEstado() {
        return this.estado;
    }
    /**
     * Establece el estado.
     *
     * @param estado el new estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public BigDecimal getPrecioCompra() {
		return precioCompra;
	}


	public void setPrecioCompra(BigDecimal precioCompra) {
		this.precioCompra = precioCompra;
	}
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idProducto == null) ? 0 : idProducto.hashCode());
        return result;
    }

    public Integer getCantidad() {
		return cantidad;
	}


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
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
        ProductoDTO other = (ProductoDTO) obj;
        if (idProducto == null) {
            if (other.idProducto != null) {
                return false;
            }
        } else if (!idProducto.equals(other.idProducto)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ProductoDTO [idProducto=" + idProducto + "]";
    }
   
}