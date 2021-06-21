package pe.com.edu.siaa.core.ejb.util.img;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import pe.com.edu.siaa.core.ejb.util.log.Logger;

//http://javasampleapproach.com/java/java-advanced/java-8-encode-decode-an-image-base64
public class Base64ImageUtils {
	
	private static Logger log = Logger.getLogger(Base64ImageUtils.class);
	
	public static void main(String[] args) {
		String imagePath = "C:\\base64\\image.jpg";
		System.out.println("=================Encoder Image to Base 64!=================");
		String base64ImageString = encoder(imagePath);
		System.out.println("Base64ImageString = " + base64ImageString);
 
		System.out.println("=================Decoder Base64ImageString to Image!=================");
		try {
			decoder(base64ImageString, "C:\\base64\\decoderimage.jpg");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		System.out.println("DONE!");
 
	}	
	public static String encoder(String imagePath) {
		String base64Image = "";
		File file = new File(imagePath);
		try (FileInputStream imageInFile = new FileInputStream(file)) {
			// Reading a Image file from file system
			byte imageData[] = new byte[(int) file.length()];
			imageInFile.read(imageData);
			base64Image = Base64.getEncoder().encodeToString(imageData);
		} catch (FileNotFoundException e) {
			log.error("Image not found" , e);
		} catch (IOException ioe) {
			log.error("Exception while reading the Image " , ioe);
		}
		return base64Image;
	}
 
	public static void decoder(String base64Image, String pathFile) throws Exception {
		try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
			// Converting a Base64 String into Image byte array
			byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
			imageOutFile.write(imageByteArray);
		} catch (FileNotFoundException e) {
			log.error("Image not found" , e);
			throw e;
		} catch (IOException ioe) {
			log.error("Exception while reading the Image " , ioe);
			throw ioe;
		}
	}

}
