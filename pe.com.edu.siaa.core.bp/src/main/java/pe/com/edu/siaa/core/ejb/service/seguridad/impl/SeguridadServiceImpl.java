package pe.com.edu.siaa.core.ejb.service.seguridad.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import pe.com.edu.siaa.core.ejb.dao.seguridad.local.ConfiguracionMenuDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.EntidadDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.GrupoUsuarioDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.GrupoUsuarioMenuDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.GrupoUsuarioUsuarioDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.MenuDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.MenuPersonalizadoDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.PrivilegioDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.PrivilegioGrupoUsuarioDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.PrivilegioMenuDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.PrivilegioPersonalizadoDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.PropertiesDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.PropertiesLenguajeDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.RecuperarPasswordDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.SistemaDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.TipoUsuarioDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.UsuarioDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.UsuarioEntidadDaoLocal;
import pe.com.edu.siaa.core.ejb.factory.CollectionUtil;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.common.local.CommonServiceLocal;
import pe.com.edu.siaa.core.ejb.service.seguridad.local.SeguridadServiceLocal;
import pe.com.edu.siaa.core.ejb.service.util.FechaUtil;
import pe.com.edu.siaa.core.ejb.util.cache.AppAuthenticator;
import pe.com.edu.siaa.core.ejb.util.cryto.EncriptarUtil;
import pe.com.edu.siaa.core.model.dto.seguridad.ConfiguracionMenuDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.EntidadDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.GrupoUsuarioDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.GrupoUsuarioMenuDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.GrupoUsuarioUsuarioDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.MenuDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.MenuPersonalizadoDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.PrivilegioDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.PrivilegioGrupoUsuarioDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.PrivilegioMenuDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.PrivilegioPersonalizadoDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.PropertiesDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.PropertiesLenguajeDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.RecuperarPasswordDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.SistemaDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.TipoUsuarioDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.UsuarioDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.UsuarioEntidadDTO;
import pe.com.edu.siaa.core.model.estate.EstadoGeneralState;
import pe.com.edu.siaa.core.model.jpa.common.Item;
import pe.com.edu.siaa.core.model.jpa.seguridad.ConfiguracionMenu;
import pe.com.edu.siaa.core.model.jpa.seguridad.Entidad;
import pe.com.edu.siaa.core.model.jpa.seguridad.GrupoUsuario;
import pe.com.edu.siaa.core.model.jpa.seguridad.GrupoUsuarioMenu;
import pe.com.edu.siaa.core.model.jpa.seguridad.GrupoUsuarioUsuario;
import pe.com.edu.siaa.core.model.jpa.seguridad.Menu;
import pe.com.edu.siaa.core.model.jpa.seguridad.MenuPersonalizado;
import pe.com.edu.siaa.core.model.jpa.seguridad.Privilegio;
import pe.com.edu.siaa.core.model.jpa.seguridad.PrivilegioGrupoUsuario;
import pe.com.edu.siaa.core.model.jpa.seguridad.PrivilegioMenu;
import pe.com.edu.siaa.core.model.jpa.seguridad.PrivilegioPersonalizado;
import pe.com.edu.siaa.core.model.jpa.seguridad.Properties;
import pe.com.edu.siaa.core.model.jpa.seguridad.PropertiesLenguaje;
import pe.com.edu.siaa.core.model.jpa.seguridad.RecuperarPassword;
import pe.com.edu.siaa.core.model.jpa.seguridad.Sistema;
import pe.com.edu.siaa.core.model.jpa.seguridad.TipoUsuario;
import pe.com.edu.siaa.core.model.jpa.seguridad.Usuario;
import pe.com.edu.siaa.core.model.jpa.seguridad.UsuarioEntidad;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.model.type.TipoComponenteType;
import pe.com.edu.siaa.core.model.util.ConstanteConfigUtil;
import pe.com.edu.siaa.core.model.util.ObjectUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;
import pe.com.edu.siaa.core.model.vo.ConfiguracionMenuVO;
import pe.com.edu.siaa.core.model.vo.EgresadoFiltroVO;
import pe.com.edu.siaa.core.model.vo.FileVO;


