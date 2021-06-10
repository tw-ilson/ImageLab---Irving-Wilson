package hw05;

import static org.junit.Assert.assertEquals;

import model.image.CheckerboardImage;
import model.image.Image;
import org.junit.Test;

public class TestCheckerBoard {

  @Test
  public void testCheckerBoard() {
    Image billGates = new CheckerboardImage(2,2);
    assertEquals(20, billGates.getWidth());
    assertEquals(20, billGates.getHeight());
    for (int x = 0; x < 10; x++) {
      for (int y = 0; y < 10; y++) {
        assertEquals(0x000000, billGates.getPixel(x, y).getRGB());
      }
    }

    for (int x = 10; x < 20; x++) {
      for (int y = 0; y < 10; y++) {
        assertEquals(0xffffff, billGates.getPixel(x, y).getRGB());
      }
    }

    for (int x = 0; x < 10; x++) {
      for (int y = 10; y < 20; y++) {
        assertEquals(0xffffff, billGates.getPixel(x, y).getRGB());
      }
    }

    for (int x = 10; x < 20; x++) {
      for (int y = 10; y < 20; y++) {
        assertEquals(0x000000, billGates.getPixel(x, y).getRGB());
      }
    }
   }
}
