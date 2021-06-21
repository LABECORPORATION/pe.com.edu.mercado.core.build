package pe.com.builderp.core.facturacion.ejb.dao.venta.impl;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.venta.local.VehiculoDaoLocal;
import pe.com.builderp.core.facturacion.model.jpa.venta.Vehiculo;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;
import pe.com.builderp.core.facturacion.model.dto.venta.VehiculoDTO;




/**
 * La Class VehiculoDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu May 02 10:20:30 COT 2019
 * @since SIAA-CORE 2.1
 */
@Stateless
public class VehiculoDaoImpl extends  GenericFacturacionDAOImpl<String, Vehiculo> implements VehiculoDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.VehiculoDaoLocal#listarVehiculo(pe.com.builderp.core.facturacion.model.jpa.venta.Vehiculo)
     */  
    @Override	 
    public List<Vehiculo> listarVehiculo(VehiculoDTO vehiculo) {
        Query query = generarQueryListaVehiculo(vehiculo, false);
        query.setFirstResult(vehiculo.getStartRow());
        query.setMaxResults(vehiculo.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Vehiculo.
     *
     * @param VehiculoDTO el vehiculo
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaVehiculo(VehiculoDTO vehiculo, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idVehiculo) from Vehiculo o where 1=1 ");
        } else {
            jpaql.append(" select o from Vehiculo o where 1=1 ");           
        }
		if (!StringUtils.isNullOrEmpty(vehiculo.getSearch())) {
	          jpaql.append(" and upper(o.idVehiculo) like :search ");
	          parametros.put("search", "%" + vehiculo.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(vehiculo.getClase())) {
				jpaql.append(" and upper(o.clase) like :clase ");
				parametros.put("clase", "%" + vehiculo.getClase().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(vehiculo.getNroPlaca())) {
				jpaql.append(" and upper(o.nroPlaca) like :nroPlaca ");
				parametros.put("nroPlaca", "%" + vehiculo.getNroPlaca().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(vehiculo.getNroRuedas())) {
				jpaql.append(" and upper(o.nroRuedas) like :nroRuedas ");
				parametros.put("nroRuedas", "%" + vehiculo.getNroRuedas().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(vehiculo.getDescripcion())) {
				jpaql.append(" and upper(o.descripcion) like :descripcion ");
				parametros.put("descripcion", "%" + vehiculo.getDescripcion().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.VehiculoDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.venta.VehiculoDTO)
     */
	@Override
    public int contarListarVehiculo(VehiculoDTO vehiculo) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaVehiculo(vehiculo, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.VehiculoDaoLocal#generarIdVehiculo()
     */
	 @Override
    public String generarIdVehiculo() {
       return UUIDUtil.generarElementUUID();
    }
   
}