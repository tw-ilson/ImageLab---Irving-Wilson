package hw06;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import model.ImageProcessorModel;
import model.LayerImageModel;
import model.color.LightColor;
import model.image.Image;
import model.image.LayeredImage;
import model.image.SimpleImage;
import model.image.SimpleLayeredImage;
import org.junit.Before;
import org.junit.Test;

public class testSimpleLayeredImage {

  private LayeredImage image1;
  private ImageProcessorModel model;
  private LightColor[] toTest = new LightColor[9];
  private LightColor[] toTest2 = new LightColor[9];
  private LightColor[] toTest3 = new LightColor[25];
  private Image image2;
  private Image image3;
  private Image image4;


  @Before
  public void init() {
    image1 = new SimpleLayeredImage();
    model = new LayerImageModel();
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

  @Test(expected = NullPointerException.class)
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


  @Test (expected = NullPointerException.class)
  public void testSetCurrentLayerNull() {
    image1 = new SimpleLayeredImage(new ArrayList<String>(Arrays.asList("Hype")), image2);
    image1.setCurrentLayer(null);
  }

  /**
   * Tests for getCurrentLayer
   */
  @Test
  public void testGetCurrentLayer() {
    image1 = new SimpleLayeredImage(new ArrayList<String>(Arrays.asList("Hype")), image3);
    image1.setCurrentLayer("e");
    assertEquals(image1.getCurrentLayer(), image3);
    image1.addLayer("Hype2");
    image1.setCurrentLayer("d");
    image1.editCurrentLayer(image3);
    assertEquals(image1.getCurrentLayer(), image2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGetCurrentLayerWithNullImage() {
    image1 = new SimpleLayeredImage();
    image1.addLayer("Layer1");
    image1.setCurrentLayer("Layer1");
    image1.getCurrentLayer();
  }

  /**
   * Tests for getVisibility.
   */
  @Test
  public void testGetVisibility() {
    image1 = new SimpleLayeredImage();
    image1.addLayer("Layer1");
    assertEquals(image1.getVisibility("Layer1"), true);
    image1.setVisibility("Layer1");
    assertEquals(image1.getVisibility("Layer1"), false);
  }

  @Test (expected = NullPointerException.class)
  public void testGetVisibilityWithNull() {
    image1.getVisibility(null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGetVisibilityWithInvalidLayer(){
    image1 = new SimpleLayeredImage();
    image1.addLayer("Layer1");
    image1.getVisibility("LLLL");
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

  @Test (expected = IllegalArgumentException.class)
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
  @Test (expected = NullPointerException.class)
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

  @Test (expected = IllegalStateException.class)
  public void testTopMostVisibleWithNoVisible() {
    image1 = new SimpleLayeredImage();
    image1.addLayer("Layer1");
    image1.setCurrentLayer("Layer1");
    image1.editCurrentLayer(image2);
    image1.setVisibility("Layer1");
    image1.addLayer("Layer2");
    image1.topMostVisibleLayer();
  }

  @Test (expected = NullPointerException.class)
  public void testNullImage() {
    image1 = null;
    image1.topMostVisibleLayer();
  }

  /**
   * Tests setCurrentLayer
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

  @Test (expected = IllegalArgumentException.class)
  public void testSetCurrentLayerWrongLayerName() {
    image1 = new SimpleLayeredImage();
    image1.addLayer("Layer1");
    image1.setCurrentLayer("Layer1");
    image1.editCurrentLayer(image3);
    image1.addLayer("Layer2");
    image1.setCurrentLayer("Layer4");
  }
}
