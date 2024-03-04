package game.frame;

import javax.swing.*;
import java.awt.*;

public class PlayAgainDialog extends JFrame {
    private int playAgain;

    public PlayAgainDialog() {
        super("Play Again?");
        playAgain = 0;

        JButton playAgainButton = new JButton("Play Again");
        JButton quitButton = new JButton("Quit");

        playAgainButton.addActionListener(e -> {
            playAgain = 1;
            setVisible(false);
        });

        quitButton.addActionListener(e -> {
            playAgain = -1;
            setVisible(false);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(playAgainButton);
        buttonPanel.add(quitButton);

        setLayout(new BorderLayout());
        add(new JLabel("Do you want to play again?"), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(false);
    }

    public int isPlayAgain() {
        return playAgain;
    }

}
