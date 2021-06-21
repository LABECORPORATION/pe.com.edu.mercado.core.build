package pe.com.edu.siaa.core.ejb.dao.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.common.local.ItemDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericDAOImpl;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO;
import pe.com.edu.siaa.core.model.estate.EstadoGeneralState;
import pe.com.edu.siaa.core.model.jpa.common.Item;
import pe.com.edu.siaa.core.model.type.ListaItemType;
import pe.com.edu.siaa.core.model.util.ObjectUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class ItemDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 19:53:32 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class ItemDaoImpl extends  GenericDAOImpl<Long, Item> implements ItemDaoLocal  {

	
	@Override
	public List<Item> listarItem() throws Exception {
		Query query = createQuery("from Item item left join fetch item.listaItems where  item.estado=:estadoActivo order by item.listaItems.idListaItems,item.codigo, item.nombre", null);
		query.setParameter("estadoActivo", EstadoGeneralState.ACTIVO.getKey());
		List<Item> resultado = query.getResultList();
		return resultado;
	}
	
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.ItemDaoLocal#listarItem(pe.com.edu.siaa.core.model.jpa.seguridad.Item)
     */  
    @Override	 
    public List<Item> listarItem(ItemDTO item) {
        Query query = generarQueryListaItem(item, false);
        query.setFirstResult(item.getStartRow());
        query.setMaxResults(item.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Item.
     *
     * @param ItemDTO el item
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaItem(ItemDTO item, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idItem) from Item o where 1=1 ");
        } else {
            jpaql.append(" select o from Item o where 1=1 ");           
        }
        
        if (!StringUtils.isNullOrEmpty(item.getId())) {
      		jpaql.append(" and o.listaItems.idListaItems = :idListaItem ");
      		parametros.put("idListaItem",ObjectUtil.objectToLong(item.getId()));
	    }
         
         if (!StringUtils.isNullOrEmptyNumeric(item.getIdItem())) {
          	  jpaql.append(" and o.idItem = :idItem ");
  	          parametros.put("idItem", item.getIdItem());
  	     }
         
         if (!StringUtils.isNullOrEmpty(item.getSearch())) { 
        	 /* item.setSearch(item.getSearch().replace(" ", "&")); 
	          jpaql.append(" and fts('simple', o.nombre,o.nombre, :search) = true  ");
	          parametros.put("search", item.getSearch().toUpperCase()+":*"); */
		     jpaql.append(" and upper(o.nombre) like :search ");
	    	 parametros.put("search", "%" + item.getSearch().toUpperCase() + "%");  
         }
		
         if (!StringUtils.isNullOrEmpty(item.getDescripcion())) {
			 jpaql.append(" and upper(o.descripcion) like :descripcion ");
			 parametros.put("descripcion", "%" + item.getDescripcion().toUpperCase() + "%");
		 }
		 if (!StringUtils.isNullOrEmpty(item.getNombre())) {
			jpaql.append(" and upper(o.nombre) like :nombre ");
			parametros.put("nombre", "%" + item.getNombre().toUpperCase() + "%");
		 }
		 if (!StringUtils.isNullOrEmptyNumeric(item.getCodigo())) {
			jpaql.append(" and o.codigo = :codigo ");
			parametros.put("codigo", item.getCodigo());
		 }
		 if (!StringUtils.isNullOrEmpty(item.getCodigoExterno())) {
			jpaql.append(" and upper(o.codigoExterno) like :codigoExterno ");
			parametros.put("codigoExterno", "%" + item.getCodigoExterno().toUpperCase() + "%");
		 }
		 if (!StringUtils.isNullOrEmpty(item.getEstado())) {
			jpaql.append(" and upper(o.estado) like :estado ");
			parametros.put("estado", "%" + item.getEstado().toUpperCase() + "%");
		}
		
		 if (!StringUtils.isNullOrEmpty(item.getTipo())) {
			 if ("LIBRO".equalsIgnoreCase(item.getTipo())) {
				 jpaql.append(" and o.idItem in (select it.item.idItem from ConfigDependenciaItem it where it.estado =:estadoIT ) ");
				 parametros.put("estadoIT", EstadoGeneralState.ACTIVO.getKey());
			 } else if ("SUBLIBRO".equalsIgnoreCase(item.getTipo())) {
				 jpaql.append(" and o.idItem in (select it.itemHijo.idItem from ConfigDependenciaItem it where it.estado =:estadoIT and it.item.idItem =:idItemPadreView ) ");
				 parametros.put("idItemPadreView", ObjectUtil.objectToLong(item.getIdPadreView()));
				 parametros.put("estadoIT", EstadoGeneralState.ACTIVO.getKey());
			 }
 	     }
		 
        if (!esContador) {
            jpaql.append(" ORDER BY o.idItem desc ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.ItemDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.seguridad.ItemDTO)
     */
	@Override
    public int contarListarItem(ItemDTO item) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaItem(item, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.ItemDaoLocal#generarIdItem()
     */
	 @Override
    public Long generarIdItem() {
        Long resultado = 1L;
        Query query = createQuery("select max(o.idItem) from Item o", null);
        List<Object> listLong =  query.getResultList();
        if (listLong != null && listLong.size() > 0 && listLong.get(0) != null)  {
            Long ultimoIdGenerado = Long.valueOf(listLong.get(0).toString());
            if (!StringUtils.isNullOrEmpty(ultimoIdGenerado)) {
                resultado = resultado + ultimoIdGenerado;
            }
        }
        return resultado;
    }
	 
	 
	 //agre
		@Override
		public Item obteneItemId(ListaItemType itemState) throws Exception {
			Item resultado = null;
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("idItem", itemState.getKey());
			Query query = createQuery("from Item a where   a.idItem =:idItem",parametros);
		
			List<Item> listaItem = query.getResultList();
			if (listaItem != null && listaItem.size() > 0) {
				resultado = listaItem.get(0);
			} 
			return resultado;
		}
   
}