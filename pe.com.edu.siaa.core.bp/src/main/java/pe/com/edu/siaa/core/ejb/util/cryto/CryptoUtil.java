package pe.com.edu.siaa.core.ejb.util.cryto;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.codec.binary.Base64;

import pe.com.edu.siaa.core.ejb.util.log.Logger;

/**
 * La Class CryptoUtil.
 * <ul>
 * <li>Copyright 2017 ndavilal-
 * mapfre. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, 01/03/2016
 * @since PWR v1.0
 */
public class CryptoUtil {

	/** La Constante CLAVE_CIFRADO. */
	private static final String CLAVE_CIFRADO = "14704173533428417a466c6f772925";

	 /** La log. */
	private static Logger log = Logger.getLogger(CryptoUtil.class);
	
	/**
	 * Encriptar cadena.
	 *
	 * @param s el s
	 * @return the string
	 * @throws Exception the exception
	 */
	public static String encriptarCadena(String s) throws Exception {
		//if (!estaEncriptado(s)) {
		    SecretKeySpec clave = crearClaveEncriptacion();
		    /*
			Cipher cifrador = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			cifrador.init(Cipher.ENCRYPT_MODE, clave);
			Base64 base64 = new Base64();
			s = new String(base64.encode(cifrador.doFinal(s.getBytes())));
			return s;*/
		    
			byte[] input = s.getBytes("utf-8");
		    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, clave);
			
			byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
			int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
			ctLength += cipher.doFinal(cipherText, ctLength);
		    	
