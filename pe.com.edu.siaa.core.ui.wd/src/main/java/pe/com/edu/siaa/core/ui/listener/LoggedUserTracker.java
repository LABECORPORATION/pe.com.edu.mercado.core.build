package pe.com.edu.siaa.core.ui.listener;

import java.util.HashMap;
import java.util.Map;

/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class LoggedUserTracker.
 *
 * @author ndavilal
 * @version 1.0 , 08/04/2015
 * @since SIAA-CORE 2.1
 */
public class LoggedUserTracker {
	
	/** La loginned user ids. */
	private Map<Long, String> loginnedUserIds = new HashMap<Long, String>();
	
	/**
	 * Add user id and session id to store after each successfull authentication. Last authentication will be current
	 * @param id - user id to add
	 * @param sessionId - sessionId
	 */
	public void addUserId(Long id, String sessionId) {
		loginnedUserIds.put(id, sessionId);
	}
	
	/**
	 * Remove user id from store after each logout.
	 *
	 * @param id - user id to remove
	 */
	public void removeUserId(Long id) {
		loginnedUserIds.remove(id);
	}
	
	/**
	 * Checks if in the store contained user with specified user id and session id.
	 *
	 * @param id - user id to check
	 * @param sessionId - session id to check
	 * @return true if such user contained in the store, that indicating that current user is actual.
	 */
	public boolean containsUser(Long id, String sessionId) {
		String sessId = loginnedUserIds.get(id);
		return sessId != null && sessId.equals(sessionId);
	}
	
	/**
	 * Checks if in the store contained user with specified user id.
	 *
	 * @param id - user id to check
	 * @return true if user with specified id contained in the store
	 */
	public boolean containsUserId(Long id) {
		return loginnedUserIds.containsKey(id);
	}
}
