package pe.com.builderp.core.facturacion.ejb.dao.compra.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.compra.local.CuentaBancariaDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.compra.CuentaBancariaDTO;
import pe.com.builderp.core.facturacion.model.jpa.compra.CuentaBancaria;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericContabilidadDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.util.ObjectUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class CuentaBancariaDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:32:54 COT 2017
 * @since SIAA-CORE 2.1
 * 
 */
@Stateless
public class CuentaBancariaDaoImpl extends  GenericContabilidadDAOImpl<String, CuentaBancaria> implements CuentaBancariaDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.contabilidad.local.CuentaBancariaDaoLocal#listarCuentaBancaria(pe.com.edu.siaa.core.model.jpa.contabilidad.CuentaBancaria)
     */  
    @Override	 
    public List<CuentaBancaria> listarCuentaBancaria(CuentaBancariaDTO cuentaBancaria) {
        Query query = generarQueryListaCuentaBancaria(cuentaBancaria, false);
        query.setFirstResult(cuentaBancaria.getStartRow());
        query.setMaxResults(cuentaBancaria.getOffset());
        return query.getResultList();
    }   

    /**
     * Generar query lista CuentaBancaria.
     *
     * @param CuentaBancariaDTO el cuentaBancaria
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaCuentaBancaria(CuentaBancariaDTO cuentaBancaria, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idCuentaBancaria) from CuentaBancaria o where 1=1 ");
        } else {
            jpaql.append(" select o from CuentaBancaria o left join fetch o.itemByBanco left join fetch o.itemByMoneda  left join fetch o.itemByTipoCuenta where 1=1 ");           
        }
        
       // jpaql.append(" and o.proveedor.idProveedor =:idProveedor ");
		//parametros.put("idProveedor", ObjectUtil.objectToLong(cuentaBancaria.getId()));
		
		/*if (!StringUtils.isNullOrEmpty(cuentaBancaria.getSearch())) {
	          jpaql.append(" and upper(o.nroCuenta) like :search ");
	          parametros.put("search", "%" + cuentaBancaria.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(cuentaBancaria.getNroCuenta())) {
				jpaql.append(" and upper(o.nroCuenta) like :nroCuenta ");
				parametros.put("nroCuenta", "%" + cuentaBancaria.getNroCuenta().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(cuentaBancaria.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + cuentaBancaria.getEstado().toUpperCase() + "%");
			}
		}*/
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.contabilidad.local.CuentaBancariaDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.contabilidad.CuentaBancariaDTO)
     */
	@Override
    public int contarListarCuentaBancaria(CuentaBancariaDTO cuentaBancaria) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaCuentaBancaria(cuentaBancaria, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.contabilidad.local.CuentaBancariaDaoLocal#generarIdCuentaBancaria()
     */
	 @Override
    public String generarIdCuentaBancaria() {
		  String resultado = UUIDUtil.generarElementUUID();
	        return resultado;
       /* String resultado = "1";
        Query query = createQuery("select max(o.idCuentaBancaria) from CuentaBancaria o", null);
        List<Object> listLong =  query.getResultList();
        if (listLong != null && listLong.size() > 0 && listLong.get(0) != null)  {
            Long ultimoIdGenerado = Long.valueOf(listLong.get(0).toString());
            if (!StringUtils.isNullOrEmpty(ultimoIdGenerado)) {
                resultado = resultado + ultimoIdGenerado;
            }
        }
        return resultado;*/
    }
   
}