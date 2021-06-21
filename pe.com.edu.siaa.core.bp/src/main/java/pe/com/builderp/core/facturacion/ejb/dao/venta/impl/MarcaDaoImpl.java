package pe.com.builderp.core.facturacion.ejb.dao.venta.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.venta.local.MarcaDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.venta.MarcaDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.Marca;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class MarcaDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:32:57 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class MarcaDaoImpl extends  GenericFacturacionDAOImpl<Long, Marca> implements MarcaDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.MarcaDaoLocal#listarMarca(pe.com.builderp.core.facturacion.model.jpa.venta.Marca)
     */  
    @Override	 
    public List<Marca> listarMarca(MarcaDTO marca) {
        Query query = generarQueryListaMarca(marca, false);
        query.setFirstResult(marca.getStartRow());
        query.setMaxResults(marca.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Marca.
     *
     * @param MarcaDTO el marca
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaMarca(MarcaDTO marca, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idMarca) from Marca o where 1=1 ");
        } else {
            jpaql.append(" select o from Marca o where 1=1 ");           
        }
		if (!StringUtils.isNullOrEmpty(marca.getSearch())) {
	          jpaql.append(" and upper(o.nombre) like :search ");
	          parametros.put("search", "%" + marca.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(marca.getNombre())) {
				jpaql.append(" and upper(o.nombre) like :nombre ");
				parametros.put("nombre", "%" + marca.getNombre().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(marca.getCodigo())) {
				jpaql.append(" and upper(o.codigo) like :codigo ");
				parametros.put("codigo", "%" + marca.getCodigo().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(marca.getCodigoReferencia())) {
				jpaql.append(" and upper(o.codigoReferencia) like :codigoReferencia ");
				parametros.put("codigoReferencia", "%" + marca.getCodigoReferencia().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(marca.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + marca.getEstado().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.MarcaDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.venta.MarcaDTO)
     */
	@Override
    public int contarListarMarca(MarcaDTO marca) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaMarca(marca, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.MarcaDaoLocal#generarIdMarca()
     */
	 @Override
    public Long generarIdMarca() {
        Long resultado = 1L;
        Query query = createQuery("select max(o.idMarca) from Marca o", null);
        List<Object> listLong =  query.getResultList();
        if (listLong != null && listLong.size() > 0 && listLong.get(0) != null)  {
            Long ultimoIdGenerado = Long.valueOf(listLong.get(0).toString());
            if (!StringUtils.isNullOrEmpty(ultimoIdGenerado)) {
                resultado = resultado + ultimoIdGenerado;
            }
        }
        return resultado;
    }
   
}