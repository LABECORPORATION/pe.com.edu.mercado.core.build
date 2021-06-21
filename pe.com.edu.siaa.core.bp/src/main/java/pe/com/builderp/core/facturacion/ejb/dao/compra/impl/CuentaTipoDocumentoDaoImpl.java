package pe.com.builderp.core.facturacion.ejb.dao.compra.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.compra.local.CuentaTipoDocumentoDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.compra.CuentaTipoDocumentoDTO;
import pe.com.builderp.core.facturacion.model.jpa.compra.CuentaTipoDocumento;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.model.util.ObjectUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class CuentaTipoDocumentoDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:32:54 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class CuentaTipoDocumentoDaoImpl extends  GenericFacturacionDAOImpl<String, CuentaTipoDocumento> implements CuentaTipoDocumentoDaoLocal  {

	/* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.contabilidad.local.CuentaTipoDocumentoDaoLocal#listarCuentaTipoDocumento(pe.com.edu.siaa.core.model.jpa.contabilidad.CuentaTipoDocumento)
     */  
    @Override	 
    public List<CuentaTipoDocumento> listarCuentaTipoDocumento(CuentaTipoDocumentoDTO cuentaTipoDocumento) {
        Query query = generarQueryListaCuentaTipoDocumento(cuentaTipoDocumento, false);
        query.setFirstResult(cuentaTipoDocumento.getStartRow());
        query.setMaxResults(cuentaTipoDocumento.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista CuentaTipoDocumento.
     *
     * @param CuentaTipoDocumentoDTO el cuentaTipoDocumento
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaCuentaTipoDocumento(CuentaTipoDocumentoDTO cuentaTipoDocumento, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idCuentaTipoDocumento) from CuentaTipoDocumento o where 1=1 ");
        } else {
            jpaql.append(" select o from CuentaTipoDocumento o left join fetch o.planContable  ");
            jpaql.append(" left join fetch o.itemByTipoDocumento left join fetch o.itemByMoneda where 1=1 ");
        }
        
        jpaql.append(" and o.proveedor.idProveedor =:idProveedor ");
		parametros.put("idProveedor", ObjectUtil.objectToLong(cuentaTipoDocumento.getId()));

		if (!StringUtils.isNullOrEmpty(cuentaTipoDocumento.getSearch())) {
	          jpaql.append(" and upper(o.planContable.nombre) like :search ");
	          parametros.put("search", "%" + cuentaTipoDocumento.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(cuentaTipoDocumento.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + cuentaTipoDocumento.getEstado().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.contabilidad.local.CuentaTipoDocumentoDaoLocal#contarListarCuentaTipoDocumento(pe.com.edu.siaa.core.model.jpa.contabilidad.CuentaTipoDocumentoDTO)
     */
	@Override
    public int contarListarCuentaTipoDocumento(CuentaTipoDocumentoDTO cuentaTipoDocumento) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaCuentaTipoDocumento(cuentaTipoDocumento, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.contabilidad.local.CuentaTipoDocumentoDaoLocal#generarIdCuentaTipoDocumento()
     */
	 @Override
    public String generarIdCuentaTipoDocumento() {
        String resultado = "1";
        Query query = createQuery("select max(o.idCuentaTipoDocumento) from CuentaTipoDocumento o", null);
        List<Object> listLong =  query.getResultList();
        if (listLong != null && listLong.size() > 0 && listLong.get(0) != null)  {
            Long ultimoIdGenerado = Long.valueOf(listLong.get(0).toString());
            if (!StringUtils.isNullOrEmpty(ultimoIdGenerado)) {
                resultado = resultado + ultimoIdGenerado;
            }
        }
        return resultado;
    }   
}