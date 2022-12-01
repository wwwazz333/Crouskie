package crouskiebackoffice.controle;

import crouskiebackoffice.model.FileDownloader;
import crouskiebackoffice.model.Picture;
import crouskiebackoffice.model.Product;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.EventListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControllerImageProduct implements ActionListener {

    private Product product;
    private JPanel panel;
    private JButton addBtn;

    private BufferedImage bfrImage;

    public ControllerImageProduct(JPanel panel, Product product, JButton addBtn) {
        this.product = product;
        this.panel = panel;
        this.addBtn = addBtn;

        this.addBtn.addActionListener(this);

        for (Picture pic : product.getPictures()) {
            addPicture(pic);
        }
    }

    private void addPicture(Picture pic) {
        ErrorHandeler.getInstance().exec(() -> {
            String[] partOfUrl = pic.getPath().split("/");

            bfrImage = FileDownloader.downloadImageFromUrl(new URL(pic.getPath()), partOfUrl[partOfUrl.length - 1]);

            double ratio = (double) bfrImage.getWidth() / (double) bfrImage.getHeight();
            System.out.println(ratio);

            ImageIcon img = new ImageIcon(getScaledImage(new ImageIcon(bfrImage).getImage(), 200, (int) (200 / ratio)));
            img.setDescription(pic.getAlt());

            JLabel imageDisplay = new JLabel(img);

            this.panel.add(imageDisplay);
            System.out.println("ajouté");
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
        try {
            FileDownloader.uploadImage(bfrImage);
        } catch (URISyntaxException | IOException | InterruptedException ex) {
            Logger.getLogger(ControllerImageProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
