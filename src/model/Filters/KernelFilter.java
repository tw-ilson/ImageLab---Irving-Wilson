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
  public Image apply(Image i) {
    Objects.requireNonNull(i);
    Color[] toEdit = i.pixArray();

    int edgeDist = (int) Math.ceil(kernel.length / 2);
    int widthBound = (i.getWidth() % kernel.length) * kernel.length;
    int heightBound = (i.getHeight() % kernel.length) * kernel.length;

    Color[] finalColors = new Color[widthBound * heightBound];
    Image toReturn = new SimpleImage(finalColors, widthBound, heightBound);


    for (int x = 0; x < i.getWidth(); x++) {
      for (int y = 0; y < i.getHeight(); y++) {
        if (x + kernel.length <= i.getWidth() && y + kernel.length <= i.getHeight()) {
          Color toAdd = new Color(0);
          int sumRed = 0;
          int sumGreen = 0;
          int sumBlue = 0;
          for (int c = 0; c < kernel.length; c++) {
            for (int g = 0; g < kernel.length; g++) {
              sumRed += i.getPixel(x + c, y + g).getRed() * kernel[c][g];
              sumGreen += i.getPixel(x + c, y + g).getGreen() * kernel[c][g];
              sumBlue += i.getPixel(x + c, y + g).getBlue() * kernel[c][g];
            }
          }
          // possibly abstract (duplicate code with color filter)
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
          toAdd = new Color(sumRed, sumGreen, sumBlue);
          toReturn.setPixel(x + edgeDist, y + edgeDist, toAdd);
        } else {
          // set color of pixel to black?
        }

      }
    }
    return toReturn;
  }



  //Old code
  /*@Override
  public Image apply(Image i) {
    Objects.requireNonNull(i);
    Color[] toEdit = i.pixArray();
    int[] reds = Arrays.stream(toEdit).map((c) -> c.getRed());
    int edgeDist = (kernel.length / 2);
    // apply at each individual pixel, returning the new sum for that pixel=

    for (int x = 0; x < i.getWidth(); x++) {
      for (int y = 0; y < i.getHeight(); y++) {
        if( x - edgeDist < MIN || x + edgeDist > MAX
            || y - edgeDist < MIN|| y + edgeDist > MAX) {
          int sum = 0;
          for (int k = 0; k < kernel.length; k++) {
            for (int j = 0; j < kernel[0].length; j++) {
              sum += toEdit[q]
            }
          }
        }
        Color toAdd = new Color(total);
        toEdit[x] = toAdd;
      }
    }

    return null;
  }*/
}

