package hw05;

import static org.junit.Assert.assertEquals;

import model.ColorUtils.LightColor;
import model.ImageProcessorModel;
import model.SimpleImageProcessorModel;
import model.image.Image;
import model.image.SimpleImage;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the SimpleImageProcessor class.
 */
public class TestSimpleImageProcessor {

  private ImageProcessorModel model;
  private Image image1;
  private Image image2;
  private Image image3;
  private Image image4;
  private LightColor[] toTest = new LightColor[9];
  private LightColor[] tooSmall = new LightColor[1];
  private LightColor[] another = new LightColor[9];
  private LightColor[] larger = new LightColor[900];

  @Before
  public void initialize() {
    this.model = new SimpleImageProcessorModel();
    for (int i = 0; i < 9; i++) {
      toTest[i] = new LightColor(2, 1, 1);
    }
    for (int i = 0; i < 900; i++) {
      larger[i] = new LightColor(10, 10, 10);
    }

    tooSmall[0] = new LightColor(300);

    another[0] = new LightColor(1, 1, 1);
    another[1] = new LightColor(2, 2, 2);
    another[2] = new LightColor(3, 3, 3);
    another[3] = new LightColor(4, 4, 4);
    another[4] = new LightColor(5, 5,5);
    another[5] = new LightColor(6, 6, 6);
    another[6] = new LightColor(7, 7, 7);
    another[7] = new LightColor(8, 7, 7);
    another[8] = new LightColor(9, 8, 8);

    this.image1 = new SimpleImage(toTest, 3, 3);

    this.image2 = new SimpleImage(tooSmall, 3, 3);

    this.image3 = new SimpleImage(another, 3, 3);

    this.image4 = new SimpleImage(larger, 300, 300);
  }

  /**
   * Tests for getImageState
   */
  @Test (expected = IllegalStateException.class)
  public void testImageVersionsNotInstatiated() {
    model.getImageState();
  }

}
