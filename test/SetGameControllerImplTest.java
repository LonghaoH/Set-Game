import org.junit.Test;

import java.io.StringReader;

import cs3500.set.controller.SetGameController;
import cs3500.set.controller.SetGameControllerImpl;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.model.hw02.SetThreeGameModel;
import cs3500.set.view.SetGameTextView;
import cs3500.set.view.SetGameView;

import static org.junit.Assert.assertEquals;

/**
 * This class contains a collection of tests for the SetGameControllerImpl class.
 */
public class SetGameControllerImplTest {
  SetGameModel model;
  SetGameView view;
  SetGameController controller;

  @Test
  public void playGame() {
    // Test quitting after creating grid
    Readable input = new StringReader("3 3 q");
    StringBuilder output = new StringBuilder();
    this.model = new SetThreeGameModel();
    this.view = new SetGameTextView(this.model, output);
    this.controller = new SetGameControllerImpl(this.model, this.view, input);
    this.controller.playGame();
    String[] expected = output.toString().split("\n");
    assertEquals("1EO 1EQ 1ED", expected[1]);
    assertEquals("1SO 1SQ 1SD", expected[2]);
    assertEquals("1FO 1FQ 1FD", expected[3]);
    assertEquals("Score: 0", expected[4]);
    assertEquals("Game quit!", expected[5]);
    assertEquals("State of game when quit:", expected[6]);
    assertEquals("1EO 1EQ 1ED", expected[7]);
    assertEquals("1SO 1SQ 1SD", expected[8]);
    assertEquals("1FO 1FQ 1FD", expected[9]);
    assertEquals("Score: 0", expected[10]);

    // Test immediate quit
    input = new StringReader("q");
    output = new StringBuilder();
    this.model = new SetThreeGameModel();
    this.view = new SetGameTextView(this.model, output);
    this.controller = new SetGameControllerImpl(this.model, this.view, input);
    this.controller.playGame();
    expected = output.toString().split("\n");
    assertEquals("Game quit!", expected[1]);
    assertEquals("Score: 0", expected[2]);

    // Test quitting after inputting card1's row pos
    input = new StringReader("3 3 1 q");
    output = new StringBuilder();
    this.model = new SetThreeGameModel();
    this.view = new SetGameTextView(this.model, output);
    this.controller = new SetGameControllerImpl(this.model, this.view, input);
    this.controller.playGame();
    expected = output.toString().split("\n");
    assertEquals("1EO 1EQ 1ED", expected[1]);
    assertEquals("1SO 1SQ 1SD", expected[2]);
    assertEquals("1FO 1FQ 1FD", expected[3]);
    assertEquals("Score: 0", expected[4]);
    assertEquals("Game quit!", expected[5]);
    assertEquals("State of game when quit:", expected[6]);
    assertEquals("1EO 1EQ 1ED", expected[7]);
    assertEquals("1SO 1SQ 1SD", expected[8]);
    assertEquals("1FO 1FQ 1FD", expected[9]);
    assertEquals("Score: 0", expected[10]);

    // Test quitting after inputting card1's col pos
    input = new StringReader("3 3 1 1 q");
    output = new StringBuilder();
    this.model = new SetThreeGameModel();
    this.view = new SetGameTextView(this.model, output);
    this.controller = new SetGameControllerImpl(this.model, this.view, input);
    this.controller.playGame();
    expected = output.toString().split("\n");
    assertEquals("1EO 1EQ 1ED", expected[1]);
    assertEquals("1SO 1SQ 1SD", expected[2]);
    assertEquals("1FO 1FQ 1FD", expected[3]);
    assertEquals("Score: 0", expected[4]);
    assertEquals("Game quit!", expected[5]);
    assertEquals("State of game when quit:", expected[6]);
    assertEquals("1EO 1EQ 1ED", expected[7]);
    assertEquals("1SO 1SQ 1SD", expected[8]);
    assertEquals("1FO 1FQ 1FD", expected[9]);
    assertEquals("Score: 0", expected[10]);

    // Test quitting after inputting card2's row pos
    input = new StringReader("3 3 1 1 1 q");
    output = new StringBuilder();
    this.model = new SetThreeGameModel();
    this.view = new SetGameTextView(this.model, output);
    this.controller = new SetGameControllerImpl(this.model, this.view, input);
    this.controller.playGame();
    expected = output.toString().split("\n");
    assertEquals("1EO 1EQ 1ED", expected[1]);
    assertEquals("1SO 1SQ 1SD", expected[2]);
    assertEquals("1FO 1FQ 1FD", expected[3]);
    assertEquals("Score: 0", expected[4]);
    assertEquals("Game quit!", expected[5]);
    assertEquals("State of game when quit:", expected[6]);
    assertEquals("1EO 1EQ 1ED", expected[7]);
    assertEquals("1SO 1SQ 1SD", expected[8]);
    assertEquals("1FO 1FQ 1FD", expected[9]);
    assertEquals("Score: 0", expected[10]);

    // Test quitting after inputting card2's col pos
    input = new StringReader("3 3 1 1 1 2 q");
    output = new StringBuilder();
    this.model = new SetThreeGameModel();
    this.view = new SetGameTextView(this.model, output);
    this.controller = new SetGameControllerImpl(this.model, this.view, input);
    this.controller.playGame();
    expected = output.toString().split("\n");
    assertEquals("1EO 1EQ 1ED", expected[1]);
    assertEquals("1SO 1SQ 1SD", expected[2]);
    assertEquals("1FO 1FQ 1FD", expected[3]);
    assertEquals("Score: 0", expected[4]);
    assertEquals("Game quit!", expected[5]);
    assertEquals("State of game when quit:", expected[6]);
    assertEquals("1EO 1EQ 1ED", expected[7]);
    assertEquals("1SO 1SQ 1SD", expected[8]);
    assertEquals("1FO 1FQ 1FD", expected[9]);
    assertEquals("Score: 0", expected[10]);

    // Test quitting after inputting card3's row pos
    input = new StringReader("3 3 1 1 1 2 1 q");
    output = new StringBuilder();
    this.model = new SetThreeGameModel();
    this.view = new SetGameTextView(this.model, output);
    this.controller = new SetGameControllerImpl(this.model, this.view, input);
    this.controller.playGame();
    expected = output.toString().split("\n");
    assertEquals("1EO 1EQ 1ED", expected[1]);
    assertEquals("1SO 1SQ 1SD", expected[2]);
    assertEquals("1FO 1FQ 1FD", expected[3]);
    assertEquals("Score: 0", expected[4]);
    assertEquals("Game quit!", expected[5]);
    assertEquals("State of game when quit:", expected[6]);
    assertEquals("1EO 1EQ 1ED", expected[7]);
    assertEquals("1SO 1SQ 1SD", expected[8]);
    assertEquals("1FO 1FQ 1FD", expected[9]);
    assertEquals("Score: 0", expected[10]);

    // Test quitting after claiming one set
    input = new StringReader("3 3 1 1 1 2 1 3 q");
    output = new StringBuilder();
    this.model = new SetThreeGameModel();
    this.view = new SetGameTextView(this.model, output);
    this.controller = new SetGameControllerImpl(this.model, this.view, input);
    this.controller.playGame();
    expected = output.toString().split("\n");
    assertEquals("1EO 1EQ 1ED", expected[1]);
    assertEquals("1SO 1SQ 1SD", expected[2]);
    assertEquals("1FO 1FQ 1FD", expected[3]);
    assertEquals("Score: 0", expected[4]);
    assertEquals("2EO 2EQ 2ED", expected[5]);
    assertEquals("1SO 1SQ 1SD", expected[6]);
    assertEquals("1FO 1FQ 1FD", expected[7]);
    assertEquals("Score: 1", expected[8]);
    assertEquals("Game quit!", expected[9]);
    assertEquals("State of game when quit:", expected[10]);
    assertEquals("2EO 2EQ 2ED", expected[11]);
    assertEquals("1SO 1SQ 1SD", expected[12]);
    assertEquals("1FO 1FQ 1FD", expected[13]);
    assertEquals("Score: 1", expected[14]);

    // Test invalid inputs for card1 row
    input = new StringReader("3 3 a b c 0 -1 -10 q");
    output = new StringBuilder();
    this.model = new SetThreeGameModel();
    this.view = new SetGameTextView(this.model, output);
    this.controller = new SetGameControllerImpl(this.model, this.view, input);
    this.controller.playGame();
    expected = output.toString().split("\n");
    assertEquals("1EO 1EQ 1ED", expected[1]);
    assertEquals("1SO 1SQ 1SD", expected[2]);
    assertEquals("1FO 1FQ 1FD", expected[3]);
    assertEquals("Score: 0", expected[4]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[5]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[6]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[7]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[8]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[9]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[10]);
    assertEquals("Game quit!", expected[11]);
    assertEquals("State of game when quit:", expected[12]);
    assertEquals("1EO 1EQ 1ED", expected[13]);
    assertEquals("1SO 1SQ 1SD", expected[14]);
    assertEquals("1FO 1FQ 1FD", expected[15]);
    assertEquals("Score: 0", expected[16]);

    // Test invalid inputs for card1 row, then valid, then invalid card1 col
    input = new StringReader("3 3 a b 1 0 -1 -10 q");
    output = new StringBuilder();
    this.model = new SetThreeGameModel();
    this.view = new SetGameTextView(this.model, output);
    this.controller = new SetGameControllerImpl(this.model, this.view, input);
    this.controller.playGame();
    expected = output.toString().split("\n");
    assertEquals("1EO 1EQ 1ED", expected[1]);
    assertEquals("1SO 1SQ 1SD", expected[2]);
    assertEquals("1FO 1FQ 1FD", expected[3]);
    assertEquals("Score: 0", expected[4]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[5]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[6]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[7]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[8]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[9]);
    assertEquals("Game quit!", expected[10]);
    assertEquals("State of game when quit:", expected[11]);
    assertEquals("1EO 1EQ 1ED", expected[12]);
    assertEquals("1SO 1SQ 1SD", expected[13]);
    assertEquals("1FO 1FQ 1FD", expected[14]);
    assertEquals("Score: 0", expected[15]);

    // Test invalid inputs for card1 row, then valid, then invalid card1 col, then valid card1 col
    // then invalid card2 row
    input = new StringReader("3 3 a 1 -2 0 1 -10 q");
    output = new StringBuilder();
    this.model = new SetThreeGameModel();
    this.view = new SetGameTextView(this.model, output);
    this.controller = new SetGameControllerImpl(this.model, this.view, input);
    this.controller.playGame();
    expected = output.toString().split("\n");
    assertEquals("1EO 1EQ 1ED", expected[1]);
    assertEquals("1SO 1SQ 1SD", expected[2]);
    assertEquals("1FO 1FQ 1FD", expected[3]);
    assertEquals("Score: 0", expected[4]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[5]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[6]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[7]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[8]);
    assertEquals("Game quit!", expected[9]);
    assertEquals("State of game when quit:", expected[10]);
    assertEquals("1EO 1EQ 1ED", expected[11]);
    assertEquals("1SO 1SQ 1SD", expected[12]);
    assertEquals("1FO 1FQ 1FD", expected[13]);
    assertEquals("Score: 0", expected[14]);

    // Test invalid inputs for card1 row, then valid, then invalid card1 col, then valid card1 col
    // then invalid card2 row, then valid card2 row
    input = new StringReader("3 3 a 1 -2 1 -10 1 q");
    output = new StringBuilder();
    this.model = new SetThreeGameModel();
    this.view = new SetGameTextView(this.model, output);
    this.controller = new SetGameControllerImpl(this.model, this.view, input);
    this.controller.playGame();
    expected = output.toString().split("\n");
    assertEquals("1EO 1EQ 1ED", expected[1]);
    assertEquals("1SO 1SQ 1SD", expected[2]);
    assertEquals("1FO 1FQ 1FD", expected[3]);
    assertEquals("Score: 0", expected[4]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[5]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[6]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[7]);
    assertEquals("Game quit!", expected[8]);
    assertEquals("State of game when quit:", expected[9]);
    assertEquals("1EO 1EQ 1ED", expected[10]);
    assertEquals("1SO 1SQ 1SD", expected[11]);
    assertEquals("1FO 1FQ 1FD", expected[12]);
    assertEquals("Score: 0", expected[13]);

    // Test invalid inputs for card1 row, then valid, then invalid card1 col, then valid card1 col
    // then invalid card2 row, then valid card2 row, then invalid card2 col
    input = new StringReader("3 3 a 1 -2 1 -10 1 p q");
    output = new StringBuilder();
    this.model = new SetThreeGameModel();
    this.view = new SetGameTextView(this.model, output);
    this.controller = new SetGameControllerImpl(this.model, this.view, input);
    this.controller.playGame();
    expected = output.toString().split("\n");
    assertEquals("1EO 1EQ 1ED", expected[1]);
    assertEquals("1SO 1SQ 1SD", expected[2]);
    assertEquals("1FO 1FQ 1FD", expected[3]);
    assertEquals("Score: 0", expected[4]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[5]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[6]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[7]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[8]);
    assertEquals("Game quit!", expected[9]);
    assertEquals("State of game when quit:", expected[10]);
    assertEquals("1EO 1EQ 1ED", expected[11]);
    assertEquals("1SO 1SQ 1SD", expected[12]);
    assertEquals("1FO 1FQ 1FD", expected[13]);
    assertEquals("Score: 0", expected[14]);

    // Test invalid inputs for card1 row, then valid, then invalid card1 col, then valid card1 col
    // then invalid card2 row, then valid card2 row, then invalid card2 col, then valid card2 col
    input = new StringReader("3 3 a 1 -2 1 -10 1 p 2 q");
    output = new StringBuilder();
    this.model = new SetThreeGameModel();
    this.view = new SetGameTextView(this.model, output);
    this.controller = new SetGameControllerImpl(this.model, this.view, input);
    this.controller.playGame();
    expected = output.toString().split("\n");
    assertEquals("1EO 1EQ 1ED", expected[1]);
    assertEquals("1SO 1SQ 1SD", expected[2]);
    assertEquals("1FO 1FQ 1FD", expected[3]);
    assertEquals("Score: 0", expected[4]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[5]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[6]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[7]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[8]);
    assertEquals("Game quit!", expected[9]);
    assertEquals("State of game when quit:", expected[10]);
    assertEquals("1EO 1EQ 1ED", expected[11]);
    assertEquals("1SO 1SQ 1SD", expected[12]);
    assertEquals("1FO 1FQ 1FD", expected[13]);
    assertEquals("Score: 0", expected[14]);

    // Test invalid inputs for card1 row, then valid, then invalid card1 col, then valid card1 col
    // then invalid card2 row, then valid card2 row, then invalid card2 col, then valid card2 col
    // then invalid card3 row
    input = new StringReader("3 3 a 1 -2 1 -10 1 p 2 c q");
    output = new StringBuilder();
    this.model = new SetThreeGameModel();
    this.view = new SetGameTextView(this.model, output);
    this.controller = new SetGameControllerImpl(this.model, this.view, input);
    this.controller.playGame();
    expected = output.toString().split("\n");
    assertEquals("1EO 1EQ 1ED", expected[1]);
    assertEquals("1SO 1SQ 1SD", expected[2]);
    assertEquals("1FO 1FQ 1FD", expected[3]);
    assertEquals("Score: 0", expected[4]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[5]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[6]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[7]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[8]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[9]);
    assertEquals("Game quit!", expected[10]);
    assertEquals("State of game when quit:", expected[11]);
    assertEquals("1EO 1EQ 1ED", expected[12]);
    assertEquals("1SO 1SQ 1SD", expected[13]);
    assertEquals("1FO 1FQ 1FD", expected[14]);
    assertEquals("Score: 0", expected[15]);

    // Test invalid inputs for card1 row, then valid, then invalid card1 col, then valid card1 col
    // then invalid card2 row, then valid card2 row, then invalid card2 col, then valid card2 col
    // then invalid card3 row, then valid card3 row
    input = new StringReader("3 3 a 1 -2 1 -10 1 p 2 c 1 q");
    output = new StringBuilder();
    this.model = new SetThreeGameModel();
    this.view = new SetGameTextView(this.model, output);
    this.controller = new SetGameControllerImpl(this.model, this.view, input);
    this.controller.playGame();
    expected = output.toString().split("\n");
    assertEquals("1EO 1EQ 1ED", expected[1]);
    assertEquals("1SO 1SQ 1SD", expected[2]);
    assertEquals("1FO 1FQ 1FD", expected[3]);
    assertEquals("Score: 0", expected[4]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[5]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[6]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[7]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[8]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[9]);
    assertEquals("Game quit!", expected[10]);
    assertEquals("State of game when quit:", expected[11]);
    assertEquals("1EO 1EQ 1ED", expected[12]);
    assertEquals("1SO 1SQ 1SD", expected[13]);
    assertEquals("1FO 1FQ 1FD", expected[14]);
    assertEquals("Score: 0", expected[15]);

    // Test invalid inputs for card1 row, then valid, then invalid card1 col, then valid card1 col
    // then invalid card2 row, then valid card2 row, then invalid card2 col, then valid card2 col
    // then invalid card3 row, then valid card3 row, then invalid card3 col
    input = new StringReader("3 3 a 1 -2 1 -10 1 p 2 c 1 y q");
    output = new StringBuilder();
    this.model = new SetThreeGameModel();
    this.view = new SetGameTextView(this.model, output);
    this.controller = new SetGameControllerImpl(this.model, this.view, input);
    this.controller.playGame();
    expected = output.toString().split("\n");
    assertEquals("1EO 1EQ 1ED", expected[1]);
    assertEquals("1SO 1SQ 1SD", expected[2]);
    assertEquals("1FO 1FQ 1FD", expected[3]);
    assertEquals("Score: 0", expected[4]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[5]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[6]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[7]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[8]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[9]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[10]);
    assertEquals("Game quit!", expected[11]);
    assertEquals("State of game when quit:", expected[12]);
    assertEquals("1EO 1EQ 1ED", expected[13]);
    assertEquals("1SO 1SQ 1SD", expected[14]);
    assertEquals("1FO 1FQ 1FD", expected[15]);
    assertEquals("Score: 0", expected[16]);

    // Test invalid inputs for card1 row, then valid, then invalid card1 col, then valid card1 col
    // then invalid card2 row, then valid card2 row, then invalid card2 col, then valid card2 col
    // then invalid card3 row, then valid card3 row, then invalid card3 col, then valid card3 col
    // Should result in claiming a set
    input = new StringReader("3 3 a 1 -2 1 -10 1 p 2 c 1 y 3 q");
    output = new StringBuilder();
    this.model = new SetThreeGameModel();
    this.view = new SetGameTextView(this.model, output);
    this.controller = new SetGameControllerImpl(this.model, this.view, input);
    this.controller.playGame();
    expected = output.toString().split("\n");
    assertEquals("1EO 1EQ 1ED", expected[1]);
    assertEquals("1SO 1SQ 1SD", expected[2]);
    assertEquals("1FO 1FQ 1FD", expected[3]);
    assertEquals("Score: 0", expected[4]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[5]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[6]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[7]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[8]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[9]);
    assertEquals("Try again. Values must be positive integer, q, or Q.", expected[10]);
    assertEquals("2EO 2EQ 2ED", expected[11]);
    assertEquals("1SO 1SQ 1SD", expected[12]);
    assertEquals("1FO 1FQ 1FD", expected[13]);
    assertEquals("Score: 1", expected[14]);
    assertEquals("Game quit!", expected[15]);
    assertEquals("State of game when quit:", expected[16]);
    assertEquals("2EO 2EQ 2ED", expected[17]);
    assertEquals("1SO 1SQ 1SD", expected[18]);
    assertEquals("1FO 1FQ 1FD", expected[19]);
    assertEquals("Score: 1", expected[20]);

    // Test invalid claim
    input = new StringReader("3 3 1 1 1 2 2 2 q");
    output = new StringBuilder();
    this.model = new SetThreeGameModel();
    this.view = new SetGameTextView(this.model, output);
    this.controller = new SetGameControllerImpl(this.model, this.view, input);
    this.controller.playGame();
    expected = output.toString().split("\n");
    assertEquals("1EO 1EQ 1ED", expected[1]);
    assertEquals("1SO 1SQ 1SD", expected[2]);
    assertEquals("1FO 1FQ 1FD", expected[3]);
    assertEquals("Score: 0", expected[4]);
    assertEquals("Invalid claim. Try again.", expected[5]);
    assertEquals("1EO 1EQ 1ED", expected[6]);
    assertEquals("1SO 1SQ 1SD", expected[7]);
    assertEquals("1FO 1FQ 1FD", expected[8]);
    assertEquals("Score: 0", expected[9]);
    assertEquals("Game quit!", expected[10]);
    assertEquals("State of game when quit:", expected[11]);
    assertEquals("1EO 1EQ 1ED", expected[12]);
    assertEquals("1SO 1SQ 1SD", expected[13]);
    assertEquals("1FO 1FQ 1FD", expected[14]);
    assertEquals("Score: 0", expected[15]);

    // Test invalid claim, then claim correctly
    input = new StringReader("3 3 1 1 1 2 2 2 1 1 1 2 1 3 q");
    output = new StringBuilder();
    this.model = new SetThreeGameModel();
    this.view = new SetGameTextView(this.model, output);
    this.controller = new SetGameControllerImpl(this.model, this.view, input);
    this.controller.playGame();
    expected = output.toString().split("\n");
    assertEquals("1EO 1EQ 1ED", expected[1]);
    assertEquals("1SO 1SQ 1SD", expected[2]);
    assertEquals("1FO 1FQ 1FD", expected[3]);
    assertEquals("Score: 0", expected[4]);
    assertEquals("Invalid claim. Try again.", expected[5]);
    assertEquals("1EO 1EQ 1ED", expected[6]);
    assertEquals("1SO 1SQ 1SD", expected[7]);
    assertEquals("1FO 1FQ 1FD", expected[8]);
    assertEquals("Score: 0", expected[9]);
    assertEquals("2EO 2EQ 2ED", expected[10]);
    assertEquals("1SO 1SQ 1SD", expected[11]);
    assertEquals("1FO 1FQ 1FD", expected[12]);
    assertEquals("Score: 1", expected[13]);
    assertEquals("Game quit!", expected[14]);
    assertEquals("State of game when quit:", expected[15]);
    assertEquals("2EO 2EQ 2ED", expected[16]);
    assertEquals("1SO 1SQ 1SD", expected[17]);
    assertEquals("1FO 1FQ 1FD", expected[18]);
    assertEquals("Score: 1", expected[19]);
  }
}