package pe.com.builderp.core.facturacion.model.dto.empresa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import pe.com.builderp.core.facturacion.model.dto.compra.CuentaBancariaDTO; 
import pe.com.builderp.core.facturacion.model.dto.venta.ClienteDTO;
import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.EntidadDTO; 
 

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
public class IngresoDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    private String idIngreso;
   
    private CategoriaByEmpresaDTO categoriaByEmpresa;
        
    private ItemDTO modoPago;
    
    private CuentaBancariaDTO cuentaBancaria;
    
    private ClienteDTO cliente; 
    
    private EntidadDTO entidad;
    
    private BigDecimal monto;
    
    private Date fecha;
    
    private String esFacturado;
    
    private String descripcion;
    
    private String adjunto; 
    
    private ItemDTO tipoDocSunat;
    
    private String nroDoc;
    
    private String serie;
    
    private String tipoEgreso;
   
    /**
     * Instancia un nuevo categoriaDTO.
     */
    public IngresoDTO() {
    }

	public IngresoDTO(String idIngreso, CategoriaByEmpresaDTO categoriaByEmpresa, ItemDTO modoPago,
			CuentaBancariaDTO cuentaBancaria, ClienteDTO cliente, BigDecimal monto, Date fecha, String esFacturado,
			String descripcion, String adjunto,EntidadDTO entidad, ItemDTO tipoDocSunat,
			String nroDoc, String serie, String tipoEgreso) {
		super();
		this.idIngreso = idIngreso;
		this.categoriaByEmpresa = categoriaByEmpresa;
		this.modoPago = modoPago;
		this.cuentaBancaria = cuentaBancaria;
		this.cliente = cliente;
		this.monto = monto;
		this.fecha = fecha;
		this.esFacturado = esFacturado;
		this.descripcion = descripcion;
		this.adjunto = adjunto;
		this.entidad=entidad;
		this.tipoDocSunat = tipoDocSunat;
		this.nroDoc = nroDoc;
		this.serie = serie;
		this.tipoEgreso = tipoEgreso;
	}
	
	
	public ItemDTO getTipoDocSunat() {
		return tipoDocSunat;
	}

	public void setTipoDocSunat(ItemDTO tipoDocSunat) {
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

	public EntidadDTO getEntidad() {
		return entidad;
	}

	public void setEntidad(EntidadDTO entidad) {
		this.entidad = entidad;
	}

	public String getIdIngreso() {
		return idIngreso;
	}

	public void setIdIngreso(String idIngreso) {
		this.idIngreso = idIngreso;
	}

 

	public CategoriaByEmpresaDTO getCategoriaByEmpresa() {
		return categoriaByEmpresa;
	}

	public void setCategoriaByEmpresa(CategoriaByEmpresaDTO categoriaByEmpresa) {
		this.categoriaByEmpresa = categoriaByEmpresa;
	}

	public ItemDTO getModoPago() {
		return modoPago;
	}

	public void setModoPago(ItemDTO modoPago) {
		this.modoPago = modoPago;
	}

	public CuentaBancariaDTO getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(CuentaBancariaDTO cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
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
		result = prime * result + ((idIngreso == null) ? 0 : idIngreso.hashCode());
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
		IngresoDTO other = (IngresoDTO) obj;
		if (idIngreso == null) {
			if (other.idIngreso != null)
				return false;
		} else if (!idIngreso.equals(other.idIngreso))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IngresoDTO [idIngreso=" + idIngreso + "]";
	}

	
 }