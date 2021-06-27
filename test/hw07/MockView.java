package hw07;

import controller.Features;
import controller.Features.FilterAction;
import controller.Features.IOAction;
import controller.Features.LayerAction;
import java.awt.event.ActionEvent;
import java.io.IOException;
import model.image.Image;
import view.ImageProcessorView;
import view.JFrameView;

public class MockView implements ImageProcessorView {

  public MockController features;


  @Override
  public void giveMessage(String text) throws IOException {

  }

  @Override
  public void displayImage(Image image) {

  }

  @Override
  public void displayLayers(String[] layerNames) throws IOException {

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
