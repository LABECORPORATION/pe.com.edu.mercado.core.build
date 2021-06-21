package pe.com.builderp.core.facturacion.ejb.service.empresa.impl;
 
import pe.com.edu.siaa.core.ejb.util.log.Logger;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import pe.com.edu.siaa.core.model.util.StringUtils;
 
import pe.com.builderp.core.facturacion.ejb.dao.compra.local.ProveedorDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.ActividadDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.AnhioDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.AsistenciaDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.AsociadoDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.AsociadoFamiliaDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.CategoriaByEmpresaDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.ControlPagoDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.CuotaConceptoDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.DetControlPagoDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.DetPlanPagosDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.EgresoDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.IngresoDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.PersonalDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.PlanPagosDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.PuestoDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.RecordatorioDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.RubroDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.SectorDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.TipoDocSunatEntidadDaoLocal; 
import pe.com.builderp.core.facturacion.ejb.service.empresa.local.EmpresaServiceLocal;
import pe.com.builderp.core.facturacion.model.dto.compra.CuentaBancariaDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.ActividadDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.AnhioDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.AsistenciaDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.AsociadoDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.AsociadoFamiliaDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.CategoriaByEmpresaDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.ConceptoPagoDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.ControlPagoDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.CuotaConceptoDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.DetControlPagoDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.DetPlanPagosDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.EgresoDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.IngresoDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.PersonalDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.PlanPagosDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.PuestoDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.RecordatorioDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.RubroDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.SectorDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.DetalleVentaDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.VentaDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.Venta;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.UsuarioDaoLocal;
import pe.com.edu.siaa.core.ejb.factory.CollectionUtil;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.common.local.CommonServiceLocal;
import pe.com.edu.siaa.core.ejb.service.contabilidad.local.ContabilidadServiceLocal;
import pe.com.edu.siaa.core.ejb.service.local.GenerarReporteServiceLocal;
import pe.com.edu.siaa.core.ejb.service.seguridad.local.SeguridadServiceLocal;
import pe.com.edu.siaa.core.ejb.service.util.FechaUtil;
import pe.com.edu.siaa.core.ejb.util.cache.AppAuthenticator;
import pe.com.edu.siaa.core.ejb.util.cache.SelectItemServiceCacheUtil;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.EntidadDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.UsuarioDTO;
import pe.com.edu.siaa.core.model.estate.EstadoAnhoSemestreState;
import pe.com.edu.siaa.core.model.estate.EstadoGeneralState;
import pe.com.edu.siaa.core.model.jpa.common.Item;
import pe.com.edu.siaa.core.model.jpa.empresa.Actividad;
import pe.com.edu.siaa.core.model.jpa.empresa.Anhio;
import pe.com.edu.siaa.core.model.jpa.empresa.Asistencia;
import pe.com.edu.siaa.core.model.jpa.empresa.Asociado;
import pe.com.edu.siaa.core.model.jpa.empresa.AsociadoFamilia;
import pe.com.edu.siaa.core.model.jpa.empresa.CategoriaByEmpresa;
import pe.com.edu.siaa.core.model.jpa.empresa.ControlPago;
import pe.com.edu.siaa.core.model.jpa.empresa.CuotaConcepto;
import pe.com.edu.siaa.core.model.jpa.empresa.DetControlPago;
import pe.com.edu.siaa.core.model.jpa.empresa.DetPlanPagos;
import pe.com.edu.siaa.core.model.jpa.empresa.Egreso;
import pe.com.edu.siaa.core.model.jpa.empresa.Ingreso;
import pe.com.edu.siaa.core.model.jpa.empresa.Personal;
import pe.com.edu.siaa.core.model.jpa.empresa.PlanPagos;
import pe.com.edu.siaa.core.model.jpa.empresa.Puesto;
import pe.com.edu.siaa.core.model.jpa.empresa.Recordatorio;
import pe.com.edu.siaa.core.model.jpa.empresa.Rubro;
import pe.com.edu.siaa.core.model.jpa.empresa.Sector;
import pe.com.edu.siaa.core.model.jpa.seguridad.Usuario;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.model.type.FlagConceptoPagoFraccionadoType;
import pe.com.edu.siaa.core.model.type.NombreReporteType;
import pe.com.edu.siaa.core.model.type.TipoReporteGenerarType;
import pe.com.edu.siaa.core.model.util.ConstanteConfigUtil;
import pe.com.edu.siaa.core.model.util.NumerosUtil;
import pe.com.edu.siaa.core.model.vo.DetallePlanPagosFiltroVO;
import pe.com.edu.siaa.core.model.vo.FileVO;
import pe.com.edu.siaa.core.model.vo.PagoPersonalVO;
import pe.com.edu.siaa.core.model.vo.ParametroReporteVO;
import pe.com.edu.siaa.core.model.vo.SelectItemVO;
import pe.com.edu.siaa.core.model.vo.VentaFiltroVO; 


/**
 * La Class CompraServiceImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:32:52 COT 2017
 * @since SIAA-CORE 2.1
 */
 @Stateless
public class EmpresaServiceImpl implements EmpresaServiceLocal{
	 
	 private Logger log = Logger.getLogger(EmpresaServiceImpl.class);
	/** El servicio compra dao impl. */
	@EJB
	private CategoriaByEmpresaDaoLocal categoriaByEmpresaDaoImpl; 
	
	/** El servicio contacto proveedor dao impl. */
	@EJB
	private EgresoDaoLocal egresoDaoImpl; 
	
	/** El servicio cuenta bancaria dao impl. */
	@EJB
	private IngresoDaoLocal ingresoDaoImpl; 
	
	/** El servicio cuenta tipo documento dao impl. */
	@EJB
	private PersonalDaoLocal personalDaoImpl; 
	
	/** El servicio detalle compra dao impl. */
	@EJB
	private RecordatorioDaoLocal recordatorioDaoImpl; 
	
	/** El servicio proveedor dao impl. */
	@EJB
	private ProveedorDaoLocal proveedorDaoImpl; 
	
	@EJB
	private GenerarReporteServiceLocal	 generarReporteServiceImpl; 
	
	/** El servicio contabilidad service impl. */
	@EJB
	private ContabilidadServiceLocal contabilidadServiceImpl; 

	@EJB
	private SeguridadServiceLocal seguridadServiceLocal; 
	
	/** El servicio tipo doc sunat entidad dao impl. */
	@EJB
	private TipoDocSunatEntidadDaoLocal tipoDocSunatEntidadDaoImpl;
	
	@EJB
	private AsociadoDaoLocal asociadoDaoImpl;
	
	@EJB
	private AsociadoFamiliaDaoLocal asociadoFamiliaDaoImpl;
	
	@EJB
	private PuestoDaoLocal puestoDaoImpl;
	
	@EJB
	private SectorDaoLocal sectorDaoImpl;
	
	@EJB
	private RubroDaoLocal rubroDaoImpl;
	
	@EJB
	private transient CommonServiceLocal commonServiceLocal;
	
	/** El servicio cuota concepto dao impl. */
	@EJB
	private CuotaConceptoDaoLocal cuotaConceptoDaoImpl; 
	
	/** El servicio det plan pagos dao impl. */
	@EJB
	private DetPlanPagosDaoLocal detPlanPagosDaoImpl;
	
	/** El servicio plan pagos dao impl. */
	@EJB
	private PlanPagosDaoLocal planPagosDaoImpl; 
	
	@EJB
	private ControlPagoDaoLocal controlPagoDaoImpl; 
	
	@EJB
	private DetControlPagoDaoLocal detControlPagoDaoImpl; 
	
	@EJB
	private UsuarioDaoLocal usuarioServiceImpl; 
	
	@EJB
	private AnhioDaoLocal anhioDaoImpl; 
	
	@EJB
	private AsistenciaDaoLocal asistenciaDaoImpl; 
	
	@EJB
	private ActividadDaoLocal actividadDaoImpl; 

	@Override
	public CategoriaByEmpresaDTO controladorAccionCategoria(CategoriaByEmpresaDTO categoria, AccionType accionType) throws Exception {
		//String userName = AppAuthenticator.getInstance().getUserName(categoria.getAuthToken());
		CategoriaByEmpresaDTO resultado = null;
		CategoriaByEmpresa resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				categoria.setIdCategoria(this.categoriaByEmpresaDaoImpl.generarIdCategoria());  
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(categoria, CategoriaByEmpresa.class);
				this.categoriaByEmpresaDaoImpl.save(resultadoEntity);	
				resultado = categoria;
				break;				
			case MODIFICAR:  
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(categoria, CategoriaByEmpresa.class);
				this.categoriaByEmpresaDaoImpl.update(resultadoEntity);
				resultado = categoria;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.categoriaByEmpresaDaoImpl.find(CategoriaByEmpresa.class, categoria.getIdCategoria());
				this.categoriaByEmpresaDaoImpl.delete(resultadoEntity);
				resultado = categoria;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.categoriaByEmpresaDaoImpl.find(CategoriaByEmpresa.class, categoria.getIdCategoria());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,CategoriaByEmpresaDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.categoriaDaoImpl.findByNombre(categoria),CategoriaDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}

