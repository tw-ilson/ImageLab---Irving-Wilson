package model.filters;

import java.util.function.Function;
import model.image.Image;

/**
 * A type of filter that is applied to an image.
 */
public interface Filter extends Function<Image, Image> {

  /**
   * Applys the filter to the provided image and returns the new, filtered image.
   * @param i the source image to filter.
   * @return the newly filtered image.
   */
  public Image apply(Image i);

  int MAX = 255;
  int MIN = 0;

}