			return Base64.encodeBase64String(cipherText);
			
			
		//}
		//return s;
	}

	/**
	 * Desencriptar cadena.
	 *
	 * @param s el s
	 * @return the string
	 * @throws Exception the exception
	 */
	public static String desencriptarCadena(String s) throws Exception {
		if (s == null) {
			return null;
		}
		//if (estaEncriptado(s)) {
		    SecretKeySpec clave = crearClaveEncriptacion();
			/*Cipher cifrador = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			cifrador.init(Cipher.DECRYPT_MODE, clave);
			Base64 base64 = new Base64();
			s = new String(cifrador.doFinal(base64.decode(s.getBytes())));
			return s ;*/

			Cipher cifrador = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cifrador.init(Cipher.DECRYPT_MODE, clave);
			Base64 base64 = new Base64();
			return new String(cifrador.doFinal(base64.decode(s.getBytes())));
		//}
		//return s;
	}
	
	
	/**
	 * Esta encriptado.
	 *
	 * @param s the s
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public static boolean estaEncriptado(String s) throws Exception {
		boolean resultado = true;
		if (s == null) {
			return true;
		}
		try {
			SecretKeySpec clave = crearClaveEncriptacion();
			Cipher cifrador = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			cifrador.init(Cipher.DECRYPT_MODE, clave);
			Base64 base64 = new Base64();
			s = new String(cifrador.doFinal(base64.decode(s.getBytes())));
		} catch (Exception e) {
			resultado = false;
		}		
		
		return resultado;
	}

	/**
	 * Crear clave encriptacion.
	 *
	 * @return the secret key
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 */
	//SecretKey
	private static SecretKeySpec crearClaveEncriptacion() throws Exception {
		/*KeyGenerator keyGen = KeyGenerator.getInstance("DESede");
		SecureRandom sc = SecureRandom.getInstance("SHA1PRNG");
		sc.setSeed((new BigInteger(CLAVE_CIFRADO, 16)).toByteArray());
		keyGen.init(168, sc);
		return keyGen.generateKey();*/
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] thedigest = md.digest(CLAVE_CIFRADO.getBytes("UTF-8"));
		//SecretKeySpec skc = new SecretKeySpec(thedigest, "AES/ECB/PKCS5Padding");
		SecretKeySpec skc = new SecretKeySpec(thedigest, "AES");
		return skc;
	}

	/**
	 * Encriptar desencriptar.
	 *
	 * @param <T> el tipo generico
	 * @param dto el dto
	 * @param flaj el flaj
	 * @return the t
	 */
	public static <T> T encriptarDesencriptar(T dto, boolean flaj) {
		PropertyDescriptor[] propiedades = BeanUtilsBean.getInstance().getPropertyUtils().getPropertyDescriptors(dto.getClass());	
		for (PropertyDescriptor pd : propiedades) {
			try {
				// ENUMERADOS NO SE ENCRYPTAN
				if (pd.getPropertyType().isEnum()
						|| pd.getName().equalsIgnoreCase("Class")) {
					continue;
				}
				// SE OBTIENE EL CAMPO
				Field f = dto.getClass().getDeclaredField(pd.getName());
				// SE VALIDA QUE SEA UNA COLUMNA
				if (f != null && f.isAnnotationPresent(Column.class)) {
					// NO APLICA PARA ID O EMBEDDED
					if (f.isAnnotationPresent(Id.class)
							|| f.isAnnotationPresent(EmbeddedId.class)) {
						continue;
					}
					// CADENAS
					if (pd.getPropertyType().equals(String.class)) {
						String value = (String) pd.getReadMethod().invoke(dto);
						if (value != null && !value.trim().equals("")
								&& value.length() > 0) {
							value = (flaj) ? CryptoUtil.encriptarCadena(value)
									: CryptoUtil.desencriptarCadena(value);
							pd.getWriteMethod().invoke(dto, value);
						}
					} 
				} else if (f != null && !flaj) {
					if (pd.getPropertyType().equals(String.class)) {
						String value = (String) pd.getReadMethod().invoke(dto);
						if (value != null && !value.trim().equals("")
								&& value.length() > 0) {
							value = (flaj) ? CryptoUtil.encriptarCadena(value)
									: CryptoUtil.desencriptarCadena(value);
							pd.getWriteMethod().invoke(dto, value);
						}
					} 
				}
			} catch (Exception e) {
				//log.error(e.getMessage(), e);
				continue;
			}
		}
		return dto;
	}

	/**
	 * Comprueba si es numeric.
	 *
	 * @param pd el pd
	 * @return true, si es numeric
	 */
	private static boolean isNumeric(PropertyDescriptor pd) {
		Class<?> t = pd.getPropertyType();
		if (t.equals(Number.class) || t.equals(Integer.class)
				|| t.equals(int.class) || t.equals(Double.class)
				|| t.equals(double.class) || t.equals(BigInteger.class)
				|| t.equals(Long.class)) {
			return true;
		}

		return false;
	}

	/**
	 * Desencriptar lista.
	 *
	 * @param <T> el tipo generico
	 * @param lista el lista
	 * @return the collection
	 */
	public static <T> Collection<T> desencriptarLista(Collection<T> lista) {
		try {
			if (lista != null && !lista.isEmpty()) {
				for (T t : lista) {
					t = desencriptaObject(t);
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
		return lista;
	}

	/**
	 * Desencriptar lista.
	 *
	 * @param <T> el tipo generico
	 * @param lista el lista
	 * @return the list
	 */
	public static <T> List<T> desencriptarLista(List<T> lista) {
		try {
			if (lista != null && !lista.isEmpty()) {
				for (T t : lista) {
					t = desencriptaObject(t);
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
		return lista;
	}
	
	/**
	 * Encriptar lista objetos.
	 *
	 * @param <T> el tipo generico
	 * @param lista el lista
	 * @return the list
	 */
	public static <T> List<T> encriptarListaObjetos(List<T> lista) {
		try {
			if (lista != null && !lista.isEmpty()) {
				for (T t : lista) {
					t = encriptarDesencriptar(t, true);
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
		return lista;
	}
	
	/**
	 * Desencriptar lista objetos.
	 *
	 * @param <T> el tipo generico
	 * @param lista el lista
	 * @return the list
	 */
	public static <T> List<T> desencriptarListaObjetos(List<T> lista) {
		try {
			if (lista != null && !lista.isEmpty()) {
				for (T t : lista) {
					t = encriptarDesencriptar(t, false);
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
		return lista;
	}
	
	/**
	 * Desencriptar collecion.
	 *
	 * @param <T> el tipo generico
	 * @param lista el lista
	 * @return the list
	 */
	public static <T> List<T> desencriptarCollecion(Collection<T> lista) {
		try {
			if (lista != null && !lista.isEmpty()) {
				for (T t : lista) {
					t = desencriptaObject(t);
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
		return (List<T>) lista;
	}

	/**
	 * Desencripta object.
	 *
	 * @param <T> el tipo generico
	 * @param objeto el objeto
	 * @return the t
	 */
	public static <T> T desencriptaObject(T objeto) {
		try {
			if (objeto != null) {
				PropertyDescriptor propiedad = BeanUtilsBean.getInstance().getPropertyUtils().getPropertyDescriptor(objeto, "encriptado");
				boolean encriptado = (Boolean) propiedad.getReadMethod().invoke(objeto);
				if (encriptado) {
					objeto = encriptarDesencriptar(objeto, false);
					propiedad.getWriteMethod().invoke(objeto, false);
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
		return objeto;
	}
}
