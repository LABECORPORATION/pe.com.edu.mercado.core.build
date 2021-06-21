package pe.com.edu.siaa.core.ui.servlets;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperPrint;
import pe.com.edu.siaa.core.ejb.service.local.GenerarReporteServiceLocal;
import pe.com.edu.siaa.core.ejb.util.cache.SessionUtil;
import pe.com.edu.siaa.core.ejb.util.jasper.ArchivoUtilidades;
import pe.com.edu.siaa.core.ejb.util.jasper.ConstanteGenerarReporteUtil;
import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.model.util.ConstanteConfigUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;
import pe.com.edu.siaa.core.model.vo.FileVO;
import pe.com.edu.siaa.core.ui.util.Utilitario;

/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class DescargarReporte.
 *
 * @author ndavilal
 * @version 1.0 , 08/04/2015
 * @since SIAA-CORE 2.1
 */
public class DescargarReporte extends HttpServlet {
	
	private static final String NO_EXISTE_EL_ARCHIVO_EN = "No existe el archivo en ";


	private static final String ARCHIVO_TEMP = "archivo";


	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
       

	/** El servicio de reporte service impl. */
	@EJB
	private GenerarReporteServiceLocal reporteServiceImpl;
	
	/** La log. */
	private static Logger log = Logger.getLogger(DescargarReporte.class);
	
    /** La Constante DEFAULT_BUFFER_SIZE. @see HttpServlet#HttpServlet() */
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	private static final int DEFAULT_BUFFER_SIZE = 1024;


	private static final int TAMANO_BUFFER = DEFAULT_BUFFER_SIZE; 
	
