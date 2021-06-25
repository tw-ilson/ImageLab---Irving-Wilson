package view;

import java.io.IOException;
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
   * @param image the image to display.
   */
  void displayImage(Image image);
}
