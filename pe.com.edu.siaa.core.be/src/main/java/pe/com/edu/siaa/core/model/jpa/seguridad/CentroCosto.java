package pe.com.edu.siaa.core.model.jpa.seguridad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import pe.com.edu.siaa.core.model.jpa.contabilidad.PlanContable;
import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class CentroCosto.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Wed Dec 20 00:30:21 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "CentroCosto", schema = ConfiguracionEntityManagerUtil.ESQUEMA_CORE_SECURIDAD)
public class CentroCosto implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id centro costo. */
    @Id
    @Column(name = "idCentroCosto" , length = 32)
    private String idCentroCosto;
   
    /** El entidad. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEntidad", referencedColumnName = "idEntidad")
    private Entidad entidad;
   
    /** El codigo. */
    @Column(name = "codigo" , length = 30)
    private String codigo;
   
    /** El descripcion. */
    @Column(name = "descripcion" , length = 150)
    private String descripcion;
   
    /** El abreviatura. */
    @Column(name = "abreviatura" , length = 100)
    private String abreviatura;
   
    /** El centro costo padre. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCentroCostoPadre", referencedColumnName = "idCentroCosto")
    private CentroCosto centroCostoPadre;
   
    /** El plan contable. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPlanContable", referencedColumnName = "idPlanContable")
    private PlanContable planContable;
   
    /** El estado. */
    @Column(name = "estado" , length = 1)
    private String estado;
   
    /** El centro costo padre centro costo list. */
    @OneToMany(mappedBy = "centroCostoPadre", fetch = FetchType.LAZY)
    private List<CentroCosto> centroCostoPadreCentroCostoList = new ArrayList<CentroCosto>();
    
    /**
     * Instancia un nuevo centro costo.
     */
    public CentroCosto() {
    }
   
   
    /**
     * Instancia un nuevo centro costo.
     *
     * @param idCentroCosto el id centro costo
     * @param entidad el entidad
     * @param codigo el codigo
     * @param descripcion el descripcion
     * @param abreviatura el abreviatura
     * @param centroCostoPadre el centro costo padre
     * @param planContable el plan contable
     * @param estado el estado
     */
    public CentroCosto(String idCentroCosto, Entidad entidad,String codigo, String descripcion, String abreviatura, CentroCosto centroCostoPadre,PlanContable planContable,String estado ) {
        super();
        this.idCentroCosto = idCentroCosto;
        this.entidad = entidad;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
        this.centroCostoPadre = centroCostoPadre;
        this.planContable = planContable;
        this.estado = estado;
    }
   
    //get y set
    /**
     * Obtiene id centro costo.
     *
     * @return id centro costo
     */
     public String getIdCentroCosto() {
        return this.idCentroCosto;
    }
    /**
     * Establece el id centro costo.
     *
     * @param idCentroCosto el new id centro costo
     */
    public void setIdCentroCosto(String idCentroCosto) {
        this.idCentroCosto = idCentroCosto;
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
     * Obtiene descripcion.
     *
     * @return descripcion
     */
     public String getDescripcion() {
        return this.descripcion;
    }
    /**
     * Establece el descripcion.
     *
     * @param descripcion el new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * Obtiene abreviatura.
     *
     * @return abreviatura
     */
     public String getAbreviatura() {
        return this.abreviatura;
    }
    /**
     * Establece el abreviatura.
     *
     * @param abreviatura el new abreviatura
     */
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
    /**
     * Obtiene centro costo padre.
     *
     * @return centro costo padre
     */
     public CentroCosto getCentroCostoPadre() {
        return this.centroCostoPadre;
    }
    /**
     * Establece el centro costo padre.
     *
     * @param centroCostoPadre el new centro costo padre
     */
    public void setCentroCostoPadre(CentroCosto centroCostoPadre) {
        this.centroCostoPadre = centroCostoPadre;
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
     * Establece el centro costo padre centro costo list.
     *
     * @param centroCostoPadreCentroCostoList el new centro costo padre centro costo list
     */
    public List<CentroCosto> getCentroCostoPadreCentroCostoList(){
        return this.centroCostoPadreCentroCostoList;
    }
    /**
     * Establece el centro costo padre list.
     *
     * @param centroCostoPadreList el new centro costo padre list
     */
    public void setCentroCostoPadreCentroCostoList(List<CentroCosto> centroCostoPadreCentroCostoList) {
        this.centroCostoPadreCentroCostoList = centroCostoPadreCentroCostoList;
    }   
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idCentroCosto == null) ? 0 : idCentroCosto.hashCode());
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
        CentroCosto other = (CentroCosto) obj;
        if (idCentroCosto == null) {
            if (other.idCentroCosto != null) {
                return false;
            }
        } else if (!idCentroCosto.equals(other.idCentroCosto)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CentroCosto [idCentroCosto=" + idCentroCosto + "]";
    }
   
}