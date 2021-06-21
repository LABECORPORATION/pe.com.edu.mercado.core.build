package pe.com.edu.siaa.core.ui.paginator;

import java.util.List;


/**
 * La Interface IDataProvider.
 * <ul>
 * <li>Copyright 2017 ndavilal-
 * mapfre. Todos los derechos reservados.</li>
 * </ul>
 *
 * @param <T> el tipo generico
 * @author ndavilal
 * @version 1.0, Tue Apr 29 17:38:17 COT 2017
 * @since SIAA-CORE 2.1
 */
public interface IDataProvider<T> {
	
	/**
	 * M�todo que se debe implementar para el manejo de la paginaci�n
	 * fila inicial y cuantas filas quiero.
	 *
	 * @param startRow el start row
	 * @param offset el offset
	 * @return buffered data
	 */
    List<T> getBufferedData(int startRow, int offset);
    
    /**
     * M�todo que se debe implementar para traer el n�mero total de resultados de la consulta.
     *
     * @return total results number
     */
    int getTotalResultsNumber();
    
}
