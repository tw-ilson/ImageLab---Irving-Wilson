package hw05.TestFilters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import model.ColorUtils.Color;
import model.Filters.Filter;
import model.Filters.FilterBuilder;
import model.ImageProcessorModel;
import model.SimpleImageProcessorModel;
import model.image.Image;
import org.junit.Before;
import org.junit.Test;

public class TestColorFilter {
  ImageProcessorModel model;
  Image image;

  @Before
  public void setup() {
    model = new SimpleImageProcessorModel();
    model.importImage("manhattan-small.ppm");
    image = model.getImageState();
  }

  @Test
  public void testGrayScale() {
    Filter filter = FilterBuilder.getFilter("greyscale");
    Image grey = filter.apply(image);

    for (int i = 0; i < grey.getHeight(); i++) {
      for (int j = 0; j < grey.getWidth(); j++) {

      }
    }
  }

  @Test
  public void testSepia() {

  }
}
