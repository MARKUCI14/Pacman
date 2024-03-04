package frames;

import game.Leaderboards;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreBoardFrame extends JFrame {
    private Leaderboards leaderboards;

    public ScoreBoardFrame() {
        setTitle("Leaderboards");
        setSize(400, 400);

        JMenuBar menuBar = new JMenuBar();
        JMenu showMenu = new JMenu("Show");
        JMenuItem top3MenuItem = new JMenuItem("Top 3");

        top3MenuItem.addActionListener(e -> showTop3());

        showMenu.add(top3MenuItem);
        menuBar.add(showMenu);
        setJMenuBar(menuBar);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        leaderboards = new Leaderboards();
        textArea.setBackground(Color.BLACK);
        textArea.setFont(new Font("Arial", Font.BOLD, 20));
        textArea.setForeground(Color.YELLOW);

        List<Leaderboards.ScoreEntry> list = leaderboards.getList();

        int i = 1;
        for (Leaderboards.ScoreEntry entry : list) {
            textArea.append(String.valueOf(i) + ". " + entry.getName() + " " + String.valueOf(entry.getScore()) + "\n");
            i++;
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);
        setBackground(Color.BLACK);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void showTop3() {
        List<Leaderboards.ScoreEntry> top3 = leaderboards.getList()
                .stream()
                .sorted(Comparator.comparingInt(Leaderboards.ScoreEntry::getScore).reversed())
                .limit(3)
                .toList();

        JTextArea top3TextArea = new JTextArea();
        top3TextArea.setEditable(false);

        int i = 1;
        for (Leaderboards.ScoreEntry entry : top3) {
            top3TextArea.append(String.valueOf(i) + ". " + entry.getName() + " " + String.valueOf(entry.getScore()) + "\n");
            i++;
        }

        JOptionPane.showMessageDialog(this, new JScrollPane(top3TextArea), "Top 3 Scores", JOptionPane.PLAIN_MESSAGE);
    }

}