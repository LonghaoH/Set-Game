import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.set.model.hw02.Card;
import cs3500.set.model.hw02.Coord;
import cs3500.set.model.hw03.GeneralSetGameModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This class contains a collection of tests for the GeneralSetGameModel class.
 */
public class GeneralSetGameModelTest {
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

  List<Card> deck;

  GeneralSetGameModel model;

  Coord coord1;
  Coord coord2;
  Coord coord3;
  Coord coord4;
  Coord coord5;
  Coord coord6;

  private void initConditions() {
    card1 = new Card(Card.Count.One, Card.Filling.Empty, Card.Shape.Oval);
    card2 = new Card(Card.Count.One, Card.Filling.Striped, Card.Shape.Oval);
    card3 = new Card(Card.Count.One, Card.Filling.Striped, Card.Shape.Squiggle);
    card4 = new Card(Card.Count.One, Card.Filling.Empty, Card.Shape.Squiggle);
    card5 = new Card(Card.Count.One, Card.Filling.Full, Card.Shape.Diamond);
    card6 = new Card(Card.Count.Two, Card.Filling.Empty, Card.Shape.Oval);
    card7 = new Card(Card.Count.Two, Card.Filling.Striped, Card.Shape.Squiggle);
    card8 = new Card(Card.Count.Two, Card.Filling.Full, Card.Shape.Oval);
    card9 = new Card(Card.Count.Two, Card.Filling.Empty, Card.Shape.Diamond);
    card10 = new Card(Card.Count.Two, Card.Filling.Full, Card.Shape.Squiggle);
    card11 = new Card(Card.Count.Three, Card.Filling.Empty, Card.Shape.Oval);
    card12 = new Card(Card.Count.Three, Card.Filling.Striped, Card.Shape.Oval);
    card13 = new Card(Card.Count.Three, Card.Filling.Full, Card.Shape.Diamond);
    card14 = new Card(Card.Count.Three, Card.Filling.Empty, Card.Shape.Diamond);
    card15 = new Card(Card.Count.Three, Card.Filling.Striped, Card.Shape.Squiggle);

    deck = new ArrayList<>();
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

    model = new GeneralSetGameModel();

    coord1 = new Coord(0, 0);
    coord2 = new Coord(0, 2);
    coord3 = new Coord(1, 1);
    coord4 = new Coord(1, 0);
    coord5 = new Coord(2, 2);
    coord6 = new Coord(0, 1);
  }

  @Test
  public void claimSet() {
    this.initConditions();
    try { // Game not started
      model.claimSet(coord1, coord2, coord3);
    } catch (IllegalStateException e) {
      assertEquals("Game is not started.", e.getMessage());
    }

    model.startGameWithDeck(deck, 3, 3);
    // Try to claim from invalid first Coord
    try {
      Coord badCoord1 = new Coord(-2, 0);
      model.claimSet(badCoord1, coord2, coord3);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid coords for this grid.", e.getMessage());
    }

    // Try to claim from invalid second Coord
    try {
      Coord badCoord2 = new Coord(1, 6);
      model.claimSet(coord1, badCoord2, coord3);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid coords for this grid.", e.getMessage());
    }

    // Try to claim from invalid third Coord
    try {
      Coord badCoord3 = new Coord(-5, 5);
      model.claimSet(coord1, coord2, badCoord3);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid coords for this grid.", e.getMessage());
    }

    // Try to claim a set that is invalid
    try {
      model.claimSet(coord1, coord2, coord4);
    } catch (IllegalArgumentException e) {
      assertEquals("Not a set!", e.getMessage());
    }

    // Test playing claiming multiple sets
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

    // Test board change after claiming a set and there are no sets present
    this.initConditions();
    model.startGameWithDeck(deck, 3, 2);
    assertEquals(3, model.getHeight());
    assertEquals(2, model.getWidth());
    model.claimSet(coord1, coord4, new Coord(2, 0));
    assertEquals(1, model.getScore());
    assertEquals(3, model.getHeight());
    assertEquals(2, model.getWidth());
    model.claimSet(coord1, coord4, new Coord(2, 0));
    assertEquals(2, model.getScore());
    assertEquals(4, model.getHeight());
    assertEquals(2, model.getWidth());
  }

