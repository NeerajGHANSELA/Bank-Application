package ActionListener;

import BankDataBase.BankDataBase;
import GUI.BankAppGUI;
import GUI.DepositGUI;
import GUI.LoginGUI;
import GUI.WithdrawMoneyGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WithdrawButtonActionListener implements ActionListener {

    private BankDataBase bankDataBase;
    private LoginGUI loginGUI;
    private BankAppGUI bankAppGUI;

    public WithdrawButtonActionListener(BankDataBase bankDataBase, LoginGUI loginGUI, BankAppGUI bankAppGUI) {
        this.bankDataBase = bankDataBase;
        this.loginGUI = loginGUI;
        this.bankAppGUI = bankAppGUI;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // pop the WithdrawGUI to let the user remove the amount that they would like to withdraw
        new WithdrawMoneyGUI(bankDataBase, loginGUI, bankAppGUI);
    }
}
