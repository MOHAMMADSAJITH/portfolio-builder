import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.net.URI;
import java.awt.Desktop;

public class PortfolioBuilder extends JFrame {

    JTextField firstName, lastName, age, linkedin, github, email;
    JTextArea hobbies;
    JComboBox<String> genderBox, degreeBox, collegeBox;
    JCheckBox java, python, web, ai;
    JLabel photoStatus;
    File photoFile;

    Color bg = new Color(235,238,242);
    Color card = Color.WHITE;

    public PortfolioBuilder() {

        setTitle("Student Portfolio Generator");
        setSize(720,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(bg);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(card);
        form.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8,8,8,8);
        c.fill = GridBagConstraints.HORIZONTAL;

        firstName = field();
        lastName = field();
        linkedin = field();
        github = field();
        email = field();
        age = numberField();
        hobbies = new JTextArea(3,20);

        genderBox = new JComboBox<>(new String[]{
                "Select Gender","Female","Male","Other"
        });

        degreeBox = new JComboBox<>(new String[]{
                "Select Qualification",
                "Bachelor's Degree",
                "Diploma",
                "Doctorate (PhD)",
                "Master's Degree"
        });

        collegeBox = new JComboBox<>(new String[]{
                "Select College / University",
                "ACE Engineering College",
                "Anurag University",
                "Aurora's Technological and Research Institute",
                "B V Raju Institute of Technology",
                "Chaitanya Bharathi Institute of Technology",
                "CMR College of Engineering and Technology",
                "CVR College of Engineering",
                "Geethanjali College of Engineering and Technology",
                "Gokaraju Rangaraju Institute of Engineering and Technology",
                "G Narayanamma Institute of Technology and Science for Women",
                "Institute of Aeronautical Engineering",
                "Jawaharlal Nehru Technological University Hyderabad College of Engineering",
                "Kakatiya Institute of Technology and Science",
                "KG Reddy College of Engineering and Technology",
                "Mahatma Gandhi Institute of Technology",
                "Malla Reddy College of Engineering",
                "Malla Reddy Engineering College",
                "Malla Reddy Institute of Engineering and Technology",
                "Maturi Venkata Subba Rao Engineering College",
                "Methodist College of Engineering and Technology",
                "Muffakham Jah College of Engineering and Technology",
                "MVSR Engineering College",
                "Neil Gogte Institute of Technology",
                "Osmania University College of Engineering",
                "Sreenidhi Institute of Science and Technology",
                "Stanley College of Engineering and Technology for Women",
                "Vardhaman College of Engineering",
                "Vasavi College of Engineering",
                "VNR Vignana Jyothi Institute of Engineering and Technology"
        });

        java = new JCheckBox("Java");
        python = new JCheckBox("Python");
        web = new JCheckBox("Web Development");
        ai = new JCheckBox("Artificial Intelligence / Machine Learning");

        int y = 0;
        row(form,c,y++,"First Name",firstName);
        row(form,c,y++,"Last Name",lastName);
        row(form,c,y++,"Gender",genderBox);
        row(form,c,y++,"Age",age);
        row(form,c,y++,"Highest Qualification",degreeBox);
        row(form,c,y++,"College / University",collegeBox);
        row(form,c,y++,"Technical Skills",skills());
        row(form,c,y++,"Hobbies & Interests",new JScrollPane(hobbies));
        row(form,c,y++,"Email Address",email);
        row(form,c,y++,"LinkedIn Profile",linkedin);
        row(form,c,y++,"GitHub Profile",github);

        JButton upload = new JButton("Upload Photograph");
        photoStatus = new JLabel("No File Selected");

        upload.addActionListener(e -> {
            JFileChooser j = new JFileChooser();
            if (j.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                photoFile = j.getSelectedFile();
                photoStatus.setText(photoFile.getName());
            }
        });

        JButton generate = new JButton("Generate Portfolio");
        generate.addActionListener(e -> showPortfolio());

        c.gridx=0;c.gridy=y;
        form.add(upload,c);
        c.gridx=1;
        form.add(photoStatus,c);
        y++;

        c.gridx=0;c.gridy=y;c.gridwidth=2;
        form.add(generate,c);

        root.add(new JScrollPane(form),BorderLayout.CENTER);
        add(root);
        setVisible(true);
    }

    JTextField field() { return new JTextField(14); }

    JTextField numberField() {
        JTextField f = new JTextField(5);
        ((AbstractDocument) f.getDocument()).setDocumentFilter(new DocumentFilter() {

            private boolean isValid(String text) {
                return text.matches("\\d*");
            }

            public void insertString(FilterBypass fb,int o,String t,AttributeSet a) throws BadLocationException {
                String newText = fb.getDocument().getText(0, fb.getDocument().getLength());
                newText = newText.substring(0,o) + t + newText.substring(o);
                if(isValid(newText)) super.insertString(fb,o,t,a);
            }

            public void replace(FilterBypass fb,int o,int l,String t,AttributeSet a) throws BadLocationException {
                String current = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = current.substring(0,o) + t + current.substring(o+l);
                if(isValid(newText)) super.replace(fb,o,l,t,a);
            }
        });
        return f;
    }

