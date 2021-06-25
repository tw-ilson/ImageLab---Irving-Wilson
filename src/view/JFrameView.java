package view;

import controller.Features;
import controller.Features.IOAction;
import controller.Features.LayerAction;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.ImageProcessorLayerModel;
import model.ImageProcessorModel;
import model.LayeredImageModel;
import model.image.Image;
import model.image.SimpleLayeredImage;

public class JFrameView extends JFrame implements ActionListener,
    MouseListener, KeyListener, ListSelectionListener, ImageProcessorView {

  private final JPanel application;
  private final JMenuBar topBar;
  private final JMenu file;
  private final JMenuItem importButton;
  private final JMenuItem exportButton;
  private final JMenu layer;
  private final JMenuItem addLayer;
  private final JMenuItem rmLayer;
  private final JMenu filter;
  private final JMenuItem blur;
  private final JMenuItem sharpen;
  private final JMenuItem greyscale;
  private final JMenuItem sepia;
  private final JList<String> layers;


  private Features features;
  private JLabel imageToShow;
  private JLabel errorMessage;
  private JPanel ImagePanel;
  private JScrollPane imageScrollPane;

  // the image to be edited
 // private final JLabel currentImage;



  public JFrameView(Features features) {

    // constructs the frame that is initially visible
    super();
    this.features = features;
    features.addView(this);

    setSize(200, 300);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    setLayout( new FlowLayout());

    application = new JPanel();
    application.setLayout(new BorderLayout());

    // boarder along the top
    JPanel imageEdits = new JPanel();
    imageEdits.setBorder(BorderFactory.createEmptyBorder());
    imageEdits.setLayout(new FlowLayout());

    //create toolbar
    this.file = new JMenu("File");
    this.importButton = new JMenuItem("Import...");
    this.exportButton = new JMenuItem("Export...");
    this.layer = new JMenu("Layer");
    this.addLayer = new JMenuItem("Add");
    this.rmLayer = new JMenuItem("Remove");
    this.filter = new JMenu("Filter");
    this.blur = new JMenuItem("Blur");
    this.sharpen = new JMenuItem("Sharpen");
    this.greyscale = new JMenuItem("Greyscale");
    this.sepia = new JMenuItem("Sepia");
    this.topBar = createAppToolbar();
    this.setJMenuBar(topBar);

    application.add(imageEdits, BorderLayout.NORTH);

    // align this panel to the right of the image that is being edited//
    // panel for the layers selection
    JPanel layersPanel = new JPanel();
    layersPanel.setBorder(BorderFactory.createTitledBorder("Selection lists"));
    layersPanel.setLayout(new BoxLayout(layersPanel, BoxLayout.X_AXIS));

    // then, on side panel, we will have the layers listed
    // selection list
    DefaultListModel<String> dataForListOfStrings = new DefaultListModel<>();
    layers = new JList<>(dataForListOfStrings);
    layers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    layers.addListSelectionListener(this);
    layersPanel.add(layers);
    layersPanel.add(new JScrollBar());
    application.add(layersPanel, BorderLayout.EAST);

    // image itself
    ImagePanel = new JPanel();
    errorMessage = new JLabel();
    imageToShow = new JLabel();
    imageScrollPane = new JScrollPane(imageToShow);
    imageScrollPane.setPreferredSize(new Dimension(100, 100));
    ImagePanel.add(imageScrollPane);
    ImagePanel.add(errorMessage, BorderLayout.NORTH);
    application.add(ImagePanel, BorderLayout.WEST);

    add(application);
    setVisible(true);

    setFocusable(true);
    requestFocus();

    pack();
  }

  private JMenuBar createAppToolbar() {
    JMenuBar toolBar = new JMenuBar();
    file.add(importButton);
    this.importButton.addActionListener(this);
    importButton.setActionCommand("save");
    file.add(exportButton);
    this.exportButton.addActionListener(this);
    exportButton.setActionCommand("load");
    layer.add(addLayer);
    this.addLayer.addActionListener(this);
    addLayer.setActionCommand("addLayer");
    layer.add(rmLayer);
    this.rmLayer.addActionListener(this);
    rmLayer.setActionCommand("removeLayer");
    filter.add(blur);
    this.blur.addActionListener(this);
    blur.setActionCommand("blur");
    filter.add(sharpen);
    this.sharpen.addActionListener(this);
    sharpen.setActionCommand("sharpen");
    filter.add(greyscale);
    this.greyscale.addActionListener(this);
    greyscale.setActionCommand("greyscale");
    filter.add(sepia);
    this.sepia.addActionListener(this);
    sepia.setActionCommand("sepia");
    toolBar.add(file);
    toolBar.add(layer);
    toolBar.add(filter);
    file.setVisible(true);
    layer.setVisible(true);
    filter.setVisible(true);
    toolBar.setVisible(true);
    return toolBar;
  }



  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "save": {
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG & GIF Images", "jpg", "gif");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          try {
            features.handleIO(IOAction.IMPORT, f.getAbsolutePath());
          } catch (IOException ioException) {
            ioException.printStackTrace();
          }
        }
        break;
      }
      case "load": {
        final JFileChooser fchooser = new JFileChooser(".");
        int retvalue = fchooser.showSaveDialog(this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          try {
            features.handleIO(IOAction.EXPORT, f.getAbsolutePath());
          } catch (IOException ioException) {
            ioException.printStackTrace();
          }
        }
        break;
      }
      case "addLayer": {
        final JOptionPane newLayerDialog = new JOptionPane("New Layer",
            JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        newLayerDialog.setInitialValue("");
        String input = JOptionPane.showInputDialog("Name for new Layer:");
        features.handleLayers(LayerAction.ADD, input);
        break;
      }
      case "rmLayer":
        break;
      case "blur":
        break;
      case "sharpen":
        break;
      case "greyscale":
        break;
      case "sepia":
        break;
    }
    features.show();
  }

  @Override
  public void displayImage(Image image) {
    BufferedImage bi = new BufferedImage(image.getWidth(), image.getHeight(),
        BufferedImage.TYPE_INT_RGB);
    imageScrollPane.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        bi.setRGB(j, i, image.getPixel(j, i).getRGB());
      }
    }
    imageToShow.setIcon(new ImageIcon(bi));
  }


  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void keyReleased(KeyEvent e) {

  }

  @Override
  public void mouseClicked(MouseEvent e) {
    Object source = e.getSource();
//    if (source == load) {
//
//    } else if (source == save) {
//
//    } else if (source == createLayer) {
//
//    }
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

  @Override
  public void valueChanged(ListSelectionEvent e) {

  }

  @Override
  public void giveMessage(String text) throws IOException {

  }

}
