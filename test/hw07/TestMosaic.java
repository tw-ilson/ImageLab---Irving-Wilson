package hw07;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import model.color.LightColor;
import model.image.Image;
import model.image.SimpleImage;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the mosaic method, seeing if the seeds correctly populated the image and if the
 * distances between pixels is found to be correct, in terms of the manner in which the various
 * colors are altered.
 */
public class TestMosaic {

  private Image image1;
  private final LightColor[] toTest = new LightColor[9];


  @Before
  public void init() {
    for (int i = 0; i < 9; i++) {
      toTest[i] = new LightColor(1, 1, 1);
    }
    this.image1 = new SimpleImage(toTest, 3, 3);
  }

  @Test
  public void testMosaic() {
    toTest[0] = new LightColor(2, 2, 2);
    Image toCheck = image1.mosaic(1);
    assertEquals(toCheck.getPixel(0, 0).getRed(), toCheck.getPixel(2, 2).getRed());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMosaicWNegative() {
    toTest[0] = new LightColor(2, 2, 2);
    image1.mosaic(-2000);
  }

  @Test(expected = NullPointerException.class)
  public void testMosaicWStringInput() {
    toTest[0] = new LightColor(2, 2, 2);
    Image toCheck = image1.mosaic(0);
    assertNotEquals(toCheck.getPixel(0, 0).getRed(), toCheck.getPixel(1, 1));
  }

  @Test
  public void testMosaicSeedDistancing() {
    LightColor[] oneMore = new LightColor[25];
    for (int i = 0; i < 13; i++) {
      oneMore[i] = new LightColor(1, 1, 1);
    }
    for (int i = 13; i < 25; i++) {
      oneMore[i] = new LightColor(2, 2, 2);
    }
    Image toCheck = new SimpleImage(oneMore, 5, 5);
    Image toConfirm = toCheck.mosaic(2);
    assertEquals(toConfirm.getPixel(0, 0).getRed(), toConfirm.getPixel(1, 1).getRed());
    assertNotEquals(toConfirm.getPixel(0, 0).getRed(),
        toConfirm.getPixel(4, 4).getRed());
  }
}
