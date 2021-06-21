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
@Table(name = "Personal", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_EMPRESA)
public class Personal implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id agrupa entidad. */
    @Id
    @Column(name = "idPersonal" , length = 32)
    private String idPersonal;

    /** El nombre. */
    @Column(name = "nombre" , length = 50)
    private String nombre;
    
    /** El apellidoPaterno. */
    @Column(name = "apellidoPaterno" , length = 50)
    private String apellidoPaterno;
    
    /** El apellidoPaterno. */
    @Column(name = "apellidoMaterno" , length = 50)
    private String apellidoMaterno;
 
    
    /** El nombre. */
    @Column(name = "celular" , length = 10)
    private String celular;
    
    /** El nroCuentaCII. */
    @Column(name = "nrodoc" , length = 10)
    private String nrodoc;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoPersonal", referencedColumnName = "idItem")
    private Item tipoPersonal;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoDocumento", referencedColumnName = "idItem")
    private Item tipoDocumento; 

    /** El nroCuentaCII. */
    @Column(name = "direccion" , length = 200)
    private String direccion;
    
    /** El nroCuentaCII. */
    @Column(name = "email" , length = 50)
    private String email;
    
 
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
    
    /** El foto. */
    @Column(name = "foto" , length = 50)
    private String foto;
   
    /**
     * Instancia un nuevo agrupa entidad.
     */
    public Personal() {
    }

	public Personal(String idPersonal, String nombre, String apellidoPaterno, String apellidoMaterno, String celular,
			String nrodoc, Item tipoPersonal, Item tipoDocumento, String direccion, String email, String estado,String foto) {
		super();
		this.idPersonal = idPersonal;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.celular = celular;
		this.nrodoc = nrodoc;
		this.tipoPersonal = tipoPersonal;
		this.tipoDocumento = tipoDocumento;
		this.direccion = direccion;
		this.email = email;
		this.estado = estado;
		this.foto=foto;	
	}

	public String getIdPersonal() {
		return idPersonal;
	}

	public void setIdPersonal(String idPersonal) {
		this.idPersonal = idPersonal;
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

	public Item getTipoPersonal() {
		return tipoPersonal;
	}

	public void setTipoPersonal(Item tipoPersonal) {
		this.tipoPersonal = tipoPersonal;
	}

	public Item getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(Item tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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
		result = prime * result + ((idPersonal == null) ? 0 : idPersonal.hashCode());
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
		Personal other = (Personal) obj;
		if (idPersonal == null) {
			if (other.idPersonal != null)
				return false;
		} else if (!idPersonal.equals(other.idPersonal))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Personal [idPersonal=" + idPersonal + "]";
	}

    
    
	 
}