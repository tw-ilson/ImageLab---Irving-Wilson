package hw06;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import model.ImageProcessorLayerModel;
import model.ImageProcessorModel;
import model.LayeredImageModel;
import model.color.LightColor;
import model.image.Image;
import model.image.SimpleImage;
import org.junit.Before;
import org.junit.Test;

public class testLayerImageModel {

  private ImageProcessorLayerModel model;
  private Image image2;
  private LightColor[] toTest2 = new LightColor[9];


  @Before
  public void init() {
    model = new LayeredImageModel();
    for (int i = 0; i < 9; i++) {
      toTest2[i] = new LightColor(3, 3, 3);
    }
    this.image2 = new SimpleImage(toTest2, 3, 3);

  }

  @Test
  public void testGetWidth() {
    model.createLayer("Okayy");
    model.setCurrentLayer("Okayy");
    model.editCurrentLayer(image2);
    assertEquals(model.getImageWidth(), 3);
  }

  @Test
  public void testGetHeight() {
    model.createLayer("Okayy");
    model.setCurrentLayer("Okayy");
    model.editCurrentLayer(image2);
    assertEquals(model.getImageHeight(), 3);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetWidthNull() {
    model.createLayer("Okayy");
    model.getImageHeight();
  }

  @Test(expected = IllegalStateException.class)
  public void getHeightNull() {
    model.createLayer("Okayy");
    model.getImageHeight();
  }


  @Test
  public void testApplyFilter() {
    model.createLayer("Okayy");
    model.setCurrentLayer("Okayy");
    model.editCurrentLayer(image2);
    model.applyFilter("blur");
    assertNotEquals(model.getImage(), image2);
  }

  @Test
  public void testApplyFilterBlurLayeredImage() {
    model.createLayer("Layer1");
    model.setCurrentLayer("Layer1");
    model.editCurrentLayer(image2);
    model.applyFilter("sepia");
    Image toCompare = model.getImage();
    model.createLayer("Layer2");
    model.setCurrentLayer("Layer2");
    model.editCurrentLayer(image2);
    model.applyFilter("blur");
    assertEquals(model.getImage().getPixel(2, 2).getRed(), 3);
    model.applyFilter("greyscale");
  }


  @Test
  public void testCreateLayer() {
    model.createLayer("Layer1");
    model.createLayer("layer2");
    model.createLayer("layer3");
    model.editCurrentLayer(image2);
    assertEquals(model.getImage(), image2);
  }

  /**
   * Tests for setCurrentLayer.
   */
  @Test
  public void testSetCurrentLayer() {

  }






}
