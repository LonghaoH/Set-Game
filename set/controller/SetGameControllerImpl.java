package cs3500.set.controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cs3500.set.model.hw02.Coord;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.view.SetGameView;

/**
 * This class represents an implementation of the SetGameController. The user can play the game
 * through this controller and use it the inputs/outputs to interact with the view and model.
 */
public class SetGameControllerImpl implements SetGameController {
  private final SetGameModel model;
  private final SetGameView view;
  private final Readable readable;
  private boolean quit;
  private boolean start; // is game started?

  /**
   * Instantiate an implementation of the Controller.
   *
   * @param model represents the model of the Set game.
   * @param view represents the view of the Set game.
   * @param readable represents the inputs coming from the user.
   * @throws IllegalArgumentException if any of the arguments are null.
   */
  public SetGameControllerImpl(SetGameModel model, SetGameView view, Readable readable)
          throws IllegalArgumentException {
    if (model == null || view == null || readable == null) {
      throw new IllegalArgumentException("Model, view, and readable cannot be null.");
    }
    this.model = model;
    this.view = view;
    this.readable = readable;
    this.quit = false;
    this.start = false;
  }

  @Override
  public void playGame() throws IllegalStateException {
    Scanner scan = new Scanner(this.readable);
    while (!this.quit) {
      int height;
      int width;

      try {
        height = this.checkInput(scan);
        width = this.checkInput(scan);
      } catch (IOException | NoSuchElementException e) {
        throw new IllegalStateException();
      }

      if (this.quit) { // if game is quit after evaluating inputs, break out of loop.
        break;
      }

      try { // create a game with given height/width.
        this.model.startGameWithDeck(this.model.getCompleteDeck(), height, width);
        this.start = true;
        break;
      } catch (IllegalArgumentException e) {
        try {
          this.view.renderMessage("\nInvalid height/width. Try again.");
        } catch (IOException ex) {
          throw new IllegalStateException("Failed to transmit.");
        }
      }
    }

    while (!this.model.isGameOver() && !this.quit) {
      try {
        this.view.renderGrid();
        this.view.renderMessage("Score: " + this.model.getScore());
      } catch (IOException e) {
        throw new IllegalStateException("Failed to transmit.");
      }

      int card1Row;
      int card1Col;
      int card2Row;
      int card2Col;
      int card3Row;
      int card3Col;

      try {
        card1Row = this.checkInput(scan);
        card1Col = this.checkInput(scan);
        card2Row = this.checkInput(scan);
        card2Col = this.checkInput(scan);
        card3Row = this.checkInput(scan);
        card3Col = this.checkInput(scan);
      } catch (IOException | NoSuchElementException e) {
        throw new IllegalStateException();
      }

      if (this.quit) { // if game is quit after evaluating inputs, break out of loop.
        break;
      }

      Coord coord1;
      Coord coord2;
      Coord coord3;

      try {
        coord1 = new Coord(card1Row - 1, card1Col - 1);
        coord2 = new Coord(card2Row - 1, card2Col - 1);
        coord3 = new Coord(card3Row - 1, card3Col - 1);
        this.model.claimSet(coord1, coord2, coord3);
      } catch (IllegalArgumentException e) {
        try {
          this.view.renderMessage("\nInvalid claim. Try again.");
        } catch (IOException ex) {
          throw new IllegalStateException("Failed to transmit.");
        }
      }

      if (!this.model.isGameOver() && !this.quit && !scan.hasNext()) {
        throw new IllegalStateException("No more input.");
      }
    }

    try {
      if (!this.quit) { // Game over.
        this.view.renderMessage("\nGame over!\n");
      } else { // Manual quit.
        this.view.renderMessage("\nGame quit!");
        if (!this.start) { // If game has not started
          this.view.renderMessage("");
        } else {
          this.view.renderMessage("\nState of game when quit:");
          this.view.renderGrid();
        }
      }
      if (!this.start) { // If game has not started
        this.view.renderMessage("\nScore: 0");
      } else {
        this.view.renderMessage("Score: " + this.model.getScore());
      }
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  /**
   * Check if the input is a positive integer, q, or Q.
   *
   * @param scan the scanner of the inputs.
   * @return a value as soon as the input is valid.
   * @throws IOException if there is no next value in the scanner to check.
   */
  private int checkInput(Scanner scan) throws IOException {
    String str;
    if (this.quit) {
      return -1;
    }

    while (true) {
      if (!scan.hasNext()) {
        throw new IOException();
      }
      else {
        str = scan.next();
      }
      if (!this.isValidInput(str)) {
        this.view.renderMessage(
                "\nTry again. Values must be positive integer, q, or Q.");
      } else if (str.equalsIgnoreCase("q")) {
        this.quit = true;
        return -1;
      } else {
        return Integer.parseInt(str);
      }
    }
  }

  /**
   * Checks if the given String is a valid input of the game.
   * A valid input is one of: positive integer, q, or Q.
   *
   * @param str the given String.
   * @return true if the given String is a valid input, else return false.
   */
  private boolean isValidInput(String str) {
    try {
      return Integer.parseInt(str) >= 1;
    } catch (NumberFormatException e) {
      return str.equalsIgnoreCase("q");
    }
  }
}
