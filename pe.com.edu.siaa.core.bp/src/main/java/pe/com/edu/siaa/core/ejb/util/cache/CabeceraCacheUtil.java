package pe.com.edu.siaa.core.ejb.util.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.com.edu.siaa.core.ejb.util.log.Logger;

public class CabeceraCacheUtil {

	private static Logger log = Logger.getLogger(CabeceraCacheUtil.class);

	private static CabeceraCacheUtil cabeceraCacheUtil = null;

	private static Map<String, List<String>> parametrosHeaderMap = new HashMap<String, List<String>>();

	private boolean flagCargoListado = false;

	public CabeceraCacheUtil() {
	}

	public static CabeceraCacheUtil getInstance() {
		if (cabeceraCacheUtil == null) {
			createInstance();
		} else if (!cabeceraCacheUtil.isFlagCargoListado()) {
			cabeceraCacheUtil.sincronizarData();
		}
		return cabeceraCacheUtil;
	}

	private static synchronized void createInstance() {
		if (cabeceraCacheUtil == null) {
			cabeceraCacheUtil = new CabeceraCacheUtil();
			cabeceraCacheUtil.sincronizarData();
		}
	}

	public String sincronizarData() {
		return sincronizarAtributos();
	}

	private <T> String sincronizarAtributos() {
		try {
			flagCargoListado = true;
		} catch (Exception e) {
			log.error(e);
			flagCargoListado = false;
			return e.toString();
		}
		return null;
	}
	
	public static synchronized List<String> get(String key) {
		if (parametrosHeaderMap.containsKey(key)) {
			return parametrosHeaderMap.get(key);
		} else {
			return new ArrayList<String>();
		}
	}
	
	public static synchronized void put(String key, List<String> listCabera) {
		List<String> listCaberaFiltrada = new ArrayList<String>(listCabera);
		for (String cabecera : listCaberaFiltrada) {
			if ("ROWNUM".equals(cabecera)) {
				listCabera.remove("ROWNUM");
			}
		}
		parametrosHeaderMap.put(key, listCabera);
	}
	
	public boolean isFlagCargoListado() {
		return flagCargoListado;
	}
}
