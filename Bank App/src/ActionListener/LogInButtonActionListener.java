package ActionListener;

import GUI.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import BankDataBase.*;

public class LogInButtonActionListener implements ActionListener {
    private LoginGUI login_GUI;
    private BankDataBase bank_data_base;
    private ResetPasswordGUI resetPasswordGUI;
    private PasswordDoesNotMatchGUI passwordDoesNotMatchGUI;

    public LogInButtonActionListener(LoginGUI login_GUI, BankDataBase bank_data_base, ResetPasswordGUI resetPasswordGUI,
                                     PasswordDoesNotMatchGUI passwordDoesNotMatchGUI) {
        this.login_GUI = login_GUI;
        this.bank_data_base = bank_data_base;
        this.resetPasswordGUI = resetPasswordGUI;
        this.passwordDoesNotMatchGUI = passwordDoesNotMatchGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // check whether the username is valid: whether the username exists within the bank database.
        boolean is_username_valid = checkUsername();

        if(!is_username_valid) {  // if the username is invalid
            // pop the Invalid_usernameGUI
            Invalid_usernameGUI invalid_usernameGUI = new Invalid_usernameGUI(login_GUI, bank_data_base, passwordDoesNotMatchGUI);
            invalid_usernameGUI.Initialize();
        } else {  // username is valid
            // check whether the password is valid

            boolean is_password_valid = checkPassword();

            if(!is_password_valid) {  // if password is invalid
                // pop the message
                new invalid_passwordGUI();
                return;
            } else {  // password is valid
                // username and the password are valid

                // dispose the LogInGUI
                login_GUI.DisposeLoginGUI();

                // pop the BankAppGUI for the user to enter their account
                new BankAppGUI(login_GUI, bank_data_base, resetPasswordGUI, passwordDoesNotMatchGUI);


            }
        }
    }

    /**
     * checks whether the username entered is valid by checking over the bank's database.
     * @return returns true or false based on whether the username exists in the bank's database.
     */
    private boolean checkUsername() {
        String username_entered = login_GUI.getUsername_txt_field().getText();
        boolean is_username_valid = bank_data_base.Check_username(username_entered);

        if(is_username_valid) {
            return true;  // username is valid
        } else {
            return false;  // username is not valid
        }
    }

    /**
     * checks whether the password entered by the user is valid by checking over the bank's database.
     * @return returns true or false based on whether the password exists in the bank's database.
     */
    private boolean checkPassword() {
        String password_entered = login_GUI.getPassword_txt_field().getText();
        String username_entered = login_GUI.getUsername_txt_field().getText();
        boolean is_password_valid = bank_data_base.Check_password(password_entered, username_entered);

        if(is_password_valid) {
            return true;  // password is valid
        } else {
            return false;  // password is invalid
        }
    }
}
