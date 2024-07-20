package ActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

import BankDataBase.*;
import GUI.PasswordDoesNotMatchGUI;
import GUI.UsernameAlreadyExistsGUI;

/**
 * Used to get all the information needed for the client to get started with their account.
 * information such as: username, password, first name, last name, SIN number
 */
public class CreateAccountButtonActionListener implements ActionListener {

    private BankDataBase bank_data_base;
    private PasswordDoesNotMatchGUI passwordDoesNotMatchGUI;

    // GUI
    private JFrame create_account_frame;
    private JLabel first_name_label;
    private JTextField first_name_txt_field;
    private JLabel last_name_label;
    private JTextField last_name_txt_field;
    private JLabel username_label;
    private JTextField username_txt_field;
    private JLabel password_label;
    private JPasswordField password_txt_field;
    private JLabel re_enter_password_label;
    private JPasswordField re_enter_password_txt_field;
    private JLabel sin_label;
    private JTextField sin_txt_field;
    private JButton create_account_button;


    public CreateAccountButtonActionListener(BankDataBase bank_data_base, PasswordDoesNotMatchGUI passwordDoesNotMatchGUI) {
        this.bank_data_base = bank_data_base;
        this.passwordDoesNotMatchGUI = passwordDoesNotMatchGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // pop a new screen
        InitializeFrame();

    }

    public void InitializeFrame() {
        // personal information consists of:

        // first name
        // last name
        // username
        // password
        // sin number

        create_account_frame = new JFrame("create new account");
        Container frame_cp = create_account_frame.getContentPane();
        create_account_frame.setLayout(new BorderLayout());

        // dividing the frame into upper and lower panel

        // upper panel: information
        JPanel upper_panel = new JPanel();
        upper_panel.setBackground(new Color(212, 239, 223));
        upper_panel.setBounds(0, 20, create_account_frame.getWidth()-40, 10);

        // information label
        JLabel info_label = new JLabel("please fill up the following information before creating a new account");
        info_label.setFont(new Font("Dialog", Font.BOLD, 14));
        upper_panel.add(info_label);

        frame_cp.add(upper_panel, BorderLayout.NORTH);  // adding the upper panel to the fame content pane


        // lower panel: for user to enter their personal information
        JPanel lower_panel = new JPanel();
        lower_panel.setBounds(30, 35, create_account_frame.getWidth()-40, 10);
        lower_panel.setBackground(new Color(169, 223, 191));
        lower_panel.setLayout(new GridLayout(7, 2, 4, 5));

            // first name label
        first_name_label = new JLabel("    First name ");
        first_name_label.setFont(new Font("Dialog", Font.PLAIN, 14));
        lower_panel.add(first_name_label);

            // first name text field
        first_name_txt_field = new JTextField();
        first_name_txt_field.setFont(new Font("Dialog", Font.PLAIN, 12));
        lower_panel.add(first_name_txt_field);

            // last name label
        last_name_label = new JLabel("   Last name ");
        last_name_label.setFont(new Font("Dialog", Font.PLAIN, 14));
        lower_panel.add(last_name_label);

            // last name text field
        last_name_txt_field = new JTextField();
        first_name_txt_field.setFont(new Font("Dialog", Font.PLAIN, 12));
        lower_panel.add(last_name_txt_field);

            // username label
        username_label = new JLabel("    username ");
        username_label.setFont(new Font("Dialog", Font.PLAIN, 14));
        lower_panel.add(username_label);

            // username text field
        username_txt_field = new JTextField();
        username_txt_field.setFont(new Font("Dialog", Font.PLAIN, 12));
        lower_panel.add(username_txt_field);

            // password label
        password_label = new JLabel("    password ");
        password_label.setFont(new Font("Dialog", Font.PLAIN, 14));
        lower_panel.add(password_label);

            // password text field
        password_txt_field = new JPasswordField();
        password_txt_field.setFont(new Font("Dialog", Font.PLAIN, 12));
        lower_panel.add(password_txt_field);

            // re enter password label
        re_enter_password_label = new JLabel("    re-enter password ");
        re_enter_password_label.setFont(new Font("Dialog", Font.PLAIN, 14));
        lower_panel.add(re_enter_password_label);

            // re enter password text field
        re_enter_password_txt_field = new JPasswordField();
        re_enter_password_txt_field.setFont(new Font("Dialog", Font.PLAIN, 12));
        lower_panel.add(re_enter_password_txt_field);

            // sin number label
        sin_label = new JLabel("    SIN number ");
        sin_label.setFont(new Font("Dialog", Font.PLAIN, 14));
        lower_panel.add(sin_label);

            // sin number text field
        sin_txt_field = new JTextField();
        sin_txt_field.setFont(new Font("Dialog", Font.PLAIN, 12));
        lower_panel.add(sin_txt_field);

        lower_panel.add(new JLabel());  // adding an empty label

            // create account button
        create_account_button = new JButton("create account");
        create_account_button.addActionListener(new InnerCreate_Account_Button_ActionListener(this,
                bank_data_base, passwordDoesNotMatchGUI));
        lower_panel.add(create_account_button);

        frame_cp.add(lower_panel, BorderLayout.CENTER);  // adding lower panel to the frame content pane


        create_account_frame.setSize(500, 450);
        create_account_frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        create_account_frame.setResizable(false);
        create_account_frame.setLocationRelativeTo(null);
        create_account_frame.setVisible(true);
    }

