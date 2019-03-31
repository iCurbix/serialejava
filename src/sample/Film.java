package sample;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The type Film.
 */
public class Film extends Produkt implements Serializable {
    /**
     * Instantiates a new Film.
     *
     * @param produkt the produkt
     */
    public Film(Produkt produkt) {
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
    }

    /**
     * Instantiates a new Film.
     *
     * @param nazwa        the nazwa
     * @param opis         the opis
     * @param data         the data
     * @param dlugosc      the dlugosc
     * @param umowa        the umowa
     * @param kraje        the kraje
     * @param oceny        the oceny
     * @param gatunek      the gatunek
     * @param aktorzy      the aktorzy
     * @param cena         the cena
     * @param promocja     the promocja
     * @param obejrzany    the obejrzany
     * @param data_kupione the data kupione
     */
    public Film(String nazwa, String opis, Date data, Integer dlugosc, Umowa umowa, List<String> kraje, Map<Klient, Integer> oceny, Gatunek gatunek, List<Aktor> aktorzy, Integer cena, Promocja promocja, Map<Klient, Integer> obejrzany, Map<Klient, Date> data_kupione) {
        super(nazwa, opis, data, dlugosc, umowa, kraje, oceny, gatunek, aktorzy, cena, promocja, obejrzany, data_kupione);
    }
}