	@Override
	public List<CategoriaByEmpresaDTO> listarCategoria(CategoriaByEmpresaDTO Categoria) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.categoriaByEmpresaDaoImpl.listarCategoria(Categoria),CategoriaByEmpresaDTO.class);
	}

	@Override
	public int contarListarCategoria(CategoriaByEmpresaDTO Categoria) {
		// TODO Auto-generated method stub
		return categoriaByEmpresaDaoImpl.contarListarCategoria(Categoria);
	}

	@Override
	public EgresoDTO controladorAccionEgreso(EgresoDTO egreso, AccionType accionType) throws Exception {
		//String userName = AppAuthenticator.getInstance().getUserName(egreso.getAuthToken());
		EgresoDTO resultado = null;
		Egreso resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				egreso.setIdEgreso(this.egresoDaoImpl.generarIdEgreso());  
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(egreso, Egreso.class,"tipoDocSunat@PK@","categoriaByEmpresa@PK@","modoPago@PK@","cuentaBancaria@PK@","personal@PK@","proveedor@PK@","entidad@PK@");
				this.egresoDaoImpl.save(resultadoEntity);	
				resultado = egreso;
				break;				
			case MODIFICAR:  
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(egreso, Egreso.class,"tipoDocSunat@PK@","categoriaByEmpresa@PK@","modoPago@PK@","cuentaBancaria@PK@","personal@PK@","proveedor@PK@","entidad@PK@");
				this.egresoDaoImpl.update(resultadoEntity);
				resultado = egreso;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.egresoDaoImpl.find(Egreso.class, egreso.getIdEgreso());
				this.egresoDaoImpl.delete(resultadoEntity);
				resultado = egreso;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.egresoDaoImpl.find(Egreso.class, egreso.getIdEgreso());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,EgresoDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.categoriaDaoImpl.findByNombre(categoria),CategoriaDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}

	@Override
	public List<EgresoDTO> listarEgreso(EgresoDTO egreso) throws Exception {
		List<Egreso> listaEgreso = egresoDaoImpl.listarEgreso(egreso);
		List<EgresoDTO> listaEgresoTemp = new ArrayList<EgresoDTO>(); 
		for(Egreso objEgreso : listaEgreso ) { 
			EgresoDTO egresoDTO = TransferDataObjectUtil.transferObjetoEntityDTO(objEgreso,EgresoDTO.class,"tipoDocSunat","categoriaByEmpresa","modoPago","cuentaBancaria:{itemByBanco;proveedor}","personal","proveedor","entidad");
			egresoDTO.setCuentaBancaria(TransferDataObjectUtil.transferObjetoEntityDTO(objEgreso.getCuentaBancaria(), CuentaBancariaDTO.class,"itemByBanco","proveedor"));
			listaEgresoTemp.add(egresoDTO);
		}
		listaEgreso =  null;
		return listaEgresoTemp;
		//	return TransferDataObjectUtil.transferObjetoEntityDTOList(this.egresoDaoImpl.listarEgreso(egreso),EgresoDTO.class,"categoriaByEmpresa","modoPago","cuentaBancaria:{itemByBanco;proveedor}","cliente","entidad");
	}

	@Override
	public int contarListarEgreso(EgresoDTO egreso) {
		// TODO Auto-generated method stub
		return this.egresoDaoImpl.contarListarEgreso(egreso);
	}

	@Override
	public IngresoDTO controladorAccionIngreso(IngresoDTO ingreso, AccionType accionType) throws Exception {
		//String userName = AppAuthenticator.getInstance().getUserName(ingreso.getAuthToken());
		IngresoDTO resultado = null;
		Ingreso resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				ingreso.setIdIngreso(this.ingresoDaoImpl.generarIdIngreso());  
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(ingreso, Ingreso.class,"categoriaByEmpresa@PK@","modoPago@PK@","cuentaBancaria@PK@","cliente@PK@","entidad@PK@","tipoDocSunat@PK@" );
				this.ingresoDaoImpl.save(resultadoEntity);	
				resultado = ingreso;
				break;				
			case MODIFICAR:  
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(ingreso, Ingreso.class,"categoriaByEmpresa@PK@","modoPago@PK@","cuentaBancaria@PK@", "cliente@PK@","entidad@PK@","tipoDocSunat@PK@");
				this.ingresoDaoImpl.update(resultadoEntity);
				resultado = ingreso;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.ingresoDaoImpl.find(Ingreso.class, ingreso.getIdIngreso());
				this.ingresoDaoImpl.delete(resultadoEntity);
				resultado = ingreso;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.ingresoDaoImpl.find(Ingreso.class, ingreso.getIdIngreso());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,IngresoDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.categoriaDaoImpl.findByNombre(categoria),CategoriaDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}

	@Override
	public List<IngresoDTO> listarIngreso(IngresoDTO ingreso) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.ingresoDaoImpl.listarIngreso(ingreso),IngresoDTO.class,"categoriaByEmpresa","modoPago","cuentaBancaria:{itemByBanco;proveedor}","cliente","entidad","tipoDocSunat");
	}

	@Override
	public int contarListarIngreso(IngresoDTO ingreso) {
		// TODO Auto-generated method stub
		return this.ingresoDaoImpl.contarListarIngreso(ingreso);
	}

	@Override
	public PersonalDTO controladorAccionPersonal(PersonalDTO personal, AccionType accionType) throws Exception {
		//String userName = AppAuthenticator.getInstance().getUserName(personal.getAuthToken());
		PersonalDTO resultado = null;
		Personal resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				personal.setIdPersonal(this.personalDaoImpl.generarIdPersonal());  
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(personal, Personal.class,"tipoPersonal","tipoDocumento");
				this.personalDaoImpl.save(resultadoEntity);	
				resultado = personal;
				break;				
			case MODIFICAR:  
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(personal, Personal.class,"tipoPersonal","tipoDocumento");
				this.personalDaoImpl.update(resultadoEntity);
				resultado = personal;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.personalDaoImpl.find(Personal.class, personal.getIdPersonal());
				this.personalDaoImpl.delete(resultadoEntity);
				resultado = personal;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.personalDaoImpl.find(Personal.class, personal.getIdPersonal());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,PersonalDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.categoriaDaoImpl.findByNombre(categoria),CategoriaDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}

	@Override
	public List<PersonalDTO> listarPersonal(PersonalDTO personal) throws Exception {
		List<Personal> listaTemp = personalDaoImpl.listarPersonal(personal);
		List<PersonalDTO> listaPer = new ArrayList<PersonalDTO>(); 
		for (Personal per : listaTemp) {
			PersonalDTO personalDTO = TransferDataObjectUtil.transferObjetoEntityDTO(per, PersonalDTO.class,"tipoPersonal","tipoDocumento"); 
			FileVO fileVO = new FileVO();
			fileVO.setRuta(ConstanteConfigUtil.RUTA_RECURSOS_FOTO_ALUMN + ConstanteConfigUtil.SEPARADOR_FILE + "P_" +  per.getFoto());			
			personalDTO.setFoto(commonServiceLocal.obtenerImagenEncodeBase64(fileVO));
			listaPer.add(personalDTO);
		}	
		listaTemp = null;
		return listaPer;
		//return TransferDataObjectUtil.transferObjetoEntityDTOList(this.personalDaoImpl.listarPersonal(personal),PersonalDTO.class,"tipoPersonal","tipoDocumento");
	}

	@Override
	public int contarListarPersonal(PersonalDTO personal) {
		// TODO Auto-generated method stub
		return this.personalDaoImpl.contarListarPersonal(personal);
	}

	@Override
	public RecordatorioDTO controladorAccionRecordatorio(RecordatorioDTO recordatorio, AccionType accionType)throws Exception {
		//String userName = AppAuthenticator.getInstance().getUserName(recordatorio.getAuthToken());
		RecordatorioDTO resultado = null;
		Recordatorio resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				recordatorio.setIdRecordatorio(this.recordatorioDaoImpl.generarIdRecordatorio());  
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(recordatorio, Recordatorio.class);
				this.recordatorioDaoImpl.save(resultadoEntity);	
				resultado = recordatorio;
				break;				
			case MODIFICAR:  
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(recordatorio, Recordatorio.class);
				this.recordatorioDaoImpl.update(resultadoEntity);
				resultado = recordatorio;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.recordatorioDaoImpl.find(Recordatorio.class, recordatorio.getIdRecordatorio());
				this.recordatorioDaoImpl.delete(resultadoEntity);
				resultado = recordatorio;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.recordatorioDaoImpl.find(Recordatorio.class, recordatorio.getIdRecordatorio());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,RecordatorioDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.categoriaDaoImpl.findByNombre(categoria),CategoriaDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}

	@Override
	public List<RecordatorioDTO> listarRecordatorio(RecordatorioDTO recordatorio) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.recordatorioDaoImpl.listarRecordatorio(recordatorio),RecordatorioDTO.class);
	}

	@Override
	public int contarListarRecordatorio(RecordatorioDTO recordatorio) {
		// TODO Auto-generated method stub
		return this.recordatorioDaoImpl.contarListarRecordatorio(recordatorio);
	}

	@Override
	public List<PagoPersonalVO> listarPersonalTemp(VentaFiltroVO filtro) throws Exception {
		List<PagoPersonalVO> resultado = new ArrayList<PagoPersonalVO>(); ;
		PersonalDTO personal= new PersonalDTO();
		personal.setEstado("A");
		personal.setTipoPersonal(new ItemDTO());
		personal.getTipoPersonal().setIdItem(filtro.getIdTemp());
		List<PersonalDTO> listaPersonal = listarPersonal(personal);
		for(PersonalDTO obj : listaPersonal) {
			PagoPersonalVO pagoPersonal = new PagoPersonalVO();
			pagoPersonal.setIdPersonal(obj.getIdPersonal());
			pagoPersonal.setNombre(obj.getNombre());
			pagoPersonal.setApellidoPaterno(obj.getApellidoPaterno());
			pagoPersonal.setApellidoMaterno(obj.getApellidoMaterno());
			pagoPersonal.setTipoPersonal(obj.getTipoPersonal().getNombre()); 
			pagoPersonal.setFoto(obj.getFoto());
			resultado.add(pagoPersonal);
		}
		listaPersonal = null;
		// TODO Auto-generated method stub
		return resultado;
	} 
	
	@Override
	public CategoriaByEmpresaDTO obtenerCategoriaByParameter(CategoriaByEmpresaDTO categoriaByEmpresaDTO) throws Exception {
	  return TransferDataObjectUtil.transferObjetoEntityDTO(categoriaByEmpresaDaoImpl.obtenerCategoriaByParameter(categoriaByEmpresaDTO),CategoriaByEmpresaDTO.class);
	}

	@Override
	public void registrarPagoPersonal(List<PagoPersonalVO> listaPagoPersonalVO) throws Exception {
		// TODO Auto-generated method stub
		if(!CollectionUtil.isEmpty(listaPagoPersonalVO)) {
			for(PagoPersonalVO obj : listaPagoPersonalVO) {
				if(obj.isChecked()) {
					EgresoDTO egreso = new EgresoDTO();
					egreso.setCategoriaByEmpresa(new CategoriaByEmpresaDTO());
					egreso.getCategoriaByEmpresa().setIdCategoria(obj.getIdCategoria());
					egreso.setModoPago(new ItemDTO());
					egreso.getModoPago().setIdItem(obj.getIdMetodoPago());
					egreso.setFecha(obj.getFechaPago());
					egreso.setEntidad(new EntidadDTO());
					egreso.getEntidad().setIdEntidad(obj.getIdEntidadSelect());
					if(!StringUtils.isNotNullOrBlank(obj.getCuentaBancaria())) {
						egreso.setCuentaBancaria(new CuentaBancariaDTO());
						egreso.getCuentaBancaria().setIdCuentaBancaria(obj.getIdCuentaBancaria());
					}
					egreso.setPersonal(new PersonalDTO());
					egreso.getPersonal().setIdPersonal(obj.getIdPersonal());
					if(obj.isEsFacturado()) {
						egreso.setEsFacturado("S");
					}else {
						egreso.setEsFacturado("N");
					}
					egreso.setDescripcion(obj.getDescripcion());
					egreso.setMonto(obj.getMonto());
					this.controladorAccionEgreso(egreso, AccionType.CREAR);
				}
			}
		}
	}

	@Override
	public AsociadoDTO controladorAccionAsociado(AsociadoDTO asociado, AccionType accionType) throws Exception {
		// TODO Auto-generated method stub
		AsociadoDTO resultado = null;
		Asociado resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				asociado.setIdAsociado(this.asociadoDaoImpl.generarIdAsociado());  
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(asociado, Asociado.class,"itemGrado@PK@","itemActividad@PK@","ubigeoActual@PK@","ubigeoNacimiento@PK@","itemByTipoAsociado@PK@","tipoAsociado@PK@","tipoDocumento@PK@","itemByEsatdoCivil@PK@");
				this.asociadoDaoImpl.save(resultadoEntity);	
				resultado = asociado;
				break;				
			case MODIFICAR:  
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(asociado, Asociado.class,"itemGrado@PK@","itemActividad@PK@","ubigeoActual@PK@","ubigeoNacimiento@PK@","itemByTipoAsociado@PK@","tipoAsociado@PK@","tipoDocumento@PK@","itemByEsatdoCivil@PK@");
				this.asociadoDaoImpl.update(resultadoEntity);
				resultado = asociado;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.asociadoDaoImpl.find(Asociado.class, asociado.getIdAsociado());
				this.asociadoDaoImpl.delete(resultadoEntity);
				resultado = asociado;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.asociadoDaoImpl.find(Asociado.class, asociado.getIdAsociado());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,AsociadoDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.categoriaDaoImpl.findByNombre(categoria),CategoriaDTO .class);
				break;*/
				
			default:
				break;
		}
		if (!CollectionUtil.isEmpty(asociado.getAsociadoFamiliaDTOList())) {
			for (AsociadoFamiliaDTO asociadoFamilia : asociado.getAsociadoFamiliaDTOList()) {
				if (!asociadoFamilia.isEsEliminado()) {
					if (StringUtils.isNullOrEmpty(asociadoFamilia.getIdAsociadoFamilia())) {
						controladorAccionAsociadoFamilia(asociadoFamilia,resultadoEntity,AccionType.CREAR);
					} else {
						controladorAccionAsociadoFamilia(asociadoFamilia,resultadoEntity,AccionType.MODIFICAR);
					}
				} else {
					controladorAccionAsociadoFamilia(asociadoFamilia,resultadoEntity, AccionType.ELIMINAR);
				}
			}
		}
		return resultado;
	}

	@Override
	public List<AsociadoDTO> listarAsociado(AsociadoDTO asociado) throws Exception {
		// TODO Auto-generated method stub
		List<Asociado> listaTemp = asociadoDaoImpl.listarAsociado(asociado);
		List<AsociadoDTO> listaPer = new ArrayList<AsociadoDTO>(); 
		for (Asociado per : listaTemp) {
			AsociadoDTO asociadoDTO = TransferDataObjectUtil.transferObjetoEntityDTO(per, AsociadoDTO.class,"itemGrado","itemActividad","itemByRubro","itemByEsatdoCivil","itemByTipoAsociado","tipoDocumento","ubigeoNacimiento:{ubigeoByDependencia}","ubigeoActual:{ubigeoByDependencia}"); 
			FileVO fileVO = new FileVO();
			fileVO.setRuta(ConstanteConfigUtil.RUTA_RECURSOS_FOTO_ALUMN + ConstanteConfigUtil.SEPARADOR_FILE + "P_" +  per.getFoto());			
			asociadoDTO.setFoto(commonServiceLocal.obtenerImagenEncodeBase64(fileVO));
			listaPer.add(asociadoDTO);
		}	
		listaTemp = null;
		return listaPer;
	}

	@Override
	public int contarListarAsociado(AsociadoDTO asociado) {
		// TODO Auto-generated method stub
		return this.asociadoDaoImpl.contarListarAsociado(asociado);
	}

	@Override
	public AsociadoFamiliaDTO controladorAccionAsociadoFamilia(AsociadoFamiliaDTO asociadoFamilia,Asociado asociado,AccionType accionType) throws Exception {
		AsociadoFamiliaDTO resultado = null;
		AsociadoFamilia resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				asociadoFamilia.setIdAsociadoFamilia(this.asociadoFamiliaDaoImpl.generarIdAsociadoFamilia());  
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(asociadoFamilia, AsociadoFamilia.class,"asociado@PK@");
				resultadoEntity.setAsociado(asociado);
				this.asociadoFamiliaDaoImpl.save(resultadoEntity);	
				resultado = asociadoFamilia;
				break;				
			case MODIFICAR:  
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(asociadoFamilia, AsociadoFamilia.class,"asociado@PK@" );
				resultadoEntity.setAsociado(asociado);
				this.asociadoFamiliaDaoImpl.update(resultadoEntity);
				resultado = asociadoFamilia;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.asociadoFamiliaDaoImpl.find(AsociadoFamilia.class, asociadoFamilia.getIdAsociadoFamilia());
				this.asociadoFamiliaDaoImpl.delete(resultadoEntity);
				resultado = asociadoFamilia;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.asociadoFamiliaDaoImpl.find(AsociadoFamilia.class, asociadoFamilia.getIdAsociadoFamilia());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,AsociadoFamiliaDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.categoriaDaoImpl.findByNombre(categoria),CategoriaDTO .class);
				break;*/
				
			default:
				break;
		}
		return resultado;
	}

	@Override
	public List<AsociadoFamiliaDTO> listarAsociadoFamilia(AsociadoFamiliaDTO asociadoFamilia) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.asociadoFamiliaDaoImpl.listarAsociadoFamilia(asociadoFamilia),AsociadoFamiliaDTO.class,"asociado");

	}

	@Override
	public int contarListarAsociadoFamilia(AsociadoFamiliaDTO AsociadoFamilia) {
		// TODO Auto-generated method stub
		return this.asociadoFamiliaDaoImpl.contarListarAsociadoFamilia(AsociadoFamilia);
	}

	@Override
	public PuestoDTO controladorAccionPuesto(PuestoDTO puesto, AccionType accionType) throws Exception {
		PuestoDTO resultado = null;
		Puesto resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				puesto.setIdPuesto(this.puestoDaoImpl.generarIdPuesto());  
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(puesto, Puesto.class,"itemCondicion@PK@","sector@PK@","asociado@PK@"); 
				this.puestoDaoImpl.save(resultadoEntity);	
				resultado = puesto;
				break;				
			case MODIFICAR:  
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(puesto, Puesto.class,"itemCondicion@PK@","sector@PK@","asociado@PK@"); 
				this.puestoDaoImpl.update(resultadoEntity);
				resultado = puesto;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.puestoDaoImpl.find(Puesto.class, puesto.getIdPuesto());
				this.puestoDaoImpl.delete(resultadoEntity);
				resultado = puesto;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.puestoDaoImpl.find(Puesto.class, puesto.getIdPuesto());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,PuestoDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.categoriaDaoImpl.findByNombre(categoria),CategoriaDTO .class);
				break;*/
				
			default:
				break;
		}
		return resultado;
	}

	@Override
	public List<PuestoDTO> listarPuesto(PuestoDTO Puesto) throws Exception {
		// TODO Auto-generated method stub
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.puestoDaoImpl.listarPuesto(Puesto),PuestoDTO.class,"itemCondicion","sector","asociado");
	}

	@Override
	public int contarListarPuesto(PuestoDTO Puesto) {
		// TODO Auto-generated method stub
		return this.puestoDaoImpl.contarListarPuesto(Puesto);
	}

	@Override
	public SectorDTO controladorAccionSector(SectorDTO sector, AccionType accionType) throws Exception {
		SectorDTO resultado = null;
		Sector resultadoEntity = null;
		boolean isCrearRubroRequerido = false;
		switch (accionType) {
			case CREAR:
				sector.setIdSector(this.sectorDaoImpl.generarIdSector());  
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(sector, Sector.class,"delegado@PK@"); 
				this.sectorDaoImpl.save(resultadoEntity);	
				resultado = sector;
				isCrearRubroRequerido = true;
				break;				
			case MODIFICAR:  
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(sector, Sector.class,"delegado@PK@" ); 
				this.sectorDaoImpl.update(resultadoEntity);
				resultado = sector;	
				isCrearRubroRequerido = true;
				break;
				
			case ELIMINAR:
				resultadoEntity = this.sectorDaoImpl.findSectorByID(sector.getIdSector());
				this.sectorDaoImpl.delete(resultadoEntity);
				resultado = sector;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.sectorDaoImpl.find(Sector.class, sector.getIdSector());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,SectorDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.categoriaDaoImpl.findByNombre(categoria),CategoriaDTO .class);
				break;*/
				
			default:
				break;
		}
		if (isCrearRubroRequerido) {
			crearRubroRequerido(resultadoEntity, sector.getListaRubroRequerido(),accionType);
		}
		return resultado;
	}

	private void crearRubroRequerido(Sector sector,List<SelectItemVO> listaTipoRubroRequeridoTemp,AccionType accionType) throws Exception {
		//List<ItemDTO> listaTipoRubroRequerido = SelectItemServiceCacheUtil.getInstance().converItemDTO(listaTipoRubroRequeridoTemp);
		if (AccionType.MODIFICAR.getKey().equals(accionType.getKey())) {
			//ya que tiene uuid
			rubroDaoImpl.eliminarRubroRequerido(sector.getIdSector());
		}				
		for (SelectItemVO objItem : listaTipoRubroRequeridoTemp) {
			if (objItem.isChecked()) {
				Rubro objPersist = new Rubro();
				objPersist.setSector(sector);
				Long id = Long.valueOf(objItem.getId() + "");
				objPersist.setItemRubro(TransferDataObjectUtil.transferObjetoEntityPK(SelectItemServiceCacheUtil.getInstance().obtenerItem(id), Item.class));
				objPersist.setIdRubro(UUIDUtil.generarElementUUID());
				rubroDaoImpl.save(objPersist);
			}			
		}
	}
	
	@Override
	public List<SectorDTO> listarSector(SectorDTO sector) throws Exception {
		List<Sector> listaTemp = sectorDaoImpl.listarSector(sector);
		List<SectorDTO> listaPer = new ArrayList<SectorDTO>(); 
		for (Sector per : listaTemp) {
			SectorDTO sectorDTO = TransferDataObjectUtil.transferObjetoEntityDTO(per, SectorDTO.class,"delegado"); 
			List<RubroDTO> listaRubroRequerido = listarRubroRequerido(per.getIdSector());
			for (RubroDTO documentoRequeridoDTO : listaRubroRequerido) {
				sectorDTO.getListaRubroRequerido().add(new SelectItemVO(documentoRequeridoDTO.getItemRubro()));
			}	
			listaPer.add(sectorDTO);
		}	
		listaTemp = null;
		return listaPer;
		//return TransferDataObjectUtil.transferObjetoEntityDTOList(this.sectorDaoImpl.listarSector(sector),SectorDTO.class,"delegado");
	}

	@Override
	public int contarListarSector(SectorDTO sector) { 
		return this.sectorDaoImpl.contarListarSector(sector);
	}

	@Override
	public List<RubroDTO> listarRubroRequerido(String idSector) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.rubroDaoImpl.listarRubro(idSector),RubroDTO.class,"itemRubro");
	}

	@Override
	public PlanPagosDTO registrarPlanPagos(PlanPagosDTO planPagos) throws Exception {
		PlanPagosDTO resultado = null;
		PlanPagos resultadoEntity = null;
		String userName = AppAuthenticator.getInstance().getUserName(planPagos.getAuthToken());
		boolean isCrearDetPlanPagos = false;
		if (!StringUtils.isNotNullOrBlank(planPagos.getIdPlanPagos())) {
			planPagos.setIdPlanPagos(this.planPagosDaoImpl.generarIdPlanPagos());
			planPagos.setUsuarioCreacion(userName);
			planPagos.setFechaCreacion(FechaUtil.obtenerFecha());
			resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(planPagos, PlanPagos.class,"asociado@PK@","anio@PK@");
			this.planPagosDaoImpl.save(resultadoEntity);	
			resultado = planPagos;
			isCrearDetPlanPagos = true;
		} else {
			planPagos.setUsuarioModificacion(userName);
			planPagos.setFechaModificacion(FechaUtil.obtenerFecha());
			resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(planPagos, PlanPagos.class,"asociado@PK@","anio@PK@");
			this.planPagosDaoImpl.update(resultadoEntity);
			resultado = planPagos;	
			isCrearDetPlanPagos = true;
		}		
		if (isCrearDetPlanPagos) {
			if (!CollectionUtil.isEmpty(planPagos.getPlanPagosDetPlanPagosList())) {
				registrarDetPlanPagos(userName,resultadoEntity, planPagos.getPlanPagosDetPlanPagosList());
			}
		}
		return resultado;
	}

	private void registrarDetPlanPagos(String userName,PlanPagos planPagos, List<DetPlanPagosDTO> listDetPlanPagos) throws Exception {
		DetPlanPagos resultadoEntity = null; 
		for(DetPlanPagosDTO detPlanPagos :listDetPlanPagos) {
			if (!StringUtils.isNotNullOrBlank(detPlanPagos.getIdDetPlanPagos())) {
				detPlanPagos.setIdDetPlanPagos(this.detPlanPagosDaoImpl.generarIdDetPlanPagos(planPagos.getIdPlanPagos()));
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(detPlanPagos, DetPlanPagos.class,"cuotaConcepto@PK@");
				resultadoEntity.setPlanPagos(planPagos);
				this.detPlanPagosDaoImpl.save(resultadoEntity);	
			} else {
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(detPlanPagos, DetPlanPagos.class,"cuotaConcepto@PK@");
				resultadoEntity.setPlanPagos(planPagos);
				this.detPlanPagosDaoImpl.update(resultadoEntity);  
			}	
		}
	}
	@Override
	public List<PlanPagosDTO> listarPlanPagos(PlanPagosDTO planPagos) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.planPagosDaoImpl.listarPlanPagos(planPagos),PlanPagosDTO.class,"anio","asociado");
	}

	@Override
	public int contarListarPlanPagos(PlanPagosDTO planPagos) {
		return  this.planPagosDaoImpl.contarListarPlanPagos(planPagos);
	}

	@Override
	public List<DetPlanPagosDTO> listarDetPlanPagos(DetPlanPagosDTO detPlanPagos) throws Exception {
		List<DetPlanPagos> listaTemp = this.detPlanPagosDaoImpl.listarDetPlanPagos(detPlanPagos);
		List<DetPlanPagosDTO> listaPer = new ArrayList<DetPlanPagosDTO>(); 
		for (DetPlanPagos per : listaTemp) {
			DetPlanPagosDTO detPlanPagosDTO = TransferDataObjectUtil.transferObjetoEntityDTO(per, DetPlanPagosDTO.class,"cuotaConcepto","planPagos:{asociado}"); 
			PlanPagosDTO planPagos = TransferDataObjectUtil.transferObjetoEntityDTO(per.getPlanPagos(), PlanPagosDTO.class,"asociado","anio");
			detPlanPagosDTO.setPlanPagos(planPagos);
			listaPer.add(detPlanPagosDTO);
		}	
		listaTemp = null;
		return listaPer; 
	}

	@Override
	public DetPlanPagosDTO eliminarDetPlanPagos(DetPlanPagosDTO detPlanPagos) {
		DetPlanPagos resultadoEntity = this.detPlanPagosDaoImpl.find(DetPlanPagos.class, detPlanPagos.getIdDetPlanPagos()); 
		this.detPlanPagosDaoImpl.delete(resultadoEntity);
		return detPlanPagos;
	}

	@Override
	public int contarListarDetPlanPagos(DetPlanPagosDTO detPlanPagos) {
		return  this.detPlanPagosDaoImpl.contarListarDetPlanPagos(detPlanPagos);
	}

	@Override
	public CuotaConceptoDTO controladorAccionCuotaConcepto(CuotaConceptoDTO cuotaConcepto, AccionType accionType)
			throws Exception { 
		CuotaConceptoDTO resultado = null;
		CuotaConcepto resultadoEntity = null; 
		switch (accionType) {
			case CREAR:
				cuotaConcepto.setIdCuotaConcepto(this.cuotaConceptoDaoImpl.generarIdCuotaConcepto());  
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(cuotaConcepto, CuotaConcepto.class); 
				this.cuotaConceptoDaoImpl.save(resultadoEntity);	
				resultado = cuotaConcepto; 
				break;				
			case MODIFICAR:  
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(cuotaConcepto, CuotaConcepto.class ); 
				this.cuotaConceptoDaoImpl.update(resultadoEntity);
				resultado = cuotaConcepto;	 
				break;
				
			case ELIMINAR:
				resultadoEntity = this.cuotaConceptoDaoImpl.find(CuotaConcepto.class, cuotaConcepto.getIdCuotaConcepto());
				this.cuotaConceptoDaoImpl.delete(resultadoEntity);
				resultado = cuotaConcepto;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.cuotaConceptoDaoImpl.find(CuotaConcepto.class, cuotaConcepto.getIdCuotaConcepto());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,CuotaConceptoDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.categoriaDaoImpl.findByNombre(categoria),CategoriaDTO .class);
				break;*/
				
			default:
				break;
		} 
		return resultado;
	}

	@Override
	public List<CuotaConceptoDTO> listarCuotaConcepto(CuotaConceptoDTO cuotaConcepto) throws Exception {
		List<CuotaConceptoDTO> resultado = new ArrayList<CuotaConceptoDTO>();
		List<CuotaConcepto> resultadoTemp =  this.cuotaConceptoDaoImpl.listarCuotaConcepto(cuotaConcepto);
		for (CuotaConcepto objConcepto : resultadoTemp) {
			CuotaConceptoDTO objRes = TransferDataObjectUtil.transferObjetoEntityDTO(objConcepto, CuotaConceptoDTO.class); 
			resultado.add(objRes);
		}
		return resultado;
	}

	@Override
	public int contarListarCuotaConcepto(CuotaConceptoDTO cuotaConcepto) { 
		return  this.cuotaConceptoDaoImpl.contarListarCuotaConcepto(cuotaConcepto);
	}

	@Override
	public List<DetPlanPagosDTO> listarConceptoPagoAsociado(String idAsociado, boolean flagFaltaMontoResta)
			throws Exception {
		List<DetPlanPagosDTO> resultado = new ArrayList<DetPlanPagosDTO>();
		List<DetPlanPagos> resultadoTemp = detPlanPagosDaoImpl.listarConceptoPagoAsociadoSemestre(idAsociado, flagFaltaMontoResta);
		List<String> listaDetPlanPagos = new ArrayList<String>();
		for (DetPlanPagos objData : resultadoTemp) {
			if (FlagConceptoPagoFraccionadoType.SI.getKey().equals(objData.getFlagFraccionado())) {
				listaDetPlanPagos.add(objData.getIdDetPlanPagos());			
			}
			DetPlanPagosDTO detPlanPagos = TransferDataObjectUtil.transferObjetoEntityDTO(objData, DetPlanPagosDTO.class);
			CuotaConceptoDTO cuotaConcepto = TransferDataObjectUtil.transferObjetoEntityDTO(objData.getCuotaConcepto(), CuotaConceptoDTO.class);
			detPlanPagos.setCuotaConcepto(cuotaConcepto);
			resultado.add(detPlanPagos);
		}
		return resultado;
	}
	
	@Override
	public List<DetPlanPagosDTO> listarConceptoPagoAsociadoAPP(String idAsociado, String idCuotaConcpeto) throws Exception {
		List<DetPlanPagosDTO> resultado = new ArrayList<DetPlanPagosDTO>();
		List<DetPlanPagos> resultadoTemp = detPlanPagosDaoImpl.listarConceptoPagoAsociadoSemestreAPP(idAsociado, idCuotaConcpeto);
		List<String> listaDetPlanPagos = new ArrayList<String>();
		for (DetPlanPagos objData : resultadoTemp) {
			if (FlagConceptoPagoFraccionadoType.SI.getKey().equals(objData.getFlagFraccionado())) {
				listaDetPlanPagos.add(objData.getIdDetPlanPagos());			
			}
			DetPlanPagosDTO detPlanPagos = TransferDataObjectUtil.transferObjetoEntityDTO(objData, DetPlanPagosDTO.class);
			CuotaConceptoDTO cuotaConcepto = TransferDataObjectUtil.transferObjetoEntityDTO(objData.getCuotaConcepto(), CuotaConceptoDTO.class);
			detPlanPagos.setCuotaConcepto(cuotaConcepto);
			resultado.add(detPlanPagos);
		}
		return resultado;
	}

	@Override
	public DetPlanPagosDTO optenerByDetPlanPagos(String idAlumno) throws Exception {
		DetPlanPagosDTO resultado = new DetPlanPagosDTO();
		DetPlanPagos alumno =  null;//detPlanPagosDaoImpl. optenerByDetPlanPagos( idAlumno, "");
		resultado = TransferDataObjectUtil.transferObjetoEntityDTO(alumno,DetPlanPagosDTO.class,"cuotaConcepto:{catalogoCuenta}");		
		alumno = null;
		return resultado;
	}

	@Override
	public ControlPagoDTO registrarPago(ControlPagoDTO controlPago) throws Exception {
		String userName = AppAuthenticator.getInstance().getUserName(controlPago.getAuthToken());
		//Alumno alumno = this.alumnoDaoImpl.findAlumno(controlPago.getAlumno().getIdAlumno());
		ControlPago controlPagoPersist = null;
		List<ConceptoPagoDTO> listaConceptoPagoDTO = controlPago.getListaConceptoPagoDTO();
		
	

		if (!StringUtils.isNotNullOrBlank(controlPago.getIdControlPago())) {
			controlPago.setFechaCreacion(FechaUtil.obtenerFecha());
			if (!StringUtils.isNotNullOrBlank(controlPago.getFechaPago())) {
				controlPago.setFechaPago(FechaUtil.obtenerFecha());
			}
			if (StringUtils.isNotNullOrBlank(controlPago.getTipoDocSunat().getIdItem())) {
				String nroDocCalc = tipoDocSunatEntidadDaoImpl.actualizarTipoDocSunat(controlPago.getTipoDocSunat().getIdItem(),controlPago.getIdEntidadSelect(),controlPago.getNroDoc(),controlPago.getSerie()); //OJO AQUI
				if (!StringUtils.isNotNullOrBlank(controlPago.getNroDoc()) ) {
					//generar el nro doc
					controlPago.setNroDoc(nroDocCalc);
				}
				
			}
			
			controlPago.setUsuarioCreacion(userName);
			if(!StringUtils.isNotNullOrBlank(controlPago.getIgv())) {
				controlPago.setIgv(new BigDecimal("0.00"));
			}
			if (!StringUtils.isNotNullOrBlank(controlPago.getDescuento())) {
				controlPago.setDescuento(new BigDecimal("0.00"));
			}
			
			if (!StringUtils.isNotNullOrBlank(controlPago.getDescuentoTotal())) {
				controlPago.setDescuentoTotal(new BigDecimal("0.00"));
			}
			//controlPago.setNroCorrelativoOperacion(controlPagoDaoImpl.generarNumeroOperacion(FechaUtil.anio(FechaUtil.obtenerFecha())));
			controlPago.setIdControlPago(controlPagoDaoImpl.generarIdControlPago(controlPago.getAnio().getIdAnhio().toString()));
			controlPagoPersist = TransferDataObjectUtil.transferObjetoEntity(controlPago, ControlPago.class,"anio@PK@","asociado@PK@","tipoDocSunat@PK@","itemByTipoMoneda@PK@");
			//personaDaoImpl.actualizarPersona(controlPago.getAlumno().getPostulante().getPersona());
			controlPagoPersist = controlPagoDaoImpl.save(controlPagoPersist);
 
		} else {
			controlPago.setFechaModificacion(FechaUtil.obtenerFecha());
			controlPago.setUsuarioModificacion(userName);
			controlPagoPersist = TransferDataObjectUtil.transferObjetoEntity(controlPago, ControlPago.class,"anio@PK@","asociado@PK@","tipoDocSunat@PK@","itemByTipoMoneda@PK@");
			controlPagoPersist.setMontoTotal(controlPago.getMontoTotal());
			controlPagoPersist = controlPagoDaoImpl.update(controlPagoPersist); 
		 
		}
		if (listaConceptoPagoDTO == null) {
			listaConceptoPagoDTO = new ArrayList<ConceptoPagoDTO>();
		}
		Map<String,BigDecimal> montoPagoFraccionadoMap = new HashMap<String, BigDecimal>();
		//registrando detalle de pago y actualizando montos restantes
		for (ConceptoPagoDTO conceptoPagoDTO : listaConceptoPagoDTO) {
			if (conceptoPagoDTO.isCheck()) {
				DetControlPago detControlPago = new DetControlPago();
				detControlPago.setControlPago(controlPagoPersist);
				if (conceptoPagoDTO.isEsFraccionado()) {
					BigDecimal montoPago = conceptoPagoDTO.getMontoTotal().subtract(conceptoPagoDTO.getMontoResta());
					if (montoPagoFraccionadoMap.containsKey(conceptoPagoDTO.getIdPadre())) {
						BigDecimal montoPagoMap =  montoPagoFraccionadoMap.get(conceptoPagoDTO.getIdPadre());
						montoPago = montoPago.add(montoPagoMap);
					}
					montoPagoFraccionadoMap.put(conceptoPagoDTO.getIdPadre(), montoPago);
					detControlPago.setNroCuota(conceptoPagoDTO.getNroCuota()); 
				} else {
					if (conceptoPagoDTO.getId() != null && !conceptoPagoDTO.getId().toString().contains("Artificio")) {
						DetPlanPagos detPlanPagos = new DetPlanPagos();
						detPlanPagos.setIdDetPlanPagos(conceptoPagoDTO.getId() + "");
						detPlanPagos.setMontoResta(conceptoPagoDTO.getMontoResta()); 
						detPlanPagosDaoImpl.actualizarMontoResta(detPlanPagos);
					}
				}
				detControlPago.setCuotaConcepto(new CuotaConcepto());
				detControlPago.getCuotaConcepto().setIdCuotaConcepto(conceptoPagoDTO.getIdCuotaConcepto()  + "");
				detControlPago.setMonto(conceptoPagoDTO.getMonto().subtract(conceptoPagoDTO.getMontoResta()));
				detControlPago.setDescripcionConcepto(conceptoPagoDTO.getDescripcion());
				detControlPago.setIdDetControlPago(detControlPagoDaoImpl.generarIdDetControlPago(controlPagoPersist.getIdControlPago()));
				
				detControlPago.setUsuarioCreacion(controlPago.getUsuarioCreacion());
				detControlPago.setFechaCreacion(controlPago.getFechaCreacion());
				detControlPago.setIp(controlPago.getIp());
				detControlPago.setEstado(EstadoGeneralState.ACTIVO.getKey());
				
				detControlPago = detControlPagoDaoImpl.save(detControlPago);
			}
		}
		//actualizando los datos del padre
		for (Map.Entry<String,BigDecimal> entryMap : montoPagoFraccionadoMap.entrySet()) {
			DetPlanPagos detPlanPagos = detPlanPagosDaoImpl.find(DetPlanPagos.class, entryMap.getKey());
			BigDecimal montoPago = new BigDecimal("0");
			if (detPlanPagos.getMontoResta() != null) {
				montoPago = detPlanPagos.getMontoResta();
			} else {
				montoPago = detPlanPagos.getCuota();
			}
			detPlanPagos.setMontoResta(montoPago.subtract(entryMap.getValue()));
			detPlanPagosDaoImpl.update(detPlanPagos);
		}
		return controlPago;
	}

	@Override
	public List<ControlPagoDTO> listarControlPago(ControlPagoDTO controlPago) throws Exception {
		//return TransferDataObjectUtil.transferObjetoEntityDTOList(this.controlPagoDaoImpl.listarControlPago(controlPago),ControlPagoDTO.class,"tipoDocSunat","anhoSemestre","tipoDocSunat","itemByTipoMoneda","cliente");
		List<ControlPagoDTO> resultado = new ArrayList<ControlPagoDTO>();
		List<ControlPago> listaTemo = controlPagoDaoImpl.listarControlPago(controlPago);
		for (ControlPago controlPago2 : listaTemo) {
			ControlPagoDTO controlPagoDTO = TransferDataObjectUtil.transferObjetoEntityDTO(controlPago2,ControlPagoDTO.class,"tipoDocSunat","anio","itemByTipoMoneda","asociado" );
 
			 
			//List<DetControlPago> listaTemoa = listarDetControlPago(controlPago2.getIdControlPago());
			//if(StringUtils.isNullOrEmpty(controlPago.getFechaInicio())){
				List<DetControlPagoDTO> ListarDetControlPago = verDetallePagosRealizados(controlPago2.getIdControlPago());
				controlPagoDTO.setControlPagoDetControlPagoList(ListarDetControlPago);
				controlPagoDTO.setVarCantidad(this.detControlPagoDaoImpl.listarDetControlPago(controlPago2.getIdControlPago()).size());
			//}
			resultado.add(controlPagoDTO);
		}
		listaTemo = null;
		return resultado;
	}

	@Override
	public int contarListarControlPago(ControlPagoDTO controlPago) {
		return  this.controlPagoDaoImpl.contarListarControlPago(controlPago);
	}

	@Override
	public String generarReportePago(String idControlPago, String idAsociado, String usuario) throws Exception {
		ControlPago control = controlPagoDaoImpl.find(ControlPago.class, idControlPago);
		String fileName = control.getSerie() +"-"+control.getNroDoc();
		String codigoGeneradoReporte = fileName;
		UsuarioDTO resultado = null;
		Usuario resultadoEntity = null;
		try {
			AsociadoDTO asociado = new AsociadoDTO();
			asociado.setIdAsociado(idAsociado);		
			asociado = controladorAccionAsociado(asociado, AccionType.FIND_BY_ID);
			Map<String, Object> parametros = new HashMap<String, Object>();
			String[] subreportes;
			subreportes = new String[0];	 
			parametros.put("nombreAlumno", asociado.getNombre() + " " +  asociado.getApellidoPaterno() + " " + asociado.getApellidoMaterno());
			parametros.put("ruta", "");
			parametros.put("nroDni", asociado.getNrodoc());
			parametros.put("direccionAL", asociado.getDireccion());
			
			List<ControlPagoDTO> listaControlPagoGenerar = new ArrayList<ControlPagoDTO>();
			ControlPagoDTO controlPagoReporte = new ControlPagoDTO();
			controlPagoReporte.setIdControlPago(idControlPago);
			controlPagoReporte.setId(idAsociado);
			controlPagoReporte.setAsociado(asociado);

			controlPagoReporte = this.listarControlPago(controlPagoReporte).get(0); 
			resultadoEntity= this.usuarioServiceImpl.findUsuario(controlPagoReporte.getUsuarioCreacion());
			resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,UsuarioDTO.class);
			parametros.put("nombreCajero", resultado.getNombre()+ " " + resultado.getApellidoPaterno()+ " " +resultado.getApellidoMaterno());
			NumerosUtil numLetra= new NumerosUtil();
			parametros.put("montoLetra", numLetra.Convertir(controlPagoReporte.getMontoTotal().toString(), true));
			
			controlPagoReporte.setAsociado(asociado);
			List<DetControlPagoDTO> listaDetControlPago = verDetallePagosRealizados(idControlPago);
			controlPagoReporte.setControlPagoDetControlPagoList(listaDetControlPago);
			listaControlPagoGenerar.add(controlPagoReporte);
			NombreReporteType reporte = NombreReporteType.JR_REP_BOLETA_PAGOS_REALIZADOS_ALUMNO;
			ParametroReporteVO parametroReporteVO = new ParametroReporteVO(parametros, listaControlPagoGenerar, reporte, subreportes, null, true, "", "");
			parametroReporteVO.setFormato(TipoReporteGenerarType.PDF.getKey());
			parametroReporteVO.setUserName(usuario);
			parametroReporteVO.setFileName(fileName);
			codigoGeneradoReporte = generarReporteServiceImpl.obtenerParametroReporteBigMemory(parametroReporteVO);
			return codigoGeneradoReporte;
		} catch (Exception e) {
			log.error(e);
		}
		return codigoGeneradoReporte;
	}
	
	
	@Override
	public String generarReportePagoBase64(String idControlPago, String idAsociado, String usuario) throws Exception {
		String fileName = UUIDUtil.generarElementUUID();
		String codigoGeneradoReporte = fileName;
		UsuarioDTO resultado = null;
		Usuario resultadoEntity = null;
		try {
			AsociadoDTO asociado = new AsociadoDTO();
			asociado.setIdAsociado(idAsociado);		
			asociado = controladorAccionAsociado(asociado, AccionType.FIND_BY_ID);
			Map<String, Object> parametros = new HashMap<String, Object>();
			String[] subreportes;
			subreportes = new String[0];	 
			parametros.put("nombreAlumno", asociado.getNombre() + " " +  asociado.getApellidoPaterno() + " " + asociado.getApellidoMaterno());
			parametros.put("ruta", "");
			parametros.put("nroDni", asociado.getNrodoc());
			parametros.put("direccionAL", asociado.getDireccion());
			
			List<ControlPagoDTO> listaControlPagoGenerar = new ArrayList<ControlPagoDTO>();
			ControlPagoDTO controlPagoReporte = new ControlPagoDTO();
			controlPagoReporte.setIdControlPago(idControlPago);
			controlPagoReporte.setId(idAsociado);
			controlPagoReporte.setAsociado(asociado);

			controlPagoReporte = this.listarControlPago(controlPagoReporte).get(0); 
			resultadoEntity= this.usuarioServiceImpl.findUsuario(controlPagoReporte.getUsuarioCreacion());
			resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,UsuarioDTO.class);
			parametros.put("nombreCajero", resultado.getNombre()+ " " + resultado.getApellidoPaterno()+ " " +resultado.getApellidoMaterno());
			NumerosUtil numLetra= new NumerosUtil();
			parametros.put("montoLetra", numLetra.Convertir(controlPagoReporte.getMontoTotal().toString(), true));
			
			controlPagoReporte.setAsociado(asociado);
			List<DetControlPagoDTO> listaDetControlPago = verDetallePagosRealizados(idControlPago);
			controlPagoReporte.setControlPagoDetControlPagoList(listaDetControlPago);
			listaControlPagoGenerar.add(controlPagoReporte);
			NombreReporteType reporte = NombreReporteType.JR_REP_BOLETA_PAGOS_REALIZADOS_ALUMNO;
			ParametroReporteVO parametroReporteVO = new ParametroReporteVO(parametros, listaControlPagoGenerar, reporte, subreportes, null, true, "", "");
			parametroReporteVO.setFormato(TipoReporteGenerarType.PDF.getKey());
			parametroReporteVO.setUserName(usuario);
			parametroReporteVO.setFileName(fileName);
			codigoGeneradoReporte = generarReporteServiceImpl.obtenerParametroReporteBigMemory(parametroReporteVO);
			FileVO fileVO = new FileVO();
			fileVO.setRuta(ConstanteConfigUtil.RUTA_RECURSOS_BYTE_BUFFER + ConstanteConfigUtil.generarRuta(usuario) + codigoGeneradoReporte+ ".pdf");
			codigoGeneradoReporte = commonServiceLocal.obtenerImagenEncodeBase64(fileVO);
			return codigoGeneradoReporte;
		} catch (Exception e) {
			log.error(e);
		}
		return codigoGeneradoReporte;
	}

	@Override
	public ControlPagoDTO controladorAccionControlPago(ControlPagoDTO controlPago, AccionType accionType)
			throws Exception {
		ControlPagoDTO resultado = null;
		ControlPago resultadoEntity = null; 
		switch (accionType) {
			case CREAR:
				controlPago.setIdControlPago(this.controlPagoDaoImpl.generarIdControlPago());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(controlPago, ControlPago.class,"anhoSemestre@PK@","alumno@PK@","tipoDocSunat@PK@","itemByTipoMoneda@PK@","empresa@PK@");
				this.controlPagoDaoImpl.save(resultadoEntity);	
				resultado = controlPago;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(controlPago, ControlPago.class,"anhoSemestre@PK@","alumno@PK@","tipoDocSunat@PK@","itemByTipoMoneda@PK@","empresa@PK@");
				this.controlPagoDaoImpl.update(resultadoEntity);
				resultado = controlPago;	
				break;
				
			case ELIMINAR:
				//idControlPago=this.listarDetcontrolPagoDelectid(controlPago.getIdControlPago());
				//this.detControlPagoDaoImpl.deleteDetControlpago(idControlPago);
				resultadoEntity = this.controlPagoDaoImpl.find(ControlPago.class, controlPago.getIdControlPago());
				this.controlPagoDaoImpl.delete(resultadoEntity);
				resultado = controlPago;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.controlPagoDaoImpl.find(ControlPago.class, controlPago.getIdControlPago());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,ControlPagoDTO.class,"alumno","cliente");
				List<DetControlPago> listaTemo = detControlPagoDaoImpl.listarDetControlPago(controlPago.getIdControlPago());
				List<DetControlPagoDTO> ListarDetControlPago = new ArrayList<DetControlPagoDTO>();
				for (DetControlPago detControlPag : listaTemo) {
					DetControlPagoDTO detControlPagoDTO = TransferDataObjectUtil.transferObjetoEntityDTO(detControlPag, DetControlPagoDTO.class,"controlPago:{alumno;anhoSemestre}", "cuotaConcepto:{catalogoCuenta}");
					ListarDetControlPago.add(detControlPagoDTO);
				}
				resultado.setControlPagoDetControlPagoList(ListarDetControlPago);
				
				
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.controlPagoDaoImpl.findByNombre(controlPago),ControlPagoDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}

	@Override
	public DetControlPagoDTO controladorAccionDetControlPago(DetControlPagoDTO detControlPago, AccionType accionType)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DetControlPagoDTO> listarDetControlPago(DetControlPagoDTO detControlPago) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.detControlPagoDaoImpl.listarDetControlPago(detControlPago),DetControlPagoDTO.class);
	}

	@Override
	public int contarListarDetControlPago(DetControlPagoDTO detControlPago) {
		return  this.detControlPagoDaoImpl.contarListarDetControlPago(detControlPago);
	}

	@Override
	public List<DetControlPagoDTO> verDetallePagosRealizados(String idControlPago) throws Exception {
		List<DetControlPagoDTO> resultado = new ArrayList<DetControlPagoDTO>();
		List<DetControlPago> listaTemo = detControlPagoDaoImpl.listarDetControlPago(idControlPago);
		for (DetControlPago detControlPago : listaTemo) {
			DetControlPagoDTO detControlPagoDTO = TransferDataObjectUtil.transferObjetoEntityDTO(detControlPago, DetControlPagoDTO.class,"cuotaConcepto","controlPago");
			CuotaConceptoDTO cuotaConceptoDTO = TransferDataObjectUtil.transferObjetoEntityDTO(detControlPago.getCuotaConcepto(), CuotaConceptoDTO.class);
			detControlPagoDTO.setCuotaConceptoDTO(cuotaConceptoDTO);
			resultado.add(detControlPagoDTO);
		}
		listaTemo = null;
		return resultado;
	}

	@Override
	public List<DetControlPagoDTO> obtenerDetControlPagoAsociado(String idAsociado, String idAnio) throws Exception {
		List<DetControlPago> listaTemp = detControlPagoDaoImpl.listarDetControlPagoAlumno(idAsociado, idAnio);
		List<DetControlPagoDTO> ListarDetControlPago = new ArrayList<DetControlPagoDTO>();
		for (DetControlPago detControlPag : listaTemp) {
			DetControlPagoDTO detControlPagoDTO = TransferDataObjectUtil.transferObjetoEntityDTO(detControlPag, DetControlPagoDTO.class,"controlPago:{asociado}", "cuotaConcepto");
			
			ListarDetControlPago.add(detControlPagoDTO);
		}
		listaTemp = null;
		return ListarDetControlPago;
	}
	
	@Override
	public AnhioDTO controladorAccionAnhio(AnhioDTO anhio, AccionType accionType){
		AnhioDTO resultado = null;
		Anhio resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				anhio.setIdAnhio(this.anhioDaoImpl.generarIdAnhio());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(anhio, Anhio.class);
				this.anhioDaoImpl.save(resultadoEntity);	
				resultado = anhio;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(anhio, Anhio.class);
				this.anhioDaoImpl.update(resultadoEntity);
				resultado = anhio;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.anhioDaoImpl.find(Anhio.class, anhio.getIdAnhio());
				this.anhioDaoImpl.delete(resultadoEntity);
				resultado = anhio;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.anhioDaoImpl.find(Anhio.class, anhio.getIdAnhio());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,AnhioDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.anhioDaoImpl.findByNombre(anhio),AnhioDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<AnhioDTO> listarAnhio(AnhioDTO anhio){
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.anhioDaoImpl.listarAnhio(anhio),AnhioDTO.class);
	}
	@Override
	public int contarListarAnhio(AnhioDTO anhio){
		return  this.anhioDaoImpl.contarListarAnhio(anhio);
	}

	@Override
	public AnhioDTO obtenerAnhioByEstado(EstadoAnhoSemestreState estadoAnhioState) throws Exception {
		  return TransferDataObjectUtil.transferObjetoEntityDTO(anhioDaoImpl.obtenerAnioByEstado(estadoAnhioState),AnhioDTO.class);
	}
	
	@Override
	public void registrarAsistencia(List<AsistenciaDTO> listaAsistencia,String userName) throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		for (AsistenciaDTO objData : listaAsistencia) {
        	objData.setItemByDia(new ItemDTO());
			 if (!StringUtils.isNullOrEmpty(objData.getFechaHorario())) {
				    objData.setSelect(simpleDateFormat.format(objData.getFechaHorario()));
			        Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(objData.getSelect());
			        Calendar calendar = Calendar.getInstance();
			        calendar.setTime(inputDate);
			        String dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG,Locale.US).toUpperCase();
			        if (dayOfWeek.equals("SUNDAY")) {
			        	objData.getItemByDia().setIdItem(30l);
			        }if (dayOfWeek.equals("MONDAY")) {
			        	objData.getItemByDia().setIdItem(24l);
			        }if (dayOfWeek.equals("TUESDAY")) {
			        	objData.getItemByDia().setIdItem(25l);
			        }if (dayOfWeek.equals("WEDNESDAY")) {
			        	objData.getItemByDia().setIdItem(26l);
			        }if (dayOfWeek.equals("THURSDAY")) {
			        	objData.getItemByDia().setIdItem(27l);
			        }if (dayOfWeek.equals("FRIDAY")) {
			        	objData.getItemByDia().setIdItem(28l);
			        }if (dayOfWeek.equals("SATURDAY")) {
			        	objData.getItemByDia().setIdItem(29l);
			        }	
	            }
			Asistencia objAsistencia = TransferDataObjectUtil.transferObjetoEntity(objData, Asistencia.class, "asociado@PK@","personal@PK@","actividad@PK@","itemByDia@PK@");
			//objAsistencia.setFechaHorario(FechaUtil.obtenerFecha());
			if (!StringUtils.isNotNullOrBlank(objAsistencia.getIdAsistencia())) {
				objAsistencia.setIdAsistencia(asistenciaDaoImpl.generarCodigoAsistencia(objAsistencia));
				objAsistencia.setUsuarioCreacion(userName);
				objAsistencia.setFechaCreacion(FechaUtil.obtenerFecha());
				asistenciaDaoImpl.save(objAsistencia);
			} else {
				objAsistencia.setUsuarioModificacion(userName);
				objAsistencia.setFechaModificacion(FechaUtil.obtenerFecha());
				asistenciaDaoImpl.update(objAsistencia);
			}
		}
	}
	
	@Override
	public AsistenciaDTO controladorAccionAsistencia(AsistenciaDTO asistencia, AccionType accionType) throws Exception {
		AsistenciaDTO resultado = null;
		Asistencia resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				asistencia.setIdAsistencia(this.asistenciaDaoImpl.generarIdAsistencia());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(asistencia, Asistencia.class,"asociado@PK@","personal@PK@","actividad@PK@","itemByDia@PK@");
				this.asistenciaDaoImpl.save(resultadoEntity);	
				resultado = asistencia;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(asistencia, Asistencia.class,"asociado@PK@","personal@PK@","actividad@PK@","itemByDia@PK@");
				this.asistenciaDaoImpl.update(resultadoEntity);
				resultado = asistencia;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.asistenciaDaoImpl.find(Asistencia.class, asistencia.getIdAsistencia());
				this.asistenciaDaoImpl.delete(resultadoEntity);
				resultado = asistencia;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.asistenciaDaoImpl.find(Asistencia.class, asistencia.getIdAsistencia());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,AsistenciaDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.asistenciaDaoImpl.findByNombre(asistencia),AsistenciaDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<AsistenciaDTO> listarAsistencia(AsistenciaDTO asistencia) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.asistenciaDaoImpl.listarAsistencia(asistencia),AsistenciaDTO.class);
	}
	@Override
	public int contarListarAsistencia(AsistenciaDTO asistencia){
		return  this.asistenciaDaoImpl.contarListarAsistencia(asistencia);
	}
	
	@Override
	public List<AsistenciaDTO> obtenerAsistencia(boolean isConsulta,String idDetCargaLectiva,String idAlumno,Date fechaHorario,String userName) throws Exception {
		List<AsistenciaDTO> resultado = new ArrayList<AsistenciaDTO>();
		List<Asistencia> resultadoTemp = asistenciaDaoImpl.listarAsistencia(isConsulta,idDetCargaLectiva,idAlumno,null,fechaHorario);
					
		/*Map<String,String> asistenciaRegistroMap = 	new HashMap<String,String>();
		if (resultadoTemp != null) {
			for (Asistencia asistencia : resultadoTemp) {
				asistenciaRegistroMap.put(asistencia.getAsociado().getIdAsociado(), "");
			}
		} else {
			resultadoTemp = new ArrayList<Asistencia>();
		}
		if (!isConsulta) {
			List<DetMatricula> listaTemp = detMatriculaDaoImpl.obtenerDetalleMatricula(idDetCargaLectiva,idAlumno);
			if (listaTemp != null ) {
				for (DetMatricula objDet : listaTemp) {
					if (!asistenciaRegistroMap.containsKey(objDet.getMatricula().getAlumno().getIdAlumno())) {
						Asistencia asistenciaPersist = new Asistencia();
						asistenciaPersist.setAlumno(objDet.getMatricula().getAlumno());
						asistenciaPersist.setDetCargaLectiva( new DetCargaLectiva() );
						asistenciaPersist.getDetCargaLectiva().setIdDetCargaLectiva(idDetCargaLectiva);
						asistenciaPersist.setItemByDia(new Item(1L));
						asistenciaPersist.setEstado(EstadoAsistenciaType.DESIDIR.getKey());
						asistenciaPersist.setJustificacion("");
						asistenciaPersist.setFechaHorario(fechaHorario);
						if (!StringUtils.isNotNullOrBlank(fechaHorario)) {
							asistenciaPersist.setFechaHorario(FechaUtil.obtenerFecha());
					    } 
						asistenciaPersist.setUsuarioCreacion(userName);
						resultadoTemp.add((Asistencia)BeanUtils.cloneBean(asistenciaPersist));
					}
				}
		   }
		}*/
		//ADD ASIS
		for (Asistencia objData : resultadoTemp) {
			AsistenciaDTO asistencia = TransferDataObjectUtil.transferObjetoEntityDTO(objData, AsistenciaDTO.class,"asociado:{itemByTipoAsociado}","personal:{tipoPersonal}","actividad","itemByDia");  
			PersonalDTO personalDTO = TransferDataObjectUtil.transferObjetoEntityDTO(objData.getPersonal(), PersonalDTO.class,"tipoPersonal","tipoDocumento"); 
			FileVO fileVO = new FileVO();
			fileVO.setRuta(ConstanteConfigUtil.RUTA_RECURSOS_FOTO_ALUMN + ConstanteConfigUtil.SEPARADOR_FILE + "P_" +  objData.getPersonal().getFoto());			
			personalDTO.setFoto(commonServiceLocal.obtenerImagenEncodeBase64(fileVO));
			asistencia.setPersonal(personalDTO);
			resultado.add(asistencia);
		}
	  return resultado;
	}

	@Override
	public ActividadDTO controladorAccionActividad(ActividadDTO actividad, AccionType accionType) throws Exception {
		ActividadDTO resultado = null;
		Actividad resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				actividad.setIdActividad(this.actividadDaoImpl.generarIdActividad());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(actividad, Actividad.class,"responsable@PK@","tipoActividad@PK@","anhio@PK@");
				this.actividadDaoImpl.save(resultadoEntity);	
				resultado = actividad;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(actividad, Actividad.class,"responsable@PK@","tipoActividad@PK@","anhio@PK@");
				this.actividadDaoImpl.update(resultadoEntity);
				resultado = actividad;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.actividadDaoImpl.find(Actividad.class, actividad.getIdActividad());
				this.actividadDaoImpl.delete(resultadoEntity);
				resultado = actividad;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.actividadDaoImpl.find(Actividad.class, actividad.getIdActividad());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,ActividadDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.asistenciaDaoImpl.findByNombre(asistencia),AsistenciaDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}

	@Override
	public List<ActividadDTO> listarActividad(ActividadDTO Actividad) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.actividadDaoImpl.listarActividad(Actividad),ActividadDTO.class,"responsable","tipoActividad","anhio");
	}

	@Override
	public int contarListarActividad(ActividadDTO Actividad) {
		// TODO Auto-generated method stub
		return this.actividadDaoImpl.contarListarActividad(Actividad);
	}
	
	
	@Override
	public PlanPagosDTO findByPlanPagos(PlanPagosDTO planPagos) throws Exception {
		PlanPagosDTO resultado = new PlanPagosDTO();
		PlanPagos planPagosTemp = planPagosDaoImpl.findByPlanPagos(planPagos);
		resultado = TransferDataObjectUtil.transferObjetoEntityDTO(planPagosTemp,PlanPagosDTO.class,"asociado","anio"); 
		planPagosTemp = null;
		return resultado;
	}
	
	@Override
	public String generarPlanPagosMaisvo(String idCuotaConcepto,String userName) throws Exception {
		PuestoDTO puestoDTO = new PuestoDTO();
		puestoDTO.setEstado(EstadoGeneralState.ACTIVO.getKey()); 
		EstadoAnhoSemestreState estadoAnhoSemestreState = EstadoAnhoSemestreState.get(EstadoGeneralState.ACTIVO.getKey());
		AnhioDTO anioDTO = obtenerAnhioByEstado(estadoAnhoSemestreState);
		List<PuestoDTO> listadoPuestoDTO = listarPuesto(puestoDTO);
		for(PuestoDTO obj : listadoPuestoDTO) {
			PlanPagosDTO planPagos=new PlanPagosDTO();
			planPagos.setAsociado(obj.getAsociado());
			planPagos.setAnio(anioDTO);
			planPagos.setIdFiltro1(anioDTO);
			PlanPagosDTO planPagosTemp = findByPlanPagos(planPagos);
			 if(StringUtils.isNullOrEmpty(planPagosTemp)) {
				 List<DetPlanPagosDTO> planPagosDetPlanPagosList = new ArrayList<DetPlanPagosDTO>();
				 DetPlanPagosDTO detPlanPagosTemp = new DetPlanPagosDTO();
				 detPlanPagosTemp.setCuotaConcepto(new CuotaConceptoDTO());
				 detPlanPagosTemp.getCuotaConcepto().setIdCuotaConcepto(idCuotaConcepto);
				 detPlanPagosTemp.setCuota(obj.getPago());
				 detPlanPagosTemp.setFechaVencimiento(FechaUtil.obtenerFecha());
				 detPlanPagosTemp.setFlagFraccionado("N");
				 detPlanPagosTemp.setIdPuesto(obj.getIdPuesto());
				 planPagosDetPlanPagosList.add(detPlanPagosTemp);
				 
				 planPagosTemp = new PlanPagosDTO();
				 planPagosTemp.setAsociado(new AsociadoDTO());
				 planPagosTemp.getAsociado().setIdAsociado(obj.getAsociado().getIdAsociado());
				 planPagosTemp.setFechaCreacion(FechaUtil.obtenerFecha());
				 planPagosTemp.setAuthToken(userName);
				 planPagosTemp.setAnio(anioDTO);
				 planPagosTemp.setPlanPagosDetPlanPagosList(planPagosDetPlanPagosList);
				 registrarPlanPagos(planPagosTemp);
			}else {
				DetPlanPagos detPlanPagos = this.detPlanPagosDaoImpl.optenerByDetPlanPagos(planPagosTemp.getIdPlanPagos(), idCuotaConcepto, obj.getIdPuesto());
				 if(!StringUtils.isNotNullOrBlank(detPlanPagos.getIdDetPlanPagos())) {
					 List<DetPlanPagosDTO> planPagosDetPlanPagosList = new ArrayList<DetPlanPagosDTO>();
					 DetPlanPagosDTO detPlanPagosTemp = new DetPlanPagosDTO();
					 detPlanPagosTemp.setCuotaConcepto(new CuotaConceptoDTO());
					 detPlanPagosTemp.getCuotaConcepto().setIdCuotaConcepto(idCuotaConcepto);
					 detPlanPagosTemp.setCuota(obj.getPago());
					 detPlanPagosTemp.setFechaVencimiento(FechaUtil.obtenerFecha());
					 detPlanPagosTemp.setFlagFraccionado("N");
					 detPlanPagosTemp.setIdPuesto(obj.getIdPuesto());
					 planPagosDetPlanPagosList.add(detPlanPagosTemp);
					 
					 planPagosTemp.setAsociado(new AsociadoDTO());
					 planPagosTemp.getAsociado().setIdAsociado(obj.getAsociado().getIdAsociado());
					 planPagosTemp.setFechaCreacion(FechaUtil.obtenerFecha());
					 planPagosTemp.setAuthToken(userName);
					 planPagosTemp.setAnio(anioDTO);
					 planPagosTemp.setPlanPagosDetPlanPagosList(planPagosDetPlanPagosList);
					 registrarPlanPagos(planPagosTemp);
				 }
			}
		}
		return idCuotaConcepto;
	}
	
	@Override
	public List<DetallePlanPagosFiltroVO> obtenerDetallePlanPagosFiltroVO(DetallePlanPagosFiltroVO filtro) throws Exception {
		List<DetallePlanPagosFiltroVO> resultado = new ArrayList<DetallePlanPagosFiltroVO>();
		resultado = detPlanPagosDaoImpl.listarDetallePlanPagosFiltroVO(filtro);
		return resultado;
	}
	
	@Override
	public PuestoDTO optenerByPuesto(String idPuesto) throws Exception {
		PuestoDTO resultado = new PuestoDTO();
		Puesto puesto =  puestoDaoImpl.findByPuesto(idPuesto);
		resultado = TransferDataObjectUtil.transferObjetoEntityDTO(puesto,PuestoDTO.class,"itemCondicion");		
		puesto = null;
		return resultado;
	}
	
	@Override
	public List<ControlPagoDTO> obtenerListaCajaFiltroPago(VentaFiltroVO filtro) throws Exception {
		List<ControlPago> listaCon = controlPagoDaoImpl.obtenerListaCajaFiltroPago(filtro);
		BigDecimal Valor1 = new BigDecimal("0.00");
		for(ControlPago control1 : listaCon) {
			Valor1 = Valor1.add(control1.getMontoTotal());
		} 
		List<ControlPagoDTO> listaCont = new ArrayList<ControlPagoDTO>();
		for(ControlPago cont1 : listaCon ) { 
			ControlPagoDTO ventaDTO = TransferDataObjectUtil.transferObjetoEntityDTO(cont1,ControlPagoDTO.class,"tipoDocSunat","itemByTipoMoneda","asociado","anio");
			DetControlPagoDTO detalleControl = new DetControlPagoDTO();
			detalleControl.setId(ventaDTO.getIdControlPago()); 
			ventaDTO.setMontoTotalItem(Valor1);
			ventaDTO.setVarCantidad(detControlPagoDaoImpl.contarListarDetControlPago(detalleControl));
			listaCont.add(ventaDTO);
		}
		listaCon =  null;
		return listaCont;
		//return TransferDataObjectUtil.transferObjetoEntityDTOList(this.ventaDaoImpl.listarVenta(venta),VentaDTO.class,"tipoDocSunat","itemByTipoMoneda","cliente","pedido");
	}
	
	
	@Override
	public PersonalDTO findPersonalByNro(String nroDoc) throws Exception {
		PersonalDTO resultado = new PersonalDTO();
		Personal personal = personalDaoImpl.findPersonalByNro(nroDoc);
		resultado = TransferDataObjectUtil.transferObjetoEntityDTO(personal,PersonalDTO.class,"tipoDocumento","tipoPersonal");
		personal = null;
		return resultado;
	}

	@Override
	public AsociadoDTO findAsociadoByNro(String nroDoc) throws Exception {
		AsociadoDTO resultado = new AsociadoDTO();
		Asociado asociado = asociadoDaoImpl.findAsociadoByNro(nroDoc);
		resultado = TransferDataObjectUtil.transferObjetoEntityDTO(asociado,AsociadoDTO.class,"itemByTipoAsociado","tipoDocumento");
		asociado = null;
		return resultado;
	}

}