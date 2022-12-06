package crouskiebackoffice.controle;

import crouskiebackoffice.model.Collection;
import crouskiebackoffice.model.FileDownloader;
import crouskiebackoffice.model.Picture;
import crouskiebackoffice.model.dao.DAOCollection;
import crouskiebackoffice.view.PopupMenuImage;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ControllerImageCollection implements ActionListener {

    class Tuple {

        Picture pic;
        JLabel image;
        Collection collection;

        public Tuple(Picture pic, JLabel image, Collection collection) {
            this.pic = pic;
            this.image = image;
            this.collection = collection;
        }

    }

    private Container panel;
    private JDialog parent;
    private JButton addBtn;

    private BufferedImage bfrImage;
    private List<Tuple> elements = new LinkedList<>();

    private ControllerUploadFile controllerUploadFile;

    private DAOCollection dao = new DAOCollection();
    private Collection collectionSelect;

    public ControllerImageCollection(JDialog parent, JButton addBtn, Collection collectionSelect) {
        this.panel = parent.getContentPane();
        this.parent = parent;
        this.addBtn = addBtn;
        this.collectionSelect = collectionSelect;

        this.addBtn.addActionListener(this);
        controllerUploadFile = new ControllerUploadFile(panel);

        ErrorHandeler.getInstance().exec(() -> {
            List<Collection> collections = dao.getAllData();
            for (Collection coll : collections) {
                System.out.println(coll.getPathPicture());
                Picture pic = new Picture(coll.getPathPicture(), coll.getName(), null);
                addPicture(new Tuple(pic, null, coll));
            }
            return true;
        });

    }

    public List<Picture> getPictures() {
        List<Picture> pictures = new LinkedList<>();

        for (Tuple elem : elements) {
            pictures.add(elem.pic);
        }
        return pictures;
    }

    private void addPicture(Tuple tuple) {
        elements.add(tuple);
        ErrorHandeler.getInstance().exec(() -> {
            String[] partOfUrl = tuple.pic.getPath().split("/");

            bfrImage = FileDownloader.downloadImageFromUrl(tuple.pic.getPath(), partOfUrl[partOfUrl.length - 1]);

            double ratio = (double) bfrImage.getWidth() / (double) bfrImage.getHeight();

            ImageIcon img = new ImageIcon(getScaledImage(new ImageIcon(bfrImage).getImage(), 200, (int) (200 / ratio)));
            img.setDescription(tuple.pic.getAlt());

            JLabel imageDisplay = new JLabel(img);
            tuple.image = imageDisplay;

            imageDisplay.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent evt) {
                    if (evt.getButton() == MouseEvent.BUTTON1) {
                        collectionSelect.setName(tuple.collection.getName());
                        collectionSelect.setPathPicture(tuple.collection.getPathPicture());
                        collectionSelect.setId(tuple.collection.getId());
                        parent.dispose();
                    } else if (evt.getButton() == MouseEvent.BUTTON3) {
                        Point mousePos = evt.getPoint();

                        var popup = new PopupMenuImage(tuple.pic,
                                () -> {
                                    String nameCollection = getDescriptionImage(tuple.collection.getName());
                                    if (nameCollection != null) {
                                        tuple.collection.setName(nameCollection);

                                        ErrorHandeler.getInstance().exec(() -> {
                                            dao.insertOrUpdate(tuple.collection);
                                            return true;

                                        });
                                    }

                                }, () -> {
                                    removePicture(tuple);
                                });
                        popup.show(imageDisplay, mousePos.x, mousePos.y);
                    }
                }
            });

            this.panel.add(imageDisplay);

            updatePanel();
            return true;
        });
    }

    private void removePicture(Tuple tuple) {
        ErrorHandeler.getInstance().exec(() -> {
            System.out.println("Suppression de l'image...");
            dao.remove(tuple.collection);
            this.panel.remove(tuple.image);
            elements.remove(tuple);
            updatePanel();
            System.out.println("Image supprimer.");
            return true;
        });

    }

    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String pathToImage = controllerUploadFile.choose();
        if (pathToImage != null) {
            ErrorHandeler.getInstance().exec(() -> {
                BufferedImage image = ImageIO.read(new File(pathToImage));
                String urlRelativeToOnlineImage = FileDownloader.uploadImage(image);
                if (urlRelativeToOnlineImage != null) {
                    String nomCollection = getDescriptionImage();
                    if (nomCollection == null) {
                        nomCollection = "";
                    }
                    Picture pic = new Picture(urlRelativeToOnlineImage, nomCollection, null);
                    Collection collection = new Collection(-1, nomCollection, urlRelativeToOnlineImage);
                    dao.insertOrUpdate(collection);
                    addPicture(new Tuple(pic, null, collection));
                    updatePanel();
                }
                return true;
            });

        }
    }

    private String getDescriptionImage() {
        return JOptionPane.showInputDialog("Nom de la collection");
    }

    private String getDescriptionImage(String defaultValue) {
        return JOptionPane.showInputDialog("Nom de la collection", defaultValue);
    }

    private void updatePanel() {
        panel.revalidate();
        panel.repaint();
    }

}
