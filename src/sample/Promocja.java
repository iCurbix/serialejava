package sample;

import org.omg.CORBA.DATA_CONVERSION;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Promocja.
 */
public class Promocja implements Serializable {
    private Date rozpoczecie;
    private Date zakonczenie;
    private Integer upust;
    private List<Produkt> rzeczy;

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
     * Gets upust.
     *
     * @return the upust
     */
    public Integer getUpust() {
        return upust;
    }

    /**
     * Sets upust.
     *
     * @param upust the upust
     */
    public void setUpust(Integer upust) {
        this.upust = upust;
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
     * Instantiates a new Promocja.
     *
     * @param rozpoczecie the rozpoczecie
     * @param zakonczenie the zakonczenie
     * @param upust       the upust
     * @param rzeczy      the rzeczy
     */
    public Promocja(Date rozpoczecie, Date zakonczenie, Integer upust, List<Produkt> rzeczy) {
        this.rozpoczecie = rozpoczecie;
        this.zakonczenie = zakonczenie;
        this.upust = upust;
        this.rzeczy = rzeczy;
    }

    /**
     * Instantiates a new Promocja.
     */
    public Promocja(){
        this.rozpoczecie = new Date();
        this.rozpoczecie.setTime(Main.getNaddata().getTime());
        this.zakonczenie = new Date();
        this.zakonczenie.setTime(this.rozpoczecie.getTime() + 30L * 24 * 60 * 60 * 1000);
        this.upust = 20;
        this.rzeczy = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            int indeks = (int)(Math.random() * Main.biblioteka.getFilmy().size());
            this.rzeczy.add(Main.biblioteka.getFilmy().get(indeks));
            Main.biblioteka.getFilmy().get(indeks).setPromocja(this);
        }
        int indeks = (int)(Math.random() * Main.biblioteka.getSeriale().size());
        this.rzeczy.add(Main.biblioteka.getSeriale().get(indeks));
        Main.biblioteka.getSeriale().get(indeks).setPromocja(this);
    }
}
