package pe.com.edu.siaa.core.ejb.socketio.server;

import java.util.ArrayList;
import java.util.List;
 
import pe.com.builderp.core.facturacion.model.dto.venta.VentaDTO;
import pe.com.edu.siaa.core.model.vo.SunatDatosVO;

public class ChatObject {

    private String userName;
    
    private String message;
    
    private String contadorPedi;

    private String contadorPediMoso ="0";
    
    private int diaSemana;

    private String idUsuario;
    
    private String listadoCaja;
    
    private String correlativoB;
    
    private boolean esServicioSunat;
    
    private List<SunatDatosVO> listadoSunatDatos = new ArrayList<SunatDatosVO>();
     
    
    private List<VentaDTO> listadoCajaDTO = new ArrayList<VentaDTO>();
    
    private boolean esMesaListado;
    
    private boolean esCajaListado;
    
    private String listadoContadorEdit;
    
    public ChatObject() {
    }

    public ChatObject(String userName, String message,int diaSemana) {
        super();
        this.userName = userName;
        this.message = message;
        this.diaSemana=diaSemana;
    }
    
    
 
    
	 

	public String getCorrelativoB() {
		return correlativoB;
	}

	public void setCorrelativoB(String correlativoB) {
		this.correlativoB = correlativoB;
	}

	public String getListadoContadorEdit() {
		return listadoContadorEdit;
	}

	public void setListadoContadorEdit(String listadoContadorEdit) {
		this.listadoContadorEdit = listadoContadorEdit;
	}

	public List<VentaDTO> getListadoCajaDTO() {
		return listadoCajaDTO;
	}

	public void setListadoCajaDTO(List<VentaDTO> listadoCajaDTO) {
		this.listadoCajaDTO = listadoCajaDTO;
	}

	public boolean isEsCajaListado() {
		return esCajaListado;
	}

	public void setEsCajaListado(boolean esCajaListado) {
		this.esCajaListado = esCajaListado;
	}

	public boolean isEsMesaListado() {
		return esMesaListado;
	}

	public void setEsMesaListado(boolean esMesaListado) {
		this.esMesaListado = esMesaListado;
	}
 
	public boolean isEsServicioSunat() {
		return esServicioSunat;
	}

	public void setEsServicioSunat(boolean esServicioSunat) {
		this.esServicioSunat = esServicioSunat;
	}

	public List<SunatDatosVO> getListadoSunatDatos() {
		return listadoSunatDatos;
	}

	public void setListadoSunatDatos(List<SunatDatosVO> listadoSunatDatos) {
		this.listadoSunatDatos = listadoSunatDatos;
	}
 
	
 

	public String getListadoCaja() {
		return listadoCaja;
	}

	public void setListadoCaja(String listadoCaja) {
		this.listadoCaja = listadoCaja;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getContadorPedi() {
		return contadorPedi;
	}

	public void setContadorPedi(String contadorPedi) {
		this.contadorPedi = contadorPedi;
	}

	public String getContadorPediMoso() {
		return contadorPediMoso;
	}

	public void setContadorPediMoso(String contadorPediMoso) {
		this.contadorPediMoso = contadorPediMoso;
	}

	public int getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(int diaSemana) {
		this.diaSemana = diaSemana;
	}

	public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
