package controller;

/**
 * A controller for the image processing program represented by the model. Takes input in the form
 * of a Readable. The program is started with run().
 */
public interface ImageProcessorController {
  /**
   * Start and run the application, and begin to work on the image. The method returns only when the
   * user quits.
   *
   * @throws IllegalArgumentException if the written appendable or the reading from the provided
   *                                  readable fails.
   * @throws IllegalStateException    if model or the image provided to it are null
   */
  void run() throws IllegalArgumentException, IllegalStateException;

}
