package sample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The type Serial.
 */
public class Serial extends Produkt implements Serializable {
    private List<Sezon> sezony;

    /**
     * Instantiates a new Serial.
     */
    public Serial() {
    }

    @Override
    public void obejrzyj(Klient klient) {
        for (Sezon sezon : sezony) {
            boolean ee = true;
            for (int j = 0; j < sezon.getOdcinki().size(); j++) {
                if (sezon.getOdcinki().get(j).getObejrzany().containsKey(klient)) {
                    continue;
                } else {
                    if(umowa == null) return;
                    if(umowa.getZakonczenie().before(Main.getNaddata())) return;
                    if (klient.getAbonament() != null) {
                        if (klient.getAbonament().getNazwa().equals("Premium") || klient.getAbonament().getNazwa().equals("Family") || klient.getAbonament().getNazwa().equals("Basic")) {
                            sezon.getOdcinki().get(j).getObejrzany().put(klient , true);
                            zwiekszwys(klient);
                            return;
                        }
                    }
                    if (getData_kupione().containsKey(klient)) {
                        if (getData_kupione().get(klient).before(Main.getNaddata())) {
                            sezon.getOdcinki().get(j).getObejrzany().put(klient , true);
                            zwiekszwys(klient);
                            return;
                        }
                        else {
                            Main.matrix.setKaska(Main.matrix.getKaska() + cena);
                            getData_kupione().replace(klient , new Date());
                            getData_kupione().get(klient).setTime(Main.getNaddata().getTime() + 7L * 24 * 60 * 60 * 1000);
                            sezon.getOdcinki().get(j).getObejrzany().put(klient , true);
                            zwiekszwys(klient);
                            return;
                        }
                    }
                    else{
                        Main.matrix.setKaska(Main.matrix.getKaska() + cena);
                        getData_kupione().put(klient , new Date());
                        getData_kupione().get(klient).setTime(Main.getNaddata().getTime() + 7L * 24 * 60 * 60 * 1000);
                        sezon.getOdcinki().get(j).getObejrzany().put(klient , true);
                        zwiekszwys(klient);
                        return;
                    }
                }
            }
        }
    }

    /**
     * Gets sezony.
     *
     * @return the sezony
     */
    public List<Sezon> getSezony() {
        return sezony;
    }

    /**
     * Sets sezony.
     *
     * @param sezony the sezony
     */
    public void setSezony(List<Sezon> sezony) {
        this.sezony = sezony;
    }

    /**
     * Instantiates a new Serial.
     *
     * @param produkt the produkt
     */
    public Serial(Produkt produkt){
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
        this.sezony = new ArrayList<>();
        this.sezony.add(new Sezon(1));
        this.sezony.add(new Sezon(2));
    }

    /**
     * Instantiates a new Serial.
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
     * @param sezony       the sezony
     */
    public Serial(String nazwa, String opis, Date data, Integer dlugosc, Umowa umowa, List<String> kraje, Map<Klient, Integer> oceny, Gatunek gatunek, List<Aktor> aktorzy, Integer cena, Promocja promocja, Map<Klient, Integer> obejrzany, Map<Klient, Date> data_kupione, List<Sezon> sezony) {
        super(nazwa, opis, data, dlugosc, umowa, kraje, oceny, gatunek, aktorzy, cena, promocja, obejrzany, data_kupione);
        this.sezony = sezony;
    }
}
