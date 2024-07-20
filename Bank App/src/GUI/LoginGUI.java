package GUI;

import javax.swing.*;
import java.awt.*;
import BankDataBase.BankDataBase;
import ActionListener.*;

public class LoginGUI {  // public class GUI.LoginGUI extends Database??
    private BankDataBase bank_data_base;
    private ResetPasswordGUI resetPasswordGUI;
    private PasswordDoesNotMatchGUI passwordDoesNotMatchGUI;
    //private Invalid_usernameGUI invalid_usernameGUI;

    // GUI
    private JFrame loginGUI_frame;
    private JLabel title;
    private JLabel username_text;
    private JTextField username_txt_field;
    private JLabel password_text;
    private JPasswordField password_txt_field;
    private JButton forgotten_button;
    private JButton create_account_button;

    // added invalid_usernameGUI in the constructor
    public LoginGUI(BankDataBase bank_data_base, ResetPasswordGUI resetPasswordGUI, PasswordDoesNotMatchGUI passwordDoesNotMatchGUI) {
        this.bank_data_base = bank_data_base;
        this.resetPasswordGUI = resetPasswordGUI;
        this.passwordDoesNotMatchGUI = passwordDoesNotMatchGUI;
        //this.invalid_usernameGUI = invalid_usernameGUI;

        loginGUI_frame = new JFrame("Bank App Login");
        Container frame_cp = loginGUI_frame.getContentPane();
        loginGUI_frame.setLayout(new GridLayout(8, 1, 5, 5));
        frame_cp.setBackground(new Color(169, 223, 191));

        // everything gets added to the content pane

        // title at the very top
        title = new JLabel("  BANK APP");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 18));
        frame_cp.add(title);

        // username text
        username_text = new JLabel("  Username");
        username_text.setFont(new Font("Dialog", Font.PLAIN, 14));
        frame_cp.add(username_text);

        // username text field - for user to enter their username
        username_txt_field = new JTextField();
        username_txt_field.setFont(new Font("Dialog", Font.PLAIN, 12));
        frame_cp.add(username_txt_field);

        // password text
        password_text = new JLabel("  Password");
        password_text.setFont(new Font("Dialog", Font.PLAIN, 14));
        frame_cp.add(password_text);

        // password text field - for user to enter their password
        password_txt_field = new JPasswordField();
        password_txt_field.setFont(new Font("Dialog", Font.PLAIN, 12));
        frame_cp.add(password_txt_field);


        // login button
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));
        panel.setBackground(new Color(169, 223, 191));

        JLabel empty_label = new JLabel();  // adding empty label
        panel.add(empty_label);
        panel.add(empty_label);

        JButton login_button = new JButton("login");
        // add action listener to the login button
        login_button.addActionListener(new LogInButtonActionListener(this, bank_data_base, resetPasswordGUI, passwordDoesNotMatchGUI));

        panel.add(login_button);
        frame_cp.add(panel);

        // button for forgotten username/password
        forgotten_button = new JButton("forgot password");
        // add action listener to the forgot password button
        forgotten_button.addActionListener(new ForgotButtonActionListener(bank_data_base, this.resetPasswordGUI));
        frame_cp.add(forgotten_button);

        // create an account button
        create_account_button = new JButton("create an account");
        frame_cp.add(create_account_button);
        // add action listener to the create_account_button
        create_account_button.addActionListener(new CreateAccountButtonActionListener(bank_data_base, passwordDoesNotMatchGUI));

        loginGUI_frame.setSize(300, 400);
        loginGUI_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginGUI_frame.setResizable(false);
        loginGUI_frame.setLocationRelativeTo(null);
        loginGUI_frame.setVisible(true);
    }

    /**
     * @return returns the username text field
     */
    public JTextField getUsername_txt_field() {
        return username_txt_field;
    }

    /**
     * @return returns the password text field
     */
    public JPasswordField getPassword_txt_field() {
        return password_txt_field;
    }

    /**
     * @return returns the username entered, in the login page.
     */
    public String getUsername() {
        return username_txt_field.getText();
    }

    /**
     * Dispose the LoginGUI
     */
    public void DisposeLoginGUI() {
        loginGUI_frame.dispose();
    }

}
