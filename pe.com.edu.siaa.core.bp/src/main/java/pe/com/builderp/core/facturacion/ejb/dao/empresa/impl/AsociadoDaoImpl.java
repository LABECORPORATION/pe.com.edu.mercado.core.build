package pe.com.builderp.core.facturacion.ejb.dao.empresa.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map; 

import javax.ejb.Stateless;
import javax.persistence.Query;
 
import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.AsociadoDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.empresa.AsociadoDTO;
import pe.com.builderp.core.facturacion.model.jpa.compra.Proveedor;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.jpa.empresa.Asociado;
import pe.com.edu.siaa.core.model.util.ObjectUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class AsociadoDaoImpl.
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
public class AsociadoDaoImpl extends  GenericFacturacionDAOImpl<String, Asociado> implements AsociadoDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.AsociadoDaoLocal#listarAsociado(pe.com.builderp.core.facturacion.model.jpa.venta.Asociado)
     */  
    @Override	 
    public List<Asociado> listarAsociado(AsociadoDTO Asociado) {
        Query query = generarQueryListaAsociado(Asociado, false);
        query.setFirstResult(Asociado.getStartRow());
        query.setMaxResults(Asociado.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Asociado.
     *
     * @param AsociadoDTO el Asociado
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaAsociado(AsociadoDTO Asociado, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idAsociado) from Asociado o where 1=1 ");
        } else {
            jpaql.append(" select o from Asociado o left join fetch o.itemByTipoAsociado  where 1=1 ");           
        }
		if (!StringUtils.isNullOrEmpty(Asociado.getId())) {
	          jpaql.append(" and  o.itemByTipoAsociado.idItem =:itemByTipoAsociado ");
	          parametros.put("itemByTipoAsociado", ObjectUtil.objectToLong(Asociado.getId())  );
	    }
        if (!StringUtils.isNullOrEmpty(Asociado.getEstado())) {
	          jpaql.append(" and  o.estado =:temEstado ");
	          parametros.put("temEstado",  Asociado.getEstado() );
	    } 
        
		if (!StringUtils.isNullOrEmpty(Asociado.getSearch())) { 
			  jpaql.append(" and (TRANSLATE(UPPER(o.nombre || ' ' || o.apellidoPaterno || ' ' || o.apellidoMaterno ), :discriminaTildeMAC, :discriminaTildeMAT) like UPPER(translate(:search, :discriminaTildeMIC, :discriminaTildeMIT)) or (upper(o.ruc) like :search) or (upper(o.nrodoc) like :search) )");
			  parametros.putAll(obtenerParametroDiscriminarTilde());
			  parametros.put("search", "%" + Asociado.getSearch().toUpperCase() + "%");
	    } else {
 
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.AsociadoDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.venta.AsociadoDTO)
     */
	@Override
    public int contarListarAsociado(AsociadoDTO Asociado) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaAsociado(Asociado, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.AsociadoDaoLocal#generarIdAsociado()
     */
	 @Override
    public String generarIdAsociado() { 
        return UUIDUtil.generarElementUUID();
    }
	 
	
	 
	 @Override
	public Asociado findAsociadoByNro(String nroDoc) throws Exception {
		 Asociado resultado = null;
			StringBuilder jpaql = new StringBuilder();
			jpaql.append(" from Asociado o left join fetch o.itemByTipoAsociado   ");
			jpaql.append(" where o.nrodoc=:nroDoc");
			Query query = createQuery(jpaql.toString(),null);
			query.setParameter("nroDoc", nroDoc); 
			List<Asociado> listaCliente = query.getResultList();
			if (listaCliente != null && listaCliente.size() > 0) {
				resultado = listaCliente.get(0);
			}
			return resultado;	
	}
   
}