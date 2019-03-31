package sample;

import java.io.Serializable;
import java.rmi.MarshalledObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * The type Klient.
 */
public class Klient extends Thread implements Serializable {
    private Integer id;
    private Date narodziny;
    private String mail;
    private String nrkarty;
    private Abonament abonament;

    /**
     * Gets .
     *
     * @return the
     */
    public Integer getid() {
        return id;
    }

    /**
     * Sets .
     *
     * @param id the id
     */
    public void setid(Integer id) {
        this.id = id;
    }

    /**
     * Gets narodziny.
     *
     * @return the narodziny
     */
    public Date getNarodziny() {
        return narodziny;
    }

    /**
     * Sets narodziny.
     *
     * @param narodziny the narodziny
     */
    public void setNarodziny(Date narodziny) {
        this.narodziny = narodziny;
    }

    /**
     * Gets mail.
     *
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * Sets mail.
     *
     * @param mail the mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Gets nrkarty.
     *
     * @return the nrkarty
     */
    public String getNrkarty() {
        return nrkarty;
    }

    /**
     * Sets nrkarty.
     *
     * @param nrkarty the nrkarty
     */
    public void setNrkarty(String nrkarty) {
        this.nrkarty = nrkarty;
    }

    /**
     * Gets abonament.
     *
     * @return the abonament
     */
    public Abonament getAbonament() {
        return abonament;
    }

    /**
     * Sets abonament.
     *
     * @param abonament the abonament
     */
    public void setAbonament(Abonament abonament) {
        this.abonament = abonament;
    }

    /**
     * Instantiates a new Klient.
     *
     * @param id        the id
     * @param narodziny the narodziny
     * @param mail      the mail
     * @param nrkarty   the nrkarty
     * @param abonament the abonament
     */
    public Klient(Integer id, Date narodziny, String mail, String nrkarty, Abonament abonament) {
        this.id = id;
        this.narodziny = narodziny;
        this.mail = mail;
        this.nrkarty = nrkarty;
        this.abonament = abonament;
    }

    /**
     * Instantiates a new Klient.
     *
     * @throws ParseException the parse exception
     */
    public Klient() throws ParseException {
        this.id = Main.biblioteka.getKlienci().size();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        this.narodziny = format.parse(String.valueOf((int)(Math.random() * 40 + 1980)));
        this.mail = "noszePLecak@buziaczek.pl";
        this.nrkarty = "1234 5678 9101 1121";
        double co = Math.random();
        if (co < 0.25){
            this.abonament = Main.biblioteka.getAbonamenty().get(0);
        }
        else if(co < 0.5){
            this.abonament = Main.biblioteka.getAbonamenty().get(1);
        }
        else if(co < 0.75){
            this.abonament = Main.biblioteka.getAbonamenty().get(2);
        }
        else{
            this.abonament = (Abonament) null;
        }
        this.abonament = (Abonament)null;
    }

    /**
     * Ogladajcos.
     */
    public void ogladajcos(){
        for (Wydarzenie w : new ArrayList<Wydarzenie>(Main.biblioteka.getWydarzenia())){
            if (w.getData_wyswietlane().before(Main.getNaddata()) && w.getData_wyswietlane().after(new Date(Main.getNaddata().getTime() + 24 * 60 * 60 * 1000))){
                w.obejrzyj(this);
                return;
            }
        }
        double co = Math.random();
        try {
            if (co < 0.5) {
                Main.biblioteka.getFilmy().get((int) (Math.random() * Main.biblioteka.getFilmy().size())).obejrzyj(this);
            } else {
                Main.biblioteka.getSeriale().get((int) (Math.random() * Main.biblioteka.getSeriale().size())).obejrzyj(this);
            }
        }
        catch (NullPointerException e){
            System.out.println("yy no takie żytko");
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Main.matrix.getSem2().acquire(1);


                if(this.abonament != null){
                    if (abonament.getCzaszaplacic().get(this).after(Main.getNaddata())){
                        Main.matrix.setKaska(Main.matrix.getKaska() + abonament.getCena());
                        abonament.getCzaszaplacic().replace(this , new Date());
                        abonament.getCzaszaplacic().get(this).setTime(Main.getNaddata().getTime() + 30L * 24 * 60 * 60 * 1000);
                    }
                    if (Math.random() < 0.25){
                        ogladajcos();
                    }
                }
                else {
                    if (Math.random() < 0.1){
                        ogladajcos();
                    }
                }


                Main.matrix.getSem().release(1);
            } catch (InterruptedException | IndexOutOfBoundsException e) {
                System.out.println("bywa i tak");
                //e.printStackTrace();
            }
        }
    }
}
