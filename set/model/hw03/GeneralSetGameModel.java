package cs3500.set.model.hw03;

import java.util.ArrayList;
import java.util.List;

import cs3500.set.model.hw02.Card;
import cs3500.set.model.hw02.Coord;
import cs3500.set.model.hw02.SetGameModel;

/**
 * This class represents the General version of a Set game. It implements methods from the
 * SetGameModel interface.
 */
public class GeneralSetGameModel implements SetGameModel<Card> {
  public List<Card> deck;
  protected int height;
  protected int width;
  public Card[][] grid;
  protected int score;
  public boolean isGameStarted;
  protected boolean hasCard;

  /**
   * Default constructor: Instantiate an instance of a general Set game.
   */
  public GeneralSetGameModel() {
    this.deck = new ArrayList<>();
    this.height = 0;
    this.width = 0;
    this.grid = new Card[0][0];
    this.score = 0;
    this.isGameStarted = false;
    this.hasCard = false;
  }

  @Override
  public void claimSet(Coord coord1, Coord coord2, Coord coord3) {
    this.claimASet(coord1, coord2, coord3);
    this.updateGrid();
  }

  /**
   * Given 3 coordinates, process if the Cards in the given 3 coords is a set,
   * if it is, claim the set and fill the grid with cards from the deck. Game is over
   * if there aren't enough cards to fill the deck.
   *
   * @param c1 the first card's coordinate.
   * @param c2 the second card's coordinate.
   * @param c3 the third card's coordinate.
   */
  public void claimASet(Coord c1, Coord c2, Coord c3) {
    if (!this.isGameStarted) {
      throw new IllegalStateException("Game is not started.");
    }
    if (!this.isValidCoord(c1) || !this.isValidCoord(c2) || !this.isValidCoord(c3)) {
      throw new IllegalArgumentException("Invalid coords for this grid.");
    }
    if (!this.isValidSet(c1, c2, c3)) {
      throw new IllegalArgumentException("Not a set!");
    }
    this.score += 1;
    this.grid[c1.row][c1.col] = null;
    this.grid[c2.row][c2.col] = null;
    this.grid[c3.row][c3.col] = null;
    if (this.deck.size() >= 3) { // Have at least 3 cards to fill the claimed cards.
      this.grid[c1.row][c1.col] = this.deck.get(0);
      this.deck.remove(0);
      this.grid[c2.row][c2.col] = this.deck.get(0);
      this.deck.remove(0);
      this.grid[c3.row][c3.col] = this.deck.get(0);
      this.deck.remove(0);
    } else {
      this.hasCard = false; // Not enough cards to replace claimed set.
      this.isGameOver();
    }
  }

  /**
   * Checks if the given coord is a valid coord on the grid.
   *
   * @param coord the given coord.
   * @return true if the given coord is valid, otherwise, return false.
   */
  public boolean isValidCoord(Coord coord) {
    return (coord.row >= 0 && coord.row <= this.height - 1
            && coord.col >= 0 && coord.col <= this.width - 1);
  }

  @Override
  public void startGameWithDeck(List<Card> deck, int height, int width)
          throws IllegalArgumentException {
    if (height <= 0 || width <= 0) { // Check for height and width.
      throw new IllegalArgumentException("Grid must be positive dimensions.");
    }
    if (height * width < 3) { // Grid cannot hold at least 3 cards.
      throw new IllegalArgumentException("Grid must hold at least 3 cards.");
    }
    if (deck == null) { // Check if deck is null.
      throw new IllegalArgumentException("Deck of cards cannot be null.");
    }
    if (deck.size() < height * width) { // Check if deck has enough cards to fill grid.
      throw new IllegalArgumentException("Must have enough cards in deck to fill grid.");
    }
    this.initializeFields(deck, height, width);
    this.createGrid();
    this.updateGrid();
  }

  /**
   * Initialize the fields given a deck, height, and width.
   *
   * @param d the given deck of cards.
   * @param h the given grid height.
   * @param w the given grid width.
   */
  public void initializeFields(List<Card> d, int h, int w) {
    this.height = h;
    this.width = w;
    this.deck = d;
    this.grid = new Card[this.height][this.width];
    this.isGameStarted = true;
    this.hasCard = true;
  }

