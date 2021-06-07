package model;


public abstract class AbstractImageProcessorIO implements ImageProcessorIO{

  Image mainImage;

  @Override
  public void importImage(Readable file) throws IllegalArgumentException {
    return null;
  }

  @Override
  public String export(FileType f) throws IllegalStateException {
    return null;
  }
}
