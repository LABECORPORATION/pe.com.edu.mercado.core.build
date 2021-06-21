package pe.com.edu.siaa.core.ejb.util.txt;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.Sides;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;

import com.google.gson.JsonObject;

import pe.com.builderp.core.facturacion.model.dto.venta.VentaDTO;
import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.model.dto.BaseEntidad;

import pe.com.edu.siaa.core.model.type.ListaItemType;
import pe.com.edu.siaa.core.model.util.ConstanteConfigUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;
import pe.com.edu.siaa.core.model.vo.SelectItemVO;
import pe.com.edu.siaa.core.model.vo.SunatDatosVO;

/**
 * <ul>
 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Class Utilidades.
 *
 * @author ndavilal.
 * @version 1.0 , 18/03/2012
 * @since SIAA 2.0
 */
public class Utilidades {

	/** La Constante ITEM_VACIO. */
	private static final String ITEM_VACIO = "ItemVacio";

	/**
	 * Logger para el registro de errores.
	 */
	private static Logger log = Logger.getLogger(Utilidades.class);
	
	/** La Constante SI. */
	private static final String SI = "Si";
	
	/** La Constante NO. */
	private static final String NO = "No";
	
	/** La Constante isPosgres. */
	private static final boolean ISPOSGRES = true;

	/** La Constante RUTA_RECURSOS. */
	public static final String RUTA_RECURSOS_BYTE_BUFFER = ConstanteConfigUtil.RUTA_RECURSOS_BYTE_BUFFER;
	
	/**
	 * Obtener with recursivo.
	 *
	 * @return the string
	 */
	public static String obtenerWITHRecursivo() {
		if (ISPOSGRES) {
			return "WITH RECURSIVE";
		}
		return "WITH";
	}
	
	/**
	 * Obtener contatenar.
	 *
	 * @return the string
	 */
	public static String obtenerContatenar() {
		if (ISPOSGRES) {
			return "";
		}
		return " + ''";
	}
	
	/**
	 * Obtener separar.
	 *
	 * @return the string
	 */
	public static String obtenerSeparar() {
		if (ISPOSGRES) {
			return " ";
		}
		return " + ' ' + ";
	}
	
	/**
	 * Obtener sql.
	 *
	 * @return the string
	 */
	public static String obtenerReporteAdmisionPostulanteSQL() {
		StringBuilder sql = new StringBuilder();
		sql.append(" select EntidadUniversitaria.codigoEntidad, EntidadUniversitaria.nombre as universidad,AnhoSemestre.anho,   ( case AnhoSemestre.semestre  when '1' then 'I' when '2' then 'II' when '0' then '0' end) as NomSemestre, Postulante.codigoPostulante,");
		sql.append(" Escuela.nombre as Escuela, Persona.nombre, Persona.apellidoPaterno, Persona.apellidoMaterno, Postulante.puntaje, ( case Postulante.flagIngreso  when 'S' then 'SI' when 'N' then 'NO' end) as Ingreso");
		sql.append(" from dbo.Postulante Postulante inner join dbo.Persona Persona  on  Postulante.idPersona = Persona.idPersona");
		sql.append(" left join dbo.Escuela Escuela on Postulante.idEscuelaIngreso = Escuela.idEscuela");
		sql.append(" inner join dbo.EntidadUniversitaria EntidadUniversitaria on  Persona.idEntidadUniversitaria = EntidadUniversitaria.idEntidadUniversitaria");
		sql.append(" inner join dbo.AnhoSemestre AnhoSemestre on Postulante.idAhnoSemestre = AnhoSemestre.idAnhoSemestre");
		sql.append(" where 1= 1 ");
		return sql.toString();
	}
	
