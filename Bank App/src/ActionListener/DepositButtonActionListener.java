package ActionListener;

import BankDataBase.BankDataBase;
import GUI.BankAppGUI;
import GUI.DepositGUI;
import GUI.LoginGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * lets the user to enter money into their bank account.
 */
public class DepositButtonActionListener implements ActionListener {

    private BankDataBase bankDataBase;
    private BankAppGUI bankAppGUI;
    private LoginGUI loginGUI;

    private String username;

    public DepositButtonActionListener(BankDataBase bankDataBase, BankAppGUI bankAppGUI, LoginGUI loginGUI, String username) {
        this.bankDataBase = bankDataBase;
        this.bankAppGUI = bankAppGUI;
        this.loginGUI = loginGUI;
        this.username = username;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // pop the DepositGUI to let the user enter the amount that they would like to deposit
        new DepositGUI(bankDataBase, loginGUI, bankAppGUI);

    }
}
