package hw07;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import controller.Features;
import controller.Features.FilterAction;
import controller.Features.IOAction;
import controller.Features.LayerAction;
import controller.ImageUtils;
import controller.StandardFeatures;
import java.io.IOException;
import model.ImageProcessorLayerModel;
import model.LayeredImageModel;
import model.color.LightColor;
import model.image.Image;
import model.image.SimpleImage;
import org.junit.Before;
import org.junit.Test;
import view.JFrameView;

public class TestFeatures {

  private ImageProcessorLayerModel model;
  private Image image1;
  private Features features;
  private JFrameView view;


  @Before
  public void init() {
    this.model = new LayeredImageModel();
    LightColor[] toTest = new LightColor[9];
    this.image1 = new SimpleImage(toTest, 3, 3);
    this.features = new StandardFeatures(model, view);
    this.view = new JFrameView(features);
  }

  @Test
  public void testImport() throws IOException {
    features.handleLayers(LayerAction.ADD, "layer1");
    features.handleIO(IOAction.IMPORT, "photos/Moon.jpeg");
    assertEquals(model.getImage().getHeight(),
        ImageUtils.read("photos/Moon.jpeg").getHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidImport() throws IOException {
    features.handleLayers(LayerAction.ADD, "layer1");
    features.handleIO(IOAction.IMPORT, "2swerqhiefq");
    assertEquals(model.getImage(), "");
  }

  @Test
  public void testExport() throws IOException {
    features.handleLayers(LayerAction.ADD, "layer1");
    features.handleIO(IOAction.IMPORT, "photos/Moon.jpeg");
    assertEquals(model.getImage().getHeight(),
        ImageUtils.read("photos/Moon.jpeg").getHeight());
    features.handleIO(IOAction.EXPORT, "new_moon.jpeg");
    assertEquals(ImageUtils.read("new_moon.jpeg").getHeight(),
        ImageUtils.read("photos/Moon.jpeg").getHeight());
  }

  @Test
  public void invalidExport() throws IOException {
    features = new StandardFeatures(model, new MockView());
    features.handleLayers(LayerAction.ADD, "layer1");
    features.handleIO(IOAction.IMPORT, "photos/Moon.jpeg");
    assertEquals(model.getImage().getHeight(),
        ImageUtils.read("photos/Moon.jpeg").getHeight());
    features.handleIO(IOAction.EXPORT, "new_moon.2209*#@&");
  }

  @Test(expected = NullPointerException.class)
  public void invalidExport2() throws IOException {
    features = new StandardFeatures(model, new MockView());
    features.handleLayers(LayerAction.ADD, null);
    features.handleIO(IOAction.IMPORT, "photos/Moon.jpeg");
    assertEquals(model.getImage().getHeight(),
        ImageUtils.read("photos/Moon.jpeg").getHeight());
    features.handleIO(IOAction.EXPORT, null);
  }


  @Test
  public void testLoadAll() throws IOException {
    features.handleLayers(LayerAction.ADD, "layer1");
    features.handleLayers(LayerAction.ADD, "layer2");
    features.handleLayers(LayerAction.SETCURRENT, "layer1");
    features.handleIO(IOAction.LOADALL, "photos/Moon.jpeg");
    assertEquals(model.getImage().getHeight(),
        ImageUtils.read("photos/Moon.jpeg").getHeight());
  }

  @Test
  public void testSaveAll() throws IOException {
    features.handleLayers(LayerAction.ADD, "layer1");
    features.handleLayers(LayerAction.ADD, "layer2");
    features.handleLayers(LayerAction.SETCURRENT, "layer1");
    features.handleIO(IOAction.LOADALL, "photos/Moon.jpeg");
    assertEquals(model.getImage().getHeight(),
        ImageUtils.read("photos/Moon.jpeg").getHeight());
    features.handleIO(IOAction.SAVEALL, "moon_versions.jpeg");
    assertEquals(ImageUtils.read("moon_versions1.jpeg").getHeight(),
        ImageUtils.read("photos/Moon.jpeg").getHeight());
  }

  @Test
  public void testHandleBlur() throws IOException {
    features.handleLayers(LayerAction.ADD, "layer1");
    features.handleIO(IOAction.IMPORT, "photos/Moon.jpeg");
    features.handleFilter(FilterAction.BLUR);
    assertNotEquals(model.getImage().getHeight(),
        ImageUtils.read("photos/Moon.jpeg").getHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHandleBlurWrong() throws IOException {
    features.handleLayers(LayerAction.ADD, "layer1");
    features.handleIO(IOAction.IMPORT, "204920498209");
    features.handleFilter(FilterAction.BLUR);
    assertNotEquals(model.getImage().getHeight(),
        ImageUtils.read("photos/Moon.jpeg").getHeight());
  }

  @Test(expected = NullPointerException.class)
  public void testHandleBlurNull() throws IOException {
    features.handleLayers(LayerAction.ADD, "layer1");
    features.handleIO(IOAction.IMPORT, null);
    features.handleFilter(FilterAction.BLUR);
    assertNotEquals(model.getImage().getHeight(),
        ImageUtils.read("photos/Moon.jpeg").getHeight());
  }

  @Test
  public void testHandleSharpen() throws IOException {
    features.handleLayers(LayerAction.ADD, "layer1");
    features.handleIO(IOAction.IMPORT, "photos/Moon.jpeg");
    features.handleFilter(FilterAction.SHARPEN);
    assertNotEquals(model.getImage().getHeight(),
        ImageUtils.read("photos/Moon.jpeg").getHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHandleSharpenWrong() throws IOException {
    features.handleLayers(LayerAction.ADD, "layer1");
    features.handleIO(IOAction.IMPORT, "204920498209");
    features.handleFilter(FilterAction.SHARPEN);
    assertNotEquals(model.getImage().getHeight(),
        ImageUtils.read("photos/Moon.jpeg").getHeight());
  }

  @Test(expected = NullPointerException.class)
  public void testHandleSharpenNull() throws IOException {
    features.handleLayers(LayerAction.ADD, "layer1");
    features.handleIO(IOAction.IMPORT, null);
    features.handleFilter(FilterAction.SHARPEN);
    assertNotEquals(model.getImage().getHeight(),
        ImageUtils.read("photos/Moon.jpeg").getHeight());
  }

  @Test
  public void testHandleSepia() throws IOException {
    features.handleLayers(LayerAction.ADD, "layer1");
    features.handleIO(IOAction.IMPORT, "photos/Moon.jpeg");
    features.handleFilter(FilterAction.SEPIA);
    assertNotEquals(model.getImage().getHeight(),
        ImageUtils.read("photos/Moon.jpeg").getHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHandleSepiaWrong() throws IOException {
    features.handleLayers(LayerAction.ADD, "layer1");
    features.handleIO(IOAction.IMPORT, "204920498209");
    features.handleFilter(FilterAction.BLUR);
    assertNotEquals(model.getImage().getHeight(),
        ImageUtils.read("photos/Moon.jpeg").getHeight());
  }

  @Test(expected = NullPointerException.class)
  public void testHandleSepiaNull() throws IOException {
    features.handleLayers(LayerAction.ADD, "layer1");
    features.handleIO(IOAction.IMPORT, null);
    features.handleFilter(FilterAction.SEPIA);
    assertNotEquals(model.getImage().getHeight(),
        ImageUtils.read("photos/Moon.jpeg").getHeight());
  }


}
