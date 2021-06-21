package pe.com.edu.siaa.core.ui.listener;

import javax.servlet.http.HttpSession;

import pe.com.edu.siaa.core.ejb.dao.generic.impl.SwapVirtualizadorCacheUtil;

/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class SessionExpirationChecker.
 *
 * @author ndavilal
 * @version 1.0 , 08/04/2015
 * @since SIAA-CORE 2.1
 */
public class SessionExpirationChecker {

	
	/**
	 * Utility method for check is the user session was expired or user were login in another browser.
	 * Observes <code>Constants.CHECK_USER_EXPIRED_EVENT</code> event.
	 * Redirects to error page if user were login in another browser.
	 * @param session - user's session
	 */
	public static void checkUserExpiration(HttpSession session) {
		if (isShouldExpireUser(session)) {
	    	 try {
	    		 //UsuarioDTO user = (UsuarioDTO)session.getAttribute(ConstantesParameterUtil.ATRIBUTE_SESSION_USUARIO);
	    		 String user = "";
	    		 if (user != null) {
	    			 SwapVirtualizadorCacheUtil.getInstance().cleanup(user);
	    		 }
	    		session.invalidate();
	    		/*FacesContext facesContext = FacesContext.getCurrentInstance();
	    		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
				facesContext.getExternalContext().redirect(request.getContextPath() + "/error.xhtml");*/
			} catch (Exception e1) {
				//FacesContext.getCurrentInstance().responseComplete();
			}
	     }
	}
	
	/**
	 * Comprueba si es should expire user.
	 *
	 * @param session el session
	 * @return true, si es should expire user
	 */
	//TODO:NO_PASAR A PRODUCCION
	private static boolean isShouldExpireUser(HttpSession session) {
		//LoggedUserTracker userTracker = (LoggedUserTracker)FacesUtil.getBean("loggedUserTracker");
		//UsuarioDTO user = (UsuarioDTO)session.getAttribute(ConstantesParameterUtil.ATRIBUTE_SESSION_USUARIO);
		return false;// user != null && !"admin".toUpperCase().equals(user.getUserName().toUpperCase()) && userTracker.containsUserId(user.getIdUsuario()) && !userTracker.containsUser(user.getIdUsuario(), session.getId());
	}
}