package hw06;

import static org.junit.Assert.assertEquals;

import controller.ImageProcessorController;
import controller.SimpleImageController;
import java.io.StringReader;
import model.ImageProcessorLayerModel;
import model.LayeredImageModel;
import org.junit.Before;
import org.junit.Test;
import view.ImageProcessorTextView;
import view.ImageProcessorView;

/**
 * Tests for the simpleImageController, specifically the viability of the batch commands. Sees if it
 * correctly delegates the intended functionality to the model that it is fed, and manipulates the
 * layered images in the specified ways.
 */
public class TestSimpleImageController {

  private ImageProcessorLayerModel model;
  private ImageProcessorController controller;
  private Readable in;
  private Appendable out;


  @Before
  public void init() {
    model = new LayeredImageModel();
  }


  @Test
  public void testQuit() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("quit");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "User quit.\n");
  }

  @Test
  public void testQuitQ() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("q");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "User quit.\n");
  }

  @Test
  public void testQuitWOtherCommand() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("dalkdsadquit");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "Unrecognized command.\n");
  }

  @Test
  public void testCreate() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("create layer layer1");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "Layer \"layer1\" created\n");
  }

  @Test
  public void testCreate2() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("create layered layer1");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "Not a valid command. Skipping.\n");
  }

  @Test
  public void testCreate3MultipleLayers() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("create layer layer1\ncreate layer layer2");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "Layer \"layer1\" created\n"
        + "Layer \"layer2\" created\n");
  }

  @Test
  public void testCurrent() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("create layer layer1\ncreate layer layer2\ncurrent layer1");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "Layer \"layer1\" created\n"
        + "Layer \"layer2\" created\n"
        + "Current layer: \"layer1\"\n");
  }

  @Test
  public void testCurrentInvalid() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("create layer layer1\ncreate layer layer2\ncurrent layer3");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "Layer \"layer1\" created\n"
        + "Layer \"layer2\" created\n"
        + "Requested layer layer3 does not exist. Skipping.\n");
  }

  @Test
  public void testCurrentEmpty() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("create layer layer1\ncreate layer layer2\ncurrent ");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "Layer \"layer1\" created\n"
        + "Layer \"layer2\" created\n"
        + "No layer specified. Skipping.\n");
  }

  @Test
  public void testLoad() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("create layer layer1\ncreate layer layer2\nload bay.png");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "Layer \"layer1\" created\n"
        + "Layer \"layer2\" created\n"
        + "IO error occurred. Make sure that the file path is valid. Skipping.\n");
  }

  @Test
  public void testLoadInvalidFilename() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("create layer layer1\ncreate layer layer2\nload bayasdads.png");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "Layer \"layer1\" created\n"
        + "Layer \"layer2\" created\n"
        + "IO error occurred. Make sure that the file path is valid. Skipping.\n");
  }

  @Test
  public void testLoadInvalid() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("create layer layer1\ncreate layer layer2\nload bay.png "
        + "bay_sharp.jpeg");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "Layer \"layer1\" created\n"
        + "Layer \"layer2\" created\n"
        + "IO error occurred. Make sure that the file path is valid. Skipping.\n");
  }

  @Test
  public void testLoadMultiple() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("create layer layer1\ncreate layer layer2\nload bay.png "
        + "\nload bay_sharp.jpeg");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "Layer \"layer1\" created\n"
        + "Layer \"layer2\" created\n"
        + "IO error occurred. Make sure that the file path is valid. Skipping.\n"
        + "Successfully loaded image into current layer.\n");
  }

  @Test
  public void testSave() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("create layer layer1\ncreate layer layer2\nload bay.png "
        + "\nload bay_sharp.jpeg\n save bay_sharp");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "Layer \"layer1\" created\n"
        + "Layer \"layer2\" created\n"
        + "IO error occurred. Make sure that the file path is valid. Skipping.\n"
        + "Successfully loaded image into current layer.\n"
        + "Successfully exported jpeg image: bay_sharp.jpeg\n");
  }


  @Test(expected = IllegalArgumentException.class)
  public void testSaveNoImageToSave() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("create layer layer1\nsave nothing.png");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "Layer \"layer1\" created\n"
        + "Incorrect arguments. Aborting.\n");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSaveWithInvisible() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("create layer layer1\ncreate layer layer2\nload bay.png "
        + "\nload bay_sharp.jpeg\ninvisible layer1"
        + "\ninvisible layer2\nsave s");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "Layer \"layer1\" created\n"
        + "Layer \"layer2\" created\n"
        + "Successfully loaded image into current layer.\n"
        + "Successfully loaded image into current layer.\n"
        + "Incorrect arguments. Aborting.\n");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSaveWInvisibleCurrentLayer() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("create layer layer1\nload bay.png\ncreate layer layer2"
        + "\nload bay_sharp.jpeg\ninvisible layer2\nsave s");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "Layer \"layer1\" created\n"
        + "Successfully loaded image into current layer.\n"
        + "Layer \"layer2\" created\n"
        + "Successfully loaded image into current layer.\n"
        + "Successfully exported jpeg image: s.jpeg\n");
  }

  /**
   * Tests for the filter command.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testFilter() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("create layer layer1\nload bay.png\nfilter blur\ncreate layer layer2"
        + "\nload bay_sharp.jpeg\ninvisible layer2\nsave s");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "Layer \"layer1\" created\n"
        + "Successfully loaded image into current layer.\n"
        + "blur applied to image.\n"
        + "Layer \"layer2\" created\n"
        + "Successfully loaded image into current layer.\n"
        + "Successfully exported jpeg image: s.jpeg\n");
  }


  @Test(expected = IllegalArgumentException.class)
  public void testFilterSharpen() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("create layer layer1\nload bay.png\nfilter sharpen\ncreate layer layer2"
        + "\nload bay_sharp.jpeg\ninvisible layer2\nsave s");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "Layer \"layer1\" created\n"
        + "Successfully loaded image into current layer.\n"
        + "sharpen applied to image.\n"
        + "Layer \"layer2\" created\n"
        + "Successfully loaded image into current layer.\n"
        + "Successfully exported jpeg image: s.jpeg\n");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFilterSepia() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("create layer layer1\nload bay.png\nfilter sepia\ncreate layer layer2"
        + "\nload bay_sharp.jpeg\ninvisible layer2\nsave s");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "Layer \"layer1\" created\n"
        + "Successfully loaded image into current layer.\n"
        + "sepia applied to image.\n"
        + "Layer \"layer2\" created\n"
        + "Successfully loaded image into current layer.\n"
        + "Successfully exported jpeg image: s.jpeg\n");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFilterGreyScale() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("create layer layer1\nload bay.png\nfilter greyscale\ncreate layer layer2"
        + "\nload bay_sharp.jpeg\ninvisible layer2\nsave s");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "Layer \"layer1\" created\n"
        + "Successfully loaded image into current layer.\n"
        + "greyscale applied to image.\n"
        + "Layer \"layer2\" created\n"
        + "Successfully loaded image into current layer.\n"
        + "Successfully exported jpeg image: s.jpeg\n");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFilterInvalidFilter() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("create layer layer1\nload bay.png\nfilter bop");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "Layer \"layer1\" created\n"
        + "Successfully loaded image into current layer.\n"
        + "No such filter.\n");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFilterBlankImage() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("filter sharpen");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "No image to apply filter to\n");
  }


  /**
   * Tests for the invisible command.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvisible() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("create layer layer1\nload bay.png\nfilter blur\ncreate layer layer2"
        + "\nload bay_sharp.jpeg\ninvisible layer2\ninvisible layer1\nsave s");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "Layer \"layer1\" created\n"
        + "Successfully loaded image into current layer.\n"
        + "blur applied to image.\n"
        + "Layer \"layer2\" created\n"
        + "Successfully loaded image into current layer.\n"
        + "Incorrect arguments. Aborting.\n");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvisibleInvalidLayer() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("create layer layer1\nload bay.png\nfilter blur\ncreate layer layer2"
        + "\nload bay_sharp.jpeg\ninvisible layer2\ninvisible layer5\n");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "Layer \"layer1\" created\n"
        + "Successfully loaded image into current layer.\n"
        + "blur applied to image.\n"
        + "Layer \"layer2\" created\n"
        + "Successfully loaded image into current layer.\n"
        + "Layer does not exist.\n");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testVisibleLayer() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("create layer layer1\nload bay.png\nfilter blur\ncreate layer layer2"
        + "\nload bay_sharp.jpeg\ninvisible layer2\ninvisible layer1\nvisible layer2\nsave s");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "Layer \"layer1\" created\n"
        + "Successfully loaded image into current layer.\n"
        + "blur applied to image.\n"
        + "Layer \"layer2\" created\n"
        + "Successfully loaded image into current layer.\n"
        + "Successfully exported jpeg image: s.jpeg\n");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testVisibleLayerInvalidLayerName() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("create layer layer1\nload bay.png\nfilter blur\ncreate layer layer2"
        + "\nload bay_sharp.jpeg\ninvisible layer2\ninvisible layer1\nvisible layer5000\nsave s");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "Layer \"layer1\" created\n"
        + "Successfully loaded image into current layer.\n"
        + "blur applied to image.\n"
        + "Layer \"layer2\" created\n"
        + "Successfully loaded image into current layer.\n"
        + "Layer does not exist.\n"
        + "Incorrect arguments. Aborting.\n");
  }

  @Test
  public void testRemove() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("create layer layer1\nremove layer1");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "Layer \"layer1\" created\n"
        + "\"layer1\" removed.\n");
  }

  @Test
  public void testRemoveInvalidLayer() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("create layer layer1\nremove layer5");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "Layer \"layer1\" created\n"
        + "Layer does not exist.\n");
  }

  @Test
  public void testEmptyImageRemove() {
    ImageProcessorView view = new ImageProcessorTextView(out);
    in = new StringReader("remove layer1");
    out = new StringBuilder();
    this.controller = new SimpleImageController(model, in, out);
    controller.run();
    assertEquals(out.toString(), "Welcome.\n"
        + "Layer does not exist.\n");
  }


}
