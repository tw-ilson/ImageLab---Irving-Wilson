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
   * @throws IllegalArgumentException if the file passed as argument is not supported.
   */
  public String importImage(Readable file) throws IllegalArgumentException;

  /**
   * Exports the stored image as a ???
   * NOTE: possibly add enumerated argument to represent supported file formats.
   * @return the compiled image
   * @throws IllegalStateException if the export requested is not possible
   */
  public String export() throws IllegalStateException;

  /**
   * Gets the current image state as an array of hexadecimal integers representing the pixels of the image
   * @return pixel array
   * @throws IllegalStateException if the pixels cannot be read from the current image
   */
  public int[] getImageState() throws IllegalStateException;
}
