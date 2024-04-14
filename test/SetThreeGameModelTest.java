import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.set.model.hw02.Card;
import cs3500.set.model.hw02.Card.Count;
import cs3500.set.model.hw02.Card.Filling;
import cs3500.set.model.hw02.Card.Shape;
import cs3500.set.model.hw02.Coord;
import cs3500.set.model.hw02.SetThreeGameModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This class contains a collection of tests for the SetThreeGameModel class and its methods.
 */
public class SetThreeGameModelTest {
  Card card1;
  Card card2;
  Card card3;
  Card card4;
  Card card5;
  Card card6;
  Card card7;
  Card card8;
  Card card9;
  Card card10;
  Card card11;
  Card card12;
  Card card13;
  Card card14;
  Card card15;

  List<Card> deck = new ArrayList<>();

  SetThreeGameModel model;

  private void initConditions() {
    card1 = new Card(Count.One, Filling.Empty, Shape.Oval);
    card2 = new Card(Count.One, Filling.Striped, Shape.Oval);
    card3 = new Card(Count.One, Filling.Striped, Shape.Squiggle);
    card4 = new Card(Count.One, Filling.Empty, Shape.Squiggle);
    card5 = new Card(Count.One, Filling.Full, Shape.Diamond);
    card6 = new Card(Count.Two, Filling.Empty, Shape.Oval);
    card7 = new Card(Count.Two, Filling.Striped, Shape.Squiggle);
    card8 = new Card(Count.Two, Filling.Full, Shape.Oval);
    card9 = new Card(Count.Two, Filling.Empty, Shape.Diamond);
    card10 = new Card(Count.Two, Filling.Full, Shape.Squiggle);
    card11 = new Card(Count.Three, Filling.Empty, Shape.Oval);
    card12 = new Card(Count.Three, Filling.Striped, Shape.Oval);
    card13 = new Card(Count.Three, Filling.Full, Shape.Diamond);
    card14 = new Card(Count.Three, Filling.Empty, Shape.Diamond);
    card15 = new Card(Count.Three, Filling.Striped, Shape.Squiggle);

    deck.add(card1);
    deck.add(card2);
    deck.add(card3);
    deck.add(card4);
    deck.add(card5);
    deck.add(card6);
    deck.add(card7);
    deck.add(card8);
    deck.add(card9);
    deck.add(card10);
    deck.add(card11);
    deck.add(card12);
    deck.add(card13);
    deck.add(card14);
    deck.add(card15);

    model = new SetThreeGameModel();
  }

  @Test
  public void claimSet() {
    initConditions();
    Coord coord1 = new Coord(0, 0);
    Coord coord2 = new Coord(0, 2);
    Coord coord3 = new Coord(1, 1);
    Coord coord4 = new Coord(1, 0);
    Coord coord5 = new Coord(2, 2);
    Coord coord6 = new Coord(0, 1);
    try { // Game not started.
      model.claimSet(coord1, coord2, coord3);
    } catch (IllegalStateException e) {
      assertEquals("Game is not started.", e.getMessage());
    }

    model.startGameWithDeck(deck, 3, 3);
    try { // Out of bounds coordinates.
      Coord badCoord1 = new Coord(-1, 0);
      Coord badCoord2 = new Coord(0, 3);
      model.claimSet(coord1, badCoord2, badCoord1);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid coords for this grid.", e.getMessage());
    }

    try { // If the given coordinates does not form a set.
      model.claimSet(coord1, coord2, coord4);
    } catch (IllegalArgumentException e) {
      assertEquals("Not a set!", e.getMessage());
    }

    assertEquals(0, model.getScore());
    assertEquals(6, model.deck.size());
    model.claimSet(coord1, coord2, coord3);
    assertEquals(1, model.getScore());
    assertEquals(3, model.deck.size());
    assertEquals(card10, model.getCardAtCoord(coord1));
    assertEquals(card11, model.getCardAtCoord(coord2));
    assertEquals(card12, model.getCardAtCoord(coord3));

    model.claimSet(coord2, coord4, coord5);
    assertEquals(2, model.getScore());
    assertEquals(0, model.deck.size());
    assertEquals(card13, model.getCardAtCoord(coord2));
    assertEquals(card14, model.getCardAtCoord(coord4));
    assertEquals(card15, model.getCardAtCoord(coord5));

    model.claimSet(coord1, coord6, coord4);
    assertEquals(3, model.getScore());
  }

