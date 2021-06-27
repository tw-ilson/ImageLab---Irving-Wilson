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
import view.JFrameView;

/**
 * StandardFeatures is a class which represents the functionality that the user can enact upon the
 * data through the model, by interacting with the GUI on the other end. This class serves as a node
 * through which the model can communicate with the view, and vice versa.
 */
public class StandardFeatures implements Features {

  private ImageProcessorLayerModel model;
  private ImageProcessorView view;

  /**
   * Initializes a StandardFeatures Object.
   *
   * @param model (the model which the StandardFeatures Object will process data through).
   */
  public StandardFeatures(ImageProcessorLayerModel model) {
    Objects.requireNonNull(model);
    this.model = model;
  }

  /**
   * Alternate constructor which can be fed a view. Can be a textual view, or a JFrameView.
   *
   * @param model (the model which standardFeatures will access the data through)
   * @param view  (the graphical user interface through which the user can interact with the data)
   */
  public StandardFeatures(ImageProcessorLayerModel model, ImageProcessorView view) {
    Objects.requireNonNull(model);
    this.model = model;
    this.view = view;
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
                      ImageUtils.write(ext.substring(1), fileName + i, toProcess));
                } catch (IOException e) {
                  e.printStackTrace();
                }
              } else {
                displayMessage("Cannot recognize file type. Skipping.");
              }
            } else {
              try {
                displayMessage(
                    ImageUtils.write("jpeg", fileName + i + ".jpeg", model.getImage()));
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
  public void listLayers() {
    try {
      view.displayLayers(model.listLayers());
    } catch (IOException e) {
      this.displayMessage("An IO error occurred!");
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
      try {
        view.displayImage(toPass);
      } catch (IOException e) {
        displayMessage("IO error occurred.");
      }
    } catch (IllegalStateException a) {
      try {
        view.displayImage(new SimpleImage(white, 1, 1));
      } catch (IOException e) {
        displayMessage("IO error occurred.");
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
      view.giveMessage(text + "\n");
    } catch (IOException e) {
      System.out.println("IO error occurred.");
    }
  }
}
