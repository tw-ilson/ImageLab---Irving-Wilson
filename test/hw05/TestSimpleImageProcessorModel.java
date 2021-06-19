/*
package hw05;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import model.color.LightColor;
import model.SimpleImageModel;
import model.image.SimpleImage;
import org.junit.Before;
import org.junit.Test;

*/
/**
 * Tests for the SimpleImageProcessorModelClass.
 *//*

public class TestSimpleImageProcessorModel {

  private SimpleImageModel model;
  private SimpleImage image;
  private LightColor[] toTest = new LightColor[9];


  @Before
  public void init() {
    for (int i = 0; i < 9; i++) {
      toTest[i] = new LightColor(2, 1, 1);
    }
    this.model = new SimpleImageModel();
    this.image = new SimpleImage(toTest, 3, 3);
  }

  */
/**
   * Tests for getImageState.
   *//*


  @Test(expected = IllegalStateException.class)
  public void testGetImageStateWithNullStack() {
    model.getImagePixels();
  }

  @Test
  public void testGetImageState() {
    image = new SimpleImage(toTest, 3, 3);
    model.importImage(image);
    model.applyFilter("blur");
    assertNotEquals(image, model.getImagePixels());
  }

  */
/**
   * Tests for apply filter.
   *//*


  @Test(expected = IllegalStateException.class)
  public void applyFilterWithEmptyStack() {
    model.applyFilter("blur");
  }

  @Test
  public void applyFilterBlur() {
    model.importImage(image);
    model.applyFilter("blur");
    assertEquals(model.getImagePixels().getPixel(1, 1).getRed(),
        new LightColor(2, 2, 2).getRed());
  }

  @Test(expected = IllegalStateException.class)
  public void applyFilterSharpen() {
    model.importImage(image);
    model.applyFilter("sharpen");
    assertEquals(model.getImagePixels().getPixel(1, 1).getRed(),
        new LightColor(2, 2, 2).getRed());
  }

  @Test
  public void applyFilterSepia() {
    model.importImage(image);
    model.applyFilter("sepia");
    assertEquals(model.getImagePixels().getPixel(1, 1).getRed(),
        new LightColor(1, 2, 2).getRed());
  }

  @Test
  public void applyFilterGreyScale() {
    model.importImage(image);
    model.applyFilter("sepia");
    assertEquals(model.getImagePixels().getPixel(1, 1).getRed(),
        new LightColor(1, 2, 2).getRed());
  }

  */
/**
   * importImageTests.
   *//*


  @Test(expected = NullPointerException.class)
  public void importNull() {
    model.importImage(new SimpleImage(null));
  }

  @Test
  public void importImageImageVersionsClear() {
    model.importImage(image);
    assertEquals(model.getImagePixels(), image);
  }


}
*/
