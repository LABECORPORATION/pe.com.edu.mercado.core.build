package pe.com.builderp.core.facturacion.ejb.dao.empresa.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.hibernate.id.UUIDGenerator;

import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.RecordatorioDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.empresa.RecordatorioDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.jpa.empresa.Recordatorio;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class RecordatorioDaoImpl.
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
public class RecordatorioDaoImpl extends  GenericFacturacionDAOImpl<String, Recordatorio> implements RecordatorioDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.RecordatorioDaoLocal#listarRecordatorio(pe.com.builderp.core.facturacion.model.jpa.venta.Recordatorio)
     */  
    @Override	 
    public List<Recordatorio> listarRecordatorio(RecordatorioDTO Recordatorio) {
        Query query = generarQueryListaRecordatorio(Recordatorio, false);
        query.setFirstResult(Recordatorio.getStartRow());
        query.setMaxResults(Recordatorio.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Recordatorio.
     *
     * @param RecordatorioDTO el Recordatorio
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaRecordatorio(RecordatorioDTO Recordatorio, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idRecordatorio) from Recordatorio o where 1=1 ");
        } else {
            jpaql.append(" select o from Recordatorio o    where 1=1 ");           
        }
		if (!StringUtils.isNullOrEmpty(Recordatorio.getSearch())) {
	          jpaql.append(" and upper(o.nombre) like :search ");
	          parametros.put("search", "%" + Recordatorio.getSearch().toUpperCase() + "%");
	    } else {
 
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.RecordatorioDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.venta.RecordatorioDTO)
     */
	@Override
    public int contarListarRecordatorio(RecordatorioDTO Recordatorio) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaRecordatorio(Recordatorio, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.RecordatorioDaoLocal#generarIdRecordatorio()
     */
	 @Override
    public String generarIdRecordatorio() { 
        return UUIDUtil.generarElementUUID();
    }
   
}