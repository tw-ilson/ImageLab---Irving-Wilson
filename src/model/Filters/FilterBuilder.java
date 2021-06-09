package model.Filters;

import java.util.HashMap;
import java.util.Map;

/**
 * Creates filters.
 */
public class FilterBuilder {

  private static Map<String, Filter> filters = new HashMap<>();
  private static Filter BLUR = createKernelFilter(
      new double[][]{{1 / 16, 1 / 8, 1 / 16},
          {1 / 8, 1 / 4, 1 / 8},
          {1 / 16, 1 / 8, 1 / 16}});
  private static Filter SHARPEN;
  private static Filter GRAYSCALE = createColorFilter(new double[][]{{0.212, .7152,.0722}});
  private static Filter SEPIA = createKernelFilter(new double[][]{{0.393, 0.769, 0.189},
      {0.349, 0.686, 0.189},
      {0.272, 0.534, 0.131}});


  public FilterBuilder() {
  }

  private static Filter createKernelFilter(double[][] kernel) {
    return new KernelFilter(kernel);
  }

  private static Filter createColorFilter(double[][] shift) {
  }
}
