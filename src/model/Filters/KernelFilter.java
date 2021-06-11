package model.Filters;

import java.util.Objects;
import model.ColorUtils.Color;
import model.ColorUtils.LightColor;
import model.image.Image;
import model.image.SimpleImage;

/**
 * A simple filter defined by a kernel applied to all possible pixels (does not wrap).
 */
class KernelFilter implements Filter {

  private double[][] kernel;

  public KernelFilter(double[][] kernel) {
    this.kernel = kernel;
  }

  @Override
  public Image apply(Image image) throws IllegalStateException{
    if (kernel.length * kernel[0].length > image.pixArray().length) {
      throw new IllegalStateException("Image is too small for the filter.");
    }
    Objects.requireNonNull(image);

    // sets it to the length of the image pixel array
    int edgeDistFloor = (int) Math.floor(kernel.length / 2);

    // crop, or does return obey the bounds?
    Image toReturn = new SimpleImage(image);

    for (int x = edgeDistFloor; x < image.getWidth() - edgeDistFloor; x++) {
      for (int y = edgeDistFloor; y < image.getHeight() - edgeDistFloor; y++) {
          double sumRed = 0;
          double sumGreen = 0;
          double sumBlue = 0;

          int kx = 0;
          int ky = 0;
          for (int i = y - edgeDistFloor; i <= y + edgeDistFloor; i++) {
            for (int j = x - edgeDistFloor; j <= x + edgeDistFloor; j++) {
              Color cur = image.getPixel(j, i);
              sumRed += (double) cur.getRed() * kernel[ky][kx];
              sumGreen += (double)cur.getGreen() * kernel[ky][kx];
              sumBlue += (double) cur.getBlue() * kernel[ky][kx];
              kx++;
            }
            kx = 0;
            ky++;
          }

          // clamping
          if (sumRed > MAX) {
            sumRed = MAX;
          } else if (sumRed < MIN) {
            sumRed = MIN;
          }
          if (sumGreen > MAX) {
            sumGreen = MAX;
          } else if (sumGreen < MIN) {
            sumGreen = MIN;
          }
          if (sumBlue > MAX) {
            sumBlue = MAX;
          } else if (sumBlue < MIN) {
            sumBlue = MIN;
          }
          Color toAdd = new LightColor((int) sumRed, (int) sumGreen, (int) sumBlue);
          toReturn.setPixel(x + edgeDistFloor, y + edgeDistFloor, toAdd);
      }
    }
    return toReturn;
  }
}

