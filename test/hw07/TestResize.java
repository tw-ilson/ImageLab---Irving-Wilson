package hw07;

import controller.ImageUtils;
import java.io.IOException;
import model.image.Image;
import org.junit.Before;
import org.junit.Test;

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
    try {
      ImageUtils.write("png", "minisquid.png", after);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
