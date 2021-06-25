package controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import javax.imageio.ImageIO;
import model.ImageProcessorLayerModel;
import model.image.Image;
import view.ImageProcessorView;

public class StandardFeatures implements Features {

  ImageProcessorLayerModel model;
  ImageProcessorView view;

  public StandardFeatures(ImageProcessorLayerModel model) {
    Objects.requireNonNull(model);
    this.model = model;
  }

  @Override
  public void handleIO(IOAction action, String fileName) throws IOException, IllegalStateException {
    if ( model == null || view == null) {
      throw new IllegalStateException("Application not initialized");
    }
    if (fileName == null ) {
       displayMessage("no File specified.");
    }
    switch (action) {
      case IMPORT:
        this.model.editCurrentLayer(ImageUtils.read(fileName));
        break;
      case EXPORT:
        Image currentImage = model.getImage();
        if (currentImage != null) {
          if (fileName != null) {
            if (fileName.contains(".")) {
              String ext = fileName.substring(fileName.lastIndexOf('.'));
              if (Arrays.stream(ImageIO.getWriterFormatNames())
                  .anyMatch(name -> ext.substring(1).equals(name)) || ext.equals(".ppm")) {
                displayMessage(
                    ImageUtils.write(ext.substring(1), fileName, currentImage));
              } else {
                displayMessage("Cannot recognize file type. Skipping.");
              }
            } else {
              //defaults to jpeg
              displayMessage(
                  ImageUtils.write("jpeg", fileName + ".jpeg", model.getImage()));
            }
          } else {
            displayMessage(ImageUtils
                .write("jpeg", currentImage.toString() + ".jpeg", model.getImage()));
          }
        } else {
          displayMessage("Cannot filter empty Image.");
        }
    }
  }

  @Override
  public void handleFilter(FilterAction action) throws IllegalStateException {
    String filter = action.toString().toLowerCase();
    try {
      model.applyFilter(filter);
      displayMessage(filter + " applied to image.");
    } catch (IllegalStateException e) {
      displayMessage("No image to apply filter to");
    } catch (IllegalArgumentException e) {
      displayMessage("No such filter.");
    }
  }

  @Override
  public void handleLayers(LayerAction action, String layerName) throws IllegalStateException{
    switch (action) {
      case ADD:
        model.createLayer(layerName);
        model.setCurrentLayer(layerName);
        displayMessage("Layer \"" + layerName + "\" created");
        break;
      case REMOVE:
        try {
          model.removeLayer(layerName);
          displayMessage("\"" + layerName + "\" removed.");
        } catch (IllegalArgumentException e) {
          displayMessage("Layer does not exist.");
        }
        break;
      case VISIBLE:
        try {
          model.setVisibility(layerName, true);
        } catch (IllegalArgumentException e) {
          displayMessage("Layer does not exist.");
        }
        break;
      case INVISIBLE:
        try {
          model.setVisibility(layerName, false);
        } catch (IllegalArgumentException e) {
          displayMessage("Layer does not exist.");
        }
        break;
    }
  }

  @Override
  public void addView(ImageProcessorView view) {
    Objects.requireNonNull(view);
    this.view = view;
  }

  @Override
  public void show() throws IllegalStateException{
    try {
        view.displayImage(model.getImage());
    } catch (IllegalArgumentException e) {

    }
  }

  /**
   * Sends the specified message to be displayed by the View.
   *
   * @param text the text to display.
   */
  private void displayMessage(String text) {
    try {
      view.giveMessage(text);
      view.giveMessage("\n");
    } catch (IOException e) {
      System.out.println("IO error occurred.");
    }
  }
}