    JPanel skills() {
        JPanel p=new JPanel();
        p.add(java); p.add(python); p.add(web); p.add(ai);
        return p;
    }

    void row(JPanel p, GridBagConstraints c, int y, String label, Component comp) {
        c.gridx=0; c.gridy=y;
        p.add(new JLabel(label),c);
        c.gridx=1;
        p.add(comp,c);
    }

    void showPortfolio() {

        if(firstName.getText().trim().isEmpty() || lastName.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,"Please enter your name.");
            return;
        }

        if(genderBox.getSelectedIndex()==0 || degreeBox.getSelectedIndex()==0 || collegeBox.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(this,"Please complete all dropdown selections.");
            return;
        }

        ArrayList<String> skillsList=new ArrayList<>();
        if(java.isSelected()) skillsList.add("Java");
        if(python.isSelected()) skillsList.add("Python");
        if(web.isSelected()) skillsList.add("Web Development");
        if(ai.isSelected()) skillsList.add("Artificial Intelligence / Machine Learning");

        if(skillsList.isEmpty()) skillsList.add("No skills selected");

        JFrame view=new JFrame("Professional Portfolio");
        view.setSize(750,850);
        view.setLocationRelativeTo(null);

        JPanel main=new JPanel(new BorderLayout());
        main.setBackground(bg);

        JPanel cardPanel=new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel,BoxLayout.Y_AXIS));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createEmptyBorder(30,40,30,40));

        JLabel name=new JLabel(firstName.getText()+" "+lastName.getText());
        name.setFont(new Font("Segoe UI",Font.BOLD,32));
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardPanel.add(name);
        cardPanel.add(Box.createVerticalStrut(20));

        if(photoFile!=null){
            Image img=new ImageIcon(photoFile.getAbsolutePath())
                    .getImage().getScaledInstance(130,130,Image.SCALE_SMOOTH);
            JLabel pic=new JLabel(new ImageIcon(img));
            pic.setAlignmentX(Component.CENTER_ALIGNMENT);
            cardPanel.add(pic);
            cardPanel.add(Box.createVerticalStrut(25));
        }

        cardPanel.add(section("Personal Information",
                "Age: "+age.getText(),
                "Gender: "+genderBox.getSelectedItem()));

        cardPanel.add(section("Education",
                "Qualification: "+degreeBox.getSelectedItem(),
                "Institution: "+collegeBox.getSelectedItem()));

        cardPanel.add(section("Technical Skills",
                String.join(", ",skillsList)));

        // 🔥 CLICKABLE CONTACT SECTION
        JPanel contactPanel = new JPanel();
        contactPanel.setLayout(new BoxLayout(contactPanel, BoxLayout.Y_AXIS));
        contactPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        contactPanel.setBorder(BorderFactory.createEmptyBorder(10,0,15,0));

        JLabel title = new JLabel("Contact Information");
        title.setFont(new Font("Segoe UI",Font.BOLD,18));
        contactPanel.add(title);
        contactPanel.add(Box.createVerticalStrut(5));

        JLabel emailLabel = new JLabel("Email: " + email.getText());
        contactPanel.add(emailLabel);

        JLabel linkedinLabel = new JLabel("<html><a href=''>LinkedIn Profile</a></html>");
        linkedinLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        linkedinLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                try {
                    String input = linkedin.getText().trim();
                    String url = input.startsWith("http") ? input :
                            "https://www.linkedin.com/in/" + input;
                    Desktop.getDesktop().browse(new URI(url));
                } catch (Exception ex) {}
            }
        });
        contactPanel.add(linkedinLabel);

        JLabel githubLabel = new JLabel("<html><a href=''>GitHub Profile</a></html>");
        githubLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        githubLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                try {
                    String input = github.getText().trim();
                    String url = input.startsWith("http") ? input :
                            "https://github.com/" + input;
                    Desktop.getDesktop().browse(new URI(url));
                } catch (Exception ex) {}
            }
        });
        contactPanel.add(githubLabel);

        cardPanel.add(contactPanel);

        cardPanel.add(section("Hobbies & Interests",
                hobbies.getText()));

        main.add(cardPanel,BorderLayout.CENTER);
        view.add(main);
        view.setVisible(true);
    }

    JPanel section(String title,String... lines){
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setBorder(BorderFactory.createEmptyBorder(10,0,15,0));

        JLabel t=new JLabel(title);
        t.setFont(new Font("Segoe UI",Font.BOLD,18));
        panel.add(t);
        panel.add(Box.createVerticalStrut(5));

        for(String s:lines){
            JLabel l=new JLabel(s);
            l.setFont(new Font("Segoe UI",Font.PLAIN,14));
            panel.add(l);
        }
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PortfolioBuilder::new);
    }
}
