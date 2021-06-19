package hw06;

import controller.ImageProcessorController;
import controller.SimpleImageController;
import java.io.StringReader;
import model.ImageProcessorLayerModel;
import model.LayeredImageModel;
import model.color.LightColor;
import model.image.Image;
import model.image.SimpleImage;
import model.image.SimpleLayeredImage;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the simpleImageController
 */
public class testSimpleImageController {

  private ImageProcessorLayerModel model;
  private ImageProcessorController controller;
  private Image image1;
  private LightColor[] toTest = new LightColor[9];
  private Readable in;
  private Appendable out;


  @Before
  public void init() {
    model = new LayeredImageModel();
    for (int i = 0; i < 9; i++) {
      toTest[i] = new LightColor(2, 1, 1);
    }
    image1 = new SimpleImage(toTest, 3, 3);
  }


  @Test
  public void testQuit() {
    in = new StringReader("quit");
    out = new StringBuilder("quit");
    this.controller = new SimpleImageController(model, out);

  }

}
