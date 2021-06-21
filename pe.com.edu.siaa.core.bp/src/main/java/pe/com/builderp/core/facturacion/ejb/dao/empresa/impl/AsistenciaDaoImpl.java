package pe.com.builderp.core.facturacion.ejb.dao.empresa.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.AsistenciaDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.empresa.AsistenciaDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.service.util.FechaUtil;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.jpa.empresa.Asistencia;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class AsistenciaDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 19:53:33 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class AsistenciaDaoImpl extends  GenericFacturacionDAOImpl<String, Asistencia> implements AsistenciaDaoLocal  {

	@Override
	public String generarCodigoAsistencia(Asistencia asistencia) throws Exception {
		/*/String resultado = asistencia.getDetCargaLectiva().getIdDetCargaLectiva() + "01";
		 Query query = createQuery("select max(o.idAsistencia) from Asistencia o where o.detCargaLectiva.idDetCargaLectiva=:idDetCargaLectiva",null);
		 query.setParameter("idDetCargaLectiva", asistencia.getDetCargaLectiva().getIdDetCargaLectiva());
		 List<String> listaCodigo = query.getResultList();
		 if (listaCodigo != null && listaCodigo.size() > 0) {
			 String ultimoCodigoGenerado = listaCodigo.get(0);
			 if (StringUtils.isNotNullOrBlank(ultimoCodigoGenerado)) {
				 long ultimoCodigo = Long.parseLong(ultimoCodigoGenerado.trim());
				 ultimoCodigo++;
				 resultado = "" + ultimoCodigo;
				
			 }
		 }
		return resultado;*/
		 return UUIDUtil.generarElementUUID();
	}
	
	@Override
	public List<Asistencia> listarAsistencia(boolean isConculta,String idDetcargaLectiva,String idAlumno,String estado,Date fechaHorario) throws Exception {
		Map<String, Object> parametros = new HashMap<String, Object>();
		boolean ejecutarQuery = false;
		StringBuilder jpaql = new StringBuilder();
		jpaql.append("from Asistencia o left join fetch  o.actividad dcl ");
		jpaql.append(" left join fetch  o.personal  ");
		jpaql.append(" left join fetch o.asociado "); 
		jpaql.append(" left join fetch o.itemByDia where 1 = 1 ");
		if (StringUtils.isNotNullOrBlank(idDetcargaLectiva)) {
			ejecutarQuery = true;
			jpaql.append(" and o.actividad.idActividad = :idDetCargaLectiva ");
			parametros.put("idDetCargaLectiva", idDetcargaLectiva);
			if (StringUtils.isNotNullOrBlank(estado)) {
				jpaql.append(" and o.estado = :estado ");
				parametros.put("estado", estado);
			}		
			if (StringUtils.isNotNullOrBlank(idAlumno)) {
				ejecutarQuery = true;
				jpaql.append(" and a.idAlumno = :idAlumno ");
				parametros.put("idAlumno", idAlumno);
			}
			if (!isConculta) {
				jpaql.append(" and to_char(o.fechaHorario,'dd/MM/yyyy') = :fechaHorario");
				parametros.put("fechaHorario",fechaHorario == null ?  FechaUtil.obtenerFechaFormato("dd/MM/yyyy") : FechaUtil.obtenerFechaFormatoPersonalizado(fechaHorario, "dd/MM/yyyy"));
			} else {
				if (fechaHorario != null) {
					jpaql.append(" and to_char(o.fechaHorario,'dd/MM/yyyy') = :fechaHorario");
					parametros.put("fechaHorario",FechaUtil.obtenerFechaFormatoPersonalizado(fechaHorario, "dd/MM/yyyy"));
				}
			}
			
			 
		}
		if (ejecutarQuery) {	
			//jpaql.append(" order by o.alumno.postulante.persona.apellidoPaterno asc ");
			Query query = createQuery(jpaql.toString(), parametros);
			return query.getResultList();
		} 
		return null;
	}
	
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.AsistenciaDaoLocal#listarAsistencia(pe.com.edu.siaa.core.model.jpa.seguridad.Asistencia)
     */  
    @Override	 
    public List<Asistencia> listarAsistencia(AsistenciaDTO asistencia) {
        Query query = generarQueryListaAsistencia(asistencia, false);
        query.setFirstResult(asistencia.getStartRow());
        query.setMaxResults(asistencia.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Asistencia.
     *
     * @param AsistenciaDTO el asistencia
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaAsistencia(AsistenciaDTO asistencia, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idAsistencia) from Asistencia o where 1=1 ");
        } else {
            jpaql.append(" select o from Asistencia o where 1=1 ");           
        }
		if (!StringUtils.isNullOrEmpty(asistencia.getSearch())) {
	          jpaql.append(" and upper(o.idAsistencia) like :search ");
	          parametros.put("search", "%" + asistencia.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(asistencia.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + asistencia.getEstado().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(asistencia.getJustificacion())) {
				jpaql.append(" and upper(o.justificacion) like :justificacion ");
				parametros.put("justificacion", "%" + asistencia.getJustificacion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(asistencia.getFechaHorario())) {
				jpaql.append(" and o.fechaHorario = :fechaHorario ");
				parametros.put("fechaHorario", asistencia.getFechaHorario());
			}
			if (!StringUtils.isNullOrEmpty(asistencia.getFechaCreacion())) {
				jpaql.append(" and o.fechaCreacion = :fechaCreacion ");
				parametros.put("fechaCreacion", asistencia.getFechaCreacion());
			}
			if (!StringUtils.isNullOrEmpty(asistencia.getUsuarioCreacion())) {
				jpaql.append(" and upper(o.usuarioCreacion) like :usuarioCreacion ");
				parametros.put("usuarioCreacion", "%" + asistencia.getUsuarioCreacion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(asistencia.getFechaModificacion())) {
				jpaql.append(" and o.fechaModificacion = :fechaModificacion ");
				parametros.put("fechaModificacion", asistencia.getFechaModificacion());
			}
			if (!StringUtils.isNullOrEmpty(asistencia.getUsuarioModificacion())) {
				jpaql.append(" and upper(o.usuarioModificacion) like :usuarioModificacion ");
				parametros.put("usuarioModificacion", "%" + asistencia.getUsuarioModificacion().toUpperCase() + "%");
			}
		}
		jpaql.append(" order by o.alumno.postulante.persona.apellidoPaterno asc ");
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.AsistenciaDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.seguridad.AsistenciaDTO)
     */
	@Override
    public int contarListarAsistencia(AsistenciaDTO asistencia) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaAsistencia(asistencia, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.AsistenciaDaoLocal#generarIdAsistencia()
     */
	 @Override
    public String generarIdAsistencia() {
        String resultado = "1";
        Query query = createQuery("select max(o.idAsistencia) from Asistencia o", null);
        List<Object> listLong =  query.getResultList();
        if (listLong != null && listLong.size() > 0 && listLong.get(0) != null)  {
            Long ultimoIdGenerado = Long.valueOf(listLong.get(0).toString());
            if (!StringUtils.isNullOrEmpty(ultimoIdGenerado)) {
                resultado = resultado + ultimoIdGenerado;
            }
        }
        return resultado;
    }
   
	 
	//agregado PARA SALVAR EL DIA
		
		@Override
		public List<String>  obtenerListadofechaBydetCraglectiva(String idDetCargalectiva,String idAnhosemestre) throws Exception {
			System.out.println("IdCarga:: " +idDetCargalectiva);
			StringBuilder jpaql = new StringBuilder();
			jpaql.append("select to_char(o.fechaHorario,'dd/MM/yyyy')  from Asistencia o ");
			jpaql.append(" where o.detCargaLectiva.idDetCargaLectiva = :idDetCargalectiva and o.detCargaLectiva.cargaLectiva.anhoSemestre.idAnhoSemestre = :idAnhosemestre ");
			jpaql.append(" group by to_char(o.fechaHorario,'dd/MM/yyyy'),o.fechaHorario ");
			jpaql.append(" order by o.fechaHorario asc ");
			Query query = createQuery(jpaql.toString(),null);
			query.setParameter("idDetCargalectiva", idDetCargalectiva);
			query.setParameter("idAnhosemestre", idAnhosemestre);
			List<String> detPlanEstudioResulCredito = query.getResultList();
			 
			return detPlanEstudioResulCredito;
		}
		
		
		@Override
		public List<String>  obtenerListadofecha(String idDetCargalectiva,String idAnhosemestre,String fecha)throws Exception{
			StringBuilder jpaql = new StringBuilder();
			jpaql.append("select o.estado from Asistencia o");
			jpaql.append(" where o.detCargaLectiva.idDetCargaLectiva = :idDetCargalectiva and o.detCargaLectiva.cargaLectiva.anhoSemestre.idAnhoSemestre = :idAnhosemestre ");
			jpaql.append(" and to_char(o.fechaHorario,'dd/MM/yyyy') in :fecha ");
			jpaql.append(" order by o.alumno.postulante.persona.apellidoPaterno asc ");
			Query query = createQuery(jpaql.toString(),null);
			query.setParameter("idDetCargalectiva", idDetCargalectiva);
			query.setParameter("idAnhosemestre", idAnhosemestre);
			query.setParameter("fecha", fecha);
			List<String> detPlanEstudioResulCredito = query.getResultList();
			
			return detPlanEstudioResulCredito;
		}
		
		@Override
		public List<String>  obtenerNombres(String idDetCargalectiva,String idAnhosemestre)throws Exception{
			StringBuilder jpaql = new StringBuilder();
			jpaql.append("select (o.alumno.postulante.persona.apellidoPaterno ||' '|| o.alumno.postulante.persona.apellidoMaterno ||' '|| o.alumno.postulante.persona.nombre)as nombre from Asistencia o");
			jpaql.append(" where o.detCargaLectiva.idDetCargaLectiva = :idDetCargalectiva and o.detCargaLectiva.cargaLectiva.anhoSemestre.idAnhoSemestre = :idAnhosemestre ");
			jpaql.append(" group by (o.alumno.postulante.persona.apellidoPaterno ||' '|| o.alumno.postulante.persona.apellidoMaterno ||' '|| o.alumno.postulante.persona.nombre) ");
			jpaql.append(" order by (o.alumno.postulante.persona.apellidoPaterno ||' '|| o.alumno.postulante.persona.apellidoMaterno ||' '|| o.alumno.postulante.persona.nombre) asc ");
			Query query = createQuery(jpaql.toString(),null);
			query.setParameter("idDetCargalectiva", idDetCargalectiva);
			query.setParameter("idAnhosemestre", idAnhosemestre);
	 
			List<String> detPlanEstudioResulCredito = query.getResultList();
			
			return detPlanEstudioResulCredito;
		}
		@Override
		public List<String>  obtenerCodigoAlumno(String idDetCargalectiva,String idAnhosemestre)throws Exception{
			StringBuilder jpaql = new StringBuilder();
			jpaql.append("select o.alumno.codigoCarnet from Asistencia o");
			jpaql.append(" where o.detCargaLectiva.idDetCargaLectiva = :idDetCargalectiva and o.detCargaLectiva.cargaLectiva.anhoSemestre.idAnhoSemestre = :idAnhosemestre ");
			jpaql.append(" group by o.alumno.codigoCarnet,o.alumno.postulante.persona.apellidoPaterno ");
			jpaql.append(" order by o.alumno.postulante.persona.apellidoPaterno asc ");
			Query query = createQuery(jpaql.toString(),null);
			query.setParameter("idDetCargalectiva", idDetCargalectiva);
			query.setParameter("idAnhosemestre", idAnhosemestre);
	 
			List<String> detPlanEstudioResulCredito = query.getResultList();
			
			return detPlanEstudioResulCredito;
		}
		
		
		@Override
		public List<Date>  obtenerListadofechaByAsistencia(String idDetCargalectiva)throws Exception{
			StringBuilder jpaql = new StringBuilder();
			jpaql.append("select o.fechaHorario from Asistencia o");
			jpaql.append(" where o.detCargaLectiva.idDetCargaLectiva = :idDetCargalectiva )");
			jpaql.append(" group by o.fechaHorario");
			Query query = createQuery(jpaql.toString(),null);
			query.setParameter("idDetCargalectiva", idDetCargalectiva);
			List<Date> detPlanEstudioResulCredito = query.getResultList();
			return detPlanEstudioResulCredito;
		}
}