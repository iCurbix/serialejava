package sample;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The type Wydarzenie.
 */
public class Wydarzenie extends Produkt implements Serializable {
    private Date data_wyswietlane;

    /**
     * Instantiates a new Wydarzenie.
     *
     * @param produkt the produkt
     */
    public Wydarzenie(Produkt produkt){
        this.data_kupione = produkt.getData_kupione();
        this.data = produkt.getData();
        this.obejrzany = produkt.getObejrzany();
        this.aktorzy = produkt.getAktorzy();
        this.cena = produkt.getCena();
        this.dlugosc = produkt.getDlugosc();
        this.gatunek = produkt.getGatunek();
        this.kraje = produkt.getKraje();
        this.nazwa = produkt.getNazwa();
        this.oceny = produkt.getOceny();
        this.opis = produkt.getOpis();
        this.promocja = produkt.getPromocja();
        this.umowa = produkt.getUmowa();
        this.data_wyswietlane = null;
    }

    /**
     * Gets data wyswietlane.
     *
     * @return the data wyswietlane
     */
    public Date getData_wyswietlane() {
        return data_wyswietlane;
    }

    /**
     * Sets data wyswietlane.
     *
     * @param data_wyswietlane the data wyswietlane
     */
    public void setData_wyswietlane(Date data_wyswietlane) {
        this.data_wyswietlane = data_wyswietlane;
    }

    /**
     * Instantiates a new Wydarzenie.
     *
     * @param nazwa            the nazwa
     * @param opis             the opis
     * @param data             the data
     * @param dlugosc          the dlugosc
     * @param umowa            the umowa
     * @param kraje            the kraje
     * @param oceny            the oceny
     * @param gatunek          the gatunek
     * @param aktorzy          the aktorzy
     * @param cena             the cena
     * @param promocja         the promocja
     * @param obejrzany        the obejrzany
     * @param data_kupione     the data kupione
     * @param data_wyswietlane the data wyswietlane
     */
    public Wydarzenie(String nazwa, String opis, Date data, Integer dlugosc, Umowa umowa, List<String> kraje, Map<Klient, Integer> oceny, Gatunek gatunek, List<Aktor> aktorzy, Integer cena, Promocja promocja, Map<Klient, Integer> obejrzany, Map<Klient, Date> data_kupione, Date data_wyswietlane) {
        super(nazwa, opis, data, dlugosc, umowa, kraje, oceny, gatunek, aktorzy, cena, promocja, obejrzany, data_kupione);
        this.data_wyswietlane = data_wyswietlane;
    }
}
