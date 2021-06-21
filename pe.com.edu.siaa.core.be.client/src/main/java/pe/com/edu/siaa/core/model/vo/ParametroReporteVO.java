package pe.com.edu.siaa.core.model.vo;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import pe.com.edu.siaa.core.model.type.NombreReporteType;
import pe.com.edu.siaa.core.model.type.RutaReporteType;

/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class ParametroReporteVO.
 *
 * @author ndavilal
 * @version 1.0 , 06/04/2015
 * @since SIAA-CORE 2.1
 */
public class ParametroReporteVO  implements Serializable {
	
	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** La parametros. */
	private Map<String, Object> parametros;
	
	/** La lista. */
	private List<?> lista;
	
	/** La jasper. */
	private NombreReporteType jasper;
	
	/** La jasper file. */
	private String jasperFile;
	
	/** La subreportes. */
	private String[] subreportes;
	
	/** La ruta reporte type. */
	private RutaReporteType rutaReporteType;
	
	/** La is bean. */
	private boolean isBean;
	
	/** La cn. */
	private Connection cn;
	
	/** La ruta reportes absoluto. */
	private String rutaReportesAbsoluto;
	
	/** La formato. */
	private String formato;
	
	/** La file name. */
	private String fileName;
	
	/** La crear archivo. */
	private boolean crearArchivo = false;
	
	/** La is online. */
	private boolean isOnline = false;
	
	/** La user name. */
	private String userName = "";
	
	/** La fecha generacion. */
	private Date fechaGeneracion;
	
	/** La criterio busqueda. */
	private Object criterioBusqueda;
	
	
	/** La big memory. */
	private boolean bigMemory = false;
	
	/** La requiere conexion. */
	private String nombreJNDIConexion; 
		
	/**  SWFACTORY INICIO*. */
	private Boolean withParameters;
	
	/** La tipo proceso. */
	private String tipoProceso;
	
	private boolean enviarCorreo = false;
	
	private String para;
	private String asunto;
	private String mensaje;
	
	
	private boolean esCopiaCorreo;
	
	
	/**
	 *  SWFACTORY FIN *.
	 */
	
	/**
	 * Instancia un nuevo parametro reporte vo.
	 */
	public ParametroReporteVO() {
		super();
	}


	/**
	 * Instancia un nuevo parametro reporte vo.
	 *
	 * @param parametros el parametros
	 * @param lista el lista
	 * @param jasper el jasper
	 * @param subreportes el subreportes
	 * @param rutaReporteType el ruta reporte type
	 * @param isBean el is bean
	 */
	public ParametroReporteVO(Map<String, Object> parametros, List<?> lista,
			NombreReporteType jasper, String[] subreportes,
			RutaReporteType rutaReporteType, boolean isBean) {
		super();
		this.parametros = parametros;
		this.lista = lista;
		this.jasper = jasper;
		this.subreportes = subreportes;
		this.rutaReporteType = rutaReporteType;
		this.isBean = isBean;
	}
	
	/**
	 * Instancia un nuevo parametro reporte vo.
	 *
	 * @param parametros el parametros
	 * @param lista el lista
	 * @param jasperFile el jasper file
	 * @param subreportes el subreportes
	 * @param rutaReporteType el ruta reporte type
	 * @param isBean el is bean
	 */
	public ParametroReporteVO(Map<String, Object> parametros, List<?> lista,
			String jasperFile, String[] subreportes,
			RutaReporteType rutaReporteType, boolean isBean) {
		super();
		this.parametros = parametros;
		this.lista = lista;
		this.jasperFile = jasperFile;
		this.subreportes = subreportes;
		this.rutaReporteType = rutaReporteType;
		this.isBean = isBean;
	}
	
	/**
	 * Instancia un nuevo parametro reporte vo.
	 *
	 * @param parametros el parametros
	 * @param lista el lista
	 * @param jasperFile el jasper file
	 * @param subreportes el subreportes
	 * @param rutaReporteType el ruta reporte type
	 * @param isBean el is bean
	 * @param cn el cn
	 */
	public ParametroReporteVO(Map<String, Object> parametros, List<?> lista,
			String jasperFile, String[] subreportes,
			RutaReporteType rutaReporteType, boolean isBean,Connection cn) {
		super();
		this.parametros = parametros;
		this.lista = lista;
		this.jasperFile = jasperFile;
		this.subreportes = subreportes;
		this.rutaReporteType = rutaReporteType;
		this.isBean = isBean;
		this.cn = cn;
	}
	
