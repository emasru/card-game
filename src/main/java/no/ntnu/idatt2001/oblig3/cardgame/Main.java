package no.ntnu.idatt2001.oblig3.cardgame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;


import java.util.ArrayList;
import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public static HBox createInfoField(String text){
        HBox textBox = new HBox();
        Text t = new Text(text);
        t.setFont(new Font(12));
        TextField textField = new TextField();
        textField.setDisable(true);
        textBox.getChildren().add(t);
        textBox.getChildren().add(textField);
        textBox.setPadding(new Insets(5));

        return textBox;
    }

    public static void showSumOfFaces(ArrayList<PlayingCard> hand, FlowPane pane){
        HBox sumBox = (HBox) pane.getChildren().get(0);
        TextField sumField = (TextField) sumBox.getChildren().get(1);

        int sum = 0;

        for (PlayingCard card : hand) {
            sum += card.getFace();
        }

        sumField.setText(String.valueOf(sum));
    }

    public static void showCardsOfHearts(ArrayList<PlayingCard> hand, FlowPane pane){
        HBox heartBox = (HBox) pane.getChildren().get(1);
        TextField heartField = (TextField) heartBox.getChildren().get(1);

        String hearts = "";

        for (PlayingCard card : hand) {
            if (card.getSuit() == 'H') {
                hearts = hearts.concat(card.getAsString() + " ");
            }
        }

        if (hearts.equals("")) {
            hearts = "No hearts";
        }

        heartField.setText(hearts);
    }

    public static void showFlush(ArrayList<PlayingCard> hand, FlowPane pane){
        HBox flushBox = (HBox) pane.getChildren().get(2);
        TextField flushField = (TextField) flushBox.getChildren().get(1);

        flushField.setText("Yes");

        // Get the first card's suit, and check if the rest of the cards have the same suit
        char startSuit = hand.get(0).getSuit();
        for (PlayingCard card : hand) {
            if (card.getSuit() != startSuit) {
                flushField.setText("No");
                return;
            }
        }
    }

    public static void showQueenOfSpades(ArrayList<PlayingCard> hand, FlowPane pane){
        HBox queenBox = (HBox) pane.getChildren().get(3);
        TextField queenField = (TextField) queenBox.getChildren().get(1);

        queenField.setText("No");
        for (PlayingCard card : hand) {
            if (card.getSuit() == 'S' && card.getFace() == 12) {
                queenField.setText("Yes");
                return;
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox cardBox = new HBox();
        VBox btnBox = new VBox();
        cardBox.setPadding(new Insets(5));
        btnBox.setPadding(new Insets(25));

        FlowPane rootPane = new FlowPane();
        rootPane.setPadding(new Insets(25));

        FlowPane infoPane = new FlowPane();
        infoPane.setPadding(new Insets(25));

        Button dealBtn = new Button();
        dealBtn.setText("Deal hand");

        Button checkBtn = new Button();
        checkBtn.setText("Check hand");

        infoPane.getChildren().addAll(createInfoField("Sum of the faces: "),
                createInfoField("Cards of hearts: "),
                createInfoField("Flush: "),
                createInfoField("Queen of spades: "));

        final ArrayList<PlayingCard> hand = new ArrayList<>();

        dealBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cardBox.getChildren().clear();
                hand.clear();

                DeckOfCards deck = new DeckOfCards();
                hand.addAll(deck.dealHand(5));


                ArrayList<StackPane> cardImages = new ArrayList<>();
                for (PlayingCard card : hand) {
                    cardImages.add(card.getCardImage());
                }

                cardBox.getChildren().addAll(cardImages);
            }
        });

        checkBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (hand.isEmpty()) {
                    return;
                }

                showSumOfFaces(hand, infoPane);
                showCardsOfHearts(hand, infoPane);
                showFlush(hand, infoPane);
                showQueenOfSpades(hand, infoPane);
            }
        });

        rootPane.getChildren().add(cardBox);

        btnBox.getChildren().add(dealBtn);
        btnBox.getChildren().add(checkBtn);
        rootPane.getChildren().add(btnBox);

        rootPane.getChildren().add(infoPane);

        Scene scene = new Scene(rootPane, 980, 620);
        primaryStage.setTitle("Card Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}