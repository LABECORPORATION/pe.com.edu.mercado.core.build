package pe.com.edu.siaa.core.model.dto.contabilidad;

import java.io.Serializable;

import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.EntidadDTO;

/**
 * La Class ConfiguracionCuentaDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Wed Dec 20 10:42:11 COT 2017
 * @since SIAA-CORE 2.1
 */
public class ConfiguracionCuentaDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id configuracion cuenta. */
    private String idConfiguracionCuenta;
    
    /** El item by libro. */
    private EntidadDTO entidad;
   
    /** El item by libro. */
    private ItemDTO itemByLibro;
   
    /** El plan contable. */
    private PlanContableDTO planContable;
   
    /** El estado. */
    private String estado;
   
    /** El tipo movimiento. */
    private String tipoMovimiento;
   
    /**
     * Instancia un nuevo configuracion cuentaDTO.
     */
    public ConfiguracionCuentaDTO() {
    }
   
   
    /**
     * Instancia un nuevo configuracion cuentaDTO.
     *
     * @param idConfiguracionCuenta el id configuracion cuenta
     * @param itemByLibro el item by libro
     * @param planContable el plan contable
     * @param estado el estado
     * @param tipoMovimiento el tipo movimiento
     */
    public ConfiguracionCuentaDTO(String idConfiguracionCuenta, ItemDTO itemByLibro,PlanContableDTO planContable,String estado, String tipoMovimiento ) {
        super();
        this.idConfiguracionCuenta = idConfiguracionCuenta;
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
    
    
    public EntidadDTO getEntidad() {
		return entidad;
	}


	public void setEntidad(EntidadDTO entidad) {
		this.entidad = entidad;
	}


	/**
     * Obtiene item by libro.
     *
     * @return item by libro
     */
     public ItemDTO getItemByLibro() {
        return this.itemByLibro;
    }
    /**
     * Establece el item by libro.
     *
     * @param itemByLibro el new item by libro
     */
    public void setItemByLibro(ItemDTO itemByLibro) {
        this.itemByLibro = itemByLibro;
    }
    /**
     * Obtiene plan contable.
     *
     * @return plan contable
     */
     public PlanContableDTO getPlanContable() {
        return this.planContable;
    }
    /**
     * Establece el plan contable.
     *
     * @param planContable el new plan contable
     */
    public void setPlanContable(PlanContableDTO planContable) {
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
        ConfiguracionCuentaDTO other = (ConfiguracionCuentaDTO) obj;
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
        return "ConfiguracionCuentaDTO [idConfiguracionCuenta=" + idConfiguracionCuenta + "]";
    }
   
}