  @Test
  public void isValidCoord() {
    initConditions();
    model.startGameWithDeck(deck, 3, 3);
    Coord valid1 = new Coord(0, 0);
    Coord valid2 = new Coord(1, 0);
    Coord valid3 = new Coord(2, 2);
    Coord invalid1 = new Coord(-1, 0);
    Coord invalid2 = new Coord(0, 3);
    assertTrue(model.isValidCoord(valid1));
    assertTrue(model.isValidCoord(valid2));
    assertTrue(model.isValidCoord(valid3));
    assertFalse(model.isValidCoord(invalid1));
    assertFalse(model.isValidCoord(invalid2));
  }

  @Test
  public void startGameWithDeck() {
    initConditions();
    try { // given a null deck of cards.
      model.startGameWithDeck(null, 3, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("Deck of cards cannot be null.", e.getMessage());
    }

    try { // given dimensions that is not exactly 3x3.
      model.startGameWithDeck(deck, 1, 2);
    } catch (IllegalArgumentException e) {
      assertEquals("Grid must be 3x3 in dimension.", e.getMessage());
    }

    try { // given a deck of cards that contains less than 9 cards.
      List<Card> smallDeck = new ArrayList<>();
      smallDeck.add(card1);
      smallDeck.add(card2);
      smallDeck.add(card3);
      model.startGameWithDeck(smallDeck, 3, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("Must have at least 9 cards in deck.", e.getMessage());
    }

    assertFalse(model.isGameStarted);
    model.startGameWithDeck(deck, 3, 3);
    assertTrue(model.isGameStarted);
    assertEquals(3, model.getHeight());
    assertEquals(3, model.getWidth());
    assertEquals(0, model.getScore());
    assertEquals(card1, this.model.getCardAtCoord(0, 0));
    assertEquals(card2, this.model.getCardAtCoord(0, 1));
    assertEquals(card3, this.model.getCardAtCoord(0, 2));
    assertEquals(card4, this.model.getCardAtCoord(1, 0));
    assertEquals(card5, this.model.getCardAtCoord(1, 1));
    assertEquals(card6, this.model.getCardAtCoord(1, 2));
    assertEquals(card7, this.model.getCardAtCoord(2, 0));
    assertEquals(card8, this.model.getCardAtCoord(2, 1));
    assertEquals(card9, this.model.getCardAtCoord(2, 2));
  }

  @Test
  public void createGrid() {
    initConditions();
    model.startGameWithDeck(deck, 3, 3);
    assertEquals(6, model.deck.size());
    List<Card> cardsOnGrid = new ArrayList<>();
    cardsOnGrid.add(card1);
    cardsOnGrid.add(card2);
    cardsOnGrid.add(card3);
    cardsOnGrid.add(card4);
    cardsOnGrid.add(card5);
    cardsOnGrid.add(card6);
    cardsOnGrid.add(card7);
    cardsOnGrid.add(card8);
    cardsOnGrid.add(card9);
    int count = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(cardsOnGrid.get(count), model.grid[i][j]);
        count += 1;
      }
    }
  }

  @Test
  public void getWidth() {
    initConditions();
    try { // Game not started.
      model.getWidth();
    } catch (IllegalStateException e) {
      assertEquals("Game is not started.", e.getMessage());
    }

    model.startGameWithDeck(deck, 3, 3);
    assertEquals(3, model.getWidth());
  }

  @Test
  public void getHeight() {
    initConditions();
    try { // Game not started.
      model.getHeight();
    } catch (IllegalStateException e) {
      assertEquals("Game is not started.", e.getMessage());
    }

    model.startGameWithDeck(deck, 3, 3);
    assertEquals(3, model.getHeight());
  }

  @Test
  public void getScore() {
    initConditions();
    Coord coord1 = new Coord(0, 0);
    Coord coord2 = new Coord(0, 2);
    Coord coord3 = new Coord(1, 1);
    try { // Game not started.
      model.getScore();
    } catch (IllegalStateException e) {
      assertEquals("Game is not started.", e.getMessage());
    }

    model.startGameWithDeck(deck, 3, 3);
    assertEquals(0, model.getScore());
    model.claimSet(coord1, coord2, coord3);
    assertEquals(1, model.getScore());
  }

  @Test
  public void anySetsPresent() {
    initConditions();
    Coord coord1 = new Coord(0, 0);
    Coord coord2 = new Coord(0, 2);
    Coord coord3 = new Coord(1, 1);
    Coord coord4 = new Coord(1, 0);
    Coord coord5 = new Coord(2, 2);
    Coord coord6 = new Coord(0, 1);
    try {
      model.anySetsPresent();
    } catch (IllegalStateException e) {
      assertEquals("Game is not started.", e.getMessage());
    }

    model.startGameWithDeck(deck, 3, 3);
    assertTrue(model.anySetsPresent());
    model.claimSet(coord1, coord2, coord3);
    assertTrue(model.anySetsPresent());
    model.claimSet(coord2, coord4, coord5);
    assertTrue(model.anySetsPresent());
    model.claimSet(coord1, coord6, coord4);
    assertFalse(model.anySetsPresent());
  }

