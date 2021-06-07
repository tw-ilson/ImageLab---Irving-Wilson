package model;


public abstract class AbstractImageProcessorIO implements ImageProcessorIO{

  @Override
  public String importImage(Readable file) throws IllegalArgumentException {
    return null;
  }

  @Override
  public String export(FileType f) throws IllegalStateException {
    return null;
  }
}
