import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {

    JTextField usernameField;
    JPasswordField passwordField;

    public LoginPage() {

        setTitle("Login");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // COLORS
        Color bg = new Color(30, 32, 40);
        Color card = new Color(45, 48, 60);
        Color buttonColor = new Color(0, 120, 215);

        // ROOT PANEL
        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(bg);

        // LOGIN CARD
        JPanel loginCard = new JPanel(new GridBagLayout());
        loginCard.setBackground(card);
        loginCard.setPreferredSize(new Dimension(450, 350));
        loginCard.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(15, 10, 15, 10);
        c.fill = GridBagConstraints.HORIZONTAL;

        // TITLE
        JLabel title = new JLabel("Login");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(JLabel.CENTER);

        // USERNAME
        JLabel userLabel = new JLabel("Enter Username");
        userLabel.setForeground(Color.LIGHT_GRAY);
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        usernameField = new JTextField(15);
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        usernameField.setPreferredSize(new Dimension(250, 35));

        // PASSWORD
        JLabel passLabel = new JLabel("Enter Password");
        passLabel.setForeground(Color.LIGHT_GRAY);
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passwordField.setPreferredSize(new Dimension(250, 35));

        // BUTTON
        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(buttonColor);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginBtn.setFocusPainted(false);

        int y = 0;

        // TITLE
        c.gridx = 0; c.gridy = y++; c.gridwidth = 2;
        loginCard.add(title, c);

        c.gridwidth = 1;

        // USERNAME (label above field)
        c.gridx = 0; c.gridy = y; c.gridwidth = 2;
        loginCard.add(userLabel, c);
        y++;

        c.gridx = 0; c.gridy = y; c.gridwidth = 2;
        loginCard.add(usernameField, c);
        y++;

        // PASSWORD
        c.gridx = 0; c.gridy = y; c.gridwidth = 2;
        loginCard.add(passLabel, c);
        y++;

        c.gridx = 0; c.gridy = y; c.gridwidth = 2;
        loginCard.add(passwordField, c);
        y++;

        // BUTTON
        c.gridx = 0; c.gridy = y; c.gridwidth = 2;
        loginCard.add(loginBtn, c);

        // LOGIN LOGIC
        loginBtn.addActionListener(e -> {
            String user = usernameField.getText().trim();
            String pass = new String(passwordField.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter username and password!");
            } 
            else if (user.equals("admin") && pass.equals("1234")) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                new PortfolioBuilder();
                dispose();
            } 
            else {
                JOptionPane.showMessageDialog(this, "Invalid Login!");
            }
        });

        root.add(loginCard);
        add(root);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPage::new);
    }
}
