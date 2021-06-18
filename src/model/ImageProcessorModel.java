package model;

import model.image.Image;

/**
 * This interface represents an ImageProcessorModel, which is meant to handle the functionality
 * for editing a pre-uploaded image, and then exporting it based on the users desires.
 * A simple image editing program, with basic functionality, and layers which are self contained.
 */
public interface ImageProcessorModel extends ImageProcessorIO {

  /**
   * Gets the current state of the image.
   *
   * @return pixel array
   * @throws IllegalStateException if the pixels cannot be read from the current image
   */
  public Image getImageState() throws IllegalStateException;

  /**
   * Applys the specified filter to the current image.
   * @param filter the filter object to apply to the current working image.
   */
  public void applyFilter(String filter) throws IllegalStateException;
}
