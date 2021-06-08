package model;

import java.awt.Color;
import model.IO.ImageProcessorIO;
import model.image.Image;

/**
 * The model for applying Filters to an image.
 */
public interface ImageProcessorModel extends ImageProcessorIO {

  /**
   * Gets the current image state as an array of hexadecimal integers representing the pixels of the
   * image
   *
   * @return pixel array
   * @throws IllegalStateException if the pixels cannot be read from the current image
   */
  public Color[] getImageState() throws IllegalStateException;

  /**
   * Applys the specified filter to the current image.
   * @param filter
   */
  public void applyFilter(String filter, Image i);
}
