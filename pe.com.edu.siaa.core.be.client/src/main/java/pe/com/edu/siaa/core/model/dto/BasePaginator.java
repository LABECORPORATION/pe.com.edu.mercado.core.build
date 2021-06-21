package pe.com.edu.siaa.core.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * La Class BasePaginator.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * Mapfre. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Wed Sep 30 11:24:54 COT 2017
 * @since SIAA-CORE 2.1
 */
public class BasePaginator implements Serializable {
	
	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** La offset. */
	private int offset;
	
	/** La start row. */
	private int startRow;
	
	private String search;
	
	private boolean checked;
	
	private Integer varCantidad = 0;
	
	private String idUsuarioLogin = "";
	
	private Long idEmpresaSelect = 0L;
	
	private boolean esEliminado = false;
	
	private Object id;
	
	private Object idFiltro1;
	
	private Object idFiltro2;
	
	private Object idFiltro3;
	
	private Object idFiltro4;
	
	private String descripcionView;
	
	private String serviceKey = "";
	
	private String authToken;
	
	private String codigoGeneradoReporte = "";
	
	private String ip = "";
	
	private String tipo = "";
	
	private Object idPadreView;
	
	private String idEntidadSelect = "";
	
	private Date fechaUltimoAcceso;
	
	private Integer position = 0;
	
	private String select ="";
	
	private String update ="";
	
	private String insert ="";
	
    private String sistem ="";
	
	private String admin ="";
	
	private String conta ="";
	
	private String derech ="";
	
	private String todos ="";
	
	private Long idItemComodin ;
	
	private String idanhosemestreEgreso ="";
	
	private String idEscuelaEgresado ="";
	
	private String tieneBachiller ="";
	
	private String tieneTitulo ="";
	
	private List<String> ListidEgresados = null;
	
	private List<String> ListidAlumno = null;
	
	private String name ="";
	
	private String nameImpresoraCaja ="";
	
	private String tri ="";
	
	private String det ="";
	
	private String ley ="";
	
	private BigDecimal montoTotalItem;
	
	private String montoletra ="";
	
	private boolean disbled;
	
	private BigDecimal precioTotal;
	
	private BigDecimal descuentoMonto;
	
	private String envioSunatValor;
	/**
	 * Establece el start row.
	 *
	 * @param startRow el new start row
	 */
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	/**
	 * Obtiene start row.
	 *
	 * @return start row
	 */
	public int getStartRow() {
		return startRow;
	}

