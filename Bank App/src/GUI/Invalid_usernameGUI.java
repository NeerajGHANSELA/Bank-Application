package GUI;

import GUI.LoginGUI;

import javax.swing.*;
import java.awt.*;

import BankDataBase.*;
import ActionListener.CreateAccountButtonActionListener;

public class Invalid_usernameGUI extends JFrame {

    private LoginGUI login_GUI;
    private BankDataBase bank_data_base;
    private PasswordDoesNotMatchGUI passwordDoesNotMatchGUI;


    // GUI
    private JTextField user_tf;
    private JLabel invalid_text;
    private JPanel panel;
    private JLabel no_account_text;
    private JButton create_new_account_button;

    public Invalid_usernameGUI(LoginGUI login_GUI, BankDataBase bank_data_base, PasswordDoesNotMatchGUI passwordDoesNotMatchGUI) {
        this.login_GUI = login_GUI;
        this.bank_data_base = bank_data_base;
        this.passwordDoesNotMatchGUI = passwordDoesNotMatchGUI;
    }

    public void Initialize() {
        // send a message saying the username is invalid

        user_tf = login_GUI.getUsername_txt_field();

        if(user_tf.getText().length() == 0) {
            setLayout(new GridLayout(1, 1));
            // display the username is invalid
            invalid_text = new JLabel("  username entered is invalid");
            add(invalid_text);  // adding the invalid text to the frame
            setSize(300, 100);
        } else {
            setLayout(new GridLayout(2, 1, 0, 5));
            // display the username is not correct
            invalid_text = new JLabel("  username entered is invalid");
            add(invalid_text);  // adding the invalid text to the frame

            panel = new JPanel();
            panel.setLayout(new GridLayout(1, 2, 5, 0));

            no_account_text = new JLabel("  Don't have an account?");
            panel.add(no_account_text);

            JPanel inside_panel = new JPanel();
            inside_panel.setLayout(new GridLayout(2, 1, 5, 5));

            create_new_account_button = new JButton("create new account");
            create_new_account_button.addActionListener(new CreateAccountButtonActionListener(bank_data_base,
                    passwordDoesNotMatchGUI));
            inside_panel.add(new JLabel());  // adding an empty label
            inside_panel.add(create_new_account_button);

            panel.add(inside_panel);

            add(panel);

            setSize(350, 150);
        }

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void DisposeInvalid_usernameGUI() {
        dispose();
    }

    public JButton getCreate_new_account_button_in_Invalid_usernameGUI() {
        return create_new_account_button;
    }

    public static class DisposeInvalid_usernameGUI {

        public static void Dispose_Invalid_usernameGUI() {

        }
    }
}



