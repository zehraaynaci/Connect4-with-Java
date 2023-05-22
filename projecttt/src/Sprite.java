import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {

    public int width, height;
    public int[] pixels;

    public static Sprite overlay = new Sprite("/blueball.jpg");
    public static Sprite ball1 = new Sprite("/pinkball.jpg");
    public static Sprite ball2 = new Sprite("/table.jpg");


    public Sprite(String path){
        try{
            BufferedImage image = ImageIO.read(Sprite.class.getResource(path));
            width = image.getWidth();
            height = image.getHeight();
            pixels = image.getRGB(0,0,width, height, null,0,width);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    
}
