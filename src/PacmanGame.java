import game.*;
import game.characters.*;
import game.frame.EnterNameScoreFrame;
import game.frame.PlayAgainDialog;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PacmanGame extends JPanel implements ActionListener {
    private final Dimension d;
    private Map map;
    private Pacman pacman;
    private RedGhost blinky;
    private PinkGhost pinky;
    private BlueGhost inky;
    private OrangeGhost clyde;
    private Timer timer;
    private Score score;
    private final Font smallFont = new Font("Arial", Font.BOLD, 14);
    private final Sounds sounds;

    private int lifes;
    private boolean inGame, dying, gameWon, paused;
    private final Image heart;

    private PlayAgainDialog playAgain;
    private EnterNameScoreFrame enterScore;

    public PacmanGame() {
        sounds = new Sounds();
        sounds.playIntro();
        pacman = new Pacman(9 * 24, 11 * 24);
        blinky = new RedGhost(10 * 24, 7 * 24);
        pinky = new PinkGhost(7 * 24, 9 * 24);
        inky = new BlueGhost(9 * 24, 9 * 24);
        clyde = new OrangeGhost(11 * 24, 9 * 24);
        map = new Map();
        d = new Dimension(800, 800);
        heart = new ImageIcon("img/heart.png").getImage();
        dying = false;
        score = new Score();
        gameWon = false;
        paused = false;

        setFocusable(true);
        initGame();
        this.addKeyListener(new TAdapter());

        playAgain = new PlayAgainDialog();
        playAgain.setVisible(false);
        enterScore = new EnterNameScoreFrame(score);
        enterScore.setVisible(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.black);
        g2d.fillRect(0, 0, d.width, d.height);

        map.draw(g2d);
        pacman.draw(g2d);
        blinky.draw(g2d);
        inky.draw(g2d);
        pinky.draw(g2d);
        clyde.draw(g2d);
        drawScore(g2d);

        if (!gameWon) {
            if (inGame) {
                playGame();
            } else {
                if (!paused) {
                    showIntroScreen(g2d);
                }
            }
        } else {
            if (!enterScore.isVisible() && !enterScore.getEnteredScore()) {
                enterScore.setVisible(true);
            }
            if (enterScore.getEnteredScore()) {
                enterScore.setVisible(false);
            }
            if (!playAgain.isVisible() && !enterScore.isVisible()) {
                playAgain.setVisible(true);
            }
            timer.stop();
            inGame = false;
            gameWon = true;
            showPlayAgain();
        }

        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    private void showPlayAgain() {
        if (playAgain.isPlayAgain() == -1) {
            System.exit(0);
        } else if (playAgain.isPlayAgain() == 1) {
            gameWon = false;
            playAgain.setVisible(false);
            lifes = 0;
            death();
        }
    }

    private void showIntroScreen(Graphics2D g2d) {
        String start = "Press SPACE to start";
        g2d.setColor(Color.yellow);
        g2d.drawString(start, (map.SCREEN_SIZE) / 4 + 50, 210);
    }

    public void pause() {
        if (paused) {
            paused = false;
            inGame = true;
        } else {
            paused = true;
            inGame = false;
            timer.stop();
        }
    }

    private void drawScore(Graphics2D g) {
        g.setFont(smallFont);
        g.setColor(new Color(5, 181, 79));
        String s = "Score: " + score.get();
        g.drawString(s, map.SCREEN_SIZE / 2 + 96, map.SCREEN_SIZE + 16);

        for (int i = 0; i < lifes; i++) {
            g.drawImage(heart, i * 28 + 8, map.SCREEN_SIZE + 1, this);
        }
    }

    private void initGame() {
        gameWon = false;
        lifes = 3;
        timer = new Timer(40, this);
        timer.start();
        playAgain = new PlayAgainDialog();
        enterScore = new EnterNameScoreFrame(score);
    }

    private void playGame() {
        if (dying) {
            death();
        } else {
            if (!paused && !gameWon) {
                pacman.move(map, score);
                inky.move(map);
                pinky.move(map);
                clyde.move(map);
                blinky.move(map);
                checkCollision();
                if (map.gameDone()) {
                    gameWon = true;
                    sounds.play();
                    System.out.println("Game is over! You won!");
                    timer.stop();
                }
            } else {
                if (gameWon) {
                    timer.stop();
                }
            }
        }
    }

    private void death() {
        lifes--;

        if (lifes <= 0) {
            inGame = false;
            map = new Map();
            score = new Score();
            initGame();
        }

        continueLevel();
    }

    private void continueLevel() {
        pacman = new Pacman(9 * 24, 11 * 24);
        blinky = new RedGhost(10 * 24, 7 * 24);
        pinky = new PinkGhost(7 * 24, 9 * 24);
        inky = new BlueGhost(9 * 24, 9 * 24);
        clyde = new OrangeGhost(11 * 24, 9 * 24);
        dying = false;
    }

    private void checkCollision() {
        if ((pacman.getX() > (inky.getX() - 12)
                && pacman.getX() < (inky.getX() + 12)
                && pacman.getY() > (inky.getY() - 12)
                && pacman.getY() < (inky.getY() + 12))
                || (pacman.getX() > (pinky.getX() - 12)
                && pacman.getX() < (pinky.getX() + 12)
                && pacman.getY() > (pinky.getY() - 12)
                && pacman.getY() < (pinky.getY() + 12))
                || (pacman.getX() > (blinky.getX() - 12)
                && pacman.getX() < (blinky.getX() + 12)
                && pacman.getY() > (blinky.getY() - 12)
                && pacman.getY() < (blinky.getY() + 12))
                || (pacman.getX() > (clyde.getX() - 12)
                && pacman.getX() < (clyde.getX() + 12)
                && pacman.getY() > (clyde.getY() - 12)
                && pacman.getY() < (clyde.getY() + 12))
                && inGame) {
            dying = true;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame && !dying && !map.gameDone() && !paused) {
            pacman.move(map, score);
        }
        repaint();
    }

    class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_P || key == KeyEvent.VK_ESCAPE) {
                pause();
            }

            if (inGame) {
                if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
                    pacman.setDirections(-1, 0);
                } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
                    pacman.setDirections(1, 0);
                } else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
                    pacman.setDirections(0, -1);
                } else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
                    pacman.setDirections(0, 1);
                }
            } else {
                if (key == KeyEvent.VK_SPACE && !gameWon && !paused) {
                    inGame = true;
                    initGame();
                }
            }
        }
    }
}