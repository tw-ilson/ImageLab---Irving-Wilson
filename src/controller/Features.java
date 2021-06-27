package controller;

import java.io.IOException;
import view.ImageProcessorView;

/**
 * This interface is meant to represent a general set of features that an Image Processing
 * application shall support.
 */
public interface Features {

  enum IOAction {IMPORT, EXPORT, SAVEALL, BATCH, LOADALL}

  ;

  enum FilterAction {BLUR, SHARPEN, GREYSCALE, SEPIA, CUSTOM}

  ;

  enum LayerAction {ADD, REMOVE, VISIBLE, INVISIBLE, SETCURRENT}

  ;

  /**
   * Handles the IO features of the Image processing Application. see {@link IOAction}.
   *
   * @param action   the IO action to perform.
   * @param fileName the file on which to perform the IO operation.
   */
  void handleIO(IOAction action, String fileName);

  /**
   * Handles the Image filtering features supported by the application. see {@link FilterAction}.
   */
  void handleFilter(FilterAction action);

  /**
   * Handles the features of the program related to the management of a specific layer. see {@link
   * LayerAction}
   *
   * @param action the layer action to perform.
   */
  void handleLayers(LayerAction action, String layerName);

  /**
   * Utilizes the view to list the current working layers of the image.
   */
  void listLayers();

  /**
   * Makes a mosaic of the current image with the requested number of seeds.
   *
   * @param nSeeds the number of "tiles" that the mosaic will have
   */
  void mosaic(int nSeeds);

  /**
   * Resizes the image model.
   *
   * @param w the new width
   * @param h the new height
   */
  void resize(int w, int h);


  /**
   * Enables use of a view for this Application.
   *
   * @param view the view to use
   */
  void addView(ImageProcessorView view);

  /**
   * Utilizes the main functionality of user interface
   */
  void show();
}
