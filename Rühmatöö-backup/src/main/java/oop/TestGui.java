package oop;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TestGui extends Application {
    Label label;
    Button nuppAlusta;
    Button nuppLõpeta;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        Group juur = new Group();

        label = new Label("Tikumäng");
        label.setFont(Font.font("Arial", 30));

        nuppAlusta = new Button("Alusta");
        nuppAlusta.setLayoutX(10);
        nuppAlusta.setLayoutY(50);

        nuppLõpeta = new Button("Lõpeta");
        nuppLõpeta.setLayoutX(10);
        nuppLõpeta.setLayoutY(80);
        nuppLõpeta.setOnAction((ActionEvent e) -> {
            System.exit(0);
        });
        juur.getChildren().addAll(label, nuppAlusta, nuppLõpeta);
        Scene stseen = new Scene(juur,500,150,Color.SNOW);
        primaryStage.setTitle("Tikumäng");
        primaryStage.setScene(stseen);
        primaryStage.show();
    }
}
