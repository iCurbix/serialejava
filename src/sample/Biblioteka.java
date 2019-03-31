package sample;

import java.awt.font.GraphicAttribute;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.text.SimpleDateFormat;

/**
 * The type Biblioteka.
 *
 *
 */
public class Biblioteka implements Serializable {
    private List<Serial> seriale;
    private List<Wydarzenie> wydarzenia;
    private List<Film> filmy;

    private List<Aktor> aktorzy;
    private List<Gatunek> gatunki;
    private List<Umowa> umowy;
    private List<Promocja> promocje;
    private transient List<Klient> klienci;
    private List<Abonament> abonamenty;

    private List<String> nazwapula1;
    private List<String> nazwapula2;
    private List<String> krajepula;
    private List<String> gadypula;


    /**
     * Losujprodukt produkt.
     *
     * @param umowa the umowa
     * @return the produkt
     * @throws ParseException the parse exception
     */
    public Produkt losujprodukt(Umowa umowa) throws ParseException {
        Produkt a = new Produkt();
        a.setNazwa(nazwapula1.get((int)(Math.random() * nazwapula1.size())) + " " + nazwapula1.get((int)(Math.random() * nazwapula1.size())) + " " + String.valueOf((int)(Math.random() * 6)) + " " + nazwapula2.get((int)(Math.random() * nazwapula2.size())));
        //System.out.println(a.getNazwa());
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        a.setData(format.parse(String.valueOf((int)(Math.random() * 40 + 1980))));
        //System.out.println(a.getData().toString());
        a.getKraje().add(krajepula.get((int)(Math.random() * krajepula.size())));

        a.setGatunek(gatunki.get((int)(Math.random() * gatunki.size())));
        a.getGatunek().getRzeczy().add(a);
        for (int i = 0; i < 4; i++){
            a.getAktorzy().add(aktorzy.get((int)(Math.random() * aktorzy.size())));
            a.getAktorzy().get(a.getAktorzy().size() - 1).getRzeczy().add(a);
        }
        a.setCena((int)(Math.random() * 25 + 5));
        a.setOpis("jest to " + a.getGatunek().getNazwa());
        a.setDlugosc((int)(Math.random() * 40 + 20));
        a.setUmowa(umowa);
        //a.setZwiastun("vid" + String.valueOf((int)(Math.random() * 3 + 1)) + ".mp4");
        //a.setZwiastun("vid1.mp4");
        return a;
    }

    /**
     * Dodaj film film.
     *
     * @param umowa the umowa
     * @return the film
     * @throws ParseException the parse exception
     */
    public Film dodajFilm(Umowa umowa) throws ParseException {
        Film filmik = new Film(losujprodukt(umowa));
        getFilmy().add(filmik);
        return filmik;
    }

    /**
     * Dodaj serial serial.
     *
     * @param umowa the umowa
     * @return the serial
     * @throws ParseException the parse exception
     */
    public Serial dodajSerial(Umowa umowa) throws ParseException {
        Serial serialik = new Serial(losujprodukt(umowa));
        getSeriale().add(serialik);
        return serialik;
    }

    /**
     * Dodaj wydarzenie wydarzenie.
     *
     * @param umowa the umowa
     * @return the wydarzenie
     * @throws ParseException the parse exception
     */
    public Wydarzenie dodajWydarzenie(Umowa umowa) throws ParseException {
        Wydarzenie wydarzonko = new Wydarzenie(losujprodukt(umowa));
        wydarzonko.setData_wyswietlane(new Date(Main.getNaddata().getTime() + 7L * 24 * 60 * 60 * 1000));
        getWydarzenia().add(wydarzonko);
        return wydarzonko;
    }

    /**
     * Dodaj umowa.
     *
     * @throws ParseException the parse exception
     */
    public void dodajUmowa() throws ParseException {
        Umowa umowka = new Umowa();
        this.getUmowy().add(umowka);
    }

    /**
     * Dodaj promocja.
     */
    public void dodajPromocja(){
        Promocja promocyjka = new Promocja();
        this.promocje.add(promocyjka);
    }

    /**
     * Dodaj gatunek.
     *
     * @param co the co
     */
    public void dodajGatunek(String co){
        Gatunek gadzina = new Gatunek(co , (List<Produkt>) null);
        this.gatunki.add(gadzina);
    }

    /**
     * Dodaj aktor.
     */
    public void dodajAktor(){
        Aktor aktorzyna = new Aktor(nazwapula1.get((int)(Math.random() * nazwapula1.size())) + " " + nazwapula1.get((int)(Math.random() * nazwapula1.size())) , (List<Produkt>) null);
        this.aktorzy.add(aktorzyna);
    }

    /**
     * Dodaj klient.
     *
     * @throws ParseException the parse exception
     */
    public void dodajKlient() throws ParseException {
        Klient klientella = new Klient();
        if (klientella.getAbonament() != null) {
            klientella.getAbonament().getCzaszaplacic().put(klientella, new Date());
            klientella.getAbonament().getCzaszaplacic().get(klientella).setTime(Main.getNaddata().getTime() + 30L * 24 * 60 * 60 * 1000);
        }
        klientella.start();
        this.klienci.add(klientella);
    }

    /**
     * Gets umowy.
     *
     * @return the umowy
     */
    public List<Umowa> getUmowy() {
        return umowy;
    }

    /**
     * Sets umowy.
     *
     * @param umowy the umowy
     */
    public synchronized void setUmowy(List<Umowa> umowy) {
        this.umowy = umowy;
    }

