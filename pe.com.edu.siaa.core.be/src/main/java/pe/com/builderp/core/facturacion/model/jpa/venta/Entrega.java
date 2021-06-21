package pe.com.builderp.core.facturacion.model.jpa.venta;

import java.io.Serializable;

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


import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class Entrega.
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
@Table(name = "Entrega", schema = ConfiguracionEntityManagerUtil.ESQUEMA_FACTURACION)
public class Entrega implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id Entrega. */
    @Id
    @Column(name = "idEntrega" , length = 32)
    private String idEntrega;
   
    /** El estado Entrega. */
    @Column(name = "estadoEntrega" , length = 2)
    private String estadoEntrega;      

    /** El observacion. */
    @Column(name = "observacion" , length = 100)
    private String observacion; 
    
    /** El fecha Entrega. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "fechaEntrega")
    private Date fechaEntrega;
    
    /** El producto. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPedido", referencedColumnName = "idPedido")
    private Pedido pedido;
   
    /** El pedido detalle pedido list. */
    @OneToMany(mappedBy = "entrega", fetch = FetchType.LAZY)
    private List<DetalleEntrega> entregaDetalleEntregaList = new ArrayList<DetalleEntrega>();    
    
    
    /**
     * Instancia un nuevo pedido.
     */
    public Entrega() {
    }
   
   
    /**
     * Instancia un nuevo pedido.
     *
     * @param idEntrega el id idEntrega
     * @param estadoEntrega
     * @param observacion
     * @param fechaEntrega
     */
    public Entrega(String idEntrega, String estadoEntrega,String observacion,Date fechaEntrega,Pedido pedido ) {
        super();
        this.idEntrega = idEntrega;
        this.estadoEntrega = estadoEntrega;
        this.observacion = observacion;
        this.fechaEntrega = fechaEntrega;
        this.pedido = pedido;
             
        
    }
   
    //get y set
   
    public String getIdEntrega() {
		return idEntrega;
	}


	public void setIdEntrega(String idEntrega) {
		this.idEntrega = idEntrega;
	}


	public String getEstadoEntrega() {
		return estadoEntrega;
	}


	public void setEstadoEntrega(String estadoEntrega) {
		this.estadoEntrega = estadoEntrega;
	}


	public String getObservacion() {
		return observacion;
	}


	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}


	public Date getFechaEntrega() {
		return fechaEntrega;
	}


	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	

	public Pedido getPedido() {
		return pedido;
	}


	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}


	public List<DetalleEntrega> getEntregaDetalleEntregaList() {
		return entregaDetalleEntregaList;
	}


	public void setEntregaDetalleEntregaList(List<DetalleEntrega> entregaDetalleEntregaList) {
		this.entregaDetalleEntregaList = entregaDetalleEntregaList;
	}

    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idEntrega == null) ? 0 : idEntrega.hashCode());
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
        Entrega other = (Entrega) obj;
        if (idEntrega == null) {
            if (other.idEntrega != null) {
                return false;
            }
        } else if (!idEntrega.equals(other.idEntrega)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Entrega [idEntrega=" + idEntrega + "]";
    }
   
}