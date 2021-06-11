package hw05;

import static org.junit.Assert.assertTrue;

import model.color.Color;
import model.color.LightColor;
import model.image.Image;
import model.image.SimpleImage;
import org.junit.Test;

/**
 * Tests that the copy constructor for Image works and makes a deep copy of the image.
 */
public class TestImageCopy {

  @Test
  public void testCopyConstructorIsDeepCopy() {
    Color[] doobabp = new LightColor[4];
    doobabp[0] = new LightColor(0x0066ff);
    doobabp[1] = new LightColor(0xff00ff);
    doobabp[2] = new LightColor(0xcccccc);
    doobabp[3] = new LightColor(0x123456);
    Image wachooblap = new SimpleImage(doobabp, 4, 1);
    Image poserblapff = new SimpleImage(wachooblap);
    for (int i = 0; i < 4; i++) {
      assertTrue(wachooblap.getPixel(i, 0) != poserblapff.getPixel(i, 0));
    }
  }
}
