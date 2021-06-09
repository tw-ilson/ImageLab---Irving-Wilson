package model.Filters;

import model.image.Image;
import model.image.SimpleImage;

/**
 * A simple filter defined by a kernel applied to all possible pixels (does not wrap).
 */
class KernelFilter implements Filter{
  private double[][] kernel;

  public KernelFilter(double[][] kernel) {
    this.kernel = kernel;
  }

  @Override
  public Image apply(Image i) {
    return null;
  }
}
