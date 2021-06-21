package pe.com.builderp.core.facturacion.ejb.dao.empresa.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.AnhioDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.empresa.AnhioDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericDAOImpl;
import pe.com.edu.siaa.core.model.estate.EstadoAnhoSemestreState;
import pe.com.edu.siaa.core.model.jpa.empresa.Anhio;
import pe.com.edu.siaa.core.model.util.StringUtils;
 

/**
 * La Class AnhioDaoImpl.
 * <ul>
 * <li>Copyright 2020 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Wed Oct 21 20:17:19 COT 2020
 * @since BUILDERP-CORE 2.1
 */
@Stateless
public class AnhioDaoImpl extends  GenericDAOImpl<Long, Anhio> implements AnhioDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.service.admision.dao.local.AnhioDaoLocal#listarAnhio(pe.com.builderp.core.service.admision.model.jpa.Anhio)
     */  
    @Override	 
    public List<Anhio> listarAnhio(AnhioDTO anhio) {
        Query query = generarQueryListaAnhio(anhio, false);
        if (anhio.getOffset() > 0) {
        	query.setFirstResult(anhio.getStartRow());
        	query.setMaxResults(anhio.getOffset());
        }
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Anhio.
     *
     * @param AnhioDTO el anhio
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaAnhio(AnhioDTO anhio, boolean esContador) {
        Map<String, Object> parametros = new HashMap<>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idAnhio) from Anhio o where 1=1 ");
        } else {
            jpaql.append(" select o from Anhio o where 1=1 ");           
        }
		if (!StringUtils.isNullOrEmpty(anhio.getSearch())) {
	          jpaql.append(" and (upper(o.nombre) like :search or upper(o.idAnhio || '') like :search ) ");
	          parametros.put("search", "%" + anhio.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(anhio.getNombre())) {
				jpaql.append(" and upper(o.nombre) like :nombre ");
				parametros.put("nombre", "%" + anhio.getNombre().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(anhio.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + anhio.getEstado().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            jpaql.append(" ORDER BY o.idAnhio desc ");
        }
        return createQuery(jpaql.toString(), parametros);
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.service.admision.dao.local.AnhioDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.service.admision.model.jpa.AnhioDTO)
     */
	@Override
    public int contarListarAnhio(AnhioDTO anhio) {
        int resultado = 0;
        try {
            Query query = generarQueryListaAnhio(anhio, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.service.admision.dao.local.AnhioDaoLocal#generarIdAnhio()
     */
	 @Override
    public Long generarIdAnhio() {
        Long resultado = 1L;
        Query query = createQuery("select max(o.idAnhio) from Anhio o", null);
        List<Object> listLong =  query.getResultList();
        if (listLong != null && listLong.size() > 0 && listLong.get(0) != null)  {
            Long ultimoIdGenerado = Long.valueOf(listLong.get(0).toString());
            if (!StringUtils.isNullOrEmpty(ultimoIdGenerado)) {
                resultado = resultado + ultimoIdGenerado;
            }
        }
        return resultado;
    }

	@Override
	public Anhio obtenerAnioByEstado(EstadoAnhoSemestreState estadoAnhioState) throws Exception {
		Anhio resultado = null;
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("estado", estadoAnhioState.getKey());
		Query query = createQuery("from Anhio a where   a.estado =:estado",parametros);
	
		List<Anhio> listaAnhio = query.getResultList();
		if (listaAnhio != null && listaAnhio.size() > 0) {
			resultado = listaAnhio.get(0);
		} 
		return resultado;
	}
   
}