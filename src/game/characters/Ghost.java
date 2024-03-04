package game.characters;

import game.Map;

public abstract class Ghost {
    private int x, y;
    private int dx, dy;
    private int speed;

    public Ghost(int x, int y) {
        this.x = x;
        this.y = y;
        dx = 0;
        dy = 0;
        speed = 3;
    }

    public void move(Map map) {
        if (x % map.BLOCK_SIZE == 0 && y % map.BLOCK_SIZE == 0) {
            int pos = x / map.BLOCK_SIZE + map.N_BLOCKS * (int) (y / map.BLOCK_SIZE);
            int count = 0;

            int[] dx = new int[4];
            int[] dy = new int[4];

            if ((map.screenData[pos] & 1) == 0 && this.dx != 1) {
                dx[count] = -1;
                dy[count] = 0;
                count++;
            }

            if ((map.screenData[pos] & 2) == 0 && this.dy != 1) {
                dx[count] = 0;
                dy[count] = -1;
                count++;
            }

            if ((map.screenData[pos] & 4) == 0 && this.dx != -1) {
                dx[count] = 1;
                dy[count] = 0;
                count++;
            }

            if ((map.screenData[pos] & 8) == 0 && this.dy != -1) {
                dx[count] = 0;
                dy[count] = 1;
                count++;
            }

            if (count == 0) {
                if ((map.screenData[pos] & 15) == 15) {
                    this.dx = 0;
                    this.dy = 0;
                } else {
                    this.dx = -this.dx;
                    this.dy = -this.dy;
                }
            } else {
                count = (int) (Math.random() * count);
                if (count > 3) {
                    count = 3;
                }

                this.dx = dx[count];
                this.dy = dy[count];
            }
        }

        x += dx * speed;
        y += dy * speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDx() {
        return dx;
    }

}
