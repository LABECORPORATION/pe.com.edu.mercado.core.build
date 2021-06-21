package pe.com.builderp.core.facturacion.model.jpa.venta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import pe.com.builderp.core.facturacion.model.jpa.compra.DetalleCompra;
import pe.com.builderp.core.facturacion.model.jpa.compra.DetalleOrdenCompra;
import pe.com.edu.siaa.core.model.jpa.common.Item;
import pe.com.edu.siaa.core.model.jpa.contabilidad.PlanContable;
import pe.com.edu.siaa.core.model.jpa.seguridad.Entidad;
import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class Producto.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Fri Dec 22 11:10:36 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "Producto", schema = ConfiguracionEntityManagerUtil.ESQUEMA_FACTURACION)
public class Producto implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id producto. */
    @Id
    @Column(name = "idProducto" , length = 18)
    private Long idProducto;
   
    /** El categoria. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCategoria", referencedColumnName = "idCategoria")
    private Categoria categoria;
   
    /** El item by unidad medida. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUnidadMedida", referencedColumnName = "idItem")
    private Item itemByUnidadMedida;
   
    /** El modelo. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idModelo", referencedColumnName = "idModelo")
    private Modelo modelo;
   
    /** El item by color. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idColor", referencedColumnName = "idItem")
    private Item itemByColor;
    

    /** El item by talla. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTalla", referencedColumnName = "idItem")
    private Item itemByTalla;
   
    /** El nombre. */
    @Column(name = "nombre" , length = 150)
    private String nombre;
   
    /** El descripcion. */
    @Column(name = "descripcion" , length = 200)
    private String descripcion;
   
    /** El codigo. */
    @Column(name = "codigo" , length = 50)
    private String codigo;
   
    /** El codigo equivalente. */
    @Column(name = "codigoEquivalente" , length = 50)
    private String codigoEquivalente;
   
    /** El codigo externo. */
    @Column(name = "codigoExterno" , length = 50)
    private String codigoExterno;
   
    /** El codigo referencia. */
    @Column(name = "codigoReferencia" , length = 50)
    private String codigoReferencia;
   
    /** El precio. */
    @Column(name = "precio" , precision = 18 , scale = 2)
    private BigDecimal precio;
   
    /** El stock. */
    @Column(name = "stock" , precision = 18 , scale = 2)
    private BigDecimal stock;
    
    /** El precio Por Mayor. */
    @Column(name = "precioPorMayor" , precision = 18 , scale = 2)
    private BigDecimal precioPorMayor;
    
    /** El descuento Minimo. */
    @Column(name = "descuentoMinimo" , precision = 18 , scale = 2)
    private BigDecimal descuentoMinimo;
   
    /** El fecha vencimiento. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaVencimiento")
    private Date fechaVencimiento;
   
    /** El version. */
    @Column(name = "version" , length = 50)
    private String version;
   
    /** El anho. */
    @Column(name = "anho" , length = 4)
    private String anho;
   
    /** El nro serie. */
    @Column(name = "nroSerie" , length = 50)
    private String nroSerie;
   
    /** El tipo. */
    @Column(name = "tipo" , length = 1)
    private String tipo;
   
    /** El plan contable venta. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPlanContableVenta", referencedColumnName = "idPlanContable")
    private PlanContable planContableVenta;
   
    /** El plan contable compra. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPlanContableCompra", referencedColumnName = "idPlanContable")
    private PlanContable planContableCompra;
   
    /** El es afecto i g v. */
    @Column(name = "esAfectoIGV" , length = 1)
    private String esAfectoIGV;
   
    /** El entidad. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEntidad", referencedColumnName = "idEntidad")
    private Entidad entidad;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
    
    /** El precio. */
    @Column(name = "precioCompraUnitario" , precision = 18 , scale = 2)
    private BigDecimal precioCompraUnitario;
    
    
    /** El stock minimo. */
    @Column(name = "stockMinimo" , precision = 18 , scale = 2)
    private BigDecimal stockMinimo;
    
    
    /** La utilidad por producto. */
    @Column(name = "utilidadPorProducto" , precision = 18 , scale = 2)
    private BigDecimal utilidadPorProducto;
    
    /** La foto. */
    @Column(name = "foto" , length = 50)
    private String foto;
    
    
    @Column(name = "estadoOferta" , length = 50)
    private String estadoOferta;
   
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaVigenciaOferta")
    private Date fechaVigenciaOferta;
    
    @Column(name = "precioOferta" , precision = 18 , scale = 2)
    private BigDecimal precioOferta;
    
    /** El precio. */
    @Column(name = "precioCompra" , precision = 18 , scale = 2)
    private BigDecimal precioCompra;
    
    
    
    /** El producto detalle proforma list. */
    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
    private List<DetalleProforma> productoDetalleProformaList = new ArrayList<DetalleProforma>();   
    
    /** El producto detalle orden compra list. */
    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
    private List<DetalleOrdenCompra> productoDetalleOrdenCompraList = new ArrayList<DetalleOrdenCompra>();
    
    /** El producto detalle compra list. */
    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
    private List<DetalleCompra> productoDetalleCompraList = new ArrayList<DetalleCompra>();
    
    /**
     * Instancia un nuevo producto.
     */
    public Producto() {
    }
   
   
    /**
     * Instancia un nuevo producto.
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
    public Producto(BigDecimal precioOferta, Date fechaVigenciaOferta, String estadoOferta, BigDecimal precioPorMayor,BigDecimal descuentoMinimo, String foto,BigDecimal utilidadPorProducto, BigDecimal stockMinimo,BigDecimal precioCompraUnitario, Long idProducto, Categoria categoria,Item itemByUnidadMedida,Modelo modelo,Item itemByColor,String nombre, String descripcion, String codigo, String codigoEquivalente, String codigoExterno, String codigoReferencia, BigDecimal precio, BigDecimal stock, Date fechaVencimiento, String version, String anho, String nroSerie, String tipo, PlanContable planContableVenta,PlanContable planContableCompra,String esAfectoIGV, Entidad entidad,String estado,
    		BigDecimal precioCompra ) {
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
     public Categoria getCategoria() {
        return this.categoria;
    }
    /**
     * Establece el categoria.
     *
     * @param categoria el new categoria
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    /**
     * Obtiene item by unidad medida.
     *
     * @return item by unidad medida
     */
     public Item getItemByUnidadMedida() {
        return this.itemByUnidadMedida;
    }
    /**
     * Establece el item by unidad medida.
     *
     * @param itemByUnidadMedida el new item by unidad medida
     */
    public void setItemByUnidadMedida(Item itemByUnidadMedida) {
        this.itemByUnidadMedida = itemByUnidadMedida;
    }
    /**
     * Obtiene modelo.
     *
     * @return modelo
     */
     public Modelo getModelo() {
        return this.modelo;
    }
    /**
     * Establece el modelo.
     *
     * @param modelo el new modelo
     */
    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
    /**
     * Obtiene item by color.
     *
     * @return item by color
     */
     public Item getItemByColor() {
        return this.itemByColor;
    }
    /**
     * Establece el item by color.
     *
     * @param itemByColor el new item by color
     */
    public void setItemByColor(Item itemByColor) {
        this.itemByColor = itemByColor;
    }
    /**
     * Obtiene item by talla.
     *
     * @return item by talla
     */
     public Item getItemByTalla() {
        return this.itemByTalla;
    }
    /**
     * Establece el item by talla.
     *
     * @param itemByTalla el new item by talla
     */
    public void setItemByTalla(Item itemByTalla) {
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
     public PlanContable getPlanContableVenta() {
        return this.planContableVenta;
    }
    /**
     * Establece el plan contable venta.
     *
     * @param planContableVenta el new plan contable venta
     */
    public void setPlanContableVenta(PlanContable planContableVenta) {
        this.planContableVenta = planContableVenta;
    }
    /**
     * Obtiene plan contable compra.
     *
     * @return plan contable compra
     */
     public PlanContable getPlanContableCompra() {
        return this.planContableCompra;
    }
    /**
     * Establece el plan contable compra.
     *
     * @param planContableCompra el new plan contable compra
     */
    public void setPlanContableCompra(PlanContable planContableCompra) {
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
     public Entidad getEntidad() {
        return this.entidad;
    }
    /**
     * Establece el entidad.
     *
     * @param entidad el new entidad
     */
    public void setEntidad(Entidad entidad) {
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
	
       
    /**
     * Establece el producto detalle proforma list.
     *
     * @param productoDetalleProformaList el new producto detalle proforma list
     */
    public List<DetalleProforma> getProductoDetalleProformaList(){
        return this.productoDetalleProformaList;
    }
    /**
     * Establece el producto list.
     *
     * @param productoList el new producto list
     */
    public void setProductoDetalleProformaList(List<DetalleProforma> productoDetalleProformaList) {
        this.productoDetalleProformaList = productoDetalleProformaList;
    }   
    
    /**
     * Establece el producto detalle orden compra list.
     *
     * @param productoDetalleOrdenCompraList el new producto detalle orden compra list
     */
    public List<DetalleOrdenCompra> getProductoDetalleOrdenCompraList(){
        return this.productoDetalleOrdenCompraList;
    }
    /**
     * Establece el producto list.
     *
     * @param productoList el new producto list
     */
    public void setProductoDetalleOrdenCompraList(List<DetalleOrdenCompra> productoDetalleOrdenCompraList) {
        this.productoDetalleOrdenCompraList = productoDetalleOrdenCompraList;
    }   
    /**
     * Establece el producto detalle compra list.
     *
     * @param productoDetalleCompraList el new producto detalle compra list
     */
    public List<DetalleCompra> getProductoDetalleCompraList(){
        return this.productoDetalleCompraList;
    }
    /**
     * Establece el producto list.
     *
     * @param productoList el new producto list
     */
    public void setProductoDetalleCompraList(List<DetalleCompra> productoDetalleCompraList) {
        this.productoDetalleCompraList = productoDetalleCompraList;
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
        Producto other = (Producto) obj;
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
        return "Producto [idProducto=" + idProducto + "]";
    }
   
}