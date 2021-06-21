package pe.com.builderp.core.facturacion.ejb.dao.venta.impl;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.venta.local.GuiaRemisionDaoLocal;
import pe.com.builderp.core.facturacion.model.jpa.venta.GuiaRemision;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;
import pe.com.builderp.core.facturacion.model.dto.venta.GuiaRemisionDTO;


/**
 * La Class GuiaRemisionDaoImpl.
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
public class GuiaRemisionDaoImpl extends  GenericFacturacionDAOImpl<String, GuiaRemision> implements GuiaRemisionDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.GuiaRemisionDaoLocal#listarGuiaRemision(pe.com.builderp.core.facturacion.model.jpa.venta.GuiaRemision)
     */  
    @Override	 
    public List<GuiaRemision> listarGuiaRemision(GuiaRemisionDTO guiaRemision) {
        Query query = generarQueryListaGuiaRemision(guiaRemision, false);
        query.setFirstResult(guiaRemision.getStartRow());
        query.setMaxResults(guiaRemision.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista GuiaRemision.
     *
     * @param GuiaRemisionDTO el guiaRemision
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaGuiaRemision(GuiaRemisionDTO guiaRemision, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idGuiaRemision) from GuiaRemision o where 1=1 ");
        } else {
            jpaql.append(" select o from GuiaRemision o where 1=1 ");           
        }
		if (!StringUtils.isNullOrEmpty(guiaRemision.getSearch())) {
	          jpaql.append(" and upper(o.idGuiaRemision) like :search ");
	          parametros.put("search", "%" + guiaRemision.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(guiaRemision.getFechaInicioTraslado())) {
				jpaql.append(" and o.fechaInicioTraslado = :fechaInicioTraslado ");
				parametros.put("fechaInicioTraslado", guiaRemision.getFechaInicioTraslado());
			}
			if (!StringUtils.isNullOrEmpty(guiaRemision.getMotivoTraslado())) {
				jpaql.append(" and upper(o.motivoTraslado) like :motivoTraslado ");
				parametros.put("motivoTraslado", "%" + guiaRemision.getMotivoTraslado().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(guiaRemision.getModalidadTrasporte())) {
				jpaql.append(" and upper(o.modalidadTrasporte) like :modalidadTrasporte ");
				parametros.put("modalidadTrasporte", "%" + guiaRemision.getModalidadTrasporte().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmptyNumeric(guiaRemision.getPesoTotal())) {
				jpaql.append(" and o.pesoTotal = :pesoTotal ");
				parametros.put("pesoTotal", guiaRemision.getPesoTotal());
			}
			if (!StringUtils.isNullOrEmpty(guiaRemision.getUnidadMedidad())) {
				jpaql.append(" and upper(o.unidadMedidad) like :unidadMedidad ");
				parametros.put("unidadMedidad", "%" + guiaRemision.getUnidadMedidad().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(guiaRemision.getDireccionPuntoPartida())) {
				jpaql.append(" and upper(o.direccionPuntoPartida) like :direccionPuntoPartida ");
				parametros.put("direccionPuntoPartida", "%" + guiaRemision.getDireccionPuntoPartida().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(guiaRemision.getDireccionPuntoLLegada())) {
				jpaql.append(" and upper(o.direccionPuntoLLegada) like :direccionPuntoLLegada ");
				parametros.put("direccionPuntoLLegada", "%" + guiaRemision.getDireccionPuntoLLegada().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            jpaql.append(" ORDER BY  o.fechaInicioTraslado desc ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.GuiaRemisionDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.venta.GuiaRemisionDTO)
     */
	@Override
    public int contarListarGuiaRemision(GuiaRemisionDTO guiaRemision) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaGuiaRemision(guiaRemision, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.GuiaRemisionDaoLocal#generarIdGuiaRemision()
     */
	 @Override
    public String generarIdGuiaRemision() {
		 return UUIDUtil.generarElementUUID();
       /* String resultado = "1";
        Query query = createQuery("select max(o.idGuiaRemision) from GuiaRemision o", null);
        List<Object> listLong =  query.getResultList();
        if (listLong != null && listLong.size() > 0 && listLong.get(0) != null)  {
            Long ultimoIdGenerado = Long.valueOf(listLong.get(0).toString());
            if (!StringUtils.isNullOrEmpty(ultimoIdGenerado)) {
                resultado = resultado + ultimoIdGenerado;
            }
        }
        return resultado;*/
    }
   
}