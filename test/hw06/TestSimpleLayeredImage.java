package hw06;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import model.color.LightColor;
import model.image.Image;
import model.image.LayeredImage;
import model.image.SimpleImage;
import model.image.SimpleLayeredImage;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the simpleLayeredImage, which see the viability of the newly added functionality.
 * Specifically the adding and removal of layers, along with the continued functionality of the use
 * of the filters.
 */
public class TestSimpleLayeredImage {

  private LayeredImage image1;
  private Image image2;
  private Image image3;
  private Image image4;


  @Before
  public void init() {
    LightColor[] toTest = new LightColor[9];
    LightColor[] toTest2 = new LightColor[9];
    LightColor[] toTest3 = new LightColor[25];
    image1 = new SimpleLayeredImage();
    for (int i = 0; i < 9; i++) {
      toTest[i] = new LightColor(2, 1, 1);
    }
    this.image2 = new SimpleImage(toTest, 3, 3);

    for (int i = 0; i < 9; i++) {
      toTest2[i] = new LightColor(3, 3, 3);
    }
    this.image3 = new SimpleImage(toTest2, 3, 3);
    for (int i = 0; i < 25; i++) {
      toTest3[i] = new LightColor(1, 1, 1);
    }
    this.image4 = new SimpleImage(toTest3, 5, 5);
  }

  @Test(expected = IllegalStateException.class)
  public void testTopMostVisibleWithNoCreatedLayers() {
    image1.topMostVisibleLayer();
  }

  @Test(expected = IllegalStateException.class)
  public void testTopMostVisibleLayer() {
    image1.addLayer("Layer1");
    image1.topMostVisibleLayer().pixArray();
  }


  /**
   * Tests for setCurrentLayer.
   */


  @Test
  public void testSetCurrentLayerValid() {
    image1 = new SimpleLayeredImage(new ArrayList<String>(Arrays.asList("Hype")), image2);
    image1.setCurrentLayer("Hype");
    assertEquals(image1.getCurrentLayer(), image2);
    image1.addLayer("Hype2");
    image1.setCurrentLayer("Hype2");
    image1.editCurrentLayer(image3);
    assertEquals(image1.getCurrentLayer(), image3);
  }


  @Test(expected = NullPointerException.class)
  public void testSetCurrentLayerNull() {
    image1 = new SimpleLayeredImage(new ArrayList<String>(Arrays.asList("Hype")), image2);
    image1.setCurrentLayer(null);
  }

  /**
   * Tests for getCurrentLayer.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetCurrentLayer() {
    image1 = new SimpleLayeredImage(new ArrayList<String>(Arrays.asList("Hype")), image3);
    image1.setCurrentLayer("e");
    assertEquals(image1.getCurrentLayer(), image3);
    image1.addLayer("Hype2");
    image1.editCurrentLayer(image3);
    assertEquals(image1.getCurrentLayer(), image2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCurrentLayerWithNullImage() {
    image1 = new SimpleLayeredImage();
    image1.addLayer("Layer1");
    image1.setCurrentLayer("Layer1");
    image1.getCurrentLayer();
  }

  /**
   * Tests for editCurrentLayer.
   */
  @Test
  public void testEditCurrentLayer() {
    image1 = new SimpleLayeredImage();
    image1.addLayer("Layer1");
    image1.setCurrentLayer("Layer1");
    image1.editCurrentLayer(image2);
    assertEquals(image1.getCurrentLayer(), image2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditCurrentLayerWithInvalidWidth() {
    image1 = new SimpleLayeredImage();
    image1.addLayer("Layer1");
    image1.setCurrentLayer("Layer1");
    image1.editCurrentLayer(image2);
    image1.addLayer("Layer2");
    image1.setCurrentLayer("Layer2");
    image1.editCurrentLayer(image4);
  }

  /**
   * Tests for numlayers.
   */
  @Test
  public void testNumLayer() {
    image1 = new SimpleLayeredImage();
    image1.addLayer("Layer1");
    image1.addLayer("Layer2");
    assertEquals(image1.numLayers(), 2);
  }

  @Test(expected = NullPointerException.class)
  public void testNumLayerWithNull() {
    image1 = null;
    image1.numLayers();
  }

  @Test
  public void testLayeredImageNoLayersMade() {
    image1 = new SimpleLayeredImage();
    assertEquals(image1.numLayers(), 0);
  }

  /**
   * Tests for topMostVisible layer.
   */
  @Test
  public void testTopMostVisible() {
    image1 = new SimpleLayeredImage();
    image1.addLayer("Layer1");
    image1.setCurrentLayer("Layer1");
    image1.editCurrentLayer(image2);
    image1.addLayer("Layer2");
    assertEquals(image1.topMostVisibleLayer(), image2);
  }

  @Test(expected = IllegalStateException.class)
  public void testTopMostVisibleWithNoVisible() {
    image1 = new SimpleLayeredImage();
    image1.addLayer("Layer1");
    image1.setCurrentLayer("Layer1");
    image1.editCurrentLayer(image2);
    image1.setVisibility("Layer1", false);
    image1.addLayer("Layer2");
    image1.setVisibility("Layer2", false);
    image1.editCurrentLayer(image2);
    image1.topMostVisibleLayer();
  }

  @Test(expected = NullPointerException.class)
  public void testNullImage() {
    image1 = null;
    image1.topMostVisibleLayer();
  }

  /**
   * Tests setCurrentLayer.
   */
  @Test
  public void testSetCurrentLayer() {
    image1 = new SimpleLayeredImage();
    image1.addLayer("Layer1");
    image1.setCurrentLayer("Layer1");
    image1.editCurrentLayer(image3);
    image1.addLayer("Layer2");
    image1.setCurrentLayer("Layer2");
    image1.editCurrentLayer(image2);
    assertEquals(image1.getCurrentLayer(), image2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetCurrentLayerWrongLayerName() {
    image1 = new SimpleLayeredImage();
    image1.addLayer("Layer1");
    image1.setCurrentLayer("Layer1");
    image1.editCurrentLayer(image3);
    image1.addLayer("Layer2");
    image1.setCurrentLayer("Layer4");
  }

  /**
   * Tests for remove layer.
   */
  @Test
  public void testRemoveLayer() {
    image1 = new SimpleLayeredImage();
    image1.addLayer("Layer1");
    image1.removeLayer("Layer1");
    assertEquals(image1.numLayers(), 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveLayerWNoLayer() {
    image1 = new SimpleLayeredImage();
    image1.removeLayer("Layer1");
  }

  @Test(expected = NullPointerException.class)
  public void testRemoveLayerWNUll() {
    image1 = null;
    image1.removeLayer("Layer1");
  }

}
