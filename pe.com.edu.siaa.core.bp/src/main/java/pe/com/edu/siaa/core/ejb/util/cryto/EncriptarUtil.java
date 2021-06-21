/**
 * License Agreement.
 *
 * Rich Faces - Natural Ajax for Java Server Faces (JSF)
 *
 * Copyright (C) 2007 Exadel, Inc.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */
package pe.com.edu.siaa.core.ejb.util.cryto;
/**
 * Convenience class to hash user passwords.
 *
 * @author Andrey Markhel
 */
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 * <ul>
 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Class EncriptarUtil.
 *
 * @author ndavilal.
 * @version 1.0 , 21/08/2012
 * @since SIAA 2.0
 */
public class EncriptarUtil {
	
	/** La digest algorithm. */
	private static String digestAlgorithm = "SHA-1";
	
	/** La charset. */
	private static String charset = "UTF-8";
	
	/**
	 * Convenience method to hash user passwords.
	 *
	 * @param plainTextPassword - password to hash
	 * @return the string
	 */
	public static String hash(String plainTextPassword) {
		try {
			MessageDigest digest = MessageDigest.getInstance(digestAlgorithm);
			digest.update(plainTextPassword.getBytes(charset));
			byte[] rawHash = digest.digest();
			return new String(Hex.encodeHex(rawHash));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	 /**
 	 * Encriptar.
 	 *
 	 * @param textoplano el textoplano
 	 * @return the string
 	 * @throws IllegalStateException the illegal state exception
 	 */
 	public static String encriptar(String textoplano) throws IllegalStateException {
	        MessageDigest md = null;

	        try {
	            md = MessageDigest.getInstance("SHA"); // Instancia de generador SHA-1
	        } catch (NoSuchAlgorithmException e) {
	            throw new IllegalStateException(e.getMessage());
	        }

	        try {
	            md.update(textoplano.getBytes(charset)); // Generaci�n de resumen de mensaje
	        } catch (UnsupportedEncodingException e) {
	            throw new IllegalStateException(e.getMessage());
	        }
	        Base64 base64 = new Base64();
	        byte raw[] = md.digest(); // Obtenci�n del resumen de mensaje
	        String hash = (base64).encodeToString(raw); // Traducci�n a BASE64
	        return hash;
	    }
}
