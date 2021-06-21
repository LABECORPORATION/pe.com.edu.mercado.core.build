package pe.com.edu.siaa.core.ejb.factory;
 import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

import pe.com.edu.siaa.core.ejb.util.log.Logger;

public class DBManager implements Serializable {
	/**
	 * 
	 */
	private Logger log = Logger.getLogger(DBManager.class);
	private static final long serialVersionUID = 1L;
	private String url = "";
	private String driver = "";
	private String userName = "";
	private String password = "";
	
     static String cadenaConexion = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP) (HOST = 10.160.120.160) (PORT = 1525)) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = TRONDES.mapfreperu.com) ))";

     public DBManager() {
		super();
		this.url = cadenaConexion;
		this.driver = "oracle.jdbc.driver.OracleDriver";
		this.userName = "tron2000"; 
		this.password = "tron2000";
	}
     
	public DBManager(String url, String driver, String userName, String password) {
		super();
		this.url = url;
		this.driver = driver;
		this.userName = userName;
		this.password = password;
	}

	public Connection getConnection() throws Exception {
            Connection conexion = null;
            Class.forName(driver);//driver
            conexion = DriverManager.getConnection(url,userName,password);
            return conexion;
    }
    
	public void closeConnection(Connection conexion) {
       try {
             conexion.close();
       } catch (Exception e) {
              conexion = null;
       }
     
    }
	
     //get y set
	public String getUrl() {
		return url;
	}  

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
     
}