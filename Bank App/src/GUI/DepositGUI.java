package GUI;

import BankDataBase.BankDataBase;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

public class DepositGUI {

    private BankDataBase bankDataBase;
    private LoginGUI loginGUI;
    private BankAppGUI bankAppGUI;

    // GUI
    private JFrame depositGUI_frame;
    private JLabel enter_amount_label;
    private JFormattedTextField amount_to_deposit_txt_field;
    private JButton enter_button;

    public DepositGUI(BankDataBase bankDataBase, LoginGUI loginGUI, BankAppGUI bankAppGUI) {
        this.bankDataBase = bankDataBase;
        this.loginGUI = loginGUI;
        this.bankAppGUI = bankAppGUI;

        Initialize();
    }

    private void Initialize() {
        depositGUI_frame = new JFrame("Deposit Money");
        Container frame_cp = depositGUI_frame.getContentPane();

        depositGUI_frame.setLayout(new GridLayout(4, 2, 2, 0));

        frame_cp.add(new JLabel());
        frame_cp.add(new JLabel());

        enter_amount_label = new JLabel("  Enter the amount     $");
        enter_amount_label.setFont(new Font("Monaco", Font.PLAIN, 16));
        frame_cp.add(enter_amount_label);

        DecimalFormat format = new DecimalFormat("#,###.00");
        format.setDecimalSeparatorAlwaysShown(true);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setAllowsInvalid(false);

        amount_to_deposit_txt_field = new JFormattedTextField(formatter);
        amount_to_deposit_txt_field.setFont(new Font("Monaco", Font.PLAIN, 18));
        amount_to_deposit_txt_field.setHorizontalAlignment(SwingConstants.RIGHT);
        frame_cp.add(amount_to_deposit_txt_field);

        frame_cp.add(new JLabel());  // inserting empty label to the frame
        frame_cp.add(new JLabel());
        frame_cp.add(new JLabel());

        enter_button = new JButton("enter");
        enter_button.setFont(new Font("Dialog", Font.BOLD, 14));
        // adding action listener to the enter button
        enter_button.addActionListener(new EnterButtonActionListenerInDepositGUI(bankDataBase, this, loginGUI, bankAppGUI));
        frame_cp.add(enter_button);

        depositGUI_frame.setSize(350, 200);
        depositGUI_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        depositGUI_frame.setResizable(false);
        depositGUI_frame.setLocationRelativeTo(null);
        depositGUI_frame.setVisible(true);
    }

    /**
     * @return returns the amount to be deposited into the bank account in a String format
     */
    public String getAmountToBeDeposited() {
        return String.valueOf(amount_to_deposit_txt_field.getValue());
    }

    public void DisposeDepositGUI() {
        depositGUI_frame.dispose();
    }

    /**
     * Action Listener for the enter button in the DepositGUI
     */
    private class EnterButtonActionListenerInDepositGUI implements ActionListener {

        private BankDataBase bankDataBase;
        private DepositGUI depositGUI;
        private LoginGUI loginGUI;
        private BankAppGUI bankAppGUI;

        public EnterButtonActionListenerInDepositGUI(BankDataBase bankDataBase, DepositGUI depositGUI, LoginGUI loginGUI,
                                                     BankAppGUI bankAppGUI) {
            this.bankDataBase = bankDataBase;
            this.depositGUI = depositGUI;
            this.loginGUI = loginGUI;
            this.bankAppGUI = bankAppGUI;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // add the amount entered by the user into their bank account

            String amount = depositGUI.getAmountToBeDeposited();

            String amount_to_be_deposit_str = "";

            // iterate over the String amount
            for(int i = 0; i < amount.length(); i++) {
                if(amount.charAt(i) == ',' || amount.charAt(i) == '$' || amount.charAt(i) == ' ') {
                    amount_to_be_deposit_str = amount_to_be_deposit_str;
                } else {
                    amount_to_be_deposit_str += amount.charAt(i);
                }
            }

            System.out.println("amount_to_be_deposit:" + amount_to_be_deposit_str);

            float amount_to_be_deposit = Float.parseFloat(amount_to_be_deposit_str);
            System.out.println("amount_to_be_deposit (FLOAT) :" + amount_to_be_deposit_str);

            // get the username of the user
            String username = loginGUI.getUsername();

            // depositing the money into the bank account of the user
            bankDataBase.DepositMoney(username, amount_to_be_deposit);

            // update the current balance text field into showing the updated current bank balance
            bankAppGUI.setCurrent_balance_txt_field(bankDataBase.getCurrentBankBalance(username));

            // pop the MoneyDepositedSuccessfullyGUI
            new MoneyDepositedSuccessfullyGUI(bankDataBase, loginGUI);

            // dispose the DepositGUI
            depositGUI.DisposeDepositGUI();
        }

    }


}
