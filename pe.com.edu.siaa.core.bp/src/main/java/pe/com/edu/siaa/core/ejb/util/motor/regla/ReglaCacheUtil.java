package pe.com.edu.siaa.core.ejb.util.motor.regla;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.drools.compiler.compiler.io.File;
import org.drools.compiler.compiler.io.memory.MemoryFileSystem;
import org.drools.compiler.kie.builder.impl.InternalKieModule;
import org.drools.compiler.kie.builder.impl.KieBuilderImpl;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.ReleaseId;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.conf.EqualityBehaviorOption;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;

import pe.com.edu.siaa.core.ejb.factory.CollectionUtil;
import pe.com.edu.siaa.core.ejb.util.jasper.ArchivoUtilidades;
import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.model.type.ReglaType;
import pe.com.edu.siaa.core.model.type.TipoErrorDroolsType;
import pe.com.edu.siaa.core.model.util.ConstanteConfigUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;
import pe.com.edu.siaa.core.model.vo.ErrorValidacionVO;
import pe.com.edu.siaa.core.model.vo.RespuestaReglaNegocioVO;

/**
 * <ul>
 * <li>Copyright 2014 siaa. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class ReglaCacheUtil.
 *
 * @author ndavilal
 * @version 1.0 , 28/06/2016
 * @since SIAA-CORE 2.0
 */
public class ReglaCacheUtil {
	
	/** La log. */
	private static Logger log = Logger.getLogger(ReglaCacheUtil.class);

	/** La Constante END. */
    private static final String END = "end";

	/** La Constante RULE. */
	private static final String RULE = "rule";
	
	/** La Constante SEPARADOR_FILE. */
	public static final String SEPARADOR_FILE = ConstanteConfigUtil.SEPARADOR_FILE;
	
	/** La Constante RUTA_RECURSOS_REGLA. */
	public static final String RUTA_RECURSOS_REGLA = ConstanteConfigUtil.RUTA_RECURSOS_REGLA;
	
	/** La regla cache util. */
	private static ReglaCacheUtil reglaCacheUtil;

	/** El required map. */
	private static Map<String, KieContainer> reglaMap = new HashMap<String, KieContainer>();
	
	/** La regla dls map. */
	private static Map<String, String> reglaDlsMap = new HashMap<String, String>();
	
	/** La regla dls cantidad linea map. */
	private static Map<String, Integer> reglaDlsCantidadLineaMap = new HashMap<String, Integer>();
	
	
	/**
	 * Obtiene la instancia de ReglaCacheUtil.
	 *
	 * @return instancia de ReglaCacheUtil
	 */
	public synchronized static ReglaCacheUtil getInstance() {
		if (reglaCacheUtil == null) {
			reglaCacheUtil = new ReglaCacheUtil();
			reglaCacheUtil.init();
		}
		return reglaCacheUtil;
	}

	/**
	 * Actualizar regla cache.
	 *
	 * @param nombreRegla el nombre regla
	 * @param ruleBase el rule base
	 */
	public void actualizarReglaCache(String nombreRegla,KieBase ruleBase) {
		
	}

	/**
	 * Inits the.
	 *
	 * @return the string
	 */
	public synchronized String init() {
		String resultado = null;
		// aqui cargar la regla
		leerRegla(ParametroReglaUtil.NOMBRE_ARCHIVO_IMPORTACIONES, RUTA_RECURSOS_REGLA + generarRuta ("rule_base") + ParametroReglaUtil.NOMBRE_ARCHIVO_IMPORTACIONES);
		try {
			leerRegla(ReglaType.CALCULAR_LIMITE_CREDITO.getKey(), RUTA_RECURSOS_REGLA + generarRuta ("rules") + ReglaType.CALCULAR_LIMITE_CREDITO.getKey() + ".drl");
			parsearRegla(reglaDlsMap.get(ReglaType.CALCULAR_LIMITE_CREDITO.getKey()) ,  ReglaType.CALCULAR_LIMITE_CREDITO.getKey());
		} catch (Exception e) {
			log.error(ReglaType.CALCULAR_LIMITE_CREDITO.getKey() + " ==> " + e);
			resultado = e.getMessage();
		}
		
		try {
			leerRegla(ReglaType.CURSOS_POSIBLES_MATRICULAR.getKey(), RUTA_RECURSOS_REGLA + generarRuta ("rules") + ReglaType.CURSOS_POSIBLES_MATRICULAR.getKey() + ".drl");
			parsearRegla(reglaDlsMap.get(ReglaType.CURSOS_POSIBLES_MATRICULAR.getKey()), ReglaType.CURSOS_POSIBLES_MATRICULAR.getKey());
		} catch (Exception e) {
			log.error(ReglaType.CURSOS_POSIBLES_MATRICULAR.getKey() + " ==> " + e);
			resultado = e.getMessage();
		}
		return resultado;
	}
	
