package pe.com.edu.siaa.core.ejb.util.jms;

import java.io.IOException;

import pe.com.edu.siaa.core.ejb.cache.ConfiguracionCacheUtil;

// TODO: Auto-generated Javadoc
/**
 * La Class MailMessages.
 * <ul>
 * <li>Copyright 2017 ndavilal-
 * mapfre. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Fri Apr 25 18:56:23 COT 2017
 * @since SIAA-CORE 2.1
 */
public class MailMessages {	
        /**
         * Obtiene parametro.
         *
         * @param key el key
         * @return parametro
         * @throws IOException Se�ales de que una excepci�n de E / S se ha producido.
         */
        public static String getParametro(String key) throws IOException{
		return ConfiguracionCacheUtil.getInstance().getMailServer(key);
	}

}
