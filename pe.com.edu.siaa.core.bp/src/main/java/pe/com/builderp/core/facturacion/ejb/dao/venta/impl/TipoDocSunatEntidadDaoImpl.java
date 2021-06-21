package pe.com.builderp.core.facturacion.ejb.dao.venta.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.venta.local.TipoDocSunatEntidadDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.venta.TipoDocSunatEntidadDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.TipoDocSunatEntidad;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class TipoDocSunatEntidadDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:32:58 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class TipoDocSunatEntidadDaoImpl extends  GenericFacturacionDAOImpl<String, TipoDocSunatEntidad> implements TipoDocSunatEntidadDaoLocal  {

	

	@Override
	public  String actualizarTipoDocSunat(Long idTipoDocSunat,String idEntidad,String nroDoc,String serie) throws Exception {
		String resultado = "";
		TipoDocSunatEntidad tipoDocSunat = obtenberTipoDocSunatEntidad(idTipoDocSunat,idEntidad,serie);
		if (!StringUtils.isNotNullOrBlank(nroDoc)) {
			//generar el nro doc
			resultado = (tipoDocSunat.getCorrela().trim());
			Long nroDocCalc = Long.parseLong(tipoDocSunat.getCorrela().trim());
			nroDocCalc++;
			tipoDocSunat.setCorrela(StringUtils.completeLeft(nroDocCalc, '0', tipoDocSunat.getCorrela().trim().length()));
			update(tipoDocSunat);
		} else {
			//actualizando correla
			/*Long nroDocCalc = Long.parseLong(nroDoc.trim());
			nroDocCalc++;
			tipoDocSunat.setCorrela(StringUtils.completeLeft(nroDocCalc, '0', nroDoc.trim().length()));
			update(tipoDocSunat);*/
		}
		return resultado;
	}
	@Override
	public TipoDocSunatEntidad obtenberTipoDocSunatEntidad(Long idTipoDocSunat,String idEntidad,String serie) throws Exception {
		TipoDocSunatEntidad resultado = new TipoDocSunatEntidad();
		List<TipoDocSunatEntidad> resultadoList = new ArrayList<TipoDocSunatEntidad>();
		Map<String,Object> parametro = new HashMap<String, Object>();
		StringBuilder jpaql = new StringBuilder();
		jpaql.append(" select o from TipoDocSunatEntidad o left join fetch o.entidad  left join fetch o.itemByTipoDocSunat ");
		jpaql.append(" where o.itemByTipoDocSunat.idItem = :idTipoDocSunat ");
		jpaql.append(" and o.entidad.idEntidad = :idEntidad and o.serie = :serie");
		parametro.put("idTipoDocSunat", idTipoDocSunat);
		parametro.put("idEntidad", idEntidad);
		parametro.put("serie", serie);
		Query query = createQuery(jpaql.toString(), parametro);
		resultadoList = query.getResultList();		
		if (resultadoList != null && resultadoList.size() > 0) {
			resultado = resultadoList.get(0);
		}
		return resultado;
	}
	

	
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.TipoDocSunatEntidadDaoLocal#listarTipoDocSunatEntidad(pe.com.edu.siaa.core.model.jpa.seguridad.TipoDocSunatEntidad)
     */  
    @Override	 
    public List<TipoDocSunatEntidad> listarTipoDocSunatEntidad(TipoDocSunatEntidadDTO tipoDocSunatEntidad) {
        Query query = generarQueryListaTipoDocSunatEntidad(tipoDocSunatEntidad, false);
        query.setFirstResult(tipoDocSunatEntidad.getStartRow());
        query.setMaxResults(tipoDocSunatEntidad.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista TipoDocSunatEntidad.
     *
     * @param TipoDocSunatEntidadDTO el tipoDocSunatEntidad
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaTipoDocSunatEntidad(TipoDocSunatEntidadDTO tipoDocSunatEntidad, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idTipoDocSunatEntidad) from TipoDocSunatEntidad o where 1=1 ");
        } else {
            jpaql.append(" select o from TipoDocSunatEntidad o left join fetch o.itemByTipoDocSunat where 1=1 ");           
        }
        if (!StringUtils.isNullOrEmpty(tipoDocSunatEntidad.getIdEntidadSelect())) {
	        jpaql.append(" and o.entidad.idEntidad =:idEntidad ");
	        parametros.put("idEntidad", tipoDocSunatEntidad.getIdEntidadSelect());
        }
		if (!StringUtils.isNullOrEmpty(tipoDocSunatEntidad.getSearch())) {
	          jpaql.append(" and upper(o.itemByTipoDocSunat.nombre) like :search ");
	          parametros.put("search", "%" + tipoDocSunatEntidad.getSearch().toUpperCase() + "%");
	    } else {
			if (tipoDocSunatEntidad.getItemByTipoDocSunat() != null && !StringUtils.isNullOrEmptyNumeric(tipoDocSunatEntidad.getItemByTipoDocSunat().getIdItem())) {
				jpaql.append(" and o.itemByTipoDocSunat.idItem = :idTipoDocSunat ");
				parametros.put("idTipoDocSunat", tipoDocSunatEntidad.getItemByTipoDocSunat().getIdItem());
			}
			if (!StringUtils.isNullOrEmpty(tipoDocSunatEntidad.getCorrela())) {
				jpaql.append(" and upper(o.correla) like :correla ");
				parametros.put("correla", "%" + tipoDocSunatEntidad.getCorrela().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.TipoDocSunatEntidadDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.seguridad.TipoDocSunatEntidadDTO)
     */
	@Override
    public int contarListarTipoDocSunatEntidad(TipoDocSunatEntidadDTO tipoDocSunatEntidad) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaTipoDocSunatEntidad(tipoDocSunatEntidad, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.TipoDocSunatEntidadDaoLocal#generarIdTipoDocSunatEntidad()
     */
	 @Override
    public String generarIdTipoDocSunatEntidad() {
        return UUIDUtil.generarElementUUID();
    }
   
}