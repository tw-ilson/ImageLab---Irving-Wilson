package model.Filters;

import java.util.HashMap;
import java.util.Map;

/**
 * Creates filters.
 */
public class FilterBuilder {

  private static Map<String, Filter> filters = new HashMap<>();
  private static Filter BLUR = createKernelFilter(
      new double[][]{
          {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0},
          {1.0 / 8.0, 1.0 / 4.0, 1.0 / 8.0},
          {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0}});
  private static Filter SHARPEN = createKernelFilter(
      new double[][]{
          {-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0},
          {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0/ 8.0},
          {-1.0 / 8.0, 1.0 / 4.0, 1.0, 1.0 / 4.0, -1.0 / 8.0},
          {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
          {-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0}});
  private static Filter GREYSCALE = createColorFilter(
      new double[][]{
          {0.212, .7152, .0722},
          {0.212, .7152, .0722},
          {0.212, .7152, .0722}});
  private static Filter SEPIA = createColorFilter(
      new double[][]{
          {0.393, 0.769, 0.189},
          {0.349, 0.686, 0.189},
          {0.272, 0.534, 0.131}});


  public FilterBuilder() {
    filters.put("blur", BLUR);
    filters.put("sharpen", SHARPEN);
    filters.put("greyscale", GREYSCALE);
    filters.put("sepia", SEPIA);
  }

  /**
   * returns the filter to be applied to the image
   *
   * @param filter
   * @return
   */
  public static Filter getFilter(String filter) {
    return filters.get(filter);
  }


  private static Filter createKernelFilter(double[][] kernel) {

    return new KernelFilter(kernel);
  }

  private static Filter createColorFilter(double[][] shift) {
    return new ColorFilter(shift);
  }
}
