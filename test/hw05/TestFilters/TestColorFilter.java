package hw05.TestFilters;

import model.ImageProcessorModel;
import model.SimpleImageProcessorModel;
import org.junit.Test;

public class TestColorFilter {

  @Test
  public void testGrayScale() {
    ImageProcessorModel model = new SimpleImageProcessorModel();
    model.importImage("");
  }

  @Test
  public void testSepia() {

  }
}
