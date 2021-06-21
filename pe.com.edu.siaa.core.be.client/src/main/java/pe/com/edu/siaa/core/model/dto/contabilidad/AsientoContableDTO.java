package pe.com.edu.siaa.core.model.dto.contabilidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.EntidadDTO;
 

/**
 * La Class AsientoContableDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Tue Dec 26 12:08:01 COT 2017
 * @since SIAA-CORE 2.1
 */
public class AsientoContableDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id asiento contable. */
    private String idAsientoContable;
   
    /** El entidad. */
    private EntidadDTO entidad;
   
    /** El item bylibro. */
    private ItemDTO itemByLibro;
   
    /** El item by sub libro. */
    private ItemDTO itemBySubLibro;
   
    /** El nro correlativo asiento. */
    private String nroCorrelativoAsiento;
   
    /** El fecha operacion. */
    private Date fechaOperacion;
   
    /** El glosa. */
    private String glosa;
   
    /** El nro correlativo operacion. */
    private String nroCorrelativoOperacion;
   
    /** El nro documento operacion. */
    private String nroDocumentoOperacion;
   
    /** El id operacion. */
    private String idOperacion;
   
    /** El fecha creacion. */
    private Date fechaCreacion;
   
    /** El usuario creacion. */
    private String usuarioCreacion;
   
    /** El fecha modificacion. */
    private Date fechaModificacion;
   
    /** El usuario modificacion. */
    private String usuarioModificacion;
   
    /** El ip acceso. */
    private String ipAcceso;
   
    /** El asiento contable asiento contable det list. */
    private List<AsientoContableDetDTO> asientoContableAsientoContableDetList = new ArrayList<AsientoContableDetDTO>();
   
    /**
     * Instancia un nuevo asiento contableDTO.
     */
    public AsientoContableDTO() {
    }
   
   
    /**
     * Instancia un nuevo asiento contableDTO.
     *
     * @param idAsientoContable el id asiento contable
     * @param entidad el entidad
     * @param itemByLibro el item bylibro
     * @param itemBySubLibro el item by sub libro
     * @param nroCorrelativoAsiento el nro correlativo asiento
     * @param fechaOperacion el fecha operacion
     * @param glosa el glosa
     * @param nroCorrelativoOperacion el nro correlativo operacion
     * @param nroDocumentoOperacion el nro documento operacion
     * @param idOperacion el id operacion
     * @param fechaCreacion el fecha creacion
     * @param usuarioCreacion el usuario creacion
     * @param fechaModificacion el fecha modificacion
     * @param usuarioModificacion el usuario modificacion
     * @param ipAcceso el ip acceso
     */
    public AsientoContableDTO(String idAsientoContable, EntidadDTO entidad,ItemDTO itemByLibro,ItemDTO itemBySubLibro,String nroCorrelativoAsiento, Date fechaOperacion, String glosa, String nroCorrelativoOperacion, String nroDocumentoOperacion, String idOperacion, Date fechaCreacion, String usuarioCreacion, Date fechaModificacion, String usuarioModificacion, String ipAcceso ) {
        super();
        this.idAsientoContable = idAsientoContable;
        this.entidad = entidad;
        this.itemByLibro = itemByLibro;
        this.itemBySubLibro = itemBySubLibro;
        this.nroCorrelativoAsiento = nroCorrelativoAsiento;
        this.fechaOperacion = fechaOperacion;
        this.glosa = glosa;
        this.nroCorrelativoOperacion = nroCorrelativoOperacion;
        this.nroDocumentoOperacion = nroDocumentoOperacion;
        this.idOperacion = idOperacion;
        this.fechaCreacion = fechaCreacion;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioModificacion = usuarioModificacion;
        this.ipAcceso = ipAcceso;
    }
   
    //get y set
    /**
     * Obtiene id asiento contable.
     *
     * @return id asiento contable
     */
     public String getIdAsientoContable() {
        return this.idAsientoContable;
    }
    /**
     * Establece el id asiento contable.
     *
     * @param idAsientoContable el new id asiento contable
     */
    public void setIdAsientoContable(String idAsientoContable) {
        this.idAsientoContable = idAsientoContable;
    }
    /**
     * Obtiene entidad.
     *
     * @return entidad
     */
     public EntidadDTO getEntidad() {
        return this.entidad;
    }
    /**
     * Establece el entidad.
     *
     * @param entidad el new entidad
     */
    public void setEntidad(EntidadDTO entidad) {
        this.entidad = entidad;
    }
    /**
     * Obtiene item by libro.
     *
     * @return item by libro
     */
     public ItemDTO getItemByLibro() {
        return this.itemByLibro;
    }
    /**
     * Establece el item bylibro.
     *
     * @param itemBy Libro el new item by libro
     */
    public void setItemByLibro(ItemDTO itemByLibro) {
        this.itemByLibro = itemByLibro;
    }
    /**
     * Obtiene item by sub libro.
     *
     * @return item by sub libro
     */
     public ItemDTO getItemBySubLibro() {
        return this.itemBySubLibro;
    }
    /**
     * Establece el item by sub libro.
     *
     * @param itemBySubLibro el new item by sub libro
     */
    public void setItemBySubLibro(ItemDTO itemBySubLibro) {
        this.itemBySubLibro = itemBySubLibro;
    }
    /**
     * Obtiene nro correlativo asiento.
     *
     * @return nro correlativo asiento
     */
     public String getNroCorrelativoAsiento() {
        return this.nroCorrelativoAsiento;
    }
    /**
     * Establece el nro correlativo asiento.
     *
     * @param nroCorrelativoAsiento el new nro correlativo asiento
     */
    public void setNroCorrelativoAsiento(String nroCorrelativoAsiento) {
        this.nroCorrelativoAsiento = nroCorrelativoAsiento;
    }
    /**
     * Obtiene fecha operacion.
     *
     * @return fecha operacion
     */
     public Date getFechaOperacion() {
        return this.fechaOperacion;
    }
    /**
     * Establece el fecha operacion.
     *
     * @param fechaOperacion el new fecha operacion
     */
    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }
    /**
     * Obtiene glosa.
     *
     * @return glosa
     */
     public String getGlosa() {
        return this.glosa;
    }
    /**
     * Establece el glosa.
     *
     * @param glosa el new glosa
     */
    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }
    /**
     * Obtiene nro correlativo operacion.
     *
     * @return nro correlativo operacion
     */
     public String getNroCorrelativoOperacion() {
        return this.nroCorrelativoOperacion;
    }
    /**
     * Establece el nro correlativo operacion.
     *
     * @param nroCorrelativoOperacion el new nro correlativo operacion
     */
    public void setNroCorrelativoOperacion(String nroCorrelativoOperacion) {
        this.nroCorrelativoOperacion = nroCorrelativoOperacion;
    }
    /**
     * Obtiene nro documento operacion.
     *
     * @return nro documento operacion
     */
     public String getNroDocumentoOperacion() {
        return this.nroDocumentoOperacion;
    }
    /**
     * Establece el nro documento operacion.
     *
     * @param nroDocumentoOperacion el new nro documento operacion
     */
    public void setNroDocumentoOperacion(String nroDocumentoOperacion) {
        this.nroDocumentoOperacion = nroDocumentoOperacion;
    }
    /**
     * Obtiene id operacion.
     *
     * @return id operacion
     */
     public String getIdOperacion() {
        return this.idOperacion;
    }
    /**
     * Establece el id operacion.
     *
     * @param idOperacion el new id operacion
     */
    public void setIdOperacion(String idOperacion) {
        this.idOperacion = idOperacion;
    }
    /**
     * Obtiene fecha creacion.
     *
     * @return fecha creacion
     */
     public Date getFechaCreacion() {
        return this.fechaCreacion;
    }
    /**
     * Establece el fecha creacion.
     *
     * @param fechaCreacion el new fecha creacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    /**
     * Obtiene usuario creacion.
     *
     * @return usuario creacion
     */
     public String getUsuarioCreacion() {
        return this.usuarioCreacion;
    }
    /**
     * Establece el usuario creacion.
     *
     * @param usuarioCreacion el new usuario creacion
     */
    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }
    /**
     * Obtiene fecha modificacion.
     *
     * @return fecha modificacion
     */
     public Date getFechaModificacion() {
        return this.fechaModificacion;
    }
    /**
     * Establece el fecha modificacion.
     *
     * @param fechaModificacion el new fecha modificacion
     */
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
    /**
     * Obtiene usuario modificacion.
     *
     * @return usuario modificacion
     */
     public String getUsuarioModificacion() {
        return this.usuarioModificacion;
    }
    /**
     * Establece el usuario modificacion.
     *
     * @param usuarioModificacion el new usuario modificacion
     */
    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }
    /**
     * Obtiene ip acceso.
     *
     * @return ip acceso
     */
     public String getIpAcceso() {
        return this.ipAcceso;
    }
    /**
     * Establece el ip acceso.
     *
     * @param ipAcceso el new ip acceso
     */
    public void setIpAcceso(String ipAcceso) {
        this.ipAcceso = ipAcceso;
    }
    /**
     * Establece el asiento contable asiento contable det list.
     *
     * @param asientoContableAsientoContableDetList el new asiento contable asiento contable det list
     */
    public List<AsientoContableDetDTO> getAsientoContableAsientoContableDetList(){
        return this.asientoContableAsientoContableDetList;
    }
    /**
     * Establece el asiento contable list.
     *
     * @param asientoContableList el new asiento contable list
     */
    public void setAsientoContableAsientoContableDetList(List<AsientoContableDetDTO> asientoContableAsientoContableDetList) {
        this.asientoContableAsientoContableDetList = asientoContableAsientoContableDetList;
    }   
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idAsientoContable == null) ? 0 : idAsientoContable.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AsientoContableDTO other = (AsientoContableDTO) obj;
        if (idAsientoContable == null) {
            if (other.idAsientoContable != null) {
                return false;
            }
        } else if (!idAsientoContable.equals(other.idAsientoContable)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AsientoContableDTO [idAsientoContable=" + idAsientoContable + "]";
    }
   
}