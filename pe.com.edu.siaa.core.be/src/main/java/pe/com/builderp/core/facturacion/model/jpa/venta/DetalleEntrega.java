package pe.com.builderp.core.facturacion.model.jpa.venta;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pe.com.edu.siaa.core.model.jpa.seguridad.Usuario;
import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class DetalleEntrega.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Fri Dec 22 11:10:34 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "DetalleEntrega", schema = ConfiguracionEntityManagerUtil.ESQUEMA_FACTURACION)
public class DetalleEntrega implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id detalle pedido. */
    @Id
    @Column(name = "idDetalleEntrega" , length = 32)
    private String idDetalleEntrega;
   
    /** El pedido. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private Usuario usuario;
   
    /** El producto. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEntrega", referencedColumnName = "idEntrega")
    private Entrega entrega;
   
   
    /**
     * Instancia un nuevo detalle pedido.
     */
    public DetalleEntrega() {
    }
   
   
    /**
     * Instancia un nuevo detalle pedido.
     *
     * @param idDetalleEntrega el id detalle pedido
     * @param pedido el pedido
     * @param producto el producto
     * @param descripcionProducto el descripcion producto
     * @param precio el precio
     * @param cantidad el cantidad
     * @param descuento el descuento
     * @param subMontoTotal el sub monto total
     * @param montoTotal el monto total
     */
    public DetalleEntrega(String idDetalleEntrega, Usuario usuario,Entrega entrega ) {
        super();
        this.idDetalleEntrega = idDetalleEntrega;
        this.usuario = usuario;
        this.entrega = entrega;   
      
    }
   
    //get y set
    /**
     * Obtiene id detalle pedido.
     *
     * @return id detalle pedido
     */
     public String getIdDetalleEntrega() {
        return this.idDetalleEntrega;
    }
    /**
     * Establece el id detalle pedido.
     *
     * @param idDetalleEntrega el new id detalle pedido
     */
    public void setIdDetalleEntrega(String idDetalleEntrega) {
        this.idDetalleEntrega = idDetalleEntrega;
    }
  

    public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Entrega getEntrega() {
		return entrega;
	}


	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}


	/* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idDetalleEntrega == null) ? 0 : idDetalleEntrega.hashCode());
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
        DetalleEntrega other = (DetalleEntrega) obj;
        if (idDetalleEntrega == null) {
            if (other.idDetalleEntrega != null) {
                return false;
            }
        } else if (!idDetalleEntrega.equals(other.idDetalleEntrega)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DetalleEntrega [idDetalleEntrega=" + idDetalleEntrega + "]";
    }
   
}