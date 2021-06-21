package pe.com.builderp.core.facturacion.ejb.dao.venta.impl;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.venta.local.TransportistaDaoLocal;
import pe.com.builderp.core.facturacion.model.jpa.venta.Transportista;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;
import pe.com.builderp.core.facturacion.model.dto.venta.TransportistaDTO;


/**
 * La Class TransportistaDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu May 02 10:20:31 COT 2019
 * @since SIAA-CORE 2.1
 */
@Stateless
public class TransportistaDaoImpl extends  GenericFacturacionDAOImpl<String, Transportista> implements TransportistaDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.TransportistaDaoLocal#listarTransportista(pe.com.builderp.core.facturacion.model.jpa.venta.Transportista)
     */  
    @Override	 
    public List<Transportista> listarTransportista(TransportistaDTO transportista) {
        Query query = generarQueryListaTransportista(transportista, false);
        query.setFirstResult(transportista.getStartRow());
        query.setMaxResults(transportista.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Transportista.
     *
     * @param TransportistaDTO el transportista
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaTransportista(TransportistaDTO transportista, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idTransportistaConductor) from Transportista o where 1=1 ");
        } else {
            jpaql.append(" select o from Transportista o where 1=1 ");           
        }
		if (!StringUtils.isNullOrEmpty(transportista.getSearch())) {
	          jpaql.append(" and upper(o.idTransportistaConductor) like :search ");
	          parametros.put("search", "%" + transportista.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(transportista.getTipoTransportistaConductor())) {
				jpaql.append(" and upper(o.tipoTransportistaConductor) like :tipoTransportistaConductor ");
				parametros.put("tipoTransportistaConductor", "%" + transportista.getTipoTransportistaConductor().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(transportista.getNombre())) {
				jpaql.append(" and upper(o.nombre) like :nombre ");
				parametros.put("nombre", "%" + transportista.getNombre().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(transportista.getApellidoPaterno())) {
				jpaql.append(" and upper(o.apellidoPaterno) like :apellidoPaterno ");
				parametros.put("apellidoPaterno", "%" + transportista.getApellidoPaterno().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(transportista.getApellidoMaterno())) {
				jpaql.append(" and upper(o.apellidoMaterno) like :apellidoMaterno ");
				parametros.put("apellidoMaterno", "%" + transportista.getApellidoMaterno().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(transportista.getNroDoc())) {
				jpaql.append(" and upper(o.nroDoc) like :nroDoc ");
				parametros.put("nroDoc", "%" + transportista.getNroDoc().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(transportista.getEmail())) {
				jpaql.append(" and upper(o.email) like :email ");
				parametros.put("email", "%" + transportista.getEmail().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(transportista.getCelular())) {
				jpaql.append(" and upper(o.celular) like :celular ");
				parametros.put("celular", "%" + transportista.getCelular().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(transportista.getDireccion())) {
				jpaql.append(" and upper(o.direccion) like :direccion ");
				parametros.put("direccion", "%" + transportista.getDireccion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(transportista.getFlagLicencia())) {
				jpaql.append(" and upper(o.flagLicencia) like :flagLicencia ");
				parametros.put("flagLicencia", "%" + transportista.getFlagLicencia().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(transportista.getTipoLicencia())) {
				jpaql.append(" and upper(o.tipoLicencia) like :tipoLicencia ");
				parametros.put("tipoLicencia", "%" + transportista.getTipoLicencia().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(transportista.getNroLicencia())) {
				jpaql.append(" and upper(o.nroLicencia) like :nroLicencia ");
				parametros.put("nroLicencia", "%" + transportista.getNroLicencia().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(transportista.getClaseLicencia())) {
				jpaql.append(" and upper(o.claseLicencia) like :claseLicencia ");
				parametros.put("claseLicencia", "%" + transportista.getClaseLicencia().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(transportista.getCategoriaLicencia())) {
				jpaql.append(" and upper(o.categoriaLicencia) like :categoriaLicencia ");
				parametros.put("categoriaLicencia", "%" + transportista.getCategoriaLicencia().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.TransportistaDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.venta.TransportistaDTO)
     */
	@Override
    public int contarListarTransportista(TransportistaDTO transportista) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaTransportista(transportista, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.TransportistaDaoLocal#generarIdTransportista()
     */
	 @Override
    public String generarIdTransportista() {
		 return UUIDUtil.generarElementUUID();
    }
   
}