package sample;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Abonament.
 */
public class Abonament implements Serializable {
    private String nazwa;
    private Integer cena;
    private Integer rozdzialka;
    private Integer ileurzadzen;
    private transient Map<Klient , Date> czaszaplacic;

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
     * Gets rozdzialka.
     *
     * @return the rozdzialka
     */
    public Integer getRozdzialka() {
        return rozdzialka;
    }

    /**
     * Sets rozdzialka.
     *
     * @param rozdzialka the rozdzialka
     */
    public void setRozdzialka(Integer rozdzialka) {
        this.rozdzialka = rozdzialka;
    }

    /**
     * Gets ileurzadzen.
     *
     * @return the ileurzadzen
     */
    public Integer getIleurzadzen() {
        return ileurzadzen;
    }

    /**
     * Sets ileurzadzen.
     *
     * @param ileurzadzen the ileurzadzen
     */
    public void setIleurzadzen(Integer ileurzadzen) {
        this.ileurzadzen = ileurzadzen;
    }

    /**
     * Gets czaszaplacic.
     *
     * @return the czaszaplacic
     */
    public Map<Klient, Date> getCzaszaplacic() {
        return czaszaplacic;
    }

    /**
     * Sets czaszaplacic.
     *
     * @param czaszaplacic the czaszaplacic
     */
    public void setCzaszaplacic(Map<Klient, Date> czaszaplacic) {
        this.czaszaplacic = czaszaplacic;
    }

    /**
     * Instantiates a new Abonament.
     *
     * @param nazwa       the nazwa
     * @param cena        the cena
     * @param rozdzialka  the rozdzialka
     * @param ileurzadzen the ileurzadzen
     */
    public Abonament(String nazwa, Integer cena, Integer rozdzialka, Integer ileurzadzen) {
        this.nazwa = nazwa;
        this.cena = cena;
        this.rozdzialka = rozdzialka;
        this.ileurzadzen = ileurzadzen;
        this.czaszaplacic = new HashMap<>();
    }
}