	/** La Constante RUTA_RECURSOS. */
	public static final String RUTA_RECURSOS_BYTE_BUFFER = ConstanteConfigUtil.RUTA_RECURSOS_BYTE_BUFFER;

	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	/**
	 * Do post.
	 *
	 * @param request el request
	 * @param response el response
	 * @throws ServletException the servlet exception
	 * @throws IOException Se�ales de que una excepci�n de E / S se ha producido.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}
	
	/**
	 * Process request.
	 *
	 * @param request el request
	 * @param response el response
	 * @throws IOException Se�ales de que una excepci�n de E / S se ha producido.
	 */
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean eliminarFileSession = false;
		String nombreArchivo = request.getParameter("fileName");
		String userName =  request.getParameter("userName");
		boolean isError = false;
		String mensajeError = "";
		try {
			String isViewPdf = request.getParameter("isViewPdf");
			
			String cola = request.getParameter("cola");
			//String userNameBandeja = request.getParameter("userName");
			String isJasperPrint  = request.getParameter("jasperPrint");
			String contextUrl = Utilitario.obtenerContextUtl(request);
			FileVO documento = null;

			if (isJasperPrint != null) {
				String formato = request.getParameter("formato");
				documento = (FileVO)SessionUtil.getSession().get(nombreArchivo);
				Map<String,Object> reporteGeneradoTemp = documento.getReporteGeneradoMap();
				//String userName = ((PersonaDTO)request.getSession().getAttribute(ConstantesParameterUtil.ATRIBUTE_SESSION_USUARIO)).getUserName();
				JasperPrint jasperPrint = (JasperPrint) reporteGeneradoTemp.get(ConstanteGenerarReporteUtil.JASPER_PRINT);
				String rutaSession = ConstanteConfigUtil.RUTA_SESSION_TEMP + ConstanteConfigUtil.generarRuta(userName,ARCHIVO_TEMP);
				File file = new File(rutaSession );
				if (!file.exists()) {
					file.mkdirs();
				}
				rutaSession = rutaSession + nombreArchivo + "." + formato;
				documento = reporteServiceImpl.generarReporte(jasperPrint, formato, contextUrl,rutaSession);
				documento.setName(nombreArchivo + "." + formato);
			} else {
				if (cola == null) {
					documento = (FileVO)SessionUtil.getSession().get(nombreArchivo);
					if (!StringUtils.isNotNullOrBlank(userName)) {
						userName = documento.getUserName();
					}
					eliminarFileSession = true;
					
				} else {
					/*String userName = ((UsuarioDTO)request.getSession().getAttribute(ConstantesParameterUtil.ATRIBUTE_SESSION_USUARIO)).getUserName();
					String rutaArchivo =  ArchivoUtilidades.RUTA_REPORTE_GENERADO + ConstanteConfigUtil.SEPARADOR_FILE +  userName + ConstanteConfigUtil.SEPARADOR_FILE +  nombreArchivo ;
					documento = ArchivoUtilidades.obtenerArchivo(rutaArchivo);
					*/
					documento = new FileVO();
				}
			}
			response.setBufferSize(DEFAULT_BUFFER_SIZE);
			if (isViewPdf == null) {
				if (StringUtils.isNullOrEmpty(documento.getDataBig())) {
					if (cola == null) {
						response.setHeader("Content-Length", String.valueOf(documento.getLength()));
						response.setHeader("Content-Type", documento.getMime());
						response.setHeader("Content-Disposition", "attachment; filename=\"" + documento.getName() + "\"");
					}
				}
			} else {
					response.setHeader("Content-Length", String.valueOf(documento.getLength()));
					response.setHeader("Content-Disposition", "inline; filename=\"" + documento.getName() + "\"");
			}
			if (StringUtils.isNullOrEmpty(documento.getDataBig())) {
				if (cola == null) {
					//String userName = ((PersonaDTO)request.getSession().getAttribute(ConstantesParameterUtil.ATRIBUTE_SESSION_USUARIO)).getUserName();
					String rutaArchivo = ConstanteConfigUtil.RUTA_SESSION_TEMP + ConstanteConfigUtil.generarRuta(userName,ARCHIVO_TEMP) + nombreArchivo + "." + documento.getExtension() ;
					File  file = new File(rutaArchivo);
					documento.setName(documento.getName());
					if (file.exists()) {
						descargarReporteFile(request, response, file,documento);
						file.delete();
					} else {
						isError = true;
						mensajeError = NO_EXISTE_EL_ARCHIVO_EN + rutaArchivo;
					}
					file = null;
					
				} else {
					//userName = userNameBandeja;
					if (StringUtils.isNullOrEmpty(userName)){
						//userName = ((PersonaDTO)request.getSession().getAttribute(ConstantesParameterUtil.ATRIBUTE_SESSION_USUARIO)).getUserName();	
					}					
					String rutaArchivo = ConstanteConfigUtil.generarRuta(ArchivoUtilidades.RUTA_RECURSOS , ArchivoUtilidades.RUTA_REPORTE_GENERADO , userName ) +  nombreArchivo;
					File  file = new File(rutaArchivo);
					documento.setName(file.getName());
					if (file.exists()) {
						descargarReporteFile(request, response, file,documento);
					} else {
						isError = true;
						mensajeError = NO_EXISTE_EL_ARCHIVO_EN + rutaArchivo;
					}
					file = null;
				}
			} else {
				if (cola == null && !documento.getDataBig().contains(".")) {
					String rutaArchivo = ConstanteConfigUtil.RUTA_SESSION_TEMP + ConstanteConfigUtil.generarRuta(userName,ARCHIVO_TEMP) + nombreArchivo + "." + documento.getExtension() ;
					File  file = new File(rutaArchivo);
					documento.setName(documento.getName());
					if (file.exists()) {
						descargarReporteFile(request, response, file,documento);
						file.delete();
					} else {
						isError = true;
						mensajeError = NO_EXISTE_EL_ARCHIVO_EN + rutaArchivo;
					}					
					file = null;
				} else {
					userName = documento.getUserName();
					if (!StringUtils.isNullOrEmpty(userName)) {
						userName = userName +  ConstanteConfigUtil.SEPARADOR_FILE  ;
					} else {
						userName = "";
					}
					File  file = new File(RUTA_RECURSOS_BYTE_BUFFER + userName + "" + documento.getName());
					if (file.exists()) {
						descargarReporteFile(request, response, file,documento);
						if (!documento.isEsCopiaCorreo()) {
							file.delete();	
						}
						
					} else {
						isError = true;
						mensajeError = NO_EXISTE_EL_ARCHIVO_EN + file.getAbsolutePath();
					}
				}
			}
					
			if (isViewPdf == null  && !isError) {
				response.flushBuffer();
			}
		} catch (Exception e) {
			mensajeError = mensajeError + " Exception==> " + e.getMessage();
			isError = true;			
		} finally {
			if (!isError) {
				response.getOutputStream().flush();
				response.getOutputStream().close();
				if (eliminarFileSession) {
					request.getSession().removeAttribute(nombreArchivo);
				}
			}
	    }
		if (isError) {
			log.error("DescargarReporte.error==> " + mensajeError);
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>MyTron</title>");
			out.println("</head>");
			out.println("<body ><center>");
			out.print( mensajeError);
			out.println("</center><body >");
			out.println("</body>");
			out.println("</html>");
		}
   }
	private void descargarReporteFile(HttpServletRequest request, HttpServletResponse response,File file,FileVO documento) throws Exception {
		response.setHeader("Content-Length", String.valueOf(file.length()));
		response.setHeader("Content-Type", documento.getMime());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + documento.getName() + "\"");
		DataInputStream in = new DataInputStream(new FileInputStream(file) );
		byte[] bbuf = new byte[ TAMANO_BUFFER  ];
		int leido = 0;
	    while ((in != null) && ( (leido = in.read(bbuf) ) != -1)) {
            	response.getOutputStream().write(bbuf, 0, leido);
        }
		in.close();
	}
		
}