  @Test
  public void isValidSet() {
    initConditions();
    Coord coord1 = new Coord(0, 0);
    Coord coord2 = new Coord(0, 2);
    Coord coord3 = new Coord(1, 1);
    Coord coord4 = new Coord(1, 0);
    try { // Game not started.
      model.isValidSet(coord1, coord2, coord3);
    } catch (IllegalStateException e) {
      assertEquals("Game is not started.", e.getMessage());
    }

    model.startGameWithDeck(deck, 3, 3);
    try { // Out of bound coordinates.
      Coord badCoord1 = new Coord(-1, 0);
      Coord badCoord2 = new Coord(0, 3);
      model.isValidSet(coord1, badCoord1, badCoord2);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid coordinates.", e.getMessage());
    }

    assertFalse(model.isValidSet(coord1, coord2, coord4));
    assertTrue(model.isValidSet(coord1, coord2, coord3));
  }

  @Test
  public void isSameOrDiffAttri() {
    initConditions();
    model.startGameWithDeck(deck, 3, 3);
    assertTrue(model.isSameOrDiffAttri(card1, card3, card5));
    assertFalse(model.isSameOrDiffAttri(card1, card4, card5));
    assertTrue(model.isSameOrDiffAttri(card1, card7, card13));
    assertTrue(model.isSameOrDiffAttri(card1, card6, card11));
  }

  @Test
  public void getCardAtCoord() {
    initConditions();
    try { // Game not started.
      model.getCardAtCoord(0, 0);
    } catch (IllegalStateException e) {
      assertEquals("Game is not started.", e.getMessage());
    }

    model.startGameWithDeck(deck, 3, 3);
    try { // Negative row or col.
      model.getCardAtCoord(-1, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid position.", e.getMessage());
    }

    try { // Out of bound row or col.
      model.getCardAtCoord(0, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid position.", e.getMessage());
    }

    assertEquals(card1, model.getCardAtCoord(0, 0));
    assertEquals(card3, model.getCardAtCoord(0, 2));
    assertEquals(card5, model.getCardAtCoord(1, 1));
    assertEquals(card9, model.getCardAtCoord(2, 2));
  }

  @Test
  public void testGetCardAtCoord() {
    initConditions();
    Coord coord1 = new Coord(0, 0);
    Coord coord2 = new Coord(0, 1);
    Coord coord3 = new Coord(1, 0);
    Coord coord4 = new Coord(2, 1);
    try { // Game not started.
      model.getCardAtCoord(coord1);
    } catch (IllegalStateException e) {
      assertEquals("Game is not started.", e.getMessage());
    }

    model.startGameWithDeck(deck, 3, 3);
    try { // Negative coordinates.
      Coord badCoord = new Coord(-1, 0);
      model.getCardAtCoord(badCoord);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid position.", e.getMessage());
    }

    try { // Out of bound coordinates.
      Coord badCoord = new Coord(0, 3);
      model.getCardAtCoord(badCoord);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid position.", e.getMessage());
    }

    assertEquals(card1, model.getCardAtCoord(coord1));
    assertEquals(card2, model.getCardAtCoord(coord2));
    assertEquals(card4, model.getCardAtCoord(coord3));
    assertEquals(card8, model.getCardAtCoord(coord4));
  }

  @Test
  public void isGameOver() {
    initConditions();
    Coord coord1 = new Coord(0, 0);
    Coord coord2 = new Coord(0, 2);
    Coord coord3 = new Coord(1, 1);
    Coord coord4 = new Coord(1, 0);
    Coord coord5 = new Coord(2, 2);
    Coord coord6 = new Coord(0, 1);
    model.startGameWithDeck(deck, 3, 3);
    assertFalse(model.isGameOver());
    model.claimSet(coord1, coord2, coord3);
    assertFalse(model.isGameOver());
    model.claimSet(coord2, coord4, coord5);
    assertFalse(model.isGameOver());
    model.claimSet(coord1, coord6, coord4);
    assertTrue(model.isGameOver());
  }

  @Test
  public void getCompleteDeck() {
    initConditions();
    List<Card> fullDeck = new ArrayList<>();
    fullDeck = model.getCompleteDeck();
    assertEquals(27, fullDeck.size());
  }
}