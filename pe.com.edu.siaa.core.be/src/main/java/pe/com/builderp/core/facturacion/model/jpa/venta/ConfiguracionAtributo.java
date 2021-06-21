package pe.com.builderp.core.facturacion.model.jpa.venta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import pe.com.edu.siaa.core.model.jpa.common.Item;
import pe.com.edu.siaa.core.model.jpa.common.ListaItems;
import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class ConfiguracionAtributo.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Fri Dec 22 11:10:34 COT 2017
 * @since SIAA-CORE 2.1
 */
@Entity
@Table(name = "ConfiguracionAtributo", schema = ConfiguracionEntityManagerUtil.ESQUEMA_FACTURACION)
public class ConfiguracionAtributo implements Serializable {
 
    /** La Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
    /** El id configuracion atributo. */
    @Id
    @Column(name = "idConfiguracionAtributo" , length = 32)
    private String idConfiguracionAtributo;
   
    /** El item by id nombre entidad. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idNombreEntidad", referencedColumnName = "idItem")
    private Item itemByIdNombreEntidad;
   
    /** El item by id componte. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idComponte", referencedColumnName = "idItem")
    private Item itemByIdComponte;
   
    /** El nombre atributo. */
    @Column(name = "nombreAtributo" , length = 20)
    private String nombreAtributo;
   
    /** El tipo dato. */
    @Column(name = "tipoDato" , length = 1)
    private String tipoDato;
   
    /** El formato. */
    @Column(name = "formato" , length = 20)
    private String formato;
   
    /** El lista item. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idListaItem", referencedColumnName = "idListaItems")
    private ListaItems listaItem;
   
    /** El nivel. */
    @Column(name = "nivel" , length = 18)
    private Long nivel;
   
    /** El es persistente. */
    @Column(name = "esPersistente")
    private Boolean esPersistente;
   
    /** El orden. */
    @Column(name = "orden" , length = 18)
    private Long orden;
   
    /** El configuracion atributo configuracion atributo value list. */
    @OneToMany(mappedBy = "configuracionAtributo", fetch = FetchType.LAZY)
    private List<ConfiguracionAtributoValue> configuracionAtributoConfiguracionAtributoValueList = new ArrayList<ConfiguracionAtributoValue>();
    
    /**
     * Instancia un nuevo configuracion atributo.
     */
    public ConfiguracionAtributo() {
    }
   
   
    /**
     * Instancia un nuevo configuracion atributo.
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
    public ConfiguracionAtributo(String idConfiguracionAtributo, Item itemByIdNombreEntidad,Item itemByIdComponte,String nombreAtributo, String tipoDato, String formato, ListaItems listaItem,Long nivel, Boolean esPersistente, Long orden ) {
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
     public Item getItemByIdNombreEntidad() {
        return this.itemByIdNombreEntidad;
    }
    /**
     * Establece el item by id nombre entidad.
     *
     * @param itemByIdNombreEntidad el new item by id nombre entidad
     */
    public void setItemByIdNombreEntidad(Item itemByIdNombreEntidad) {
        this.itemByIdNombreEntidad = itemByIdNombreEntidad;
    }
    /**
     * Obtiene item by id componte.
     *
     * @return item by id componte
     */
     public Item getItemByIdComponte() {
        return this.itemByIdComponte;
    }
    /**
     * Establece el item by id componte.
     *
     * @param itemByIdComponte el new item by id componte
     */
    public void setItemByIdComponte(Item itemByIdComponte) {
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
     public ListaItems getListaItem() {
        return this.listaItem;
    }
    /**
     * Establece el lista item.
     *
     * @param listaItem el new lista item
     */
    public void setListaItem(ListaItems listaItem) {
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
    public List<ConfiguracionAtributoValue> getConfiguracionAtributoConfiguracionAtributoValueList(){
        return this.configuracionAtributoConfiguracionAtributoValueList;
    }
    /**
     * Establece el configuracion atributo list.
     *
     * @param configuracionAtributoList el new configuracion atributo list
     */
    public void setConfiguracionAtributoConfiguracionAtributoValueList(List<ConfiguracionAtributoValue> configuracionAtributoConfiguracionAtributoValueList) {
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
        ConfiguracionAtributo other = (ConfiguracionAtributo) obj;
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
        return "ConfiguracionAtributo [idConfiguracionAtributo=" + idConfiguracionAtributo + "]";
    }
   
}