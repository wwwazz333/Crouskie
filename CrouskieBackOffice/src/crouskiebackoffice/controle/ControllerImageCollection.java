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

/**
 * Contrôleur pour l'affichage et la sélection d'images de collections dans une
 * vue.
 */
public class ControllerImageCollection implements ActionListener {

    /**
     * Classe interne représentant une image avec son JLabel associé et sa
     * collection.
     */
    class Tuple {

        /**
         * L'image.
         */
        Picture pic;
        /**
         * Le JLabel associé à l'image.
         */
        JLabel image;
        /**
         * La collection associée à l'image.
         */
        Collection collection;

        /**
         * Crée un nouveau tuple avec l'image, le JLabel et la collection
         * donnés.
         *
         * @param pic l'image
         * @param image le JLabel associé à l'image
         * @param collection la collection associée à l'image
         */
        public Tuple(Picture pic, JLabel image, Collection collection) {
            this.pic = pic;
            this.image = image;
            this.collection = collection;
        }

    }

    /**
     * Le panneau de la vue.
     */
    private Container panel;
    /**
     * La vue parente.
     */
    private JDialog parent;
    /**
     * Le bouton d'ajout d'image.
     */
    private JButton addBtn;
    /**
     * L'image en mémoire.
     */
    private BufferedImage bfrImage;
    /**
     * La liste des tuples d'images de la vue.
     */
    private List<Tuple> elements = new LinkedList<>();
    /**
     * Le contrôleur de téléchargement de fichier.
     */
    private ControllerUploadFile controllerUploadFile;
    /**
     * Le DAO de collection.
     */
    private DAOCollection dao = new DAOCollection();
    /**
     * La collection sélectionnée.
     */
    private Collection collectionSelect;

    /**
     * Crée un nouveau contrôleur d'images de collection avec la vue parente, le
     * bouton d'ajout et la collection sélectionnée.
     *
     * @param parent la vue parente
     * @param addBtn le bouton d'ajout
     * @param collectionSelect la collection sélectionnée
     */
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

    /**
     * Retourne la liste des images de la vue.
     *
     * @return la liste des images de la vue
     */
    public List<Picture> getPictures() {
        List<Picture> pictures = new LinkedList<>();

        for (Tuple elem : elements) {
            pictures.add(elem.pic);
        }
        return pictures;
    }

    /**
     * Ajoute une image à la vue.
     *
     * @param tuple le tuple avec l'image, le JLabel et la collection associée
     */
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

    /**
     * Supprime une image de la vue.
     *
     * @param tuple le tuple avec l'image, le JLabel et la collection associée
     */
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

    /**
     * Redimensionne une image.
     *
     * @param srcImg l'image à redimensionner
     * @param width la largeur de l'image redimensionnée
     * @param height la hauteur de l'image redimensionnée
     * @return l'image redimensionnée
     */
    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    /**
     * Traite les événements d'action du contrôleur.
     *
     * @param ae l'événement d'action
     */
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

    /**
     * Demande à l'utilisateur de saisir la description d'une image.
     *
     * @return la description saisie par l'utilisateur ou null s'il n'a rien
     * saisi
     */
    private String getDescriptionImage() {
        return JOptionPane.showInputDialog("Nom de la collection");
    }

    /**
     * Demande à l'utilisateur de saisir la description d'une image.
     *
     * @param defaultValue la valeur par défaut
     * @return la description saisie par l'utilisateur ou null s'il n'a rien
     * saisi
     */
    private String getDescriptionImage(String defaultValue) {
        return JOptionPane.showInputDialog("Nom de la collection", defaultValue);
    }

    /**
     * Met à jour le panneau de la vue en ajoutant ou en supprimant des images.
     */
    private void updatePanel() {
        panel.revalidate();
        panel.repaint();
    }

}
