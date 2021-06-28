package hw07;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * Tests for the JFrameView, specifically seeing if it responds correctly to events by observing
 * changes in the appendable output in the model, which is tracking alterations to the current
 * condition of the data, listening for events enacted by the user.
 */
public class TestJFrameView {


  @Test
  public void testSaveButton() {
    Appendable out = new StringBuilder();
    MockController features = new MockController(out);
    MockView view = new MockView();
    view.features = features;
    view.saveEvent();
    assertEquals("save", out.toString());
  }

  @Test
  public void testLoadButton() {
    Appendable out = new StringBuilder();
    MockController features = new MockController(out);
    MockView view = new MockView();
    view.features = features;
    view.loadEvent();
    assertEquals("load", out.toString());
  }

  @Test
  public void testLoadAllEvent() {
    Appendable out = new StringBuilder();
    MockController features = new MockController(out);
    MockView view = new MockView();
    view.features = features;
    view.loadAllEvent();
    assertEquals("load all", out.toString());
  }

  @Test
  public void testSaveAllEvent() {
    Appendable out = new StringBuilder();
    MockController features = new MockController(out);
    MockView view = new MockView();
    view.features = features;
    view.saveAllEvent();
    assertEquals("save all", out.toString());
  }

  @Test
  public void testBatchEvent() {
    Appendable out = new StringBuilder();
    MockController features = new MockController(out);
    MockView view = new MockView();
    view.features = features;
    view.batchEvent();
    assertEquals("batch", out.toString());
  }

  @Test
  public void testBlurEvent() {
    Appendable out = new StringBuilder();
    MockController features = new MockController(out);
    MockView view = new MockView();
    view.features = features;
    view.blurEvent();
    assertEquals("blur", out.toString());
  }

  @Test
  public void testSepiaEvent() {
    Appendable out = new StringBuilder();
    MockController features = new MockController(out);
    MockView view = new MockView();
    view.features = features;
    view.sepiaEvent();
    assertEquals("sepia", out.toString());
  }

  @Test
  public void testCustomEvent() {
    Appendable out = new StringBuilder();
    MockController features = new MockController(out);
    MockView view = new MockView();
    view.features = features;
    view.customEvent();
    assertEquals("custom", out.toString());
  }

  @Test
  public void testSharpenEvent() {
    Appendable out = new StringBuilder();
    MockController features = new MockController(out);
    MockView view = new MockView();
    view.features = features;
    view.sharpenEvent();
    assertEquals("sharpen", out.toString());
  }

  @Test
  public void testGreyScaleEvent() {
    Appendable out = new StringBuilder();
    MockController features = new MockController(out);
    MockView view = new MockView();
    view.features = features;
    view.greyscaleEvent();
    assertEquals("greyscale", out.toString());
  }

  @Test
  public void testAddEvent() {
    Appendable out = new StringBuilder();
    MockController features = new MockController(out);
    MockView view = new MockView();
    view.features = features;
    view.addEvent();
    assertEquals("add", out.toString());
  }

  @Test
  public void testRemoveEvent() {
    Appendable out = new StringBuilder();
    MockController features = new MockController(out);
    MockView view = new MockView();
    view.features = features;
    view.removeEvent();
    assertEquals("remove", out.toString());
  }

  @Test
  public void testVisibleEvent() {
    Appendable out = new StringBuilder();
    MockController features = new MockController(out);
    MockView view = new MockView();
    view.features = features;
    view.visibleEvent();
    assertEquals("visible", out.toString());
  }

  @Test
  public void testInvisibleEvent() {
    Appendable out = new StringBuilder();
    MockController features = new MockController(out);
    MockView view = new MockView();
    view.features = features;
    view.invisibleEvent();
    assertEquals("invisible", out.toString());
  }

  @Test
  public void testSetCurrentEvent() {
    Appendable out = new StringBuilder();
    MockController features = new MockController(out);
    MockView view = new MockView();
    view.features = features;
    view.setCurrentEvent();
    assertEquals("set current", out.toString());
  }

  @Test
  public void testResizeEvent() {
    Appendable out = new StringBuilder();
    MockController features = new MockController(out);
    MockView view = new MockView();
    view.features = features;
    view.resizeEvent();
    assertEquals("resize of image with new dimensions of 0 by 0.", out.toString());
  }

  @Test
  public void testMosaicEvent() {
    Appendable out = new StringBuilder();
    MockController features = new MockController(out);
    MockView view = new MockView();
    view.features = features;
    view.mosaicEvent();
    assertEquals("mosaic on model with 1000 seeds", out.toString());
  }


}
