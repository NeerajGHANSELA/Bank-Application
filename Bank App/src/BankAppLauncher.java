import ActionListener.*;
import BankDataBase.BankDataBase;
import GUI.*;

public class BankAppLauncher {

    public static void main(String[] args) {


        BankDataBase bank_data_base = new BankDataBase(10);
        PasswordDoesNotMatchGUI passwordDoesNotMatchGUI = new PasswordDoesNotMatchGUI();

        ResetPasswordGUI resetPasswordGUI = new ResetPasswordGUI(bank_data_base, passwordDoesNotMatchGUI);
        ForgotButtonActionListener forgotButtonActionListener = new ForgotButtonActionListener(bank_data_base, resetPasswordGUI);

        LoginGUI login_gui = new LoginGUI(bank_data_base, resetPasswordGUI, passwordDoesNotMatchGUI);  // invalid_usernameGUI is used to create ActionListener.CreateAccountButtonActionListener
        Invalid_usernameGUI invalid_usernameGUI = new Invalid_usernameGUI(login_gui, bank_data_base, passwordDoesNotMatchGUI);

        CreateAccountButtonActionListener createAccountButtonActionListener = new CreateAccountButtonActionListener(bank_data_base,
                passwordDoesNotMatchGUI);

        LogInButtonActionListener logInButtonActionListener = new LogInButtonActionListener(login_gui, bank_data_base, resetPasswordGUI,
                passwordDoesNotMatchGUI);
    }
}