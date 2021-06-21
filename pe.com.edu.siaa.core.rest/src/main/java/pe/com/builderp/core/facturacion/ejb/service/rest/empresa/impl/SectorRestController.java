package pe.com.builderp.core.facturacion.ejb.service.rest.empresa.impl;
   
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import pe.com.builderp.core.facturacion.ejb.service.empresa.local.EmpresaServiceLocal;
import pe.com.builderp.core.facturacion.model.dto.empresa.SectorDTO; 
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.type.AccionType; 
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class SectorRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:21 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/sectorRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class SectorRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient EmpresaServiceLocal empresaServiceLocal;
	
	@POST
	public ResultadoRestVO<SectorDTO> crear(SectorDTO Sector) throws Exception {
		return controladorAccion(Sector,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<SectorDTO> modificar(SectorDTO Sector) throws Exception {
		return controladorAccion(Sector,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<SectorDTO> eliminar(@PathParam("id") String idSector) throws Exception {
		SectorDTO Sector = new SectorDTO();
		Sector.setIdSector(idSector);		
		return controladorAccion(Sector,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<SectorDTO> controladorAccion(SectorDTO Sector, AccionType accionType){
		ResultadoRestVO<SectorDTO> resultado = new ResultadoRestVO<SectorDTO>();
		 try {
    		resultado.setObjetoResultado(empresaServiceLocal.controladorAccionSector(Sector,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<SectorDTO> finById(@PathParam("id") String idSector) throws Exception {
		SectorDTO Sector = new SectorDTO();
		Sector.setIdSector(idSector);
		return controladorAccion(Sector,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<SectorDTO> listarSector(@Context UriInfo info){
		ResultadoRestVO<SectorDTO> resultado = new ResultadoRestVO<SectorDTO>();
		SectorDTO Sector = transferUriInfo(info);
		 try {
			resultado.setListaResultado(empresaServiceLocal.listarSector(Sector));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<SectorDTO> contarSector(@Context UriInfo info){
		ResultadoRestVO<SectorDTO> resultado = new ResultadoRestVO<SectorDTO>();
		SectorDTO Sector = transferUriInfo(info);
		 try {
			 resultado.setContador(empresaServiceLocal.contarListarSector(Sector));
			 if (resultado.isData()) {
				resultado.setListaResultado(empresaServiceLocal.listarSector(Sector));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<SectorDTO> inicializarSector(@Context UriInfo info) throws Exception {
	     SectorDTO Sector = transferUriInfo(info);
		 ResultadoRestVO<SectorDTO> resultado = new ResultadoRestVO<SectorDTO>();
		 try {
    		resultado.setObjetoResultado(Sector);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private SectorDTO transferUriInfo(@Context UriInfo info) {
		SectorDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,SectorDTO.class);
		return resultado;
	}
	
 
}