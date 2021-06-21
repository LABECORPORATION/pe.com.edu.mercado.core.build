package pe.com.builderp.core.facturacion.model.vo.venta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.com.edu.siaa.core.model.dto.BasePaginator;


/**
 * <ul>
 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Class RegistroNotaDTO.
 *
 * @author ndavilal.
 * @version 1.0 , 06/10/2012
 * @since SIAA 2.0
 */
public class TendenciasVO extends BasePaginator  implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = -5568277544450873979L;

		
	private String count;	
	
	private String nombre;
	
	private String descripcion;
	
	private String color;
	
	private String icon;
	
	
	/**
	 * Instancia un nuevo concepto pago dto.
	 */
	public TendenciasVO() {
		super();
	}


	public String getCount() {
		return count;
	}


	public void setCount(String count) {
		this.count = count;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getIcon() {
		return icon;
	}


	public void setIcon(String icon) {
		this.icon = icon;
	}

	
	
	
}