	/**
	 * Establece el offset.
	 *
	 * @param offset el new offset
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * Obtiene offset.
	 *
	 * @return offset
	 */
	public int getOffset() {
		return offset;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	

	public List<String> getListidAlumno() {
		return ListidAlumno;
	}

	public void setListidAlumno(List<String> listidAlumno) {
		ListidAlumno = listidAlumno;
	}

	public Object getIdFiltro3() {
		return idFiltro3;
	}

	public void setIdFiltro3(Object idFiltro3) {
		this.idFiltro3 = idFiltro3;
	}

	public Object getIdFiltro4() {
		return idFiltro4;
	}

	public void setIdFiltro4(Object idFiltro4) {
		this.idFiltro4 = idFiltro4;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}
	 

	public BigDecimal getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(BigDecimal precioTotal) {
		this.precioTotal = precioTotal;
	}

	public BigDecimal getDescuentoMonto() {
		return descuentoMonto;
	}

	public void setDescuentoMonto(BigDecimal descuentoMonto) {
		this.descuentoMonto = descuentoMonto;
	}

	public String getEnvioSunatValor() {
		return envioSunatValor;
	}

	public void setEnvioSunatValor(String envioSunatValor) {
		this.envioSunatValor = envioSunatValor;
	}

	public BigDecimal getMontoTotalItem() {
		return montoTotalItem;
	}

	public void setMontoTotalItem(BigDecimal montoTotalItem) {
		this.montoTotalItem = montoTotalItem;
	}

	
	
	public boolean isDisbled() {
		return disbled;
	}

	public void setDisbled(boolean disbled) {
		this.disbled = disbled;
	}

	public String getMontoletra() {
		return montoletra;
	}

	public void setMontoletra(String montoletra) {
		this.montoletra = montoletra;
	}

	public String getTri() {
		return tri;
	}

	public void setTri(String tri) {
		this.tri = tri;
	}

	public String getDet() {
		return det;
	}

	public void setDet(String det) {
		this.det = det;
	}

	public String getLey() {
		return ley;
	}

	public void setLey(String ley) {
		this.ley = ley;
	}

	public String getNameImpresoraCaja() {
		return nameImpresoraCaja;
	}

	public void setNameImpresoraCaja(String nameImpresoraCaja) {
		this.nameImpresoraCaja = nameImpresoraCaja;
	}

	public String getTodos() {
		return todos;
	}

	public void setTodos(String todos) {
		this.todos = todos;
	}

	public String getInsert() {
		return insert;
	}

	public void setInsert(String insert) {
		this.insert = insert;
	}

	public String getSistem() {
		return sistem;
	}

	public void setSistem(String sistem) {
		this.sistem = sistem;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getDerech() {
		return derech;
	}

	public void setDerech(String derech) {
		this.derech = derech;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Integer getVarCantidad() {
		return varCantidad;
	}

	public void setVarCantidad(Integer cantidad) {
		this.varCantidad = cantidad;
	}

	public String getIdUsuarioLogin() {
		return idUsuarioLogin;
	}

	public void setIdUsuarioLogin(String idUsuarioLogin) {
		this.idUsuarioLogin = idUsuarioLogin;
	}

	public boolean isEsEliminado() {
		return esEliminado;
	}

	public void setEsEliminado(boolean esEliminado) {
		this.esEliminado = esEliminado;
	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}
	
	

	public Object getIdFiltro1() {
		return idFiltro1;
	}

	public void setIdFiltro1(Object idFiltro1) {
		this.idFiltro1 = idFiltro1;
	}

	public Object getIdFiltro2() {
		return idFiltro2;
	}

	public void setIdFiltro2(Object idFiltro2) {
		this.idFiltro2 = idFiltro2;
	}

	public Long getIdEmpresaSelect() {
		return idEmpresaSelect;
	}

	public void setIdEmpresaSelect(Long idEmpresaSelect) {
		this.idEmpresaSelect = idEmpresaSelect;
	}

	public String getDescripcionView() {
		return descripcionView;
	}

	public void setDescripcionView(String descripcionView) {
		this.descripcionView = descripcionView;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getCodigoGeneradoReporte() {
		return codigoGeneradoReporte;
	}

	public void setCodigoGeneradoReporte(String codigoGeneradoReporte) {
		this.codigoGeneradoReporte = codigoGeneradoReporte;
	}

	public String getServiceKey() {
		return serviceKey;
	}

	public void setServiceKey(String serviceKey) {
		this.serviceKey = serviceKey;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Object getIdPadreView() {
		return idPadreView;
	}

	public void setIdPadreView(Object idPadreView) {
		this.idPadreView = idPadreView;
	}

	public String getIdEntidadSelect() {
		return idEntidadSelect;
	}

	public void setIdEntidadSelect(String idEntidadSelect) {
		this.idEntidadSelect = idEntidadSelect;
	}

	public Date getFechaUltimoAcceso() {
		return fechaUltimoAcceso;
	}

	public void setFechaUltimoAcceso(Date fechaUltimoAcceso) {
		this.fechaUltimoAcceso = fechaUltimoAcceso;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Long getIdItemComodin() {
		return idItemComodin;
	}

	public void setIdItemComodin(Long idItemComodin) {
		this.idItemComodin = idItemComodin;
	}

	public String getIdanhosemestreEgreso() {
		return idanhosemestreEgreso;
	}

	public void setIdanhosemestreEgreso(String idanhosemestreEgreso) {
		this.idanhosemestreEgreso = idanhosemestreEgreso;
	}

	public String getIdEscuelaEgresado() {
		return idEscuelaEgresado;
	}

	public void setIdEscuelaEgresado(String idEscuelaEgresado) {
		this.idEscuelaEgresado = idEscuelaEgresado;
	}

	public String getTieneBachiller() {
		return tieneBachiller;
	}

	public void setTieneBachiller(String tieneBachiller) {
		this.tieneBachiller = tieneBachiller;
	}

	public String getTieneTitulo() {
		return tieneTitulo;
	}

	public void setTieneTitulo(String tieneTitulo) {
		this.tieneTitulo = tieneTitulo;
	}

	public List<String> getListidEgresados() {
		return ListidEgresados;
	}

	public void setListidEgresados(List<String> listidEgresados) {
		ListidEgresados = listidEgresados;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
