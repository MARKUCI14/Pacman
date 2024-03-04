import frames.ControlsDialog;
import frames.HowToPlay;
import frames.ScoreBoardFrame;

import javax.swing.*;
import java.awt.*;

public class PacmanFrame extends JFrame {
    private PacmanGame pacman;
    private JMenuBar menuBar;

    public PacmanFrame() {
        setSize(480, 560);
        setTitle("Pacman");
        menuBar = new JMenuBar();
        JMenu fileMenuGame = new JMenu("Game");
        setLocationRelativeTo(null);

        JMenuItem resetMenuItem = new JMenuItem("Reset");
        resetMenuItem.addActionListener(e -> {
            resetGame();
        });

        JMenuItem pauseGame = new JMenuItem("Pause/Continue");
        pauseGame.addActionListener(e -> {
            pacman.pause();
        });

        fileMenuGame.add(pauseGame);
        fileMenuGame.add(resetMenuItem);

        JMenu fileMenuHelp = new JMenu("Help");

        JMenuItem controls = new JMenuItem("Controls");
        controls.addActionListener(e -> {
            new ControlsDialog();
        });

        JMenuItem game = new JMenuItem("How to play?");
        game.addActionListener(e -> {
            new HowToPlay();
        });

        fileMenuHelp.add(controls);
        fileMenuHelp.add(game);

        JMenu fileMenuScoreboard = new JMenu("Scoreboard");
        JMenuItem leaderboards = new JMenuItem("Leaderboards");
        leaderboards.addActionListener(e ->{
            new ScoreBoardFrame();
        });

        fileMenuScoreboard.add(leaderboards);

        menuBar.add(fileMenuGame);
        menuBar.add(fileMenuHelp);
        menuBar.add(fileMenuScoreboard);
        add(menuBar, BorderLayout.NORTH);
        setBackground(Color.BLACK);
        pacman = new PacmanGame();
        add(pacman);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void resetGame() {
        remove(pacman);
        pacman = new PacmanGame();
        add(pacman);
        revalidate();
        repaint();
        pacman.requestFocusInWindow();
    }

    public static void main(String[] args) {
        new PacmanFrame();
    }
}
