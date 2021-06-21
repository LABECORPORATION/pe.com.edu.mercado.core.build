package pe.com.edu.siaa.core.ejb.dao.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.common.local.UbigeoDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericDAOImpl;
import pe.com.edu.siaa.core.model.dto.common.UbigeoDTO;
import pe.com.edu.siaa.core.model.jpa.common.Ubigeo;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class UbigeoDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 19:53:42 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class UbigeoDaoImpl extends  GenericDAOImpl<String, Ubigeo> implements UbigeoDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.UbigeoDaoLocal#listarUbigeo(pe.com.edu.siaa.core.model.jpa.seguridad.Ubigeo)
     */  
    @Override	 
    public List<Ubigeo> listarUbigeo(UbigeoDTO ubigeo) {
        Query query = generarQueryListaUbigeo(ubigeo, false);
        query.setFirstResult(ubigeo.getStartRow());
        query.setMaxResults(ubigeo.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Ubigeo.
     *
     * @param UbigeoDTO el ubigeo
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaUbigeo(UbigeoDTO ubigeo, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idUbigeo) from Ubigeo o where 1=1 ");
        } else {
            jpaql.append(" select o from Ubigeo o left join fetch o.ubigeoByDependencia where 1=1 ");           
        }
        boolean ejecutoBusqueda = false;
        if (!StringUtils.isNullOrEmpty(ubigeo.getId())) {//comodin
        	jpaql.append(" and o.ubigeoByDependencia.idUbigeo =:idUbigeoDependencia ");
			parametros.put("idUbigeoDependencia", ubigeo.getId() + "");
			ejecutoBusqueda = true;
        }
        if (!StringUtils.isNullOrEmpty(ubigeo.getIdPadreView())) {//comodin2
        	jpaql.append(" and o.idUbigeo =:idComodin ");
			parametros.put("idComodin", ubigeo.getIdPadreView() + "");
        }
        
		if (!StringUtils.isNullOrEmpty(ubigeo.getSearch())) {
	          jpaql.append(" and upper(o.descripcion) like :search ");
	          parametros.put("search", "%" + ubigeo.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(ubigeo.getDescripcion())) {
				jpaql.append(" and upper(o.descripcion) like :descripcion ");
				parametros.put("descripcion", "%" + ubigeo.getDescripcion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(ubigeo.getTipo())) {
				jpaql.append(" and upper(o.tipo) like :tipo ");
				parametros.put("tipo", "%" + ubigeo.getTipo().toUpperCase() + "%");
			}
		}
		if (!ejecutoBusqueda) {
			jpaql.append(" and o.ubigeoByDependencia is  null ");
		}
        if (!esContador) {
            jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.UbigeoDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.seguridad.UbigeoDTO)
     */
	@Override
    public int contarListarUbigeo(UbigeoDTO ubigeo) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaUbigeo(ubigeo, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.UbigeoDaoLocal#generarIdUbigeo()
     */
	 @Override
    public String generarIdUbigeo() {
        String resultado = "1";
        Query query = createQuery("select max(o.idUbigeo) from Ubigeo o", null);
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