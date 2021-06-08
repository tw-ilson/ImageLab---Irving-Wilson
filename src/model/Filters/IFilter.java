package model.Filters;

import model.image.Image;

public interface IFilter {
  public Image apply(Image i);
  int MAX = 255;
  int MIN = 0;
}
