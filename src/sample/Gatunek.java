package sample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Gatunek.
 */
public class Gatunek implements Serializable {
    private String nazwa;
    private List<Produkt> rzeczy;

    /**
     * Gets nazwa.
     *
     * @return the nazwa
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     * Sets nazwa.
     *
     * @param nazwa the nazwa
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    /**
     * Gets rzeczy.
     *
     * @return the rzeczy
     */
    public List<Produkt> getRzeczy() {
        return rzeczy;
    }

    /**
     * Sets rzeczy.
     *
     * @param rzeczy the rzeczy
     */
    public void setRzeczy(List<Produkt> rzeczy) {
        this.rzeczy = rzeczy;
    }

    /**
     * Instantiates a new Gatunek.
     *
     * @param nazwa  the nazwa
     * @param rzeczy the rzeczy
     */
    public Gatunek(String nazwa, List<Produkt> rzeczy) {
        this.nazwa = nazwa;
        if (rzeczy != null) {
            this.rzeczy = rzeczy;
        }
        else {
            this.rzeczy = new ArrayList<>();
        }
    }

    /**
     * Instantiates a new Gatunek.
     *
     * @param nazwa the nazwa
     * @param rzecz the rzecz
     */
    public Gatunek(String nazwa, Produkt rzecz) {
        this.nazwa = nazwa;
        this.rzeczy = new ArrayList<>();
        this.rzeczy.add(rzecz);
    }
}
