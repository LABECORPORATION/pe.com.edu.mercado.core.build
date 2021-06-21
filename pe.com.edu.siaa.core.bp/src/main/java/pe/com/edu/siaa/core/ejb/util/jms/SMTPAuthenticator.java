package pe.com.edu.siaa.core.ejb.util.jms;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * La Class SMTPAuthenticator.
 * <ul>
 * <li>Copyright 2017 ndavilal-
 * mapfre. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Fri Apr 25 18:56:23 COT 2017
 * @since SIAA-CORE 2.1
 */
public class SMTPAuthenticator extends Authenticator {
	
	/** La usuario. */
	private String usuario;
	
	/** La password. */
	private String password;
	
	/**
	 * Instancia un nuevo sMTP authenticator.
	 *
	 * @param usuario el usuario
	 * @param password el password
	 */
	public SMTPAuthenticator(String usuario, String password) {
		this.usuario = usuario;
		this.password = password;
	}
	
	/* (non-Javadoc)
	 * @see javax.mail.Authenticator#getPasswordAuthentication()
	 */
	@Override
	public PasswordAuthentication getPasswordAuthentication() {
	    return new PasswordAuthentication(usuario, password);

	}
}