  /**
   * Creates a grid for the game and fills the grid with cards from the deck.
   * After filling a grid position with a card from the deck, the card is removed from the deck.
   */
  public void createGrid() {
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        this.grid[i][j] = this.deck.get(0);
        this.deck.remove(0);
      }
    }
  }

  /**
   * If there are no set present on the current grid, add a new row to the current grid
   * and fill the new row with cards from the deck. If there are not enough cards in the deck
   * during this process, the game will end.
   */
  public void updateGrid() {
    int oldHeight = this.getHeight();
    Card[][] oldGrid = this.grid;
    if (!anySetsPresent()) {
      this.height += 1;
      this.grid = new Card[this.getHeight()][this.getWidth()];
      if (this.deck.size() < this.getWidth()) { // Check if there are enough cards.
        this.hasCard = false;
        this.isGameOver();
      } else { // There are enough cards.
        // Refill the new grid with the current game state.
        for (int i = 0; i < oldHeight; i++) {
          for (int j = 0; j < this.getWidth(); j++) {
            this.grid[i][j] = oldGrid[i][j];
          }
        }

        // Fill the new row from the deck.
        for (int k = 0; k < this.getWidth(); k++) {
          this.grid[this.getHeight() - 1][k] = this.deck.get(0);
          this.deck.remove(0);
        }
      }
    }
  }

  @Override
  public int getWidth() throws IllegalStateException {
    if (!this.isGameStarted) {
      throw new IllegalStateException("Game is not started.");
    }
    return this.width;
  }

  @Override
  public int getHeight() throws IllegalStateException {
    if (!this.isGameStarted) {
      throw new IllegalStateException("Game is not started.");
    }
    return this.height;
  }

  @Override
  public int getScore() throws IllegalStateException {
    if (!this.isGameStarted) {
      throw new IllegalStateException("Game is not started.");
    }
    return this.score;
  }

  @Override
  public boolean anySetsPresent() {
    if (!this.isGameStarted) {
      throw new IllegalStateException("Game is not started.");
    }
    List<Card> currentCards = new ArrayList<>();
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        currentCards.add(this.grid[i][j]);
      }
    }
    boolean setPresent = false;
    for (int first = 0; first < currentCards.size() - 2; first++) {
      for (int second = first + 1; second < currentCards.size() - 1; second++) {
        for (int third = second + 1; third < currentCards.size(); third++) {
          if (currentCards.get(first) == null || currentCards.get(second) == null
                  || currentCards.get(third) == null) {
            return false;
          }
          if (this.isSameOrDiffAttri(currentCards.get(first), currentCards.get(second),
                  currentCards.get(third))) {
            setPresent = true;
            break;
          }
        }
      }
    }
    return setPresent;
  }

  @Override
  public boolean isValidSet(Coord coord1, Coord coord2, Coord coord3)
          throws IllegalArgumentException {
    if (!this.isGameStarted) {
      throw new IllegalStateException("Game is not started.");
    }
    if (!this.isValidCoord(coord1) || !this.isValidCoord(coord2) || !this.isValidCoord(coord3)) {
      throw new IllegalArgumentException("Invalid coordinates.");
    }
    Card card1 = this.grid[coord1.row][coord1.col];
    Card card2 = this.grid[coord2.row][coord2.col];
    Card card3 = this.grid[coord3.row][coord3.col];
    return this.isSameOrDiffAttri(card1, card2, card3);
  }

  /**
   * Given 3 cards, check to see if each attributes for the 3 cards are all the same or different.
   *
   * @param card1 the first given card.
   * @param card2 the second given card.
   * @param card3 the third given card.
   * @return true if each attribute are the same or different across all 3 cards.
   */
  public boolean isSameOrDiffAttri(Card card1, Card card2, Card card3) {
    boolean count = false;
    boolean filling = false;
    boolean shape = false;

    String card1Count = card1.getCountValues();
    String card2Count = card2.getCountValues();
    String card3Count = card3.getCountValues();
    if ((card1Count.equals(card2Count) && card2Count.equals(card3Count))
            || (!card1Count.equals(card2Count) && !card1Count.equals(card3Count)
            && !card2Count.equals(card3Count))) {
      count = true;
    }

    String card1Fill = card1.getFillingValues();
    String card2Fill = card2.getFillingValues();
    String card3Fill = card3.getFillingValues();
    if ((card1Fill.equals(card2Fill) && card2Fill.equals(card3Fill))
            || (!card1Fill.equals(card2Fill) && !card1Fill.equals(card3Fill)
            && !card2Fill.equals(card3Fill))) {
      filling = true;
    }

    String card1Shape = card1.getShapeValues();
    String card2Shape = card2.getShapeValues();
    String card3Shape = card3.getShapeValues();
    if ((card1Shape.equals(card2Shape) && card2Shape.equals(card3Shape))
            || (!card1Shape.equals(card2Shape) && !card1Shape.equals(card3Shape)
            && !card2Shape.equals(card3Shape))) {
      shape = true;
    }
    return count && filling && shape;
  }

  @Override
  public Card getCardAtCoord(int row, int col) {
    if (!this.isGameStarted) {
      throw new IllegalStateException("Game is not started.");
    }
    if (row < 0 || row > this.height - 1 || col < 0 || col > this.width - 1) {
      throw new IllegalArgumentException("Invalid position.");
    }
    return this.grid[row][col];
  }

  @Override
  public Card getCardAtCoord(Coord coord) {
    if (!this.isGameStarted) {
      throw new IllegalStateException("Game is not started.");
    }
    if (!this.isValidCoord(coord)) {
      throw new IllegalArgumentException("Invalid position.");
    }
    return this.grid[coord.row][coord.col];
  }

  @Override
  public boolean isGameOver() {
    return !this.hasCard;
  }

  @Override
  public List<Card> getCompleteDeck() {
    List<Card> fullDeck = new ArrayList<>();
    for (Card.Count c : Card.Count.values()) {
      for (Card.Filling f : Card.Filling.values()) {
        for (Card.Shape s : Card.Shape.values()) {
          fullDeck.add(new Card(c, f, s));
        }
      }
    }
    // Shuffles the deck into randomized order, commented out for easier testing.
    // Collections.shuffle(fullDeck);
    return fullDeck;
  }
}
