package pe.com.edu.siaa.core.model.dto.reporte;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pe.com.edu.siaa.core.model.dto.BasePaginator;

/**
 * La Class ScriptSqlDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Tue Apr 18 09:56:50 COT 2017
 * @since SIAA-CORE 2.1
 */
public class ScriptSqlDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id script sql. */
    private String idScriptSql;
   
    /** El codigo. */
    private String codigo;
   
    /** El tipo sentencia. */
    private String tipoSentencia;
   
    /** El descripcion. */
    private String descripcion;
   
    /** El sql. */
    private String sql;
    
    /** El sql. */
    private String escuelaPertenece;
   
    /** El script sql parametros script sql list. */
    private List<ParametrosScriptSqlDTO> scriptSqlParametrosScriptSqlList = new ArrayList<ParametrosScriptSqlDTO>();
   
    /**
     * Instancia un nuevo script sqlDTO.
     */
    public ScriptSqlDTO() {
    }
   
   
    /**
     * Instancia un nuevo script sqlDTO.
     *
     * @param idScriptSql el id script sql
     * @param codigo el codigo
     * @param tipoSentencia el tipo sentencia
     * @param descripcion el descripcion
     * @param sql el sql
     */
    public ScriptSqlDTO(String idScriptSql, String codigo, String tipoSentencia, String descripcion, String sql, String escuelaPertenece ) {
        super();
        this.idScriptSql = idScriptSql;
        this.codigo = codigo;
        this.tipoSentencia = tipoSentencia;
        this.descripcion = descripcion;
        this.sql = sql;
        this.escuelaPertenece = escuelaPertenece;
    }
   
    //get y set
    /**
     * Obtiene id script sql.
     *
     * @return id script sql
     */
     public String getIdScriptSql() {
        return this.idScriptSql;
    }
    /**
     * Establece el id script sql.
     *
     * @param idScriptSql el new id script sql
     */
    public void setIdScriptSql(String idScriptSql) {
        this.idScriptSql = idScriptSql;
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
     * Obtiene tipo sentencia.
     *
     * @return tipo sentencia
     */
     public String getTipoSentencia() {
        return this.tipoSentencia;
    }
    /**
     * Establece el tipo sentencia.
     *
     * @param tipoSentencia el new tipo sentencia
     */
    public void setTipoSentencia(String tipoSentencia) {
        this.tipoSentencia = tipoSentencia;
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
    
    public String getEscuelaPertenece() {
		return escuelaPertenece;
	}


	public void setEscuelaPertenece(String escuelaPertenece) {
		this.escuelaPertenece = escuelaPertenece;
	}


	/**
     * Obtiene sql.
     *
     * @return sql
     */
     public String getSql() {
        return this.sql;
    }
    /**
     * Establece el sql.
     *
     * @param sql el new sql
     */
    public void setSql(String sql) {
        this.sql = sql;
    }
    /**
     * Establece el script sql parametros script sql list.
     *
     * @param scriptSqlParametrosScriptSqlList el new script sql parametros script sql list
     */
    public List<ParametrosScriptSqlDTO> getScriptSqlParametrosScriptSqlList(){
        return this.scriptSqlParametrosScriptSqlList;
    }
    /**
     * Establece el script sql list.
     *
     * @param scriptSqlList el new script sql list
     */
    public void setScriptSqlParametrosScriptSqlList(List<ParametrosScriptSqlDTO> scriptSqlParametrosScriptSqlList) {
        this.scriptSqlParametrosScriptSqlList = scriptSqlParametrosScriptSqlList;
    }   
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idScriptSql == null) ? 0 : idScriptSql.hashCode());
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
        ScriptSqlDTO other = (ScriptSqlDTO) obj;
        if (idScriptSql == null) {
            if (other.idScriptSql != null) {
                return false;
            }
        } else if (!idScriptSql.equals(other.idScriptSql)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ScriptSqlDTO [idScriptSql=" + idScriptSql + "]";
    }
   
}