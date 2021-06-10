package hw05.TestFilters;

import static org.junit.Assert.assertEquals;

import model.ColorUtils.Color;
import org.junit.Test;

/**
 * Tests the proper functionality of the color object and its methods.
 */
public class TestColor {

  @Test
  public void testGetters() {
    Color c = new Color(0xff9900);
    assertEquals(255, c.getRed());
    assertEquals(153, c.getGreen());
    assertEquals(0, c.getBlue());
  }
  @Test
  public void testColorRGBConstructor() {
    Color c = new Color(204, 51, 255);
    //Color c = new Color(0xcc, 0x33, 0xff);

    assertEquals(204, c.getRed());
    assertEquals(51, c.getGreen());
    assertEquals(255, c.getBlue());
  }
}
