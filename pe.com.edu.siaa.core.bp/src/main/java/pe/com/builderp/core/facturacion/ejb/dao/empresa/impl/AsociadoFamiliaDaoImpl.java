package pe.com.builderp.core.facturacion.ejb.dao.empresa.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map; 

import javax.ejb.Stateless;
import javax.persistence.Query;
  
import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.AsociadoFamiliaDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.empresa.AsociadoFamiliaDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.jpa.empresa.Asociado;
import pe.com.edu.siaa.core.model.jpa.empresa.AsociadoFamilia;
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
public class AsociadoFamiliaDaoImpl extends  GenericFacturacionDAOImpl<String, AsociadoFamilia> implements AsociadoFamiliaDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.AsociadoFamiliaDaoLocal#listarAsociadoFamilia(pe.com.builderp.core.facturacion.model.jpa.venta.AsociadoFamilia)
     */  
    @Override	 
    public List<AsociadoFamilia> listarAsociadoFamilia(AsociadoFamiliaDTO AsociadoFamilia) {
        Query query = generarQueryListaAsociadoFamilia(AsociadoFamilia, false);
        query.setFirstResult(AsociadoFamilia.getStartRow());
        query.setMaxResults(AsociadoFamilia.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista AsociadoFamilia.
     *
     * @param AsociadoFamiliaDTO el AsociadoFamilia
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaAsociadoFamilia(AsociadoFamiliaDTO AsociadoFamilia, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idAsociadoFamilia) from AsociadoFamilia o where 1=1 ");
        } else {
            jpaql.append(" select o from AsociadoFamilia o    where 1=1 ");           
        } 
        
        jpaql.append(" and o.asociado.idAsociado = :idAsociado ");
      	parametros.put("idAsociado", AsociadoFamilia.getId() + "");
        
		if (!StringUtils.isNullOrEmpty(AsociadoFamilia.getSearch())) {
	          jpaql.append(" and upper(o.nombre) like :search ");
	          parametros.put("search", "%" + AsociadoFamilia.getSearch().toUpperCase() + "%");
	    } else {
 
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.AsociadoFamiliaDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.venta.AsociadoFamiliaDTO)
     */
	@Override
    public int contarListarAsociadoFamilia(AsociadoFamiliaDTO AsociadoFamilia) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaAsociadoFamilia(AsociadoFamilia, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.AsociadoFamiliaDaoLocal#generarIdAsociadoFamilia()
     */
	 @Override
    public String generarIdAsociadoFamilia() { 
        return UUIDUtil.generarElementUUID();
    }
   
}