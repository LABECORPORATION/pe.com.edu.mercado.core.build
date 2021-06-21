package pe.com.builderp.core.facturacion.ejb.service.compra.local;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.compra.CompraDTO;
import pe.com.builderp.core.facturacion.model.dto.compra.ContactoProveedorDTO;
import pe.com.builderp.core.facturacion.model.dto.compra.CuentaBancariaDTO;
import pe.com.builderp.core.facturacion.model.dto.compra.CuentaTipoDocumentoDTO;
import pe.com.builderp.core.facturacion.model.dto.compra.DetalleCompraDTO;
import pe.com.builderp.core.facturacion.model.dto.compra.DetalleOrdenCompraDTO;
import pe.com.builderp.core.facturacion.model.dto.compra.OrdenCompraDTO;
import pe.com.builderp.core.facturacion.model.dto.compra.ProveedorDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.DetallePedidoDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.PedidoDTO;
import pe.com.builderp.core.facturacion.model.jpa.compra.OrdenCompra;
import pe.com.builderp.core.facturacion.model.jpa.venta.Pedido;
import pe.com.edu.siaa.core.model.type.AccionType;

/**
 * La Class CompraServiceLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:32:52 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface CompraServiceLocal{
	
	List<DetalleCompraDTO> listarDetalleCompra(DetalleCompraDTO detalleCompra) throws Exception;
	
	String generarReporteRegistroCompra(String userName,String idEntidad,Long idLibro,Long idSubLibro, Date fechaAsientoDesde, Date fechaAsientoHasta) throws Exception;
	/**
	 * Controlador accion compra.
	 *
	 * @param compra el compra
	 * @return the compra
	 * @throws Exception the exception
	 */
	CompraDTO registrarCompra(CompraDTO compra) throws Exception; 
	
	/**
	 * Listar compra.
	 *
	 * @param compra el compra
	 * @return the list
	 * @throws Exception the exception
	 */
	List<CompraDTO> listarCompra(CompraDTO compra) throws Exception;
	
	/**
	 * contar lista compra.
	 *
	 * @param compra el compra
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarCompra(CompraDTO compra);
	
	/**
	 * Controlador accion contacto proveedor.
	 *
	 * @param contactoProveedor el contacto proveedor
	 * @param accionType el accion type
	 * @return the contacto proveedor
	 * @throws Exception the exception
	 */
	ContactoProveedorDTO controladorAccionContactoProveedor(ContactoProveedorDTO contactoProveedor,AccionType accionType) throws Exception; 
	
	/**
	 * Listar contacto proveedor.
	 *
	 * @param contactoProveedor el contacto proveedor
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ContactoProveedorDTO> listarContactoProveedor(ContactoProveedorDTO contactoProveedor) throws Exception;
	
	/**
	 * contar lista contacto proveedor.
	 *
	 * @param contactoProveedor el contacto proveedor
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarContactoProveedor(ContactoProveedorDTO contactoProveedor);
	
	/**
	 * Controlador accion cuenta bancaria.
	 *
	 * @param cuentaBancaria el cuenta bancaria
	 * @param accionType el accion type
	 * @return the cuenta bancaria
	 * @throws Exception the exception
	 */
	CuentaBancariaDTO controladorAccionCuentaBancaria(CuentaBancariaDTO cuentaBancaria,AccionType accionType) throws Exception; 
	
	/**
	 * Listar cuenta bancaria.
	 *
	 * @param cuentaBancaria el cuenta bancaria
	 * @return the list
	 * @throws Exception the exception
	 */
	List<CuentaBancariaDTO> listarCuentaBancaria(CuentaBancariaDTO cuentaBancaria) throws Exception;
	
	/**
	 * contar lista cuenta bancaria.
	 *
	 * @param cuentaBancaria el cuenta bancaria
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarCuentaBancaria(CuentaBancariaDTO cuentaBancaria);
	
	/**
	 * Controlador accion cuenta tipo documento.
	 *
	 * @param cuentaTipoDocumento el cuenta tipo documento
	 * @param accionType el accion type
	 * @return the cuenta tipo documento
	 * @throws Exception the exception
	 */
	CuentaTipoDocumentoDTO controladorAccionCuentaTipoDocumento(CuentaTipoDocumentoDTO cuentaTipoDocumento,AccionType accionType) throws Exception; 
	
	/**
	 * Listar cuenta tipo documento.
	 *
	 * @param cuentaTipoDocumento el cuenta tipo documento
	 * @return the list
	 * @throws Exception the exception
	 */
	List<CuentaTipoDocumentoDTO> listarCuentaTipoDocumento(CuentaTipoDocumentoDTO cuentaTipoDocumento) throws Exception;
	
	/**
	 * contar lista cuenta tipo documento.
	 *
	 * @param cuentaTipoDocumento el cuenta tipo documento
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarCuentaTipoDocumento(CuentaTipoDocumentoDTO cuentaTipoDocumento);
	
	
	/**
	 * Controlador accion detalle orden compra.
	 *
	 * @param detalleOrdenCompra el detalle orden compra
	 * @param accionType el accion type
	 * @return the detalle orden compra
	 * @throws Exception the exception
	 */
	//DetalleOrdenCompraDTO controladorAccionDetalleOrdenCompra(DetalleOrdenCompraDTO detalleOrdenCompra,AccionType accionType) throws Exception; 
	DetalleOrdenCompraDTO controladorAccionDetalleOrdenCompra(DetalleOrdenCompraDTO detalleOrdenVenta,OrdenCompra ordencompra, AccionType accionType) throws Exception;
	/**
	 * Listar detalle orden compra.
	 *
	 * @param detalleOrdenCompra el detalle orden compra
	 * @return the list
	 * @throws Exception the exception
	 */
	List<DetalleOrdenCompraDTO> listarDetalleOrdenCompra(DetalleOrdenCompraDTO detalleOrdenCompra) throws Exception;
	
	/**
	 * contar lista detalle orden compra.
	 *
	 * @param detalleOrdenCompra el detalle orden compra
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarDetalleOrdenCompra(DetalleOrdenCompraDTO detalleOrdenCompra);
	
	/**
	 * Controlador accion orden compra.
	 *
	 * @param ordenCompra el orden compra
	 * @param accionType el accion type
	 * @return the orden compra
	 * @throws Exception the exception
	 */
	OrdenCompraDTO controladorAccionOrdenCompra(OrdenCompraDTO ordenCompra,AccionType accionType) throws Exception; 
	
	/**
	 * Listar orden compra.
	 *
	 * @param ordenCompra el orden compra
	 * @return the list
	 * @throws Exception the exception
	 */
	List<OrdenCompraDTO> listarOrdenCompra(OrdenCompraDTO ordenCompra) throws Exception;
	
	/**
	 * contar lista orden compra.
	 *
	 * @param ordenCompra el orden compra
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarOrdenCompra(OrdenCompraDTO ordenCompra);
	
	/**
	 * Controlador accion proveedor.
	 *
	 * @param proveedor el proveedor
	 * @param accionType el accion type
	 * @return the proveedor
	 * @throws Exception the exception
	 */
	ProveedorDTO controladorAccionProveedor(ProveedorDTO proveedor,AccionType accionType) throws Exception; 
	
	/**
	 * Listar proveedor.
	 *
	 * @param proveedor el proveedor
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ProveedorDTO> listarProveedor(ProveedorDTO proveedor) throws Exception;
	
	/**
	 * contar lista proveedor.
	 *
	 * @param proveedor el proveedor
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarProveedor(ProveedorDTO proveedor);
	
	OrdenCompraDTO registrarOrdenCompraDTO(OrdenCompraDTO ordenCompra) throws Exception;	
	
	String generarReporteOrdenCompra(String idOrdenCompra) throws Exception;
	
	void eliminarOrdenCompra(String idOrdenCompra,String userName) throws Exception;
	
	void eliminarCompra(String idCompra,String userName) throws Exception;
	
	ProveedorDTO findProveedorByNro(String nroDoc) throws Exception;

	
	
}