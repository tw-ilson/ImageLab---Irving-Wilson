package model.filters;

import java.util.Objects;
import model.color.Color;
import model.color.LightColor;
import model.image.Image;
import model.image.SimpleImage;

/**
 * A simple filter defined by a kernel applied to all possible pixels (does not wrap). Applies a
 * filter, which is defined to be an odd-dimensioned matrix, by multiplying the associated squares
 * together and adding them up to produced the desired affect at each pixel.
 */
class KernelFilter implements Filter {

  private double[][] kernel;

  /**
   * Initializes the kernel filter.
   *
   * @param kernel (the filter matrix itself, represented by a 2-D array of doubles)
   */
  public KernelFilter(double[][] kernel) {
    Objects.requireNonNull(kernel);
    this.kernel = kernel;
  }

  /**
   * The apply method in the KernelFilter class multiplies the associated squares together, starting
   * from the top left of the area of the image that it wishes to cover. It then adds all of those
   * products into one value, assigning it to the desired pixel (the one which rests in the
   * middle).
   *
   * @param image (the image which is altered by the filter)
   * @return Image (the desired Image)
   * @throws IllegalStateException if the kernel dimensions are greater than that of the images
   *                               pixel array, or it the argument passed to the method is null.
   */
  @Override
  public Image apply(Image image) throws IllegalStateException {
    if (image == null) {
      throw new IllegalStateException("Cannot pass a null image to this method.");
    }
    if (kernel.length * kernel[0].length > image.pixArray().length) {
      throw new IllegalStateException("Image is too small for the filter.");
    }
    // sets it to the length of the image pixel array
    int edgeDistFloor = (int) Math.floor(kernel.length / 2);

    Color[] toEdit = image.pixArray();
    // crop, or does return obey the bounds?
    Image toReturn = new SimpleImage(toEdit, image.getWidth(), image.getHeight());

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
            sumGreen += (double) cur.getGreen() * kernel[ky][kx];
            sumBlue += (double) cur.getBlue() * kernel[ky][kx];
            kx++;
          }
          kx = 0;
          ky++;
        }
//        // clamping
//        if (sumRed > MAX) {
//          sumRed = MAX;
//        } else if (sumRed < MIN) {
//          sumRed = MIN;
//        }
//        if (sumGreen > MAX) {
//          sumGreen = MAX;
//        } else if (sumGreen < MIN) {
//          sumGreen = MIN;
//        }
//        if (sumBlue > MAX) {
//          sumBlue = MAX;
//        } else if (sumBlue < MIN) {
//          sumBlue = MIN;
//        }
        Color toAdd = new LightColor(Filter.clamp((int) sumRed), Filter.clamp((int) sumGreen),
            Filter.clamp((int) sumBlue));
        toEdit[image.getWidth() * y + x] = toAdd;
      }
    }
    return toReturn;
  }
}

