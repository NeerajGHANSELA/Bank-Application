package GUI;

import BankDataBase.BankDataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoneyWithdrawnSuccessfullyGUI {

    private BankDataBase bankDataBase;
    private LoginGUI loginGUI;

    // GUI
    private JFrame successfully_withdrawn_frame;
    private Container frame_cp;
    private JPanel upper_panel;
    private JLabel successfully_withdrawn_label;
    private JPanel middle_panel;
    private JLabel current_bank_balance_label;
    private JTextField current_bank_balance_txt_field;
    private JPanel lower_panel;
    private JButton ok_button;

    public MoneyWithdrawnSuccessfullyGUI(BankDataBase bankDataBase, LoginGUI loginGUI) {
        this.bankDataBase = bankDataBase;
        this.loginGUI = loginGUI;

        // GUI
        successfully_withdrawn_frame = new JFrame("successfully deposited");
        successfully_withdrawn_frame.setLayout(new GridLayout(3, 1, 0, 5));
        frame_cp = successfully_withdrawn_frame.getContentPane();

        // inserting the upper panel
        upper_panel = new JPanel();
        // inserting the successfully_deposited_label into the upper panel
        successfully_withdrawn_label = new JLabel("  Money has been successfully withdrawn");
        successfully_withdrawn_label.setFont(new Font("Monaco", Font.BOLD, 16));
        upper_panel.add(successfully_withdrawn_label);  // adding the successfully_deposited_label to the upper panel

        frame_cp.add(upper_panel);  // adding the upper panel to the frame

        // inserting the middle panel
        middle_panel = new JPanel();
        middle_panel.setLayout(new GridLayout(1, 2, 5, 0));  // lower panel is divided into 2 columns

            // inserting the current_bank_balance_label to the lower panel
        current_bank_balance_label = new JLabel("  Current bank balance");
        current_bank_balance_label.setFont(new Font("Monaco", Font.PLAIN, 18));
        middle_panel.add(current_bank_balance_label);

                // inserting the current_bank_balance_txt_field to the lower panel
        current_bank_balance_txt_field = new JTextField(String.format("$%,.2f", bankDataBase.getCurrentBankBalance(loginGUI.getUsername())));
        current_bank_balance_txt_field.setFont(new Font("Dialog", Font.PLAIN, 16));
        current_bank_balance_txt_field.setEditable(false);
        middle_panel.add(current_bank_balance_txt_field);

        frame_cp.add(middle_panel); // adding the lower panel to the frame

        // inserting the lower panel
        lower_panel = new JPanel();
        lower_panel.setLayout(new GridLayout(1, 2, 5, 0));  // dividing the layout of the lower panel into 2 columns

        lower_panel.add(new JLabel());  // adding an empty label

        ok_button = new JButton("OK");
        ok_button.addActionListener(new Ok_buttonActionListenerInWithdrawnSuccessfully(this));
        lower_panel.add(ok_button);

        frame_cp.add(lower_panel);

        successfully_withdrawn_frame.setSize(450, 250);
        successfully_withdrawn_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        successfully_withdrawn_frame.setResizable(false);
        successfully_withdrawn_frame.setLocationRelativeTo(null);
        successfully_withdrawn_frame.setVisible(true);
    }

    /**
     * Used to dispose the MoneyWithdrawnSuccessfullyGUI
     */
    private void DisposeMoneyWithdrawnSuccessfullyGUI() {
        successfully_withdrawn_frame.dispose();
    }

    /**
     * Action listener for the ok button in MoneyWithdrawnSuccessfullyGUI
     */
    private class Ok_buttonActionListenerInWithdrawnSuccessfully implements ActionListener {

        private MoneyWithdrawnSuccessfullyGUI moneyWithdrawnSuccessfullyGUI;

        private Ok_buttonActionListenerInWithdrawnSuccessfully(MoneyWithdrawnSuccessfullyGUI moneyWithdrawnSuccessfullyGUI) {
            this.moneyWithdrawnSuccessfullyGUI = moneyWithdrawnSuccessfullyGUI;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // dispose the MoneyWithdrawnSuccessfullyGUI
            moneyWithdrawnSuccessfullyGUI.DisposeMoneyWithdrawnSuccessfullyGUI();
        }
    }
}
