package GUI;

import javax.swing.*;

import java.awt.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * pops a message to the screen saying that the password and the re_entered password do not match.
 */
public class PasswordDoesNotMatchGUI {

    // GUI
    private JFrame password_dont_match_frame;
    private JLabel password_dont_match_label;

    public PasswordDoesNotMatchGUI() {}

    public void Initialize() {

        password_dont_match_frame = new JFrame("password do not match");
        Container frame_cp = password_dont_match_frame.getContentPane();
        password_dont_match_frame.setLayout(new FlowLayout());

        password_dont_match_label = new JLabel("  password does not match");
        password_dont_match_label.setFont(new Font("Dialog", Font.BOLD, 16));
        frame_cp.add(password_dont_match_label);


        password_dont_match_frame.setSize(300, 90);
        password_dont_match_frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        password_dont_match_frame.setResizable(false);
        password_dont_match_frame.setLocationRelativeTo(null);
        password_dont_match_frame.setVisible(true);
    }
}
