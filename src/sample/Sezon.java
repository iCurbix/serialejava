package sample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.*;

/**
 * The type Sezon.
 */
public class Sezon implements Serializable {
    private List<Odcinek> odcinki;
    private Integer numer;
    private Integer cena;
    private transient Map<Klient , Boolean> kupiony;

    /**
     * Gets odcinki.
     *
     * @return the odcinki
     */
    public List<Odcinek> getOdcinki() {
        return odcinki;
    }

    /**
     * Sets odcinki.
     *
     * @param odcinki the odcinki
     */
    public void setOdcinki(List<Odcinek> odcinki) {
        this.odcinki = odcinki;
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
     * Gets cena.
     *
     * @return the cena
     */
    public Integer getCena() {
        return cena;
    }

    /**
     * Sets cena.
     *
     * @param cena the cena
     */
    public void setCena(Integer cena) {
        this.cena = cena;
    }

    /**
     * Gets kupiony.
     *
     * @return the kupiony
     */
    public Map<Klient, Boolean> getKupiony() {
        return kupiony;
    }

    /**
     * Sets kupiony.
     *
     * @param kupiony the kupiony
     */
    public void setKupiony(Map<Klient, Boolean> kupiony) {
        this.kupiony = kupiony;
    }

    /**
     * Instantiates a new Sezon.
     *
     * @param odcinki the odcinki
     * @param numer   the numer
     * @param cena    the cena
     * @param kupiony the kupiony
     */
    public Sezon(List<Odcinek> odcinki, Integer numer, Integer cena, Map<Klient, Boolean> kupiony) {
        this.odcinki = odcinki;
        this.numer = numer;
        this.cena = cena;
        this.kupiony = kupiony;
    }

    /**
     * Instantiates a new Sezon.
     *
     * @param numer the numer
     */
    public Sezon(Integer numer){
        this.odcinki = new ArrayList<>();
        this.numer = numer;
        this.cena = 15;
        this.kupiony = new HashMap<>();
        for (int i = 0; i < 12; i++) {
            odcinki.add(new Odcinek(i));
        }
    }
}
