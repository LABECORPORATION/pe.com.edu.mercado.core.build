package pe.com.builderp.core.facturacion.ejb.dao.empresa.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.hibernate.id.UUIDGenerator;

import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.PuestoDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.empresa.PuestoDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.jpa.empresa.DetControlPago;
import pe.com.edu.siaa.core.model.jpa.empresa.Puesto;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class PuestoDaoImpl.
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
public class PuestoDaoImpl extends  GenericFacturacionDAOImpl<String, Puesto> implements PuestoDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.PuestoDaoLocal#listarPuesto(pe.com.builderp.core.facturacion.model.jpa.venta.Puesto)
     */  
    @Override	 
    public List<Puesto> listarPuesto(PuestoDTO Puesto) {
        Query query = generarQueryListaPuesto(Puesto, false);
        query.setFirstResult(Puesto.getStartRow());
        query.setMaxResults(Puesto.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Puesto.
     *
     * @param PuestoDTO el Puesto
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaPuesto(PuestoDTO Puesto, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idPuesto) from Puesto o where 1=1 ");
        } else {
            jpaql.append(" select o from Puesto o  left join fetch o.sector left join fetch o.asociado   where 1=1 ");           
        }
        if (!StringUtils.isNullOrEmpty(Puesto.getEstado())) {
	          jpaql.append(" and  o.estado =:temEstado ");
	          parametros.put("temEstado",  Puesto.getEstado() );
	    } 
        
		if (!StringUtils.isNullOrEmpty(Puesto.getSearch())) {
			  jpaql.append(" and (TRANSLATE(UPPER(o.codigo || ' ' || o.descripcion ), :discriminaTildeMAC, :discriminaTildeMAT) like UPPER(translate(:search, :discriminaTildeMIC, :discriminaTildeMIT)) or TRANSLATE(UPPER(o.asociado.nombre || ' ' || o.asociado.apellidoPaterno ), :discriminaTildeMAC, :discriminaTildeMAT) like UPPER(translate(:search, :discriminaTildeMIC, :discriminaTildeMIT)) or (upper(o.codigo) like :search) or (upper(o.descripcion) like :search) )");
			  parametros.putAll(obtenerParametroDiscriminarTilde());
	          parametros.put("search", "%" + Puesto.getSearch().toUpperCase() + "%");
	    } else {
 
		}
        if (!esContador) {
            jpaql.append(" ORDER BY to_number(o.sector.codigo,'9') asc  ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.PuestoDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.venta.PuestoDTO)
     */
	@Override
    public int contarListarPuesto(PuestoDTO Puesto) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaPuesto(Puesto, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.PuestoDaoLocal#generarIdPuesto()
     */
	 @Override
    public String generarIdPuesto() { 
        return UUIDUtil.generarElementUUID();
    }
	 
	 
	@Override
	public Puesto findByPuesto(String idPuesto) throws Exception {
		Puesto resultado = null;
		StringBuilder jpaql = new StringBuilder();
		jpaql.append("from Puesto a left join fetch a.itemCondicion ");
		jpaql.append(" where a.idPuesto =:idPuesto");
		Query query = createQuery(jpaql.toString(),null);
		query.setParameter("idPuesto", idPuesto);
		List<Puesto> listaAlumno = query.getResultList();
		if (listaAlumno != null && listaAlumno.size() > 0) {
			resultado = listaAlumno.get(0);
		}
		return resultado;	
	}
   
}