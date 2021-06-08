package model;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import model.Filters.IFilter;

public abstract class AbstractImageProcessorModel extends AbstractImageProcessorIO implements
    ImageProcessorModel {
  Map<String, IFilter> filters = new HashMap<>();


}
