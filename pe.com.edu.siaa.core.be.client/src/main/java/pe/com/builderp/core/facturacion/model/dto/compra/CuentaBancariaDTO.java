package pe.com.builderp.core.facturacion.model.dto.compra;

import java.io.Serializable;

import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO;
 

/**
 * La Class CuentaBancariaDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Fri Dec 22 11:06:59 COT 2017
 * @since SIAA-CORE 2.1
 */
public class CuentaBancariaDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id cuenta bancaria. */
    private String idCuentaBancaria;
   
    /** El item by banco. */
    private ItemDTO itemByBanco;
   
    /** El proveedor. */
    private ProveedorDTO proveedor;
   
    /** El item by moneda. */
    private ItemDTO itemByMoneda;
   
    /** El item by tipo cuenta. */
    private ItemDTO itemByTipoCuenta;
   
    /** El nro cuenta. */
    private String nroCuenta;
   
    /** El estado. */
    private String estado;
   
    /**
     * Instancia un nuevo cuenta bancariaDTO.
     */
    public CuentaBancariaDTO() {
    }
   
   
    /**
     * Instancia un nuevo cuenta bancariaDTO.
     *
     * @param idCuentaBancaria el id cuenta bancaria
     * @param itemByBanco el item by banco
     * @param proveedor el proveedor
     * @param itemByMoneda el item by moneda
     * @param itemByTipoCuenta el item by tipo cuenta
     * @param nroCuenta el nro cuenta
     * @param estado el estado
     */
    public CuentaBancariaDTO(String idCuentaBancaria, ItemDTO itemByBanco,ProveedorDTO proveedor,ItemDTO itemByMoneda,ItemDTO itemByTipoCuenta,String nroCuenta, String estado ) {
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
     public ItemDTO getItemByBanco() {
        return this.itemByBanco;
    }
    /**
     * Establece el item by banco.
     *
     * @param itemByBanco el new item by banco
     */
    public void setItemByBanco(ItemDTO itemByBanco) {
        this.itemByBanco = itemByBanco;
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
     * Obtiene item by tipo cuenta.
     *
     * @return item by tipo cuenta
     */
     public ItemDTO getItemByTipoCuenta() {
        return this.itemByTipoCuenta;
    }
    /**
     * Establece el item by tipo cuenta.
     *
     * @param itemByTipoCuenta el new item by tipo cuenta
     */
    public void setItemByTipoCuenta(ItemDTO itemByTipoCuenta) {
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
        CuentaBancariaDTO other = (CuentaBancariaDTO) obj;
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
        return "CuentaBancariaDTO [idCuentaBancaria=" + idCuentaBancaria + "]";
    }
   
}