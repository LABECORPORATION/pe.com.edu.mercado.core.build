package pe.com.builderp.core.facturacion.model.dto.empresa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List; 
import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO;
import pe.com.edu.siaa.core.model.dto.common.UbigeoDTO; 
 

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
public class AsociadoDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    private String idAsociado;
    
    private String nombre;
    
    private String apellidoPaterno;
    
    private String apellidoMaterno;
    
    private ItemDTO itemByEsatdoCivil;
    
    private Date fechaNacimiento;
    
    private String celular;
    
    private String nrodoc;
    
    private String ruc;
    
    private ItemDTO itemByTipoAsociado;
    
    private ItemDTO tipoDocumento;
    
    private UbigeoDTO ubigeoNacimiento;
    
    private UbigeoDTO ubigeoActual;
    
    private String direccion;
    
    private String direccionReferencia;
    
    private String condicionAsociado;
    
    private String asociadoDerecho;
    
    private ItemDTO itemByRubro;
    
    private String cuentaconPuesto;
    
    private Date fechaIncorporacion;
    
    private Date fechaTransfiere;
    
    private String voluntadAsociado;
    
    private String anotaciones;
    
    private String email;
   
    private String estado;
    
    private String foto;
    
    private ItemDTO itemGrado;
    
    private ItemDTO itemActividad;
   
    /** El compra detalle compra list. */
    private List<AsociadoFamiliaDTO> asociadoFamiliaDTOList = new ArrayList<AsociadoFamiliaDTO>();
    /**
     * Instancia un nuevo categoriaDTO.
     */
    public AsociadoDTO() {
    }


	public AsociadoDTO(String idAsociado, String nombre, String apellidoPaterno, String apellidoMaterno,
			ItemDTO itemByEsatdoCivil, Date fechaNacimiento, String celular, String nrodoc, String ruc,
			ItemDTO itemByTipoAsociado, ItemDTO tipoDocumento, UbigeoDTO ubigeoNacimiento, UbigeoDTO ubigeoActual,
			String direccion, String direccionReferencia, String condicionAsociado, String asociadoDerecho,
			ItemDTO itemByRubro, String cuentaconPuesto, Date fechaIncorporacion, Date fechaTransfiere,
			String voluntadAsociado, String anotaciones, String email, String estado, String foto,ItemDTO itemGrado,ItemDTO itemActividad) {
		super();
		this.idAsociado = idAsociado;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.itemByEsatdoCivil = itemByEsatdoCivil;
		this.fechaNacimiento = fechaNacimiento;
		this.celular = celular;
		this.nrodoc = nrodoc;
		this.ruc = ruc;
		this.itemByTipoAsociado = itemByTipoAsociado;
		this.tipoDocumento = tipoDocumento;
		this.ubigeoNacimiento = ubigeoNacimiento;
		this.ubigeoActual = ubigeoActual;
		this.direccion = direccion;
		this.direccionReferencia = direccionReferencia;
		this.condicionAsociado = condicionAsociado;
		this.asociadoDerecho = asociadoDerecho;
		this.itemByRubro = itemByRubro;
		this.cuentaconPuesto = cuentaconPuesto;
		this.fechaIncorporacion = fechaIncorporacion;
		this.fechaTransfiere = fechaTransfiere;
		this.voluntadAsociado = voluntadAsociado;
		this.anotaciones = anotaciones;
		this.email = email;
		this.estado = estado;
		this.foto = foto;
		this.itemGrado = itemGrado;
		this.itemActividad = itemActividad;
	}


	public ItemDTO getItemGrado() {
		return itemGrado;
	}


	public void setItemGrado(ItemDTO itemGrado) {
		this.itemGrado = itemGrado;
	}


	public ItemDTO getItemActividad() {
		return itemActividad;
	}


	public void setItemActividad(ItemDTO itemActividad) {
		this.itemActividad = itemActividad;
	}


	public List<AsociadoFamiliaDTO> getAsociadoFamiliaDTOList() {
		return asociadoFamiliaDTOList;
	}


	public void setAsociadoFamiliaDTOList(List<AsociadoFamiliaDTO> asociadoFamiliaDTOList) {
		this.asociadoFamiliaDTOList = asociadoFamiliaDTOList;
	}


	public String getIdAsociado() {
		return idAsociado;
	}


	public void setIdAsociado(String idAsociado) {
		this.idAsociado = idAsociado;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidoPaterno() {
		return apellidoPaterno;
	}


	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}


	public String getApellidoMaterno() {
		return apellidoMaterno;
	}


	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}


	public ItemDTO getItemByEsatdoCivil() {
		return itemByEsatdoCivil;
	}


	public void setItemByEsatdoCivil(ItemDTO itemByEsatdoCivil) {
		this.itemByEsatdoCivil = itemByEsatdoCivil;
	}


	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public String getCelular() {
		return celular;
	}


	public void setCelular(String celular) {
		this.celular = celular;
	}


	public String getNrodoc() {
		return nrodoc;
	}


	public void setNrodoc(String nrodoc) {
		this.nrodoc = nrodoc;
	}


	public String getRuc() {
		return ruc;
	}


	public void setRuc(String ruc) {
		this.ruc = ruc;
	}


	public ItemDTO getItemByTipoAsociado() {
		return itemByTipoAsociado;
	}


	public void setItemByTipoAsociado(ItemDTO itemByTipoAsociado) {
		this.itemByTipoAsociado = itemByTipoAsociado;
	}


	public ItemDTO getTipoDocumento() {
		return tipoDocumento;
	}


	public void setTipoDocumento(ItemDTO tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}


	public UbigeoDTO getUbigeoNacimiento() {
		return ubigeoNacimiento;
	}


	public void setUbigeoNacimiento(UbigeoDTO ubigeoNacimiento) {
		this.ubigeoNacimiento = ubigeoNacimiento;
	}


	public UbigeoDTO getUbigeoActual() {
		return ubigeoActual;
	}


	public void setUbigeoActual(UbigeoDTO ubigeoActual) {
		this.ubigeoActual = ubigeoActual;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getDireccionReferencia() {
		return direccionReferencia;
	}


	public void setDireccionReferencia(String direccionReferencia) {
		this.direccionReferencia = direccionReferencia;
	}


	public String getCondicionAsociado() {
		return condicionAsociado;
	}


	public void setCondicionAsociado(String condicionAsociado) {
		this.condicionAsociado = condicionAsociado;
	}


	public String getAsociadoDerecho() {
		return asociadoDerecho;
	}


	public void setAsociadoDerecho(String asociadoDerecho) {
		this.asociadoDerecho = asociadoDerecho;
	}


	public ItemDTO getItemByRubro() {
		return itemByRubro;
	}


	public void setItemByRubro(ItemDTO itemByRubro) {
		this.itemByRubro = itemByRubro;
	}


	public String getCuentaconPuesto() {
		return cuentaconPuesto;
	}


	public void setCuentaconPuesto(String cuentaconPuesto) {
		this.cuentaconPuesto = cuentaconPuesto;
	}


	public Date getFechaIncorporacion() {
		return fechaIncorporacion;
	}


	public void setFechaIncorporacion(Date fechaIncorporacion) {
		this.fechaIncorporacion = fechaIncorporacion;
	}


	public Date getFechaTransfiere() {
		return fechaTransfiere;
	}


	public void setFechaTransfiere(Date fechaTransfiere) {
		this.fechaTransfiere = fechaTransfiere;
	}


	public String getVoluntadAsociado() {
		return voluntadAsociado;
	}


	public void setVoluntadAsociado(String voluntadAsociado) {
		this.voluntadAsociado = voluntadAsociado;
	}


	public String getAnotaciones() {
		return anotaciones;
	}


	public void setAnotaciones(String anotaciones) {
		this.anotaciones = anotaciones;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAsociado == null) ? 0 : idAsociado.hashCode());
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
		AsociadoDTO other = (AsociadoDTO) obj;
		if (idAsociado == null) {
			if (other.idAsociado != null)
				return false;
		} else if (!idAsociado.equals(other.idAsociado))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "AsociadoDTO [idAsociado=" + idAsociado + "]";
	}

}