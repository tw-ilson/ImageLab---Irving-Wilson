//package hw05.filters;
//
//import static org.junit.Assert.assertEquals;
//
//import model.color.LightColor;
//import model.ImageProcessorModel;
//import model.SimpleImageModel;
//import model.image.Image;
//import model.image.SimpleImage;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//
///**
// * TestKernel filter is a test class for the KernelFilter class.
// */
//public class TestKernelFilter {
//
//  private Image image1;
//  private Image image2;
//  private Image image3;
//  private Image image4;
//  private Image image5;
//  private LightColor[] toTest = new LightColor[9];
//  private LightColor[] tooSmall = new LightColor[1];
//  private LightColor[] another = new LightColor[9];
//  private LightColor[] another2 = new LightColor[9];
//  private LightColor[] large = new LightColor[25];
//  private ImageProcessorModel model;
//  private ImageProcessorModel model2;
//
//  /**
//   * For the purpose of testing for the error messages.
//   */
//  @Rule
//  public ExpectedException expectedEx = ExpectedException.none();
//
//
//  @Before
//  public void initialize() {
//    model = new SimpleImageModel();
//    model2 = new SimpleImageModel();
//    for (int i = 0; i < 9; i++) {
//      toTest[i] = new LightColor(2, 1, 1);
//    }
//
//    for (int i = 0; i < 4; i++) {
//      another2[i] = new LightColor(1, 1, 1);
//    }
//    another2[4] = new LightColor(2000, 256, 256);
//    for (int i = 5; i < 9; i++) {
//      another2[i] = new LightColor(1, 1, 1);
//    }
//
//    for (int i = 0; i < 25; i++) {
//      large[i] = new LightColor(1, 1, 1);
//    }
//
//    tooSmall[0] = new LightColor(300);
//
//    another[0] = new LightColor(1, 1, 1);
//    another[1] = new LightColor(2, 2, 2);
//    another[2] = new LightColor(3, 3, 3);
//    another[3] = new LightColor(4, 4, 4);
//    another[4] = new LightColor(5, 5, 5);
//    another[5] = new LightColor(6, 6, 6);
//    another[6] = new LightColor(7, 7, 7);
//    another[7] = new LightColor(8, 7, 7);
//    another[8] = new LightColor(9, 8, 8);
//
//    this.image1 = new SimpleImage(toTest, 3, 3);
//    this.image2 = new SimpleImage(tooSmall, 3, 3);
//    this.image3 = new SimpleImage(another, 3, 3);
//    this.image4 = new SimpleImage(another2, 3, 3);
//    this.image5 = new SimpleImage(large, 9, 9);
//
//  }
//
//  @Test
//  public void testKernelFilterNullImage() {
//    expectedEx.expect(IllegalStateException.class);
//    model.applyFilter("sharpen");
//  }
//
//  @Test
//  public void testArrayLength() {
//    image1 = new SimpleImage(toTest, 3, 3);
//    assertEquals(image1.pixArray().length, 9);
//  }
//
//  @Test
//  public void testKernelFilterSharpenWithTooSmallImage() {
//    expectedEx.expect(IllegalStateException.class);
//    expectedEx.expectMessage("Image is too small for the filter.");
//    model.importImage(image1);
//    model.applyFilter("sharpen");
//  }
//
//  @Test
//  public void testKernelFilterBlurWithTooSmallImage() {
//    expectedEx.expect(IllegalStateException.class);
//    expectedEx.expectMessage("The width and height are invalid.");
//    model.importImage(image2);
//    model.applyFilter("blur");
//  }
//
//  @Test
//  public void testBlur() {
//    image2 = new SimpleImage(tooSmall, 3, 3);
//    model.importImage(image1);
//    model.applyFilter("blur");
//    assertEquals(image1.getPixel(1, 2).getRed(), new LightColor(2, 0, 0).getRed());
//  }
//
//  @Test
//  public void testBlurAddition() {
//    model.importImage(image1);
//    model.applyFilter("blur");
//    assertEquals(image1.getPixel(1, 2).getRed(), new LightColor(2, 0, 0).getRed());
//  }
//
//  @Test
//  public void testBLURRR() {
//    model.importImage(image3);
//    model.applyFilter("blur");
//    assertEquals(image3.getPixel(1, 1).getRed(), new LightColor(5, 0, 0).getRed());
//  }
//
//  /**
//   * Testing the sharpen filter.
//   */
//  @Test(expected = IllegalStateException.class)
//  public void testSharpen() {
//    model.importImage(image5);
//    model.applyFilter("sharpen");
//    assertEquals(image3.getPixel(1, 1).getRed(), new LightColor(5, 0, 0).getRed());
//  }
//
//  @Test
//  public void testSharpen1() {
//    expectedEx.expect(IllegalStateException.class);
//    expectedEx.expectMessage("Image is too small for the filter.");
//    image3 = new SimpleImage(another, 3, 3);
//    model.importImage(image3);
//    model.applyFilter("sharpen");
//    assertEquals(image3.getPixel(1, 1).getRed(), new LightColor(5, 0, 0).getRed());
//  }
//
//
//  /**
//   * Tests to confirm that if the pixels are on the border, they are not changed.
//   */
//  @Test
//  public void testBlurNotChange() {
//    model.importImage(image3);
//    model.applyFilter("blur");
//    assertEquals(image3.getPixel(0, 0).getRed(), new LightColor(1, 0, 0).getRed());
//  }
//
//  @Test
//  public void testBlurBottomRight() {
//    model.importImage(image3);
//    model.applyFilter("blur");
//    assertEquals(image3.getPixel(2, 2).getRed(), new LightColor(9, 0, 0).getRed());
//  }
//
//  /**
//   * Test clamping functionality, that it sets the pixel to 255 if the value is too high.
//   */
//  @Test
//  public void testOverClamping() {
//    image4 = new SimpleImage(another2, 3, 3);
//    model2.importImage(image4);
//    model2.applyFilter("blur");
//    assertEquals(model2.getImagePixels().getPixel(1, 1).getRed(),
//        new LightColor(255, 9, 9).getRed());
//  }
//
//  @Test
//  public void testUnderClamping() {
//    another2[4] = new LightColor(-2000, 1, 1);
//    image4 = new SimpleImage(another2, 3, 3);
//    model2.importImage(image4);
//    model2.applyFilter("blur");
//    assertEquals(model2.getImagePixels().getPixel(1, 1).getRed(), new LightColor(0, 9, 9).getRed());
//  }
//
//  // clamping with sharpen
//  @Test
//  public void testSharpen2() {
//    large[5] = new LightColor(25000, 0, 0);
//    image5 = new SimpleImage(large, 5, 5);
//    model.importImage(image5);
//    model.applyFilter("sharpen");
//    assertEquals(model.getImagePixels().getPixel(2, 2).getRed(),
//        new LightColor(0, 0, 0).getRed());
//  }
//
//  // minimum clamp
//  @Test
//  public void testSharpen3() {
//    large[5] = new LightColor(-25000, 0, 0);
//    image5 = new SimpleImage(large, 5, 5);
//    model.importImage(image5);
//    model.applyFilter("sharpen");
//    assertEquals(model.getImagePixels().getPixel(2, 2).getRed(),
//        new LightColor(255, 0, 0).getRed());
//  }
//
//
//}
