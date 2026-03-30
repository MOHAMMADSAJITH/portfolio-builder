import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

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
                 "Arjun College of Technology and Sciences",
                 "Aurora's Scientific and Technological Institute",
                 "Aurora's Technological and Research Institute",
                 "Avanthi Institute of Engineering and Technology",
                 "B V Raju Institute of Technology",
                 "Bharat Institute of Engineering and Technology",
                 "Brilliant Grammar School Educational Society Group of Institutions",
                 "Chaitanya Bharathi Institute of Technology",
                 "CMR College of Engineering and Technology",
                 "CMR Engineering College",
                 "CVR College of Engineering",
                 "Daripally Anantha Ramulu College of Engineering and Technology",
                 "Ellenki College of Engineering and Technology",                 
                 "Geethanjali College of Engineering and Technology",
                 "Global Institute of Engineering and Technology",
                 "Gokaraju Rangaraju Institute of Engineering and Technology",
                 "G Narayanamma Institute of Technology and Science for Women",
                 "Holy Mary Institute of Technology and Science",
                 "Institute of Aeronautical Engineering",
                 "Jaya Prakash Narayan College of Engineering",
                 "Joginpally B R Engineering College",
                 "Jawaharlal Nehru Technological University Hyderabad College of Engineering",
                 "Kakatiya Institute of Technology and Science",
                 "Keshav Memorial Institute of Technology",
                 "Khammam Institute of Technology and Sciences",
                 "KG Reddy College of Engineering and Technology",
                 "Kommuri Pratap Reddy Institute of Technology",
                 "Lords Institute of Engineering and Technology",
                 "Mahatma Gandhi Institute of Technology",
                 "Malla Reddy College of Engineering",
                 "Malla Reddy Engineering College",
                 "Malla Reddy Institute of Engineering and Technology",
                 "Maturi Venkata Subba Rao Engineering College",
                 "Methodist College of Engineering and Technology",
                 "MNR College of Engineering and Technology",
                 "Mother Teresa Institute of Science and Technology",
                 "Muffakham Jah College of Engineering and Technology",
                 "MVSR Engineering College",
                 "Narsimha Reddy Engineering College","Neil Gogte Institute of Technology",
                 "Osmania University College of Engineering",
                 "Princeton Institute of Engineering and Technology for Women",
                 "Siddhartha Institute of Engineering and Technology","Sreenidhi Institute of Science and Technology","Sri Indu College of Engineering and Technology","Sri Venkateswara Engineering College","St Martin's Engineering College","St Mary's Engineering College",
                 "Stanley College of Engineering and Technology for Women",
                 "Teegala Krishna Reddy Engineering College",
                 "Tirumala Engineering College",
                 "Vaagdevi College of Engineering",
                 "Vardhaman College of Engineering",
                 "Vasavi College of Engineering",
                 "Vathsalya Institute of Science and Technology",
                 "Vidya Jyothi Institute of Technology",
                 "Vignan Institute of Technology and Science",
                 "Vivekananda Institute of Technology and Science",
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
            public void insertString(FilterBypass fb,int o,String t,AttributeSet a) throws BadLocationException {
                if(t.matches("\\d+")) super.insertString(fb,o,t,a);
            }
            public void replace(FilterBypass fb,int o,int l,String t,AttributeSet a) throws BadLocationException {
                if(t.matches("\\d+")) super.replace(fb,o,l,t,a);
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

        if(genderBox.getSelectedIndex()==0 || degreeBox.getSelectedIndex()==0 || collegeBox.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(this,"Please complete all dropdown selections.");
            return;
        }

        ArrayList<String> skills=new ArrayList<>();
        if(java.isSelected()) skills.add("Java");
        if(python.isSelected()) skills.add("Python");
        if(web.isSelected()) skills.add("Web Development");
        if(ai.isSelected()) skills.add("Artificial Intelligence / Machine Learning");

        JFrame view=new JFrame("Professional Portfolio");
        view.setSize(750,850);
        view.setLocationRelativeTo(null);

        JPanel main=new JPanel(new BorderLayout());
        main.setBackground(bg);

        JPanel cardPanel=new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel,BoxLayout.Y_AXIS));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createEmptyBorder(30,40,30,40));

        // NAME
        JLabel name=new JLabel(firstName.getText()+" "+lastName.getText());
        name.setFont(new Font("Segoe UI",Font.BOLD,32));
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardPanel.add(name);
        cardPanel.add(Box.createVerticalStrut(20));

        // PHOTO
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
                String.join(", ",skills)));

        cardPanel.add(section("Contact Information",
                "Email: "+email.getText(),
                "LinkedIn: "+linkedin.getText(),
                "GitHub: "+github.getText()));

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
        new PortfolioBuilder();
    }
}