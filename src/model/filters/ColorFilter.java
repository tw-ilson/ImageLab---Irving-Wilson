package model.filters;

import model.color.Color;
import model.color.LightColor;
import model.image.Image;
import model.image.SimpleImage;

/**
 * A type of filter that operates on the colors of individual pixels.
 */
public class ColorFilter implements Filter {

  private double[][] shift;

  /**
   * Constructs a color filter.
   *
   * @param shift the color shift matrix.
   */
  public ColorFilter(double[][] shift) {
    this.shift = shift;
  }

  @Override
  public Image apply(Image image) {
    if (image == null) {
      throw new IllegalArgumentException("Cannot pass a null image.");
    }
    Color[] toEdit = image.pixArray();
    Image alteredImage = new SimpleImage(toEdit, image.getWidth(), image.getHeight());

    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        Color cur = image.getPixel(i, j);

        int newRed = (int) (cur.getRed() * shift[0][0] + cur.getGreen() * shift[0][1]
            + cur.getBlue() * shift[0][2]);
        int newGreen = (int) (cur.getRed() * shift[1][0] + cur.getGreen() * shift[1][1]
            + cur.getBlue() * shift[1][2]);
        int newBlue = (int) (cur.getRed() * shift[2][0] + cur.getGreen() * shift[2][1]
            + cur.getBlue() * shift[2][2]);
//        // clamping
//        if (newRed > MAX) {
//          newRed = MAX;
//        } else if (newRed < MIN) {
//          newRed = MIN;
//        }
//        if (newGreen > MAX) {
//          newGreen = MAX;
//        } else if (newGreen < MIN) {
//          newGreen = MIN;
//        }
//        if (newBlue > MAX) {
//          newBlue = MAX;
//        } else if (newBlue < MIN) {
//          newBlue = MIN;
//        }
        toEdit[image.getWidth() * j + i] = new LightColor(Filter.clamp(newRed),
            Filter.clamp(newGreen), Filter.clamp(newBlue));
      }
    }
    return alteredImage;
  }
}
