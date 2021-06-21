package pe.com.edu.siaa.core.ejb.util.log;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.PropertyConfigurator;

/**
 * <ul>
 * <li>Copyright 2010 MAPFRE-
 * mapfre. Todos los derechos reservados.</li>
 * </ul>
 * 
 * Clase Utilitaria para el uso de Log4j.
 *
 * @author ndavilal
 * @version 1.0 , 13/02/2012
 * @since SIAA-CORE 2.1
 */
public class Logger implements Serializable {
	
	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** El log. */
	private transient static org.apache.log4j.Logger log;
	
	/** El objeto properties. */
	private static final Properties properties = new Properties();
	
	/** La Constante LOG4J_PROPERTIES. */
	private static final String LOG4J_PROPERTIES = "pe/com/edu/siaa/core/ejb/util/log/log4j.properties";

	/** La version pase. */
	private static final String VERSION_PASE = "Rev 1.0 " + "10/05/2016 ";
	
	/** La Constante USER_DEFAULT. */
	private static final String USER_DEFAULT = VERSION_PASE + "USER_REP_SYSTEM";
	
	/** La Constante EXCEPTION. */
	private static final String EXCEPTION = "Exception";
	

	/**
	 * Gets the logger.
	 *
	 * @param class1 the class1
	 * @return the logger
	 */
	public static  Logger getLogger(Class class1) {
		Logger resultado = new Logger();
		try {
			properties.load(Logger.class.getClassLoader()
					.getResourceAsStream(LOG4J_PROPERTIES));
			PropertyConfigurator.configure(properties);
		} catch (IOException e) {
			log.error("Error Logger.getLogger(Class class1) no se pudo cargar el properties " + LOG4J_PROPERTIES);
		}
		log = org.apache.log4j.Logger.getLogger(class1);
		return resultado;
	}
	

	/**
	 * Constructor de la clase.
	 *
	 * @param name nombre del Logger
	 */
	public static  Logger getLogger(String name) {
		Logger resultado = new Logger();
		try {
			properties.load(Logger.class.getClassLoader()
					.getResourceAsStream(LOG4J_PROPERTIES));
			PropertyConfigurator.configure(properties);
		} catch (IOException e) {
			log.error("Error Logger.getLogger(Class class1) no se pudo cargar el properties " + LOG4J_PROPERTIES);
		}
		log = org.apache.log4j.Logger.getLogger(name);
		return resultado;
	}
	
