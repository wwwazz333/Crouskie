package crouskiebackoffice.model;

import crouskiebackoffice.exceptions.ErrorDownloadImage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class FileDownloader {

    public static String downloadFromUrl(URL url, String localFilename) throws IOException {
        InputStream is = null;
        FileOutputStream fos = null;

        String tempDir = System.getProperty("java.io.tmpdir");
        String outputPath = tempDir + "/" + localFilename;

        try {
            //connect
            URLConnection urlConn = url.openConnection();

            //get inputstream from connection
            is = urlConn.getInputStream();
            fos = new FileOutputStream(outputPath);

            // 4KB buffer
            byte[] buffer = new byte[4096];
            int length;

            // read from source and write into local file
            while ((length = is.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
            return outputPath;
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } finally {
                if (fos != null) {
                    fos.close();
                }
            }
        }
    }

    public static ImageIcon downloadImageFromUrl(URL url, String localFilename) throws ErrorDownloadImage {
        try {
            String result = FileDownloader.downloadFromUrl(url, localFilename);
            File file = new File(result);
            return new ImageIcon(ImageIO.read(file));
        } catch (IOException e) {
            throw new ErrorDownloadImage(localFilename);
        }
    }
}