	/**
	 * Instancia un nuevo parametro reporte vo.
	 *
	 * @param parametros el parametros
	 * @param lista el lista
	 * @param jasper el jasper
	 * @param subreportes el subreportes
	 * @param rutaReporteType el ruta reporte type
	 * @param isBean el is bean
	 * @param cn el cn
	 */
	public ParametroReporteVO(Map<String, Object> parametros, List<?> lista,
			NombreReporteType jasper, String[] subreportes,
			RutaReporteType rutaReporteType, boolean isBean,Connection cn) {
		super();
		this.parametros = parametros;
		this.lista = lista;
		this.jasper = jasper;
		this.subreportes = subreportes;
		this.rutaReporteType = rutaReporteType;
		this.isBean = isBean;
		this.cn = cn;
	}
	
		
	
	/**
	 * Instancia un nuevo parametro reporte vo.
	 *
	 * @param parametros el parametros
	 * @param lista el lista
	 * @param jasper el jasper
	 * @param subreportes el subreportes
	 * @param rutaReporteType el ruta reporte type
	 * @param isBean el is bean
	 * @param formato el formato
	 * @param fileName el file name
	 */
	public ParametroReporteVO(Map<String, Object> parametros, List<?> lista,
			NombreReporteType jasper, String[] subreportes,
			RutaReporteType rutaReporteType, boolean isBean, String formato,
			String fileName) {
		super();
		this.parametros = parametros;
		this.lista = lista;
		this.jasper = jasper;
		this.subreportes = subreportes;
		this.rutaReporteType = rutaReporteType;
		this.isBean = isBean;
		this.formato = formato;
		this.fileName = fileName;
	}
	
	/**
	 * Instancia un nuevo parametro reporte vo.
	 *
	 * @param parametros el parametros
	 * @param lista el lista
	 * @param jasperFile el jasper file
	 * @param subreportes el subreportes
	 * @param rutaReporteType el ruta reporte type
	 * @param isBean el is bean
	 * @param formato el formato
	 * @param fileName el file name
	 */
	public ParametroReporteVO(Map<String, Object> parametros, List<?> lista,
			String jasperFile, String[] subreportes,
			RutaReporteType rutaReporteType, boolean isBean, String formato,
			String fileName) {
		super();
		this.parametros = parametros;
		this.lista = lista;
		this.jasperFile = jasperFile;
		this.subreportes = subreportes;
		this.rutaReporteType = rutaReporteType;
		this.isBean = isBean;
		this.formato = formato;
		this.fileName = fileName;
	}
	
	//get y set

	/**
	 * Obtiene parametros.
	 *
	 * @return parametros
	 */
	public Map<String, Object> getParametros() {
		return parametros;
	}


	/**
	 * Sets the parametros.
	 *
	 * @param parametros el parametros
	 */
	public void setParametros(Map<String, Object> parametros) {
		this.parametros = parametros;
	}


	/**
	 * Obtiene lista.
	 *
	 * @return lista
	 */
	public List<?> getLista() {
		return lista;
	}


	/**
	 * Establece el lista.
	 *
	 * @param lista el new lista
	 */
	public void setLista(List<?> lista) {
		this.lista = lista;
	}


	/**
	 * Obtiene jasper.
	 *
	 * @return jasper
	 */
	public NombreReporteType getJasper() {
		return jasper;
	}


	/**
	 * Establece el jasper.
	 *
	 * @param jasper el new jasper
	 */
	public void setJasper(NombreReporteType jasper) {
		this.jasper = jasper;
	}

	

	/**
	 * Obtiene jasper file.
	 *
	 * @return jasper file
	 */
	public String getJasperFile() {
		return jasperFile;
	}


	/**
	 * Establece el jasper file.
	 *
	 * @param jasperFile el new jasper file
	 */
	public void setJasperFile(String jasperFile) {
		this.jasperFile = jasperFile;
	}


	/**
	 * Obtiene subreportes.
	 *
	 * @return subreportes
	 */
	public String[] getSubreportes() {
		return subreportes;
	}


	/**
	 * Establece el subreportes.
	 *
	 * @param subreportes el new subreportes
	 */
	public void setSubreportes(String[] subreportes) {
		this.subreportes = subreportes;
	}


	/**
	 * Obtiene ruta reporte type.
	 *
	 * @return ruta reporte type
	 */
	public RutaReporteType getRutaReporteType() {
		return rutaReporteType;
	}


	/**
	 * Establece el ruta reporte type.
	 *
	 * @param rutaReporteType el new ruta reporte type
	 */
	public void setRutaReporteType(RutaReporteType rutaReporteType) {
		this.rutaReporteType = rutaReporteType;
	}


	/**
	 * Comprueba si es bean.
	 *
	 * @return true, si es bean
	 */
	public boolean isBean() {
		return isBean;
	}


	/**
	 * Establece el bean.
	 *
	 * @param isBean el new bean
	 */
	public void setBean(boolean isBean) {
		this.isBean = isBean;
	}


	/**
	 * Obtiene cn.
	 *
	 * @return cn
	 */
	public Connection getCn() {
		return cn;
	}


	/**
	 * Establece el cn.
	 *
	 * @param cn el new cn
	 */
	public void setCn(Connection cn) {
		this.cn = cn;
	}

	 

