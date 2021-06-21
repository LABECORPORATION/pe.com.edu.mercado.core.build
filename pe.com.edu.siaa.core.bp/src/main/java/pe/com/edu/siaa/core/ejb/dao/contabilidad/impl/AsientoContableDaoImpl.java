package pe.com.edu.siaa.core.ejb.dao.contabilidad.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.contabilidad.model.vo.RegistroAsientoFiltroVO;
import pe.com.edu.siaa.core.ejb.dao.contabilidad.local.AsientoContableDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericContabilidadDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.jpa.contabilidad.AsientoContable;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class AsientoContableDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Fri Sep 08 19:44:57 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class AsientoContableDaoImpl extends  GenericContabilidadDAOImpl<String, AsientoContable> implements AsientoContableDaoLocal  {

	@Override
	public String generarNumeroAsiento(Integer anho,String idEntidad) throws Exception {
		Map<String, Object> parametros = new HashMap<String, Object>();
		String resultado = "0000000001";
		parametros.put("anho", anho);
		parametros.put("idEntidad", idEntidad);
		Query query = createQuery("select max(ac.nroCorrelativoAsiento) from AsientoContable ac where YEAR(ac.fechaCreacion) =:anho and ac.entidad.idEntidad=:idEntidad",parametros);
		query.setParameter("anho", anho);
		List<String> listLong =  query.getResultList();
		if (listLong != null && listLong.size() > 0) {
			String ultimoIdGenerado = listLong.get(0);
			if (StringUtils.isNotNullOrBlank(ultimoIdGenerado)) {
				long ultimoId = Long.parseLong(ultimoIdGenerado.trim());
				ultimoId++;
				resultado = "" + ultimoId;
			}
		}
		resultado = StringUtils.completeLeft(resultado, '0', 10);
		return resultado;
	}
	
	@Override
	public  String generarNumeroOperacion(Integer anho,String idEntidad, Long idLibro, Long idSubLibro) throws Exception {
		Map<String, Object> parametros = new HashMap<String, Object>();
		String resultado = "0000000001";
		parametros.put("anho", anho);
		parametros.put("idLibro", idLibro);
		parametros.put("idSubLibro", idSubLibro);
		parametros.put("idEntidad", idEntidad);
		Query query = createQuery("select max(ac.nroCorrelativoOperacion) from AsientoContable ac where YEAR(ac.fechaCreacion) =:anho and ac.entidad.idEntidad=:idEntidad and ac.itemByLibro.idItem=:idLibro and ac.itemBySubLibro.idItem=:idSubLibro ",parametros);
		query.setParameter("anho", anho);
		List<String> listLong =  query.getResultList();
		if (listLong != null && listLong.size() > 0) {
			String ultimoIdGenerado = listLong.get(0);
			if (StringUtils.isNotNullOrBlank(ultimoIdGenerado)) {
				long ultimoId = Long.parseLong(ultimoIdGenerado.trim());
				ultimoId++;
				resultado = "" + ultimoId;
			}
		}
		resultado = StringUtils.completeLeft(resultado, '0', 10);
		return resultado;
	}
	
    /* (non-Javadoc)
     * @see pe.com.conta.core.ejb.dao.seguridad.local.AsientoContableDaoLocal#listarAsientoContable(pe.com.conta.core.model.jpa.seguridad.AsientoContable)
     */
    @Override
    public List<AsientoContable> listarAsientoContable(RegistroAsientoFiltroVO registroVentaFiltro) throws Exception {
        Query query = generarQueryListaAsientoContable(registroVentaFiltro, false);
        query.setFirstResult(registroVentaFiltro.getStartRow());
        query.setMaxResults(registroVentaFiltro.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista AsientoContable.
     *
     * @param AsientoContableDTO el asientoContable
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaAsientoContable(RegistroAsientoFiltroVO registroVentaFiltro, boolean esContador) throws Exception {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idAsientoContable) from AsientoContable o where 1=1 ");
        } else {            
            jpaql.append(" select o from AsientoContable o left join fetch o.entidad left join fetch o.itemByLibro   ");
            jpaql.append(" left join fetch o.itemBySubLibro where 1=1 ");
        }
		if (!StringUtils.isNullOrEmpty(registroVentaFiltro.getSearch())) {
	          jpaql.append(" and upper(o.idAsientoContable) like :search ");
	          parametros.put("search", "%" + registroVentaFiltro.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(registroVentaFiltro.getFechaOperacion())) {
				jpaql.append(" and o.fechaOperacion = :fechaOperacion ");
				parametros.put("fechaOperacion", registroVentaFiltro.getFechaOperacion());
			}
			if (!StringUtils.isNullOrEmpty(registroVentaFiltro.getIdLibro())) {
				jpaql.append(" and o.itemByLibro.idItem = :itemByLibro ");
				parametros.put("itemByLibro", registroVentaFiltro.getIdLibro());
			}
			
			if (!StringUtils.isNullOrEmpty(registroVentaFiltro.getIdSubLibro())) {
				jpaql.append(" and o.itemBySubLibro.idItem = :itemBySubLibro ");
				parametros.put("itemBySubLibro", registroVentaFiltro.getIdSubLibro());
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.conta.core.ejb.dao.seguridad.local.AsientoContableDaoLocal#contarListar{entity.getClassName()}(pe.com.conta.core.model.jpa.seguridad.AsientoContableDTO)
     */
    @Override
    public int contarListarAsientoContable(RegistroAsientoFiltroVO registroVentaFiltro) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaAsientoContable(registroVentaFiltro, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.conta.core.ejb.dao.seguridad.local.AsientoContableDaoLocal#generarIdAsientoContable()
     */
    @Override
    public String generarIdAsientoContable() throws Exception {
        return UUIDUtil.generarElementUUID();
    }
   
}