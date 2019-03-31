package sample;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type Odcinek.
 */
public class Odcinek implements Serializable {

    private Integer dlugosc;
    private Integer numer;
    private transient Map<Klient , Boolean> obejrzany;
    private Date premiera;

    /**
     * Gets dlugosc.
     *
     * @return the dlugosc
     */
    public Integer getDlugosc() {
        return dlugosc;
    }

    /**
     * Sets dlugosc.
     *
     * @param dlugosc the dlugosc
     */
    public void setDlugosc(Integer dlugosc) {
        this.dlugosc = dlugosc;
    }

    /**
     * Gets obejrzany.
     *
     * @return the obejrzany
     */
    public Map<Klient, Boolean> getObejrzany() {
        return obejrzany;
    }

    /**
     * Sets obejrzany.
     *
     * @param obejrzany the obejrzany
     */
    public void setObejrzany(Map<Klient, Boolean> obejrzany) {
        this.obejrzany = obejrzany;
    }

    /**
     * Gets premiera.
     *
     * @return the premiera
     */
    public Date getPremiera() {
        return premiera;
    }

    /**
     * Sets premiera.
     *
     * @param premiera the premiera
     */
    public void setPremiera(Date premiera) {
        this.premiera = premiera;
    }

    /**
     * Gets numer.
     *
     * @return the numer
     */
    public Integer getNumer() {
        return numer;
    }

    /**
     * Sets numer.
     *
     * @param numer the numer
     */
    public void setNumer(Integer numer) {
        this.numer = numer;
    }

    /**
     * Instantiates a new Odcinek.
     *
     * @param numer the numer
     */
    public Odcinek(Integer numer) {
        this.dlugosc = (int)(Math.random() * 40 + 20);
        this.numer = numer;
        this.obejrzany = new ConcurrentHashMap<>();
        this.premiera = new Date();
        premiera.setTime(Main.getNaddata().getTime());
    }
}
