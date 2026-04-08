import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

// ✅ Correct PDF imports (no conflict)
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PortfolioBuilder extends JFrame {

    JTextField firstName, lastName, age, email;
    JTextArea hobbies;
    JCheckBox java, python;
    File photoFile;

    Color bg = new Color(30,32,40);

    public PortfolioBuilder() {

        setTitle("Portfolio Builder");
        setSize(700,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel outer = new JPanel(new GridBagLayout());
        outer.setBackground(Color.WHITE);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8,8,8,8);
        c.fill = GridBagConstraints.HORIZONTAL;

        firstName = new JTextField(15);
        lastName = new JTextField(15);
        age = new JTextField(5);
        email = new JTextField(15);
        hobbies = new JTextArea(3,20);

        java = new JCheckBox("Java");
        python = new JCheckBox("Python");

        int y=0;
        addRow(form,c,y++,"First Name",firstName);
        addRow(form,c,y++,"Last Name",lastName);
        addRow(form,c,y++,"Age",age);
        addRow(form,c,y++,"Email",email);
        addRow(form,c,y++,"Hobbies",new JScrollPane(hobbies));

        JPanel skills = new JPanel();
        skills.add(java);
        skills.add(python);
        addRow(form,c,y++,"Skills",skills);

        JButton upload = new JButton("Upload Photo");
        upload.addActionListener(e -> {
            JFileChooser j = new JFileChooser();
            if(j.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
                photoFile = j.getSelectedFile();
            }
        });

        JButton generate = new JButton("Generate Portfolio");
        generate.addActionListener(e -> showPortfolio());

        c.gridx=0;c.gridy=y;form.add(upload,c);
        c.gridx=1;form.add(generate,c);

        add(new JScrollPane(form));
        setVisible(true);
    }

    void addRow(JPanel p,GridBagConstraints c,int y,String label,Component comp){
        c.gridx=0;c.gridy=y;
        p.add(new JLabel(label),c);
        c.gridx=1;
        p.add(comp,c);
    }

    void showPortfolio(){

        if(firstName.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Enter Name");
            return;
        }

        ArrayList<String> skillsList=new ArrayList<>();
        if(java.isSelected()) skillsList.add("Java");
        if(python.isSelected()) skillsList.add("Python");

        JFrame view=new JFrame("Portfolio");
        view.setSize(900,700);
        view.setLocationRelativeTo(null);

        JPanel root=new JPanel(new GridBagLayout());
        root.setBackground(bg);

        JPanel card=new JPanel();
        card.setLayout(new BoxLayout(card,BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(500,600));
        card.setBorder(BorderFactory.createEmptyBorder(30,40,30,40));

        // ✅ FIXED IMAGE (no conflict)
        if(photoFile!=null){
            Image img=new ImageIcon(photoFile.getAbsolutePath())
                    .getImage().getScaledInstance(120,120,Image.SCALE_SMOOTH);
            JLabel pic=new JLabel(new ImageIcon(img));
            pic.setAlignmentX(Component.CENTER_ALIGNMENT);
            card.add(pic);
        }

        // ✅ FIXED FONT
        JLabel name=new JLabel(firstName.getText()+" "+lastName.getText());
        name.setFont(new Font("Segoe UI",Font.BOLD,28));
        name.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(name);
        card.add(Box.createVerticalStrut(20));

        card.add(section("👤 Info",
                "Age: "+age.getText(),
                "Email: "+email.getText()));

        card.add(section("💻 Skills",
                String.join(", ",skillsList)));

        card.add(section("🎯 Hobbies",
                hobbies.getText()));

        JButton pdfBtn=new JButton("Export PDF");
        pdfBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        pdfBtn.addActionListener(e -> generatePDF(skillsList));

        card.add(Box.createVerticalStrut(20));
        card.agitdd(pdfBtn);

        root.add(card);
        view.add(root);
        view.setVisible(true);
    }

    JPanel section(String title,String... lines){

        JPanel p=new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        p.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel t=new JLabel(title);
        t.setFont(new Font("Segoe UI",Font.BOLD,18)); // ✅ FIXED
        t.setForeground(new Color(0,120,215));

        p.add(t);

        for(String s:lines){
            p.add(new JLabel(s));
        }

        p.add(Box.createVerticalStrut(10));
        return p;
    }

    void generatePDF(ArrayList<String> skillsList){
        try{
            Document doc=new Document();
            PdfWriter.getInstance(doc,new FileOutputStream("portfolio.pdf"));

            doc.open();

            doc.add(new Paragraph("PORTFOLIO\n\n"));
            doc.add(new Paragraph("Name: "+firstName.getText()+" "+lastName.getText()));
            doc.add(new Paragraph("Email: "+email.getText()));
            doc.add(new Paragraph("Skills: "+String.join(", ",skillsList)));
            doc.add(new Paragraph("Hobbies: "+hobbies.getText()));

            doc.close();

            JOptionPane.showMessageDialog(this,"PDF Created!");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new PortfolioBuilder();
    }
}
