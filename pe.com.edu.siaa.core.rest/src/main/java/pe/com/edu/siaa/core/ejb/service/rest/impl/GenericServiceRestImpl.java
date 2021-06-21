package pe.com.edu.siaa.core.ejb.service.rest.impl;

import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class SeguridadServiceRestImpl.
 * <ul>
 * <li>Copyright 2016 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Sat Oct 08 23:07:10 COT 2016
 * @since ERP-CRIS 1.0
 */

public class GenericServiceRestImpl {
   
   
	 protected <T> ResultadoRestVO<T> parsearResultadoError(Exception e,ResultadoRestVO<T> resultado) {
    	resultado.setError(true);
		resultado.setCodigoError(e.getLocalizedMessage());
		resultado.setMensajeError(e.getMessage() +" --> " + e.toString());
		return resultado;
    }
	 
	 protected Map<String,Object> transferUriInfoMap(@Context UriInfo info) {
			return TransferDataObjectUtil.transferObjetoEntityGetRestMap(info);
	}
}