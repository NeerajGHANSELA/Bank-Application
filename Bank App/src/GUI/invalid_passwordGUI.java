package GUI;

import javax.swing.*;
import java.awt.*;

public class invalid_passwordGUI extends JFrame {
    public invalid_passwordGUI() {

        setLayout(new GridLayout(2, 1, 0, 5));

        // display the username is not correct
        JLabel invalid_text = new JLabel("  username or password entered is invalid");
        add(invalid_text);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2, 5, 0));

        JLabel no_account_text = new JLabel("  Don't have an account?");
        panel.add(no_account_text);

        JPanel inside_panel = new JPanel();
        inside_panel.setLayout(new GridLayout(2, 1, 5, 5));

        JButton create_new_account_button = new JButton("create new account");
        inside_panel.add(new JLabel());  // adding an empty label
        inside_panel.add(create_new_account_button);

        panel.add(inside_panel);

        add(panel);

        setSize(350, 150);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
