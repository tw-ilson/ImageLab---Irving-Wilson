package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import javax.imageio.ImageIO;
import model.ImageProcessorLayerModel;
import model.image.Image;
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

    displayMessage("Welcome.");

    //We are giving significance to the meaning of a line separator here
    while (scan.hasNextLine()) {
      Scanner linescan = new Scanner(scan.nextLine());
      if (linescan.hasNext()) {
        switch (linescan.next()) {
          case "q":
          case "quit":
            displayMessage("User quit.");
            return;
          case "create":
            if (linescan.hasNext()
                && linescan.next().equals("layer")
                && linescan.hasNext()) {
              String newLayerName = linescan.next();
              model.createLayer(newLayerName);
              displayMessage("Layer \"" + newLayerName + "\" created");
            } else {
              displayMessage("Not a valid command. Skipping.");
              continue;
            }
            break;
          case "current":
            if (linescan.hasNext()) {
              String layername = null;
              try {
                layername = linescan.next();
                model.setCurrentLayer(layername);
                displayMessage("Current layer: \"" + layername + "\"");
              } catch (IllegalArgumentException e) {
                displayMessage("Requested layer " + layername + " does not exist. Skipping.");
              }
            } else {
              displayMessage("No layer specified. Skipping.");
            }
            break;
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
            break;
          case "save":
            try {
              Image currentImage = model.getImage();
              if (linescan.hasNext()) {
                String fileToSave = linescan.next();
                if (fileToSave.contains(".")) {
                  String ext = fileToSave.substring(fileToSave.lastIndexOf('.'));
                  try {
                    if (Arrays.stream(ImageIO.getWriterFormatNames())
                        .anyMatch(name -> ext.substring(1).equals(name)) || ext.equals(".ppm")) {
                      displayMessage(
                          ImageUtils.write(ext.substring(1), fileToSave, currentImage));
                    } else {
                      displayMessage("Cannot recognize file type. Skipping.");
                    }
                  } catch (IOException e) {
                    displayMessage("IO error occurred.");
                    e.printStackTrace();
                  }
                } else {
                  //defaults to jpeg
                  try {
                    displayMessage(
                        ImageUtils.write("jpeg", fileToSave + ".jpeg", model.getImage()));
                  } catch (IOException e) {
                    displayMessage("IO error occurred.");
                    e.printStackTrace();
                  }
                }
              } else {
                try {
                  displayMessage(ImageUtils.write("jpeg", ".jpeg", model.getImage()));
                } catch (IOException e) {
                  displayMessage("IO error occurred.");
                  e.printStackTrace();
                }
              }
            } catch (IllegalArgumentException e) {
              displayMessage("Incorrect arguments. Aborting.");
            }
            break;
          case "filter":
            if (linescan.hasNext()) {
              String filter = linescan.next();
              try {
                model.applyFilter(filter);
              } catch (IllegalStateException e) {
                displayMessage("No image to apply filter to");
              } catch (IllegalArgumentException e) {
                displayMessage("No such filter.");
              }
            } else {
              displayMessage("no filter specified.");
            }
            break;
          default: //do nothing
        }
      }
    }
  }

  private void displayMessage(String text) {
    try {
      this.view.giveMessage(text);
      this.view.giveMessage("\n");
    } catch (IOException e) {
      System.out.println("IO error occurred.");
    }
  }
}