/**
 * La Class SeguridadServiceImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 13:43:23 COT 2017
 * @since SIAA-CORE 2.1
 */
 @Stateless
 @EJB(name = "java:app/SeguridadService", beanInterface = SeguridadServiceLocal.class)
 @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class SeguridadServiceImpl implements SeguridadServiceLocal{
	
	private static final long LENGUAJE_SPANISH = 526L;

	/** El servicio grupo usuario usuario dao impl. */
	@EJB
	private GrupoUsuarioUsuarioDaoLocal grupoUsuarioUsuarioDaoImpl; 
	
	/** El servicio grupo usuario dao impl. */
	@EJB
	private GrupoUsuarioDaoLocal grupoUsuarioDaoImpl; 
	
	/** El servicio privilegio menu dao impl. */
	@EJB
	private PrivilegioMenuDaoLocal privilegioMenuDaoImpl; 
	
	/** El servicio properties lenguaje dao impl. */
	@EJB
	private PropertiesLenguajeDaoLocal propertiesLenguajeDaoImpl; 
	
	/** El servicio menu personalizado dao impl. */
	@EJB
	private MenuPersonalizadoDaoLocal menuPersonalizadoDaoImpl; 
	
	/** El servicio privilegio personalizado dao impl. */
	@EJB
	private PrivilegioPersonalizadoDaoLocal privilegioPersonalizadoDaoImpl; 	
	
	/** El servicio persona dao impl. */
	@EJB
	private UsuarioDaoLocal usuarioDaoImpl; 
	
	/** El servicio properties dao impl. */
	@EJB
	private PropertiesDaoLocal propertiesDaoImpl; 
	
	/** El servicio privilegio dao impl. */
	@EJB
	private PrivilegioDaoLocal privilegioDaoImpl; 
	
	/** El servicio menu dao impl. */
	@EJB
	private MenuDaoLocal menuDaoImpl; 
		
	/** El servicio configuracion menu dao impl. */
	@EJB
	private ConfiguracionMenuDaoLocal configuracionMenuDaoImpl; 
	
	/** El servicio sistema dao impl. */
	@EJB
	private SistemaDaoLocal sistemaDaoImpl; 
		
	/** El servicio privilegio grupo usuario dao impl. */
	@EJB
	private PrivilegioGrupoUsuarioDaoLocal privilegioGrupoUsuarioDaoImpl; 
	
	/** El servicio usuario entidad dao impl. */
	@EJB
	private UsuarioEntidadDaoLocal usuarioEntidadDaoImpl;
	
	/** El servicio grupo usuario menu dao impl. */
	@EJB
	private GrupoUsuarioMenuDaoLocal grupoUsuarioMenuDaoImpl; 

	/** El servicio entidad dao impl. */
	@EJB
	private EntidadDaoLocal entidadDaoImpl; 
	
	/** El servicio tipo usuario dao impl. */
	@EJB
	private TipoUsuarioDaoLocal tipoUsuarioDaoImpl; 
	
	@EJB
	private RecuperarPasswordDaoLocal recuperarPasswordDaoImpl;
	
	@EJB
	private transient CommonServiceLocal commonServiceLocal;

	@Override
	public UsuarioDTO obtenerUsuarioByCodigoExterno(String codigoExterno) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTO(this.usuarioDaoImpl.obtenerUsuarioByCodigoExterno(codigoExterno),UsuarioDTO.class,"tipoUsuario");
	}
	
	@Override
	public EntidadDTO controladorAccionEntidad(EntidadDTO entidad, AccionType accionType) throws Exception {
		EntidadDTO resultado = null;
		Entidad resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				entidad.setIdEntidad(this.entidadDaoImpl.generarIdEntidad());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(entidad, Entidad.class,"itemByTipoVia@PK@","itemByZona@PK@");
				this.entidadDaoImpl.save(resultadoEntity);	
				resultado = entidad;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(entidad, Entidad.class,"itemByTipoVia@PK@","itemByZona@PK@");
				this.entidadDaoImpl.update(resultadoEntity);
				resultado = entidad;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.entidadDaoImpl.find(Entidad.class, entidad.getIdEntidad());
				this.entidadDaoImpl.delete(resultadoEntity);
				resultado = entidad;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.entidadDaoImpl.find(Entidad.class, entidad.getIdEntidad());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,EntidadDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.entidadDaoImpl.findByNombre(entidad),EntidadDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<EntidadDTO> listarEntidad(EntidadDTO entidad) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.entidadDaoImpl.listarEntidad(entidad),EntidadDTO.class,"itemByTipoVia","itemByZona");
	}
	@Override
	public int contarListarEntidad(EntidadDTO entidad){
		return  this.entidadDaoImpl.contarListarEntidad(entidad);
	}
	@Override
	public TipoUsuarioDTO controladorAccionTipoUsuario(TipoUsuarioDTO tipoUsuario, AccionType accionType) throws Exception {
		TipoUsuarioDTO resultado = null;
		TipoUsuario resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				tipoUsuario.setIdTipoUsuario(this.tipoUsuarioDaoImpl.generarIdTipoUsuario());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(tipoUsuario, TipoUsuario.class);
				this.tipoUsuarioDaoImpl.save(resultadoEntity);	
				resultado = tipoUsuario;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(tipoUsuario, TipoUsuario.class);
				this.tipoUsuarioDaoImpl.update(resultadoEntity);
				resultado = tipoUsuario;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.tipoUsuarioDaoImpl.find(TipoUsuario.class, tipoUsuario.getIdTipoUsuario());
				this.tipoUsuarioDaoImpl.delete(resultadoEntity);
				resultado = tipoUsuario;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.tipoUsuarioDaoImpl.find(TipoUsuario.class, tipoUsuario.getIdTipoUsuario());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,TipoUsuarioDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.tipoUsuarioDaoImpl.findByNombre(tipoUsuario),TipoUsuarioDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<TipoUsuarioDTO> listarTipoUsuario(TipoUsuarioDTO tipoUsuario) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.tipoUsuarioDaoImpl.listarTipoUsuario(tipoUsuario),TipoUsuarioDTO.class);
	}
	@Override
	public int contarListarTipoUsuario(TipoUsuarioDTO tipoUsuario){
		return  this.tipoUsuarioDaoImpl.contarListarTipoUsuario(tipoUsuario);
	}
	@Override
	public UsuarioDTO controladorAccionUsuario(UsuarioDTO usuario, AccionType accionType) throws Exception {
		UsuarioDTO resultado = null;
		Usuario resultadoEntity = null;
		UsuarioEntidad objPersist = null;
		EgresadoFiltroVO filtroLi =new EgresadoFiltroVO();
		switch (accionType) {
			case CREAR:
				usuario.setIdUsuario(this.usuarioDaoImpl.generarIdUsuario());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(usuario, Usuario.class,"tipoUsuario@PK@");
				if (StringUtils.isNotNullOrBlank(resultadoEntity.getUserPassword())) {
					resultadoEntity.setUserPassword(EncriptarUtil.encriptar(resultadoEntity.getUserPassword()));
			    } else {
			    	resultadoEntity.setUserPassword(EncriptarUtil.encriptar(resultadoEntity.getUserName()));
			    }
				this.usuarioDaoImpl.save(resultadoEntity);	
				resultado = usuario;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(usuario, Usuario.class,"tipoUsuario@PK@");
			    if (StringUtils.isNotNullOrBlank(resultadoEntity.getUserPassword())) {
			    	resultadoEntity.setUserPassword(EncriptarUtil.encriptar(resultadoEntity.getUserPassword()));
			    } else {
			    	resultadoEntity.setUserPassword(usuario.getUserPasswordEncriptado());
			    }
				this.usuarioDaoImpl.update(resultadoEntity);
				resultado = usuario;	
				break;
				
			case ELIMINAR:
				filtroLi.setIdUsuario(usuario.getIdUsuario());
				UsuarioEntidadDTO codigoexterno = this.listarUsuarioEntidadDelect(filtroLi);
				System.out.println("aqui:: " +codigoexterno.getIdUsuarioEntidad());
				objPersist = this.usuarioEntidadDaoImpl.find(UsuarioEntidad.class,codigoexterno.getIdUsuarioEntidad());
				this.usuarioEntidadDaoImpl.delete(objPersist);
				
				resultadoEntity = this.usuarioDaoImpl.find(Usuario.class, usuario.getIdUsuario());
				this.usuarioDaoImpl.delete(resultadoEntity);
				resultado = usuario;
				break;
				/*resultadoEntity = this.usuarioDaoImpl.find(Usuario.class, usuario.getIdUsuario());
				this.usuarioDaoImpl.delete(resultadoEntity);
				resultado = usuario;
				break;*/
				
			case FIND_BY_ID:
				resultadoEntity = this.usuarioDaoImpl.find(Usuario.class, usuario.getIdUsuario());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,UsuarioDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.usuarioDaoImpl.findByNombre(usuario),UsuarioDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public UsuarioDTO integracionUsuario(UsuarioDTO usuario,AccionType accionType) throws Exception {
		UsuarioDTO resultado = null;
		Usuario resultadoEntity = null;
		Usuario usuarioEntity = null;
		UsuarioEntidadDTO obj = null;
		EgresadoFiltroVO filtro =new EgresadoFiltroVO();
		UsuarioEntidad objPersist = null;
		if (!AccionType.CREAR.equals(accionType)) {
			usuarioEntity = this.usuarioDaoImpl.obtenerUsuarioByCodigoExterno(usuario.getCodigoExterno());
		}
		switch (accionType) {
			case CREAR:
				usuario.setIdUsuario(this.usuarioDaoImpl.generarIdUsuario());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(usuario, Usuario.class,"tipoUsuario@PK@");
				this.usuarioDaoImpl.save(resultadoEntity);	
				//Inicio creando usuario
				UsuarioEntidad usuarioEntidad = new UsuarioEntidad();
				usuarioEntidad.setIdUsuarioEntidad(this.usuarioEntidadDaoImpl.generarIdUsuarioEntidad());
				usuarioEntidad.setEntidad(new Entidad());
				usuarioEntidad.getEntidad().setIdEntidad(usuario.getIdEntidadSelect());
				usuarioEntidad.setUsuario(resultadoEntity);
				usuarioEntidad.setEstado(EstadoGeneralState.ACTIVO.getKey());
				this.usuarioEntidadDaoImpl.save(usuarioEntidad);
				//Fin creando usuario
				resultado = usuario;
				break;				
			case MODIFICAR:
				if(usuarioEntity.getIdUsuario() !=null){
					usuario.setIdUsuario(usuarioEntity.getIdUsuario());
					usuario.setTipoUsuario(new TipoUsuarioDTO());
					usuario.getTipoUsuario().setIdTipoUsuario(usuarioEntity.getTipoUsuario().getIdTipoUsuario());
					usuario.setEstado(usuarioEntity.getEstado());
				    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(usuario, Usuario.class,"tipoUsuario@PK@");
					this.usuarioDaoImpl.update(resultadoEntity);
					resultado = usuario;	
					break;
				}else {
					usuario.setIdUsuario(this.usuarioDaoImpl.generarIdUsuario());
					resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(usuario, Usuario.class,"tipoUsuario@PK@");
					this.usuarioDaoImpl.save(resultadoEntity);	
					//Inicio creando usuario
					UsuarioEntidad usuarioEntidade = new UsuarioEntidad();
					usuarioEntidade.setIdUsuarioEntidad(this.usuarioEntidadDaoImpl.generarIdUsuarioEntidad());
					usuarioEntidade.setEntidad(new Entidad());
					usuarioEntidade.getEntidad().setIdEntidad(usuario.getIdEntidadSelect());
					usuarioEntidade.setUsuario(resultadoEntity);
					usuarioEntidade.setEstado(EstadoGeneralState.ACTIVO.getKey());
					this.usuarioEntidadDaoImpl.save(usuarioEntidade);
					//Fin creando usuario
					resultado = usuario;
					break;	
				}
			
				
			case ELIMINAR:
				filtro.setIdFiltro(usuario.getCodigoExterno());
				obj=this.listarUsuarioEntidadDelect(filtro);
				if(usuarioEntity!=null) {
				objPersist = this.usuarioEntidadDaoImpl.find(UsuarioEntidad.class,obj.getIdUsuarioEntidad());
				this.usuarioEntidadDaoImpl.delete(objPersist);
				resultadoEntity = usuarioEntity;
				this.usuarioDaoImpl.delete(resultadoEntity);
				
				}
				resultado = usuario;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = usuarioEntity;
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,UsuarioDTO.class);
				break;
				
			default:
				break;
		}
		return resultado;
	}
	@Override
	public List<UsuarioDTO> listarUsuario(UsuarioDTO usuario) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.usuarioDaoImpl.listarUsuario(usuario),UsuarioDTO.class,"tipoUsuario");
	}
	@Override
	public int contarListarUsuario(UsuarioDTO usuario){
		return  this.usuarioDaoImpl.contarListarUsuario(usuario);
	}
	 
	@Override
	  public List<PropertiesDTO> obtenerPropertiesLenguaje(PropertiesDTO propertiesFiltro) throws Exception { 
		  return listarProperties(propertiesFiltro);
	  }
	
	 @Override
	  public List<ConfiguracionMenuVO> obtenerConfiguracionMenu(Long idMenu) throws Exception {
		  List<ConfiguracionMenuVO> resultado = new ArrayList<ConfiguracionMenuVO>();
		  Map<Long,List<ConfiguracionMenuDTO>> resultadoMap = new HashMap<Long, List<ConfiguracionMenuDTO>>();
		  List<ConfiguracionMenu> listaConfiguracionMenuTemp = configuracionMenuDaoImpl.obtenerConfiguracionMenu(idMenu);
		  List<ConfiguracionMenuDTO> listaConfiguracionMenu = TransferDataObjectUtil.transferObjetoEntityDTOList(listaConfiguracionMenuTemp, ConfiguracionMenuDTO.class,"itemByComponente","properties");
		  List<Long> listaIdProperties = new ArrayList<Long>();
		  for (ConfiguracionMenuDTO configuracionMenu : listaConfiguracionMenu) {
			  listaIdProperties.add(configuracionMenu.getProperties().getIdProperties());
		  }
		  if (listaConfiguracionMenu != null) {
			  for (ConfiguracionMenuDTO configuracionMenu : listaConfiguracionMenu) {
				  if (!resultadoMap.containsKey(configuracionMenu.getItemByComponente().getIdItem())) {
					  List<ConfiguracionMenuDTO> value = new ArrayList<ConfiguracionMenuDTO>();
					  value.add(configuracionMenu);
					  resultadoMap.put(configuracionMenu.getItemByComponente().getIdItem(), value);
					  ConfiguracionMenuVO configuracionMenuDTO = new ConfiguracionMenuVO();
					  configuracionMenuDTO.setId(configuracionMenu.getItemByComponente().getIdItem());
					  configuracionMenuDTO.setCodigo(configuracionMenu.getItemByComponente().getCodigo());
					  configuracionMenuDTO.setDescripcion(configuracionMenu.getItemByComponente().getNombre());
					  resultado.add(configuracionMenuDTO);
				  } else  {
					  List<ConfiguracionMenuDTO> value = resultadoMap.get(configuracionMenu.getItemByComponente().getIdItem());
					  value.add(configuracionMenu);
					  resultadoMap.put(configuracionMenu.getItemByComponente().getIdItem(), value);
				  }
			}
			//cargando lista componente con su lista configuracion menu
			  for (ConfiguracionMenuVO configuracionMenu : resultado) {
				  TipoComponenteType componenteType = TipoComponenteType.get(configuracionMenu.getCodigo());
				   configuracionMenu.setListaConfiguracionMenus(resultadoMap.get(configuracionMenu.getId()));
				   for (ConfiguracionMenuDTO objConfi : configuracionMenu.getListaConfiguracionMenus()) {
					    if (componenteType != null) {
						switch (componenteType) {
							case LABEL:
								objConfi.setShowRequired(false);
								objConfi.setShowReadonly(false);
								objConfi.setShowRendered(true);
								objConfi.setShowDisabled(false);
								break;
							case INPUT:
								objConfi.setShowRequired(true);
								objConfi.setShowReadonly(true);
								objConfi.setShowRendered(true);
								objConfi.setShowDisabled(true);
								break;
		
							case BUTTON:
								objConfi.setShowRequired(false);
								objConfi.setShowReadonly(false);
								objConfi.setShowRendered(true);
								objConfi.setShowDisabled(true);
								break;
		
							default:
								break;
						}
					}
				}
			  }  
		  }
		  
		  return resultado;
	  }
	
	 /**
	   * Obtener properties lenguaje map.
	   *
	   * @param listaIdProperties el lista id properties
	   * @return the map
	   * @throws Exception the exception
	   */
	  private Map<String,Map<String,String>> obtenerPropertiesLenguajeMap(List<Long> listaIdProperties) throws Exception { 
		  return propertiesLenguajeDaoImpl.obtenerPropertiesLenguajeMap(listaIdProperties);
	  }
	@Override
	public List<MenuDTO> obtenerMenuUsuario(String idUsuario) throws Exception {
		List<MenuDTO> resultado = new ArrayList<MenuDTO>();
		//resultado = menuDaoImpl.obtenerMenuUsuario(usuario);
        List<MenuDTO> listaMenu = TransferDataObjectUtil.transferObjetoEntityDTOList(menuDaoImpl.obtenerMenuUsuario(idUsuario),MenuDTO.class,"sistema","menuPadre");
		if (listaMenu != null) {
			resultado = generarMenu(listaMenu);
		}
		return resultado;
	}
	
	/**
	 * Generar menu.
	 *
	 * @param listaMenu el lista menu
	 * @return the list
	 */
	private List<MenuDTO> generarMenu(List<MenuDTO> listaMenu) {
		List<MenuDTO> resultado = new ArrayList<MenuDTO>();
		for (MenuDTO menu : listaMenu) {
			if (menu.getMenuPadre().getIdMenu() == null) {
				menu.setMenuHijos(generarSubMenu(listaMenu,menu.getIdMenu()));
				resultado.add(menu);
			}
		}
		return resultado;
	}
	
	/**
	 * Generar sub menu.
	 *
	 * @param listaMenu el lista menu
	 * @param idMenuPadre el id menu padre
	 * @return the list
	 */
	private List<MenuDTO> generarSubMenu(List<MenuDTO> listaMenu, Long idMenuPadre) {
		List<MenuDTO> resultado = new ArrayList<MenuDTO>();
		for (MenuDTO menu : listaMenu) {
			if (menu.getMenuPadre().getIdMenu() != null) {
				if (menu.getMenuPadre().getIdMenu().equals(idMenuPadre)) {
					menu.setMenuHijos(generarSubMenu(listaMenu,menu.getIdMenu()));
					resultado.add(menu);
				}
			}
		}
		return resultado;
	}
	
	@Override
	public Map<String,Map<String,String>> obtenerPropertiesLenguajeAllMap() {
	  return propertiesLenguajeDaoImpl.obtenerPropertiesLenguajeAllMap();
	}
	
	@Override
	public UsuarioDTO validarLogin(String userName, String userPassword) throws Exception {
		UsuarioDTO resultado = TransferDataObjectUtil.transferObjetoEntityDTO(usuarioDaoImpl.validarLogin(userName, EncriptarUtil.encriptar(userPassword)), UsuarioDTO.class,"tipoUsuario");
		if (resultado != null && resultado.getIdUsuario() != null) {
			resultado.setPrivilegiosMap(obtenerPrivilegiosUsuario(resultado.getIdUsuario()));
			resultado.setListaMenu(obtenerMenuUsuario(resultado.getIdUsuario()));
			FileVO fileVO = new FileVO();
			fileVO.setRuta(ConstanteConfigUtil.RUTA_RECURSOS_FOTO_ALUMN + ConstanteConfigUtil.SEPARADOR_FILE + "086" +  resultado.getFoto());
			resultado.setFoto(commonServiceLocal.obtenerImagenEncodeBase64(fileVO));
			
			List<String> listaIdUsuario = new ArrayList<String>();
			listaIdUsuario.add(resultado.getIdUsuario());
			//Para saber si es un usuario que tiene mas de una empresa
			Map<String,Integer> cantidadEmpresaAsignadoMap =  usuarioEntidadDaoImpl.obtenerCantidadEntidadAsignadaMap(listaIdUsuario );
			if (cantidadEmpresaAsignadoMap != null && cantidadEmpresaAsignadoMap.containsKey(resultado.getIdUsuario())) {
				resultado.setVarCantidad(cantidadEmpresaAsignadoMap.get(resultado.getIdUsuario()));
				if (resultado.getVarCantidad() == 1) {
					UsuarioEntidadDTO usuarioEntidadFiltro = new UsuarioEntidadDTO();
					usuarioEntidadFiltro.setId(resultado.getIdUsuario());
					UsuarioEntidad usuarioEntidad = usuarioEntidadDaoImpl.listarUsuarioEntidad(usuarioEntidadFiltro).get(0);
					resultado.setEntidad(TransferDataObjectUtil.transferObjetoEntityDTO(usuarioEntidad.getEntidad(),EntidadDTO.class));
				}
			}
		}
		return resultado;
	}
	private Map<String,Boolean> obtenerPrivilegiosUsuario(String idUsuario) throws Exception {
		  Map<String,Boolean> resultado = new HashMap<String, Boolean>();
		  List<Privilegio> listaPrivilegio = privilegioDaoImpl.obtenerPrivilegioByUsuario(idUsuario);
		  if (listaPrivilegio != null) {
			  for (Privilegio privilegio : listaPrivilegio) {
				  resultado.put(privilegio.getAcronimo(), true);
			}
		  }
		  return resultado;
	  }
	@Override
	public void asociarGrupoUsuarioByUsuario(List<GrupoUsuarioDTO> listaGrupoUsuario,String userName)  throws Exception {
		if (!CollectionUtil.isEmpty(listaGrupoUsuario)) {
			GrupoUsuarioDTO objData = listaGrupoUsuario.get(0);
			String idUsuario = objData.getId() + "";
			Map<Long,GrupoUsuarioUsuarioDTO> listaGrupoMapBD = listarGrupoUsuarioUsuarioMap(idUsuario,null);
			for (GrupoUsuarioDTO grupoUsuarioDTO : listaGrupoUsuario) {
				Long idGrupoUsuario = grupoUsuarioDTO.getIdGrupoUsuario();
				if (grupoUsuarioDTO.isChecked()) {
					if (!listaGrupoMapBD.containsKey(idGrupoUsuario)) {
						GrupoUsuarioUsuario grupoUsuarioUsuario = new GrupoUsuarioUsuario();
						grupoUsuarioUsuario.setIdGrupoUsuarioUsuario(this.grupoUsuarioUsuarioDaoImpl.generarIdGrupoUsuarioUsuario());
						grupoUsuarioUsuario.setEstado(EstadoGeneralState.ACTIVO.getKey());
						parseGrupoUsuarioUsuario(grupoUsuarioUsuario, idUsuario, userName, idGrupoUsuario, true);
						this.grupoUsuarioUsuarioDaoImpl.save(grupoUsuarioUsuario);	
					} else {
						GrupoUsuarioUsuarioDTO grupoUsuarioUsuario = listaGrupoMapBD.get(idGrupoUsuario);
						if (!(EstadoGeneralState.ACTIVO.getKey().equalsIgnoreCase(grupoUsuarioUsuario.getEstado()))) {
							grupoUsuarioUsuario.setEstado(EstadoGeneralState.ACTIVO.getKey());
							GrupoUsuarioUsuario resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(grupoUsuarioUsuario, GrupoUsuarioUsuario.class);
							parseGrupoUsuarioUsuario(resultadoEntity, idUsuario, userName, idGrupoUsuario, false);
							this.grupoUsuarioUsuarioDaoImpl.update(resultadoEntity);
						}
					}
				} else {
					if (listaGrupoMapBD.containsKey(grupoUsuarioDTO.getIdGrupoUsuario())) {
						GrupoUsuarioUsuarioDTO grupoUsuarioUsuario = listaGrupoMapBD.get(idGrupoUsuario);
						if (!(EstadoGeneralState.INACTIVO.getKey().equalsIgnoreCase(grupoUsuarioUsuario.getEstado()))) {
							grupoUsuarioUsuario.setEstado(EstadoGeneralState.INACTIVO.getKey());
							GrupoUsuarioUsuario resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(grupoUsuarioUsuario, GrupoUsuarioUsuario.class);
							this.grupoUsuarioUsuarioDaoImpl.update(resultadoEntity);
						}
					}
				}
			}
		}
	}
	private GrupoUsuarioUsuario parseGrupoUsuarioUsuario(GrupoUsuarioUsuario grupoUsuarioUsuario,String idUsuario,String userName,Long idGrupoUsuario,boolean isNew ) {
		grupoUsuarioUsuario.setUsuario(new Usuario());
		grupoUsuarioUsuario.getUsuario().setIdUsuario(idUsuario);
		grupoUsuarioUsuario.setGrupoUsuario(new GrupoUsuario());
		if (isNew) {
			grupoUsuarioUsuario.setFechaCreacion(FechaUtil.obtenerFecha());
			grupoUsuarioUsuario.setUsuarioCreacion(userName);
		}
		grupoUsuarioUsuario.getGrupoUsuario().setIdGrupoUsuario(idGrupoUsuario);
		return grupoUsuarioUsuario;
	}
	@Override
	public Map<Long,GrupoUsuarioUsuarioDTO> listarGrupoUsuarioUsuarioMap(String idUsuario,String estado) throws Exception {
		GrupoUsuarioUsuarioDTO grupoUsuarioUsuario = new GrupoUsuarioUsuarioDTO();
		grupoUsuarioUsuario.setId(idUsuario);
		grupoUsuarioUsuario.setEstado(estado);
		Map<Long,GrupoUsuarioUsuarioDTO> grupoUsuarioMap = new HashMap<Long,GrupoUsuarioUsuarioDTO>();
		List<GrupoUsuarioUsuario> listaTem = this.grupoUsuarioUsuarioDaoImpl.listarGrupoUsuarioUsuario(grupoUsuarioUsuario);
		for (GrupoUsuarioUsuario objData : listaTem) {
			grupoUsuarioMap.put(objData.getGrupoUsuario().getIdGrupoUsuario(), TransferDataObjectUtil.transferObjetoEntityDTO(objData,GrupoUsuarioUsuarioDTO.class));
		}
		return grupoUsuarioMap;
	}
	
	
	@Override
	public GrupoUsuarioDTO controladorAccionGrupoUsuario(GrupoUsuarioDTO grupoUsuario, AccionType accionType) throws Exception {
		GrupoUsuarioDTO resultado = null;
		GrupoUsuario resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				grupoUsuario.setIdGrupoUsuario(this.grupoUsuarioDaoImpl.generarIdGrupoUsuario());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(grupoUsuario, GrupoUsuario.class);
				this.grupoUsuarioDaoImpl.save(resultadoEntity);	
				resultado = grupoUsuario;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(grupoUsuario, GrupoUsuario.class);
				this.grupoUsuarioDaoImpl.update(resultadoEntity);
				resultado = grupoUsuario;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.grupoUsuarioDaoImpl.find(GrupoUsuario.class, grupoUsuario.getIdGrupoUsuario());
				this.grupoUsuarioDaoImpl.delete(resultadoEntity);
				resultado = grupoUsuario;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.grupoUsuarioDaoImpl.find(GrupoUsuario.class, grupoUsuario.getIdGrupoUsuario());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,GrupoUsuarioDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.grupoUsuarioDaoImpl.findByNombre(grupoUsuario),GrupoUsuarioDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<GrupoUsuarioDTO> listarGrupoUsuario(GrupoUsuarioDTO grupoUsuario) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.grupoUsuarioDaoImpl.listarGrupoUsuario(grupoUsuario),GrupoUsuarioDTO.class);
	}
	@Override
	public int contarListarGrupoUsuario(GrupoUsuarioDTO grupoUsuario){
		return  this.grupoUsuarioDaoImpl.contarListarGrupoUsuario(grupoUsuario);
	}
	
	@Override
	public PrivilegioMenuDTO controladorAccionPrivilegioMenu(PrivilegioMenuDTO privilegioMenu, AccionType accionType) throws Exception {
		PrivilegioMenuDTO resultado = null;
		PrivilegioMenu resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				privilegioMenu.setIdPrivilegioMenu(this.privilegioMenuDaoImpl.generarIdPrivilegioMenu());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(privilegioMenu, PrivilegioMenu.class,"menu@PK@","privilegio@PK@");
				this.privilegioMenuDaoImpl.save(resultadoEntity);	
				resultado = privilegioMenu;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(privilegioMenu, PrivilegioMenu.class,"menu@PK@","privilegio@PK@");
				this.privilegioMenuDaoImpl.update(resultadoEntity);
				resultado = privilegioMenu;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.privilegioMenuDaoImpl.find(PrivilegioMenu.class, privilegioMenu.getIdPrivilegioMenu());
				this.privilegioMenuDaoImpl.delete(resultadoEntity);
				resultado = privilegioMenu;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.privilegioMenuDaoImpl.find(PrivilegioMenu.class, privilegioMenu.getIdPrivilegioMenu());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,PrivilegioMenuDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.privilegioMenuDaoImpl.findByNombre(privilegioMenu),PrivilegioMenuDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<PrivilegioMenuDTO> listarPrivilegioMenu(PrivilegioMenuDTO privilegioMenu) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.privilegioMenuDaoImpl.listarPrivilegioMenu(privilegioMenu),PrivilegioMenuDTO.class,"menu","privilegio");
	}
	@Override
	public int contarListarPrivilegioMenu(PrivilegioMenuDTO privilegioMenu){
		return  this.privilegioMenuDaoImpl.contarListarPrivilegioMenu(privilegioMenu);
	}
	
	@Override
	public PropertiesLenguajeDTO controladorAccionPropertiesLenguaje(PropertiesLenguajeDTO propertiesLenguaje, AccionType accionType) throws Exception {
		PropertiesLenguajeDTO resultado = null;
		PropertiesLenguaje resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				propertiesLenguaje.setIdPropertiesLenguaje(this.propertiesLenguajeDaoImpl.generarIdPropertiesLenguaje());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(propertiesLenguaje, PropertiesLenguaje.class,"itemByLenguaje@PK@","properties@PK@");
				this.propertiesLenguajeDaoImpl.save(resultadoEntity);	
				resultado = propertiesLenguaje;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(propertiesLenguaje, PropertiesLenguaje.class,"itemByLenguaje@PK@","properties@PK@");
				this.propertiesLenguajeDaoImpl.update(resultadoEntity);
				resultado = propertiesLenguaje;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.propertiesLenguajeDaoImpl.find(PropertiesLenguaje.class, propertiesLenguaje.getIdPropertiesLenguaje());
				this.propertiesLenguajeDaoImpl.delete(resultadoEntity);
				resultado = propertiesLenguaje;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.propertiesLenguajeDaoImpl.find(PropertiesLenguaje.class, propertiesLenguaje.getIdPropertiesLenguaje());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,PropertiesLenguajeDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.propertiesLenguajeDaoImpl.findByNombre(propertiesLenguaje),PropertiesLenguajeDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<PropertiesLenguajeDTO> listarPropertiesLenguaje(PropertiesLenguajeDTO propertiesLenguaje) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.propertiesLenguajeDaoImpl.listarPropertiesLenguaje(propertiesLenguaje),PropertiesLenguajeDTO.class);
	}
	@Override
	public int contarListarPropertiesLenguaje(PropertiesLenguajeDTO propertiesLenguaje){
		return  this.propertiesLenguajeDaoImpl.contarListarPropertiesLenguaje(propertiesLenguaje);
	}
	
	@Override
	public void asociarMenuPersonalizadoByUsuario(List<MenuDTO> listaMenu,String userName) throws Exception {
		if (!CollectionUtil.isEmpty(listaMenu)) {
			MenuDTO objData = listaMenu.get(0);
			String idUsuario = objData.getId() + "";
			Map<Long,MenuPersonalizadoDTO> listaGrupoMapBD = listarMenuPersonalizadoMap(idUsuario,null);
			for (MenuDTO menuDTO : listaMenu) {
				Long idMenu = menuDTO.getIdMenu();
				if (menuDTO.isChecked()) {
					if (!listaGrupoMapBD.containsKey(idMenu)) {
						MenuPersonalizado menuPersonalizado = new MenuPersonalizado();
						menuPersonalizado.setIdMenuPersonalizado(this.menuPersonalizadoDaoImpl.generarIdMenuPersonalizado());
						menuPersonalizado.setEstado(EstadoGeneralState.ACTIVO.getKey());
						parseMenuPersonalizado(menuPersonalizado, idUsuario, userName, idMenu, true);
						this.menuPersonalizadoDaoImpl.save(menuPersonalizado);	
					} else {
						MenuPersonalizadoDTO menuPersonalizado = listaGrupoMapBD.get(menuDTO.getIdMenu());
						if (!(EstadoGeneralState.ACTIVO.getKey().equalsIgnoreCase(menuPersonalizado.getEstado()))) {
							menuPersonalizado.setEstado(EstadoGeneralState.ACTIVO.getKey());
							MenuPersonalizado resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(menuPersonalizado, MenuPersonalizado.class);
							parseMenuPersonalizado(resultadoEntity, idUsuario, userName, idMenu, false);
							this.menuPersonalizadoDaoImpl.update(resultadoEntity);
						}
					}
				} else {
					if (listaGrupoMapBD.containsKey(idMenu)) {
						MenuPersonalizadoDTO menuPersonalizado = listaGrupoMapBD.get(idMenu);
						if (!(EstadoGeneralState.INACTIVO.getKey().equalsIgnoreCase(menuPersonalizado.getEstado()))) {
							menuPersonalizado.setEstado(EstadoGeneralState.INACTIVO.getKey());
							MenuPersonalizado resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(menuPersonalizado, MenuPersonalizado.class);
							parseMenuPersonalizado(resultadoEntity, idUsuario, userName, idMenu, false);
							this.menuPersonalizadoDaoImpl.update(resultadoEntity);
							
						}
					}
				}
			}
		}
		
	}
	private MenuPersonalizado parseMenuPersonalizado(MenuPersonalizado menuPersonalizado,String idUsuario,String userName,Long idMenu,boolean isNew ) {
		menuPersonalizado.setPersona(new Usuario());
		menuPersonalizado.getPersona().setIdUsuario(idUsuario);
		menuPersonalizado.setMenu(new Menu());
		if (isNew) {
			menuPersonalizado.setFechaCreacion(FechaUtil.obtenerFecha());
			menuPersonalizado.setUsuarioCreacion(userName);
		}
		menuPersonalizado.getMenu().setIdMenu(idMenu);
		return menuPersonalizado;
	}
	@Override
	public Map<Long,MenuPersonalizadoDTO> listarMenuPersonalizadoMap(String idUsuario,String estado) throws Exception {
		MenuPersonalizadoDTO menuPersonalizado = new MenuPersonalizadoDTO();
		menuPersonalizado.setId(idUsuario);
		menuPersonalizado.setEstado(estado);
		Map<Long,MenuPersonalizadoDTO> menuPersonalizadoMap = new HashMap<Long,MenuPersonalizadoDTO>();
		List<MenuPersonalizado> listaTem = this.menuPersonalizadoDaoImpl.listarMenuPersonalizado(menuPersonalizado);
		for (MenuPersonalizado objData : listaTem) {
			menuPersonalizadoMap.put(objData.getMenu().getIdMenu(), TransferDataObjectUtil.transferObjetoEntityDTO(objData,MenuPersonalizadoDTO.class));
		}
		return menuPersonalizadoMap;
	}
	@Override
	public void asociarPrivilegioPersonalizadoByUsuario(List<PrivilegioDTO> listaPrivilegio,String userName) throws Exception {
		if (!CollectionUtil.isEmpty(listaPrivilegio)) {
			PrivilegioDTO objData = listaPrivilegio.get(0);
			String idUsuario = objData.getId() + "";
			Map<Long,PrivilegioPersonalizadoDTO> listaPrivilegioMapBD = listarPrivilegioPersonalizadoMap(idUsuario,null);
			for (PrivilegioDTO privilegioDTO : listaPrivilegio) {
				Long idPrivilegio = privilegioDTO.getIdPrivilegio();
				if (privilegioDTO.isChecked()) {
					if (!listaPrivilegioMapBD.containsKey(idPrivilegio)) {
						PrivilegioPersonalizado privilegioPersonalizado = new PrivilegioPersonalizado();
						privilegioPersonalizado.setIdPrivilegioPersonalizado(this.privilegioPersonalizadoDaoImpl.generarIdPrivilegioPersonalizado());
						privilegioPersonalizado.setEstado(EstadoGeneralState.ACTIVO.getKey());
						parsePrivilegioPersonalizado(privilegioPersonalizado, idUsuario, userName, idPrivilegio, true);
						this.privilegioPersonalizadoDaoImpl.save(privilegioPersonalizado);	
					} else {
						PrivilegioPersonalizadoDTO privilegioPersonalizado = listaPrivilegioMapBD.get(idPrivilegio);
						if (!(EstadoGeneralState.ACTIVO.getKey().equalsIgnoreCase(privilegioPersonalizado.getEstado()))) {
							privilegioPersonalizado.setEstado(EstadoGeneralState.ACTIVO.getKey());
							PrivilegioPersonalizado resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(privilegioPersonalizado, PrivilegioPersonalizado.class);
							parsePrivilegioPersonalizado(resultadoEntity, idUsuario, userName, idPrivilegio, false);
							this.privilegioPersonalizadoDaoImpl.update(resultadoEntity);
						}
					}
				} else {
					if (listaPrivilegioMapBD.containsKey(idPrivilegio)) {
						PrivilegioPersonalizadoDTO privilegioPersonalizado = listaPrivilegioMapBD.get(idPrivilegio);
						if (!(EstadoGeneralState.INACTIVO.getKey().equalsIgnoreCase(privilegioPersonalizado.getEstado()))) {
							privilegioPersonalizado.setEstado(EstadoGeneralState.INACTIVO.getKey());
							PrivilegioPersonalizado resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(privilegioPersonalizado, PrivilegioPersonalizado.class);
							parsePrivilegioPersonalizado(resultadoEntity, idUsuario, userName, idPrivilegio, false);
							this.privilegioPersonalizadoDaoImpl.update(resultadoEntity);
							
						}
					}
				}
			}
		}
		
	}
	private PrivilegioPersonalizado parsePrivilegioPersonalizado(PrivilegioPersonalizado privilegioPersonalizado,String idUsuario,String userName,Long idPrivilegio,boolean isNew ) {
		privilegioPersonalizado.setUsuario(new Usuario());
		privilegioPersonalizado.getUsuario().setIdUsuario(idUsuario);
		privilegioPersonalizado.setPrivilegio(new Privilegio());
		if (isNew) {
			//privilegioPersonalizado.setFechaCreacion(FechaUtil.obtenerFecha());
			//privilegioPersonalizado.setUsuarioCreacion(userName);
		}
		privilegioPersonalizado.getPrivilegio().setIdPrivilegio(idPrivilegio);
		return privilegioPersonalizado;
	}
	public PrivilegioPersonalizadoDTO controladorAccionPrivilegioPersonalizado(PrivilegioPersonalizadoDTO privilegioPersonalizado, AccionType accionType) throws Exception {
		PrivilegioPersonalizadoDTO resultado = null;
		PrivilegioPersonalizado resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				privilegioPersonalizado.setIdPrivilegioPersonalizado(this.privilegioPersonalizadoDaoImpl.generarIdPrivilegioPersonalizado());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(privilegioPersonalizado, PrivilegioPersonalizado.class,"usuario@PK@","privilegio@PK@");
				this.privilegioPersonalizadoDaoImpl.save(resultadoEntity);	
				resultado = privilegioPersonalizado;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(privilegioPersonalizado, PrivilegioPersonalizado.class,"usuario@PK@","privilegio@PK@");
				this.privilegioPersonalizadoDaoImpl.update(resultadoEntity);
				resultado = privilegioPersonalizado;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.privilegioPersonalizadoDaoImpl.find(PrivilegioPersonalizado.class, privilegioPersonalizado.getIdPrivilegioPersonalizado());
				this.privilegioPersonalizadoDaoImpl.delete(resultadoEntity);
				resultado = privilegioPersonalizado;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.privilegioPersonalizadoDaoImpl.find(PrivilegioPersonalizado.class, privilegioPersonalizado.getIdPrivilegioPersonalizado());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,PrivilegioPersonalizadoDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.privilegioPersonalizadoDaoImpl.findByNombre(privilegioPersonalizado),PrivilegioPersonalizadoDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public Map<Long,PrivilegioPersonalizadoDTO> listarPrivilegioPersonalizadoMap(String idUsuario,String estado) throws Exception {
		PrivilegioPersonalizadoDTO privilegioPersonalizado = new PrivilegioPersonalizadoDTO();
		privilegioPersonalizado.setId(idUsuario);
		privilegioPersonalizado.setEstado(estado);
		Map<Long,PrivilegioPersonalizadoDTO> resultado = new HashMap<Long,PrivilegioPersonalizadoDTO>();
		List<PrivilegioPersonalizado> listaTem = this.privilegioPersonalizadoDaoImpl.listarPrivilegioPersonalizado(privilegioPersonalizado);
		for (PrivilegioPersonalizado objData : listaTem) {
			resultado.put(objData.getPrivilegio().getIdPrivilegio(), TransferDataObjectUtil.transferObjetoEntityDTO(objData,PrivilegioPersonalizadoDTO.class));
		}
		return resultado;
	}
	
	
	@Override
	public PropertiesDTO controladorAccionProperties(PropertiesDTO properties, AccionType accionType) throws Exception {
		PropertiesDTO resultado = null;
		Properties resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				properties.setIdProperties(this.propertiesDaoImpl.generarIdProperties());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(properties, Properties.class);
				this.propertiesDaoImpl.save(resultadoEntity);	
				PropertiesLenguaje propertiesLenguaje = new PropertiesLenguaje();
				propertiesLenguaje.setProperties(resultadoEntity);
				propertiesLenguaje.setValue(properties.getValue());
				propertiesLenguaje.setIdPropertiesLenguaje(propertiesLenguajeDaoImpl.generarIdPropertiesLenguaje());
				propertiesLenguaje.setItemByLenguaje(new Item(LENGUAJE_SPANISH));
				this.propertiesLenguajeDaoImpl.save(propertiesLenguaje);
				resultado = properties;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(properties, Properties.class);
				this.propertiesDaoImpl.update(resultadoEntity);
				this.propertiesLenguajeDaoImpl.actualizarPropertiesLenguaje(properties);
				resultado = properties;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.propertiesDaoImpl.find(Properties.class, properties.getIdProperties());
				this.propertiesDaoImpl.delete(resultadoEntity);
				resultado = properties;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.propertiesDaoImpl.find(Properties.class, properties.getIdProperties());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,PropertiesDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.propertiesDaoImpl.findByNombre(properties),PropertiesDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<PropertiesDTO> listarProperties(PropertiesDTO properties) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.propertiesDaoImpl.listarProperties(properties),PropertiesDTO.class);
	}
	@Override
	public int contarListarProperties(PropertiesDTO properties){
		return  this.propertiesDaoImpl.contarListarProperties(properties);
	}
	@Override
	public PrivilegioDTO controladorAccionPrivilegio(PrivilegioDTO privilegio, AccionType accionType) throws Exception {
		PrivilegioDTO resultado = null;
		Privilegio resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				privilegio.setIdPrivilegio(this.privilegioDaoImpl.generarIdPrivilegio());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(privilegio, Privilegio.class);
				this.privilegioDaoImpl.save(resultadoEntity);	
				resultado = privilegio;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(privilegio, Privilegio.class);
				this.privilegioDaoImpl.update(resultadoEntity);
				resultado = privilegio;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.privilegioDaoImpl.find(Privilegio.class, privilegio.getIdPrivilegio());
				this.privilegioDaoImpl.delete(resultadoEntity);
				resultado = privilegio;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.privilegioDaoImpl.find(Privilegio.class, privilegio.getIdPrivilegio());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,PrivilegioDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.privilegioDaoImpl.findByNombre(privilegio),PrivilegioDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<PrivilegioDTO> listarPrivilegio(PrivilegioDTO privilegio) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.privilegioDaoImpl.listarPrivilegio(privilegio),PrivilegioDTO.class);
	}
	@Override
	public int contarListarPrivilegio(PrivilegioDTO privilegio){
		return  this.privilegioDaoImpl.contarListarPrivilegio(privilegio);
	}
	
	@Override
	public MenuDTO controladorAccionMenu(MenuDTO menu, AccionType accionType) throws Exception {
		String userName = AppAuthenticator.getInstance().getUserName(menu.getAuthToken());
		MenuDTO resultado = null;
		Menu resultadoEntity = null; 
		switch (accionType) {
			case CREAR:
				menu.setIdMenu(this.menuDaoImpl.generarIdMenu());
				menu.setFechaCreacion(FechaUtil.obtenerFecha());
				menu.setUsuarioCreacion(userName);
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(menu, Menu.class,"sistema@PK@","menuPadre@PK@");
				this.menuDaoImpl.save(resultadoEntity);	
				resultado = menu;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(menu, Menu.class,"sistema@PK@","menuPadre@PK@");
				this.menuDaoImpl.update(resultadoEntity);
				resultado = menu;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.menuDaoImpl.findByMenu(menu.getIdMenu());
				List<GrupoUsuarioMenu> listaGrupoUsuarioMenu = grupoUsuarioMenuDaoImpl.listarGrupoUsuarioMenu(menu.getIdMenu());
				for(GrupoUsuarioMenu resultadoEntityGrupo :listaGrupoUsuarioMenu ) {
					this.grupoUsuarioMenuDaoImpl.delete(resultadoEntityGrupo);
				} 
				this.menuDaoImpl.delete(resultadoEntity);
				resultado = menu;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.menuDaoImpl.find(Menu.class, menu.getIdMenu());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,MenuDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.menuDaoImpl.findByNombre(menu),MenuDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<MenuDTO> listarMenu(MenuDTO menu) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.menuDaoImpl.listarMenu(menu),MenuDTO.class,"menuPadre","sistema");
	}
	@Override
	public int contarListarMenu(MenuDTO menu){
		return  this.menuDaoImpl.contarListarMenu(menu);
	}
	
	
	@Override
	public ConfiguracionMenuDTO controladorAccionConfiguracionMenu(ConfiguracionMenuDTO configuracionMenu, AccionType accionType) throws Exception {
		ConfiguracionMenuDTO resultado = null;
		ConfiguracionMenu resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				configuracionMenu.setIdConfiguracionMenu(this.configuracionMenuDaoImpl.generarIdConfiguracionMenu());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(configuracionMenu, ConfiguracionMenu.class,"menu@PK@","itemByComponente@PK@","properties@PK@");
				this.configuracionMenuDaoImpl.save(resultadoEntity);	
				resultado = configuracionMenu;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(configuracionMenu, ConfiguracionMenu.class,"menu@PK@","itemByComponente@PK@","properties@PK@");
				this.configuracionMenuDaoImpl.update(resultadoEntity);
				resultado = configuracionMenu;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.configuracionMenuDaoImpl.find(ConfiguracionMenu.class, configuracionMenu.getIdConfiguracionMenu());
				this.configuracionMenuDaoImpl.delete(resultadoEntity);
				resultado = configuracionMenu;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.configuracionMenuDaoImpl.find(ConfiguracionMenu.class, configuracionMenu.getIdConfiguracionMenu());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,ConfiguracionMenuDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.configuracionMenuDaoImpl.findByNombre(configuracionMenu),ConfiguracionMenuDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<ConfiguracionMenuDTO> listarConfiguracionMenu(ConfiguracionMenuDTO configuracionMenu) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.configuracionMenuDaoImpl.listarConfiguracionMenu(configuracionMenu),ConfiguracionMenuDTO.class);
	}
	@Override
	public int contarListarConfiguracionMenu(ConfiguracionMenuDTO configuracionMenu){
		return  this.configuracionMenuDaoImpl.contarListarConfiguracionMenu(configuracionMenu);
	}
	@Override
	public SistemaDTO controladorAccionSistema(SistemaDTO sistema, AccionType accionType) throws Exception {
		SistemaDTO resultado = null;
		Sistema resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				sistema.setIdSistema(this.sistemaDaoImpl.generarIdSistema());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(sistema, Sistema.class);
				this.sistemaDaoImpl.save(resultadoEntity);	
				resultado = sistema;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(sistema, Sistema.class);
				this.sistemaDaoImpl.update(resultadoEntity);
				resultado = sistema;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.sistemaDaoImpl.find(Sistema.class, sistema.getIdSistema());
				this.sistemaDaoImpl.delete(resultadoEntity);
				resultado = sistema;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.sistemaDaoImpl.find(Sistema.class, sistema.getIdSistema());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,SistemaDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.sistemaDaoImpl.findByNombre(sistema),SistemaDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<SistemaDTO> listarSistema(SistemaDTO sistema) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.sistemaDaoImpl.listarSistema(sistema),SistemaDTO.class);
	}
	@Override
	public int contarListarSistema(SistemaDTO sistema){
		return  this.sistemaDaoImpl.contarListarSistema(sistema);
	}
	
	@Override
	public void asociarPrivilegioByGrupoUsuario(List<PrivilegioDTO> listaPrivilegio,String userName) throws Exception {
		if (!CollectionUtil.isEmpty(listaPrivilegio)) {
			PrivilegioDTO objData = listaPrivilegio.get(0);
			Long idGrupoUsuario = ObjectUtil.objectToLong(objData.getId());
			Map<Long,PrivilegioGrupoUsuarioDTO> listaPrivilegioMapBD = listarPrivilegioGrupoUsuarioMap(idGrupoUsuario,null);
			for (PrivilegioDTO privilegioDTO : listaPrivilegio) {
				Long idPrivilegio = privilegioDTO.getIdPrivilegio();
				if (privilegioDTO.isChecked()) {
					if (!listaPrivilegioMapBD.containsKey(idPrivilegio)) {
						PrivilegioGrupoUsuario privilegioGrupoUsuario = new PrivilegioGrupoUsuario();
						privilegioGrupoUsuario.setIdPrivilegioGrupoUsuario(this.privilegioGrupoUsuarioDaoImpl.generarIdPrivilegioGrupoUsuario());
						privilegioGrupoUsuario.setEstado(EstadoGeneralState.ACTIVO.getKey());
						parsePrivilegioGrupoUsuario(privilegioGrupoUsuario, idGrupoUsuario, userName, idPrivilegio, true);
						this.privilegioGrupoUsuarioDaoImpl.save(privilegioGrupoUsuario);	
					} else {
						PrivilegioGrupoUsuarioDTO privilegioGrupoUsuario = listaPrivilegioMapBD.get(idPrivilegio);
						if (!(EstadoGeneralState.ACTIVO.getKey().equalsIgnoreCase(privilegioGrupoUsuario.getEstado()))) {
							privilegioGrupoUsuario.setEstado(EstadoGeneralState.ACTIVO.getKey());
							PrivilegioGrupoUsuario resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(privilegioGrupoUsuario, PrivilegioGrupoUsuario.class);
							parsePrivilegioGrupoUsuario(resultadoEntity, idGrupoUsuario, userName, idPrivilegio, false);
							this.privilegioGrupoUsuarioDaoImpl.update(resultadoEntity);
						}
					}
				} else {
					if (listaPrivilegioMapBD.containsKey(idPrivilegio)) {
						PrivilegioGrupoUsuarioDTO privilegioGrupoUsuario = listaPrivilegioMapBD.get(idPrivilegio);
						if (!(EstadoGeneralState.INACTIVO.getKey().equalsIgnoreCase(privilegioGrupoUsuario.getEstado()))) {
							privilegioGrupoUsuario.setEstado(EstadoGeneralState.INACTIVO.getKey());
							PrivilegioGrupoUsuario resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(privilegioGrupoUsuario, PrivilegioGrupoUsuario.class);
							parsePrivilegioGrupoUsuario(resultadoEntity, idGrupoUsuario, userName, idPrivilegio, false);
							this.privilegioGrupoUsuarioDaoImpl.update(resultadoEntity);
							
						}
					}
				}
			}
		}
		
	}
	private PrivilegioGrupoUsuario parsePrivilegioGrupoUsuario(PrivilegioGrupoUsuario privilegioPersonalizado,Long idGrupoUsuario,String userName,Long idPrivilegio,boolean isNew ) {
		privilegioPersonalizado.setGrupoUsuario(new GrupoUsuario());
		privilegioPersonalizado.getGrupoUsuario().setIdGrupoUsuario(idGrupoUsuario);
		privilegioPersonalizado.setPrivilegio(new Privilegio());
		if (isNew) {
			//privilegioPersonalizado.setFechaCreacion(FechaUtil.obtenerFecha());
			//privilegioPersonalizado.setUsuarioCreacion(userName);
		}
		privilegioPersonalizado.getPrivilegio().setIdPrivilegio(idPrivilegio);
		return privilegioPersonalizado;
	}
	
	
	@Override
	public Map<Long,PrivilegioGrupoUsuarioDTO> listarPrivilegioGrupoUsuarioMap(Long idGrupoUsuario,String estado) throws Exception {
		PrivilegioGrupoUsuarioDTO privilegioGrupoUsuario= new PrivilegioGrupoUsuarioDTO();
		privilegioGrupoUsuario.setId(idGrupoUsuario);
		privilegioGrupoUsuario.setEstado(estado);
		Map<Long,PrivilegioGrupoUsuarioDTO> resultado = new HashMap<Long,PrivilegioGrupoUsuarioDTO>();
		List<PrivilegioGrupoUsuario> listaTem = this.privilegioGrupoUsuarioDaoImpl.listarPrivilegioGrupoUsuario(privilegioGrupoUsuario);
		for (PrivilegioGrupoUsuario objData : listaTem) {
			resultado.put(objData.getPrivilegio().getIdPrivilegio(), TransferDataObjectUtil.transferObjetoEntityDTO(objData,PrivilegioGrupoUsuarioDTO.class));
		}
		return resultado;
	}
	@Override
	public int contarListarPrivilegioGrupoUsuario(PrivilegioGrupoUsuarioDTO privilegioGrupoUsuario){
		return  this.privilegioGrupoUsuarioDaoImpl.contarListarPrivilegioGrupoUsuario(privilegioGrupoUsuario);
	}
	
	
	
	@Override
	public UsuarioEntidadDTO controladorAccionUsuarioEntidad(UsuarioEntidadDTO usuarioEntidad, AccionType accionType) throws Exception {
		UsuarioEntidadDTO resultado = null;
		UsuarioEntidad resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				usuarioEntidad.setIdUsuarioEntidad(this.usuarioEntidadDaoImpl.generarIdUsuarioEntidad());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(usuarioEntidad, UsuarioEntidad.class,"entidad@PK@","usuario@PK@");
				this.usuarioEntidadDaoImpl.save(resultadoEntity);	
				resultado = usuarioEntidad;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(usuarioEntidad, UsuarioEntidad.class,"entidad@PK@","usuario@PK@");
				this.usuarioEntidadDaoImpl.update(resultadoEntity);
				resultado = usuarioEntidad;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.usuarioEntidadDaoImpl.find(UsuarioEntidad.class, usuarioEntidad.getIdUsuarioEntidad());
				this.usuarioEntidadDaoImpl.delete(resultadoEntity);
				resultado = usuarioEntidad;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.usuarioEntidadDaoImpl.find(UsuarioEntidad.class, usuarioEntidad.getIdUsuarioEntidad());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,UsuarioEntidadDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.usuarioEntidadDaoImpl.findByNombre(usuarioEntidad),UsuarioEntidadDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<UsuarioEntidadDTO> listarUsuarioEntidad(UsuarioEntidadDTO usuarioEntidad) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.usuarioEntidadDaoImpl.listarUsuarioEntidad(usuarioEntidad),UsuarioEntidadDTO.class,"entidad");
	}
	@Override
	public int contarListarUsuarioEntidad(UsuarioEntidadDTO usuarioEntidad){
		return  this.usuarioEntidadDaoImpl.contarListarUsuarioEntidad(usuarioEntidad);
	}

	@Override
	public void asociarMenuByGrupoUsuario(List<MenuDTO> listaMenu,String userName) throws Exception {
		if (!CollectionUtil.isEmpty(listaMenu)) {
			MenuDTO objData = listaMenu.get(0);
			Long idGrupoUsuario = ObjectUtil.objectToLong(objData.getId());
			Map<Long,GrupoUsuarioMenuDTO> listaGrupoMapBD = listarGrupoUsuarioMenuMap(idGrupoUsuario,null);
			for (MenuDTO menuDTO : listaMenu) {
				Long idMenu = menuDTO.getIdMenu();
				if (menuDTO.isChecked()) {
					if (!listaGrupoMapBD.containsKey(idMenu)) {
						GrupoUsuarioMenu objDataPersis = new GrupoUsuarioMenu();
						objDataPersis.setIdGrupoUsuarioMenu(this.grupoUsuarioMenuDaoImpl.generarIdGrupoUsuarioMenu());
						objDataPersis.setEstado(EstadoGeneralState.ACTIVO.getKey());
						parseGrupoUsuarioMenu(objDataPersis, idGrupoUsuario, userName, idMenu, true);
						this.grupoUsuarioMenuDaoImpl.save(objDataPersis);	
					} else {
						GrupoUsuarioMenuDTO objDataPersist = listaGrupoMapBD.get(menuDTO.getIdMenu());
						if (!(EstadoGeneralState.ACTIVO.getKey().equalsIgnoreCase(objDataPersist.getEstado()))) {
							objDataPersist.setEstado(EstadoGeneralState.ACTIVO.getKey());
							GrupoUsuarioMenu resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(objDataPersist, GrupoUsuarioMenu.class);
							parseGrupoUsuarioMenu(resultadoEntity, idGrupoUsuario, userName, idMenu, false);
							this.grupoUsuarioMenuDaoImpl.update(resultadoEntity);
						}
					}
				} else {
					if (listaGrupoMapBD.containsKey(idMenu)) {
						GrupoUsuarioMenuDTO objPersist = listaGrupoMapBD.get(idMenu);
						if (!(EstadoGeneralState.INACTIVO.getKey().equalsIgnoreCase(objPersist.getEstado()))) {
							objPersist.setEstado(EstadoGeneralState.INACTIVO.getKey());
							GrupoUsuarioMenu resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(objPersist, GrupoUsuarioMenu.class);
							parseGrupoUsuarioMenu(resultadoEntity, idGrupoUsuario, userName, idMenu, false);
							this.grupoUsuarioMenuDaoImpl.update(resultadoEntity);
							//this.grupoUsuarioMenuDaoImpl.delete(resultadoEntity);
						}
					}
				}
			}
		}
		
	}
	private GrupoUsuarioMenu parseGrupoUsuarioMenu(GrupoUsuarioMenu objDataPersis,Long idGrupoUsuario,String userName,Long idMenu,boolean isNew ) {
		objDataPersis.setGrupoUsuario(new GrupoUsuario());
		objDataPersis.getGrupoUsuario().setIdGrupoUsuario(idGrupoUsuario);
		objDataPersis.setMenu(new Menu());
		if (isNew) {
			objDataPersis.setFechaCreacion(FechaUtil.obtenerFecha());
			objDataPersis.setUsuarioCreacion(userName);
		}
		objDataPersis.getMenu().setIdMenu(idMenu);
		return objDataPersis;
	}
	@Override
	public Map<Long,GrupoUsuarioMenuDTO> listarGrupoUsuarioMenuMap(Long idGrupoUsuario,String estado) throws Exception {
		GrupoUsuarioMenuDTO filtro = new GrupoUsuarioMenuDTO();
		filtro.setId(idGrupoUsuario);
		filtro.setEstado(estado);
		Map<Long,GrupoUsuarioMenuDTO> resultadoMap = new HashMap<Long,GrupoUsuarioMenuDTO>();
		List<GrupoUsuarioMenu> listaTem = this.grupoUsuarioMenuDaoImpl.listarGrupoUsuarioMenu(filtro);
		for (GrupoUsuarioMenu objData : listaTem) {
			resultadoMap.put(objData.getMenu().getIdMenu(), TransferDataObjectUtil.transferObjetoEntityDTO(objData,GrupoUsuarioMenuDTO.class));
		}
		return resultadoMap;
	}
	
	
	//agregado
	@Override
	public UsuarioEntidadDTO listarUsuarioEntidadDelect(EgresadoFiltroVO filtro) throws Exception {		
		UsuarioEntidadDTO resultado = new UsuarioEntidadDTO();
		UsuarioEntidad usuario = usuarioEntidadDaoImpl.listarUsuarioEntidadDelect(filtro);
		resultado = TransferDataObjectUtil.transferObjetoEntityDTO(usuario,UsuarioEntidadDTO.class,"usuario","entidad");
		usuario = null;
		return resultado;
	}
	
	
	@Override
	public UsuarioDTO controladorAccionUsuario2(String idUsuario,String filtro, AccionType accionType) throws Exception {
		UsuarioDTO usuario = new UsuarioDTO();
    	Usuario resultadoEntity2 = null;
		resultadoEntity2 = this.usuarioDaoImpl.find(Usuario.class, idUsuario);
		usuario = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity2,UsuarioDTO.class,"tipoUsuario");
		UsuarioDTO resultado = null;
		Usuario resultadoEntity = null;
		UsuarioEntidad objPersist = null;
		EgresadoFiltroVO filtroLi =null;
		switch (accionType) {
			case CREAR:
				usuario.setIdUsuario(this.usuarioDaoImpl.generarIdUsuario());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(usuario, Usuario.class,"tipoUsuario@PK@");
				if (StringUtils.isNotNullOrBlank(resultadoEntity.getUserPassword())) {
					resultadoEntity.setUserPassword(EncriptarUtil.encriptar(resultadoEntity.getUserPassword()));
			    } else {
			    	resultadoEntity.setUserPassword(EncriptarUtil.encriptar(resultadoEntity.getUserName()));
			    }
				this.usuarioDaoImpl.save(resultadoEntity);	
				resultado = usuario;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(usuario, Usuario.class,"tipoUsuario@PK@");
			    if (StringUtils.isNotNullOrBlank(resultadoEntity.getUserPassword())) {
			    	resultadoEntity.setUserPassword(EncriptarUtil.encriptar(filtro));
			    } else {
			    	resultadoEntity.setUserPassword(usuario.getUserPasswordEncriptado());
			    }
				this.usuarioDaoImpl.update(resultadoEntity);
				resultado = usuario;	
				break;
				
			case ELIMINAR:
				filtroLi.setIdUsuario(usuario.getIdUsuario());
				UsuarioEntidadDTO codigoexterno=this.listarUsuarioEntidadDelect(filtroLi);
				objPersist = this.usuarioEntidadDaoImpl.find(UsuarioEntidad.class,codigoexterno.getIdUsuarioEntidad());
				this.usuarioEntidadDaoImpl.delete(objPersist);
				
				resultadoEntity = this.usuarioDaoImpl.find(Usuario.class, usuario.getIdUsuario());
				this.usuarioDaoImpl.delete(resultadoEntity);
				resultado = usuario;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.usuarioDaoImpl.find(Usuario.class, usuario.getIdUsuario());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,UsuarioDTO.class,"tipoUsuario");
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.usuarioDaoImpl.findByNombre(usuario),UsuarioDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	
	@Override
	public RecuperarPasswordDTO listarRecuperarPassword(String recuperarPassword) throws Exception {
		RecuperarPasswordDTO resultado = new RecuperarPasswordDTO();
		RecuperarPassword recuperar = recuperarPasswordDaoImpl.listarRecuperarPassword(recuperarPassword);
		resultado = TransferDataObjectUtil.transferObjetoEntityDTO(recuperar,RecuperarPasswordDTO.class,"usuario");	
		recuperar = null;
		return resultado;
	}
	
	@Override
	public RecuperarPasswordDTO registrarRecuperarPassword(RecuperarPasswordDTO recuperarPassword) throws Exception {
		RecuperarPassword resultadoEntity = new RecuperarPassword();
		RecuperarPassword resultadoEntityUsuario = null;
		if (!StringUtils.isNotNullOrBlank(recuperarPassword.getIdRecuperar())) {
			resultadoEntityUsuario=this.recuperarPasswordDaoImpl.findUsuario(recuperarPassword.getUsuario().getIdUsuario());
			if(StringUtils.isNotNullOrBlank(resultadoEntityUsuario)) {
				this.recuperarPasswordDaoImpl.deleteRecuperar(recuperarPassword.getUsuario().getIdUsuario());
			}
			recuperarPassword.setIdRecuperar(this.recuperarPasswordDaoImpl.generarIdRecuperarPassword());
			resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(recuperarPassword, RecuperarPassword.class, "usuario@PK@");
			resultadoEntity = recuperarPasswordDaoImpl.save(resultadoEntity);
		} 		
				
		return recuperarPassword;
	}
	
	@Override
	public UsuarioDTO validarLoginPassword(String userName, String email) throws Exception {
		UsuarioDTO resultado = TransferDataObjectUtil.transferObjetoEntityDTO(usuarioDaoImpl.validarLoginPassword(userName, email), UsuarioDTO.class,"tipoUsuario");
		if (resultado != null && resultado.getIdUsuario() != null) {
			resultado.setPrivilegiosMap(obtenerPrivilegiosUsuario(resultado.getIdUsuario()));
			resultado.setListaMenu(obtenerMenuUsuario(resultado.getIdUsuario()));
			List<String> listaIdUsuario = new ArrayList<String>();
			listaIdUsuario.add(resultado.getIdUsuario());
			//Para saber si es un usuario que tiene mas de una empresa
			RecuperarPasswordDTO recuperar = new RecuperarPasswordDTO();
			recuperar.setUsuario(resultado);
	       	this.registrarRecuperarPassword(recuperar);
			Map<String,Integer> cantidadEmpresaAsignadoMap =  usuarioEntidadDaoImpl.obtenerCantidadEntidadAsignadaMap(listaIdUsuario );
			if (cantidadEmpresaAsignadoMap != null && cantidadEmpresaAsignadoMap.containsKey(resultado.getIdUsuario())) {
				resultado.setVarCantidad(cantidadEmpresaAsignadoMap.get(resultado.getIdUsuario()));
				if (resultado.getVarCantidad() == 1) {
					UsuarioEntidadDTO usuarioEntidadFiltro = new UsuarioEntidadDTO();
					usuarioEntidadFiltro.setId(resultado.getIdUsuario());
					UsuarioEntidad usuarioEntidad = usuarioEntidadDaoImpl.listarUsuarioEntidad(usuarioEntidadFiltro).get(0);
					resultado.setEntidad(TransferDataObjectUtil.transferObjetoEntityDTO(usuarioEntidad.getEntidad(),EntidadDTO.class));
				}
			}
		}
		return resultado;
	}
	
	@Override
	public UsuarioDTO findByUsuarioID(String idUsuario) throws Exception {
		UsuarioDTO resultado = new UsuarioDTO();
		Usuario usuario = usuarioDaoImpl.findByUsuarioID(idUsuario);
		resultado = TransferDataObjectUtil.transferObjetoEntityDTO(usuario,UsuarioDTO.class,"tipoUsuario@PK@");
		usuario = null;
		return resultado;
	}
}