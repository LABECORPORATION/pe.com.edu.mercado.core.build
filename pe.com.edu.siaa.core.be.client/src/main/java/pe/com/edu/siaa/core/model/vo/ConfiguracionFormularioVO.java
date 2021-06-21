/*
 * 
 */
package pe.com.edu.siaa.core.model.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <ul>
 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Class MsgBean.
 *
 * @author ndavilal.
 * @version 1.0 , 22/03/2014
 * @since SIAA 2.0
 */
public class ConfiguracionFormularioVO implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	

	/** El required map. */
	private Map<String,Boolean> requiredInputMap = new HashMap<String, Boolean>();
	
	/** El required map. */
	private Map<String,Boolean> requiredGrillaMap = new HashMap<String, Boolean>();
	
	/** El readonly map. */
	private Map<String,Boolean> readonlyInputMap = new HashMap<String, Boolean>();
	
	/** El readonly map. */
	private Map<String,Boolean> readonlyGrillaMap = new HashMap<String, Boolean>();

	/** El rendered map. */
	private Map<String,Boolean> renderedLabelMap = new HashMap<String, Boolean>();
	
	/** El rendered map. */
	private Map<String,Boolean> renderedInputMap = new HashMap<String, Boolean>();
	
	/** El rendered map. */
	private Map<String,Boolean> renderedButtonMap = new HashMap<String, Boolean>();
	
	/** El rendered map. */
	private Map<String,Boolean> renderedGrillaMap = new HashMap<String, Boolean>();
	
	/** El rendered map. */
	private Map<String,Boolean> renderedButtonGrillaMap = new HashMap<String, Boolean>();
	
	/** El disabled map. */
	private Map<String,Boolean> disabledInputMap = new HashMap<String, Boolean>();
	
	/** El disabled map. */
	private Map<String,Boolean> disabledInputGrillaMap = new HashMap<String, Boolean>();
	
	/** El disabled map. */
	private Map<String,Boolean> disabledButtonMap = new HashMap<String, Boolean>();
	
	/** El disabled map. */
	private Map<String,Boolean> disabledButtonGrillaMap = new HashMap<String, Boolean>();


	
	/**
	 * Instancia un nuevo faces home.
	 */
	public ConfiguracionFormularioVO() {
		
	}
	
	/**
	 * Obtiene required input map.
	 *
	 * @return required input map
	 */
	public Map<String, Boolean> getRequiredInputMap() {
		return requiredInputMap;
	}

	/**
	 * Obtiene required grilla map.
	 *
	 * @return required grilla map
	 */
	public Map<String, Boolean> getRequiredGrillaMap() {
		return requiredGrillaMap;
	}

	/**
	 * Obtiene readonly input map.
	 *
	 * @return readonly input map
	 */
	public Map<String, Boolean> getReadonlyInputMap() {
		return readonlyInputMap;
	}

	/**
	 * Obtiene readonly grilla map.
	 *
	 * @return readonly grilla map
	 */
	public Map<String, Boolean> getReadonlyGrillaMap() {
		return readonlyGrillaMap;
	}

	/**
	 * Obtiene rendered label map.
	 *
	 * @return rendered label map
	 */
	public Map<String, Boolean> getRenderedLabelMap() {
		return renderedLabelMap;
	}

	/**
	 * Obtiene rendered input map.
	 *
	 * @return rendered input map
	 */
	public Map<String, Boolean> getRenderedInputMap() {
		return renderedInputMap;
	}

	/**
	 * Obtiene rendered button map.
	 *
	 * @return rendered button map
	 */
	public Map<String, Boolean> getRenderedButtonMap() {
		return renderedButtonMap;
	}

	/**
	 * Obtiene rendered grilla map.
	 *
	 * @return rendered grilla map
	 */
	public Map<String, Boolean> getRenderedGrillaMap() {
		return renderedGrillaMap;
	}


	/**
	 * Obtiene rendered button grilla map.
	 *
	 * @return rendered button grilla map
	 */
	public Map<String, Boolean> getRenderedButtonGrillaMap() {
		return renderedButtonGrillaMap;
	}

	/**
	 * Obtiene disabled input map.
	 *
	 * @return disabled input map
	 */
	public Map<String, Boolean> getDisabledInputMap() {
		return disabledInputMap;
	}

	/**
	 * Obtiene disabled input grilla map.
	 *
	 * @return disabled input grilla map
	 */
	public Map<String, Boolean> getDisabledInputGrillaMap() {
		return disabledInputGrillaMap;
	}

	/**
	 * Obtiene disabled button map.
	 *
	 * @return disabled button map
	 */
	public Map<String, Boolean> getDisabledButtonMap() {
		return disabledButtonMap;
	}

	/**
	 * Obtiene disabled button grilla map.
	 *
	 * @return disabled button grilla map
	 */
	public Map<String, Boolean> getDisabledButtonGrillaMap() {
		return disabledButtonGrillaMap;
	}

}
