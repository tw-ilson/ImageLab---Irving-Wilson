package hw05;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import model.ColorUtils.Color;
import model.ColorUtils.LightColor;
import model.ImageProcessorModel;
import model.SimpleImageProcessorModel;
import model.image.Image;
import org.junit.Test;

/**
 * Tests that the import and export functionality of the ImageProcessorIO interface is correct.
 */
public class TestImageProcessorIO {

  @Test
  public void testImportPPM() {
    String filename = "Koala.ppm";
    ImageProcessorModel importer = new SimpleImageProcessorModel();
    importer.importImage(filename);
    Image imported = importer.getImageState();
    Scanner sc = new Scanner("hello");

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    sc = new Scanner(builder.toString());
    assertEquals("P3", sc.next());
    assertEquals(imported.getWidth(), sc.nextInt());
    assertEquals(imported.getHeight(), sc.nextInt());
    assertEquals(255, sc.nextInt());
    for (int i = 0; i < imported.getHeight(); i++) {
      for (int j = 0; j < imported.getWidth(); j++) {
        Color cur = imported.getPixel(j, i);
        assertNotNull(cur);

        assertEquals(cur.getRed(), sc.nextInt());
        assertEquals(cur.getGreen(), sc.nextInt());
        assertEquals(cur.getBlue(), sc.nextInt());
      }
    }
  }

  @Test
  public void testExport() {

  }
}
