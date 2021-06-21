package pe.com.edu.siaa.core.ejb.util.jms;

import java.io.File;
import java.io.StringWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import pe.com.edu.siaa.core.ejb.util.log.Logger;

/**
 * <ul>
 * <li>Copyright 2012 UNIVERSIDAD PRIVADA DE PUCALLPA - UPP. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Class ProcesarPlantillaUtil.
 *
 * @author ndavilal and lpereirat.
 * @version 1.0 , 27/07/2013
 * @since SIAA 2.0
 */
public class ProcesarPlantillaUtil {
	
	/** La Constante LOG. */
	private static final Logger LOG = Logger.getLogger(ProcesarPlantillaUtil.class);
    
	
	/**
	 * Procesar plantilla by freemarker do.
	 *
	 * @param dataModel el data model
	 * @param basePath el base path
	 * @param template el template
	 * @return the string
	 */
	public static String procesarPlantillaByFreemarkerDo(Map<String,Object> dataModel,String basePath,String template) {
		String resultado = "";
	    // Configuration
	    Configuration cfg = new Configuration();

	    try {
	      // Set Directory for templates
	      cfg.setDirectoryForTemplateLoading(new File(basePath));
	      // load template
	      Template tpl = cfg.getTemplate(template);
	      
	      // data-model = dataModel
	      
	     StringWriter sw = new StringWriter();

		 tpl.process(dataModel, sw);
		 
		 resultado = sw.toString();
         
	    } catch (Exception e) {
	    	LOG.error(e.getMessage());

	    } 
	    
	    return resultado;

	  }
	
	
}
