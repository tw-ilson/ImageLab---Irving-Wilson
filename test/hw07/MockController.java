package hw07;

import controller.Features;
import controller.ImageProcessorController;
import java.io.IOException;
import java.util.Objects;
import view.ImageProcessorView;

/**
 * A mock controller to allow for the testing of the event functionality within the view. How to
 * stimulate the press of a button, while not being able to really interact with the GUI through the
 * testing syntax itself? This class allows for this behaviour.
 */
public class MockController implements ImageProcessorController, Features {

  private final Appendable out;

  public MockController(Appendable out) {
    this.out = Objects.requireNonNull(out);
  }

  @Override
  public void run() throws IllegalArgumentException, IllegalStateException {
    // empty
  }

  @Override
  public void handleIO(IOAction action, String fileName) {
    switch (action) {
      case IMPORT:
        try {
          out.append("load");
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      case EXPORT:
        try {
          out.append("save");
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      case LOADALL:
        try {
          out.append("load all");
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      case SAVEALL:
        try {
          out.append("save all");
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      case BATCH:
        try {
          out.append("batch");
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      default:
        try {
          out.append("Nothing.");
        } catch (IOException e) {
          e.printStackTrace();
        }
    }
  }

  @Override
  public void handleFilter(FilterAction action) {
    switch (action) {
      case BLUR:
        try {
          out.append("blur");
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      case SEPIA:
        try {
          out.append("sepia");
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      case CUSTOM:
        try {
          out.append("custom");
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      case SHARPEN:
        try {
          out.append("sharpen");
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      case GREYSCALE:
        try {
          out.append("greyscale");
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      default:
        try {
          out.append("Nothing.");
        } catch (IOException e) {
          e.printStackTrace();
        }
    }
  }

  @Override
  public void handleLayers(LayerAction action, String layerName) {
    switch (action) {
      case ADD:
        try {
          out.append("add");
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      case REMOVE:
        try {
          out.append("remove");
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      case VISIBLE:
        try {
          out.append("visible");
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      case INVISIBLE:
        try {
          out.append("invisible");
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      case SETCURRENT:
        try {
          out.append("set current");
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      default:
        try {
          out.append("nothing");
        } catch (IOException e) {
          e.printStackTrace();
        }
    }
  }

  @Override
  public void listLayers() {
    // empty
  }

  @Override
  public void mosaic(int nSeeds) {
    try {
      out.append("mosaic on model with " + nSeeds + " seeds");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void resize(int w, int h) {
    try {
      out.append("resize of image with new dimensions of " + w + " by " + h + ".");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void addView(ImageProcessorView view) {
    try {
      out.append("Add view.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void show() {
    try {
      out.append("show image.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
