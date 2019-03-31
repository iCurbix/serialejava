package sample;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The type Umowa.
 */
public class Umowa implements Serializable {
    private Integer kasa;
    private Date rozpoczecie;
    private Date zakonczenie;
    private List<Produkt> rzeczy;


    /**
     * Gets kasa.
     *
     * @return the kasa
     */
    public Integer getKasa() {
        return kasa;
    }

    /**
     * Sets kasa.
     *
     * @param kasa the kasa
     */
    public void setKasa(Integer kasa) {
        this.kasa = kasa;
    }

    /**
     * Gets rozpoczecie.
     *
     * @return the rozpoczecie
     */
    public Date getRozpoczecie() {
        return rozpoczecie;
    }

    /**
     * Sets rozpoczecie.
     *
     * @param rozpoczecie the rozpoczecie
     */
    public void setRozpoczecie(Date rozpoczecie) {
        this.rozpoczecie = rozpoczecie;
    }

    /**
     * Gets zakonczenie.
     *
     * @return the zakonczenie
     */
    public Date getZakonczenie() {
        return zakonczenie;
    }

    /**
     * Sets zakonczenie.
     *
     * @param zakonczenie the zakonczenie
     */
    public void setZakonczenie(Date zakonczenie) {
        this.zakonczenie = zakonczenie;
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
     * Instantiates a new Umowa.
     *
     * @param kasa        the kasa
     * @param rozpoczecie the rozpoczecie
     * @param zakonczenie the zakonczenie
     * @param rzeczy      the rzeczy
     */
    public Umowa(Integer kasa, Date rozpoczecie, Date zakonczenie, List<Produkt> rzeczy) {
        this.kasa = kasa;
        this.rozpoczecie = rozpoczecie;
        this.zakonczenie = zakonczenie;
        this.rzeczy = rzeczy;
    }

    /**
     * Haracz.
     */
    public void haracz(){
        Main.matrix.setKaska(Main.matrix.getKaska() - (int)(Math.random() * 5000 + 5000));
        this.zakonczenie.setTime(this.zakonczenie.getTime() + 100L * 24 * 60 * 60 * 1000);
    }

    /**
     * Instantiates a new Umowa.
     *
     * @throws ParseException the parse exception
     */
    public Umowa() throws ParseException {
        this.kasa = (int)(Math.random() * 5000 + 5000);
        Main.matrix.setKaska(Main.matrix.getKaska() - kasa);
        this.rozpoczecie = new Date();
        this.rozpoczecie.setTime(Main.getNaddata().getTime());
        this.zakonczenie = new Date();
        this.zakonczenie.setTime(this.rozpoczecie.getTime() + 100L * 24 * 60 * 60 * 1000);
        this.rzeczy = new ArrayList<>();
        this.rzeczy.add(Main.biblioteka.dodajFilm(this));
        this.rzeczy.add(Main.biblioteka.dodajFilm(this));
        this.rzeczy.add(Main.biblioteka.dodajFilm(this));
        this.rzeczy.add(Main.biblioteka.dodajFilm(this));
        this.rzeczy.add(Main.biblioteka.dodajFilm(this));
        this.rzeczy.add(Main.biblioteka.dodajSerial(this));
        this.rzeczy.add(Main.biblioteka.dodajSerial(this));
        this.rzeczy.add(Main.biblioteka.dodajSerial(this));
        this.rzeczy.add(Main.biblioteka.dodajWydarzenie(this));
    }
}
