package hw05;

import model.ImageProcessorModel;
import model.SimpleImageModel;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the SimpleImageProcessor class.
 */
public class TestSimpleImageProcessor {

  private ImageProcessorModel model;

  @Before
  public void initialize() {
    this.model = new SimpleImageModel();
  }

  /**
   * Tests for getImageState.
   */
  @Test (expected = IllegalStateException.class)
  public void testImageVersionsNotInstatiated() {
    model.getImagePixels();
  }

}
