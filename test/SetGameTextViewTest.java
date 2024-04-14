import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cs3500.set.model.hw02.Card;
import cs3500.set.model.hw02.Coord;
import cs3500.set.model.hw02.SetThreeGameModel;
import cs3500.set.model.hw03.GeneralSetGameModel;
import cs3500.set.view.SetGameTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class contains a collection of tests for the SetGameTextView class.
 */
public class SetGameTextViewTest {
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
  Coord coord1;
  Coord coord2;
  Coord coord3;
  Coord coord4;
  Coord coord5;
  Coord coord6;
  SetThreeGameModel model;
  GeneralSetGameModel genModel;
  SetGameTextView view;
  SetGameTextView genView;

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

    coord1 = new Coord(0, 0);
    coord2 = new Coord(0, 2);
    coord3 = new Coord(1, 1);
    coord4 = new Coord(1, 0);
    coord5 = new Coord(2, 2);
    coord6 = new Coord(2, 0);
  }

  @Test
  public void testToString() {
    this.initConditions();
    // Test the SetThree game model's view
    model = new SetThreeGameModel();
    model.startGameWithDeck(deck, 3, 3);
    view = new SetGameTextView(model);
    assertTrue(this.model.isGameStarted);
    assertEquals("1EO 1SO 1SQ\n1EQ 1FD 2EO\n2SQ 2FO 2ED", this.view.toString());
    this.model.claimSet(coord1, coord2, coord3);
    assertEquals("2FQ 1SO 3EO\n1EQ 3SO 2EO\n2SQ 2FO 2ED", this.view.toString());
    this.model.claimSet(coord2, coord4, coord5);
    assertEquals("2FQ 1SO 3FD\n3ED 3SO 2EO\n2SQ 2FO 3SQ", this.view.toString());

    // Test the GeneralSet game model's view
    this.initConditions();
    genModel = new GeneralSetGameModel();
    genModel.startGameWithDeck(deck, 3, 2);
    genView = new SetGameTextView(genModel);
    assertEquals("1EO 1SO\n1SQ 1EQ\n1FD 2EO", genView.toString());
    genModel.claimSet(coord1, coord4, coord6);
    assertEquals("2SQ 1SO\n2FO 1EQ\n2ED 2EO", genView.toString());
    genModel.claimSet(coord1, coord4, coord6);
    assertEquals("2FQ 1SO\n3EO 1EQ\n3SO 2EO\n3FD 3ED", genView.toString());
  }

  @Test
  public void testConstructorException() {
    try { // Null model
      this.view = new SetGameTextView(null);
    } catch (IllegalArgumentException e) {
      assertEquals("Model cannot be null.", e.getMessage());
    }

    try { // Null destination
      SetThreeGameModel validModel = new SetThreeGameModel();
      this.view = new SetGameTextView(model, null);
    } catch (IllegalArgumentException e) {
      assertEquals("Model or destination cannot be null.", e.getMessage());
    }
  }

  @Test
  public void renderGrid() throws IOException {
    // Test SetThree game model's view
    this.initConditions();
    model = new SetThreeGameModel();
    model.startGameWithDeck(deck, 3, 3);
    Appendable output = new StringBuilder();
    this.view = new SetGameTextView(this.model, output);
    this.view.renderGrid();
    String[] expected = output.toString().split("\n");
    assertEquals("1EO 1SO 1SQ", expected[1]);
    assertEquals("1EQ 1FD 2EO", expected[2]);
    assertEquals("2SQ 2FO 2ED", expected[3]);

    this.model.claimSet(coord1, coord2, coord3);
    this.view.renderGrid();
    expected = output.toString().split("\n");
    assertEquals("2FQ 1SO 3EO", expected[5]);
    assertEquals("1EQ 3SO 2EO", expected[6]);
    assertEquals("2SQ 2FO 2ED", expected[7]);

    this.model.claimSet(coord2, coord4, coord5);
    this.view.renderGrid();
    expected = output.toString().split("\n");
    assertEquals("2FQ 1SO 3FD", expected[9]);
    assertEquals("3ED 3SO 2EO", expected[10]);
    assertEquals("2SQ 2FO 3SQ", expected[11]);

    // Test GeneralSet game model's view
    this.initConditions();
    genModel = new GeneralSetGameModel();
    genModel.startGameWithDeck(deck, 3, 2);
    output = new StringBuilder();
    genView = new SetGameTextView(genModel, output);
    genView.renderGrid();
    expected = output.toString().split("\n");
    assertEquals("1EO 1SO", expected[1]);
    assertEquals("1SQ 1EQ", expected[2]);
    assertEquals("1FD 2EO", expected[3]);

    genModel.claimSet(coord1, coord4, coord6);
    genView.renderGrid();
    expected = output.toString().split("\n");
    assertEquals("2SQ 1SO", expected[5]);
    assertEquals("2FO 1EQ", expected[6]);
    assertEquals("2ED 2EO", expected[7]);

    genModel.claimSet(coord1, coord4, coord6);
    genView.renderGrid();
    expected = output.toString().split("\n");
    assertEquals("2FQ 1SO", expected[9]);
    assertEquals("3EO 1EQ", expected[10]);
    assertEquals("3SO 2EO", expected[11]);
    assertEquals("3FD 3ED", expected[12]);
  }

  @Test
  public void renderMessage() throws IOException {
    this.initConditions();
    model = new SetThreeGameModel();
    model.startGameWithDeck(deck, 3, 3);
    Appendable output = new StringBuilder();
    this.view = new SetGameTextView(this.model, output);
    this.view.renderMessage("hello");
    assertEquals("hello", output.toString());
    this.view.renderMessage(" world");
    assertEquals("hello world", output.toString());
    this.view.renderMessage(" 123");
    assertEquals("hello world 123", output.toString());
  }
}