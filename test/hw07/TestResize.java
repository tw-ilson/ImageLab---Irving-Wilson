package hw07;

import static org.junit.Assert.assertEquals;

import controller.ImageUtils;
import java.io.IOException;
import model.image.Image;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class TestResize {

  Image before;

  @Before
  public void setup() {
    try {
      before = ImageUtils.read("photos/squidward.png");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @Test
  public void testResizeSimpleImage() {
    Image after = before.resize(321, 544);
    assertEquals(321, after.getWidth());
    assertEquals(544, after.getHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testResizeIllegalArg() {
    before.resize(987788765, 1349807110);
  }

  @Test//no exception
  public void test1by1Resize() {
    before.resize(1,1);
  }
}
