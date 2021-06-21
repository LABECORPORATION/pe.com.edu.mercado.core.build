package pe.com.edu.siaa.core.ejb.dao.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.common.local.ListaValoresDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericDAOImpl;
import pe.com.edu.siaa.core.model.dto.common.ListaValoresDTO;
import pe.com.edu.siaa.core.model.jpa.common.ListaValores;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class ListaValoresDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 19:53:48 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class ListaValoresDaoImpl extends  GenericDAOImpl<Long, ListaValores> implements ListaValoresDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.ListaValoresDaoLocal#listarListaValores(pe.com.edu.siaa.core.model.jpa.seguridad.ListaValores)
     */  
    @Override	 
    public List<ListaValores> listarListaValores(ListaValoresDTO listaValores) {
        Query query = generarQueryListaListaValores(listaValores, false);
        query.setFirstResult(listaValores.getStartRow());
        query.setMaxResults(listaValores.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista ListaValores.
     *
     * @param ListaValoresDTO el listaValores
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaListaValores(ListaValoresDTO listaValores, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idListaValores) from ListaValores o where 1=1 ");
        } else {
            jpaql.append(" select o from ListaValores o where 1=1 ");           
        }
		if (!StringUtils.isNullOrEmpty(listaValores.getSearch())) {
	          jpaql.append(" and upper(o.idListaValores) like :search ");
	          parametros.put("search", "%" + listaValores.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(listaValores.getNombre())) {
				jpaql.append(" and upper(o.nombre) like :nombre ");
				parametros.put("nombre", "%" + listaValores.getNombre().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(listaValores.getDescripcion())) {
				jpaql.append(" and upper(o.descripcion) like :descripcion ");
				parametros.put("descripcion", "%" + listaValores.getDescripcion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(listaValores.getSentenciaSql())) {
				jpaql.append(" and upper(o.sentenciaSql) like :sentenciaSql ");
				parametros.put("sentenciaSql", "%" + listaValores.getSentenciaSql().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(listaValores.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + listaValores.getEstado().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.ListaValoresDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.seguridad.ListaValoresDTO)
     */
	@Override
    public int contarListarListaValores(ListaValoresDTO listaValores) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaListaValores(listaValores, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.ListaValoresDaoLocal#generarIdListaValores()
     */
	 @Override
    public Long generarIdListaValores() {
        Long resultado = 1L;
        Query query = createQuery("select max(o.idListaValores) from ListaValores o", null);
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