package view;

import controller.Features;
import controller.Features.FilterAction;
import controller.Features.IOAction;
import controller.Features.LayerAction;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.color.Color;
import model.image.Image;

/**
 * JFrameView represents the graphical user interface for the application itself, presenting the
 * image in a viewable format that allows the user to edit/transform the layered image, before
 * saving the image to any location on the computer.
 */
public class JFrameView extends JFrame implements ActionListener, ListSelectionListener,
    ImageProcessorView {

  private final JPanel application;
  private final JMenuBar topBar;
  private final JMenu file;
  private final JMenuItem importButton;
  private final JMenuItem loadAll;
  private final JMenuItem exportButton;
  private final JMenuItem exportAll;
  private final JMenuItem quit;
  private final JMenuItem batch;
  private final JMenu layer;
  private final JMenuItem addLayer;
  private final JMenuItem rmLayer;
  private final JMenu filter;
  private final JMenuItem blur;
  private final JMenuItem sharpen;
  private final JMenuItem greyscale;
  private final JMenuItem sepia;
  private final JMenuItem mosaic;
  private final JMenu visibility;
  private final JMenuItem invisible;
  private final JMenuItem visible;
  private final JMenuItem resize;
  private JPanel layersPanel;
  private JList<String> layerList;
  private String current;

  private Features features;
  private JLabel imageToShow;
  private JLabel errorMessage;
  private JPanel ImagePanel;
  private JScrollPane imageScrollPane;


  /**
   * Initializes the JFrameView, building it from the components. Uses swing, most technical
   * functionality revolves around the documentation in the Java Swing library.
   *
   * @param features (the "features" are the actions which the graphical user interface has to
   *                 handle from the model itself).
   */
  public JFrameView(Features features) {

    // constructs the frame that is initially visible
    super();
    this.features = features;
    features.addView(this);

    setSize(200, 300);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    setLayout(new FlowLayout());

    application = new JPanel();
    application.setLayout(new BorderLayout());

    //create toolbar
    this.file = new JMenu("File");
    this.importButton = new JMenuItem("Import...");
    this.loadAll = new JMenuItem("Import all...");
    this.exportButton = new JMenuItem("Export...");
    this.exportAll = new JMenuItem("Export all...");
    this.quit = new JMenuItem("Quit");
    this.batch = new JMenuItem("Batch");
    this.layer = new JMenu("Layer");
    this.addLayer = new JMenuItem("Add");
    this.rmLayer = new JMenuItem("Remove");
    this.filter = new JMenu("Filter");
    this.blur = new JMenuItem("Blur");
    this.sharpen = new JMenuItem("Sharpen");
    this.greyscale = new JMenuItem("Greyscale");
    this.sepia = new JMenuItem("Sepia");
    this.mosaic = new JMenuItem("Mosaic");
    this.visibility = new JMenu("Visibility");
    this.invisible = new JMenuItem("Invisible");
    this.visible = new JMenuItem("Visible");
    this.resize = new JMenuItem("Resize...");
    this.topBar = createAppToolbar();
    this.setJMenuBar(topBar);

    // align this panel to the right of the image that is being edited//
    // panel for the layers selection
    this.layersPanel = new JPanel();
    layersPanel.setBorder(BorderFactory.createTitledBorder("Layers"));
    layersPanel.setLayout(new BoxLayout(layersPanel, BoxLayout.X_AXIS));

    // then, on side panel, we will have the layers listed
    // selection list

    //layerListModel = new DefaultListModel<>();
    layerList = new JList<>();
    layerList.setPreferredSize(new Dimension(100, 200));
    layerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    layerList.addListSelectionListener(this);
    layersPanel.add(layerList, BorderLayout.WEST);
    //layersPanel.add(new JScrollBar());
    application.add(layersPanel, BorderLayout.EAST);

    // image itself
    ImagePanel = new JPanel();
    errorMessage = new JLabel();
    imageToShow = new JLabel();
    imageScrollPane = new JScrollPane(imageToShow);
    imageScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    imageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    imageScrollPane.setPreferredSize(new Dimension(500, 500));
    ImagePanel.add(imageScrollPane);
    ImagePanel.add(errorMessage, BorderLayout.NORTH);
    application.add(ImagePanel, BorderLayout.WEST);

    add(application, BorderLayout.WEST);
    setVisible(true);
    setResizable(true);

    setFocusable(true);
    requestFocus();

    pack();
  }

  private JMenuBar createAppToolbar() {
    JMenuBar toolBar = new JMenuBar();
    file.add(importButton);
    importButton.addActionListener(this);
    importButton.setActionCommand("load");
    loadAll.addActionListener(this);
    loadAll.setActionCommand("load all");
    file.add(loadAll);
    file.add(exportButton);
    exportButton.addActionListener(this);
    exportButton.setActionCommand("save");
    exportAll.addActionListener(this);
    exportAll.setActionCommand("save all");
    file.add(exportAll);
    file.add(batch);
    batch.addActionListener(this);
    batch.setActionCommand("batch");
    file.add(quit);
    quit.addActionListener(this);
    quit.setActionCommand("quit");
    layer.add(addLayer);
    addLayer.addActionListener(this);
    addLayer.setActionCommand("addLayer");
    layer.add(rmLayer);
    rmLayer.addActionListener(this);
    rmLayer.setActionCommand("removeLayer");
    filter.add(blur);
    blur.addActionListener(this);
    blur.setActionCommand("blur");
    filter.add(sharpen);
    sharpen.addActionListener(this);
    sharpen.setActionCommand("sharpen");
    filter.add(greyscale);
    greyscale.addActionListener(this);
    greyscale.setActionCommand("greyscale");
    filter.add(sepia);
    sepia.addActionListener(this);
    sepia.setActionCommand("sepia");
    filter.add(mosaic);
    mosaic.addActionListener(this);
    mosaic.setActionCommand("mosaic");
    visibility.add(invisible);
    invisible.addActionListener(this);
    invisible.setActionCommand("invisible");
    visibility.add(visible);
    visible.addActionListener(this);
    visible.setActionCommand("visible");
    resize.addActionListener(this);
    resize.setActionCommand("resize");
    toolBar.add(file);
    toolBar.add(layer);
    toolBar.add(filter);
    toolBar.add(visibility);
    toolBar.add(resize);

    return toolBar;
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "load": {
        final JFileChooser fchooser = new JFileChooser(".");
        String[] formats = new String[ImageIO.getReaderFormatNames().length + 2];
        formats[0] = "ppm";
        formats[1] = ("PPM");
        for (int i = 2; i < formats.length; i++) {
          formats[i] = ImageIO.getReaderFormatNames()[i - 2];
        }
        fchooser.setFileFilter(new FileNameExtensionFilter("Supported Image types", formats));
        int retvalue = fchooser.showOpenDialog(this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          features.handleIO(IOAction.IMPORT, f.getAbsolutePath());
        }
        break;
      }
      case "save": {
        final JFileChooser fchooser = new JFileChooser(".");
        int retvalue = fchooser.showSaveDialog(this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          features.handleIO(IOAction.EXPORT, f.getAbsolutePath());
        }
        break;
      }
      case "save all": {
        // need to do this for each individual image
        String input = JOptionPane.showInputDialog(this, "Name of file to"
                + "save: ",
            "Save All", JOptionPane.QUESTION_MESSAGE);
        features.handleIO(IOAction.SAVEALL, input);
        break;
      }
      case "load all": {
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Supported Image Formats", ImageIO.getReaderFormatNames());
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          features.handleIO(IOAction.LOADALL, f.getAbsolutePath());
        }
        break;
      }
      case "addLayer": {
        String input = JOptionPane.showInputDialog(this, "Name for new Layer:",
            "layer name", JOptionPane.QUESTION_MESSAGE);
        current = input;
        features.handleLayers(LayerAction.ADD, input);
        break;
      }
      case "removeLayer": {
        String input = JOptionPane.showInputDialog(this, "Name of Layer to Remove:", "Layer Name",
            JOptionPane.QUESTION_MESSAGE);
        features.handleLayers(LayerAction.REMOVE, input);
        break;
      }
      case "resize": {
        Container dialogPanel = new JPanel();
        dialogPanel.setLayout(new GridLayout(0, 1));
        dialogPanel.setSize(200, 200);
        JLabel prompt = new JLabel("Enter new dimensions:");
        JLabel wLabel = new JLabel("Width:");
        JLabel hLabel = new JLabel("Height:");
        JSpinner widthSpinner = new JSpinner(new SpinnerNumberModel(imageToShow.getWidth(), 1, imageToShow.getWidth(), 1));
        JSpinner heightSpinner = new JSpinner(new SpinnerNumberModel(imageToShow.getHeight(), 1, imageToShow.getHeight(), 1));
        heightSpinner.setValue(imageToShow.getHeight());
        dialogPanel.add(prompt);
        dialogPanel.add(wLabel);
        wLabel.setLabelFor(widthSpinner);
        dialogPanel.add(widthSpinner);
        dialogPanel.add(hLabel);
        hLabel.setLabelFor(heightSpinner);
        dialogPanel.add(heightSpinner);
        int result = JOptionPane
            .showConfirmDialog(this, dialogPanel, "Resize", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
          try {
            features.resize((Integer) widthSpinner.getValue(), (Integer) heightSpinner.getValue());
          } catch (IllegalStateException ise) {
            safeMessage("No Image to resize!");
          } catch (IllegalArgumentException iae) {
            safeMessage("Invalid size.");
          }
        }
        break;
      }
      case "blur":
        features.handleFilter(FilterAction.BLUR);
        break;
      case "sharpen":
        features.handleFilter(FilterAction.SHARPEN);
        break;
      case "greyscale":
        features.handleFilter(FilterAction.GREYSCALE);
        break;
      case "sepia":
        features.handleFilter(FilterAction.SEPIA);
        break;
      case "mosaic":
        String anotherInput = JOptionPane.showInputDialog(this, "Enter the number"
                + " of seeds you wish to see in the mosaic: ", "NumSeeds",
            JOptionPane.QUESTION_MESSAGE);
        try {
          features.mosaic(Integer.parseInt(anotherInput));
        } catch (NumberFormatException nfe) {
          safeMessage("Please provide a number for seed.");
        }
        break;
      case "invisible":
        features.handleLayers(LayerAction.INVISIBLE, current);
        break;
      case "visible":
        features.handleLayers(LayerAction.VISIBLE, current);
        break;
      case "batch":

        final JFileChooser fchooser = new JFileChooser(".");

        fchooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        int retvalue = fchooser.showOpenDialog(this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          features.handleIO(IOAction.BATCH, f.getAbsolutePath());
        }
        break;
      case "quit":
        int saveChoice = JOptionPane
            .showConfirmDialog(this, "Save changes?", "Quit", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (saveChoice == JOptionPane.YES_OPTION) {
          this.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "save all"));
        } else if (saveChoice == JOptionPane.CANCEL_OPTION) {
          break;
        }
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
    features.show();
    features.listLayers();
  }

  @Override
  public void displayImage(Image image) {
    if (image == null) {
      throw new IllegalStateException("Given image cannot be null.");
    }
    BufferedImage buf = new BufferedImage(image.getWidth(), image.getHeight(),
        BufferedImage.TYPE_INT_RGB);
    if (!(image.pixArray().length == 1)) {
      Dimension panelDimension = new Dimension(image.getWidth(), image.getHeight());
      imageScrollPane.setPreferredSize(panelDimension);
      this.ImagePanel.setSize(panelDimension);
      Dimension appDimension = new Dimension();
      appDimension.setSize(panelDimension.width * 1.2 + layerList.getWidth(),
          panelDimension.height * 1.2 + topBar.getHeight());
      setSize(appDimension);
    }
    int[] rgbArray = Arrays.stream(image.pixArray()).mapToInt(Color::getRGB).toArray();
    buf.setRGB(0, 0, buf.getWidth(), buf.getHeight(), rgbArray, 0, buf.getWidth());
    imageToShow.setIcon(new ImageIcon(buf));
  }

  @Override
  public void displayLayers(String[] layerNames) {
    layerList.setListData(layerNames);
  }


  @Override
  public void giveMessage(String text) throws IOException {
    JOptionPane.showMessageDialog(this, text, "Alert", JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    // sets the current layer to the one selected
    features.handleLayers(LayerAction.SETCURRENT, this.layerList.getSelectedValue());
    current = this.layerList.getSelectedValue();
  }

  /**
   * So I don't have to repeat catch blocks.
   *
   * @param text the text to display
   */
  private void safeMessage(String text) {
    try {
      giveMessage(text);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
