package no.ntnu.idatt2001.oblig3.cardgame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Represents a deck of cards. In the constructor the deck is automatically shuffled.
 *
 * @author Henning Strandå
 * @version 2023-03-20
 */
public class DeckOfCards {
    private final ArrayList<PlayingCard> deck;
    private final char[] suit = { 'S', 'H', 'D', 'C' }; // 'S'=spade, 'H'=heart, 'D'=diamonds, 'C'=clubs

    /**
     * Creates a deck of cards. The cards are automatically shuffled and represented by "deck"
     */
    public DeckOfCards() {
        this.deck = new ArrayList<>();

        for (char s : suit) {
            for (int i = 1; i <= 13; i++) {
                deck.add(new PlayingCard(s, i));
            }
        }

        Collections.shuffle(deck);
    }

    /**
     * Deal a random hand consisting of n cards from the deck, without removing the cards from the deck.
     *
     * @param n The number of cards to deal
     * @return An ArrayList of PlayingCard objects
     */
    public ArrayList<PlayingCard> dealHand(int n){
        ArrayList<PlayingCard> hand = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            hand.add(deck.get(i));
        }

        //Shuffle the deck again
        Collections.shuffle(deck);

        return hand;
    }
}
