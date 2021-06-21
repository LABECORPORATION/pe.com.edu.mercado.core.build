package pe.com.edu.siaa.core.ui.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.edu.siaa.core.ejb.factory.CollectionUtil;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.model.util.ConstanteTypeUtil;
import pe.com.edu.siaa.core.model.util.ResourceUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;
import pe.com.edu.siaa.core.model.vo.SelectItemVO;

/**
 * La Class Utilitario.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * MAPFRE. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Wed Jul 30 17:25:41 COT 2017
 * @since SIAA-CORE 2.1
 */
public class Utilitario implements Serializable {
	

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

		/** La log. */
	private static Logger log = Logger.getLogger(Utilitario.class);
	

	/**
	 * Ordenador.
	 *
	 * @param descending el descending
	 * @param listaGeneral el lista general
	 * @param nombreColumna el nombre columna
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void ordenador(boolean descending, List listaGeneral, String nombreColumna) {
		CollectionUtil.ordenador(descending, listaGeneral, nombreColumna);
	}
	
	

	/**
	 * Obtiene view root locale.
	 *
	 * @return view root locale
	 */
	public static Locale getViewRootLocale() {
		return FacesContext.getCurrentInstance().getViewRoot().getLocale();
	}


	/**
	 * Validar hora.
	 *
	 * @param hora el hora
	 * @return true, en caso de exito
	 */
	public static boolean validarHora(String hora) {
		if (hora == null || hora.trim().equals("")) {
			return true;
		}
		try {
			StringTokenizer horaToken = new StringTokenizer(hora,":");
			// validando hora
			String horaValidando = horaToken.nextToken();
			if (Integer.parseInt(horaValidando) < 0 || Integer.parseInt(horaValidando) > 23) {
				return false;
			}

			// validando minuto
			String minutoValidando = horaToken.nextToken();
			if (Integer.parseInt(minutoValidando) < 0 || Integer.parseInt(minutoValidando) > 59) {
				return false;
			}
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}
		
	}

	
	/**
	 * Obtener hora.
	 *
	 * @param hora el hora
	 * @return the integer
	 */
	public static Integer obtenerHora(String hora) {
		try {
			StringTokenizer horaToken = new StringTokenizer(hora,":");

			// validando hora
			String horaValidando = horaToken.nextToken();
			return Integer.parseInt(horaValidando);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * Obtener minuto.
	 *
	 * @param hora el hora
	 * @return the integer
	 */
	public static Integer obtenerMinuto(String hora) {
		try {
			StringTokenizer horaToken = new StringTokenizer(hora,":");

			// validando hora
			String horaValidando = horaToken.nextToken();
			String minutoValidando = horaToken.nextToken();
			return Integer.parseInt(minutoValidando);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

	
	/**
	 * Formatear decimal seg�n patr�n ###,###.##.
	 *
	 * @param numero el numero
	 * @return the string
	 */
	public static String formatearDecimal(String numero) {
		DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
	    simbolo.setDecimalSeparator('.');
	    simbolo.setGroupingSeparator(',');
	    DecimalFormat formateador = new DecimalFormat("###,###.##",simbolo);
	    return formateador.format(new BigDecimal(numero)); 
	}
	
	public static String reingenieriaFormateoNumerico(String dataResul) {
		return TransferDataObjectUtil.reingenieriaFormateoNumerico(dataResul);
	}
	/**
	 * Substring.
	 *
	 * @param origen el origen
	 * @param start el start
	 * @param end el end
	 * @return the string
	 */
	public static String substring(String origen, int start, int end) {
		if (origen != null) {
			return origen.substring(start, end);
		}
		return "";
	}

	
	/**
	 * Obtener fecha.
	 *
	 * @param fechaSeleccionada el fecha seleccionada
	 * @param formato el formato
	 * @return the string
	 */
	public static String obtenerFecha(Date fechaSeleccionada, String formato) {
		SimpleDateFormat sdf = new SimpleDateFormat(formato);		
		String cadenaFecha = sdf.format(fechaSeleccionada);
		return cadenaFecha;
	}
	
	/**
	 * Obtiene description type.
	 *
	 * @param locale el locale
	 * @param value el value
	 * @return description type
	 */
	public String getDescriptionType(Locale locale,String value) {
		return ResourceUtil.getString(locale, ConstanteTypeUtil.BUNDLE_NAME_TYPE, value);
    }
	
	/**
	 * Convertir select item.
	 *
	 * @param resultadoTemp el resultado temp
	 * @return the list
	 */
	public static List<SelectItem> convertirSelectItem(List<SelectItemVO> resultadoTemp) {
		List<SelectItem> resultado = new ArrayList<SelectItem>();
		for (SelectItemVO selectItemVO : resultadoTemp) {
			resultado.add(new SelectItem(selectItemVO.getId(), selectItemVO.getNombre(), selectItemVO.getDescripcion()));
		}
		return resultado;
	}
	
	public static List<String> convertirSelectComboExcelItem(List<SelectItem> resultadoTemp) {
		List<String> resultado = new ArrayList<String>();
		for (SelectItem selectItem: resultadoTemp) {
			resultado.add(selectItem.getValue() + "-" + selectItem.getLabel());
		}
		return resultado;
	}
	/**
	 * Retorna contexto de faces.
	 *
	 * @return faces context
	 */
	protected static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	
	/**
	 * Obtiene request.
	 *
	 * @return request
	 */
	public static HttpServletRequest getRequest() {
		FacesContext facesContext = getFacesContext();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		return request;
	}

	/**
	 * Obtiene response.
	 *
	 * @return response
	 */
	public static HttpServletResponse getResponse() {
		FacesContext facesContext = getFacesContext();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		return response;
	}
	
	/**
	 * Obtener context utl.
	 *
	 * @return the string
	 */
	public static String obtenerContextUtl() {
		return obtenerContextUtl(getRequest());
	}

	/**
	 * Obtener context utl.
	 *
	 * @param request el request
	 * @return the string
	 */
	public static String obtenerContextUtl(HttpServletRequest request) {
		String context = request.getContextPath();
		String ip = request.getLocalName();
		String protocolo = request.getProtocol();
		String puerto = request.getLocalPort() + "";
		String contextUrl = "";
		if (protocolo.contains("HTTP/1.1")) {
			contextUrl = "http://" + ip + ":" + puerto  + "" + context + "";
		} else {
			contextUrl = "https://" + ip + ":" + puerto  + "" + context + "";
		}
		return contextUrl;
	}
	
	/**
	 * Byte to string.
	 *
	 * @param data el data
	 * @return the string
	 */
	public static String byteToString(byte[] data) {
		String resultado = null;
		try {
			resultado = new String(data, "UTF-8");
		} catch (Exception e) {
		}
		return resultado;
	}
	
	/**
	 * Leer archivo plano.
	 *
	 * @param inputStream el input stream
	 * @return the list
	 */
	public  static List<String> leerArchivoPlano(InputStream inputStream) {
		List<String> resultado = new ArrayList<String>();	
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				resultado.add(line);
			}
		} catch (IOException e) {
			log.error(e);
		}
		return resultado;
	}

	/**
	 * Completar cerosy coma.
	 *
	 * @param numero el numero
	 * @return the string
	 */
	public static String completarCerosyComa(String numero) {
		return StringUtils.completarCerosyComa(numero);
	}
	
	public static int obtenetDifNum( int a , int b){
		int resultado = a-b;
		
		return resultado;
	}


}