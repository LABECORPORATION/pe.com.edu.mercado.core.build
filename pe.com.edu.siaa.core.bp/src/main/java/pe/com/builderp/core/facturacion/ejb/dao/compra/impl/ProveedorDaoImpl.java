package pe.com.builderp.core.facturacion.ejb.dao.compra.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.compra.local.ProveedorDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.compra.ProveedorDTO;
import pe.com.builderp.core.facturacion.model.jpa.compra.Proveedor;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.jpa.empresa.Personal;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class ProveedorDaoImpl.
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
public class ProveedorDaoImpl extends  GenericFacturacionDAOImpl<String, Proveedor> implements ProveedorDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.compra.local.ProveedorDaoLocal#listarProveedor(pe.com.builderp.core.facturacion.model.jpa.compra.Proveedor)
     */  
    @Override	 
    public List<Proveedor> listarProveedor(ProveedorDTO proveedor) {
        Query query = generarQueryListaProveedor(proveedor, false);
        query.setFirstResult(proveedor.getStartRow());
        query.setMaxResults(proveedor.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Proveedor.
     *
     * @param ProveedorDTO el proveedor
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaProveedor(ProveedorDTO proveedor, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idProveedor) from Proveedor o where 1=1 ");
        } else {
            jpaql.append(" select o from Proveedor o  left join fetch o.itemByTipoDocumentoIdentidad where 1=1 ");           
        }
		if (!StringUtils.isNullOrEmpty(proveedor.getSearch())) {
	          jpaql.append(" and upper(o.nombre) like :search ");
	          parametros.put("search", "%" + proveedor.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(proveedor.getTipoProveedor())) {
				jpaql.append(" and upper(o.tipoProveedor) like :tipoProveedor ");
				parametros.put("tipoProveedor", "%" + proveedor.getTipoProveedor().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(proveedor.getNombre())) {
				jpaql.append(" and upper(o.nombre) like :nombre ");
				parametros.put("nombre", "%" + proveedor.getNombre().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(proveedor.getApellidoPaterno())) {
				jpaql.append(" and upper(o.apellidoPaterno) like :apellidoPaterno ");
				parametros.put("apellidoPaterno", "%" + proveedor.getApellidoPaterno().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(proveedor.getApellidoMaterno())) {
				jpaql.append(" and upper(o.apellidoMaterno) like :apellidoMaterno ");
				parametros.put("apellidoMaterno", "%" + proveedor.getApellidoMaterno().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(proveedor.getNroDoc())) {
				jpaql.append(" and upper(o.nroDoc) like :nroDoc ");
				parametros.put("nroDoc", "%" + proveedor.getNroDoc().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(proveedor.getEmail())) {
				jpaql.append(" and upper(o.email) like :email ");
				parametros.put("email", "%" + proveedor.getEmail().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(proveedor.getTelefono())) {
				jpaql.append(" and upper(o.telefono) like :telefono ");
				parametros.put("telefono", "%" + proveedor.getTelefono().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(proveedor.getCelular())) {
				jpaql.append(" and upper(o.celular) like :celular ");
				parametros.put("celular", "%" + proveedor.getCelular().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(proveedor.getPaginaWeb())) {
				jpaql.append(" and upper(o.paginaWeb) like :paginaWeb ");
				parametros.put("paginaWeb", "%" + proveedor.getPaginaWeb().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(proveedor.getDireccion())) {
				jpaql.append(" and upper(o.direccion) like :direccion ");
				parametros.put("direccion", "%" + proveedor.getDireccion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(proveedor.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + proveedor.getEstado().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.compra.local.ProveedorDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.compra.ProveedorDTO)
     */
	@Override
    public int contarListarProveedor(ProveedorDTO proveedor) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaProveedor(proveedor, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.compra.local.ProveedorDaoLocal#generarIdProveedor()
     */
	 @Override
    public String generarIdProveedor() {
		 String resultado = UUIDUtil.generarElementUUID();
	        return resultado;
       /* String resultado = "1";
        Query query = createQuery("select max(o.idProveedor) from Proveedor o", null);
        List<Object> listLong =  query.getResultList();
        if (listLong != null && listLong.size() > 0 && listLong.get(0) != null)  {
            Long ultimoIdGenerado = Long.valueOf(listLong.get(0).toString());
            if (!StringUtils.isNullOrEmpty(ultimoIdGenerado)) {
                resultado = resultado + ultimoIdGenerado;
            }
        }
        return resultado;*/
    }
	 
	 
	 @Override
	public Proveedor findProveedorByNro(String nroDoc) throws Exception {
		 Proveedor resultado = null;
			StringBuilder jpaql = new StringBuilder();
			jpaql.append(" from Proveedor o left join fetch o.itemByTipoDocumentoIdentidad   ");
			jpaql.append(" where o.nroDoc=:nroDoc");
			Query query = createQuery(jpaql.toString(),null);
			query.setParameter("nroDoc", nroDoc); 
			List<Proveedor> listaCliente = query.getResultList();
			if (listaCliente != null && listaCliente.size() > 0) {
				resultado = listaCliente.get(0);
			}
			return resultado;	
	}
}