package view;

import controller.Features;
import controller.ImageUtils;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
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
  private final JButton load;
  private final JButton save;
  private final JPopupMenu filters;
  private final JButton createLayer;
  private final JButton changeVisibility;
  private final JList<String> layers;
  private Features features;
  private ImageProcessorLayerModel model;
  private JLabel imageToShow;
  private JLabel errorMessage;
  private JPanel ImagePanel;

  // the image to be edited
  // private final JLabel currentImage;


  public JFrameView(ImageProcessorModel model) {

    // constructs the frame that is initially visible
    super();

    // sets the given model as the model which this view works with
    this.model = new LayeredImageModel();

    setSize(200, 300);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    setLayout(new FlowLayout());

    application = new JPanel();
    application.setLayout(new BorderLayout());

    // boarder along the top
    JPanel imageEdits = new JPanel();
    imageEdits.setBorder(BorderFactory.createEmptyBorder());
    imageEdits.setLayout(new FlowLayout());

    // all of these will be along the top boarder
    JPanel loadFile = new JPanel();
    loadFile.setLayout(new FlowLayout());
    load = new JButton("Load Image");
    load.addActionListener(this);
    load.setActionCommand("load");
    loadFile.add(load);
    imageEdits.add(loadFile);

    save = new JButton("Save Image");
    save.addActionListener(this);
    save.setActionCommand("save");
    imageEdits.add(save);

    // filters popUp?
    filters = new JPopupMenu();
    JMenuItem sepia = new JMenuItem("sepia");
    sepia.addActionListener(this);
    filters.add(sepia);

    JMenuItem greyscale = new JMenuItem("greyscale");
    greyscale.addActionListener(this);
    filters.add(greyscale);

    JMenuItem sharpen = new JMenuItem("sharpen");
    sharpen.addActionListener(this);
    filters.add(sharpen);

    JMenuItem blur = new JMenuItem("blur");
    blur.addActionListener(this);
    filters.add(blur);

    imageEdits.add(filters);

    createLayer = new JButton("Create Layer");
    createLayer.addActionListener(this);
    createLayer.setActionCommand("create layer");
    imageEdits.add(createLayer);

    changeVisibility = new JButton("Change Visibility");
    changeVisibility.addActionListener(this);
    changeVisibility.setActionCommand("change visibility");
    imageEdits.add(changeVisibility);

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
    JScrollPane imageScrollPane = new JScrollPane(imageToShow);
    imageScrollPane.setPreferredSize(new Dimension(1000, 1000));
    ImagePanel.add(imageScrollPane);
    ImagePanel.add(errorMessage, BorderLayout.NORTH);
    application.add(ImagePanel, BorderLayout.WEST);

    add(application);
    setVisible(true);

    setFocusable(true);
    requestFocus();

    pack();

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "load":
        errorMessage.setText("");
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG, PPM, PNG", "jpg", "png", "ppm");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          Image toRead = new SimpleLayeredImage();
          try {
            toRead = ImageUtils.read(f.toString());
          } catch (IOException ioException) {
            ioException.printStackTrace();
          }
          try {
            model.editCurrentLayer(ImageUtils.read(f.toString()));
            toRead.pixArray();
            BufferedImage bi = new BufferedImage(toRead.getWidth(), toRead.getHeight(),
                BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < toRead.getHeight(); i++) {
              for (int j = 0; j < toRead.getWidth(); j++) {
                bi.setRGB(j, i, toRead.getPixel(j, i).getRGB());
              }
            }
            imageToShow.setIcon(new ImageIcon(bi));
          } catch (IOException j) {
            errorMessage.setText("Invalid file.");
          } catch (IllegalStateException l) {
            errorMessage.setText("Layer not created");
          }
        }
      case "save":
      case "create layer":
      case "change visibility":

    }
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
