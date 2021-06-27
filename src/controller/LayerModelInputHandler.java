package controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import javax.imageio.ImageIO;
import model.ImageProcessorLayerModel;
import model.image.Image;
import view.ImageProcessorView;

/**
 * This is a class meant to contain the functionality to deal with a LayedImageModel. This code is
 * contained within this class in order to increase reusability and replacablility. It stores a
 * model and a view.
 */
public class LayerModelInputHandler {

  ImageProcessorLayerModel model;
  ImageProcessorView view;

  /**
   * Scans inputs from a readable source and displays status with a ImageProcessorTextView.
   *
   * @param model the model to send commands to
   * @param in the source of input
   * @param out the destination for output.
   */
  public void scanInput(ImageProcessorLayerModel model, Readable in, ImageProcessorView out) {
    this.model = model;
    this.view = out;
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
          case "batch":
            try {
              if (linescan.hasNext()) {
                String textfilename = linescan.next();
                this.scanInput(this.model, new FileReader(textfilename), this.view);
              } else {
                displayMessage("No batch file provided.");
              }
            } catch (IOException e) {

            }
            break;
          case "create":
            if (linescan.hasNext()
                && linescan.next().equals("layer")
                && linescan.hasNext()) {
              String newLayerName = linescan.next();
              model.createLayer(newLayerName);
              model.setCurrentLayer(newLayerName);
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
                try {
                  this.model.editCurrentLayer(ImageUtils.read(filename));
                } catch (IllegalArgumentException e) {
                  displayMessage("Image could not be loaded. Make sure it is the right size.");
                }
                displayMessage("Successfully loaded image into current layer.");
              } else {
                displayMessage("No file specified. Skipping.");
              }
            } catch (IOException e) {
              displayMessage("IO error occurred. Make sure that the file path is valid. Skipping.");
            }
            break;
          case "save":
            Image currentImage = model.getImage();
            if (currentImage != null) {
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
                  displayMessage(ImageUtils
                      .write("jpeg", currentImage.toString() + ".jpeg", model.getImage()));
                } catch (IOException e) {
                  displayMessage("IO error occurred.");
                  e.printStackTrace();
                }
              }
            } else {
              displayMessage("Cannot filter empty Image.");
            }

            break;
          case "filter":
            if (model.getImage() != null) {
              if (linescan.hasNext()) {
                String filter = linescan.next();
                try {
                  model.applyFilter(filter);
                  displayMessage(filter + " applied to image.");
                } catch (IllegalStateException e) {
                  displayMessage("No image to apply filter to");
                } catch (IllegalArgumentException e) {
                  displayMessage("No such filter.");
                }
              } else {
                displayMessage("no filter specified.");
              }
            } else {
              displayMessage("Cannot filter empty layer.");
            }
            break;
          case "list":
            for (String s : model.listLayers()) {
              displayMessage(s);
            }
            break;
          case "invisible":
            if (linescan.hasNext()) {
              try {
                model.setVisibility(linescan.next(), false);
              } catch (IllegalArgumentException e) {
                displayMessage("Layer does not exist.");
              }
            } else {
              displayMessage("No layer name specified.");
            }
            break;
          case "visible":
            if (linescan.hasNext()) {
              try {
                model.setVisibility(linescan.next(), true);
              } catch (IllegalArgumentException e) {
                displayMessage("Layer does not exist.");
              }
            } else {
              displayMessage("No layer name specified.");
            }
            break;
          case "remove":
            if (linescan.hasNext()) {
              String layerToRemove = linescan.next();
              try {
                model.removeLayer(layerToRemove);
                displayMessage("\"" + layerToRemove + "\" removed.");
              } catch (IllegalArgumentException e) {
                displayMessage("Layer does not exist.");
              }

            } else {
              displayMessage("No layer specified.");
            }
            break;
          default:
            displayMessage("Unrecognized command.");
        }
      }
    }
  }

  /**
   * Sends the specified message to be displayed by the View.
   *
   * @param text the text to display.
   */
  private void displayMessage(String text) {
    try {
      this.view.giveMessage(text);
      this.view.giveMessage("\n");
    } catch (IOException e) {
      System.out.println("IO error occurred.");
    }
  }
}