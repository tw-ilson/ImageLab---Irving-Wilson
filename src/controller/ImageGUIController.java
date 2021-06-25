package controller;

import java.util.Objects;
import model.ImageProcessorLayerModel;
import view.ImageProcessorView;
import view.JFrameView;

public class ImageGUIController implements ImageProcessorController {

  ImageProcessorView view;
  ImageProcessorLayerModel model;
  Features features;

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
