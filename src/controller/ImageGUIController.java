package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    this.view = new JFrameView(this.model);
  }

  @Override
  public void run() throws IllegalArgumentException, IllegalStateException {

  }
}
