package pe.com.builderp.core.facturacion.ejb.dao.empresa.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.empresa.CuotaConceptoDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.jpa.empresa.CuotaConcepto; 

/**
 * La Class CuotaConceptoDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 11:25:02 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface CuotaConceptoDaoLocal  extends GenericDAOLocal<String,CuotaConcepto> {
	 
	/**
	 * Listar cuota concepto.
	 *
	 * @param cuotaConcepto el cuota conceptoDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<CuotaConcepto> listarCuotaConcepto(CuotaConceptoDTO cuotaConcepto) throws Exception;
	
	/**
	 * contar lista cuota concepto.
	 *
	 * @param cuotaConcepto el cuota concepto
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarCuotaConcepto(CuotaConceptoDTO cuotaConcepto);
	/**
	 * Generar id cuotaConcepto.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdCuotaConcepto() throws Exception;
	
	List<CuotaConcepto> listaCuotaConceptoId(String idcuotaconcepto) throws Exception;
}