	/**
	 * Log de Level Warn,
	 * utiliza el mensaje de la excepcion por defecto.
	 *
	 * @param exception excepcion
	 */
	public void warn(Exception exception) {
		if (isHabilitadoWarn()) {
			String message = mensajeTipoError(exception);
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			log.warn(USER_DEFAULT.concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Warn,
	 * utiliza el mensaje de la excepcion por defecto.
	 *
	 * @param exception excepcion
	 */
	public void warn(NoClassDefFoundError exception) {
		if (isHabilitadoWarn()) {
			String message = EXCEPTION;
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			log.warn(USER_DEFAULT.concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Warn, utilizando el username del usuario para mostrar,
	 * utiliza el mensaje de la excepcion por defecto.
	 *
	 * @param username username del usuario
	 * @param exception excepcion
	 */
	public void warn(String username, Exception exception) {
		if (isHabilitadoWarn()) {
			String message = mensajeTipoError(exception);
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			username = username == null ? "" : username;
			log.warn(VERSION_PASE + username.concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Warn, utilizando el username del usuario para mostrar,
	 * utiliza el mensaje de la excepcion por defecto.
	 *
	 * @param username username del usuario
	 * @param exception excepcion
	 */
	public void warn(String username, NoClassDefFoundError exception) {
		if (isHabilitadoWarn()) {
			String message = EXCEPTION;
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			username = username == null ? "" : username;
			log.warn(VERSION_PASE + username.concat(" - ").concat(message) , exception);
		}
	}

	/**
	 * Log de Level Warn, utilizando el username del usuario para mostrar.
	 *
	 * @param username username del usuario
	 * @param mensaje mensaje de la excepcion
	 * @param exception excepcion
	 */
	public void warn(String username, String mensaje, Exception exception) {
		if (isHabilitadoWarn()) {
			username = username == null ? "" : username;
			mensaje = mensaje == null ? "" : mensaje;
			String message = mensajeTipoError(exception).concat(" : ").concat(mensaje);
			log.info(VERSION_PASE + username.concat(" - ").concat(message), exception);
		}
	}
	
	/**
	 * Log de Level Warn, utilizando el username del usuario para mostrar.
	 *
	 * @param username username del usuario
	 * @param mensaje mensaje de la excepcion
	 * @param exception excepcion
	 */
	public void warn(String username, String mensaje, NoClassDefFoundError exception) {
		if (isHabilitadoWarn()) {
			username = username == null ? "" : username;
			mensaje = mensaje == null ? "" : mensaje;
			String message = EXCEPTION.concat(" : ").concat(mensaje);
			log.info(VERSION_PASE + username.concat(" - ").concat(message), exception);
		}
	}
	
	
	/**
	 * Log de Level Warn, utilizando el username del usuario para mostrar.
	 *
	 * @param username username del usuario
	 * @param mensaje mensaje a imprimir
	 */
	public void warn(String username, String mensaje) {
		if (isHabilitadoWarn()) {
			username = username == null ? "" : username;
			mensaje = mensaje == null ? "" : mensaje;
			log.info(VERSION_PASE + username.concat(" - ").concat(mensaje));
		}
	}
	
	/**
	 * Log de Level Warn.
	 *
	 * @param mensaje mensaje a imprimir
	 */
	public void warn(String mensaje) {
		if (isHabilitadoWarn()) {
			mensaje = mensaje == null ? "" : mensaje;
			log.warn(USER_DEFAULT.concat(" - ").concat(mensaje));
		}
	}
	
	/**
	 * Log de Level Error.
	 *
	 * @param exception excepcion
	 */
	public void error(Exception exception) {
		if (isHabilitadoError()) {
			String message = mensajeTipoError(exception);
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			log.error(USER_DEFAULT.concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Error.
	 *
	 * @param exception excepcion
	 */
	public void error(NoClassDefFoundError exception) {
		if (isHabilitadoError()) {
			String message = EXCEPTION;
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			log.error(USER_DEFAULT.concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Error, utilizando el username del usuario para mostrar.
	 *
	 * @param username username del usuario
	 * @param exception excepcion
	 */
	public void error(String username, Exception exception) {
		if (isHabilitadoError()) {
			String message = mensajeTipoError(exception);
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			username = username == null ? "" : username;
			log.error(VERSION_PASE + username.concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Error, utilizando el username del usuario para mostrar.
	 *
	 * @param username username del usuario
	 * @param exception excepcion
	 */
	public void error(String username, NoClassDefFoundError exception) {
		if (isHabilitadoError()) {
			String message = EXCEPTION;
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			username = username == null ? "" : username;
			log.error(VERSION_PASE + username.concat(" - ").concat(message) , exception);
		}
	}
	
	
	
	/**
	 * Log de Level Error, utilizando el username del usuario para mostrar.
	 *
	 * @param username username del usuario
	 * @param mensaje mensaje de la excepcion
	 * @param exception excepcion
	 */
	public void error(String username, String mensaje, Exception exception) {
		if (isHabilitadoError()) {
			username = username == null ? "" : username;
			mensaje = mensaje == null ? "" : mensaje;
			String message = mensajeTipoError(exception).concat(" : ").concat(mensaje);
			log.error(VERSION_PASE + username.concat(" - ").concat(message), exception);
		}
	}
	
	/**
	 * Log de Level Error, utilizando el username del usuario para mostrar.
	 *
	 * @param username username del usuario
	 * @param mensaje mensaje de la excepcion
	 * @param exception excepcion
	 */
	public void error(String username, String mensaje, NoClassDefFoundError exception) {
		if (isHabilitadoError()) {
			username = username == null ? "" : username;
			mensaje = mensaje == null ? "" : mensaje;
			String message = EXCEPTION.concat(" : ").concat(mensaje);
			log.error(VERSION_PASE + username.concat(" - ").concat(message), exception);
		}
	}
	
	
	/**
	 * Log de Level Error, utilizando el username del usuario para mostrar.
	 *
	 * @param username username del usuario
	 * @param mensaje mensaje a imprimir
	 */
	public void error(String username, String mensaje) {
		username = username == null ? "" : username;
		mensaje = mensaje == null ? "" : mensaje;
		log.error(VERSION_PASE + username.concat(" - ").concat(mensaje));
	}
	
	
	
	/**
	 * Log de Level Error.
	 *
	 * @param mensaje mensaje a imprimir
	 */
	public void error(String mensaje) {
		mensaje = mensaje == null ? "" : mensaje;
		log.error(USER_DEFAULT.concat(" - ").concat(mensaje));
	}
	
	/**
	 * Log de Level Info.
	 *
	 * @param exception excepcion
	 */
	public void info(Exception exception) {
		if (isHabilitadoInfo()) {
			String message = mensajeTipoError(exception);
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			log.info(USER_DEFAULT.concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Info.
	 *
	 * @param exception excepcion
	 */
	public void info(NoClassDefFoundError exception) {
		if (isHabilitadoInfo()) {
			String message = EXCEPTION;
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			log.info(USER_DEFAULT.concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Info, utilizando el username del usuario para mostrar.
	 *
	 * @param username username del usuario
	 * @param exception excepcion
	 */
	public void info(String username, Exception exception) {
		if (isHabilitadoInfo()) {
			String message = mensajeTipoError(exception);
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			username = username == null ? "" : username;
			log.info(VERSION_PASE + username.concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Info, utilizando el username del usuario para mostrar.
	 *
	 * @param username username del usuario
	 * @param exception excepcion
	 */
	public void info(String username, NoClassDefFoundError exception) {
		if (isHabilitadoInfo()) {
			String message = EXCEPTION;
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			username = username == null ? "" : username;
			log.info(VERSION_PASE + username.concat(" - ").concat(message) , exception);
		}
	}
	
	
	/**
	 * Log de Level Info, utilizando el username del usuario para mostrar.
	 *
	 * @param username username del usuario
	 * @param mensaje mensaje de la excepcion
	 * @param exception excepcion
	 */
	public void info(String username, String mensaje, Exception exception) {
		if (isHabilitadoInfo()) {
			username = username == null ? "" : username;
			mensaje = mensaje == null ? "" : mensaje;
			String message = mensajeTipoError(exception).concat(" : ").concat(mensaje);
			log.info(VERSION_PASE + username.concat(" - ").concat(message), exception);
		}
	}
	
	/**
	 * Log de Level Info, utilizando el username del usuario para mostrar.
	 *
	 * @param username username del usuario
	 * @param mensaje mensaje de la excepcion
	 * @param exception excepcion
	 */
	public void info(String username, String mensaje, NoClassDefFoundError exception) {
		if (isHabilitadoInfo()) {
			username = username == null ? "" : username;
			mensaje = mensaje == null ? "" : mensaje;
			String message = EXCEPTION.concat(" : ").concat(mensaje);
			log.info(VERSION_PASE + username.concat(" - ").concat(message), exception);
		}
	}
	

	/**
	 * Log de Level Info, utilizando el username del usuario para mostrar.
	 *
	 * @param username username del usuario
	 * @param mensaje mensaje a imprimir
	 */
	public void info(String username, String mensaje) {
		if (isHabilitadoInfo()) {
			username = username == null ? "" : username;
			mensaje = mensaje == null ? "" : mensaje;
			log.info(VERSION_PASE + username.concat(" - ").concat(mensaje));
		}
	}
	
	
	
	/**
	 * Log de Level Info.
	 *
	 * @param mensaje mensaje a imprimir
	 */
	public void info(String mensaje) {
		if (isHabilitadoInfo()) {
			mensaje = mensaje == null ? "" : mensaje;
			log.info(USER_DEFAULT.concat(" - ").concat(mensaje));
		}
	}
	
	/**
	 * Log de Level Debug.
	 *
	 * @param mensaje the mensaje
	 */
	public void debug(String mensaje) {
		if (isHabilitadoDebug()) {
			log.debug(mensaje);
		}
	}
	
	/**
	 * Devuelve el tipo de excepcion.
	 *
	 * @param exception excepcion
	 * @return tipo de excepcion
	 */
	private String mensajeTipoError(Exception exception) {
		String mensajeTipoError = "";
		if (!mensajeTipoError.equalsIgnoreCase("")) {
			return mensajeTipoError;
		} else {
			if (!mensajeTipoError.equalsIgnoreCase("")) {
				return mensajeTipoError;
			} else {
				if (!mensajeTipoError.equalsIgnoreCase("")) {
					return mensajeTipoError;
				}
			}
		}
		return EXCEPTION;
	}
	
	
	
	/**
	 * Verifica si esta habilitado el nivel Info.
	 *
	 * @return true, if is habilitado Info
	 */
	public boolean isHabilitadoInfo() {
		return log.isEnabledFor(Level.INFO);
	}
	
	/**
	 * Verifica si esta habilitado el nivel Error.
	 *
	 * @return true, if is habilitado error
	 */
	public boolean isHabilitadoError() {
		return log.isEnabledFor(Level.ERROR);
	}
	
	/**
	 * Verifica si esta habilitado el nivel Warn.
	 *
	 * @return true, if is habilitado warn
	 */
	public boolean isHabilitadoWarn() {
		return log.isEnabledFor(Level.WARN);
	}
	
	/**
	 * Verifica si esta habilitado el nivel Debug.
	 *
	 * @return true, if is debug enabled
	 */
	public boolean isHabilitadoDebug() {
		return log.isDebugEnabled();
	}
	
	
}