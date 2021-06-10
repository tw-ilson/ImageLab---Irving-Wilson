package hw05;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import model.ColorUtils.Color;
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
  private Color[] toTest = new Color[9];
  private Color[] tooSmall = new Color[1];
  private Color[] another = new Color[9];
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
      toTest[i] = new Color(2, 1, 1);
    }

    tooSmall[0] = new Color(300);

    another[0] = new Color(1, 1, 1);
    another[1] = new Color(2, 2, 2);
    another[2] = new Color(3, 3, 3);
    another[3] = new Color(4, 4, 4);
    another[4] = new Color(5, 5,5);
    another[5] = new Color(6, 6, 6);
    another[6] = new Color(7, 7, 7);
    another[7] = new Color(8, 7, 7);
    another[8] = new Color(9, 8, 8);

    this.image1 = new SimpleImage(toTest, 3, 3);

    this.image2 = new SimpleImage(tooSmall, 3, 3);

    this.image3 = new SimpleImage(another, 3, 3);


  }

  @Test
  public void testKernelFilterNullImage() {
    expectedEx.expect(NullPointerException.class);
    model.applyFilter("sharpen", null);
  }

  @Test
  public void testKernelFilterSharpenWithTooSmallImage() {
    expectedEx.expect(IllegalStateException.class);
    expectedEx.expectMessage("Image is too small for the filter.");
    model.applyFilter("sharpen", image1);
  }

  @Test
  public void testKernelFilterBlurWithTooSmallImage() {
    expectedEx.expect(IllegalStateException.class);
    expectedEx.expectMessage("Image is too small for the filter.");
    model.applyFilter("blur", image2);
  }

  @Test
  public void testBlur() {
    model.applyFilter("blur", image1);
    assertEquals(image1.getPixel(1,2).getRed(), new Color(2, 0, 0).getRed());
  }

  @Test
  public void testBlurAddition() {
    model.applyFilter("blur", image1);
    assertEquals(image1.getPixel(1,2).getRed(), new Color(2, 0, 0).getRed());
  }

  @Test
  public void testBLURRR() {
    model.applyFilter("blur", image3);
    assertEquals(image3.getPixel(1,1).getRed(), new Color(5, 0, 0).getRed());
  }

}
