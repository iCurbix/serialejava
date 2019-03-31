package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The type Controller.
 */
public class Controller implements Initializable {
    @FXML
    private Label countLabel;

    @FXML
    private Label labelZwiastunFilm;

    @FXML
    private Label labelGatunekFilm;

    @FXML
    private TableView<Film> tableFilm;

    @FXML
    private TableColumn<Film , String> columnFilm;

    @FXML
    private TableView<Serial> tableSerial;

    @FXML
    private TableColumn<Serial , String> columnSerial;

    @FXML
    private TableView<Wydarzenie> tableWydarzenie;

    @FXML
    private TableColumn<Wydarzenie , String> columnWydarzenie;

    @FXML
    private MediaView videoFilm;

    @FXML
    private MediaPlayer mediaPlayer;

    @FXML
    private List<String> vid;

    @FXML
    private TabPane paneRzeczy;

    @FXML
    private Button buttonStartStop;

    @FXML
    private Button buttonZapisz;

    @FXML
    private Button buttonLaduj;

    @FXML
    private TextField textSzukaj;

    @FXML
    private Button buttonSzukaj;

    @FXML
    private LineChart chart;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private LineChart chart2;

    @FXML
    private NumberAxis xAxis2;

    @FXML
    private NumberAxis yAxis2;

    @FXML
    private Label labelRok;

    @FXML
    private Label labelDlugosc;

    @FXML
    private Label labelDlugosc0;

    @FXML
    private Label labelKraje;

    @FXML
    private Label labelOpis0;

    @FXML
    private Label labelOpis;

    @FXML
    private Label labelCena0;

    @FXML
    private Label labelCena;

    @FXML
    private Label labelAktorzy;

    @FXML
    private Label labelAktor1;

    @FXML
    private Label labelAktor2;

    @FXML
    private Label labelAktor3;

    @FXML
    private Label labelAktor4;

    @FXML
    private ImageView image;


    private ObservableList<Film> filmy;
    private ObservableList<Serial> seriale;
    private ObservableList<Wydarzenie> wydarzenia;
    private int stan;


    @FXML
    private void test(ActionEvent event){
        countLabel.setText(Integer.toString(Integer.parseInt(countLabel.getText())+1));
    }

    @FXML
    private void test2(){
        System.out.println("dzialaj kapiszonie!!!");
    }

    @FXML
    private void laduj(){
        try {
            seriale = FXCollections.observableArrayList();
            filmy = FXCollections.observableArrayList();
            wydarzenia = FXCollections.observableArrayList();

            filmy.addAll(Main.biblioteka.getFilmy());
            tableFilm.setItems(filmy);

            seriale.addAll(Main.biblioteka.getSeriale());
            tableSerial.setItems(seriale);

            wydarzenia.addAll(Main.biblioteka.getWydarzenia());
            tableWydarzenie.setItems(wydarzenia);
        }
        catch (NullPointerException ex){
            System.out.println("GUANO!!!!!");
        }
    }

    @FXML
    private void szukaj(){
        tableFilm.getItems().stream()
                .filter(item -> item.getNazwa().toLowerCase().contains(textSzukaj.getText().toLowerCase()))
                .findAny()
                .ifPresent(item -> {
                    tableFilm.getSelectionModel().select(item);
                    tableFilm.scrollTo(item);
                });
        tableSerial.getItems().stream()
                .filter(item -> item.getNazwa().toLowerCase().contains(textSzukaj.getText().toLowerCase()))
                .findAny()
                .ifPresent(item -> {
                    tableSerial.getSelectionModel().select(item);
                    tableSerial.scrollTo(item);
                });
        tableWydarzenie.getItems().stream()
                .filter(item -> item.getNazwa().toLowerCase().contains(textSzukaj.getText().toLowerCase()))
                .findAny()
                .ifPresent(item -> {
                    tableWydarzenie.getSelectionModel().select(item);
                    tableWydarzenie.scrollTo(item);
                });
    }

