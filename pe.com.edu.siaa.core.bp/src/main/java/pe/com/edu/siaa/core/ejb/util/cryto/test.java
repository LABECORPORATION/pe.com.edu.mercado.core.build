package pe.com.edu.siaa.core.ejb.util.cryto;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String mmm = "";
		try {
			mmm = EncriptarUtil.encriptar("admin");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("" + mmm);
	}

}
