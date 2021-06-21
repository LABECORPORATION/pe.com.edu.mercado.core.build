package pe.com.edu.siaa.core.model.dto.reporte;

import java.io.Serializable;

import pe.com.edu.siaa.core.model.dto.BasePaginator;

/**
 * La Class ParametrosScriptSqlDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Tue Apr 18 09:57:01 COT 2017
 * @since SIAA-CORE 2.1
 */
public class ParametrosScriptSqlDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id parametros script sql. */
    private String idParametrosScriptSql;
   
    /** El script sql. */
    private ScriptSqlDTO scriptSql;
   
    /** El descripcion. */
    private String descripcion;
   
    /** El codigo parametro. */
    private String codigoParametro;
   
    /** El valor defecto. */
    private String valorDefecto;
   
    /**
     * Instancia un nuevo parametros script sqlDTO.
     */
    public ParametrosScriptSqlDTO() {
    }
   
   
    /**
     * Instancia un nuevo parametros script sqlDTO.
     *
     * @param idParametrosScriptSql el id parametros script sql
     * @param scriptSql el script sql
     * @param descripcion el descripcion
     * @param codigoParametro el codigo parametro
     * @param valorDefecto el valor defecto
     */
    public ParametrosScriptSqlDTO(String idParametrosScriptSql, ScriptSqlDTO scriptSql,String descripcion, String codigoParametro, String valorDefecto ) {
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
     public ScriptSqlDTO getScriptSql() {
        return this.scriptSql;
    }
    /**
     * Establece el script sql.
     *
     * @param scriptSql el new script sql
     */
    public void setScriptSql(ScriptSqlDTO scriptSql) {
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
        ParametrosScriptSqlDTO other = (ParametrosScriptSqlDTO) obj;
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
        return "ParametrosScriptSqlDTO [idParametrosScriptSql=" + idParametrosScriptSql + "]";
    }
   
}