package oop;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainMenu extends Application {
    //menu stseeni nupud
    HBox tikkudeNäitja = new HBox();
    HBox tikkudeValik;

    Label sissejuhatus;
    Label nimiLabel;
    Label valik;
    Label alustab;
    Label mängija;
    Label allesTikkelbl;
    Label võitja;
    Label tikkudeError;


    TextField nimiTF;
    TextField nimiTeineTF;

    Hyperlink bttnAlusta;
    Hyperlink bttnLõpeta;
    Hyperlink bttnAlustaRaske;
    Hyperlink bttnAlustaTeiseVastu;
    Hyperlink bttnAlustaKerge;
    Hyperlink bttnEdasi;

    Mängija mangija1;
    Mängija mangija2;
    Mängija Player;

    // nupp, mis viib tagasi menüü screenile
    Button bttnBack;
    Button käiguLõpp;

    int tikkude_arv = 0;

    boolean inimene;
    boolean tarkarvuti;

    Font font = new Font("Bauhaus 93", 15);

    public Mängija algus(){
        tikkude_arv = 5 + (int) (Math.random() * 20);// tikkude arv, genereeritakse suvaliselt vahemikust 5-25
        System.out.println(tikkude_arv);

        int a = (int) (Math.random() * 1000);//suvaline arv, et vaadata kumb alustab
        if (a % 2 == 0) {
            alustab.setText("Tikkude arv on " + tikkude_arv + "\n\n Alustab " + mangija1.getNimi());
            return mangija1;//kui paariarv, siis alustab mängija 1
        } else {
            alustab.setText("Tikkude arv on " + tikkude_arv + "\n\n Alustab " + mangija2.getNimi());
            return mangija2;// kui paaritu, siis alustab mängija 2
        }
    }

    public Mängija käigud(Mängija m1, Mängija m2) {  // meetod, kus tehakse mängija käik
        int tikke;
        if(m1 instanceof MängijaComputer){
            try {
                wait(10);
            } catch (InterruptedException e) {
                System.out.println("Midagi on siin lappes.");
            }
            tikke = m1.käik(tikkude_arv);
            tikkude_arv -= tikke;
            tikkudeNäitja.getChildren().subList(0, tikke).clear();
            allesTikkelbl.setText("TIKKE ON ALLES " + tikkude_arv);
        }else{
            System.out.println(tikkude_arv);
            int tikkeKäiguAlguses=tikkude_arv;
            tikkudeNäitja.setOnMouseClicked((MouseEvent e) -> {
                tikkude_arv--;
                tikkudeNäitja.getChildren().remove(0);
                allesTikkelbl.setText("TIKKE ON ALLES " + tikkude_arv);
            });
            /**while(tikkeKäiguAlguses-tikkude_arv<=3){
            } see jääb kas siia kinni*/
            tikkudeNäitja.removeEventHandler(MouseEvent.MOUSE_CLICKED, tikkudeNäitja.getOnMouseClicked());
        }
        if (tikkude_arv == 0) {
            System.out.println("läbi");
            return m2;
        }//tagastab võitja
        mängija.setText(m2.getNimi() + " KORD");
        return käigud(m2, m1);
    }

    //pildid tikkudest
    public void tikud(int tikkudeArv){
        Image tikk = null;
        try {
            tikk = new Image(new FileInputStream("tikk.jpg"));
        } catch (FileNotFoundException e) {
            System.out.println("Faili ei leitud");
        }
        for (int j = 0; j < tikkudeArv; j++) {
            ImageView iwTikk = new ImageView(tikk);
            iwTikk.setFitHeight(300);
            iwTikk.setFitWidth(30);
            iwTikk.setPreserveRatio(true);
            tikkudeNäitja.getChildren().add(iwTikk);
        }
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        Group firstPageGroup = new Group();
        Group menuGroup = new Group();
        Group mängGroup = new Group();

        Scene firstPage = new Scene(firstPageGroup, 500, 450);
        Scene menu = new Scene(menuGroup, 500, 450);
        Scene mäng = new Scene(mängGroup, 500, 450);

        /** Images */
        {   //stseen 1 - esileht - pilt
            Image i = new Image(new FileInputStream("11230723.jpg"));
            ImageView iw = new ImageView(i);
            iw.setX(0);
            iw.setY(0);
            iw.setFitHeight(455);
            iw.setFitWidth(500);
            iw.setPreserveRatio(true);
            firstPageGroup.getChildren().add(iw);

            //stseen 2 - menu - pilt
            Image i2v = new Image(new FileInputStream("polev.jpg"));
            ImageView iw2v = new ImageView(i2v);
            iw2v.setX(30);
            iw2v.setY(30);
            iw2v.setFitHeight(380);
            iw2v.setFitWidth(250);
            iw2v.setPreserveRatio(true);
            menuGroup.getChildren().add(iw2v);
            Image i2p = new Image(new FileInputStream("polev2.jpg"));
            ImageView iw2p = new ImageView(i2p);
            iw2p.setX(280);
            iw2p.setY(30);
            iw2p.setFitHeight(380);
            iw2p.setFitWidth(250);
            iw2p.setPreserveRatio(true);
            menuGroup.getChildren().add(iw2p);

        }
        /** Vbox/HBox**/
        {   tikkudeNäitja = new HBox();
            tikkudeNäitja.setLayoutX(100);
            tikkudeNäitja.setLayoutY(100);
        }
        /**Labels **/
        {   //sissejuhatav teks avalehel
            sissejuhatus = new Label("Tikumäng -\n sissejuhatav tekst, mida peaks \nenne mängimist teadma, nt, et mängija saab \n võtta 1,2 või 3 tikku.");
            sissejuhatus.setFont(font);
            sissejuhatus.setLayoutX(130);
            sissejuhatus.setLayoutY(30);

            //silt, valikute steenil
            valik = new Label("Vali kummaga soovid mängida: ");
            valik.setFont(font);
            valik.setLayoutX(140);
            valik.setLayoutY(40);

            //silt, küsib nime
            nimiLabel = new Label("Mängija nimi:");
            nimiLabel.setFont(font);
            nimiLabel.setLayoutY(60);
            nimiLabel.setLayoutX(180);

            //näitab tikkude arv ja alustavat mängijat enne mängu algust
            alustab = new Label();
            alustab.setFont(font);
            alustab.setLayoutY(100);
            alustab.setLayoutX(180);
            alustab.setOnMouseEntered((MouseEvent e) -> { //edasise peaks kuskile mujale tõstma
                FadeTransition fade = new FadeTransition(Duration.seconds(2));
                fade.setNode(alustab);
                fade.setFromValue(1.0);
                fade.setToValue(0.0);
                fade.setAutoReverse(false);
                fade.play();
                allesTikkelbl.setText("TIKKE ON ALLES: " + tikkude_arv);//näitab tikkude arvu
                mängija.setText(Player.getNimi() + " KORD");//näitab kumma kord on
                tikud(tikkude_arv);
                System.out.println(Player.getNimi());
                mängGroup.getChildren().addAll(allesTikkelbl, mängija, tikkudeNäitja);
                mängGroup.getChildren().removeAll(alustab);
                if(Player == mangija1){
                    Player = käigud(mangija1, mangija2);
                }else{
                    Player = käigud(mangija2,mangija1);
                }
                System.out.println(Player.getNimi());
            });

            //label, näitab, kelle kord on
            mängija = new Label();
            mängija.setFont(font);
            mängija.setLayoutY(50);
            mängija.setLayoutX(180);

            //alles tikkude näitamise label
            allesTikkelbl = new Label();
            allesTikkelbl.setFont(font);
            allesTikkelbl.setLayoutY(30);
            allesTikkelbl.setLayoutX(180);

            võitja = new Label("");
            võitja.setFont(Font.font("Arial", 18));
            võitja.setTextFill(Color.GOLD);

            tikkudeError = new Label("");
            tikkudeError.setFont(Font.font("Arial", 18));
            tikkudeError.setTextFill(Color.RED);
        }
        /** Text fields / combo box **/
        {   //esimese mängija nimi
            nimiTF = new TextField("");
            nimiTF.setFont(font);
            nimiTF.setLayoutX(160);
            nimiTF.setLayoutY(90);
            nimiTF.setBackground(Background.EMPTY);

            //teise mängija nimi
            nimiTeineTF = new TextField("");
            nimiTeineTF.setFont(font);
            nimiTeineTF.setLayoutX(160);
            nimiTeineTF.setLayoutY(90);
            nimiTeineTF.setBackground(Background.EMPTY);
        }
        /** Buttons **/
        {   //AVALEHT
            //nupp avalehel
            Hyperlink nupp1 = new Hyperlink("Mängi!"); // luuakse nupp
            nupp1.setFont(new Font("Bauhaus 93", 30));//lambi font, võiks ära muuta
            nupp1.setLayoutX(320); // nupu paigutamine x-koordinaadi mõttes
            nupp1.setLayoutY(150); // nupu paigutamine y-koordinaadi mõttes
            nupp1.setRotate(-33);//nupu kalle
            nupp1.setBorder(Border.EMPTY);//et hyperlingil poleks piiri
            firstPageGroup.getChildren().add(nupp1); // nupp lisatakse juure alluvaks
            nupp1.setOnMouseClicked(e -> primaryStage.setScene(menu));

            //MENU LEHT
            //Alusta arvuti vastu
            bttnAlusta = new Hyperlink("ARVUTI");
            bttnAlusta.setFont(font);
            bttnAlusta.setLayoutX(90);
            bttnAlusta.setLayoutY(140);
            bttnAlusta.setBorder(Border.EMPTY);//et hyperlingil poleks piiri
            bttnAlusta.setOnAction((ActionEvent e) -> {
                inimene = false;
                menuGroup.getChildren().removeAll(bttnAlusta, bttnAlustaTeiseVastu);
                menuGroup.getChildren().addAll(bttnAlustaRaske, bttnAlustaKerge);
            });

            //Alusta raske arvuti vastu
            bttnAlustaRaske = new Hyperlink("RASKE");
            bttnAlustaRaske.setFont(font);
            bttnAlustaRaske.setLayoutX(90);
            bttnAlustaRaske.setLayoutY(140);
            bttnAlustaRaske.setBorder(Border.EMPTY);//et hyperlingil poleks piiri
            bttnAlustaRaske.setOnAction((ActionEvent e) -> {
                tarkarvuti = true;
                mangija2 = new MängijaComputerHard();
                Player = algus();
                primaryStage.setScene(mäng);
            });

            //Mängitakse kergema arvuti vastu
            bttnAlustaKerge = new Hyperlink("KERGE");
            bttnAlustaKerge.setFont(font);
            bttnAlustaKerge.setLayoutX(320);
            bttnAlustaKerge.setLayoutY(140);
            bttnAlustaKerge.setBorder(Border.EMPTY);//et hyperlingil poleks piiri
            bttnAlustaKerge.setOnAction((ActionEvent e)->{
                tarkarvuti = false;
                mangija2 = new MängijaComputerHard();
                Player = algus();
                primaryStage.setScene(mäng);
            });

            //Alusta kaksikmängu
            bttnAlustaTeiseVastu = new Hyperlink("INIMENE");
            bttnAlustaTeiseVastu.setFont(font);
            bttnAlustaTeiseVastu.setLayoutX(320);
            bttnAlustaTeiseVastu.setLayoutY(140);
            bttnAlustaTeiseVastu.setBorder(Border.EMPTY);//et hyperlingil poleks piiri
            bttnAlustaTeiseVastu.setOnAction((ActionEvent e)->{
                inimene = true;
                menuGroup.getChildren().removeAll(bttnAlusta, bttnAlustaTeiseVastu, valik);
                nimiLabel.setText("2. mängija nimi:");
                menuGroup.getChildren().addAll(nimiTeineTF, nimiLabel, bttnEdasi);
            });

            //nimede küsimisel edasi liikumiseks
            bttnEdasi = new Hyperlink("EDASI");
            bttnEdasi.setFont(font);
            bttnEdasi.setLayoutX(180);
            bttnEdasi.setLayoutY(140);
            bttnEdasi.setBorder(Border.EMPTY);//et hyperlingil poleks piiri
            bttnEdasi.setOnAction((ActionEvent e)->{
                if(inimene) {
                    mangija2 = new Mängija(nimiTeineTF.getText());
                    System.out.println(nimiTF.getText());
                    System.out.println(nimiTeineTF.getText());
                    Player = algus();
                    primaryStage.setScene(mäng);
                }else {
                    mangija1 = new Mängija(nimiTF.getText());
                    menuGroup.getChildren().removeAll(nimiTF, nimiLabel, bttnEdasi);
                    menuGroup.getChildren().addAll(valik, bttnAlusta, bttnAlustaTeiseVastu);
                }
            });

            //MÄNGU LEHT
            bttnBack = new Button("Back");
            bttnBack.setLayoutY(265);
            bttnBack.setLayoutX(10);
            bttnBack.setPrefWidth(50);
            bttnBack.setOnAction((ActionEvent e) -> primaryStage.setScene(firstPage));

            bttnLõpeta = new Hyperlink("Lõpeta");
            bttnLõpeta.setPrefWidth(150);
            bttnLõpeta.setOnAction((ActionEvent e) -> {
                System.out.println("Exiting");
                System.exit(0);
            });
        }

        //first buttons/labels/etc for every stage
        firstPageGroup.getChildren().add(sissejuhatus);
        menuGroup.getChildren().addAll(nimiLabel, nimiTF, bttnEdasi);
        mängGroup.getChildren().add(alustab);

        primaryStage.setTitle("Tikumäng");
        primaryStage.setScene(firstPage);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    //main
    public static void main(String[] args) {
        launch(args);
    }
}
