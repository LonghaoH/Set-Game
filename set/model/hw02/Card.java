package cs3500.set.model.hw02;

import java.util.Objects;

/**
 * This class represents cards being used for the SetThree game.
 * A card has 3 unique features: Count, Filling, and Shape.
 */
public class Card {
  private final Count count;
  private final Filling filling;
  private final Shape shape;

  /**
   * This enum represents the Count value of a card.
   * Count values can be one of: One, Two, or Three.
   */
  public enum Count {
    One("One"),
    Two("Two"),
    Three("Three");

    private final String value;

    /**
     * Instantiate a Count value.
     *
     * @param value the given Count value.
     */
    Count(String value) {
      this.value = value;
    }
  }

  /**
   * This enum represents the Filling value of a card.
   * Filling values can be one of: Empty, Striped, or Full.
   */
  public enum Filling {
    Empty("Empty"),
    Striped("Stripe"),
    Full("Full");

    private final String value;

    /**
     * Instantiate a Filling value.
     *
     * @param value the given Filling value.
     */
    Filling(String value) {
      this.value = value;
    }
  }

  /**
   * This enum represents the Shape value of a card.
   * Shape values can be one of: Oval, Squiggle, or Diamond.
   */
  public enum Shape {
    Oval("Oval"),
    Squiggle("Squiggle"),
    Diamond("Diamond");

    private final String value;

    /**
     * Instantiate a Shape value.
     *
     * @param value the given Shape value.
     */
    Shape(String value) {
      this.value = value;
    }
  }

  /**
   * Constructor to instantiate a Card given the three unique attributes of a card.
   *
   * @param count   represents the count value.
   * @param filling represents the filling value.
   * @param shape   represents the shape value.
   * @throws IllegalArgumentException when a given value is invalid or null.
   */
  public Card(Count count, Filling filling, Shape shape) throws IllegalArgumentException {
    if (count == null || filling == null || shape == null) { // Check if values are null.
      throw new IllegalArgumentException("Values cannot be null.");
    }
    if (!isValidEnum(count) && !isValidEnum(filling) && !isValidEnum(shape)) { // Check validity.
      throw new IllegalArgumentException("Values must be valid.");
    }
    this.count = count;
    this.filling = filling;
    this.shape = shape;
  }

  /**
   * Takes in an object and checks if it is a valid value for Count, Filling, or Shape.
   *
   * @param o the given object.
   * @return true if it is a valid value, else, return false.
   */
  public boolean isValidEnum(Object o) {
    if (o instanceof Count) {
      Count count = (Count) o;
      return count.value.equals("One") || count.value.equals("Two") || count.value.equals("Three");
    } else if (o instanceof Filling) {
      Filling filling = (Filling) o;
      return filling.value.equals("Empty") || filling.value.equals("Stripe")
              || filling.value.equals("Full");
    } else if (o instanceof Shape) {
      Shape shape = (Shape) o;
      return shape.value.equals("Oval") || shape.value.equals("Squiggle")
              || shape.value.equals("Diamond");
    } else {
      return false;
    }
  }

  /**
   * Return the Count value of a card.
   * Counts are represented as 1 for One, 2 for Two, and 3 for Three.
   *
   * @return the Count value of a card in String format.
   */
  public String getCountValues() {
    if (this.count.value.equals("One")) { // case One
      return "1";
    } else if (this.count.value.equals("Two")) { // case Two
      return "2";
    } else { // case Three
      return "3";
    }
  }

  /**
   * Return the Filling value of a card.
   * Fillings are represented as E for Empty, S for Striped, and F for Full.
   *
   * @return the Filling value of a card in String format.
   */
  public String getFillingValues() {
    if (this.filling.value.equals("Empty")) { // case Empty
      return "E";
    } else if (this.filling.value.equals("Stripe")) { // case Stripe
      return "S";
    } else { // case Full
      return "F";
    }
  }

  /**
   * Return the Shape value of a card.
   * Shapes are represented as O for Oval, Q for Squiggle, and D for Diamond.
   *
   * @return the Shape value of a card in String format.
   */
  public String getShapeValues() {
    if (this.shape.value.equals("Oval")) { // case Oval
      return "O";
    } else if (this.shape.value.equals("Squiggle")) { // case Squiggle
      return "Q";
    } else { // case Diamond
      return "D";
    }
  }

  /**
   * Override the toString method to return a String representation of a card.
   * The representation will print out the attributes in the order of number, filling, then
   * shapes.
   *
   * @return the String representation of a card.
   */
  @Override
  public String toString() {
    return this.getCountValues() + this.getFillingValues() + this.getShapeValues();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Card card = (Card) o;
    return this.getCountValues().equals(card.getCountValues())
            && this.getFillingValues().equals(card.getFillingValues())
            && this.getShapeValues().equals(card.getShapeValues());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getCountValues(), this.getFillingValues(), this.getShapeValues());
  }
}
