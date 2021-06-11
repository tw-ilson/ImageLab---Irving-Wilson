package model.Filters;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A singleton Accessor class for Image Filters.
 * Designed so that the static final field "filters" is the only public facing part of this class.
 */
public class FilterBuilder {
  private static Map<String, Filter> filters = initFilters();

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


  private static HashMap<String, Filter> initFilters() {
    HashMap<String, Filter> r = new HashMap<String, Filter>();
    r.put("blur", BLUR);
    r.put("sharpen", SHARPEN);
    r.put("greyscale", GREYSCALE);
    r.put("sepia", SEPIA);
    return r;
  }

  /**
   * returns the filter to be applied to the image
   *
   * @param filter
   * @return
   */
  public static Filter getFilter(String filter) {
    filters = initFilters();
    Filter f = filters.get(filter);
    Objects.requireNonNull(f);
    return f;
  }


  private static Filter createKernelFilter(double[][] kernel) {
    return new KernelFilter(kernel);
  }

  private static Filter createColorFilter(double[][] shift) {
    return new ColorFilter(shift);
  }
}
