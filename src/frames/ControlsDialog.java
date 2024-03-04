package frames;

import javax.swing.*;
import java.awt.*;

public class ControlsDialog extends JFrame {

    public ControlsDialog(){
        setSize(300, 300);
        JTextArea controlsText = new JTextArea(
                "Controls:\n" +
                        "P or ESC: Pause/Continue\n" +
                        "A or Left Arrow: Move Left\n" +
                        "D or Right Arrow: Move Right\n" +
                        "W or Up Arrow: Move Up\n" +
                        "S or Down Arrow: Move Down\n" +
                        "Space: Start a New Game\n"
        );

        controlsText.setEditable(false);
        controlsText.setBackground(Color.BLACK);
        controlsText.setForeground(Color.YELLOW);
        controlsText.setFont(new Font("Arial", Font.BOLD, 20));

        JScrollPane scrollPane = new JScrollPane(controlsText);
        scrollPane.setBorder(null);

        getContentPane().add(scrollPane);
        setBackground(Color.BLACK);
        setLocationRelativeTo(null);


        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
