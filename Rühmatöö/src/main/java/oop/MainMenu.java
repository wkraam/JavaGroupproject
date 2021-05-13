package oop;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

public class MainMenu extends Application {
    //menu stseeni nupud
    VBox menuVbox;

    VBox gameLabels;
    HBox käiguNäitaja;
    HBox tikkudeNäitja;
    HBox tikkudeValik;

    ComboBox tikkudeArvComboBox;

    Label label;
    Label alustab;
    Label mängija;
    Label allesTikkelbl;
    Label allesTikke;
    Label võitja;
    Label tikkudeError;

    TextField nimiTF;
    TextField nimiTeineTF;

    Button bttnAlusta;
    Button bttnLõpeta;
    Button bttnAlustaRaske;
    Button bttnAlustaTeiseVastu;
    Button bttnValiTikud;

    Mängija mangija1;
    Mängija mangija2;
    Mängija current_player;

    // nupp, mis viib tagasi menüü screenile
    Button bttnBack;

    int tikkude_arv;
    int valitudTikud;

    public void setTikkude_arv(int uusArv){
        tikkude_arv = uusArv;

    }

    public void algus(){

        tikkude_arv = 5 + (int) (Math.random() * 20);
        allesTikke.setText(String.valueOf(tikkude_arv));
        System.out.println(tikkude_arv);

        int a = (int) (Math.random() * 1000);//suvaline arv, et vaadata kumb alustab
        if (a % 2 == 0) {

            mängija.setText(mangija1.getNimi());//GUI osa, mis väljastab, et esimene mängija alustab
            current_player = mangija1;
        } else {

            mängija.setText(mangija2.getNimi());//GUI osa, mis väljastab, et teine mängija alustab
            current_player = mangija2;
            tikkude_arv = käigud(tikkude_arv, mangija2, mangija1);
        }
    }

    public int game() throws InterruptedException {
        // esimese mängija käik
        if (current_player == mangija1){
            mängija.setText(mangija1.getNimi());
            wait(10);
        }

        // teise mängija käik
        // kui on mängijaks computer
        if (mangija2 instanceof MängijaComputer && current_player == mangija2) {
            mängija.setText(mangija2.getNimi());
            wait(10);
            if (tikkude_arv > 0) tikkude_arv = käigud(tikkude_arv, mangija2, mangija1);
            current_player = mangija1;
        }
        // kui mängijaks on player
        else if (current_player == mangija2){
            mängija.setText(mangija2.getNimi());
            wait(10);
            if (tikkude_arv > 0){
                tikkude_arv = käigud(tikkude_arv, mangija2, mangija1);
                current_player = mangija1;
            }
        }
    return tikkude_arv;
    }//käigud, kuni kumbki võidab


