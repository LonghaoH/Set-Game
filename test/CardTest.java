import org.junit.Test;

import cs3500.set.model.hw02.Card;
import cs3500.set.model.hw02.Card.Count;
import cs3500.set.model.hw02.Card.Filling;
import cs3500.set.model.hw02.Card.Shape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This class contains tests for the methods and constructor in the Card class.
 */
public class CardTest {
  Card card1;
  Card card2;
  Card card3;
  Card invalidCard;

  private void initConditions() {
    card1 = new Card(Count.One, Filling.Empty, Shape.Oval);
    card2 = new Card(Count.Two, Filling.Striped, Shape.Squiggle);
    card3 = new Card(Count.Three, Filling.Full, Shape.Diamond);
  }

  @Test
  public void testConstructorExceptions() {
    try { // All null values
      invalidCard = new Card(null, null, null);
    } catch (IllegalArgumentException e) {
      assertEquals("Values cannot be null.", e.getMessage());
    }

    try { // One null value
      invalidCard = new Card(Count.One, Filling.Full, null);
    } catch (IllegalArgumentException e) {
      assertEquals("Values cannot be null.", e.getMessage());
    }
  }

  @Test
  public void isValidEnum() {
    initConditions();
    assertTrue(this.card1.isValidEnum(Count.One));
    assertTrue(this.card1.isValidEnum(Filling.Empty));
    assertTrue(this.card1.isValidEnum(Shape.Oval));
    assertTrue(this.card2.isValidEnum(Count.Two));
    assertTrue(this.card2.isValidEnum(Filling.Striped));
    assertTrue(this.card2.isValidEnum(Shape.Squiggle));
    assertTrue(this.card3.isValidEnum(Count.Three));
    assertTrue(this.card3.isValidEnum(Filling.Full));
    assertTrue(this.card3.isValidEnum(Shape.Diamond));

    assertFalse(this.card1.isValidEnum("One"));
    assertFalse(this.card1.isValidEnum("Empty"));
    assertFalse(this.card1.isValidEnum("Oval"));
    assertFalse(this.card1.isValidEnum(1));
  }

  @Test
  public void getCountValues() {
    initConditions();
    assertEquals("1", this.card1.getCountValues());
    assertEquals("2", this.card2.getCountValues());
    assertEquals("3", this.card3.getCountValues());
  }

  @Test
  public void getFillingValues() {
    initConditions();
    assertEquals("E", this.card1.getFillingValues());
    assertEquals("S", this.card2.getFillingValues());
    assertEquals("F", this.card3.getFillingValues());
  }

  @Test
  public void getShapeValues() {
    initConditions();
    assertEquals("O", this.card1.getShapeValues());
    assertEquals("Q", this.card2.getShapeValues());
    assertEquals("D", this.card3.getShapeValues());
  }

  @Test
  public void testToString() {
    initConditions();
    assertEquals("1EO", this.card1.toString());
    assertEquals("2SQ", this.card2.toString());
    assertEquals("3FD", this.card3.toString());
  }

  @Test
  public void testEquals() {
    initConditions();
    Card identicalCard = new Card(Count.One, Filling.Empty, Shape.Oval);
    assertEquals(identicalCard, this.card1);
    assertTrue(identicalCard.equals(this.card1));
    assertFalse(identicalCard.equals(this.card2));
  }
}