	/**
	 * Obtiene ruta reportes absoluto.
	 *
	 * @return ruta reportes absoluto
	 */
	public String getRutaReportesAbsoluto() {
		return rutaReportesAbsoluto;
	}


	/**
	 * Establece el ruta reportes absoluto.
	 *
	 * @param rutaReportesAbsoluto el new ruta reportes absoluto
	 */
	public void setRutaReportesAbsoluto(String rutaReportesAbsoluto) {
		this.rutaReportesAbsoluto = rutaReportesAbsoluto;
	}


	/**
	 * Obtiene formato.
	 *
	 * @return formato
	 */
	public String getFormato() {
		return formato;
	}


	/**
	 * Establece el formato.
	 *
	 * @param formato el new formato
	 */
	public void setFormato(String formato) {
		this.formato = formato;
	}


	/**
	 * Obtiene file name.
	 *
	 * @return file name
	 */
	public String getFileName() {
		return fileName;
	}


	/**
	 * Establece el file name.
	 *
	 * @param fileName el new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	/**
	 * Comprueba si es crear archivo.
	 *
	 * @return true, si es crear archivo
	 */
	public boolean isCrearArchivo() {
		return crearArchivo;
	}


	/**
	 * Establece el crear archivo.
	 *
	 * @param crearArchivo el new crear archivo
	 */
	public void setCrearArchivo(boolean crearArchivo) {
		this.crearArchivo = crearArchivo;
	}


	/**
	 * Comprueba si es online.
	 *
	 * @return true, si es online
	 */
	public boolean isOnline() {
		return isOnline;
	}


	/**
	 * Establece el online.
	 *
	 * @param isOnline el new online
	 */
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}


	/**
	 * Obtiene user name.
	 *
	 * @return user name
	 */
	public String getUserName() {
		return userName;
	}


	/**
	 * Establece el user name.
	 *
	 * @param userName el new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}


	/**
	 * Obtiene criterio busqueda.
	 *
	 * @return criterio busqueda
	 */
	public Object getCriterioBusqueda() {
		return criterioBusqueda;
	}


	/**
	 * Establece el criterio busqueda.
	 *
	 * @param criterioBusqueda el new criterio busqueda
	 */
	public void setCriterioBusqueda(Object criterioBusqueda) {
		this.criterioBusqueda = criterioBusqueda;
	}


	/**
	 * Comprueba si es big memory.
	 *
	 * @return true, si es big memory
	 */
	public boolean isBigMemory() {
		return bigMemory;
	}


	/**
	 * Establece el big memory.
	 *
	 * @param bigMemory el new big memory
	 */
	public void setBigMemory(boolean bigMemory) {
		this.bigMemory = bigMemory;
	}


	/**
	 *  SWFACTORY:  Tipo Proceso *.
	 *
	 * @return with parameters
	 */
	
	public Boolean getWithParameters() {
		return withParameters;
	}


	/**
	 * Establece el with parameters.
	 *
	 * @param withParameters el new with parameters
	 */
	public void setWithParameters(Boolean withParameters) {
		this.withParameters = withParameters;
	}
	
	/**
	 * Obtiene tipo proceso.
	 *
	 * @return tipo proceso
	 */
	public String getTipoProceso() {
		return tipoProceso;
	}
	
	/**
	 * Establece el tipo proceso.
	 *
	 * @param tipoProceso el new tipo proceso
	 */
	public void setTipoProceso(String tipoProceso) {
		this.tipoProceso = tipoProceso;
	}
	
	/**
	 * Obtiene nombre conexion.
	 *
	 * @return nombre conexion
	 */
	public String getNombreJNDIConexion() {
		return nombreJNDIConexion;
	}

	/**
	 * Establece el nombre conexion.
	 *
	 * @param nombreConexion el new nombre conexion
	 */
	public void setNombreJNDIConexion(String nombreJNDIConexion) {
		this.nombreJNDIConexion = nombreJNDIConexion;
	}

	/**
	 * Obtiene fecha generacion.
	 *
	 * @return fecha generacion
	 */
	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}

	/**
	 * Establece el fecha generacion.
	 *
	 * @param fechaGeneracion el new fecha generacion
	 */
	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}


	public boolean isEnviarCorreo() {
		return enviarCorreo;
	}


	public void setEnviarCorreo(boolean enviarCorreo) {
		this.enviarCorreo = enviarCorreo;
	}


	public String getPara() {
		return para;
	}


	public void setPara(String para) {
		this.para = para;
	}


	public String getAsunto() {
		return asunto;
	}


	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}


	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	public boolean isEsCopiaCorreo() {
		return esCopiaCorreo;
	}


	public void setEsCopiaCorreo(boolean esCopiaCorreo) {
		this.esCopiaCorreo = esCopiaCorreo;
	}
	
	
	
}
