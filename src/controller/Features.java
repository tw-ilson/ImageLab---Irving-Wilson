package controller;

import java.io.IOException;
import view.ImageProcessorView;

/**
 * This interface is meant to represent a general set of features that an Image Processing application
 * shall support.
 */
public interface Features {

  enum IOAction { IMPORT, EXPORT };
  enum FilterAction { BLUR, SHARPEN, GREYSCALE, SEPIA, CUSTOM };
  enum LayerAction { ADD, REMOVE, VISIBLE, INVISIBLE, SETCURRENT };

  /**
   * Handles the IO features of the Image processing Application. see {@link IOAction}.
   * @param action the IO action to perform.
   * @param fileName the file on which to perform the IO operation.
   * @throws IllegalStateException if application is not yet initialized
   * @throws IOException if an IO error occurs
   */
  void handleIO(IOAction action, String fileName) throws IOException, IllegalStateException;

  /**
   * Handles the Image filtering features supported by the application. see {@link FilterAction}.
   * @param action the filtering action to perform.
   * @throws IllegalStateException if application is not yet initialized.
   */
  void handleFilter(FilterAction action) throws IllegalStateException;

  /**
   * Handles the features of the program related to the management of a specific layer. see {@link LayerAction}
   * @param action the layer action to perform.
   * @throws IllegalStateException if application is not yet initialized.
   */
  void handleLayers(LayerAction action, String layerName) throws IllegalStateException;

  /**
   * Handles the features of the program related to the management of all layers.
   * @param action the action to execute
   * @return string representation of layers
   * @throws IllegalStateException
   */
  String handleLayers(LayerAction action) throws IllegalStateException;

  /**
   * Enables use of a view for this Application.
   * @param view the view to use
   */
  void addView(ImageProcessorView view);

  /**
   * Utilizes the main functionality of user interface
   * @throws IllegalStateException if the application is not yet initialized.
   */
  void show() throws IllegalStateException;
}
