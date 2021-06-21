package pe.com.edu.siaa.core.ejb.dao.seguridad.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericDAOImpl;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.RecuperarPasswordDaoLocal;
import pe.com.edu.siaa.core.ejb.factory.CollectionUtil;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.jpa.seguridad.RecuperarPassword;

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
public class RecuperarPasswordDaoImpl extends  GenericDAOImpl<String, RecuperarPassword> implements RecuperarPasswordDaoLocal  {

	@Override
	public String generarIdRecuperarPassword() throws Exception {
	    return UUIDUtil.generarElementUUID();
	}

	@Override
	public RecuperarPassword listarRecuperarPassword(String idrecuperarPassword) throws Exception {
		RecuperarPassword resultado = null;
		StringBuilder jpaql = new StringBuilder();
		jpaql.append("from RecuperarPassword p left join fetch p.usuario  where   p.idRecuperar =:idRecuperar ");
		Query query = createQuery(jpaql.toString(),null);
		query.setParameter("idRecuperar", idrecuperarPassword); 
		List<RecuperarPassword> listaAlumno = query.getResultList();
		if (listaAlumno != null && listaAlumno.size() > 0) {
			resultado = listaAlumno.get(0);
		}
		return resultado;	
	}

	@Override
    public void deleteRecuperar(String idUsuario )throws Exception {
		Map<String, Object> parametros = new HashMap<String, Object>();
		Query query = createQuery("delete from RecuperarPassword  where usuario.idUsuario =:idUsuario",parametros);
		query.setParameter("idUsuario", idUsuario);
		query.executeUpdate();
	}
 
	@Override	 
	public RecuperarPassword findUsuario(String idUsuario) throws Exception {
		RecuperarPassword resultado = new RecuperarPassword();
		Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        jpaql.append(" select p from RecuperarPassword p left join fetch p.usuario  ");        
        jpaql.append(" where p.usuario.idUsuario = :idUsuario ");
        parametros.put("idUsuario", idUsuario);
        
        Query query = createQuery(jpaql.toString(), parametros);
        List<RecuperarPassword> listaTemp = query.getResultList();
        if (!CollectionUtil.isEmpty(listaTemp)) {
        	resultado = listaTemp.get(0);
        }
		return resultado;
	}
}