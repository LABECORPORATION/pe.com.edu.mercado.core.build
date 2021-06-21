package pe.com.edu.siaa.core.ejb.util.motor.regla;
import java.util.ArrayList;
import java.util.List;

import org.drools.compiler.commons.jci.compilers.EclipseCompilationProblem;
import org.drools.compiler.compiler.DescrBuildError;
import org.drools.compiler.compiler.ParserError;
import org.drools.compiler.compiler.RuleBuildError;
import org.drools.compiler.lang.ExpanderException;
import org.kie.api.KieServices;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

import pe.com.edu.siaa.core.ejb.factory.CollectionUtil;
import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.model.type.CodigoAreaDroolsType;
import pe.com.edu.siaa.core.model.type.TipoErrorDroolsType;
import pe.com.edu.siaa.core.model.util.StringUtils;
import pe.com.edu.siaa.core.model.vo.ErrorValidacionVO;
import pe.com.edu.siaa.core.model.vo.RespuestaReglaNegocioVO;

public class ProcesarReglaUtil <E> {
	
	/** La Constante LOG. */
	private static final Logger log = Logger.getLogger(ProcesarReglaUtil.class);
	/**
	 * Procesar regla.
	 *
	 * @param listaMemoria el lista memoria
	 * @param regla el regla
	 * @throws Exception the exception
	 */
	public static <E> void procesarRegla(List<E> listaMemoria, String regla) throws Exception {
        //Cargamos la base de reglas
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ReglaCacheUtil.getInstance().getReglaMap().get("KSession" + regla);
	    //KieSession kieSession = kContainer.newKieSession(regla);
	    KieSession kieSession = kContainer.newKieSession("KSession" + regla);
        //insertamos un objeto al motor
         for (E e : listaMemoria) {
        	 kieSession.insert(e);
        }
        //Disparamos las reglas de Drools
         kieSession.fireAllRules();
         kieSession.dispose();
        
    }
	
	/**
	 * Procesar regla.
	 *
	 * @param memoria el memoria
	 * @param regla el regla
	 * @throws Exception the exception
	 */
	public static <E>  void procesarRegla(E memoria, String regla) throws Exception {
        //Cargamos la base de reglas
		KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ReglaCacheUtil.getInstance().getReglaMap().get("KSession" + regla);
        //KieSession kieSession = kContainer.newKieSession(regla);
        KieSession kieSession = kContainer.newKieSession("KSession" + regla);
        //insertamos un objeto al motor
        kieSession.insert(memoria);

        //Disparamos las reglas de Drools
        kieSession.fireAllRules();
        
        kieSession.dispose();
    }
	
