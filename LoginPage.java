import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {

    JTextField usernameField;
    JPasswordField passwordField;

    public LoginPage() {

        setTitle("Login");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Color bg = new Color(235,238,242);
        Color card = Color.WHITE;

        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(bg);

        JPanel loginCard = new JPanel(new GridBagLayout());
        loginCard.setBackground(card);
        loginCard.setPreferredSize(new Dimension(350, 220));
        loginCard.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Login");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setHorizontalAlignment(JLabel.CENTER);

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);

        JButton loginBtn = new JButton("Login");

        int y = 0;

        c.gridx=0; c.gridy=y++; c.gridwidth=2;
        loginCard.add(title, c);

        c.gridwidth=1;

        c.gridx=0; c.gridy=y;
        loginCard.add(new JLabel("Username:"), c);
        c.gridx=1;
        loginCard.add(usernameField, c);
        y++;

        c.gridx=0; c.gridy=y;
        loginCard.add(new JLabel("Password:"), c);
        c.gridx=1;
        loginCard.add(passwordField, c);
        y++;

        c.gridx=0; c.gridy=y; c.gridwidth=2;
        loginCard.add(loginBtn, c);

        // login logic
        loginBtn.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());

            if (user.equals("admin") && pass.equals("1234")) {
                new PortfolioBuilder();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Login!");
            }
        });

        root.add(loginCard); // center it
        add(root);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPage::new);
    }
}
