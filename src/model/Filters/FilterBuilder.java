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
  private static Filter SEPIA;

  public FilterBuilder() {

  }

  private static Filter createKernelFilter(double[][] kernel) {
    return new KernelFilter(kernel);
  }

  private static Filter createColorFilter(double[][] shift) {

  }
}
