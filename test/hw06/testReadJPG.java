package hw06;

import static org.junit.Assert.assertEquals;

import model.ImageProcessorModel;
import model.SimpleImageModel;
import model.image.Image;
import org.junit.Before;
import org.junit.Test;

public class testReadJPG {

  private Image newImage;

  @Before
  public void setUp() {
    ImageProcessorModel processor = new SimpleImageModel();
    String filename = "photos/squidward.png";
    processor.importImage(filename);
    newImage = processor.getImagePixels();
  }

  @Test
  public void plswork() {
    assertEquals(newImage.getWidth(), 800);
  }



}
