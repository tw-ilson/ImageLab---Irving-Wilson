package view;

import java.io.IOException;

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
}
