package study.classes;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Exchanger;

public class Sender extends Thread {

    private Exchanger<BufferedImage> exchanger;
    private DbxClientV2 client;
    private BufferedImage image;
    private DbxRequestConfig config;

    public Sender(Exchanger<BufferedImage> exchanger, String ACCESS_TOKEN)
    {
        config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        client = new DbxClientV2(config, ACCESS_TOKEN);
        this.exchanger = exchanger;
        start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (isInterrupted()) return;
                image = exchanger.exchange(null);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            ByteArrayOutputStream os = new ByteArrayOutputStream();

            try {
                ImageIO.write(image, "png", os);
                InputStream is = new ByteArrayInputStream(os.toByteArray());
                client.files().uploadBuilder("/" + dateFormating() + ".png")
                        .uploadAndFinish(is);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private String dateFormating(){
        Date nowDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return dateFormat.format(nowDate);
    }
}
