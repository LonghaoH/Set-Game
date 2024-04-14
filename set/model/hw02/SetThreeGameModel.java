package cs3500.set.model.hw02;

import java.util.List;

import cs3500.set.model.hw03.GeneralSetGameModel;

/**
 * This class represents the implementations of the SetGameModel interface.
 * It contains the methods for instantiating a game of Set Three game, keeping track of
 * the game states, as well as validating sets and updating scores.
 */
public class SetThreeGameModel extends GeneralSetGameModel {

  /**
   * Instantiates a game model without any parameters and default all fields.
   */
  public SetThreeGameModel() {
    super();
  }

  @Override
  public void claimSet(Coord coord1, Coord coord2, Coord coord3) {
    this.claimASet(coord1, coord2, coord3);
  }

  @Override
  public void startGameWithDeck(List<Card> deck, int height, int width)
          throws IllegalArgumentException {
    if (height != 3 || width != 3) { // Check for height and width.
      throw new IllegalArgumentException("Grid must be 3x3 in dimension.");
    }
    if (deck == null) { // Check if deck is null.
      throw new IllegalArgumentException("Deck of cards cannot be null.");
    }
    if (deck.size() < 9) { // Check if deck has at least 9 cards.
      throw new IllegalArgumentException("Must have at least 9 cards in deck.");
    }
    this.initializeFields(deck, height, width);
    this.createGrid();
  }

  @Override
  public boolean isGameOver() {
    return (!this.hasCard || !this.anySetsPresent());
  }
}