    public int käigud(int tikkude_arv, Mängija m1, Mängija m2) {  // meetod, kus tehakse mängija käik
        tikkude_arv -= m1.käik(tikkude_arv); // kutsub välja vastava Mängija klassis oleva funktsiooni käik ning lahutab vastava tikkude arvu kogu tikkude hulgast.
        allesTikke.setText(String.valueOf(tikkude_arv)); // GUI osa, kus väljastatakse kasutajale, palju on tikke alles

        if (tikkude_arv == 0) {
            tikkudeArvComboBox.setDisable(true);
            bttnValiTikud.setDisable(true);
            allesTikke.setText("");
            allesTikkelbl.setText("Võitja: ");
            võitja.setText(m2.getNimi()); // GUI osa, kus näidatakse, et m2 võitis
        }//väljastab võitja
        return tikkude_arv;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        Group menuGroup = new Group();
        Group mängGroup = new Group();

        Scene menu = new Scene(menuGroup, 170, 230, Color.SNOW);
        Scene mäng = new Scene(mängGroup, 300, 300, Color.WHITE);


        /** Vbox/HBox **/
        {
            menuVbox = new VBox();
            menuVbox.setSpacing(6);
            menuVbox.setLayoutX(10);

            käiguNäitaja = new HBox();

            gameLabels = new VBox();
            gameLabels.setSpacing(6);
            gameLabels.setLayoutX(10);

            tikkudeNäitja = new HBox();
            tikkudeNäitja.setSpacing(6);

            tikkudeValik = new HBox();
        }
        /** Labels **/
        {
            label = new Label("Tikumäng");
            label.setFont(Font.font("Arial", 30));

            alustab = new Label("Praegu käib: ");
            alustab.setFont(Font.font("Arial", 24));

            mängija = new Label("");
            mängija.setFont(Font.font("Arial", 24));

            allesTikkelbl = new Label("Tikke on alles: ");
            allesTikkelbl.setFont(Font.font("Arial", 18));

            allesTikke = new Label("");
            allesTikke.setFont(Font.font("Arial", 18));

            võitja = new Label("");
            võitja.setFont(Font.font("Arial", 18));
            võitja.setTextFill(Color.GOLD);

            tikkudeError = new Label("");
            tikkudeError.setFont(Font.font("Arial", 18));
            tikkudeError.setTextFill(Color.RED);
        }
        /** Text fields / combo box **/
        {
            nimiTF = new TextField("Esimese mängija nimi");
            nimiTF.setLayoutX(10);
            nimiTF.setPrefWidth(150);

            nimiTeineTF = new TextField("Teise mängija nimi");
            nimiTeineTF.setLayoutX(10);
            nimiTeineTF.setPrefWidth(150);

            ObservableList<String> options =
                    FXCollections.observableArrayList(
                            "1 tikk",
                            "2 tikku",
                            "3 tikku"
                    );
            tikkudeArvComboBox = new ComboBox(options);
            tikkudeArvComboBox.setOnAction((Event e) ->{
                String valik = tikkudeArvComboBox.getValue()+"";
                if (valik.equals("1 tikk")) valitudTikud = 1;
                if (valik.equals("2 tikku")) valitudTikud = 2;
                if (valik.equals("3 tikku")) valitudTikud = 3;
            });
        }
        /** Buttons **/
        {
            //Alusta lihtsa arvuti vastu
            bttnAlusta = new Button("Alusta lihtsa arvuti vastu");
            bttnAlusta.setPrefWidth(150);
            bttnAlusta.setOnAction((ActionEvent e) -> {
                System.out.println("Tikumäng peaks tööle minema");
                mangija1 = new Mängija(nimiTF.getText());
                mangija2 = new MängijaComputer();
                primaryStage.setScene(mäng);
                algus(); // otsustatakse, kes alustab
            });

            //Alusta raske arvuti vastu
            bttnAlustaRaske = new Button("Alusta raske arvuti vastu");
            bttnAlustaRaske.setPrefWidth(150);
            bttnAlustaRaske.setOnAction((ActionEvent e) -> {
                primaryStage.setScene(mäng);
                mangija1 = new Mängija(nimiTF.getText());
                mangija2 = new MängijaComputerHard();

            });

            //Alusta kaksikmängu
            bttnAlustaTeiseVastu = new Button("Alusta teise mängija vastu");
            bttnAlustaTeiseVastu.setPrefWidth(150);
            bttnAlustaTeiseVastu.setOnAction((ActionEvent e)->{
                primaryStage.setScene(mäng);
                mangija1 = new Mängija(nimiTF.getText());
                mangija2 = new Mängija(nimiTeineTF.getText());

            });

            bttnBack = new Button("Back");
            bttnBack.setLayoutY(265);
            bttnBack.setLayoutX(10);
            bttnBack.setPrefWidth(50);
            bttnBack.setOnAction((ActionEvent e) -> {
                primaryStage.setScene(menu);
            });

            bttnLõpeta = new Button("Lõpeta");
            bttnLõpeta.setPrefWidth(150);
            bttnLõpeta.setOnAction((ActionEvent e) -> {
                System.out.println("Exiting");
                System.exit(0);
            });

            bttnValiTikud = new Button("vali");
            bttnValiTikud.prefWidth(50);
            bttnValiTikud.setOnAction(new EventHandler<ActionEvent>() {
                //valitudTikud = tikkudeArvComboBox.getValue()+"";
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Valik"+valitudTikud);
                    setTikkude_arv(käigud(tikkude_arv, mangija1, mangija2));
                }
            });
        }

        //game stuff
        käiguNäitaja.getChildren().addAll(alustab, mängija);
        tikkudeNäitja.getChildren().addAll(allesTikkelbl, allesTikke, võitja);
        tikkudeValik.getChildren().addAll(tikkudeArvComboBox, bttnValiTikud, tikkudeError);
        gameLabels.getChildren().addAll(käiguNäitaja, tikkudeNäitja, tikkudeValik);
        mängGroup.getChildren().addAll(gameLabels, bttnBack);

        //menu stuff
        menuVbox.getChildren().addAll(label, nimiTF, bttnAlusta, bttnAlustaRaske, nimiTeineTF, bttnAlustaTeiseVastu, bttnLõpeta);
        menuGroup.getChildren().addAll(menuVbox);

        primaryStage.setTitle("Tikumäng");
        primaryStage.setScene(menu);
        primaryStage.show();
    }
}
