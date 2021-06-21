package hw06;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import model.ImageProcessorLayerModel;
import model.LayeredImageModel;
import model.color.LightColor;
import model.image.Image;
import model.image.SimpleImage;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the layerImageModel, specifically that look for the problems in the translation between
 * the delegation and the layerImageModel class itself. Checks that the manipulation of layers
 * functions correctly.
 */
public class TestLayerImageModel {

  private ImageProcessorLayerModel model;
  private Image image2;


  @Before
  public void init() {
    LightColor[] toTest2 = new LightColor[9];
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
    model.createLayer("Layer1");
    model.createLayer("Layer2");
    model.editCurrentLayer(image2);
    model.setCurrentLayer("Layer1");
    model.editCurrentLayer(image2);
    assertEquals(model.getImage(), image2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetCurrentLayerWBadArgument() {
    model.createLayer("Layer1");
    model.setCurrentLayer("Layer4");
  }

  @Test
  public void testEditCurrentLayer() {
    model.createLayer("Layer1");
    model.editCurrentLayer(image2);
    assertEquals(model.getImage(), image2);
  }

  @Test(expected = NullPointerException.class)
  public void testEditCurrentLayerBad() {
    model.createLayer("Layer1");
    model.editCurrentLayer(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetVisibility() {
    model.createLayer("Layer1");
    model.editCurrentLayer(image2);
    model.setVisibility("Layer1", false);
    model.getImage();
  }

  /**
   * Tests for the remove method in the model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemove() {
    model.createLayer("layer1");
    model.removeLayer("layer1");
    model.setCurrentLayer("layer1");
  }

  @Test(expected = NullPointerException.class)
  public void testRemove2() {
    model.createLayer("layer1");
    model.editCurrentLayer(image2);
    model.createLayer("layer2");
    model.editCurrentLayer(image2);
    model.removeLayer("layer2");
    assertEquals(model.getImage(), image2);
  }


}
