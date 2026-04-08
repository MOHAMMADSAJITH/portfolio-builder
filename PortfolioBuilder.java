import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.io.File;
import java.awt.Component;
import com.itextpdf.text.Anchor;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class PortfolioBuilder extends JFrame {

    JTextField firstName, lastName, age, linkedin, github, email, phone, location;
    JComboBox<String> genderBox, qualificationBox;

    JLabel photoLabel;
    File photoFile;

    ArrayList<JTextField> educationFields = new ArrayList<>();
    ArrayList<JTextField> projectFields = new ArrayList<>();
    ArrayList<JTextField> skillsFields = new ArrayList<>();
ArrayList<JTextField> certificationFields = new ArrayList<>();

    JPanel form;
    GridBagConstraints c;
    int y = 0;

    JPanel educationPanel = new JPanel();
    JPanel projectPanel = new JPanel();
    JPanel skillsPanel = new JPanel();
    JPanel certificationPanel = new JPanel();

    public PortfolioBuilder() {

        setTitle("Student Portfolio Generator");
        setSize(700,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel outer = new JPanel(new GridBagLayout());
        outer.setBackground(Color.WHITE);

        form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        c = new GridBagConstraints();
        c.insets = new Insets(8,8,8,8);
        c.fill = GridBagConstraints.HORIZONTAL;

        firstName = new JTextField(15);
        lastName = new JTextField(15);
        age = new JTextField(5);
        phone = new JTextField(15);
        email = new JTextField(15);
        location = new JTextField(15);
        linkedin = new JTextField(15);
        github = new JTextField(15);

        genderBox = new JComboBox<>(new String[]{"Male","Female","Other"});
qualificationBox = new JComboBox<>(new String[]{"Diploma","Bachelor","Master","PhD"});
genderBox.setSelectedIndex(-1);
qualificationBox.setSelectedIndex(-1);

        addRow("First Name", firstName);
        addRow("Last Name", lastName);
        addRow("Gender", genderBox);
        addRow("Age", age);
        addRow("Phone", phone);
        addRow("Location", location);
        addRow("Qualification", qualificationBox);
        addRow("Email", email);
        addRow("LinkedIn", linkedin);
        addRow("GitHub", github);

        // EDUCATION
        educationPanel.setLayout(new BoxLayout(educationPanel, BoxLayout.Y_AXIS));
        JButton addEdu = new JButton("+ Add Education");
        addEdu.addActionListener(e -> {
            JTextField f = new JTextField(15);
            educationFields.add(f);
            educationPanel.add(f);
            educationPanel.revalidate();
        });
        addRow("Education", addEdu);
        addRow("", educationPanel);

        // PROJECTS
        projectPanel.setLayout(new BoxLayout(projectPanel, BoxLayout.Y_AXIS));
        JButton addProj = new JButton("+ Add Project");
        addProj.addActionListener(e -> {
            JTextField f = new JTextField(15);
            projectFields.add(f);
            projectPanel.add(f);
            projectPanel.revalidate();
        });
        addRow("Projects", addProj);
        addRow("", projectPanel);

        // SKILLS
        skillsPanel.setLayout(new BoxLayout(skillsPanel, BoxLayout.Y_AXIS));
        JButton addSkill = new JButton("+ Add Skill");
        addSkill.addActionListener(e -> {
            JTextField f = new JTextField(15);
            skillsFields.add(f);
            skillsPanel.add(f);
            skillsPanel.revalidate();
        });
        addRow("Skills", addSkill);
        addRow("", skillsPanel);

        // CERTIFICATIONS
certificationPanel.setLayout(new BoxLayout(certificationPanel, BoxLayout.Y_AXIS));

JButton addCert = new JButton("+ Add Certification");
addCert.addActionListener(e -> {
    JTextField f = new JTextField(15);
    certificationFields.add(f);
    certificationPanel.add(f);
    certificationPanel.revalidate();
});

addRow("Certifications", addCert);
addRow("", certificationPanel);
        // PHOTO
        JButton uploadBtn = new JButton("Upload Photo");
        photoLabel = new JLabel("No file");

        uploadBtn.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
                photoFile = chooser.getSelectedFile();
                photoLabel.setText(photoFile.getName());
            }
        });

        addRow("Photo", uploadBtn);
        addRow("", photoLabel);

        // GENERATE BUTTON
        JButton generate = new JButton("Generate PDF");
        generate.addActionListener(e -> generatePDF());

        c.gridx = 0;
        c.gridy = y;
        c.gridwidth = 2;
        form.add(generate, c);

        outer.add(form);
        add(new JScrollPane(outer));

        setVisible(true);
    }

    void addRow(String label, Component comp){
        c.gridx = 0;
        c.gridy = y;
        c.gridwidth = 1;
        form.add(new JLabel(label), c);

        c.gridx = 1;
        form.add(comp, c);

        y++;
    }

    void generatePDF() {
        try {
            JFileChooser chooser = new JFileChooser();

            if(chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){

                String path = chooser.getSelectedFile().getAbsolutePath();
                if(!path.endsWith(".pdf")) path += ".pdf";

                Document doc = new Document();
                PdfWriter.getInstance(doc, new FileOutputStream(path));
                doc.open();

                com.itextpdf.text.Font nameFont =
                        FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
                com.itextpdf.text.Font headingFont =
                        FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
                com.itextpdf.text.Font normalFont =
                        FontFactory.getFont(FontFactory.HELVETICA, 12);

                // ✅ PHOTO IN PDF
                if(photoFile != null){
                   com.itextpdf.text.Image img =
        com.itextpdf.text.Image.getInstance(photoFile.getAbsolutePath());
                    img.scaleToFit(100, 100);
                    img.setAlignment(Element.ALIGN_CENTER);
                    doc.add(img);
                }

                // NAME
                Paragraph name = new Paragraph(firstName.getText()+" "+lastName.getText(), nameFont);
                name.setAlignment(Element.ALIGN_CENTER);
                doc.add(name);

                // CONTACT
                Paragraph contact = new Paragraph("+91 "+phone.getText()+" | "+email.getText(), normalFont);
                contact.setAlignment(Element.ALIGN_CENTER);
                doc.add(contact);
         // LinkedIn
String link = linkedin.getText().trim();
if(!link.startsWith("http")){
    link = "https://" + link;
}
Anchor linkedinLink = new Anchor("LinkedIn : " + link, normalFont);
linkedinLink.setReference(link);

// GitHub
String git = github.getText().trim();
if(!git.startsWith("http")){
    git = "https://" + git;
}
Anchor githubLink = new Anchor("GitHub : " + git, normalFont);
githubLink.setReference(git);

// Combine
Paragraph links = new Paragraph();
links.setAlignment(Element.ALIGN_CENTER);
links.add(linkedinLink);
links.add(Chunk.NEWLINE); // moves GitHub to next line
links.add(githubLink);

doc.add(links);

                // ADDRESS
                Paragraph addr = new Paragraph(location.getText(), normalFont);
                addr.setAlignment(Element.ALIGN_CENTER);
                doc.add(addr);

                doc.add(new Paragraph("\n                              \n"));
                doc.add(new Paragraph("\n                              \n"));

                // PERSONAL INFO
                doc.add(new Paragraph("PERSONAL INFO", headingFont));
                doc.add(new Paragraph("Age: "+age.getText(), normalFont));
                doc.add(new Paragraph("Gender: "+genderBox.getSelectedItem(), normalFont));
                doc.add(new Paragraph("Qualification: "+qualificationBox.getSelectedItem(), normalFont));

                doc.add(new Paragraph("\n----------------------------------------\n"));

                // EDUCATION
                doc.add(new Paragraph("EDUCATION", headingFont));
                for(JTextField f : educationFields){
                    if(!f.getText().isEmpty())
                        doc.add(new Paragraph("• "+f.getText(), normalFont));
                }

                doc.add(new Paragraph("\n----------------------------------------\n"));

                // PROJECTS
                doc.add(new Paragraph("PROJECTS", headingFont));
                for(JTextField f : projectFields){
                    if(!f.getText().isEmpty())
                        doc.add(new Paragraph("• "+f.getText(), normalFont));
                }

                doc.add(new Paragraph("\n----------------------------------------\n"));

                // SKILLS
                doc.add(new Paragraph("SKILLS", headingFont));
                for(JTextField f : skillsFields){
                    if(!f.getText().isEmpty())
                        doc.add(new Paragraph("• "+f.getText(), normalFont));
                }
doc.add(new Paragraph("\n----------------------------------------\n"));

// CERTIFICATIONS
doc.add(new Paragraph("CERTIFICATIONS", headingFont));
for(JTextField f : certificationFields){
    if(!f.getText().isEmpty())
        doc.add(new Paragraph("• " + f.getText(), normalFont));
}


                doc.close();

                JOptionPane.showMessageDialog(this,"PDF Saved Successfully!");
            }

        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args){
        new PortfolioBuilder();
    }
}