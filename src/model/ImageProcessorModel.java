package model;

import model.image.Image;

/**
 * The model for applying Filters to an image.
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
