package model;

/**
 * This is an interface to handle Input/Output operations for an image Processing model. Enables
 * images to be imported and exported to and from the model in this API.
 */
public interface ImageProcessorIO {

  /**
   * Imports the specified image file to be stored locally.
   * NOTE: Change to void method?
   *
   * @throws IllegalArgumentException if the file passed as argument is not supported.
   */
  public void importImage(Readable file) throws IllegalArgumentException;

  /**
   * Exports the stored image as a ??? NOTE: possibly add enumerated argument to represent supported
   * file formats.
   *
   * @return the compiled image
   * @throws IllegalStateException if the export requested is not possible
   */
  public String export(FileType f) throws IllegalStateException;
}
