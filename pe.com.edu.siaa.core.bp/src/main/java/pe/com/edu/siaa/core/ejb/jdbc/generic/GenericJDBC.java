package pe.com.edu.siaa.core.ejb.jdbc.generic;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.util.FechaUtil;
import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.model.jdbc.vo.ScriptSqlResulJDBCVO;

/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class GenericJDBC.
 *
 * @author ndavilal
 * @version 1.0 , 24/05/2016
 * @param <T> el tipo generico
 * @since SIAA-CORE 2.1
 */
public class GenericJDBC<T> {
	
	/** La log. */
	private static Logger log = Logger.getLogger(GenericJDBC.class);
	
	/**
	 * Execute query.
	 *
	 * @param <T> el tipo generico
	 * @param sql el sql
	 * @param parametros el parametros
	 * @param entityClassEntity el entity class entity
	 * @return the t
	 * @throws Exception the exception
	 */
	public static <T> T executeQuery(StringBuilder sql,Map<String, Object> parametros,Class<T> entityClassEntity) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityVO(ejecutarScriptSql(sql,parametros,null,true,false), entityClassEntity);
	}
	
	/**
	 * Execute query.
	 *
	 * @param sp el sql
	 * @param parametros el parametros
	 * @param isProcedure el is procedure
	 * @return the script sql resul jdbcvo
	 * @throws Exception the exception
	 */
	public static ScriptSqlResulJDBCVO  executeQuerySP(StringBuilder sp) throws Exception {
		List<Integer> parametroType = new ArrayList<Integer>();
		parametroType.add(java.sql.Types.VARCHAR);
		return ejecutarScriptSqlSP(sp,null,parametroType, new ArrayList<Object>());
	}
	
	public static ScriptSqlResulJDBCVO  executeQuerySP(String sp) throws Exception {
		List<Integer> parametroType = new ArrayList<Integer>();
		parametroType.add(java.sql.Types.VARCHAR);
		
		return ejecutarScriptSqlSP(definirParametroCall(sp,parametroType.size()),null,parametroType, new ArrayList<Object>());
	}
	protected static StringBuilder definirParametroCall(String spEjecutar, int cantidad) throws Exception {
		StringBuilder resultado = new StringBuilder();
		resultado.append("{call " + spEjecutar +"(");
		int size = cantidad;
		for (int i=1; i <= size; i++ ) {
			resultado.append("?");
			if (!(i == size)) {
				resultado.append(",");
			}
		}
		resultado.append(")} ");
		return resultado;
	}
	/**
	 * Execute query.
	 *
	 * @param sql el sql
	 * @param parametros el parametros
	 * @param isProcedure el is procedure
	 * @param parametroOutType el parametro out type
	 * @param parametroInType el parametro in type
	 * @return the script sql resul jdbcvo
	 * @throws Exception the exception
	 */
	public static ScriptSqlResulJDBCVO  executeQuerySP(StringBuilder sql,String JNDIConexion,List<Integer> parametroOutType,List<Object> parametroInType) throws Exception {
		return ejecutarScriptSqlSP(sql,JNDIConexion,parametroOutType,parametroInType);
	}
	public static ScriptSqlResulJDBCVO  executeQuerySP(String sp,String JNDIConexion,List<Integer> parametroOutType,List<Object> parametroInType) throws Exception {
		return ejecutarScriptSqlSP(definirParametroCall(sp,parametroOutType.size() + parametroInType.size()),JNDIConexion,parametroOutType,parametroInType);
	}
	
	/**
	 * Execute query.
	 *
	 * @param <T> el tipo generico
	 * @param sql el sql
	 * @param parametros el parametros
	 * @param JNDIConexion el JNDI conexion
	 * @param entityClassEntity el entity class entity
	 * @return the t
	 * @throws Exception the exception
	 */
	public static <T> T executeQuery(StringBuilder sql,Map<String, Object> parametros,String JNDIConexion,Class<T> entityClassEntity) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityVO(ejecutarScriptSql(sql,parametros,JNDIConexion,true,false), entityClassEntity);
	}
	
	/**
	 * Execute query list.
	 *
	 * @param <T> el tipo generico
	 * @param sql el sql
	 * @param parametros el parametros
	 * @param entityClassEntity el entity class entity
	 * @return the list
	 * @throws Exception the exception
	 */
	public static <T> List<T> executeQueryList(StringBuilder sql,Map<String, Object> parametros,Class<T> entityClassEntity) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityListVO(ejecutarScriptSql(sql,parametros,null,true,false), entityClassEntity);
	}
	
	/**
	 * Execute query list.
	 *
	 * @param <T> el tipo generico
	 * @param sql el sql
	 * @param parametros el parametros
	 * @param entityClassEntity el entity class entity
	 * @param formatoMap el formato map
	 * @return the list
	 * @throws Exception the exception
	 */
	public static <T> List<T> executeQueryList(StringBuilder sql,Map<String, Object> parametros,Class<T> entityClassEntity,Map<String,String> formatoMap) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityListVO(ejecutarScriptSql(sql,parametros,null,true,false), entityClassEntity,formatoMap);
	}
	
	/**
	 * Execute query list.
	 *
	 * @param sql el sql
	 * @param parametros el parametros
	 * @return the list
	 * @throws Exception the exception
	 */
	public static  List<Object[]> executeQueryList(StringBuilder sql,Map<String, Object> parametros) throws Exception {
		return ejecutarScriptSql(sql,parametros,null,true,true).getListaDataObject();
	}
	
	/**
	 * Execute query list.
	 *
	 * @param <T> el tipo generico
	 * @param sql el sql
	 * @param parametros el parametros
	 * @param JNDIConexion el JNDI conexion
	 * @param entityClassEntity el entity class entity
	 * @return the list
	 * @throws Exception the exception
	 */
	public static <T> List<T> executeQueryList(StringBuilder sql,Map<String, Object> parametros,String JNDIConexion,Class<T> entityClassEntity) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityListVO(ejecutarScriptSql(sql,parametros,JNDIConexion,true,false), entityClassEntity);
	}
	
	/**
	 * Execute query.
	 *
	 * @param sql el sql
	 * @param parametros el parametros
	 * @return the script sql resul jdbcvo
	 * @throws Exception the exception
	 */
	public static ScriptSqlResulJDBCVO executeQuery(StringBuilder sql,Map<String, Object> parametros) throws Exception {
		return ejecutarScriptSql(sql,parametros,null,true,false);
	}
	
	/**
	 * Execute query.
	 *
	 * @param sql el sql
	 * @param parametros el parametros
	 * @param JNDIConexion el JNDI conexion
	 * @return the script sql resul jdbcvo
	 * @throws Exception the exception
	 */
	public static ScriptSqlResulJDBCVO executeQuery(StringBuilder sql,Map<String, Object> parametros,String JNDIConexion) throws Exception {
		return ejecutarScriptSql(sql,parametros,JNDIConexion,true,false);
	}
	
	/**
	 * Execute update.
	 *
	 * @param sql el sql
	 * @param parametros el parametros
	 * @return the script sql resul jdbcvo
	 * @throws Exception the exception
	 */
	public static ScriptSqlResulJDBCVO executeUpdate(StringBuilder sql,Map<String, Object> parametros) throws Exception {
		return ejecutarScriptSql(sql,parametros,null,false,false);
	}
	
	/**
	 * Execute update.
	 *
	 * @param sql el sql
	 * @param parametros el parametros
	 * @param JNDIConexion el JNDI conexion
	 * @return the script sql resul jdbcvo
	 * @throws Exception the exception
	 */
	public static ScriptSqlResulJDBCVO executeUpdate(StringBuilder sql,Map<String, Object> parametros,String JNDIConexion) throws Exception {
		return ejecutarScriptSql(sql,parametros,JNDIConexion,false,false);
	}
	
	/**
	 * Ejecutar script sql.
	 *
	 * @param sql el sql
	 * @param parametros el parametros
	 * @param JNDIConexion el JNDI conexion
	 * @param isConsulta el is consulta
	 * @param devolverOject el devolver oject
	 * @param isProcedure el is procedure
	 * @param parametroOutType el parametro out type
	 * @return the script sql resul jdbcvo
	 * @throws Exception the exception
	 */
	private static ScriptSqlResulJDBCVO ejecutarScriptSql(StringBuilder sql,Map<String, Object> parametros,String JNDIConexion, boolean isConsulta, boolean devolverOject) throws Exception {
		ScriptSqlResulJDBCVO resultado = new ScriptSqlResulJDBCVO();
		List<String> listaHeader = new ArrayList<String>();
		List<Map<String,Object>> listaData = new ArrayList<Map<String,Object>>();
		List<Object[]> listaDataOject = new ArrayList<Object[]>();
		String jpaql = sql.toString();
		int i = 1;		
		Connection connection = null;
		Statement  st = null;
		ResultSet rs = null;
		if (JNDIConexion == null) {
			connection = getConexionDS();
		} else {
			connection = getConexionDS(JNDIConexion);
		}
		if (parametros != null) {
			for (Map.Entry<String, Object> objParamMap : parametros.entrySet()) {
				if (objParamMap.getValue() != null) {
					if (objParamMap.getValue().getClass().isAssignableFrom(String.class)) {
						jpaql  = jpaql.replaceAll(":" + objParamMap.getKey() , "'" + objParamMap.getValue()  + "'");
					} else if (objParamMap.getValue().getClass().isAssignableFrom(Character.class)) {
						jpaql  = jpaql.replaceAll(":" + objParamMap.getKey() , "'" + objParamMap.getValue()  + "'");
					} else if (objParamMap.getValue().getClass().isAssignableFrom(Date.class)) { 
						String formato = "yyyyMMdd HH:mm:ss";
						String formatoBD = "yyyymmdd HH24:MI:SS";
						String dateValue = "to_date('" + FechaUtil.obtenerFechaFormatoPersonalizado((Date)objParamMap.getValue(),formato)   + "','" + formatoBD + "')";
						jpaql  = jpaql.replaceAll(":" + objParamMap.getKey() , dateValue);
					} else	if (objParamMap.getValue().getClass().isAssignableFrom(ArrayList.class)) { 
						jpaql  = jpaql.replaceAll(":" + objParamMap.getKey() ,  objParamMap.getValue().toString().replace("[", "").replace("]", "").trim()  + "");
					} else {
						jpaql  = jpaql.replaceAll(":" + objParamMap.getKey() ,  objParamMap.getValue()  + "");
					}
					
				} else {
					jpaql  = jpaql.replaceAll(":" + objParamMap.getKey() ,  "''");
				}
			}
		}
		try {
				st = connection.createStatement();
				if (isConsulta) {
					log.info("jpaql " + jpaql);
					rs = st.executeQuery(jpaql.toString());
					ResultSetMetaData metadata = rs.getMetaData();
				    for ( i = 0; i < metadata.getColumnCount(); i++) {
				        listaHeader.add(metadata.getColumnLabel(i + 1).toUpperCase());
				    }
				    while (rs.next()) {
				        Map<String,Object> dataMap = new HashMap<String,Object>();
				        for ( i = 0; i < metadata.getColumnCount(); i++) {
				        	String nombreCampo  = metadata.getColumnLabel(i + 1);
				        	Object value = null;
				        	if ("IDU".equals(nombreCampo) || "ROWID".equals(nombreCampo)) {
				        		value = rs.getString(i + 1);
				        	} else {
				        		value = rs.getObject(i + 1);
				        	}
				        	value = value == null ? "" : value;
				        	dataMap.put(metadata.getColumnLabel(i + 1).toUpperCase(), value);
				        }
				        listaData.add(dataMap);
				        if (devolverOject) {
					        Object[] data = new Object[metadata.getColumnCount()];
					        for ( i = 0; i < metadata.getColumnCount(); i++) {
					        	Object value = rs.getObject(i + 1);
					        	data[i] = value == null ? "" : value;
					        }
					        listaDataOject.add(data);
				        }
			       }
				} else {
					log.info("jpaql " + jpaql);
					st.executeUpdate(jpaql);
				} 
			 
		}  catch (Exception e) {
			resultado.setTieneError(true);
			resultado.setMensajeError(e.getMessage() + " \n " + e.toString());
			throw e;
		} finally {
			if (st != null) {
				st.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (connection != null) {
				connection.close();
			}
			
		}
		resultado.setListaDataObject(listaDataOject);
		resultado.setListaData(listaData);
		resultado.setListaHeader(listaHeader);
	  return resultado;
	}
	
	public static ScriptSqlResulJDBCVO executeQueryPreparedStatement(StringBuilder sql,Map<String, Object> parametros,String JNDIConexion) throws Exception {
		return ejecutarScriptSqlPreparedStatement(sql,parametros,JNDIConexion,true,false);
	}
	public static ScriptSqlResulJDBCVO executeQueryPreparedStatement(StringBuilder sql,Map<String, Object> parametros) throws Exception {
		return ejecutarScriptSqlPreparedStatement(sql,parametros,null,true,false);
	}
	public static ScriptSqlResulJDBCVO executeUpdatePreparedStatement(StringBuilder sql,Map<String, Object> parametros,String JNDIConexion) throws Exception {
		return ejecutarScriptSqlPreparedStatement(sql,parametros,JNDIConexion,false,false);
	}
	public static ScriptSqlResulJDBCVO executeUpdatePreparedStatement(StringBuilder sql,Map<String, Object> parametros) throws Exception {
		return ejecutarScriptSqlPreparedStatement(sql,parametros,null,false,false);
	}
	/*
	 * sql = "select * from mytabla where (fecha=? or fecha=?) and fechaFin<? "
	 * parametros.put("1","01/01/2010");
	 * parametros.put("2","01/01/2011");
	 * parametros.put("3","01/01/2015");
	 * */
	private static ScriptSqlResulJDBCVO ejecutarScriptSqlPreparedStatement(StringBuilder sql, Map<String, Object> parametros,String JNDIConexion, boolean isConsulta, boolean devolverOject) throws Exception {
        ScriptSqlResulJDBCVO resultado = new ScriptSqlResulJDBCVO();
        List<String> listaHeader = new ArrayList<String>();
        List<Map<String, Object>> listaData = new ArrayList<Map<String, Object>>();
        List<Object[]> listaDataOject = new ArrayList<Object[]>();
        String jpaql = sql.toString();
        int i = 1;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection connection = null;
        if (JNDIConexion == null) {
			connection = getConexionDS();
		} else {
			connection = getConexionDS(JNDIConexion);
		}
        try {
            pst = connection.prepareStatement(jpaql.toLowerCase());
            if (parametros != null) {
                for (Map.Entry<String, Object> objParamMap : parametros.entrySet()) {

                    if (objParamMap.getValue() != null) {
                        if (objParamMap.getValue().getClass().isAssignableFrom(String.class)) {
                            pst.setString(Integer.parseInt(objParamMap.getKey()), (String) objParamMap.getValue());
                        } else if (objParamMap.getValue().getClass().isAssignableFrom(Character.class)) {
                            pst.setString(Integer.parseInt(objParamMap.getKey()),  objParamMap.getValue().toString());
                        } else if (objParamMap.getValue().getClass().isAssignableFrom(Date.class)) {
                        	 java.sql.Timestamp dateTime = new Timestamp(((Date)objParamMap.getValue()).getTime());
                            pst.setTimestamp(Integer.parseInt(objParamMap.getKey()), dateTime);
                        } else if (objParamMap.getValue().getClass().isAssignableFrom(java.sql.Timestamp.class)) {
                            java.sql.Timestamp dateTime = (java.sql.Timestamp) objParamMap.getValue();
                            Calendar start = Calendar.getInstance();
                            start.setTimeInMillis(dateTime.getTime());
                            pst.setTimestamp(Integer.parseInt(objParamMap.getKey()), dateTime);
                        } else if (objParamMap.getValue().getClass().isAssignableFrom(ArrayList.class)) {
                            pst.setObject(Integer.parseInt(objParamMap.getKey()), objParamMap.getValue().toString().replace("[", "").replace("]", "").trim() + "");
                        }   else if (objParamMap.getValue().getClass().isAssignableFrom(Integer.class)) {
                        	pst.setInt(Integer.parseInt(objParamMap.getKey()), (Integer) objParamMap.getValue());
                        } else if (objParamMap.getValue().getClass().isAssignableFrom(Long.class)) {
                        	pst.setLong(Integer.parseInt(objParamMap.getKey()), (Long) objParamMap.getValue());
                        } else if (objParamMap.getValue().getClass().isAssignableFrom(Double.class)) {
                        	pst.setDouble(Integer.parseInt(objParamMap.getKey()), (Double) objParamMap.getValue());
                        } else if (objParamMap.getValue().getClass().isAssignableFrom(Float.class)) {
                        	pst.setFloat(Integer.parseInt(objParamMap.getKey()), (Float) objParamMap.getValue());
                        }   else if (objParamMap.getValue().getClass().isAssignableFrom(BigDecimal.class)) {
                        	 pst.setBigDecimal(Integer.parseInt(objParamMap.getKey()), (BigDecimal) objParamMap.getValue());
                        } else {
                            pst.setObject(Integer.parseInt(objParamMap.getKey()), objParamMap.getValue());
                        }
                    } else {
                        pst.setObject(Integer.parseInt(objParamMap.getKey()), objParamMap.getValue());
                    }
                }
            }
            if (isConsulta) {
                rs = pst.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                for (i = 0; i < metadata.getColumnCount(); i++) {
                    listaHeader.add(metadata.getColumnLabel(i + 1).toUpperCase());
                }
                while (rs.next()) {
                    Map<String, Object> dataMap = new HashMap<String, Object>();
                    for (i = 0; i < metadata.getColumnCount(); i++) {
                        String nombreCampo = metadata.getColumnLabel(i + 1);
                        Object value = null;
                        if ("rownum".equalsIgnoreCase(nombreCampo)) {
                            continue;
                        }
                        if ("IDU".equalsIgnoreCase(nombreCampo) || "ROWID".equalsIgnoreCase(nombreCampo)) {
                            value = rs.getString(i + 1);
                        } else {
                            value = rs.getObject(i + 1);
                        }
                        value = value == null ? "" : value;
                        dataMap.put(metadata.getColumnLabel(i + 1).toUpperCase(), value);
                    }
                    listaData.add(dataMap);
                    if (devolverOject) {
                        Object[] data = new Object[metadata.getColumnCount()];
                        for (i = 0; i < metadata.getColumnCount(); i++) {
                            Object value = rs.getObject(i + 1);
                            data[i] = value == null ? "" : value;
                        }
                        listaDataOject.add(data);
                    }
                }
            } else {
                int dataProcesado = pst.executeUpdate();
                resultado.setExecuteUpdate(dataProcesado);
            }

        } catch (Exception e) {
            System.err.println("jpaql " + jpaql);
            e.printStackTrace();
            resultado.setTieneError(true);
            resultado.setMensajeError(e.getMessage() + " \n " + e.toString());
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }

        }
        resultado.setListaDataObject(listaDataOject);
        resultado.setListaData(listaData);
        resultado.setListaHeader(listaHeader);
        return resultado;
    }
	
	
	private static ScriptSqlResulJDBCVO ejecutarScriptSqlSP(StringBuilder sql,String JNDIConexion,List<Integer> parametroOutType,List<Object> parametroInType) throws Exception {
		ScriptSqlResulJDBCVO resultado = new ScriptSqlResulJDBCVO();
		List<String> listaHeader = new ArrayList<String>();
		List<Map<String,Object>> listaData = new ArrayList<Map<String,Object>>();
		List<Object[]> listaDataOject = new ArrayList<Object[]>();
		String jpaql = sql.toString();
		Connection connection = null;
		PreparedStatement  st = null;
		ResultSet rs = null;
		if (JNDIConexion == null) {
			connection = getConexionDS();
		} else {
			connection = getConexionDS(JNDIConexion);
		}
		try {
				log.info("jpaql " + jpaql);
				CallableStatement cst = connection.prepareCall(sql.toString());
				int index = 1;
				List<Integer> listaIndexOutPut = new ArrayList<Integer>();
				for (Object objParamIn : parametroInType) {
					  if (objParamIn instanceof Boolean) {
						cst.setBoolean(index, (Boolean)objParamIn);
					  } else if (objParamIn instanceof BigDecimal) {
						cst.setBigDecimal(index,(BigDecimal)objParamIn);
					  } else  if (objParamIn instanceof Long) {
						cst.setLong(index,(Long)objParamIn);
					  } else if (objParamIn instanceof Integer) {
						cst.setInt(index,(Integer)objParamIn);
					  } else if (objParamIn instanceof Date) {
						Date value = (Date)objParamIn;
						java.sql.Date jdbcDate = java.sql.Date.class.isInstance( value ) ? ( java.sql.Date ) value : new java.sql.Date( value.getTime() );
						cst.setDate(index, jdbcDate);
					  } else if (objParamIn instanceof String) {
						cst.setString(index, (String)objParamIn);
					  } else if (objParamIn instanceof Character) {
						cst.setString(index, objParamIn + "");
					  } else {
						cst.setObject(index, objParamIn);
					  }
					 index++;
				}
				for (Integer paramMap : parametroOutType) {
					cst.registerOutParameter(index, paramMap);
					listaIndexOutPut.add(index);
					index++;
				}				
				cst.execute();
				if (parametroOutType.size() > 0) {
					Map<String,Object> dataMap = new HashMap<String,Object>();
					int indexResul = 1;
					for (Integer indexOutPut : listaIndexOutPut) {
						String resultadoSp = cst.getString(indexOutPut);
					    dataMap.put("resultado".toUpperCase() + indexResul , resultadoSp);
					    indexResul++;
					}					
				    listaData.add(dataMap);
				}		   				
			 
		}  catch (Exception e) {
			resultado.setTieneError(true);
			resultado.setMensajeError(e.getMessage() + " \n " + e.toString());
			throw e;
		} finally {
			if (st != null) {
				st.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (connection != null) {
				connection.close();
			}
			
		}
		resultado.setListaDataObject(listaDataOject);
		resultado.setListaData(listaData);
		resultado.setListaHeader(listaHeader);
	  return resultado;
	}
	
	/**
	 * Gets the conexion ds.
	 *
	 * @return the conexion ds
	 * @throws Exception the exception
	 */
	public static Connection getConexionDS() throws Exception {
		InitialContext ctx;
		ctx = new InitialContext();
		DataSource dataSource = (DataSource) ctx.lookup("java:jboss/datasources/siaaDS");
		return dataSource.getConnection();
	}
	
	/**
	 * Gets the conexion ds.
	 *
	 * @param JNDIConexion the JNDI conexion
	 * @return the conexion ds
	 * @throws Exception the exception
	 */
	public static Connection getConexionDS(String JNDIConexion) throws Exception {
		InitialContext ctx;
		ctx = new InitialContext();
		DataSource dataSource = (DataSource) ctx.lookup("java:jboss/datasources/" + JNDIConexion);
		return dataSource.getConnection();
	}
}
