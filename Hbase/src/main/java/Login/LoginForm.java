package Login ;
import javax.swing.*;

import JFrame.HBaseJFrameIntegration;

import java.awt.event.*;

public class LoginForm extends JFrame {
    private JLabel nameLabel, passLabel;
    private JTextField nameField;
    private JPasswordField passField;
    private JButton loginButton;

    public LoginForm() {
        setTitle("Login Form");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        nameLabel = new JLabel("Username:");
        passLabel = new JLabel("Password:");

        nameField = new JTextField(20);
        passField = new JPasswordField(20);

        loginButton = new JButton("Login");

        setLayout(null);

        nameLabel.setBounds(10, 10, 80, 25);
        passLabel.setBounds(10, 40, 80, 25);

        nameField.setBounds(100, 10, 160, 25);
        passField.setBounds(100, 40, 160, 25);

        loginButton.setBounds(100, 80, 80, 25);

        add(nameLabel);
        add(passLabel);
        add(nameField);
        add(passField);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = nameField.getText();
                String password = new String(passField.getPassword());

                // Kiểm tra tên người dùng và mật khẩu ở đây
                if (username.equals("admin") && password.equals("admin")) {
                    JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");

                    // Chuyển sang JFrame HbaseJFrameIntegration mới
                    new HBaseJFrameIntegration().setVisible(true);
                    dispose(); // Đóng JFrame hiện tại
                } else {
                    JOptionPane.showMessageDialog(null, "Đăng nhập thất bại. Vui lòng kiểm tra lại thông tin!");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }
}