    /**
     * Pokacos.
     *
     * @throws IOException               the io exception
     * @throws InvocationTargetException the invocation target exception
     */
    @FXML
    public void pokacos() throws IOException , InvocationTargetException {
        int co = paneRzeczy.getSelectionModel().getSelectedIndex();
        try {
            mediaPlayer.stop();
        }
        catch (NullPointerException e){
            System.out.println("no bywa i tak");
        }

        switch (co) {
            case 0:
                try {
                    Image img = new Image(this.getClass().getResource(tableFilm.getSelectionModel().getSelectedItem().getZdjecie()).toExternalForm());
                    image.setImage(img);
                }
                catch (RuntimeException e){
                    System.out.println("gdzies to tam jest");
                }
                //System.out.println(System.getProperty("user.dir"));
                //FileInputStream inputstream = new FileInputStream("src/sample/" + Main.biblioteka.getFilmy().get(tableFilm.getSelectionModel().getSelectedIndex()).getZdjecie());
                //Image img = new Image(inputstream);
                labelGatunekFilm.setText(Main.biblioteka.getFilmy().get(tableFilm.getSelectionModel().getSelectedIndex()).getGatunek().getNazwa());
                labelZwiastunFilm.visibleProperty().setValue(true);
                labelOpis0.visibleProperty().setValue(true);
                labelAktorzy.visibleProperty().setValue(true);
                labelCena0.visibleProperty().setValue(true);
                labelDlugosc0.visibleProperty().setValue(true);
                labelRok.setText(Main.biblioteka.getFilmy().get(tableFilm.getSelectionModel().getSelectedIndex()).getData().toString().substring(24));
                labelDlugosc.setText(Main.biblioteka.getFilmy().get(tableFilm.getSelectionModel().getSelectedIndex()).getDlugosc().toString());
                labelKraje.setText(Main.biblioteka.getFilmy().get(tableFilm.getSelectionModel().getSelectedIndex()).getKraje().get(0));
                labelOpis.setText(Main.biblioteka.getFilmy().get(tableFilm.getSelectionModel().getSelectedIndex()).getOpis());
                labelAktor1.setText(Main.biblioteka.getFilmy().get(tableFilm.getSelectionModel().getSelectedIndex()).getAktorzy().get(0).getNazwa());
                labelAktor2.setText(Main.biblioteka.getFilmy().get(tableFilm.getSelectionModel().getSelectedIndex()).getAktorzy().get(1).getNazwa());
                labelAktor3.setText(Main.biblioteka.getFilmy().get(tableFilm.getSelectionModel().getSelectedIndex()).getAktorzy().get(2).getNazwa());
                labelAktor4.setText(Main.biblioteka.getFilmy().get(tableFilm.getSelectionModel().getSelectedIndex()).getAktorzy().get(3).getNazwa());
                labelCena.setText(Main.biblioteka.getFilmy().get(tableFilm.getSelectionModel().getSelectedIndex()).getCena().toString());
                mediaPlayer = new MediaPlayer(new Media(this.getClass().getResource(tableFilm.getSelectionModel().getSelectedItem().getZwiastun()).toExternalForm()));

                if(stan == 1) {
                    chart2.setVisible(true);
                    xAxis2.setLabel("dni");
                    yAxis2.setLabel("wyświetlenia");
                    XYChart.Series series = new XYChart.Series<>();
                    for (Dane d : Main.biblioteka.getFilmy().get(tableFilm.getSelectionModel().getSelectedIndex()).getDanewyswietlenia()) {
                        series.getData().add(new XYChart.Data(d.getX(), d.getY()));
                    }
                    chart2.setCreateSymbols(false);
                    chart2.getData().removeAll(Collections.singleton(chart2.getData().setAll()));
                    chart2.getData().add(series);
                    chart2.setLegendVisible(false);
                }
                break;
            case 1:
                try {
                    Image img = new Image(this.getClass().getResource(tableSerial.getSelectionModel().getSelectedItem().getZdjecie()).toExternalForm());
                    image.setImage(img);
                }
                catch (RuntimeException e){
                    System.out.println("gdzies to tam jest");
                }
                labelGatunekFilm.setText(Main.biblioteka.getSeriale().get(tableSerial.getSelectionModel().getSelectedIndex()).getGatunek().getNazwa());
                labelZwiastunFilm.visibleProperty().setValue(true);
                labelOpis0.visibleProperty().setValue(true);
                labelAktorzy.visibleProperty().setValue(true);
                labelCena0.visibleProperty().setValue(true);
                labelDlugosc0.visibleProperty().setValue(true);
                labelRok.setText(Main.biblioteka.getSeriale().get(tableSerial.getSelectionModel().getSelectedIndex()).getData().toString().substring(24));
                labelDlugosc.setText(Main.biblioteka.getSeriale().get(tableSerial.getSelectionModel().getSelectedIndex()).getDlugosc().toString());
                labelKraje.setText(Main.biblioteka.getSeriale().get(tableSerial.getSelectionModel().getSelectedIndex()).getKraje().get(0));
                labelOpis.setText(Main.biblioteka.getSeriale().get(tableSerial.getSelectionModel().getSelectedIndex()).getOpis());
                labelAktor1.setText(Main.biblioteka.getSeriale().get(tableSerial.getSelectionModel().getSelectedIndex()).getAktorzy().get(0).getNazwa());
                labelAktor2.setText(Main.biblioteka.getSeriale().get(tableSerial.getSelectionModel().getSelectedIndex()).getAktorzy().get(1).getNazwa());
                labelAktor3.setText(Main.biblioteka.getSeriale().get(tableSerial.getSelectionModel().getSelectedIndex()).getAktorzy().get(2).getNazwa());
                labelAktor4.setText(Main.biblioteka.getSeriale().get(tableSerial.getSelectionModel().getSelectedIndex()).getAktorzy().get(3).getNazwa());
                labelCena.setText(Main.biblioteka.getSeriale().get(tableSerial.getSelectionModel().getSelectedIndex()).getCena().toString());
                mediaPlayer = new MediaPlayer(new Media(this.getClass().getResource(tableSerial.getSelectionModel().getSelectedItem().getZwiastun()).toExternalForm()));
                if(stan == 1) {
                    chart2.setVisible(true);
                    xAxis2.setLabel("dni");
                    yAxis2.setLabel("wyświetlenia");
                    XYChart.Series series = new XYChart.Series<>();
                    for (Dane d : Main.biblioteka.getSeriale().get(tableSerial.getSelectionModel().getSelectedIndex()).getDanewyswietlenia()) {
                        series.getData().add(new XYChart.Data(d.getX(), d.getY()));
                    }
                    chart2.setCreateSymbols(false);
                    chart2.getData().removeAll(Collections.singleton(chart2.getData().setAll()));
                    chart2.getData().add(series);
                    chart2.setLegendVisible(false);
                }
                break;
            case 2:
                try {
                    Image img = new Image(this.getClass().getResource(tableWydarzenie.getSelectionModel().getSelectedItem().getZdjecie()).toExternalForm());
                    image.setImage(img);
                }
                catch (RuntimeException e){
                    System.out.println("gdzies to tam jest");
                }
                labelGatunekFilm.setText(Main.biblioteka.getWydarzenia().get(tableWydarzenie.getSelectionModel().getSelectedIndex()).getGatunek().getNazwa());
                labelZwiastunFilm.visibleProperty().setValue(true);
                labelOpis0.visibleProperty().setValue(true);
                labelAktorzy.visibleProperty().setValue(true);
                labelCena0.visibleProperty().setValue(true);
                labelDlugosc0.visibleProperty().setValue(true);
                labelRok.setText(Main.biblioteka.getWydarzenia().get(tableWydarzenie.getSelectionModel().getSelectedIndex()).getData().toString().substring(24));
                labelDlugosc.setText(Main.biblioteka.getWydarzenia().get(tableWydarzenie.getSelectionModel().getSelectedIndex()).getDlugosc().toString());
                labelKraje.setText(Main.biblioteka.getWydarzenia().get(tableWydarzenie.getSelectionModel().getSelectedIndex()).getKraje().get(0));
                labelOpis.setText(Main.biblioteka.getWydarzenia().get(tableWydarzenie.getSelectionModel().getSelectedIndex()).getOpis());
                labelAktor1.setText(Main.biblioteka.getWydarzenia().get(tableWydarzenie.getSelectionModel().getSelectedIndex()).getAktorzy().get(0).getNazwa());
                labelAktor2.setText(Main.biblioteka.getWydarzenia().get(tableWydarzenie.getSelectionModel().getSelectedIndex()).getAktorzy().get(1).getNazwa());
                labelAktor3.setText(Main.biblioteka.getWydarzenia().get(tableWydarzenie.getSelectionModel().getSelectedIndex()).getAktorzy().get(2).getNazwa());
                labelAktor4.setText(Main.biblioteka.getWydarzenia().get(tableWydarzenie.getSelectionModel().getSelectedIndex()).getAktorzy().get(3).getNazwa());
                labelCena.setText(Main.biblioteka.getWydarzenia().get(tableWydarzenie.getSelectionModel().getSelectedIndex()).getCena().toString());
                mediaPlayer = new MediaPlayer(new Media(this.getClass().getResource(tableWydarzenie.getSelectionModel().getSelectedItem().getZwiastun()).toExternalForm()));
                if(stan == 1) {
                    chart2.setVisible(true);
                    xAxis2.setLabel("dni");
                    yAxis2.setLabel("wyświetlenia");
                    XYChart.Series series = new XYChart.Series<>();
                    for (Dane d : Main.biblioteka.getWydarzenia().get(tableWydarzenie.getSelectionModel().getSelectedIndex()).getDanewyswietlenia()) {
                        series.getData().add(new XYChart.Data(d.getX(), d.getY()));
                    }
                    chart2.setCreateSymbols(false);
                    chart2.getData().removeAll(Collections.singleton(chart2.getData().setAll()));
                    chart2.getData().add(series);
                    chart2.setLegendVisible(false);
                }
                break;

        }

        videoFilm.setMediaPlayer(mediaPlayer);
        mediaPlayer.autoPlayProperty().setValue(true);
    }

