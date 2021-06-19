package controller;

import java.util.Scanner;
import model.ImageProcessorLayerModel;
import model.ImageProcessorModel;


public class SimpleImageController implements ImageProcessorController {

  private ImageProcessorLayerModel model;
  private Readable input;
  private Appendable output;
  private boolean hasQuit;
  private boolean isActive;

  public SimpleImageController(ImageProcessorLayerModel model, Appendable output) throws
      IllegalArgumentException {
    this.model = model;
    this.output = output;

    if (this.output == null) {
      throw new IllegalArgumentException("Text output source cannot be null");
    }
    if (this.model == null) {
      throw new IllegalArgumentException("Model provided cannot be null");
    }
  }

  @Override
  public void run(Readable input) throws IllegalArgumentException, IllegalStateException {
    this.input = input;
    new LayerModelInputHandler().scanInput(model, input, output);
  }

}
