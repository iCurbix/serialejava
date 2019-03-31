package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * The type Symulacja.
 */
public class Symulacja extends Thread{
    private Integer kaska;
    private Boolean lecim;
    private Semaphore sem;
    private Semaphore sem2;
    private List<Dane> danekaska;
    private Integer liczonko;

    @Override
    public void run() {
        /*FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            TabPane p = fxmlLoader.load(getClass().getResource("sample.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Controller samleController = fxmlLoader.getController();*/
        Date teraz = new Date();
        liczonko = 0;
        //int mies1 = 0 , mies2 = 0 ,mies3 = 0;
        while (true){
            teraz.setTime(Main.getNaddata().getTime());
            for (int i = 0; i < Main.biblioteka.getUmowy().size(); i++){
                if(teraz.after(Main.biblioteka.getUmowy().get(i).getZakonczenie())) Main.biblioteka.getUmowy().get(i).haracz();
            }
            if(liczonko % 24 == 0){
                System.out.println(getKaska());
                danekaska.add(new Dane(liczonko/24 , getKaska()));
                for(Film f : new ArrayList<Film>(Main.biblioteka.getFilmy())){
                    f.podsumowaniednia();
                }
                for(Serial s : new ArrayList<Serial>(Main.biblioteka.getSeriale())){
                    s.podsumowaniednia();
                }
                for(Wydarzenie w : new ArrayList<Wydarzenie>(Main.biblioteka.getWydarzenia())){
                    w.podsumowaniednia();
                }
            }
            /*if (liczonko % (24 * 30) == 0){
                mies3 = mies2;
                mies2 = mies1;
                mies1 = kaska;
                if(mies3 > mies2 && mies2 > mies1){
                    try {
                        samleController.startstop();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }*/
            liczonko++;

            sem2.release(Main.biblioteka.getKlienci().size());

            try {
                sem.acquire(Main.biblioteka.getKlienci().size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < (int)(Math.random() * 4); i++) {
                if(Math.random() < 0.04) {
                    try {
                        Main.biblioteka.dodajKlient();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            if(Math.random() < 0.0014){
                try {
                    Main.biblioteka.dodajUmowa();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (Math.random() < 0.0005){
                Main.biblioteka.dodajPromocja();
            }


            Main.getNaddata().setTime(Main.getNaddata().getTime() + 3 * 60 * 60 * 1000);
        }
    }

    /**
     * Gets sem.
     *
     * @return the sem
     */
    public Semaphore getSem() {
        return sem;
    }

    /**
     * Gets liczonko.
     *
     * @return the liczonko
     */
    public Integer getLiczonko() {
        return liczonko;
    }

    /**
     * Sets liczonko.
     *
     * @param liczonko the liczonko
     */
    public void setLiczonko(Integer liczonko) {
        this.liczonko = liczonko;
    }

    /**
     * Gets danekaska.
     *
     * @return the danekaska
     */
    public List<Dane> getDanekaska() {
        return danekaska;
    }

    /**
     * Sets danekaska.
     *
     * @param danekaska the danekaska
     */
    public void setDanekaska(List<Dane> danekaska) {
        this.danekaska = danekaska;
    }

    /**
     * Sets sem.
     *
     * @param sem the sem
     */
    public void setSem(Semaphore sem) {
        this.sem = sem;
    }

    /**
     * Gets sem 2.
     *
     * @return the sem 2
     */
    public Semaphore getSem2() {
        return sem2;
    }

    /**
     * Sets sem 2.
     *
     * @param sem2 the sem 2
     */
    public void setSem2(Semaphore sem2) {
        this.sem2 = sem2;
    }

    /**
     * Gets lecim.
     *
     * @return the lecim
     */
    public Boolean getLecim() {
        return lecim;
    }

    /**
     * Sets lecim.
     *
     * @param lecim the lecim
     */
    public void setLecim(Boolean lecim) {
        this.lecim = lecim;
    }

    /**
     * Gets kaska.
     *
     * @return the kaska
     */
    public Integer getKaska() {
        return kaska;
    }

    /**
     * Sets kaska.
     *
     * @param kaska the kaska
     */
    public synchronized void setKaska(Integer kaska) {
        this.kaska = kaska;
    }


    /**
     * Instantiates a new Symulacja.
     *
     * @throws ParseException the parse exception
     */
    public Symulacja() throws ParseException {
        this.kaska = 15000;
        for (int i = 0; i < 5; i++) Main.biblioteka.dodajKlient();
        this.sem = new Semaphore(5);
        this.sem2 = new Semaphore(5);
        danekaska = new ArrayList<>();
    }
}
