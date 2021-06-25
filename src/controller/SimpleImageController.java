package controller;

import model.ImageProcessorLayerModel;
import view.ImageProcessorTextView;
import view.ImageProcessorView;


/**
 * A controller for a program that deals with processing images. This controller supports Models for
 * the program that utilize layers. It has a source of input, a destination for output, and it is
 * used with the run() method.
 */
public class SimpleImageController implements ImageProcessorController {

  private ImageProcessorLayerModel model;
  //private Readable input; //style checker made me comment this :/
  private ImageProcessorView output;

  private Readable input;

  /**
   * constructs a new Simple Image Controller with the specified model and output.
   */
  public SimpleImageController(ImageProcessorLayerModel model, Readable input, Appendable output) throws
      IllegalArgumentException {

    if (output == null) {
      throw new IllegalArgumentException("Text output source cannot be null");
    }
    if (model == null) {
      throw new IllegalArgumentException("Model provided cannot be null");
    }

    if (input == null) {
      throw new IllegalArgumentException("Input source provided cannot be null");
    }
    this.input = input;
    this.model = model;
    this.output = new ImageProcessorTextView(output);
  }

  @Override
  public void run() throws IllegalArgumentException, IllegalStateException {
    new LayerModelInputHandler().scanInput(model, input, output);
  }

}
