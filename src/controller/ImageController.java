package controller;

public interface ImageController {

  /**
   * Start the application, and begin to work on the image. The method returns only when the user
   * quits.
   *
   * @throws IllegalArgumentException if the written appendable or the reading from the provided
   *                                  readable fails.
   * @throws IllegalStateException if model or the image provided to it are null
   */
  void appOpen() throws IllegalArgumentException, IllegalStateException;

}
