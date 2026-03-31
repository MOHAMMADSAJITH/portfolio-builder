import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {

    JTextField username;
    JPasswordField password;

    // 🔐 Hardcoded credentials (you can change)
    private final String USER = "admin";
    private final String PASS = "1234";

    public LoginPage() {

        setTitle("Login - Portfolio System");
        setSize(400,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(235,238,242));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);
        c.fill = GridBagConstraints.HORIZONTAL;

        username = new JTextField(15);
        password = new JPasswordField(15);

        JLabel title = new JLabel("Student Login");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JButton loginBtn = new JButton("Login");

        // Layout
        c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
        panel.add(title, c);

        c.gridwidth = 1;
        c.gridy++;
        panel.add(new JLabel("Username:"), c);

        c.gridx = 1;
        panel.add(username, c);

        c.gridx = 0; c.gridy++;
        panel.add(new JLabel("Password:"), c);

        c.gridx = 1;
        panel.add(password, c);

        c.gridx = 0; c.gridy++; c.gridwidth = 2;
        panel.add(loginBtn, c);

        // 🔥 Login Logic
        loginBtn.addActionListener(e -> login());

        add(panel);
        setVisible(true);
    }

    void login() {
        String user = username.getText();
        String pass = new String(password.getPassword());

        if(user.equals(USER) && pass.equals(PASS)) {

            JOptionPane.showMessageDialog(this, "Login Successful!");

            dispose(); // close login page

            // 👉 Open your existing app
            new PortfolioBuilder();

        } else {
            JOptionPane.showMessageDialog(this, "Invalid Username or Password!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPage::new);
    }
}
