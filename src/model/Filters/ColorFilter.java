package model.Filters;

import model.ColorUtils.Color;
import model.ColorUtils.LightColor;
import model.image.Image;
import model.image.SimpleImage;

/**
 * A type of filter that operates on the colors of individual pixels.
 */
public class ColorFilter implements Filter {

  private static double[][] shift;

  public ColorFilter(double[][] shift) {
    this.shift = shift;
  }

  @Override
  public Image apply(Image image) {
    Image alteredImage = new SimpleImage(image);
    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        Color cur = image.getPixel(i, j);
        int newRed = (int) (cur.getRed() * shift[0][0] + cur.getGreen() * shift[0][1]
            + cur.getBlue() * shift[0][2]);
        int newGreen = (int) (cur.getRed() * shift[1][0] + cur.getGreen() * shift[1][1]
            + cur.getBlue() * shift[1][2]);
        int newBlue = (int) (cur.getRed() * shift[2][0] + cur.getGreen() * shift[2][1]
            + cur.getBlue() * shift[2][2]);
        // clamping
        if (newRed > MAX) {
          newRed = MAX;
        } else if (newRed < MIN) {
          newRed = MIN;
        }
        if (newGreen > MAX) {
          newGreen = MAX;
        } else if (newGreen < MIN) {
          newGreen = MIN;
        }
        if (newBlue > MAX) {
          newBlue = MAX;
        } else if (newBlue < MIN) {
          newBlue = MIN;
        }
        alteredImage.setPixel(i, j, new LightColor(newRed, newGreen, newBlue));
      }
    }
    return alteredImage;
  }
}
