package model;

/**
 * This is an interface to handle Input/Output operations for an image Processing model. Enables
 * images to be imported and exported to and from the model in this API.
 * NOTE: generalized type?
 */
public interface ImageProcessorIO {

  /**
   * Imports the specified image file to the model.
   * NOTE: is readable the right parameter type to use here? Change to void method?
   * @return message detailing sucessful import.
   */
  public String importImage(Readable file) throws IllegalArgumentException;

  /**
   * Exports the stored image as a ???
   * @return the compiled image
   */
  public String export() throws IllegalStateException;
}
