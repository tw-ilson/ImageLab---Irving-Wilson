package hw06;

import static org.junit.Assert.assertEquals;

import controller.ImageUtils;
import java.io.File;
import java.io.IOException;
import model.image.Image;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests that Images from ppms, jpegs and pngs can be successfully imported and exported. Instead of
 * using a module extended by the model, we now have all the IO functionality contained by the
 * ImageUtils class in the controller's package.
 */
public class TestReadWrite {

  String[] files;
  Image[] images = new Image[3];

  @Before
  public void setup() {

    files = new String[]
        {"photos/bay.ppm",
            "photos/squidward.png",
            "photos/Moon.jpeg"};

    images = new Image[3];
    for (int i = 0; i < 3; i++) {
      try {
        images[i] = ImageUtils.read(files[i]);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Test
  public void testRead() {
    for (Image image : images) {
      assertEquals(800, image.getWidth());
      assertEquals(600, image.getHeight());
    }
  }

  @Test
  public void testWrite() {
    File[] files = new File[]{new File("example1.ppm"), new File("example2.png"),
        new File("example3.jpeg")};
    try {
      assertEquals("Successfully exported ppm image: example1.ppm",
          ImageUtils.write("ppm", "example1.ppm", images[0]));
      assertEquals("Successfully exported png image: example2.png",
          ImageUtils.write("png", "example2.png", images[1]));
      assertEquals("Successfully exported jpeg image: example3.jpeg",
          ImageUtils.write("jpeg", "example3.jpeg", images[2]));
      for (File f : files) {
        f.delete();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
