package crouskiebackoffice.controle;

import crouskiebackoffice.model.AttachImage;
import crouskiebackoffice.model.FileDownloader;
import crouskiebackoffice.model.Picture;
import crouskiebackoffice.model.Product;
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

public class ControllerImageProduct implements ActionListener {

    private AttachImage attachPicture;
    private JPanel panel;
    private JButton addBtn;

    private BufferedImage bfrImage;
    private List<Picture> pictures = new LinkedList<>();
    private List<JLabel> imagesLabel = new LinkedList<>();

    private ControllerUploadFile controllerUploadFile;


    public ControllerImageProduct(JPanel panel, AttachImage attachPicture, JButton addBtn) {
        this.attachPicture = attachPicture;
        this.panel = panel;
        this.addBtn = addBtn;

        this.addBtn.addActionListener(this);
        controllerUploadFile = new ControllerUploadFile(panel);

        for (Picture pic : attachPicture.getPictures()) {
            addPicture(pic);
        }

    }

    public List<Picture> getPictures() {
        return pictures;
    }

    private void addPicture(Picture pic) {
        if (attachPicture.isSingleAttach()) {
            for (int i = 0; i < pictures.size() && i < imagesLabel.size(); i++) {
                removePicture(imagesLabel.get(i), pictures.get(i));
            }
        }
        pictures.add(pic);
        ErrorHandeler.getInstance().exec(() -> {
            String[] partOfUrl = pic.getPath().split("/");

            bfrImage = FileDownloader.downloadImageFromUrl(pic.getPath(), partOfUrl[partOfUrl.length - 1]);

            double ratio = (double) bfrImage.getWidth() / (double) bfrImage.getHeight();

            ImageIcon img = new ImageIcon(getScaledImage(new ImageIcon(bfrImage).getImage(), 200, (int) (200 / ratio)));
            img.setDescription(pic.getAlt());

            JLabel imageDisplay = new JLabel(img);
            imagesLabel.add(imageDisplay);

            imageDisplay.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent evt) {
                    if (evt.getButton() == MouseEvent.BUTTON3) {
                        Point mousePos = evt.getPoint();

                        var popup = new PopupMenuImage(pic,
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

    private void removePicture(JLabel image, Picture pic) {
        this.panel.remove(image);
        pictures.remove(pic);
        imagesLabel.remove(image);
        updatePanel();
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

    private String getDescriptionImage() {
        return JOptionPane.showInputDialog("Description de l'image");
    }

    private String getDescriptionImage(String defaultValue) {
        return JOptionPane.showInputDialog("Description de l'image", defaultValue);
    }

    private void updatePanel() {
        panel.revalidate();
        panel.repaint();
    }

}
