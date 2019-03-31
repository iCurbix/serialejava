package sample;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The type Produkt.
 */
public class Produkt implements Serializable {
    /**
     * The Zdjecie.
     */
    protected String zdjecie;
    /**
     * The Nazwa.
     */
    protected String nazwa;
    /**
     * The Opis.
     */
    protected String opis;
    /**
     * The Data.
     */
    protected Date data;
    /**
     * The Dlugosc.
     */
    protected Integer dlugosc;
    /**
     * The Umowa.
     */
    protected Umowa umowa;
    /**
     * The Kraje.
     */
    protected List<String> kraje;
    /**
     * The Oceny.
     */
    protected transient Map<Klient , Integer> oceny;
    /**
     * The Gatunek.
     */
    protected Gatunek gatunek;
    /**
     * The Aktorzy.
     */
    protected List<Aktor> aktorzy;
    /**
     * The Zwiastun.
     */
    protected String zwiastun;
    /**
     * The Cena.
     */
    protected Integer cena;
    /**
     * The Promocja.
     */
    protected Promocja promocja;
    /**
     * The Obejrzany.
     */
    protected transient Map<Klient , Integer> obejrzany;
    /**
     * The Data kupione.
     */
    protected transient Map<Klient , Date> data_kupione;
    /**
     * The Danewyswietlenia.
     */
    protected transient List<Dane> danewyswietlenia;


    /**
     * Obejrzyj.
     *
     * @param klient the klient
     */
    public void obejrzyj(Klient klient){
        if(umowa == null) return;
        if(umowa.getZakonczenie().before(Main.getNaddata())) return;
        if (this instanceof Wydarzenie){
            Main.matrix.setKaska(Main.matrix.getKaska() + cena);
            getData_kupione().put(klient , new Date());
            getData_kupione().get(klient).setTime(Main.getNaddata().getTime() + 7L * 24 * 60 * 60 * 1000);
            zwiekszwys(klient);
            return;
        }
        if (klient.getAbonament() != null) {
            if (klient.getAbonament().getNazwa().equals("Premium") || klient.getAbonament().getNazwa().equals("Family") || klient.getAbonament().getNazwa().equals("Basic")) {
                zwiekszwys(klient);
                return;
            }
        }
        if (getData_kupione().containsKey(klient)) {
            if (getData_kupione().get(klient).before(Main.getNaddata())) {
                zwiekszwys(klient);
            }
            else {
                Main.matrix.setKaska(Main.matrix.getKaska() + cena);
                getData_kupione().replace(klient , new Date());
                getData_kupione().get(klient).setTime(Main.getNaddata().getTime() + 7L * 24 * 60 * 60 * 1000);
                zwiekszwys(klient);
            }
        }
        else{
            Main.matrix.setKaska(Main.matrix.getKaska() + cena);
            getData_kupione().put(klient , new Date());
            getData_kupione().get(klient).setTime(Main.getNaddata().getTime() + 7L * 24 * 60 * 60 * 1000);
            zwiekszwys(klient);
        }
    }

    /**
     * Podsumowaniednia.
     */
    public void podsumowaniednia(){
        int sum = 0;
        for(Integer a : getObejrzany().values()) sum += a;
        getDanewyswietlenia().add(new Dane(Main.matrix.getLiczonko()/24 , sum));
    }

    /**
     * Zwiekszwys.
     *
     * @param klient the klient
     */
    public void zwiekszwys(Klient klient){
        if(!getObejrzany().containsKey(klient)) {
            getObejrzany().put(klient, 1);
        }
        else getObejrzany().replace(klient , obejrzany.get(klient) + 1);
    }

    /**
     * Kup.
     *
     * @param klient  the klient
     * @param naddata the naddata
     */
    public void kup(Klient klient , Date naddata){
        data_kupione.put(klient , naddata);
    }

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
     * Gets opis.
     *
     * @return the opis
     */
    public String getOpis() {
        return opis;
    }

