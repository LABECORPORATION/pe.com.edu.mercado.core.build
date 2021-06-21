package pe.com.edu.siaa.core.ejb.service.rest.seguridad.impl;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.seguridad.jwt.rsa.util.AppHTTPHeaderNames;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.ejb.service.seguridad.local.SeguridadServiceLocal;
import pe.com.edu.siaa.core.ejb.util.cache.AppAuthenticator;
import pe.com.edu.siaa.core.model.dto.seguridad.PrivilegioDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.PrivilegioGrupoUsuarioDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.PrivilegioMenuDTO;
import pe.com.edu.siaa.core.model.estate.EstadoGeneralState;
import pe.com.edu.siaa.core.model.util.ObjectUtil;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class PrivilegioGrupoUsuarioRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Mon Oct 16 14:31:02 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/privilegioGrupoUsuarioRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class PrivilegioGrupoUsuarioRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient SeguridadServiceLocal seguridadServiceLocal;
	
	@POST
	public ResultadoRestVO<PrivilegioGrupoUsuarioDTO> asociarPrivilegioPersonalizado(@Context HttpHeaders httpHeaders,List<PrivilegioDTO> listaPrivilegio) throws Exception {
		ResultadoRestVO<PrivilegioGrupoUsuarioDTO> resultado = new ResultadoRestVO<PrivilegioGrupoUsuarioDTO>();
		 try {
			  String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
			  String userName = AppAuthenticator.getInstance().getUserName(authToken);
			  seguridadServiceLocal.asociarPrivilegioByGrupoUsuario(listaPrivilegio,userName);
			  resultado.setObjetoResultado(new PrivilegioGrupoUsuarioDTO());
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<PrivilegioMenuDTO> listarPrivilegioPersonalizado(@Context UriInfo info){
		ResultadoRestVO<PrivilegioMenuDTO> resultado = new ResultadoRestVO<PrivilegioMenuDTO>();
		PrivilegioMenuDTO privilegio = transferUriInfo(info);
		 try {
			 resultado.setListaResultado(obtenerPrivilegioMenuCheck(privilegio, ObjectUtil.objectToLong(privilegio.getId())));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;	
	}
	
	@GET
    @Path("/contar")
	public ResultadoRestVO<PrivilegioMenuDTO> contarPrivilegioPersonalizado(@Context UriInfo info){
		ResultadoRestVO<PrivilegioMenuDTO> resultado = new ResultadoRestVO<PrivilegioMenuDTO>();
		PrivilegioMenuDTO privilegio = transferUriInfo(info);
		 try {
			 resultado.setContador(seguridadServiceLocal.contarListarPrivilegioMenu(privilegio));
			 if (resultado.isData()) {
				resultado.setListaResultado(obtenerPrivilegioMenuCheck(privilegio, ObjectUtil.objectToLong(privilegio.getId())));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	private List<PrivilegioMenuDTO> obtenerPrivilegioMenuCheck(PrivilegioMenuDTO privilegioMenu,Long idGrupoUsuario) throws Exception {
		List<PrivilegioMenuDTO> resultado = null;
		Map<Long,PrivilegioGrupoUsuarioDTO> privilegioPersonalizadoMap = seguridadServiceLocal.listarPrivilegioGrupoUsuarioMap(idGrupoUsuario,EstadoGeneralState.ACTIVO.getKey());
		resultado = seguridadServiceLocal.listarPrivilegioMenu(privilegioMenu);
		for (PrivilegioMenuDTO objData : resultado) {
			if (privilegioPersonalizadoMap.containsKey(objData.getPrivilegio().getIdPrivilegio())) {
				objData.setChecked(true);
				objData.getPrivilegio().setChecked(true);				
			}
		}
		return resultado;
	}
	
	private PrivilegioMenuDTO transferUriInfo(@Context UriInfo info) {
		PrivilegioMenuDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,PrivilegioMenuDTO.class);
		List<String> listaIdMenu = info.getQueryParameters().get("idMenu");
		if (listaIdMenu != null) {
			for (String idMenu : listaIdMenu) {
				resultado.getListaIdMenu().add(ObjectUtil.objectToLong(idMenu));
			}
		}
		return resultado;
	}
}