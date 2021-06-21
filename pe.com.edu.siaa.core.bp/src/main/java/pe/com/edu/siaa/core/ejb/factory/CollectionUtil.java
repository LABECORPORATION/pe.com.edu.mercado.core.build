package pe.com.edu.siaa.core.ejb.factory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ComparatorChain;

import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class CollectionUtil.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * MAPFRE. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Lu Eno 25 01:25:41 COT 2016
 * @since SIAA-CORE 2.1
 */
public class CollectionUtil implements Serializable {
	

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

		/** La log. */
	private static Logger log = Logger.getLogger(CollectionUtil.class);
	

	/**
	 * Ordenador.
	 *
	 * @param descending el descending
	 * @param listaGeneral el lista general
	 * @param nombreColumna el nombre columna
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void ordenador(boolean descending, List listaGeneral, String nombreColumna) {
		try {
			BeanComparator actorComparator = null;
			if (descending) {
				actorComparator = new BeanComparator(nombreColumna, Collections.reverseOrder());
			} else {
				actorComparator = new BeanComparator(nombreColumna);
			}
			Collections.sort(listaGeneral,actorComparator);
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	public static void ordenadorGroup(boolean descending, List listaGeneral, String... nombreColumna) {
		try {
			List<BeanComparator> listaFiled = new ArrayList<BeanComparator>();
			ComparatorChain chain = null;
			if (descending) {
				for (String columna : nombreColumna) {
					listaFiled.add(new BeanComparator(columna,Collections.reverseOrder()));
				}
			} else {
				for (String columna : nombreColumna) {
					listaFiled.add(new BeanComparator(columna));
				}
			}
			chain = new ComparatorChain(listaFiled);
			Collections.sort(listaGeneral, chain);
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	public static boolean isEmpty(List<?> list) {
		boolean respuesta = false;
		if (list == null || list.size() == 0) {
			respuesta = true;
		}
		return respuesta;
	}
	
	/**
	 * Ordenador numeric.
	 *
	 * @param descending el descending
	 * @param listaGeneral el lista general
	 * @param nombreColumna el nombre columna
	 */
	public static void ordenadorNumeric(boolean descending, List<?> listaGeneral, String nombreColumna) {
		try {
			BeanComparator actorComparator = null;
			if (!descending) {
				actorComparator = new BeanComparator(nombreColumna, new Comparator() {	
					public int compare(Object o1, Object o2) {	
						if (StringUtils.isNotNullOrBlank(o1) 
								&& StringUtils.isNotNullOrBlank(o2)) {
							      BigDecimal valor1 = new BigDecimal(o1.toString());
							      BigDecimal valor2 = new BigDecimal(o2.toString());
							      return valor1.compareTo(valor2);						
					      }
						  return 0;
						}
					}
				);
			} else {
				actorComparator = new BeanComparator(nombreColumna, new Comparator() {	
					public int compare(Object o1, Object o2) {
						if (StringUtils.isNotNullOrBlank(o1) 
								&& StringUtils.isNotNullOrBlank(o2)) {
							  BigDecimal valor1 = new BigDecimal(o1.toString());
						      BigDecimal valor2 = new BigDecimal(o2.toString());
						      return valor2.compareTo(valor1);
						
					      }
						  return 0;
						}
					}
				);
			}
			Collections.sort(listaGeneral,actorComparator);
		} catch (Exception e) {
			log.error(e);
		}
	}
}