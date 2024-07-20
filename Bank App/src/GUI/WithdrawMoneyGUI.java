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

public class WithdrawMoneyGUI {

    private BankDataBase bankDataBase;
    private LoginGUI loginGUI;
    private BankAppGUI bankAppGUI;

    // GUI
    private JFrame withdrawMoneyGUI_frame;
    private JLabel enter_amount_label;
    private JFormattedTextField amount_to_withdraw_txt_field;
    private JButton enter_button;

    public WithdrawMoneyGUI(BankDataBase bankDataBase, LoginGUI loginGUI, BankAppGUI bankAppGUI) {
        this.bankDataBase = bankDataBase;
        this.loginGUI = loginGUI;
        this.bankAppGUI = bankAppGUI;

        // if the bank balance is 0, user cannot withdraw any money
        if(bankDataBase.getCurrentBankBalance(loginGUI.getUsername()) <= 0) {  // user does not have any money
            // create a gui for this
            new WithdrawErrorGUI();  // pop the WithdrawErrorGUI
            System.out.println("Cannot withdraw any money. Bank balance is : $" + bankDataBase.getCurrentBankBalance(loginGUI.getUsername()));
        } else {  // user does have money in their account
            Initialize();
        }
    }

    private void Initialize() {
        withdrawMoneyGUI_frame = new JFrame("Withdraw Money");
        Container frame_cp = withdrawMoneyGUI_frame.getContentPane();

        withdrawMoneyGUI_frame.setLayout(new GridLayout(4, 2, 2, 0));

        frame_cp.add(new JLabel());  // adding empty label
        frame_cp.add(new JLabel());

        // inserting enter the amount label
        enter_amount_label = new JLabel("  Enter the amount     $");
        enter_amount_label.setFont(new Font("Monaco", Font.PLAIN, 16));
        frame_cp.add(enter_amount_label);

        // inserting the amount text field
        DecimalFormat format = new DecimalFormat("#,###.00");
        format.setDecimalSeparatorAlwaysShown(true);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setAllowsInvalid(false);

        amount_to_withdraw_txt_field = new JFormattedTextField(formatter);
        amount_to_withdraw_txt_field.setFont(new Font("Monaco", Font.PLAIN, 18));
        amount_to_withdraw_txt_field.setHorizontalAlignment(SwingConstants.RIGHT);
        frame_cp.add(amount_to_withdraw_txt_field);

        frame_cp.add(new JLabel());  // inserting empty label to the frame
        frame_cp.add(new JLabel());
        frame_cp.add(new JLabel());

        // inserting the enter button
        enter_button = new JButton("enter");
        enter_button.setFont(new Font("Dialog", Font.BOLD, 14));
        // adding action listener to the enter button
        enter_button.addActionListener(new EnterButtonActionListenerInWithdrawMoneyGUI(bankDataBase, this, loginGUI, bankAppGUI));
        frame_cp.add(enter_button);


        withdrawMoneyGUI_frame.setSize(350, 200);
        withdrawMoneyGUI_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        withdrawMoneyGUI_frame.setResizable(false);
        withdrawMoneyGUI_frame.setLocationRelativeTo(null);
        withdrawMoneyGUI_frame.setVisible(true);
    }

    public String getAmountToBeWithdrawn() {
        return String.valueOf(amount_to_withdraw_txt_field.getValue());
    }

    public void DisposeWithdrawMoneyGUI() {
        withdrawMoneyGUI_frame.dispose();
    }

    /**
     * Action listner for the enter button in the WithdrawMoneyGUI
     */
    private class EnterButtonActionListenerInWithdrawMoneyGUI implements ActionListener {

        private BankDataBase bankDataBase;
        private WithdrawMoneyGUI withdrawMoneyGUI;
        private LoginGUI loginGUI;
        private BankAppGUI bankAppGUI;

