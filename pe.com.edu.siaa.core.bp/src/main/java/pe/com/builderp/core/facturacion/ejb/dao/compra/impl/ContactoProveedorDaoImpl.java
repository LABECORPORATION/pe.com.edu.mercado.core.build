package pe.com.builderp.core.facturacion.ejb.dao.compra.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.compra.local.ContactoProveedorDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.compra.ContactoProveedorDTO;
import pe.com.builderp.core.facturacion.model.jpa.compra.ContactoProveedor;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.util.ObjectUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class ContactoProveedorDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:32:53 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class ContactoProveedorDaoImpl extends  GenericFacturacionDAOImpl<String, ContactoProveedor> implements ContactoProveedorDaoLocal  {

	 /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.contabilidad.local.ContactoProveedorDaoLocal#listarContactoProveedor(pe.com.edu.siaa.core.model.jpa.contabilidad.ContactoProveedor)
     */  
    @Override	 
    public List<ContactoProveedor> listarContactoProveedor(ContactoProveedorDTO contactoProveedor) {
        Query query = generarQueryListaContactoProveedor(contactoProveedor, false);
        query.setFirstResult(contactoProveedor.getStartRow());
        query.setMaxResults(contactoProveedor.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista ContactoProveedor.
     *
     * @param ContactoProveedorDTO el contactoProveedor
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaContactoProveedor(ContactoProveedorDTO contactoProveedor, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idContactoProveedor) from ContactoProveedor o where 1=1 ");
        } else {
            jpaql.append(" select o from ContactoProveedor o left join fetch o.itemByContacto  where 1=1 ");           
        }
        
        
		/*jpaql.append(" and o.proveedor.idProveedor =:idProveedor ");
		parametros.put("idProveedor", ObjectUtil.objectToLong(contactoProveedor.getId()));
		
		if (!StringUtils.isNullOrEmpty(contactoProveedor.getSearch())) {
	          jpaql.append(" and upper(o.nombres) like :search ");
	          parametros.put("search", "%" + contactoProveedor.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(contactoProveedor.getCodigo())) {
				jpaql.append(" and upper(o.codigo) like :codigo ");
				parametros.put("codigo", "%" + contactoProveedor.getCodigo().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(contactoProveedor.getApellidoPaterno())) {
				jpaql.append(" and upper(o.apellidoPaterno) like :apellidoPaterno ");
				parametros.put("apellidoPaterno", "%" + contactoProveedor.getApellidoPaterno().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(contactoProveedor.getApellidoMaterno())) {
				jpaql.append(" and upper(o.apellidoMaterno) like :apellidoMaterno ");
				parametros.put("apellidoMaterno", "%" + contactoProveedor.getApellidoMaterno().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(contactoProveedor.getNombres())) {
				jpaql.append(" and upper(o.nombres) like :nombres ");
				parametros.put("nombres", "%" + contactoProveedor.getNombres().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(contactoProveedor.getDireccion())) {
				jpaql.append(" and upper(o.direccion) like :direccion ");
				parametros.put("direccion", "%" + contactoProveedor.getDireccion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(contactoProveedor.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + contactoProveedor.getEstado().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }*/
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.contabilidad.local.ContactoProveedorDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.contabilidad.ContactoProveedorDTO)
     */
	@Override
    public int contarListarContactoProveedor(ContactoProveedorDTO contactoProveedor) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaContactoProveedor(contactoProveedor, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.contabilidad.local.ContactoProveedorDaoLocal#generarIdContactoProveedor()
     */
	 @Override
    public String generarIdContactoProveedor() {
        String resultado = UUIDUtil.generarElementUUID();
        return resultado;
    }
   
}