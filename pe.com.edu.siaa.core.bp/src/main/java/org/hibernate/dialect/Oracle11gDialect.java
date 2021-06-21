package org.hibernate.dialect;

import java.sql.Types;

import org.hibernate.type.StandardBasicTypes;
  
/** 
 * @author lysming 
 * 
 */  
public class Oracle11gDialect extends Oracle10gDialect {  
    /*public Oracle11gDialect() {  
        super();  
        registerColumnType( Types.NVARCHAR, "nvarchar2($l)" );
        registerHibernateType(Types.NVARCHAR, StandardBasicTypes.STRING.getName() );
    }  */
	public Oracle11gDialect() {
	    registerFunction("fts", new PostgreSQLFullTextSearchFunction());
        registerColumnType( Types.NVARCHAR, "nvarchar2($l)" );
        registerHibernateType(Types.NVARCHAR, StandardBasicTypes.STRING.getName() );
	} 
}  
