package controller;

import java.util.Objects;
import model.ImageProcessorLayerModel;
import view.ImageProcessorView;
import view.JFrameView;

/**
 * A program controller that utilizes a Graphical user interface in addition to a layered model for
 * an image.
 */
public class ImageGUIController implements ImageProcessorController {

  ImageProcessorView view;
  ImageProcessorLayerModel model;
  Features features;

  /**
   * Constructs a Gui controller.
   * @param model
   */
  public ImageGUIController(ImageProcessorLayerModel model) {
    Objects.requireNonNull(model);
    this.model = model;
    this.features = new StandardFeatures(model);
  }

  @Override
  public void run() throws IllegalArgumentException, IllegalStateException {
    view = new JFrameView(features);

  }
}
