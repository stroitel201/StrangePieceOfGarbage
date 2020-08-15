package study.classes;

import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.util.concurrent.Exchanger;

public class hello {
    public static void main (String[] args) {

        String ACCESS_TOKEN = "LprmlCm3SjAAAAAAAAAAAbXH6IhUc304JX4kWxW_vNIlu_RJFD2e7YVHIyltxI8r";
        Exchanger<BufferedImage> exchanger = new Exchanger<>();

        Printer printer = new Printer(exchanger);
        Sender sender = new Sender(exchanger, ACCESS_TOKEN);

        System.out.println("To stop enter symbol");
        int symbol = new Scanner(System.in).nextInt();

        printer.interrupt();
        sender.interrupt();

    }
}
