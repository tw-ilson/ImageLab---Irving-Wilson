package model;

public class AbstractImage implements Image{
  protected int width;
  protected int height;
  protected int[] pixArray;


  @Override
  public int[] pixArray() throws IllegalStateException {
    return pixArray.clone();
  }

  @Override
  public int getPixel(int x, int y) {
    return pixArray[width*y + x];
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }
}
