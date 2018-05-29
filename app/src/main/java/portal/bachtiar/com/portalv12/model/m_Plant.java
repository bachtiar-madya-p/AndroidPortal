package portal.bachtiar.com.portalv12.model;

/**
 * Created by bachtiar on 06/12/17.
 */

public class m_Plant {
    private Integer idPlant;
    private String plant;

    /**
     * No args constructor for use in serialization
     *
     */
    public m_Plant() {
    }

    /**
     *
     * @param plant
     * @param idPlant
     */
    public m_Plant(Integer idPlant, String plant) {
        super();
        this.idPlant = idPlant;
        this.plant = plant;
    }

    public Integer getIdPlant() {
        return idPlant;
    }

    public void setIdPlant(Integer idPlant) {
        this.idPlant = idPlant;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }
}
