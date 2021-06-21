package pe.com.builderp.core.facturacion.ejb.service.rest.venta.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.json.JSONArray;
import org.json.JSONObject;

import pe.com.edu.siaa.core.ejb.dao.generic.impl.HibernateUtil;
import pe.com.builderp.core.contabilidad.model.vo.RegistroAsientoFiltroVO;
import pe.com.builderp.core.facturacion.ejb.service.venta.impl.VentaServiceImpl;
import pe.com.builderp.core.facturacion.ejb.service.venta.local.VentaServiceLocal;
import pe.com.builderp.core.facturacion.model.dto.venta.CajaDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.ClienteDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.DetalleVentaDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.VentaDTO;
import pe.com.builderp.core.facturacion.model.vo.venta.VentaGraficoVO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.seguridad.jwt.rsa.util.AppHTTPHeaderNames;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.ejb.util.cache.AppAuthenticator;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.model.vo.CvPerfilesVO;
import pe.com.edu.siaa.core.model.vo.SunatDatosVO;
import pe.com.edu.siaa.core.model.vo.VentaFiltroVO;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class VentaRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:24 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/ventaRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class VentaRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient VentaServiceLocal ventaServiceLocal;
	
	@POST
	public ResultadoRestVO<VentaDTO> registrarVenta(@Context HttpHeaders httpHeaders,VentaDTO venta) throws Exception {
		ResultadoRestVO<VentaDTO> resultado = new ResultadoRestVO<VentaDTO>();
		String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		String ip = httpHeaders.getHeaderString( AppHTTPHeaderNames.ORIGIN );
		venta.setIpAcceso(ip);
		venta.setServiceKey(serviceKey);
		venta.setAuthToken(authToken);		
		 try {
			 resultado.setObjetoResultado(ventaServiceLocal.registrarVenta(venta));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<VentaDTO> eliminar(@PathParam("id") String idVenta) throws Exception {
		ResultadoRestVO<VentaDTO> resultado = new ResultadoRestVO<VentaDTO>();
		VentaDTO venta = new VentaDTO();
		venta.setIdVenta(idVenta);		
		return resultado;
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<VentaDTO> listarVenta(@Context UriInfo info){
		ResultadoRestVO<VentaDTO> resultado = new ResultadoRestVO<VentaDTO>();
		VentaDTO venta = transferUriInfo(info);
		 try {
			resultado.setListaResultado(ventaServiceLocal.listarVenta(venta));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<VentaDTO> contarVenta(@Context UriInfo info){
		ResultadoRestVO<VentaDTO> resultado = new ResultadoRestVO<VentaDTO>();
		VentaDTO venta = transferUriInfo(info);
		 try {
			 resultado.setContador(ventaServiceLocal.contarListarVenta(venta));
			 if (resultado.isData()) {
				resultado.setListaResultado(ventaServiceLocal.listarVenta(venta));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@GET
    @Path("/")
	public ResultadoRestVO<VentaDTO> inicializarVenta(@Context UriInfo info) throws Exception {
	     VentaDTO venta = transferUriInfo(info);
		 ResultadoRestVO<VentaDTO> resultado = new ResultadoRestVO<VentaDTO>();
		 try {
    		resultado.setObjetoResultado(venta);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@POST
    @Path("/generarReporteRegistroVenta")
	public ResultadoRestVO<String> generarReporteRegistroVenta(@Context HttpHeaders httpHeaders,RegistroAsientoFiltroVO registroVentaFiltro){
		ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
		String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		registroVentaFiltro.setAuthToken(authToken);
		registroVentaFiltro.setServiceKey(serviceKey);
		 try {
    		resultado.setObjetoResultado(ventaServiceLocal.generarReporteRegistroVenta(registroVentaFiltro));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@POST
    @Path("/generarReporteRegistroVentaTXT")
	public ResultadoRestVO<String> generarReporteRegistroVentaTXT(@Context HttpHeaders httpHeaders,RegistroAsientoFiltroVO registroVentaFiltro){
		ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
		String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		registroVentaFiltro.setAuthToken(authToken);
		registroVentaFiltro.setServiceKey(serviceKey);
		 try {
    		resultado.setObjetoResultado(ventaServiceLocal.generarReporteRegistroVentaTXT(registroVentaFiltro));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@GET
    @Path("/listarDetalleVenta")
	public ResultadoRestVO<DetalleVentaDTO> listarDetalleVenta(@Context UriInfo info){
		ResultadoRestVO<DetalleVentaDTO> resultado = new ResultadoRestVO<DetalleVentaDTO>();
		DetalleVentaDTO detalleVenta = transferUriInfoDetalle(info);
		 try {
			resultado.setListaResultado(ventaServiceLocal.listarDetalleVenta(detalleVenta));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	private DetalleVentaDTO transferUriInfoDetalle(@Context UriInfo info) {
		DetalleVentaDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,DetalleVentaDTO.class);
		return resultado;
	}
	
	private VentaDTO transferUriInfo(@Context UriInfo info) {
		VentaDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,VentaDTO.class);
		return resultado;
	}
	@POST
    @Path("/generarReporteVenta")
	public ResultadoRestVO<String> generarReporteVenta(@Context HttpHeaders httpHeaders,VentaDTO venta){
		ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
		String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		 try {
			 String userName = AppAuthenticator.getInstance().getUserName(authToken);
			 resultado.setObjetoResultado(ventaServiceLocal.generarReporteVenta(venta, userName));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@POST
    @Path("/generarReporteVentaBase64")
	public ResultadoRestVO<String> generarReporteVentaBase64(@Context HttpHeaders httpHeaders,VentaDTO venta){
		ResultadoRestVO<String> resultado = new ResultadoRestVO<String>(); 
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		 try {
			 String userName = AppAuthenticator.getInstance().getUserName(authToken);
			 resultado.setObjetoResultado(ventaServiceLocal.generarReporteVentaBase64(venta, userName));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@POST
    @Path("/generarReporteVentaA4")
	public ResultadoRestVO<String> generarReporteVentaA4(@Context HttpHeaders httpHeaders,VentaDTO venta){
		ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
		String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		 try {
			 String userName = AppAuthenticator.getInstance().getUserName(authToken);
			 resultado.setObjetoResultado(ventaServiceLocal.generarReporteVentaA4(venta, userName));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@POST
    @Path("/generarReporteVistaPrevia")
	public ResultadoRestVO<String> generarReporteVistaPrevia(@Context HttpHeaders httpHeaders,List<VentaFiltroVO> venta){
		ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
		String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		 try {
			 String userName = AppAuthenticator.getInstance().getUserName(authToken);
			 resultado.setObjetoResultado(ventaServiceLocal.generarReporteVistaPrevia(venta, userName));
			 //Utilidades.imprimirVistaPrevia(resultado.getObjetoResultado(),userName,venta.get(0).getNombreImpresora());
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	
	@POST
	@Path("/anularVenta")
	public ResultadoRestVO<VentaDTO> anularVenta(@Context HttpHeaders httpHeaders,VentaDTO venta) throws Exception {
		ResultadoRestVO<VentaDTO> resultado = new ResultadoRestVO<VentaDTO>();		
		 try {
			 resultado.setObjetoResultado(ventaServiceLocal.anularVenta(venta));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	//add
	
	@GET
	@Path("/ejecutar")
	public static void ejecutar() {
		Runtime app = Runtime.getRuntime();
		 try {
			 app.exec("C:/Facturador Nuevo Estacion - Fer/Facturador.exe");
			 
		} catch (Exception e) {
			
		}
	}
	
	
	@GET
	@Path("/consultaDocumentoSunat/{nroDoc}")
    public ResultadoRestVO<String>  consultaDocumentoSunat(@PathParam("nroDoc")String nroDoc) throws Exception{
		ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
		 try {
			if(nroDoc.length() <= 8){
				resultado.setListaResultado(this.consultarReniecDni(nroDoc));
			}else{
				resultado.setListaResultado(this.consultarRUC(nroDoc));
			}
			 
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}

		return resultado;
	}
	
	
	//modificado
	 public List<String> consultarReniecDni(String nrodni) throws Exception
	   {    
		 final String USER_AGENT = "Mozilla/5.0";
	   	    List<String> consulta_dni= new ArrayList<String>();
	        String url = "https://dniruc.apisperu.com/api/v1/dni/"+nrodni+"?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImtyaXNzLnJlYmlsQGdtYWlsLmNvbSJ9.uq92gVngaARZDf0rG6Ju82WBZcvd0fec32R_h2Va0iE";
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", USER_AGENT);
			int responseCode = con.getResponseCode();
			//System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			JSONObject Jobj = new JSONObject(response.toString());
		 
		    for(int i = 0 ; i < Jobj.length() ; i++){
		      consulta_dni.add(Jobj.getString("nombres"));
		      consulta_dni.add(Jobj.getString("apellidoPaterno"));
		      consulta_dni.add(Jobj.getString("apellidoMaterno"));
		    }
	 
			/*String dem =consulta_dni.get(0)+" "+consulta_dni.get(1)+" "+consulta_dni.get(2);
			consulta_dni= new ArrayList<String>();
			consulta_dni.add(dem); */
			return consulta_dni;
	   }
	
	 public List<String> consultarRUC(String ruc) throws Exception
	 {    
		 final String USER_AGENT = "Mozilla/5.0";
	   	    List<String> consulta_dni= new ArrayList<String>();
	        String url = "https://dniruc.apisperu.com/api/v1/ruc/"+ruc+"?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImtyaXNzLnJlYmlsQGdtYWlsLmNvbSJ9.uq92gVngaARZDf0rG6Ju82WBZcvd0fec32R_h2Va0iE";
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", USER_AGENT);
			int responseCode = con.getResponseCode();
			//System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			JSONObject Jobj = new JSONObject(response.toString());
		 
		    for(int i = 0 ; i < Jobj.length() ; i++){
		      consulta_dni.add(Jobj.getString("ruc"));
		      consulta_dni.add(Jobj.getString("razonSocial"));
		      consulta_dni.add(Jobj.getString("direccion"));
		    }
	 
			/*String dem =consulta_dni.get(0)+" "+consulta_dni.get(1)+" "+consulta_dni.get(2);
			consulta_dni= new ArrayList<String>();
			consulta_dni.add(dem); */
			return consulta_dni;
	   }
	
	
	 public List<String> consultaDemo (String dem) throws Exception{
	    	List<String> consulta_demo = new ArrayList<String>() ;
	        URL url = new URL("http://negocioscastillo.com/php/servicesperu/sunatRUC/");
	        Map<String, Object> params = new LinkedHashMap<>();
	        params.put("hdOpcion", "ruc"); 
	        params.put("numero", dem);  
	        params.put("btnConsultar", "Consultar+RUC");      
	 
	        StringBuilder postData = new StringBuilder();
	        for (Map.Entry<String, Object> param : params.entrySet()) {
	            if (postData.length() != 0)
	                postData.append('&');
	            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
	            postData.append('=');
	            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
	        }
	        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        conn.setRequestProperty("Content-Length",String.valueOf(postDataBytes.length));
	        conn.setDoOutput(true);
	        conn.getOutputStream().write(postDataBytes);
	        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	        String sCadena; 
	        List<String> supplierNames = new ArrayList<String>();
	        while ((sCadena = in.readLine())!=null) { 
					if(sCadena.indexOf("type=\"text\"")!= -1){
						if(sCadena.indexOf("value")!= -1){
							supplierNames.add(sCadena);
						}
					}
			  }
	        String salida;
	        for(String de : supplierNames) {
             if(de.trim().equals("")==false){
             	String replacement="";
	        	salida = (((((((((((((((((((((((((de.replaceAll("<input type=\"text\"", "")).replaceAll("class=\"form-control input-sm\"", "")).
	        			replaceAll("\" id=\"txt", "")).replaceAll("\"  value=\"", ": \n ")).replaceAll("\" />", "")).
	        			replaceAll("name=\"","")).replaceAll("txtDni", "")).replaceAll("txtTipoContribuyente", "")).replaceAll("txtRazonSocial", "")).
    		            replaceAll("txtFechaInscripcion", "")).replaceAll("txtFechaInicioActividad", replacement)).replaceAll("txtEstadoContribuyente", replacement)).
                     replaceAll("txtFechaBaja", replacement)).replaceAll("txtCondicionContribuyente", replacement)).replaceAll("txtNombreComercial", "")).
    		            replaceAll("txtTelefonos", replacement)).replaceAll("txtDirecionDomicilioFiscal", replacement)).replaceAll("txtDepartamento", replacement)).
    		            replaceAll("txtProvincia", replacement)).replaceAll("txtDistrito", replacement)).replaceAll("txtSistemaEmisionComprobante", replacement)).
    		            replaceAll("txtActividadComercioExterior", replacement)).replaceAll("txtSistemaContabilidad", replacement)).
	        			replaceAll("txtProfesionOficio", replacement)).
    		            replaceAll("txtEmisionElectronica", replacement)).replaceAll("txtAfiliadoPLE", replacement).trim();  
    		if(salida.trim().equals("")==false && (salida.replaceAll("Razon Social:","")).replaceAll("eeee","").equals("")==false)
             {
             	postData.append(salida).append("\n ");
             }
            } 
	        }
	        String [][]consulta_ruc=new String[15][2];
	        String mostrar ,rsocial; 
         mostrar=postData.toString().replaceAll("Condicion:","Condicion:");
         String [] vectorconsultaruc = mostrar.split("\n");

         if(vectorconsultaruc.length>=15)
         {   rsocial=vectorconsultaruc[1];
             consulta_ruc[0][0]=dem;
             consulta_ruc[0][1]=rsocial;
             consulta_ruc[1][0]=vectorconsultaruc[2];
             consulta_ruc[1][1]=vectorconsultaruc[3];
             consulta_ruc[2][0]=vectorconsultaruc[4];
             consulta_ruc[2][1]=vectorconsultaruc[5];
             consulta_ruc[3][0]=vectorconsultaruc[6];
             consulta_ruc[3][1]=vectorconsultaruc[7];
             consulta_ruc[4][0]=vectorconsultaruc[8];
             consulta_ruc[4][1]=vectorconsultaruc[9];
             consulta_ruc[5][0]=vectorconsultaruc[10];
             consulta_ruc[5][1]=vectorconsultaruc[11];
             consulta_ruc[6][0]=vectorconsultaruc[12];
             consulta_ruc[6][1]=vectorconsultaruc[13];
             consulta_ruc[7][0]=vectorconsultaruc[14];
             consulta_ruc[7][1]=vectorconsultaruc[15];
             consulta_ruc[8][0]=vectorconsultaruc[16];
             consulta_ruc[8][1]=vectorconsultaruc[17];
             consulta_ruc[9][0]=vectorconsultaruc[18];
             consulta_ruc[9][1]=vectorconsultaruc[19];
             consulta_ruc[10][0]=vectorconsultaruc[20];
             consulta_ruc[10][1]=vectorconsultaruc[21];
             consulta_ruc[11][0]=vectorconsultaruc[22];
             consulta_ruc[11][1]=vectorconsultaruc[23];
             consulta_ruc[12][0]=vectorconsultaruc[24];
             consulta_ruc[12][1]=vectorconsultaruc[25];
         }else if(vectorconsultaruc.length==1)
         {
            System.out.println("Ruc Invalido, \nIngrese numero de Ruc correcto!");
         } 
	            System.out.println(" RUC: " +consulta_ruc[0][0]);
	            System.out.println(consulta_ruc[1][0] +" "+consulta_ruc[1][1]);
	            System.out.println(consulta_ruc[2][0] +" "+consulta_ruc[2][1]);
	            System.out.println(consulta_ruc[3][0] +" "+consulta_ruc[3][1]);
	            System.out.println(consulta_ruc[4][0] +" "+consulta_ruc[4][1]);
	            System.out.println(consulta_ruc[5][0] +" "+consulta_ruc[5][1]);
	            System.out.println(consulta_ruc[6][0] +" "+consulta_ruc[6][1]);
	            System.out.println(consulta_ruc[7][0] +" "+consulta_ruc[7][1]);
	            System.out.println(consulta_ruc[8][0] +" "+consulta_ruc[8][1]);
	            System.out.println(consulta_ruc[9][0] +" "+consulta_ruc[9][1]);
	            System.out.println(consulta_ruc[10][0] +" "+consulta_ruc[10][1]);
	            System.out.println(consulta_ruc[11][0] +" "+consulta_ruc[11][1]);
	            System.out.println(consulta_ruc[12][0] +" "+consulta_ruc[12][1]);
	            
	            consulta_demo.add(consulta_ruc[0][0]);
	            consulta_demo.add(consulta_ruc[1][1]);
	            consulta_demo.add(consulta_ruc[2][1]);
	            consulta_demo.add(consulta_ruc[3][1]);
	            consulta_demo.add(consulta_ruc[4][1]);
	            consulta_demo.add(consulta_ruc[5][1]);
	            consulta_demo.add(consulta_ruc[6][1]);
	            consulta_demo.add(consulta_ruc[7][1]);
	            consulta_demo.add(consulta_ruc[8][1]);
	            consulta_demo.add(consulta_ruc[9][1]);
	            consulta_demo.add(consulta_ruc[10][1]);
	            consulta_demo.add(consulta_ruc[11][1]);

		    in.close();
			return consulta_demo;
	    }
	 
	 
	//add
		@POST
		@Path("/registrarventamanual")
		public ResultadoRestVO<VentaDTO> registrarVentaManual(@Context HttpHeaders httpHeaders,VentaDTO venta) throws Exception {
			ResultadoRestVO<VentaDTO> resultado = new ResultadoRestVO<VentaDTO>();
			String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
			String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
			String ip = httpHeaders.getHeaderString( AppHTTPHeaderNames.ORIGIN );
			venta.setServiceKey(serviceKey);
			venta.setAuthToken(authToken);
			venta.setIp(ip);
			 try {                                                           
				 String userName = AppAuthenticator.getInstance().getUserName(authToken);
				 resultado.setObjetoResultado(ventaServiceLocal.registrarVentaManual(venta));
				// String codigoGeneradoReporte = ventaServiceLocal.generarReportePago(venta.getIdVenta(), venta.getCliente().getIdCliente(), userName);
				 //resultado.getObjetoResultado().setCodigoGeneradoReporte(codigoGeneradoReporte);
				 HibernateUtil.setListaNull(resultado.getObjetoResultado());
			} catch (Exception e) {
				parsearResultadoError(e, resultado);
			}
			return resultado;
		}
	 
	 
		@DELETE
		@Path("eliminar_manual/{id}")
		public ResultadoRestVO<DetalleVentaDTO> eliminar_manual(@PathParam("id") String idDet) throws Exception {
			DetalleVentaDTO det = new DetalleVentaDTO();
			det.setIdDetalleVenta(idDet);		
			return controladorAccion(det,AccionType.ELIMINAR_MANUAL);
		}
		
		private ResultadoRestVO<DetalleVentaDTO> controladorAccion(DetalleVentaDTO det, AccionType accionType){
			ResultadoRestVO<DetalleVentaDTO> resultado = new ResultadoRestVO<DetalleVentaDTO>();
			 try {
	    		 resultado.setObjetoResultado(ventaServiceLocal.controladorAccionDetalleVenta(det,null, accionType));
			} catch (Exception e) {
				parsearResultadoError(e, resultado);
			}
			return resultado;
		}
		
		
		@GET
	    @Path("/generarReporteMultiple/{tipoReporte}/{fechaInicio}/{fechaFin}/{usuarioCrea}")
		public ResultadoRestVO<String> generarReporteMultiple(@Context HttpHeaders httpHeaders,@PathParam("tipoReporte") String tipoReporte,@PathParam("fechaInicio") Date fechaInicio,@PathParam("fechaFin")  Date fechaFin,@PathParam("usuarioCrea") String usuarioCrea  ){
			ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
			String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
			String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
			 try {		
	    		resultado.setObjetoResultado(ventaServiceLocal.generarReporteMultiple(tipoReporte, fechaInicio, fechaFin,usuarioCrea,serviceKey, authToken));
			} catch (Exception e) {
				parsearResultadoError(e, resultado);
			}
			return resultado;
		}
	
		
		//add
		@POST
	    @Path("/generarExtracionTXT")
		public ResultadoRestVO<SunatDatosVO> generarExtracionTXT(@Context HttpHeaders httpHeaders){
			ResultadoRestVO<SunatDatosVO> resultado = new ResultadoRestVO<SunatDatosVO>(); 
			 try {
				 
	    		resultado.setListaResultado(ventaServiceLocal.generarExtracionTXT());
			} catch (Exception e) {
				parsearResultadoError(e, resultado);
			}
			return resultado;
		}
		
		@POST
	    @Path("/generarComprobante")
		public ResultadoRestVO<SunatDatosVO> generarComprobante(@Context HttpHeaders httpHeaders,SunatDatosVO sfs){
			ResultadoRestVO<SunatDatosVO> resultado = new ResultadoRestVO<SunatDatosVO>(); 
			 try {
	    		resultado.setListaResultado(ventaServiceLocal.generarComprobante(sfs));
			} catch (Exception e) {
				parsearResultadoError(e, resultado);
			}
			return resultado;
		}
		
		@POST
	    @Path("/enviarComprobante")
		public ResultadoRestVO<SunatDatosVO> enviarComprobante(@Context HttpHeaders httpHeaders,SunatDatosVO sfs){
			ResultadoRestVO<SunatDatosVO> resultado = new ResultadoRestVO<SunatDatosVO>(); 
			 try {
	    		resultado.setListaResultado(ventaServiceLocal.enviarComprobante(sfs));
			} catch (Exception e) {
				parsearResultadoError(e, resultado);
			}
			return resultado;
		}
		
		@POST
	    @Path("/eliminarBandeja")
		public ResultadoRestVO<SunatDatosVO> eliminarBandeja(@Context HttpHeaders httpHeaders){
			ResultadoRestVO<SunatDatosVO> resultado = new ResultadoRestVO<SunatDatosVO>(); 
			 try {
	    		resultado.setListaResultado(ventaServiceLocal.eliminarBandeja());
			} catch (Exception e) {
				parsearResultadoError(e, resultado);
			}
			return resultado;
		}
		
		@GET
	    @Path("/actualizarBandeja")
		public ResultadoRestVO<SunatDatosVO> actualizarBandeja(@Context HttpHeaders httpHeaders){
			ResultadoRestVO<SunatDatosVO> resultado = new ResultadoRestVO<SunatDatosVO>(); 
			 try {
	    		resultado.setListaResultado(ventaServiceLocal.actualizarBandeja());
			} catch (Exception e) {
				parsearResultadoError(e, resultado);
			}
			return resultado;
		}
		
		@POST
	    @Path("/cerrarCaja")
		public ResultadoRestVO<String> cerrarCaja(@Context UriInfo info,CajaDTO cajaTemp){
			ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
			 try {
				resultado.setObjetoResultado(ventaServiceLocal.cerrarCaja(cajaTemp));
			} catch (Exception e) {
				parsearResultadoError(e, resultado);
			}
			return resultado;
		}
		
		@POST
		@Path("/generarReporteCaja")
		public ResultadoRestVO<String> generarReporteCaja(@Context UriInfo info,CvPerfilesVO filtro){
			ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
			 try {
				resultado.setObjetoResultado(ventaServiceLocal.reporteCaja(filtro.getIdUusraio(),filtro.getFechaCreacion(),filtro.getIdCaja()));
			} catch (Exception e) {
				parsearResultadoError(e, resultado);
			}
			return resultado;
		}
		
		
		@POST
	    @Path("/updateVentaCierre")
		public ResultadoRestVO<String> updateVentaCierre(@Context UriInfo info,CajaDTO cajaTemp){
			ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
			 try {
				resultado.setObjetoResultado(ventaServiceLocal.updateVentaCierre(cajaTemp));
			} catch (Exception e) {
				parsearResultadoError(e, resultado);
			}
			return resultado;
		}
		
		
		@POST
	    @Path("/iniciarAperturaCaja")
		public ResultadoRestVO<String> iniciarAperturaCaja(@Context UriInfo info){
			ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
			 try {
				resultado.setObjetoResultado(ventaServiceLocal.iniciarAperturaCaja());
			} catch (Exception e) {
				parsearResultadoError(e, resultado);
			}
			return resultado;
		}
		
		@GET
	    @Path("/obtenerGanaciasVenta")
		public ResultadoRestVO<VentaGraficoVO> obtenerGanaciasVenta(@Context UriInfo info){
			ResultadoRestVO<VentaGraficoVO> resultado = new ResultadoRestVO<VentaGraficoVO>();
			VentaDTO venta = transferUriInfo(info);
			try {				
				resultado.setObjetoResultado(ventaServiceLocal.obtenerVentaGanacias());
			} catch (Exception e) {
				parsearResultadoError(e, resultado);
			}
			return resultado;
		}
		
		@POST
		@Path("/obtenerListaCajaFiltro")
		public ResultadoRestVO<VentaDTO>  obtenerListaCajaFiltro(VentaFiltroVO cajafilterVo) throws Exception {
			ResultadoRestVO<VentaDTO> resultado = new ResultadoRestVO<VentaDTO>();
			 try {
	   		resultado.setListaResultado(ventaServiceLocal.obtenerListaCajaFiltro(cajafilterVo));
			} catch (Exception e) {
				parsearResultadoError(e, resultado);
			}
			return resultado;
		}
		
		@POST
	    @Path("/exportarIngresoDetalladoExcel")
		public ResultadoRestVO<String> exportarIngresoDetalladoExcel(VentaFiltroVO filter) throws Exception {
			 ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
			 try {
				 resultado.setObjetoResultado(ventaServiceLocal.generarReporteIngresoDetalladoByExcel(filter));
			} catch (Exception e) {
				parsearResultadoError(e, resultado);
			}
			return resultado;
		}
		
		@POST
	    @Path("/exportarVentasFechaExcel")
		public ResultadoRestVO<String> exportarVentasFechaExcel(VentaFiltroVO filter) throws Exception {
			 ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
			 try {
				 resultado.setObjetoResultado(ventaServiceLocal.generarReporVentasFechaByExcel(filter));
			} catch (Exception e) {
				parsearResultadoError(e, resultado);
			}
			return resultado;
		}
		
		
		@POST
	    @Path("/descargarProductoVendidoFecha")
		public ResultadoRestVO<String> descargarProductoVendidoFecha(VentaFiltroVO filter) throws Exception {
			 ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
			 try {
				 resultado.setObjetoResultado(ventaServiceLocal.descargarProductoVendidoFecha(filter));
			} catch (Exception e) {
				parsearResultadoError(e, resultado);
			}
			return resultado;
		}
		
		@POST
	    @Path("/generarReporVentasFechaByExcelByProducto")
		public ResultadoRestVO<String> generarReporVentasFechaByExcelByProducto(VentaFiltroVO filter) throws Exception {
			 ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
			 try {
				 resultado.setObjetoResultado(ventaServiceLocal.generarReporVentasFechaByExcelByProducto(filter));
			} catch (Exception e) {
				parsearResultadoError(e, resultado);
			}
			return resultado;
		}
}