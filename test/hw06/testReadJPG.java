package hw06;

import static org.junit.Assert.assertEquals;

import model.ImageProcessorModel;
import model.SimpleImageProcessorModel;
import model.image.Image;
import org.junit.Before;
import org.junit.Test;

public class testReadJPG {

  private Image newImage;

  @Before
  public void setUp() {
    ImageProcessorModel processor = new SimpleImageProcessorModel();
    String filename = "photos/squidward.png";
    processor.importImage(filename);
    newImage = processor.getImageState();
  }

  @Test
  public void plswork() {
    assertEquals(newImage.getWidth(), 800);
  }



}