    /**
     * Gets promocje.
     *
     * @return the promocje
     */
    public List<Promocja> getPromocje() {
        return promocje;
    }

    /**
     * Sets promocje.
     *
     * @param promocje the promocje
     */
    public void setPromocje(List<Promocja> promocje) {
        this.promocje = promocje;
    }

    /**
     * Gets klienci.
     *
     * @return the klienci
     */
    public List<Klient> getKlienci() {
        return klienci;
    }

    /**
     * Sets klienci.
     *
     * @param klienci the klienci
     */
    public void setKlienci(List<Klient> klienci) {
        this.klienci = klienci;
    }

    /**
     * Gets abonamenty.
     *
     * @return the abonamenty
     */
    public List<Abonament> getAbonamenty() {
        return abonamenty;
    }

    /**
     * Sets abonamenty.
     *
     * @param abonamenty the abonamenty
     */
    public void setAbonamenty(List<Abonament> abonamenty) {
        this.abonamenty = abonamenty;
    }

    /**
     * Gets gadypula.
     *
     * @return the gadypula
     */
    public List<String> getGadypula() {
        return gadypula;
    }

    /**
     * Sets gadypula.
     *
     * @param gadypula the gadypula
     */
    public void setGadypula(List<String> gadypula) {
        this.gadypula = gadypula;
    }

    /**
     * Gets seriale.
     *
     * @return the seriale
     */
    public synchronized List<Serial> getSeriale() {
        return seriale;
    }

    /**
     * Sets seriale.
     *
     * @param seriale the seriale
     */
    public void setSeriale(List<Serial> seriale) {
        this.seriale = seriale;
    }

    /**
     * Gets wydarzenia.
     *
     * @return the wydarzenia
     */
    public synchronized List<Wydarzenie> getWydarzenia() {
        return wydarzenia;
    }

    /**
     * Sets wydarzenia.
     *
     * @param wydarzenia the wydarzenia
     */
    public void setWydarzenia(List<Wydarzenie> wydarzenia) {
        this.wydarzenia = wydarzenia;
    }

    /**
     * Gets filmy.
     *
     * @return the filmy
     */
    public synchronized List<Film> getFilmy() {
        return filmy;
    }

    /**
     * Sets filmy.
     *
     * @param filmy the filmy
     */
    public void setFilmy(List<Film> filmy) {
        this.filmy = filmy;
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
     * Gets gatunki.
     *
     * @return the gatunki
     */
    public List<Gatunek> getGatunki() {
        return gatunki;
    }

    /**
     * Sets gatunki.
     *
     * @param gatunki the gatunki
     */
    public void setGatunki(List<Gatunek> gatunki) {
        this.gatunki = gatunki;
    }

    /**
     * Gets nazwapula 1.
     *
     * @return the nazwapula 1
     */
    public List<String> getNazwapula1() {
        return nazwapula1;
    }

    /**
     * Sets nazwapula 1.
     *
     * @param nazwapula1 the nazwapula 1
     */
    public void setNazwapula1(List<String> nazwapula1) {
        this.nazwapula1 = nazwapula1;
    }

    /**
     * Gets nazwapula 2.
     *
     * @return the nazwapula 2
     */
    public List<String> getNazwapula2() {
        return nazwapula2;
    }

    /**
     * Sets nazwapula 2.
     *
     * @param nazwapula2 the nazwapula 2
     */
    public void setNazwapula2(List<String> nazwapula2) {
        this.nazwapula2 = nazwapula2;
    }

    /**
     * Gets krajepula.
     *
     * @return the krajepula
     */
    public List<String> getKrajepula() {
        return krajepula;
    }

    /**
     * Sets krajepula.
     *
     * @param krajepula the krajepula
     */
    public void setKrajepula(List<String> krajepula) {
        this.krajepula = krajepula;
    }

    /**
     * Instantiates a new Biblioteka.
     */
    public Biblioteka() {
        filmy = new ArrayList<Film>();
        wydarzenia = new ArrayList<Wydarzenie>();
        seriale = new ArrayList<Serial>();
        nazwapula1 = Stream.of("Lorem","Ipsum","Dolor","Sit","Amet","Consectetur","Adipiscing","Elit","Sed","Do","Eiusmod","Tempor","Incididunt","Ut","Labore","Et","Dolore","Magna","Aliqua").collect(Collectors.toList());
        nazwapula2 = Stream.of("w kosmosie" , "pod wodą" , "reaktywacja" , "ostatnie starcie").collect(Collectors.toList());
        krajepula = Stream.of("USA","Polsza","Japonia","Chiny","UK","Francja").collect(Collectors.toList());
        gadypula = Stream.of("kryminał" , "komedia" , "anime" , "bajeczka dla dzieci" , "thriller" , "horror" , "sci-fi").collect(Collectors.toList());
        gatunki = new ArrayList<Gatunek>();
        aktorzy = new ArrayList<Aktor>();
        umowy = new ArrayList<>();
        promocje = new ArrayList<>();
        klienci = new ArrayList<>();
        abonamenty = new ArrayList<>();
        abonamenty.add(new Abonament("Basic" , 20 , 144 , 1));
        abonamenty.add(new Abonament("Family" , 35 , 1080 , 2));
        abonamenty.add(new Abonament("Premium" , 50 , 2160 , 4));
        for(int i = 0; i < gadypula.size(); i++) dodajGatunek(gadypula.get(i));
        for(int i = 0; i < 20; i++) this.dodajAktor();
    }
}
