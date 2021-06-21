package pe.com.builderp.core.facturacion.ejb.dao.empresa.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map; 

import javax.ejb.Stateless;
import javax.persistence.Query;
 
import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.ActividadDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.empresa.ActividadDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.jpa.empresa.Actividad;
import pe.com.edu.siaa.core.model.util.ObjectUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class ActividadDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:32:55 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class ActividadDaoImpl extends  GenericFacturacionDAOImpl<String, Actividad> implements ActividadDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.ActividadDaoLocal#listarActividad(pe.com.builderp.core.facturacion.model.jpa.venta.Actividad)
     */  
    @Override	 
    public List<Actividad> listarActividad(ActividadDTO Actividad) {
        Query query = generarQueryListaActividad(Actividad, false);
        query.setFirstResult(Actividad.getStartRow());
        query.setMaxResults(Actividad.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Actividad.
     *
     * @param ActividadDTO el Actividad
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaActividad(ActividadDTO Actividad, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idActividad) from Actividad o where 1=1 ");
        } else {
            jpaql.append(" select o from Actividad o left join fetch o.responsable left join fetch o.tipoActividad left join fetch o.anhio  where 1=1 ");           
        }
		if (!StringUtils.isNullOrEmpty(Actividad.getId())) {
	          jpaql.append(" and  o.tipoActividad.idItem =:itemByTipoActividad ");
	          parametros.put("itemByTipoActividad", ObjectUtil.objectToLong(Actividad.getId())  );
	    }
        if (!StringUtils.isNullOrEmpty(Actividad.getEstado())) {
	          jpaql.append(" and  o.estado =:temEstado ");
	          parametros.put("temEstado",  Actividad.getEstado() );
	    } 
        
		if (!StringUtils.isNullOrEmpty(Actividad.getSearch())) {
	          jpaql.append(" and upper(o.nombre) like :search ");
	          parametros.put("search", "%" + Actividad.getSearch().toUpperCase() + "%");
	    } else {
 
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.ActividadDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.venta.ActividadDTO)
     */
	@Override
    public int contarListarActividad(ActividadDTO Actividad) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaActividad(Actividad, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.ActividadDaoLocal#generarIdActividad()
     */
	 @Override
    public String generarIdActividad() { 
        return UUIDUtil.generarElementUUID();
    }
   
}