package model;


public abstract class AbstractImageProcessorIO implements ImageProcessorIO{

  Image mainImage;

  @Override
  public void importImage(String  fileName) throws IllegalArgumentException {
    ImageUtil util = new ImageUtil();


  }

  @Override
  public String export(FileType f) throws IllegalStateException {
    return null;
  }
}
