package pe.com.builderp.core.facturacion.model.dto.venta;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pe.com.edu.siaa.core.model.dto.BasePaginator;


/**
 * La Class EntregaDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Fri Dec 22 11:07:03 COT 2017
 * @since SIAA-CORE 2.1
 */
public class EntregaDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id pedido. */
    private String idEntrega; 
   
    /** El estadoEntrega. */
    private String estadoEntrega;
   
    /** El observacione. */
    private String observacion; 
    
    private Date fechaEntrega;    
    
    private PedidoDTO pedido;
   
   
    /** El pedido detalle pedido list. */
    private List<DetalleEntregaDTO> entregaDetalleEntregaList = new ArrayList<DetalleEntregaDTO>();   
   
   
    /**
     * Instancia un nuevo pedidoDTO.
     */
    public EntregaDTO() {
    }
   
   
    /**
     * Instancia un nuevo pedidoDTO.
     *
     * @param idEntrega el id idEntrega
     * @param estadoEntrega
     * @param observacion
     * @param fechaEntrega
     */
    public EntregaDTO(String idEntrega,String estadoEntrega,String observacion,Date fechaEntrega,PedidoDTO pedido ) {
        super();
        this.idEntrega = idEntrega;
        this.estadoEntrega = estadoEntrega;
        this.observacion = observacion;
        this.fechaEntrega = fechaEntrega;  
        this.pedido = pedido; 
        
    }
   
    //get y set
    /**
     * Obtiene id pedido.
     *
     * @return id pedido
     */
     public String getIdEntrega() {
        return this.idEntrega;
    }
    /**
     * Establece el id pedido.
     *
     * @param idEntrega el new id pedido
     */
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

	public PedidoDTO getPedido() {
		return pedido;
	}


	public void setPedido(PedidoDTO pedido) {
		this.pedido = pedido;
	}


	public List<DetalleEntregaDTO> getEntregaDetalleEntregaList() {
		return entregaDetalleEntregaList;
	}


	public void setEntregaDetalleEntregaList(List<DetalleEntregaDTO> entregaDetalleEntregaList) {
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
        EntregaDTO other = (EntregaDTO) obj;
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
        return "EntregaDTO [idEntrega=" + idEntrega + "]";
    }
   
}