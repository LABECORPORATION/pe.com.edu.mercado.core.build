package pe.com.builderp.core.facturacion.ejb.dao.venta.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.venta.local.ClienteDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.venta.ClienteDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.Cliente;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class ClienteDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:32:56 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class ClienteDaoImpl extends  GenericFacturacionDAOImpl<String, Cliente> implements ClienteDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.ClienteDaoLocal#listarCliente(pe.com.builderp.core.facturacion.model.jpa.venta.Cliente)
     */  
    @Override	 
    public List<Cliente> listarCliente(ClienteDTO cliente) {
        Query query = generarQueryListaCliente(cliente, false);
        query.setFirstResult(cliente.getStartRow());
        query.setMaxResults(cliente.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Cliente.
     *
     * @param ClienteDTO el cliente
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaCliente(ClienteDTO cliente, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idCliente) from Cliente o left join  o.itemByTipoDocumentoIdentidad where 1=1 ");
        } else {
            jpaql.append(" select o from Cliente o left join fetch o.itemByTipoDocumentoIdentidad left join fetch o.itemByCategoriaCliente  where 1=1 ");           
        }
        
        if (!StringUtils.isNullOrEmptyNumeric(cliente.getIdEmpresaSelect())) {
	          jpaql.append(" and o.itemByTipoDocumentoIdentidad.idItem =:id ");
	          parametros.put("id",cliente.getIdEmpresaSelect());
	   } 
                  
		if (!StringUtils.isNullOrEmpty(cliente.getSearch())) {
	          //jpaql.append(" and upper(o.nombre) like :search ");
	          jpaql.append(" and upper(o.nombre || o.nroDoc) like :search ");
	          parametros.put("search", "%" + cliente.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(cliente.getTipoCliente())) {
				jpaql.append(" and upper(o.tipoCliente) like :tipoCliente ");
				parametros.put("tipoCliente", "%" + cliente.getTipoCliente().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(cliente.getNombre())) {
				jpaql.append(" and upper(o.nombre) like :nombre ");
				parametros.put("nombre", "%" + cliente.getNombre().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(cliente.getApellidoPaterno())) {
				jpaql.append(" and upper(o.apellidoPaterno) like :apellidoPaterno ");
				parametros.put("apellidoPaterno", "%" + cliente.getApellidoPaterno().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(cliente.getApellidoMaterno())) {
				jpaql.append(" and upper(o.apellidoMaterno) like :apellidoMaterno ");
				parametros.put("apellidoMaterno", "%" + cliente.getApellidoMaterno().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(cliente.getNroDoc())) {
				jpaql.append(" and upper(o.nroDoc) like :nroDoc ");
				parametros.put("nroDoc", "%" + cliente.getNroDoc().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(cliente.getEmail())) {
				jpaql.append(" and upper(o.email) like :email ");
				parametros.put("email", "%" + cliente.getEmail().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(cliente.getTelefono())) {
				jpaql.append(" and upper(o.telefono) like :telefono ");
				parametros.put("telefono", "%" + cliente.getTelefono().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(cliente.getCelular())) {
				jpaql.append(" and upper(o.celular) like :celular ");
				parametros.put("celular", "%" + cliente.getCelular().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(cliente.getPaginaWeb())) {
				jpaql.append(" and upper(o.paginaWeb) like :paginaWeb ");
				parametros.put("paginaWeb", "%" + cliente.getPaginaWeb().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(cliente.getDireccion())) {
				jpaql.append(" and upper(o.direccion) like :direccion ");
				parametros.put("direccion", "%" + cliente.getDireccion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(cliente.getUserName())) {
				jpaql.append(" and upper(o.userName) like :userName ");
				parametros.put("userName", "%" + cliente.getUserName().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(cliente.getUserPassword())) {
				jpaql.append(" and upper(o.userPassword) like :userPassword ");
				parametros.put("userPassword", "%" + cliente.getUserPassword().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmptyNumeric(cliente.getLimiteCreito())) {
				jpaql.append(" and o.limiteCreito = :limiteCreito ");
				parametros.put("limiteCreito", cliente.getLimiteCreito());
			}
			if (!StringUtils.isNullOrEmpty(cliente.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + cliente.getEstado().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.ClienteDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.venta.ClienteDTO)
     */
	@Override
    public int contarListarCliente(ClienteDTO cliente) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaCliente(cliente, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.ClienteDaoLocal#generarIdCliente()
     */
	 @Override
    public String generarIdCliente() {
		 return UUIDUtil.generarElementUUID();
       /* String resultado = "1";
        Query query = createQuery("select max(o.idCliente) from Cliente o", null);
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
		public Cliente findAlumnoByCliente(String idCliente) throws Exception {
		 Cliente resultado = null;
			StringBuilder jpaql = new StringBuilder();
			jpaql.append(" from Cliente o left join fetch o.itemByTipoDocumentoIdentidad left join fetch o.itemByCategoriaCliente");
			jpaql.append(" where o.idCliente=:idCliente");
			Query query = createQuery(jpaql.toString(),null);
			query.setParameter("idCliente", idCliente); 
			List<Cliente> listaCliente = query.getResultList();
			if (listaCliente != null && listaCliente.size() > 0) {
				resultado = listaCliente.get(0);
			}
			return resultado;	
		}
	 
	 @Override
		public Cliente findAlumnoByClienteNro(String nroDoc) throws Exception {
		 Cliente resultado = null;
			StringBuilder jpaql = new StringBuilder();
			jpaql.append(" from Cliente o left join fetch o.itemByTipoDocumentoIdentidad left join fetch o.itemByCategoriaCliente");
			jpaql.append(" where o.nroDoc=:nroDoc");
			Query query = createQuery(jpaql.toString(),null);
			query.setParameter("nroDoc", nroDoc); 
			List<Cliente> listaCliente = query.getResultList();
			if (listaCliente != null && listaCliente.size() > 0) {
				resultado = listaCliente.get(0);
			}
			return resultado;	
		}
   
}