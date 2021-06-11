package hw05.TestFilters;

import model.ImageProcessorModel;
import model.SimpleImageProcessorModel;
import model.image.Image;
import org.junit.Test;

public class TestColorFilter {

  @Test
  public void testGrayScale() {
    ImageProcessorModel model = new SimpleImageProcessorModel();
    model.importImage("manhattan-small.ppm");
    model.applyFilter("greyscale");
    Image grey = model.getImageState();
    for (int x = 0; x < )
  }

  @Test
  public void testSepia() {

  }
}