  @Test
  public void startGameWithDeck() {
    this.initConditions();
    // Test non-positive height
    try {
      model.startGameWithDeck(deck, -1, 4);
    } catch (IllegalArgumentException e) {
      assertEquals("Grid must be positive dimensions.", e.getMessage());
    }

    // Test non-positive width
    try {
      model.startGameWithDeck(deck, 3, -3);
    } catch (IllegalArgumentException e) {
      assertEquals("Grid must be positive dimensions.", e.getMessage());
    }

    // Test creating grid that holds less than 3 cards
    try {
      model.startGameWithDeck(deck, 1, 2);
    } catch (IllegalArgumentException e) {
      assertEquals("Grid must hold at least 3 cards.", e.getMessage());
    }

    // Test creating grid with a null deck of cards
    try {
      model.startGameWithDeck(null, 3, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("Deck of cards cannot be null.", e.getMessage());
    }

    // Test creating a grid with insufficient amount of cards in deck
    try {
      model.startGameWithDeck(deck, 4, 4);
    } catch (IllegalArgumentException e) {
      assertEquals("Must have enough cards in deck to fill grid.", e.getMessage());
    }

    // Test creating a valid grid
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

    // Test creating a valid grid but there is no valid set
    this.initConditions();
    assertFalse(model.isGameStarted);
    model.startGameWithDeck(deck, 2, 2);
    assertTrue(model.isGameStarted);
    assertEquals(3, model.getHeight());
    assertEquals(2, model.getWidth());
    assertEquals(0, model.getScore());
    assertEquals(card1, this.model.getCardAtCoord(0, 0));
    assertEquals(card2, this.model.getCardAtCoord(0, 1));
    assertEquals(card3, this.model.getCardAtCoord(1, 0));
    assertEquals(card4, this.model.getCardAtCoord(1, 1));
    assertEquals(card5, this.model.getCardAtCoord(2, 0));
    assertEquals(card6, this.model.getCardAtCoord(2, 1));
  }

  @Test
  public void getWidth() {
    this.initConditions();
    try { // Game not started.
      model.getWidth();
    } catch (IllegalStateException e) {
      assertEquals("Game is not started.", e.getMessage());
    }
    model.startGameWithDeck(deck, 2, 4);
    assertEquals(4, this.model.getWidth());
  }

  @Test
  public void getHeight() {
    this.initConditions();
    try { // Game not started.
      model.getHeight();
    } catch (IllegalStateException e) {
      assertEquals("Game is not started.", e.getMessage());
    }
    model.startGameWithDeck(deck, 3, 3);
    assertEquals(3, this.model.getHeight());
  }

  @Test
  public void getScore() {
    this.initConditions();
    try {
      model.getScore();
    } catch (IllegalStateException e) {
      assertEquals("Game is not started.", e.getMessage());
    }
    model.startGameWithDeck(deck, 3, 3);
    assertEquals(0, model.getScore());
    model.claimSet(new Coord(0, 0), new Coord(0, 2), new Coord(1, 1));
    assertEquals(1, model.getScore());
  }

  @Test
  public void anySetsPresent() {
    initConditions();
    try { // Game not started.
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
    this.initConditions();
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
  public void getCardAtCoord() { // Test the method that accepts row, col inputs
    initConditions();
    try { // Game not started.
      model.getCardAtCoord(1, 1);
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

    assertEquals(card1, model.getCardAtCoord(0, 0));
    assertEquals(card3, model.getCardAtCoord(0, 2));
    assertEquals(card5, model.getCardAtCoord(1, 1));
    assertEquals(card4, model.getCardAtCoord(1, 0));
  }

  @Test
  public void testGetCardAtCoord() { // Test the method that accepts Coord inputs.
    initConditions();
    try { // Game not started.
      model.getCardAtCoord(coord1);
    } catch (IllegalStateException e) {
      assertEquals("Game is not started.", e.getMessage());
    }

    model.startGameWithDeck(deck, 2, 3);
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
    assertEquals(card3, model.getCardAtCoord(coord2));
    assertEquals(card5, model.getCardAtCoord(coord3));
    assertEquals(card4, model.getCardAtCoord(coord4));
  }

  @Test
  public void isGameOver() {
    this.initConditions();
    // Test running out of cards after claiming a few sets.
    model.startGameWithDeck(deck, 3, 3);
    assertFalse(model.isGameOver());
    model.claimSet(coord1, coord2, coord3);
    assertFalse(model.isGameOver());
    model.claimSet(coord2, coord4, coord5);
    assertFalse(model.isGameOver());
    model.claimSet(coord1, coord6, coord4);
    assertTrue(model.isGameOver());

    // Test not having enough cards in deck when no set is present at creating board.
    ArrayList<Card> deck1 = new ArrayList<>();
    deck1.add(card1);
    deck1.add(card2);
    deck1.add(card3);
    deck1.add(card6);
    deck1.add(card7);
    model.startGameWithDeck(deck1, 2, 2);
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