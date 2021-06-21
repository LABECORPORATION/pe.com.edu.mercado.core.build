package pe.com.edu.siaa.core.ejb.service.seguridad.local;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

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
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.model.vo.ConfiguracionMenuVO;
import pe.com.edu.siaa.core.model.vo.EgresadoFiltroVO;

/**
 * La Class SeguridadServiceLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 13:43:23 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface SeguridadServiceLocal {
	
	UsuarioDTO obtenerUsuarioByCodigoExterno(String codigoExterno) throws Exception;
	/**
	 * Controlador accion entidad.
	 *
	 * @param entidad el entidad
	 * @param accionType el accion type
	 * @return the entidad
	 * @throws Exception the exception
	 */
	EntidadDTO controladorAccionEntidad(EntidadDTO entidad,AccionType accionType) throws Exception; 
	
	/**
	 * Listar entidad.
	 *
	 * @param entidad el entidad
	 * @return the list
	 * @throws Exception the exception
	 */
	List<EntidadDTO> listarEntidad(EntidadDTO entidad) throws Exception;
	
	/**
	 * contar lista entidad.
	 *
	 * @param entidad el entidad
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarEntidad(EntidadDTO entidad);
	
	/**
	 * Controlador accion tipo usuario.
	 *
	 * @param tipoUsuario el tipo usuario
	 * @param accionType el accion type
	 * @return the tipo usuario
	 * @throws Exception the exception
	 */
	TipoUsuarioDTO controladorAccionTipoUsuario(TipoUsuarioDTO tipoUsuario,AccionType accionType) throws Exception; 
	
	/**
	 * Listar tipo usuario.
	 *
	 * @param tipoUsuario el tipo usuario
	 * @return the list
	 * @throws Exception the exception
	 */
	List<TipoUsuarioDTO> listarTipoUsuario(TipoUsuarioDTO tipoUsuario) throws Exception;
	
	/**
	 * contar lista tipo usuario.
	 *
	 * @param tipoUsuario el tipo usuario
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarTipoUsuario(TipoUsuarioDTO tipoUsuario);
	
	/**
	 * Controlador accion usuario.
	 *
	 * @param usuario el usuario
	 * @param accionType el accion type
	 * @return the usuario
	 * @throws Exception the exception
	 */
	UsuarioDTO controladorAccionUsuario(UsuarioDTO usuario,AccionType accionType) throws Exception; 
	
	UsuarioDTO integracionUsuario(UsuarioDTO usuario,AccionType accionType) throws Exception; 
	
	/**
	 * Listar usuario.
	 *
	 * @param usuario el usuario
	 * @return the list
	 * @throws Exception the exception
	 */
	List<UsuarioDTO> listarUsuario(UsuarioDTO usuario) throws Exception;
	
	/**
	 * contar lista usuario.
	 *
	 * @param usuario el usuario
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarUsuario(UsuarioDTO usuario);

	/**
	 * Obtener properties lenguaje.
	 *
	 * @param propertiesFiltro el properties filtro
	 * @return the list
	 * @throws Exception the exception
	 */
	List<PropertiesDTO> obtenerPropertiesLenguaje(PropertiesDTO propertiesFiltro) throws Exception;
	
	/**
	 * Obtener configuracion menu.
	 *
	 * @param menu el menu
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ConfiguracionMenuVO> obtenerConfiguracionMenu(Long idMenu) throws Exception;
	
	/**
	 * Obtener menu usuario.
	 *
	 * @param usuario el usuario
	 * @param idSistema el id sistema
	 * @return the list
	 * @throws Exception the exception
	 */
	List<MenuDTO> obtenerMenuUsuario(String idUsuario) throws Exception;

	/**
	 * Obtener properties lenguaje all map.
	 *
	 * @return the map
	 */
	Map<String,Map<String,String>> obtenerPropertiesLenguajeAllMap();
	
	
	/**
	 * Validar login.
	 *
	 * @param userName el user name
	 * @param userPassword el user password
	 * @return the persona
	 * @throws Exception the exception
	 */
	UsuarioDTO validarLogin(String userName,String userPassword) throws Exception;
	
	/**
	 * Controlador accion grupo usuario usuario.
	 *
	 * @param grupoUsuarioUsuario el grupo usuario usuario
	 * @param accionType el accion type
	 * @return the grupo usuario usuario
	 * @throws Exception the exception
	 */
	void asociarGrupoUsuarioByUsuario(List<GrupoUsuarioDTO> listaGrupoUsuario,String userName) throws Exception; 
	
	/**
	 * Listar grupo usuario usuario.
	 *
	 * @param grupoUsuarioUsuario el grupo usuario usuario
	 * @return the list
	 * @throws Exception the exception
	 */
	Map<Long,GrupoUsuarioUsuarioDTO> listarGrupoUsuarioUsuarioMap(String idUsuario,String estado) throws Exception;
	
	/**
	 * Controlador accion grupo usuario.
	 *
	 * @param grupoUsuario el grupo usuario
	 * @param accionType el accion type
	 * @return the grupo usuario
	 * @throws Exception the exception
	 */
	GrupoUsuarioDTO controladorAccionGrupoUsuario(GrupoUsuarioDTO grupoUsuario,AccionType accionType) throws Exception; 
	
	/**
	 * Listar grupo usuario.
	 *
	 * @param grupoUsuario el grupo usuario
	 * @return the list
	 * @throws Exception the exception
	 */
	List<GrupoUsuarioDTO> listarGrupoUsuario(GrupoUsuarioDTO grupoUsuario) throws Exception;
	
	/**
	 * contar lista grupo usuario.
	 *
	 * @param grupoUsuario el grupo usuario
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarGrupoUsuario(GrupoUsuarioDTO grupoUsuario);
	
	/**
	 * Controlador accion privilegio menu.
	 *
	 * @param privilegioMenu el privilegio menu
	 * @param accionType el accion type
	 * @return the privilegio menu
	 * @throws Exception the exception
	 */
	PrivilegioMenuDTO controladorAccionPrivilegioMenu(PrivilegioMenuDTO privilegioMenu,AccionType accionType) throws Exception; 
	
	/**
	 * Listar privilegio menu.
	 *
	 * @param privilegioMenu el privilegio menu
	 * @return the list
	 * @throws Exception the exception
	 */
	List<PrivilegioMenuDTO> listarPrivilegioMenu(PrivilegioMenuDTO privilegioMenu) throws Exception;
	
	/**
	 * contar lista privilegio menu.
	 *
	 * @param privilegioMenu el privilegio menu
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarPrivilegioMenu(PrivilegioMenuDTO privilegioMenu);
		
	/**
	 * Controlador accion properties lenguaje.
	 *
	 * @param propertiesLenguaje el properties lenguaje
	 * @param accionType el accion type
	 * @return the properties lenguaje
	 * @throws Exception the exception
	 */
	PropertiesLenguajeDTO controladorAccionPropertiesLenguaje(PropertiesLenguajeDTO propertiesLenguaje,AccionType accionType) throws Exception; 
	
	/**
	 * Listar properties lenguaje.
	 *
	 * @param propertiesLenguaje el properties lenguaje
	 * @return the list
	 * @throws Exception the exception
	 */
	List<PropertiesLenguajeDTO> listarPropertiesLenguaje(PropertiesLenguajeDTO propertiesLenguaje) throws Exception;
	
	/**
	 * contar lista properties lenguaje.
	 *
	 * @param propertiesLenguaje el properties lenguaje
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarPropertiesLenguaje(PropertiesLenguajeDTO propertiesLenguaje);
	
	/**
	 * Controlador accion menu personalizado.
	 *
	 * @param menuPersonalizado el menu personalizado
	 * @param accionType el accion type
	 * @return the menu personalizado
	 * @throws Exception the exception
	 */
	void asociarMenuPersonalizadoByUsuario(List<MenuDTO> listaMenu,String userName) throws Exception;
	/**
	 * Listar menu personalizado.
	 *
	 * @param menuPersonalizado el menu personalizado
	 * @return the list
	 * @throws Exception the exception
	 */
	Map<Long,MenuPersonalizadoDTO> listarMenuPersonalizadoMap(String idUsuario,String estado) throws Exception;

	/**
	 * Controlador accion privilegio personalizado.
	 *
	 * @param privilegioPersonalizado el privilegio personalizado
	 * @param accionType el accion type
	 * @return the privilegio personalizado
	 * @throws Exception the exception
	 */
	void asociarPrivilegioPersonalizadoByUsuario(List<PrivilegioDTO> listaPrivilegio,String userName) throws Exception;
	
	/**
	 * Listar privilegio personalizado.
	 *
	 * @param privilegioPersonalizado el privilegio personalizado
	 * @return the list
	 * @throws Exception the exception
	 */
	Map<Long,PrivilegioPersonalizadoDTO> listarPrivilegioPersonalizadoMap(String idUsuario,String estado) throws Exception;
	
	/**
	 * Controlador accion properties.
	 *
	 * @param properties el properties
	 * @param accionType el accion type
	 * @return the properties
	 * @throws Exception the exception
	 */
	PropertiesDTO controladorAccionProperties(PropertiesDTO properties,AccionType accionType) throws Exception; 
	
	/**
	 * Listar properties.
	 *
	 * @param properties el properties
	 * @return the list
	 * @throws Exception the exception
	 */
	List<PropertiesDTO> listarProperties(PropertiesDTO properties) throws Exception;
	
	/**
	 * contar lista properties.
	 *
	 * @param properties el properties
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarProperties(PropertiesDTO properties);
	
	/**
	 * Controlador accion privilegio.
	 *
	 * @param privilegio el privilegio
	 * @param accionType el accion type
	 * @return the privilegio
	 * @throws Exception the exception
	 */
	PrivilegioDTO controladorAccionPrivilegio(PrivilegioDTO privilegio,AccionType accionType) throws Exception; 
	
	/**
	 * Listar privilegio.
	 *
	 * @param privilegio el privilegio
	 * @return the list
	 * @throws Exception the exception
	 */
	List<PrivilegioDTO> listarPrivilegio(PrivilegioDTO privilegio) throws Exception;
	
	/**
	 * contar lista privilegio.
	 *
	 * @param privilegio el privilegio
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarPrivilegio(PrivilegioDTO privilegio);
	
	/**
	 * Controlador accion menu.
	 *
	 * @param menu el menu
	 * @param accionType el accion type
	 * @return the menu
	 * @throws Exception the exception
	 */
	MenuDTO controladorAccionMenu(MenuDTO menu,AccionType accionType) throws Exception; 
	
	/**
	 * Listar menu.
	 *
	 * @param menu el menu
	 * @return the list
	 * @throws Exception the exception
	 */
	List<MenuDTO> listarMenu(MenuDTO menu) throws Exception;
	
	/**
	 * contar lista menu.
	 *
	 * @param menu el menu
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarMenu(MenuDTO menu);
	
	/**
	 * Controlador accion configuracion menu.
	 *
	 * @param configuracionMenu el configuracion menu
	 * @param accionType el accion type
	 * @return the configuracion menu
	 * @throws Exception the exception
	 */
	ConfiguracionMenuDTO controladorAccionConfiguracionMenu(ConfiguracionMenuDTO configuracionMenu,AccionType accionType) throws Exception; 
	
	/**
	 * Listar configuracion menu.
	 *
	 * @param configuracionMenu el configuracion menu
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ConfiguracionMenuDTO> listarConfiguracionMenu(ConfiguracionMenuDTO configuracionMenu) throws Exception;
	
	/**
	 * contar lista configuracion menu.
	 *
	 * @param configuracionMenu el configuracion menu
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarConfiguracionMenu(ConfiguracionMenuDTO configuracionMenu);
	
	/**
	 * Controlador accion sistema.
	 *
	 * @param sistema el sistema
	 * @param accionType el accion type
	 * @return the sistema
	 * @throws Exception the exception
	 */
	SistemaDTO controladorAccionSistema(SistemaDTO sistema,AccionType accionType) throws Exception; 
	
	/**
	 * Listar sistema.
	 *
	 * @param sistema el sistema
	 * @return the list
	 * @throws Exception the exception
	 */
	List<SistemaDTO> listarSistema(SistemaDTO sistema) throws Exception;
	
	/**
	 * contar lista sistema.
	 *
	 * @param sistema el sistema
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarSistema(SistemaDTO sistema);
	
	/**
	 * Controlador accion privilegio grupo usuario.
	 *
	 * @param privilegioGrupoUsuario el privilegio grupo usuario
	 * @param accionType el accion type
	 * @return the privilegio grupo usuario
	 * @throws Exception the exception
	 */
	void asociarPrivilegioByGrupoUsuario(List<PrivilegioDTO> listaPrivilegio,String userName) throws Exception;
	/**
	 * Listar privilegio grupo usuario.
	 *
	 * @param privilegioGrupoUsuario el privilegio grupo usuario
	 * @return the list
	 * @throws Exception the exception
	 */
	Map<Long,PrivilegioGrupoUsuarioDTO> listarPrivilegioGrupoUsuarioMap(Long idGrupoUsuario,String estado) throws Exception;
	
	/**
	 * contar lista privilegio grupo usuario.
	 *
	 * @param privilegioGrupoUsuario el privilegio grupo usuario
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarPrivilegioGrupoUsuario(PrivilegioGrupoUsuarioDTO privilegioGrupoUsuario);
	

	/**
	 * Controlador accion usuario entidad.
	 *
	 * @param usuarioEntidad el usuario entidad
	 * @param accionType el accion type
	 * @return the usuario entidad
	 * @throws Exception the exception
	 */
	UsuarioEntidadDTO controladorAccionUsuarioEntidad(UsuarioEntidadDTO usuarioEntidad,AccionType accionType) throws Exception; 
	
	/**
	 * Listar usuario entidad.
	 *
	 * @param usuarioEntidad el usuario entidad
	 * @return the list
	 * @throws Exception the exception
	 */
	List<UsuarioEntidadDTO> listarUsuarioEntidad(UsuarioEntidadDTO usuarioEntidad) throws Exception;
	
	/**
	 * contar lista usuario entidad.
	 *
	 * @param usuarioEntidad el usuario entidad
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarUsuarioEntidad(UsuarioEntidadDTO usuarioEntidad);
	
	/**
	 * Controlador accion grupo usuario menu.
	 *
	 * @param grupoUsuarioMenu el grupo usuario menu
	 * @param accionType el accion type
	 * @return the grupo usuario menu
	 * @throws Exception the exception
	 */
	void asociarMenuByGrupoUsuario(List<MenuDTO> listaMenu,String userName) throws Exception;
	/**
	 * Listar grupo usuario menu.
	 *
	 * @param grupoUsuarioMenu el grupo usuario menu
	 * @return the list
	 * @throws Exception the exception
	 */
	Map<Long,GrupoUsuarioMenuDTO> listarGrupoUsuarioMenuMap(Long idGrupoUsuario,String estado) throws Exception;
	
	//agregado
	
	UsuarioDTO controladorAccionUsuario2(String idUsuario,String filtro,AccionType accionType) throws Exception;
	
	UsuarioEntidadDTO listarUsuarioEntidadDelect(EgresadoFiltroVO filtro) throws Exception;
	
	RecuperarPasswordDTO listarRecuperarPassword(String recuperarPassword) throws Exception;

	UsuarioDTO validarLoginPassword(String userName,String email) throws Exception;
	
	RecuperarPasswordDTO registrarRecuperarPassword(RecuperarPasswordDTO recuperarPassword ) throws Exception;
	
	UsuarioDTO findByUsuarioID(String idUsuario) throws Exception;
}