package pe.com.edu.siaa.core.model.vo;

import java.io.Serializable;
import java.util.Map;

import pe.com.edu.siaa.core.model.type.TipoReporteGenerarType;

/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class FileVO.
 *
 * @author ndavilal
 * @version 1.0 , 06/04/2015
 * @since SIAA-CORE 2.1
 */
public class FileVO implements Serializable {

    /** La Constante serialVersionUID. */
	private static final long serialVersionUID = -3854043104169839788L;

	/** El name. */
    private String name;
    
    /** El mime. */
    private String mime;
    
    /** El length. */
    private long length;
    
    /** La data. */
    private byte[] data;
    
   /** La reporte generado map. */
   private Map<String,Object> reporteGeneradoMap;
    
   private String DataBig;
   
   private String userName;
   
   private TipoReporteGenerarType tipoReporteGenerarType;
   
   private boolean esCopiaCorreo;
   
   private String extension;
   
   private String dataBase64;
   
   private String dataBase64Convert;
   
   private String ruta="";
    /**
     * Obtiene data.
     *
     * @return data
     */
    public byte[] getData() {
        return data;
    }
    
    /**
     * Establece el data.
     *
     * @param data el new data
     */
    public void setData(byte[] data) {
        this.data = data;
    }
    
    /**
     * Obtiene name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Establece el name.
     *
     * @param name el new name
     */
    public void setName(String name) {
        this.name = name;
        int extDot = name.lastIndexOf('.');
        if (extDot > 0) {
            String extension = name.substring(extDot + 1);
            if ("bmp".equals(extension)) {
                mime = "image/bmp";
            } else if ("jpg".equals(extension)) {
                mime = "image/jpeg";
            } else if ("gif".equals(extension)) {
                mime = "image/gif";
            } else if ("png".equals(extension)) {
                mime = "image/png";
            } else if ("xls".equals(extension)) {
                mime = "application/vnd.ms-excel";
            } else if ("xlsx".equals(extension)) {
                mime = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            } else {
                mime = "image/unknown";
            }
        }
    }

    /**
     * Obtiene length.
     *
     * @return length
     */
    public long getLength() {
        return length;
    }
    
    /**
     * Establece el length.
     *
     * @param length el new length
     */
    public void setLength(long length) {
        this.length = length;
    }
    
    /**
     * Obtiene mime.
     *
     * @return mime
     */
    public String getMime() {
        return mime;
    }

	/**
	 * Establece el mime.
	 *
	 * @param mime el new mime
	 */
	public void setMime(String mime) {
		this.mime = mime;
	}

	/**
	 * Obtiene reporte generado map.
	 *
	 * @return reporte generado map
	 */
	public Map<String, Object> getReporteGeneradoMap() {
		return reporteGeneradoMap;
	}

	/**
	 * Sets the reporte generado map.
	 *
	 * @param reporteGeneradoMap el reporte generado map
	 */
	public void setReporteGeneradoMap(Map<String, Object> reporteGeneradoMap) {
		this.reporteGeneradoMap = reporteGeneradoMap;
	}

	public String getDataBig() {
		return DataBig;
	}

	public void setDataBig(String dataBig) {
		DataBig = dataBig;
	}

	public TipoReporteGenerarType getTipoReporteGenerarType() {
		return tipoReporteGenerarType;
	}

	public void setTipoReporteGenerarType(
			TipoReporteGenerarType tipoReporteGenerarType) {
		this.tipoReporteGenerarType = tipoReporteGenerarType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isEsCopiaCorreo() {
		return esCopiaCorreo;
	}

	public void setEsCopiaCorreo(boolean esCopiaCorreo) {
		this.esCopiaCorreo = esCopiaCorreo;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getDataBase64() {
		return dataBase64;
	}

	public void setDataBase64(String dataBase64) {
		this.dataBase64 = dataBase64;
	}

	public String getDataBase64Convert() {
		return dataBase64Convert;
	}

	public void setDataBase64Convert(String dataBase64Convert) {
		this.dataBase64Convert = dataBase64Convert;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	} 
	
    
}