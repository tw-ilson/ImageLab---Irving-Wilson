package model.filters;

import java.util.function.Function;
import model.image.Image;

/**
 * This interface represents a filter which can be applied to an individual image. The filters fall
 * primarily into two types, the Kernel Filter and the Color Filter. The kernel filters focus on
 * changing the makeup of the image (blurring, sharpening) by applying a kernel, which is an odd
 * number dimensioned matrix that alters the middle pixel rbg values. The color filter applies a
 * simple alteration to each pixel, not focusing on the middle value.
 */
public interface Filter extends Function<Image, Image> {

  /**
   * Applys the filter to the provided image and returns the new, filtered image.
   *
   * @param i the source image to filter.
   * @return the newly filtered image.
   */
  public Image apply(Image i);

  int MAX = 255;
  int MIN = 0;

  /**
   * "Clamps" the value of the provided color to be within the Minimum and maximum allowed value for
   * a color channel.
   *
   * @param color the color to clamp
   * @return the clamped value.
   */
  static int clamp(int color) {
    if (color > MAX) {
      return MAX;
    } else if (color < MIN) {
      return MIN;
    } else {
      return color;
    }
  }

}