	/**
	 * Generar ruta.
	 *
	 * @param ruta el ruta
	 * @return the string
	 */
	public static String generarRuta(String... ruta) {
		StringBuilder resultado = new StringBuilder();
		for (String carpeta : ruta) {
			resultado.append(carpeta);
			resultado.append("/");
		}
		return resultado.toString();
	}
	public static List<ErrorValidacionVO>  validarReglas(String regla) throws Exception {
	    List<ErrorValidacionVO> errorReglaNegocioList = new ArrayList<ErrorValidacionVO>();
		if (!StringUtils.isNullOrEmpty(regla)) {
			int numeroLineasRegla = ArchivoUtilidades.contarLineasTexto(regla);
			Long idCorrelativoActual = Long.valueOf(errorReglaNegocioList.size() + "");
			errorReglaNegocioList.addAll(contieneComillasSinCerrar(regla, idCorrelativoActual));
			idCorrelativoActual = Long.valueOf(errorReglaNegocioList.size() + "");
			errorReglaNegocioList.addAll(verificarRuleEnd(regla, idCorrelativoActual));
			if (CollectionUtil.isEmpty(errorReglaNegocioList)) {
				RespuestaReglaNegocioVO respuestaReglaNegocio = ProcesarReglaUtil.leerReglaDrlCompiler(regla, numeroLineasRegla);
				if (respuestaReglaNegocio.isRespuestaValidacion()) {
					errorReglaNegocioList.addAll(respuestaReglaNegocio.getErrorReglaNegocioList());
				}
			}
		} else {
			errorReglaNegocioList.add(new ErrorValidacionVO(1L, 0L, "", "Regla nula"));
		} 
		return errorReglaNegocioList;
	}
	
