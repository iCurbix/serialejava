package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * The type Main.
 */
public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("netflix 2");
        primaryStage.setScene(new Scene(root, 900, 500));
        primaryStage.setResizable(false);

        primaryStage.setOnCloseRequest(event -> {
            matrix.stop();
            for (Klient k : biblioteka.getKlienci()) {
                k.stop();
            }
        });
        primaryStage.show();
    }

    /**
     * The Naddata.
     */
    static Date naddata = new Date();
    /**
     * The Biblioteka.
     */
    static Biblioteka biblioteka = new Biblioteka();
    /**
     * The Matrix.
     */
    static Symulacja matrix;
    static {
        try {
            matrix = new Symulacja();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets naddata.
     *
     * @return the naddata
     */
    public synchronized static Date getNaddata() {
        return naddata;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws ParseException the parse exception
     */
    public static void main(String[] args) throws ParseException {
        biblioteka.dodajUmowa();
        biblioteka.dodajPromocja();
        launch(args);
    }
}
