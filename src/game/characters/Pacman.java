package game.characters;

import game.Map;
import game.Score;

import javax.swing.*;
import java.awt.*;

public class Pacman {
    private int x, y;
    private int pacmand_x, pacmand_y;
    private int req_dx, req_dy;
    private final int PACMAN_SPEED = 2;
    private Image down, right, up, left;

    public Pacman(int x, int y) {
        this.x = x;
        this.y = y;
        pacmand_x = 0;
        pacmand_y = 0;
        req_dx = 0;
        req_dy = 0;
        down = new ImageIcon("img/pacmandown.png").getImage();
        up = new ImageIcon("img/pacmanup.png").getImage();
        left = new ImageIcon("img/pacmanleft.png").getImage();
        right = new ImageIcon("img/pacmanright.png").getImage();
    }

    public void move(Map map, Score score) {
        int pos;
        short ch;

        if (x % map.BLOCK_SIZE == 0 && y % map.BLOCK_SIZE == 0) {
            pos = x / map.BLOCK_SIZE + map.N_BLOCKS * (int) (y / map.BLOCK_SIZE);
            ch = (short) map.screenData[pos];

            if ((ch & 16) != 0) {
                map.screenData[pos] = (short) (ch & 15);
                score.incrementScore();
            }

            if (req_dx != 0 || req_dy != 0) {
                if (!((req_dx == -1 && req_dy == 0 && (ch & 1) != 0)
                        || (req_dx == 1 && req_dy == 0 && (ch & 4) != 0)
                        || (req_dx == 0 && req_dy == -1 && (ch & 2) != 0)
                        || (req_dx == 0 && req_dy == 1 && (ch & 8) != 0))) {
                    pacmand_x = req_dx;
                    pacmand_y = req_dy;
                }
            }

            if ((pacmand_x == -1 && pacmand_y == 0 && (ch & 1) != 0)
                    || (pacmand_x == 1 && pacmand_y == 0 && (ch & 4) != 0)
                    || (pacmand_x == 0 && pacmand_y == -1 && (ch & 2) != 0)
                    || (pacmand_x == 0 && pacmand_y == 1 && (ch & 8) != 0)) {
                pacmand_x = 0;
                pacmand_y = 0;
            }
        }
        x = x + PACMAN_SPEED * pacmand_x;
        y = y + PACMAN_SPEED * pacmand_y;
    }

    public void draw(Graphics2D g2d) {
        if (req_dx == -1) {
            g2d.drawImage(left, x + 1, y + 1, null);
        } else if (req_dx == 1) {
            g2d.drawImage(right, x + 1, y + 1, null);
        } else if (req_dy == -1) {
            g2d.drawImage(up, x + 1, y + 1, null);
        } else {
            g2d.drawImage(down, x + 1, y + 1, null);
        }
    }

    public void setDirections(int x, int y) {
        req_dx = x;
        req_dy = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
