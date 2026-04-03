import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;

public class PortfolioBuilder extends JFrame {

    JTextField firstName, lastName, phone, email;
    JTextArea education, skills, projects, experience;

    JLabel imageLabel, pImage, pName, pContact;
    JTextArea leftArea, rightArea;

    ImageIcon selectedImage;

    public PortfolioBuilder() {

        setTitle("Portfolio Builder");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ================= LEFT INPUT =================
        JPanel form = new JPanel(new GridLayout(10,2,10,10));

        firstName = new JTextField();
        lastName = new JTextField();
        phone = new JTextField();
        email = new JTextField();

        education = new JTextArea(2,20);
        skills = new JTextArea(2,20);
        projects = new JTextArea(2,20);
        experience = new JTextArea(2,20);

        JButton uploadBtn = new JButton("Upload Photo");
        imageLabel = new JLabel("No Image");

        uploadBtn.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();

                selectedImage = new ImageIcon(
                        new ImageIcon(file.getAbsolutePath())
                                .getImage()
                                .getScaledInstance(200,200,Image.SCALE_SMOOTH)
                );

                imageLabel.setIcon(selectedImage);
                imageLabel.setText("");
                pImage.setIcon(selectedImage); // preview also
            }
        });

        form.add(new JLabel("First Name")); form.add(firstName);
        form.add(new JLabel("Last Name")); form.add(lastName);
        form.add(new JLabel("Phone")); form.add(phone);
        form.add(new JLabel("Email")); form.add(email);

        form.add(new JLabel("Education")); form.add(new JScrollPane(education));
        form.add(new JLabel("Skills")); form.add(new JScrollPane(skills));
        form.add(new JLabel("Projects")); form.add(new JScrollPane(projects));
        form.add(new JLabel("Experience")); form.add(new JScrollPane(experience));

        form.add(uploadBtn); form.add(imageLabel);

        // ================= RIGHT PREVIEW =================
        JPanel preview = new JPanel(new BorderLayout());

        // HEADER
        JPanel header = new JPanel(new GridLayout(1,2));

        pImage = new JLabel();
        pImage.setHorizontalAlignment(JLabel.CENTER);

        JPanel blueBox = new JPanel();
        blueBox.setBackground(new Color(50,100,180));
        blueBox.setLayout(new BoxLayout(blueBox, BoxLayout.Y_AXIS));

        pName = new JLabel("Your Name");
        pName.setForeground(Color.WHITE);
        pName.setFont(new Font("Segoe UI", Font.BOLD, 26));

        pContact = new JLabel("Contact Info");
        pContact.setForeground(Color.WHITE);

        blueBox.add(Box.createVerticalStrut(30));
        blueBox.add(pName);
        blueBox.add(Box.createVerticalStrut(10));
        blueBox.add(pContact);

        header.add(pImage);
        header.add(blueBox);

        // BODY
        JPanel body = new JPanel(new GridLayout(1,2));

        leftArea = new JTextArea();
        leftArea.setEditable(false);

        rightArea = new JTextArea();
        rightArea.setEditable(false);

        body.add(new JScrollPane(leftArea));
        body.add(new JScrollPane(rightArea));

        preview.add(header, BorderLayout.NORTH);
        preview.add(body, BorderLayout.CENTER);

        // ================= BUTTONS =================
        JButton previewBtn = new JButton("Generate Preview");
        JButton saveBtn = new JButton("Save as Word");

        JPanel top = new JPanel();
        top.add(previewBtn);
        top.add(saveBtn);

        // ================= SPLIT =================
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(form), preview);
        split.setDividerLocation(450);

        add(top, BorderLayout.NORTH);
        add(split, BorderLayout.CENTER);

        // ================= ACTION =================
        previewBtn.addActionListener(e -> {
            pName.setText(firstName.getText() + " " + lastName.getText());
            pContact.setText(email.getText() + " | " + phone.getText());

            leftArea.setText(
                    "EDUCATION\n" + education.getText() +
                    "\n\nSKILLS\n" + skills.getText()
            );

            rightArea.setText(
                    "PROJECTS\n" + projects.getText() +
                    "\n\nEXPERIENCE\n" + experience.getText()
            );
        });

        saveBtn.addActionListener(e -> saveDoc());

        setVisible(true);
    }

    void saveDoc() {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("Portfolio.doc"));

        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;

        try {
            FileWriter fw = new FileWriter(chooser.getSelectedFile());

            fw.write(pName.getText() + "\n");
            fw.write(pContact.getText() + "\n\n");
            fw.write(leftArea.getText() + "\n\n");
            fw.write(rightArea.getText());

            fw.close();

            JOptionPane.showMessageDialog(this, "Saved Successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error!");
        }
    }

    public static void main(String[] args) {
        new PortfolioBuilder();
    }
}
