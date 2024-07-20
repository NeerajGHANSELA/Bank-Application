package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

import BankDataBase.*;

/**
 * resetting the password because could not log in
 */
public class ResetPasswordGUI {

    private BankDataBase bankDataBase;  // bank database used to update the user's information.
    private PasswordDoesNotMatchGUI passwordDoesNotMatchGUI;

    // GUI
    private JFrame reset_password_gui_frame;
    private JLabel new_password_label;
    private JPasswordField new_password_text_field;
    private JLabel re_enter_new_password_label;
    private JPasswordField re_enter_new_password_txt_field;
    private JButton enter_button;

    private String username;

    public ResetPasswordGUI(BankDataBase bankDataBase, PasswordDoesNotMatchGUI passwordDoesNotMatchGUI) {
        this.bankDataBase = bankDataBase;
        this.passwordDoesNotMatchGUI = passwordDoesNotMatchGUI;

    }


    /**
     * Pops the ResetPassowrdGUI frame
     */
    public void Initialize() {
        // reset password GUI

        reset_password_gui_frame = new JFrame("reset password");  // initializing the reset password frame
        Container frame_cp = reset_password_gui_frame.getContentPane();
        frame_cp.setBackground(new Color(169, 223, 191));

        reset_password_gui_frame.setLayout(new GridLayout(5, 1, 0, 5));  // layout of the frame

        new_password_label = new JLabel("  enter your new password");
        frame_cp.add(new_password_label);  // adding the new password label to the 1st row

        new_password_text_field = new JPasswordField();
        frame_cp.add(new_password_text_field);  // adding the new password text field to the 2nd row


        re_enter_new_password_label = new JLabel("  re-enter your new password");
        frame_cp.add(re_enter_new_password_label);  // adding the re-enter new password label to the 4th row

        re_enter_new_password_txt_field = new JPasswordField();
        frame_cp.add(re_enter_new_password_txt_field);

        JPanel last_row_panel = new JPanel();
        last_row_panel.setLayout(new GridLayout(1, 2, 5, 5));
        last_row_panel.setBackground(new Color(169, 223, 191));
        last_row_panel.add(new JLabel());  // adding an empty label

        // enter button
        enter_button = new JButton("enter");
        enter_button.addActionListener(new enter_buttonActionListener(this, passwordDoesNotMatchGUI));
        last_row_panel.add(enter_button);

        frame_cp.add(last_row_panel);  // adding the last_row_panel to the content pane

        // check whether the new password and re-entered new password match
        DoesNewPasswordMatch(getNewPassword(), getRe_enteredNewPassword());


        reset_password_gui_frame.setSize(300, 250);
        reset_password_gui_frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        reset_password_gui_frame.setResizable(false);
        reset_password_gui_frame.setLocationRelativeTo(null);
        reset_password_gui_frame.setVisible(true);
    }

    public void DisposeResetPasswordGUIFrame() {
        reset_password_gui_frame.dispose();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewPassword() {
        return new_password_text_field.getText();
    }

    public String getRe_enteredNewPassword() {
        return re_enter_new_password_txt_field.getText();
    }

    private boolean DoesNewPasswordMatch(String new_password, String re_entered_new_password) {
        if(re_entered_new_password.equals(new_password)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * update the password for the user in the bank database
     * @param new_password new password set by the user
     */
    private void Update_password_in_database(String new_password) {
        bankDataBase.UpdatePassword(this.username, new_password);
    }

    private static class enter_buttonActionListener implements ActionListener {

        private ResetPasswordGUI resetPasswordGUI;
        private PasswordDoesNotMatchGUI passwordDoesNotMatchGUI;

        public enter_buttonActionListener(ResetPasswordGUI resetPasswordGUI, PasswordDoesNotMatchGUI passwordDoesNotMatchGUI) {
            this.resetPasswordGUI = resetPasswordGUI;
            this.passwordDoesNotMatchGUI = passwordDoesNotMatchGUI;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // check whether the password and the re-entered password match each other.
            String new_password = resetPasswordGUI.getNewPassword();
            String re_entered_password = resetPasswordGUI.getRe_enteredNewPassword();

            boolean do_password_match = resetPasswordGUI.DoesNewPasswordMatch(new_password, re_entered_password);

            // if the password match, update the password for the user in the bank database
            if(do_password_match) {
                resetPasswordGUI.Update_password_in_database(new_password);

                // pop the GUI saying password was successfully updated
                new PasswordSuccessfullyUpdatedGUI();

                // then close the successful GUi and the ResetPasswordGUI
                resetPasswordGUI.DisposeResetPasswordGUIFrame();
            } else {
                // pop the message saying that the password do not match
                passwordDoesNotMatchGUI.Initialize();
            }
        }
    }
}
