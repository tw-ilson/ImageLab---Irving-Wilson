package model.Filters;

import model.image.Image;

public interface IFilter {
  public void apply(Image i);
  int MAX = 255;
}