        private EnterButtonActionListenerInWithdrawMoneyGUI(BankDataBase bankDataBase, WithdrawMoneyGUI withdrawMoneyGUI,
                                                            LoginGUI loginGUI, BankAppGUI bankAppGUI) {
            this.bankDataBase = bankDataBase;
            this.withdrawMoneyGUI = withdrawMoneyGUI;
            this.loginGUI = loginGUI;
            this.bankAppGUI = bankAppGUI;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // subtract the amount entered by the user from their bank account

            // first check the current bank balance of the user
            float user_current_bank_balance = bankDataBase.getCurrentBankBalance(loginGUI.getUsername());

            // check whether the money to be withdrawn exceeds the limit of the account

            String amount = withdrawMoneyGUI.getAmountToBeWithdrawn();

            String amount_to_be_withdrawn_str = "";

            // iterate over the String amount. Trying to create a number out of the string
            for(int i = 0; i < amount.length(); i++) {
                if(amount.charAt(i) == ',' || amount.charAt(i) == '$' || amount.charAt(i) == ' ') {
                    amount_to_be_withdrawn_str = amount_to_be_withdrawn_str;
                } else {
                    amount_to_be_withdrawn_str += amount.charAt(i);
                }
            }

            System.out.println("amount_to_be_withdrawn:" + amount_to_be_withdrawn_str);

            float amount_to_be_withdrawn = Float.parseFloat(amount_to_be_withdrawn_str);
            System.out.println("amount_to_be_deposit (FLOAT) :" + amount_to_be_withdrawn_str);

            if((user_current_bank_balance - amount_to_be_withdrawn) < 0) {  // user cannot withdraw the amount that has been specified
                // create a gui for this
                new WithdrawAmountErrorGUI();  // pop the WithdrawAmountErrorGUI
                System.out.println("Cannot withdraw the amount specified. Amount exceeds the bank balance");
            } else {
                // get the username of the user
                String username = loginGUI.getUsername();

                // taking the money out of the bank account of the user
                bankDataBase.WithdrawMoney(username, amount_to_be_withdrawn);

                // update the current balance text field into showing the updated current bank balance
                bankAppGUI.setCurrent_balance_txt_field(bankDataBase.getCurrentBankBalance(username));

                // pop the MoneyWithdrawnSuccessfullyGUI
                new MoneyWithdrawnSuccessfullyGUI(bankDataBase, loginGUI);

                // dispose the WithdrawMoneyGUI
                withdrawMoneyGUI.DisposeWithdrawMoneyGUI();
            }
        }
    }


    private class WithdrawErrorGUI {

        private JFrame withdrawErrorGUI_frame;
        private JLabel withdraw_error_label;

        private WithdrawErrorGUI() {

            withdrawErrorGUI_frame = new JFrame("Withdraw Error");
            Container frame_cp = withdrawErrorGUI_frame.getContentPane();

            withdraw_error_label = new JLabel("Cannot withdraw any money. Bank balance is : $" + bankDataBase.getCurrentBankBalance(loginGUI.getUsername()));
            withdraw_error_label.setHorizontalAlignment(SwingConstants.CENTER);
            withdraw_error_label.setFont(new Font("Monaco", Font.BOLD, 16));
            frame_cp.add(withdraw_error_label);

            withdrawErrorGUI_frame.setSize(550, 200);
            withdrawErrorGUI_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            withdrawErrorGUI_frame.setResizable(false);
            withdrawErrorGUI_frame.setLocationRelativeTo(null);
            withdrawErrorGUI_frame.setVisible(true);
        }
    }

    private class WithdrawAmountErrorGUI {

        private JFrame withdraw_amount_errorGUI_frame;
        private JLabel withdraw_amount_error_label;

        private WithdrawAmountErrorGUI() {

            withdraw_amount_errorGUI_frame = new JFrame("Withdraw amount Error");
            Container frame_cp = withdraw_amount_errorGUI_frame.getContentPane();

            withdraw_amount_error_label = new JLabel("Cannot withdraw the amount " + getAmountToBeWithdrawn() +
                    ". Amount exceeds the bank balance.");
            withdraw_amount_error_label.setHorizontalAlignment(SwingConstants.CENTER);
            withdraw_amount_error_label.setFont(new Font("Monaco", Font.BOLD, 16));
            frame_cp.add(withdraw_amount_error_label);


            withdraw_amount_errorGUI_frame.setSize(680, 200);
            withdraw_amount_errorGUI_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            withdraw_amount_errorGUI_frame.setResizable(false);
            withdraw_amount_errorGUI_frame.setLocationRelativeTo(null);
            withdraw_amount_errorGUI_frame.setVisible(true);
        }
    }
}
