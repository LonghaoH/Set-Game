package cs3500.set.view;

import java.io.IOException;

import cs3500.set.model.hw02.SetGameModelState;

/**
 * An implementation of the SetGameView interface.
 * This takes a SetGameModelState model and prints the grid
 */
public class SetGameTextView implements SetGameView {
  private final SetGameModelState model;
  private final Appendable destination;

  /**
   * Instantiate a text view of the Set Game Model provided with a model.
   *
   * @param model the given game model.
   * @throws IllegalArgumentException if given model is null.
   */
  public SetGameTextView(SetGameModelState model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    this.model = model;
    this.destination = System.out;
  }

  /**
   * Instantiate a text view of the Set Game Model provided with a model and a destination.
   *
   * @param model the given game model.
   * @param destination the given destination.
   * @throws IllegalArgumentException if the given model or destination is null.
   */
  public SetGameTextView(SetGameModelState model, Appendable destination)
          throws IllegalArgumentException {
    if (model == null || destination == null) {
      throw new IllegalArgumentException("Model or destination cannot be null.");
    }
    this.model = model;
    this.destination = destination;
  }

  @Override
  public String toString() {
    String grid = "";
    int row = this.model.getHeight();
    int col = this.model.getWidth();
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        grid += this.model.getCardAtCoord(i, j).toString() + " ";
      }
      grid = grid.stripTrailing();
      grid = grid.concat("\n");
    }
    return grid.substring(0, grid.length() - 1);
  }

  @Override
  public void renderGrid() throws IOException {
    this.destination.append("\n").append(this.toString()).append("\n");
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.destination.append(message);
  }
}
