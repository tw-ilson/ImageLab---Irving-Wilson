package controller;

import model.ImageProcessorLayerModel;


/**
 * A controller for a program that deals with processing images. This controller supports Models for
 * the program that utilize layers. It has a source of input, a destination for output, and it is
 * used with the run() method.
 */
public class SimpleImageController implements ImageProcessorController {

  private ImageProcessorLayerModel model;
  //private Readable input; //style checker made me comment this :/
  private Appendable output;

  /**
   * constructs a new Simple Image Controller with the specified model and output.
   */
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
    //this.input = input;
    new LayerModelInputHandler().scanInput(model, input, output);
  }

}
