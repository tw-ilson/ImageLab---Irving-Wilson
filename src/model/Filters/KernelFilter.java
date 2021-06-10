package model.Filters;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import model.ColorUtils.Color;
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
  public Image apply(Image i) throws IllegalStateException{
    if (kernel.length * kernel.length > i.pixArray().length) {
      throw new IllegalStateException("Image is too small for the filter.");
    }
    Objects.requireNonNull(i);

    // sets it to the length of the image pixel array
    Color[] toEdit = new Color[i.pixArray().length];

    int edgeDist = (int) Math.ceil(kernel.length / 2);
    int widthBound = (i.getWidth() % kernel.length) * kernel.length;
    int heightBound = (i.getHeight() % kernel.length) * kernel.length;
    Color[] finalColors = new Color[widthBound * heightBound];

    // crop, or does return obey the bounds?
    Image toReturn = new SimpleImage(toEdit, i.getWidth(), i.getHeight());

    for (int x = 0; x < i.getWidth(); x++) {
      for (int y = 0; y < i.getHeight(); y++) {

        // checks if the complete "square" of pixels legitimately resides within the scope
        if (x + kernel.length < i.getWidth() && y + kernel.length < i.getHeight()) {
          Color toAdd = new Color(0);
          double sumRed = 0;
          double sumGreen = 0;
          double sumBlue = 0;
          // moves through the kernel itself
          for (int c = 0; c < kernel.length; c++) {
            for (int g = 0; g < kernel.length; g++) {
              sumRed += i.getPixel(x + c, y + g).getRed() * kernel[c][g];
              sumGreen += i.getPixel(x + c, y + g).getGreen() * kernel[c][g];
              sumBlue += i.getPixel(x + c, y + g).getBlue() * kernel[c][g];
            }
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
          toAdd = new Color((int) sumRed, (int) sumGreen, (int) sumBlue);
          toReturn.setPixel(x + edgeDist, y + edgeDist, toAdd);
        } else {
          Color toAdd = new Color(0, 0, 0);
          toReturn.setPixel(x, y, toAdd);
        }
      }
    }
    return toReturn;
  }
}

