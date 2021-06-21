package pe.com.builderp.core.facturacion.model.dto.compra;

import java.io.Serializable;

import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO;
import pe.com.edu.siaa.core.model.dto.contabilidad.PlanContableDTO;
 

/**
 * La Class CuentaTipoDocumentoDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Fri Dec 22 11:06:59 COT 2017
 * @since SIAA-CORE 2.1
 */
public class CuentaTipoDocumentoDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id cuenta tipo documento. */
    private String idCuentaTipoDocumento;
   
    /** El proveedor. */
    private ProveedorDTO proveedor;
   
    /** El id plan contable. */
    private PlanContableDTO idPlanContable;
   
    /** El item by tipo documento. */
    private ItemDTO itemByTipoDocumento;
   
    /** El item by moneda. */
    private ItemDTO itemByMoneda;
   
    /** El estado. */
    private String estado;
   
    /**
     * Instancia un nuevo cuenta tipo documentoDTO.
     */
    public CuentaTipoDocumentoDTO() {
    }
   
   
    /**
     * Instancia un nuevo cuenta tipo documentoDTO.
     *
     * @param idCuentaTipoDocumento el id cuenta tipo documento
     * @param proveedor el proveedor
     * @param idPlanContable el id plan contable
     * @param itemByTipoDocumento el item by tipo documento
     * @param itemByMoneda el item by moneda
     * @param estado el estado
     */
    public CuentaTipoDocumentoDTO(String idCuentaTipoDocumento, ProveedorDTO proveedor,PlanContableDTO idPlanContable,ItemDTO itemByTipoDocumento,ItemDTO itemByMoneda,String estado ) {
        super();
        this.idCuentaTipoDocumento = idCuentaTipoDocumento;
        this.proveedor = proveedor;
        this.idPlanContable = idPlanContable;
        this.itemByTipoDocumento = itemByTipoDocumento;
        this.itemByMoneda = itemByMoneda;
        this.estado = estado;
    }
   
    //get y set
    /**
     * Obtiene id cuenta tipo documento.
     *
     * @return id cuenta tipo documento
     */
     public String getIdCuentaTipoDocumento() {
        return this.idCuentaTipoDocumento;
    }
    /**
     * Establece el id cuenta tipo documento.
     *
     * @param idCuentaTipoDocumento el new id cuenta tipo documento
     */
    public void setIdCuentaTipoDocumento(String idCuentaTipoDocumento) {
        this.idCuentaTipoDocumento = idCuentaTipoDocumento;
    }
    /**
     * Obtiene proveedor.
     *
     * @return proveedor
     */
     public ProveedorDTO getProveedor() {
        return this.proveedor;
    }
    /**
     * Establece el proveedor.
     *
     * @param proveedor el new proveedor
     */
    public void setProveedor(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
    }
    /**
     * Obtiene id plan contable.
     *
     * @return id plan contable
     */
     public PlanContableDTO getIdPlanContable() {
        return this.idPlanContable;
    }
    /**
     * Establece el id plan contable.
     *
     * @param idPlanContable el new id plan contable
     */
    public void setIdPlanContable(PlanContableDTO idPlanContable) {
        this.idPlanContable = idPlanContable;
    }
    /**
     * Obtiene item by tipo documento.
     *
     * @return item by tipo documento
     */
     public ItemDTO getItemByTipoDocumento() {
        return this.itemByTipoDocumento;
    }
    /**
     * Establece el item by tipo documento.
     *
     * @param itemByTipoDocumento el new item by tipo documento
     */
    public void setItemByTipoDocumento(ItemDTO itemByTipoDocumento) {
        this.itemByTipoDocumento = itemByTipoDocumento;
    }
    /**
     * Obtiene item by moneda.
     *
     * @return item by moneda
     */
     public ItemDTO getItemByMoneda() {
        return this.itemByMoneda;
    }
    /**
     * Establece el item by moneda.
     *
     * @param itemByMoneda el new item by moneda
     */
    public void setItemByMoneda(ItemDTO itemByMoneda) {
        this.itemByMoneda = itemByMoneda;
    }
    /**
     * Obtiene estado.
     *
     * @return estado
     */
     public String getEstado() {
        return this.estado;
    }
    /**
     * Establece el estado.
     *
     * @param estado el new estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idCuentaTipoDocumento == null) ? 0 : idCuentaTipoDocumento.hashCode());
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
        CuentaTipoDocumentoDTO other = (CuentaTipoDocumentoDTO) obj;
        if (idCuentaTipoDocumento == null) {
            if (other.idCuentaTipoDocumento != null) {
                return false;
            }
        } else if (!idCuentaTipoDocumento.equals(other.idCuentaTipoDocumento)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CuentaTipoDocumentoDTO [idCuentaTipoDocumento=" + idCuentaTipoDocumento + "]";
    }
   
}