    /**
     * @return returns the username of the client.
     */
    private String getClientUsername() {
        return username_txt_field.getText();
    }

    /**
     * @return returns the password of the client.
     */
    private String getClientPassword() {
        return password_txt_field.getText();
    }

    /**
     * @return returns the re-entered password entered by the user.
     */
    private String getRe_enteredPassword() {
        return re_enter_password_txt_field.getText();
    }

    /**
     * @return returns the first name of the client.
     */
    private String getClientFirstName() {
        return first_name_txt_field.getText();
    }

    /**
     * @return returns the last name of the client.
     */
    private String getClientLastName() {
        return last_name_txt_field.getText();
    }

    /**
     * @return returns the SIN number of the client.
     */
    private String getSIN_Number() {
        return sin_txt_field.getText();
    }

    /**
     * Used to get the create new account button from the Invalid_usernameGUI. Uses the method from the Invalid_usernameGUI
     * to return the button.
     * @return returns the create new account button from the Invalid_usernameGUI
     */


    /**
     * Action Listener for the create account button.
     * Store the client's information into the bank's database.
     */
    private static class InnerCreate_Account_Button_ActionListener implements ActionListener {
        private CreateAccountButtonActionListener createAccountButtonActionListener;
        private BankDataBase bank_data_base;
        private PasswordDoesNotMatchGUI passwordDoesNotMatchGUI;
        private InnerCreate_Account_Button_ActionListener(CreateAccountButtonActionListener createAccountButtonActionListener,
                                                          BankDataBase dataBase, PasswordDoesNotMatchGUI passwordDoesNotMatchGUI) {
            this.createAccountButtonActionListener = createAccountButtonActionListener;
            this.bank_data_base = dataBase;
            this.passwordDoesNotMatchGUI = passwordDoesNotMatchGUI;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // check whether all the information is filled out

            if(createAccountButtonActionListener.getClientFirstName().length() == 0) {  // first name has not been entered
                System.out.println("PLEASE ENTER YOUR FIRST NAME");
                return;
            }

            if (createAccountButtonActionListener.getClientLastName().length() == 0) {  // last name has not been entered
                System.out.println("PLEASE ENTER YOUR LAST NAME");
                return;
            }

            if (createAccountButtonActionListener.getClientUsername().length() == 0) {  // username has not been entered
                System.out.println("PLEASE ENTER YOUR USERNAME");
                return;
            }

            if (createAccountButtonActionListener.getClientPassword().length() == 0) {  // password has not been entered
                System.out.println("PLEASE ENTER YOUR PASSWORD");
                return;
            }

            if (createAccountButtonActionListener.getRe_enteredPassword().length() == 0) {  // password has not been re-entered
                System.out.println("PLEASE RE-ENTER YOUR PASSWORD");
                return;
            }

            if (createAccountButtonActionListener.getSIN_Number().length() == 0) {  // sin number has not been entered
                System.out.println("PLEASE ENTER YOUR SIN NUMBER");
                return;
            }

            if (createAccountButtonActionListener.getSIN_Number().length() > 0) {
                // check whether all are digits
                try {
                    Integer.parseInt(createAccountButtonActionListener.getSIN_Number());
                } catch (NumberFormatException nfe) {
                    System.out.println("SIN not valid");
                    return;
                }
            }

            // check whether the new account already exists or not --- NEEDS TO BE IMPLEMENTED
            String client_username = createAccountButtonActionListener.getClientUsername();
            String client_password = createAccountButtonActionListener.getClientPassword();

            boolean does_username_alrdy_exist = bank_data_base.Check_username(client_username);

            if(does_username_alrdy_exist) {  // username already exists
                // pop a new screen saying the username already exists. Please enter another username
                new UsernameAlreadyExistsGUI();
                System.out.println("username already exists");

            } else {  // username does not exist
                // check whether re-enter password matches with the initial password.
                // if not, make the user to enter the password again.
                String re_entered_password = createAccountButtonActionListener.getRe_enteredPassword();

                if(!client_password.equals(re_entered_password)) {  // if the password do not match
                    passwordDoesNotMatchGUI.Initialize();  // pop the PasswordDoesNotMatch GUI
                } else {  // password do match
                    //String client_username = createAccountButtonActionListener.getClientUsername();
                    String client_first_name = createAccountButtonActionListener.getClientFirstName();
                    String client_last_name = createAccountButtonActionListener.getClientLastName();
                    String client_SIN_number = createAccountButtonActionListener.getSIN_Number();

                    // store all the information into the bank's database
                    bank_data_base.Add(client_username, client_password, client_first_name, client_last_name, client_SIN_number);
                    createAccountButtonActionListener.create_account_frame.dispose();
                }
            }
        }
    }
}
