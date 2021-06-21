package pe.com.edu.siaa.core.model.jpa.contabilidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pe.com.edu.siaa.core.model.jpa.common.Item;
import pe.com.edu.siaa.core.model.jpa.seguridad.Entidad;
import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class ConfiguracionCuenta.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Wed Dec 20 00:30:24 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "ConfiguracionCuenta", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CONTABILIDAD)
public class ConfiguracionCuenta implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id configuracion cuenta. */
    @Id
    @Column(name = "idConfiguracionCuenta" , length = 32)
    private String idConfiguracionCuenta;
   
    /** El entidad. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEntidad", referencedColumnName = "idEntidad")
    private Entidad entidad;
   
    /** El item by libro. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idLibro", referencedColumnName = "idItem")
    private Item itemByLibro;
   
    /** El plan contable. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPlanContable", referencedColumnName = "idPlanContable")
    private PlanContable planContable;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
   
    /** El tipo movimiento. */
    @Column(name = "tipoMovimiento" , length = 1)
    private String tipoMovimiento;
   
    /**
     * Instancia un nuevo configuracion cuenta.
     */
    public ConfiguracionCuenta() {
    }
   
   
    /**
     * Instancia un nuevo configuracion cuenta.
     *
     * @param idConfiguracionCuenta el id configuracion cuenta
     * @param entidad el entidad
     * @param itemByLibro el item by libro
     * @param planContable el plan contable
     * @param estado el estado
     * @param tipoMovimiento el tipo movimiento
     */
    public ConfiguracionCuenta(String idConfiguracionCuenta, Entidad entidad,Item itemByLibro,PlanContable planContable,String estado, String tipoMovimiento ) {
        super();
        this.idConfiguracionCuenta = idConfiguracionCuenta;
        this.entidad = entidad;
        this.itemByLibro = itemByLibro;
        this.planContable = planContable;
        this.estado = estado;
        this.tipoMovimiento = tipoMovimiento;
    }
   
    //get y set
    /**
     * Obtiene id configuracion cuenta.
     *
     * @return id configuracion cuenta
     */
     public String getIdConfiguracionCuenta() {
        return this.idConfiguracionCuenta;
    }
    /**
     * Establece el id configuracion cuenta.
     *
     * @param idConfiguracionCuenta el new id configuracion cuenta
     */
    public void setIdConfiguracionCuenta(String idConfiguracionCuenta) {
        this.idConfiguracionCuenta = idConfiguracionCuenta;
    }
    /**
     * Obtiene entidad.
     *
     * @return entidad
     */
     public Entidad getEntidad() {
        return this.entidad;
    }
    /**
     * Establece el entidad.
     *
     * @param entidad el new entidad
     */
    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }
    /**
     * Obtiene item by libro.
     *
     * @return item by libro
     */
     public Item getItemByLibro() {
        return this.itemByLibro;
    }
    /**
     * Establece el item by libro.
     *
     * @param itemByLibro el new item by libro
     */
    public void setItemByLibro(Item itemByLibro) {
        this.itemByLibro = itemByLibro;
    }
    /**
     * Obtiene plan contable.
     *
     * @return plan contable
     */
     public PlanContable getPlanContable() {
        return this.planContable;
    }
    /**
     * Establece el plan contable.
     *
     * @param planContable el new plan contable
     */
    public void setPlanContable(PlanContable planContable) {
        this.planContable = planContable;
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
    /**
     * Obtiene tipo movimiento.
     *
     * @return tipo movimiento
     */
     public String getTipoMovimiento() {
        return this.tipoMovimiento;
    }
    /**
     * Establece el tipo movimiento.
     *
     * @param tipoMovimiento el new tipo movimiento
     */
    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idConfiguracionCuenta == null) ? 0 : idConfiguracionCuenta.hashCode());
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
        ConfiguracionCuenta other = (ConfiguracionCuenta) obj;
        if (idConfiguracionCuenta == null) {
            if (other.idConfiguracionCuenta != null) {
                return false;
            }
        } else if (!idConfiguracionCuenta.equals(other.idConfiguracionCuenta)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ConfiguracionCuenta [idConfiguracionCuenta=" + idConfiguracionCuenta + "]";
    }
   
}