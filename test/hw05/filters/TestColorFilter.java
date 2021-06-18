package hw05.filters;

import static org.junit.Assert.assertEquals;

import model.color.Color;
import model.ImageProcessorModel;
import model.SimpleImageModel;
import model.image.Image;
import org.junit.Test;

/**
 * Tests that color filter works properly by checking grayscale and sepia.
 */
public class TestColorFilter {

  @Test
  public void testGrayScale() {
    ImageProcessorModel model = new SimpleImageModel();
    model.importImage("bay.ppm");
    Image original = model.getImagePixels();
    model.applyFilter("greyscale");
    Image grey = model.getImagePixels();
    for (int y = 0; y < grey.getHeight(); y++) {
      for (int x = 0; x < grey.getWidth(); x++) {
        Color greyPixel = grey.getPixel(x, y);
        Color origPixel = original.getPixel(x, y);
        assertEquals((origPixel.getRed() * 0.2126 + origPixel.getGreen() * 0.7152
            + origPixel.getBlue() * 0.0722), greyPixel.getRed(), 2.0);
        assertEquals((origPixel.getRed() * 0.2126 + origPixel.getGreen() * 0.7152
            + origPixel.getBlue() * 0.0722), greyPixel.getGreen(), 2.0);
        assertEquals((origPixel.getRed() * 0.2126 + origPixel.getGreen() * 0.7152
            + origPixel.getBlue() * 0.0722), greyPixel.getBlue(), 2.0);
      }
    }
  }

  @Test
  public void testSepia() {
    //hello this is an important change
    ImageProcessorModel model = new SimpleImageModel();
    model.importImage("bay.ppm");
    Image original = model.getImagePixels();
    model.applyFilter("sepia");
    Image sepi = model.getImagePixels();
    for (int y = 0; y < sepi.getHeight(); y++) {
      for (int x = 0; x < sepi.getWidth(); x++) {
        Color sepiPixel = sepi.getPixel(x, y);
        Color origPixel = original.getPixel(x, y);
        if (sepiPixel.getRed() < 0xff && sepiPixel.getRed() > 0
            && sepiPixel.getGreen() < 0xff && sepiPixel.getGreen() > 0
            && sepiPixel.getBlue() < 0xff && sepiPixel.getBlue() > 0) {
          assertEquals((origPixel.getRed() * 0.393 + origPixel.getGreen() * 0.769
              + origPixel.getBlue() * 0.189), sepiPixel.getRed(), 10);
          assertEquals((origPixel.getRed() * 0.349 + origPixel.getGreen() * 0.686
              + origPixel.getBlue() * 0.168), sepiPixel.getGreen(), 6);
          assertEquals((origPixel.getRed() * 0.272 + origPixel.getGreen() * 0.534
              + origPixel.getBlue() * 0.131), sepiPixel.getBlue(), 6);
        }
      }
    }
  }
}
