package view;

import java.io.IOException;
import java.util.List;
import model.image.Image;

/**
 * A visual representation of the workings of an Image processing model.
 */
public interface ImageProcessorView {


  /**
   * gives the program user the specified message.
   *
   * @param text the message to display
   */
  void giveMessage(String text) throws IOException;

  /**
   * Passes this Image to the view to be displayed.
   *
   * @param image the image to display.
   * @throws IOException if an IO error occurs.
   */
  void displayImage(Image image) throws IOException;

  /**
   * Displays all currently working layers.
   *
   * @param layerNames the names of the layers to display. Should be all layers in the associated
   *                   model.
   * @throws IOException if an IO error occurs.
   */
  void displayLayers(String[] layerNames) throws IOException;
}
