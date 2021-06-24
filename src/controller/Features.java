package controller;

/**
 * This interface is meant to represent a general set of features that an Image Processing application
 * shall support.
 */
public interface Features {
  enum IOAction { IMPORT, EXPORT };
  enum FilterAction { BLUR, SHARPEN, GREYSCALE, SEPIA, CUSTOM };
  enum LayerAction { ADD, REMOVE, VISIBILITY };

  /**
   * Handles the IO features of the Image processing Application. see {@link IOAction}.
   * @param action the IO action to perform.
   * @param fileName the file on which to perform the IO operation.
   */
  void handleIO(IOAction action, String fileName);

  /**
   * Handles the Image filtering features supported by the application. see {@link FilterAction}.
   * @param action the filtering action to perform.
   */
  void handleFilter(FilterAction action);

  /**
   * Handles the features of the program related to the management of multiple layers. see {@link LayerAction}
   * @param action the layer action to perform.
   */
  void handleLayers(LayerAction action);
}
