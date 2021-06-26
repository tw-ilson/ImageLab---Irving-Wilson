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
  public Image getImage() throws IllegalArgumentException;

  /**
   * Gets the width of the image being processed.
   *
   * @return the width of the image
   * @throws IllegalStateException if no image is initialized
   */
  public int getImageWidth() throws IllegalStateException;

  /**
   * Gets the height of the image being processed.
   *
   * @return the height of the image stored at this model.
   * @throws IllegalStateException if no image is initialized.
   */
  public int getImageHeight() throws IllegalStateException;

  /**
   * Applys the specified filter to the current image.
   *
   * @param filter the filter object to apply to the current working image.
   * @throws IllegalStateException if no image is initialized
   */
  public void applyFilter(String filter) throws IllegalStateException;

  /**
   * Resizes the image(s);
   *
   * @param w the resulting end width
   * @param h the resulting end height
   * @throws IllegalStateException    if the image has not been initialized
   * @throws IllegalArgumentException if the width and height supplied is negative or greater than
   *                                  the current size.
   */
  public void resize(int w, int h) throws IllegalStateException, IllegalArgumentException;

  /**
   * Applies the "mosaic" affect to the current image in the layeredImage. The number of seeds
   * dictates how abstract the image is, meaning how many kernels the remaining pixels are
   * clustered around.
   * @param numSeeds (the number of kernels to cluster the image around)
   */
  public void mosaic(int numSeeds);
}
