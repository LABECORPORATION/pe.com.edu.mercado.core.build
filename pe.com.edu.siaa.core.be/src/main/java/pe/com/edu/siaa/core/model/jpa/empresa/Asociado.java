package pe.com.edu.siaa.core.model.jpa.empresa;

import java.io.Serializable;
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

import pe.com.edu.siaa.core.model.jpa.common.Item;
import pe.com.edu.siaa.core.model.jpa.common.Ubigeo;
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
@Table(name = "Asociado", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_EMPRESA)
public class Asociado implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id agrupa entidad. */
    @Id
    @Column(name = "idAsociado" , length = 32)
    private String idAsociado;

    /** El nombre. */
    @Column(name = "nombre" , length = 50)
    private String nombre;
    
    /** El apellidoPaterno. */
    @Column(name = "apellidoPaterno" , length = 50)
    private String apellidoPaterno;
    
    /** El apellidoPaterno. */
    @Column(name = "apellidoMaterno" , length = 50)
    private String apellidoMaterno;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEstadoCivil", referencedColumnName = "idItem")
    private Item itemByEsatdoCivil;
    
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaNacimiento")
    private Date fechaNacimiento;
    
    /** El nombre. */
    @Column(name = "celular" , length = 10)
    private String celular;
    
    /** El nroCuentaCII. */
    @Column(name = "nrodoc" , length = 10)
    private String nrodoc;
    
    /** El nroCuentaCII. */
    @Column(name = "ruc" , length = 10)
    private String ruc;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoAsociado", referencedColumnName = "idItem")
    private Item itemByTipoAsociado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoDocumento", referencedColumnName = "idItem")
    private Item tipoDocumento; 
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUbigeoNacimiento", referencedColumnName = "idUbigeo")
    private Ubigeo ubigeoNacimiento; 
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUbigeoActual", referencedColumnName = "idUbigeo")
    private Ubigeo ubigeoActual; 

    /** El nroCuentaCII. */
    @Column(name = "direccion" , length = 200)
    private String direccion;
    
    /** El nroCuentaCII. */
    @Column(name = "direccionReferencia" , length = 200)
    private String direccionReferencia;
    
    /** El nroCuentaCII. */
    @Column(name = "condicionAsociado" , length = 200)
    private String condicionAsociado;
    
    /** El nroCuentaCII. */
    @Column(name = "asociadoDerecho" , length = 200)
    private String asociadoDerecho;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRubro", referencedColumnName = "idItem")
    private Item itemByRubro; 
    
    /** El estado. */
    @Column(name = "cuentaconPuesto" , length = 1)
    private String cuentaconPuesto;
    
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaIncorporacion")
    private Date fechaIncorporacion;
    
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaTransfiere")
    private Date fechaTransfiere;
    
    /** El nroCuentaCII. */
    @Column(name = "voluntadAsociado" , length = 200)
    private String voluntadAsociado;
    
    /** El nroCuentaCII. */
    @Column(name = "anotaciones" , length = 200)
    private String anotaciones;
    
    /** El nroCuentaCII. */
    @Column(name = "email" , length = 50)
    private String email;
    
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
    
    /** El foto. */
    @Column(name = "foto" , length = 50)
    private String foto;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGrado", referencedColumnName = "idItem")
    private Item itemGrado; 
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idActividad", referencedColumnName = "idItem")
    private Item itemActividad; 
   
    /**
     * Instancia un nuevo agrupa entidad.
     */
    public Asociado() {
    }

	public Asociado(String idAsociado, String nombre, String apellidoPaterno, String apellidoMaterno,
			Item itemByEsatdoCivil, Date fechaNacimiento, String celular, String nrodoc, String ruc,
			Item itemByTipoAsociado, Item tipoDocumento, Ubigeo ubigeoNacimiento, Ubigeo ubigeoActual, String direccion,
			String direccionReferencia, String condicionAsociado, String asociadoDerecho, Item itemByRubro,
			String cuentaconPuesto, Date fechaIncorporacion, Date fechaTransfiere, String voluntadAsociado,
			String anotaciones, String email, String estado, String foto,Item itemGrado,Item itemActividad) {
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

	
	public Item getItemGrado() {
		return itemGrado;
	}

	public void setItemGrado(Item itemGrado) {
		this.itemGrado = itemGrado;
	}

	public Item getItemActividad() {
		return itemActividad;
	}

	public void setItemActividad(Item itemActividad) {
		this.itemActividad = itemActividad;
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

	public Item getItemByEsatdoCivil() {
		return itemByEsatdoCivil;
	}

	public void setItemByEsatdoCivil(Item itemByEsatdoCivil) {
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

	public Item getItemByTipoAsociado() {
		return itemByTipoAsociado;
	}

	public void setItemByTipoAsociado(Item itemByTipoAsociado) {
		this.itemByTipoAsociado = itemByTipoAsociado;
	}

	public Item getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(Item tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Ubigeo getUbigeoNacimiento() {
		return ubigeoNacimiento;
	}

	public void setUbigeoNacimiento(Ubigeo ubigeoNacimiento) {
		this.ubigeoNacimiento = ubigeoNacimiento;
	}

	public Ubigeo getUbigeoActual() {
		return ubigeoActual;
	}

	public void setUbigeoActual(Ubigeo ubigeoActual) {
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

	public Item getItemByRubro() {
		return itemByRubro;
	}

	public void setItemByRubro(Item itemByRubro) {
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
		Asociado other = (Asociado) obj;
		if (idAsociado == null) {
			if (other.idAsociado != null)
				return false;
		} else if (!idAsociado.equals(other.idAsociado))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Asociado [idAsociado=" + idAsociado + "]";
	}

	 
}