	/**
	 * Obtener query ficha matricula sql.
	 *
	 * @return the string
	 */
	public static String obtenerQueryFichaMatriculaSQL() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append(" Matricula.idMatricula AS Matricula_Id_Matricula,");
		sql.append(" AnhoSemestre.anho AS AnhoSemestre_NomAnho,");
		sql.append(" (case AnhoSemestre.semestre when '1'then 'I'when '2'then 'II'when '0'then '0'end) as NomSemestre,");
		sql.append(" Matricula.idAlumno AS Matricula_CodCarnet,");
		sql.append(" Persona.nombre AS Persona_NomPersona,");
		sql.append(" Persona.apellidoPaterno AS Persona_ApePat,");
		sql.append(" Persona.apellidoMaterno AS Persona_ApeMat,");
		sql.append("  Persona.Direccion AS Persona_DirPersona,");
		sql.append("  Persona.Telefono1 AS Persona_TelPersona,");
		sql.append(" Ciclo.nombre AS Ciclo_NomCiclo,");
		sql.append(" Turno.codigoExterno AS Alumno_TurnoActual,");
		sql.append(" Escuela.nombre AS Escuela_NomEscuela,");
		sql.append(" Persona.NroDoc AS Persona_DNI,");
		sql.append(" Persona.idEstadoCivil AS Persona_EstadoCivil,");
		sql.append(" Persona.Genero AS Persona_Genero,");
		sql.append(" Persona.Email AS Persona_EmaPersona,");
		sql.append(" Matricula.FechaMatricula as FechaMatricula,");
		sql.append(" Persona.Foto,");
		sql.append(" Alumno.idEscuela AS Alumno_Id_Escuela");
		sql.append(" FROM");
		sql.append(" dbo.Alumno Alumno ");
		sql.append(" INNER JOIN dbo.Matricula Matricula ");
		sql.append(" ON Alumno.idAlumno = Matricula.idAlumno");
		sql.append(" INNER JOIN dbo.Postulante Postulante ");
		sql.append(" ON Alumno.idPostulante = Postulante.idPostulante");
		sql.append("  INNER JOIN dbo.Persona Persona ");
		sql.append(" ON Postulante.idPersona = Persona.idPersona");
		sql.append(" INNER JOIN dbo.Escuela Escuela ON Alumno.idEscuela = Escuela.idEscuela");
		sql.append(" INNER JOIN commo.Item Ciclo ON Alumno.idCicloActual = Ciclo.idItem");
		sql.append(" INNER JOIN commo.Item Turno ON Alumno.idTurnoActual = Turno.idItem");
		sql.append(" INNER JOIN dbo.AnhoSemestre AnhoSemestre ON Matricula.idAnhoSemestre = AnhoSemestre.idAnhoSemestre");
		sql.append(" WHERE ");
		sql.append("  Matricula.idAnhoSemestre = $P{Id_AhnoSemestre}");
		sql.append("  and Matricula.idAlumno = $P{CodCarnet}");
		sql.append(" ORDER BY");
		sql.append(" Escuela.nombre ASC,");
		sql.append(" idCicloActual ASC,");
		sql.append(" apellidoPaterno ASC,");
		sql.append(" apellidoMaterno ASC,");
		sql.append(" Persona.nombre ASC");
		return sql.toString();
	}
	
	/**
	 * Obtener query ficha matricula masivo sql.
	 *
	 * @return the string
	 */
	public static String obtenerQueryFichaMatriculaMasivoSQL() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append(" Matricula.idMatricula AS Matricula_Id_Matricula,");
		sql.append(" AnhoSemestre.anho AS AnhoSemestre_NomAnho,");
		sql.append(" (case AnhoSemestre.semestre when '1'then 'I'when '2'then 'II'when '0'then '0'end) as NomSemestre,");
		sql.append(" Matricula.idAlumno AS Matricula_CodCarnet,");
		sql.append(" Persona.nombre AS Persona_NomPersona,");
		sql.append(" Persona.apellidoPaterno AS Persona_ApePat,");
		sql.append(" Persona.apellidoMaterno AS Persona_ApeMat,");
		sql.append("  Persona.Direccion AS Persona_DirPersona,");
		sql.append("  Persona.Telefono1 AS Persona_TelPersona,");
		sql.append(" Ciclo.nombre AS Ciclo_NomCiclo,");
		sql.append(" Turno.codigoExterno AS Alumno_TurnoActual,");
		sql.append(" Escuela.nombre AS Escuela_NomEscuela,");
		sql.append(" Persona.NroDoc AS Persona_DNI,");
		sql.append(" Persona.idEstadoCivil AS Persona_EstadoCivil,");
		sql.append(" Persona.Genero AS Persona_Genero,");
		sql.append(" Persona.Email AS Persona_EmaPersona,");
		sql.append(" Matricula.FechaMatricula as FechaMatricula,");
		sql.append(" Persona.Foto,");
		sql.append(" Alumno.idEscuela AS Alumno_Id_Escuela");
		sql.append(" FROM");
		sql.append(" dbo.Alumno Alumno ");
		sql.append(" INNER JOIN dbo.Matricula Matricula ");
		sql.append(" ON Alumno.idAlumno = Matricula.idAlumno");
		sql.append(" INNER JOIN dbo.Postulante Postulante ");
		sql.append(" ON Alumno.idPostulante = Postulante.idPostulante");
		sql.append("  INNER JOIN dbo.Persona Persona ");
		sql.append(" ON Postulante.idPersona = Persona.idPersona");
		sql.append(" INNER JOIN dbo.Escuela Escuela ON Alumno.idEscuela = Escuela.idEscuela");
		sql.append(" INNER JOIN commo.Item Ciclo ON Alumno.idCicloActual = Ciclo.idItem");
		sql.append(" INNER JOIN commo.Item Turno ON Alumno.idTurnoActual = Turno.idItem");
		sql.append(" INNER JOIN dbo.AnhoSemestre AnhoSemestre ON Matricula.idAnhoSemestre = AnhoSemestre.idAnhoSemestre");
		sql.append(" WHERE ");
		sql.append("  Matricula.idAnhoSemestre = $P{Id_AhnoSemestre}");
		sql.append("  and Alumno.idEscuela LIKE $P{Id_Escuela} ");
		sql.append(" ORDER BY");
		sql.append(" Escuela.nombre ASC,");
		sql.append(" idCicloActual ASC,");
		sql.append(" apellidoPaterno ASC,");
		sql.append(" apellidoMaterno ASC,");
		sql.append(" Persona.nombre ASC");
		return sql.toString();
	}
	
	/**
	 * Obtener query ficha matricula sub reporte curso sql.
	 *
	 * @return the string
	 */
	public static String obtenerQueryFichaMatriculaSubReporteCursoSQL() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  DetPlanEstudio.idDetPlanEstudio as Id_DetPlanEstudio , DetPlanEstudio.CodigoAsignatura,DetPlanEstudio.descripcionCurso as NomCurso,");
		sql.append(" DetPlanEstudio.Credito,(DetPlanEstudio.horaPractica+DetPlanEstudio.horaTeorica)as THrs,DetPlanEstudio.horaPractica as HrsPractica ,DetPlanEstudio.horaTeorica as HrsTeorica,");
		sql.append(" Ciclo.nombre as NomCiclo, Turno.codigoExterno AS Turno,AnhoSemestre.FechaInicio as FechaInicio, ");
		sql.append(" AnhoSemestre.FechaCierre as FechaCierre,DetPlanEstudio.idCiclo as Id_Ciclo,FechaMatricula as FechaMatricula");
		sql.append(" ,(select COUNT(DetCar.idDetPlanEstudio) ");
		sql.append(" from dbo.Matricula Mat,dbo.DetMatricula DetMat,dbo.DetCargaLectiva DetCar");
		sql.append(" where Mat.idMatricula=DetMat.idMatricula ");
		sql.append(" and DetMat.idDetCargaLectiva=DetCar.idDetCargaLectiva ");
		sql.append(" and Mat.idAlumno=Matricula.idAlumno ");
		sql.append(" and DetCar.idDetPlanEstudio=DetCargaLectiva.idDetPlanEstudio )as NroV");
		sql.append(" FROM dbo.DetMatricula DetMatricula INNER JOIN dbo.Matricula  Matricula");
		sql.append(" ON DetMatricula.idMatricula = Matricula.idMatricula ");
		sql.append(" INNER JOIN dbo.AnhoSemestre AnhoSemestre");
		sql.append(" ON Matricula.idAnhoSemestre = AnhoSemestre.idAnhoSemestre ");
		sql.append(" INNER JOIN dbo.RegistroNota RegistroNota");
		sql.append(" on RegistroNota.idMatricula= Matricula.idMatricula ");
		sql.append(" INNER JOIN dbo.DetCargaLectiva  DetCargaLectiva");
		sql.append(" ON DetMatricula.idDetCargaLectiva = DetCargaLectiva.idDetCargaLectiva ");
		sql.append(" INNER JOIN commo.Item Turno ");
		sql.append(" ON DetCargaLectiva.idTurno = Turno.idItem ");
		sql.append(" INNER JOIN dbo.DetPlanEstudio DetPlanEstudio");
		sql.append(" ON DetCargaLectiva.idDetPlanEstudio = DetPlanEstudio.idDetPlanEstudio ");
		sql.append(" INNER JOIN commo.Item Ciclo ");
		sql.append(" on DetPlanEstudio.idCiclo=Ciclo.idItem");
		sql.append(" where Matricula.idAlumno=$P{CodCarnet} and Matricula.idAnhoSemestre=$P{Id_AnhoSemestre}");
		sql.append(" order by DetPlanEstudio.idCiclo,CodigoAsignatura ");
		
		return sql.toString();
	}
	
	/**
	 * Obtener query ficha matricula sub reporte pre requisito sql.
	 *
	 * @return the string
	 */
	public static String obtenerQueryFichaMatriculaSubReportePreRequisitoSQL() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT");
		sql.append(" DetPlanEstudio.descripcionCurso AS Curso_NomCurso");
		sql.append(" FROM dbo.PreRequisito PreRequisito");
		sql.append(" INNER JOIN dbo.DetPlanEstudio DetPlanEstudio ON");
		sql.append(" PreRequisito.idRequisito=   DetPlanEstudio.idDetPlanEstudio");
		sql.append(" where PreRequisito.idDetPlanEstudio=$P{Id_PlanEstudio}");
		return sql.toString();
	}

	
  	/**
	   * Obtener colores map.
	   *
	   * @return the map
	   */
	  public static Map<Integer,String> obtenerColoresMap() {
  		Map<Integer,String> coloresMap = new HashMap<Integer, String>();
		coloresMap.put(1, "yellow");
		coloresMap.put(2, "green");
		coloresMap.put(3, "orange");
		coloresMap.put(4, "blue");
		coloresMap.put(5, "pink");
		coloresMap.put(6, "gray");
		coloresMap.put(7, "brown");		
		coloresMap.put(8, "purple");
		coloresMap.put(9, "color9");
		coloresMap.put(10, "color10");
		coloresMap.put(11, "color11");
		coloresMap.put(12, "white");
		return coloresMap;
	  }
  	
  	/**
	 * Devolver color.
	 *
	 * @param color el color
	 * @return the string
	 */
	public static String devolverColor(String color) {
		StringBuilder resultado = new StringBuilder();
	    resultado.append(color + " ");
	    return resultado.toString();
	}
	 /**
 	 * Obtener select item.
 	 *
 	 * @param listaItemType el lista item type
 	 * @param listaItemSelectItemVOMap el lista item select item map
 	 * @return the list
 	 */
 	public static List<SelectItemVO> obtenerSelectItemVO(ListaItemType listaItemType, Map<Long, List<SelectItemVO>> listaItemSelectItemVOMap) {
		 List<SelectItemVO> resultado = new ArrayList<SelectItemVO>();
		 List<SelectItemVO> resultadoTemp = listaItemSelectItemVOMap.get(listaItemType.getKey());
		 if (resultadoTemp != null) {
			 for (SelectItemVO SelectItemVO : resultadoTemp) {
				 resultado.add(SelectItemVO);
			}
			 
		 }
		 return resultado;
	 }
 	
 	/**
	  * Obtener select item.
	  *
	  * @param listaItemType el lista item type
	  * @param listaItemSelectItemVOMap el lista item select item map
	  * @return the list
	  */
	 public static List<SelectItemVO> obtenerSelectItemVO(Long listaItemType, Map<Long, List<SelectItemVO>> listaItemSelectItemVOMap) {
		 List<SelectItemVO> resultado = new ArrayList<SelectItemVO>();
		 List<SelectItemVO> resultadoTemp = listaItemSelectItemVOMap.get(listaItemType);
		 if (resultadoTemp != null) {
			 for (SelectItemVO SelectItemVO : resultadoTemp) {
				 resultado.add(SelectItemVO);
			}
			 
		 }
		 return resultado;
	 }
	/**
	 * Generar numero aleatorio de seis cifras.
	 *
	 * @return the string
	 */
	public static String generarNumeroAleatorioDeSeisCifras() {
		Random r = new Random();
		int nextInt = r.nextInt(899999);
		String resultado = String.valueOf(nextInt + 100000);
		return resultado;
	}
	
	/**
	 * Generar numero aleatorio de ocho cifras.
	 *
	 * @return the string
	 */
	public static String generarNumeroAleatorioDeOchoCifras() {
		Random r = new Random();
		int nextInt = r.nextInt(89999999);
		String resultado = String.valueOf(nextInt + 10000000);
		return resultado;
	}
	
	/**
	 * Lista string to arreglo string.
	 *
	 * @param lista el lista
	 * @return the string[]
	 */
	public static String[] listaStringToArregloString(final List<String> lista) {
		String[] resultado = (String[]) lista.toArray(new String[lista.size()]);
		return resultado;
	}

	/**
	 * Lista select item to arreglo select item.
	 *
	 * @param lista el lista
	 * @return the select item[]
	 */
	public static SelectItemVO[] listaSelectItemVOToArregloSelectItemVO(final List<SelectItemVO> lista) {
		SelectItemVO[] resultado = (SelectItemVO[]) lista.toArray(new SelectItemVO[lista.size()]);
		return resultado;
	}

	/**
	 * Convertir lista string a select item.
	 *
	 * @param items el items
	 * @return the list
	 */
	public static List<SelectItemVO> convertirListaStringASelectItemVO(List<String> items) {
		List<SelectItemVO> SelectItemVOs = new ArrayList<SelectItemVO>();
		if (items != null) {
			SelectItemVO SelectItemVO = null;
			for (String item : items) {
				SelectItemVO = new SelectItemVO();
				SelectItemVO.setNombre(item);
				SelectItemVO.setId(item);
				SelectItemVO.setDescripcion(item);
				SelectItemVOs.add(SelectItemVO);
			}
			organizarListaAlfabeticamente(SelectItemVOs);
		}
		return SelectItemVOs;
	}

	/**
	 * Dar select item vacio.
	 *
	 * @return the select item
	 */
	public static SelectItemVO darSelectItemVOVacio() {
		SelectItemVO SelectItemVOVacio = new SelectItemVO();
		SelectItemVOVacio.setId(null);
		SelectItemVOVacio.setNombre("[Seleccionar]");
		return SelectItemVOVacio;
	}
	
	/**
	 * Dar lista select item vacia.
	 *
	 * @return the list
	 */
	public static List<SelectItemVO> darListaSelectItemVOVacia() {
		List<SelectItemVO> SelectItemVOs = null;
		SelectItemVOs = new ArrayList<SelectItemVO>();
		return SelectItemVOs;
	}

	/**
	 * Obtener label de select item.
	 *
	 * @param lista el lista
	 * @param id el id
	 * @return the string
	 */
	public static String obtenerLabelDeSelectItemVO(List<SelectItemVO> lista, Object id) {
		for (SelectItemVO SelectItemVO : lista) {
			if (SelectItemVO.getId() != null && SelectItemVO.getId().equals(id)) {
				return SelectItemVO.getNombre();
			}
		}
		return null;
	}

	/**
	 * Obtener descripcion de select item.
	 *
	 * @param lista el lista
	 * @param id el id
	 * @return the string
	 */
	public static String obtenerDescripcionDeSelectItemVO(List<SelectItemVO> lista, Object id) {
		for (SelectItemVO SelectItemVO : lista) {
			if (SelectItemVO.getId() != null && SelectItemVO.getId().equals(id)) {
				return SelectItemVO.getDescripcion();
			}
		}
		return null;
	}

	/**
	 * Boolean convertidor.
	 *
	 * @param dato el dato
	 * @return the string
	 */
	public static String booleanConvertidor(boolean dato) {
		if (dato) {
			return SI;
		}
		return NO;
	}

	/**
	 * Cambiar nombre archivo.
	 *
	 * @param nombreCompleto el nombre completo
	 * @return the string
	 */
	public static String cambiarNombreArchivo(String nombreCompleto) {
		String nom = nombreCompleto.replace("\\", "#");
		String[] nombres = nom.split("#");
		return nombres[(nombres.length) - 1];
	}

	/**
	 * Pasar parametro.
	 *
	 * @param llave el llave
	 * @param objeto el objeto
	 */
	public static void pasarParametro(String llave, Object objeto) {
		//Contexts.getSessionContext().set(llave, objeto);
	}

	/**
	 * Obtener parametro.
	 *
	 * @param llave el llave
	 * @return the object
	 */
	public static Object obtenerParametro(String llave) {
		//return Contexts.getSessionContext().get(llave);
		return null;
	}
	
	/**
	 * Remover parametro.
	 *
	 * @param llave el llave
	 */
	public static void removerParametro(String llave) {
		//Contexts.getSessionContext().remove(llave);
	}

