package hw06;

import static org.junit.Assert.assertEquals;

import model.ImageProcessorModel;
import model.LayerImageModel;
import model.SimpleImageProcessorModel;
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
  private Image image2;


  @Before
  public void init() {
    image1 = new SimpleLayeredImage();
    model = new LayerImageModel();
    for (int i = 0; i < 9; i++) {
      toTest[i] = new LightColor(2, 1, 1);
    }
    this.image2 = new SimpleImage(toTest, 3, 3);

  }

  @Test (expected = IllegalStateException.class)
  public void testTopMostVisibleWithNoCreatedLayers() {
    image1.topMostVisibleLayer();
  }

  @Test (expected = NullPointerException.class)
  public void testTopMostVisibleLayer() {
    image1.addLayer("Layer1");
    image1.topMostVisibleLayer().pixArray();
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSetCurrentLayer() {
    image1.addLayer("Layer1");
    image1.setCurrentLayer("Layer1");
    // pass it an image to mimic
    image1.editCurrentLayer(image2);
  }


}
