package pe.com.edu.siaa.core.ui.util;

import java.io.Serializable;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;




/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class FacesUtil.
 *
 * @author ndavilal
 * @version 1.0 , 08/04/2015
 * @since SIAA-CORE 2.1
 */
public class FacesUtil implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	 /**
 	 * Obtiene bean.
 	 *
 	 * @param name el name
 	 * @param classs el classs
 	 * @return bean
 	 */
 	public static Object getBean(String name, Class<?> classs) {
    	FacesContext faces = FacesContext.getCurrentInstance();
		ELContext contextoEL = faces.getELContext( );
		Application apli = faces.getApplication( );
		ExpressionFactory ef = apli.getExpressionFactory( );
		ValueExpression ve = ef.createValueExpression(contextoEL, "#{" + name + "}",classs);
		return ve.getValue(contextoEL);
		
        
    }
	 
 	/**
 	 * Obtiene bean.
 	 *
 	 * @param name el name
 	 * @return bean
 	 */
 	public static Object getBean(String name) {
		 ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		 Object lBean = (Object) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, name);
		 return lBean;
	 }
 
	 /**
 	 * Find bean.
 	 *
 	 * @param <T> el tipo generico
 	 * @param beanName el bean name
 	 * @return the t
 	 */
 	public static <T> T findBean(String beanName) {
		    FacesContext context = FacesContext.getCurrentInstance();
		    return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
		}
}
