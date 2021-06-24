package view;

import controller.Features;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.ImageProcessorLayerModel;

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


  // the image to be edited
 // private final JLabel currentImage;



  public JFrameView(ImageProcessorLayerModel model) {

    // constructs the frame that is initially visible
    super();


    setSize(200, 300);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    setLayout( new FlowLayout());

    application = new JPanel();
    application.setLayout(new BorderLayout());

    // boarder along the top
    JPanel imageEdits = new JPanel();
    imageEdits.setBorder(BorderFactory.createEmptyBorder());
    imageEdits.setLayout(new FlowLayout());


    // all of these will be along the top boarder
    load = new JButton("Load Image");
    load.addActionListener(this);
    load.setActionCommand("load");
    imageEdits.add(load);

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


    add(application);
    setVisible(true);

    setFocusable(true);
    requestFocus();

    pack();

  }



  @Override
  public void actionPerformed(ActionEvent e) {

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
