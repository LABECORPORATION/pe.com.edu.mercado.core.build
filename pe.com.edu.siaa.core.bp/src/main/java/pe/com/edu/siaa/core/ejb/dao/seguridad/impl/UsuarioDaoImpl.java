package pe.com.edu.siaa.core.ejb.dao.seguridad.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericDAOImpl;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.UsuarioDaoLocal;
import pe.com.edu.siaa.core.ejb.factory.CollectionUtil;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.dto.seguridad.UsuarioDTO;
import pe.com.edu.siaa.core.model.estate.EstadoGeneralState;
import pe.com.edu.siaa.core.model.jpa.seguridad.Usuario;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class UsuarioDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 14 00:27:45 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class UsuarioDaoImpl extends  GenericDAOImpl<String, Usuario> implements UsuarioDaoLocal  {

	@Override
	public Usuario obtenerUsuarioByCodigoExterno(String codigoExterno) throws Exception {
		Usuario resultado = null;
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("codigoExterno", codigoExterno);
		
		Query query = createQuery("from Usuario p left join fetch p.tipoUsuario  where   p.codigoExterno =:codigoExterno ", parametros);
		
		List<Usuario> listaUsuario = query.getResultList();
		if (listaUsuario != null && listaUsuario.size() > 0) {
			resultado = listaUsuario.get(0);
		}
		return resultado;
	}
	@Override
	public Usuario validarLogin(String userName,String userPassword) throws Exception {
		Usuario resultado = null;
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("userName", userName);
		parametros.put("userPassword", userPassword);
		parametros.put("estado", EstadoGeneralState.ACTIVO.getKey());
		
		Query query = createQuery("from Usuario p left join fetch p.tipoUsuario  where   p.userName =:userName and p.userPassword=:userPassword and p.estado =:estado ", parametros);
		
		List<Usuario> listaUsuario = query.getResultList();
		if (listaUsuario != null && listaUsuario.size() > 0) {
			resultado = listaUsuario.get(0);
		}
		return resultado;
	}
    /* (non-Javadoc)
     * @see pe.com.builderp.core.ejb.dao.seguridad.local.UsuarioDaoLocal#listarUsuario(pe.com.builderp.core.model.jpa.seguridad.Usuario)
     */  
    @Override	 
    public List<Usuario> listarUsuario(UsuarioDTO usuario) {
        Query query = generarQueryListaUsuario(usuario, false);
        query.setFirstResult(usuario.getStartRow());
        query.setMaxResults(usuario.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Usuario.
     *
     * @param UsuarioDTO el usuario
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaUsuario(UsuarioDTO usuario, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idUsuario) from Usuario o where 1=1 ");
        } else {
            jpaql.append(" select o from Usuario o left join fetch  o.tipoUsuario where 1=1 ");           
        }
        
    	if (!StringUtils.isNullOrEmptyNumeric(usuario.getIdEmpresaSelect())) {
	      	  jpaql.append(" and o.tipoUsuario.idTipoUsuario !=10L "); 
	    } 
    	
		if (!StringUtils.isNullOrEmpty(usuario.getSearch())) {
	          jpaql.append(" and (TRANSLATE(UPPER(o.nombre || ' ' || o.apellidoPaterno || ' ' || o.apellidoMaterno ), :discriminaTildeMAC, :discriminaTildeMAT) like UPPER(translate(:search, :discriminaTildeMIC, :discriminaTildeMIT)) or (upper(o.codigoExterno) like :search) ");
			  jpaql.append(" or ( TRANSLATE(UPPER(o.userName), :discriminaTildeMAC, :discriminaTildeMAT) like UPPER(translate(:search, :discriminaTildeMIC, :discriminaTildeMIT)) ) ) ");
			  parametros.putAll(obtenerParametroDiscriminarTilde());	
		      parametros.put("search", "%" + usuario.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(usuario.getNombre())) {
				jpaql.append(" and upper(o.nombre) like :nombre ");
				parametros.put("nombre", "%" + usuario.getNombre().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(usuario.getApellidoPaterno())) {
				jpaql.append(" and upper(o.apellidoPaterno) like :apellidoPaterno ");
				parametros.put("apellidoPaterno", "%" + usuario.getApellidoPaterno().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(usuario.getApellidoMaterno())) {
				jpaql.append(" and upper(o.apellidoMaterno) like :apellidoMaterno ");
				parametros.put("apellidoMaterno", "%" + usuario.getApellidoMaterno().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(usuario.getEmail())) {
				jpaql.append(" and upper(o.email) like :email ");
				parametros.put("email", "%" + usuario.getEmail().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(usuario.getTelefono())) {
				jpaql.append(" and upper(o.telefono) like :telefono ");
				parametros.put("telefono", "%" + usuario.getTelefono().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(usuario.getCelular())) {
				jpaql.append(" and upper(o.celular) like :celular ");
				parametros.put("celular", "%" + usuario.getCelular().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(usuario.getUserName())) {
				jpaql.append(" and upper(o.userName) like :userName ");
				parametros.put("userName", "%" + usuario.getUserName().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(usuario.getUserPassword())) {
				jpaql.append(" and upper(o.userPassword) like :userPassword ");
				parametros.put("userPassword", "%" + usuario.getUserPassword().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(usuario.getCodigoExterno())) {
				jpaql.append(" and upper(o.codigoExterno) like :codigoExterno ");
				parametros.put("codigoExterno", "%" + usuario.getCodigoExterno().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(usuario.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + usuario.getEstado().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.ejb.dao.seguridad.local.UsuarioDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.model.jpa.seguridad.UsuarioDTO)
     */
	@Override
    public int contarListarUsuario(UsuarioDTO usuario) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaUsuario(usuario, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.ejb.dao.seguridad.local.UsuarioDaoLocal#generarIdUsuario()
     */
	 @Override
    public String generarIdUsuario() {
        return UUIDUtil.generarElementUUID();
    }
   
	 
	 //agregado
	 
		@Override
		public Usuario validarLoginPassword(String userName,String email) throws Exception {
			Usuario resultado = null;
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("userName", userName);
			parametros.put("email", email);
			parametros.put("estado", EstadoGeneralState.ACTIVO.getKey());
			
			Query query = createQuery("from Usuario p left join fetch p.tipoUsuario  where   p.userName =:userName and p.email=:email and p.estado =:estado ", parametros);
			
			List<Usuario> listaUsuario = query.getResultList();
			if (listaUsuario != null && listaUsuario.size() > 0) {
				resultado = listaUsuario.get(0);
			}
			return resultado;
		}
		
		
		@Override	 
		public Usuario findUsuario(String userName) throws Exception {
			Usuario resultado = new Usuario();
			Map<String, Object> parametros = new HashMap<String, Object>();
	        StringBuilder jpaql = new StringBuilder();
	        jpaql.append(" select o from Usuario o ");        
	        jpaql.append(" where o.userName = :userName ");
	        parametros.put("userName", userName);
	        
	        Query query = createQuery(jpaql.toString(), parametros);
	        List<Usuario> listaTemp = query.getResultList();
	        if (!CollectionUtil.isEmpty(listaTemp)) {
	        	resultado = listaTemp.get(0);
	        }
			return resultado;
		}
		
		@Override	 
		public Usuario findByUsuarioID(String idUusario) throws Exception {
			Usuario resultado = new Usuario();
			Map<String, Object> parametros = new HashMap<String, Object>();
	        StringBuilder jpaql = new StringBuilder();
	        jpaql.append(" select o from Usuario o ");        
	        jpaql.append(" where o.idUsuario = :idUsuario ");
	        parametros.put("idUsuario", idUusario);
	        Query query = createQuery(jpaql.toString(), parametros);
	        List<Usuario> listaTemp = query.getResultList();
	        if (!CollectionUtil.isEmpty(listaTemp)) {
	        	resultado = listaTemp.get(0);
	        }
			return resultado;
		}
}