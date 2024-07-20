package ActionListener;

import GUI.ResetPasswordGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

import BankDataBase.*;

/**
 * GUI for the forgot button in the LoginGUI.
 * Lets the user reset their password.
 */
public class ForgotButtonActionListener implements ActionListener {

    private BankDataBase bankDataBase;
    private ResetPasswordGUI resetPasswordGUI;

    // GUI
    private JFrame forgot_button_frame;
    private JPanel upper_panel;
    private JLabel ask_username_label;
    private JTextField ask_username_txt_field;

    private JPanel lower_panel;
    private JPanel lower_upper_panel;
    private JButton enter_button;
    private JPanel lower_lower_panel;
    private JTextArea text_area;

    public ForgotButtonActionListener(BankDataBase bankDataBase, ResetPasswordGUI resetPasswordGUI) {
        this.bankDataBase = bankDataBase;
        this.resetPasswordGUI = resetPasswordGUI;
    }

    /**
     * After the forgot button is clicked, pops the frame to ask the user to enter their username
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // ask the user to enter their username
        Initialize();
    }

    public void Initialize() {
        forgot_button_frame = new JFrame("Forgot password");
        forgot_button_frame.setLayout(new GridLayout(2, 1, 5, 5));
        Container frame_cp = forgot_button_frame.getContentPane();
        frame_cp.setBackground(new Color(169, 223, 191));

        // dividing the frame into 2 (upper and lower) panel

        upper_panel = new JPanel();
        upper_panel.setLayout(new GridLayout(2, 1, 10, 5));
        upper_panel.setBackground(new Color(212, 239, 223));
        ask_username_label = new JLabel("  please enter your username ");
        upper_panel.add(ask_username_label);

        ask_username_txt_field = new JTextField();
        ask_username_txt_field.setPreferredSize(new Dimension(forgot_button_frame.getWidth()-30, 20));
        upper_panel.add(ask_username_txt_field);

        frame_cp.add(upper_panel);  // adding upper panel into the frame

        lower_panel = new JPanel();
        lower_panel.setLayout(new GridLayout(2, 1, 5, 5));
        lower_panel.setBackground(new Color(169, 223, 191));

        // divide the lower panel into 2 panel

            // lower- upper panel
        lower_upper_panel = new JPanel();
        lower_upper_panel.setLayout(new GridLayout(1, 2, 5, 5));
        lower_upper_panel.setBackground(new Color(169, 223, 191));

        lower_upper_panel.add(new JLabel());  // adding an empty space into lower_upper_panel

        enter_button = new JButton("enter");
        enter_button.addActionListener(new enter_buttonActionListener(bankDataBase, this, resetPasswordGUI));
        lower_upper_panel.add(enter_button);

        lower_panel.add(lower_upper_panel);  // adding the lower_upper_panel into the lower panel

            // lower- lower panel
        lower_lower_panel = new JPanel();
        lower_lower_panel.setBackground(new Color(169, 223, 191));

        text_area = new JTextArea();
        text_area.setEditable(false);
        text_area.setRows(2);
        text_area.setColumns(30);

        lower_lower_panel.add(text_area);

        lower_panel.add(lower_lower_panel);  // adding the lower_lower_panel into the lower panel

        frame_cp.add(lower_panel);  // adding the lower panel into the frame


        forgot_button_frame.setSize(300, 250);
        forgot_button_frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        forgot_button_frame.setResizable(false);
        forgot_button_frame.setLocationRelativeTo(null);
        forgot_button_frame.setVisible(true);
    }

    public void DisposeForgotButtonFrame() {
        forgot_button_frame.dispose();
    }
    public String getUsernameEntered() {
        return ask_username_txt_field.getText();
    }

    public void setText_area(String str) {
        text_area.setText(str);
    }

    private static class enter_buttonActionListener implements ActionListener {

        private BankDataBase bankDataBase;
        private ForgotButtonActionListener forgotButtonActionListener;
        private ResetPasswordGUI resetPasswordGUI;

        public enter_buttonActionListener(BankDataBase bankDataBase, ForgotButtonActionListener forgotButtonActionListener,
                                          ResetPasswordGUI resetPasswordGUI) {
            this.bankDataBase = bankDataBase;
            this.forgotButtonActionListener = forgotButtonActionListener;
            this.resetPasswordGUI = resetPasswordGUI;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // check whether such username exists in the database
            // if yes - ask the user if they want to change their password
            // if no - print out "such username does not exist"
            String username_entered = forgotButtonActionListener.getUsernameEntered();
            boolean does_username_exist = bankDataBase.Check_username(username_entered);

            if(does_username_exist) {  // username does exist
                // send the username to the ResetPasswordGUI
                resetPasswordGUI.setUsername(username_entered);

                // pop up the ResetPasswordGUI for the user to reset their password.
                resetPasswordGUI.Initialize();

                // dispose the forgot button
                forgotButtonActionListener.DisposeForgotButtonFrame();

            } else {  // username does not exist
                // print it in the text_area
                forgotButtonActionListener.setText_area("Username does not exist");
            }
        }
    }
}
