package proyecto.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ImageLoader {
    public Image loadImage(String route){
        return new ImageIcon(Objects.requireNonNull(getClass().getResource(route))).getImage();
    }
    public ImageIcon loadIcon(String route){
        return new ImageIcon(Objects.requireNonNull(getClass().getResource(route)));
    }
}