/*	*//**
 * Organizar lista alfabeticamente.
 *
 * @param lista el lista
 *//*
	private static boolean verificarRolPagina(HashMap<String, List<String>> seguridadPaginas, String Rol, String URL) {
		if (seguridadPaginas != null) {
			List<String> paginas = seguridadPaginas.get(Rol);
			for (String pagina : paginas) {
				if (pagina.equalsIgnoreCase(URL)) {
					return true;
				}
			}
			return false;
		}
		return false;
	}*/

	/**
	 * M�todo para organizar alfabeticamente.
	 * 
	 * @param lista la lista
	 */
	public static void organizarListaAlfabeticamente(List<SelectItemVO> lista) {
		if (lista != null && 1 < lista.size()) {
			Collections.sort(lista, new Comparador());
		}
	}

	/**
	 * <ul>
	 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
	 * </ul>
	 * 
	 * La Class Comparador.
	 *
	 * @author ndavilal.
	 * @version 1.0 , 18/03/2012
	 * @since SIAA 2.0
	 */
	public static class Comparador implements Comparator<SelectItemVO> {
		
		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		public int compare(final SelectItemVO SelectItemVOA, final SelectItemVO SelectItemVOB) {
			int resultado = 0;
			if (SelectItemVOA != null && SelectItemVOB != null) {
				String labelA = SelectItemVOA.getNombre();
				String labelB = SelectItemVOB.getNombre();
				if (labelA != null && labelB != null) {
					String a = aTextoComparable(labelA);
					String b = aTextoComparable(labelB);
					resultado = a.compareTo(b);
				}
			}
			return resultado;
		}
		
		/**
		 * A texto comparable.
		 *
		 * @param texto el texto
		 * @return the string
		 */
		private String aTextoComparable(final String texto) {
			String resultado = "";
			if (texto != null && 0 < texto.length()) {
				resultado = eliminarAcentos(texto);
				resultado = resultado.toLowerCase();
				StringBuffer flujo = new StringBuffer();
				for (int i = 0; i < resultado.length(); i++) {
					if (resultado.charAt(i) < 'o') {
						flujo.append(resultado.charAt(i));
					} else if (resultado.charAt(i) == '�') {
						flujo.append((char) (resultado.charAt(i) - 130));
					} else if (resultado.charAt(i) >= 'o' && resultado.charAt(i) <= 'z') {
						flujo.append((char) (resultado.charAt(i) + 1));
					}
				}
				resultado = flujo.toString();
			}
			return resultado;
		}
	}
	
	/**
	 * Eliminar acentos.
	 *
	 * @param texto el texto
	 * @return the string
	 */
	public static String eliminarAcentos(final String texto) {
		String resultado = "";
		if (texto != null && 0 < texto.length()) {
			resultado = texto;
			resultado = resultado.replace('�', 'A');
			resultado = resultado.replace('�', 'E');
			resultado = resultado.replace('�', 'I');
			resultado = resultado.replace('�', 'O');
			resultado = resultado.replace('�', 'U');
			resultado = resultado.replace('�', 'U');
			resultado = resultado.replace('�', 'a');
			resultado = resultado.replace('�', 'e');
			resultado = resultado.replace('�', 'i');
			resultado = resultado.replace('�', 'o');
			resultado = resultado.replace('�', 'u');
			resultado = resultado.replace('�', 'u');
		}
		return resultado;
	}

	/**
	 * Obtiene default time stamp.
	 *
	 * @param objValue el obj value
	 * @param dateFormat el date format
	 * @return default time stamp
	 */
	public static Timestamp getDefaultTimeStamp(Object objValue, String dateFormat) {
		if (objValue instanceof Timestamp) {
			return (Timestamp) objValue;
		}
		// if(objValue!=null && StringUtils.isNotBlank(objValue.toString()))
		if (objValue != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(upperMonthOfDateFormat(dateFormat));
				java.util.Date date = sdf.parse(objValue.toString());// new
																		// Date(objValue.toString());
				return new java.sql.Timestamp(date.getTime());
			} catch (Exception e) {
				log.error(e);
			}
		}
		return null;
	}

	/**
	 * Obtiene default time stamp.
	 *
	 * @param objValue el obj value
	 * @return default time stamp
	 */
	public static Timestamp getDefaultTimeStamp(Object objValue) {
		try {
			return Timestamp.valueOf(objValue.toString());
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}

	/**
	 * Obtiene current string date.
	 *
	 * @param dateFormat el date format
	 * @return current string date
	 */
	public static String getCurrentStringDate(String dateFormat) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(upperMonthOfDateFormat(dateFormat));
			java.util.Date date = new Date();
			return sdf.format(date).toString();
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}

	/**
	 * Obtiene current timestamp.
	 *
	 * @return current timestamp
	 */
	public static Timestamp getCurrentTimestamp() {
		try {
			return new Timestamp(new Date().getTime());
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}

	/**
	 * Upper month of date format.
	 *
	 * @param dateFormat el date format
	 * @return the string
	 */
	private static String upperMonthOfDateFormat(String dateFormat) {
		String cad = "";
		for (int i = 0; i < dateFormat.length(); i++) {
			char c = dateFormat.charAt(i);
			cad += c == 'm' ? 'M' : c;
		}
		return cad;
	}

	/**
	 * Convertir lista base entidad a select item.
	 *
	 * @param items el items
	 * @return the list
	 */
	
	public static List<SelectItemVO> convertirListaBaseEntidadASelectItemVO(List<? extends BaseEntidad> items) {
		List<SelectItemVO> SelectItemVOs = null;
		SelectItemVOs = new ArrayList<SelectItemVO>();

		if (items != null) {
			for (BaseEntidad item : items) {
				SelectItemVO SelectItemVO = new SelectItemVO();
				SelectItemVO.setNombre(item.getLabel());
				SelectItemVO.setId(item.getId());
				SelectItemVOs.add(SelectItemVO);
			}
			//organizarListaAlfabeticamente(SelectItemVOs);
		}
		
		
		return SelectItemVOs;
	}
	
	/**
	 * Redondear.
	 *
	 * @param numero el numero
	 * @param decimales el decimales
	 * @return the double
	 */
	  public static double redondear(double numero, int decimales) {
	        long factor = (long) Math.pow(10, decimales);
	        // Shift the decimal the correct number of places
	        // to the right.
	        numero = numero * factor;
	        // Round to the nearest integer.
	        long tmp = Math.round(numero);
	        // Shift the decimal the correct number of places
	        // back to the left.
	        return (double) tmp / factor;
	    }
	  
  	/**
	   * Redondear.
	   *
	   * @param numero el numero
	   * @param decimales el decimales
	   * @return the float
	   */
	    public static float redondear(float numero, int decimales) {
	        return (float) redondear((double) numero, decimales);
	    }
		
	/*
	 * Inicio para comparar Listas Genericas
	 */
	/**
	 * <ul>
	 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
	 * </ul>
	 * 
	 * La Class GenericComparator.
	 *
	 * @param <T> el tipo generico
	 * @author ndavilal.
	 * @version 1.0 , 18/03/2012
	 * @since SIAA 2.0
	 */
	public static class GenericComparator<T> implements Comparator<T> {
	    
		/**
		 * Logger para el registro de errores.
		 */
		private static Logger log = Logger.getLogger(Utilidades.class);
		
    	/** La lista metodos. */
    	private List<String> listaMetodos;
	    
    	/** La asc. */
    	private boolean asc = true;
	    
	    /**
    	 * Instancia un nuevo generic comparator.
    	 *
    	 * @param listaMetodos el lista metodos
    	 * @param asc el asc
    	 */
    	public GenericComparator(List<String> listaMetodos, boolean asc) {
	        this.listaMetodos = listaMetodos;
	        this.asc = asc;
	    }
	    
		/**
		 * Obtiene lista metodos.
		 *
		 * @return lista metodos
		 */
		public List<String> getListaMetodos() {
			return listaMetodos;
		}
		
		/**
		 * Establece el lista metodos.
		 *
		 * @param listaMetodos el new lista metodos
		 */
		public void setListaMetodos(List<String> listaMetodos) {
			this.listaMetodos = listaMetodos;
		}

		/**
		 * Comprueba si es asc.
		 *
		 * @return true, si es asc
		 */
		public boolean isAsc() {
			return asc;
		}

		/**
		 * Establece el asc.
		 *
		 * @param asc el new asc
		 */
		public void setAsc(boolean asc) {
			this.asc = asc;
		}
		
	    /* (non-Javadoc)
    	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
    	 */
    	public int compare(T o1, T o2) {
	        int result = -2; //error code
	        Comparable s1 = (Comparable)getValueOfMethodAsObject(o1, listaMetodos);
	        Comparable s2 = (Comparable)getValueOfMethodAsObject(o2, listaMetodos);
	        if (s1 != null && s2 != null) {
	            result = asc ? s1.compareTo(s2) : s2.compareTo(s1);
	        } else if (s1 != null) {
	            result = asc ? 1 : -1;
	        } else if (s2 != null) {
	            result = asc ? -1 : 1;
	        } else {
	            result = 0;
	        }
	        return result;
	    }
	    
	    /**
    	 * Obtiene value of method as object.
    	 *
    	 * @param <E> el tipo de elemento
    	 * @param obj el obj
    	 * @param listaMetodos el lista metodos
    	 * @return value of method as object
    	 */
    	public static final <E> Object getValueOfMethodAsObject(final E obj, final List<String> listaMetodos) {
	        Object result = obj;
	        try {
	            for (String metodo : listaMetodos) {
	            	Method metodoGet = result.getClass().getMethod(metodo);
	            	result = metodoGet.invoke(result, (Object[]) null);
	            }
	        } catch (Exception e) {
	            log.info("GenericComparator - getValueOfMethodAsObject controlled exception");
	        }
	        return result;
	    }
	}
    
    /**
     * Sort list.
     *
     * @param <T> el tipo generico
     * @param aCollection el a collection
     * @param methodName el method name
     * @param asc el asc
     */
    public static <T> void sortList(List<T> aCollection, final String methodName, boolean asc)
    {
    	if (aCollection.size() > 0) {
	        List<String> listaMetodos = encontrarMetodo(aCollection.get(0).getClass(), methodName);
	    	Collections.sort(aCollection, new GenericComparator(listaMetodos,asc));
    	}
    }

	
	/**
	 * Encontrar metodo.
	 *
	 * @param clase el clase
	 * @param methodName el method name
	 * @return the list
	 */
	private static List<String> encontrarMetodo(Class<?> clase, String methodName) {
		methodName = methodName.replaceAll("\\(\\)", "");
		methodName = methodName.replaceAll("\\.", ",");
		List<String> lista = Arrays.asList(methodName.split(","));
		Class<?> classResult = clase;
		
		List<String> listaMetodos = new ArrayList<String>();
		for (String metodo: lista) {
			try {
				String propiedad = metodo;
				boolean encontro = probarMetodo(propiedad, classResult);
				if (!encontro) {
					propiedad = "get" + metodo.substring(0,1).toUpperCase() + metodo.substring(1,metodo.length());
					encontro = probarMetodo(propiedad, classResult);
				}
				if (!encontro) {
					propiedad = "is" + metodo.substring(0,1).toUpperCase() + metodo.substring(1,metodo.length());
					encontro = probarMetodo(propiedad, classResult);
				}
				if (encontro) {
					classResult = classMetodo(propiedad, classResult);
					if (classResult != null) {
						listaMetodos.add(propiedad);
					} else {
						throw new Exception("No se encontr� la clase del M�todo");
					}
				} else {
					throw new Exception("No se encontro el M�todo");
				}
			} catch (Exception e) {
				log.error(e);
			}
		}
		return listaMetodos;
	}
	
	/**
	 * Probar metodo.
	 *
	 * @param metodo el metodo
	 * @param clase el clase
	 * @return true, en caso de exito
	 */
	private static boolean probarMetodo(String metodo, Class<?> clase) {
		try {
			Method metodoGet  = clase.getMethod(metodo);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Class metodo.
	 *
	 * @param metodo el metodo
	 * @param clase el clase
	 * @return the class
	 */
	private static Class<?> classMetodo(String metodo, Class<?> clase) {
		try {
			Class<?> claseResult  = clase.getMethod(metodo).getReturnType();
			return claseResult;
		} catch (Exception e) {
			return null;
		}
	}
	
	

	
	public static List<SunatDatosVO> generaraccionComprobante(SunatDatosVO sfs,String targetURL) {
		List<SunatDatosVO> listaResul = new ArrayList<>();
		URL url;
	    HttpURLConnection connection = null;  
	    String urlParameters="{\"num_ruc\":\""+sfs.getNum_ruc()+"\",\"tip_docu\":\""+sfs.getTip_docu()+"\",\"num_docu\":\""+sfs.getNum_docu()+"\"}";

	    System.out.println("JSN:: " +urlParameters);
	    try {
	      //Create connection
	      url = new URL(targetURL);
	      connection = (HttpURLConnection)url.openConnection();
	      connection.setRequestMethod("POST");
	      connection.setRequestProperty("Content-Type", 
	           "application/json");
	      connection.setRequestProperty("Content-Length", "" + 
	               Integer.toString(urlParameters.getBytes().length));
	      connection.setRequestProperty("Content-Language", "en-US");  
	      connection.setUseCaches (false);
	      connection.setDoInput(true);
	      connection.setDoOutput(true);

	      //Send request
	      DataOutputStream wr = new DataOutputStream (
	                  connection.getOutputStream ());
	      wr.writeBytes (urlParameters);
	      wr.flush ();
	      wr.close ();

	      //Get Response    
	      InputStream is = connection.getInputStream();
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	      String line;
	      StringBuffer response = new StringBuffer(); 
	      while((line = rd.readLine()) != null) {
	        response.append(line);
	        response.append('\r');
	      }
	      rd.close();
	      System.out.println("messageGenerarCom= "+response.toString());
	      List<String> listadoStrin = new ArrayList<>();
	      listadoStrin.add(response.toString());
	      SunatDatosVO obj = new SunatDatosVO();
	      obj.setListaResultado(listadoStrin);	      
	      listaResul.add(obj);

	    } catch (Exception e) {

	      e.printStackTrace();

	    } finally {

	      if(connection != null) {
	        connection.disconnect(); 
	      }
	    }
		return listaResul;
	}
	
	
	public static List<SunatDatosVO> eliminarBandeja() {
		List<SunatDatosVO> listaResul = new ArrayList<>();
		URL url;
	    HttpURLConnection connection = null;  
	    String targetURL="http://localhost:9000/api/EliminarBandeja.htm";//here is my local server url
	    String urlParameters="{\"rutaCertificado\":\"\"}";

	    try {
	      //Create connection
	      url = new URL(targetURL);
	      connection = (HttpURLConnection)url.openConnection();
	      connection.setRequestMethod("POST");
	      connection.setRequestProperty("Content-Type", 
	           "application/json");
	      connection.setRequestProperty("Content-Length", "" + 
	               Integer.toString(urlParameters.getBytes().length));
	      connection.setRequestProperty("Content-Language", "en-US");  
	      connection.setUseCaches (false);
	      connection.setDoInput(true);
	      connection.setDoOutput(true);

	      //Send request
	      DataOutputStream wr = new DataOutputStream (
	                  connection.getOutputStream ());
	      wr.writeBytes (urlParameters);
	      wr.flush ();
	      wr.close ();

	      //Get Response    
	      InputStream is = connection.getInputStream();
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	      String line;
	      StringBuffer response = new StringBuffer(); 
	      while((line = rd.readLine()) != null) {
	        response.append(line);
	        response.append('\r');
	      }
	      rd.close();  

	    } catch (Exception e) {

	      e.printStackTrace();

	    } finally {

	      if(connection != null) {
	        connection.disconnect(); 
	      }
	    }
		return listaResul;
	}
	
	public static List<SunatDatosVO> actualizarPantalla(JsonObject sfs) { 
		List<SunatDatosVO> listaResul = new ArrayList<>();
		URL url;
	    HttpURLConnection connection = null;  
	    String targetURL="http://localhost:9000/api/ActualizarPantalla.htm";//here is my local server url
	    String urlParameters="{\"txtSecuencia\":\"000\"}";
	     
	    try {
	      //Create connection
	      url = new URL(targetURL);
	      connection = (HttpURLConnection)url.openConnection();
	      connection.setRequestMethod("POST");
	      connection.setRequestProperty("Content-Type", 
	           "application/json");
	      connection.setRequestProperty("Content-Length", "" + 
	               Integer.toString(urlParameters.getBytes().length));
	      connection.setRequestProperty("Content-Language", "en-US");  
	      connection.setUseCaches (false);
	      connection.setDoInput(true);
	      connection.setDoOutput(true);

	      //Send request
	      DataOutputStream wr = new DataOutputStream (
	                  connection.getOutputStream ());
	      wr.writeBytes (urlParameters);
	      wr.flush ();
	      wr.close ();

	      //Get Response    
	      InputStream is = connection.getInputStream();
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	      String line;
	      StringBuffer response = new StringBuffer(); 
	      while((line = rd.readLine()) != null) {
	        response.append(line);
	        response.append('\r');
	      }
	      rd.close();
	      //System.out.println("messageActualizar= "+response.toString());
	      List<String> listadoStrin = new ArrayList<>();
	      listadoStrin.add(response.toString());
	      SunatDatosVO obj = new SunatDatosVO();
	      obj.setListaResultado(listadoStrin);
	      listaResul.add(obj);

	    } catch (Exception e) {
	    String mensaje2="Sin Conexion al Servicio de Sunat";	
	    String mensaje ="Sin Conexion al Servicio de Sunat";
	    if(mensaje.equals(mensaje2)) {
		    System.out.println(mensaje);
		    mensaje2="";
	    }
	    

	      //e.printStackTrace();
	    } finally {
	      if(connection != null) {
	        connection.disconnect(); 
	      }
	    } 
	    
	    return listaResul;
	}
    //Fin de Ordenamiento de Listas Gen�ricas
	
	public static void imprimirPedido(String nombreArchivo,String userName,VentaDTO venta) throws IOException, PrinterException{
	    String rutaArchivo = RUTA_RECURSOS_BYTE_BUFFER + ConstanteConfigUtil.generarRuta(userName) + nombreArchivo + ".pdf";
	    File  file = new File(rutaArchivo); 
		PDDocument document = PDDocument.load(file);
		PrintService[] defaultPrintService2 = PrinterJob.lookupPrintServices();

		PDFPrintable printable = new PDFPrintable(document,Scaling.STRETCH_TO_FIT);
		System.out.println("PreparandoPra Imprimir Pedido");
		for (PrintService printService: defaultPrintService2) { 
			if(!StringUtils.isNullOrEmpty(venta.getNameImpresoraCaja()) ) {
				if (printService.getName().trim().equals(venta.getNameImpresoraCaja())) {
					System.out.println("Nombre-ImpreCocina:: " +printService.getName());
					PrintRequestAttributeSet attr_set = new HashPrintRequestAttributeSet();
					attr_set.add(MediaSizeName.E); // <<< supposedly prints in A4 format
					attr_set.add(Sides.ONE_SIDED);
			        PrinterJob job = PrinterJob.getPrinterJob();
			        job.setPageable(new PDFPageable(document));
			        job.setPrintable(printable);
			        job.setPrintService(printService);
			        job.print(attr_set);   
				}	
			} 
		}
 
    }
	
	public static void imprimirFactura(String nombreArchivo,String userName,VentaDTO venta) throws IOException, PrinterException{
		  String rutaArchivo = RUTA_RECURSOS_BYTE_BUFFER + ConstanteConfigUtil.generarRuta(userName) + nombreArchivo + ".pdf";
		    File  file = new File(rutaArchivo); 
			PDDocument document = PDDocument.load(file);
			PrintService[] defaultPrintService2 = PrinterJob.lookupPrintServices();
			PDFPrintable printable = new PDFPrintable(document,Scaling.STRETCH_TO_FIT);
			System.out.println("Preparando_Para Imprimir Pedido");
			for (PrintService printService: defaultPrintService2) { 
				if(!StringUtils.isNullOrEmpty(venta.getNameImpresoraCaja()) ) {
					System.out.println("Nombre_Impresoras:: " +printService.getName());
					if (printService.getName().trim().equals(venta.getNameImpresoraCaja())) {
						System.out.println("Nombre_Impre:: " +printService.getName());
						PrintRequestAttributeSet attr_set = new HashPrintRequestAttributeSet();
						attr_set.add(MediaSizeName.E); // <<< supposedly prints in A4 format
						attr_set.add(Sides.ONE_SIDED);
				        PrinterJob job = PrinterJob.getPrinterJob();
				        job.setPageable(new PDFPageable(document));
				        job.setPrintable(printable);
				        job.setPrintService(printService);
				        job.print(attr_set);
					}	
				} 
			}
	}
	
	public static void imprimirVistaPrevia(String nombreArchivo,String userName,String impresora) throws IOException, PrinterException{
		  String rutaArchivo = RUTA_RECURSOS_BYTE_BUFFER + ConstanteConfigUtil.generarRuta(userName) + nombreArchivo + ".pdf";
		    File  file = new File(rutaArchivo); 
			PDDocument document = PDDocument.load(file);
			PrintService[] defaultPrintService2 = PrinterJob.lookupPrintServices();
			PDFPrintable printable = new PDFPrintable(document,Scaling.STRETCH_TO_FIT);
			System.out.println("Preparando_Para Imprimir Pedido");
			for (PrintService printService: defaultPrintService2) { 
				if(!StringUtils.isNullOrEmpty(impresora) ) {
					System.out.println("Nombre_Impresoras:: " +printService.getName());
					if (printService.getName().trim().equals(impresora)) {
						System.out.println("Nombre_Impre:: " +printService.getName());
						PrintRequestAttributeSet attr_set = new HashPrintRequestAttributeSet();
						attr_set.add(MediaSizeName.E); // <<< supposedly prints in A4 format
						attr_set.add(Sides.ONE_SIDED);
				        PrinterJob job = PrinterJob.getPrinterJob();
				        job.setPageable(new PDFPageable(document));
				        job.setPrintable(printable);
				        job.setPrintService(printService);
				        job.print(attr_set);
					}	
				} 
			}
	}
}