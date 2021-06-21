package pe.com.builderp.core.facturacion.model.dto.venta;

import java.io.Serializable;

import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.seguridad.UsuarioDTO;

/**
 * La Class DetalleEntregaDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Fri Dec 22 11:07:01 COT 2017
 * @since SIAA-CORE 2.1
 */
public class DetalleEntregaDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id detalle pedido. */
    private String idDetalleEntrega;
   
    /** El pedido. */
    private UsuarioDTO usuario;
   
    /** El entrega. */
    private EntregaDTO entrega;  
   
   
    /**
     * Instancia un nuevo detalle pedidoDTO.
     */
    public DetalleEntregaDTO() {
    }
   
   
    /**
     * Instancia un nuevo detalle pedidoDTO.
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
    public DetalleEntregaDTO(String idDetalleEntrega,UsuarioDTO usuario ,EntregaDTO entrega ) {
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
    
    
    public UsuarioDTO getUsuario() {
		return usuario;
	}


	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}


	public EntregaDTO getEntrega() {
		return entrega;
	}


	public void setEntrega(EntregaDTO entrega) {
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
        DetalleEntregaDTO other = (DetalleEntregaDTO) obj;
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
        return "DetalleEntregaDTO [idDetalleEntrega=" + idDetalleEntrega + "]";
    }
   
}