    /**
     * Pauza.
     */
    @FXML
    public void pauza(){
        if(mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING))
            mediaPlayer.pause();
        else mediaPlayer.play();
    }

    /**
     * Dodaj.
     *
     * @throws ParseException the parse exception
     */
    @FXML
    public void dodaj() throws ParseException {
        int co = paneRzeczy.getSelectionModel().getSelectedIndex();
        switch (co) {
            case 0:
                Main.biblioteka.dodajFilm(Main.biblioteka.getUmowy().get(0));
                //Main.biblioteka.dodajUmowa();
                break;
            case 1:
                Main.biblioteka.dodajSerial(Main.biblioteka.getUmowy().get(0));
                break;
            case 2:
                Main.biblioteka.dodajWydarzenie(Main.biblioteka.getUmowy().get(0));
                break;
        }
        laduj();
    }

    /**
     * Dodajumowa.
     *
     * @throws ParseException the parse exception
     */
    @FXML
    public void dodajumowa() throws ParseException {
        Main.biblioteka.dodajUmowa();
        laduj();
    }

    /**
     * Usun.
     */
    @FXML
    public void usun(){
        int co = paneRzeczy.getSelectionModel().getSelectedIndex();
        switch (co) {
            case 0:
                Main.biblioteka.getFilmy().remove(tableFilm.getSelectionModel().getSelectedIndex());
                break;
            case 1:
                Main.biblioteka.getSeriale().remove(tableSerial.getSelectionModel().getSelectedIndex());
                break;
            case 2:
                Main.biblioteka.getWydarzenia().remove(tableWydarzenie.getSelectionModel().getSelectedIndex());
                break;
        }
        laduj();
    }


    /**
     * Startstop.
     *
     * @throws InterruptedException the interrupted exception
     */
    @FXML
    public void startstop() throws InterruptedException {
        if(stan == 0){
            stan = 1;
            try {
                Main.matrix.start();
        }
            catch (IllegalThreadStateException e){
                System.out.println("zytko");
            }
            buttonZapisz.setDisable(true);
            buttonLaduj.setDisable(true);
            buttonStartStop.setText("stop");
        }
        else {
            Main.matrix.stop();
            while (true) {
                try {
                    for (Klient k : Main.biblioteka.getKlienci()) {
                        k.stop();
                    }
                    break;
                } catch (RuntimeException e) {
                    System.out.println("nie ma takiego bicia");
                }
            }
            buttonZapisz.setDisable(false);
            buttonLaduj.setDisable(false);
            buttonStartStop.setText("start");
            buttonStartStop.setDisable(true);

            xAxis.setLabel("dni");
            yAxis.setLabel("kasa");
            XYChart.Series series = new XYChart.Series<>();
            for (Dane d : Main.matrix.getDanekaska()){
                series.getData().add(new XYChart.Data(d.getX(), d.getY()));
            }
            chart.setCreateSymbols(false);
            chart.getData().add(series);
            chart.setLegendVisible(false);
        }
    }

    /**
     * Umrzyj.
     */
    @FXML
    public void umrzyj(){
        Main.matrix.stop();
        for (Klient k : Main.biblioteka.getKlienci()){
            k.stop();
        }
    }

    /**
     * Zapiszbiblioteke.
     *
     * @throws IOException the io exception
     */
    @FXML
    public void zapiszbiblioteke() throws IOException {
        for (Klient k : Main.biblioteka.getKlienci()) {
            k.stop();
        }
        ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream("biblioteka.exe")));
        out.writeObject(Main.biblioteka);
        out.close();
    }

    /**
     * Ladujbiblioteke.
     */
    @FXML
    public void ladujbiblioteke(){
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream("biblioteka.exe")));
            Main.biblioteka = (Biblioteka) in.readObject();
            in.close();
            Main.biblioteka.setKlienci(new ArrayList<>());
            for(Film f : Main.biblioteka.getFilmy()){
                f.setDanewyswietlenia(new ArrayList<>());
                f.setObejrzany(new ConcurrentHashMap<>());
                f.setData_kupione(new HashMap<>());
            }
            for(Serial s : Main.biblioteka.getSeriale()){
                s.setDanewyswietlenia(new ArrayList<>());
                s.setObejrzany(new ConcurrentHashMap<>());
                s.setData_kupione(new HashMap<>());
                for(Sezon se : s.getSezony()){
                    se.setKupiony(new HashMap<>());
                    for(Odcinek o : se.getOdcinki()){
                        o.setObejrzany(new ConcurrentHashMap<>());
                    }
                }
            }
            for(Wydarzenie w : Main.biblioteka.getWydarzenia()){
                w.setDanewyswietlenia(new ArrayList<>());
                w.setObejrzany(new ConcurrentHashMap<>());
                w.setData_kupione(new HashMap<>());
            }
            for(Abonament a : Main.biblioteka.getAbonamenty()){
                a.setCzaszaplacic(new HashMap<>());
            }
            laduj();
        } catch (ClassNotFoundException | IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stan = 0;
        seriale = FXCollections.observableArrayList();
        filmy = FXCollections.observableArrayList();
        wydarzenia = FXCollections.observableArrayList();
        columnFilm.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        columnSerial.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        columnWydarzenie.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        vid = Stream.of("vid1.mp4" , "vid2.mp4" , "vid3.mp4" , "vid4.mp4").collect(Collectors.toList());


    }
}