import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.awt.Desktop;

public class PortfolioBuilder extends JFrame {

    JTextField firstName, lastName, linkedin, github;

    public PortfolioBuilder() {
        setTitle("Portfolio Builder");
        setSize(400, 350);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("First Name:"));
        firstName = new JTextField();
        add(firstName);

        add(new JLabel("Last Name:"));
        lastName = new JTextField();
        add(lastName);

        add(new JLabel("LinkedIn Username:"));
        linkedin = new JTextField();
        add(linkedin);

        add(new JLabel("GitHub Username:"));
        github = new JTextField();
        add(github);

        JButton generateBtn = new JButton("Generate Portfolio");
        add(new JLabel());
        add(generateBtn);

        generateBtn.addActionListener(e -> showPortfolio());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void showPortfolio() {
        JFrame frame = new JFrame("Your Portfolio");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(4, 1, 10, 10));

        frame.add(new JLabel("Name: " + firstName.getText() + " " + lastName.getText()));

        // 🔗 LinkedIn Link
        JLabel linkedinLink = new JLabel("<html><a href=''>LinkedIn Profile</a></html>");
        linkedinLink.setForeground(Color.BLUE);
        linkedinLink.setCursor(new Cursor(Cursor.HAND_CURSOR));

        linkedinLink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    String input = linkedin.getText().trim();
                    String url;

                    if (input.startsWith("http")) {
                        url = input;
                    } else {
                        url = "https://www.linkedin.com/in/" + input;
                    }

                    Desktop.getDesktop().browse(new URI(url));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // 🔗 GitHub Link
        JLabel githubLink = new JLabel("<html><a href=''>GitHub Profile</a></html>");
        githubLink.setForeground(Color.BLUE);
        githubLink.setCursor(new Cursor(Cursor.HAND_CURSOR));

        githubLink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    String input = github.getText().trim();
                    String url;

                    if (input.startsWith("http")) {
                        url = input;
                    } else {
                        url = "https://github.com/" + input;
                    }

                    Desktop.getDesktop().browse(new URI(url));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        frame.add(linkedinLink);
        frame.add(githubLink);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
