package hw05.TestFilters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import model.ColorUtils.LightColor;
import model.ColorUtils.LightColor;
import model.ImageProcessorModel;
import model.SimpleImageProcessorModel;
import model.image.Image;
import model.image.SimpleImage;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestKernelFilter {

  private Image image1;
  private Image image2;
  private Image image3;
  private LightColor[] toTest = new LightColor[9];
  private LightColor[] tooSmall = new LightColor[1];
  private LightColor[] another = new LightColor[9];
  private ImageProcessorModel model;
  private ImageProcessorModel model2;

  /**
   * For the purpose of testing for the error messages.
   */
  @Rule
  public ExpectedException expectedEx = ExpectedException.none();


  @Before
  public void initialize() {
    model = new SimpleImageProcessorModel();
    for (int i = 0; i < 9; i++) {
      toTest[i] = new LightColor(2, 1, 1);
    }

    tooSmall[0] = new LightColor(300);

    another[0] = new LightColor(1, 1, 1);
    another[1] = new LightColor(2, 2, 2);
    another[2] = new LightColor(3, 3, 3);
    another[3] = new LightColor(4, 4, 4);
    another[4] = new LightColor(5, 5,5);
    another[5] = new LightColor(6, 6, 6);
    another[6] = new LightColor(7, 7, 7);
    another[7] = new LightColor(8, 7, 7);
    another[8] = new LightColor(9, 8, 8);

    this.image1 = new SimpleImage(toTest, 3, 3);

    this.image2 = new SimpleImage(tooSmall, 3, 3);

    this.image3 = new SimpleImage(another, 3, 3);


  }

  @Test
  public void testKernelFilterNullImage() {
    expectedEx.expect(IllegalStateException.class);
    model.applyFilter("sharpen");
  }

  @Test
  public void testKernelFilterSharpenWithTooSmallImage() {
    expectedEx.expect(IllegalStateException.class);
    expectedEx.expectMessage("Image is too small for the filter.");
    model.importImage(image1);
    model.applyFilter("sharpen");
  }

  @Test
  public void testKernelFilterBlurWithTooSmallImage() {
    expectedEx.expect(IllegalStateException.class);
    expectedEx.expectMessage("Image is too small for the filter.");
    model.importImage(image2);
    model.applyFilter("blur");
  }

  @Test
  public void testBlur() {
    model.importImage(image1);
    model.applyFilter("blur");
    assertEquals(image1.getPixel(1,2).getRed(), new LightColor(2, 0, 0).getRed());
  }

  @Test
  public void testBlurAddition() {
    model.importImage(image1);
    model.applyFilter("blur");
    assertEquals(image1.getPixel(1,2).getRed(), new LightColor(2, 0, 0).getRed());
  }

  @Test
  public void testBLURRR() {
    model.importImage(image3);
    model.applyFilter("blur");
    assertEquals(image3.getPixel(1,1).getRed(), new LightColor(5, 0, 0).getRed());
  }

}
