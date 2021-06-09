package model.Filters;

import java.util.function.Function;
import model.image.Image;

public interface Filter extends Function<Image, Image> {
  public Image apply(Image i);
  int MAX = 255;
  int MIN = 0;

}
