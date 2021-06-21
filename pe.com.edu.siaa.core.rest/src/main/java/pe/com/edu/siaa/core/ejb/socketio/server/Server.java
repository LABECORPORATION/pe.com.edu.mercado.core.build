package pe.com.edu.siaa.core.ejb.socketio.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.google.gson.JsonObject;

import pe.com.builderp.core.facturacion.ejb.service.venta.local.VentaServiceLocal; 
import pe.com.edu.siaa.core.ejb.service.util.FechaUtil;
import pe.com.edu.siaa.core.ejb.util.txt.Utilidades; 
 
public class Server  {

	public static int contador;
 
	
	@EJB
	private static VentaServiceLocal ventaServiceImpl; 
	
	
	public static String cadenaConexion = "jdbc:postgresql://localhost:5432/BD_GRIFO_LR?" + "user=postgres&password=admin";
	
    public static void main(String[] args) throws InterruptedException {
        Configuration config = new Configuration();
        config.setHostname("190.117.151.181");
        config.setPort(3700); 

        final SocketIOServer server = new SocketIOServer(config);
        server.addConnectListener(new ConnectListener() {
            String demo = "";
            @Override
            public void onConnect(SocketIOClient client) {
                System.out.println("onConnected");
                try {
					demo =  contarListarVenta();
				} catch (Exception e) { 
					e.printStackTrace();
				}
                client.sendEvent("message", new ChatObject("", demo,FechaUtil.getDiaSemana(FechaUtil.obtenerFecha())-1) );
            }
        });
        server.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient client) {
                System.out.println("onDisconnected");
            }
        });
        server.addEventListener("send", ChatObject.class, new DataListener<ChatObject>() {
            @Override
            public void onData(SocketIOClient client, ChatObject data, AckRequest ackSender) throws Exception {
            	System.out.println("Ejecutando Socket " +data.isEsServicioSunat());
            	if(data.getUserName().equals(obtenerUsuario(data.getIdUsuario()))) {  
                	data.setContadorPediMoso(contarListarVentaMoso(data.getUserName())); 
            	}
            	JsonObject sfs = new JsonObject();
            	data.setContadorPedi(contarListarVenta()); 
            	data.setContadorPediMoso(data.getContadorPediMoso());
            	if(data.isEsServicioSunat()) {
                	data.setListadoSunatDatos(Utilidades.actualizarPantalla(sfs));	
            	}else {
            		data.setEsServicioSunat(false);
            	}
            	
 
            	
            	if(data.isEsCajaListado()) {
            		data.setListadoCaja(verificarCaja()); 
            		data.setListadoContadorEdit(verificarContadorEdit());
            	} 
            	
            	//data.setListadoMesaDTO(data.getListadoMesaDTO());
                server.getBroadcastOperations().sendEvent("message", data);
            }
        });
        System.out.println("Starting server...");
        server.start();
        Thread.sleep(Integer.MAX_VALUE);
        System.out.println("Server started");
        server.stop();
    }
    
    

    
	@SuppressWarnings("resource")
	public static String contarListarVentaMoso(String idtipoUs) throws Exception{
		String conta;
		int con = 0;
        Statement sentencia = null;
        Connection conexion = null;
        ResultSet resultado = null;
        try { 
            try { 
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            conexion = DriverManager.getConnection(cadenaConexion);
            sentencia = conexion.createStatement();
            String consultaSQL = "select * from factu.venta where (estadoventa='A') and usuarioCreacion="+"'"+idtipoUs+"'"+"";
            resultado = sentencia.executeQuery(consultaSQL);
            while (resultado.next()) { 
            	con=resultado.getRow(); 
            }
        } catch (Exception e) {
            e.printStackTrace();
            conexion = null;
        } finally {
            if (resultado != null) {
                try {
                    resultado.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        conta =Integer.toString(con);
		return conta;
	 }
	
	
	@SuppressWarnings("resource")
	public static String contarListarVenta() throws Exception{
		String conta;
		int con = 0;
        Statement sentencia = null;
        Connection conexion = null;
        ResultSet resultado = null;
        try { 
            try { 
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            conexion = DriverManager.getConnection(cadenaConexion);
            sentencia = conexion.createStatement();
            String consultaSQL = "select * from factu.venta where (estado='I' or estado='E')";
            resultado = sentencia.executeQuery(consultaSQL);
            while (resultado.next()) { 
            	con=resultado.getRow();
            }
        } catch (Exception e) {
            e.printStackTrace();
            conexion = null;
        } finally {
            if (resultado != null) {
                try {
                    resultado.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        conta =Integer.toString(con);
		return conta;
	 }
	
	
	@SuppressWarnings("resource")
	public static String obtenerUsuario(String idUsuario) throws Exception{
		String conta =""; 
        Statement sentencia = null;
        Connection conexion = null;
        ResultSet resultado = null;
        try { 
            try { 
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            conexion = DriverManager.getConnection(cadenaConexion);
            sentencia = conexion.createStatement();
            String consultaSQL = "select * from segur.usuario where idusuario="+"'"+idUsuario+"'";
            resultado = sentencia.executeQuery(consultaSQL);
            while (resultado.next()) {  
            	conta = resultado.getString("username"); 
            }
        } catch (Exception e) {
            e.printStackTrace();
            conexion = null;
        } finally {
            if (resultado != null) {
                try {
                    resultado.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } 
		return conta;
	 }
	 
	 
	 
	 
	 @SuppressWarnings("resource")
		public static String verificarCaja() throws Exception{ 
		    String conta;
			Integer con = 0;
	        Statement sentencia = null;
	        Connection conexion = null;
	        ResultSet resultado = null;
	        try { 
	            try { 
	                Class.forName("org.postgresql.Driver");
	            } catch (ClassNotFoundException ex) {
	                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
	            }
	            conexion = DriverManager.getConnection(cadenaConexion);
	            sentencia = conexion.createStatement();
	            String consultaSQL = "select count(*) from factu.venta where venta.estadoventa is null and estado!='A'";
	            resultado = sentencia.executeQuery(consultaSQL);
	            while (resultado.next()) { 
	            	con=resultado.getInt("count"); 
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            conexion = null;
	        } finally {
	            if (resultado != null) {
	                try {
	                    resultado.close();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	            if (sentencia != null) {
	                try {
	                    sentencia.close();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	            if (conexion != null) {
	                try {
	                    conexion.close();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        } 
	        conta =Integer.toString(con); 
			return conta;
		 } 
	 
	 
	 
	 @SuppressWarnings("resource")
		public static String verificarContadorEdit() throws Exception{ 
		    String conta;
			Integer con = 0;
	        Statement sentencia = null;
	        Connection conexion = null;
	        ResultSet resultado = null;
	        try { 
	            try { 
	                Class.forName("org.postgresql.Driver");
	            } catch (ClassNotFoundException ex) {
	                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
	            }
	            conexion = DriverManager.getConnection(cadenaConexion);
	            sentencia = conexion.createStatement();
	            String consultaSQL = "select SUM(contadoredit) from factu.venta where venta.estadoventa is null and estado!='A'";
	            resultado = sentencia.executeQuery(consultaSQL);
	            while (resultado.next()) { 
	            	con=resultado.getInt("sum"); 
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            conexion = null;
	        } finally {
	            if (resultado != null) {
	                try {
	                    resultado.close();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	            if (sentencia != null) {
	                try {
	                    sentencia.close();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	            if (conexion != null) {
	                try {
	                    conexion.close();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        } 
	        conta =Integer.toString(con); 
			return conta;
		 } 
	 
	 
	 @SuppressWarnings("resource")
		public static String obtenerCorrelativoBoleta() throws Exception{
			String conta =""; 
	        Statement sentencia = null;
	        Connection conexion = null;
	        ResultSet resultado = null;
	        try { 
	            try { 
	                Class.forName("org.postgresql.Driver");
	            } catch (ClassNotFoundException ex) {
	                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
	            }
	            conexion = DriverManager.getConnection(cadenaConexion);
	            sentencia = conexion.createStatement();
	            String consultaSQL = "select * from factu.tipodocsunatentidad  where idtipodocsunat=688 and serie='B001' ";
	            resultado = sentencia.executeQuery(consultaSQL);
	            while (resultado.next()) {  
	            	conta = resultado.getString("correla"); 
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            conexion = null;
	        } finally {
	            if (resultado != null) {
	                try {
	                    resultado.close();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	            if (sentencia != null) {
	                try {
	                    sentencia.close();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	            if (conexion != null) {
	                try {
	                    conexion.close();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        } 
			return conta;
		 }
}