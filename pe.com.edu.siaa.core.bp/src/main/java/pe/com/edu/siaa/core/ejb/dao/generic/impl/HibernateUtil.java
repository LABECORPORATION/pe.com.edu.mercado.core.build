package  pe.com.edu.siaa.core.ejb.dao.generic.impl;

import java.lang.reflect.Method;
import java.util.Iterator;

import org.apache.commons.collections.BeanMap;

/**
 * La Class HibernateUtil.
 * <ul>
 * <li>Copyright 2017 ndavilal-
 * mapfre. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, 01/03/2016
 * @since PWR v1.0
 */
public class HibernateUtil {
	
	/**M�todo para setear las collecciones a null por depedencia de hibernate
	 * Sets the lista null.
	 *
	 * @param object el object
	 * @return the object
	 * @throws Exception the exception
	 */
	public static Object setListaNull(Object object) throws Exception {
		BeanMap beanMap = new BeanMap(object);
		Iterator properties = beanMap.keyIterator();
		while (properties.hasNext()) {
			String property = (String) properties.next();
			Class typeField = beanMap.getType(property);
			if (Iterable.class.isAssignableFrom(typeField)) {
				Method getter = beanMap.getReadMethod(property);
				Object objField = getter.invoke(object);
				if (objField != null) {
					Object[] args = { null };
					Method setter = beanMap.getWriteMethod(property);
					setter.invoke(object, args);
				}
			}
		}
		return object;
	}
}
