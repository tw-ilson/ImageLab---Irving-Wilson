package controller;

import controller.commands.ControlCommands;
import java.util.Scanner;
import model.ImageProcessorModel;


public class SimpleImageController implements ImageController {

  private ImageProcessorModel model;
  private Readable input;
  //private Appendable ap;
  private boolean hasQuit;
  private ControlCommands command;
  private boolean isActive;

  public SimpleImageController(ImageProcessorModel model, Readable rd) throws
      IllegalArgumentException {
    this.model = model;
    this.input = rd;

    if (this.input == null) {
      throw new IllegalArgumentException("Text input source cannot be null");
    }
    if (this.model == null) {
      throw new IllegalArgumentException("Model provided cannot be null");
    }
  }

  @Override
  public void appOpen() throws IllegalArgumentException, IllegalStateException {
    this.operateApplication();
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
