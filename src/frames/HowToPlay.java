package frames;

import javax.swing.*;
import java.awt.*;

public class HowToPlay extends JFrame {
    public HowToPlay(){
        setBounds(600, 350, 450, 300);

        JTextArea controlsText = new JTextArea(
                "Pac-Man is a classic arcade game that\n" +
                        "was first released in 1980. The game \n" +
                        "features a circular, yellow character named \n" +
                        "Pac-Man, who navigates through a maze filled \n" +
                        "with pellets, fruits, and ghosts. The main goal\n" +
                        "is to accumulate points by eating all the pellets\n" +
                        "while avoiding the ghosts.\n" +
                        "\n" +
                        "Game Elements:\n\n" +
                        "\n" +
                        "Pellets: The primary objective is to eat all the small\n" +
                        "pellets scattered throughout the maze. Each pellet adds\n" +
                        "points to the player's score.\n" +
                        "\n" +
                        "Power Pellets:\n\n Occasionally, larger power\n" +
                        "pellets appear in the corners of the maze.\n" +
                        "When Pac-Man consumes a power pellet, the\n" +
                        "ghosts turn blue, and Pac-Man can eat them\n" +
                        "for extra points.\n" +
                        "\n" +
                        "Ghosts:\n\n" +
                        "Blinky (red)\n" +
                        "Pinky (pink)\n" +
                        "Inky (blue)\n" +
                        "Clyde (orange)\n\n" +
                        "roam the maze, attempting to catch Pac-Man.\n" +
                        "If Pac-Man collides with a ghost without the\n" +
                        "aid of a power pellet, he loses a life.\n" +
                        "\n" +
                        "Gameplay:\n" +
                        "\n" +
                        "Pac-Man moves through the maze, attempting to consume\n" +
                        "all the pellets while avoiding the ghosts.\n" +
                        "Eating a power pellet temporarily allows Pac-Man to\n" +
                        "turn the tables on the ghosts, making them\n" +
                        "vulnerable and granting the opportunity to eat\n" +
                        "them for extra points.\n" +
                        "The game continues until Pac-Man loses all lives.\n" +
                        "Players can earn extra lives based on their score.\n\n" +
                        "Objective:\n\n" +
                        "The primary goal of Pac-Man is to achieve the\n" +
                        "highest possible score by eating all the pellets,\n" +
                        "consuming fruits, and strategically using power\n" +
                        "pellets to gain an advantage over the ghosts.\n" +
                        "Players must navigate the maze efficiently,\n" +
                        "anticipate ghost movements, and aim to clear\n" +
                        "each level. Pac-Man is renowned for its simplicity,\n" +
                        "addictive gameplay, and challenging pursuit of high scores."
        );

        controlsText.setEditable(false);
        controlsText.setBackground(Color.BLACK);
        controlsText.setForeground(Color.YELLOW);
        controlsText.setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(controlsText);
        scrollPane.setBorder(null);

        getContentPane().add(scrollPane);
        setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setResizable(false);
        setVisible(true);
    }
}
