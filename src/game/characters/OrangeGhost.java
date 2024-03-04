package game.characters;

import game.Map;
import javax.swing.*;
import java.awt.*;

public class OrangeGhost extends Ghost {
    private Image imgLeft, imgRight;

    public OrangeGhost(int x, int y){
        super(x,y);
        imgLeft = new ImageIcon("img/OrangeGhostLeft.png").getImage();
        imgRight = new ImageIcon("img/OrangeGhostRight.png").getImage();
    }

    public void move(Map map){
        super.move(map);
    }

    public int getX() {
        return super.getX();
    }

    public int getY() {
        return super.getY();
    }

    public void draw(Graphics2D g2d) {
        if (super.getDx() < 0) {
            g2d.drawImage(imgLeft, super.getX(), super.getY(), null);
        } else {
            g2d.drawImage(imgRight, super.getX(), super.getY(), null);
        }
    }
}
