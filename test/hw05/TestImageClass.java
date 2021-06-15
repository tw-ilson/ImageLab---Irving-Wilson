package hw05;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import model.color.LightColor;
import model.image.Image;
import model.image.SimpleImage;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for the Image class.
 */
public class TestImageClass {


  private Image image1;
  private LightColor[] toTest = new LightColor[9];

  /**
   * For the purpose of testing for the error messages.
   */
  @Rule
  public ExpectedException expectedEx = ExpectedException.none();


  @Before
  public void initialize() {
    for (int i = 0; i < 9; i++) {
      toTest[i] = new LightColor(2, 1, 1);
    }

    this.image1 = new SimpleImage(toTest, 3, 3);

  }

  /**
   * Tests for PixArray.
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
   * Tests for getPixel.
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


  @Test
  public void testGetWidth() {
    assertEquals(image1.getWidth(), 3);
  }

  @Test
  public void testGetHeight() {
    assertEquals(image1.getHeight(), 3);
  }


}
