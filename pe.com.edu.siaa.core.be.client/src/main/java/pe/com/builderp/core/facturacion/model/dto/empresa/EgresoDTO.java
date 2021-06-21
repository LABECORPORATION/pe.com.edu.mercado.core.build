package pe.com.builderp.core.facturacion.model.dto.empresa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import pe.com.builderp.core.facturacion.model.dto.compra.CuentaBancariaDTO;
import pe.com.builderp.core.facturacion.model.dto.compra.ProveedorDTO; 
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
public class EgresoDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    private String idEgreso;
   
    private CategoriaByEmpresaDTO categoriaByEmpresa;
        
    private ItemDTO modoPago;
    
    private CuentaBancariaDTO cuentaBancaria;
    
    private PersonalDTO personal;
    
    private ProveedorDTO proveedor;
    
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
    public EgresoDTO() {
    }

	 
	
	
	public EgresoDTO(String idEgreso, CategoriaByEmpresaDTO categoriaByEmpresa, ItemDTO modoPago,
			CuentaBancariaDTO cuentaBancaria, PersonalDTO personal, ProveedorDTO proveedor, EntidadDTO entidad,
			BigDecimal monto, Date fecha, String esFacturado, String descripcion, String adjunto, ItemDTO tipoDocSunat,
			String nroDoc, String serie, String tipoEgreso) {
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

	public String getIdEgreso() {
		return idEgreso;
	}

	public void setIdEgreso(String idEgreso) {
		this.idEgreso = idEgreso;
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

	public PersonalDTO getPersonal() {
		return personal;
	}

	public void setPersonal(PersonalDTO personal) {
		this.personal = personal;
	}

	public ProveedorDTO getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorDTO proveedor) {
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
		EgresoDTO other = (EgresoDTO) obj;
		if (idEgreso == null) {
			if (other.idEgreso != null)
				return false;
		} else if (!idEgreso.equals(other.idEgreso))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EgresoDTO [idEgreso=" + idEgreso + "]";
	}
    
 }