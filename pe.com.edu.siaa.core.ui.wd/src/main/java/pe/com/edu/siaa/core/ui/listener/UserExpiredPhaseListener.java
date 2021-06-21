package pe.com.edu.siaa.core.ui.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;


/**
 * The listener interface for receiving userExpiredPhase events.
 * The class that is interested in processing a userExpiredPhase
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addUserExpiredPhaseListener</code> method. When
 * the userExpiredPhase event occurs, that object's appropriate
 * method is invoked.
 *
 * @see UserExpiredPhaseEvent
 */
public class UserExpiredPhaseListener implements PhaseListener {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** La phase. */
	private PhaseId phase = PhaseId.RESTORE_VIEW; 

	  /* (non-Javadoc)
  	 * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
  	 */
	@Override
  	public void beforePhase(PhaseEvent e) {
  		//SessionExpirationChecker.checkUserExpiration(SessionUtil.getSession());
	  }         

	  /* (non-Javadoc)
  	 * @see javax.faces.event.PhaseListener#afterPhase(javax.faces.event.PhaseEvent)
  	 */
	@Override
  	public void afterPhase(PhaseEvent e) {
	  }         

	  /**
  	 * Establece el phase.
  	 *
  	 * @param phase el new phase
  	 */
  	public void setPhase(PhaseId phase) {
	    this.phase = phase;
	  }         

	  /* (non-Javadoc)
  	 * @see javax.faces.event.PhaseListener#getPhaseId()
  	 */
  	@Override
  	public PhaseId getPhaseId() {
	    return phase;
	  }
}
