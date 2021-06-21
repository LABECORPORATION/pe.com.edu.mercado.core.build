package pe.com.edu.siaa.core.ejb.service.automatico.local;

import java.util.Date;

import javax.ejb.Local;

@Local
public interface ProcesoAutomaticoEliminarReporteHistoricoLocal {
	
	/**
	 * Parar.
	 *
	 * @param name el name
	 * @throws Exception the exception
	 */
	void parar(String name) throws Exception;

	
 	void createTimer(Date hour, long interval,String name)  throws Exception;
 	
	
	/**
	 * Execute.
	 */
	String eliminarReporteHistorico();
}
