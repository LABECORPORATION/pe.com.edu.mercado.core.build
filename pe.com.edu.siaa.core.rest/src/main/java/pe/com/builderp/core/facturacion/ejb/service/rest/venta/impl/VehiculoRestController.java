package pe.com.builderp.core.facturacion.ejb.service.rest.venta.impl;

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


import pe.com.builderp.core.facturacion.ejb.service.venta.local.VentaServiceLocal;
import pe.com.builderp.core.facturacion.model.dto.venta.VehiculoDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;



/**
 * La Class VehiculoRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu May 02 10:20:30 COT 2019
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/vehiculoRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class VehiculoRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient VentaServiceLocal ventaServiceLocal;
	
	@POST
	public ResultadoRestVO<VehiculoDTO> crear(VehiculoDTO vehiculo) throws Exception {
		return controladorAccion(vehiculo,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<VehiculoDTO> modificar(VehiculoDTO vehiculo) throws Exception {
		return controladorAccion(vehiculo,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<VehiculoDTO> eliminar(@PathParam("id") String idVehiculo) throws Exception {
		VehiculoDTO vehiculo = new VehiculoDTO();
		vehiculo.setIdVehiculo(idVehiculo);		
		return controladorAccion(vehiculo,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<VehiculoDTO> controladorAccion(VehiculoDTO vehiculo, AccionType accionType){
		ResultadoRestVO<VehiculoDTO> resultado = new ResultadoRestVO<>();
		 try {
    		resultado.setObjetoResultado(ventaServiceLocal.controladorAccionVehiculo(vehiculo,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<VehiculoDTO> finById(@PathParam("id") String idVehiculo) throws Exception {
		VehiculoDTO vehiculo = new VehiculoDTO();
		vehiculo.setIdVehiculo(idVehiculo);
		return controladorAccion(vehiculo,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<VehiculoDTO> listarVehiculo(@Context UriInfo info){
		ResultadoRestVO<VehiculoDTO> resultado = new ResultadoRestVO<>();
		VehiculoDTO vehiculo = transferUriInfo(info);
		 try {
			resultado.setListaResultado(ventaServiceLocal.listarVehiculo(vehiculo));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<VehiculoDTO> contarVehiculo(@Context UriInfo info){
		ResultadoRestVO<VehiculoDTO> resultado = new ResultadoRestVO<>();
		VehiculoDTO vehiculo = transferUriInfo(info);
		 try {
			 resultado.setContador(ventaServiceLocal.contarListarVehiculo(vehiculo));
			 if (resultado.isData()) {
				resultado.setListaResultado(ventaServiceLocal.listarVehiculo(vehiculo));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<VehiculoDTO> inicializarVehiculo(@Context UriInfo info) throws Exception {
	     VehiculoDTO vehiculo = transferUriInfo(info);
		 ResultadoRestVO<VehiculoDTO> resultado = new ResultadoRestVO<>();
		 try {
    		resultado.setObjetoResultado(vehiculo);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private VehiculoDTO transferUriInfo(@Context UriInfo info) {
		return TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,VehiculoDTO.class);
	}
}