    /**
     * Sets opis.
     *
     * @param opis the opis
     */
    public void setOpis(String opis) {
        this.opis = opis;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(Date data) {
        this.data = data;
    }

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
     * Gets umowa.
     *
     * @return the umowa
     */
    public Umowa getUmowa() {
        return umowa;
    }

    /**
     * Sets umowa.
     *
     * @param umowa the umowa
     */
    public void setUmowa(Umowa umowa) {
        this.umowa = umowa;
    }

    /**
     * Gets kraje.
     *
     * @return the kraje
     */
    public List<String> getKraje() {
        return kraje;
    }

    /**
     * Sets kraje.
     *
     * @param kraje the kraje
     */
    public void setKraje(List<String> kraje) {
        this.kraje = kraje;
    }

    /**
     * Gets oceny.
     *
     * @return the oceny
     */
    public Map<Klient, Integer> getOceny() {
        return oceny;
    }

    /**
     * Sets oceny.
     *
     * @param oceny the oceny
     */
    public void setOceny(Map<Klient, Integer> oceny) {
        this.oceny = oceny;
    }

    /**
     * Gets gatunek.
     *
     * @return the gatunek
     */
    public Gatunek getGatunek() {
        return gatunek;
    }

    /**
     * Sets gatunek.
     *
     * @param gatunek the gatunek
     */
    public void setGatunek(Gatunek gatunek) {
        this.gatunek = gatunek;
    }

    /**
     * Gets aktorzy.
     *
     * @return the aktorzy
     */
    public List<Aktor> getAktorzy() {
        return aktorzy;
    }

    /**
     * Sets aktorzy.
     *
     * @param aktorzy the aktorzy
     */
    public void setAktorzy(List<Aktor> aktorzy) {
        this.aktorzy = aktorzy;
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
     * Gets promocja.
     *
     * @return the promocja
     */
    public Promocja getPromocja() {
        return promocja;
    }

    /**
     * Sets promocja.
     *
     * @param promocja the promocja
     */
    public void setPromocja(Promocja promocja) {
        this.promocja = promocja;
    }

    /**
     * Gets obejrzany.
     *
     * @return the obejrzany
     */
    public synchronized Map<Klient, Integer> getObejrzany() {
        return obejrzany;
    }

    /**
     * Sets obejrzany.
     *
     * @param obejrzany the obejrzany
     */
    public void setObejrzany(Map<Klient, Integer> obejrzany) {
        this.obejrzany = obejrzany;
    }

    /**
     * Gets data kupione.
     *
     * @return the data kupione
     */
    public synchronized Map<Klient, Date> getData_kupione() {
        return data_kupione;
    }

    /**
     * Sets data kupione.
     *
     * @param data_kupione the data kupione
     */
    public void setData_kupione(Map<Klient, Date> data_kupione) {
        this.data_kupione = data_kupione;
    }

    /**
     * Gets zwiastun.
     *
     * @return the zwiastun
     */
    public String getZwiastun() {
        return zwiastun;
    }

    /**
     * Sets zwiastun.
     *
     * @param zwiastun the zwiastun
     */
    public void setZwiastun(String zwiastun) {
        this.zwiastun = zwiastun;
    }

    /**
     * Gets zdjecie.
     *
     * @return the zdjecie
     */
    public String getZdjecie() {
        return zdjecie;
    }

    /**
     * Sets zdjecie.
     *
     * @param zdjecie the zdjecie
     */
    public void setZdjecie(String zdjecie) {
        this.zdjecie = zdjecie;
    }

    /**
     * Gets danewyswietlenia.
     *
     * @return the danewyswietlenia
     */
    public synchronized List<Dane> getDanewyswietlenia() {
        return danewyswietlenia;
    }

    /**
     * Sets danewyswietlenia.
     *
     * @param danewyswietlenia the danewyswietlenia
     */
    public void setDanewyswietlenia(List<Dane> danewyswietlenia) {
        this.danewyswietlenia = danewyswietlenia;
    }

    /**
     * Instantiates a new Produkt.
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
    public Produkt(String nazwa, String opis, Date data, Integer dlugosc, Umowa umowa, List<String> kraje, Map<Klient, Integer> oceny, Gatunek gatunek, List<Aktor> aktorzy, Integer cena, Promocja promocja, Map<Klient, Integer> obejrzany, Map<Klient, Date> data_kupione) {
        this.nazwa = nazwa;
        this.opis = opis;
        this.data = data;
        this.dlugosc = dlugosc;
        this.umowa = umowa;
        this.kraje = kraje;
        this.oceny = oceny;
        this.gatunek = gatunek;
        this.aktorzy = aktorzy;
        this.cena = cena;
        this.promocja = promocja;
        this.obejrzany = obejrzany;
        this.data_kupione = data_kupione;
    }

    /**
     * Instantiates a new Produkt.
     */
    public Produkt() {
        this.nazwa = null;
        this.opis = null;
        this.data = null;
        this.dlugosc = null;
        this.umowa = null;
        this.kraje = new ArrayList<>();
        this.oceny = new HashMap<>();
        this.gatunek = null;
        this.aktorzy = new ArrayList<>();
        this.cena = null;
        this.promocja = null;
        this.obejrzany = new ConcurrentHashMap<>();
        this.data_kupione = new HashMap<>();
        this.zwiastun = "vid" + String.valueOf((int)(Math.random() * 34 + 1)) + ".mp4";
        List<String> zdjpula = Stream.of("img1.png" , "img2.png" , "img3.jpg" , "img4.jpg" , "img5.jpg").collect(Collectors.toList());
        this.zdjecie = zdjpula.get((int)(Math.random() * zdjpula.size()));
        this.danewyswietlenia = new ArrayList<>();
    }

}
