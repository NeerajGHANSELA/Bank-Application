package GUI;

import ActionListener.DepositButtonActionListener;
import ActionListener.LogoutButtonActionListener;
import ActionListener.WithdrawButtonActionListener;
import BankDataBase.BankDataBase;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class BankAppGUI {
    private LoginGUI loginGUI;
    private BankDataBase bankDataBase;
    private ResetPasswordGUI resetPasswordGUI;
    private PasswordDoesNotMatchGUI passwordDoesNotMatchGUI;

    // GUI
    private JFrame bank_app_GUI_frame;
    private Container frame_cp;
    private JLabel welcome_label;
    private JLabel label;
    private JLabel current_balance_label;
    private JTextField current_balance_txt_field;
    private JButton deposit_button;
    private JButton withdraw_button;
    private JButton logout_button;

    public BankAppGUI(LoginGUI loginGUI, BankDataBase bankDataBase, ResetPasswordGUI resetPasswordGUI,
                      PasswordDoesNotMatchGUI passwordDoesNotMatchGUI) {
        this.loginGUI = loginGUI;
        this.bankDataBase = bankDataBase;
        this.resetPasswordGUI = resetPasswordGUI;
        this.passwordDoesNotMatchGUI = passwordDoesNotMatchGUI;

        Initialize();
    }

    public void Initialize() {
        bank_app_GUI_frame = new JFrame("banking app");
        frame_cp = bank_app_GUI_frame.getContentPane();
        frame_cp.setBackground(new Color(169, 223, 191));
        bank_app_GUI_frame.setLayout(new GridLayout(8, 1, 5, 10));

        String welcome_mssg = "Hello " + loginGUI.getUsername();

        // inserting a welcome message to the user at the very top of the frame
        welcome_label = new JLabel(welcome_mssg);
        welcome_label.setFont(new Font("Garamond", Font.BOLD, 40));
        welcome_label.setHorizontalAlignment(SwingConstants.CENTER);
        frame_cp.add(welcome_label);

        // inserting the label asking the user what they would like to do?
        label = new JLabel("What would you like to do today?");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Dialog", Font.PLAIN, 20));
        frame_cp.add(label);

        // inserting the current balance label
        current_balance_label = new JLabel("Current Balance");
        current_balance_label.setFont(new Font("Dialog", Font.PLAIN, 18));
        current_balance_label.setHorizontalAlignment(SwingConstants.CENTER);
        frame_cp.add(current_balance_label);

        // inserting the text field to show the current balance within the user account
        current_balance_txt_field = new JTextField(String.format("$    %,.2f", bankDataBase.getCurrentBankBalance(loginGUI.getUsername())));
        current_balance_txt_field.setEditable(false);
        current_balance_txt_field.setHorizontalAlignment(JTextField.RIGHT);  // makes the text in the text field appear from right to left
        current_balance_txt_field.setFont(new Font("Monaco", Font.BOLD, 24));
        frame_cp.add(current_balance_txt_field);

        frame_cp.add(new JLabel());  // inserting an empty label

        // inserting a deposit button: lets the user add money into their account
        deposit_button = new JButton("Deposit");
        // adding action listener to the deposit button
        deposit_button.addActionListener(new DepositButtonActionListener(bankDataBase, this, loginGUI, loginGUI.getUsername()));
        deposit_button.setFont(new Font("Monaco", Font.BOLD, 16));
        frame_cp.add(deposit_button);

        // inserting the withdraw button: lets the user take money out of their account
        withdraw_button = new JButton("Withdraw");
        // adding action listener to the withdraw button
        withdraw_button.addActionListener(new WithdrawButtonActionListener(bankDataBase, loginGUI, this));
        withdraw_button.setFont(new Font("Monaco", Font.BOLD, 16));
        frame_cp.add(withdraw_button);

        // inserting the logout button: lets the user logout of their account
        logout_button = new JButton("Logout");
        logout_button.addActionListener(new LogoutButtonActionListener(this, bankDataBase, resetPasswordGUI, passwordDoesNotMatchGUI));
        logout_button.setFont(new Font("Monaco", Font.BOLD, 16));
        frame_cp.add(logout_button);


        bank_app_GUI_frame.setSize(400, 550);
        bank_app_GUI_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        bank_app_GUI_frame.setResizable(false);
        bank_app_GUI_frame.setLocationRelativeTo(null);
        bank_app_GUI_frame.setVisible(true);
    }

    /**
     * Disposes the BankAppGUI frame
     */
    public void DisposeBankAppGUI() {
        bank_app_GUI_frame.dispose();
    }

    /**
     * sets the text in the current_balance_txt_field into the updated current bank balance
     * @param amount
     */
    public void setCurrent_balance_txt_field(float amount) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        nf.setGroupingUsed(true);
        String formattedAmount = nf.format(amount);
        current_balance_txt_field.setText("$ " + formattedAmount);
    }
}