	/**
	 * Contiene comillas sin cerrar.
	 *
	 * @param regla el regla
	 * @param idCorrelativoActual el id correlativo actual
	 * @return the list
	 */
	private static List<ErrorValidacionVO> verificarRuleEnd(String regla, Long idCorrelativoActual) {
		List<ErrorValidacionVO> respuesta = new ArrayList<ErrorValidacionVO>();
		String TempRegla = regla.toLowerCase();
		byte[] content = TempRegla.getBytes();
		InputStream is = null;
		BufferedReader bfReader = null;
		int linea = 0;
		int lineaTemporalRule = 1;
		Long idCorrelativo = idCorrelativoActual;
		boolean buscarEnd = false;
		try {
			is = new ByteArrayInputStream(content);
			bfReader = new BufferedReader(new InputStreamReader(is));
			String temp = null;
			while ((temp = bfReader.readLine()) != null) {
				linea++;
				if (!buscarEnd) {
					if (temp.contains(RULE)) {
						buscarEnd = true;
						lineaTemporalRule = linea;
						continue;
					} else {
						 if (temp.contains(END)) {
							idCorrelativo++;
							ErrorValidacionVO errorValidacionVO = new ErrorValidacionVO();
							errorValidacionVO.setLineaError(Long.valueOf(linea + ""));
							errorValidacionVO.setIdCorrelativoError(idCorrelativo);
							errorValidacionVO.setDescripcionError("Completar la regla");
							errorValidacionVO.setTipoError(TipoErrorDroolsType.SISTEMA.getValue());
							errorValidacionVO.setErrorTecnico("Error de sintaxis");
							respuesta.add(errorValidacionVO);
							buscarEnd = false;
							continue;
						 }
					}
				}
				
				if (buscarEnd) {
					if (temp.contains(RULE)) {
						idCorrelativo++;
						ErrorValidacionVO errorValidacionVO = new ErrorValidacionVO();
						errorValidacionVO.setLineaError(Long.valueOf(lineaTemporalRule + ""));
						errorValidacionVO.setIdCorrelativoError(idCorrelativo);
						errorValidacionVO.setDescripcionError("Completar la regla");
						errorValidacionVO.setTipoError(TipoErrorDroolsType.SISTEMA.getValue());
						errorValidacionVO.setErrorTecnico("Error de sintaxis");
						respuesta.add(errorValidacionVO);
						lineaTemporalRule = linea;
					}
					if (temp.contains(END)) {
						buscarEnd = false;
					}
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		return respuesta;
	}
	/**
	 * Contiene comillas sin cerrar.
	 *
	 * @param regla el regla
	 * @param idCorrelativoActual el id correlativo actual
	 * @return the list
	 */
	private static List<ErrorValidacionVO> contieneComillasSinCerrar(String regla, Long idCorrelativoActual) {
		List<ErrorValidacionVO> respuesta = new ArrayList<ErrorValidacionVO>();
		String TempRegla = regla.toLowerCase();
		byte[] content = TempRegla.getBytes();
		InputStream is = null;
		BufferedReader bfReader = null;
		int contador = 1;
		Long idCorrelativo = idCorrelativoActual;
		try {
			is = new ByteArrayInputStream(content);
			bfReader = new BufferedReader(new InputStreamReader(is));
			String temp = null;
			while ((temp = bfReader.readLine()) != null) {
				if (temp.contains("")) {
					int contarComillas = ArchivoUtilidades.contarCarateres(temp, '"');
					if (contarComillas % 2 > 0) {
						idCorrelativo++;
						ErrorValidacionVO errorValidacionVO = new ErrorValidacionVO();
						errorValidacionVO.setLineaError(Long.valueOf(contador + ""));
						errorValidacionVO.setIdCorrelativoError(idCorrelativo);
						errorValidacionVO.setDescripcionError("Falta cerrar comillas");
						errorValidacionVO.setTipoError(TipoErrorDroolsType.SISTEMA.getValue());
						errorValidacionVO.setErrorTecnico("Error de sintaxis");
						respuesta.add(errorValidacionVO);
					}
				}
				contador++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return respuesta;
	}
	/**
	 * Parsear regla.
	 *
	 * @param reglaPersonalizado el regla personalizado
	 * @param nombreRegla el nombre regla
	 */
	public synchronized static void parsearRegla(String reglaPersonalizado, String nombreRegla) {
		try {
			if (validarReglas(reglaPersonalizado).size() > 0) {
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		try {
			String dslrImport = reglaDlsMap.get(ParametroReglaUtil.NOMBRE_ARCHIVO_IMPORTACIONES);
			reglaPersonalizado = dslrImport + "\n" + reglaPersonalizado;

			KieServices ks = KieServices.Factory.get();
			ReleaseId releaseId = ks.newReleaseId("org.kie.siaa.pwr", "rule-siaa", "1.0-SNAPSHOT");
			KieModuleModel kieModuleModel = obtenerBaseKieModuleModel(ks);
			kieModuleModel = obtenerBaseKieModuleModelPersonalizado(kieModuleModel, nombreRegla);
			
			KieFileSystem kfs = ks.newKieFileSystem().generateAndWritePomXML(releaseId)
			.write(generarRuta("src","main","resources","KBase",nombreRegla,"org","siaa","pkg") + nombreRegla + ".drl", reglaPersonalizado)
			.writeKModuleXML(kieModuleModel.toXML());
			
			ks.newKieBuilder(kfs).buildAll();
			
			InternalKieModule kieModule = (InternalKieModule) ks.getRepository().getKieModule(releaseId);
			byte[] jar = kieModule.getBytes();
			MemoryFileSystem mfs = MemoryFileSystem.readFromJar(jar);
			File file = mfs.getFile(KieBuilderImpl.getCompilationCachePath(releaseId, "kbase1"));
			file = mfs.getFile(KieBuilderImpl.getCompilationCachePath(releaseId, "kbase" + nombreRegla));
			Resource jarRes = ks.getResources().newByteArrayResource(jar);
			KieModule KieModule = ks.getRepository().addKieModule(jarRes);
			KieContainer kieContainer = ks.newKieContainer(KieModule.getReleaseId());
			// KieSession ksession = ks.newKieContainer(
			// KieModule.getReleaseId() ).newKieSession("KSession" +
			// nombreRegla);
			reglaMap.put("KSession" + nombreRegla, kieContainer);
		} catch (Exception e) {
			log.error(e);
		}

	}
	
	/**
	 * Obtener base kie module model.
	 *
	 * @param ks el ks
	 * @return the kie module model
	 */
	private static KieModuleModel obtenerBaseKieModuleModel(KieServices ks) {
		KieModuleModel kproj = ks.newKieModuleModel();
		kproj.newKieBaseModel("kbase1")
		.setEqualsBehavior(EqualityBehaviorOption.EQUALITY)
		.setEventProcessingMode(EventProcessingOption.STREAM)
		.addPackage("org.siaa.pkg")
		.newKieSessionModel("KSessionBase");
	    return kproj;
	}
	
	/**
	 * Obtener base kie module model personalizado.
	 *
	 * @param kproj el kproj
	 * @param nombreRegla el nombre regla
	 * @return the kie module model
	 */
	private static KieModuleModel obtenerBaseKieModuleModelPersonalizado(KieModuleModel kproj, String nombreRegla) {
		kproj.removeKieBaseModel("kbase" + nombreRegla);
		kproj.newKieBaseModel("kbase" + nombreRegla)
		.setEqualsBehavior(EqualityBehaviorOption.EQUALITY)
		.setEventProcessingMode(EventProcessingOption.STREAM)
		.addPackage("org.siaa.pkg")
		.addInclude("kbase1")
		.newKieSessionModel("KSession" + nombreRegla);
		return kproj;
	}
	
	/**
	 * Leer regla concatenar.
	 *
	 * @param pathFile el path file
	 * @return the string
	 */
	public static String leerReglaConcatenar(String... pathFile) {
		StringBuilder resultado = new StringBuilder();
		for (String key : pathFile) {
			if (reglaDlsMap.containsKey(key)) {
				resultado.append(reglaDlsMap.get(key) + "\n");
			}
		}
		return resultado.toString();
	}
	
	/**
	 * Leer regla cantidad linea.
	 *
	 * @param pathFile el path file
	 * @return the integer
	 */
	public static Integer leerReglaCantidadLinea(String... pathFile) {
		Integer resultado = 0;
		for (String key : pathFile) {
			if (reglaDlsCantidadLineaMap.containsKey(key)) {
				resultado = resultado + reglaDlsCantidadLineaMap.get(key);
			}
		}
		return resultado;
	}
	
	/**
	 * Leer regla.
	 *
	 * @param key el key
	 * @param pathFile el path file
	 */
	public void leerRegla(String key, String pathFile) {
		FileInputStream fis = null;
		BufferedReader bufferedReader = null;
		try {
			fis = new FileInputStream(pathFile);
			bufferedReader = new BufferedReader(new InputStreamReader(fis));
			Integer cantidadLinea = obenerCantidadLiena(bufferedReader);
			fis = new FileInputStream(pathFile);
			byte[] datosArchivo = datosArchivo = new byte[fis.available()];
			fis.read(datosArchivo);
			String regla = new String(datosArchivo);
			reglaDlsMap.put(key, regla);
			reglaDlsCantidadLineaMap.put(key, cantidadLinea);
		} catch (Exception e) {
			log.error(e);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
		}
	}
	
	/**
	 * Obener cantidad liena.
	 *
	 * @param br el br
	 * @return the integer
	 * @throws Exception the exception
	 */
	public static Integer obenerCantidadLiena(BufferedReader br) throws Exception {
		int resultado = 0;
		try {
			String line = "";
			while ((line = br.readLine()) != null) {
				resultado++;
			}
		} catch (Exception e) {
			log.error(e);
		}
		return resultado;
	}	
	
	/**
	 * Obtiene regla map.
	 *
	 * @return regla map
	 */
	public Map<String, KieContainer> getReglaMap() {
		return reglaMap;
	}

	/**
	 * Obtiene regla dls map.
	 *
	 * @return regla dls map
	 */
	public Map<String, String> getReglaDlsMap() {
		return reglaDlsMap;
	}
	
	/**
	 * Obtiene regla dls cantidad linea map.
	 *
	 * @return regla dls cantidad linea map
	 */
	public static Map<String, Integer> getReglaDlsCantidadLineaMap() {
		return reglaDlsCantidadLineaMap;
	}
	
}