package controller;

import controller.commands.CreateCommandInterface;
import controller.commands.CreateLayer;
import java.util.Scanner;
import model.ImageProcessorModel;


public class SimpleImageController implements ImageController {

  private final ImageProcessorModel model;
  private final Readable rd;
  private final Appendable ap;
  private boolean hasQuit;
  private CreateCommandInterface command;

  public SimpleImageController(ImageProcessorModel model, Readable rd, Appendable ap) throws
      IllegalArgumentException {
    this.model = model;
    this.rd = rd;
    this.ap = ap;
    if (this.ap == null || this.rd == null) {
      throw new IllegalArgumentException("Cannot provide null readable or nullable");
    }
  }

  @Override
  public void appOpen(String filename) throws IllegalArgumentException, IllegalStateException {
    if (filename == null) {
      throw new IllegalArgumentException("Cannot provide null arguments");
    }
    // try catch statement <-- need to throw exception
    model.importImage(filename);
    this.operateApplication();


  }

  private void operateApplication() {

    Scanner scanner = new Scanner(this.rd);

    while (scanner.hasNext()) {
      String input = scanner.next();

      if (input.contains("quit") || input.contains("q")) {
        hasQuit = true;
        return;
      }

      switch (input) {
        case "create":
          if (scanner.hasNext()) {
            if (scanner.next() == "layer") {
              return;
            }
          }

          break;

      }
    }
  }


}
