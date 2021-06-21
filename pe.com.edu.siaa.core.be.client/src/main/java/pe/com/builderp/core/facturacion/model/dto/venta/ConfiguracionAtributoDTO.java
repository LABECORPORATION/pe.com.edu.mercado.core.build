package pe.com.builderp.core.facturacion.model.dto.venta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pe.com.edu.siaa.core.model.dto.BasePaginator;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO;
import pe.com.edu.siaa.core.model.dto.common.ListaItemsDTO;
 

/**
 * La Class ConfiguracionAtributoDTO.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Fri Dec 22 11:07:01 COT 2017
 * @since SIAA-CORE 2.1
 */
public class ConfiguracionAtributoDTO extends BasePaginator implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id configuracion atributo. */
    private String idConfiguracionAtributo;
   
    /** El item by id nombre entidad. */
    private ItemDTO itemByIdNombreEntidad;
   
    /** El item by id componte. */
    private ItemDTO itemByIdComponte;
   
    /** El nombre atributo. */
    private String nombreAtributo;
   
    /** El tipo dato. */
    private String tipoDato;
   
    /** El formato. */
    private String formato;
   
    /** El lista item. */
    private ListaItemsDTO listaItem;
   
    /** El nivel. */
    private Long nivel;
   
    /** El es persistente. */
    private Boolean esPersistente;
   
    /** El orden. */
    private Long orden;
   
    /** El configuracion atributo configuracion atributo value list. */
    private List<ConfiguracionAtributoValueDTO> configuracionAtributoConfiguracionAtributoValueList = new ArrayList<ConfiguracionAtributoValueDTO>();
   
    /**
     * Instancia un nuevo configuracion atributoDTO.
     */
    public ConfiguracionAtributoDTO() {
    }
   
   
    /**
     * Instancia un nuevo configuracion atributoDTO.
     *
     * @param idConfiguracionAtributo el id configuracion atributo
     * @param itemByIdNombreEntidad el item by id nombre entidad
     * @param itemByIdComponte el item by id componte
     * @param nombreAtributo el nombre atributo
     * @param tipoDato el tipo dato
     * @param formato el formato
     * @param listaItem el lista item
     * @param nivel el nivel
     * @param esPersistente el es persistente
     * @param orden el orden
     */
    public ConfiguracionAtributoDTO(String idConfiguracionAtributo, ItemDTO itemByIdNombreEntidad,ItemDTO itemByIdComponte,String nombreAtributo, String tipoDato, String formato, ListaItemsDTO listaItem,Long nivel, Boolean esPersistente, Long orden ) {
        super();
        this.idConfiguracionAtributo = idConfiguracionAtributo;
        this.itemByIdNombreEntidad = itemByIdNombreEntidad;
        this.itemByIdComponte = itemByIdComponte;
        this.nombreAtributo = nombreAtributo;
        this.tipoDato = tipoDato;
        this.formato = formato;
        this.listaItem = listaItem;
        this.nivel = nivel;
        this.esPersistente = esPersistente;
        this.orden = orden;
    }
   
    //get y set
    /**
     * Obtiene id configuracion atributo.
     *
     * @return id configuracion atributo
     */
     public String getIdConfiguracionAtributo() {
        return this.idConfiguracionAtributo;
    }
    /**
     * Establece el id configuracion atributo.
     *
     * @param idConfiguracionAtributo el new id configuracion atributo
     */
    public void setIdConfiguracionAtributo(String idConfiguracionAtributo) {
        this.idConfiguracionAtributo = idConfiguracionAtributo;
    }
    /**
     * Obtiene item by id nombre entidad.
     *
     * @return item by id nombre entidad
     */
     public ItemDTO getItemByIdNombreEntidad() {
        return this.itemByIdNombreEntidad;
    }
    /**
     * Establece el item by id nombre entidad.
     *
     * @param itemByIdNombreEntidad el new item by id nombre entidad
     */
    public void setItemByIdNombreEntidad(ItemDTO itemByIdNombreEntidad) {
        this.itemByIdNombreEntidad = itemByIdNombreEntidad;
    }
    /**
     * Obtiene item by id componte.
     *
     * @return item by id componte
     */
     public ItemDTO getItemByIdComponte() {
        return this.itemByIdComponte;
    }
    /**
     * Establece el item by id componte.
     *
     * @param itemByIdComponte el new item by id componte
     */
    public void setItemByIdComponte(ItemDTO itemByIdComponte) {
        this.itemByIdComponte = itemByIdComponte;
    }
    /**
     * Obtiene nombre atributo.
     *
     * @return nombre atributo
     */
     public String getNombreAtributo() {
        return this.nombreAtributo;
    }
    /**
     * Establece el nombre atributo.
     *
     * @param nombreAtributo el new nombre atributo
     */
    public void setNombreAtributo(String nombreAtributo) {
        this.nombreAtributo = nombreAtributo;
    }
    /**
     * Obtiene tipo dato.
     *
     * @return tipo dato
     */
     public String getTipoDato() {
        return this.tipoDato;
    }
    /**
     * Establece el tipo dato.
     *
     * @param tipoDato el new tipo dato
     */
    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }
    /**
     * Obtiene formato.
     *
     * @return formato
     */
     public String getFormato() {
        return this.formato;
    }
    /**
     * Establece el formato.
     *
     * @param formato el new formato
     */
    public void setFormato(String formato) {
        this.formato = formato;
    }
    /**
     * Obtiene lista item.
     *
     * @return lista item
     */
     public ListaItemsDTO getListaItem() {
        return this.listaItem;
    }
    /**
     * Establece el lista item.
     *
     * @param listaItem el new lista item
     */
    public void setListaItem(ListaItemsDTO listaItem) {
        this.listaItem = listaItem;
    }
    /**
     * Obtiene nivel.
     *
     * @return nivel
     */
     public Long getNivel() {
        return this.nivel;
    }
    /**
     * Establece el nivel.
     *
     * @param nivel el new nivel
     */
    public void setNivel(Long nivel) {
        this.nivel = nivel;
    }
    /**
     * Obtiene es persistente.
     *
     * @return es persistente
     */
     public Boolean getEsPersistente() {
        return this.esPersistente;
    }
    /**
     * Establece el es persistente.
     *
     * @param esPersistente el new es persistente
     */
    public void setEsPersistente(Boolean esPersistente) {
        this.esPersistente = esPersistente;
    }
    /**
     * Obtiene orden.
     *
     * @return orden
     */
     public Long getOrden() {
        return this.orden;
    }
    /**
     * Establece el orden.
     *
     * @param orden el new orden
     */
    public void setOrden(Long orden) {
        this.orden = orden;
    }
    /**
     * Establece el configuracion atributo configuracion atributo value list.
     *
     * @param configuracionAtributoConfiguracionAtributoValueList el new configuracion atributo configuracion atributo value list
     */
    public List<ConfiguracionAtributoValueDTO> getConfiguracionAtributoConfiguracionAtributoValueList(){
        return this.configuracionAtributoConfiguracionAtributoValueList;
    }
    /**
     * Establece el configuracion atributo list.
     *
     * @param configuracionAtributoList el new configuracion atributo list
     */
    public void setConfiguracionAtributoConfiguracionAtributoValueList(List<ConfiguracionAtributoValueDTO> configuracionAtributoConfiguracionAtributoValueList) {
        this.configuracionAtributoConfiguracionAtributoValueList = configuracionAtributoConfiguracionAtributoValueList;
    }   
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idConfiguracionAtributo == null) ? 0 : idConfiguracionAtributo.hashCode());
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
        ConfiguracionAtributoDTO other = (ConfiguracionAtributoDTO) obj;
        if (idConfiguracionAtributo == null) {
            if (other.idConfiguracionAtributo != null) {
                return false;
            }
        } else if (!idConfiguracionAtributo.equals(other.idConfiguracionAtributo)) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ConfiguracionAtributoDTO [idConfiguracionAtributo=" + idConfiguracionAtributo + "]";
    }
   
}