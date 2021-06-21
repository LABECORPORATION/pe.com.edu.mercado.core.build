package pe.com.builderp.core.facturacion.model.jpa.compra;

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
 * La Class CuentaBancaria.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Wed Dec 20 00:30:17 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "CuentaBancaria", schema = ConfiguracionEntityManagerUtil.ESQUEMA_FACTURACION)
public class CuentaBancaria implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id cuenta bancaria. */
    @Id
    @Column(name = "idCuentaBancaria" , length = 32)
    private String idCuentaBancaria;
   
    /** El item by banco. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idBanco", referencedColumnName = "idItem")
    private Item itemByBanco;
   
    /** El proveedor. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProveedor", referencedColumnName = "idProveedor")
    private Proveedor proveedor;
   
    /** El item by moneda. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMoneda", referencedColumnName = "idItem")
    private Item itemByMoneda;
   
    /** El item by tipo cuenta. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoCuenta", referencedColumnName = "idItem")
    private Item itemByTipoCuenta;
   
    /** El nro cuenta. */
    @Column(name = "nroCuenta" , length = 150)
    private String nroCuenta;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
   
    /**
     * Instancia un nuevo cuenta bancaria.
     */
    public CuentaBancaria() {
    }
   
   
    /**
     * Instancia un nuevo cuenta bancaria.
     *
     * @param idCuentaBancaria el id cuenta bancaria
     * @param itemByBanco el item by banco
     * @param proveedor el proveedor
     * @param itemByMoneda el item by moneda
     * @param itemByTipoCuenta el item by tipo cuenta
     * @param nroCuenta el nro cuenta
     * @param estado el estado
     */
    public CuentaBancaria(String idCuentaBancaria, Item itemByBanco,Proveedor proveedor,Item itemByMoneda,Item itemByTipoCuenta,String nroCuenta, String estado ) {
        super();
        this.idCuentaBancaria = idCuentaBancaria;
        this.itemByBanco = itemByBanco;
        this.proveedor = proveedor;
        this.itemByMoneda = itemByMoneda;
        this.itemByTipoCuenta = itemByTipoCuenta;
        this.nroCuenta = nroCuenta;
        this.estado = estado;
    }
   
    //get y set
    /**
     * Obtiene id cuenta bancaria.
     *
     * @return id cuenta bancaria
     */
     public String getIdCuentaBancaria() {
        return this.idCuentaBancaria;
    }
    /**
     * Establece el id cuenta bancaria.
     *
     * @param idCuentaBancaria el new id cuenta bancaria
     */
    public void setIdCuentaBancaria(String idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }
    /**
     * Obtiene item by banco.
     *
     * @return item by banco
     */
     public Item getItemByBanco() {
        return this.itemByBanco;
    }
    /**
     * Establece el item by banco.
     *
     * @param itemByBanco el new item by banco
     */
    public void setItemByBanco(Item itemByBanco) {
        this.itemByBanco = itemByBanco;
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
     * Obtiene item by tipo cuenta.
     *
     * @return item by tipo cuenta
     */
     public Item getItemByTipoCuenta() {
        return this.itemByTipoCuenta;
    }
    /**
     * Establece el item by tipo cuenta.
     *
     * @param itemByTipoCuenta el new item by tipo cuenta
     */
    public void setItemByTipoCuenta(Item itemByTipoCuenta) {
        this.itemByTipoCuenta = itemByTipoCuenta;
    }
    /**
     * Obtiene nro cuenta.
     *
     * @return nro cuenta
     */
     public String getNroCuenta() {
        return this.nroCuenta;
    }
    /**
     * Establece el nro cuenta.
     *
     * @param nroCuenta el new nro cuenta
     */
    public void setNroCuenta(String nroCuenta) {
        this.nroCuenta = nroCuenta;
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
                + ((idCuentaBancaria == null) ? 0 : idCuentaBancaria.hashCode());
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
        CuentaBancaria other = (CuentaBancaria) obj;
        if (idCuentaBancaria == null) {
            if (other.idCuentaBancaria != null) {
                return false;
            }
        } else if (!idCuentaBancaria.equals(other.idCuentaBancaria)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CuentaBancaria [idCuentaBancaria=" + idCuentaBancaria + "]";
    }
   
}