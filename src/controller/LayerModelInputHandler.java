package controller;

import java.io.IOException;
import java.util.Scanner;
import model.ImageProcessorLayerModel;
import view.ImageProcessorTextView;
import view.ImageProcessorView;

public class LayerModelInputHandler {

  ImageProcessorLayerModel model;
  ImageProcessorView view;

  /**
   * Scans inputs from a readable source and displays status with a ImageProcessorTextView.
   *
   * @param model
   * @param in
   * @param out
   */
  public void scanInput(ImageProcessorLayerModel model, Readable in, Appendable out) {
    this.model = model;
    this.view = new ImageProcessorTextView(out);
    Scanner scan = new Scanner(in);

    //We are giving significance to the meaning of a line separator here
    while (scan.hasNextLine()) {
      Scanner linescan = new Scanner(scan.nextLine());
      if (linescan.hasNext()) {
        switch (linescan.next()) {
          case "create":
            if (linescan.hasNext()
                && linescan.next().equals("layer")
                && linescan.hasNext()) {
              model.createLayer(linescan.next());

            } else {
              displayMessage("Not a valid command. Skipping.");
              continue;
            }
          case "current":
            if (linescan.hasNext()) {
              String layername = null;
              try {
                layername = linescan.next();
                model.setCurrentLayer(layername);
              } catch (IllegalArgumentException e) {
                displayMessage("Requested layer " + layername + " does not exist. Skipping.");
              }
            } else {
              displayMessage("No layer specified. Skipping.");
            }
          case "load":
            try {
              if (linescan.hasNext()) {
                String filename = linescan.next();
                this.model.editCurrentLayer(ImageUtils.read(filename));
                displayMessage("Successfully loaded image into current layer.");
              } else {
                displayMessage("No file specified. Skipping.");
              }
            } catch (IOException e) {
              displayMessage("IO error occurred. Make sure that the file path is valid. Skipping.");
            }
          case "save":
            if (linescan.hasNext()) {
              String fileToSave = linescan.next();
              if (fileToSave.contains(".")) {
                String ext = fileToSave.substring(fileToSave.lastIndexOf('.'));
              } else {
                //defaults to jpeg
                try {
                  displayMessage(ImageUtils.write("jpeg", fileToSave, model.getImage()));
                } catch (IOException e) {

                }
              }
            }
          case "filter":

          default: //do nothing
        }
      }
    }
  }

  private void displayMessage(String text) {
    try {
      this.view.giveMessage(text);
    } catch (IOException e) {
      System.out.println("IO error occurred.");
    }
  }
}