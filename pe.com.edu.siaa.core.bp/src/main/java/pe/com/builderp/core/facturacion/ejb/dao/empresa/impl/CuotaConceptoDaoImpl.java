package pe.com.builderp.core.facturacion.ejb.dao.empresa.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.CuotaConceptoDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.empresa.CuotaConceptoDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.jpa.empresa.CuotaConcepto;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class CuotaConceptoDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 19:53:31 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class CuotaConceptoDaoImpl extends  GenericFacturacionDAOImpl<String, CuotaConcepto> implements CuotaConceptoDaoLocal  {

 
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.CuotaConceptoDaoLocal#listarCuotaConcepto(pe.com.edu.siaa.core.model.jpa.seguridad.CuotaConcepto)
     */  
    @Override	 
    public List<CuotaConcepto> listarCuotaConcepto(CuotaConceptoDTO cuotaConcepto) {
        Query query = generarQueryListaCuotaConcepto(cuotaConcepto, false);
        query.setFirstResult(cuotaConcepto.getStartRow());
        query.setMaxResults(cuotaConcepto.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista CuotaConcepto.
     *
     * @param CuotaConceptoDTO el cuotaConcepto
     * @param esContador el es contador
     * @return the query
     */
    //agregando join al producto y plan contable
    private Query generarQueryListaCuotaConcepto(CuotaConceptoDTO cuotaConcepto, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idCuotaConcepto) from CuotaConcepto o where 1=1 ");
        } else {
        	jpaql.append(" select o from CuotaConcepto o    where 1=1 ");           
           }
  
		if (!StringUtils.isNullOrEmpty(cuotaConcepto.getSearch())) {
	          jpaql.append(" and (  (upper(o.cuenta) like :search) )");
	          parametros.put("search", "%" + cuotaConcepto.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmptyNumeric(cuotaConcepto.getNroMinFraccionamiento())) {
				jpaql.append(" and o.nroMinFraccionamiento = :nroMinFraccionamiento ");
				parametros.put("nroMinFraccionamiento", cuotaConcepto.getNroMinFraccionamiento());
			}
			if (!StringUtils.isNullOrEmptyNumeric(cuotaConcepto.getNroMaxFraccionamiento())) {
				jpaql.append(" and o.nroMaxFraccionamiento = :nroMaxFraccionamiento ");
				parametros.put("nroMaxFraccionamiento", cuotaConcepto.getNroMaxFraccionamiento());
			}
			if (!StringUtils.isNullOrEmptyNumeric(cuotaConcepto.getMonto())) {
				jpaql.append(" and o.monto = :monto ");
				parametros.put("monto", cuotaConcepto.getMonto());
			}
			if (!StringUtils.isNullOrEmpty(cuotaConcepto.getPermanente())) {
				jpaql.append(" and upper(o.permanente) like :permanente ");
				parametros.put("permanente", "%" + cuotaConcepto.getPermanente().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(cuotaConcepto.getFechaTentativa())) {
				jpaql.append(" and o.fechaTentativa = :fechaTentativa ");
				parametros.put("fechaTentativa", cuotaConcepto.getFechaTentativa());
			}
			if (!StringUtils.isNullOrEmpty(cuotaConcepto.getFechaCreacion())) {
				jpaql.append(" and o.fechaCreacion = :fechaCreacion ");
				parametros.put("fechaCreacion", cuotaConcepto.getFechaCreacion());
			}
			if (!StringUtils.isNullOrEmpty(cuotaConcepto.getUsuarioCreacion())) {
				jpaql.append(" and upper(o.usuarioCreacion) like :usuarioCreacion ");
				parametros.put("usuarioCreacion", "%" + cuotaConcepto.getUsuarioCreacion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(cuotaConcepto.getFechaModificacion())) {
				jpaql.append(" and o.fechaModificacion = :fechaModificacion ");
				parametros.put("fechaModificacion", cuotaConcepto.getFechaModificacion());
			}
			if (!StringUtils.isNullOrEmpty(cuotaConcepto.getUsuarioModificacion())) {
				jpaql.append(" and upper(o.usuarioModificacion) like :usuarioModificacion ");
				parametros.put("usuarioModificacion", "%" + cuotaConcepto.getUsuarioModificacion().toUpperCase() + "%");
			}
 
		}
        if (!esContador) {
            jpaql.append(" ORDER BY  o.nroCuenta ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.CuotaConceptoDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.seguridad.CuotaConceptoDTO)
     */
	@Override
    public int contarListarCuotaConcepto(CuotaConceptoDTO cuotaConcepto) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaCuotaConcepto(cuotaConcepto, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.CuotaConceptoDaoLocal#generarIdCuotaConcepto()
     */
	 @Override
    public String generarIdCuotaConcepto() {
      /*  String resultado = "1";
        Query query = createQuery("select max(o.idCuotaConcepto) from CuotaConcepto o", null);
        List<Object> listLong =  query.getResultList();
        if (listLong != null && listLong.size() > 0 && listLong.get(0) != null)  {
            Long ultimoIdGenerado = Long.valueOf(listLong.get(0).toString());
            if (!StringUtils.isNullOrEmpty(ultimoIdGenerado)) {
                resultado = resultado + ultimoIdGenerado;
            }
        }*/
        return UUIDUtil.generarElementUUID();
    }
	 
	 
	 //agregado
		@Override
	public List<CuotaConcepto> listaCuotaConceptoId(String idcuotaconcepto) throws Exception {
		Map<String, Object> parametros = new HashMap<String, Object>();
		boolean ejecutarQuery = false;
		StringBuilder jpaql = new StringBuilder();
		jpaql.append("from CuotaConcepto o   where 1=1");
		if (idcuotaconcepto != null) {
				ejecutarQuery = true;
				jpaql.append(" and o.idCuotaConcepto = :idCuotaConcepto ");
				parametros.put("idCuotaConcepto", idcuotaconcepto);
		}
		//jpaql.append(" order by cl.escuela.idEscuela ");
		if (ejecutarQuery) {		
			Query query = createQuery(jpaql.toString(), parametros);
			return query.getResultList();
		} 
		return null;
	}
   
}