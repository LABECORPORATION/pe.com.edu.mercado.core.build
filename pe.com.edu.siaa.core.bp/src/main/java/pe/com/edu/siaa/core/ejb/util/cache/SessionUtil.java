package pe.com.edu.siaa.core.ejb.util.cache;

import java.util.HashMap;
import java.util.Map;

import pe.com.edu.siaa.core.ejb.service.lucene.impl.BigMemoryManager;
import pe.com.edu.siaa.core.model.vo.FileVO;

/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class SessionUtil.
 *
 * @author ndavilal
 * @version 1.0 , 08/04/2015
 * @since SIAA-CORE 2.1
 */
public class SessionUtil {
	 
	/** La Session cache utl. */
	private static SessionUtil sessionUtil = null;
	
	/** El flag cargo listado. */
	private boolean flagCargoListado = false;
	
	private Map<String,Object> parametroCacheMap = new HashMap<String, Object>();
	/**
	 * Instanciar.
	 *
	 * @return the configurador cache utl
	 */
	public static SessionUtil getInstance() {
		if (sessionUtil == null) {
			createInstance();
		} else if (!sessionUtil.isFlagCargoListado()) {
			//sessionUtil.sincronizarData();
		}
		return sessionUtil;
	}
	 /**
     * Creates the instance.
     */
    private static synchronized void createInstance() {
    	if (sessionUtil == null) {
    		sessionUtil = new SessionUtil();
    		//sessionUtil.sincronizarData();
		}
    }
    
 	/**
 	 * Obtiene session.
 	 *
 	 * @return session
 	 */
 	public static Map<String,Object> getSession() {
		 //return (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
 		return SessionUtil.getInstance().getParametroCacheMap();
     }

	/**
	 * Pasar parametro.
	 *
	 * @param resultado el resultado
	 * @param objeto el objeto
	 */
	public static void pasarParametro(String resultado, FileVO objeto) {
		//getSession().setAttribute(resultado, objeto);
		getSession().put(resultado, objeto);
		
	}
	public static void pasarParametro(String resultado, BigMemoryManager<String, byte[]> objeto) {
		//getSession().setAttribute(resultado, objeto);
		getSession().put(resultado, objeto);
		
	}
	
	/**
	 * Remover parametro.
	 *
	 * @param resultado el resultado
	 */
	public static void removerParametro(String resultado) {
		//getSession().removeAttribute(resultado);
		getSession().remove(resultado);
	}
	
	/**
	 * Comprueba si es flag cargo listado.
	 *
	 * @return true, si es flag cargo listado
	 */
	public boolean isFlagCargoListado() {
		return flagCargoListado;
	}

	/**
	 * Establece el flag cargo listado.
	 *
	 * @param flagCargoListado el new flag cargo listado
	 */
	public void setFlagCargoListado(boolean flagCargoListado) {
		this.flagCargoListado = flagCargoListado;
	}
	public Map<String, Object> getParametroCacheMap() {
		return parametroCacheMap;
	}
	
	
}
