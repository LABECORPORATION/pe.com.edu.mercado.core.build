package pe.com.builderp.core.facturacion.ejb.dao.empresa.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.hibernate.id.UUIDGenerator;

import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.PersonalDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.empresa.PersonalDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.jpa.empresa.Personal;
import pe.com.edu.siaa.core.model.util.ObjectUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class PersonalDaoImpl.
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
public class PersonalDaoImpl extends  GenericFacturacionDAOImpl<String, Personal> implements PersonalDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.PersonalDaoLocal#listarPersonal(pe.com.builderp.core.facturacion.model.jpa.venta.Personal)
     */  
    @Override	 
    public List<Personal> listarPersonal(PersonalDTO Personal) {
        Query query = generarQueryListaPersonal(Personal, false);
        query.setFirstResult(Personal.getStartRow());
        query.setMaxResults(Personal.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Personal.
     *
     * @param PersonalDTO el Personal
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaPersonal(PersonalDTO Personal, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idPersonal) from Personal o where 1=1 ");
        } else {
            jpaql.append(" select o from Personal o    where 1=1 ");           
        }
        if (!StringUtils.isNullOrEmpty(Personal.getEstado())) {
	          jpaql.append(" and  o.estado =:temEstado ");
	          parametros.put("temEstado",  Personal.getEstado() );
	    } 
        
        if (!StringUtils.isNullOrEmpty(Personal.getTipoPersonal())) {
	          jpaql.append(" and  o.tipoPersonal.idItem =:idItem ");
	          parametros.put("idItem", ObjectUtil.objectToLong( Personal.getTipoPersonal().getIdItem()) );
	    } 
        
        if (!StringUtils.isNullOrEmpty(Personal.getEstado())) {
	          jpaql.append(" and  o.estado =:temEstado ");
	          parametros.put("temEstado",  Personal.getEstado() );
	    } 
        
		if (!StringUtils.isNullOrEmpty(Personal.getSearch())) {
	          jpaql.append(" and upper(o.nombre) like :search ");
	          parametros.put("search", "%" + Personal.getSearch().toUpperCase() + "%");
	    } else {
 
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.PersonalDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.venta.PersonalDTO)
     */
	@Override
    public int contarListarPersonal(PersonalDTO Personal) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaPersonal(Personal, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.PersonalDaoLocal#generarIdPersonal()
     */
	 @Override
    public String generarIdPersonal() { 
        return UUIDUtil.generarElementUUID();
    }
	 
	 
 @Override
	public Personal findPersonalByNro(String nroDoc) throws Exception {
	    Personal resultado = null;
		StringBuilder jpaql = new StringBuilder();
		jpaql.append(" from Personal o left join fetch o.tipoDocumento left join fetch o.tipoPersonal  ");
		jpaql.append(" where o.nrodoc=:nroDoc");
		Query query = createQuery(jpaql.toString(),null);
		query.setParameter("nroDoc", nroDoc); 
		List<Personal> listaCliente = query.getResultList();
		if (listaCliente != null && listaCliente.size() > 0) {
			resultado = listaCliente.get(0);
		}
		return resultado;	
	}
   
}