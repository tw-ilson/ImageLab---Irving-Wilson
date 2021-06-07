package model;

import java.awt.Color;

public class AbstractImageProcessorModel extends AbstractImageProcessorIO implements
    ImageProcessorModel {

  @Override
  public Color[] getImageState() throws IllegalStateException {
    return mainImage.pixArray();
  }

  @Override
  public void applyFilter(Filter filter) {
    
  }
}
