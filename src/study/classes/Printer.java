package study.classes;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.Exchanger;

public class Printer extends Thread {

    private Exchanger<BufferedImage> exchanger;

    public Printer(Exchanger<BufferedImage> exchanger)
    {
        this.exchanger=exchanger;
        start();
    }


    @Override
    public void run() {
        while (true) {
            try {
                exchanger.exchange(grabScreen());
                sleep(5000);
                if (isInterrupted()) return;

            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
    private BufferedImage grabScreen(){
        try{
            return new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        }
        catch (SecurityException e){
            System.out.println("Security exception "+e);
        }
        catch (AWTException e){
            System.out.println("AWTException "+e);
        }
        return null;
    }
}
