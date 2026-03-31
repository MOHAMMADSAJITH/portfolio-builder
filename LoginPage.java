import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {

    JTextField usernameField;
    JPasswordField passwordField;

    public LoginPage() {
        setTitle("Login Page");
        setSize(300, 200);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginBtn = new JButton("Login");
        add(new JLabel()); // empty space
        add(loginBtn);

        loginBtn.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());

            if (user.equals("admin") && pass.equals("1234")) {
                new PortfolioBuilder(); // open main app
                dispose(); // close login window
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password!");
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}
