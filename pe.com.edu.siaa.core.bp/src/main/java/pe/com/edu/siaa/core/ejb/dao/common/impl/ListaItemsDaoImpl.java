package pe.com.edu.siaa.core.ejb.dao.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.common.local.ListaItemsDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericDAOImpl;
import pe.com.edu.siaa.core.model.dto.common.ListaItemsDTO;
import pe.com.edu.siaa.core.model.jpa.common.ListaItems;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class ListaItemsDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 19:53:31 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class ListaItemsDaoImpl extends  GenericDAOImpl<Long, ListaItems> implements ListaItemsDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.ListaItemsDaoLocal#listarListaItems(pe.com.edu.siaa.core.model.jpa.seguridad.ListaItems)
     */  
    @Override	 
    public List<ListaItems> listarListaItems(ListaItemsDTO listaItems) {
        Query query = generarQueryListaListaItems(listaItems, false);
        query.setFirstResult(listaItems.getStartRow());
        query.setMaxResults(listaItems.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista ListaItems.
     *
     * @param ListaItemsDTO el listaItems
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaListaItems(ListaItemsDTO listaItems, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idListaItems) from ListaItems o where 1=1 ");
        } else {
            jpaql.append(" select o from ListaItems o where 1=1 ");           
        }
        
        if (!StringUtils.isNullOrEmpty(listaItems.getId())) {
      		jpaql.append(" and o.estado =:estado"); 
      		parametros.put("estado", listaItems.getId());
	    }
        
        
		if (!StringUtils.isNullOrEmpty(listaItems.getSearch())) {
	          jpaql.append(" and upper(o.descripcion) like :search ");
	          parametros.put("search", "%" + listaItems.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(listaItems.getDescripcion())) {
				jpaql.append(" and upper(o.descripcion) like :descripcion ");
				parametros.put("descripcion", "%" + listaItems.getDescripcion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(listaItems.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + listaItems.getEstado().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.ListaItemsDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.seguridad.ListaItemsDTO)
     */
	@Override
    public int contarListarListaItems(ListaItemsDTO listaItems) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaListaItems(listaItems, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.ListaItemsDaoLocal#generarIdListaItems()
     */
	 @Override
    public Long generarIdListaItems() {
        Long resultado = 1L;
        Query query = createQuery("select max(o.idListaItems) from ListaItems o", null);
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