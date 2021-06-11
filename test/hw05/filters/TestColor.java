package hw05.filters;

import static org.junit.Assert.assertEquals;

import model.color.LightColor;
import org.junit.Test;

/**
 * Tests the proper functionality of the color object and its methods.
 */
public class TestColor {

  @Test
  public void testGetters() {
    LightColor c = new LightColor(0xff9900);
    assertEquals(255, c.getRed());
    assertEquals(153, c.getGreen());
    assertEquals(0, c.getBlue());
    assertEquals(0xff9900, c.getRGB());
  }

  @Test
  public void testColorRGBConstructor() {
    LightColor c = new LightColor(204, 51, 255);
    //Color c = new Color(0xcc, 0x33, 0xff);

    assertEquals(204, c.getRed());
    assertEquals(51, c.getGreen());
    assertEquals(255, c.getBlue());
  }

  @Test
  public void testAllColors() {
    for (int r = 0; r < 256; r++) {
      for (int g = 0; g < 256; g++) {
        for (int b = 0; b < 256; b++) {
          LightColor c = new LightColor(r, g, b);
          assertEquals(r, c.getRed());
          assertEquals(g, c.getGreen());
          assertEquals(b, c.getBlue());
        }
      }
    }
  }
}
