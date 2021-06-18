package controller;

import controller.commands.ControlCommands;
import java.util.Scanner;
import model.ImageProcessorModel;


public class SimpleImageController implements ImageProcessorController {

  private ImageProcessorModel model;
  private Readable input;
  private Appendable output;
  private boolean hasQuit;
  private boolean isActive;

  public SimpleImageController(ImageProcessorModel model, Appendable output) throws
      IllegalArgumentException {
    this.model = model;
    this.output = output;

    if (this.output == null) {
      throw new IllegalArgumentException("Text output source cannot be null");
    }
    if (this.model == null) {
      throw new IllegalArgumentException("Model provided cannot be null");
    }
  }

  @Override
  public void run(Readable input) throws IllegalArgumentException, IllegalStateException {
    this.input = input;

  }

  private void operateApplication() {
    command = null;

    Scanner scanner = new Scanner(input);

    while (scanner.hasNext()) {
      String token = scanner.next();

      if (token.equals("quit") || token.equals("q")) {
        hasQuit = true;
        return;
      }

      if (command == null) {
        // each of these initial tokens extend into a larger command
        switch (token) {
          case "create":
            break;
          case "current":
            break;
          case "load":
            break;
          case "save":
            break;
          case "invisible":
            break;
          case "apply":
            break;
        }
      }
    }
  }
}
