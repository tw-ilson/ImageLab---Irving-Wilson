package hw05;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import model.ColorUtils.Color;
import model.ColorUtils.LightColor;
import model.ImageProcessorModel;
import model.SimpleImageProcessorModel;
import model.image.Image;
import model.image.SimpleImage;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for the Image class
 */
public class TestImageClass {


  private Image image1;
  private Image image2;
  private Image image3;
  private Image image4;
  private Image image5;
  private LightColor[] toTest = new LightColor[9];
  private LightColor[] tooSmall = new LightColor[1];
  private LightColor[] another = new LightColor[9];
  private LightColor[] another2 = new LightColor[9];
  private LightColor[] large = new LightColor[25];
  private ImageProcessorModel model;
  private ImageProcessorModel model2;

  /**
   * For the purpose of testing for the error messages.
   */
  @Rule
  public ExpectedException expectedEx = ExpectedException.none();


  @Before
  public void initialize() {
    model = new SimpleImageProcessorModel();
    model2 = new SimpleImageProcessorModel();
    for (int i = 0; i < 9; i++) {
      toTest[i] = new LightColor(2, 1, 1);
    }

    for (int i = 0; i < 4; i++) {
      another2[i] = new LightColor(1, 1, 1);
    }
    another2[4] = new LightColor(2000, 256, 256);
    for (int i = 5; i < 9; i++) {
      another2[i] = new LightColor(1, 1, 1);
    }

    for (int i = 0; i < 25; i++) {
      large[i] = new LightColor(1, 1, 1);
    }

    tooSmall[0] = new LightColor(300);

    another[0] = new LightColor(1, 1, 1);
    another[1] = new LightColor(2, 2, 2);
    another[2] = new LightColor(3, 3, 3);
    another[3] = new LightColor(4, 4, 4);
    another[4] = new LightColor(5, 5, 5);
    another[5] = new LightColor(6, 6, 6);
    another[6] = new LightColor(7, 7, 7);
    another[7] = new LightColor(8, 7, 7);
    another[8] = new LightColor(9, 8, 8);

    this.image1 = new SimpleImage(toTest, 3, 3);
    this.image2 = new SimpleImage(tooSmall, 3, 3);
    this.image3 = new SimpleImage(another, 3, 3);
    this.image4 = new SimpleImage(another2, 3, 3);
    this.image5 = new SimpleImage(large, 9, 9);

  }

  /**
   * Tests for PixArray
   */
  @Test
  public void testPixArray() {
    // creates a clone
    assertNotEquals(image1.pixArray(), toTest);
  }

  @Test(expected = NullPointerException.class)
  public void testPixArrayNUll() {
    // creates a clone
    image1 = new SimpleImage(null, 3, 3);
    assertNotEquals(image1.pixArray(), toTest);
  }

  /**
   * Tests for getPixel
   */
  @Test
  public void testGetPixel() {
    assertNotEquals(image1.getPixel(1, 1), new LightColor(1, 1, 1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelTooLarge() {
    assertNotEquals(image1.getPixel(5, 5), new LightColor(1, 1, 1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelTooSmall() {
    assertNotEquals(image1.getPixel(-5, -5), new LightColor(1, 1, 1));
  }

  /**
   * Tests for setPixel
   */
  @Test
  public void testSetPixel() {
    image1.setPixel(1, 1, new LightColor(200, 200, 200));
    assertNotEquals(image1.getPixel(1, 1), new LightColor(200, 200, 200));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelTooLarge() {
    image1.setPixel(5, 5, new LightColor(200, 200, 200));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelTooSmall() {
    image1.setPixel(-5, -5, new LightColor(200, 200, 200));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullColor() {
    image1.setPixel(-5, -5, null);
  }

  @Test
  public void testGetWidth() {
    assertEquals(image1.getWidth(), 3);
  }

  @Test
  public void testGetHeight() {
    assertEquals(image1.getHeight(), 3);
  }


}
