package GUI;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class UsernameAlreadyExistsGUI {

    private JFrame username_alrdy_exists_frame;
    private JLabel username_alrdy_exists_label;

    public UsernameAlreadyExistsGUI(){

        username_alrdy_exists_frame = new JFrame("Username already exists");
        Container frame_cp = username_alrdy_exists_frame.getContentPane();
        username_alrdy_exists_frame.setLayout(new FlowLayout());

        username_alrdy_exists_label = new JLabel("  Username already exists");
        username_alrdy_exists_label.setFont(new Font("Dialog", Font.BOLD, 16));
        frame_cp.add(username_alrdy_exists_label);


        username_alrdy_exists_frame.setSize(300, 90);
        username_alrdy_exists_frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        username_alrdy_exists_frame.setResizable(false);
        username_alrdy_exists_frame.setLocationRelativeTo(null);
        username_alrdy_exists_frame.setVisible(true);

    }


}
