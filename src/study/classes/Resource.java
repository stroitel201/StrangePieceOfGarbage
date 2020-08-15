package study.classes;

import java.awt.image.BufferedImage;

public class Resource {
    private BufferedImage resource;

    public synchronized BufferedImage getResource() {
        return resource;
    }

    public synchronized void setResource(BufferedImage resource) {
        this.resource = resource;
    }
}
