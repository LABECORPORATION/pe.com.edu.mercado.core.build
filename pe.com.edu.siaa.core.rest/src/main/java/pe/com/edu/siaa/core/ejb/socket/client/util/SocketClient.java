package pe.com.edu.siaa.core.ejb.socket.client.util;

import java.net.URISyntaxException;

import org.json.JSONObject;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import pe.com.edu.siaa.core.ejb.socketio.server.ChatObject;

public class SocketClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Socket socket = IO.socket("http://localhost:9092");
			
			 Emitter.Listener onLogin = new Emitter.Listener() {
				 @Override
				 public void call(final Object... args) {
					 JSONObject data = (JSONObject) args[0];
					 System.out.println("Logged in"+ data.toString());
				 }
			 };

		
			

			 socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

				    @Override
				    public void call(Object... args) {
				    	System.out.println(Socket.EVENT_CONNECT + args);
				    	
				    	if (socket.connected()) {
							  System.out.println("conecto la conexion");
							  ChatObject datax = new ChatObject();
						    	datax.setMessage("Soy cliente java ");
						    	datax.setUserName("admin11");
						    	JSONObject data = new JSONObject(datax);
						      socket.emit("chatevent", data);
						     // socket.emit(Socket.EVENT_CONNECT_ERROR, data);
						     socket.disconnect();
						  } else {
							  System.out.println("fallo la conexion");
						  }
				    }

			}).on("event", new Emitter.Listener() {

				    @Override
				    public void call(Object... args) {
				    	System.out.println("event" + args);
				    }

		   }).on("chateventx", new Emitter.Listener() {

					    @Override
					    public void call(Object... args) {
					    	JSONObject obj = (JSONObject)args[0];
					    	System.out.println("chatevent" + obj);
					    }

					  }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

				    @Override
				    public void call(Object... args) {
				    	//JSONObject obj = (JSONObject)args[0];
				    	System.out.println(Socket.EVENT_DISCONNECT + args);
				    }

				  });
			 socket.on(Socket.EVENT_CONNECT_TIMEOUT, new Emitter.Listener() {
		            @Override
		            public void call(Object... args) {
		                System.out.println("connect timeout" + args);
		            }
		        }).on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
		            @Override
		            public void call(Object... args) {
		                System.out.println("connect error" + args);
		               // client.dispatcher().executorService().shutdown();
		            }
		        }).on("chatevent", onLogin);
				  socket.connect();
				  
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}

}
