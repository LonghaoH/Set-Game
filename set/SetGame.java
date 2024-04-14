package cs3500.set;

import java.io.InputStreamReader;

import cs3500.set.controller.SetGameController;
import cs3500.set.controller.SetGameControllerImpl;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.model.hw02.SetThreeGameModel;
import cs3500.set.model.hw03.GeneralSetGameModel;
import cs3500.set.view.SetGameTextView;
import cs3500.set.view.SetGameView;

/**
 * This class contains the main method to run a Set game. It can be used to run either
 * the general version of the game or the Set Three version. This program takes inputs as
 * command-line arguments.
 */
public final class SetGame {

  /**
   * This method represents the main method for running the Set game.
   *
   * @param args represents the input for the types of Set game the user want to play.
   */
  public static void main(String[] args) {
    SetGameModel model;
    SetGameView view;
    SetGameController controller;
    Readable readable;
    Appendable appendable;

    switch (args[0]) {
      case "three":
        model = new SetThreeGameModel();
        readable = new InputStreamReader(System.in);
        appendable = System.out;
        view = new SetGameTextView(model, appendable);
        controller = new SetGameControllerImpl(model, view, readable);
        controller.playGame();
        break;
      case "general":
        model = new GeneralSetGameModel();
        readable = new InputStreamReader(System.in);
        appendable = System.out;
        view = new SetGameTextView(model, appendable);
        controller = new SetGameControllerImpl(model, view, readable);
        controller.playGame();
        break;
      default:
        break;
    }
  }
}
