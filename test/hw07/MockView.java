package hw07;

import controller.Features.FilterAction;
import controller.Features.IOAction;
import controller.Features.LayerAction;
import java.io.IOException;
import model.image.Image;
import view.ImageProcessorView;

/**
 * This is a mock view class which allows the client to interact with the inherent functionality of
 * the program, specifically the events themselves. Thus, the client can test the responses through
 * the appendable output of the mock controller, to ensure this communication is happening
 * correctly.
 */
public class MockView implements ImageProcessorView {

  public MockController features;


  @Override
  public void giveMessage(String text) throws IOException {
    //empty
  }

  @Override
  public void displayImage(Image image) {
    //empty
  }

  @Override
  public void displayLayers(String[] layerNames) throws IOException {
    // empty
  }

  public void saveEvent() {
    features.handleIO(IOAction.EXPORT, "");
  }

  public void loadEvent() {
    features.handleIO(IOAction.IMPORT, "");
  }

  public void batchEvent() {
    features.handleIO(IOAction.BATCH, "");
  }

  public void loadAllEvent() {
    features.handleIO(IOAction.LOADALL, "");
  }

  public void saveAllEvent() {
    features.handleIO(IOAction.SAVEALL, "");
  }

  public void addEvent() {
    features.handleLayers(LayerAction.ADD, "");
  }

  public void removeEvent() {
    features.handleLayers(LayerAction.REMOVE, "");
  }

  public void visibleEvent() {
    features.handleLayers(LayerAction.VISIBLE, "");
  }

  public void invisibleEvent() {
    features.handleLayers(LayerAction.INVISIBLE, "");
  }

  public void customEvent() {
    features.handleFilter(FilterAction.CUSTOM);
  }

  public void setCurrentEvent() {
    features.handleLayers(LayerAction.SETCURRENT, "");
  }

  public void resizeEvent() {
    features.resize(0, 0);
  }

  public void mosaicEvent() {
    features.mosaic(1000);
  }

  public void blurEvent() {
    features.handleFilter(FilterAction.BLUR);
  }

  public void sharpenEvent() {
    features.handleFilter(FilterAction.SHARPEN);
  }

  public void sepiaEvent() {
    features.handleFilter(FilterAction.SEPIA);
  }

  public void greyscaleEvent() {
    features.handleFilter(FilterAction.GREYSCALE);
  }

}
