package controller;

import model.ImageProcessorModel;


public class SimpleImageController implements ImageController {

  private final ImageProcessorModel model;
  private final Readable rd;
  private final Appendable ap;

  public SimpleImageController(ImageProcessorModel model, Readable rd, Appendable ap) throws
      IllegalArgumentException{
    this.model = model;
    this.rd = rd;
    this.ap = ap;
    if(this.ap == null || this.rd == null){
      throw new IllegalArgumentException("Cannot provide null readable or nullable");
    }
  }

  @Override
  public void appOpen(String filename) throws IllegalArgumentException, IllegalStateException {
    if (filename == null) {
      throw new IllegalArgumentException("Cannot provide null arguments");
    }
    // try catch statement <-- need to throw exception
    model.importImage(filename);
  }


}
