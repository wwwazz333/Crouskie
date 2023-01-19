package crouskiebackoffice.controle;

import crouskiebackoffice.model.AttachImage;
import crouskiebackoffice.model.FileDownloader;
import crouskiebackoffice.model.Picture;
import crouskiebackoffice.model.dao.DAOProduct;
import crouskiebackoffice.view.PopupMenuImage;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Contrôleur pour les images associées à un produit. Gère l'ajout et la
 * suppression d'images, ainsi que la modification de leur description.
 * Implémente l'interface ActionListener pour réagir aux événements déclenchés
 * par des actions de l'utilisateur.
 */
public class ControllerImageProduct implements ActionListener {

    // AttachImage et JButton sont utilisés pour l'ajout d'images
    // JPanel est utilisé pour afficher les images
    private AttachImage attachPicture;
    private JPanel panel;
    private JButton addBtn;

    // BufferedImage est utilisé pour stocker une image téléchargée à partir d'une URL
    private BufferedImage bfrImage;

    // Listes pour stocker les images et leurs labels respectifs
    private List<Picture> pictures = new LinkedList<>();
    private List<JLabel> imagesLabel = new LinkedList<>();

    // Contrôleur pour le téléchargement de fichier
    private ControllerUploadFile controllerUploadFile;

    /**
     * Constructeur pour initialiser les attributs et ajouter un écouteur
     * d'action au bouton d'ajout.
     *
     * @param panel JPanel pour afficher les images
     * @param attachPicture AttachImage utilisé pour l'ajout d'images
     * @param addBtn JButton utilisé pour déclencher l'ajout d'une image
     */
    public ControllerImageProduct(JPanel panel, AttachImage attachPicture, JButton addBtn) {
        this.attachPicture = attachPicture;
        this.panel = panel;
        this.addBtn = addBtn;

        this.addBtn.addActionListener(this);
        controllerUploadFile = new ControllerUploadFile(panel);

        // Ajouter toutes les images existantes à l'interface utilisateur
        for (Picture pic : attachPicture.getPictures()) {
            addPicture(pic);
        }

    }

    /**
     * Renvoie la liste des images associées au produit.
     *
     * @return Liste de Picture
     */
    public List<Picture> getPictures() {
        return pictures;
    }

    /**
     * Ajoute une image à la liste et à l'interface utilisateur.
     *
     * @param pic Picture à ajouter
     */
    private void addPicture(Picture pic) {
        if (attachPicture.isSingleAttach()) {
            for (int i = 0; i < pictures.size() && i < imagesLabel.size(); i++) {
                removePicture(imagesLabel.get(i), pictures.get(i));
            }
        }
        // Ajouter l'image à la liste et télécharger l'image à partir de l'URL
        pictures.add(pic);
        ErrorHandeler.getInstance().exec(() -> {
            String[] partOfUrl = pic.getPath().split("/");

            bfrImage = FileDownloader.downloadImageFromUrl(pic.getPath(), partOfUrl[partOfUrl.length - 1]);

            // Calculer le ratio de l'image pour redimensionner l'image de manière à ce qu'elle ait une hauteur de 200 pixels
            double ratio = (double) bfrImage.getWidth() / (double) bfrImage.getHeight();

            // Redimensionner l'image et créer un ImageIcon à partir de cette image
            ImageIcon img = new ImageIcon(getScaledImage(new ImageIcon(bfrImage).getImage(), 200, (int) (200 / ratio)));
            img.setDescription(pic.getAlt());

            // Créer un label d'image à partir de l'ImageIcon et ajouter un écouteur de souris pour afficher un menu contextuel lorsque l'utilisateur clique avec le bouton droit de la souris sur l'image
            JLabel imageDisplay = new JLabel(img);
            imagesLabel.add(imageDisplay);

            imageDisplay.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent evt) {
                    if (evt.getButton() == MouseEvent.BUTTON3) {
                        Point mousePos = evt.getPoint();

                        PopupMenuImage popup = new PopupMenuImage(pic,
                                () -> {
                                    String descriptionImage = getDescriptionImage(pic.getAlt());
                                    if (descriptionImage != null) {
                                        pic.setAlt(descriptionImage);
                                    }
                                }, () -> {
                                    removePicture(imageDisplay, pic);
                                });
                        popup.show(imageDisplay, mousePos.x, mousePos.y);
                    }
                }
            });

            this.panel.add(imageDisplay);

            return true;
        });
    }

    /**
     * Supprime une image de la liste et de l'interface utilisateur.
     *
     * @param image JLabel de l'image à supprimer
     * @param pic Picture à supprimer
     */
    private void removePicture(JLabel image, Picture pic) {
        this.panel.remove(image);
        pictures.remove(pic);
        imagesLabel.remove(image);
        updatePanel();
    }

    /**
     * Redimensionne une image.
     *
     * @param srcImg Image à redimensionner
     * @param w Largeur de l'image redimensionnée
     * @param h Hauteur de l'image redimensionnée
     * @return Image redimensionnée
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
     * Méthode appelée lorsqu'un événement ActionEvent est déclenché. Si
     * l'événement est déclenché par le bouton d'ajout, ouvre une fenêtre de
     * sélection de fichier pour que l'utilisateur puisse sélectionner une image
     * à ajouter.
     *
     * @param ae ActionEvent déclenché
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        String pathToImage = controllerUploadFile.choose();
        if (pathToImage != null) {
            ErrorHandeler.getInstance().exec(() -> {
                BufferedImage image = ImageIO.read(new File(pathToImage));
                String urlRelativeToOnlineImage;
                if (pictures.isEmpty()) {//cas particulier si c'est la 1er image
                    urlRelativeToOnlineImage = FileDownloader.uploadImage(image, attachPicture.getProductId());

                } else {
                    urlRelativeToOnlineImage = FileDownloader.uploadImage(image, null);
                }

                if (urlRelativeToOnlineImage != null) {
                    String descriptionImage = getDescriptionImage();
                    if (descriptionImage == null) {
                        descriptionImage = "";
                    }
                    Picture pic = new Picture(urlRelativeToOnlineImage, descriptionImage, -1);
                    this.attachPicture.attachPicture(pic);
//                    Picture pic = new Picture(urlRelativeToOnlineImage, descriptionImage, product.getId());
                    addPicture(pic);
                    updatePanel();
                }
                return true;
            });

        }
    }

    /**
     * Demande à l'utilisateur de saisir une nouvelle description pour l'image
     * en utilisant une boîte de dialogue.
     *
     * @return Nouvelle description de l'image, ou null si l'utilisateur a
     * annulé
     */
    private String getDescriptionImage() {
        return JOptionPane.showInputDialog("Description de l'image");
    }

    /**
     * Demande à l'utilisateur de saisir une nouvelle description pour l'image
     * en utilisant une boîte de dialogue.
     *
     * @param currentAlt Description actuelle de l'image
     * @return Nouvelle description de l'image, ou null si l'utilisateur a
     * annulé
     */
    private String getDescriptionImage(String currentAlt) {
        return JOptionPane.showInputDialog("Description de l'image", currentAlt);
    }

    /**
     * Met à jour le panel en le révalidant et en le réaffichant.
     */
    private void updatePanel() {
        panel.revalidate();
        panel.repaint();
    }

}
