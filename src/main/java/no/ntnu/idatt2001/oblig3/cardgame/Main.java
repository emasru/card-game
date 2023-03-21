package no.ntnu.idatt2001.oblig3.cardgame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;


import java.util.ArrayList;
import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    /*
    Using javaFX, make a window. The window contains a window within that shows a hand of playing cards.
    On the right side there are two buttons: "Deal hand" and "Check hand".
    At the bottom: four display boxes showing text values, with a description inline of what the value represents
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Card Game");
        Button button = new Button();
        button.setText("Click me");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        HBox rootBox = new HBox();
        //TODO this shouldnt be stack pane because they will stack on each other
        //rootPane.getChildren().add(button);


        ArrayList<PlayingCard> hand;
        DeckOfCards deck = new DeckOfCards();
        hand = deck.dealHand(5);


        ArrayList<StackPane> cardImages = new ArrayList<>();
        for (PlayingCard card : hand) {
            cardImages.add(card.getCardImage());
        }

        rootBox.getChildren().addAll(cardImages);

        Scene scene = new Scene(rootBox, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}