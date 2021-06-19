package model;


import model.image.Image;

/**
 * This interface represents an ImageProcessorModel, which is meant to handle the functionality for
 * editing a pre-uploaded image, and then exporting it based on the users desires. A simple image
 * editing program, with basic functionality, and layers which are self contained.
 */
public interface ImageProcessorModel {

  /**
   * Gets the state of the current image.
   *
   * @return pixel array
   * @throws IllegalStateException if the pixels cannot be read from the current image
   */
  public Image getImage() throws IllegalStateException;

  /**
   * Gets the width of the image being processed.
   *
   * @return the width of the image
   * @throws IllegalStateException
   */
  public int getImageWidth() throws IllegalStateException;

  /**
   * Gets the height of the image being processed.
   *
   * @return
   * @throws IllegalStateException
   */
  public int getImageHeight() throws IllegalStateException;

  /**
   * Applys the specified filter to the current image.
   *
   * @param filter the filter object to apply to the current working image.
   */
  public void applyFilter(String filter) throws IllegalStateException;


}
