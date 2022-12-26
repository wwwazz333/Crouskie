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

public class FileDownloader {

    //le dernier slash est très important
    //http://menardbediant.fr:8081/
    public final static String SERVER_ADRESSE = "http://menardbediant.fr:8081/";

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

    public static String uploadImage(BufferedImage image) throws URISyntaxException, IOException, InterruptedException {
        HashMap<String, String> jsonMap = new HashMap<>();
        Gson gson = new Gson();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        ImageIO.write(image, "png", byteArrayOutputStream);

        String base64Image = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());

        jsonMap.put("action", "upload");
        jsonMap.put("image", base64Image);

        URI uri = new URI(SERVER_ADRESSE + "/api.php");
        HttpRequest post = HttpRequest.newBuilder().uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(jsonMap))).header("Content-Type", "application/json").build();

        HttpClient cl = HttpClient.newHttpClient();

        var res = cl.send(post, HttpResponse.BodyHandlers.ofString());

        //resultat
        Map<String, JsonElement> map = JsonParser.parseString(res.body()).getAsJsonObject().asMap();
        if (map.get("success").getAsBoolean()) {
            return map.get("result").getAsString();
        }
        return null;
    }
}
