package model.Filters;

import java.util.Arrays;
import java.util.Objects;
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
  }
}
