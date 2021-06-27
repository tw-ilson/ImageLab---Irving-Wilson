package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import javax.imageio.ImageIO;
import model.ImageProcessorLayerModel;
import model.color.Color;
import model.color.LightColor;
import model.image.Image;
import model.image.SimpleImage;
import view.ImageProcessorView;

public class StandardFeatures implements Features {

  ImageProcessorLayerModel model;
  ImageProcessorView view;
  String current;

  public StandardFeatures(ImageProcessorLayerModel model) {
    Objects.requireNonNull(model);
    this.model = model;
  }

  @Override
  public void handleIO(IOAction action, String fileName) {
    if (model == null || view == null) {
      throw new IllegalStateException("Application not initialized");
    }
    if (fileName == null) {
      displayMessage("no File specified.");
    }
    switch (action) {
      case IMPORT:
        try {
          this.model.editCurrentLayer(ImageUtils.read(fileName));
        } catch (IllegalStateException e) {
          displayMessage("No layers created.");
        } catch (IllegalArgumentException e) {
          displayMessage("Image is not the right size.");
        } catch (IOException e) {
          displayMessage("IO error occurred on import.");
        }
        break;
      case EXPORT:
        Image currentImage = model.getImage();
        try {
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
        } catch (IOException e) {
          displayMessage("IO error occurred on file export.");
        }
        break;
      case SAVEALL:
        ArrayList<Image> allImages = model.allVisibleImages();
        if (allImages.size() == 0) {
          displayMessage("No viable images to save.");
        }
        for (int i = 0; i < allImages.size(); i++) {
          Image toProcess = allImages.get(i);
          if (fileName != null) {
            if (fileName.contains(".")) {
              String ext = fileName.substring(fileName.lastIndexOf('.'));
              if (Arrays.stream(ImageIO.getWriterFormatNames())
                  .anyMatch(name -> ext.substring(1).equals(name)) || ext.equals(".ppm")) {
                try {
                  displayMessage(
                      ImageUtils.write(ext.substring(1), i + fileName, toProcess));
                } catch (IOException e) {
                  e.printStackTrace();
                }
              } else {
                displayMessage("Cannot recognize file type. Skipping.");
              }
            } else {
              //defaults to jpeg
              try {
                displayMessage(
                    ImageUtils.write("jpeg", i + fileName + i + ".jpeg", model.getImage()));
              } catch (IOException e) {
                e.printStackTrace();
              }
            }
          } else {
            try {
              displayMessage(ImageUtils
                  .write("jpeg", toProcess.toString() + i + ".jpeg", model.getImage()));
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
        break;
      case LOADALL:
        for (int i = 0; i < model.listLayers().length; i++) {
          try {
            this.model.setCurrentLayer(model.listLayers()[i]);
            model.editCurrentLayer(ImageUtils.read(fileName));
          } catch (IllegalStateException e) {
            displayMessage("No layers created.");
          } catch (IllegalArgumentException e) {
            displayMessage("Image is not the right size.");
          } catch (IOException e) {
            displayMessage("IO error occurred on import.");
          }
        }
        break;
      case BATCH:
        // so run the commands from the other controller, which will modify the model
        // that is passed, then runs the commands updating the model
        Readable toRead = null;
        try {
          toRead = new FileReader(fileName);
        } catch (FileNotFoundException e) {
          displayMessage("File not found");
        }
        ImageProcessorController controller =
            new SimpleImageController(model, toRead, new StringBuilder());
        controller.run();

        break;
    }

  }

  @Override
  public void handleFilter(FilterAction action) {
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
  public void handleLayers(LayerAction action, String layerName) {
    switch (action) {
      case ADD:
        try {
          model.createLayer(layerName);
          model.setCurrentLayer(layerName);
          displayMessage("Layer \"" + layerName + "\" created");
        } catch (IllegalArgumentException e) {
          displayMessage("Layer with the same name already exists.");
        }
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
      case SETCURRENT:
        try {
          model.setCurrentLayer(layerName);
        } catch (IllegalArgumentException j) {
          displayMessage("Layer does not exist");
        }
        break;
    }
  }

  @Override
  public void mosaic(int nSeeds) {
    try {
      model.mosaic(nSeeds);
    } catch (IllegalStateException e) {
      displayMessage("No image to apply filter to.");
    }
  }

  @Override
  public void resize(int w, int h) {
    try {
      model.resize(w, h);
    } catch (IllegalArgumentException iea) {
      displayMessage("Invalid dimensions provided.");
    } catch (IllegalStateException e) {
      displayMessage("No Image to resize.");
    }
  }


  @Override
  public void addView(ImageProcessorView view) {
    Objects.requireNonNull(view);
    this.view = view;
  }

  @Override
  public void show() {
    Color[] white = new LightColor[1];
    white[0] = new LightColor(0);
    try {
      Image toPass = model.getTopMostVisible();
      view.displayImage(toPass);
    } catch (IllegalStateException a) {
      view.displayImage(new SimpleImage(white, 1, 1));
    }
  }


  /**
   * Sends the specified message to be displayed by the View.
   *
   * @param text the text to display.
   */
  private void displayMessage(String text) {
    try {
      view.giveMessage(text + "\n");
    } catch (IOException e) {
      System.out.println("IO error occurred.");
    }
  }
}