	public static KieContainer leerRegla() {
		try {
			// Leemos el archivo de reglas (DRL)
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			return kContainer;
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

	public static String leerRegla(String reglaNombreArchivo) {
		if (ReglaCacheUtil.getInstance().getReglaDlsMap().containsKey(reglaNombreArchivo)) {
			return ReglaCacheUtil.getInstance().getReglaDlsMap().get(reglaNombreArchivo);
		}
		return "";
	}

	public static Integer leerReglaCantidadLinea(String reglaNombreArchivo) {
		if (ReglaCacheUtil.getInstance().getReglaDlsCantidadLineaMap().containsKey(reglaNombreArchivo)) {
			return ReglaCacheUtil.getInstance().getReglaDlsCantidadLineaMap().get(reglaNombreArchivo);
		}
		return 0;
	}
	
	public static List<ErrorValidacionVO> procesarErrorRegla(KnowledgeBuilder kbuilder, Integer numeroLineasRegla) {
		List<ErrorValidacionVO> listaErrorReglaVO = new ArrayList<ErrorValidacionVO>();
		for (KnowledgeBuilderError error : kbuilder.getErrors()) {
			boolean esMetodoDuplicado = false;
			StringBuilder errorTecnico = new StringBuilder();
			errorTecnico.append(error.getMessage());
			ErrorValidacionVO errorReglaVO = new ErrorValidacionVO();
			StringBuilder mensajeError = new StringBuilder();
			String codigoError = error.getMessage().substring(1,8);
			if (error instanceof DescrBuildError) {
				codigoError = CodigoAreaDroolsType.ERR_001.getValue();
			}
			if (error instanceof ExpanderException) {
				codigoError = CodigoAreaDroolsType.ERR_EXP_001.getValue();
				errorTecnico.append("");
			}
			if (error instanceof RuleBuildError) {
				if (listaErrorReglaVO.size() == 0) {
					codigoError = CodigoAreaDroolsType.ERR_103.getValue();
					RuleBuildError ruleBuildError = (RuleBuildError) error;
					errorTecnico.append("\n");
					errorTecnico.append(ruleBuildError.getMessage());
					Object[] object = (Object[]) ruleBuildError.getObject();
					for (Object data : object) {
						if (data instanceof EclipseCompilationProblem) {
							EclipseCompilationProblem problema = (EclipseCompilationProblem) data;
							errorTecnico.append("\n");
							errorTecnico.append(problema.getMessage());
						}
					}

				}
			}
			
			if (error instanceof ParserError) {
				codigoError = CodigoAreaDroolsType.ERR_102.getValue();
				if (error.getMessage().contains("Duplicate rule name")) {
					esMetodoDuplicado = true;
				}
			}
			
			log.error(error.getMessage());  //ESCRIBIR ERROR EN EL LOG.
			
			if (!StringUtils.isNullOrEmpty(codigoError)) {
				codigoError = codigoError.trim();
				if (!CodigoAreaDroolsType.getLooKupMap().containsKey(codigoError)) {
					continue;
				} 
			}
			CodigoAreaDroolsType areaDroolsType = CodigoAreaDroolsType.get(codigoError);
			errorReglaVO.setTipoError(TipoErrorDroolsType.SINTAXIS.getValue());
			
	        Integer cantidadImportacion = leerReglaCantidadLinea(ParametroReglaUtil.NOMBRE_ARCHIVO_IMPORTACIONES);
	        Long lineaErrorOcurrido = Long.valueOf(error.getLines()[0]);
	        Long lineaError = 1L;
	        
	        if (lineaErrorOcurrido > 0) {
	        	lineaError = lineaErrorOcurrido - cantidadImportacion;
	        } else {
	        	continue;
	        }
	        errorReglaVO.setLineaError(lineaError);
			errorReglaVO.setErrorTecnico(errorTecnico.toString());
	        
			switch (areaDroolsType) {
			case ERR_101:
			case ERR_EXP_001:
				mensajeError.append("Error de Sintaxis no existe la palabra reservada ");
				errorReglaVO.setDescripcionError(mensajeError.toString());
				break;
			
			case ERR_102:
				if (esMetodoDuplicado) {
					mensajeError.append("Error ya existe un regla con ese nombre ");
				} else {
					mensajeError.append("La estructura de la regla contiene errores en la linea ");	
				}
				errorReglaVO.setDescripcionError(mensajeError.toString());
				break;
				
			case ERR_103:
				mensajeError.append("Error de Sintaxis la regla contiene caracteres extraños ");
				errorReglaVO.setDescripcionError(mensajeError.toString());
				break;
				
			case ERR_104:
				mensajeError.append("Error de Sintaxis punto y coma no permitido ");
				errorReglaVO.setDescripcionError(mensajeError.toString());
				break;
	
			case ERR_105:
				mensajeError.append("Error de Sintaxis no se ha encontrado la entrada para ");
				errorReglaVO.setDescripcionError(mensajeError.toString());
				break;
				
			case ERR_107:
				mensajeError.append("La estructura de la regla contiene errores en la linea ");
				errorReglaVO.setDescripcionError(mensajeError.toString());
				break;
				
			case ERR_001:
				mensajeError.append("No se encontro el objeto o clase ");
				errorReglaVO.setDescripcionError(mensajeError.toString());
				break;
			
			default:
				mensajeError.append("Ha sucesido un error desconocido ponganse en contacto con el administrador del sistema ");
				errorReglaVO.setTipoError(TipoErrorDroolsType.SISTEMA.getValue());
				errorReglaVO.setDescripcionError(mensajeError.toString());
				break;
			}
			listaErrorReglaVO.add(errorReglaVO);
		}
		return listaErrorReglaVO;
	}

	public static RespuestaReglaNegocioVO leerReglaDrlCompiler(String nombreReglaDrl, Integer numeroLineasRegla) {
		RespuestaReglaNegocioVO resultado = new RespuestaReglaNegocioVO();
		List<ErrorValidacionVO> listaErrores = new ArrayList<ErrorValidacionVO>();
		try {
			KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
			String dlrImport = leerRegla(ParametroReglaUtil.NOMBRE_ARCHIVO_IMPORTACIONES);
			nombreReglaDrl = dlrImport + "\n" + nombreReglaDrl;
			kbuilder.add(ResourceFactory.newByteArrayResource(nombreReglaDrl.getBytes("UTF-8")), ResourceType.DRL);
			if (kbuilder.hasErrors()) {
				listaErrores.addAll(procesarErrorRegla(kbuilder, numeroLineasRegla));
			}
			if (CollectionUtil.isEmpty(listaErrores)) {
				resultado.setRespuestaValidacion(false);
			} else {
				resultado.getErrorReglaNegocioList().addAll(listaErrores);
				resultado.setRespuestaValidacion(true);
			}
		} catch (Exception e) {
			ErrorValidacionVO errorReglaNegocioVO = new ErrorValidacionVO();
			Long idError = Long.valueOf(resultado.getErrorReglaNegocioList().size() + 1);
			errorReglaNegocioVO.setIdCorrelativoError(idError);
			errorReglaNegocioVO.setLineaError(0L);
			errorReglaNegocioVO.setDescripcionError("Error No Controlado pongase en contacto con el administrador del sistema");
			errorReglaNegocioVO.setErrorTecnico(e.getMessage());
			resultado.getErrorReglaNegocioList().add(errorReglaNegocioVO);
			log.error(e);
			return resultado;
		}
		CollectionUtil.ordenador(false, resultado.getErrorReglaNegocioList(), "lineaError");
		Long idError = 1L;
		for (ErrorValidacionVO objError : resultado.getErrorReglaNegocioList()) {
			objError.setIdCorrelativoError(idError);
			idError++;
		}
		return resultado;
	}
}



