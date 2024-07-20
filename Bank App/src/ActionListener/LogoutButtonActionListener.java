package ActionListener;

import BankDataBase.BankDataBase;
import GUI.BankAppGUI;
import GUI.LoginGUI;
import GUI.PasswordDoesNotMatchGUI;
import GUI.ResetPasswordGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * After the user has clicked the logout button in BankAppGUI, goes back to the login page.
 */
public class LogoutButtonActionListener implements ActionListener {

    private BankAppGUI bankAppGUI;
    private BankDataBase bankDataBase;
    private ResetPasswordGUI resetPasswordGUI;
    private PasswordDoesNotMatchGUI passwordDoesNotMatchGUI;

    public LogoutButtonActionListener(BankAppGUI bankAppGUI, BankDataBase bankDataBase, ResetPasswordGUI resetPasswordGUI,
                                      PasswordDoesNotMatchGUI passwordDoesNotMatchGUI) {
        this.bankAppGUI = bankAppGUI;
        this.bankDataBase = bankDataBase;
        this.resetPasswordGUI = resetPasswordGUI;
        this.passwordDoesNotMatchGUI = passwordDoesNotMatchGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // dispose the BankAppGUI
        bankAppGUI.DisposeBankAppGUI();

        // initialize the LoginGUI
        new LoginGUI(bankDataBase, resetPasswordGUI, passwordDoesNotMatchGUI);
    }
}
