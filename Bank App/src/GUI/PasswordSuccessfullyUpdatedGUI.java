package GUI;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class PasswordSuccessfullyUpdatedGUI {

    private JFrame successfully_updated_frame;
    private Container frame_cp;
    private JLabel successfully_updated_label;


    /**
     * GUI used to display that password has been successfully updated
     */
    public PasswordSuccessfullyUpdatedGUI() {
        successfully_updated_frame = new JFrame("password successfully updated");
        frame_cp = successfully_updated_frame.getContentPane();
        successfully_updated_frame.setLayout(new FlowLayout());


        JLabel successfully_updated_label = new JLabel("Password has been successfully updated");
        frame_cp.add(successfully_updated_label);

        successfully_updated_frame.setSize(300, 90);
        successfully_updated_frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        successfully_updated_frame.setResizable(false);
        successfully_updated_frame.setLocationRelativeTo(null);
        successfully_updated_frame.setVisible(true);
    }
}
