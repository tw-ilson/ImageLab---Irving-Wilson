package hw07;

import static controller.ImageUtils.read;
import static controller.ImageUtils.write;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import model.color.Color;
import model.filters.FilterBuilder;
import model.image.Image;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the proper functionality of Kernel filter, ensuring that it can work with images of all
 * kinds.
 */
public class TestKernelFilter {
  Image squidward;

  @Before
  public void setup() {
    try {
      squidward = read("photos/squidward.png");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testBlur() {
    Color before = squidward.getPixel(30,30);
    squidward = new FilterBuilder().getFilter("blur").apply(squidward);
    Color after = squidward.getPixel(30, 30);
    System.out.println(Integer.toHexString(before.getRGB()));
    System.out.println(Integer.toHexString(after.getRGB()));
    try {
      write("jpeg", "test.jpg", squidward);
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertNotEquals(before, after);
  }
}
