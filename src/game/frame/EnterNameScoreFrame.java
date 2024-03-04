package game.frame;

import game.Leaderboards;
import game.Score;

import javax.swing.*;
import java.awt.*;

public class EnterNameScoreFrame extends JFrame {
    private JTextField nameField;
    private Leaderboards leaderboards;

    private boolean enteredScore;

    public EnterNameScoreFrame(Score score) {
        enteredScore = false;
        setTitle("You Won!");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLayout(new FlowLayout());

        leaderboards = new Leaderboards();
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(15);
        JButton saveButton = new JButton("Save");

        add(nameLabel);
        add(nameField);
        add(saveButton);

        setVisible(false);
        setLocationRelativeTo(null);

        saveButton.addActionListener(e -> {
            String playerName = nameField.getText();
            leaderboards.insert(new Leaderboards.ScoreEntry(playerName, score.get()));
            leaderboards.save();
            setVisible(false);
            enteredScore = true;
        });
    }

    public boolean getEnteredScore(){
        return enteredScore;
    }
}

