package pe.com.edu.siaa.core.model.jpa.contabilidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pe.com.builderp.core.facturacion.model.jpa.compra.Proveedor;
import pe.com.edu.siaa.core.model.jpa.common.Item;
import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class CuentaTipoDocumento.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Fri Dec 15 11:57:35 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "CuentaTipoDocumento", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CONTABILIDAD)
public class CuentaTipoDocumento implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id cuenta tipo documento. */
    @Id
    @Column(name = "idCuentaTipoDocumento" , length = 32)
    private String idCuentaTipoDocumento;
   
    /** El proveedor. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProveedor", referencedColumnName = "idProveedor")
    private Proveedor proveedor;
   
    /** El id plan contable. */
    @Column(name = "idPlanContable" , length = 32)
    private String idPlanContable;
   
    /** El item by tipo documento. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoDocumento", referencedColumnName = "idItem")
    private Item itemByTipoDocumento;
   
    /** El item by moneda. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoMoneda", referencedColumnName = "idItem")
    private Item itemByMoneda;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
   
    /**
     * Instancia un nuevo cuenta tipo documento.
     */
    public CuentaTipoDocumento() {
    }
   
   
    /**
     * Instancia un nuevo cuenta tipo documento.
     *
     * @param idCuentaTipoDocumento el id cuenta tipo documento
     * @param proveedor el proveedor
     * @param idPlanContable el id plan contable
     * @param itemByTipoDocumento el item by tipo documento
     * @param itemByMoneda el item by moneda
     * @param estado el estado
     */
    public CuentaTipoDocumento(String idCuentaTipoDocumento, Proveedor proveedor,String idPlanContable, Item itemByTipoDocumento,Item itemByMoneda,String estado ) {
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
     public Proveedor getProveedor() {
        return this.proveedor;
    }
    /**
     * Establece el proveedor.
     *
     * @param proveedor el new proveedor
     */
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    /**
     * Obtiene id plan contable.
     *
     * @return id plan contable
     */
     public String getIdPlanContable() {
        return this.idPlanContable;
    }
    /**
     * Establece el id plan contable.
     *
     * @param idPlanContable el new id plan contable
     */
    public void setIdPlanContable(String idPlanContable) {
        this.idPlanContable = idPlanContable;
    }
    /**
     * Obtiene item by tipo documento.
     *
     * @return item by tipo documento
     */
     public Item getItemByTipoDocumento() {
        return this.itemByTipoDocumento;
    }
    /**
     * Establece el item by tipo documento.
     *
     * @param itemByTipoDocumento el new item by tipo documento
     */
    public void setItemByTipoDocumento(Item itemByTipoDocumento) {
        this.itemByTipoDocumento = itemByTipoDocumento;
    }
    /**
     * Obtiene item by moneda.
     *
     * @return item by moneda
     */
     public Item getItemByMoneda() {
        return this.itemByMoneda;
    }
    /**
     * Establece el item by moneda.
     *
     * @param itemByMoneda el new item by moneda
     */
    public void setItemByMoneda(Item itemByMoneda) {
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
        CuentaTipoDocumento other = (CuentaTipoDocumento) obj;
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
        return "CuentaTipoDocumento [idCuentaTipoDocumento=" + idCuentaTipoDocumento + "]";
    }
   
}