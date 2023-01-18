package crouskiebackoffice.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import crouskiebackoffice.exceptions.ErrorDownloadImage;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 * Classe permettant de télécharger et de charger des fichiers depuis un
 * serveur.
 */
public class FileDownloader {

    /**
     * Adresse du serveur. le dernier slash est très important
     *
     * @exemple http://menardbediant.fr:8080/
     */
    public final static String SERVER_ADRESSE = "http://menardbediant.fr:8080/";

    /**
     * Télécharge un fichier depuis une URL donnée et l'enregistre localement.
     *
     * @param url L'URL du fichier à télécharger.
     * @param localFilename Le nom du fichier local où enregistrer le fichier
     * téléchargé.
     * @return Le chemin absolu du fichier local enregistré.
     * @throws IOException Si une erreur est survenue lors de la lecture ou de
     * l'écriture du fichier.
     */
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

    /**
     * Télécharger une image depuis un url
     *
     * @param url l'url du fichier à télécharger
     * @param localFilename nom du fichier télécharger en local
     * @return l'image sous forme d'une {@code BufferedImage}
     * @throws ErrorDownloadImage S'il y à une erreur lors du téléchargement de
     * l'image
     */
    public static BufferedImage downloadImageFromUrl(String url, String localFilename) throws ErrorDownloadImage {
        try {
            System.out.println("try download : " + SERVER_ADRESSE + url);
            String result = FileDownloader.downloadFromUrl(new URL(SERVER_ADRESSE + url), localFilename);
            File file = new File(result);
            return ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ErrorDownloadImage(localFilename);
        }
    }

    /**
     * uplaoder une image sur le serveur
     *
     * @param image l'image à uploader
     * @param idProductForPreviewImage null if the uploaded image is note a
     * preview of a product, else the id of the product
     * @return le nom aléatoire qui à été donné à l'image
     * @throws URISyntaxException en cas d'erreur dans le nom de l'image
     * @throws IOException
     * @throws InterruptedException
     */
    public static String uploadImage(BufferedImage image, Integer idProductForPreviewImage) throws URISyntaxException, IOException, InterruptedException {
        HashMap<String, Object> jsonMap = new HashMap<>();
        Gson gson = new Gson();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        ImageIO.write(image, "png", byteArrayOutputStream);

        String base64Image = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());

        jsonMap.put("action", "upload");
        jsonMap.put("image", base64Image);
        if (idProductForPreviewImage != null) {
            jsonMap.put("id", idProductForPreviewImage);
        }

        URI uri = new URI(SERVER_ADRESSE + "/api.php");
        gson.toJson(jsonMap);

        HttpRequest post = HttpRequest.newBuilder().uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(jsonMap))).header("Content-Type", "application/json").build();

        HttpClient cl = HttpClient.newHttpClient();

        var res = cl.send(post, HttpResponse.BodyHandlers.ofString());

        //resultat
        System.out.println(res.body());
        Map<String, JsonElement> map = JsonParser.parseString(res.body()).getAsJsonObject().asMap();
        if (map.get("success").getAsBoolean()) {
            return map.get("result").getAsString();
        }
        return null;
    }
}
