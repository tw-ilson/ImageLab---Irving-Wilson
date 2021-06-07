package model;

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
  public int[] getImageState() throws IllegalStateException;

  public void applyFilter(Filter filter);
}
