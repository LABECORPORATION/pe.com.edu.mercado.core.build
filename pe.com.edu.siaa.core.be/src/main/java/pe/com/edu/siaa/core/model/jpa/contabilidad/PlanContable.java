package pe.com.edu.siaa.core.model.jpa.contabilidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pe.com.edu.siaa.core.model.jpa.seguridad.Entidad;
import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class PlanContable.
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
@Table(name = "PlanContable", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CONTABILIDAD)
public class PlanContable implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id plan contable. */
    @Id
    @Column(name = "idPlanContable" , length = 32)
    private String idPlanContable;
   
    /** El codigo. */
    @Column(name = "codigo" , length = 20)
    private String codigo;
   
    /** El nombre. */
    @Column(name = "nombre" , length = 200)
    private String nombre;
   
    /** El plan contable depedencia. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPlanContableDepedencia", referencedColumnName = "idPlanContable")
    private PlanContable planContableDepedencia;
   
    /** El plan contable debe. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPlanContableDebe", referencedColumnName = "idPlanContable")
    private PlanContable planContableDebe;
   
    /** El plan contable haber. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPlanContableHaber", referencedColumnName = "idPlanContable")
    private PlanContable planContableHaber;
   
    /** El entidad. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEntidad", referencedColumnName = "idEntidad")
    private Entidad entidad;
   
    /** El anho. */
    @Column(name = "anho" , length = 18)
    private Long anho;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
   
   
    /**
     * Instancia un nuevo plan contable.
     */
    public PlanContable() {
    }
   
   
    /**
     * Instancia un nuevo plan contable.
     *
     * @param idPlanContable el id plan contable
     * @param codigo el codigo
     * @param nombre el nombre
     * @param planContableDepedencia el plan contable depedencia
     * @param planContableDebe el plan contable debe
     * @param planContableHaber el plan contable haber
     * @param entidad el entidad
     * @param anho el anho
     * @param estado el estado
     */
    public PlanContable(String idPlanContable, String codigo, String nombre, PlanContable planContableDepedencia,PlanContable planContableDebe,PlanContable planContableHaber,Entidad entidad,Long anho, String estado ) {
        super();
        this.idPlanContable = idPlanContable;
        this.codigo = codigo;
        this.nombre = nombre;
        this.planContableDepedencia = planContableDepedencia;
        this.planContableDebe = planContableDebe;
        this.planContableHaber = planContableHaber;
        this.entidad = entidad;
        this.anho = anho;
        this.estado = estado;
    }
   
    //get y set
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
     * Obtiene codigo.
     *
     * @return codigo
     */
     public String getCodigo() {
        return this.codigo;
    }
    /**
     * Establece el codigo.
     *
     * @param codigo el new codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    /**
     * Obtiene nombre.
     *
     * @return nombre
     */
     public String getNombre() {
        return this.nombre;
    }
    /**
     * Establece el nombre.
     *
     * @param nombre el new nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Obtiene plan contable depedencia.
     *
     * @return plan contable depedencia
     */
     public PlanContable getPlanContableDepedencia() {
        return this.planContableDepedencia;
    }
    /**
     * Establece el plan contable depedencia.
     *
     * @param planContableDepedencia el new plan contable depedencia
     */
    public void setPlanContableDepedencia(PlanContable planContableDepedencia) {
        this.planContableDepedencia = planContableDepedencia;
    }
    /**
     * Obtiene plan contable debe.
     *
     * @return plan contable debe
     */
     public PlanContable getPlanContableDebe() {
        return this.planContableDebe;
    }
    /**
     * Establece el plan contable debe.
     *
     * @param planContableDebe el new plan contable debe
     */
    public void setPlanContableDebe(PlanContable planContableDebe) {
        this.planContableDebe = planContableDebe;
    }
    /**
     * Obtiene plan contable haber.
     *
     * @return plan contable haber
     */
     public PlanContable getPlanContableHaber() {
        return this.planContableHaber;
    }
    /**
     * Establece el plan contable haber.
     *
     * @param planContableHaber el new plan contable haber
     */
    public void setPlanContableHaber(PlanContable planContableHaber) {
        this.planContableHaber = planContableHaber;
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
     * Obtiene anho.
     *
     * @return anho
     */
     public Long getAnho() {
        return this.anho;
    }
    /**
     * Establece el anho.
     *
     * @param anho el new anho
     */
    public void setAnho(Long anho) {
        this.anho = anho;
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
                + ((idPlanContable == null) ? 0 : idPlanContable.hashCode());
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
        PlanContable other = (PlanContable) obj;
        if (idPlanContable == null) {
            if (other.idPlanContable != null) {
                return false;
            }
        } else if (!idPlanContable.equals(other.idPlanContable)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PlanContable [idPlanContable=" + idPlanContable + "]";
    }
   
}