package cs3500.set.controller;

/**
 * This interface represents the controller for the Set game. It includes the method
 * that allows the user to play the game.
 */
public interface SetGameController {
  /**
   * Allows the user to play the Set game.
   *
   * @throws IllegalStateException if the controller is unable to successfully read input or
   *                               transmit output.
   */
  void playGame() throws IllegalStateException;
}
