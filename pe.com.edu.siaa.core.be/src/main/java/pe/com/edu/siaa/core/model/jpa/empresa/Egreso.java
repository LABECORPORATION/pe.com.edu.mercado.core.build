package pe.com.edu.siaa.core.model.jpa.empresa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import pe.com.builderp.core.facturacion.model.jpa.compra.CuentaBancaria;
import pe.com.builderp.core.facturacion.model.jpa.compra.Proveedor; 
import pe.com.edu.siaa.core.model.jpa.common.Item;
import pe.com.edu.siaa.core.model.jpa.seguridad.Entidad;
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
@Table(name = "Egreso", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_EMPRESA)
public class Egreso implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id agrupa entidad. */
    @Id
    @Column(name = "idEgreso" , length = 32)
    private String idEgreso;

    
    /** El tipo. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCategoria", referencedColumnName = "idCategoria")
    private CategoriaByEmpresa categoriaByEmpresa;
    
    
    /** El idModoPago. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idModoPago", referencedColumnName = "idItem")
    private Item modoPago;
    
    /** El idCuentabancaria. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCuentabancaria", referencedColumnName = "idCuentaBancaria")
    private CuentaBancaria cuentaBancaria;

    /** El idCuentabancaria. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersonal", referencedColumnName = "idPersonal")
    private Personal personal;
    
    
    /** El idCuentabancaria. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProveedor", referencedColumnName = "idProveedor")
    private Proveedor proveedor;
    
    /** El idCuentabancaria. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEntidad", referencedColumnName = "idEntidad")
    private Entidad entidad;

    /** El codigo. */
    @Column(name = "monto" , precision = 18 , scale = 2)
    private BigDecimal monto;
   
    
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fecha")
    private Date fecha;
     
    /** El nroCuenta. */
    @Column(name = "esFacturado" , length = 1)
    private String esFacturado;
    
    /** El nroCuentaCII. */
    @Column(name = "descripcion" , length = 200)
    private String descripcion;
    
  
    /** El estado. */
    @Column(name = "adjunto" , length = 20)
    private String adjunto;
    
    /** El tipo doc sunat. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoDocSunat", referencedColumnName = "idItem")
    private Item tipoDocSunat;
    
    /** El nro doc. */
    @Column(name = "nroDoc" , length = 50)
    private String nroDoc;
    
    /** El serie. */
    @Column(name = "serie" , length = 4)
    private String serie;
    
    
    /** El nro doc. */
    @Column(name = "tipoEgreso" , length = 50)
    private String tipoEgreso;
   
    /**
     * Instancia un nuevo agrupa entidad.
     */
    public Egreso() {
    }

	 
	
	

	public Egreso(String idEgreso, CategoriaByEmpresa categoriaByEmpresa, Item modoPago, CuentaBancaria cuentaBancaria,
			Personal personal, Proveedor proveedor, Entidad entidad, BigDecimal monto, Date fecha, String esFacturado,
			String descripcion, String adjunto, Item tipoDocSunat, String nroDoc, String serie, String tipoEgreso) {
		super();
		this.idEgreso = idEgreso;
		this.categoriaByEmpresa = categoriaByEmpresa;
		this.modoPago = modoPago;
		this.cuentaBancaria = cuentaBancaria;
		this.personal = personal;
		this.proveedor = proveedor;
		this.entidad = entidad;
		this.monto = monto;
		this.fecha = fecha;
		this.esFacturado = esFacturado;
		this.descripcion = descripcion;
		this.adjunto = adjunto;
		this.tipoDocSunat = tipoDocSunat;
		this.nroDoc = nroDoc;
		this.serie = serie;
		this.tipoEgreso = tipoEgreso;
	}





	public Item getTipoDocSunat() {
		return tipoDocSunat;
	}





	public void setTipoDocSunat(Item tipoDocSunat) {
		this.tipoDocSunat = tipoDocSunat;
	}





	public String getNroDoc() {
		return nroDoc;
	}





	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}





	public String getSerie() {
		return serie;
	}





	public void setSerie(String serie) {
		this.serie = serie;
	}





	public String getTipoEgreso() {
		return tipoEgreso;
	}





	public void setTipoEgreso(String tipoEgreso) {
		this.tipoEgreso = tipoEgreso;
	}





	public Entidad getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	public String getIdEgreso() {
		return idEgreso;
	}

	public void setIdEgreso(String idEgreso) {
		this.idEgreso = idEgreso;
	}

	public CategoriaByEmpresa getCategoriaByEmpresa() {
		return categoriaByEmpresa;
	}

	public void setCategoriaByEmpresa(CategoriaByEmpresa categoriaByEmpresa) {
		this.categoriaByEmpresa = categoriaByEmpresa;
	}

	public Item getModoPago() {
		return modoPago;
	}

	public void setModoPago(Item modoPago) {
		this.modoPago = modoPago;
	}

	public CuentaBancaria getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEsFacturado() {
		return esFacturado;
	}

	public void setEsFacturado(String esFacturado) {
		this.esFacturado = esFacturado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAdjunto() {
		return adjunto;
	}

	public void setAdjunto(String adjunto) {
		this.adjunto = adjunto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEgreso == null) ? 0 : idEgreso.hashCode());
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
		Egreso other = (Egreso) obj;
		if (idEgreso == null) {
			if (other.idEgreso != null)
				return false;
		} else if (!idEgreso.equals(other.idEgreso))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Egreso [idEgreso=" + idEgreso + "]";
	}
 
    
}