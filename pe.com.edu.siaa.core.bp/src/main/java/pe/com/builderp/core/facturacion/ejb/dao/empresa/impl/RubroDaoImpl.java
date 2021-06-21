package pe.com.builderp.core.facturacion.ejb.dao.empresa.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.hibernate.id.UUIDGenerator;

import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.RubroDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.empresa.RubroDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.jpa.empresa.Rubro;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class RubroDaoImpl.
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
public class RubroDaoImpl extends  GenericFacturacionDAOImpl<String, Rubro> implements RubroDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.RubroDaoLocal#listarRubro(pe.com.builderp.core.facturacion.model.jpa.venta.Rubro)
     */  
    @Override	 
    public List<Rubro> listarRubro(String idSector) {
        Query query = generarQueryListaRubroRequerido(idSector);
        //query.setFirstResult(Rubro.getStartRow());
        //query.setMaxResults(Rubro.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Rubro.
     *
     * @param RubroDTO el Rubro
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaRubroRequerido(String idSector) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        jpaql.append(" select o from Rubro o  left join fetch  o.itemRubro where 1=1 and o.sector.idSector=:idSector ");
        parametros.put("idSector", idSector);
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }
 
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.RubroDaoLocal#generarIdRubro()
     */
	 @Override
    public String generarIdRubro() { 
        return UUIDUtil.generarElementUUID();
    }

	@Override
	public boolean eliminarRubroRequerido(String idSector) throws Exception {
    	boolean resultado = false;
    	Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        jpaql.append(" delete from Rubro o where  o.sector.idSector=:idSector ");
        parametros.put("idSector", idSector);
        Query query = createQuery(jpaql.toString(), parametros);
        query.executeUpdate();
        return resultado;
	}
   
}