package pe.com.edu.siaa.core.model.vo;

import java.io.Serializable;

import pe.com.edu.siaa.core.model.dto.BasePaginator;

public class EmailVO extends BasePaginator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sms;
	private String asunto;
	private String para;
	private String de;
	private String email;
	
	private String fileName;

	
	
	public EmailVO() {
		super();
	}



	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public String getSms() {
		return sms;
	}



	public void setSms(String sms) {
		this.sms = sms;
	}



	public String getAsunto() {
		return asunto;
	}



	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}



	public String getPara() {
		return para;
	}



	public void setPara(String para) {
		this.para = para;
	}



	public String getDe() {
		return de;
	}



	public void setDe(String de) {
		this.de = de;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}


 
	

}
