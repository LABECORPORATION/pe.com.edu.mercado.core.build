package pe.com.edu.siaa.core.model.jpa.reporte;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class ParametrosScriptSql.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 09:57:01 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "ParametrosScriptSql", schema = ConfiguracionEntityManagerUtil.ESQUEMA_REPORTE)
public class ParametrosScriptSql implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id parametros script sql. */
    @Id
    @Column(name = "idParametrosScriptSql" , length = 32)
    private String idParametrosScriptSql;
   
    /** El script sql. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idScriptSql", referencedColumnName = "idScriptSql")
    private ScriptSql scriptSql;
   
    /** El descripcion. */
    @Column(name = "descripcion" , length = 100)
    private String descripcion;
   
    /** El codigo parametro. */
    @Column(name = "codigoParametro" , length = 100)
    private String codigoParametro;
   
    /** El valor defecto. */
    @Column(name = "valorDefecto" , length = 200)
    private String valorDefecto;
   
    /**
     * Instancia un nuevo parametros script sql.
     */
    public ParametrosScriptSql() {
    }
   
   
    /**
     * Instancia un nuevo parametros script sql.
     *
     * @param idParametrosScriptSql el id parametros script sql
     * @param scriptSql el script sql
     * @param descripcion el descripcion
     * @param codigoParametro el codigo parametro
     * @param valorDefecto el valor defecto
     */
    public ParametrosScriptSql(String idParametrosScriptSql, ScriptSql scriptSql,String descripcion, String codigoParametro, String valorDefecto ) {
        super();
        this.idParametrosScriptSql = idParametrosScriptSql;
        this.scriptSql = scriptSql;
        this.descripcion = descripcion;
        this.codigoParametro = codigoParametro;
        this.valorDefecto = valorDefecto;
    }
   
    //get y set
    /**
     * Obtiene id parametros script sql.
     *
     * @return id parametros script sql
     */
     public String getIdParametrosScriptSql() {
        return this.idParametrosScriptSql;
    }
    /**
     * Establece el id parametros script sql.
     *
     * @param idParametrosScriptSql el new id parametros script sql
     */
    public void setIdParametrosScriptSql(String idParametrosScriptSql) {
        this.idParametrosScriptSql = idParametrosScriptSql;
    }
    /**
     * Obtiene script sql.
     *
     * @return script sql
     */
     public ScriptSql getScriptSql() {
        return this.scriptSql;
    }
    /**
     * Establece el script sql.
     *
     * @param scriptSql el new script sql
     */
    public void setScriptSql(ScriptSql scriptSql) {
        this.scriptSql = scriptSql;
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
     * Obtiene codigo parametro.
     *
     * @return codigo parametro
     */
     public String getCodigoParametro() {
        return this.codigoParametro;
    }
    /**
     * Establece el codigo parametro.
     *
     * @param codigoParametro el new codigo parametro
     */
    public void setCodigoParametro(String codigoParametro) {
        this.codigoParametro = codigoParametro;
    }
    /**
     * Obtiene valor defecto.
     *
     * @return valor defecto
     */
     public String getValorDefecto() {
        return this.valorDefecto;
    }
    /**
     * Establece el valor defecto.
     *
     * @param valorDefecto el new valor defecto
     */
    public void setValorDefecto(String valorDefecto) {
        this.valorDefecto = valorDefecto;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idParametrosScriptSql == null) ? 0 : idParametrosScriptSql.hashCode());
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
        ParametrosScriptSql other = (ParametrosScriptSql) obj;
        if (idParametrosScriptSql == null) {
            if (other.idParametrosScriptSql != null) {
                return false;
            }
        } else if (!idParametrosScriptSql.equals(other.idParametrosScriptSql)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ParametrosScriptSql [idParametrosScriptSql=" + idParametrosScriptSql + "]";
    }
   
}