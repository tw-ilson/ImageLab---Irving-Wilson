package model.filters;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Builder type for Filter objects. Supports the creation of arbitrary filters either by a color
 * transformation or kernel mapping. Commonly used filters can be stored as constants and accessed
 * with the getFilter method.
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
          {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
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


  /**
   * Constructs a filterBuilder. adds all filters to the hashmap.
   */
  public FilterBuilder() {
    filters.put("blur", BLUR);
    filters.put("sharpen", SHARPEN);
    filters.put("greyscale", GREYSCALE);
    filters.put("sepia", SEPIA);
  }

  /**
   * returns the filter to be applied to the image.
   *
   * @param filter the name of the filter to get.
   * @return the requested filter.
   */
  public Filter getFilter(String filter) {
    return filters.get(filter);
  }

  /**
   * Produces a new kernel filter instance.
   *
   * @param kernel the kernel matrix to use for the new kernelfilter object
   * @return the Filter produced.
   */
  public static Filter createKernelFilter(double[][] kernel) {
    return new KernelFilter(kernel);
  }

  /**
   * Produces a new Color filter instance.
   *
   * @param shift the color shift matrix to create the new Filter with
   * @return the Filter produced.
   */
  public static Filter createColorFilter(double[][] shift) {
    return new ColorFilter(shift);
  }

  /**
   * Does FilterBuilder have the requested filter?.
   *
   * @param filter the requested filter
   * @return whether or not it has it.
   */
  public boolean hasFilter(String filter) {
    Objects.requireNonNull(filter);
    return filters.containsKey(filter);
  }

}
