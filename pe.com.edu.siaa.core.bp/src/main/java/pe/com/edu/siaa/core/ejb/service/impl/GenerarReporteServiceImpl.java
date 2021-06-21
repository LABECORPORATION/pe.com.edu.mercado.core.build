package pe.com.edu.siaa.core.ejb.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import net.sf.jasperreports.engine.JasperPrint;
import pe.com.edu.siaa.core.ejb.dao.local.ReporteDaoLocal;
import pe.com.edu.siaa.core.ejb.service.local.GenerarReporteServiceLocal;
import pe.com.edu.siaa.core.ejb.service.local.MensajeriaServiceLocal;
import pe.com.edu.siaa.core.ejb.service.util.FechaUtil;
import pe.com.edu.siaa.core.ejb.util.cache.SessionUtil;
import pe.com.edu.siaa.core.ejb.util.jasper.AdministradorReportes;
import pe.com.edu.siaa.core.ejb.util.jasper.ConstanteGenerarReporteUtil;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.model.type.RutaReporteType;
import pe.com.edu.siaa.core.model.type.TipoReporteGenerarType;
import pe.com.edu.siaa.core.model.vo.FileVO;
import pe.com.edu.siaa.core.model.vo.ParametroReporteVO;

@Stateless
@EJB(name = "java:app/GenerarReporteService", beanInterface = GenerarReporteServiceLocal.class)
@TransactionManagement(TransactionManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class GenerarReporteServiceImpl implements GenerarReporteServiceLocal {

	private Logger log = Logger.getLogger(GenerarReporteServiceImpl.class);
	
	/** La reporte dao impl. */
	@EJB
	private ReporteDaoLocal reporteDaoImpl;
	
	/** La MensajeriaServiceLocal. */
	@EJB
	private MensajeriaServiceLocal mensajeriaServiceImpl;
	
	
	@Override
	public  String obtenerParametroReporteBigMemory(ParametroReporteVO parametroReporteVO) {
		RutaReporteType rutaReporteType = RutaReporteType.JASPER;
		parametroReporteVO.setRutaReporteType(rutaReporteType);
		parametroReporteVO.setOnline(false);
		parametroReporteVO.setBigMemory(true);
		parametroReporteVO.setCrearArchivo(false);
		//parametroReporteVO.getParametros().put(ConstanteGenerarReporteUtil.CONTEXT_URL, Utilitario.obtenerContextUtl());
		//SwapVirtualizadorCacheUtil.getInstance().cleanup(usuario,super.getCodigoReporteGenerado());
		return generarReporteCacheTemp(parametroReporteVO);
	}
	
	
	private String generarReporteCacheTemp(ParametroReporteVO parametroReporteVO) {
		FileVO objeto = new FileVO();
        String resultado = "";
		String filename = parametroReporteVO.getFileName();
		boolean enviarCorreo = false;
		try {
			objeto = generarReporte(parametroReporteVO);
			if (objeto.getReporteGeneradoMap() == null) {
				objeto.setReporteGeneradoMap(new HashMap<String, Object>());
			} 
			//objeto.getReporteGeneradoMap().put(ConstanteReporteUtil.JASPER_CABECERA_STYLE_PERSONALIZADO, parametroReporteVO.getParametros().get(ConstanteReporteUtil.JASPER_CABECERA_STYLE_PERSONALIZADO));
		} catch (Exception e) {
			log.error(e);
		}
	    enviarCorreo = parametroReporteVO.isEnviarCorreo(); 
		/*if (parametroReporteVO.getCriterioEnvioCorreoVO() != null ) {
			if (parametroReporteVO.getCriterioEnvioCorreoVO().getPara() != null) {
				enviarCorreo = true;
				parametroReporteVO.setCrearArchivo(true);
			}
		}*/
		if (enviarCorreo && objeto != null) {//TODO:VER SI SE VA A USAR
			FileVO fileVO = new FileVO();
			Map<String,Object> reporteGeneradoTemp = objeto.getReporteGeneradoMap();
			JasperPrint jasperPrint = (JasperPrint) reporteGeneradoTemp.get(ConstanteGenerarReporteUtil.JASPER_PRINT);
			try {
				String nombreReporteEnviar = parametroReporteVO.getJasper().getValue() + "" + FechaUtil.obtenerFechaFormatoPersonalizado(FechaUtil.obtenerFechaActual(), "yyyyMMddHHmmss") ;
				String fileNameAntes = parametroReporteVO.getFileName();
				parametroReporteVO.setFileName(nombreReporteEnviar);
				String rutaSession = AdministradorReportes.generarRutaReporte(parametroReporteVO);
				fileVO = generarReporte(jasperPrint,parametroReporteVO.getFormato() , null,rutaSession);
				//ArchivoUtilidades.subirArchivoReporte(fileVO.getData(), nombreReporteEnviar, parametroReporteVO.getUserName(), false);
				fileVO.setName(nombreReporteEnviar + "." + parametroReporteVO.getFormato() );
				mensajeriaServiceImpl.enviarMailDataReporte(parametroReporteVO.getPara(),parametroReporteVO.getAsunto(),parametroReporteVO.getMensaje(),parametroReporteVO.getUserName(), fileVO);
				parametroReporteVO.setFileName(fileNameAntes);
				fileVO = new FileVO();//liberando memoria
			} catch (Exception e) {
				log.error(e);
			}
			
		}
		objeto.setEsCopiaCorreo(parametroReporteVO.isEsCopiaCorreo());
		resultado = filename;
		if (parametroReporteVO.isOnline()) {
			SessionUtil.pasarParametro(resultado, objeto);
		}
		if (parametroReporteVO.isBigMemory()) {
			objeto.setDataBig(objeto.getName());
			SessionUtil.pasarParametro(resultado, objeto);
		}
		parametroReporteVO.setLista(null);//liberando menoria
		return resultado;
	}
	
	@Override
	public FileVO generarReporte(ParametroReporteVO parametroReporteVO) throws Exception {
		FileVO resultado = new FileVO();
		String codigo = "";
		String contentType = "";
		String filename = parametroReporteVO.getFileName();
		byte[] reporteGenerado = null;
		Map<String,Object> reporteGeneradoTemp = null;
		TipoReporteGenerarType tipoReporteGenerarType = TipoReporteGenerarType.get(parametroReporteVO.getFormato());
			switch (tipoReporteGenerarType) {
				case PDF:
						reporteGeneradoTemp = generarReporteArrayPdf(parametroReporteVO);
						break;
				case HTML:
					    reporteGeneradoTemp = generarReporteArrayHtml(parametroReporteVO);
					break;
					
				case XLS:
						reporteGeneradoTemp  = generarReporteArrayXls(parametroReporteVO);
					break;
					
				case RTF:
						reporteGeneradoTemp = generarReporteArrayRtf(parametroReporteVO);
					break;
					
				case ODT:
						reporteGeneradoTemp = generarReporteArrayOdt(parametroReporteVO);
					break;
					
				case ODS:
						reporteGeneradoTemp = generarReporteArrayOds(parametroReporteVO);
					break;
					
				case DOCX:
						reporteGeneradoTemp = generarReporteArrayDocx(parametroReporteVO);
					break;
					
				case PPTX:
						reporteGeneradoTemp = generarReporteArrayPptx(parametroReporteVO);
					break;
					
				case XLSX:
						reporteGeneradoTemp = generarReporteArrayXlsx(parametroReporteVO);
					break;
	
				default:
					break;
			}
		contentType = tipoReporteGenerarType.getContentType();
		if (tipoReporteGenerarType != null) {
			filename += "."+ tipoReporteGenerarType.getKey();
		}
		if (reporteGeneradoTemp != null) {
			if (reporteGeneradoTemp.containsKey(ConstanteGenerarReporteUtil.BYTE)) {
				reporteGenerado = (byte[])reporteGeneradoTemp.get(ConstanteGenerarReporteUtil.BYTE);
			}
		}
		resultado.setReporteGeneradoMap(reporteGeneradoTemp);
		byte[] bufferFinal = reporteGenerado;
		codigo = filename;
		if (reporteGeneradoTemp != null) {
			if (reporteGeneradoTemp.containsKey(ConstanteGenerarReporteUtil.BYTE)) {
				resultado.setData(bufferFinal);
				resultado.setLength(bufferFinal.length);
			}
		}
		reporteGenerado = null;//liberando memoria
		resultado.setName(codigo);
		resultado.setMime(contentType);
		resultado.setUserName(parametroReporteVO.getUserName());
		return resultado;
	}
	
	@Override
	public FileVO generarReporte(JasperPrint jasperPrint, String formato, String context,String ruta) {
		FileVO resultado = new FileVO();
		String contentType = "";
		TipoReporteGenerarType tipoReporteGenerarType = TipoReporteGenerarType.get(formato);
			switch (tipoReporteGenerarType) {
				case PDF:
					    AdministradorReportes.generarReporteArrayPdf(jasperPrint,ruta);
						break;
				case HTML:
					     AdministradorReportes.generarReporteArrayHtml(jasperPrint,context,ruta);
					break;
					
				case XLS:
					    AdministradorReportes.generarReporteArrayXls(jasperPrint,ruta);
					break;
					
				case RTF:
					    AdministradorReportes.generarReporteArrayRtf(jasperPrint,ruta);
					break;
					
				case ODT:
					    AdministradorReportes.generarReporteArrayOdt(jasperPrint,ruta);
					break;
					
				case ODS:
					    AdministradorReportes.generarReporteArrayOds(jasperPrint,ruta);
					break;
					
				case DOCX:
					    AdministradorReportes.generarReporteArrayDocx(jasperPrint,ruta);
					break;
					
				case PPTX:
					    AdministradorReportes.generarReporteArrayPptx(jasperPrint,ruta);
					break;
					
				case XLSX:
					     AdministradorReportes.generarReporteArrayXlsx(jasperPrint,ruta);
					break;
					/*SWFACTORY INICIO 17-12-2015*/	
				case XLSX2:
					  AdministradorReportes.generarReporteArrayXlsx2(jasperPrint,ruta);
					break;
				/*SWFACTORY FIN*/	
				default:
					break;
			}
		contentType = tipoReporteGenerarType.getContentType();
		resultado.setDataBig(UUIDUtil.generarElementUUID());
		resultado.setMime(contentType);
		return resultado;
	}
	@Override
	public Map<String,Object> generarReporteArrayPdf(ParametroReporteVO parametroReporteVO) throws Exception {
		if (parametroReporteVO.isBean()) {
			return reporteDaoImpl.generarReporteArrayPdfBean(parametroReporteVO);
		} else {
			return reporteDaoImpl.generarReporteArrayPdf(parametroReporteVO);
		}
	}

	@Override
	public Map<String,Object> generarReporteArrayHtml(ParametroReporteVO parametroReporteVO) throws Exception {
		if (parametroReporteVO.isBean()) {
			return reporteDaoImpl.generarReporteArrayHtmlBean(parametroReporteVO);
		} else {
			return reporteDaoImpl.generarReporteArrayHtml(parametroReporteVO);
		}
	}

	@Override
	public Map<String,Object> generarReporteArrayXls(ParametroReporteVO parametroReporteVO) throws Exception {
		if (parametroReporteVO.isBean()) {
			return reporteDaoImpl.generarReporteArrayXlsBean(parametroReporteVO);
		} else {
			return reporteDaoImpl.generarReporteArrayXls(parametroReporteVO);
		}
	}

	@Override
	public Map<String,Object> generarReporteArrayRtf(ParametroReporteVO parametroReporteVO) throws Exception {
		if (parametroReporteVO.isBean()) {
			return reporteDaoImpl.generarReporteArrayRtfBean(parametroReporteVO);
		} else {
			return reporteDaoImpl.generarReporteArrayRtf(parametroReporteVO);
		}
	}

	@Override
	public Map<String,Object> generarReporteArrayOdt(ParametroReporteVO parametroReporteVO) throws Exception {
		if (parametroReporteVO.isBean()) {
			return reporteDaoImpl.generarReporteArrayOdtBean(parametroReporteVO);
		} else {
			return reporteDaoImpl.generarReporteArrayOdt(parametroReporteVO);
		}
	}

	@Override
	public Map<String,Object> generarReporteArrayOds(ParametroReporteVO parametroReporteVO) throws Exception {
		if (parametroReporteVO.isBean()) {
			return reporteDaoImpl.generarReporteArrayOdsBean(parametroReporteVO);
		} else {
			return reporteDaoImpl.generarReporteArrayOds(parametroReporteVO);
		}
	}

	@Override
	public Map<String,Object> generarReporteArrayDocx(ParametroReporteVO parametroReporteVO) throws Exception {
		if (parametroReporteVO.isBean()) {
			return reporteDaoImpl.generarReporteArrayDocxBean(parametroReporteVO);
		} else {
			return reporteDaoImpl.generarReporteArrayDocx(parametroReporteVO);
		}
	}

	@Override
	public Map<String,Object> generarReporteArrayPptx(ParametroReporteVO parametroReporteVO) throws Exception {
		if (parametroReporteVO.isBean()) {
			return reporteDaoImpl.generarReporteArrayPptxBean(parametroReporteVO);
		} else {
			return reporteDaoImpl.generarReporteArrayPptx(parametroReporteVO);
		}
	}

	@Override
	public Map<String,Object> generarReporteArrayXlsx(ParametroReporteVO parametroReporteVO) throws Exception {
		if (parametroReporteVO.isBean()) {
			return reporteDaoImpl.generarReporteArrayXlsxBean(parametroReporteVO);
		} else {
			return reporteDaoImpl.generarReporteArrayXlsx(parametroReporteVO);
		}
	}

	
	
}