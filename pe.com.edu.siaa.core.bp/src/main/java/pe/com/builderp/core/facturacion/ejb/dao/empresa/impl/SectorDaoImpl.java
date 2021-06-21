package pe.com.builderp.core.facturacion.ejb.dao.empresa.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.hibernate.id.UUIDGenerator;

import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.SectorDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.empresa.SectorDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.jpa.empresa.Sector;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class SectorDaoImpl.
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
public class SectorDaoImpl extends  GenericFacturacionDAOImpl<String, Sector> implements SectorDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.SectorDaoLocal#listarSector(pe.com.builderp.core.facturacion.model.jpa.venta.Sector)
     */  
    @Override	 
    public List<Sector> listarSector(SectorDTO Sector) {
        Query query = generarQueryListaSector(Sector, false);
        query.setFirstResult(Sector.getStartRow());
        query.setMaxResults(Sector.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Sector.
     *
     * @param SectorDTO el Sector
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaSector(SectorDTO Sector, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idSector) from Sector o where 1=1 ");
        } else {
            jpaql.append(" select o from Sector o    where 1=1 ");           
        }
        if (!StringUtils.isNullOrEmpty(Sector.getEstado())) {
	          jpaql.append(" and  o.estado =:temEstado ");
	          parametros.put("temEstado",  Sector.getEstado() );
	    } 
        
		if (!StringUtils.isNullOrEmpty(Sector.getSearch())) {
	          jpaql.append(" and (upper(o.descricpion) like :search  or upper(o.codigo) like :search) ");
	          parametros.put("search", "%" + Sector.getSearch().toUpperCase() + "%");
	    } else {
 
		}
        if (!esContador) {
            jpaql.append(" ORDER BY to_number(o.codigo,'999999') asc");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.SectorDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.venta.SectorDTO)
     */
	@Override
    public int contarListarSector(SectorDTO Sector) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaSector(Sector, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.SectorDaoLocal#generarIdSector()
     */
	 @Override
    public String generarIdSector() { 
        return UUIDUtil.generarElementUUID();
    }
	 
	 
	@Override
	public Sector findSectorByID(String idSector) throws Exception {
		Sector resultado = null;
		StringBuilder jpaql = new StringBuilder();
		jpaql.append("from Sector a left join fetch a.delegado  "); 
		jpaql.append(" where a.idSector =:idSector");
		Query query = createQuery(jpaql.toString(),null);
		query.setParameter("idSector", idSector); 
		List<Sector> listaSector = query.getResultList();
		if (listaSector != null && listaSector.size() > 0) {
			resultado = listaSector.get(0);
		}
		return resultado;	
	}
   
}